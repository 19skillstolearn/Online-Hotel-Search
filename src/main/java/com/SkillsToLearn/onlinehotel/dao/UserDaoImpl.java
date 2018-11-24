package com.SkillsToLearn.onlinehotel.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.SkillsToLearn.onlinehotel.formbean.UserFormBean;
import com.SkillsToLearn.onlinehotel.model.Gender;
import com.SkillsToLearn.onlinehotel.model.UserBean;

@Component
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	private static final Map<Long, UserBean> USERS_MAP = new HashMap<>();
	
	static {
        initDATA();
    }
	
	private static void initDATA() {
		// TODO Auto-generated method stub
		
		String encryptedPassword = "";
		UserBean tom = new UserBean(1L, "tom", "Tom", "Tom", true, Gender.MALE, "tom@waltdisney.com", encryptedPassword);
 
		UserBean jerry = new UserBean(2L, "jerry", "Jerry", "Jerry", true, Gender.MALE, "jerry@waltdisney.com", encryptedPassword);
 
        USERS_MAP.put(tom.getId(), tom);
        USERS_MAP.put(jerry.getId(), jerry);
		
	}

	@Override
	public Long getMaxUserId() {
		// TODO Auto-generated method stub
		long max = 0;
        for (Long id : USERS_MAP.keySet()) {
            if (id > max) {
                max = id;
            }
        }
        return max;
	}

	@Override
	public UserBean findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		Collection<UserBean> users = USERS_MAP.values();
        for (UserBean user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
	}

	@Override
	public UserBean findUserByEmail(String email) {
		// TODO Auto-generated method stub
		Collection<UserBean> users = USERS_MAP.values();
        for (UserBean user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
	}

	@Override
	public List<UserBean> getUsers() {
		// TODO Auto-generated method stub
		List<UserBean> list = new ArrayList<>();
		 
        list.addAll(USERS_MAP.values());
        return list;
	}

	@Override
	public UserBean createUser(UserFormBean form) {
		// TODO Auto-generated method stub
		Long userId = this.getMaxUserId() + 1;
        String encryptedPassword = this.passwordEncoder.encode(form.getPassword());
 
        UserBean user = new UserBean(userId, form.getUserName(), form.getFirstName(), form.getLastName(), false, 
                					form.getGender(), form.getEmail(), encryptedPassword);
 
        USERS_MAP.put(userId, user);
        return user;
	}

	

}
