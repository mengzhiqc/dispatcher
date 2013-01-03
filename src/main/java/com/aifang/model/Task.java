package com.aifang.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.aifang.util.HibernateUtil;
import com.aifang.util.TimeUtil;
@Component(value="task")
public class Task {
	private int id;
	private int score;
	private int mytinyint;

	public int getMytinyint() {
		return mytinyint;
	}
	public void setMytinyint(int mytinyint) {
		this.mytinyint = mytinyint;
	}



	private String username;
	private String create_time;
	private String last_update_time;
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}
	
	

	private String taskname;

	private String description;
    public Task(){}
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addTask(String taskname,int score,String description,String username)
	{
		Transaction trsn=null;
		
		Session session=HibernateUtil.getSessionFactory().openSession();
		try{
			         trsn=session.beginTransaction();
			            
			        	 Task task=new Task();
			        	 task.setUsername(username);
					     task.setTaskname(taskname);				    
					     task.setScore(score);
					     task.setDescription(description);					    
					     task.setCreate_time(TimeUtil.getTime());
					     task.setLast_update_time(TimeUtil.getTime());
					     task.setMytinyint(1);
					     session.save(task);
					     trsn.commit();			  
			
		}catch(RuntimeException e)
		{
			trsn.rollback();
			
		}
		finally{
			session.close();
			
		}
		
		
	}
	public List<Task> showTask(String username)
	{
		Transaction trsn=null;
		Session session=HibernateUtil.getSessionFactory().openSession();
		try{
			trsn=session.beginTransaction();
			List<Task> list=session.createQuery("from Task where uername=:username")
					.setString("username", username).list();
			trsn.commit();
			if(list!=null)
			{
				return list;
			}
			
		}catch(RuntimeException e)
		{
			trsn.rollback();
			
		}finally{
			session.close();
		}
		return null;
		
	}
	public void deleteTask(String username,int id)
	{
		Transaction trsn=null;
		Task task=null;
		Session session=HibernateUtil.getSessionFactory().openSession();
		try{
			trsn=session.beginTransaction();
			List<Task> list=session.createQuery("from Task where id=:id").setInteger("id", id).list();
		
			task=list.get(0);
			if(null!=task)
			{
				task.setMytinyint(0);
			    trsn.commit();
			}
			else 
				return ;
			
		}catch(RuntimeException e)
		{
			trsn.rollback();
			
		}finally{
			session.close();
		}
		
		
		
	}
	
    public Task searchTask(int id)
    {
    	Transaction trsn=null;
    	Task task=null;
    	Session session=HibernateUtil.getSessionFactory().openSession();
    	try{
    		
    		trsn=session.beginTransaction();
    		List<Task> list=session.createQuery("from Task where id=:id").setInteger("id", id).list();
    		task=list.get(0);
    		trsn.commit();
    		if(task.getMytinyint()!=0)
    		{
    			return task;
    			
    		}
    		else 
    			return null;
    		
    	}catch(RuntimeException e)
    	{
    		trsn.rollback();
    		
    	}finally{
    		session.close();
    	}
    	return task;
    }
    
    public void updateTask(String taskname,int score,String description,String username,int id)
    {
    	
    	Transaction trsn=null;
   	     Task task=null;
    	Session session=HibernateUtil.getSessionFactory().openSession();
    	try{
    		trsn=session.beginTransaction();
    		List<Task> list=session.createQuery("from Task where id=:id").setInteger("id",id).list();
    		           task=list.get(0);
    		           
    		           if(task.getMytinyint()!=0)
    		           {
    		           task.setTaskname(taskname);
    		           task.setScore(score);
    		           task.setDescription(description);
    		           task.setLast_update_time(TimeUtil.getTime());
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
