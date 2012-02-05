package com.deploymentplan.server.user;

import java.io.Reader;

import javax.servlet.http.HttpSession;

import com.deploymentplan.client.user.LoginWindowService;
import com.deploymentplan.domain.user.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LoginWindowImpl extends RemoteServiceServlet implements LoginWindowService {

	public User login(User user) throws Exception {
		User lOBUser = null;
		SqlMapClient sqlMap = null;
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();
			lOBUser = (User) sqlMap.queryForObject("getUser", user);
			if (lOBUser == null) {
				throw new Exception("User null");
			}
			sqlMap.commitTransaction();
			
//			HttpSession lOBSession = this.getThreadLocalRequest().getSession(true);
//			lOBSession.setAttribute("ownerId", new Integer(lOBUser.getId()));
//			lOBSession.setMaxInactiveInterval(14400);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		
		return lOBUser;

	}
	
	@Override
	protected void checkPermutationStrongName() throws SecurityException {
		return;
	}
}
