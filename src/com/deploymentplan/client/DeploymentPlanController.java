package com.deploymentplan.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.deploymentplan.domain.DevDeployment;
import com.deploymentplan.domain.DevDeploymentTree;
import com.deploymentplan.domain.DevOtherTaskTree;
import com.deploymentplan.domain.ProdDeployment;
import com.deploymentplan.domain.ProdDeploymentTree;
import com.deploymentplan.domain.ProdOtherTaskTree;
import com.deploymentplan.domain.user.User;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

public class DeploymentPlanController {
	private DeploymentPlanView view = null;
	private int minDevDeploymentId = -1;
	private int minProdDeploymentId = -1;
	private VLayout mOBVLayoutDeploymentTreeEntry = null;
	private User mOBUser;

	public User getmOBUser() {
		return mOBUser;
	}

	public void setmOBUser(User pOBUser) {
		mOBUser = pOBUser;
		view.setTitle(view.getTitle() + " - Username: " + getmOBUser().getName());
	}

	public DeploymentPlanController(DeploymentPlanView pOBDeploymentPlanView) {
		view = pOBDeploymentPlanView;
		view.getmOBGridDevDeployment().addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
			@Override
			public void onRecordDoubleClick(RecordDoubleClickEvent event) {
				ListGridRecord lOBListGridRecord = (ListGridRecord) event.getRecord();
				setMinDevDeploymentId(Integer.parseInt(lOBListGridRecord.getAttribute("id")));
				resetDevDeploymentTree();
				setRightPanel(view.getmOBVLayoutDevRP());
				resetTreeDetailComboboxes();
				view.getmOBFormDevDetail().clearValues();
				view.getmOBFormGenerateKYSComment().clearValues();
				view.getmOBFormDevOtherTasks().clearValues();
				view.getmOBFormDevDetailTextDevName().setValue(lOBListGridRecord.getAttribute("name"));
				view.getmOBFormDevDetailCheckDeployed().setValue(isDeployed(lOBListGridRecord));
				resetOtherTaskTypesComboForDev();
				resetDevOtherTasksTree();
				view.getmOBFormDevOtherTasksButtonDelete().setDisabled(true);
				view.getmOBFormDevOtherTasksButtonAdd().setDisabled(true);
				view.getmOBFormDevTreeDetailButtonAdd().setDisabled(true);
				view.getmOBFormDevTreeDetailButtonDelete().setDisabled(true);
			}
		});

		view.getmOBGridProdDeployment().addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
			@Override
			public void onRecordDoubleClick(RecordDoubleClickEvent event) {
				ListGridRecord lOBListGridSelectedRow = (ListGridRecord) event.getRecord();
				setMinProdDeploymentId(Integer.parseInt(lOBListGridSelectedRow.getAttribute("id")));
				setMinDevDeploymentId(-1);
				resetRelatedDevDeployments();
				resetProdDeploymentTree();
				setRightPanel(view.getmOBVLayoutProdRP());
				view.getmOBFormProdDetail().clearValues();
				view.getmOBFormGenerateProdKYSComment().clearValues();
				view.getmOBFormProdOtherTasks().clearValues();
				view.getmOBFormProdDetailTextProdName().setValue(lOBListGridSelectedRow.getAttribute("name"));
				view.getmOBFormProdDetailCheckDeployed().setValue(isDeployed(lOBListGridSelectedRow));
				view.getmOBFormProdDetailTextCEBFlowID().setValue(lOBListGridSelectedRow.getAttribute("cebflowid"));
				view.getmOBFormProdDetailSelectStatus().setValue(lOBListGridSelectedRow.getAttribute("prodstatusid"));
				resetProdOtherTaskTree();
				resetProdTransferOthersForm();
			}
		});

		view.getmOBFormSearchButtonSearch().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (view.getmOBFormSearch().validate()) {
					clearDeploymentGrids();
					searchDeployments();
				}

			}
		});

		view.getmOBFormDevTreeDetailButtonAdd().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (view.getmOBFormDevTreeDetail().validate()) {
					insertDeploymentItemDetail();

				}
			}
		});

		view.getmOBFormProdDetailButtonUpdate().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (view.getmOBFormProdDetail().validate()) {
					updateProdDeployment();
				}
			}

		});

		view.getmOBFormDevDetailButtonDeploy().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deployDevDeployment();
			}
		});

		view.getmOBFormProdDetailButtonDeploy().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deployProdDeployment();

			}
		});

		view.getmOBFormNewDevDeploymentButtonAddDev().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (view.getmOBFormNewDevDeployment().validate()) {
					addDevDeployment();
				}
			}

		});

		view.getmOBFormProdDetailButtonDelete().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteProdDeployment();

			}
		});

		view.getmOBFormDevDetailButtonDelete().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteDevDeployment();

			}
		});

		view.getmOBFormDevDetailButtonUpdate().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (view.getmOBFormProdDetail().validate()) {
					updateDevDeployment();
				}
			}

		});

		view.getmOBFormDevTreeDetailButtonDelete().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteTreeDetail();

			}
		});

		view.getmOBFormSearchButtonNewProd().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setRightPanel(view.getmOBVLayoutProdRPNewProd());
				clearDeploymentGrids();
				clearTreeGrid();
				setMinDevDeploymentId(-1);
				setMinProdDeploymentId(-1);

			}
		});

		view.getmOBFormNewProdDeploymentButtonAddProd().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addProdDeployment();

			}
		});

		view.getmOBFormDevTreeDetailSelectDeployItemType().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				fireDeployItemTypeChanged();
			}
		});

		view.getmOBFormDevTreeDetailSelectItemName().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				fireItemNameChanged();
			}
		});

		view.getmOBFormDevTreeDetailSelectComponentName().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				fireComponentNameChanged();
			}
		});

		view.getmOBFormGenerateKYSCommentButtonGenerate().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				generateKYSComment();
			}
		});

		view.getmOBFormGenerateProdKYSCommentButtonGenerate().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				generateKYSComment();
			}
		});

		view.getmOBTreeGridDeploymentTree().addCellClickHandler(new CellClickHandler() {

			@Override
			public void onCellClick(CellClickEvent event) {
				fireDeploymentTreeClicked();
			}
		});

		view.getmOBTreeGridOtherTree().addCellClickHandler(new CellClickHandler() {

			@Override
			public void onCellClick(CellClickEvent event) {
				resetOtherTaskForm();

			}
		});

		view.getmOBFormDevOtherTasksButtonAdd().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addDevOtherTask();

			}
		});

		view.getmOBFormDevOtherTasksButtonDelete().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteDevOtherTask();

			}
		});

		view.getmOBFormDevOtherTasksSelectOtherTaskTypes().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				view.getmOBFormDevOtherTasksTextItemShortDescription().clearValue();
				view.getmOBFormDevOtherTasksTextAreaExplanation().clearValue();
				view.getmOBFormDevOtherTasksButtonDelete().setDisabled(true);
				view.getmOBFormDevOtherTasksButtonAdd().setDisabled(false);
			}
		});

		view.getmOBFormProdRequestButtonSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				view.getmOBFormProdRequestFileItem().getValue();

			}
		});

		view.getmOBFormProdTransferOthersButtonTransfer().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (view.getmOBFormProdTransferOthers().validate()) {
					ProdDeployment lOBFromProdDeployment = new ProdDeployment();
					lOBFromProdDeployment.setId(getMinProdDeploymentId());
					ProdDeployment lOBToProdDeployment = new ProdDeployment();
					lOBToProdDeployment.setId(Integer.parseInt((String) view.getmOBFormProdTransferOthersSelectProdDeployment().getValue()));
					DPServiceAsync lOBAsync = DPService.Util.getInstance();
					lOBAsync.transferOthers(lOBFromProdDeployment, lOBToProdDeployment, generateCallbackTransferOthers());
				}

			}
		});

		view.getmOBButtonRefreshGridSummaryBusyComponents().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.refreshBusyComponents(generateCallbackRefreshBusyComponents());
			}
		});

		view.getmOBButtonRefreshGridUserList().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.refreshUserList(generateCallbackRefreshUserList());
			}
		});

		view.getmOBButtonRefreshGridUserComponentKnowledge().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.refreshUserComponentKnowledge(generateCallbackRefreshUserComponentKnowledge());
			}
		});

		view.getmOBGridSummaryBusyComponents().addRecordClickHandler(new RecordClickHandler() {

			@Override
			public void onRecordClick(RecordClickEvent event) {
				// TODO Auto-generated method stub
				ListGridRecord lOBListGridSelectedRow = (ListGridRecord) event.getRecord();
				String lSTComponentName = lOBListGridSelectedRow.getAttribute("component");
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.getBusyComponentsProjects(lSTComponentName, generateCallbackGetBusyComponentsProjects());
			}
		});

		view.getmOBButtonRefreshBulkDeployment().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				String[] lSTKeys = new String[0];
				ProdDeployment lOBProdDeployment = new ProdDeployment();
				lOBProdDeployment.setKeyList(lSTKeys);
				lOBProdDeployment.setDeployed(0);// not deployed
				lOBAsync.searchDeployments(lOBProdDeployment, generateCallbackRefreshBulkDeployment());
			}
		});

		view.getmOBGridProdBulkDeployment().addRecordClickHandler(new RecordClickHandler() {

			@Override
			public void onRecordClick(RecordClickEvent event) {
				// TODO Auto-generated method stub
				ListGridRecord lOBListGridSelectedRow = (ListGridRecord) event.getRecord();
				ProdDeployment lOBProdDeployment = new ProdDeployment();
				lOBProdDeployment.setId(Integer.parseInt(lOBListGridSelectedRow.getAttribute("id")));
				lOBProdDeployment.setName(lOBListGridSelectedRow.getAttribute("name"));
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.getBulkDeploymentDependentProjects(lOBProdDeployment, generateCallbackGetBulkDeploymentDependentProjects());
			}
		});

		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.refreshUserList(generateCallbackRefreshUserList());

