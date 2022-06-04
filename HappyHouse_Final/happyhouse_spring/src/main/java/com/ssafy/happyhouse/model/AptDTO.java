package com.ssafy.happyhouse.model;

import java.util.LinkedHashMap;

public class AptDTO {
	private String aptCode;
	private String buildYear;
	private String roadName;
	private String dongName;
	private String dongCode;
	private String aptName;
	private String jibun;
	private String lng;
	private String lat;

	public AptDTO(String aptCode, String buildYear, String roadName, String dongName, String dongCode, String aptName,
			String jibun, String lng, String lat) {
		super();
		this.aptCode = aptCode;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.dongName = dongName;
		this.dongCode = dongCode;
		this.aptName = aptName;
		this.jibun = jibun;
		this.lng = lng;
		this.lat = lat;
	}

	public AptDTO() {
		super();
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "AptDTO [aptCode=" + aptCode + ", buildYear=" + buildYear + ", roadName=" + roadName + ", dongName="
				+ dongName + ", dongCode=" + dongCode + ", aptName=" + aptName + ", jibun=" + jibun + ", lng=" + lng
				+ ", lat=" + lat + "]";
	}

}
