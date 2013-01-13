package com.aifang.model;

import java.io.Serializable;
import java.sql.Clob;

import org.springframework.stereotype.Component;


@Component(value="detail")
public class Detail extends Task implements Serializable{
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
