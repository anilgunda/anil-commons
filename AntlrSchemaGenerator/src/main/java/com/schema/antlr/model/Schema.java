package com.schema.antlr.model;

import java.util.List;

/**
 * @author anil
 */
public class Schema {
	private String databaseName;
	private String hbaseTableName;
	private RowKeyMetaData rowKeyMetaData;
	private List<Column> columnList;
	private DynamicPartMetaData dynamicPartMetaData;
	private String storage;

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getHbaseTableName() {
		return hbaseTableName;
	}

	public void setHbaseTableName(String hbaseTableName) {
		this.hbaseTableName = hbaseTableName;
	}

	public RowKeyMetaData getRowKeyMetaData() {
		return rowKeyMetaData;
	}

	public void setRowKeyMetaData(RowKeyMetaData rowKeyMetaData) {
		this.rowKeyMetaData = rowKeyMetaData;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	public DynamicPartMetaData getDynamicPartMetaData() {
		return dynamicPartMetaData;
	}

	public void setDynamicPartMetaData(DynamicPartMetaData dynamicPartMetaData) {
		this.dynamicPartMetaData = dynamicPartMetaData;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}
}
