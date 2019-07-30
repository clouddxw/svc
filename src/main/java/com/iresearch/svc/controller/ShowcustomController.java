package com.iresearch.svc.controller;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.mapper.svc.AllshowMapper;
import com.iresearch.svc.mapper.svc.ShowcustomMapper;
import com.iresearch.svc.outbean.Showcustom;
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

@RequestMapping("/showcustom")
@Controller
public class ShowcustomController {
        Logger logger = LoggerFactory.getLogger(ShowcustomController.class);
        @Autowired(required = false)
        private AllshowMapper allshowmapper;
        @Autowired(required = false)
        private ShowcustomMapper showcustommapper;
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
            session.setAttribute("showcustomnorm", norm);
            String showname=new String(URLDecoder.decode(request.getParameter("showname")).getBytes("ISO-8859-1"),"UTF-8");
            System.out.println(showname);

            String scshowkey="scshow_"+showname+Normmap.normmap(norm);
            String sshowkey="sshow_"+showname+Normmap.normmap(norm);
            String showtypekey="showtype_"+showname;
            String bdindexkey="bdindex_"+showname;
            String wbindexkey="wbindex_"+showname;
            String wxindexkey="wxindex_"+showname;
            String tgikey="showtgi_"+showname;
            String showbrandkey="showbrand_"+showname+Normmap.normmap(norm);
            String showbranddtkey="showbranddt_"+showname+Normmap.normmap(norm);

            Scshow show;
            List<Scshow> sshow=new ArrayList<>();
            Showtype showtype;
            List<Bdindex> bdindex;
            List<Wbindex> wbindex;
            List<Wxindex> wxindex;
            Showtgi showtgi;
            List<Showbrand> showbrand=new ArrayList<>();
            List<Showbranddt> lshowbranddt=new ArrayList<>();

            if(redisUtils.hasKey(scshowkey)){
                show=(Scshow)redisUtils.get(scshowkey);
            }else{
                show=showcustommapper.getShow(showname,norm);
                redisUtils.set(scshowkey,show,3600);
            }
            if(redisUtils.hasKey(sshowkey)){
                sshow=(List<Scshow>)redisUtils.get(sshowkey);
            }else{
                sshow=showcustommapper.getSshow(showname,norm);
                redisUtils.set(sshowkey,sshow,3600);
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


            if(redisUtils.hasKey(showbrandkey)){
                showbrand=(List<Showbrand>)redisUtils.get(showbrandkey);
            }else{
                showbrand=allshowmapper.getShowbrand(showname,norm);
                redisUtils.set(showbrandkey,showbrand,3600);
            }
            if(redisUtils.hasKey(showbranddtkey)){
                lshowbranddt=(List<Showbranddt>)redisUtils.get(showbranddtkey);
            }else{
                lshowbranddt=showcustommapper.getShowbranddt(showname,norm);
                redisUtils.set(showbranddtkey,lshowbranddt,3600);
            }

            Showcustom showcustom=new Showcustom();

            showcustom.setShow(show);
            showcustom.setSshow(sshow);
            showcustom.setShowtype(showtype);
            showcustom.setBdindex(bdindex);
            showcustom.setWbindex(wbindex);
            showcustom.setWxindex(wxindex);
            showcustom.setShowtgi(showtgi);
            showcustom.setShowbrand(showbrand);
            showcustom.setLshowbranddt(lshowbranddt);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|定制节目详情|打开页面"+"|@|";
            logger.info(slog);

            return showcustom;
        }

