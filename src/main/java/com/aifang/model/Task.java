package com.aifang.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.aifang.dao.DAO;
import com.aifang.util.HibernateUtil;
import com.aifang.util.TimeUtil;
@Component(value="task")
public class Task extends DAO   implements Serializable{
	private int id;
	private int score;
	private int flag;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
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
	
	
	
	public void addTask(String taskname,int score,String description,String username)
	{
		Transaction trsn=null;
		
		Session session=sessionFactory.openSession();
		try{
			         trsn=session.beginTransaction();
			            
			        	 Task task=new Task();
			        	 Detail detail=new Detail();
			        	 task.setUsername(username);
					     task.setTaskname(taskname);				    
					     task.setScore(score);
					     detail.setDescription(description);					    
					     task.setCreate_time(TimeUtil.getTime());
					     task.setLast_update_time(TimeUtil.getTime());
					     task.setFlag(1);
					     session.save(task);
					     session.save(detail);
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
		Session session=sessionFactory.openSession();
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
		Session session=sessionFactory.openSession();
		try{
			trsn=session.beginTransaction();
			List<Task> list=session.createQuery("from Task where id=:id").setInteger("id", id).list();
		
			task=list.get(0);
			if(null!=task)
			{
				task.setFlag(0);
			    trsn.commit();
			}
			
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
    	Session session=sessionFactory.openSession();
    	try{
    		
    		trsn=session.beginTransaction();
    		List<Task> list=session.createQuery("from Task where id=:id").setInteger("id", id).list();
    		task=list.get(0);
    		trsn.commit();
    		if( task!=null && task.getFlag()!=0)
    		{
    			return task;
    			
    		}
   
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
   	     Detail detail=null;
   	     Session session=sessionFactory.openSession();
    	try{
    		trsn=session.beginTransaction();
    		List<Task> list=session.createQuery("from Task where id=:id").setInteger("id",id).list();
    		           task=list.get(0);
    		           List<Detail> dlist=session.createQuery("from Detail where id=:id").setInteger("id",id).list();
    		           detail=dlist.get(0);
    		           
    		           if(task.getFlag()!=0)
    		           {
    		           task.setTaskname(taskname);
    		           task.setScore(score);
    		           detail.setDescription(description);
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
