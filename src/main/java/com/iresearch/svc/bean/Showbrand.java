package com.iresearch.svc.bean;
import lombok.Data;
@Data
public class Showbrand {
	private String show;
	private String brand;
	private String sponsortype;
	private String logo;
	private String episodenum;
	private int svc; //SVC综合指数
	private int showval; //节目价值指数
	private int showbdrel; //节目品牌相关指数
	private int brandpro; //品牌资产提升度指数
	private int svcrk; //svc综合指数排名;
	private int rzup;  //认知提升
	private int xaup; //喜爱提升
	private int tjup; //推荐提升
	private int rznorm;
	private int xanorm;
	private int tjnorm;
	private int rzupindex;
	private int xaupindex;
    private int tjupindex;
    private int rel;
    private int relnorm;
    private int relindex;
    private int rm;
    private int rmnorm;
    private int rmindex;
    private int rw;
    private int rwnorm;
    private int rwindex;
    private int kbindex;
    private int wt;
    private int wtnorm;
    private int wtindex;

}
