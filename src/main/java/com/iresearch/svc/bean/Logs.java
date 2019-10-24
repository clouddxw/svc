package com.iresearch.svc.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Logs {

    private Date date;
    private String project; //项目名称
    private String model; //模块
    private String name;//操作人
    private String method;//具体路径
    private String parameter;//参数
    private Integer consuming;//操作时长
    private String ip;
    private Date time;
}
