package com.ssafy.happyhouse.model;

import java.util.LinkedHashMap;

public class AptDTO {
	private String aptName;
	private String lat;
	private String lng;
	private String dealAmount;
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private String area;

	public AptDTO() {

	}
	
	public AptDTO(LinkedHashMap<String, String> list) {
		super();
		this.aptName = list.get("aptName");
		this.lat = list.get("lat");
		this.lng = list.get("lng");
		this.dealAmount = list.get("dealAmount");
		this.dealYear = list.get("dealYear");
		this.dealMonth = list.get("dealMonth");
		this.dealDay = list.get("dealDay");
		this.area = list.get("area");
	}
	public AptDTO(String aptName, String lat, String lng, String dealAmount, String dealYear, String dealMonth,
			String dealDay, String area) {
		super();
		this.aptName = aptName;
		this.lat = lat;
		this.lng = lng;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getDealDay() {
		return dealDay;
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "AptDTO [aptName=" + aptName + ", lat=" + lat + ", lng=" + lng + ", dealAmount=" + dealAmount
				+ ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area
				+ "]";
	}

}
