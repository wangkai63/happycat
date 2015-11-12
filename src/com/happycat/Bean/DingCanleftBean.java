package com.happycat.Bean;

import java.io.Serializable;

public class DingCanleftBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String gcname;
	private int gcid;

	public DingCanleftBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DingCanleftBean(String gcname, int gcid) {
		super();
		this.gcname = gcname;
		this.gcid = gcid;
	}

	public String getGcname() {
		return gcname;
	}

	public void setGcname(String gcname) {
		this.gcname = gcname;
	}

	public int getGcid() {
		return gcid;
	}

	public void setGcid(int gcid) {
		this.gcid = gcid;
	}

}
