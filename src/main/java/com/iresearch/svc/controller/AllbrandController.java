package com.iresearch.svc.controller;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.mapper.svc.AllbrandMapper;
import com.iresearch.svc.mapper.svc.AllshowMapper;
import com.iresearch.svc.mapper.svc.IndexMapper;
import com.iresearch.svc.mapper.svc.ShowcompMapper;
import com.iresearch.svc.outbean.Allbrand;
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
import java.util.*;

@RequestMapping("/allbrand")
@Controller
public class AllbrandController {
        Logger logger = LoggerFactory.getLogger(AllbrandController.class);
        @Autowired(required = false)
        private AllbrandMapper allbrandmapper;
        @Autowired(required = false)
        private AllshowMapper allshowmapper;
        @Autowired(required = false)
        private ShowcompMapper showcompmapper;
        @Autowired(required = false)
        private IndexMapper indexmapper;
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
            session.setAttribute("brandnorm", norm);

            List<Brand> allbrandmax=new ArrayList<>();
            String allbrandkey="allbrand_"+Normmap.normmap(norm);
            boolean asexist = redisUtils.hasKey(allbrandkey);
            if (asexist) {
                allbrandmax =(List<Brand>)redisUtils.get(allbrandkey);
            } else {
                allbrandmax = allbrandmapper.getAllbrand(norm);
                redisUtils.set(allbrandkey,  allbrandmax,3600);
            }

            List<Brand> allbrandavg= new ArrayList<>();
            CollectionUtils.addAll(allbrandavg, new Object[allbrandmax.size()]);
            Collections.copy(allbrandavg, allbrandmax);
            Collections.sort(allbrandavg,new Comparator () {
                @Override
                public int compare(Object o1, Object o2) {
                    // TODO Auto-generated method stub
                    Brand e1=(Brand) o1;
                    Brand e2=(Brand) o2;
                    return e1.getSvcavgrk() - e2.getSvcavgrk();
                }
            });
            String allshowrkkey="allshowrk_"+Normmap.normmap(norm);
            List<Showname> allshowrk=new ArrayList<>();
            if(redisUtils.hasKey(allshowrkkey)){
                allshowrk=(List<Showname>)redisUtils.get(allshowrkkey);
            }else{
                allshowrk=showcompmapper.getLshowname(norm);
                redisUtils.set(allshowrkkey,allshowrk,3600);
            }




            // 最大值数据加载

            String brandnamemax=allbrandmax.get(0).getBrand();
            String brandtypemaxkey="brandtype_"+brandnamemax;
            Brandtype brandtypemax;
            if(redisUtils.hasKey(brandtypemaxkey)){
                brandtypemax=(Brandtype)redisUtils.get(brandtypemaxkey);
            }else{
                brandtypemax=allbrandmapper.getBrandtype(brandnamemax);
                redisUtils.set(brandtypemaxkey,brandtypemax,3600);
            }

            String brandshowmaxkey="brandshow_"+brandnamemax+Normmap.normmap(norm);
            List<Brandshow> brandshowmax=new ArrayList<>();
            if(redisUtils.hasKey(brandshowmaxkey)){
                brandshowmax=(List<Brandshow>)redisUtils.get(brandshowmaxkey);
            }else{
                brandshowmax=allbrandmapper.getBrandshow(brandnamemax,norm);
                redisUtils.set(brandshowmaxkey,brandshowmax,3600);
            }

            String brandtrendmaxkey="brandtrend_"+brandnamemax+Normmap.normmap(norm);;
            List<Brandtrend> brandtrendmax;
            if(redisUtils.hasKey(brandtrendmaxkey)){
                brandtrendmax=(List<Brandtrend>)redisUtils.get(brandtrendmaxkey);
            }else{
                brandtrendmax=allbrandmapper.getBrandtrend(brandnamemax,norm);
                redisUtils.set(brandtrendmaxkey,brandtrendmax,3600);
            }

