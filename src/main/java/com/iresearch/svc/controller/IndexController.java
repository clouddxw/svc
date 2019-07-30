package com.iresearch.svc.controller;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.mapper.svc.IndexMapper;
import com.iresearch.svc.mapper.svc.ShowcompMapper;
import com.iresearch.svc.outbean.Index;
import com.iresearch.svc.redis.RedisUtils;
import com.iresearch.svc.utils.Normdefv;
import com.iresearch.svc.utils.Normmap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/index")
@Controller
public class IndexController {
        Logger logger = LoggerFactory.getLogger(IndexController.class);
        @Autowired(required = false)
        private IndexMapper indexmapper;
        @Autowired(required = false)
        private ShowcompMapper showcompmapper;
        @Autowired
        private  RedisUtils redisUtils;
        private  String playplatdefv=Normdefv.playplat;
        private  String showtypedefv=Normdefv.showtype;
        private  String showleveldefv=Normdefv.showlevel;
        private  String sponsortypedefv=Normdefv.sponsortype;
        private  String brandtypedefv=Normdefv.brandtype;
        private  String sexdefv=Normdefv.sex;
        private  String agegroupdefv=Normdefv.agegroup;
        private  String cityleveldefv=Normdefv.citylevel;
        private  java.sql.Date begindatedefv=Normdefv.begindate;
        private  java.sql.Date enddatedefv=Normdefv.enddate;



        @GetMapping("getData")
        @ResponseBody
        public Object getData(HttpServletRequest request) {
            HttpSession session=request.getSession();
            Norm norm = new Norm();
            norm.setType(playplatdefv);
            norm.setShowtype(showtypedefv);
            norm.setShowlevel(showleveldefv);
            norm.setSponsortype(sponsortypedefv);
            norm.setBrandtype(brandtypedefv);
            norm.setSex(sexdefv);
            norm.setAgegroup(agegroupdefv);
            norm.setCitylevel(cityleveldefv);
            norm.setBegindate(begindatedefv);
            norm.setEnddate(enddatedefv);
            session.setAttribute("indexnorm", norm);

            //session.setAttribute("ip",request.getRemoteAddr());
            String newskey="index_news";
            String customshowkey="customshow";
            String custombrandkey="custombrand";

            List<News> newslist=new ArrayList<>();
            List<Showname> customshow=new ArrayList<>();
            List<Brandname> custombrand=new ArrayList<>();

            if(redisUtils.hasKey(newskey)){
                newslist=(List<News>)redisUtils.get(newskey);
            }else{
                newslist=indexmapper.getNews();
                redisUtils.set(newskey,newslist,3600);
            }
            if(redisUtils.hasKey(customshowkey)){
                customshow=(List<Showname>)redisUtils.get(customshowkey);
            }else{
                customshow=indexmapper.getCustomshow();
                redisUtils.set(customshowkey,customshow,3600);
            }
            if(redisUtils.hasKey(custombrandkey)){
                custombrand=(List<Brandname>)redisUtils.get(custombrandkey);
            }else{
                custombrand=indexmapper.getCustombrand();
                redisUtils.set(custombrandkey,custombrand,3600);
            }

            String allshowrkkey="allshowrk_"+Normmap.normmap(norm);
            List<Showname> allshow=new ArrayList<>();
            if(redisUtils.hasKey(allshowrkkey)){
                allshow=(List<Showname>)redisUtils.get(allshowrkkey);
            }else{
                allshow=indexmapper.getLibshowname();
                redisUtils.set(allshowrkkey,allshow,3600);
            }

            Index index=new Index();
            index.setAllshow(allshow);
            index.setCustombrand(custombrand);
            index.setCustomshow(customshow);
            index.setNewslist(newslist);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|首页|打开页面"+"|@|";
            logger.info(slog);

            return index;

        }





}

