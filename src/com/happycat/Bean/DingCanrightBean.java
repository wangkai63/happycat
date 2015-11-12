package com.happycat.Bean;

import java.io.Serializable;

public class DingCanrightBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String gname, price, number, gtext;

	public DingCanrightBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DingCanrightBean(String gname, String price, String number,
			String gtext) {
		super();
		this.gname = gname;
		this.price = price;
		this.number = number;
		this.gtext = gtext;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getGtext() {
		return gtext;
	}

	public void setGtext(String gtext) {
		this.gtext = gtext;
	}

}
