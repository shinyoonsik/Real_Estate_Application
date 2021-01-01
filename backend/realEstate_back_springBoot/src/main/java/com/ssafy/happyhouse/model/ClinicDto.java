package com.ssafy.happyhouse.model;

public class ClinicDto {
	private String possibility;
	private String sido_name;
	private String gugun_name;
	private String addr;
	private String medical_name;
	private String weekday;
	private String phone;
	
	public ClinicDto() {
		super();
	}
	
	public String getSido_name() {
		return sido_name;
	}

	public void setSido_name(String sido_name) {
		this.sido_name = sido_name;
	}

	public String getGugun_name() {
		return gugun_name;
	}

	public void setGugun_name(String gugun_name) {
		this.gugun_name = gugun_name;
	}

	public String getMedical_name() {
		return medical_name;
	}

	public void setMedical_name(String medical_name) {
		this.medical_name = medical_name;
	}

	public String getPossibility() {
		return possibility;
	}
	public void setPossibility(String possibility) {
		this.possibility = possibility;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
