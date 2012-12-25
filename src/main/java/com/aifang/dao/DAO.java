package com.aifang.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		rs = session.get(getClass(), id);
		return rs;
	}

	/**
	 * 通过ID序列查询结果集
	 * 
	 * @param List <Integer> ids
	 * @return List<Object>
	 */
	public List<Object> findByIds(List<Integer> ids) {
		List<Object> rs = new LinkedList<Object>();
		LogUtil.debug(ids.toString());
		if (null != ids) {
			Session session = sessionFactory.openSession();
			for (int i : ids) {
				Object returnAction = session.get(getClass(), i);
				rs.add(returnAction);
				if (returnAction == null) {
					LogUtil.debug("returnAction return NULL");
				} else {
					LogUtil.debug(returnAction.toString());
				}
			}
		}
		return rs;
	}

	/**
	 * 根据条件获取查询列表
	 * @param where
	 * @return
	 */
	public List<Object> findByWhere(HashMap<String, String> where) {
		List<Object> rs = new LinkedList<Object>();
		if(null != where && where.size() > 0){
			Iterator it = (Iterator)where.entrySet().iterator();
			List<String> conditionList = new LinkedList<String>();
			while(it.hasNext()){
				Map.Entry<String,String> entry = (Map.Entry<String,String>)it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				conditionList.add(key+"='"+value+"'");
			}
			rs = this.findByWhere(StringUtils.join(conditionList," and "));
		}
		
		return rs;
	}
	
	public List<Object> findByWhere(Object where){
		List<Object> rs = new LinkedList<Object>();
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		StringBuilder sb = new StringBuilder();
		sb.append("from "+getClass().getSimpleName()+" ");
		LogUtil.debug(sb.toString());
		if(where instanceof String  && ((String)where).length() > 0){
			sb.append("where ");
			sb.append(where);
			System.out.println(sb.toString());
		}
		Query q = session.createQuery(sb.toString());
		try{
			rs =  q.list();
			tran.commit();
			return rs;
		}catch (RuntimeException e) {
			if (tran != null) {
				tran.rollback();
			}
		} finally {
			session.flush();
			session.close();
		}
		return rs;
	}

	public Object insertOne(Object model) {
		Object rs = new Object();
		LogUtil.info("Now Insert Model :" + model.toString());
		if (null != model) {
			Session session = sessionFactory.openSession();
			Transaction tran = session.beginTransaction();
			session.save(model);
			try {
				tran.commit();
				return model;
			} catch (RuntimeException e) {
				if (tran != null) {
					tran.rollback();
				}
			} finally {
				session.flush();
				session.close();
			}
		}
		return rs;
	}

	public Object updateOne(Object model) {
		Object rs = new Object();
		LogUtil.info("Now Update Model :" + model.toString());
		if (null != model) {
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction tran = session.beginTransaction();
			session.update(model);
			try {
				tran.commit();
				return model;
			} catch (RuntimeException e) {
				if (tran != null) {
					tran.rollback();
				}
			} finally {
				session.flush();
				session.close();
			}
		}
		return rs;
	}

	public void delete(Object model) {
		LogUtil.info("Now Update Model :" + model.toString());
		if (null != model) {
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction tran = session.beginTransaction();
			session.delete(model);
			try {
				tran.commit();
			} catch (RuntimeException e) {
				if (tran != null) {
					tran.rollback();
				}
			} finally {
				session.flush();
				session.close();
			}
		}
	}

	public void truncateTable(String tableName) {
		LogUtil.info("Warnning: Talbe" + tableName + "will be truncated");
		if (null != tableName) {
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction tran = session.beginTransaction();
			session.createSQLQuery("truncate table ?").setString(0, tableName);

			try {
				tran.commit();
			} catch (RuntimeException e) {
				if (tran != null) {
					tran.rollback();
				}
			} finally {
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
