package com.cg.iter.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	private int HouseNo;
	private String City;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(int houseNo, String city) {
		super();
		HouseNo = houseNo;
		City = city;
	}
	@Override
	public String toString() {
		return "Address [HouseNo=" + HouseNo + ", City=" + City + "]";
	}
	public int getHouseNo() {
		return HouseNo;
	}
	public void setHouseNo(int houseNo) {
		HouseNo = houseNo;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	
}
