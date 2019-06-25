package com.schema.antlr.builder;

import com.schema.antlr.model.Schema;
import com.schema.antlr.model.SchemaType;

import java.util.*;

/**
 * @author anil
 */
public class HdfsSchemaBuilder {
	private String getSchema(Schema schema) {
		List<String> schemaFields = new LinkedList<>();
		schemaFields.add(schema.getDatabaseName());
		ColumnSchemaBuilder columnSchemaBuilder=new ColumnSchemaBuilder();
		String columnsSchema = columnSchemaBuilder.getColumnsSchema(schema.getColumnList(),SchemaType.HDFS.getType());
		schemaFields.add(columnsSchema);
		return String.join("->", schemaFields);
	}
	public Set<String> getSchema(Map<String, Schema> jsonSchemas){
		Set<String> schemas=new LinkedHashSet<String>();
		jsonSchemas.forEach((schemaName, jsonSchema) -> {
			String schema = getSchema(jsonSchema);
			schemas.add(schemaName+":"+schema);
			System.out.println("hdfs schema name :"+schemaName+" hdfs schema : "+schema);
		});
		return schemas;
	}
}
