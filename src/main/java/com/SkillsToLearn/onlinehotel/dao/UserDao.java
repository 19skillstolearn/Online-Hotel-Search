package com.SkillsToLearn.onlinehotel.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.SkillsToLearn.onlinehotel.formbean.UserFormBean;
import com.SkillsToLearn.onlinehotel.model.UserBean;


public interface UserDao {
	
	public Long getMaxUserId();
	public UserBean findUserByUserName(String userName);
	public UserBean findUserByEmail(String email);
	public List<UserBean> getUsers();
	public UserBean createUser(UserFormBean form);

}
