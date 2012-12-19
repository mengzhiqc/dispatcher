package com.aifang.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.aifang.util.HibernateUtil;
import com.aifang.util.LogUtil;

@Component(value="users")
public class Users {

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
	
	public void addUser(String username, String email, String chinesename) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			LogUtil.debug(chinesename);
			trns = session.beginTransaction();
			Users users = new Users();
			users.setUsername(username);
			users.setChinesename(chinesename);
			users.setEmail(email);
			long currentTime = System.currentTimeMillis();
			LogUtil.debug("currentTime:"+String.valueOf(currentTime/1000));
			users.setCreated((int)(currentTime/1000));
			LogUtil.info(Calendar.getInstance().getTimeZone().toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			LogUtil.info(sdf.getTimeZone().toString());
			users.setLast_update(sdf.format(currentTime).toString());
			session.save(users);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			trns.rollback();
		} finally {
			session.flush();
			session.clear();
		}
	}

	public Users getUserInfoByUsername(String username) {
		Users rs = null;
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			List<Users> users = session
					.createQuery("from Users where username=:username")
					.setString("username", username).list();
			trns.commit();
			return users.get(0);
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
		} finally {
			session.flush();
			session.close();
		}
		return rs;
	}
	
	
}
