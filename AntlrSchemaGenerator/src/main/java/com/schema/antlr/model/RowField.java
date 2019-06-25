package com.schema.antlr.model;

/**
 * @author anil
 */
public class RowField {

//	private String hashValueTemplate = ":hash({fieldName})[{bitRangeMin}:{bitRangeMax}]";
//	private String literalTemplate = "{fieldName}=\"{literalValue}\"";
	private String fieldName;
	private boolean hashed;
	private boolean literal;
	private String bitRange;
	private String literalValue;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isHashed() {
		return hashed;
	}

	public void setHashed(boolean hashed) {
		this.hashed = hashed;
	}

	public boolean isLiteral() {
		return literal;
	}

	public void setLiteral(boolean literal) {
		this.literal = literal;
	}

	public String getBitRange() {
		return bitRange;
	}

	public void setBitRange(String bitRange) {
		this.bitRange = bitRange;
	}

	public String getLiteralValue() {
		return literalValue;
	}

	public void setLiteralValue(String literalValue) {
		this.literalValue = literalValue;
	}

//	public String getHashedValue(String fieldName, String bitRange) {
//		String hashValue = hashValueTemplate.replaceAll("\\{fieldName\\}", fieldName);
//		if (bitRange != null && !bitRange.equals("")) {
//			String[] bitRanges = bitRange.split("-");
//			if (bitRanges.length == 2) {
//				hashValue = hashValue.replaceAll("\\{bitRangeMin\\}", bitRanges[0]).replaceAll("\\{bitRangeMax\\}", bitRanges[1]);
//			}
//		}
//
//
//		return hashValue;
//	}

//	@Override
//	public String toString() {
//		String rowKey = fieldName;
//		if (fieldName != null && !fieldName.equals("")) {
//			if (isHashed()) {
//				rowKey = getHashedValue(fieldName, bitRange);
//			}
//			if (isLiteral()) {
//				rowKey = literalTemplate.replaceAll("\\{fieldName\\}", fieldName).replaceAll("\\{literalValue\\}", literalValue);
//			}
//
//		}
//
//		return rowKey;
//
//	}
}
