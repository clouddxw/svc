package com.iresearch.svc.controller;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.mapper.svc.AllbrandMapper;
import com.iresearch.svc.mapper.svc.AllshowMapper;
import com.iresearch.svc.mapper.svc.BrandcustomMapper;
import com.iresearch.svc.mapper.svc.ShowcompMapper;
import com.iresearch.svc.outbean.Brandcustom;
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
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/brandcustom")
@Controller
public class BrandcustomController {
        Logger logger = LoggerFactory.getLogger(BrandcustomController.class);
        @Autowired(required = false)
        private AllshowMapper allshowmapper;
        @Autowired(required = false)
        private BrandcustomMapper brandcustommapper;
        @Autowired(required = false)
        private AllbrandMapper allbrandmapper;
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
        public Object get1st(HttpServletRequest request) throws UnsupportedEncodingException{
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
            session.setAttribute("brandcustomnorm", norm);
            String brandname=new String(URLDecoder.decode(request.getParameter("brandname")).getBytes("ISO-8859-1"),"UTF-8");
            //String brandname=new String(request.getParameter("brandname").getBytes("ISO-8859-1"),"UTF-8");
            
            String brandshowkey="brandshow_"+brandname+Normmap.normmap(norm);
            String brandshowdtkey="brandshowdt_"+brandname+Normmap.normmap(norm);
            List<Brandshow> brandshow=new ArrayList<>();
            List<Brandshowdt> brandshowdts=new ArrayList<>();
            if(redisUtils.hasKey(brandshowkey)){
                brandshow=(List<Brandshow>)redisUtils.get(brandshowkey);
            }else{
                brandshow=allbrandmapper.getBrandshow(brandname,norm);
                redisUtils.set(brandshowkey,brandshow,3600);
            }
            if(redisUtils.hasKey(brandshowdtkey)){
                brandshowdts=(List<Brandshowdt>)redisUtils.get(brandshowdtkey);
            }else{
                brandshowdts=brandcustommapper.getBrandshowdt(brandname,norm);
                redisUtils.set(brandshowdtkey,brandshowdts,3600);
            }
            String t1showname=brandshow.get(0).getShow();
            String allshowrkkey="allshowrk_"+Normmap.normmap(norm);
            String brandtypekey="brandtype_"+brandname;
            String t1showtypekey="showtype_"+t1showname;
            String bdindexkey="bdindex_"+t1showname;
            String wbindexkey="wbindex_"+t1showname;
            String wxindexkey="wxindex_"+t1showname;
            String tgikey="showtgi_"+t1showname;

            List<Showname> allshowrk=new ArrayList<>();
            Brandtype brandtype;
            Showtype t1showtype;
            List<Bdindex> bdindex=new ArrayList<>();
            List<Wbindex> wbindex=new ArrayList<>();
            List<Wxindex> wxindex=new ArrayList<>();
            Showtgi showtgi;
            if(redisUtils.hasKey(allshowrkkey)){
                allshowrk=(List<Showname>)redisUtils.get(allshowrkkey);
            }else{
                allshowrk=showcompmapper.getLshowname(norm);
                redisUtils.set(allshowrkkey,allshowrk,3600);
            }
            if(redisUtils.hasKey(brandtypekey)){
                brandtype=(Brandtype)redisUtils.get(brandtypekey);
            }else{
                brandtype=allbrandmapper.getBrandtype(brandname);
                redisUtils.set(brandtypekey,brandtype,3600);
            }
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

            Brandcustom brandcustom=new Brandcustom();
            brandcustom.setAllshowrk(allshowrk);
            brandcustom.setBrandtype(brandtype);
            brandcustom.setBdindex(bdindex);
            brandcustom.setWbindex(wbindex);
            brandcustom.setWxindex(wxindex);
            brandcustom.setShowtgi(showtgi);
            brandcustom.setBrandshow(brandshow);
            brandcustom.setT1showtype(t1showtype);
            brandcustom.setLbrandshowdt(brandshowdts);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|定制品牌详情|打开页面"+"|@|";
            logger.info(slog);

            return brandcustom;


            
        }

