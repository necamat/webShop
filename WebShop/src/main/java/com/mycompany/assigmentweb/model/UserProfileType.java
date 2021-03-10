package com.mycompany.assigmentweb.model;

 
public enum UserProfileType {
	ADMIN("ADMIN"),
        USER("USER"),
        EMPMENAGER("EMPMENAGER"),
        WARMENAGER("WARMENAGER"),
        SHOPMENAGER("SHOPMENAGER");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
