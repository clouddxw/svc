package com.iresearch.svc.utils;

import com.iresearch.svc.bean.Norm;

public class Normmap {
    public static String playplatm(String str){
        String out="p1_";
        out+=str.replace("网络平台","0").replace("电视平台","1");
        return out;
    }

    public static String showtypem(String str){
        String out="p2_";
        out+=str.replace("谈话/脱口秀类","0").replace("生活观察类","1").
                replace("综合游戏类","2").replace("才艺竞演类","3").
                replace("文化类","4").replace("美食类","5").
                replace("亲子/儿童互动类","6").replace("明星竞演类","7").
                replace("喜剧类","8").replace("创意类","9").replace("其他","10");
        return out;
    }

    public static String showlevelm(String str){
        String out="p3_";
        out+=str.replace("S+级","0").replace("S级","1").replace("A级","2").
                 replace("B级","3").replace("C级","4");
        return out;
    }

    public static String spontypem(String str){
        String out="p4_";
        out+=str.replace("总冠名","0").replace("其他","1");
        return out;
    }

    public static String brandtypem(String str){
        String out="p5_";
        out+=str.replace("快速消费品","0").replace("互联网应用","1").replace("耐用消费品","2").
                 replace("汽车","3").replace("手机","4").replace("其他","5");
        return out;
    }

    public static String sexm(String str){
        String out="p6_";
        out+=str.replace("男","0").replace("女","1");
        return out;
    }
    public static String agegroupm(String str){
        String out="p7_";
        out+=str.replace("18岁以下","0").replace("18-24岁","1").replace("25-34岁","2").
                 replace("35-50岁","3").replace("50岁以上","4");
        return out;
    }

    public static String citylevelm(String str){
        String out="p8_";
        out+=str.replace("一线城市","0").replace("二线城市","1").replace("三线城市及以下","2");
        return out;
    }

    public static String normmap(Norm norm){
        String out="";
        out+=playplatm(norm.getType())+showtypem(norm.getShowtype())+showlevelm(norm.getShowlevel()) +spontypem(norm.getSponsortype())
                +brandtypem(norm.getBrandtype())+sexm(norm.getSex())+agegroupm(norm.getAgegroup())+citylevelm(norm.getCitylevel())
                +norm.getBegindate().toString()+norm.getEnddate().toString();
        return out;
    }
}
