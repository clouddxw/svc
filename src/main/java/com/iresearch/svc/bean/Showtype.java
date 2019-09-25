package com.iresearch.svc.bean;

import java.util.Date;
import lombok.Data;
@Data
public class Showtype {
	private String show;//节目名称
	private String type; //类型(电视综艺,网络综艺)
	private String platform; //播放平台类型
	private String showlevel;//节目等级
	private String showtype;//节目类型
	private int episode;//期数
	private String begindate;
    
}
