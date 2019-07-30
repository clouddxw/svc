package com.iresearch.svc.controller;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.mapper.svc.AllshowMapper;
import com.iresearch.svc.mapper.svc.IndexMapper;
import com.iresearch.svc.outbean.Allshow;
import com.iresearch.svc.redis.RedisUtils;
import com.iresearch.svc.utils.Normdefv;
import com.iresearch.svc.utils.Normmap;
import org.apache.commons.collections.CollectionUtils;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequestMapping("/basic/allshow")
@Controller
public class Allshow_BasicController {
        private static Logger logger = LoggerFactory.getLogger(Allshow_BasicController.class);
        @Autowired(required = false)
        private AllshowMapper allshowmapper;
        //@Resource
        //private  RedisTemplate redisTemplate;
        @Autowired(required = false)
        private IndexMapper indexmapper;
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
            session.setAttribute("shownorm", norm);

            List<Show> allshowmax=new ArrayList<>();
            String allshowkey="basic_allshow_"+Normmap.normmap(norm);
            boolean asexist = redisUtils.hasKey(allshowkey);
            if (asexist) {
                allshowmax =(List<Show>)redisUtils.get(allshowkey);
            } else {
                allshowmax = allshowmapper.getAllshow_Basic(norm);
                redisUtils.set(allshowkey,  allshowmax,3600);
            }

            List<Show> allshowavg= new ArrayList<>();
            CollectionUtils.addAll(allshowavg, new Object[allshowmax.size()]);
            Collections.copy(allshowavg, allshowmax);
            Collections.sort(allshowavg,new Comparator () {
                @Override
                public int compare(Object o1, Object o2) {
                    // TODO Auto-generated method stub
                    Show e1=(Show) o1;
                    Show e2=(Show) o2;
                    return e1.getSvcavgrk() - e2.getSvcavgrk();
                }
            });

            // 最大值数据加载
            
            

            String shownamemax=allshowmax.get(0).getShow();
            String showtypemaxkey="basic_showtype_"+shownamemax;
            Showtype showtypemax;
            if(redisUtils.hasKey(showtypemaxkey)){
                showtypemax=(Showtype)redisUtils.get(showtypemaxkey);
            }else{
                showtypemax=allshowmapper.getShowtype(shownamemax);
            }
            
            String showbrandmaxkey="basic_showbrand_"+shownamemax+Normmap.normmap(norm);
            List<Showbrand> showbrandmax=new ArrayList<>();
            if(redisUtils.hasKey(showbrandmaxkey)){
                showbrandmax=(List<Showbrand>)redisUtils.get(showbrandmaxkey);
            }else{
                showbrandmax=allshowmapper.getShowbrand_Basic(shownamemax,norm);
            }
            
            String bdindexmaxkey="basic_bdindex_"+shownamemax;
            String wbindexmaxkey="basic_wbindex_"+shownamemax;
            String wxindexmaxkey="basic_wxindex_"+shownamemax;
            String tgimaxkey="basic_showtgi_"+shownamemax;

            List<Bdindex> bdindexmax=new ArrayList<>();
            List<Wbindex> wbindexmax=new ArrayList<>();
            List<Wxindex> wxindexmax=new ArrayList<>();
            Showtgi showtgimax;
            
            if(redisUtils.hasKey(bdindexmaxkey)){
                bdindexmax=(List<Bdindex>)redisUtils.get(bdindexmaxkey);
            }else{
                bdindexmax=allshowmapper.getBdindex(shownamemax);
                redisUtils.set(bdindexmaxkey,bdindexmax,3600);
            }

            if(redisUtils.hasKey(wbindexmaxkey)){
                wbindexmax=(List<Wbindex>)redisUtils.get(wbindexmaxkey);
            }else{
                wbindexmax=allshowmapper.getWbindex(shownamemax);
                redisUtils.set(wbindexmaxkey,wbindexmax,3600);
            }

