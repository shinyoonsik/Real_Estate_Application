package com.ssafy.happyhouse.model;

public class MemberDto {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String birth;
	private String phone;
	private String interestLocation;
	
	public MemberDto(String id, String pw, String name, String email, String birth, String phone,
			String interestLocation) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.birth = birth;
		this.phone = phone;
		this.interestLocation = interestLocation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getInterestLocation() {
		return interestLocation;
	}
	public void setInterestLocation(String interestLocation) {
		this.interestLocation = interestLocation;
	}
	
	@Override
	public String toString() {
		return "Member [usr=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", birth=" + birth
				+ ", phone=" + phone + ", interestLocation=" + interestLocation + "]";
	}
	
}
