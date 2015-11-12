package com.happycat.Bean;

import java.io.Serializable;

public class MyShouYeBean implements Serializable {
	private int uid;
	private String uname;
	private String sex;
	private String uimg;
	private String uphone;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUimg() {
		return uimg;
	}
	public void setUimg(String uimg) {
		this.uimg = uimg;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public MyShouYeBean(int uid, String uname, String sex, String uimg, String uphone) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.sex = sex;
		this.uimg = uimg;
		this.uphone = uphone;
	}

	


}
