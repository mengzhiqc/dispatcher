package com.aifang.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.aifang.dao.DAO;
import com.aifang.util.HibernateUtil;
import com.aifang.util.LogUtil;

@Component(value = "users")
public class Users extends DAO{

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

	/**
	 * 添加用户
	 * 
	 * @param Users
	 *            userInfo
	 */
	public void addUser(Users userInfo) {
		LogUtil.debug("UserInfo:"+userInfo.toString());
		Transaction trns = null;
		Session session = sessionFactory.openSession();
		try {
			LogUtil.debug(chinesename);
			trns = session.beginTransaction();

			long currentTime = System.currentTimeMillis();
			LogUtil.debug("currentTime:" + String.valueOf(currentTime / 1000));
			userInfo.setCreated((int) (currentTime / 1000));
			LogUtil.info(Calendar.getInstance().getTimeZone().toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			LogUtil.info(sdf.getTimeZone().toString());
			userInfo.setLast_update(sdf.format(currentTime).toString());
			session.save(userInfo);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			trns.rollback();
		} finally {
			session.flush();
			session.close();
		}
	}

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param String
	 *            username
	 * @return Users userinfo
	 */

	public Users getUserInfoByUsername(String username) {
		Users rs = null;
		Transaction trns = null;
		Session session = sessionFactory.openSession();
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
	
	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param String username
	 * @return List<Users> userinfos
	 */

	public List<Users> getUserInfosByUsername(String username) {
		List<Users> rs =  null;
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			List<Users> users = session
					.createQuery("from Users where username=:username")
					.setString("username", username).list();
			trns.commit();
			return users;
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

	/**
	 * 根据UserId删除数据
	 * 
	 * @param int userId
	 * @return int rs 删除的条数
	 */
	public int deleteUserById(int userId) {
		int rs = 0;
		Transaction trns = null;
		Session session = sessionFactory.openSession();
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("DELETE Users  WHERE id=?")
					.setInteger(0, userId);
			rs = q.executeUpdate();
			trns.commit();

		} catch (RuntimeException e) {
			e.printStackTrace();
			if (trns != null) {
				LogUtil.info("用户删除失败！用户ID是：" + String.valueOf(userId));
				trns.rollback();
			}
		} finally {
			session.flush();
			session.close();
		}
		return rs;
	}

	/**
	 * 根据username删除数据
	 * 
	 * @param String
	 *            userName
	 * @return int rs 删除条数
	 */
	public int deleteUserByUserName(String userName) {
		int rs = 0;
		Transaction trns = null;
		Session session = sessionFactory.openSession();
		try{
			trns = session.beginTransaction();
			Query q = session.createQuery("DELETE Users WHERE username=?")
					.setString(0, userName);
			rs = q.executeUpdate();
			trns.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (trns != null) {
				LogUtil.info("用户删除失败！用户username是：" + userName);
				trns.rollback();
			}

		} finally {
			session.flush();
			session.close();
		}
		return rs;
	}

}
