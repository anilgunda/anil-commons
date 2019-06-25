package com.schema.antlr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author anil
 */
public class Column {

	private String columnFamily;
	private String columnName;
	private String dataType;
	@JsonProperty(value = "defaultValue",defaultValue = "")
	private String defaultValue;
	@JsonProperty(value = "compressionType",defaultValue = "")
	private String compressionType;
	@JsonProperty(value = "isPartitionColumn")
	private boolean isPartitionColumn;
	private String json;

	public String getColumnFamily() {
		return columnFamily;
	}

	public void setColumnFamily(String columnFamily) {
		this.columnFamily = columnFamily;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getCompressionType() {
		return compressionType;
	}

	public void setCompressionType(String compressionType) {
		this.compressionType = compressionType;
	}

	public boolean isPartitionColumn() {
		return isPartitionColumn;
	}

	public void setPartitionColumn(boolean partitionColumn) {
		isPartitionColumn = partitionColumn;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
