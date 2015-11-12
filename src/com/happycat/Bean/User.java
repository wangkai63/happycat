package com.happycat.Bean;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int uid;
	private String uname;
	private String upassword;
	private String sex;
	private String uphone;
	private String uimg;
	private String uprovince;
	private String ucity;
	private String ucountry;
	private String udetail;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String u_name) {
		this.uname = u_name;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUimg() {
		return uimg;
	}
	public void setUimg(String uimg) {
		this.uimg = uimg;
	}
	public String getUprovince() {
		return uprovince;
	}
	public void setUprovince(String uprovince) {
		this.uprovince = uprovince;
	}
	public String getUcity() {
		return ucity;
	}
	public void setUcity(String ucity) {
		this.ucity = ucity;
	}
	public String getUcountry() {
		return ucountry;
	}
	public void setUcountry(String ucountry) {
		this.ucountry = ucountry;
	}
	public String getUdetail() {
		return udetail;
	}
	public void setUdetail(String udetail) {
		this.udetail = udetail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public User(int uid, String uname, String upassword, String sex, String uphone, String uimg, String uprovince,
			String ucity, String ucountry, String udetail) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upassword = upassword;
		this.sex = sex;
		this.uphone = uphone;
		this.uimg = uimg;
		this.uprovince = uprovince;
		this.ucity = ucity;
		this.ucountry = ucountry;
		this.udetail = udetail;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upassword=" + upassword + ", sex=" + sex + ", uphone="
				+ uphone + ", uimg=" + uimg + ", uprovince=" + uprovince + ", ucity=" + ucity + ", ucountry=" + ucountry
				+ ", udetail=" + udetail + "]";
	}
	
	

}
