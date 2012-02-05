package com.deploymentplan.client;

import com.deploymentplan.client.user.LoginWindow;
import com.deploymentplan.client.user.LoginWindowInterface;
import com.deploymentplan.domain.user.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;
 
/** 
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DeploymentPlan implements EntryPoint, LoginWindowInterface {

	// private int minDevDeploymentId = -1;
	// private int minProdDeploymentId = -1;
	// private VLayout mOBVLayoutDeploymentTreeEntry = null;
	//
	// private DynamicForm mOBFormItemDetail = null;
	// private SelectItem mOBDeployItemType = null;
	// private SelectOtherItem mOBDeployItemName = null;
	// private SelectOtherItem mOBSelectComponentName = null;
	// private IButton mOBButtonFormItemDetailAdd = null;
	// private IButton mOBButtonAddDev = null;
	// private IButton mOBButtonProdDetailUpdate = null;
	//
	// private DynamicForm mOBFormSearch = null;
	//
	// private TextItem mOBTextSearchBox = null;
	//
	// private TreeGrid mOBTreeGridDeploymentTree = null;
	//
	// private ListGrid mOBGridProdDeployment = null;
	// private ListGrid mOBGridDevDeployment = null;
	//
	// private TextItem mOBTextProdName = null;
	// private CheckboxItem mOBCheckDeployed = null;
	// private DynamicForm mOBFormProdDeployDetail = null;
	// private DynamicForm mOBFormNewDevDeployment = null;
	// private TextItem mOBTextDevName = null;
	//
	// private VLayout mOBVLayoutDeployments = null;
	//
	// private HLayout mOBHLayoutMain = null;
	//
	// private VLayout mOBVLayoutProdTreeItemEntry = null;
	//
	// private VLayout mOBVLayoutNewDevDeploymentEntry = null;
	//
	// private VLayout mOBVLayoutDevTreeItemEntry = null;
	//
	// private VLayout mOBVLayoutProdDetailEntry = null;

	// public VLayout getmOBVLayoutProdDetailEntry() {
	// if (mOBVLayoutProdDetailEntry == null) {
	// mOBVLayoutProdDetailEntry = new VLayout(15);
	//
	// mOBVLayoutProdDetailEntry.addMember(getmOBVLayoutProdTreeItemEntry());
	// mOBVLayoutProdDetailEntry.addMember(getmOBVLayoutNewDevDeploymentEntry());
	//
	// }
	// return mOBVLayoutProdDetailEntry;
	// }

	// public VLayout getmOBVLayoutDevTreeItemEntry() {
	// if (mOBVLayoutDevTreeItemEntry == null) {
	// mOBVLayoutDevTreeItemEntry = new VLayout(5);
	// mOBVLayoutDevTreeItemEntry.setPadding(10);
	// mOBVLayoutDevTreeItemEntry.setBackgroundColor("white");
	// mOBVLayoutDevTreeItemEntry.setBorder("1px solid #c0c0c0");
	// mOBVLayoutDevTreeItemEntry.setShowShadow(true);
	// mOBVLayoutDevTreeItemEntry.setShadowSoftness(10);
	// mOBVLayoutDevTreeItemEntry.setShadowOffset(5);
	// mOBVLayoutDevTreeItemEntry.addMember(getmOBFormItemDetail());
	// mOBVLayoutDevTreeItemEntry.addMember(getmOBButtonFormItemDetailAdd());
	//
	// }
	// return mOBVLayoutDevTreeItemEntry;
	// }

	// public VLayout getmOBVLayoutNewDevDeploymentEntry() {
	// if (mOBVLayoutNewDevDeploymentEntry == null) {
	// mOBVLayoutNewDevDeploymentEntry = new VLayout(5);
	// mOBVLayoutNewDevDeploymentEntry.setPadding(10);
	// mOBVLayoutNewDevDeploymentEntry.setBackgroundColor("white");
	// mOBVLayoutNewDevDeploymentEntry.setBorder("1px solid #c0c0c0");
	// mOBVLayoutNewDevDeploymentEntry.setShowShadow(true);
	// mOBVLayoutNewDevDeploymentEntry.setShadowSoftness(10);
	// mOBVLayoutNewDevDeploymentEntry.setShadowOffset(5);
	//
	// mOBVLayoutNewDevDeploymentEntry.addMember(getmOBFormNewDevDeployment());
	// mOBVLayoutNewDevDeploymentEntry.addMember(getmOBButtonAddDev());
	// }
	// return mOBVLayoutNewDevDeploymentEntry;
	// }

	// public VLayout getmOBVLayoutProdTreeItemEntry() {
	// if (mOBVLayoutProdTreeItemEntry == null) {
	// mOBVLayoutProdTreeItemEntry = new VLayout(5);
	// mOBVLayoutProdTreeItemEntry.setPadding(10);
	// mOBVLayoutProdTreeItemEntry.setBackgroundColor("white");
	// mOBVLayoutProdTreeItemEntry.setBorder("1px solid #c0c0c0");
	// mOBVLayoutProdTreeItemEntry.setShowShadow(true);
	// mOBVLayoutProdTreeItemEntry.setShadowSoftness(10);
	// mOBVLayoutProdTreeItemEntry.setShadowOffset(5);
	//
	// mOBVLayoutProdTreeItemEntry.addMember(getmOBFormProdDeployDetail());
	// mOBVLayoutProdTreeItemEntry.addMember(getmOBButtonProdDetailUpdate());
	// }
	// return mOBVLayoutProdTreeItemEntry;
	// }

	// public HLayout getmOBHLayoutMain() {
	// if (mOBHLayoutMain == null) {
	// mOBHLayoutMain = new HLayout();
	//
	// mOBHLayoutMain.addMember(getmOBVLayoutDeployments());
	// mOBHLayoutMain.addMember(getmOBTreeGridDeploymentTree());
	// }
	// return mOBHLayoutMain;
	// }

	// public VLayout getmOBVLayoutDeployments() {
	// if (mOBVLayoutDeployments == null) {
	// mOBVLayoutDeployments = new VLayout();
	// mOBVLayoutDeployments.addMember(getmOBFormSearch());
	// mOBVLayoutDeployments.addMember(getmOBGridProdDeployment());
	// mOBVLayoutDeployments.addMember(getmOBGridDevDeployment());
	// }
	// return mOBVLayoutDeployments;
	// }

	// public ListGrid getmOBGridDevDeployment() {
	// if (mOBGridDevDeployment == null) {
	// mOBGridDevDeployment = new ListGrid();
	// mOBGridDevDeployment.setWidth(300);
	// mOBGridDevDeployment.setHeight(178);
	// mOBGridDevDeployment.setShowAllRecords(true);
	// mOBGridDevDeployment.setCanEdit(false);
	// mOBGridDevDeployment.setEditEvent(ListGridEditEvent.CLICK);
	// mOBGridDevDeployment.setModalEditing(true);
	// mOBGridDevDeployment.setShowGridSummary(true);
	// ListGridField lOBListGridDevDeploymentID = new ListGridField("id", "ID");
	// ListGridField lOBListGirdDevDeploymentName = new ListGridField("name", "Dev Deployment Name");
	// lOBListGirdDevDeploymentName.setShowGridSummary(true);
	// lOBListGirdDevDeploymentName.setSummaryFunction(new SummaryFunction() {
	// @Override
	// public Object getSummaryValue(Record[] records, ListGridField field) {
	// return records.length + " Deployments";
	// }
	// });
	// mOBGridDevDeployment.setFields(new ListGridField[] { lOBListGridDevDeploymentID, lOBListGirdDevDeploymentName });
	// mOBGridDevDeployment.hideField("id");
	//
	// mOBGridDevDeployment.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
	// @Override
	// public void onRecordDoubleClick(RecordDoubleClickEvent event) {
	// ListGridRecord lOBListGridRecord = (ListGridRecord) event.getRecord();
	// DeploymentTree lOBTree = new DeploymentTree();
	// setMinDevDeploymentId(Integer.parseInt(lOBListGridRecord.getAttribute("id")));
	// lOBTree.setDevDeploymentId(getMinDevDeploymentId());
	// DPServiceAsync lOBAsync = DPService.Util.getInstance();
	// lOBAsync.getDeploymentTree(lOBTree, generateCallbackGetDeploymentTree(getmOBTreeGridDeploymentTree()));
	// if (getmOBVLayoutDeploymentTreeEntry() != null) {
	// getmOBHLayoutMain().removeMember(getmOBVLayoutDeploymentTreeEntry());
	// getmOBVLayoutDeploymentTreeEntry().destroy();
	// }
	// setmOBVLayoutDeploymentTreeEntry(getmOBVLayoutDevTreeItemEntry());
	// getmOBHLayoutMain().addMember(getmOBVLayoutDeploymentTreeEntry());
	// }
	// });
	// }
	// return mOBGridDevDeployment;
	// }

	// public ListGrid getmOBGridProdDeployment() {
	// if (mOBGridProdDeployment == null) {
	// mOBGridProdDeployment = new ListGrid();
	// mOBGridProdDeployment.setWidth(300);
	// mOBGridProdDeployment.setHeight(178);
	// mOBGridProdDeployment.setShowAllRecords(true);
	// mOBGridProdDeployment.setCanEdit(false);
	// mOBGridProdDeployment.setEditEvent(ListGridEditEvent.CLICK);
	// mOBGridProdDeployment.setModalEditing(true);
	// mOBGridProdDeployment.setShowGridSummary(true);
	// ListGridField lOBListGridProdDeploymentID = new ListGridField("id", "ID");
	// ListGridField lOBListGirdProdDeploymentName = new ListGridField("name", "Production Deployment");
	// lOBListGirdProdDeploymentName.setShowGridSummary(true);
	// lOBListGirdProdDeploymentName.setSummaryFunction(new SummaryFunction() {
	// @Override
	// public Object getSummaryValue(Record[] records, ListGridField field) {
	// return records.length + " Deployments";
	// }
	// });
	// mOBGridProdDeployment.setFields(new ListGridField[] { lOBListGridProdDeploymentID, lOBListGirdProdDeploymentName });
	// mOBGridProdDeployment.hideField("id");
	//
	// mOBGridProdDeployment.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
	// @Override
	// public void onRecordDoubleClick(RecordDoubleClickEvent event) {
	// ListGridRecord lOBListGridSelectedRow = (ListGridRecord) event.getRecord();
	// DeploymentTree lOBDeploymentTreeDevSelector = new DeploymentTree();
	// setMinProdDeploymentId(Integer.parseInt(lOBListGridSelectedRow.getAttribute("id")));
	// setMinDevDeploymentId(-1);
	// lOBDeploymentTreeDevSelector.setProdDeploymentId(getMinProdDeploymentId());
	// DPServiceAsync lOBAsync = DPService.Util.getInstance();
	// lOBAsync.getRelatedDevDeployments(lOBDeploymentTreeDevSelector, generateCallbackGetRelatedDevDeployments(getmOBGridDevDeployment()));
	// // lOBAsync.getComponents(lOBDeploymentTreeDevSelector, generateCallbackGetComponents(lOBSelectComponentName));
	// if (getmOBVLayoutDeploymentTreeEntry() != null) {
	// getmOBHLayoutMain().removeMember(getmOBVLayoutDeploymentTreeEntry());
	// getmOBVLayoutDeploymentTreeEntry().destroy();
	// }
	// setmOBVLayoutDeploymentTreeEntry(getmOBVLayoutProdTreeItemEntry());
	// getmOBHLayoutMain().addMember(getmOBVLayoutDeploymentTreeEntry());
	// }
	// });
	// }
	// return mOBGridProdDeployment;
	// }

	// public DynamicForm getmOBFormSearch() {
	// if (mOBFormSearch == null) {
	// mOBFormSearch = new DynamicForm();
	// mOBFormSearch.setFields(getmOBTextSearchBox());
	// }
	// return mOBFormSearch;
	// }

	// public TextItem getmOBTextSearchBox() {
	// if (mOBTextSearchBox == null) {
	// mOBTextSearchBox = new TextItem();
	// mOBTextSearchBox.setTitle("Search");
	// mOBTextSearchBox.setRequired(true);
	// mOBTextSearchBox.setDefaultValue("");
	//
	// mOBTextSearchBox.addKeyDownHandler(new KeyDownHandler() {
	// @Override
	// public void onKeyDown(KeyDownEvent event) {
	// if (com.smartgwt.client.util.EventHandler.getKey().equalsIgnoreCase("Enter")) {
	// if (getmOBFormSearch().validate()) {
	// getmOBGridDevDeployment().setData(new ListGridRecord[0]);
	// getmOBGridProdDeployment().setData(new ListGridRecord[0]);
	// DPServiceAsync lOBAsync = DPService.Util.getInstance();
	// String lSTAllKeys = getmOBTextSearchBox().getDisplayValue();
	// String[] lSTKeys = lSTAllKeys.split(" ");
	// lOBAsync.searchDeployments(lSTKeys, generateCallbackSearch(getmOBGridProdDeployment()));
	// }
	// }
	// }
	// });
	// }
	// return mOBTextSearchBox;
	// }

	// public IButton getmOBButtonProdDetailUpdate() {
	// if (mOBButtonProdDetailUpdate == null) {
	// mOBButtonProdDetailUpdate = new IButton();
	// mOBButtonProdDetailUpdate.setTitle("Update");
	// mOBButtonProdDetailUpdate.setLayoutAlign(Alignment.RIGHT);
	// mOBButtonProdDetailUpdate.addClickHandler(new ClickHandler() {
	// public void onClick(ClickEvent event) {
	// if (getmOBFormProdDeployDetail().validate()) {
	//
	// }
	// }
	// });
	// }
	// return mOBButtonProdDetailUpdate;
	// }

	// public IButton getmOBButtonAddDev() {
	// if (mOBButtonAddDev == null) {
	// mOBButtonAddDev = new IButton();
	// mOBButtonAddDev.setTitle("Add");
	// mOBButtonAddDev.setLayoutAlign(Alignment.RIGHT);
	//
	// mOBButtonAddDev.addClickHandler(new ClickHandler() {
	// public void onClick(ClickEvent event) {
	// if (getmOBFormNewDevDeployment().validate()) {
	// DeploymentTree lOBDeploymentTree = new DeploymentTree();
	// lOBDeploymentTree.setProdDeploymentId(getMinProdDeploymentId());
	// lOBDeploymentTree.setDevDeploymentId(-1);
	// lOBDeploymentTree.setName(getmOBTextDevName().getDisplayValue());
	// lOBDeploymentTree.setItemType(0);
	// lOBDeploymentTree.setParentId(getMinProdDeploymentId());
	//
	// DPServiceAsync lOBAsync = DPService.Util.getInstance();
	// lOBAsync.insertDeploymentTree(lOBDeploymentTree, generateCallbackInsertDeploymentTree());
	// }
	// }
	//
	// });
	// }
	// return mOBButtonAddDev;
	// }

	// public TextItem getmOBTextDevName() {
	// if (mOBTextDevName == null) {
	// mOBTextDevName = new TextItem();
	// mOBTextDevName.setTitle("<nobr>Dev Deployment Name</nobr>");
	// }
	// return mOBTextDevName;
	// }
	//
	// public DynamicForm getmOBFormNewDevDeployment() {
	// if (mOBFormNewDevDeployment == null) {
	// mOBFormNewDevDeployment = new DynamicForm();
	// mOBFormNewDevDeployment.setGroupTitle("New Development Deployment");
	// mOBFormNewDevDeployment.setIsGroup(true);
	// mOBFormNewDevDeployment.setPadding(5);
	// mOBFormNewDevDeployment.setFields(getmOBTextDevName());
	// }
	// return mOBFormNewDevDeployment;
	// }
	//
	// public DynamicForm getmOBFormProdDeployDetail() {
	// if (mOBFormProdDeployDetail == null) {
	// mOBFormProdDeployDetail = new DynamicForm();
	// mOBFormProdDeployDetail.setGroupTitle("Production Deployment Details");
	// mOBFormProdDeployDetail.setIsGroup(true);
	// mOBFormProdDeployDetail.setPadding(5);
	//
	// mOBFormProdDeployDetail.setFields(getmOBTextProdName(), getmOBCheckDeployed());
	// }
	// return mOBFormProdDeployDetail;
	// }
	//
	// public CheckboxItem getmOBCheckDeployed() {
	// if (mOBCheckDeployed == null) {
	// mOBCheckDeployed = new CheckboxItem();
	// mOBCheckDeployed.setTitle("<nobr>Deployed ? </nobr>");
	// }
	// return mOBCheckDeployed;
	// }
	//
	// public TextItem getmOBTextProdName() {
	// if (mOBTextProdName == null) {
	// mOBTextProdName = new TextItem();
	// mOBTextProdName.setTitle("<nobr>Prod Deployment Name</nobr>");
	// }
	// return mOBTextProdName;
	// }
	//
	// public TreeGrid getmOBTreeGridDeploymentTree() {
	// if (mOBTreeGridDeploymentTree == null) {
	// mOBTreeGridDeploymentTree = new TreeGrid();
	// mOBTreeGridDeploymentTree.setWidth(300);
	// mOBTreeGridDeploymentTree.setHeight(400);
	//
	// TreeGridField field = new TreeGridField("Name", "Deployment Items");
	// field.setCanSort(false);
	//
	// mOBTreeGridDeploymentTree.setFields(field);
	//
	// final Tree lOBTreeDataModel = new Tree();
	// lOBTreeDataModel.setModelType(TreeModelType.PARENT);
	// lOBTreeDataModel.setNameProperty("Name");
	// lOBTreeDataModel.setIdField("EmployeeId");
	// lOBTreeDataModel.setParentIdField("ReportsTo");
	// lOBTreeDataModel.setShowRoot(true);
	//
	// mOBTreeGridDeploymentTree.addDrawHandler(new DrawHandler() {
	// public void onDraw(DrawEvent event) {
	// lOBTreeDataModel.openAll();
	// }
	// });
	//
	// mOBTreeGridDeploymentTree.setData(lOBTreeDataModel);
	// }
	// return mOBTreeGridDeploymentTree;
	// }

	// public IButton getmOBButtonFormItemDetailAdd() {
	// if (mOBButtonFormItemDetailAdd == null) {
	// mOBButtonFormItemDetailAdd = new IButton();
	// mOBButtonFormItemDetailAdd.setTitle("Add");
	// mOBButtonFormItemDetailAdd.setLayoutAlign(Alignment.RIGHT);
	// mOBButtonFormItemDetailAdd.addClickHandler(new ClickHandler() {
	// public void onClick(ClickEvent event) {
	// if (getmOBFormItemDetail().validate()) {
	// DeploymentTree lOBComponent = new DeploymentTree();
	//
	// lOBComponent.setParentId(getMinDevDeploymentId());
	// lOBComponent.setProdDeploymentId(getMinProdDeploymentId());
	// lOBComponent.setDevDeploymentId(getMinDevDeploymentId());
	// lOBComponent.setName((String) getmOBSelectComponentName().getDisplayValue());
	// lOBComponent.setItemType(1);
	//
	// DeploymentTree lOBDeploymentType = new DeploymentTree();
	// lOBDeploymentType.setProdDeploymentId(getMinProdDeploymentId());
	// lOBDeploymentType.setDevDeploymentId(getMinDevDeploymentId());
	// lOBDeploymentType.setName((String) getmOBDeployItemType().getDisplayValue());
	// lOBDeploymentType.setItemType(2);
	//
	// DeploymentTree lOBDeploymentItemName = new DeploymentTree();
	// lOBDeploymentItemName.setName((String) getmOBDeployItemName().getDisplayValue());
	// lOBDeploymentItemName.setProdDeploymentId(getMinProdDeploymentId());
	// lOBDeploymentItemName.setDevDeploymentId(getMinDevDeploymentId());
	// lOBDeploymentItemName.setItemType(3);
	//
	// DPServiceAsync lOBAsync = DPService.Util.getInstance();
	// lOBAsync.insertDeploymentTrees(lOBComponent, lOBDeploymentType, lOBDeploymentItemName, generateCallbackInsertDeploymentTrees(getmOBTreeGridDeploymentTree(),
	// getmOBSelectComponentName()));
	//
	// }
	// }
	// });
	//
	// }
	// return mOBButtonFormItemDetailAdd;
	//
	// }

	// public DynamicForm getmOBFormItemDetail() {
	// if (mOBFormItemDetail == null) {
	// mOBFormItemDetail = new DynamicForm();
	// mOBFormItemDetail.setGroupTitle("Development Deployment Tree Entry");
	// mOBFormItemDetail.setIsGroup(true);
	// mOBFormItemDetail.setPadding(5);
	// // final SelectItem lOBDeployItemType = getMySelectDeployItemType();
	// // final SelectOtherItem lOBDeployItemName = getMySelectDeployItemName();
	// // final SelectOtherItem lOBSelectComponentName = getMySelectComponentName();
	// mOBFormItemDetail.setFields(getmOBSelectComponentName(), getmOBDeployItemType(), getmOBDeployItemName());
	// }
	// return mOBFormItemDetail;
	// }

	// public SelectOtherItem getmOBSelectComponentName() {
	// if (mOBSelectComponentName == null) {
	// mOBSelectComponentName = new SelectOtherItem();
	// mOBSelectComponentName.setTitle("<nobr>Select Component</nobr>");
	// mOBSelectComponentName.setOtherTitle("New Component");
	// mOBSelectComponentName.setOtherValue("-1");
	// mOBSelectComponentName.setRequired(true);
	// }
	// return mOBSelectComponentName;
	// }
	//
	// public SelectOtherItem getmOBDeployItemName() {
	// if (mOBDeployItemName == null) {
	// mOBDeployItemName = new SelectOtherItem();
	// mOBDeployItemName.setTitle("<nobr>Select Item</nobr>");
	// mOBDeployItemName.setOtherTitle("New Item");
	// mOBDeployItemName.setOtherValue("-1");
	// mOBDeployItemName.setRequired(true);
	// }
	// return mOBDeployItemName;
	// }

	// public SelectItem getmOBDeployItemType() {
	// if (mOBDeployItemType == null) {
	// mOBDeployItemType = new SelectItem();
	// mOBDeployItemType.setTitle("<nobr>Select Type</nobr>");
	// mOBDeployItemType.setRequired(true);
	//
	// DPServiceAsync lOBAsync = DPService.Util.getInstance();
	// lOBAsync.getDeployItemTypes(generateCallbackGetDeployItemTypes(mOBDeployItemType));
	// }
	// return mOBDeployItemType;
	// }

	// public VLayout getmOBVLayoutDeploymentTreeEntry() {
	// return mOBVLayoutDeploymentTreeEntry;
	// }
	//
	// public void setmOBVLayoutDeploymentTreeEntry(VLayout mOBVLayoutDeploymentTreeEntry) {
	// this.mOBVLayoutDeploymentTreeEntry = mOBVLayoutDeploymentTreeEntry;
	// }
	//
	// public int getMinDevDeploymentId() {
	// return minDevDeploymentId;
	// }
	//
	// public void setMinDevDeploymentId(int minDevDeploymentId) {
	// this.minDevDeploymentId = minDevDeploymentId;
	// }
	//
	// public int getMinProdDeploymentId() {
	// return minProdDeploymentId;
	// }
	//
	// public void setMinProdDeploymentId(int minProdDeploymentId) {
	// this.minProdDeploymentId = minProdDeploymentId;
	// }

	@Override
	public void onModuleLoad() {
		LoginWindow lOBLoginWindow = new LoginWindow(this);
		lOBLoginWindow.draw();
	}

	public AsyncCallback generateCallbackLogin(final LoginWindow pOBLoginWindow) {
		return new AsyncCallback() {
			public void onFailure(Throwable caught) {
				System.out.println();
				caught.printStackTrace();
			}

			public void onSuccess(Object result) {
				pOBLoginWindow.animateFade(0, getAnimationCallbackLogin(pOBLoginWindow,(User) result), 1200);
			}

		};
	}

	private AnimationCallback getAnimationCallbackLogin(final LoginWindow pOBLoginWindow,final User pOBUser) {
		return new AnimationCallback() {

			@Override
			public void execute(boolean earlyFinish) {
				pOBLoginWindow.clear();

				VLayout horizontalBars = new VLayout(4);
				horizontalBars.setWidth(300);

				final Label hBar1Label = new Label("Loading View...");
				hBar1Label.setHeight(16);

				horizontalBars.addMember(hBar1Label);

				final Canvas canvas = new Canvas();
				canvas.addChild(horizontalBars);
				canvas.draw();

				DeploymentPlanView lOBDeploymentPlanView = new DeploymentPlanView();
				DeploymentPlanController lOBDeploymentPlanController = new DeploymentPlanController(lOBDeploymentPlanView);
				lOBDeploymentPlanController.setmOBUser(pOBUser);
				lOBDeploymentPlanView.draw();
				canvas.destroy();

				// Window lOBWindow = new Window();
				// lOBWindow.setShowShadow(true);
				// lOBWindow.setShadowSoftness(10);
				// lOBWindow.setShadowOffset(5);
				// lOBWindow.setAutoSize(true);
				// lOBWindow.setTitle("Deployment Plan Management");
				// lOBWindow.setCanDragReposition(true);
				// lOBWindow.setCanDragResize(true);
				// lOBWindow.setLeft(130);
				// lOBWindow.setTop(15);
				// lOBWindow.setShowCloseButton(false);
				// lOBWindow.setShowMinimizeButton(false);
				// lOBWindow.addItem(getmOBHLayoutMain());
				//
				// lOBWindow.draw();

			}
		};
	}

	// private TextItem getMyTextItemSearchBox() {
	// TextItem lOBTextSearchBox = new TextItem();
	// lOBTextSearchBox.setTitle("Search");
	// lOBTextSearchBox.setRequired(true);
	// lOBTextSearchBox.setDefaultValue("");
	// return lOBTextSearchBox;
	// }

	// private SelectOtherItem getMySelectComponentName() {
	// SelectOtherItem lOBSelectComponentName = new SelectOtherItem();
	// lOBSelectComponentName.setTitle("<nobr>Select Component</nobr>");
	// lOBSelectComponentName.setOtherTitle("New Component");
	// lOBSelectComponentName.setOtherValue("-1");
	// lOBSelectComponentName.setRequired(true);
	// return lOBSelectComponentName;
	// }

	// private ListGrid getMyListGridDevDeployment() {
	// ListGrid lOBGridDevDeployment = new ListGrid();
	// lOBGridDevDeployment.setWidth(300);
	// lOBGridDevDeployment.setHeight(178);
	// lOBGridDevDeployment.setShowAllRecords(true);
	// lOBGridDevDeployment.setCanEdit(false);
	// lOBGridDevDeployment.setEditEvent(ListGridEditEvent.CLICK);
	// lOBGridDevDeployment.setModalEditing(true);
	// lOBGridDevDeployment.setShowGridSummary(true);
	// ListGridField lOBListGridDevDeploymentID = new ListGridField("id", "ID");
	// ListGridField lOBListGirdDevDeploymentName = new ListGridField("name", "Dev Deployment Name");
	// lOBListGirdDevDeploymentName.setShowGridSummary(true);
	// lOBListGirdDevDeploymentName.setSummaryFunction(new SummaryFunction() {
	// @Override
	// public Object getSummaryValue(Record[] records, ListGridField field) {
	// return records.length + " Deployments";
	// }
	// });
	// lOBGridDevDeployment.setFields(new ListGridField[] { lOBListGridDevDeploymentID, lOBListGirdDevDeploymentName });
	// lOBGridDevDeployment.hideField("id");
	// return lOBGridDevDeployment;
	// }

	// private ListGrid getMyListGridProdDeployment() {
	// ListGrid lOBGridProdDeployment = new ListGrid();
	// lOBGridProdDeployment.setWidth(300);
	// lOBGridProdDeployment.setHeight(178);
	// lOBGridProdDeployment.setShowAllRecords(true);
	// lOBGridProdDeployment.setCanEdit(false);
	// lOBGridProdDeployment.setEditEvent(ListGridEditEvent.CLICK);
	// lOBGridProdDeployment.setModalEditing(true);
	// lOBGridProdDeployment.setShowGridSummary(true);
	// ListGridField lOBListGridProdDeploymentID = new ListGridField("id", "ID");
	// ListGridField lOBListGirdProdDeploymentName = new ListGridField("name", "Production Deployment");
	// lOBListGirdProdDeploymentName.setShowGridSummary(true);
	// lOBListGirdProdDeploymentName.setSummaryFunction(new SummaryFunction() {
	// @Override
	// public Object getSummaryValue(Record[] records, ListGridField field) {
	// return records.length + " Deployments";
	// }
	// });
	// lOBGridProdDeployment.setFields(new ListGridField[] { lOBListGridProdDeploymentID, lOBListGirdProdDeploymentName });
	// lOBGridProdDeployment.hideField("id");
	// return lOBGridProdDeployment;
	// }

	// private TreeGrid getMyTreeGridDeploymentTree() {
	// TreeGrid lOBTreeGridDeploymentTree = new TreeGrid();
	// lOBTreeGridDeploymentTree.setWidth(300);
	// lOBTreeGridDeploymentTree.setHeight(400);
	//
	// TreeGridField field = new TreeGridField("Name", "Deployment Items");
	// field.setCanSort(false);
	//
	// lOBTreeGridDeploymentTree.setFields(field);
	//
	// final Tree lOBTreeDataModel = new Tree();
	// lOBTreeDataModel.setModelType(TreeModelType.PARENT);
	// lOBTreeDataModel.setNameProperty("Name");
	// lOBTreeDataModel.setIdField("EmployeeId");
	// lOBTreeDataModel.setParentIdField("ReportsTo");
	// lOBTreeDataModel.setShowRoot(true);
	//
	// lOBTreeGridDeploymentTree.addDrawHandler(new DrawHandler() {
	// public void onDraw(DrawEvent event) {
	// lOBTreeDataModel.openAll();
	// }
	// });
	//
	// lOBTreeGridDeploymentTree.setData(lOBTreeDataModel);
	// return lOBTreeGridDeploymentTree;
	// }

	// private SelectOtherItem getMySelectDeployItemName() {
	// SelectOtherItem lOBDeployItemName = new SelectOtherItem();
	// lOBDeployItemName.setTitle("<nobr>Select Item</nobr>");
	// lOBDeployItemName.setOtherTitle("New Item");
	// lOBDeployItemName.setOtherValue("-1");
	// lOBDeployItemName.setRequired(true);
	// return lOBDeployItemName;
	// }

	// private SelectItem getMySelectDeployItemType() {
	// SelectItem lOBDeployItemType = new SelectItem();
	// lOBDeployItemType.setTitle("<nobr>Select Type</nobr>");
	// lOBDeployItemType.setRequired(true);
	//
	// DPServiceAsync lOBAsync = DPService.Util.getInstance();
	// lOBAsync.getDeployItemTypes(generateCallbackGetDeployItemTypes(lOBDeployItemType));
	// return lOBDeployItemType;
	// }

	// private IButton getMyButtonAdd() {
	// IButton lOBButtonAdd = new IButton();
	// lOBButtonAdd.setTitle("Add");
	// lOBButtonAdd.setLayoutAlign(Alignment.RIGHT);
	// return lOBButtonAdd;
	// }

	// private IButton getMyButtonUpdate() {
	// IButton lOBButtonAdd = new IButton();
	// lOBButtonAdd.setTitle("Update");
	// lOBButtonAdd.setLayoutAlign(Alignment.RIGHT);
	// return lOBButtonAdd;
	// }

	// private VLayout getMyNewVLayoutDevTreeItemEntry(final TreeGrid lOBTreeGridDeploymentTree) {
	// VLayout lOBVLayoutDevTreeItemEntry = new VLayout(5);
	// lOBVLayoutDevTreeItemEntry.setPadding(10);
	// lOBVLayoutDevTreeItemEntry.setBackgroundColor("white");
	// lOBVLayoutDevTreeItemEntry.setBorder("1px solid #c0c0c0");
	// lOBVLayoutDevTreeItemEntry.setShowShadow(true);
	// lOBVLayoutDevTreeItemEntry.setShadowSoftness(10);
	// lOBVLayoutDevTreeItemEntry.setShadowOffset(5);
	// lOBVLayoutDevTreeItemEntry.addMember(getmOBFormItemDetail());
	// lOBVLayoutDevTreeItemEntry.addMember(getmOBButtonFormItemDetailAdd());
	// return lOBVLayoutDevTreeItemEntry;
	// }

	// private VLayout getMyNewVLayoutProdDetailEntry(final TreeGrid lOBTreeGridDeploymentTree) {
	// VLayout lOBMainVLayout = new VLayout(15);
	//
	// lOBMainVLayout.addMember(getmOBVLayoutProdTreeItemEntry());
	// lOBMainVLayout.addMember(getmOBVLayoutNewDevDeploymentEntry());
	//
	// return lOBMainVLayout;
	// }

	// private Canvas getMyPanelMain() {
	// final HLayout lOBHLayoutMain = new HLayout();
	//
	// lOBHLayoutMain.addMember(getmOBVLayoutDeployments());
	// lOBHLayoutMain.addMember(getmOBTreeGridDeploymentTree());
	//
	// return lOBHLayoutMain;
	// }

}
