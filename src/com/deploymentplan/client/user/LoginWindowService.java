package com.deploymentplan.client.user;

import com.deploymentplan.domain.user.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login.rpc")
public interface LoginWindowService extends RemoteService{

	User login(User lOBUser) throws Exception;
	
	
	
	
//	public static final String SERVICE_URI = "loginwindowservice";
//
//	public static class Util {
//
//		public static LoginWindowServiceAsync getInstance() {
//			LoginWindowServiceAsync instance = (LoginWindowServiceAsync) GWT.create(LoginWindowService.class);
//			ServiceDefTarget target = (ServiceDefTarget) instance;
//			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
//			return instance;
//		}
//	}
//
//	User login(User lOBUser) throws Exception;

}
