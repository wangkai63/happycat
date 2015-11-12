package com.happycat.Bean;

import java.io.Serializable;

public class Lunbobean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String img;	
	private int mid;
	private String mname;
	private String longtime;
	private String mprice;
	private String tip;
	private String mimg;
	private String mtime;
	public Lunbobean(String img, int mid, String mname, String longtime, String mprice, String tip, String mimg,
			String mtime) {
		super();
		this.img = img;
		this.mid = mid;
		this.mname = mname;
		this.longtime = longtime;
		this.mprice = mprice;
		this.tip = tip;
		this.mimg = mimg;
		this.mtime = mtime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	
}