package com.qaprosoft.servicenow.util;

import static java.io.File.separator;

import com.qaprosoft.carina.core.foundation.utils.Configuration;

public interface Constants {
	
	String SERVICE_NOW_URL = Configuration.getEnvArg("service_now.url");
	String FILE_PATH = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "resources" + separator;
	
	public interface USERS {
		String NO_ROLES_USER_NAME = Configuration.getEnvArg("service_now.no_roles.user");;
		String NO_ROLES_USER_PASSWORD = Configuration.getEnvArg("service_now.no_roles.password");;
		
		String ITIL_USER_NAME = Configuration.getEnvArg("service_now.itil.user");;
		String ITIL_USER_PASSWORD = Configuration.getEnvArg("service_now.itil.password");;
	}
}
