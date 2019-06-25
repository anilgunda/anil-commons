package com.schema.antlr.builder;

import com.schema.antlr.model.RowField;
import com.schema.antlr.model.RowKeyMetaData;

import java.util.regex.Pattern;

/**
 * @author anil
 */
public class RowKeySchemaBuilder {

	private String rowkeyTemplate = "RK={rowKey}";
	private String hashValueTemplate = "hash({fieldName})[{bitRangeMin}:{bitRangeMax}]";
	private String literalTemplate = "{fieldName}=\"{literalValue}\"";

	private String getHashedValue(String fieldName, String bitRange) {
		String hashValue = hashValueTemplate.replaceAll("\\{fieldName\\}", fieldName);
		if (bitRange != null && !bitRange.equals("")) {
			String[] bitRanges = bitRange.split("-");
			if (bitRanges.length == 2) {
				hashValue = hashValue.replaceAll("\\{bitRangeMin\\}", bitRanges[0]).replaceAll("\\{bitRangeMax\\}", bitRanges[1]);
			}
		}


		return hashValue;
	}

	private String getRowFieldSchema(RowField rowField) {
		String rowKey = rowField.getFieldName();
		if (rowField.getFieldName() != null && !rowField.getFieldName().equals("")) {
			if (rowField.isHashed()) {
				rowKey = getHashedValue(rowField.getFieldName(), rowField.getBitRange());
			}
			if (rowField.isLiteral()) {
				rowKey = literalTemplate.replaceAll("\\{fieldName\\}", rowField.getFieldName()).replaceAll("\\{literalValue\\}", rowField.getLiteralValue());
			}

		}

		return rowKey;
	}

	public String getRowKeySchema(RowKeyMetaData rowKeyMetaData) {
		String rowKey = "";
		for (RowField rowField : rowKeyMetaData.getRowFields()) {
			rowKey += rowKeyMetaData.getRowKeySeperator() + getRowFieldSchema(rowField);
		}
		rowKey = rowKey.replaceFirst(Pattern.quote(rowKeyMetaData.getRowKeySeperator()), rowKeyMetaData.getRowKeySeperator() + ":");
		rowKey = rowkeyTemplate.replaceAll("\\{rowKey\\}", rowKey);
		return rowKey;
	}
}
