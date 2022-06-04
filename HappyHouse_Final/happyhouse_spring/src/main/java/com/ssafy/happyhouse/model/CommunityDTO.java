package com.ssafy.happyhouse.model;

public class CommunityDTO {
	private String no;
	private String id;
	private String subject;
	private String content;
	private String sidoname;
	private String gugunname;
	private String dongname;
	private String keyword;
	private String regtime;
	private String updatetime;
	private String hit;
	public CommunityDTO(String no, String id, String subject, String content, String sidoname, String gugunname,
			String dongname, String keyword, String regtime, String updatetime, String hit) {
		super();
		this.no = no;
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.sidoname = sidoname;
		this.gugunname = gugunname;
		this.dongname = dongname;
		this.keyword = keyword;
		this.regtime = regtime;
		this.updatetime = updatetime;
		this.hit = hit;
	}
	public CommunityDTO() {
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSidoname() {
		return sidoname;
	}
	public void setSidoname(String sidoname) {
		this.sidoname = sidoname;
	}
	public String getGugunname() {
		return gugunname;
	}
	public void setGugunname(String gugunname) {
		this.gugunname = gugunname;
	}
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		this.dongname = dongname;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
		return "CommunityDTO [no=" + no + ", id=" + id + ", subject=" + subject + ", content=" + content + ", sidoname="
				+ sidoname + ", gugunname=" + gugunname + ", dongname=" + dongname + ", keyword=" + keyword
				+ ", regtime=" + regtime + ", updatetime=" + updatetime + ", hit=" + hit + "]";
	}
	
	
	
	
}