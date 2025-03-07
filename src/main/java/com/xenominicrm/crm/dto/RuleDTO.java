package com.xenominicrm.crm.dto;

public class RuleDTO {
	private String field;
	private String operator;
	private String value;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public RuleDTO(String field, String operator, String value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
}
