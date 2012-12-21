package com.aifang.dao;

import java.util.LinkedList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.aifang.util.LogUtil;

public abstract class DAO {

	protected static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * 通过主键查询
	 * 
	 * @param id
	 * @return
	 */
	public Object findById(Integer id) {
		Object rs = null;
		Session session = sessionFactory.openSession();
		rs = session.load(getClass(), id);
		return rs;
	}

	/**
	 * 通过ID序列查询结果集
	 * 
	 * @param List
	 *            <Integer> ids
	 * @return List<Object>
	 */
	public List<Object> findByIds(List<Integer> ids) {
		List<Object> rs = new LinkedList<Object>();
		LogUtil.debug(ids.toString());
		if (null != ids) {
			Session session = sessionFactory.openSession();
			for (int i : ids) {
				Object returnAction = session.load(getClass(), i);
				rs.add(returnAction);
				LogUtil.debug(returnAction.toString());
			}
		}
		return rs;
	}

	public Object insertOne(Object model) {
		Object rs = new Object();
		LogUtil.info("Now Insert Model :" + model.toString());
		if (null != model) {
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction tran =  session.beginTransaction();
			session.save(model);
			try {
				tran.commit();
				return model;
			} catch (RuntimeException e) {
				if (tran != null) {
					tran.rollback();
				}
			}  finally {
				session.flush();
				session.close();
			}
		}
		return rs;
	}
	
	public Object updateOne(Object model){
		Object rs = new Object();
		LogUtil.info("Now Update Model :" + model.toString());
		if (null != model) {
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction tran =  session.beginTransaction();
			session.update(model);
			try {
				tran.commit();
				return model;
			} catch (RuntimeException e) {
				if (tran != null) {
					tran.rollback();
				}
			}  finally {
				session.flush();
				session.close();
			}
		}
		return rs;	
	}
	
	public void delete(Object model){
		LogUtil.info("Now Update Model :" + model.toString());
		if (null != model) {
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction tran =  session.beginTransaction();
			session.delete(model);
			try {
				tran.commit();
			} catch (RuntimeException e) {
				if (tran != null) {
					tran.rollback();
				}
			}  finally {
				session.flush();
				session.close();
			}
		}
	}
	
	

	/**
	 * 获取hibernate的{link:SessionFactory}
	 * 
	 * @return SessionFactory sf
	 */
	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sf = configuration.buildSessionFactory(serviceRegistry);
		return sf;
	}

}
