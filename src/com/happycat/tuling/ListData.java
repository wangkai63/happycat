package com.happycat.tuling;

//把传回的数据封装一下
public class ListData {
	public static final int SEND=1;//发送
	public static final int RECEIVER=2;//接收
	private String content;
    private int flag;//标识符,用来显示是发送还是接收
    private String time;//消息的时间
    
	public ListData(String content,int flag,String time) {
		setContent(content);
		setFlag(flag);
		setTime(time);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
