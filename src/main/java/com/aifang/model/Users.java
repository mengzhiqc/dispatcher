package com.aifang.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.aifang.dao.DAO;
import com.aifang.util.HibernateUtil;
import com.aifang.util.LogUtil;

@Component(value = "users")
public class Users extends DAO{

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getChinesename() {
		return chinesename;
	}

	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	private Integer id;
	private String username;
	private String chinesename;
	private String email;
	private Integer created;
	private String last_update;



}
