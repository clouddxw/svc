package com.iresearch.svc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Data;
@Data
public class Brandshow {
	private String	brand	;	
	private String	show	;	
	private String	sponsortype	;	
	private String	photo	;
	private int begindate;
	private int	svc	;//	SVC综合指数
	private int	svcrk	;//	植入节目指数排名
	private int	showval	;//	节目价值指数
	private int	showbdrel	;//	节目品牌相关指数
	private int	brandpro	;//	品牌资产提升度指数 
	private int	rzup	;//	[认知提升度]
	private int	xaup	;//	[喜爱提升度]
	private int	tjup	;//	[推荐提升度]
	private int	rznorm	;//	[认知Norm]
	private int	xanorm	;//	[喜爱Norm]
	private int	tjnorm	;//	[推荐Norm]
	private int	rzupindex	;//	[认知度提升指数]
	private int	xaupindex	;//	[喜爱度提升指数]
	private int	tjupindex	;//	[推荐度提升指数]
	private int	rel	;//	[相关度]
	private int	relnorm	;//	[相关度Norm]
	private int	relindex	;//	[相关度指数]
	private int	rm	;//	[回忆度]
	private int	rmnorm	;//	[回忆度Norm]
	private int	rmindex	;//	[回忆度指数]
	private int	rw	;//	[重复收视度]
	private int	rwnorm	;//	[重复收视度Norm]
	private int	rwindex	;//	[重复收视度指数]
	private int	kbindex	;//	[口碑传播度指数]
	private int	wt	;//	 [收视度]
	private int	wtnorm	;//	[收视度Norm]
	private int	wtindex	;//	收视度指数


}
