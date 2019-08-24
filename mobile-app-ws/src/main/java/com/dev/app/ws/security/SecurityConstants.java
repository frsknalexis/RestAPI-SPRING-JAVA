package com.dev.app.ws.security;

import com.dev.app.ws.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864000000; //10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/api/users/users";
	//public static final String TOKEN_SECRET = "jf9i4jgu83nfl0";
	
	public static String getTokenSecret() {
		
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
}
