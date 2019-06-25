package com.schema.antlr.builder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schema.antlr.model.Column;
import com.schema.antlr.model.SchemaType;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author anil
 */
public class ColumnSchemaBuilder {

	private String columnNameTemplate = "{columnFamily}.{columnName}";
	private String jsonHbaseColumnNameTemplate = "{columnFamily}.JSON.{columnName}";
	private String jsonColumnNameTemplate = "JSON.{columnName}";
	private String jsonColumnTemplate = "PARENT={parent}>COLUMNS={column}";
	private String jsonColumnPartitionTemplate = "PARTITION={partition}";
	private Map<String, List<String>> jsonColumnsSchemaList;
	private Set<String> partitionList;

	public  String getColumnsSchema(List<Column> columnList, String schemaType) {
		String strColumnSchema = "";
		List<String> strColumnSchemaList = new LinkedList<String>();
		partitionList = new LinkedHashSet<>();

		for (Column columnObj : columnList) {
			jsonColumnsSchemaList = new LinkedHashMap<String, List<String>>();
			if (!StringUtils.isEmpty(columnObj.getColumnName())) {
				strColumnSchemaList.add(prepareColumnSchema(columnObj, schemaType, false));
			}
			if (columnObj.getJson() != null) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					Map<String, Object> jsonColumnsObjList = mapper.readValue(columnObj.getJson(), new TypeReference<Map<String, Object>>() {
					});
					Map<String, List<String>> jsonColumnsSchemaList = getJsonColumnSchema(schemaType, columnObj.getColumnFamily(), null, new LinkedList<String>(), jsonColumnsObjList);

					jsonColumnsSchemaList.forEach((parentKey, childSchemas) -> {
						String columnChildSchema = String.join(",", childSchemas);
						String jsonColumnSchema = jsonColumnTemplate.replaceAll("\\{parent\\}", parentKey).replaceAll("\\{column\\}", columnChildSchema);
						strColumnSchemaList.add(jsonColumnSchema);
					});
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		if (SchemaType.HIVE.getType().equals(schemaType) && !partitionList.isEmpty()) {
			String columnPartitionSchema = jsonColumnPartitionTemplate.replaceAll("\\{partition\\}", String.join(",", partitionList));
			strColumnSchemaList.add(columnPartitionSchema);
		}
		strColumnSchema = String.join(",", strColumnSchemaList);
		return strColumnSchema;

	}

	public  String getDynamicPartKey(String columnSchema) {
		String dynamicPartKey = StringUtils.substringBetween(columnSchema, "<", ">");
		return dynamicPartKey;
	}

	private Map<String, List<String>> getJsonColumnSchema(String type, String columnFamily, String parent, List<String> parents, Map<String, Object> jsonCoumns) {
		ObjectMapper mapper = new ObjectMapper();
		if (jsonCoumns.containsKey("columnName")) {
			Column column = mapper.convertValue(jsonCoumns, Column.class);
			column.setColumnFamily(columnFamily);
			if (!parents.isEmpty()) {
				String parentKey = String.join("#", parents);
				if (!jsonColumnsSchemaList.containsKey(parentKey)) {
					jsonColumnsSchemaList.put(parentKey, new LinkedList<String>());
				}
				LinkedList<String> childSchemas = (LinkedList<String>) jsonColumnsSchemaList.get(parentKey);
				childSchemas.add(prepareColumnSchema(column, type, true));

			}
		} else {
			if (parent != null) {
				parents.add(parent);
			}
			jsonCoumns.forEach((parentKey, childJsonSchema) -> {
				List<String> newParents = new LinkedList<>(parents);
				Map<String, Object> nestedJsonCoulmns = (Map<String, Object>) childJsonSchema;
				Map<String, List<String>> jsonCoumnSchema = getJsonColumnSchema(type, columnFamily, parentKey, newParents, nestedJsonCoulmns);
				jsonColumnsSchemaList.putAll(jsonCoumnSchema);
			});

		}

		return jsonColumnsSchemaList;

	}

	private String prepareColumnSchema(Column column, String schemaType, boolean isJsonColumn) {
		String columnSchema = "";
		String columnSchemaName = column.getColumnName();
		List<String> columnFields = new LinkedList<String>();
		columnFields.add(StringUtils.isEmpty(column.getDataType()) ? "" : column.getDataType());
		columnFields.add(StringUtils.isEmpty(column.getDefaultValue()) ? "" : column.getDefaultValue());
		switch (SchemaType.valueOf(schemaType)) {
			case HBASE:
				if (isJsonColumn) {
					columnSchemaName = jsonHbaseColumnNameTemplate.replaceAll("\\{columnFamily\\}", column.getColumnFamily()).replaceAll("\\{columnName\\}", column.getColumnName());
				} else {
					columnSchemaName = columnNameTemplate.replaceAll("\\{columnFamily\\}", column.getColumnFamily()).replaceAll("\\{columnName\\}", column.getColumnName());
				}
				((LinkedList<String>) columnFields).addLast(StringUtils.isEmpty(column.getCompressionType()) ? "" : column.getCompressionType());
				break;
			case HDFS:
			case HIVE:
				if (isJsonColumn) {
					columnSchemaName = jsonColumnNameTemplate.replaceAll("\\{columnName\\}", column.getColumnName());
				}
				break;
		}
		((LinkedList<String>) columnFields).addFirst(columnSchemaName);
		columnSchema = String.join(":", columnFields);
		if (column.isPartitionColumn()) {
			partitionList.add(columnSchema);
		}
		return columnSchema;
	}
}
