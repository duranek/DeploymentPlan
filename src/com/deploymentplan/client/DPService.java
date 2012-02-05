package com.deploymentplan.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.deploymentplan.domain.DevDeployment;
import com.deploymentplan.domain.DevDeploymentTree;
import com.deploymentplan.domain.DevOtherTaskTree;
import com.deploymentplan.domain.ProdDeployment;
import com.deploymentplan.domain.ProdDeploymentTree;
import com.deploymentplan.domain.ProdOtherTaskTree;
import com.deploymentplan.domain.user.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface DPService extends RemoteService {
	public static final String SERVICE_URI = "databaseservice";

	public static class Util {

		public static DPServiceAsync getInstance() {
			DPServiceAsync instance = (DPServiceAsync) GWT.create(DPService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}

	ArrayList searchDeployments(ProdDeployment pOBProdDeployment) throws Exception;;

	ArrayList getDevDeployments(DevDeployment pOBDevDeployment) throws Exception;

	LinkedHashMap getDeployItemTypes() throws Exception;;

	ArrayList insertDeploymentTrees(DevDeploymentTree lOBComponent, DevDeploymentTree lOBDeploymentType, DevDeploymentTree lOBDeploymentItemName) throws Exception;

	ArrayList getDevDeploymentTreeList(DevDeploymentTree pOBDevDeploymentTree) throws Exception;

	ArrayList getComponents() throws Exception;

	ArrayList getItems(ProdDeploymentTree pOBProdDeploymentTree) throws Exception;

	ProdDeployment updateProdDeployment(ProdDeployment pOBProdDeployment) throws Exception;

	ArrayList getProdDeploymentTreeList(ProdDeploymentTree pOBProdDeploymentTree) throws Exception;

	Integer addDevDeployment(DevDeployment pOBDevDeployment) throws Exception;

	void deleteProdDeployment(ProdDeployment pOBProdDeployment) throws Exception;

	void deleteDevDeployment(DevDeployment pOBDevDeployment) throws Exception;

	DevDeployment updateDevDeployment(DevDeployment pOBDevDeployment) throws Exception;

	ArrayList deleteTreeDetail(DevDeploymentTree lOBComponent) throws Exception;

	void addProdDeployment(ProdDeployment pOBProdDeployment) throws Exception;

	String generateKYSCommentForDev(DevDeploymentTree lOBDevDeployment,Integer pOBKYSCommentForDevType) throws Exception;

	String generateKYSCommentForProd(ProdDeploymentTree lOBProdDeploymentTree) throws Exception;

	LinkedHashMap getOtherTaskTypes() throws Exception;

	ArrayList addDevOtherTask(DevOtherTaskTree pOBDevOtherTaskParent, DevOtherTaskTree pOBDevOtherTaskDetail) throws Exception;

	ArrayList getDevOtherTaskList(DevOtherTaskTree lOBOtherTaskTree) throws Exception;

	DevOtherTaskTree getDevOtherTaskTree(DevOtherTaskTree pOBOtherTreeDetail) throws Exception;

	ArrayList getProdOtherTaskList(ProdOtherTaskTree lOBOtherTaskTreeProd) throws Exception;

	ProdOtherTaskTree getProdOtherTaskTree(ProdOtherTaskTree prodOtherTaskTree)throws Exception;

	void deleteDevOtherTaskTree(DevOtherTaskTree pOBDevOtherTaskTree)throws Exception;

	LinkedHashMap getTransferOthersProdList(ProdDeployment pOBProdDeployment) throws Exception;

	void transferOthers(ProdDeployment lOBFromProdDeployment,ProdDeployment lOBToProdDeployment) throws Exception;

	String checkWarningsForDev(DevDeploymentTree lOBDevDeploymentTree) throws Exception;

	ArrayList refreshBusyComponents() throws Exception;
	
	String getVersioningWarning(DevDeploymentTree lOBComponent) throws Exception; 

	DevDeployment deployDevDeployment(DevDeployment lOBDevDeployment)throws Exception;

	ProdDeployment deployProdDeployment(ProdDeployment lOBProdDeployment)throws Exception;

	ArrayList<HashMap> refreshUserList() throws Exception;

	ArrayList refreshUserComponentKnowledge() throws Exception;

	ArrayList getBusyComponentsProjects(String pSTComponentName)throws Exception;

	ArrayList getBulkDeploymentDependentProjects(ProdDeployment lOBProdDeployment)throws Exception;

	ArrayList getProdBulkDeploymentTreeList(ArrayList<HashMap<String, String>> lOBList)throws Exception;

	LinkedHashMap refreshProdStatus()throws Exception;

	LinkedHashMap refreshGenerateKYSCommentForDevTypes()throws Exception;
	
}
