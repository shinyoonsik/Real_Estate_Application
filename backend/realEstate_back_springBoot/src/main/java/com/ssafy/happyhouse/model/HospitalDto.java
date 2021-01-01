package com.ssafy.happyhouse.model;

public class HospitalDto {
	String city;
	String gugun;
	String hospital;
	String address;
	String code;
	String tel;
	
	public HospitalDto(String city, String gugun, String hospital, String address, String code, String tel) {
		super();
		this.city = city;
		this.gugun = gugun;
		this.hospital = hospital;
		this.address = address;
		this.code = code;
		this.tel = tel;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTel() {
		return tel;
	}
 
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "HospitalDto [city=" + city + ", gugun=" + gugun + ", hospital=" + hospital + ", address=" + address
				+ ", code=" + code + ", tel=" + tel + "]";
	}
	
	
}
