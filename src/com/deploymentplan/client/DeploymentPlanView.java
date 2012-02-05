package com.deploymentplan.client;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SelectOtherItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SummaryFunction;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class DeploymentPlanView extends Window {
	private DynamicForm mOBFormDevTreeDetail = null;
	private SelectItem mOBFormDevTreeDetailSelectDeployItemType = null;
	private SelectOtherItem mOBFormDevTreeDetailSelectItemName = null;
	private SelectOtherItem mOBFormDevTreeDetailSelectComponentName = null;
	private IButton mOBFormDevTreeDetailButtonAdd = null;
	private IButton mOBFormNewDevDeploymentButtonAddDev = null;
	private IButton mOBFormProdDetailButtonUpdate = null;

	private DynamicForm mOBFormSearch = null;

	private TextItem mOBFormSearchTextSearchBox = null;

	private TreeGrid mOBTreeGridDeploymentTree = null;

	private ListGrid mOBGridProdDeployment = null;
	private ListGrid mOBGridDevDeployment = null;

	private TextItem mOBFormProdDetailTextProdName = null;
	private CheckboxItem mOBFormProdDetailCheckDeployed = null;
	private DynamicForm mOBFormProdDetail = null;
	private DynamicForm mOBFormNewDevDeployment = null;

	private DynamicForm mOBFormDevDetail = null;

	private TextItem mOBFormNewDevDeploymentTextDevName = null;

	private VLayout mOBVLayoutDeployments = null;

	private HLayout mOBHLayoutMain = null;

	private VLayout mOBVLayoutProdRPProdDetail = null;

	private VLayout mOBVLayoutNewDevEntry = null;

	private VLayout mOBVLayoutDevRPTreeDetail = null;

	private VLayout mOBVLayoutProdRP = null;

	private VLayout mOBVLayoutDevRP = null;

	private VLayout mOBVLayoutDevRPUpdateDevDetail = null;
	private CheckboxItem mOBFormDevDetailCheckDeployed = null;
	private TextItem mOBFormDevDetailTextDevName = null;
	private IButton mOBFormProdDetailButtonDelete = null;
	private HLayout mOBHLayoutProdRPProdDetailButtons = null;
	private HLayout mOBHLayoutDevRPDevDetailButtons = null;
	private IButton mOBFormDevDetailButtonUpdate;
	private IButton mOBFormDevDetailButtonDelete;
	private IButton mOBFormDevTreeDetailButtonDelete;
	private HLayout mOBHLayoutDevRPTreeDetailButtons;
	private VLayout mOBVLayoutSearch;
	private IButton mOBFormSearchButtonNewProd;
	private VLayout mOBVLayoutProdRPNewProd;
	private VLayout mOBVLayoutNewProdEntry;
	private DynamicForm mOBFormNewProdDeployment;
	private TextItem mOBFormNewProdDeploymentTextProdName;
	private IButton mOBFormNewProdDeploymentButtonAddProd;
	private HLayout mOBVLayoutGenerateKYSComment;
	private DynamicForm mOBFormGenerateKYSComment;
	private TextAreaItem mOBFormGenerateKYSCommentTextComment;
	private IButton mOBFormGenerateKYSCommentButtonGenerate;
	private TextItem mOBFormGenerateKYSCommentTextComponent;
	private VLayout mOBVLayoutGenerateProdKYSComment;
	private DynamicForm mOBFormGenerateProdKYSComment;
	private TextItem mOBFormGenerateProdKYSCommentTextComponent;
	private TextItem mOBFormGenerateProdKYSCommentTextComment;
	private IButton mOBFormGenerateProdKYSCommentButtonGenerate;
	private CheckboxItem mOBFormSearchCheckDeployed;
	private HLayout mOBHLayoutSearchButtons;
	private IButton mOBFormSearchButtonSearch;
	private VLayout mOBVLayoutTreeGrids;
	private TreeGrid mOBTreeGridOtherTree;
	private DynamicForm mOBFormDevOtherTasks;
	private SelectItem mOBFormDevOtherTasksSelectOtherTaskTypes;
	private TextAreaItem mOBFormDevOtherTasksTextAreaExplanation;
	private TextItem mOBFormDevOtherTasksTextItemShortDescription;
	private VLayout mOBVLayoutDevOtherTasks;
	private TextAreaItem mOBFormProdOtherTasksTextAreaExplanation;
	private DynamicForm mOBFormProdOtherTasks;
	private VLayout mOBVLayoutProdOtherTasks;
	private IButton mOBFormDevOtherTasksButtonAdd;
	private IButton mOBFormDevOtherTasksButtonDelete;
	private HLayout mOBHLayoutDevRPOtherTasksButtons;
	private VLayout mOBVLayoutProdRequest;
	private DynamicForm mOBFormProdRequest;
	private UploadItem mOBFormProdRequestUploadItem;
	private HLayout mOBHLayoutProdRequestButtons;
	private IButton mOBFormProdRequestButtonSubmit;
	private VLayout mOBVLayoutProdTransferOthers;
	private SelectItem mOBFormProdTransferOthersSelectProdDeployment;
	private HLayout mOBHLayoutProdTransferOthersButtons;
	private IButton mOBFormProdTransferOthersButtonTransfer;
	private DynamicForm mOBFormProdTransferOthers;

	private HLayout mOBHLayoutSummaryMain;
	private ListGrid mOBGridSummaryBusyComponents;
	private IButton mOBButtonRefreshGridSummaryBusyComponents;
	private VLayout mOBVLayoutBusyComponents;
	private TextItem mOBFormNewProdCEBFlowID;
	private TextItem mOBFormProdDetailTextCEBFlowID;
	private IButton mOBFormDevDetailButtonDeploy;
	private IButton mOBFormProdDetailButtonDeploy;
	private HLayout mOBHLayoutUsersMain;
	private ListGrid mOBGridUserList;
	private IButton mOBButtonRefreshGridUserList;
	private VLayout mOBVLayoutUserProdDeploy;
	private VLayout mOBVLayoutUserComponentKnowledge;
	private IButton mOBButtonRefreshGridUserComponentKnowledge;
	private ListGrid mOBGridUserComponentKnowledge;
	private HLayout mOBHLayoutGridsBusyComponents;
	private ListGrid mOBGridSummaryBusyComponentsProjects;
	private VLayout mOBVLayoutBulkDeploymentMain;
	private IButton mOBButtonRefreshBulkDeployment;
	private HLayout mOBHLayoutBulkDeployment;
	private ListGrid mOBGridProdBulkDeployment;
	private ListGrid mOBGridProdBulkDeploymentDependentProjects;
	private TreeGrid mOBTreeGridProdBulkDeployment;
	private VLayout mOBVLayoutWhatsNew;
	private SelectItem mOBFormProdDetailSelectStatus;
	private SelectItem mOBFormNewProdDetailSelectStatus;
//	private SelectItem mOBFormDevTreeDetailSelectMatchDeployItemType;
//	private SelectOtherItem mOBFormDevTreeDetailUsedBySelectItemName;
	private SelectItem mOBFormGenerateKYSCommentSelectType;

	public DeploymentPlanView() {
		super();
		setShowShadow(true);
		setShadowSoftness(10);
		setShadowOffset(5);
		setAutoSize(true);
		setBodyColor("#A01100");
		setTitle("Deployment Plan Management v3.1.3 - Comments..");
		setCanDragReposition(true);
		setCanDragResize(true);
		// setLeft(130);
		// setTop(15);
		// setWidth100();
		setHeight100();
		setShowCloseButton(false);
		setShowMinimizeButton(false);

		final TabSet topTabSet = new TabSet();
		topTabSet.setTabBarPosition(Side.TOP);
		// topTabSet.setWidth(1500);
		topTabSet.setWidth(1400);
		topTabSet.setHeight(800);

		final Tab lOBTabDetails = new Tab();
		lOBTabDetails.setTitle("Details");
		lOBTabDetails.setPane(getmOBHLayoutMain());

		final Tab lOBTabSummary = new Tab();
		lOBTabSummary.setTitle("Summary");
		lOBTabSummary.setPane(getmOBHLayoutSummaryMain());

		final Tab lOBTabUsers = new Tab();
		lOBTabUsers.setTitle("Hall Of Fame");
		lOBTabUsers.setPane(getmOBHLayoutUsersMain());

		final Tab lOBTabBulkDeployment = new Tab();
		lOBTabBulkDeployment.setTitle("Bulk Deployment");
		lOBTabBulkDeployment.setPane(getmOBVLayoutBulkDeploymentMain());

		final Tab lOBTabWhatsNew = new Tab();
		lOBTabWhatsNew.setTitle("What's New");
		lOBTabWhatsNew.setPane(getmOBVLayoutWhatsNew());

		topTabSet.addTab(lOBTabDetails);
		topTabSet.addTab(lOBTabSummary);
		topTabSet.addTab(lOBTabBulkDeployment);
		topTabSet.addTab(lOBTabUsers);
		topTabSet.addTab(lOBTabWhatsNew);

		addItem(topTabSet);

		// // //
		//
		// ListGridRecord[] lOBRecord = new ListGridRecord[2];
		// int i = 0;
		// lOBRecord[i] = new ListGridRecord();
		// lOBRecord[i].setAttribute("username", "Ahmet");
		// lOBRecord[i].setAttribute("title", "LieueTenant");
		// lOBRecord[i].setAttribute("rank", "3");
		// i++;
		// lOBRecord[i] = new ListGridRecord();
		// lOBRecord[i].setAttribute("username", "Mehmet");
		// lOBRecord[i].setAttribute("title", "Senior LieueTenant");
		// lOBRecord[i].setAttribute("rank", "1");
		//
		// getmOBGridUserList().setData(lOBRecord);

	}

	public VLayout getmOBVLayoutProdRP() {
		if (mOBVLayoutProdRP == null) {
			mOBVLayoutProdRP = new VLayout(2);
			mOBVLayoutProdRP.addMember(getmOBVLayoutProdRPProdDetail());
			mOBVLayoutProdRP.addMember(getmOBVLayoutNewDevEntry());
			mOBVLayoutProdRP.addMember(getmOBVLayoutGenerateProdKYSComment());
			mOBVLayoutProdRP.addMember(getmOBVLayoutProdOtherTasks());
			mOBVLayoutProdRP.addMember(getmOBVLayoutProdRequest());
			mOBVLayoutProdRP.addMember(getmOBVLayoutProdTransferOthers());

		}
		return mOBVLayoutProdRP;
	}

	public VLayout getmOBVLayoutProdRPNewProd() {
		if (mOBVLayoutProdRPNewProd == null) {
			mOBVLayoutProdRPNewProd = new VLayout(15);
			mOBVLayoutProdRPNewProd.addMember(getmOBVLayoutNewProdEntry());
		}
		return mOBVLayoutProdRPNewProd;
	}

	// RP stands for : Right Panel
	public VLayout getmOBVLayoutDevRP() {
		if (mOBVLayoutDevRP == null) {
			mOBVLayoutDevRP = new VLayout(2);
			mOBVLayoutDevRP.addMember(getmOBVLayoutDevRPTreeDetail());
			mOBVLayoutDevRP.addMember(getmOBVLayoutDevRPUpdateDevDetail());
			mOBVLayoutDevRP.addMember(getmOBVLayoutGenerateKYSComment());
			mOBVLayoutDevRP.addMember(getmOBVLayoutDevOtherTasks());
		}
		return mOBVLayoutDevRP;
	}

	public VLayout getmOBVLayoutDevRPTreeDetail() {
		if (mOBVLayoutDevRPTreeDetail == null) {
			mOBVLayoutDevRPTreeDetail = new VLayout(5);
			mOBVLayoutDevRPTreeDetail.setWidth(300);
			mOBVLayoutDevRPTreeDetail.setPadding(10);
			mOBVLayoutDevRPTreeDetail.setBackgroundColor("white");
			mOBVLayoutDevRPTreeDetail.setBorder("1px solid #c0c0c0");
			mOBVLayoutDevRPTreeDetail.setShowShadow(true);
			mOBVLayoutDevRPTreeDetail.setShadowSoftness(10);
			mOBVLayoutDevRPTreeDetail.setShadowOffset(5);
			mOBVLayoutDevRPTreeDetail.setGroupTitle("Development Deployment Tree Entry");
			mOBVLayoutDevRPTreeDetail.setIsGroup(true);
			mOBVLayoutDevRPTreeDetail.addMember(getmOBFormDevTreeDetail());
			mOBVLayoutDevRPTreeDetail.addMember(getmOBHLayoutDevRPTreeDetailButtons());

		}
		return mOBVLayoutDevRPTreeDetail;
	}

	public VLayout getmOBVLayoutDevRPUpdateDevDetail() {
		if (mOBVLayoutDevRPUpdateDevDetail == null) {
			mOBVLayoutDevRPUpdateDevDetail = new VLayout(5);
			mOBVLayoutDevRPUpdateDevDetail.setGroupTitle("Development Deployment Detail");
			mOBVLayoutDevRPUpdateDevDetail.setIsGroup(true);
			mOBVLayoutDevRPUpdateDevDetail.setWidth(250);
			mOBVLayoutDevRPUpdateDevDetail.setPadding(10);
			mOBVLayoutDevRPUpdateDevDetail.setBackgroundColor("white");
			mOBVLayoutDevRPUpdateDevDetail.setBorder("1px solid #c0c0c0");
			mOBVLayoutDevRPUpdateDevDetail.setShowShadow(true);
			mOBVLayoutDevRPUpdateDevDetail.setShadowSoftness(10);
			mOBVLayoutDevRPUpdateDevDetail.setShadowOffset(5);
			mOBVLayoutDevRPUpdateDevDetail.addMember(getmOBFormDevDetail());
			mOBVLayoutDevRPUpdateDevDetail.addMember(getmOBHLayoutDevRPDevDetailButtons());

		}
		return mOBVLayoutDevRPUpdateDevDetail;
	}

	public VLayout getmOBVLayoutNewDevEntry() {
		if (mOBVLayoutNewDevEntry == null) {
			mOBVLayoutNewDevEntry = new VLayout(5);
			mOBVLayoutNewDevEntry.setPadding(10);
			mOBVLayoutNewDevEntry.setBackgroundColor("white");
			mOBVLayoutNewDevEntry.setBorder("1px solid #c0c0c0");
			mOBVLayoutNewDevEntry.setShowShadow(true);
			mOBVLayoutNewDevEntry.setShadowSoftness(10);
			mOBVLayoutNewDevEntry.setShadowOffset(5);
			mOBVLayoutNewDevEntry.setGroupTitle("New Development Deployment");
			mOBVLayoutNewDevEntry.setIsGroup(true);
			mOBVLayoutNewDevEntry.setWidth(200);
			mOBVLayoutNewDevEntry.addMember(getmOBFormNewDevDeployment());
			mOBVLayoutNewDevEntry.addMember(getmOBFormNewDevDeploymentButtonAddDev());
		}
		return mOBVLayoutNewDevEntry;
	}

	public VLayout getmOBVLayoutNewProdEntry() {
		if (mOBVLayoutNewProdEntry == null) {
			mOBVLayoutNewProdEntry = new VLayout(5);
			mOBVLayoutNewProdEntry.setPadding(10);
			mOBVLayoutNewProdEntry.setBackgroundColor("white");
			mOBVLayoutNewProdEntry.setBorder("1px solid #c0c0c0");
			mOBVLayoutNewProdEntry.setShowShadow(true);
			mOBVLayoutNewProdEntry.setShadowSoftness(10);
			mOBVLayoutNewProdEntry.setShadowOffset(5);

			mOBVLayoutNewProdEntry.addMember(getmOBFormNewProdDeployment());
			mOBVLayoutNewProdEntry.addMember(getmOBFormNewProdDeploymentButtonAddProd());
		}
		return mOBVLayoutNewProdEntry;
	}

	public VLayout getmOBVLayoutProdRPProdDetail() {
		if (mOBVLayoutProdRPProdDetail == null) {
			mOBVLayoutProdRPProdDetail = new VLayout(5);
			mOBVLayoutProdRPProdDetail.setPadding(10);
			mOBVLayoutProdRPProdDetail.setBackgroundColor("white");
			mOBVLayoutProdRPProdDetail.setBorder("1px solid #c0c0c0");
			mOBVLayoutProdRPProdDetail.setShowShadow(true);
			mOBVLayoutProdRPProdDetail.setShadowSoftness(10);
			mOBVLayoutProdRPProdDetail.setShadowOffset(5);
			mOBVLayoutProdRPProdDetail.setGroupTitle("Production Deployment Details");
			mOBVLayoutProdRPProdDetail.setIsGroup(true);
			mOBVLayoutProdRPProdDetail.setWidth(200);
			mOBVLayoutProdRPProdDetail.addMember(getmOBFormProdDetail());
			mOBVLayoutProdRPProdDetail.addMember(getmOBHLayoutProdRPProdDetailButtons());
		}
		return mOBVLayoutProdRPProdDetail;
	}

	public HLayout getmOBHLayoutProdRPProdDetailButtons() {// TODO buttonlar sagdan siralansin
		if (mOBHLayoutProdRPProdDetailButtons == null) {
			mOBHLayoutProdRPProdDetailButtons = new HLayout();
			mOBHLayoutProdRPProdDetailButtons.setAlign(Alignment.RIGHT);
			mOBHLayoutProdRPProdDetailButtons.addMember(getmOBFormProdDetailButtonUpdate());
			mOBHLayoutProdRPProdDetailButtons.addMember(getmOBFormProdDetailButtonDelete());
			mOBHLayoutProdRPProdDetailButtons.addMember(getmOBFormProdDetailButtonDeploy());
		}
		return mOBHLayoutProdRPProdDetailButtons;
	}

	public HLayout getmOBHLayoutDevRPDevDetailButtons() {// TODO buttonlar sagdan siralansin
		if (mOBHLayoutDevRPDevDetailButtons == null) {
			mOBHLayoutDevRPDevDetailButtons = new HLayout();
			mOBHLayoutDevRPDevDetailButtons.setAlign(Alignment.RIGHT);
			mOBHLayoutDevRPDevDetailButtons.addMember(getmOBFormDevDetailButtonUpdate());
			mOBHLayoutDevRPDevDetailButtons.addMember(getmOBFormDevDetailButtonDelete());
			mOBHLayoutDevRPDevDetailButtons.addMember(getmOBFormDevDetailButtonDeploy());
		}
		return mOBHLayoutDevRPDevDetailButtons;
	}

	public HLayout getmOBHLayoutDevRPTreeDetailButtons() {// TODO buttonlar sagdan siralansin
		if (mOBHLayoutDevRPTreeDetailButtons == null) {
			mOBHLayoutDevRPTreeDetailButtons = new HLayout();
			mOBHLayoutDevRPTreeDetailButtons.setAlign(Alignment.RIGHT);
			mOBHLayoutDevRPTreeDetailButtons.addMember(getmOBFormDevTreeDetailButtonDelete());
			mOBHLayoutDevRPTreeDetailButtons.addMember(getmOBFormDevTreeDetailButtonAdd());
		}
		return mOBHLayoutDevRPTreeDetailButtons;
	}

	public HLayout getmOBHLayoutSummaryMain() {
		if (mOBHLayoutSummaryMain == null) {
			mOBHLayoutSummaryMain = new HLayout(5);
			mOBHLayoutSummaryMain.setMargin(3);
			mOBHLayoutSummaryMain.addMember(getmOBVLayoutBusyComponents());
		}
		return mOBHLayoutSummaryMain;
	}

	public HLayout getmOBHLayoutUsersMain() {
		if (mOBHLayoutUsersMain == null) {
			mOBHLayoutUsersMain = new HLayout(5);
			mOBHLayoutUsersMain.setMargin(3);
			mOBHLayoutUsersMain.addMember(getmOBVLayoutUserProdDeploy());
			mOBHLayoutUsersMain.addMember(getmOBVLayoutUserComponentKnowledge());
		}
		return mOBHLayoutUsersMain;
	}

	public VLayout getmOBVLayoutBulkDeploymentMain() {
		if (mOBVLayoutBulkDeploymentMain == null) {
			mOBVLayoutBulkDeploymentMain = new VLayout(5);
			mOBVLayoutBulkDeploymentMain.setMargin(3);
			mOBVLayoutBulkDeploymentMain.addMember(getmOBButtonRefreshBulkDeployment());
			mOBVLayoutBulkDeploymentMain.addMember(getmOBHLayoutBulkDeployment());
		}
		return mOBVLayoutBulkDeploymentMain;
	}

	public VLayout getmOBVLayoutWhatsNew() {
		if (mOBVLayoutWhatsNew == null) {
			mOBVLayoutWhatsNew = new VLayout(5);
			mOBVLayoutWhatsNew.setMargin(3);
			Label lOBLabel = new Label();
			lOBLabel.setHeight100();
			lOBLabel.setPadding(5);
			lOBLabel.setValign(VerticalAlignment.TOP);
			StringBuffer lOBBuffer = new StringBuffer();
			//lOBBuffer.append("<hr><b>v3.0.1</b><br>Deniz Hepsen, 14/09/2011<br><ol><li></li><li></li><li></li></ol><hr>");
			lOBBuffer.append("<hr><b>v3.1.3</b><br>Deniz Hepsen, 12/12/2011<br><ol><li>Added class checkin comment</li><li>Ability to generate comment even dev deployment is deployed.</li></ol><hr>");
			lOBBuffer.append("<hr><b>v3.1.2</b><br>Deniz Hepsen, 21/11/2011<br><ol><li>KYS Comment, Your project name is doubled bug is solved</li><li>Conflict marker added to deployment tree</li></ol><hr>");
			lOBBuffer.append("<hr><b>v3.1.1</b><br>Deniz Hepsen, 14/11/2011<br><ol><li>Bulk Deployment Bug solved</li><li>CMS Comment Type Added</li><li></li></ol><hr>");
			lOBBuffer.append("<hr><b>v3.1.0</b><br>Deniz Hepsen, 25/09/2011<br><ol><li>Production Status Added</li></ol><hr>");
			lOBBuffer.append("<hr><b>v3.0.1</b><br>Deniz Hepsen, 20/09/2011<br><ol><li>Generate KYS Comment For Dev Fixed</li><li>Dev Summary Project Table is Extended</li></ol><hr>");
			lOBBuffer.append("<hr><b>v3.0.0</b><br>Deniz Hepsen, 05/07/2011<br><ol><li>Smart GWT 2.4 + GWT 2.1.1</li><li>Deployed Dev Quantity Indicator on Bulk Deployment</li><li>The Prod Bulk Deploy Deployment Tree Added</li></ol><hr>");
			lOBBuffer.append("<hr><b>v2.1.4</b><br>Deniz Hepsen, 17/03/2011<br><ol><li>Versioning date comparison was done with last component entry date. Now the parameter is the last item's entry date instead of component entry.</li><li>Generating KYS Comment versioning warning and inserting item versioning warning are separated, they have different warnings.</li></ol><hr>");
			lOBBuffer.append("<hr><b>v2.1.3</b><br>Deniz Hepsen, 16/03/2011<br><ol><li>One more Get Versionning Warning Bug Fixed.</li></ol><hr>");
			lOBBuffer.append("<hr><b>v2.1.2</b><br>Deniz Hepsen, 13/03/2011<br><ol><li>Get Versionning Warning Bug Fixed.</li><li>What's New Tab Added</li></ol><hr>");
			lOBLabel.setContents(lOBBuffer.toString());
			mOBVLayoutWhatsNew.addMember(lOBLabel);

		}
		return mOBVLayoutWhatsNew;
	}

	public HLayout getmOBHLayoutBulkDeployment() {
		if (mOBHLayoutBulkDeployment == null) {
			mOBHLayoutBulkDeployment = new HLayout(5);
			mOBHLayoutBulkDeployment.setMargin(3);
			mOBHLayoutBulkDeployment.addMember(getmOBGridProdBulkDeployment());
			mOBHLayoutBulkDeployment.addMember(getmOBGridProdBulkDeploymentDependentProjects());
			mOBHLayoutBulkDeployment.addMember(getmOBTreeGridProdBulkDeployment());
		}
		return mOBHLayoutBulkDeployment;
	}

	public TreeGrid getmOBTreeGridProdBulkDeployment() {
		if (mOBTreeGridProdBulkDeployment == null) {
			mOBTreeGridProdBulkDeployment = new TreeGrid();
			mOBTreeGridProdBulkDeployment.setWidth(400);
			mOBTreeGridProdBulkDeployment.setHeight(700);
			TreeGridField field = new TreeGridField("name", "Bulk Deployment Items (Not Deployed Devs Excluded)");
			field.setCanSort(false);

			mOBTreeGridProdBulkDeployment.setFields(field);

			final Tree lOBTreeDataModel = new Tree();
			lOBTreeDataModel.setModelType(TreeModelType.PARENT);
			lOBTreeDataModel.setNameProperty("name");
			lOBTreeDataModel.setIdField("id");
			lOBTreeDataModel.setParentIdField("parentid");
			lOBTreeDataModel.setShowRoot(true);

			mOBTreeGridProdBulkDeployment.addDrawHandler(new DrawHandler() {
				public void onDraw(DrawEvent event) {
					lOBTreeDataModel.openAll();
				}
			});

			mOBTreeGridProdBulkDeployment.setData(lOBTreeDataModel);
		}
		return mOBTreeGridProdBulkDeployment;
	}

	public ListGrid getmOBGridProdBulkDeploymentDependentProjects() {
		if (mOBGridProdBulkDeploymentDependentProjects == null) {
			mOBGridProdBulkDeploymentDependentProjects = new ListGrid();
			mOBGridProdBulkDeploymentDependentProjects.setTitle("Dependent Projects");
			mOBGridProdBulkDeploymentDependentProjects.setHeight(320);
			mOBGridProdBulkDeploymentDependentProjects.setWidth(450);
			mOBGridProdBulkDeploymentDependentProjects.setShowAllRecords(true);
			mOBGridProdBulkDeploymentDependentProjects.setCanEdit(false);
			mOBGridProdBulkDeploymentDependentProjects.setEditEvent(ListGridEditEvent.CLICK);
			mOBGridProdBulkDeploymentDependentProjects.setModalEditing(true);
			mOBGridProdBulkDeploymentDependentProjects.setShowGridSummary(true);
			ListGridField lOBListGridProject = new ListGridField("project", "Project");
			lOBListGridProject.setWidth(200);
			ListGridField lOBListGridUserName = new ListGridField("username", "User");
			lOBListGridUserName.setWidth(125);
			ListGridField lOBListGridUndeployedDev = new ListGridField("undeployeddev", "Undeployed Dev Quantity");
			ListGridField lOBListGridDeployedDev = new ListGridField("deployeddev", "Deployed Dev Quantity");
			lOBListGridProject.setShowGridSummary(true);
			lOBListGridProject.setSummaryFunction(new SummaryFunction() {
				@Override
				public Object getSummaryValue(Record[] records, ListGridField field) {
					return records.length + " Projects";
				}
			});
			mOBGridProdBulkDeploymentDependentProjects.setFields(new ListGridField[] { lOBListGridProject, lOBListGridUserName, lOBListGridUndeployedDev,lOBListGridDeployedDev });

		}
		return mOBGridProdBulkDeploymentDependentProjects;
	}

	public ListGrid getmOBGridProdBulkDeployment() {
		if (mOBGridProdBulkDeployment == null) {
			mOBGridProdBulkDeployment = new ListGrid();
			// mOBGridProdDeployment.setWidth(450);
			mOBGridProdBulkDeployment.setHeight(300);
			mOBGridProdBulkDeployment.setShowAllRecords(true);
			mOBGridProdBulkDeployment.setCanEdit(false);
			mOBGridProdBulkDeployment.setEditEvent(ListGridEditEvent.CLICK);
			mOBGridProdBulkDeployment.setModalEditing(true);
			mOBGridProdBulkDeployment.setShowGridSummary(true);
			ListGridField lOBListGridProdDeploymentID = new ListGridField("id", "ID");
			ListGridField lOBListGirdProdDeploymentName = new ListGridField("name", "Production Deployment");
			ListGridField lOBListGridProdDeploymentOwner = new ListGridField("owner", "Owner");
			ListGridField lOBListGridProdDate = new ListGridField("date", "Create Date");
			ListGridField lOBListGridProdCEBFlowID = new ListGridField("cebflowid", "CEBFlow ID");
			lOBListGirdProdDeploymentName.setShowGridSummary(true);
			lOBListGirdProdDeploymentName.setSummaryFunction(new SummaryFunction() {
				@Override
				public Object getSummaryValue(Record[] records, ListGridField field) {
					return records.length + " Deployments";
				}
			});
			mOBGridProdBulkDeployment.setFields(new ListGridField[] { lOBListGridProdDeploymentID, lOBListGirdProdDeploymentName, lOBListGridProdDeploymentOwner, lOBListGridProdDate,
					lOBListGridProdCEBFlowID });
			mOBGridProdBulkDeployment.hideField("id");

		}
		return mOBGridProdBulkDeployment;
	}

	public IButton getmOBButtonRefreshBulkDeployment() {
		if (mOBButtonRefreshBulkDeployment == null) {
			mOBButtonRefreshBulkDeployment = new IButton();
			mOBButtonRefreshBulkDeployment.setWidth(300);
			mOBButtonRefreshBulkDeployment.setTitle("<nobr>Refresh Bulk Deployment List<nobr>");
			mOBButtonRefreshBulkDeployment.setLayoutAlign(Alignment.LEFT);
		}
		return mOBButtonRefreshBulkDeployment;
	}

	public VLayout getmOBVLayoutUserProdDeploy() {
		if (mOBVLayoutUserProdDeploy == null) {
			mOBVLayoutUserProdDeploy = new VLayout(5);
			mOBVLayoutUserProdDeploy.setMargin(3);
			mOBVLayoutUserProdDeploy.addMember(getmOBButtonRefreshGridUserList());
			mOBVLayoutUserProdDeploy.addMember(getmOBGridUserList());
		}
		return mOBVLayoutUserProdDeploy;
	}

	public VLayout getmOBVLayoutUserComponentKnowledge() {
		if (mOBVLayoutUserComponentKnowledge == null) {
			mOBVLayoutUserComponentKnowledge = new VLayout(5);
			mOBVLayoutUserComponentKnowledge.setMargin(3);
			mOBVLayoutUserComponentKnowledge.addMember(getmOBButtonRefreshGridUserComponentKnowledge());
			mOBVLayoutUserComponentKnowledge.addMember(getmOBGridUserComponentKnowledge());
		}
		return mOBVLayoutUserComponentKnowledge;
	}

	public ListGrid getmOBGridUserList() {
		if (mOBGridUserList == null) {
			mOBGridUserList = new ListGrid();
			mOBGridUserList.setTitle("Honour Of Production Experience");
			mOBGridUserList.setHeight(750);
			mOBGridUserList.setWidth(500);
			mOBGridUserList.setShowAllRecords(true);
			mOBGridUserList.setCanEdit(false);
			mOBGridUserList.setShowGridSummary(true);
			mOBGridUserList.setFixedRecordHeights(false);
			mOBGridUserList.setCanSort(false);
			mOBGridUserList.setShowRollOver(false);

			ListGridField lOBListGridUserName = new ListGridField("username", "Name");
			ListGridField lOBListGridUserTitle = new ListGridField("title", "Title");
			ListGridField lOBListGridUserRank = new ListGridField("rank", "Rank");
			ListGridField lOBListGridDeployedToProd = new ListGridField("count", "Deployed To Prod");
			lOBListGridUserRank.setType(ListGridFieldType.IMAGE);
			lOBListGridUserRank.setImageURLPrefix("ranks/");
			lOBListGridUserRank.setImageURLSuffix("prod.png");
			lOBListGridUserRank.setImageSize(150);
			lOBListGridUserRank.setImageWidth(70);
			lOBListGridUserRank.setAlign(Alignment.CENTER);
			mOBGridUserList.setShowGridSummary(true);
			lOBListGridUserName.setSummaryFunction(new SummaryFunction() {
				@Override
				public Object getSummaryValue(Record[] records, ListGridField field) {
					return records.length + " Users";
				}
			});
			mOBGridUserList.setFields(new ListGridField[] { lOBListGridUserRank, lOBListGridUserTitle, lOBListGridUserName, lOBListGridDeployedToProd });

		}
		return mOBGridUserList;
	}

	public ListGrid getmOBGridUserComponentKnowledge() {
		if (mOBGridUserComponentKnowledge == null) {
			mOBGridUserComponentKnowledge = new ListGrid();
			mOBGridUserComponentKnowledge.setTitle("Honour Of Data Wisdom");
			mOBGridUserComponentKnowledge.setHeight(750);
			mOBGridUserComponentKnowledge.setWidth(500);
			mOBGridUserComponentKnowledge.setShowAllRecords(true);
			mOBGridUserComponentKnowledge.setCanEdit(false);
			mOBGridUserComponentKnowledge.setShowGridSummary(true);
			mOBGridUserComponentKnowledge.setFixedRecordHeights(false);
			mOBGridUserComponentKnowledge.setCanSort(false);
			mOBGridUserComponentKnowledge.setShowRollOver(false);

			ListGridField lOBListGridUserName = new ListGridField("username", "Name");
			ListGridField lOBListGridUserTitle = new ListGridField("title", "Title");
			ListGridField lOBListGridUserRank = new ListGridField("rank", "Rank");
			ListGridField lOBListGridDeployedToProd = new ListGridField("count", "Data Knowledge");
			lOBListGridUserRank.setType(ListGridFieldType.IMAGE);
			lOBListGridUserRank.setImageURLPrefix("knowledge/");
			lOBListGridUserRank.setImageURLSuffix("know.png");
			lOBListGridUserRank.setImageSize(150);
			lOBListGridUserRank.setImageWidth(70);
			lOBListGridUserRank.setAlign(Alignment.CENTER);
			mOBGridUserComponentKnowledge.setShowGridSummary(true);
			lOBListGridUserName.setSummaryFunction(new SummaryFunction() {
				@Override
				public Object getSummaryValue(Record[] records, ListGridField field) {
					return records.length + " Users";
				}
			});
			mOBGridUserComponentKnowledge.setFields(new ListGridField[] { lOBListGridUserRank, lOBListGridUserTitle, lOBListGridUserName, lOBListGridDeployedToProd });

		}
		return mOBGridUserComponentKnowledge;
	}

	public VLayout getmOBVLayoutBusyComponents() {
		if (mOBVLayoutBusyComponents == null) {
			mOBVLayoutBusyComponents = new VLayout(5);
			mOBVLayoutBusyComponents.setMargin(3);
			mOBVLayoutBusyComponents.addMember(getmOBButtonRefreshGridSummaryBusyComponents());
			mOBVLayoutBusyComponents.addMember(getmOBHLayoutGridsBusyComponents());
		}
		return mOBVLayoutBusyComponents;
	}

	public HLayout getmOBHLayoutGridsBusyComponents() {
		if (mOBHLayoutGridsBusyComponents == null) {
			mOBHLayoutGridsBusyComponents = new HLayout(5);
			mOBHLayoutGridsBusyComponents.setMargin(3);
			mOBHLayoutGridsBusyComponents.addMember(getmOBGridSummaryBusyComponents());
			mOBHLayoutGridsBusyComponents.addMember(getmOBGridSummaryBusyComponentsProjects());
		}
		return mOBHLayoutGridsBusyComponents;
	}

	public HLayout getmOBHLayoutMain() {
		if (mOBHLayoutMain == null) {
			mOBHLayoutMain = new HLayout(5);
			mOBHLayoutMain.setMargin(3);
			mOBHLayoutMain.addMember(getmOBVLayoutDeployments());
			mOBHLayoutMain.addMember(getmOBVLayoutTreeGrids());
			// mOBHLayoutMain.setWidth100();
		}
		return mOBHLayoutMain;
	}

	public VLayout getmOBVLayoutTreeGrids() {
		if (mOBVLayoutTreeGrids == null) {
			mOBVLayoutTreeGrids = new VLayout(3);
			mOBVLayoutTreeGrids.addMember(getmOBTreeGridDeploymentTree());
			mOBVLayoutTreeGrids.addMember(getmOBTreeGridOtherTree());
			mOBVLayoutTreeGrids.setWidth(400);
			mOBVLayoutTreeGrids.setHeight100();
		}
		return mOBVLayoutTreeGrids;
	}

	public VLayout getmOBVLayoutDeployments() {
		if (mOBVLayoutDeployments == null) {
			mOBVLayoutDeployments = new VLayout(3);
			mOBVLayoutDeployments.addMember(getmOBVLayoutSearch());
			mOBVLayoutDeployments.addMember(getmOBGridProdDeployment());
			mOBVLayoutDeployments.addMember(getmOBGridDevDeployment());
			mOBVLayoutDeployments.setWidth(500);
			mOBVLayoutDeployments.setHeight100();
		}
		return mOBVLayoutDeployments;
	}

	public ListGrid getmOBGridDevDeployment() {
		if (mOBGridDevDeployment == null) {
			mOBGridDevDeployment = new ListGrid() {
				protected String getBaseStyle(ListGridRecord record, int rowNum, int colNum) {
					if (record.getAttribute("deployed").equalsIgnoreCase("0")) {
						return "highlightgrid";
					} else {
						return super.getBaseStyle(record, rowNum, colNum);
					}

					// if (getFieldName(colNum).equals("deployed")) {
					// if (record.getAttribute("deployed").equalsIgnoreCase("0")) {
					// return "highlightgrid";
					// } else {
					// return super.getBaseStyle(record, rowNum, colNum);
					// }
					// } else {
					// return super.getBaseStyle(record, rowNum, colNum);
					// }

				}
			};
			// mOBGridDevDeployment.setWidth(450);
			mOBGridDevDeployment.setHeight(320);
			mOBGridDevDeployment.setShowAllRecords(true);
			mOBGridDevDeployment.setCanEdit(false);
			mOBGridDevDeployment.setEditEvent(ListGridEditEvent.CLICK);
			mOBGridDevDeployment.setModalEditing(true);
			mOBGridDevDeployment.setShowGridSummary(true);
			ListGridField lOBListGridDevDeploymentID = new ListGridField("id", "ID");
			ListGridField lOBListGirdDevDeploymentName = new ListGridField("name", "Dev Deployment Name");
			ListGridField lOBListGridDevDeploymentOwner = new ListGridField("owner", "Owner");
			ListGridField lOBListGridDevIsDeployed = new ListGridField("deployed", "Deployed");
			ListGridField lOBListGridDevDate = new ListGridField("date", "Create Date");
			ListGridField lOBListGridDeployDate = new ListGridField("deploydate", "Deploy Date");
			lOBListGirdDevDeploymentName.setShowGridSummary(true);
			lOBListGirdDevDeploymentName.setSummaryFunction(new SummaryFunction() {
				@Override
				public Object getSummaryValue(Record[] records, ListGridField field) {
					return records.length + " Deployments";
				}
			});
			mOBGridDevDeployment.setFields(new ListGridField[] { lOBListGridDevDeploymentID, lOBListGirdDevDeploymentName, lOBListGridDevDeploymentOwner, lOBListGridDevIsDeployed, lOBListGridDevDate,
					lOBListGridDeployDate });
			mOBGridDevDeployment.hideField("id");

		}
		return mOBGridDevDeployment;
	}

	public ListGrid getmOBGridProdDeployment() {
		if (mOBGridProdDeployment == null) {
			mOBGridProdDeployment = new ListGrid();
			// mOBGridProdDeployment.setWidth(450);
			mOBGridProdDeployment.setHeight(300);
			mOBGridProdDeployment.setShowAllRecords(true);
			mOBGridProdDeployment.setCanEdit(false);
			mOBGridProdDeployment.setEditEvent(ListGridEditEvent.CLICK);
			mOBGridProdDeployment.setModalEditing(true);
			mOBGridProdDeployment.setShowGridSummary(true);
			ListGridField lOBListGridProdDeploymentID = new ListGridField("id", "ID");
			ListGridField lOBListGirdProdDeploymentName = new ListGridField("name", "Production Deployment");
			ListGridField lOBListGridProdDeploymentOwner = new ListGridField("owner", "Owner",100);
			ListGridField lOBListGridProdIsDeployed = new ListGridField("deployed", "Deployed");
			ListGridField lOBListGridProdDate = new ListGridField("date", "Create Date",70);
			ListGridField lOBListGridProdCEBFlowID = new ListGridField("cebflowid", "CEBFlow ID",40);
			ListGridField lOBListGridDeployDate = new ListGridField("deploydate", "Deploy Date",70);
			ListGridField lOBListGridProdStatusID = new ListGridField("prodstatusid", "Status");
			ListGridField lOBListGridProdStatusName = new ListGridField("prodstatusname", "Status",50);
			lOBListGirdProdDeploymentName.setShowGridSummary(true);
			lOBListGirdProdDeploymentName.setSummaryFunction(new SummaryFunction() {
				@Override
				public Object getSummaryValue(Record[] records, ListGridField field) {
					return records.length + " Deployments";
				}
			});
			mOBGridProdDeployment.setFields(new ListGridField[] { lOBListGridProdDeploymentID, lOBListGridProdCEBFlowID,lOBListGirdProdDeploymentName, lOBListGridProdDeploymentOwner, lOBListGridProdStatusName,lOBListGridProdDate,
					 lOBListGridDeployDate,lOBListGridProdStatusID });
			mOBGridProdDeployment.hideField("id");
			mOBGridProdDeployment.hideField("prodstatusid");

		}
		return mOBGridProdDeployment;
	}

	public DynamicForm getmOBFormSearch() {
		if (mOBFormSearch == null) {
			mOBFormSearch = new DynamicForm();
			mOBFormSearch.setPadding(5);
			mOBFormSearch.setFields(getmOBFormSearchTextSearchBox(), getmOBFormSearchCheckDeployed());
		}
		return mOBFormSearch;
	}

	public IButton getmOBButtonRefreshGridUserList() {
		if (mOBButtonRefreshGridUserList == null) {
			mOBButtonRefreshGridUserList = new IButton();
			mOBButtonRefreshGridUserList.setWidth(300);
			mOBButtonRefreshGridUserList.setTitle("<nobr>Refresh Honour Of Production Experience<nobr>");
			mOBButtonRefreshGridUserList.setLayoutAlign(Alignment.LEFT);
		}
		return mOBButtonRefreshGridUserList;
	}

	public IButton getmOBButtonRefreshGridUserComponentKnowledge() {
		if (mOBButtonRefreshGridUserComponentKnowledge == null) {
			mOBButtonRefreshGridUserComponentKnowledge = new IButton();
			mOBButtonRefreshGridUserComponentKnowledge.setWidth(200);
			mOBButtonRefreshGridUserComponentKnowledge.setTitle("<nobr>Refresh Honour Of Data Wisdom</nobr>");
			mOBButtonRefreshGridUserComponentKnowledge.setLayoutAlign(Alignment.LEFT);
		}
		return mOBButtonRefreshGridUserComponentKnowledge;
	}

	public IButton getmOBButtonRefreshGridSummaryBusyComponents() {
		if (mOBButtonRefreshGridSummaryBusyComponents == null) {
			mOBButtonRefreshGridSummaryBusyComponents = new IButton();
			mOBButtonRefreshGridSummaryBusyComponents.setWidth(120);
			mOBButtonRefreshGridSummaryBusyComponents.setTitle("Refresh Summary");
			mOBButtonRefreshGridSummaryBusyComponents.setLayoutAlign(Alignment.LEFT);
		}
		return mOBButtonRefreshGridSummaryBusyComponents;
	}

	public ListGrid getmOBGridSummaryBusyComponents() {
		if (mOBGridSummaryBusyComponents == null) {
			mOBGridSummaryBusyComponents = new ListGrid();
			mOBGridSummaryBusyComponents.setTitle("Busy Components");
			mOBGridSummaryBusyComponents.setHeight(320);
			mOBGridSummaryBusyComponents.setWidth(280);
			mOBGridSummaryBusyComponents.setShowAllRecords(true);
			mOBGridSummaryBusyComponents.setCanEdit(false);
			mOBGridSummaryBusyComponents.setEditEvent(ListGridEditEvent.CLICK);
			mOBGridSummaryBusyComponents.setModalEditing(true);
			mOBGridSummaryBusyComponents.setShowGridSummary(true);
			ListGridField lOBListGridComponentName = new ListGridField("component", "Component Name");//ofprojects
			ListGridField lOBListGridNumberOfProjects = new ListGridField("number", "NumberOfProjects");
			lOBListGridComponentName.setShowGridSummary(true);
			lOBListGridComponentName.setSummaryFunction(new SummaryFunction() {
				@Override
				public Object getSummaryValue(Record[] records, ListGridField field) {
					return records.length + " Components";
				}
			});
			mOBGridSummaryBusyComponents.setFields(new ListGridField[] {lOBListGridComponentName, lOBListGridNumberOfProjects});

		}
		return mOBGridSummaryBusyComponents;
	}

	public ListGrid getmOBGridSummaryBusyComponentsProjects() {
		if (mOBGridSummaryBusyComponentsProjects == null) {
			mOBGridSummaryBusyComponentsProjects = new ListGrid();
			mOBGridSummaryBusyComponentsProjects.setTitle("Undeployed Projects");
			mOBGridSummaryBusyComponentsProjects.setHeight(320);
			mOBGridSummaryBusyComponentsProjects.setWidth(150);
			mOBGridSummaryBusyComponentsProjects.setAutoFitData(Autofit.HORIZONTAL);
			mOBGridSummaryBusyComponentsProjects.setShowAllRecords(true);
			mOBGridSummaryBusyComponentsProjects.setCanEdit(false);
			mOBGridSummaryBusyComponentsProjects.setEditEvent(ListGridEditEvent.CLICK);
			mOBGridSummaryBusyComponentsProjects.setModalEditing(true);
			mOBGridSummaryBusyComponentsProjects.setShowGridSummary(true);
			ListGridField lOBListGridProject = new ListGridField("project", "Project");
//			lOBListGridProject.setWidth(300);
			lOBListGridProject.setAutoFitWidth(true);
			ListGridField lOBListGridUserName = new ListGridField("username", "User");
//			lOBListGridUserName.setWidth(125);
			lOBListGridUserName.setAutoFitWidth(true);
			ListGridField lOBListGridCompDeployedDev = new ListGridField("compdeployed", "Comp+");
			lOBListGridCompDeployedDev.setWidth(50);
			ListGridField lOBListGridCompUndeployedDev = new ListGridField("compnotdeployed", "Comp-");
			lOBListGridCompUndeployedDev.setWidth(50);			
			ListGridField lOBListGridDeployedDev = new ListGridField("deployeddev", "All+");
			lOBListGridDeployedDev.setWidth(50);
			ListGridField lOBListGridUndeployedDev = new ListGridField("undeployeddev", "All-");
			lOBListGridUndeployedDev.setWidth(50);
			lOBListGridProject.setSummaryFunction(new SummaryFunction() {
				@Override
				public Object getSummaryValue(Record[] records, ListGridField field) {
					return records.length + " Projects";
				}
			});
			mOBGridSummaryBusyComponentsProjects.setFields(new ListGridField[] { lOBListGridProject, lOBListGridUserName, lOBListGridCompDeployedDev,lOBListGridCompUndeployedDev,lOBListGridDeployedDev,lOBListGridUndeployedDev });

		}
		return mOBGridSummaryBusyComponentsProjects;
	}

	public VLayout getmOBVLayoutSearch() {
		if (mOBVLayoutSearch == null) {
			mOBVLayoutSearch = new VLayout(5);
			mOBVLayoutSearch.setLayoutAlign(Alignment.CENTER);
			mOBVLayoutSearch.setIsGroup(true);
			mOBVLayoutSearch.setGroupTitle("Production Search & New");
			mOBVLayoutSearch.addMember(getmOBFormSearch());
			mOBVLayoutSearch.addMember(getmOBHLayoutSearchButtons());
		}
		return mOBVLayoutSearch;
	}

	public HLayout getmOBHLayoutSearchButtons() {
		if (mOBHLayoutSearchButtons == null) {
			mOBHLayoutSearchButtons = new HLayout();
			mOBHLayoutSearchButtons.setAlign(Alignment.RIGHT);
			mOBHLayoutSearchButtons.addMember(getmOBFormSearchButtonSearch());
			mOBHLayoutSearchButtons.addMember(getmOBFormSearchButtonNewProd());
		}
		return mOBHLayoutSearchButtons;
	}

	public CheckboxItem getmOBFormSearchCheckDeployed() {
		if (mOBFormSearchCheckDeployed == null) {
			mOBFormSearchCheckDeployed = new CheckboxItem();
			mOBFormSearchCheckDeployed.setTitle("<nobr>Deployed ? </nobr>");
			mOBFormSearchCheckDeployed.setDefaultValue(false);
		}
		return mOBFormSearchCheckDeployed;
	}

	public IButton getmOBFormSearchButtonSearch() {
		if (mOBFormSearchButtonSearch == null) {
			mOBFormSearchButtonSearch = new IButton();
			mOBFormSearchButtonSearch.setWidth(120);
			mOBFormSearchButtonSearch.setTitle("Search");
			mOBFormSearchButtonSearch.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormSearchButtonSearch;
	}

	public IButton getmOBFormSearchButtonNewProd() {
		if (mOBFormSearchButtonNewProd == null) {
			mOBFormSearchButtonNewProd = new IButton();
			mOBFormSearchButtonNewProd.setWidth(120);
			mOBFormSearchButtonNewProd.setTitle("New Prod");
			mOBFormSearchButtonNewProd.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormSearchButtonNewProd;
	}

	public TextItem getmOBFormSearchTextSearchBox() {
		if (mOBFormSearchTextSearchBox == null) {
			mOBFormSearchTextSearchBox = new TextItem();
			mOBFormSearchTextSearchBox.setTitle("Search");
			mOBFormSearchTextSearchBox.setDefaultValue("");

		}
		return mOBFormSearchTextSearchBox;
	}

	public IButton getmOBFormProdDetailButtonUpdate() {
		if (mOBFormProdDetailButtonUpdate == null) {
			mOBFormProdDetailButtonUpdate = new IButton();
			mOBFormProdDetailButtonUpdate.setTitle("Update");
			mOBFormProdDetailButtonUpdate.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormProdDetailButtonUpdate;
	}

	public IButton getmOBFormProdDetailButtonDeploy() {
		if (mOBFormProdDetailButtonDeploy == null) {
			mOBFormProdDetailButtonDeploy = new IButton();
			mOBFormProdDetailButtonDeploy.setTitle("Deployment");
			mOBFormProdDetailButtonDeploy.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormProdDetailButtonDeploy;
	}

	public IButton getmOBFormDevDetailButtonUpdate() {
		if (mOBFormDevDetailButtonUpdate == null) {
			mOBFormDevDetailButtonUpdate = new IButton();
			mOBFormDevDetailButtonUpdate.setTitle("Update");
			mOBFormDevDetailButtonUpdate.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormDevDetailButtonUpdate;
	}

	public IButton getmOBFormProdDetailButtonDelete() {
		if (mOBFormProdDetailButtonDelete == null) {
			mOBFormProdDetailButtonDelete = new IButton();
			mOBFormProdDetailButtonDelete.setTitle("Delete");
			mOBFormProdDetailButtonDelete.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormProdDetailButtonDelete;
	}

	public IButton getmOBFormDevDetailButtonDelete() {
		if (mOBFormDevDetailButtonDelete == null) {
			mOBFormDevDetailButtonDelete = new IButton();
			mOBFormDevDetailButtonDelete.setTitle("Delete");
			mOBFormDevDetailButtonDelete.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormDevDetailButtonDelete;
	}

	public IButton getmOBFormDevDetailButtonDeploy() {
		if (mOBFormDevDetailButtonDeploy == null) {
			mOBFormDevDetailButtonDeploy = new IButton();
			mOBFormDevDetailButtonDeploy.setTitle("Deployment");
			mOBFormDevDetailButtonDeploy.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormDevDetailButtonDeploy;
	}

	public IButton getmOBFormNewDevDeploymentButtonAddDev() {
		if (mOBFormNewDevDeploymentButtonAddDev == null) {
			mOBFormNewDevDeploymentButtonAddDev = new IButton();
			mOBFormNewDevDeploymentButtonAddDev.setTitle("Add");
			mOBFormNewDevDeploymentButtonAddDev.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormNewDevDeploymentButtonAddDev;
	}

	public IButton getmOBFormNewProdDeploymentButtonAddProd() {
		if (mOBFormNewProdDeploymentButtonAddProd == null) {
			mOBFormNewProdDeploymentButtonAddProd = new IButton();
			mOBFormNewProdDeploymentButtonAddProd.setTitle("Add");
			mOBFormNewProdDeploymentButtonAddProd.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormNewProdDeploymentButtonAddProd;
	}

	public TextItem getmOBFormNewDevDeploymentTextDevName() {
		if (mOBFormNewDevDeploymentTextDevName == null) {
			mOBFormNewDevDeploymentTextDevName = new TextItem();
			mOBFormNewDevDeploymentTextDevName.setTitle("<nobr>Dev Deployment Name</nobr>");
		}
		return mOBFormNewDevDeploymentTextDevName;
	}

	public TextItem getmOBFormNewProdDeploymentTextProdName() {
		if (mOBFormNewProdDeploymentTextProdName == null) {
			mOBFormNewProdDeploymentTextProdName = new TextItem();
			mOBFormNewProdDeploymentTextProdName.setRequired(true);
			mOBFormNewProdDeploymentTextProdName.setTitle("<nobr>Prod Deployment Name</nobr>");
		}
		return mOBFormNewProdDeploymentTextProdName;
	}

	public TextItem getmOBFormNewProdCEBFlowID() {
		if (mOBFormNewProdCEBFlowID == null) {
			mOBFormNewProdCEBFlowID = new TextItem();
			mOBFormNewProdCEBFlowID.setTitle("<nobr>CEBFlow ID</nobr>");
			mOBFormNewProdCEBFlowID.setRequired(true);
		}
		return mOBFormNewProdCEBFlowID;
	}

	public DynamicForm getmOBFormNewDevDeployment() {
		if (mOBFormNewDevDeployment == null) {
			mOBFormNewDevDeployment = new DynamicForm();
			mOBFormNewDevDeployment.setPadding(5);
			mOBFormNewDevDeployment.setFields(getmOBFormNewDevDeploymentTextDevName());
		}
		return mOBFormNewDevDeployment;
	}

	public DynamicForm getmOBFormNewProdDeployment() {
		if (mOBFormNewProdDeployment == null) {
			mOBFormNewProdDeployment = new DynamicForm();
			mOBFormNewProdDeployment.setGroupTitle("New Production Deployment");
			mOBFormNewProdDeployment.setIsGroup(true);
			mOBFormNewProdDeployment.setPadding(5);
			mOBFormNewProdDeployment.setFields(getmOBFormNewProdDeploymentTextProdName(), getmOBFormNewProdCEBFlowID(),getmOBFormNewProdDetailSelectStatus());
		}
		return mOBFormNewProdDeployment;
	}
	
	public SelectItem getmOBFormNewProdDetailSelectStatus() {
		if (mOBFormNewProdDetailSelectStatus == null) {
			mOBFormNewProdDetailSelectStatus = new SelectItem();
			mOBFormNewProdDetailSelectStatus.setTitle("<nobr>Status</nobr>");
			mOBFormNewProdDetailSelectStatus.setRequired(true);
			mOBFormNewProdDetailSelectStatus.setDefaultValue(String.valueOf(1));
		}
		return mOBFormNewProdDetailSelectStatus;
	}

	public DynamicForm getmOBFormDevDetail() {
		if (mOBFormDevDetail == null) {
			mOBFormDevDetail = new DynamicForm();
			mOBFormDevDetail.setPadding(5);
			mOBFormDevDetail.setFields(getmOBFormDevDetailTextDevName(), getmOBFormDevDetailCheckDeployed());
		}
		return mOBFormDevDetail;
	}

	public DynamicForm getmOBFormProdDetail() {
		if (mOBFormProdDetail == null) {
			mOBFormProdDetail = new DynamicForm();
			mOBFormProdDetail.setPadding(5);
			mOBFormProdDetail.setFields(getmOBFormProdDetailTextProdName(), getmOBFormProdDetailTextCEBFlowID(),getmOBFormProdDetailSelectStatus(), getmOBFormProdDetailCheckDeployed());
		}
		return mOBFormProdDetail;
	}
	
	public SelectItem getmOBFormProdDetailSelectStatus() {
		if (mOBFormProdDetailSelectStatus == null) {
			mOBFormProdDetailSelectStatus = new SelectItem();
			mOBFormProdDetailSelectStatus.setTitle("<nobr>Status</nobr>");
			mOBFormProdDetailSelectStatus.setRequired(true);
		}
		return mOBFormProdDetailSelectStatus;
	}

	public CheckboxItem getmOBFormProdDetailCheckDeployed() {
		if (mOBFormProdDetailCheckDeployed == null) {
			mOBFormProdDetailCheckDeployed = new CheckboxItem();
			mOBFormProdDetailCheckDeployed.setTitle("<nobr>Deployed ? </nobr>");
			mOBFormProdDetailCheckDeployed.setDefaultValue(false);
		}
		return mOBFormProdDetailCheckDeployed;
	}

	public TextItem getmOBFormProdDetailTextProdName() {
		if (mOBFormProdDetailTextProdName == null) {
			mOBFormProdDetailTextProdName = new TextItem();
			mOBFormProdDetailTextProdName.setTitle("<nobr>Prod Deployment Name</nobr>");
		}
		return mOBFormProdDetailTextProdName;
	}

	public TextItem getmOBFormProdDetailTextCEBFlowID() {
		if (mOBFormProdDetailTextCEBFlowID == null) {
			mOBFormProdDetailTextCEBFlowID = new TextItem();
			mOBFormProdDetailTextCEBFlowID.setRequired(true);
			mOBFormProdDetailTextCEBFlowID.setTitle("<nobr>CEBFlow ID</nobr>");
		}
		return mOBFormProdDetailTextCEBFlowID;
	}

	public CheckboxItem getmOBFormDevDetailCheckDeployed() {
		if (mOBFormDevDetailCheckDeployed == null) {
			mOBFormDevDetailCheckDeployed = new CheckboxItem();
			mOBFormDevDetailCheckDeployed.setTitle("<nobr>Deployed ? </nobr>");
			mOBFormDevDetailCheckDeployed.setDefaultValue(false);
		}
		return mOBFormDevDetailCheckDeployed;
	}

	public TextItem getmOBFormDevDetailTextDevName() {
		if (mOBFormDevDetailTextDevName == null) {
			mOBFormDevDetailTextDevName = new TextItem();
			mOBFormDevDetailTextDevName.setTitle("<nobr>Dev Deployment Name</nobr>");
		}
		return mOBFormDevDetailTextDevName;
	}

	public TreeGrid getmOBTreeGridDeploymentTree() {
		if (mOBTreeGridDeploymentTree == null) {
			mOBTreeGridDeploymentTree = new TreeGrid() {
							
				protected String getBaseStyle(ListGridRecord record, int rowNum, int colNum) {
					if(record != null) {
						Integer lOBConflict = Integer.parseInt(record.getAttribute("conflict"));
						if (lOBConflict.intValue() > 0 ) {
							return "conflictgrid";
						} else {
							return super.getBaseStyle(record, rowNum, colNum);
						}

						// if (getFieldName(colNum).equals("deployed")) {
						// if (record.getAttribute("deployed").equalsIgnoreCase("0")) {
						// return "highlightgrid";
						// } else {
						// return super.getBaseStyle(record, rowNum, colNum);
						// }
						// } else {
						// return super.getBaseStyle(record, rowNum, colNum);
						// }
					
					} else {
						return super.getBaseStyle(record, rowNum, colNum);
					}

				}
				
			};
			mOBTreeGridDeploymentTree.setWidth(400);
			mOBTreeGridDeploymentTree.setHeight(325);
			TreeGridField field = new TreeGridField("Name", "Deployment Items");
			field.setCanSort(false);

			mOBTreeGridDeploymentTree.setFields(field);

			final Tree lOBTreeDataModel = new Tree();
			lOBTreeDataModel.setModelType(TreeModelType.PARENT);
			lOBTreeDataModel.setNameProperty("Name");
			lOBTreeDataModel.setIdField("EmployeeId");
			lOBTreeDataModel.setParentIdField("ReportsTo");
			lOBTreeDataModel.setShowRoot(true);

			mOBTreeGridDeploymentTree.addDrawHandler(new DrawHandler() {
				public void onDraw(DrawEvent event) {
					lOBTreeDataModel.openAll();
				}
			});

			mOBTreeGridDeploymentTree.setData(lOBTreeDataModel);
		}
		return mOBTreeGridDeploymentTree;
	}

	public TreeGrid getmOBTreeGridOtherTree() {
		if (mOBTreeGridOtherTree == null) {
			mOBTreeGridOtherTree = new TreeGrid();
			mOBTreeGridOtherTree.setHeight("400");
			mOBTreeGridOtherTree.setWidth(400);
			// mOBTreeGridOtherTree.setHeight(250);
			TreeGridField field = new TreeGridField("Name", "Other Tasks");
			field.setCanSort(false);

			mOBTreeGridOtherTree.setFields(field);

			final Tree lOBTreeDataModel = new Tree();
			lOBTreeDataModel.setModelType(TreeModelType.PARENT);
			lOBTreeDataModel.setNameProperty("Name");
			lOBTreeDataModel.setIdField("EmployeeId");
			lOBTreeDataModel.setParentIdField("ReportsTo");
			lOBTreeDataModel.setShowRoot(true);

			mOBTreeGridOtherTree.addDrawHandler(new DrawHandler() {
				public void onDraw(DrawEvent event) {
					lOBTreeDataModel.openAll();
				}
			});

			mOBTreeGridOtherTree.setData(lOBTreeDataModel);
		}
		return mOBTreeGridOtherTree;
	}

	public IButton getmOBFormDevTreeDetailButtonAdd() {
		if (mOBFormDevTreeDetailButtonAdd == null) {
			mOBFormDevTreeDetailButtonAdd = new IButton();
			mOBFormDevTreeDetailButtonAdd.setTitle("Add");
			mOBFormDevTreeDetailButtonAdd.setLayoutAlign(Alignment.RIGHT);

		}
		return mOBFormDevTreeDetailButtonAdd;

	}

	public IButton getmOBFormDevTreeDetailButtonDelete() {
		if (mOBFormDevTreeDetailButtonDelete == null) {
			mOBFormDevTreeDetailButtonDelete = new IButton();
			mOBFormDevTreeDetailButtonDelete.setTitle("Delete");
			mOBFormDevTreeDetailButtonDelete.setLayoutAlign(Alignment.RIGHT);

		}
		return mOBFormDevTreeDetailButtonDelete;

	}

	public DynamicForm getmOBFormDevTreeDetail() {
		if (mOBFormDevTreeDetail == null) {
			mOBFormDevTreeDetail = new DynamicForm();
			mOBFormDevTreeDetail.setPadding(5);
			mOBFormDevTreeDetail.setFields(getmOBFormDevTreeDetailSelectComponentName(), getmOBFormDevTreeDetailSelectDeployItemType(), getmOBFormDevTreeDetailSelectItemName());
		}
		return mOBFormDevTreeDetail;
	}

	public SelectOtherItem getmOBFormDevTreeDetailSelectComponentName() {
		if (mOBFormDevTreeDetailSelectComponentName == null) {
			mOBFormDevTreeDetailSelectComponentName = new SelectOtherItem();
			mOBFormDevTreeDetailSelectComponentName.setTitle("<nobr>Select Component</nobr>");
			mOBFormDevTreeDetailSelectComponentName.setOtherTitle("New Component");
			mOBFormDevTreeDetailSelectComponentName.setOtherValue("-1");
			mOBFormDevTreeDetailSelectComponentName.setRequired(true);
			mOBFormDevTreeDetailSelectComponentName.setWidth(250);
		}
		return mOBFormDevTreeDetailSelectComponentName;
	}

	public SelectOtherItem getmOBFormDevTreeDetailSelectItemName() {
		if (mOBFormDevTreeDetailSelectItemName == null) {
			mOBFormDevTreeDetailSelectItemName = new SelectOtherItem();
			mOBFormDevTreeDetailSelectItemName.setTitle("<nobr>Select Item</nobr>");
			mOBFormDevTreeDetailSelectItemName.setOtherTitle("New Item");
			mOBFormDevTreeDetailSelectItemName.setOtherValue("-1");
			mOBFormDevTreeDetailSelectItemName.setRequired(true);
			mOBFormDevTreeDetailSelectItemName.setWidth(250);
		}
		return mOBFormDevTreeDetailSelectItemName;
	}
	
//	public SelectOtherItem getmOBFormDevTreeDetailSelectUsedByItemName() {
//		if (mOBFormDevTreeDetailUsedBySelectItemName == null) {
//			mOBFormDevTreeDetailUsedBySelectItemName = new SelectOtherItem();
//			mOBFormDevTreeDetailUsedBySelectItemName.setTitle("<nobr>Used By Item</nobr>");
//			mOBFormDevTreeDetailUsedBySelectItemName.setOtherTitle("New Item");
//			mOBFormDevTreeDetailUsedBySelectItemName.setOtherValue("-1");
//			mOBFormDevTreeDetailUsedBySelectItemName.setRequired(true);
//			mOBFormDevTreeDetailUsedBySelectItemName.setWidth(250);
//		}
//		return mOBFormDevTreeDetailUsedBySelectItemName;
//	}

	public SelectItem getmOBFormDevTreeDetailSelectDeployItemType() {
		if (mOBFormDevTreeDetailSelectDeployItemType == null) {
			mOBFormDevTreeDetailSelectDeployItemType = new SelectItem();
			mOBFormDevTreeDetailSelectDeployItemType.setTitle("<nobr>Select Type</nobr>");
			mOBFormDevTreeDetailSelectDeployItemType.setRequired(true);
		}
		return mOBFormDevTreeDetailSelectDeployItemType;
	}
	
//	public SelectItem getmOBFormDevTreeDetailSelectUsedByDeployItemType() {
//		if (mOBFormDevTreeDetailSelectMatchDeployItemType == null) {
//			mOBFormDevTreeDetailSelectMatchDeployItemType = new SelectItem();
//			mOBFormDevTreeDetailSelectMatchDeployItemType.setTitle("<nobr>Used By</nobr>");
//			mOBFormDevTreeDetailSelectMatchDeployItemType.setRequired(true);
//		}
//		return mOBFormDevTreeDetailSelectMatchDeployItemType;
//	}

	public VLayout getmOBVLayoutGenerateProdKYSComment() {
		if (mOBVLayoutGenerateProdKYSComment == null) {
			mOBVLayoutGenerateProdKYSComment = new VLayout(5);
			mOBVLayoutGenerateProdKYSComment.setPadding(10);
			mOBVLayoutGenerateProdKYSComment.setBackgroundColor("white");
			mOBVLayoutGenerateProdKYSComment.setBorder("1px solid #c0c0c0");
			mOBVLayoutGenerateProdKYSComment.setShowShadow(true);
			mOBVLayoutGenerateProdKYSComment.setShadowSoftness(10);
			mOBVLayoutGenerateProdKYSComment.setShadowOffset(5);
			mOBVLayoutGenerateProdKYSComment.setGroupTitle("Generate KYS Comment");
			mOBVLayoutGenerateProdKYSComment.setIsGroup(true);
			mOBVLayoutGenerateProdKYSComment.setWidth(200);
			mOBVLayoutGenerateProdKYSComment.addMember(getmOBFormGenerateProdKYSComment());
			mOBVLayoutGenerateProdKYSComment.addMember(getmOBFormGenerateProdKYSCommentButtonGenerate());

		}
		return mOBVLayoutGenerateProdKYSComment;
	}

	public DynamicForm getmOBFormGenerateProdKYSComment() {
		if (mOBFormGenerateProdKYSComment == null) {
			mOBFormGenerateProdKYSComment = new DynamicForm();
			mOBFormGenerateProdKYSComment.setPadding(5);
			mOBFormGenerateProdKYSComment.setFields(getmOBFormGenerateProdKYSCommentTextComponent(), getmOBFormGenerateProdKYSCommentTextComment());
		}
		return mOBFormGenerateProdKYSComment;
	}

	public TextItem getmOBFormGenerateProdKYSCommentTextComponent() {
		if (mOBFormGenerateProdKYSCommentTextComponent == null) {
			mOBFormGenerateProdKYSCommentTextComponent = new TextItem();
			mOBFormGenerateProdKYSCommentTextComponent.setTitle("<nobr>Component</nobr>");
		}
		return mOBFormGenerateProdKYSCommentTextComponent;
	}

	public TextItem getmOBFormGenerateProdKYSCommentTextComment() {
		if (mOBFormGenerateProdKYSCommentTextComment == null) {
			mOBFormGenerateProdKYSCommentTextComment = new TextItem();
			mOBFormGenerateProdKYSCommentTextComment.setTitle("<nobr>KYS Comment</nobr>");
		}
		return mOBFormGenerateProdKYSCommentTextComment;
	}

	public IButton getmOBFormGenerateProdKYSCommentButtonGenerate() {
		if (mOBFormGenerateProdKYSCommentButtonGenerate == null) {
			mOBFormGenerateProdKYSCommentButtonGenerate = new IButton();
			mOBFormGenerateProdKYSCommentButtonGenerate.setTitle("Generate");
			mOBFormGenerateProdKYSCommentButtonGenerate.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormGenerateProdKYSCommentButtonGenerate;
	}

	public HLayout getmOBVLayoutGenerateKYSComment() {
		if (mOBVLayoutGenerateKYSComment == null) {
			mOBVLayoutGenerateKYSComment = new HLayout();
			mOBVLayoutGenerateKYSComment.setWidth(250);
			mOBVLayoutGenerateKYSComment.setPadding(10);
			mOBVLayoutGenerateKYSComment.setGroupTitle("Generate KYS Comment");
			mOBVLayoutGenerateKYSComment.setIsGroup(true);
			mOBVLayoutGenerateKYSComment.setBackgroundColor("white");
			mOBVLayoutGenerateKYSComment.setBorder("1px solid #c0c0c0");
			mOBVLayoutGenerateKYSComment.setShowShadow(true);
			mOBVLayoutGenerateKYSComment.setShadowSoftness(10);
			mOBVLayoutGenerateKYSComment.setShadowOffset(5);
			mOBVLayoutGenerateKYSComment.addMember(getmOBFormGenerateKYSComment());
			mOBVLayoutGenerateKYSComment.addMember(getmOBFormGenerateKYSCommentButtonGenerate());

		}
		return mOBVLayoutGenerateKYSComment;
	}

	public DynamicForm getmOBFormGenerateKYSComment() {
		if (mOBFormGenerateKYSComment == null) {
			mOBFormGenerateKYSComment = new DynamicForm();
			mOBFormGenerateKYSComment.setWidth(300);
			mOBFormGenerateKYSComment.setPadding(5);
			mOBFormGenerateKYSComment.setFields(getmOBFormGenerateKYSCommentSelectType(),getmOBFormGenerateKYSCommentTextComponent(), getmOBFormGenerateKYSCommentTextComment());
		}
		return mOBFormGenerateKYSComment;
	}
	
	public SelectItem getmOBFormGenerateKYSCommentSelectType() {
		if (mOBFormGenerateKYSCommentSelectType == null) {
			mOBFormGenerateKYSCommentSelectType = new SelectItem();
			mOBFormGenerateKYSCommentSelectType.setWidth(200);
			mOBFormGenerateKYSCommentSelectType.setTitle("<nobr>Type</nobr>");
			mOBFormGenerateKYSCommentSelectType.setRequired(true);
		}
		return mOBFormGenerateKYSCommentSelectType;
	}

	public TextItem getmOBFormGenerateKYSCommentTextComponent() {
		if (mOBFormGenerateKYSCommentTextComponent == null) {
			mOBFormGenerateKYSCommentTextComponent = new TextItem();
			mOBFormGenerateKYSCommentTextComponent.setWidth(200);
			mOBFormGenerateKYSCommentTextComponent.setTitle("<nobr>Component</nobr>");
			mOBFormGenerateKYSCommentTextComponent.setRequired(true);
		}
		return mOBFormGenerateKYSCommentTextComponent;
	}

	public TextAreaItem getmOBFormGenerateKYSCommentTextComment() {
		if (mOBFormGenerateKYSCommentTextComment == null) {
			mOBFormGenerateKYSCommentTextComment = new TextAreaItem();
			mOBFormGenerateKYSCommentTextComment.setWidth(200);
			mOBFormGenerateKYSCommentTextComment.setTitle("<nobr>KYS Comment</nobr>");
		}
		return mOBFormGenerateKYSCommentTextComment;
	}

	public IButton getmOBFormGenerateKYSCommentButtonGenerate() {
		if (mOBFormGenerateKYSCommentButtonGenerate == null) {
			mOBFormGenerateKYSCommentButtonGenerate = new IButton();
			mOBFormGenerateKYSCommentButtonGenerate.setTitle("Generate");
			mOBFormGenerateKYSCommentButtonGenerate.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormGenerateKYSCommentButtonGenerate;
	}

	public VLayout getmOBVLayoutDevOtherTasks() {
		if (mOBVLayoutDevOtherTasks == null) {
			mOBVLayoutDevOtherTasks = new VLayout(5);
			mOBVLayoutDevOtherTasks.setWidth(250);
			mOBVLayoutDevOtherTasks.setPadding(10);
			mOBVLayoutDevOtherTasks.setGroupTitle("Other Tasks");
			mOBVLayoutDevOtherTasks.setIsGroup(true);
			mOBVLayoutDevOtherTasks.setBackgroundColor("white");
			mOBVLayoutDevOtherTasks.setBorder("1px solid #c0c0c0");
			mOBVLayoutDevOtherTasks.setShowShadow(true);
			mOBVLayoutDevOtherTasks.setShadowSoftness(10);
			mOBVLayoutDevOtherTasks.setShadowOffset(5);
			mOBVLayoutDevOtherTasks.addMember(getmOBFormDevOtherTasks());
			mOBVLayoutDevOtherTasks.addMember(getmOBHLayoutDevRPOtherTasksButtons());

		}
		return mOBVLayoutDevOtherTasks;
	}

	public DynamicForm getmOBFormDevOtherTasks() {
		if (mOBFormDevOtherTasks == null) {
			mOBFormDevOtherTasks = new DynamicForm();
			mOBFormDevOtherTasks.setWidth(300);
			mOBFormDevOtherTasks.setPadding(5);
			mOBFormDevOtherTasks.setFields(getmOBFormDevOtherTasksSelectOtherTaskTypes(), getmOBFormDevOtherTasksTextItemShortDescription(), getmOBFormDevOtherTasksTextAreaExplanation());
		}
		return mOBFormDevOtherTasks;
	}

	public SelectItem getmOBFormDevOtherTasksSelectOtherTaskTypes() {
		if (mOBFormDevOtherTasksSelectOtherTaskTypes == null) {
			mOBFormDevOtherTasksSelectOtherTaskTypes = new SelectItem();
			mOBFormDevOtherTasksSelectOtherTaskTypes.setTitle("<nobr>Other Task Types</nobr>");
			mOBFormDevOtherTasksSelectOtherTaskTypes.setRequired(true);
		}
		return mOBFormDevOtherTasksSelectOtherTaskTypes;
	}

	public TextItem getmOBFormDevOtherTasksTextItemShortDescription() {
		if (mOBFormDevOtherTasksTextItemShortDescription == null) {
			mOBFormDevOtherTasksTextItemShortDescription = new TextItem();
			mOBFormDevOtherTasksTextItemShortDescription.setLength(25);
			mOBFormDevOtherTasksTextItemShortDescription.setTitle("<nobr>Short Description</nobr>");
			mOBFormDevOtherTasksTextItemShortDescription.setRequired(true);
		}
		return mOBFormDevOtherTasksTextItemShortDescription;
	}

	public TextAreaItem getmOBFormDevOtherTasksTextAreaExplanation() {
		if (mOBFormDevOtherTasksTextAreaExplanation == null) {
			mOBFormDevOtherTasksTextAreaExplanation = new TextAreaItem();
			mOBFormDevOtherTasksTextAreaExplanation.setLength(4000);
			mOBFormDevOtherTasksTextAreaExplanation.setWidth(200);
			mOBFormDevOtherTasksTextAreaExplanation.setTitle("<nobr>Explanation</nobr>");
		}
		return mOBFormDevOtherTasksTextAreaExplanation;
	}

	public HLayout getmOBHLayoutDevRPOtherTasksButtons() {// TODO buttonlar sagdan siralansin
		if (mOBHLayoutDevRPOtherTasksButtons == null) {
			mOBHLayoutDevRPOtherTasksButtons = new HLayout();
			mOBHLayoutDevRPOtherTasksButtons.setAlign(Alignment.RIGHT);
			mOBHLayoutDevRPOtherTasksButtons.addMember(getmOBFormDevOtherTasksButtonAdd());
			mOBHLayoutDevRPOtherTasksButtons.addMember(getmOBFormDevOtherTasksButtonDelete());
		}
		return mOBHLayoutDevRPOtherTasksButtons;
	}

	public IButton getmOBFormDevOtherTasksButtonAdd() {
		if (mOBFormDevOtherTasksButtonAdd == null) {
			mOBFormDevOtherTasksButtonAdd = new IButton();
			mOBFormDevOtherTasksButtonAdd.setTitle("Add");
			mOBFormDevOtherTasksButtonAdd.setLayoutAlign(Alignment.RIGHT);
			mOBFormDevOtherTasksButtonAdd.setDisabled(true);
		}
		return mOBFormDevOtherTasksButtonAdd;
	}

	public IButton getmOBFormDevOtherTasksButtonDelete() {
		if (mOBFormDevOtherTasksButtonDelete == null) {
			mOBFormDevOtherTasksButtonDelete = new IButton();
			mOBFormDevOtherTasksButtonDelete.setTitle("Delete");
			mOBFormDevOtherTasksButtonDelete.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormDevOtherTasksButtonDelete;
	}

	public VLayout getmOBVLayoutProdOtherTasks() {
		if (mOBVLayoutProdOtherTasks == null) {
			mOBVLayoutProdOtherTasks = new VLayout(5);
			mOBVLayoutProdOtherTasks.setWidth(250);
			mOBVLayoutProdOtherTasks.setPadding(10);
			mOBVLayoutProdOtherTasks.setGroupTitle("Other Tasks");
			mOBVLayoutProdOtherTasks.setIsGroup(true);
			mOBVLayoutProdOtherTasks.setBackgroundColor("white");
			mOBVLayoutProdOtherTasks.setBorder("1px solid #c0c0c0");
			mOBVLayoutProdOtherTasks.setShowShadow(true);
			mOBVLayoutProdOtherTasks.setShadowSoftness(10);
			mOBVLayoutProdOtherTasks.setShadowOffset(5);
			mOBVLayoutProdOtherTasks.addMember(getmOBFormProdOtherTasks());

		}
		return mOBVLayoutProdOtherTasks;
	}

	public VLayout getmOBVLayoutProdRequest() {
		if (mOBVLayoutProdRequest == null) {
			mOBVLayoutProdRequest = new VLayout(5);
			mOBVLayoutProdRequest.setWidth(250);
			mOBVLayoutProdRequest.setPadding(10);
			mOBVLayoutProdRequest.setGroupTitle("Request Email");
			mOBVLayoutProdRequest.setIsGroup(true);
			mOBVLayoutProdRequest.setBackgroundColor("white");
			mOBVLayoutProdRequest.setBorder("1px solid #c0c0c0");
			mOBVLayoutProdRequest.setShowShadow(true);
			mOBVLayoutProdRequest.setShadowSoftness(10);
			mOBVLayoutProdRequest.setShadowOffset(5);
			mOBVLayoutProdRequest.addMember(getmOBFormProdRequest());
			mOBVLayoutProdRequest.addMember(getmOBHLayoutProdRequestButtons());

		}
		return mOBVLayoutProdRequest;
	}

	public HLayout getmOBHLayoutProdRequestButtons() {// TODO buttonlar sagdan siralansin
		if (mOBHLayoutProdRequestButtons == null) {
			mOBHLayoutProdRequestButtons = new HLayout();
			mOBHLayoutProdRequestButtons.setAlign(Alignment.RIGHT);
			mOBHLayoutProdRequestButtons.addMember(getmOBFormProdRequestButtonSubmit());
		}
		return mOBHLayoutProdRequestButtons;
	}

	public IButton getmOBFormProdRequestButtonSubmit() {
		if (mOBFormProdRequestButtonSubmit == null) {
			mOBFormProdRequestButtonSubmit = new IButton();
			mOBFormProdRequestButtonSubmit.setTitle("Submit");
			mOBFormProdRequestButtonSubmit.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormProdRequestButtonSubmit;
	}

	public DynamicForm getmOBFormProdOtherTasks() {
		if (mOBFormProdOtherTasks == null) {
			mOBFormProdOtherTasks = new DynamicForm();
			mOBFormProdOtherTasks.setWidth(300);
			mOBFormProdOtherTasks.setPadding(5);
			mOBFormProdOtherTasks.setFields(getmOBFormProdOtherTasksTextAreaExplanation());
		}
		return mOBFormProdOtherTasks;
	}

	public DynamicForm getmOBFormProdRequest() {
		if (mOBFormProdRequest == null) {
			mOBFormProdRequest = new DynamicForm();
			mOBFormProdRequest.setEncoding(Encoding.MULTIPART);
			mOBFormProdRequest.setWidth(300);
			mOBFormProdRequest.setPadding(5);
			mOBFormProdRequest.setFields(getmOBFormProdRequestFileItem());
		}
		return mOBFormProdRequest;
	}

	public TextAreaItem getmOBFormProdOtherTasksTextAreaExplanation() {
		if (mOBFormProdOtherTasksTextAreaExplanation == null) {
			mOBFormProdOtherTasksTextAreaExplanation = new TextAreaItem();
			mOBFormProdOtherTasksTextAreaExplanation.setLength(4000);
			mOBFormProdOtherTasksTextAreaExplanation.setTitle("<nobr>Explanation</nobr>");
		}
		return mOBFormProdOtherTasksTextAreaExplanation;
	}

	public UploadItem getmOBFormProdRequestFileItem() {
		if (mOBFormProdRequestUploadItem == null) {
			mOBFormProdRequestUploadItem = new UploadItem();
			mOBFormProdRequestUploadItem.setTitle("<nobr>Request Email</nobr>");
		}
		return mOBFormProdRequestUploadItem;
	}

	public VLayout getmOBVLayoutProdTransferOthers() {
		if (mOBVLayoutProdTransferOthers == null) {
			mOBVLayoutProdTransferOthers = new VLayout(5);
			mOBVLayoutProdTransferOthers.setWidth(250);
			mOBVLayoutProdTransferOthers.setPadding(10);
			mOBVLayoutProdTransferOthers.setGroupTitle("Transfer Others");
			mOBVLayoutProdTransferOthers.setIsGroup(true);
			mOBVLayoutProdTransferOthers.setBackgroundColor("white");
			mOBVLayoutProdTransferOthers.setBorder("1px solid #c0c0c0");
			mOBVLayoutProdTransferOthers.setShowShadow(true);
			mOBVLayoutProdTransferOthers.setShadowSoftness(10);
			mOBVLayoutProdTransferOthers.setShadowOffset(5);
			mOBVLayoutProdTransferOthers.addMember(getmOBFormProdTransferOthers());
			mOBVLayoutProdTransferOthers.addMember(getmOBHLayoutProdTransferOthersButtons());

		}
		return mOBVLayoutProdTransferOthers;
	}

	public DynamicForm getmOBFormProdTransferOthers() {
		if (mOBFormProdTransferOthers == null) {
			mOBFormProdTransferOthers = new DynamicForm();
			mOBFormProdTransferOthers.setWidth(300);
			mOBFormProdTransferOthers.setPadding(5);
			mOBFormProdTransferOthers.setFields(getmOBFormProdTransferOthersSelectProdDeployment());
		}
		return mOBFormProdTransferOthers;
	}

	public SelectItem getmOBFormProdTransferOthersSelectProdDeployment() {
		if (mOBFormProdTransferOthersSelectProdDeployment == null) {
			mOBFormProdTransferOthersSelectProdDeployment = new SelectItem();
			mOBFormProdTransferOthersSelectProdDeployment.setTitle("<nobr>Select Prod</nobr>");
			mOBFormProdTransferOthersSelectProdDeployment.setRequired(true);
		}
		return mOBFormProdTransferOthersSelectProdDeployment;
	}

	public HLayout getmOBHLayoutProdTransferOthersButtons() {// TODO buttonlar sagdan siralansin
		if (mOBHLayoutProdTransferOthersButtons == null) {
			mOBHLayoutProdTransferOthersButtons = new HLayout();
			mOBHLayoutProdTransferOthersButtons.setAlign(Alignment.RIGHT);
			mOBHLayoutProdTransferOthersButtons.addMember(getmOBFormProdTransferOthersButtonTransfer());
		}
		return mOBHLayoutProdTransferOthersButtons;
	}

	public IButton getmOBFormProdTransferOthersButtonTransfer() {
		if (mOBFormProdTransferOthersButtonTransfer == null) {
			mOBFormProdTransferOthersButtonTransfer = new IButton();
			mOBFormProdTransferOthersButtonTransfer.setTitle("Transfer");
			mOBFormProdTransferOthersButtonTransfer.setLayoutAlign(Alignment.RIGHT);
		}
		return mOBFormProdTransferOthersButtonTransfer;
	}

}
