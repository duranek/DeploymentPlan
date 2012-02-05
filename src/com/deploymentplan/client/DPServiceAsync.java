package com.deploymentplan.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.deploymentplan.domain.DevDeployment;
import com.deploymentplan.domain.DevDeploymentTree;
import com.deploymentplan.domain.DevOtherTaskTree;
import com.deploymentplan.domain.ProdDeployment;
import com.deploymentplan.domain.ProdDeploymentTree;
import com.deploymentplan.domain.ProdOtherTaskTree;
import com.deploymentplan.domain.user.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DPServiceAsync {

	void searchDeployments(ProdDeployment pOBProdDeployment, AsyncCallback generateCallbackSearch);

	void getDevDeployments(DevDeployment pOBDevDeployment, AsyncCallback generateCallbackGetRelatedDevDeployments);

	void getDeployItemTypes(AsyncCallback generateCallbackGetDeployItemTypes);

	void insertDeploymentTrees(DevDeploymentTree lOBComponent, DevDeploymentTree lOBDeploymentType, DevDeploymentTree lOBDeploymentItemName,  AsyncCallback generateCallbackInsertDeploymentTrees);

	void getDevDeploymentTreeList(DevDeploymentTree pOBDevDeploymentTree, AsyncCallback generateCallbackGetDeploymentTree);

	void getComponents(AsyncCallback generateCallbackGetComponents);

//	void insertDeploymentTree(DeploymentTree lOBDeploymentType, AsyncCallback callback);

	void getItems(ProdDeploymentTree pOBProdDeploymentTree,AsyncCallback generateCallbackGetItems);

	void updateProdDeployment(ProdDeployment pOBProdDeployment, AsyncCallback generateCallbackUpdateProdDeployment);

	void getProdDeploymentTreeList(ProdDeploymentTree lOBTree, AsyncCallback generateCallbackGetProdDeploymentTree);

	void addDevDeployment(DevDeployment pOBDevDeployment,  AsyncCallback generateCallbackAddDevDeployment);

	void deleteProdDeployment(ProdDeployment pOBProdDeployment, AsyncCallback generateCallbackDeleteProdDeployment);

	void deleteDevDeployment(DevDeployment pOBDevDeployment, AsyncCallback generateCallbackDeleteDevDeployment);

	void updateDevDeployment(DevDeployment pOBDevDeployment, AsyncCallback generateCallbackUpdateDevDeployment);

	void deleteTreeDetail(DevDeploymentTree lOBComponent, AsyncCallback generateCallbackDeleteTreeDetail);

	void addProdDeployment(ProdDeployment pOBProdDeployment, AsyncCallback generateCallbackAddProdDeployment);

	void generateKYSCommentForDev(DevDeploymentTree lOBDevDeployment, Integer pOBKYSCommentType,AsyncCallback generateCallbackGenerateKYSCommentForDev);

	void generateKYSCommentForProd(ProdDeploymentTree lOBProdDeploymentTree, AsyncCallback generateCallbackGenerateKYSCommentForProd);

	void getOtherTaskTypes(AsyncCallback generateCallbackGetOtherTaskTypesForDev);

	void addDevOtherTask(DevOtherTaskTree pOBDevOtherTaskParent, DevOtherTaskTree pOBDevOtherTaskDetail, AsyncCallback generateCallbackAddDevOtherTask);

	void getDevOtherTaskList(DevOtherTaskTree lOBOtherTaskTree, AsyncCallback generateCallbackGetDevOtherTaskList);

	void getDevOtherTaskTree(DevOtherTaskTree pOBOtherTreeDetail, AsyncCallback generateCallbackGetDevOtherTaskTree);

	void getProdOtherTaskList(ProdOtherTaskTree lOBOtherTaskTreeProd, AsyncCallback generateCallbackGetProdOtherTaskList);

	void getProdOtherTaskTree(ProdOtherTaskTree prodOtherTaskTree, AsyncCallback generateCallbackProdDevOtherTaskTree);

	void deleteDevOtherTaskTree(DevOtherTaskTree pOBDevOtherTaskTree, AsyncCallback generateCallbackDeleteDevOtherTaskTree);

	void getTransferOthersProdList(ProdDeployment lOBProdDeployment, AsyncCallback generateCallbackTransferOthersProdList);

	void transferOthers(ProdDeployment lOBProdDeployment, ProdDeployment lOBToProdDeployment, AsyncCallback generateCallbackTransferOthersProdList);

	void checkWarningsForDev(DevDeploymentTree lOBDevDeploymentTree, AsyncCallback generateCallbackCheckWarningsForDev);

	void refreshBusyComponents(AsyncCallback generateCallbackRefreshBusyComponents);

	void getVersioningWarning(DevDeploymentTree lOBComponent, AsyncCallback generateCallbackVersioningWarning);

	void deployDevDeployment(DevDeployment lOBDevDeployment, AsyncCallback generateCallbackDeployDevDeployment);

	void deployProdDeployment(ProdDeployment lOBProdDeployment, AsyncCallback generateCallbackDeployDevDeployment);

	void refreshUserList(AsyncCallback generateCallbackRefreshUserList);

	void refreshUserComponentKnowledge(AsyncCallback generateCallbackRefreshUserComponentKnowledge);

	void getBusyComponentsProjects(String pSTComponentName, AsyncCallback generateCallbackGetBusyComponentsProjects);

	void getBulkDeploymentDependentProjects(ProdDeployment lOBProdDeployment, AsyncCallback generateCallbackGetBulkDeploymentDependentProjects);

	void getProdBulkDeploymentTreeList(ArrayList<HashMap<String, String>> lOBList, AsyncCallback generateCallbackGetProdBulkDeploymentTreeList);

	void refreshProdStatus(AsyncCallback generateCallbackRefreshProdStatus);

	void refreshGenerateKYSCommentForDevTypes(AsyncCallback generateCallbackGenerateKYSCommentForDevTypes);
	



}
