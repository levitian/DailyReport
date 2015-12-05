package com.mottledog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mottledog.bo.User;
import com.mottledog.dao.UserDao;

/**
 * @ClassName: UserBO 
 * @Description: user bo
 * @author tianli 
 * @date 2015-1-21 14:15:46 
 */
@Service
@Transactional
public class UserService {

	private UserDao userDAO;

	@Autowired
	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	public void createUser(User user) {
		userDAO.save(user);
	}
	
	public void deleteUser(int userId) {
		userDAO.delete(userId);
	}
	
	public void updateUser(User user) {
		userDAO.update(user);
	}

	public int countAll(){
		return userDAO.countAll();
	}
	
	public List<User> getUserList() {
		return userDAO.list();
	}
	
	public List<User> getUserListByPage(int pageNo, int pageSize){
		return userDAO.listByPage(pageNo, pageSize);
	}
	
	public User getUserByUserName(String username) {
		return (User) userDAO.list("username", username);
	}
	
	public User getUserByEmail(String email) {
		return (User) userDAO.list("email", email);
	}
	public User getUserById(int userId) {
		return userDAO.get(userId);
	}
	
	public int countAllBySearch(String search){
		//TODO
		return 0;
	}
	public List<User> getUserListByPageAndSearch(String search,int pageNo, int pageSize){
		//TODO
		return null;
	}
}
