package com.iresearch.svc.bean;

import java.sql.Date;
import lombok.Data;
@Data
public class LoginUser {
	private  String name;
	private  String passwd;
	private  String type;
	private  String status;
	private Date enddate;
	private String role;

}
