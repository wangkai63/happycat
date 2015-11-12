package com.happycat.Bean;

import java.io.Serializable;

public class MyOrderBean implements Serializable {
	private int mid;
	private String mname;
	private String gname;
	private String istatus;
	private String gimg;
	private String price;
	private String iPhone;
	private String num;
	private String identifier;

	private String type;


	private int uid;
	private int gid;
	public MyOrderBean(int mid, String mname, String gname, String istatus, String gimg, String price, String iPhone,
			String num, String identifier, String type, int uid, int gid) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.gname = gname;
		this.istatus = istatus;
		this.gimg = gimg;
		this.price = price;
		this.iPhone = iPhone;
		this.num = num;
		this.identifier = identifier;
		this.type = type;
		this.uid = uid;
		this.gid = gid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getIstatus() {
		return istatus;
	}
	public void setIstatus(String istatus) {
		this.istatus = istatus;
	}
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getiPhone() {
		return iPhone;
	}
	public void setiPhone(String iPhone) {
		this.iPhone = iPhone;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	
}