        @RequestMapping("getnorm")
        @ResponseBody
        public Object getnorm(HttpServletRequest request) throws UnsupportedEncodingException{
            HttpSession session=request.getSession();
            String playplat=request.getParameter("playplat");
            String showtype=request.getParameter("showtype");
            String showlevel=request.getParameter("showlevel");
            String sponsortype=request.getParameter("spontype");
            String brandtypevn=request.getParameter("brandtype");
            String sex=request.getParameter("sex");
            String agegroup=request.getParameter("agegroup");
            String citylevel=request.getParameter("citylevel");
            String strbgdate=request.getParameter("bgdate");
            String strenddate=request.getParameter("enddaten");
            String brandname=request.getParameter("brandname");
            System.out.println(playplat+showtype+showlevel+sponsortype+brandtypevn+sex+agegroup+citylevel+strbgdate+strenddate);

            java.sql.Date begindate = java.sql.Date.valueOf(strbgdate);
            java.sql.Date enddate = java.sql.Date.valueOf(strenddate);
            Norm norm=new Norm();
            norm.setType(playplat);
            norm.setShowtype(showtype);
            norm.setShowlevel(showlevel);
            norm.setSponsortype(sponsortype);
            norm.setBrandtype(brandtypevn);
            norm.setSex(sex);
            norm.setAgegroup(agegroup);
            norm.setCitylevel(citylevel);
            norm.setBegindate(begindate);
            norm.setEnddate(enddate);
            session.setAttribute("brandcustomnorm", norm);

            String brandshowkey="brandshow_"+brandname+Normmap.normmap(norm);
            String brandshowdtkey="brandshowdt_"+brandname+Normmap.normmap(norm);
            List<Brandshow> brandshow=new ArrayList<>();
            List<Brandshowdt> brandshowdts=new ArrayList<>();
            if(redisUtils.hasKey(brandshowkey)){
                brandshow=(List<Brandshow>)redisUtils.get(brandshowkey);
            }else{
                brandshow=allbrandmapper.getBrandshow(brandname,norm);
                redisUtils.set(brandshowkey,brandshow,3600);
            }
            if(redisUtils.hasKey(brandshowdtkey)){
                brandshowdts=(List<Brandshowdt>)redisUtils.get(brandshowdtkey);
            }else{
                brandshowdts=brandcustommapper.getBrandshowdt(brandname,norm);
                redisUtils.set(brandshowdtkey,brandshowdts,3600);
            }
            String t1showname=brandshow.get(0).getShow();
            String allshowrkkey="allshowrk_"+Normmap.normmap(norm);
            String brandtypekey="brandtype_"+brandname;
            String t1showtypekey="showtype_"+t1showname;
            String bdindexkey="bdindex_"+t1showname;
            String wbindexkey="wbindex_"+t1showname;
            String wxindexkey="wxindex_"+t1showname;
            String tgikey="showtgi_"+t1showname;

            List<Showname> allshowrk=new ArrayList<>();
            Brandtype brandtype;
            Showtype t1showtype;
            List<Bdindex> bdindex=new ArrayList<>();
            List<Wbindex> wbindex=new ArrayList<>();
            List<Wxindex> wxindex=new ArrayList<>();
            Showtgi showtgi;
            if(redisUtils.hasKey(allshowrkkey)){
                allshowrk=(List<Showname>)redisUtils.get(allshowrkkey);
            }else{
                allshowrk=showcompmapper.getLshowname(norm);
                redisUtils.set(allshowrkkey,allshowrk,3600);
            }
            if(redisUtils.hasKey(brandtypekey)){
                brandtype=(Brandtype)redisUtils.get(brandtypekey);
            }else{
                brandtype=allbrandmapper.getBrandtype(brandname);
                redisUtils.set(brandtypekey,brandtype,3600);
            }
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

            Brandcustom brandcustom=new Brandcustom();
            brandcustom.setBgdate(strbgdate);
            brandcustom.setEnddate(strenddate);
            brandcustom.setAllshowrk(allshowrk);
            brandcustom.setBrandtype(brandtype);
            brandcustom.setBdindex(bdindex);
            brandcustom.setWbindex(wbindex);
            brandcustom.setWxindex(wxindex);
            brandcustom.setShowtgi(showtgi);
            brandcustom.setBrandshow(brandshow);
            brandcustom.setT1showtype(t1showtype);
            brandcustom.setLbrandshowdt(brandshowdts);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|定制品牌详情|Norm筛选"+"|@|";
            logger.info(slog);

            return brandcustom;

        }

    @RequestMapping("getshowdt")
    @ResponseBody
    public Object getshowdt(HttpServletRequest request){
        HttpSession session=request.getSession();
        String showname=request.getParameter("showname");
        String showtypekey="showtype_"+showname;
        String bdindexkey="bdindex_"+showname;
        String wbindexkey="wbindex_"+showname;
        String wxindexkey="wxindex_"+showname;
        String tgikey="showtgi_"+showname;

        Showtype showtype;
        List<Bdindex> bdindex=new ArrayList<>();
        List<Wbindex> wbindex=new ArrayList<>();
        List<Wxindex> wxindex=new ArrayList<>();
        Showtgi showtgi;


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

        Brandcustom brandcustom=new Brandcustom();
        brandcustom.setShowtype(showtype);
        brandcustom.setBdindex(bdindex);
        brandcustom.setWbindex(wbindex);
        brandcustom.setWxindex(wxindex);
        brandcustom.setShowtgi(showtgi);

        String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|定制品牌详情|查看节目详情"+"|@|";
        logger.info(slog);

        return brandcustom;
            
    }



}

