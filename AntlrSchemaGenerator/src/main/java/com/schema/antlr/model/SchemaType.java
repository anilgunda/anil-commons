package com.schema.antlr.model;

/**
 * @author anil
 */
public enum SchemaType {
	HIVE("HIVE"),HBASE("HBASE"),HDFS("HDFS");
	private String type;

	SchemaType(String type) {
		this.type=type;
	}
	public String getType(){
		return type;
	}
}
