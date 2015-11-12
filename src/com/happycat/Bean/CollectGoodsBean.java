package com.happycat.Bean;

import java.io.Serializable;

public class CollectGoodsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ntime;
	private String price;
	private String gimg;
	private String gname;
	private String mimg;
	private String mname;
	private String tip;
	private String longtime;
	private String mprice;
	private String mtime;
	private int mid;
	public CollectGoodsBean(String ntime, String price, String gimg,
			String gname, String mimg, String mname, String tip,
			String longtime, String mprice, String mtime, int mid) {
		super();
		this.ntime = ntime;
		this.price = price;
		this.gimg = gimg;
		this.gname = gname;
		this.mimg = mimg;
		this.mname = mname;
		this.tip = tip;
		this.longtime = longtime;
		this.mprice = mprice;
		this.mtime = mtime;
		this.mid = mid;
	}
	public String getNtime() {
		return ntime;
	}
	public void setNtime(String ntime) {
		this.ntime = ntime;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
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
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	

}
