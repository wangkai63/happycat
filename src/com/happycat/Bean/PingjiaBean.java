package com.happycat.Bean;

import java.io.Serializable;

public class PingjiaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String uname;
	private String ttime;
	private String cimg;
	private String ttext;
	public PingjiaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PingjiaBean(String uname, String ttime, String cimg, String ttext) {
		super();
		this.uname = uname;
		this.ttime = ttime;
		this.cimg = cimg;
		this.ttext = ttext;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getTtime() {
		return ttime;
	}
	public void setTtime(String ttime) {
		this.ttime = ttime;
	}
	public String getCimg() {
		return cimg;
	}
	public void setCimg(String cimg) {
		this.cimg = cimg;
	}
	public String getTtext() {
		return ttext;
	}
	public void setTtext(String ttext) {
		this.ttext = ttext;
	}

}
