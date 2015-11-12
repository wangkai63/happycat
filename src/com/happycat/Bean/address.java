package com.happycat.Bean;

import java.io.Serializable;

public class address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int aid,uid;
	private String aprovince,acity,acountry,adetail,aphone;
	public address(int aid, int uid, String aprovince, String acity, String acountry, String adetail, String aphone) {
		super();
		this.aid = aid;
		this.uid = uid;
		this.aprovince = aprovince;
		this.acity = acity;
		this.acountry = acountry;
		this.adetail = adetail;
		this.aphone = aphone;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getAprovince() {
		return aprovince;
	}
	public void setAprovince(String aprovince) {
		this.aprovince = aprovince;
	}
	public String getAcity() {
		return acity;
	}
	public void setAcity(String acity) {
		this.acity = acity;
	}
	public String getAcountry() {
		return acountry;
	}
	public void setAcountry(String acountry) {
		this.acountry = acountry;
	}
	public String getAdetail() {
		return adetail;
	}
	public void setAdetail(String adetail) {
		this.adetail = adetail;
	}
	public String getAphone() {
		return aphone;
	}
	public void setAphone(String aphone) {
		this.aphone = aphone;
	}
	@Override
	public String toString() {
		return "address [aid=" + aid + ", uid=" + uid + ", aprovince=" + aprovince + ", acity=" + acity + ", acountry="
				+ acountry + ", adetail=" + adetail + ", aphone=" + aphone + "]";
	}
	

}
