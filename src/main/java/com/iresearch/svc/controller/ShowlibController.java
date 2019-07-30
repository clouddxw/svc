package com.iresearch.svc.controller;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.mapper.svc.ShowlibMapper;
import com.iresearch.svc.outbean.Showlib;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/showlib")
@Controller
public class ShowlibController {
        Logger logger = LoggerFactory.getLogger(ShowlibController.class);
        @Autowired(required = false)
        private ShowlibMapper showlibmapper;
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
        public Object get1st(HttpServletRequest request) throws UnsupportedEncodingException {
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
            session.setAttribute("showlibnorm", norm);
            String showname=new String(URLDecoder.decode(request.getParameter("showname")).getBytes("ISO-8859-1"),"UTF-8");
            //String showname=new String(request.getParameter("showname").getBytes("ISO-8859-1"),"UTF-8");

            String showsiskey="showsis_"+showname+Normmap.normmap(norm);
            String showbdskey="showbds_"+showname;
            List<Showsimilar> lshowsi=new ArrayList<>();
            List<Libshowbd> lshowbd=new ArrayList<>();
            if(redisUtils.hasKey(showsiskey)){
                lshowsi=(List<Showsimilar>)redisUtils.get(showsiskey);
            }else{
                lshowsi=showlibmapper.getShowsimilar(showname, norm);
                redisUtils.set(showsiskey,lshowsi,3600);
            }
            if(redisUtils.hasKey(showbdskey)){
                lshowbd=(List<Libshowbd>)redisUtils.get(showbdskey);
            }else{
                lshowbd=showlibmapper.getShowbd(showname);
                redisUtils.set(showbdskey,lshowbd,3600);
            }
            Showlib showlib=new Showlib();
            showlib.setLshowbd(lshowbd);
            showlib.setLshowsi(lshowsi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目库|打开页面"+"|@|";
            logger.info(slog);

            return showlib;
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
            session.setAttribute("showlibnorm", norm);

            String showsiskey="showsis_"+Normmap.normmap(norm);
            List<Showsimilar> lshowsi=new ArrayList<>();
            if(redisUtils.hasKey(showsiskey)){
                lshowsi=(List<Showsimilar>)redisUtils.get(showsiskey);
            }else{
                lshowsi=showlibmapper.getShowsimilar(null, norm);
                redisUtils.set(showsiskey,lshowsi,3600);
            }
            String t1showname=lshowsi.get(0).getShowname();
            String showbdskey="showbds_"+t1showname;
            List<Libshowbd> lshowbd=new ArrayList<>();
            if(redisUtils.hasKey(showbdskey)){
                lshowbd=(List<Libshowbd>)redisUtils.get(showbdskey);
            }else{
                lshowbd=showlibmapper.getShowbd(t1showname);
                redisUtils.set(showbdskey,lshowbd,3600);
            }

            Showlib showlib=new Showlib();
            showlib.setBgdate(strbgdate);
            showlib.setEnddate(strenddate);
            showlib.setLshowbd(lshowbd);
            showlib.setLshowsi(lshowsi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目库|Norm筛选"+"|@|";
            logger.info(slog);

            return showlib;

        }
        
        
        @RequestMapping("getsim")
        @ResponseBody
        public Object getsim(HttpServletRequest request){
            HttpSession session=request.getSession(); 
            String showname=request.getParameter("showname");
            System.out.println(showname);
            Norm norm=(Norm)session.getAttribute("showlibnorm");

            String showsiskey="showsis_"+showname+Normmap.normmap(norm);
            String showbdskey="showbds_"+showname;
            List<Showsimilar> lshowsi=new ArrayList<>();
            List<Libshowbd> lshowbd=new ArrayList<>();
            if(redisUtils.hasKey(showsiskey)){
                lshowsi=(List<Showsimilar>)redisUtils.get(showsiskey);
            }else{
                lshowsi=showlibmapper.getShowsimilar(showname, norm);
                redisUtils.set(showsiskey,lshowsi,3600);
            }
            if(redisUtils.hasKey(showbdskey)){
                lshowbd=(List<Libshowbd>)redisUtils.get(showbdskey);
            }else{
                lshowbd=showlibmapper.getShowbd(showname);
                redisUtils.set(showbdskey,lshowbd,3600);
            }

            Showlib showlib=new Showlib();
            showlib.setLshowbd(lshowbd);
            showlib.setLshowsi(lshowsi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目库|找相似"+"|@|";
            logger.info(slog);

            return showlib;

        }

    @RequestMapping("getRF")
    @ResponseBody
    public Object getRF(HttpServletRequest request) throws UnsupportedEncodingException{
        HttpSession session=request.getSession();
        String yccon=request.getParameter("yccon");
        String pyoutkey="pyout_"+yccon;
        String pyout;
        if(redisUtils.hasKey(pyoutkey)){
            pyout=(String)redisUtils.get(pyoutkey);
        }else{
            pyout=pyscript(yccon);
            redisUtils.set(pyoutkey,pyout,3600);
        }
        Showlib showlib=new Showlib();
        showlib.setPyout(pyout);

        String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目库|节目预测"+"|@|";
        logger.info(slog);

        return showlib;


    }

    public  String pyscript(String a)   {
        String result = "";
        Process proc;
        try {
            //proc = Runtime.getRuntime().exec("cmd /c D:\\software\\Anaconda3.5.20\\python D:\\software\\script\\python\\RF.py "+a);// 执行py文件
            proc = Runtime.getRuntime().exec("python /root/scripts/python/RF.py "+a);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                result=line;
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }



}

