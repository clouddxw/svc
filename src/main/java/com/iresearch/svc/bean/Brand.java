package com.iresearch.svc.bean;

import lombok.Data;
@Data
public class Brand {
	private String 	brand	;		
	private String 	logo	;		
	private int 	svcmax	;	//	 SVC综合指数最大值
	private int 	svcavg	;	//	 SVC综合指数平均值
	private int 	showvalmax	;	//	 节目价值指数最大值
	private int 	showvalavg	;	//	 节目价值指数平均值
	private int 	showbdrelmax	;	//	 节目品牌相关指数最大值
	private int 	showbdrelavg	;	//	 节目品牌相关指数平均值
	private int 	brandpromax	;	//	 品牌资产提升度指数最大值
	private int 	brandproavg	;	//	 品牌资产提升度指数平均值 
	private int 	svcmaxrk	;	//	 svc综合指数最大值排名
	private int 	svcavgrk	;	//	 SVC综合指数平均值排名
	

}
