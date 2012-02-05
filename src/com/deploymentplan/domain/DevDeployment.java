package com.deploymentplan.domain;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DevDeployment implements IsSerializable {
	int id;
	String name;
	int ownerId;
	int prodDeploymentId;
	int deployed;
	int deleted;
	String infoOwnerName;
	String date;
	Date deployDate;

	public Date getDeployDate() {
		return deployDate;
	}

	public void setDeployDate(Date deployDate) {
		this.deployDate = deployDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInfoOwnerName() {
		return infoOwnerName;
	}

	public void setInfoOwnerName(String infoOwnerName) {
		this.infoOwnerName = infoOwnerName;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
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

	public int getDeployed() {
		return deployed;
	}

	public void setDeployed(int deployed) {
		this.deployed = deployed;
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
