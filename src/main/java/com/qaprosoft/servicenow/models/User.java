package com.qaprosoft.servicenow.models;

import static com.qaprosoft.servicenow.models.UserRoles.ITIL;
import static com.qaprosoft.servicenow.models.UserRoles.NO_ROLES;
import static com.qaprosoft.servicenow.util.Constants.USERS.ITIL_USER_NAME;
import static com.qaprosoft.servicenow.util.Constants.USERS.ITIL_USER_PASSWORD;
import static com.qaprosoft.servicenow.util.Constants.USERS.NO_ROLES_USER_NAME;
import static com.qaprosoft.servicenow.util.Constants.USERS.NO_ROLES_USER_PASSWORD;

public class User {
	private static final User ITIL_USER = new User(ITIL_USER_NAME, ITIL_USER_PASSWORD, ITIL);
	private static final User NO_ROLES_USER = new User(NO_ROLES_USER_NAME, NO_ROLES_USER_PASSWORD, NO_ROLES);
	
	private String userName;
	private String password;
	private UserRoles role;
	
	private User(String userName, String password, UserRoles role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public static User getUserInstance(UserRoles role) {
		switch (role) {
			case ITIL: return ITIL_USER;
			case NO_ROLES: return NO_ROLES_USER;
			default: throw new UnsupportedOperationException("Unsupportable user type!");
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}
}
