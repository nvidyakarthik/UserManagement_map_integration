package com.epicproportionstour.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epicproportionstour.user.dao.UsersDao;
import com.epicproportionstour.user.model.Role;
import com.epicproportionstour.user.model.Users;
import com.epicproportionstour.user.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
    
	@Autowired
	private UsersDao userDao;
	@Transactional
	public boolean addUser(Users user) {
		return userDao.addUser(user);
		
	}

	@Transactional
	public void editUser(Users user) {
		userDao.editUser(user);
		
	}

	@Transactional
	public boolean deleteUser(int userId) {
		userDao.deleteUser(userId);
		return true;
		
	}

	@Transactional
	public Users findUser(int userid) {
		userDao.findUser(userid);
		return null;
	}

	@Transactional
	public Users findUserByName(String userName) {
		return userDao.findUserByName(userName);
		
	}

	@Transactional
	public List<Users> getAllUsers(int pageSize, int page, String sidx, String sord) {
		return userDao.getAllUsers(pageSize,page,sidx,sord);
		
	}

	@Transactional
	public void updateLastLogin(String lastLogin, String userName) {
		userDao.updateLastLogin(lastLogin, userName);
		
	}

	@Transactional
	public List<Role> getUserRoles() {
		return userDao.getUserRoles();
	}

	@Transactional
	public Role findRole(int roleid) {
		return userDao.findRole(roleid);
		
	}

	@Transactional
	public void changePassword(String userName, String newPassword) {
		userDao.changePassword(userName, newPassword);
		
	}

	@Override
	public int getNoOfRecords() {
		return userDao.getNoOfRecords();
	}

}
