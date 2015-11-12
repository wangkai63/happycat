package com.happycat.Bean;

import java.io.Serializable;

public class NightMerchatBean implements Serializable {
private int mid;
private String mname;
private String maddress;
private String mphone;
private String mimg;
private String mtext;
private String mtime;
private String mcoupon;
private String longtime;
private double mprice;
private int tip;
private int oid;
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
public String getMaddress() {
	return maddress;
}
public void setMaddress(String maddresss) {
	this.maddress = maddresss;
}
public String getMphone() {
	return mphone;
}
public void setMphone(String mphone) {
	this.mphone = mphone;
}
public String getMimg() {
	return mimg;
}
public void setMimg(String mimg) {
	this.mimg = mimg;
}
public String getMtext() {
	return mtext;
}
public void setMtext(String mtext) {
	this.mtext = mtext;
}
public String getMtime() {
	return mtime;
}
public void setMtime(String mtime) {
	this.mtime = mtime;
}
public String getMcoupon() {
	return mcoupon;
}
public void setMcoupon(String mcoupon) {
	this.mcoupon = mcoupon;
}
public String getLongtime() {
	return longtime;
}
public void setLongtime(String longtime) {
	this.longtime = longtime;
}
public double getMprice() {
	return mprice;
}
public void setMprice(float mprice) {
	this.mprice = mprice;
}
public int getTip() {
	return tip;
}
public void setTip(int tip) {
	this.tip = tip;
}
public int getOid() {
	return oid;
}
public void setOid(int oid) {
	this.oid = oid;
}
public NightMerchatBean(int mid, String mname, String maddress, String mphone,
		String mimg, String mtext, String mtime, String mcoupon,
		String longtime, double mprice, int tip, int oid) {
	super();
	this.mid = mid;
	this.mname = mname;
	this.maddress = maddress;
	this.mphone = mphone;
	this.mimg = mimg;
	this.mtext = mtext;
	this.mtime = mtime;
	this.mcoupon = mcoupon;
	this.longtime = longtime;
	this.mprice = mprice;
	this.tip = tip;
	this.oid = oid;
}
public NightMerchatBean() {
	super();
}
@Override
public String toString() {
	return "MerchatBean [mid=" + mid + ", mname=" + mname + ", maddress="
			+ maddress + ", mphone=" + mphone + ", mimg=" + mimg + ", mtext="
			+ mtext + ", mtime=" + mtime + ", mcoupon=" + mcoupon
			+ ", longtime=" + longtime + ", mprice=" + mprice + ", tip=" + tip
			+ ", oid=" + oid + "]";
}
}