            String t1shownamemax=brandshowmax.get(0).getShow();
            String showtypemaxkey="showtype_"+t1shownamemax;
            String bdindexmaxkey="bdindex_"+t1shownamemax;
            String wbindexmaxkey="wbindex_"+t1shownamemax;
            String wxindexmaxkey="wxindex_"+t1shownamemax;
            String tgimaxkey="showtgi_"+t1shownamemax;

            Showtype t1showtypemax;
            List<Bdindex> bdindexmax=new ArrayList<>();
            List<Wbindex> wbindexmax=new ArrayList<>();
            List<Wxindex> wxindexmax=new ArrayList<>();
            Showtgi showtgimax;

            
            if(redisUtils.hasKey(showtypemaxkey)){
                t1showtypemax=(Showtype)redisUtils.get(showtypemaxkey);
            }else{
                t1showtypemax=allshowmapper.getShowtype(t1shownamemax);
            }

            if(redisUtils.hasKey(bdindexmaxkey)){
                bdindexmax=(List<Bdindex>)redisUtils.get(bdindexmaxkey);
            }else{
                bdindexmax=allshowmapper.getBdindex(t1shownamemax);
                redisUtils.set(bdindexmaxkey,bdindexmax,3600);
            }

            if(redisUtils.hasKey(wbindexmaxkey)){
                wbindexmax=(List<Wbindex>)redisUtils.get(wbindexmaxkey);
            }else{
                wbindexmax=allshowmapper.getWbindex(t1shownamemax);
                redisUtils.set(wbindexmaxkey,wbindexmax,3600);
            }

            if(redisUtils.hasKey(wxindexmaxkey)){
                wxindexmax=(List<Wxindex>)redisUtils.get(wxindexmaxkey);
            }else{
                wxindexmax=allshowmapper.getWxindex(t1shownamemax);
                redisUtils.set(wxindexmaxkey,wxindexmax,3600);
            }
            if(redisUtils.hasKey(tgimaxkey)){
                showtgimax=(Showtgi)redisUtils.get(tgimaxkey);
            }else{
                showtgimax=allshowmapper.getShowtgi(t1shownamemax);
                redisUtils.set(tgimaxkey,showtgimax,3600);
            }
            
            

            // 加权值数据加载

            String brandnameavg=allbrandavg.get(0).getBrand();
            String brandtypeavgkey="brandtype_"+brandnameavg;
            Brandtype brandtypeavg;
            if(redisUtils.hasKey(brandtypeavgkey)){
                brandtypeavg=(Brandtype)redisUtils.get(brandtypeavgkey);
            }else{
                brandtypeavg=allbrandmapper.getBrandtype(brandnameavg);
                redisUtils.set(brandtypeavgkey,brandtypeavg,3600);
            }

            String brandshowavgkey="brandshow_"+brandnameavg+Normmap.normmap(norm);
            List<Brandshow> brandshowavg=new ArrayList<>();
            if(redisUtils.hasKey(brandshowavgkey)){
                brandshowavg=(List<Brandshow>)redisUtils.get(brandshowavgkey);
            }else{
                brandshowavg=allbrandmapper.getBrandshow(brandnameavg,norm);
                redisUtils.set(brandshowavgkey,brandshowavg,3600);
            }

            String brandtrendavgkey="brandtrend_"+brandnameavg+Normmap.normmap(norm);;
            List<Brandtrend> brandtrendavg;
            if(redisUtils.hasKey(brandtrendavgkey)){
                brandtrendavg=(List<Brandtrend>)redisUtils.get(brandtrendavgkey);
            }else{
                brandtrendavg=allbrandmapper.getBrandtrend(brandnameavg,norm);
                redisUtils.set(brandtrendavgkey,brandtrendavg,3600);
            }

