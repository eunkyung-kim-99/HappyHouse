package com.ssafy.happyhouse.model;

public class CommentsDTO {
	private String no;
	private String id;
	private String content;
	private String communityno;
	private String regtime;
	private String updatetime;
	public CommentsDTO(String no, String id, String content, String communityno, String regtime, String updatetime) {
		super();
		this.no = no;
		this.id = id;
		this.content = content;
		this.communityno = communityno;
		this.regtime = regtime;
	}
	public CommentsDTO() {
		super();
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommunityno() {
		return communityno;
	}
	public void setCommunityno(String communityno) {
		this.communityno = communityno;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "CommentsDTO [no=" + no + ", id=" + id + ", content=" + content + ", communityno=" + communityno
				+ ", regtime=" + regtime + "]";
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	
	
}