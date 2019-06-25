package com.schema.antlr.model;

import java.util.List;

/**
 * @author anil
 */
public class DynamicPartMetaData {
	private String dynamicPatSeperator;
	private List<String> dynamicPartNames;

	public String getDynamicPatSeperator() {
		return dynamicPatSeperator;
	}

	public void setDynamicPatSeperator(String dynamicPatSeperator) {
		this.dynamicPatSeperator = dynamicPatSeperator;
	}

	public List<String> getDynamicPartNames() {
		return dynamicPartNames;
	}

	public void setDynamicPartNames(List<String> dynamicPartNames) {
		this.dynamicPartNames = dynamicPartNames;
	}

	@Override
	public String toString() {
		String dpmd="";
		for (String dynamicPart:dynamicPartNames){
			dpmd=dynamicPatSeperator+dynamicPart;
		}
		dpmd=dpmd.replaceFirst(dynamicPatSeperator,dynamicPatSeperator+":");
		return dpmd;
	}
}