            String t1shownameavg=brandshowavg.get(0).getShow();
            String showtypeavgkey="showtype_"+t1shownameavg;
            String bdindexavgkey="bdindex_"+t1shownameavg;
            String wbindexavgkey="wbindex_"+t1shownameavg;
            String wxindexavgkey="wxindex_"+t1shownameavg;
            String tgiavgkey="showtgi_"+t1shownameavg;

            Showtype t1showtypeavg;
            List<Bdindex> bdindexavg=new ArrayList<>();
            List<Wbindex> wbindexavg=new ArrayList<>();
            List<Wxindex> wxindexavg=new ArrayList<>();
            Showtgi showtgiavg;


            if(redisUtils.hasKey(showtypeavgkey)){
                t1showtypeavg=(Showtype)redisUtils.get(showtypeavgkey);
            }else{
                t1showtypeavg=allshowmapper.getShowtype(t1shownameavg);
            }

            if(redisUtils.hasKey(bdindexavgkey)){
                bdindexavg=(List<Bdindex>)redisUtils.get(bdindexavgkey);
            }else{
                bdindexavg=allshowmapper.getBdindex(t1shownameavg);
                redisUtils.set(bdindexavgkey,bdindexavg,3600);
            }

            if(redisUtils.hasKey(wbindexavgkey)){
                wbindexavg=(List<Wbindex>)redisUtils.get(wbindexavgkey);
            }else{
                wbindexavg=allshowmapper.getWbindex(t1shownameavg);
                redisUtils.set(wbindexavgkey,wbindexavg,3600);
            }

            if(redisUtils.hasKey(wxindexavgkey)){
                wxindexavg=(List<Wxindex>)redisUtils.get(wxindexavgkey);
            }else{
                wxindexavg=allshowmapper.getWxindex(t1shownameavg);
                redisUtils.set(wxindexavgkey,wxindexavg,3600);
            }
            if(redisUtils.hasKey(tgiavgkey)){
                showtgiavg=(Showtgi)redisUtils.get(tgiavgkey);
            }else{
                showtgiavg=allshowmapper.getShowtgi(t1shownameavg);
                redisUtils.set(tgiavgkey,showtgiavg,3600);
            }

            String customshowkey="customshow";
            String custombrandkey="custombrand";
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


            
            
            Allbrand allbrand=new Allbrand();
            allbrand.setAllshowrk(allshowrk);
            allbrand.setAllbrandmax(allbrandmax);
            allbrand.setBrandtypemax(brandtypemax);
            allbrand.setT1showtypemax(t1showtypemax);
            allbrand.setBrandshowmax(brandshowmax);
            allbrand.setBrandtrendmax(brandtrendmax);
            allbrand.setBdindexmax(bdindexmax);
            allbrand.setWbindexmax(wbindexmax);
            allbrand.setWxindexmax(wxindexmax);
            allbrand.setShowtgimax(showtgimax);
            allbrand.setAllbrandavg(allbrandavg);
            allbrand.setBrandtypeavg(brandtypeavg);
            allbrand.setT1showtypeavg(t1showtypeavg);
            allbrand.setBrandshowavg(brandshowavg);
            allbrand.setBrandtrendavg(brandtrendavg);
            allbrand.setBdindexavg(bdindexavg);
            allbrand.setWbindexavg(wbindexavg);
            allbrand.setWxindexavg(wxindexavg);
            allbrand.setShowtgiavg(showtgiavg);
            allbrand.setCustombrand(custombrand);
            allbrand.setCustomshow(customshow);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|品牌排名与详情|打开页面"+"|@|";
            logger.info(slog);
            return allbrand;


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
            session.setAttribute("brandnorm", norm);

            List<Brand> allbrandmax=new ArrayList<>();
            String allbrandkey="allbrand_"+Normmap.normmap(norm);
            boolean asexist = redisUtils.hasKey(allbrandkey);
            if (asexist) {
                allbrandmax =(List<Brand>)redisUtils.get(allbrandkey);
            } else {
                allbrandmax = allbrandmapper.getAllbrand(norm);
                redisUtils.set(allbrandkey,  allbrandmax,3600);
            }

