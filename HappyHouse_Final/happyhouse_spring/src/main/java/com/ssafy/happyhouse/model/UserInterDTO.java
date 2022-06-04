package com.ssafy.happyhouse.model;

public class UserInterDTO {
	private String no;
	private String id;
	private String selsidocode;
	private String selguguncode;
	private String seldongcode;
	private String aptName;
	private String img;
	private String roadname;
	private String jibun;
	private String buildyear;
	public UserInterDTO() {
		super();
	}
	public UserInterDTO(String no, String id, String selsidocode, String selguguncode, String seldongcode,
			String aptName, String img, String roadname, String jibun, String buildyear) {
		super();
		this.no = no;
		this.id = id;
		this.selsidocode = selsidocode;
		this.selguguncode = selguguncode;
		this.seldongcode = seldongcode;
		this.aptName = aptName;
		this.img = img;
		this.roadname = roadname;
		this.jibun = jibun;
		this.buildyear = buildyear;
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
	public String getSelsidocode() {
		return selsidocode;
	}
	public void setSelsidocode(String selsidocode) {
		this.selsidocode = selsidocode;
	}
	public String getSelguguncode() {
		return selguguncode;
	}
	public void setSelguguncode(String selguguncode) {
		this.selguguncode = selguguncode;
	}
	public String getSeldongcode() {
		return seldongcode;
	}
	public void setSeldongcode(String seldongcode) {
		this.seldongcode = seldongcode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getRoadname() {
		return roadname;
	}
	public void setRoadname(String roadname) {
		this.roadname = roadname;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getBuildyear() {
		return buildyear;
	}
	public void setBuildyear(String buildyear) {
		this.buildyear = buildyear;
	}
	@Override
	public String toString() {
		return "UserInterDTO [no=" + no + ", id=" + id + ", selsidocode=" + selsidocode + ", selguguncode="
				+ selguguncode + ", seldongcode=" + seldongcode + ", aptName=" + aptName + ", img=" + img
				+ ", roadname=" + roadname + ", jibun=" + jibun + ", buildyear=" + buildyear + "]";
	}
	



}
