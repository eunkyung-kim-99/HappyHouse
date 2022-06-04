package com.ssafy.happyhouse.model;

public class BoardDTO {
	private String no;
	private String id;
	private String subject;
	private String contents;
	private String regtime;
	private String updatetime;
	private String hit;
	public BoardDTO() {
		super();
	}
	public BoardDTO(String no, String id, String subject, String contents, String regtime, String updatetime,
			String hit) {
		super();
		this.no = no;
		this.id = id;
		this.subject = subject;
		this.contents = contents;
		this.regtime = regtime;
		this.updatetime = updatetime;
		this.hit = hit;
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
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "BoardDTO [no=" + no + ", id=" + id + ", subject=" + subject + ", contents=" + contents + ", regtime="
				+ regtime + ", updatetime=" + updatetime + ", hit=" + hit + "]";
	}

	
}
