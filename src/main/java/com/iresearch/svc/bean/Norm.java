package com.iresearch.svc.bean;

import java.sql.Date;

import lombok.Data;
@Data
public class Norm {
	private String sex;
	private String agegroup;
	private String citylevel;
	private String showtype;
	private String showlevel;
	private String type;
	private String brandtype;
	private String sponsortype;
	private Date begindate;
	private Date enddate;

	
}
