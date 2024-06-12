package com.xenominicrm.crm.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "campaign")
public class Campaign {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String field;
	private String operator;
	private String value;
	private Long audienceSize;
	private Date createdAt;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getAudienceSize() {
		return audienceSize;
	}
	public void setAudienceSize(Long audienceSize) {
		this.audienceSize = audienceSize;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
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
	
	
}
