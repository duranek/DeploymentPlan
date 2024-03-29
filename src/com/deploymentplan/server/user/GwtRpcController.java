package com.deploymentplan.server.user;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GwtRpcController extends RemoteServiceServlet implements
		Controller, ServletContextAware {

	private RemoteService remoteService;
	private ServletContext servletContext;
	private Class remoteServiceClass;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.doPost(request, response);
        return null;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

	@Override
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setRemoteService(RemoteService remoteService) {
		this.remoteService = remoteService;
		this.remoteServiceClass = this.remoteService.getClass();
	}

	@Override
	public String processCall(String payload) throws SerializationException {
		try {

			RPCRequest rpcRequest = RPC.decodeRequest(payload,
					this.remoteServiceClass);

			// delegate work to the spring injected service
			return RPC.invokeAndEncodeResponse(this.remoteService,
					rpcRequest.getMethod(), rpcRequest.getParameters());
		} catch (IncompatibleRemoteServiceException ex) {
			getServletContext()
					.log("An IncompatibleRemoteServiceException was thrown while processing this call.",
							ex);
			return RPC.encodeResponseForFailure(null, ex);
		}
	}

}
