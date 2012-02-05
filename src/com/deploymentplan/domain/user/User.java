package com.deploymentplan.domain.user;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable{
	private int id;
	private String name;
	private String password;
	
	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
