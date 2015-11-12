package com.happycat.Bean;

public class VersionBean {
private  int setid;
private String version;
public int getSetid() {
	return setid;
}
public void setSetid(int setid) {
	this.setid = setid;
}
public String getVersion() {
	return version;
}
public void setVersion(String version) {
	this.version = version;
}
public VersionBean(int setid, String version) {
	super();
	this.setid = setid;
	this.version = version;
}
public VersionBean() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "VersionBean [setid=" + setid + ", version=" + version + "]";
}



}

