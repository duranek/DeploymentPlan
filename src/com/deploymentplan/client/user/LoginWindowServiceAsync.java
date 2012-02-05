package com.deploymentplan.client.user;

import com.deploymentplan.domain.user.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginWindowServiceAsync {

	void login(User lOBUser, AsyncCallback generateCallbackLogin);

}
