package com.deploymentplan.domain;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DevDeploymentTree implements IsSerializable {
	int id;
	String name;
	int devDeploymentId;
	int parentId;
	int itemType;
	int deleted;
	int subItemType;
	Date datetime;

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

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

	public int getDevDeploymentId() {
		return devDeploymentId;
	}

	public void setDevDeploymentId(int devDeploymentId) {
		this.devDeploymentId = devDeploymentId;
	}

}
