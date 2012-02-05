package com.deploymentplan.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OtherTaskTypes implements IsSerializable{
	private int id;
	private String name;
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
