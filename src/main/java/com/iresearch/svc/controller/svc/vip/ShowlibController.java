package com.iresearch.svc.controller.svc.vip;

import com.iresearch.svc.aspect.SysServiceLog;
import com.iresearch.svc.bean.*;
import com.iresearch.svc.outbean.Showlib;
import com.iresearch.svc.redis.RedisUtils;
import com.iresearch.svc.service.svc.ShowlibService;
import com.iresearch.svc.constant.Normdefv;
import com.iresearch.svc.utils.Normmap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RequestMapping("/api/svc/vip/showlib")
@RestController
public class ShowlibController {
        @Autowired
        private  RedisUtils redisUtils;
        @Resource
        private ShowlibService showlibService;


        @RequestMapping("get1st")
        @SysServiceLog(project="svc",model = "节目库")
        public Object get1st(HttpServletRequest request) throws UnsupportedEncodingException {
            HttpSession session=request.getSession();
            String showname=new String(request.getParameter("showname").getBytes("ISO-8859-1"),"UTF-8");
            log.info(showname);
            Norm norm=new Norm(Normdefv.sex,Normdefv.agegroup,Normdefv.citylevel,Normdefv.showtype,Normdefv.showlevel,
                    Normdefv.playplat, Normdefv.brandtype,Normdefv.sponsortype,Normdefv.begindate,Normdefv.enddate);
            session.setAttribute("showlibnorm", norm);

            String showsiskey="showsis_"+showname+Normmap.normmap(norm);
            String showbdskey="showbds_"+showname;
            List<Showsimilar> lshowsi=showlibService.getShowsimilar(showsiskey,showname,norm);
            List<Libshowbd> lshowbd=showlibService.getShowbd(showbdskey,showname);
            Showlib showlib=new Showlib();
            showlib.setLshowbd(lshowbd);
            showlib.setLshowsi(lshowsi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目库|打开页面"+"|@|";
            log.info(slog);

            return showlib;
        }

        @RequestMapping("getnorm")
        @SysServiceLog(project="svc",model = "节目库")
        public Object getnorm(String playplat,String showtype,String showlevel,String spontype,String brandtype,
                              String sex,String agegroup,String citylevel,String bgdate,String enddaten,HttpServletRequest request){
            HttpSession session=request.getSession();
            log.info(playplat+showtype+showlevel+spontype+brandtype+sex+agegroup+citylevel+bgdate+enddaten);
            java.sql.Date begindate = java.sql.Date.valueOf(bgdate);
            java.sql.Date enddate = java.sql.Date.valueOf(enddaten);
            Norm norm=new Norm(sex,agegroup,citylevel,showtype,showlevel,playplat,brandtype,spontype,begindate,enddate);
            session.setAttribute("showlibnorm", norm);

            String showsiskey="showsis_"+Normmap.normmap(norm);
            List<Showsimilar> lshowsi=showlibService.getShowsimilar(showsiskey,null,norm);
            String t1showname=lshowsi.get(0).getShowname();
            String showbdskey="showbds_"+t1showname;
            List<Libshowbd> lshowbd=showlibService.getShowbd(showbdskey,t1showname);

            Showlib showlib=new Showlib();
            showlib.setBgdate(bgdate);
            showlib.setEnddate(enddaten);
            showlib.setLshowbd(lshowbd);
            showlib.setLshowsi(lshowsi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目库|Norm筛选"+"|@|";
            log.info(slog);

            return showlib;

        }
        
        
        @RequestMapping("getsim")
        @SysServiceLog(project="svc",model = "节目库")
        public Object getsim(HttpServletRequest request){
            HttpSession session=request.getSession(); 
            String showname=request.getParameter("showname");
            log.info(showname);
            Norm norm=(Norm)session.getAttribute("showlibnorm");

            String showsiskey="showsis_"+showname+Normmap.normmap(norm);
            String showbdskey="showbds_"+showname;
            List<Showsimilar> lshowsi=showlibService.getShowsimilar(showsiskey,showname,norm);
            List<Libshowbd> lshowbd=showlibService.getShowbd(showbdskey,showname);

            Showlib showlib=new Showlib();
            showlib.setLshowbd(lshowbd);
            showlib.setLshowsi(lshowsi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目库|找相似"+"|@|";
            log.info(slog);

            return showlib;

        }

    @RequestMapping("getRF")
    @SysServiceLog(project="svc",model = "节目库")
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
        log.info(slog);

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
                //log.info(line);
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

