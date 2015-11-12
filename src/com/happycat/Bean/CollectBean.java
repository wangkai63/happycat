package com.happycat.Bean;

import java.io.Serializable;

import android.R.integer;

public class CollectBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ntime;
	private String maddress;
	private String mimg;
	private String mname;
	private String tip;
	private String longtime;
	private String mprice;
	private String mtime;
	private String mphone;
	private int mid;
	public CollectBean(String ntime, String maddress, String mimg,
			String mname, String tip, String longtime, String mprice,
			String mtime, String mphone, int mid) {
		super();
		this.ntime = ntime;
		this.maddress = maddress;
		this.mimg = mimg;
		this.mname = mname;
		this.tip = tip;
		this.longtime = longtime;
		this.mprice = mprice;
		this.mtime = mtime;
		this.mphone = mphone;
		this.mid = mid;
	}
	public String getNtime() {
		return ntime;
	}
	public void setNtime(String ntime) {
		this.ntime = ntime;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
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
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
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
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}


}
