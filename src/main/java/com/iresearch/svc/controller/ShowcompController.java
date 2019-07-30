package com.iresearch.svc.controller;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.mapper.svc.AllbrandMapper;
import com.iresearch.svc.mapper.svc.AllshowMapper;
import com.iresearch.svc.mapper.svc.ShowcompMapper;
import com.iresearch.svc.outbean.Showcomp;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/showcomp")
@Controller
public class ShowcompController {
        Logger logger = LoggerFactory.getLogger(ShowcompController.class);
        @Autowired(required = false)
        private AllbrandMapper allbrandmapper;
        @Autowired(required = false)
        private AllshowMapper allshowmapper;
        @Autowired(required = false)
        private ShowcompMapper showcompmapper;
        //@Resource
        //private  RedisTemplate redisTemplate;
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



        @GetMapping("get1st")
        @ResponseBody
        public Object get1st(HttpServletRequest request) {
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
            session.setAttribute("showcompnorm", norm);


            String shownlistkey="allshowname_"+Normmap.normmap(norm);
            String brandnlistkey="allbrandname_"+Normmap.normmap(norm);
            List<Showname> shownlist=new ArrayList<>();
            List<Brandname> brandnlist=new ArrayList<>();
            if(redisUtils.hasKey(shownlistkey)){
                shownlist=(List<Showname>)redisUtils.get(shownlistkey);
            }else{
                shownlist=showcompmapper.getLshowname(norm);
                redisUtils.set(shownlistkey,shownlist,3600);
            }
            if(redisUtils.hasKey(brandnlistkey)){
                brandnlist=(List<Brandname>)redisUtils.get(brandnlistkey);
            }else{
                brandnlist=showcompmapper.getLbrandname(norm);
                redisUtils.set(brandnlistkey,brandnlist,3600);
            }

            Showcomp showcomp=new Showcomp();
            showcomp.setBrandnlist(brandnlist);
            showcomp.setShownlist(shownlist);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|打开页面"+"|@|";
            logger.info(slog);

            return showcomp;
        }

        @RequestMapping("getnorm")
        @ResponseBody
        public Object getnorm(HttpServletRequest request) throws UnsupportedEncodingException{
            HttpSession session=request.getSession();
            String playplat=request.getParameter("playplat");
            String showtype=request.getParameter("showtype");
            String showlevel=request.getParameter("showlevel");
            String sponsortype=request.getParameter("spontype");
            String brandtype=request.getParameter("brandtype");
            String sex=request.getParameter("sex");
            String agegroup=request.getParameter("agegroup");
            String citylevel=request.getParameter("citylevel");
            String strbgdate=request.getParameter("bgdate");
            String strenddate=request.getParameter("enddaten");
            System.out.println(playplat+showtype+showlevel+sponsortype+brandtype+sex+agegroup+citylevel+strbgdate+strenddate);

            java.sql.Date begindate = java.sql.Date.valueOf(strbgdate);
            java.sql.Date enddate = java.sql.Date.valueOf(strenddate);
            Norm norm=new Norm();
            norm.setType(playplat);
            norm.setShowtype(showtype);
            norm.setShowlevel(showlevel);
            norm.setSponsortype(sponsortype);
            norm.setBrandtype(brandtype);
            norm.setSex(sex);
            norm.setAgegroup(agegroup);
            norm.setCitylevel(citylevel);
            norm.setBegindate(begindate);
            norm.setEnddate(enddate);
            session.setAttribute("showcompnorm", norm);

            String shownlistkey="allshowname_"+Normmap.normmap(norm);
            String brandnlistkey="allbrandname_"+Normmap.normmap(norm);
            List<Showname> shownlist=new ArrayList<>();
            List<Brandname> brandnlist=new ArrayList<>();
            if(redisUtils.hasKey(shownlistkey)){
                shownlist=(List<Showname>)redisUtils.get(shownlistkey);
            }else{
                shownlist=showcompmapper.getLshowname(norm);
                redisUtils.set(shownlistkey,shownlist,3600);
            }
            if(redisUtils.hasKey(brandnlistkey)){
                brandnlist=(List<Brandname>)redisUtils.get(brandnlistkey);
            }else{
                brandnlist=showcompmapper.getLbrandname(norm);
                redisUtils.set(brandnlistkey,brandnlist,3600);
            }

            Showcomp showcomp=new Showcomp();
            showcomp.setBgdate(strbgdate);
            showcomp.setEnddate(strenddate);
            showcomp.setBrandnlist(brandnlist);
            showcomp.setShownlist(shownlist);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|Norm筛选"+"|@|";
            logger.info(slog);

            return showcomp;


        }
        
        
        @RequestMapping("getshow")
        @ResponseBody
        public Object getshow(HttpServletRequest request){
            HttpSession session=request.getSession(); 
            String showname=request.getParameter("showname");
            System.out.println(showname);
            Norm norm=(Norm)session.getAttribute("showcompnorm");
            String oneshowkey="oneshow_"+showname+Normmap.normmap(norm);
            String showtypekey="showtype_"+showname;
            Show oneshow;
            Showtype showtype;
            if(redisUtils.hasKey(oneshowkey)){
                oneshow=(Show)redisUtils.get(oneshowkey);
            }else{
                oneshow=showcompmapper.getOneshow(showname,norm);
                redisUtils.set(oneshowkey,oneshow,3600);
            }
            if(redisUtils.hasKey(showtypekey)){
                showtype=(Showtype)redisUtils.get(showtypekey);
            }else{
                showtype=allshowmapper.getShowtype(showname);
                redisUtils.set(showtypekey,showtype,3600);
            }
            
            Showcomp showcomp=new Showcomp();
            showcomp.setShowtype(showtype);
            showcomp.setOneshow(oneshow);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|节目搜索"+"|@|";
            logger.info(slog);

            return showcomp;

            
        }

