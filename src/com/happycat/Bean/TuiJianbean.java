package com.happycat.Bean;

import java.io.Serializable;

public class TuiJianbean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String img;
	private String gname;
	private int gid;
	private int mid;

	public TuiJianbean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TuiJianbean(String img, String gname, int gid, int mid) {
		super();
		this.img = img;
		this.gname = gname;
		this.gid = gid;
		this.mid = mid;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
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

}