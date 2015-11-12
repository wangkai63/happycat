package com.happycat.Bean;

import java.io.Serializable;

public class MerchatXqBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String mname;
	private String mimg;
	private String longtime;
	private String mprice;
	private String tip;
	private String mtime;
	private String mphone;
	private String maddress;
	private String mtext;
	private int mid;
	public MerchatXqBean(String mname, String mimg, String longtime, String mprice, String tip, String mtime,
			String mphone, String maddress, String mtext, int mid) {
		super();
		this.mname = mname;
		this.mimg = mimg;
		this.longtime = longtime;
		this.mprice = mprice;
		this.tip = tip;
		this.mtime = mtime;
		this.mphone = mphone;
		this.maddress = maddress;
		this.mtext = mtext;
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	public String getLongtime() {
		return longtime;
	}
	public void setLongtime(String longtime) {
		this.longtime = longtime;
	}
	public String getMprice() {
		return mprice;
	}
	public void setMprice(String mprice) {
		this.mprice = mprice;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public String getMtext() {
		return mtext;
	}
	public void setMtext(String mtext) {
		this.mtext = mtext;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	

	
}
