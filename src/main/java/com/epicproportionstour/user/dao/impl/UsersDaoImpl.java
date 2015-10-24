package com.epicproportionstour.user.dao.impl;

import java.util.List;






import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epicproportionstour.user.dao.UsersDao;
import com.epicproportionstour.user.model.Role;
import com.epicproportionstour.user.model.Users;


@Repository("userDao")
public class UsersDaoImpl implements UsersDao{
	
	private static int noOfRecords=0;
	@Autowired
	private SessionFactory session;

	@Override
	public boolean addUser(Users user) {
		session.getCurrentSession().saveOrUpdate(user);
		return true;
		
	}

	@Override
	public void editUser(Users user) {
		session.getCurrentSession().update(user);
		
	}

	@Override
	public boolean deleteUser(int userId) {
		Users user = (Users) session.getCurrentSession().get(Users.class, userId);
		session.getCurrentSession().delete(user);
		return true;
		
	}
	
	@Override
	public int getNoOfRecords() {
        return noOfRecords;
    }

	@Override
	public Users findUser(int userid) {
		
		return (Users) session.getCurrentSession().get(Users.class,userid);
	}

	@Override
	public Users findUserByName(String username) {
		Criteria criteria = session.getCurrentSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("username", username));		
		Users user= (Users) criteria.uniqueResult();
		return user;
	}

	@Override
	public List<Users> getAllUsers(int pageSize, int page, String sidx, String sord) {
		List<Users> listUsers=session.getCurrentSession().createQuery("from Users").list();
		//Query query = session.getCurrentSession().createQuery("FROM Users order by "+sidx+" "+sord);
		//noOfRecords = query.list().size();
        //query.setFirstResult((page - 1) * pageSize);
        //query.setMaxResults(pageSize);
		//query.setCacheable(true);
		//List<Users> listUsers = query.list();
		return listUsers;
		
	}
		
	@Override
		public void updateLastLogin(String lastLogin,String userName) {
		Users getUser=findUserByName(userName);
		getUser.setLastlogin(lastLogin);
		session.getCurrentSession().saveOrUpdate(getUser);

	}
	@Override
	public List<Role> getUserRoles() {
		System.out.println("                   In userdao                ");
		return  session.getCurrentSession().createQuery("from role").list();
		
		
		
	}
	
	@Override
	public Role findRole(int roleid) {
		
		return (Role) session.getCurrentSession().get(Role.class,roleid);
	}
	
	@Override
	public void changePassword(String userName,String newPassword) {
		
		Users getUser=findUserByName(userName);
		getUser.setPassword(newPassword);
		session.getCurrentSession().saveOrUpdate(getUser);
	}
	
	

}
