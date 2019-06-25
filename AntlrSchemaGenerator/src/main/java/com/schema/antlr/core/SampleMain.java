package com.schema.antlr.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schema.antlr.builder.HBaseSchemaBuilder;
import com.schema.antlr.builder.HdfsSchemaBuilder;
import com.schema.antlr.builder.HiveSchemaBuilder;
import com.schema.antlr.model.HdfsJsonSchema;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author anil
 */
public class SampleMain {
	public static void main(String[] ar){
		System.out.println("reading json");
		ObjectMapper mapper=new ObjectMapper();
		try {
			SampleMain sampleMain=new SampleMain();
			HdfsJsonSchema hbseJsonSchema = mapper.readValue(sampleMain.getFile("./hbase_example.json"), HdfsJsonSchema.class);
			HBaseSchemaBuilder hBaseSchemaBuilder =new HBaseSchemaBuilder();
			Set<String> hbaseSchema = hBaseSchemaBuilder.getSchema(hbseJsonSchema.getSchemas());
			System.out.println("hbase schemas : "+hbaseSchema);

			HdfsJsonSchema hiveJsonSchema = mapper.readValue(sampleMain.getFile("./hive_example.json"), HdfsJsonSchema.class);
			HiveSchemaBuilder hiveSchemaBuilder =new HiveSchemaBuilder();
			Set<String> hiveSchema = hiveSchemaBuilder.getSchema(hiveJsonSchema.getSchemas());
			System.out.println("hive schemas : "+hiveSchema);


			HdfsJsonSchema hdfsJsonSchema = mapper.readValue(sampleMain.getFile("./hdfs_example.json"), HdfsJsonSchema.class);
			HdfsSchemaBuilder hdfsSchemaBuilder =new HdfsSchemaBuilder();
			Set<String> hdfsSchema = hdfsSchemaBuilder.getSchema(hdfsJsonSchema.getSchemas());
			System.out.println("hdfs schemas : "+hdfsSchema);
//			List<Column> columnList = hdfsJsonSchema.getSchemas().get("Test_Schema").getColumnList();
//			ColumnSchemaBuilder columnSchemaBuilder=new ColumnSchemaBuilder();
//			String columnsSchema = columnSchemaBuilder.getColumnsSchema(columnList);
//			System.out.println(columnsSchema);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private File getFile(String fileName){
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
}
