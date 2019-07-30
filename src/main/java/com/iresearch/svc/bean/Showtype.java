package com.iresearch.svc.bean;


import java.util.Date;

public class Showtype {
	private String show;//节目名称
	private String type; //类型(电视综艺,网络综艺)
	private String platform; //播放平台类型
	private String showlevel;//节目等级
	private String showtype;//节目类型
	private int episode;//期数
	private String begindate;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getShowlevel() {
		return showlevel;
	}
	public void setShowlevel(String showlevel) {
		this.showlevel = showlevel;
	}
	public String getShowtype() {
		return showtype;
	}
	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}
	public int getEpisode() {
		return episode;
	}
	public void setEpisode(int episode) {
		this.episode = episode;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
    
}
