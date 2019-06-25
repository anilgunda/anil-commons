package com.schema.antlr.builder;

import com.schema.antlr.model.Schema;
import com.schema.antlr.model.SchemaType;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @author anil
 */
public class HBaseSchemaBuilder {

	private String getSchema(Schema schema) {
		List<String> schemaFields = new LinkedList<>();
		schemaFields.add(schema.getHbaseTableName());
		RowKeySchemaBuilder rowKeySchemaBuilder=new RowKeySchemaBuilder();
		String rowKeySchema = rowKeySchemaBuilder.getRowKeySchema(schema.getRowKeyMetaData());
		schemaFields.add(rowKeySchema);
		ColumnSchemaBuilder columnSchemaBuilder=new ColumnSchemaBuilder();
		String columnsSchema = columnSchemaBuilder.getColumnsSchema(schema.getColumnList(),SchemaType.HBASE.getType());
		schemaFields.add(columnsSchema);
		String dynamicPartKey = columnSchemaBuilder.getDynamicPartKey(columnsSchema);
		if(!StringUtils.isEmpty(dynamicPartKey)) {
			DynamicPartSchemaBuilder dynamicPartSchemaBuilder = new DynamicPartSchemaBuilder();
			String dynamicPartSchema = dynamicPartSchemaBuilder.getDynamicPartSchema(schema.getDynamicPartMetaData(), dynamicPartKey);
			schemaFields.add(dynamicPartSchema);
		}
		return String.join("->", schemaFields);
	}

	public Set<String> getSchema(Map<String, Schema> jsonSchemas) {
		Set<String> schemas=new LinkedHashSet<String>();
		jsonSchemas.forEach((schemaName, jsonSchema) -> {
			String schema = getSchema(jsonSchema);
			schemas.add(schemaName+":"+schema);
			System.out.println("hbase schema name :"+schemaName+" hbase schema : "+schema);
		});
		return schemas;

	}
}
