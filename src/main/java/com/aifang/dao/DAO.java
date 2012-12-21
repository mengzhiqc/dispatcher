package com.aifang.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public abstract class DAO {

	protected static SessionFactory sessionFactory=buildSessionFactory();
	
	/**
	 * 通过组件查询
	 * @param id
	 * @return
	 */
	public Object findById(Integer id){
		Object rs = null;
		Session session = sessionFactory.openSession();
		rs = session.byId(getClass());
		return rs;
	}
	
	/**
	 * 获取hibernate的{link:SessionFactory}
	 * @return SessionFactory sf
	 */
	private static SessionFactory buildSessionFactory(){
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
			.applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sf = configuration.buildSessionFactory(serviceRegistry);
		return sf;
	}
	
	
	
}