            if(redisUtils.hasKey(wxindexmaxkey)){
                wxindexmax=(List<Wxindex>)redisUtils.get(wxindexmaxkey);
            }else{
                wxindexmax=allshowmapper.getWxindex(shownamemax);
                redisUtils.set(wxindexmaxkey,wxindexmax,3600);
            }
            if(redisUtils.hasKey(tgimaxkey)){
                showtgimax=(Showtgi)redisUtils.get(tgimaxkey);
            }else{
                showtgimax=allshowmapper.getShowtgi(shownamemax);
                redisUtils.set(tgimaxkey,showtgimax,3600);
            }


            // 加权值数据加载



            String shownameavg=allshowavg.get(0).getShow();
            String showtypeavgkey="basic_showtype_"+shownameavg;
            Showtype showtypeavg;
            if(redisUtils.hasKey(showtypeavgkey)){
                showtypeavg=(Showtype)redisUtils.get(showtypeavgkey);
            }else{
                showtypeavg=allshowmapper.getShowtype(shownameavg);
            }

            String showbrandavgkey="basic_showbrand_"+shownameavg+Normmap.normmap(norm);
            List<Showbrand> showbrandavg=new ArrayList<>();
            if(redisUtils.hasKey(showbrandavgkey)){
                showbrandavg=(List<Showbrand>)redisUtils.get(showbrandavgkey);
            }else{
                showbrandavg=allshowmapper.getShowbrand_Basic(shownameavg,norm);
            }

            String bdindexavgkey="basic_bdindex_"+shownameavg;
            String wbindexavgkey="basic_wbindex_"+shownameavg;
            String wxindexavgkey="basic_wxindex_"+shownameavg;
            String tgiavgkey="basic_showtgi_"+shownameavg;

            List<Bdindex> bdindexavg=new ArrayList<>();
            List<Wbindex> wbindexavg=new ArrayList<>();
            List<Wxindex> wxindexavg=new ArrayList<>();
            Showtgi showtgiavg;

            if(redisUtils.hasKey(bdindexavgkey)){
                bdindexavg=(List<Bdindex>)redisUtils.get(bdindexavgkey);
            }else{
                bdindexavg=allshowmapper.getBdindex(shownameavg);
                redisUtils.set(bdindexavgkey,bdindexavg,3600);
            }

            if(redisUtils.hasKey(wbindexavgkey)){
                wbindexavg=(List<Wbindex>)redisUtils.get(wbindexavgkey);
            }else{
                wbindexavg=allshowmapper.getWbindex(shownameavg);
                redisUtils.set(wbindexavgkey,wbindexavg,3600);
            }

            if(redisUtils.hasKey(wxindexavgkey)){
                wxindexavg=(List<Wxindex>)redisUtils.get(wxindexavgkey);
            }else{
                wxindexavg=allshowmapper.getWxindex(shownameavg);
                redisUtils.set(wxindexavgkey,wxindexavg,3600);
            }

            if(redisUtils.hasKey(tgiavgkey)){
                showtgiavg=(Showtgi)redisUtils.get(tgiavgkey);
            }else{
                showtgiavg=allshowmapper.getShowtgi(shownameavg);
                redisUtils.set(tgiavgkey,showtgiavg,3600);
            }

            String customshowkey="basic_customshow";
            String custombrandkey="basic_custombrand";
            List<Showname> customshow=new ArrayList<>();
            List<Brandname> custombrand=new ArrayList<>();
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

            List<Object> out=new ArrayList<>();
            Allshow allshow=new Allshow();
            
            allshow.setAllshowmax(allshowmax);
            allshow.setShowtypemax(showtypemax);
            allshow.setShowbrandmax(showbrandmax);
            allshow.setBdindexmax(bdindexmax);
            allshow.setWbindexmax(wbindexmax);
            allshow.setWxindexmax(wxindexmax);
            allshow.setShowtgimax(showtgimax);