        @RequestMapping("getbrand")
        @ResponseBody
        public Object getbrand(HttpServletRequest request){
            String brandname=request.getParameter("brandname");
            HttpSession session=request.getSession();
            Norm norm=(Norm)session.getAttribute("showcompnorm");

            String brandcompkey="brandcomp_"+brandname+Normmap.normmap(norm);
            String brandshowkey="brandshow_"+brandname+Normmap.normmap(norm);

            Brandcomp brandcomp;
            List<Brandshow> brandshow=new ArrayList<>();
            if(redisUtils.hasKey(brandcompkey)){
                brandcomp=(Brandcomp)redisUtils.get(brandcompkey);
            }else{
                brandcomp=showcompmapper.getBrandcomp(brandname,norm);
                redisUtils.set(brandcompkey,brandcomp,3600);
            }
            if(redisUtils.hasKey(brandshowkey)){
                brandshow=(List<Brandshow>)redisUtils.get(brandshowkey);
            }else{
                brandshow=allbrandmapper.getBrandshow(brandname,norm);
                redisUtils.set(brandshowkey,brandshow,3600);
            }

            String t1showname=brandshow.get(0).getShow();
            String showtypekey="showtype_"+t1showname;
            Showtype showtype;
            if(redisUtils.hasKey(showtypekey)){
                showtype=(Showtype)redisUtils.get(showtypekey);
            }else{
                showtype=allshowmapper.getShowtype(t1showname);
                redisUtils.set(showtypekey,showtype,3600);
            }

            Showcomp showcomp=new Showcomp();
            showcomp.setBrandcomp(brandcomp);
            showcomp.setBrandshow(brandshow);
            showcomp.setShowtype(showtype);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|品牌搜索"+"|@|";
            logger.info(slog);

            return showcomp;

        }

    @RequestMapping("getshowtype")
    @ResponseBody
    public Object getshowtype(HttpServletRequest request){
        HttpSession session=request.getSession();
        String showname=request.getParameter("showname");
        String showtypekey="showtype_"+showname;
        Showtype showtype;
        if(redisUtils.hasKey(showtypekey)){
            showtype=(Showtype)redisUtils.get(showtypekey);
        }else{
            showtype=allshowmapper.getShowtype(showname);
            redisUtils.set(showtypekey,showtype,3600);
        }
        Showcomp showcomp=new Showcomp();
        showcomp.setShowtype(showtype);
        String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|查看节目详情"+"|@|";
        logger.info(slog);
        return showcomp;
    }


}

