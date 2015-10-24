package com.epicproportionstour.user.service;
import java.util.List;

import com.epicproportionstour.user.model.Role;
import com.epicproportionstour.user.model.Users;

public interface LoginService {
	boolean addUser(Users user);
	void editUser(Users user);
	boolean deleteUser(int userId);
	Users findUser(int userid);
	Users findUserByName(String userName);
	List<Users> getAllUsers(int pageSize, int page, String sidx, String sord);
	void updateLastLogin(String lastLogin, String userName);
	List<Role> getUserRoles();
	Role findRole(int roleid);
	public void changePassword(String userName,String newPassword);
	int getNoOfRecords();

}