            List<Brand> allbrandavg= new ArrayList<>();
            CollectionUtils.addAll(allbrandavg, new Object[allbrandmax.size()]);
            Collections.copy(allbrandavg, allbrandmax);
            Collections.sort(allbrandavg,new Comparator () {
                @Override
                public int compare(Object o1, Object o2) {
                    // TODO Auto-generated method stub
                    Brand e1=(Brand) o1;
                    Brand e2=(Brand) o2;
                    return e1.getSvcavgrk() - e2.getSvcavgrk();
                }
            });

            String allshowrkkey="allshowrk_"+Normmap.normmap(norm);
            List<Showname> allshowrk=new ArrayList<>();
            if(redisUtils.hasKey(allshowrkkey)){
                allshowrk=(List<Showname>)redisUtils.get(allshowrkkey);
            }else{
                allshowrk=showcompmapper.getLshowname(norm);
                redisUtils.set(allshowrkkey,allshowrk,3600);
            }



            // 最大值数据加载

            String brandnamemax=allbrandmax.get(0).getBrand();
            String brandtypemaxkey="brandtype_"+brandnamemax;
            Brandtype brandtypemax;
            if(redisUtils.hasKey(brandtypemaxkey)){
                brandtypemax=(Brandtype)redisUtils.get(brandtypemaxkey);
            }else{
                brandtypemax=allbrandmapper.getBrandtype(brandnamemax);
                redisUtils.set(brandtypemaxkey,brandtypemax,3600);
            }

            String brandshowmaxkey="brandshow_"+brandnamemax+Normmap.normmap(norm);
            List<Brandshow> brandshowmax=new ArrayList<>();
            if(redisUtils.hasKey(brandshowmaxkey)){
                brandshowmax=(List<Brandshow>)redisUtils.get(brandshowmaxkey);
            }else{
                brandshowmax=allbrandmapper.getBrandshow(brandnamemax,norm);
                redisUtils.set(brandshowmaxkey,brandshowmax,3600);
            }

            String brandtrendmaxkey="brandtrend_"+brandnamemax+Normmap.normmap(norm);;
            List<Brandtrend> brandtrendmax;
            if(redisUtils.hasKey(brandtrendmaxkey)){
                brandtrendmax=(List<Brandtrend>)redisUtils.get(brandtrendmaxkey);
            }else{
                brandtrendmax=allbrandmapper.getBrandtrend(brandnamemax,norm);
                redisUtils.set(brandtrendmaxkey,brandtrendmax,3600);
            }

            String t1shownamemax=brandshowmax.get(0).getShow();
            String showtypemaxkey="showtype_"+t1shownamemax;
            String bdindexmaxkey="bdindex_"+t1shownamemax;
            String wbindexmaxkey="wbindex_"+t1shownamemax;
            String wxindexmaxkey="wxindex_"+t1shownamemax;
            String tgimaxkey="showtgi_"+t1shownamemax;

            Showtype t1showtypemax;
            List<Bdindex> bdindexmax=new ArrayList<>();
            List<Wbindex> wbindexmax=new ArrayList<>();
            List<Wxindex> wxindexmax=new ArrayList<>();
            Showtgi showtgimax;


            if(redisUtils.hasKey(showtypemaxkey)){
                t1showtypemax=(Showtype)redisUtils.get(showtypemaxkey);
            }else{
                t1showtypemax=allshowmapper.getShowtype(t1shownamemax);
            }

            if(redisUtils.hasKey(bdindexmaxkey)){
                bdindexmax=(List<Bdindex>)redisUtils.get(bdindexmaxkey);
            }else{
                bdindexmax=allshowmapper.getBdindex(t1shownamemax);
                redisUtils.set(bdindexmaxkey,bdindexmax,3600);
            }

