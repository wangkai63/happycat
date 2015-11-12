package com.happycat.Bean;
import java.io.Serializable;
public class Goods implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int	 id;
private String gname;
private int mid;
private double price;
private int number;
private String gtext;
private  String gcoupon;
private int gcid;
String gimg;
private int gnum;
public Goods(){}
public Goods(int id, String gname, int mid, double price, int number, String gtext, String gcoupon, int gcid,
		String gimg, int gnum) {
	super();
	this.id = id;
	this.gname = gname;
	this.mid = mid;
	this.price = price;
	this.number = number;
	this.gtext = gtext;
	this.gcoupon = gcoupon;
	this.gcid = gcid;
	this.gimg = gimg;
	this.gnum = gnum;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getGname() {
	return gname;
}
public void setGname(String gname) {
	this.gname = gname;
}
public int getMid() {
	return mid;
}
public void setMid(int mid) {
	this.mid = mid;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public String getGtext() {
	return gtext;
}
public void setGtext(String gtext) {
	this.gtext = gtext;
}
public String getGcoupon() {
	return gcoupon;
}
public void setGcoupon(String gcoupon) {
	this.gcoupon = gcoupon;
}
public int getGcid() {
	return gcid;
}
public void setGcid(int gcid) {
	this.gcid = gcid;
}
public String getGimg() {
	return gimg;
}
public void setGimg(String gimg) {
	this.gimg = gimg;
}
public int getGnum() {
	return gnum;
}
public void setGnum(int gnum) {
	this.gnum = gnum;
}
@Override
public String toString() {
	return "Goods [id=" + id + ", gname=" + gname + ", mid=" + mid + ", price=" + price + ", number=" + number
			+ ", gtext=" + gtext + ", gcoupon=" + gcoupon + ", gcid=" + gcid + ", gimg=" + gimg + ", gnum=" + gnum
			+ "]";
}


}
