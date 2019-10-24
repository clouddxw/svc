package com.iresearch.svc.controller.svc.vip;

import com.iresearch.svc.aspect.SysServiceLog;
import com.iresearch.svc.bean.*;
import com.iresearch.svc.outbean.Showcustom;
import com.iresearch.svc.service.svc.AllshowService;
import com.iresearch.svc.service.svc.ShowcustomService;
import com.iresearch.svc.constant.Normdefv;
import com.iresearch.svc.utils.Normmap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

import java.util.List;

@Slf4j
@RequestMapping("/api/svc/vip/showcustom")
@RestController
public class ShowcustomController {
        @Resource
        private AllshowService allshowService;
        @Resource
        private ShowcustomService showcustomService;



        @RequestMapping("get1st")
        @SysServiceLog(project="svc",model = "定制节目详情")
        public Object get1st(HttpServletRequest request) throws UnsupportedEncodingException{
            HttpSession session=request.getSession();
            String showname=new String(request.getParameter("showname").getBytes("ISO-8859-1"),"UTF-8");
            Norm norm=new Norm(Normdefv.sex,Normdefv.agegroup,Normdefv.citylevel,Normdefv.showtype,Normdefv.showlevel,
                    Normdefv.playplat, Normdefv.brandtype,Normdefv.sponsortype,Normdefv.begindate,Normdefv.enddate);
            session.setAttribute("showcustomnorm", norm);

            String scshowkey="scshow_"+showname+Normmap.normmap(norm);
            String sshowkey="sshow_"+showname+Normmap.normmap(norm);
            String showtypekey="showtype_"+showname;
            String bdindexkey="bdindex_"+showname;
            String wbindexkey="wbindex_"+showname;
            String wxindexkey="wxindex_"+showname;
            String tgikey="showtgi_"+showname;
            String showbrandkey="showbrand_"+showname+Normmap.normmap(norm);
            String showbranddtkey="showbranddt_"+showname+Normmap.normmap(norm);

            Scshow show=showcustomService.getShow(scshowkey,showname,norm);
            List<Scshow> sshow=showcustomService.getSshow(sshowkey,showname,norm);
            Showtype showtype=allshowService.getShowtype(showtypekey,showname);
            List<Bdindex> bdindex=allshowService.getBdindex(bdindexkey,showname);
            List<Wbindex> wbindex=allshowService.getWbindex(wbindexkey,showname);
            List<Wxindex> wxindex=allshowService.getWxindex(wxindexkey,showname);
            Showtgi showtgi=allshowService.getShowtgi(tgikey,showname);
            List<Showbrand> showbrand=allshowService.getShowbrand(showbrandkey,showname,norm);
            List<Showbranddt> lshowbranddt=showcustomService.getShowbranddt(showbranddtkey,showname,norm);

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
            log.info(slog);

            return showcustom;
        }

        @RequestMapping("getnorm")
        @SysServiceLog(project="svc",model = "定制节目详情")
        public Object getnorm(String playplat,String showlevel,String spontype,String brandtype,
                              String sex,String agegroup,String citylevel,String bgdate,String enddaten,
                              String showname,HttpServletRequest request) throws UnsupportedEncodingException{
            HttpSession session=request.getSession();
            //log.info(playplat+showtypevn+showlevel+sponsortype+brandtypevn+sex+agegroup+citylevel+strbgdate+strenddate);
            String showtypei=request.getParameter("showtype");
            log.info(playplat+showtypei+showlevel+spontype+brandtype+sex+agegroup+citylevel+bgdate+enddaten);
            java.sql.Date begindate = java.sql.Date.valueOf(bgdate);
            java.sql.Date enddate = java.sql.Date.valueOf(enddaten);
            Norm norm=new Norm(sex,agegroup,citylevel,showtypei,showlevel,playplat,brandtype,spontype,begindate,enddate);
            session.setAttribute("showcustomnorm", norm);
            log.info(showname);
            String scshowkey="scshow_"+showname+Normmap.normmap(norm);
            String sshowkey="sshow_"+showname+Normmap.normmap(norm);
            String showtypekey="showtype_"+showname;
            String bdindexkey="bdindex_"+showname;
            String wbindexkey="wbindex_"+showname;
            String wxindexkey="wxindex_"+showname;
            String tgikey="showtgi_"+showname;
            String showbrandkey="showbrand_"+showname+Normmap.normmap(norm);
            String showbranddtkey="showbranddt_"+showname+Normmap.normmap(norm);

            Scshow show=showcustomService.getShow(scshowkey,showname,norm);
            List<Scshow> sshow=showcustomService.getSshow(sshowkey,showname,norm);
            Showtype showtype=allshowService.getShowtype(showtypekey,showname);
            List<Bdindex> bdindex=allshowService.getBdindex(bdindexkey,showname);
            List<Wbindex> wbindex=allshowService.getWbindex(wbindexkey,showname);
            List<Wxindex> wxindex=allshowService.getWxindex(wxindexkey,showname);
            Showtgi showtgi=allshowService.getShowtgi(tgikey,showname);
            List<Showbrand> showbrand=allshowService.getShowbrand(showbrandkey,showname,norm);
            List<Showbranddt> lshowbranddt=showcustomService.getShowbranddt(showbranddtkey,showname,norm);


            Showcustom showcustom=new Showcustom();
            showcustom.setBgdate(bgdate);
            showcustom.setEnddate(enddaten);
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
            log.info(slog);

            return showcustom;

        }
        



}

