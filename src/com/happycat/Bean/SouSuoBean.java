package com.happycat.Bean;

import java.io.Serializable;

public class SouSuoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String gtext;
	private String longtime;
	private String mname;
	private String gname;
	private String maddress;
	private String gimg;
	private int gid;
	private int mid;
	private String mprice;
	private String tip;
	private String mimg;
	private String mtime;
	int pageNow;
	public SouSuoBean(String gtext, String longtime, String mname, String gname, String maddress, String gimg, int gid,
			int mid, String mprice, String tip, String mimg, String mtime, int pageNow) {
		super();
		this.gtext = gtext;
		this.longtime = longtime;
		this.mname = mname;
		this.gname = gname;
		this.maddress = maddress;
		this.gimg = gimg;
		this.gid = gid;
		this.mid = mid;
		this.mprice = mprice;
		this.tip = tip;
		this.mimg = mimg;
		this.mtime = mtime;
		this.pageNow = pageNow;
	}
	public String getGtext() {
		return gtext;
	}
	public void setGtext(String gtext) {
		this.gtext = gtext;
	}
	public String getLongtime() {
		return longtime;
	}
	public void setLongtime(String longtime) {
		this.longtime = longtime;
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
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
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
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	


	
}