            if(redisUtils.hasKey(wbindexmaxkey)){
                wbindexmax=(List<Wbindex>)redisUtils.get(wbindexmaxkey);
            }else{
                wbindexmax=allshowmapper.getWbindex(t1shownamemax);
                redisUtils.set(wbindexmaxkey,wbindexmax,3600);
            }

            if(redisUtils.hasKey(wxindexmaxkey)){
                wxindexmax=(List<Wxindex>)redisUtils.get(wxindexmaxkey);
            }else{
                wxindexmax=allshowmapper.getWxindex(t1shownamemax);
                redisUtils.set(wxindexmaxkey,wxindexmax,3600);
            }
            if(redisUtils.hasKey(tgimaxkey)){
                showtgimax=(Showtgi)redisUtils.get(tgimaxkey);
            }else{
                showtgimax=allshowmapper.getShowtgi(t1shownamemax);
                redisUtils.set(tgimaxkey,showtgimax,3600);
            }



            // 加权值数据加载

            String brandnameavg=allbrandavg.get(0).getBrand();
            String brandtypeavgkey="brandtype_"+brandnameavg;
            Brandtype brandtypeavg;
            if(redisUtils.hasKey(brandtypeavgkey)){
                brandtypeavg=(Brandtype)redisUtils.get(brandtypeavgkey);
            }else{
                brandtypeavg=allbrandmapper.getBrandtype(brandnameavg);
                redisUtils.set(brandtypeavgkey,brandtypeavg,3600);
            }

            String brandshowavgkey="brandshow_"+brandnameavg+Normmap.normmap(norm);
            List<Brandshow> brandshowavg=new ArrayList<>();
            if(redisUtils.hasKey(brandshowavgkey)){
                brandshowavg=(List<Brandshow>)redisUtils.get(brandshowavgkey);
            }else{
                brandshowavg=allbrandmapper.getBrandshow(brandnameavg,norm);
                redisUtils.set(brandshowavgkey,brandshowavg,3600);
            }

            String brandtrendavgkey="brandtrend_"+brandnameavg+Normmap.normmap(norm);;
            List<Brandtrend> brandtrendavg;
            if(redisUtils.hasKey(brandtrendavgkey)){
                brandtrendavg=(List<Brandtrend>)redisUtils.get(brandtrendavgkey);
            }else{
                brandtrendavg=allbrandmapper.getBrandtrend(brandnameavg,norm);
                redisUtils.set(brandtrendavgkey,brandtrendavg,3600);
            }

            String t1shownameavg=brandshowavg.get(0).getShow();
            String showtypeavgkey="showtype_"+t1shownameavg;
            String bdindexavgkey="bdindex_"+t1shownameavg;
            String wbindexavgkey="wbindex_"+t1shownameavg;
            String wxindexavgkey="wxindex_"+t1shownameavg;
            String tgiavgkey="showtgi_"+t1shownameavg;

            Showtype t1showtypeavg;
            List<Bdindex> bdindexavg=new ArrayList<>();
            List<Wbindex> wbindexavg=new ArrayList<>();
            List<Wxindex> wxindexavg=new ArrayList<>();
            Showtgi showtgiavg;


            if(redisUtils.hasKey(showtypeavgkey)){
                t1showtypeavg=(Showtype)redisUtils.get(showtypeavgkey);
            }else{
                t1showtypeavg=allshowmapper.getShowtype(t1shownameavg);
            }

            if(redisUtils.hasKey(bdindexavgkey)){
                bdindexavg=(List<Bdindex>)redisUtils.get(bdindexavgkey);
            }else{
                bdindexavg=allshowmapper.getBdindex(t1shownameavg);
                redisUtils.set(bdindexavgkey,bdindexavg,3600);
            }

            if(redisUtils.hasKey(wbindexavgkey)){
                wbindexavg=(List<Wbindex>)redisUtils.get(wbindexavgkey);
            }else{
                wbindexavg=allshowmapper.getWbindex(t1shownameavg);
                redisUtils.set(wbindexavgkey,wbindexavg,3600);
            }

