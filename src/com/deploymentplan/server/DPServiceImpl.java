package com.deploymentplan.server;

import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import com.deploymentplan.client.DPService;
import com.deploymentplan.domain.DeployItemTypes;
import com.deploymentplan.domain.DevDeployment;
import com.deploymentplan.domain.DevDeploymentTree;
import com.deploymentplan.domain.DevOtherTaskTree;
import com.deploymentplan.domain.KYSCommentForDevType;
import com.deploymentplan.domain.OtherTaskTypes;
import com.deploymentplan.domain.ProdDeployment;
import com.deploymentplan.domain.ProdDeploymentTree;
import com.deploymentplan.domain.ProdOtherTaskTree;
import com.deploymentplan.domain.ProdStatus;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
/**
 * test
 * @author deniz
 * 
 * t22345
 *
 */
public class DPServiceImpl extends RemoteServiceServlet implements DPService {

	@Override
	public ArrayList searchDeployments(ProdDeployment pOBProdDeployment) throws Exception {
		ArrayList<String> L = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();

			L = (ArrayList<String>) sqlMap.queryForList("searchProdDeployments", pOBProdDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;
	}

	@Override
	public ArrayList getDevDeployments(DevDeployment pOBDevDeployment) throws Exception {
		ArrayList<String> L = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();
			L = (ArrayList<String>) sqlMap.queryForList("getDevDeployments", pOBDevDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;
	}

	@Override
	public LinkedHashMap getDeployItemTypes() throws Exception {
		LinkedHashMap lOBInfoTypeMap = new LinkedHashMap();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();
			HashMap lOBHashMap = (HashMap) sqlMap.queryForMap("getDeployItemTypes", new DeployItemTypes(), "name", "id");

			ArrayList lOBList = new ArrayList(lOBHashMap.keySet());
			Collections.sort(lOBList);

			for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
				String lSTName = (String) iterator.next();
				lOBInfoTypeMap.put(Integer.toString((Integer) lOBHashMap.get(lSTName)), lSTName);
			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lOBInfoTypeMap;
	}

	@Override
	public LinkedHashMap refreshProdStatus() throws Exception {
		LinkedHashMap lOBComponentsMap = new LinkedHashMap();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();
			HashMap lOBHashMap = (HashMap) sqlMap.queryForMap("refreshProdStatus", new ProdStatus(), "name", "id");

			ArrayList lOBList = new ArrayList(lOBHashMap.keySet());
			Collections.sort(lOBList);

			for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
				String lSTName = (String) iterator.next();
				lOBComponentsMap.put(Integer.toString((Integer) lOBHashMap.get(lSTName)), lSTName);
			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lOBComponentsMap;
	}

	@Override
	public LinkedHashMap refreshGenerateKYSCommentForDevTypes() throws Exception {
		LinkedHashMap lOBComponentsMap = new LinkedHashMap();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();
			HashMap lOBHashMap = (HashMap) sqlMap.queryForMap("refreshKYSCommentForDevType", new KYSCommentForDevType(), "name", "id");

			ArrayList lOBList = new ArrayList(lOBHashMap.keySet());
			Collections.sort(lOBList);

			for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
				String lSTName = (String) iterator.next();
				lOBComponentsMap.put(Integer.toString((Integer) lOBHashMap.get(lSTName)), lSTName);
			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lOBComponentsMap;
	}

	@Override
	public ArrayList insertDeploymentTrees(DevDeploymentTree pOBComponent, DevDeploymentTree pOBDeploymentType, DevDeploymentTree pOBDeploymentItemName) throws Exception {
		ArrayList L = new ArrayList();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			checkAddAvailability(pOBComponent);

			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();

			DevDeploymentTree lOBDevExisting = (DevDeploymentTree) sqlMap.queryForObject("getDevDeploymentTreeInSameDeployment", pOBComponent);

			if (lOBDevExisting == null) {
				Integer lOBID = (Integer) sqlMap.insert("insertDevDeploymentTree", pOBComponent);
				lOBDevExisting = pOBComponent;
				lOBDevExisting.setId(lOBID.intValue());
			}

			pOBDeploymentType.setParentId(lOBDevExisting.getId());

			lOBDevExisting = (DevDeploymentTree) sqlMap.queryForObject("getDevDeploymentTreeInSameDeployment", pOBDeploymentType);

			if (lOBDevExisting == null) {
				Integer lOBID = (Integer) sqlMap.insert("insertDevDeploymentTree", pOBDeploymentType);
				lOBDevExisting = pOBDeploymentType;
				lOBDevExisting.setId(lOBID.intValue());
			}

			pOBDeploymentItemName.setParentId(lOBDevExisting.getId());

			lOBDevExisting = (DevDeploymentTree) sqlMap.queryForObject("getDevDeploymentTreeInSameDeployment", pOBDeploymentItemName);

			if (lOBDevExisting == null) {
				sqlMap.insert("insertDevDeploymentTree", pOBDeploymentItemName);
			}

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(pOBComponent.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

			ProdDeploymentTree lOBOldDeploymentTreeItem = new ProdDeploymentTree();
			ProdDeploymentTree lOBProdComponent = new ProdDeploymentTree();
			lOBProdComponent.setParentId(-1);
			lOBProdComponent.setName(pOBComponent.getName());
			lOBProdComponent.setItemType(pOBComponent.getItemType());
			lOBProdComponent.setProdDeploymentId(lOBDevDeployment.getProdDeploymentId());
			lOBOldDeploymentTreeItem = (ProdDeploymentTree) sqlMap.queryForObject("getProdDeploymentTreeInSameDeployment", lOBProdComponent);
			if (lOBOldDeploymentTreeItem == null) {
				Integer lOBID = (Integer) sqlMap.insert("insertProdDeploymentTree", lOBProdComponent);
				lOBProdComponent.setId(lOBID.intValue());
			} else {
				lOBProdComponent = lOBOldDeploymentTreeItem;
			}

			ProdDeploymentTree lOBProdDeploymentType = new ProdDeploymentTree();
			lOBProdDeploymentType.setItemType(2);
			lOBProdDeploymentType.setName(pOBDeploymentType.getName());
			lOBProdDeploymentType.setParentId(lOBProdComponent.getId());
			lOBProdDeploymentType.setProdDeploymentId(lOBDevDeployment.getProdDeploymentId());
			lOBProdDeploymentType.setSubItemType(pOBDeploymentType.getSubItemType());
			lOBOldDeploymentTreeItem = (ProdDeploymentTree) sqlMap.queryForObject("getProdDeploymentTreeInSameDeployment", lOBProdDeploymentType);
			if (lOBOldDeploymentTreeItem == null) {
				Integer lOBID = (Integer) sqlMap.insert("insertProdDeploymentTree", lOBProdDeploymentType);
				lOBProdDeploymentType.setId(lOBID.intValue());
			} else {
				lOBProdDeploymentType = lOBOldDeploymentTreeItem;
			}

			ProdDeploymentTree lOBProdDeploymentItemName = new ProdDeploymentTree();
			lOBProdDeploymentItemName.setItemType(3);
			lOBProdDeploymentItemName.setName(pOBDeploymentItemName.getName());
			lOBProdDeploymentItemName.setParentId(lOBProdDeploymentType.getId());
			lOBProdDeploymentItemName.setProdDeploymentId(lOBDevDeployment.getProdDeploymentId());
			lOBOldDeploymentTreeItem = (ProdDeploymentTree) sqlMap.queryForObject("getProdDeploymentTreeInSameDeployment", lOBProdDeploymentItemName);
			if (lOBOldDeploymentTreeItem == null) {
				Integer lOBID = (Integer) sqlMap.insert("insertProdDeploymentTree", lOBProdDeploymentItemName);
				lOBProdDeploymentItemName.setId(lOBID.intValue());
			} else {
				lOBProdDeploymentItemName = lOBOldDeploymentTreeItem;
			}

			L = (ArrayList<String>) sqlMap.queryForList("getDevDeploymentTreeList", pOBDeploymentItemName);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;

	}

	@Override
	public ArrayList getDevDeploymentTreeList(DevDeploymentTree lOBTree) throws Exception {
		ArrayList<String> L = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();
			L = (ArrayList) sqlMap.queryForList("getDevDeploymentTreeList", lOBTree);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

		return L;
	}

	public ArrayList getProdDeploymentTreeList(ProdDeploymentTree pOBProdDeploymentTree) throws Exception {
		ArrayList<String> L = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			getSession();

			sqlMap.startTransaction();
			L = (ArrayList) sqlMap.queryForList("getProdDeploymentTreeList", pOBProdDeploymentTree);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

		return L;
	}

	@Override
	public ArrayList getComponents() throws Exception {
		ArrayList<String> L = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();
			L = (ArrayList<String>) sqlMap.queryForList("getComponents");

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;
	}

	@Override
	public ArrayList getItems(ProdDeploymentTree pOBProdDeploymentTree) throws Exception {
		ArrayList<String> L = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();
			L = (ArrayList<String>) sqlMap.queryForList("getItems", pOBProdDeploymentTree);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;
	}

	@Override
	public ProdDeployment updateProdDeployment(ProdDeployment pOBProdDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			ProdDeployment lOBOldProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", pOBProdDeployment);

			pOBProdDeployment.setDeployed(lOBOldProdDeployment.getDeployed());

			checkUpdateAvailability(pOBProdDeployment);

			sqlMap.startTransaction();

			sqlMap.update("updateProdDeployment", pOBProdDeployment);

			pOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", pOBProdDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return pOBProdDeployment;
	}

	@Override
	public Integer addDevDeployment(DevDeployment pOBDevDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		Integer lOBID = new Integer("0");
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			checkAddAvailability(pOBDevDeployment);

			sqlMap.startTransaction();

			pOBDevDeployment.setOwnerId(getSessionOwnerId());
			pOBDevDeployment.setDate(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()));
			lOBID = (Integer) sqlMap.insert("addDevDeployment", pOBDevDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lOBID;
	}

	public void addProdDeployment(ProdDeployment pOBProdDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			checkAddAvailability(pOBProdDeployment);

			sqlMap.startTransaction();

			pOBProdDeployment.setOwnerId(getSessionOwnerId());
			pOBProdDeployment.setDate(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()));
			sqlMap.insert("addProdDeployment", pOBProdDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	@Override
	public void deleteProdDeployment(ProdDeployment pOBProdDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			pOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", pOBProdDeployment);

			checkDeleteAvailability(pOBProdDeployment);

			sqlMap.startTransaction();

			sqlMap.update("deleteProdDeployment", pOBProdDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

	}

	@Override
	public void deleteDevDeployment(DevDeployment pOBDevDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();

			pOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", pOBDevDeployment);

			checkDeleteAvailability(pOBDevDeployment);

			sqlMap.update("deleteDevDeployment", pOBDevDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

	}

	@Override
	public DevDeployment updateDevDeployment(DevDeployment pOBDevDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			DevDeployment lOBOldDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", pOBDevDeployment);

			pOBDevDeployment.setDeployed(lOBOldDevDeployment.getDeployed());

			checkUpdateAvailability(pOBDevDeployment);

			sqlMap.startTransaction();

			sqlMap.update("updateDevDeployment", pOBDevDeployment);

			pOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", pOBDevDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return pOBDevDeployment;

	}

	@Override
	public DevDeployment deployDevDeployment(DevDeployment pOBDevDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			checkUpdateAvailability(pOBDevDeployment);

			sqlMap.startTransaction();
			if (pOBDevDeployment.getDeployed() == 1) {
				sqlMap.update("deployDevDeployment", pOBDevDeployment);
			} else {
				sqlMap.update("undeployDevDeployment", pOBDevDeployment);
			}

			pOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", pOBDevDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

		return pOBDevDeployment;

	}

	@Override
	public ProdDeployment deployProdDeployment(ProdDeployment pOBProdDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			checkUpdateAvailability(pOBProdDeployment);

			sqlMap.startTransaction();
			if (pOBProdDeployment.getDeployed() == 1) {
				sqlMap.update("deployProdDeployment", pOBProdDeployment);
			} else {
				sqlMap.update("undeployProdDeployment", pOBProdDeployment);
			}

			pOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", pOBProdDeployment);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

		return pOBProdDeployment;

	}

	@Override
	public ArrayList deleteTreeDetail(DevDeploymentTree pOBDevDeploymentTreeItem) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			checkDeleteAvailability(pOBDevDeploymentTreeItem);

			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();

			pOBDevDeploymentTreeItem = (DevDeploymentTree) sqlMap.queryForObject("getDevDeploymentTree", pOBDevDeploymentTreeItem);

			DevDeploymentTree lOBItemType = new DevDeploymentTree();
			lOBItemType.setId(pOBDevDeploymentTreeItem.getParentId());
			lOBItemType = (DevDeploymentTree) sqlMap.queryForObject("getDevDeploymentTree", lOBItemType);

			DevDeploymentTree lOBComponent = new DevDeploymentTree();
			lOBComponent.setId(lOBItemType.getParentId());
			lOBComponent = (DevDeploymentTree) sqlMap.queryForObject("getDevDeploymentTree", lOBComponent);

			sqlMap.update("deleteDevDeploymentTree", pOBDevDeploymentTreeItem);

			ArrayList lOBChildList = (ArrayList) sqlMap.queryForList("getDevChildItems", lOBItemType);

			if (lOBChildList.size() == 0) {
				sqlMap.update("deleteDevDeploymentTree", lOBItemType);
				lOBChildList = (ArrayList) sqlMap.queryForList("getDevChildItems", lOBComponent);
				if (lOBChildList.size() == 0) {
					sqlMap.update("deleteDevDeploymentTree", lOBComponent);
				}
			}

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(lOBComponent.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

			HashMap lOBHashMap = new HashMap();
			lOBHashMap.put("prodDeploymentId", lOBDevDeployment.getProdDeploymentId());
			lOBHashMap.put("componentName", lOBComponent.getName());
			lOBHashMap.put("itemTypeName", lOBItemType.getName());
			lOBHashMap.put("itemName", pOBDevDeploymentTreeItem.getName());
			ArrayList lOBDevDeploymentTreeItemListInSameProd = (ArrayList) sqlMap.queryForList("getDevDeploymentTreeItemListInSameProd", lOBHashMap);
			if (lOBDevDeploymentTreeItemListInSameProd.size() == 0) {
				ProdDeploymentTree lOBProdDeploymentTreeComponent = new ProdDeploymentTree();
				lOBProdDeploymentTreeComponent.setParentId(-1);
				lOBProdDeploymentTreeComponent.setProdDeploymentId(lOBDevDeployment.getProdDeploymentId());
				lOBProdDeploymentTreeComponent.setName(lOBComponent.getName());
				lOBProdDeploymentTreeComponent = (ProdDeploymentTree) sqlMap.queryForObject("getProdDeploymentTreeInSameDeployment", lOBProdDeploymentTreeComponent);

				ProdDeploymentTree lOBProdDeploymentTreeItemType = new ProdDeploymentTree();
				lOBProdDeploymentTreeItemType.setParentId(lOBProdDeploymentTreeComponent.getId());
				lOBProdDeploymentTreeItemType.setName(lOBItemType.getName());
				lOBProdDeploymentTreeItemType.setProdDeploymentId(lOBDevDeployment.getProdDeploymentId());
				lOBProdDeploymentTreeItemType = (ProdDeploymentTree) sqlMap.queryForObject("getProdDeploymentTreeInSameDeployment", lOBProdDeploymentTreeItemType);

				ProdDeploymentTree lOBProdDeploymentTreeItem = new ProdDeploymentTree();
				lOBProdDeploymentTreeItem.setName(pOBDevDeploymentTreeItem.getName());
				lOBProdDeploymentTreeItem.setProdDeploymentId(lOBDevDeployment.getProdDeploymentId());
				lOBProdDeploymentTreeItem.setParentId(lOBProdDeploymentTreeItemType.getId());
				lOBProdDeploymentTreeItem = (ProdDeploymentTree) sqlMap.queryForObject("getProdDeploymentTreeInSameDeployment", lOBProdDeploymentTreeItem);

				sqlMap.update("deleteProdDeploymentTree", lOBProdDeploymentTreeItem);

				ArrayList lOBProdDTItemTypeChildList = (ArrayList) sqlMap.queryForList("getProdChildItems", lOBProdDeploymentTreeItemType);
				if (lOBProdDTItemTypeChildList.size() == 0) {
					sqlMap.update("deleteProdDeploymentTree", lOBProdDeploymentTreeItemType);

					ArrayList lOBProdDTComponentChildList = (ArrayList) sqlMap.queryForList("getProdChildItems", lOBProdDeploymentTreeComponent);
					if (lOBProdDTComponentChildList.size() == 0) {
						sqlMap.update("deleteProdDeploymentTree", lOBProdDeploymentTreeComponent);
					}
				}

			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return getDevDeploymentTreeList(pOBDevDeploymentTreeItem);

	}

	public LinkedHashMap getOtherTaskTypes() throws Exception {
		LinkedHashMap lOBInfoTypeMap = new LinkedHashMap();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();

			HashMap lOBHashMap = (HashMap) sqlMap.queryForMap("getOtherTaskTypes", new OtherTaskTypes(), "name", "id");

			ArrayList lOBList = new ArrayList(lOBHashMap.keySet());
			Collections.sort(lOBList);

			for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
				String lSTName = (String) iterator.next();
				lOBInfoTypeMap.put(Integer.toString((Integer) lOBHashMap.get(lSTName)), lSTName);
			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lOBInfoTypeMap;

	}

	@Override
	public String generateKYSCommentForDev(DevDeploymentTree pOBDevDeploymentTree, Integer pOBKYSCommentForDevType) throws Exception {
		SqlMapClient sqlMap = null;
		StringBuffer lOBBuffer = new StringBuffer();
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			pOBDevDeploymentTree = (DevDeploymentTree) sqlMap.queryForObject("getDevDeploymentTree", pOBDevDeploymentTree);

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(pOBDevDeploymentTree.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

//			if (lOBDevDeployment.getDeployed() == 1) {
//				Exception ex = new Exception("This dev is deployed, why are you clicking?");
//				throw ex;
//			}

			ProdDeployment lOBProdDeployment = new ProdDeployment();
			lOBProdDeployment.setId(lOBDevDeployment.getProdDeploymentId());
			lOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", lOBProdDeployment);

			ArrayList lOBList = new ArrayList();

			// DevDeploymentTree.xml
			lOBList = (ArrayList) sqlMap.queryForList("generateKYSCommentForDev", pOBDevDeploymentTree);

			if (pOBKYSCommentForDevType.intValue() == 1) { // Dev Eclipse
				lOBBuffer.append("[9.3.,9.2.]");
				lOBBuffer.append("[DEV]");
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					lOBBuffer.append((String) iterator.next());
				}
				lOBBuffer.append("[" + lOBProdDeployment.getName() + "]");
				lOBBuffer.append("[DevDepID: " + lOBDevDeployment.getName() + "]");
			} else if (pOBKYSCommentForDevType.intValue() == 2) { // Prod Eclipse
				lOBBuffer.append("[9.3.,9.2.]");
				lOBBuffer.append("[PROD]");
				lOBBuffer.append("[" + lOBProdDeployment.getName() + "]");
				lOBBuffer.append("[DevDepID: " + lOBDevDeployment.getName() + "]");
			} else if (pOBKYSCommentForDevType.intValue() == 3) { // Dev CMS
				lOBBuffer.append("[9.3.,9.2.]");
				lOBBuffer.append("<span style=\"color:green\"><b>[DEV]</b></span>");
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					lOBBuffer.append((String) iterator.next());
				}
				lOBBuffer.append("<b>");
				lOBBuffer.append("[" + lOBProdDeployment.getName() + "]");
				lOBBuffer.append("[DevDepID: " + lOBDevDeployment.getName() + "]");
				lOBBuffer.append("</b>");
			} else if (pOBKYSCommentForDevType.intValue() == 4) { // Prod CMS
				lOBBuffer.append("[9.3.,9.2.]");
				lOBBuffer.append("<span style=\"color:red\"><b>[PROD]</b></span>");
				lOBBuffer.append("<b>");
				lOBBuffer.append("[" + lOBProdDeployment.getName() + "]");
				lOBBuffer.append("[DevDepID: " + lOBDevDeployment.getName() + "]");
				lOBBuffer.append("</b>");
			} else if (pOBKYSCommentForDevType.intValue() == 5) { // class checkin
				lOBBuffer.append("[Check-in Date: "+new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())+"]");
				for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
					lOBBuffer.append((String) iterator.next());
				}
				lOBBuffer.append("[" + lOBProdDeployment.getName() + "]");
				lOBBuffer.append("[DevDepID: " + lOBDevDeployment.getName() + "]");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lOBBuffer.toString();
	}

	public String generateKYSCommentForProd(ProdDeploymentTree pOBProdDeploymentTree) throws Exception {
		SqlMapClient sqlMap = null;
		StringBuffer lOBBuffer = new StringBuffer();
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			pOBProdDeploymentTree = (ProdDeploymentTree) sqlMap.queryForObject("getProdDeploymentTree", pOBProdDeploymentTree);

			ProdDeployment lOBProdDeployment = new ProdDeployment();
			lOBProdDeployment.setId(pOBProdDeploymentTree.getProdDeploymentId());
			lOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", lOBProdDeployment);

			if (lOBProdDeployment.getDeployed() == 1) {
				Exception ex = new Exception("This prod is deployed, please pay attention!");
				throw ex;
			}

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setProdDeploymentId(lOBProdDeployment.getId());
			ArrayList lOBNonDeployedList = (ArrayList) sqlMap.queryForList("getNotDeployedDevDeployments", lOBDevDeployment);

			if (lOBNonDeployedList.size() > 1) {
				Exception ex = new Exception("Related dev deployments are not deployed. You can't deploy prod, why you are generating KYS comment?");
				throw ex;
			}

			ArrayList lOBList = (ArrayList) sqlMap.queryForList("generateKYSCommentForProd", pOBProdDeploymentTree);

			for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
				lOBBuffer.append((String) iterator.next());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lOBBuffer.toString();
	}

	public ArrayList addDevOtherTask(DevOtherTaskTree pOBParentTree, DevOtherTaskTree pOBDetailTree) throws Exception {
		ArrayList<String> lOBDevOtherTaskList = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			checkAddAvailability(pOBParentTree);

			sqlMap.startTransaction();

			DevOtherTaskTree lOBOldParent = (DevOtherTaskTree) sqlMap.queryForObject("getDevOtherTaskTreeInSameDeployment", pOBParentTree);
			if (lOBOldParent == null) {
				Integer lOBID = (Integer) sqlMap.insert("addDevOtherTask", pOBParentTree);
				pOBParentTree.setId(lOBID);
				pOBDetailTree.setParentId(lOBID);
			} else {
				pOBDetailTree.setParentId(lOBOldParent.getId());
				pOBParentTree = lOBOldParent;
			}

			Integer lOBDevDetailID = (Integer) sqlMap.insert("addDevOtherTask", pOBDetailTree);
			pOBDetailTree.setId(lOBDevDetailID);

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(pOBParentTree.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

			ProdOtherTaskTree lOBProdOtherTaskDetail = new ProdOtherTaskTree();
			lOBProdOtherTaskDetail.setExplanation(pOBDetailTree.getExplanation());
			lOBProdOtherTaskDetail.setProdDeploymentId(lOBDevDeployment.getProdDeploymentId());
			lOBProdOtherTaskDetail.setShortDescription(pOBDetailTree.getShortDescription());
			lOBProdOtherTaskDetail.setDevOtherTaskTreeId(pOBDetailTree.getId());

			ProdOtherTaskTree lOBProdOtherTaskParent = new ProdOtherTaskTree();
			lOBProdOtherTaskParent.setParentId(-1);
			lOBProdOtherTaskParent.setProdDeploymentId(lOBDevDeployment.getProdDeploymentId());
			lOBProdOtherTaskParent.setShortDescription(pOBParentTree.getShortDescription());
			lOBProdOtherTaskParent.setDevOtherTaskTreeId(-1);
			ProdOtherTaskTree lOBProdOldParent = (ProdOtherTaskTree) sqlMap.queryForObject("getProdOtherTaskTreeInSameDeployment", lOBProdOtherTaskParent);
			if (lOBProdOldParent == null) {
				Integer lOBProdParentID = (Integer) sqlMap.insert("addProdOtherTask", lOBProdOtherTaskParent);
				lOBProdOtherTaskParent.setId(lOBProdParentID);
				lOBProdOtherTaskDetail.setParentId(lOBProdParentID);
			} else {
				lOBProdOtherTaskDetail.setParentId(lOBProdOldParent.getId());
				lOBProdOtherTaskParent = lOBProdOldParent;
			}

			sqlMap.insert("addProdOtherTask", lOBProdOtherTaskDetail);

			lOBDevOtherTaskList = (ArrayList) sqlMap.queryForList("getDevOtherTaskList", pOBParentTree);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

		return lOBDevOtherTaskList;
	}

	public ArrayList getDevOtherTaskList(DevOtherTaskTree pOBOtherTaskTree) throws Exception {
		ArrayList<String> lOBDevOtherTaskList = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			lOBDevOtherTaskList = (ArrayList) sqlMap.queryForList("getDevOtherTaskList", pOBOtherTaskTree);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

		return lOBDevOtherTaskList;
	}

	public ArrayList getProdOtherTaskList(ProdOtherTaskTree pOBOtherTaskTree) throws Exception {
		ArrayList<String> lOBProdOtherTaskList = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			lOBProdOtherTaskList = (ArrayList) sqlMap.queryForList("getProdOtherTaskList", pOBOtherTaskTree);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

		return lOBProdOtherTaskList;
	}

	public DevOtherTaskTree getDevOtherTaskTree(DevOtherTaskTree pOBOtherTaskTree) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			pOBOtherTaskTree = (DevOtherTaskTree) sqlMap.queryForObject("getDevOtherTaskTree", pOBOtherTaskTree);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

		return pOBOtherTaskTree;
	}

	public ProdOtherTaskTree getProdOtherTaskTree(ProdOtherTaskTree pOBOtherTaskTree) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			pOBOtherTaskTree = (ProdOtherTaskTree) sqlMap.queryForObject("getProdOtherTaskTree", pOBOtherTaskTree);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

		return pOBOtherTaskTree;
	}

	public void deleteDevOtherTaskTree(DevOtherTaskTree pOBDevOtherTaskTree) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			checkDeleteAvailability(pOBDevOtherTaskTree);

			sqlMap.startTransaction();

			pOBDevOtherTaskTree = (DevOtherTaskTree) sqlMap.queryForObject("getDevOtherTaskTree", pOBDevOtherTaskTree);

			DevOtherTaskTree lOBOtherTaskTreeParent = new DevOtherTaskTree();
			lOBOtherTaskTreeParent.setId(pOBDevOtherTaskTree.getParentId());
			lOBOtherTaskTreeParent = (DevOtherTaskTree) sqlMap.queryForObject("getDevOtherTaskTree", lOBOtherTaskTreeParent);

			sqlMap.update("deleteDevOtherTaskTree", pOBDevOtherTaskTree);

			ArrayList lOBChildList = (ArrayList) sqlMap.queryForList("getDevChildOtherTaskTree", lOBOtherTaskTreeParent);
			if (lOBChildList.size() == 0) {
				sqlMap.update("deleteDevOtherTaskTree", lOBOtherTaskTreeParent);
			}

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(pOBDevOtherTaskTree.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

			ProdOtherTaskTree lOBProdOtherTaskTreeDetail = new ProdOtherTaskTree();// TODO other task ayni short description da varsa insert etmesin
			lOBProdOtherTaskTreeDetail.setDevOtherTaskTreeId(pOBDevOtherTaskTree.getId());
			lOBProdOtherTaskTreeDetail.setProdDeploymentId(lOBDevDeployment.getProdDeploymentId());
			lOBProdOtherTaskTreeDetail = (ProdOtherTaskTree) sqlMap.queryForObject("getProdOtherTaskTreeByDevID", lOBProdOtherTaskTreeDetail);

			sqlMap.update("deleteProdOtherTaskTree", lOBProdOtherTaskTreeDetail);

			ProdOtherTaskTree lOBProdOtherTaskTreeParent = new ProdOtherTaskTree();
			lOBProdOtherTaskTreeParent.setId(lOBProdOtherTaskTreeDetail.getParentId());
			lOBProdOtherTaskTreeParent = (ProdOtherTaskTree) sqlMap.queryForObject("getProdOtherTaskTree", lOBProdOtherTaskTreeParent);

			lOBChildList = (ArrayList) sqlMap.queryForList("getProdChildOtherTaskTree", lOBProdOtherTaskTreeParent);
			if (lOBChildList.size() == 0) {
				sqlMap.update("deleteProdOtherTaskTree", lOBProdOtherTaskTreeParent);
			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

	}

	public LinkedHashMap getTransferOthersProdList(ProdDeployment pOBProdDeployment) throws Exception {
		LinkedHashMap lOBInfoTypeMap = new LinkedHashMap();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();

			HashMap lOBHashMap = (HashMap) sqlMap.queryForMap("getTransferOthersProdList", pOBProdDeployment, "name", "id");

			ArrayList lOBList = new ArrayList(lOBHashMap.keySet());
			Collections.sort(lOBList);

			for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
				String lSTName = (String) iterator.next();
				lOBInfoTypeMap.put(Integer.toString((Integer) lOBHashMap.get(lSTName)), lSTName);
			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lOBInfoTypeMap;

	}

	public void transferOthers(ProdDeployment pOBFromProdDeployment, ProdDeployment pOBToProdDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();

			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();

			pOBFromProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", pOBFromProdDeployment);

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setName("TRNS: " + pOBFromProdDeployment.getName());
			lOBDevDeployment.setProdDeploymentId(pOBToProdDeployment.getId());
			Integer lOBDevDeploymentID = addDevDeployment(lOBDevDeployment);

			HashMap lOBMap = new HashMap();
			lOBMap.put("fromProd", pOBFromProdDeployment.getId());
			lOBMap.put("toDev", lOBDevDeploymentID);
			sqlMap.update("transferAllDevOtherTasksTreeToNewDev", lOBMap);

			lOBMap = new HashMap();
			lOBMap.put("fromProd", pOBFromProdDeployment.getId());
			lOBMap.put("toProd", pOBToProdDeployment.getId());
			sqlMap.update("transferAllProdOtherTasksTreeToNewProd", lOBMap);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

	}

	@Override
	public ArrayList refreshBusyComponents() throws Exception {
		ArrayList<String> L = new ArrayList<String>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();
			L = (ArrayList<String>) sqlMap.queryForList("refreshBusyComponents");// ProdDeploymentTree.xml

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;
	}

	@Override
	public ArrayList getBusyComponentsProjects(String pSTComponentName) throws Exception {
		ArrayList L = new ArrayList();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();

			HashMap lOBMap = new HashMap();
			lOBMap.put("component", pSTComponentName);
			L = (ArrayList) sqlMap.queryForList("getBusyComponentsProjects", lOBMap);// ProdDeploymentTree.xml

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;
	}

	public ArrayList getBulkDeploymentDependentProjects(ProdDeployment pOBProdDeployment) throws Exception {
		ArrayList L = new ArrayList();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();

			HashSet<String> lOBSet = new HashSet<String>();

			lOBSet.add(pOBProdDeployment.getName());

			getDependentProjects(pOBProdDeployment, lOBSet);

			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();

			for (Iterator iterator = lOBSet.iterator(); iterator.hasNext();) {
				String lSTName = (String) iterator.next();
				ProdDeployment lOBProdDeployment = new ProdDeployment();
				lOBProdDeployment.setName(lSTName);
				lOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeploymentByName", lOBProdDeployment);
				HashMap lOBMap = new HashMap();
				lOBMap.put("prodid", lOBProdDeployment.getId());
				L.addAll((ArrayList) sqlMap.queryForList("getBulkDeploymentDependentProjectsFormatted", lOBMap));
			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
		}
		return L;
	}

	private ArrayList getDependentProjects(ProdDeployment pOBProdDeployment, HashSet<String> pOBSet) throws Exception {
		ArrayList L = new ArrayList();
		SqlMapClient sqlMap = null;
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();

			L = (ArrayList) sqlMap.queryForList("getBulkDeploymentDependentProjects", pOBProdDeployment);

			for (Iterator iterator = L.iterator(); iterator.hasNext();) {
				ProdDeployment lOBProdDeployment = (ProdDeployment) iterator.next();
				if (!pOBSet.contains(lOBProdDeployment.getName())) {
					pOBSet.add(lOBProdDeployment.getName());
					getDependentProjects(lOBProdDeployment, pOBSet);
				}

			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;
	}

	@Override
	public ArrayList<HashMap> refreshUserList() throws Exception {
		ArrayList<HashMap> L = new ArrayList<HashMap>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();
			L = (ArrayList<HashMap>) sqlMap.queryForList("refreshUserRanks");

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;
	}

	@Override
	public ArrayList<HashMap> refreshUserComponentKnowledge() throws Exception {
		ArrayList<HashMap> L = new ArrayList<HashMap>();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();
			L = (ArrayList<HashMap>) sqlMap.queryForList("refreshUserComponentKnowledge");

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return L;
	}

	@Override
	public String getVersioningWarning(DevDeploymentTree pOBComponent) throws Exception {
		String lSTString = new String();
		SqlMapClient sqlMap = null;
		try {
			checkSessionActive();
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			sqlMap.startTransaction();

			HashMap lOBMap = new HashMap();
			lOBMap.put("component", pOBComponent.getName());

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(pOBComponent.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

			lOBMap.put("ownerid", lOBDevDeployment.getOwnerId());

			DevDeployment lOBDevDeploymentOfLastDeployDateofComponentOfOtherUser = (DevDeployment) sqlMap.queryForObject("getLastDeployedDateOfOtherUserOfComponent", lOBMap, new DevDeployment());

			if (lOBDevDeploymentOfLastDeployDateofComponentOfOtherUser != null) {

				DevDeploymentTree lOBDevDepTreeLastChangedDateOfSessionUserItemOfComponent = (DevDeploymentTree) sqlMap.queryForObject("getLastChangedDateOfSessionUserItemOfComponent", lOBMap,
						new DevDeploymentTree());

				// eger sen, baska bir kullanici ayni componenti deploy ettikten sonra
				// ayni herhangi bir degisiklik yapmadiysan ve simdi degisiklik yapmaya kalkiyorsan,
				// uyari vermeli guncelle diye.
				// null olmasi da senin zaten hicbir degisiklik yapmadigin anlamina geldigi icin
				// onun da uyari vermesi gerekiyor.

				if (lOBDevDepTreeLastChangedDateOfSessionUserItemOfComponent == null
						|| lOBDevDepTreeLastChangedDateOfSessionUserItemOfComponent.getDatetime().before(lOBDevDeploymentOfLastDeployDateofComponentOfOtherUser.getDeployDate())) {
					lSTString = pOBComponent.getName() + " is deployed by " + lOBDevDeploymentOfLastDeployDateofComponentOfOtherUser.getInfoOwnerName() + " at "
							+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(lOBDevDeploymentOfLastDeployDateofComponentOfOtherUser.getDeployDate());
				}

			}

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lSTString;
	}

	@Override
	public ArrayList getProdBulkDeploymentTreeList(ArrayList<HashMap<String, String>> pOBList) throws Exception {
		SqlMapClient sqlMap = null;
		ArrayList lOBList = new ArrayList<String>();
		try {

			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			int[] linProdID = new int[pOBList.size()];
			int i = 0;
			for (Iterator iterator = pOBList.iterator(); iterator.hasNext();) {
				HashMap<String, String> lOBMap = (HashMap<String, String>) iterator.next();
				linProdID[i] = Integer.parseInt(lOBMap.get("prodid"));
				i++;
			}
			HashMap<String, int[]> lOBMap = new HashMap<String, int[]>();
			lOBMap.put("id", linProdID);
			sqlMap.startTransaction();
			lOBList = (ArrayList) sqlMap.queryForList("getDeployedDevDeploymentComponentNameOfProdDeployment", lOBMap);

			sqlMap.commitTransaction();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
		return lOBList;
	}

	private void checkAddAvailability(DevOtherTaskTree pOBOtherTaskTree) throws Exception {
		SqlMapClient sqlMap = null;
		try {

			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(pOBOtherTaskTree.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

			if (lOBDevDeployment.getOwnerId() != getSessionOwnerId()) {
				Exception ex = new Exception("User is not owner of this deployment");
				throw ex;
			}

			if (lOBDevDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Deployment is deployed, operation not performed");
				throw ex;
			}

			ProdDeployment lOBProdDeployment = new ProdDeployment();
			lOBProdDeployment.setId(lOBDevDeployment.getProdDeploymentId());
			lOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", lOBProdDeployment);

			if (lOBProdDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Related production is deployed, operation not performed");
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkUpdateAvailability(DevDeployment pOBDevDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			DevDeployment lOBOldDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", pOBDevDeployment);

			if (lOBOldDevDeployment.getOwnerId() != getSessionOwnerId()) {
				Exception ex = new Exception("User is not owner of this deployment");
				throw ex;
			}

			if (lOBOldDevDeployment.getDeployed() == 1 && pOBDevDeployment.getDeployed() == 1) {
				Exception ex = new Exception("This dev deployment is deployed. Can't do the operation");
				throw ex;
			}

			ProdDeployment lOBProdDeployment = new ProdDeployment();
			lOBProdDeployment.setId(lOBOldDevDeployment.getProdDeploymentId());
			lOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", lOBProdDeployment);

			if (lOBProdDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Related Production is Deployed, can't do operation.");
				throw ex;
			}

			if (!lOBOldDevDeployment.getName().equalsIgnoreCase(pOBDevDeployment.getName())) {
				DevDeployment lOBAlreadyExist = (DevDeployment) sqlMap.queryForObject("getDevDeploymentByName", pOBDevDeployment);
				if (lOBAlreadyExist != null) {
					Exception ex = new Exception("This deployment name already exists in dev. Please change");
					throw ex;
				}
				ProdDeployment lOBAlreadyExist2 = new ProdDeployment();
				lOBAlreadyExist2.setName(pOBDevDeployment.getName());
				lOBAlreadyExist2 = (ProdDeployment) sqlMap.queryForObject("getProdDeploymentByName", lOBAlreadyExist2);
				if (lOBAlreadyExist2 != null) {
					Exception ex = new Exception("This deployment name already exists in prod. Please change");
					throw ex;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkUpdateAvailability(ProdDeployment pOBProdDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			ProdDeployment lOBOldProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", pOBProdDeployment);

			if (pOBProdDeployment.getDeployed() == 1 && lOBOldProdDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Production is already deployed, you can't change");
				throw ex;
			}

			if (lOBOldProdDeployment.getOwnerId() != getSessionOwnerId()) {
				Exception ex = new Exception("User is not owner of this deployment");
				throw ex;
			}

			if (!lOBOldProdDeployment.getName().equalsIgnoreCase(pOBProdDeployment.getName())) {
				ProdDeployment lOBAlreadyExist = (ProdDeployment) sqlMap.queryForObject("getProdDeploymentByName", pOBProdDeployment);
				if (lOBAlreadyExist != null) {
					Exception ex = new Exception("This deployment name already exists in prod. Please change");
					throw ex;
				}
				DevDeployment lOBAlreadyExist2 = new DevDeployment();
				lOBAlreadyExist2.setName(pOBProdDeployment.getName());
				lOBAlreadyExist2 = (DevDeployment) sqlMap.queryForObject("getDevDeploymentByName", lOBAlreadyExist2);
				if (lOBAlreadyExist2 != null) {
					Exception ex = new Exception("This deployment name already exists in dev. Please change");
					throw ex;
				}
			}

			if (lOBOldProdDeployment.getDeployed() == 0 && pOBProdDeployment.getDeployed() == 1) {
				DevDeployment lOBNonDeployed = new DevDeployment();
				lOBNonDeployed.setProdDeploymentId(pOBProdDeployment.getId());
				ArrayList lOBNotDeployedDevDeployments = (ArrayList) sqlMap.queryForList("getNotDeployedDevDeployments", lOBNonDeployed);
				if (lOBNotDeployedDevDeployments.size() > 0) {
					Exception ex = new Exception("You can't set as deployed without setting dev deployments as deployed");
					throw ex;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkAddAvailability(ProdDeployment pOBProdDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			ProdDeployment lOBAlreadyExist = (ProdDeployment) sqlMap.queryForObject("getProdDeploymentByName", pOBProdDeployment);
			if (lOBAlreadyExist != null) {
				Exception ex = new Exception("This prod name already exists. Please change the name");
				throw ex;
			}

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setName(pOBProdDeployment.getName());

			DevDeployment lOBDevAlreadyExist = (DevDeployment) sqlMap.queryForObject("getDevDeploymentByName", lOBDevDeployment);
			if (lOBDevAlreadyExist != null) {
				Exception ex = new Exception("This dev name already exists. Please change the name");
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkAddAvailability(DevDeployment pOBDevDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			ProdDeployment lOBProdDeployment = new ProdDeployment();
			lOBProdDeployment.setId(pOBDevDeployment.getProdDeploymentId());
			lOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", lOBProdDeployment);

			if (lOBProdDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Related production is deployed, operation can't be performed");
				throw ex;
			}

			DevDeployment lOBAlreadyExist = (DevDeployment) sqlMap.queryForObject("getDevDeploymentByName", pOBDevDeployment);
			if (lOBAlreadyExist != null) {
				Exception ex = new Exception("This dev name already exists. Please change the name");
				throw ex;
			}

			ProdDeployment lOBProdSameName = new ProdDeployment();
			lOBProdSameName.setName(pOBDevDeployment.getName());

			ProdDeployment lOBProdAlreadyExist = (ProdDeployment) sqlMap.queryForObject("getProdDeploymentByName", lOBProdSameName);
			if (lOBProdAlreadyExist != null) {
				Exception ex = new Exception("This Prod name already exists. Please change the name");
				throw ex;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkAddAvailability(DevDeploymentTree pOBDevDeploymentTree) throws Exception {
		SqlMapClient sqlMap = null;
		try {

			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(pOBDevDeploymentTree.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

			if (lOBDevDeployment.getOwnerId() != getSessionOwnerId()) {
				Exception ex = new Exception("User is not owner of this deployment");
				throw ex;
			}

			if (lOBDevDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Deployment is deployed, operation not performed");
				throw ex;
			}

			ProdDeployment lOBProdDeployment = new ProdDeployment();
			lOBProdDeployment.setId(lOBDevDeployment.getProdDeploymentId());
			lOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", lOBProdDeployment);

			if (lOBProdDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Related production is deployed, operation not performed");
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkDeleteAvailability(DevDeploymentTree pOBDevDeploymentTree) throws Exception {
		SqlMapClient sqlMap = null;
		try {

			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			DevDeploymentTree lOBDevDeploymentTree = (DevDeploymentTree) sqlMap.queryForObject("getDevDeploymentTree", pOBDevDeploymentTree);

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(lOBDevDeploymentTree.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

			if (lOBDevDeployment.getOwnerId() != getSessionOwnerId()) {
				Exception ex = new Exception("User is not owner of this deployment");
				throw ex;
			}

			if (lOBDevDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Deployment is deployed, operation not performed");
				throw ex;
			}

			ProdDeployment lOBProdDeployment = new ProdDeployment();
			lOBProdDeployment.setId(lOBDevDeployment.getProdDeploymentId());
			lOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", lOBProdDeployment);

			if (lOBProdDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Related production is deployed, operation not performed");
				throw ex;
			}

			if (lOBDevDeploymentTree.getItemType() != 3) {
				Exception ex = new Exception("Please select an item. ( Not a component, or not a type ) ");
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkDeleteAvailability(DevOtherTaskTree pOBDevOtherTaskTree) throws Exception {
		SqlMapClient sqlMap = null;
		try {

			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			// ---

			pOBDevOtherTaskTree = (DevOtherTaskTree) sqlMap.queryForObject("getDevOtherTaskTree", pOBDevOtherTaskTree);

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setId(pOBDevOtherTaskTree.getDevDeploymentId());
			lOBDevDeployment = (DevDeployment) sqlMap.queryForObject("getDevDeployment", lOBDevDeployment);

			if (lOBDevDeployment.getOwnerId() != getSessionOwnerId()) {
				Exception ex = new Exception("User is not owner of this deployment");
				throw ex;
			}

			if (lOBDevDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Deployment is deployed, operation not performed");
				throw ex;
			}

			ProdDeployment lOBProdDeployment = new ProdDeployment();
			lOBProdDeployment.setId(lOBDevDeployment.getProdDeploymentId());
			lOBProdDeployment = (ProdDeployment) sqlMap.queryForObject("getProdDeployment", lOBProdDeployment);

			if (lOBProdDeployment.getDeployed() == 1) {
				Exception ex = new Exception("Related production is deployed, operation not performed");
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkDeleteAvailability(DevDeployment pOBDevDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			checkUpdateAvailability(pOBDevDeployment);

			DevDeploymentTree lOBDevDeploymentTree = new DevDeploymentTree();
			lOBDevDeploymentTree.setDevDeploymentId(pOBDevDeployment.getId());
			ArrayList lOBList = getDevDeploymentTreeList(lOBDevDeploymentTree);
			if (lOBList.size() > 0) {
				Exception ex = new Exception("This deployment has a deployment tree, please delete the tree first");
				throw ex;
			}

			DevOtherTaskTree lOBDevOtherTaskTree = new DevOtherTaskTree();
			lOBDevOtherTaskTree.setDevDeploymentId(pOBDevDeployment.getId());
			lOBList = getDevOtherTaskList(lOBDevOtherTaskTree);
			if (lOBList.size() > 0) {
				Exception ex = new Exception("This deployment has other task tree, please delete the tree first");
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkDeleteAvailability(ProdDeployment pOBProdDeployment) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			checkUpdateAvailability(pOBProdDeployment);

			DevDeployment lOBDevDeployment = new DevDeployment();
			lOBDevDeployment.setProdDeploymentId(pOBProdDeployment.getId());
			ArrayList lOBList = getDevDeployments(lOBDevDeployment);
			if (lOBList.size() > 0) {
				Exception ex = new Exception("This production has undeleted dev deployments, please delete them first");
				throw ex;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}
	}

	private void checkSessionActive() throws Exception {
		if (!this.getThreadLocalRequest().isRequestedSessionIdValid()) {
			Exception ex = new Exception("Your session is expired, please relogin");
			throw ex;
		}
	}

	private HttpSession getSession() {
		return this.getThreadLocalRequest().getSession();
	}

	private int getSessionOwnerId() {
		return ((Integer) getSession().getAttribute("ownerId")).intValue();
	}

	@Override
	public String checkWarningsForDev(DevDeploymentTree lOBDevDeploymentTree) throws Exception {
		SqlMapClient sqlMap = null;
		StringBuffer lOBBuffer = new StringBuffer();
		try {
			String resource = "com/deploymentplan/server/Ibatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			boolean lboDoubleVersionNeeded = false;

			ArrayList lOBList = (ArrayList) sqlMap.queryForList("checkWarningSameComponent", lOBDevDeploymentTree);

			for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
				HashMap lOBMap = (HashMap) iterator.next();
				lOBBuffer.append((String) lOBMap.get("deployment"));
				if (iterator.hasNext()) {
					lOBBuffer.append(", ");
				}
			}
			lOBBuffer.append(" has deployed versions on dev environment.<br><br>");
			lOBList = (ArrayList) sqlMap.queryForList("checkWarningSameItem", lOBDevDeploymentTree);
			if (lOBList.size() > 0) {
				lboDoubleVersionNeeded = true;
				lOBBuffer.append("Specially : ");
			}
			for (Iterator iterator = lOBList.iterator(); iterator.hasNext();) {
				HashMap lOBMap = (HashMap) iterator.next();
				lOBBuffer.append((String) lOBMap.get("item"));
				if (iterator.hasNext()) {
					lOBBuffer.append(", ");
				}
			}
			if (lboDoubleVersionNeeded) {
				lOBBuffer
						.append(" items are already deployed on dev environment. <br><br> 1) Please be sure that these versions are completely deployed <br> 2) Please continue with double version, do prod version first, then updated dev version");
			} else {
				lOBBuffer.append("1) Please be sure that these versions are completely deployed <br> 2) Please be sure that you are deploying over last version.");
			}

			return lOBBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (sqlMap != null) {
				sqlMap.endTransaction();
			}
		}

	}

	@Override
	protected void checkPermutationStrongName() throws SecurityException {
		return;
	}

}
