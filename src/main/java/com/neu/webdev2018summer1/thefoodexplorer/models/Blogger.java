package com.neu.webdev2018summer1.thefoodexplorer.models;

import javax.persistence.Entity;

@Entity
public class Blogger extends User {
	private String bloggerId;

	public String getBloggerId() {
		return bloggerId;
	}

	public void setBloggerId(String bloggerId) {
		this.bloggerId = bloggerId;
	}

}
