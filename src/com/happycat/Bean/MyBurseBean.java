package com.happycat.Bean;

import java.io.Serializable;

public class MyBurseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String uimg;
	private String sum;
	public String getUimg() {
		return uimg;
	}
	public void setUimg(String uimg) {
		this.uimg = uimg;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public MyBurseBean(String uimg, String sum) {
		super();
		this.uimg = uimg;
		this.sum = sum;
	}

}
