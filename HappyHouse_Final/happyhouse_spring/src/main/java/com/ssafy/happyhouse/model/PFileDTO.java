package com.ssafy.happyhouse.model;
//롬복? 이란걸 찾아보면 편하대요
public class PFileDTO {

	String id;
	String path;
	String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public PFileDTO( String path, String name) {
		super();
		this.path = path;
		this.name = name;
	}
	public PFileDTO() {
		super();
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
