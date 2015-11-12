package com.happycat.Bean;

import java.io.Serializable;

public class TuiJianXQBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String mimg;
	private String mname;
	private String mtime;
	private String longtime;
	private String tip;
	private String mprice;
	private int  mid;
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	public String getLongtime() {
		return longtime;
	}
	public void setLongtime(String longtime) {
		this.longtime = longtime;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getMprice() {
		return mprice;
	}
	public void setMprice(String mprice) {
		this.mprice = mprice;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public TuiJianXQBean(String mimg, String mname, String mtime, String longtime, String tip, String mprice, int mid) {
		super();
		this.mimg = mimg;
		this.mname = mname;
		this.mtime = mtime;
		this.longtime = longtime;
		this.tip = tip;
		this.mprice = mprice;
		this.mid = mid;
	}
	

	
	
}
