package com.ssafy.happyhouse.model;

public class UserDTO {
	private String id;
	private String password;
	private String name;
	private String email;
	private String phonenumber;
	private String img;

	public UserDTO(String id, String password, String name, String email, String phonenumber, String img) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.img = img;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", phonenumber="
				+ phonenumber + ", img=" + img + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}



	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public UserDTO() {
		super();
	}

}
