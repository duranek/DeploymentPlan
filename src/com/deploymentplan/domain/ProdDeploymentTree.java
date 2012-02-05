package com.deploymentplan.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProdDeploymentTree implements IsSerializable {
	int id;
	String name;
	int prodDeploymentId;
	int parentId;
	int itemType;
	int deleted;
	int subItemType;

	public int getSubItemType() {
		return subItemType;
	}

	public void setSubItemType(int subItemType) {
		this.subItemType = subItemType;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getProdDeploymentId() {
		return prodDeploymentId;
	}

	public void setProdDeploymentId(int prodDeploymentId) {
		this.prodDeploymentId = prodDeploymentId;
	}

}
