package com.aifang.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.aifang.dao.DAO;
import com.aifang.util.HibernateUtil;
import com.aifang.util.MarkUtil;
import com.aifang.util.TimeUtil;
@Component(value="user")
public class User extends DAO implements Serializable{
	private int id;
	private String username;
	private String chinesename;
	private String email;
	
	public User(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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

	public void addUser(String username,String chinesename,String email)
	{
		Transaction trsn=null;

		Session session=sessionFactory.openSession();
		try{
			trsn=session.beginTransaction();
			 User user=new User();
		     user.setUsername(username);
		     user.setChinesename(chinesename);
		     user.setEmail(email);
			 session.save(user);
			 trsn.commit();
			 
		}catch(RuntimeException e){
			trsn.rollback();
		}
		finally{
			
			session.close();
		}
		
		
	}
	
	public User getUserInforByUsername(String username)
	{
		Transaction trsn=null;
		User user=null;
		Session session=sessionFactory.openSession();
		Query query=null;
		
		try{
			
			trsn=session.beginTransaction();
			 query=session.createQuery("from User where username=:username").setString("username", username);	
				   	
			        user=(User) query.list().get(0);	
			        
			        trsn.commit();
			        return user;
			        
			
		}catch(RuntimeException e)
		{
			trsn.rollback();
			
		}finally{
			
			session.close();
		}
		return user;
	}
 

	
	
}
