package com.deploymentplan.domain;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProdDeployment implements IsSerializable{
	private int id;
	private String name;
	private int deployed;
	private int deleted;
	private int ownerId;
	private String infoOwnerName;
	private String date;
	private int cebflowId;
	private Date deployDate;
	private int prodStatus;
	public int getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(int prodStatus) {
		this.prodStatus = prodStatus;
	}
	public Date getDeployDate() {
		return deployDate;
	}
	public void setDeployDate(Date deployDate) {
		this.deployDate = deployDate;
	}
	public int getCebflowId() {
		return cebflowId;
	}
	public void setCebflowId(int cebflowId) {
		this.cebflowId = cebflowId;
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
	private transient String[] keyList;
	public String[] getKeyList() {
		return keyList;
	}
	public void setKeyList(String[] keyList) {
		this.keyList = keyList;
	}
	public int getDeployed() {
		return deployed;
	}
	public void setDeployed(int deployed) {
		this.deployed = deployed;
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

}
