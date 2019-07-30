package com.iresearch.svc.bean;

import java.sql.Date;

public class Filter {
	private String showtype;
	private String showlevel;
	private String type;
	private String brandtype;
	private String sponsortype;
	private Date begindate;
	private Date enddate;
	public String getShowtype() {
		return showtype;
	}
	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}
	public String getShowlevel() {
		return showlevel;
	}
	public void setShowlevel(String showlevel) {
		this.showlevel = showlevel;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrandtype() {
		return brandtype;
	}
	public void setBrandtype(String brandtype) {
		this.brandtype = brandtype;
	}
	public String getSponsortype() {
		return sponsortype;
	}
	public void setSponsortype(String sponsortype) {
		this.sponsortype = sponsortype;
	}
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	
	

}
