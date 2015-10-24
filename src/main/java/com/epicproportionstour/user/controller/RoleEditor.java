package com.epicproportionstour.user.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import com.epicproportionstour.user.model.Role;
import com.epicproportionstour.user.service.LoginService;



public class RoleEditor extends PropertyEditorSupport {
		 
	    private final LoginService loginService;
	 
	    public RoleEditor(LoginService loginService, Class collectionType) {
	        super(collectionType);
	        this.loginService = loginService;
	    }
	 
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException {
	        Object obj = getValue();
	        List<Role> list = (List) obj;
	        System.out.println("=======================obj============"+obj);
	        System.out.println("==================================="+text);
	        
	        for (String str : text.split(",")) {
	        	System.out.println("00000000000000000000000000"+str);
	        	Role getmyroles=loginService.findRole(Integer.parseInt(str));
	        	Role role1=new Role(getmyroles.getId(),getmyroles.getRoleName());
	        	
	            list.add(role1);
	        }
	        setValue(list);
	    }
	 
	    @Override
	    public String getAsText() {
	    	System.out.println("=======================getclass============"+super.getClass());
	        return super.getAsText();
	    }
	}

