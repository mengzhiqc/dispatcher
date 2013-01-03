package com.aifang.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PagingUtil {
	
	static public List paging(String hql,int pagenum,int pagesize)
	{
		int pagebegin=0;
		if(pagenum<0)
			pagebegin=0;
		else
		pagebegin=(pagenum-1)*pagesize;
		Transaction trsn=null;
		Session session=HibernateUtil.getSessionFactory().openSession();
		try{
			List list=new ArrayList();
			trsn=session.beginTransaction();
			Query query=session.createQuery(hql);
			      query.setFirstResult(pagebegin);
			      query.setMaxResults(pagesize);
			      trsn.commit();
			      list=query.list();
			      if(list!=null)
			      {
			    	  return list;
			      }
			      
			      
			      
			
		}catch(RuntimeException e)
		{
			trsn.rollback();
		}
		finally{
			session.close();
		}
		return null;
	}

}
