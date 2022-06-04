package com.ssafy.happyhouse.model;

public class FoodDTO {
	private String title;
	private String link;
	private String point;
	private String etc;
	private String img;
	private String review_count;

	public FoodDTO() {
		super();
	}

	public FoodDTO(String title, String link, String point, String etc, String img, String review_count) {
		super();
		this.title = title;
		this.link = link;
		this.point = point;
		this.etc = etc;
		this.img = img;
		this.review_count = review_count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getReview_count() {
		return review_count;
	}

	public void setReview_count(String review_count) {
		this.review_count = review_count;
	}

	@Override
	public String toString() {
		return "FoodDTO [title=" + title + ", link=" + link + ", point=" + point + ", etc=" + etc + ", img=" + img
				+ ", review_count=" + review_count + "]";
	}

}
