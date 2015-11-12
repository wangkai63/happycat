package com.happycat.Bean;

import java.io.Serializable;

public class goodsclassify implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int gcid;
	private int mid;
	@Override
	public String toString() {
		return "goodsclassify [gcid=" + gcid + ", mid=" + mid + ", gcname=" + gcname + "]";
	}
	public int getGcid() {
		return gcid;
	}
	public void setGcid(int gcid) {
		this.gcid = gcid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getGcname() {
		return gcname;
	}
	public void setGcname(String gcname) {
		this.gcname = gcname;
	}
	String gcname;
	public goodsclassify(int gcid, int mid, String gcname) {
		super();
		this.gcid = gcid;
		this.mid = mid;
		this.gcname = gcname;
	}
}
