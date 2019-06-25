package com.schema.antlr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author anil
 */
public class HdfsJsonSchema {

//	private List<Schema> schemas;
	@JsonProperty(value = "Schemas")
	private Map<String,Schema> schemas;

	public Map<String, Schema> getSchemas() {
		return schemas;
	}

	public void setSchemas(Map<String, Schema> schemas) {
		this.schemas = schemas;
	}
}
