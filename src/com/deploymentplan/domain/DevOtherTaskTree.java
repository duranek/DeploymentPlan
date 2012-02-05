package com.deploymentplan.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DevOtherTaskTree implements IsSerializable {
	int id;
	int parentId;
	String shortDescription;
	String explanation;
	int devDeploymentId;
	int deleted;

	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getDevDeploymentId() {
		return devDeploymentId;
	}
	public void setDevDeploymentId(int devDeploymentId) {
		this.devDeploymentId = devDeploymentId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
