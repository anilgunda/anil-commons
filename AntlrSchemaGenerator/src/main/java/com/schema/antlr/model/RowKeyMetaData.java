package com.schema.antlr.model;

import java.util.List;

/**
 * @author anil
 */
public class RowKeyMetaData {
	private String rowkeyTemplate="RK={rowKey}";
	private String rowKeySeperator;
	private List<RowField> rowFields;

	public String getRowKeySeperator() {
		return rowKeySeperator;
	}

	public void setRowKeySeperator(String rowKeySeperator) {
		this.rowKeySeperator = rowKeySeperator;
	}

	public List<RowField> getRowFields() {
		return rowFields;
	}

	public void setRowFields(List<RowField> rowFields) {
		this.rowFields = rowFields;
	}

//	@Override
//	public String toString() {
//		String rowKey="";
//		for(RowField rowField:rowFields){
//			rowKey=rowKeySeperator+rowKey;
//		}
//		rowKey=rowkeyTemplate.replaceAll("\\{rowKey\\}",rowKey);
//		return rowKey;
//	}
}
