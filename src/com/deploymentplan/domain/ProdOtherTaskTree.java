package com.deploymentplan.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProdOtherTaskTree implements IsSerializable {
	int id;
	int parentId;
	String shortDescription;
	String explanation;
	int deleted;
	int prodDeploymentId;
	int devOtherTaskTreeId;
	


	public int getDevOtherTaskTreeId() {
		return devOtherTaskTreeId;
	}
	public void setDevOtherTaskTreeId(int devOtherTaskTreeId) {
		this.devOtherTaskTreeId = devOtherTaskTreeId;
	}
	public int getProdDeploymentId() {
		return prodDeploymentId;
	}
	public void setProdDeploymentId(int prodDeploymentId) {
		this.prodDeploymentId = prodDeploymentId;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
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