        @RequestMapping("getnorm")
        @ResponseBody
        public Object getnorm(HttpServletRequest request) throws UnsupportedEncodingException{
            HttpSession session=request.getSession();
            String playplat=request.getParameter("playplat");
            String showtypevn=request.getParameter("showtype");
            String showlevel=request.getParameter("showlevel");
            String sponsortype=request.getParameter("spontype");
            String brandtypevn=request.getParameter("brandtype");
            String sex=request.getParameter("sex");
            String agegroup=request.getParameter("agegroup");
            String citylevel=request.getParameter("citylevel");
            String strbgdate=request.getParameter("bgdate");
            String strenddate=request.getParameter("enddaten");
            String brandname=request.getParameter("brandname");
            System.out.println(playplat+showtypevn+showlevel+sponsortype+brandtypevn+sex+agegroup+citylevel+strbgdate+strenddate);

            java.sql.Date begindate = java.sql.Date.valueOf(strbgdate);
            java.sql.Date enddate = java.sql.Date.valueOf(strenddate);
            Norm norm=new Norm();
            norm.setType(playplat);
            norm.setShowtype(showtypevn);
            norm.setShowlevel(showlevel);
            norm.setSponsortype(sponsortype);
            norm.setBrandtype(brandtypevn);
            norm.setSex(sex);
            norm.setAgegroup(agegroup);
            norm.setCitylevel(citylevel);
            norm.setBegindate(begindate);
            norm.setEnddate(enddate);
            session.setAttribute("showcustomnorm", norm);

            String showname=new String(request.getParameter("showname").getBytes("ISO-8859-1"),"UTF-8");
            String scshowkey="scshow_"+showname+Normmap.normmap(norm);
            String sshowkey="sshow_"+showname+Normmap.normmap(norm);
            String showtypekey="showtype_"+showname;
            String bdindexkey="bdindex_"+showname;
            String wbindexkey="wbindex_"+showname;
            String wxindexkey="wxindex_"+showname;
            String tgikey="showtgi_"+showname;
            String showbrandkey="showbrand_"+showname+Normmap.normmap(norm);
            String showbranddtkey="showbranddt_"+showname+Normmap.normmap(norm);

            Scshow show;
            List<Scshow> sshow=new ArrayList<>();
            Showtype showtype;
            List<Bdindex> bdindex;
            List<Wbindex> wbindex;
            List<Wxindex> wxindex;
            Showtgi showtgi;
            List<Showbrand> showbrand=new ArrayList<>();
            List<Showbranddt> lshowbranddt=new ArrayList<>();

            if(redisUtils.hasKey(scshowkey)){
                show=(Scshow)redisUtils.get(scshowkey);
            }else{
                show=showcustommapper.getShow(showname,norm);
                redisUtils.set(scshowkey,show,3600);
            }
            if(redisUtils.hasKey(sshowkey)){
                sshow=(List<Scshow>)redisUtils.get(sshowkey);
            }else{
                sshow=showcustommapper.getSshow(showname,norm);
                redisUtils.set(sshowkey,sshow,3600);
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


            if(redisUtils.hasKey(showbrandkey)){
                showbrand=(List<Showbrand>)redisUtils.get(showbrandkey);
            }else{
                showbrand=allshowmapper.getShowbrand(showname,norm);
                redisUtils.set(showbrandkey,showbrand,3600);
            }
            if(redisUtils.hasKey(showbranddtkey)){
                lshowbranddt=(List<Showbranddt>)redisUtils.get(showbranddtkey);
            }else{
                lshowbranddt=showcustommapper.getShowbranddt(showname,norm);
                redisUtils.set(showbranddtkey,lshowbranddt,3600);
            }

            Showcustom showcustom=new Showcustom();
            showcustom.setBgdate(strbgdate);
            showcustom.setEnddate(strenddate);
            showcustom.setShow(show);
            showcustom.setSshow(sshow);
            showcustom.setShowtype(showtype);
            showcustom.setBdindex(bdindex);
            showcustom.setWbindex(wbindex);
            showcustom.setWxindex(wxindex);
            showcustom.setShowtgi(showtgi);
            showcustom.setShowbrand(showbrand);
            showcustom.setLshowbranddt(lshowbranddt);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|定制节目详情|Norm筛选"+"|@|";
            logger.info(slog);

            return showcustom;

        }
        



}

