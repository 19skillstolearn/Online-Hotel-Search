package com.SkillsToLearn.onlinehotel.validator;

import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.SkillsToLearn.onlinehotel.dao.UserDao;
import com.SkillsToLearn.onlinehotel.formbean.UserFormBean;
import com.SkillsToLearn.onlinehotel.model.UserBean;

@Service
public class UserValidator implements Validator{
	
	@SuppressWarnings("deprecation")
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	private UserDao userDao;
	
	public boolean supports(Class<?> clazz) {
        return clazz == UserFormBean.class;
    }

	@SuppressWarnings("deprecation")
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserFormBean userFormBean = (UserFormBean) target;
		 
        // Check the fields of AppUserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.appUserForm.gender");
        
        if(!this.emailValidator.isValid(userFormBean.getEmail())) {
        	errors.rejectValue("email", "Pattern.userFormBean.email");
        } else if (userFormBean.getId() == null) {
        	UserBean user = userDao.findUserByEmail(userFormBean.getEmail());
        	if(user != null) {
        		errors.rejectValue("email", "Duplicate.userFormBean.email");
        	}
        }
        
        if(!errors.hasFieldErrors("userName")) {
        	UserBean user = userDao.findUserByUserName(userFormBean.getUserName());
        	if(user != null) {
        		errors.rejectValue("userName", "Duplicate.userFormBean.userName");
        	}
        }
        
        if(!errors.hasErrors()) {
        	if(!userFormBean.getConfirmPassword().equals(userFormBean.getPassword())) {
        		errors.rejectValue("confirmPassword", "Match.userFormBean.confirmPassword");
        	}
        }
	}
	
	

}
