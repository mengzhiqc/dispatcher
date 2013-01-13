package com.aifang.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.aifang.dao.DAO;
import com.aifang.util.HibernateUtil;
import com.aifang.util.TimeUtil;
@Component(value="accept")
public class Accept extends DAO implements Serializable{
	private int id;
	private int flag;
	private String taskname;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	private String accept_time;
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccept_time() {
		return accept_time;
	}
	public void setAccept_time(String accept_time) {
		this.accept_time = accept_time;
	}
	
	public Accept(){}
	
	public void acceptTask(Task task,String username)
	{
		Transaction trsn=null;
		Session session=sessionFactory.openSession();
		try{
			trsn=session.beginTransaction();
			Accept apt=new Accept();
			if(task!=null)
			{
			apt.setFlag(1);
		    apt.setTaskname(task.getTaskname());
			apt.setUsername(username);
			apt.setAccept_time(TimeUtil.getTime());
			session.save(apt);
			trsn.commit();
			}
			
			
		}catch(RuntimeException e)
		{
			trsn.rollback();
		}
		finally{
			session.close();
		}
		
	}
	
	public List<Accept> showTask(String username)
	{
		Transaction trsn=null;
		Session session=sessionFactory.openSession();
		try{
			trsn=session.beginTransaction();
			List<Accept> list=session.createQuery("from Accept where username=:username")
					.setString("username", username).list();
			trsn.commit();
			if(list!=null)
				return list;
			
		}catch(RuntimeException e)
		{
			trsn.rollback();
		}finally{
			session.close();
			
		}
		
		return null;
	}
	
	public void deleteTask(int id,String username)
	{
		Transaction trsn=null;
		
		Session session=sessionFactory.openSession();
		try{
			trsn=session.beginTransaction();
			List<Accept> list=session.createQuery("from Accept where id=:id").setInteger("id",id).list();
			Accept  acp=list.get(0);
			if(acp!=null)
			{
				acp.setFlag(0);
				trsn.commit();
			}
			
			
		}catch(RuntimeException e)
		{
			trsn.rollback();
		}
		finally{
			session.close();
		}
	}

}