package com.iresearch.svc.bean;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