            if(redisUtils.hasKey(wxindexavgkey)){
                wxindexavg=(List<Wxindex>)redisUtils.get(wxindexavgkey);
            }else{
                wxindexavg=allshowmapper.getWxindex(t1shownameavg);
                redisUtils.set(wxindexavgkey,wxindexavg,3600);
            }
            if(redisUtils.hasKey(tgiavgkey)){
                showtgiavg=(Showtgi)redisUtils.get(tgiavgkey);
            }else{
                showtgiavg=allshowmapper.getShowtgi(t1shownameavg);
                redisUtils.set(tgiavgkey,showtgiavg,3600);
            }

            String customshowkey="customshow";
            String custombrandkey="custombrand";
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


            Allbrand allbrand=new Allbrand();

            allbrand.setAllshowrk(allshowrk);
            allbrand.setAllbrandmax(allbrandmax);
            allbrand.setBrandtypemax(brandtypemax);
            allbrand.setT1showtypemax(t1showtypemax);
            allbrand.setBrandshowmax(brandshowmax);
            allbrand.setBrandtrendmax(brandtrendmax);
            allbrand.setBdindexmax(bdindexmax);
            allbrand.setWbindexmax(wbindexmax);
            allbrand.setWxindexmax(wxindexmax);
            allbrand.setShowtgimax(showtgimax);
            allbrand.setAllbrandavg(allbrandavg);
            allbrand.setBrandtypeavg(brandtypeavg);
            allbrand.setT1showtypeavg(t1showtypeavg);
            allbrand.setBrandshowavg(brandshowavg);
            allbrand.setBrandtrendavg(brandtrendavg);
            allbrand.setBdindexavg(bdindexavg);
            allbrand.setWbindexavg(wbindexavg);
            allbrand.setWxindexavg(wxindexavg);
            allbrand.setShowtgiavg(showtgiavg);
            allbrand.setCustombrand(custombrand);
            allbrand.setCustomshow(customshow);
            allbrand.setBgdate(strbgdate);
            allbrand.setEndate(strenddate);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|品牌排名与详情|Norm筛选"+"|@|";
            logger.info(slog);
            return allbrand;


        }
        
        
        @RequestMapping("getbrand")
        @ResponseBody
        public Object getbrand(HttpServletRequest request){
            HttpSession session=request.getSession(); 
            String brandname=request.getParameter("brandname");
            System.out.println(brandname);
            Norm norm=(Norm)session.getAttribute("brandnorm");
            
            String brandtypekey="brandtype_"+brandname;
            String brandshowkey="brandshow_"+brandname+Normmap.normmap(norm);
            String brandtrendkey="brandtrend_"+brandname+Normmap.normmap(norm);
            Brandtype brandtype;
            List<Brandshow> brandshow=new ArrayList<>();
            List<Brandtrend> brandtrend=new ArrayList<>();
            
            if(redisUtils.hasKey(brandtypekey)){
                brandtype=(Brandtype)redisUtils.get(brandtypekey);
            }else{
                brandtype=allbrandmapper.getBrandtype(brandname);
                redisUtils.set(brandtypekey,brandtype,3600);
            }

            if(redisUtils.hasKey(brandshowkey)){
                brandshow=(List<Brandshow>) redisUtils.get(brandshowkey);
            }else{
                brandshow=allbrandmapper.getBrandshow(brandname,norm);
                redisUtils.set(brandshowkey,brandshow,3600);
            }

            if(redisUtils.hasKey(brandtrendkey)){
                brandtrend=(List<Brandtrend>) redisUtils.get(brandtrendkey);
            }else{
                brandtrend=allbrandmapper.getBrandtrend(brandname,norm);
                redisUtils.set(brandtrendkey,brandtrend,3600);
            }
            
            String t1showname=brandshow.get(0).getShow();
            String t1showtypekey="showtype_"+t1showname;
            String bdindexkey="bdindex_"+t1showname;
            String wbindexkey="wbindex_"+t1showname;
            String wxindexkey="wxindex_"+t1showname;
            String tgikey="showtgi_"+t1showname;

            Showtype t1showtype;
            List<Bdindex> bdindex=new ArrayList<>();
            List<Wbindex> wbindex=new ArrayList<>();
            List<Wxindex> wxindex=new ArrayList<>();
            Showtgi showtgi;
            
            if(redisUtils.hasKey(t1showtypekey)){
                t1showtype=(Showtype)redisUtils.get(t1showtypekey);
            }else{
                t1showtype=allshowmapper.getShowtype(t1showname);
                redisUtils.set(t1showtypekey,t1showtype,3600);
            }
            if(redisUtils.hasKey(bdindexkey)){
                bdindex=(List<Bdindex>)redisUtils.get(bdindexkey);
            }else{
                bdindex=allshowmapper.getBdindex(t1showname);
                redisUtils.set(bdindexkey,bdindex,3600);
            }
            if(redisUtils.hasKey(wbindexkey)){
                wbindex=(List<Wbindex>)redisUtils.get(wbindexkey);
            }else{
                wbindex=allshowmapper.getWbindex(t1showname);
                redisUtils.set(wbindexkey,wbindex,3600);
            }

            if(redisUtils.hasKey(wxindexkey)){
                wxindex=(List<Wxindex>)redisUtils.get(wxindexkey);
            }else{
                wxindex=allshowmapper.getWxindex(t1showname);
                redisUtils.set(wxindexkey,wxindex,3600);
            }

            if(redisUtils.hasKey(tgikey)){
                showtgi=(Showtgi)redisUtils.get(tgikey);
            }else{
                showtgi=allshowmapper.getShowtgi(t1showname);
                redisUtils.set(tgikey,showtgi,3600);
            }

            Allbrand allbrand=new Allbrand();

            allbrand.setBrandtype(brandtype);
            allbrand.setBrandshow(brandshow);
            allbrand.setBrandtrend(brandtrend);
            allbrand.setT1showtype(t1showtype);
            allbrand.setBdindex(bdindex);
            allbrand.setWbindex(wbindex);
            allbrand.setWxindex(wxindex);
            allbrand.setShowtgi(showtgi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|品牌排名与详情|品牌搜索"+"|@|";
            logger.info(slog);

            return allbrand;


        }

    @RequestMapping("getoneshow")
    @ResponseBody
    public Object getoneshow(HttpServletRequest request){
        HttpSession session=request.getSession();
        String showname=request.getParameter("showname");
        System.out.println(showname);

        String t1showtypekey="showtype_"+showname;
        String bdindexkey="bdindex_"+showname;
        String wbindexkey="wbindex_"+showname;
        String wxindexkey="wxindex_"+showname;
        String tgikey="showtgi_"+showname;


        Showtype t1showtype;
        List<Bdindex> bdindex=new ArrayList<>();
        List<Wbindex> wbindex=new ArrayList<>();
        List<Wxindex> wxindex=new ArrayList<>();
        Showtgi showtgi;


        if(redisUtils.hasKey(t1showtypekey)){
            t1showtype=(Showtype)redisUtils.get(t1showtypekey);
        }else{
            t1showtype=allshowmapper.getShowtype(showname);
            redisUtils.set(t1showtypekey,t1showtype,3600);
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

        Allbrand allbrand=new Allbrand();
        allbrand.setT1showtype(t1showtype);
        allbrand.setBdindex(bdindex);
        allbrand.setWbindex(wbindex);
        allbrand.setWxindex(wxindex);
        allbrand.setShowtgi(showtgi);

        String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|品牌排名与详情|查看节目信息"+"|@|";
        logger.info(slog);

        return allbrand;


    }



}