//		lOBAsync = DPService.Util.getInstance();
//		lOBAsync.refreshBusyComponents(generateCallbackRefreshBusyComponents());
//
//		lOBAsync = DPService.Util.getInstance();
//		lOBAsync.refreshUserComponentKnowledge(generateCallbackRefreshUserComponentKnowledge());
//
//		lOBAsync.refreshProdStatus(generateCallbackRefreshProdStatus());
//		lOBAsync.refreshGenerateKYSCommentForDevTypes(generateCallbackGenerateKYSCommentForDevTypes());
	}

	private void fireDeploymentTreeClicked() {
		if (getMinDevDeploymentId() != -1) {
			clearTreeComboBoxes();
		}
		resetGenerateKYSCommentForm();
		view.getmOBFormDevTreeDetailButtonDelete().setDisabled(false);
		view.getmOBFormDevTreeDetailButtonAdd().setDisabled(true);
	}

	private void fireComponentNameChanged() {
		view.getmOBFormDevTreeDetailSelectDeployItemType().clearValue();
		view.getmOBFormDevTreeDetailSelectItemName().clearValue();
		// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().clearValue();
		// view.getmOBFormDevTreeDetailSelectUsedByItemName().clearValue();
		view.getmOBFormDevTreeDetailSelectDeployItemType().setDisabled(false);
		// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().setDisabled(true);
		// view.getmOBFormDevTreeDetailSelectUsedByItemName().setDisabled(true);
		view.getmOBFormDevTreeDetailSelectItemName().setDisabled(true);
		view.getmOBFormDevTreeDetailButtonAdd().setDisabled(false);
		view.getmOBFormDevTreeDetailButtonDelete().setDisabled(true);

	}

	private void fireItemNameChanged() {
		// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().setDisabled(false);
		// view.getmOBFormDevTreeDetailSelectUsedByItemName().setDisabled(true);
		// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().clearValue();
		// view.getmOBFormDevTreeDetailSelectUsedByItemName().clearValue();
		// DPServiceAsync lOBAsync = DPService.Util.getInstance();
		// lOBAsync.getDeployItemTypes(generateCallbackGetUsedByDeployItemTypes());
	}

	private void resetProdTransferOthersForm() {
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		ProdDeployment lOBProdDeployment = new ProdDeployment();
		lOBProdDeployment.setId(getMinProdDeploymentId());
		lOBAsync.getTransferOthersProdList(lOBProdDeployment, generateCallbackTransferOthersProdList());
	}

	private void deleteDevOtherTask() {
		DPTreeNode lOBRecord = (DPTreeNode) view.getmOBTreeGridOtherTree().getSelectedRecord();
		if (lOBRecord.getParentId() != -1) {
			DevOtherTaskTree lOBDevOtherTaskTree = new DevOtherTaskTree();
			lOBDevOtherTaskTree.setId(lOBRecord.getId());
			DPServiceAsync lOBAsync = DPService.Util.getInstance();
			lOBAsync.deleteDevOtherTaskTree(lOBDevOtherTaskTree, generateCallbackDeleteDevOtherTaskTree());
		} else {
			SC.warn("Please select a detail");
		}

	}

	private boolean isDeployed(ListGridRecord pOBListGridRecordRow) {
		String lSTDeployed = pOBListGridRecordRow.getAttribute("deployed");
		return lSTDeployed.equalsIgnoreCase("1");
	}

	private void resetOtherTaskForm() {
		DPTreeNode lOBRecord = (DPTreeNode) view.getmOBTreeGridOtherTree().getSelectedRecord();
		if (lOBRecord.getParentId() != -1) {
			if (getMinDevDeploymentId() != -1) {
				view.getmOBFormDevOtherTasks().clearValues();
				DevOtherTaskTree lOBOtherTreeDetail = new DevOtherTaskTree();
				lOBOtherTreeDetail.setId(lOBRecord.getId());
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.getDevOtherTaskTree(lOBOtherTreeDetail, generateCallbackGetDevOtherTaskTree());
			} else {
				view.getmOBFormProdOtherTasks().clearValues();
				ProdOtherTaskTree lOBOtherTreeDetail = new ProdOtherTaskTree();
				lOBOtherTreeDetail.setId(lOBRecord.getId());
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.getProdOtherTaskTree(lOBOtherTreeDetail, generateCallbackGetProdOtherTaskTree());
			}
		} else {
			view.getmOBFormDevOtherTasks().clearValues();
			view.getmOBFormProdOtherTasks().clearValues();
		}
		view.getmOBFormDevOtherTasksButtonDelete().setDisabled(false);
		view.getmOBFormDevOtherTasksButtonAdd().setDisabled(true);
	}

	private void resetDevOtherTasksTree() {
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		DevOtherTaskTree lOBOtherTaskTree = new DevOtherTaskTree();
		lOBOtherTaskTree.setDevDeploymentId(getMinDevDeploymentId());
		lOBAsync.getDevOtherTaskList(lOBOtherTaskTree, generateCallbackGetDevOtherTaskList());
	}

	private void resetProdOtherTaskTree() {
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		ProdOtherTaskTree lOBOtherTaskTreeProd = new ProdOtherTaskTree();
		lOBOtherTaskTreeProd.setProdDeploymentId(getMinProdDeploymentId());
		lOBAsync.getProdOtherTaskList(lOBOtherTaskTreeProd, generateCallbackGetProdOtherTaskList());
	}

	private void addDevOtherTask() {
		if (view.getmOBFormDevOtherTasks().validate()) {
			DevOtherTaskTree lOBDevOtherTaskParent = new DevOtherTaskTree();
			lOBDevOtherTaskParent.setParentId(-1);
			lOBDevOtherTaskParent.setShortDescription(view.getmOBFormDevOtherTasksSelectOtherTaskTypes().getDisplayValue());
			lOBDevOtherTaskParent.setDevDeploymentId(getMinDevDeploymentId());

			DevOtherTaskTree lOBDevOtherTaskDetail = new DevOtherTaskTree();
			lOBDevOtherTaskDetail.setDevDeploymentId(getMinDevDeploymentId());
			lOBDevOtherTaskDetail.setShortDescription((String) view.getmOBFormDevOtherTasksTextItemShortDescription().getValue());
			lOBDevOtherTaskDetail.setExplanation((String) view.getmOBFormDevOtherTasksTextAreaExplanation().getValue());

			DPServiceAsync lOBAsync = DPService.Util.getInstance();
			lOBAsync.addDevOtherTask(lOBDevOtherTaskParent, lOBDevOtherTaskDetail, generateCallbackAddDevOtherTask());

		}
	}

	private void resetOtherTaskTypesComboForDev() {
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.getOtherTaskTypes(generateCallbackGetOtherTaskTypesForDev());
	}

	private void resetGenerateKYSCommentForm() {
		if (getMinDevDeploymentId() != -1) {
			view.getmOBFormGenerateKYSCommentSelectType().clearValue();
			view.getmOBFormGenerateKYSCommentTextComment().clearValue();
			if (isJarItemSelected()) {
				view.getmOBFormGenerateKYSCommentSelectType().setValue(getClassCheckingCommentId());
			}
			ListGridRecord lOBRecord = view.getmOBTreeGridDeploymentTree().getSelectedRecord();
			view.getmOBFormGenerateKYSCommentTextComponent().setValue((String) lOBRecord.getAttribute("Name"));
		} else {
			view.getmOBFormGenerateProdKYSCommentTextComment().clearValue();
			ListGridRecord lOBRecord = view.getmOBTreeGridDeploymentTree().getSelectedRecord();
			view.getmOBFormGenerateProdKYSCommentTextComponent().setValue((String) lOBRecord.getAttribute("Name"));
		}
	}

	private boolean isJarItemSelected() {
		if (!isComponentSelected()) {
			DPTreeNode lOBRecord = (DPTreeNode) view.getmOBTreeGridDeploymentTree().getSelectedRecord();
			Tree lOBTree = (Tree) view.getmOBTreeGridDeploymentTree().getTree();
			DPTreeNode lOBTreeNode = (DPTreeNode) lOBTree.getParent(lOBRecord);
			return lOBTreeNode.getName().equalsIgnoreCase("jar");
		}
		return false;
	}

	private boolean isClassCheckinComment() {
		String lSTKYSCommentType = (String) view.getmOBFormGenerateKYSCommentSelectType().getValue();
		return isCommentTypeSelected() && lSTKYSCommentType.equalsIgnoreCase(getClassCheckingCommentId());
	}

	private boolean isCommentTypeSelected() {
		String lSTKYSCommentType = (String) view.getmOBFormGenerateKYSCommentSelectType().getValue();
		return lSTKYSCommentType != null;
	}

	private String getClassCheckingCommentId() {
		return String.valueOf(5);
	}

	private boolean isComponentSelected() {
		DPTreeNode lOBRecord = (DPTreeNode) view.getmOBTreeGridDeploymentTree().getSelectedRecord();
		return lOBRecord.getAttribute("ReportsTo") == null;
	}

	private void generateKYSComment() {
		DPTreeNode lOBRecord = (DPTreeNode) view.getmOBTreeGridDeploymentTree().getSelectedRecord();
		if (!isCommentTypeSelected()) {
			SC.warn("Please select a comment type");
			return;
		}
		if (lOBRecord == null || (!isComponentSelected() && !isJarItemSelected())) {
			SC.warn("Please select a component or a class");
			return;
		}		
		if (!isJarItemSelected() && isClassCheckinComment()) {
			SC.warn("For check-in comment please select a class");
			return;
		}
		if (isJarItemSelected() && !isClassCheckinComment()) {
			SC.warn("For a class, you can generate only check-in comment.");
			return;
		}
		if (view.getmOBFormGenerateKYSComment().validate()) {
			if (getMinDevDeploymentId() != -1) {
				DevDeploymentTree lOBDevDeploymentTree = new DevDeploymentTree();
				lOBDevDeploymentTree.setId(lOBRecord.getId());
				lOBDevDeploymentTree.setName(lOBRecord.getName());
				lOBDevDeploymentTree.setDevDeploymentId(getMinDevDeploymentId());
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.getVersioningWarning(lOBDevDeploymentTree, generateCallbackVersioningWarningForKYSComment());
				Integer lOBKYSCommentType = new Integer((String) view.getmOBFormGenerateKYSCommentSelectType().getValue());
				lOBAsync.generateKYSCommentForDev(lOBDevDeploymentTree, lOBKYSCommentType, generateCallbackGenerateKYSCommentForDev());
			} else {
				ProdDeploymentTree lOBProdDeploymentTree = new ProdDeploymentTree();
				lOBProdDeploymentTree.setId(lOBRecord.getId());
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.generateKYSCommentForProd(lOBProdDeploymentTree, generateCallbackGenerateKYSCommentForProd());
			}
		}
	}

	private void fireDeployItemTypeChanged() {
		view.getmOBFormDevTreeDetailSelectItemName().clearValue();
		// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().clearValue();
		// view.getmOBFormDevTreeDetailSelectUsedByItemName().clearValue();
		view.getmOBFormDevTreeDetailSelectItemName().setDisabled(false);
		// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().setDisabled(true);
		// view.getmOBFormDevTreeDetailSelectUsedByItemName().setDisabled(true);
		ProdDeploymentTree lOBProdDeploymentTree = new ProdDeploymentTree();
		// Secilmis componentin altinda, secilmis tipte olan itemlari getir
		lOBProdDeploymentTree.setSubItemType(Integer.parseInt((String) view.getmOBFormDevTreeDetailSelectDeployItemType().getValue()));
		lOBProdDeploymentTree.setName(view.getmOBFormDevTreeDetailSelectComponentName().getDisplayValue());
		// ****
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.getItems(lOBProdDeploymentTree, generateCallbackGetItems());
	}

	private void addProdDeployment() {
		ProdDeployment lOBProdDeployment = new ProdDeployment();
		lOBProdDeployment.setName(view.getmOBFormNewProdDeploymentTextProdName().getDisplayValue());
		lOBProdDeployment.setCebflowId(Integer.parseInt(view.getmOBFormNewProdCEBFlowID().getDisplayValue()));
		lOBProdDeployment.setProdStatus(Integer.parseInt((String) view.getmOBFormNewProdDetailSelectStatus().getValue()));
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.addProdDeployment(lOBProdDeployment, generateCallbackAddProdDeployment());
	}

	private void deleteTreeDetail() {
		ListGridRecord lOBRecord = view.getmOBTreeGridDeploymentTree().getSelectedRecord();

		if (lOBRecord == null) {
			SC.warn("Please select an item");
		} else {
			DevDeploymentTree lOBDelete = new DevDeploymentTree();
			lOBDelete.setId(Integer.parseInt(lOBRecord.getAttribute("EmployeeId")));
			DPServiceAsync lOBAsync = DPService.Util.getInstance();
			lOBAsync.deleteTreeDetail(lOBDelete, generateCallbackDeleteTreeDetail());
		}
		view.getmOBFormGenerateKYSComment().clearValues();

	}

	private void deleteProdDeployment() {
		ProdDeployment lOBProdDeployment = new ProdDeployment();
		lOBProdDeployment.setId(getMinProdDeploymentId());
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.deleteProdDeployment(lOBProdDeployment, generateCallbackDeleteProdDeployment());
	}

	private void deleteDevDeployment() {
		DevDeployment lOBDevDeployment = new DevDeployment();
		lOBDevDeployment.setId(getMinDevDeploymentId());
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.deleteDevDeployment(lOBDevDeployment, generateCallbackDeleteDevDeployment());
	}

	private void updateProdDeployment() {
		ProdDeployment lOBProdDeployment = new ProdDeployment();
		lOBProdDeployment.setId(getMinProdDeploymentId());
		lOBProdDeployment.setName(view.getmOBFormProdDetailTextProdName().getDisplayValue());
		lOBProdDeployment.setCebflowId(Integer.parseInt(view.getmOBFormProdDetailTextCEBFlowID().getDisplayValue()));
		lOBProdDeployment.setProdStatus(Integer.parseInt((String) view.getmOBFormProdDetailSelectStatus().getValue()));
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.updateProdDeployment(lOBProdDeployment, generateCallbackUpdateProdDeployment());
	}

	private void updateDevDeployment() {
		DevDeployment lOBDevDeployment = new DevDeployment();
		lOBDevDeployment.setId(getMinDevDeploymentId());
		lOBDevDeployment.setName(view.getmOBFormDevDetailTextDevName().getDisplayValue());
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.updateDevDeployment(lOBDevDeployment, generateCallbackUpdateDevDeployment());
	}

	private void deployDevDeployment() {
		DevDeployment lOBDevDeployment = new DevDeployment();
		lOBDevDeployment.setId(getMinDevDeploymentId());
		lOBDevDeployment.setDeployed(getCheckBoxItemValue(view.getmOBFormDevDetailCheckDeployed()));
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.deployDevDeployment(lOBDevDeployment, generateCallbackDeployDevDeployment());

	}

	private void deployProdDeployment() {
		ProdDeployment lOBProdDeployment = new ProdDeployment();
		lOBProdDeployment.setId(getMinProdDeploymentId());
		lOBProdDeployment.setDeployed(getCheckBoxItemValue(view.getmOBFormProdDetailCheckDeployed()));
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.deployProdDeployment(lOBProdDeployment, generateCallbackDeployProdDeployment());
	}

	private int getCheckBoxItemValue(CheckboxItem pOBCheckBoxItem) {
		if ((Boolean) pOBCheckBoxItem.getValue()) {
			return 1;
		} else {
			return 0;
		}
	}

	private void resetTreeDetailComboboxes() {
		clearTreeComboBoxes();
		disableTreeDetailComboboxes();
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.getDeployItemTypes(generateCallbackGetDeployItemTypes());
		lOBAsync.getComponents(generateCallbackGetComponents());
	}

	private void disableTreeDetailComboboxes() {
		view.getmOBFormDevTreeDetailSelectComponentName().setDisabled(true);
		view.getmOBFormDevTreeDetailSelectDeployItemType().setDisabled(true);
		view.getmOBFormDevTreeDetailSelectItemName().setDisabled(true);
		// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().setDisabled(true);
		// view.getmOBFormDevTreeDetailSelectUsedByItemName().setDisabled(true);
	}

	private void insertDeploymentItemDetail() {
		DevDeploymentTree lOBComponent = new DevDeploymentTree();

		lOBComponent.setParentId(-1);
		lOBComponent.setDevDeploymentId(getMinDevDeploymentId());
		lOBComponent.setName((String) view.getmOBFormDevTreeDetailSelectComponentName().getDisplayValue());
		lOBComponent.setItemType(1);

		DevDeploymentTree lOBDeploymentType = new DevDeploymentTree();
		lOBDeploymentType.setDevDeploymentId(getMinDevDeploymentId());
		lOBDeploymentType.setName((String) view.getmOBFormDevTreeDetailSelectDeployItemType().getDisplayValue());
		lOBDeploymentType.setItemType(2);
		lOBDeploymentType.setSubItemType(Integer.parseInt((String) view.getmOBFormDevTreeDetailSelectDeployItemType().getValue()));

		DevDeploymentTree lOBDeploymentItemName = new DevDeploymentTree();
		lOBDeploymentItemName.setName((String) view.getmOBFormDevTreeDetailSelectItemName().getDisplayValue());
		lOBDeploymentItemName.setDevDeploymentId(getMinDevDeploymentId());
		lOBDeploymentItemName.setItemType(3);

		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.getVersioningWarning(lOBComponent, generateCallbackVersioningWarningForInsertItem(lOBComponent, lOBDeploymentType, lOBDeploymentItemName));

	}

	private void addDevDeployment() {
		DevDeployment lOBDevDeployment = new DevDeployment();
		lOBDevDeployment.setName(view.getmOBFormNewDevDeploymentTextDevName().getDisplayValue());
		lOBDevDeployment.setProdDeploymentId(getMinProdDeploymentId());
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.addDevDeployment(lOBDevDeployment, generateCallbackAddDevDeployment());
	}

	private void searchDeployments() {
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		String lSTAllKeys = view.getmOBFormSearchTextSearchBox().getDisplayValue();
		String[] lSTKeys = lSTAllKeys.split(" ");
		ProdDeployment lOBProdDeployment = new ProdDeployment();
		lOBProdDeployment.setKeyList(lSTKeys);
		lOBProdDeployment.setDeployed(getCheckBoxItemValue(view.getmOBFormSearchCheckDeployed()));
		lOBAsync.searchDeployments(lOBProdDeployment, generateCallbackSearch());
	}

	private void clearDeploymentGrids() {
		view.getmOBGridDevDeployment().setData(new ListGridRecord[0]);
		view.getmOBGridProdDeployment().setData(new ListGridRecord[0]);
	}

	private void clearTreeComboBoxes() {
		view.getmOBFormDevTreeDetailSelectComponentName().clearValue();
		view.getmOBFormDevTreeDetailSelectItemName().clearValue();
		view.getmOBFormDevTreeDetailSelectDeployItemType().clearValue();
		// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().clearValue();
		// view.getmOBFormDevTreeDetailSelectUsedByItemName().clearValue();

	}

	private void resetDevDeploymentTree() {
		DevDeploymentTree lOBTree = new DevDeploymentTree();
		lOBTree.setDevDeploymentId(getMinDevDeploymentId());
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.getDevDeploymentTreeList(lOBTree, generateCallbackGetDevDeploymentTreeList());
	}

	private void resetProdDeploymentTree() {
		ProdDeploymentTree lOBProdDeploymentTree = new ProdDeploymentTree();
		lOBProdDeploymentTree.setProdDeploymentId(getMinProdDeploymentId());
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.getProdDeploymentTreeList(lOBProdDeploymentTree, generateCallbackGetProdDeploymentTreeList());
	}

	private void resetRelatedDevDeployments() {
		DevDeployment lOBDevDeployment = new DevDeployment();
		lOBDevDeployment.setProdDeploymentId(getMinProdDeploymentId());
		DPServiceAsync lOBAsync = DPService.Util.getInstance();
		lOBAsync.getDevDeployments(lOBDevDeployment, generateCallbackGetRelatedDevDeployments());
	}

	private void setRightPanel(VLayout pOBVLayoutRight) {
		if (getmOBVLayoutDeploymentTreeEntry() != null) {
			view.getmOBHLayoutMain().removeMember(getmOBVLayoutDeploymentTreeEntry());
		}
		setmOBVLayoutDeploymentTreeEntry(pOBVLayoutRight);
		view.getmOBHLayoutMain().addMember(getmOBVLayoutDeploymentTreeEntry());
	}

	private VLayout getmOBVLayoutDeploymentTreeEntry() {
		return mOBVLayoutDeploymentTreeEntry;
	}

	private void setmOBVLayoutDeploymentTreeEntry(VLayout mOBVLayoutDeploymentTreeEntry) {
		this.mOBVLayoutDeploymentTreeEntry = mOBVLayoutDeploymentTreeEntry;
	}

	private int getMinDevDeploymentId() {
		return minDevDeploymentId;
	}

	private void setMinDevDeploymentId(int minDevDeploymentId) {
		this.minDevDeploymentId = minDevDeploymentId;
	}

	private int getMinProdDeploymentId() {
		return minProdDeploymentId;
	}

	private void setMinProdDeploymentId(int minProdDeploymentId) {
		this.minProdDeploymentId = minProdDeploymentId;
	}

	private AsyncCallback generateCallbackSearch() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Searching Prod Deployments", lSTMessage, null, new Dialog());

			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList<String> lOBList = (ArrayList<String>) result;
				if (lOBList.size() == 0) {
					SC.warn("Searching Prod Deployments ", "There is no data", null, new Dialog());
				} else {
					ListGridRecord[] lOBRecord = new ListGridRecord[lOBList.size()];
					int i = 0;
					for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
						HashMap lOBMap = (HashMap) iterator.next();
						lOBRecord[i] = new ListGridRecord();
						lOBRecord[i].setAttribute("name", lOBMap.get("name"));
						lOBRecord[i].setAttribute("id", lOBMap.get("id"));
						lOBRecord[i].setAttribute("owner", lOBMap.get("owner"));
						lOBRecord[i].setAttribute("deployed", lOBMap.get("deployed"));
						lOBRecord[i].setAttribute("date", lOBMap.get("date"));
						lOBRecord[i].setAttribute("cebflowid", lOBMap.get("cebflowid"));
						lOBRecord[i].setAttribute("prodstatusid", lOBMap.get("prodstatus"));
						lOBRecord[i].setAttribute("prodstatusname", lOBMap.get("prodstatusname"));
						if (lOBMap.get("deploydate") == null) {
							lOBRecord[i].setAttribute("deploydate", lOBMap.get("deploydate"));
						} else {
							lOBRecord[i].setAttribute("deploydate", DateTimeFormat.getFormat("yyyyMMdd").format((Date) lOBMap.get("deploydate")));
						}
						i++;
					}
					view.getmOBGridProdDeployment().setData(lOBRecord);
				}
			}

		};
	}

	private AsyncCallback generateCallbackRefreshBulkDeployment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Searching Prod Deployments For Bulk Deployment", lSTMessage, null, new Dialog());

			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList<String> lOBList = (ArrayList<String>) result;
				if (lOBList.size() == 0) {
					SC.warn("Searching Prod Deployments For Bulk Deployment ", "There is no data", null, new Dialog());
				} else {
					ListGridRecord[] lOBRecord = new ListGridRecord[lOBList.size()];
					int i = 0;
					for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
						HashMap lOBMap = (HashMap) iterator.next();
						lOBRecord[i] = new ListGridRecord();
						lOBRecord[i].setAttribute("name", lOBMap.get("name"));
						lOBRecord[i].setAttribute("id", lOBMap.get("id"));
						lOBRecord[i].setAttribute("owner", lOBMap.get("owner"));
						lOBRecord[i].setAttribute("date", lOBMap.get("date"));
						lOBRecord[i].setAttribute("cebflowid", lOBMap.get("cebflowid"));
						i++;
					}
					view.getmOBGridProdBulkDeployment().setData(lOBRecord);
				}
			}

		};
	}

	private AsyncCallback generateCallbackGetRelatedDevDeployments() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting related Dev Deployments", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList<String> lOBList = (ArrayList<String>) result;
				ListGridRecord[] lOBRecord = new ListGridRecord[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					DevDeployment lOBDevDeployment = (DevDeployment) iterator.next();
					lOBRecord[i] = new ListGridRecord();
					lOBRecord[i].setAttribute("name", lOBDevDeployment.getName());
					lOBRecord[i].setAttribute("id", lOBDevDeployment.getId());
					lOBRecord[i].setAttribute("owner", lOBDevDeployment.getInfoOwnerName());
					lOBRecord[i].setAttribute("deployed", lOBDevDeployment.getDeployed());
					lOBRecord[i].setAttribute("date", lOBDevDeployment.getDate());
					if (lOBDevDeployment.getDeployDate() == null) {
						lOBRecord[i].setAttribute("deploydate", lOBDevDeployment.getDeployDate());
					} else {
						lOBRecord[i].setAttribute("deploydate", DateTimeFormat.getFormat("yyyyMMdd").format(lOBDevDeployment.getDeployDate()));
					}

					i++;
				}
				view.getmOBGridDevDeployment().setData(lOBRecord);
			}

		};
	}

	private AsyncCallback generateCallbackGetDeployItemTypes() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Deployment Item Type List ", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				LinkedHashMap lOBInfoTypeMap = (LinkedHashMap) result;
				view.getmOBFormDevTreeDetailSelectDeployItemType().setValueMap(lOBInfoTypeMap);
			}

		};
	}

	// private AsyncCallback generateCallbackGetUsedByDeployItemTypes() {
	// return new AsyncCallback() {
	// public void onFailure(Throwable caught) {
	// caught.printStackTrace();
	// String lSTMessage = null;
	// if (caught.getMessage() != null) {
	// lSTMessage = caught.getMessage();
	// } else {
	// lSTMessage = "No Error Message, possibly null pointer exception";
	// }
	// SC.warn("Getting Deployment Used By Item Type List ", lSTMessage, null, new Dialog());
	// }
	//
	// @SuppressWarnings("unchecked")
	// public void onSuccess(Object result) {
	// ArrayList lOBList = new ArrayList();
	// LinkedHashMap lOBInfoTypeMap = (LinkedHashMap) result;
	// for (Iterator iterator = lOBInfoTypeMap.keySet().iterator(); iterator.hasNext();) {
	// String type = (String) iterator.next();
	// if(isIgnoreUsedByDeployItemType(type)) {
	// lOBList.add(type);
	// }
	// }
	// for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
	// String lOBType = (String) iterator.next();
	// lOBInfoTypeMap.remove(lOBType);
	// }
	// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().setValueMap(lOBInfoTypeMap);
	// }
	//			
	// private boolean isIgnoreUsedByDeployItemType(String pOBDeployItemType) {
	// String lOBDeployItemType = (String) view.getmOBFormDevTreeDetailSelectDeployItemType().getValue();
	// if(lOBDeployItemType.equalsIgnoreCase("1") ) {
	// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().setRequired(true);
	// view.getmOBFormDevTreeDetailSelectUsedByItemName().setRequired(true);
	// return true;
	// } else if(lOBDeployItemType.equalsIgnoreCase("2")) {
	// if(pOBDeployItemType.equalsIgnoreCase("3")) {
	// return false;
	// } else return true;
	// } else if(lOBDeployItemType.equalsIgnoreCase("3")) {
	// if(pOBDeployItemType.equalsIgnoreCase("4") || pOBDeployItemType.equalsIgnoreCase("5") ) {
	// return false;
	// } else return true;
	// } else if(lOBDeployItemType.equalsIgnoreCase("4")) {
	// view.getmOBFormDevTreeDetailSelectUsedByDeployItemType().setRequired(true);
	// view.getmOBFormDevTreeDetailSelectUsedByItemName().setRequired(true);
	// return true;
	// } else if(lOBDeployItemType.equalsIgnoreCase("5")) {
	// if(pOBDeployItemType.equalsIgnoreCase("4") ) {
	// return false;
	// } else return true;
	// } else return true;
	// }
	//
	// };
	// }

	private AsyncCallback generateCallbackInsertDeploymentTrees() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Adding Deployment Tree Item ", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				AsyncCallback lOBAsync = generateCallbackGetDevDeploymentTreeList();
				lOBAsync.onSuccess(result);
				resetTreeDetailComboboxes();
			}

		};
	}

	private AsyncCallback generateCallbackGetDevDeploymentTreeList() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Dev Deployment Tree List", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBList = (ArrayList) result;
				DPTreeNode[] lOBDPTreeNode = new DPTreeNode[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					HashMap lOBMap = (HashMap) iterator.next();
					lOBDPTreeNode[i] = new DPTreeNode(String.valueOf(lOBMap.get("id")), String.valueOf(lOBMap.get("parentid")), (String) lOBMap.get("name"), (String) lOBMap.get("conflict"));
					i++;
				}
				final Tree tree = new Tree();
				tree.setModelType(TreeModelType.PARENT);
				tree.setNameProperty("Name");
				tree.setIdField("EmployeeId");
				tree.setParentIdField("ReportsTo");
				// tree.setShowRoot(true);

				tree.setData(lOBDPTreeNode);
				tree.openAll();

				view.getmOBTreeGridDeploymentTree().setData(tree);
			}

		};
	}

	private AsyncCallback generateCallbackGetProdDeploymentTreeList() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Prod Deployment Tree", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBList = (ArrayList) result;
				DPTreeNode[] lOBEmployeeTreeNode = new DPTreeNode[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					HashMap<String, String> lOBMap = (HashMap<String, String>) iterator.next();
					lOBEmployeeTreeNode[i] = new DPTreeNode(String.valueOf(lOBMap.get("id")), String.valueOf(lOBMap.get("parentid")), String.valueOf(lOBMap.get("name")), String.valueOf(lOBMap
							.get("conflict")));
					i++;
				}
				final Tree tree = new Tree();
				tree.setModelType(TreeModelType.PARENT);
				tree.setNameProperty("Name");
				tree.setIdField("EmployeeId");
				tree.setParentIdField("ReportsTo");
				// tree.setShowRoot(true);

				tree.setData(lOBEmployeeTreeNode);
				tree.openAll();
				view.getmOBTreeGridDeploymentTree().setData(tree);
			}

		};
	}

	private AsyncCallback generateCallbackGetComponents() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Component List ", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBInfoList = (ArrayList) result;
				String[] sarray = new String[lOBInfoList.size()];
				int i = 0;
				for (Iterator iterator = lOBInfoList.iterator(); iterator.hasNext();) {
					String s = (String) iterator.next();
					sarray[i] = s;
					i++;
				}
				view.getmOBFormDevTreeDetailSelectComponentName().setValueMap(sarray);
				view.getmOBFormDevTreeDetailSelectComponentName().setDisabled(false);
			}

		};
	}

	private AsyncCallback generateCallbackGetItems() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Item List", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBInfoList = (ArrayList) result;
				String[] sarray = new String[lOBInfoList.size()];
				int i = 0;
				for (Iterator iterator = lOBInfoList.iterator(); iterator.hasNext();) {
					String s = (String) iterator.next();
					sarray[i] = s;
					i++;
				}
				view.getmOBFormDevTreeDetailSelectItemName().setValueMap(sarray);
				view.getmOBFormDevTreeDetailSelectItemName().setDisabled(false);
			}

		};
	}

	private AsyncCallback generateCallbackUpdateProdDeployment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Updating Prod Deployment ", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ProdDeployment lOBProdDeployment = (ProdDeployment) result;
				view.getmOBFormProdDetailCheckDeployed().setValue(lOBProdDeployment.getDeployed() == 1);
				view.getmOBFormProdDetailTextCEBFlowID().setValue(String.valueOf(lOBProdDeployment.getCebflowId()));
				view.getmOBFormProdDetailTextProdName().setValue(lOBProdDeployment.getName());
				view.getmOBFormSearchCheckDeployed().setValue(lOBProdDeployment.getDeployed() == 1);
				view.getmOBFormSearchTextSearchBox().setValue(view.getmOBFormProdDetailTextProdName().getDisplayValue());
				// view.getmOBFormProdDetail().clearValues();
				clearDeploymentGrids();
				searchDeployments();
				resetRelatedDevDeployments();
				// removeRightPanel();
				// clearTreeGrid();
				SC.warn("Prod detail updated");
			}

		};
	}

	private void clearTreeGrid() {
		view.getmOBTreeGridDeploymentTree().setData(new Tree());
	}

	private void removeRightPanel() {
		view.getmOBHLayoutMain().removeMember(getmOBVLayoutDeploymentTreeEntry());
		setmOBVLayoutDeploymentTreeEntry(null);
	}

	private AsyncCallback generateCallbackAddDevDeployment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Adding Dev Deployment ", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				resetRelatedDevDeployments();
				view.getmOBFormNewDevDeployment().clearValues();
			}

		};
	}

	private AsyncCallback generateCallbackAddProdDeployment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Adding New Prod Deployment ", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				view.getmOBFormSearchTextSearchBox().setValue(view.getmOBFormNewProdDeploymentTextProdName().getDisplayValue());
				view.getmOBFormNewProdDeployment().clearValues();
				clearDeploymentGrids();
				removeRightPanel();
				searchDeployments();

			}

		};
	}

	private AsyncCallback generateCallbackDeleteProdDeployment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Deleting Prod Deployment ", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				view.getmOBFormProdDetail().clearValues();
				clearDeploymentGrids();
				setMinDevDeploymentId(-1);
				setMinProdDeploymentId(-1);
				removeRightPanel();
				clearTreeGrid();
			}

		};
	}

	private AsyncCallback generateCallbackDeleteDevDeployment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Deleting Dev Deployment", lSTMessage, null, new Dialog());

			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				view.getmOBFormDevDetail().clearValues();
				clearDeploymentGrids();
				setMinDevDeploymentId(-1);
				searchDeployments();
				resetRelatedDevDeployments();
				removeRightPanel();
				clearTreeGrid();
			}

		};
	}

	private AsyncCallback generateCallbackUpdateDevDeployment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Updating Prod Deployment ", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				DevDeployment lOBDevDeployment = (DevDeployment) result;
				view.getmOBFormDevDetailCheckDeployed().setValue(lOBDevDeployment.getDeployed() == 1);
				view.getmOBFormDevDetailTextDevName().setValue(lOBDevDeployment.getName());
				resetRelatedDevDeployments();
				view.getmOBFormDevTreeDetail().clearValues();
				SC.warn("Dev Detail Updated");
			}

		};
	}

	private AsyncCallback generateCallbackDeleteTreeDetail() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Deleting Deployment Tree Item", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				clearTreeComboBoxes();
				resetDevDeploymentTree();
			}

		};
	}

	private AsyncCallback generateCallbackGenerateKYSCommentForProd() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Generate KYS Comment For Prod", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				String lSTComment = (String) result;
				view.getmOBFormGenerateProdKYSCommentTextComment().setValue(lSTComment);
			}

		};
	}

	private AsyncCallback generateCallbackGenerateKYSCommentForDev() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Generate KYS Comment", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				String lSTComment = (String) result;
				view.getmOBFormGenerateKYSCommentTextComment().setValue(lSTComment);
			}

		};
	}

	private AsyncCallback generateCallbackGetOtherTaskTypesForDev() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Get Other Task Types For Dev", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				LinkedHashMap lOBInfoTypeMap = (LinkedHashMap) result;
				view.getmOBFormDevOtherTasksSelectOtherTaskTypes().setValueMap(lOBInfoTypeMap);
			}

		};
	}

	private AsyncCallback generateCallbackAddDevOtherTask() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Add Dev Other Task", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBList = (ArrayList) result;
				DPTreeNode[] lOBEmployeeTreeNode = new DPTreeNode[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					DevOtherTaskTree lOBTree = (DevOtherTaskTree) iterator.next();
					lOBEmployeeTreeNode[i] = new DPTreeNode(String.valueOf(lOBTree.getId()), String.valueOf(lOBTree.getParentId()), lOBTree.getShortDescription(), "0");
					i++;
				}
				final Tree tree = new Tree();
				tree.setModelType(TreeModelType.PARENT);
				tree.setNameProperty("Name");
				tree.setIdField("EmployeeId");
				tree.setParentIdField("ReportsTo");

				tree.setData(lOBEmployeeTreeNode);
				tree.openAll();
				view.getmOBTreeGridOtherTree().setData(tree);
				view.getmOBFormDevOtherTasks().clearValues();
			}

		};
	}

	private AsyncCallback generateCallbackGetDevOtherTaskList() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Get Dev Other Task List", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBList = (ArrayList) result;
				DPTreeNode[] lOBEmployeeTreeNode = new DPTreeNode[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					DevOtherTaskTree lOBTree = (DevOtherTaskTree) iterator.next();
					lOBEmployeeTreeNode[i] = new DPTreeNode(String.valueOf(lOBTree.getId()), String.valueOf(lOBTree.getParentId()), lOBTree.getShortDescription(), "0");
					i++;
				}
				final Tree tree = new Tree();
				tree.setModelType(TreeModelType.PARENT);
				tree.setNameProperty("Name");
				tree.setIdField("EmployeeId");
				tree.setParentIdField("ReportsTo");

				tree.setData(lOBEmployeeTreeNode);
				tree.openAll();
				view.getmOBTreeGridOtherTree().setData(tree);
			}

		};
	}

	private AsyncCallback generateCallbackGetProdOtherTaskList() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Get Prod Other Task List", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBList = (ArrayList) result;
				DPTreeNode[] lOBEmployeeTreeNode = new DPTreeNode[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					ProdOtherTaskTree lOBTree = (ProdOtherTaskTree) iterator.next();
					lOBEmployeeTreeNode[i] = new DPTreeNode(String.valueOf(lOBTree.getId()), String.valueOf(lOBTree.getParentId()), lOBTree.getShortDescription(), "0");
					i++;
				}
				final Tree tree = new Tree();
				tree.setModelType(TreeModelType.PARENT);
				tree.setNameProperty("Name");
				tree.setIdField("EmployeeId");
				tree.setParentIdField("ReportsTo");

				tree.setData(lOBEmployeeTreeNode);
				tree.openAll();
				view.getmOBTreeGridOtherTree().setData(tree);
			}

		};
	}

	private AsyncCallback generateCallbackGetDevOtherTaskTree() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Get Dev Other Task Item Explanation", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				DevOtherTaskTree lOBDevOtherTaskTree = (DevOtherTaskTree) result;
				view.getmOBFormDevOtherTasksTextAreaExplanation().setValue(lOBDevOtherTaskTree.getExplanation());
			}

		};
	}

	private AsyncCallback generateCallbackDeleteDevOtherTaskTree() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Delete Dev Other Task Item ", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				view.getmOBFormDevOtherTasks().clearValues();
				resetDevOtherTasksTree();
			}

		};
	}

	private AsyncCallback generateCallbackGetProdOtherTaskTree() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Get Prod Other Task Item Explanation", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ProdOtherTaskTree lOBProdOtherTaskTree = (ProdOtherTaskTree) result;
				view.getmOBFormProdOtherTasksTextAreaExplanation().setValue(lOBProdOtherTaskTree.getExplanation());
			}

		};
	}

	private AsyncCallback generateCallbackTransferOthersProdList() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("When Getting Transfer Others Prod List", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				LinkedHashMap lOBInfoTypeMap = (LinkedHashMap) result;
				view.getmOBFormProdTransferOthersSelectProdDeployment().clearValue();
				view.getmOBFormProdTransferOthersSelectProdDeployment().setValueMap(lOBInfoTypeMap);

			}

		};
	}

	private AsyncCallback generateCallbackTransferOthers() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("When Transferring Others", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				resetProdOtherTaskTree();
				resetProdTransferOthersForm();
			}

		};
	}

	private AsyncCallback generateCallbackCheckWarningsForDev() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Checking  Warning For Dev Deployments", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				SC.warn("Warning !!!", (String) result, null, new Dialog());
			}

		};
	}

	private AsyncCallback generateCallbackRefreshBusyComponents() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Refresh Busy Components", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList<String> lOBList = (ArrayList<String>) result;
				ListGridRecord[] lOBRecord = new ListGridRecord[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					HashMap lOBMap = (HashMap) iterator.next();
					lOBRecord[i] = new ListGridRecord();
					lOBRecord[i].setAttribute("component", lOBMap.get("component"));
					lOBRecord[i].setAttribute("number", lOBMap.get("numberofprojects"));
					i++;
				}
				view.getmOBGridSummaryBusyComponents().setData(lOBRecord);
				view.getmOBGridSummaryBusyComponentsProjects().setData(new ListGridRecord[0]);
			}

		};
	}

	private AsyncCallback generateCallbackRefreshUserList() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Refresh User List", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBList = (ArrayList) result;
				ListGridRecord[] lOBRecord = new ListGridRecord[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					HashMap lOBMap = (HashMap) iterator.next();
					lOBRecord[i] = new ListGridRecord();
					lOBRecord[i].setAttribute("username", lOBMap.get("username"));
					lOBRecord[i].setAttribute("title", lOBMap.get("title"));
					lOBRecord[i].setAttribute("rank", lOBMap.get("rank"));
					lOBRecord[i].setAttribute("count", lOBMap.get("count"));
					i++;
				}
				view.getmOBGridUserList().setData(lOBRecord);
			}

		};
	}

	private AsyncCallback generateCallbackRefreshUserComponentKnowledge() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Refresh User List", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBList = (ArrayList) result;
				ListGridRecord[] lOBRecord = new ListGridRecord[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					HashMap lOBMap = (HashMap) iterator.next();
					lOBRecord[i] = new ListGridRecord();
					lOBRecord[i].setAttribute("username", lOBMap.get("username"));
					lOBRecord[i].setAttribute("title", lOBMap.get("title"));
					lOBRecord[i].setAttribute("rank", lOBMap.get("rank"));
					lOBRecord[i].setAttribute("count", lOBMap.get("count"));
					i++;
				}
				view.getmOBGridUserComponentKnowledge().setData(lOBRecord);
			}

		};
	}

	private AsyncCallback generateCallbackRefreshProdStatus() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Prod Status", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				LinkedHashMap lOBInfoTypeMap = (LinkedHashMap) result;
				view.getmOBFormProdDetailSelectStatus().setValueMap(lOBInfoTypeMap);
				view.getmOBFormNewProdDetailSelectStatus().setValueMap(lOBInfoTypeMap);
			}

		};
	}

	private AsyncCallback generateCallbackGenerateKYSCommentForDevTypes() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting KYS Comment For Dev Types", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				LinkedHashMap lOBInfoTypeMap = (LinkedHashMap) result;
				view.getmOBFormGenerateKYSCommentSelectType().setValueMap(lOBInfoTypeMap);
			}

		};
	}

	private AsyncCallback generateCallbackVersioningWarningForInsertItem(final DevDeploymentTree lOBComponent, final DevDeploymentTree lOBDeploymentType, final DevDeploymentTree lOBDeploymentItemName) {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Versioning Warning For Insert Item", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				String lSTWarning = (String) result;
				if (!lSTWarning.equalsIgnoreCase("")) {
					SC.warn("This Message Is Important , Versioning Discrepancy Alert!", lSTWarning + "<br><br>Please get new jar version before doing your changes.", null, new Dialog());
				}
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.insertDeploymentTrees(lOBComponent, lOBDeploymentType, lOBDeploymentItemName, generateCallbackInsertDeploymentTrees());

			}

		};
	}

	private AsyncCallback generateCallbackVersioningWarningForKYSComment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Versioning Warning For KYS Comment", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				String lSTWarning = (String) result;
				if (!lSTWarning.equalsIgnoreCase("")) {
					SC
							.warn(
									"This Message Is Important , Versioning Discrepancy Alert!",
									lSTWarning
											+ "<br><br>1) Please check-in your classes on this component.<br> 2) Note your class versions. <br> 3) Get the new version of the component. <br> 4) Find your classes, get your versions of the classes.",
									null, new Dialog());
				}
			}

		};
	}

	private AsyncCallback generateCallbackDeployDevDeployment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Deploying/Undeploying Dev Deployment", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				DevDeployment lOBDevDeployment = (DevDeployment) result;
				if (lOBDevDeployment.getDeployed() == 1) {
					SC.warn("Dev Deployed");
				} else {
					SC.warn("Dev Undeployed");
				}
				view.getmOBFormDevDetailCheckDeployed().setValue(lOBDevDeployment.getDeployed() == 1);
				view.getmOBFormDevDetailTextDevName().setValue(lOBDevDeployment.getName());
				resetRelatedDevDeployments();
				view.getmOBFormDevTreeDetail().clearValues();
			}

		};
	}

	private AsyncCallback generateCallbackDeployProdDeployment() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Deploying/Undeploying Prod Deployment", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ProdDeployment lOBProdDeployment = (ProdDeployment) result;
				if (lOBProdDeployment.getDeployed() == 1) {
					SC.warn("Prod Deployed");
				} else {
					SC.warn("Prod Undeployed");
				}
				view.getmOBFormProdDetailCheckDeployed().setValue(lOBProdDeployment.getDeployed() == 1);
				view.getmOBFormProdDetailTextCEBFlowID().setValue(String.valueOf(lOBProdDeployment.getCebflowId()));
				view.getmOBFormProdDetailTextProdName().setValue(lOBProdDeployment.getName());

				resetRelatedDevDeployments();
				view.getmOBFormDevOtherTasks().clearValues();
				view.getmOBFormDevTreeDetail().clearValues();

			}

		};
	}

	private AsyncCallback generateCallbackGetBusyComponentsProjects() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Busy Components Projects", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList<String> lOBList = (ArrayList<String>) result;
				ListGridRecord[] lOBRecord = new ListGridRecord[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					HashMap lOBMap = (HashMap) iterator.next();
					lOBRecord[i] = new ListGridRecord();
					lOBRecord[i].setAttribute("project", lOBMap.get("project"));
					lOBRecord[i].setAttribute("undeployeddev", lOBMap.get("undeployeddev"));
					lOBRecord[i].setAttribute("deployeddev", lOBMap.get("deployeddev"));
					lOBRecord[i].setAttribute("compnotdeployed", lOBMap.get("compnotdeployed"));
					lOBRecord[i].setAttribute("compdeployed", lOBMap.get("compdeployed"));
					lOBRecord[i].setAttribute("username", lOBMap.get("username"));
					i++;
				}
				view.getmOBGridSummaryBusyComponentsProjects().setData(lOBRecord);
			}

		};
	}

	private AsyncCallback generateCallbackGetProdBulkDeploymentTreeList() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Busy Components Projects", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList lOBList = (ArrayList) result;
				BulkDeploymentTreeNode[] lOBBulkDeploymentTreeNode = new BulkDeploymentTreeNode[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					HashMap<String, String> lOBTreeMap = (HashMap) iterator.next();
					lOBBulkDeploymentTreeNode[i] = new BulkDeploymentTreeNode(lOBTreeMap.get("id"), lOBTreeMap.get("parentid"), lOBTreeMap.get("name"));
					i++;
				}
				final Tree tree = new Tree();
				tree.setModelType(TreeModelType.PARENT);
				tree.setNameProperty("name");
				tree.setIdField("id");
				tree.setParentIdField("parentid");
				// tree.setShowRoot(true);

				tree.setData(lOBBulkDeploymentTreeNode);
				tree.openAll();
				view.getmOBTreeGridProdBulkDeployment().setData(tree);
			}

		};
	}

	private AsyncCallback generateCallbackGetBulkDeploymentDependentProjects() {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				String lSTMessage = null;
				if (caught.getMessage() != null) {
					lSTMessage = caught.getMessage();
				} else {
					lSTMessage = "No Error Message, possibly null pointer exception";
				}
				SC.warn("Getting Bulk Deployment Dependent Projects", lSTMessage, null, new Dialog());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(Object result) {
				ArrayList<HashMap<String, String>> lOBList = (ArrayList<HashMap<String, String>>) result;
				ListGridRecord[] lOBRecord = new ListGridRecord[lOBList.size()];
				int i = 0;
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					HashMap lOBMap = (HashMap) iterator.next();
					lOBRecord[i] = new ListGridRecord();
					lOBRecord[i].setAttribute("project", lOBMap.get("project"));
					lOBRecord[i].setAttribute("undeployeddev", lOBMap.get("undeployeddev"));
					lOBRecord[i].setAttribute("deployeddev", lOBMap.get("deployeddev"));
					lOBRecord[i].setAttribute("username", lOBMap.get("username"));
					i++;
				}
				view.getmOBGridProdBulkDeploymentDependentProjects().setData(lOBRecord);
				DPServiceAsync lOBAsync = DPService.Util.getInstance();
				lOBAsync.getProdBulkDeploymentTreeList(lOBList, generateCallbackGetProdBulkDeploymentTreeList());
			}

		};
	}

	public static class DPTreeNode extends TreeNode {

		public DPTreeNode(String employeeId, String reportsTo, String name, String pSTConflict) {
			setEmployeeId(employeeId);
			setReportsTo(reportsTo);
			setName(name);
			setConflict(pSTConflict);
		}

		public void setConflict(String pSTConflict) {
			setAttribute("conflict", pSTConflict);
		}

		public void setEmployeeId(String value) {
			setAttribute("EmployeeId", value);
		}

		public void setReportsTo(String value) {
			setAttribute("ReportsTo", value);
		}

		public void setName(String name) {
			setAttribute("Name", name);
		}

		public int getParentId() {
			if (getAttribute("ReportsTo") == null) {
				return -1;
			} else {
				return Integer.parseInt((String) getAttribute("ReportsTo"));
			}
		}

		public int getId() {
			return Integer.parseInt((String) getAttribute("EmployeeId"));
		}

		public String getName() {
			return (String) getAttribute("Name");
		}

	}

	public static class BulkDeploymentTreeNode extends TreeNode {

		public BulkDeploymentTreeNode(String id, String parentid, String name) {
			setAttribute("id", id);
			setAttribute("parentid", parentid);
			setAttribute("name", name);
		}

	}

}
