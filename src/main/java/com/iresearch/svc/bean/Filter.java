package com.iresearch.svc.bean;

import java.sql.Date;
import lombok.Data;
@Data
public class Filter {
	private String showtype;
	private String showlevel;
	private String type;
	private String brandtype;
	private String sponsortype;
	private Date begindate;
	private Date enddate;

}
