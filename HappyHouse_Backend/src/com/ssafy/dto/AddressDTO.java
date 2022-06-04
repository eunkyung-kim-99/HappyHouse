package com.ssafy.dto;

public class AddressDTO {
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	
	
	
	public AddressDTO(String sidoName, String gugunName, String dongName) {
		super();
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	@Override
	public String toString() {
		return "AddressDTO [sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName=" + dongName + "]";
	}
	
	
	

}
