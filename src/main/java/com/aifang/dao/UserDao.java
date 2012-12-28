package com.aifang.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.aifang.model.Users;
import com.aifang.util.LogUtil;
import javax.annotation.Resource;

@Component(value="userDao")
public class UserDao extends DAO<UserDao,Users>{
	
	
	/**
	 * 添加用户
	 * 
	 * @param Users userInfo
	 */
	public void addUser(Users userInfo) {
		long currentTime = System.currentTimeMillis();
		LogUtil.debug("currentTime:" + String.valueOf(currentTime / 1000));
		userInfo.setCreated((int) (currentTime / 1000));
		LogUtil.info(Calendar.getInstance().getTimeZone().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		LogUtil.info(sdf.getTimeZone().toString());
		userInfo.setLast_update(sdf.format(currentTime).toString());
		insertOne(userInfo);
	}

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param String username
	 * @return List<Users> userinfos
	 */
	
	public List<Users> getUserInfosByUsername(final String username) {
		HashMap<String,String> where = new HashMap<String,String>(){{
			put("username",username);
		}};
		List<Users> rs = findByWhere(where);
		return rs;
	}
	
	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param String username
	 * @return Users userinfo
	 */

	public Users getUserInfoByUsername(final String username) {
		List<Users> rs = getUserInfosByUsername(username);
		return rs.get(0);
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
