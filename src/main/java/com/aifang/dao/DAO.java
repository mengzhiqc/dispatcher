package com.aifang.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.aifang.util.LogUtil;

public abstract class DAO <T,M> { //T为Dao的类型，M为Model类型
	private M modelClass;
	private String modelName = modelClass.getClass().getSimpleName();
	

	protected DAO(){
		
	}

	protected static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * 通过主键查询
	 * 
	 * @param id
	 * @return
	 */
	public M findById(Integer id) {
		M rs = null;
		Session session = sessionFactory.openSession();
		rs = (M) session.get(modelClass.getClass(), id);
		return rs;
	}

	/**
	 * 通过ID序列查询结果集
	 * 
	 * @param List <Integer> ids
	 * @return List<M> rs
	 */
	public List<M> findByIds(List<Integer> ids) {
		List<M> rs = null ;
		if (null != ids) {
			Session session = sessionFactory.openSession();
			for (int i : ids) {
				M returnAction = (M) session.get(modelClass.getClass(), i);
				rs.add(returnAction);
				if (returnAction == null) {
					LogUtil.debug("returnAction return NULL");
				} else {
					LogUtil.debug(returnAction.toString());
				}
			}
			if(rs.isEmpty()){
				rs = Collections.emptyList();
			}
		}else{
			rs = Collections.emptyList();
		}
		
		
		return rs;
	}

	/**
	 * 根据条件获取查询列表
	 * @param HashMap<String,String> where
	 * @return List<M> rs
	 * TODO 安全隐患：查询条件直接用字符拼接
	 */
	public List<M> findByWhere(HashMap<String, String> where) {
		List<M> rs ;
		if(null == where || where.isEmpty()){
			rs = this.findAll();
		}else {
			List<String> conditionList = new LinkedList<String>();
			for(Entry<String,String> entry:where.entrySet()){
				Object key = entry.getKey();
				Object value = entry.getValue();
				conditionList.add(key+"='"+value+"'");
			}
			
			rs = this.findByWhere(StringUtils.join(conditionList," and "));
		}
		return rs;
	}
	
	/**
	 * 根据String类型的条件查询列表
	 * @param String where
	 * @return List<M> rs
	 */
	public List<M> findByWhere(String where){
		List<M> rs = null ;
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		StringBuilder sb = new StringBuilder();
		sb.append("from "+ modelName +" ");
		LogUtil.info(sb.toString());
		if(null != where && where.length() > 0){
			sb.append("where ");
			sb.append(where);
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
	
	
	/**
	 * 查询所有数据
	 * @return List<M> rs
	 */
	public List<M> findAll(){
		List<M> rs = null ;
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		StringBuilder sb = new StringBuilder();
		sb.append("from "+ modelName +" ");
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

	/**
	 * 插入一条纪录
	 * @param M model
	 * @return M rs;
	 */
	public M insertOne(M model) {
		M rs = null ;
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

	/**
	 * 更新一条记录
	 * @param M model
	 * @return M rs
	 */
	public Object updateOne(M model) {
		M rs = null ;
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

	/**
	 * 删除记录
	 * @param M model
	 */
	public void delete(M model) {
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

	
	/**
	 * truncate数据表
	 * @param String tableName
	 */
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
