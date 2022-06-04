package com.ssafy.happyhouse.model;

public class BoardDTO {
	private String no;
	private String id;
	private String subject;
	private String contents;
	
	
	public BoardDTO() {
		super();
	}
	public BoardDTO(String no, String id, String subject, String contents) {
		super();
		this.no = no;
		this.id = id;
		this.subject = subject;
		this.contents = contents;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		return "BoardDTO [no=" + no + ", id=" + id + ", subject=" + subject + ", contents=" + contents + "]";
	}
	
	

}
