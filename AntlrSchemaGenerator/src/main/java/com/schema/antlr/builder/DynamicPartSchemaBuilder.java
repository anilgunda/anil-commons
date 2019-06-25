package com.schema.antlr.builder;

import com.schema.antlr.model.DynamicPartMetaData;

/**
 * @author anil
 */
public class DynamicPartSchemaBuilder {
	private String dynamicPartSchematemplate = "{dynamicKey}={dynamicPart}";

	public String getDynamicPartSchema(DynamicPartMetaData dynamicPartMetaData, String dynamicPartKey) {
		String dpmd = dynamicPartMetaData.getDynamicPatSeperator() + ":" + String.join(dynamicPartMetaData.getDynamicPatSeperator(), dynamicPartMetaData.getDynamicPartNames());
		String dynamicPartSchema = dynamicPartSchematemplate.replaceAll("\\{dynamicKey\\}", dynamicPartKey).replaceAll("\\{dynamicPart\\}", dpmd);
		return dynamicPartSchema;
	}
}
