package com.ssafy.happyhouse.model;

public class CommunityImageDTO {
	String cimno;
	String no;
	String path;
	String name;
	public CommunityImageDTO(String cimno, String no, String path, String name) {
		super();
		this.cimno = cimno;
		this.no = no;
		this.path = path;
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "CommunityImageDTO [cimno=" + cimno + ", no=" + no + ", path=" + path + ", name=" + name + "]";
	}


	public CommunityImageDTO() {
		super();
	}
	public String getCimno() {
		return cimno;
	}
	public void setCimno(String cimno) {
		this.cimno = cimno;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