            //allshow.setAllshowavg(allshowavg);
            allshow.setShowtypeavg(showtypeavg);
            allshow.setShowbrandavg(showbrandavg);
            allshow.setBdindexavg(bdindexavg);
            allshow.setWbindexavg(wbindexavg);
            allshow.setWxindexavg(wxindexavg);
            allshow.setShowtgiavg(showtgiavg);
            allshow.setCustombrand(custombrand);
            allshow.setCustomshow(customshow);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目排名与详情|打开页面"+"|@|";
            logger.info(slog);

            return allshow;
        }

        @RequestMapping("getnorm")
        @ResponseBody
        public Object getnorm(HttpServletRequest request) throws UnsupportedEncodingException{
            HttpSession session=request.getSession();
            //String normcondi=new String(request.getParameter("normcondi").getBytes("ISO-8859-1"),"UTF-8");
            //String normcondil[]= normcondi.split(";");
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
            logger.info(playplat+showtype+showlevel+sponsortype+brandtype+sex+agegroup+citylevel+strbgdate+strenddate);
            //System.out.println(playplat+showtype+showlevel+sponsortype+brandtype+sex+agegroup+citylevel+strbgdate+strenddate);

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
            session.setAttribute("shownorm", norm);

            List<Show> allshowmax=new ArrayList<>();
            String allshowkey="basic_allshow_"+Normmap.normmap(norm);
            boolean asexist = redisUtils.hasKey(allshowkey);
            if (asexist) {
                allshowmax =(List<Show>)redisUtils.get(allshowkey);
            } else {
                allshowmax = allshowmapper.getAllshow_Basic(norm);
                redisUtils.set(allshowkey,  allshowmax,3600);
            }

            List<Show> allshowavg= new ArrayList<>();
            CollectionUtils.addAll(allshowavg, new Object[allshowmax.size()]);
            Collections.copy(allshowavg, allshowmax);
            Collections.sort(allshowavg,new Comparator () {
                @Override
                public int compare(Object o1, Object o2) {
                    // TODO Auto-generated method stub
                    Show e1=(Show) o1;
                    Show e2=(Show) o2;
                    return e1.getSvcavgrk() - e2.getSvcavgrk();
                }
            });

            // 最大值数据加载



            String shownamemax=allshowmax.get(0).getShow();
            System.out.println(shownamemax);
            String showtypemaxkey="basic_showtype_"+shownamemax;
            Showtype showtypemax;
            if(redisUtils.hasKey(showtypemaxkey)){
                showtypemax=(Showtype)redisUtils.get(showtypemaxkey);
            }else{
                showtypemax=allshowmapper.getShowtype(shownamemax);
            }

            String showbrandmaxkey="basic_showbrand_"+shownamemax+Normmap.normmap(norm);
            List<Showbrand> showbrandmax=new ArrayList<>();
            if(redisUtils.hasKey(showbrandmaxkey)){
                showbrandmax=(List<Showbrand>)redisUtils.get(showbrandmaxkey);
            }else{
                showbrandmax=allshowmapper.getShowbrand_Basic(shownamemax,norm);
            }

            String bdindexmaxkey="basic_bdindex_"+shownamemax;
            String wbindexmaxkey="basic_wbindex_"+shownamemax;
            String wxindexmaxkey="basic_wxindex_"+shownamemax;
            String tgimaxkey="basic_showtgi_"+shownamemax;

            List<Bdindex> bdindexmax=new ArrayList<>();
            List<Wbindex> wbindexmax=new ArrayList<>();
            List<Wxindex> wxindexmax=new ArrayList<>();
            Showtgi showtgimax;

            if(redisUtils.hasKey(bdindexmaxkey)){
                bdindexmax=(List<Bdindex>)redisUtils.get(bdindexmaxkey);
            }else{
                bdindexmax=allshowmapper.getBdindex(shownamemax);
                redisUtils.set(bdindexmaxkey,bdindexmax,3600);
            }

            if(redisUtils.hasKey(wbindexmaxkey)){
                wbindexmax=(List<Wbindex>)redisUtils.get(wbindexmaxkey);
            }else{
                wbindexmax=allshowmapper.getWbindex(shownamemax);
                redisUtils.set(wbindexmaxkey,wbindexmax,3600);
            }

            if(redisUtils.hasKey(wxindexmaxkey)){
                wxindexmax=(List<Wxindex>)redisUtils.get(wxindexmaxkey);
            }else{
                wxindexmax=allshowmapper.getWxindex(shownamemax);
                redisUtils.set(wxindexmaxkey,wxindexmax,3600);
            }
            if(redisUtils.hasKey(tgimaxkey)){
                showtgimax=(Showtgi)redisUtils.get(tgimaxkey);
            }else{
                showtgimax=allshowmapper.getShowtgi(shownamemax);
                redisUtils.set(tgimaxkey,showtgimax,3600);
            }


            // 加权值数据加载



            String shownameavg=allshowavg.get(0).getShow();
            String showtypeavgkey="basic_showtype_"+shownameavg;
            Showtype showtypeavg;
            if(redisUtils.hasKey(showtypeavgkey)){
                showtypeavg=(Showtype)redisUtils.get(showtypeavgkey);
            }else{
                showtypeavg=allshowmapper.getShowtype(shownameavg);
            }

            String showbrandavgkey="basic_showbrand_"+shownameavg+Normmap.normmap(norm);
            List<Showbrand> showbrandavg=new ArrayList<>();
            if(redisUtils.hasKey(showbrandavgkey)){
                showbrandavg=(List<Showbrand>)redisUtils.get(showbrandavgkey);
            }else{
                showbrandavg=allshowmapper.getShowbrand_Basic(shownameavg,norm);
            }

            String bdindexavgkey="basic_bdindex_"+shownameavg;
            String wbindexavgkey="basic_wbindex_"+shownameavg;
            String wxindexavgkey="basic_wxindex_"+shownameavg;
            String tgiavgkey="basic_showtgi_"+shownameavg;

            List<Bdindex> bdindexavg=new ArrayList<>();
            List<Wbindex> wbindexavg=new ArrayList<>();
            List<Wxindex> wxindexavg=new ArrayList<>();
            Showtgi showtgiavg;

            if(redisUtils.hasKey(bdindexavgkey)){
                bdindexavg=(List<Bdindex>)redisUtils.get(bdindexavgkey);
            }else{
                bdindexavg=allshowmapper.getBdindex(shownameavg);
                redisUtils.set(bdindexavgkey,bdindexavg,3600);
            }

            if(redisUtils.hasKey(wbindexavgkey)){
                wbindexavg=(List<Wbindex>)redisUtils.get(wbindexavgkey);
            }else{
                wbindexavg=allshowmapper.getWbindex(shownameavg);
                redisUtils.set(wbindexavgkey,wbindexavg,3600);
            }

            if(redisUtils.hasKey(wxindexavgkey)){
                wxindexavg=(List<Wxindex>)redisUtils.get(wxindexavgkey);
            }else{
                wxindexavg=allshowmapper.getWxindex(shownameavg);
                redisUtils.set(wxindexavgkey,wxindexavg,3600);
            }

            if(redisUtils.hasKey(tgiavgkey)){
                showtgiavg=(Showtgi)redisUtils.get(tgiavgkey);
            }else{
                showtgiavg=allshowmapper.getShowtgi(shownameavg);
                redisUtils.set(tgiavgkey,showtgiavg,3600);
            }

            String customshowkey="basic_customshow";
            String custombrandkey="basic_custombrand";
            List<Showname> customshow=new ArrayList<>();
            List<Brandname> custombrand=new ArrayList<>();
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



            List<Object> out=new ArrayList<>();
            Allshow allshow=new Allshow();

            allshow.setAllshowmax(allshowmax);
            allshow.setShowtypemax(showtypemax);
            allshow.setShowbrandmax(showbrandmax);
            allshow.setBdindexmax(bdindexmax);
            allshow.setWbindexmax(wbindexmax);
            allshow.setWxindexmax(wxindexmax);
            allshow.setShowtgimax(showtgimax);

            //allshow.setAllshowavg(allshowavg);
            allshow.setShowtypeavg(showtypeavg);
            allshow.setShowbrandavg(showbrandavg);
            allshow.setBdindexavg(bdindexavg);
            allshow.setWbindexavg(wbindexavg);
            allshow.setWxindexavg(wxindexavg);
            allshow.setShowtgiavg(showtgiavg);
            allshow.setCustomshow(customshow);
            allshow.setCustombrand(custombrand);
            allshow.setBgdate(strbgdate);
            allshow.setEnddate(strenddate);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目排名与详情|Norm筛选"+"|@|";
            logger.info(slog);

            return allshow;


        }
        
        
        @RequestMapping("getshow")
        @ResponseBody
        public Object getshow(HttpServletRequest request){
            HttpSession session=request.getSession(); 
            String showname=request.getParameter("showname");
            System.out.println(showname);
            Norm norm=(Norm)session.getAttribute("shownorm");
            
            String showbrandkey="basic_showbrand_"+showname+Normmap.normmap(norm);
            String showtypekey="basic_showtype_"+showname;
            String bdindexkey="basic_bdindex_"+showname;
            String wbindexkey="basic_wbindex_"+showname;
            String wxindexkey="basic_wxindex_"+showname;
            String tgikey="basic_showtgi_"+showname;

            List<Showbrand> showbrand=new ArrayList<>();
            Showtype showtype;
            List<Bdindex> bdindex=new ArrayList<>();
            List<Wbindex> wbindex=new ArrayList<>();
            List<Wxindex> wxindex=new ArrayList<>();
            Showtgi showtgi;
            
            if(redisUtils.hasKey(showbrandkey)){
                showbrand=(List<Showbrand>)redisUtils.get(showbrandkey);
            }else{
                showbrand=allshowmapper.getShowbrand_Basic(showname,norm);
                redisUtils.set(showbrandkey,showbrand,3600);
            }
            if(redisUtils.hasKey(showtypekey)){
                showtype=(Showtype)redisUtils.get(showtypekey);
            }else{
                showtype=allshowmapper.getShowtype(showname);
                redisUtils.set(showtypekey,showtype,3600);
            }
            if(redisUtils.hasKey(bdindexkey)){
                bdindex=(List<Bdindex>)redisUtils.get(bdindexkey);
            }else{
                bdindex=allshowmapper.getBdindex(showname);
                redisUtils.set(bdindexkey,bdindex,3600);
            }
            if(redisUtils.hasKey(wbindexkey)){
                wbindex=(List<Wbindex>)redisUtils.get(wbindexkey);
            }else{
                wbindex=allshowmapper.getWbindex(showname);
                redisUtils.set(wbindexkey,wbindex,3600);
            }

            if(redisUtils.hasKey(wxindexkey)){
                wxindex=(List<Wxindex>)redisUtils.get(wxindexkey);
            }else{
                wxindex=allshowmapper.getWxindex(showname);
                redisUtils.set(wxindexkey,wxindex,3600);
            }

            if(redisUtils.hasKey(tgikey)){
                showtgi=(Showtgi)redisUtils.get(tgikey);
            }else{
                showtgi=allshowmapper.getShowtgi(showname);
                redisUtils.set(tgikey,showtgi,3600);
            }

            Allshow allshow=new Allshow();
            allshow.setShowtype(showtype);
            allshow.setShowbrand(showbrand);
            allshow.setBdindex(bdindex);
            allshow.setWbindex(wbindex);
            allshow.setWxindex(wxindex);
            allshow.setShowtgi(showtgi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目排名与详情|节目搜索"+"|@|";
            logger.info(slog);

            return allshow;

            
        }  





}

