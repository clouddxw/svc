package com.iresearch.svc.controller.svc.vip;
import com.iresearch.svc.aspect.SysServiceLog;
import com.iresearch.svc.bean.*;
import com.iresearch.svc.mapper.svc.IndexMapper;
import com.iresearch.svc.outbean.Allshow;
import com.iresearch.svc.redis.RedisUtils;
import com.iresearch.svc.mapper.svc.AllshowMapper;
import com.iresearch.svc.service.svc.AllshowService;
import com.iresearch.svc.service.svc.IndexService;
import com.iresearch.svc.constant.Normdefv;
import com.iresearch.svc.utils.Normmap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@RequestMapping("/api/svc/vip/allshow")
@RestController
public class AllshowController {
        @Resource
        private AllshowService allshowService;
        @Resource
        private IndexService indexService;



        @GetMapping("get1st")
        @SysServiceLog(project="svc",model = "节目排名详情")
        public Object get1st(HttpServletRequest request) {

            HttpSession session=request.getSession();
            Norm norm=new Norm(Normdefv.sex,Normdefv.agegroup,Normdefv.citylevel,Normdefv.showtype,Normdefv.showlevel,
                    Normdefv.playplat, Normdefv.brandtype,Normdefv.sponsortype,Normdefv.begindate,Normdefv.enddate);
            session.setAttribute("shownorm", norm);


            String allshowkey="allshow_"+Normmap.normmap(norm);
            List<Show> allshowmax=allshowService.getAllshow(allshowkey,norm);
            

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
            
            
            //设定键值
            String shownamemax=allshowmax.get(0).getShow();
            String showtypemaxkey="showtype_"+shownamemax;
            String showbrandmaxkey="showbrand_"+shownamemax+Normmap.normmap(norm);
            String bdindexmaxkey="bdindex_"+shownamemax;
            String wbindexmaxkey="wbindex_"+shownamemax;
            String wxindexmaxkey="wxindex_"+shownamemax;
            String tgimaxkey="showtgi_"+shownamemax;
            
            //从redis或者数据库中获取数据
            Showtype showtypemax=allshowService.getShowtype(showtypemaxkey,shownamemax);
            List<Showbrand> showbrandmax=allshowService.getShowbrand(showbrandmaxkey,shownamemax,norm);
            List<Bdindex> bdindexmax=allshowService.getBdindex(bdindexmaxkey,shownamemax);
            List<Wbindex> wbindexmax=allshowService.getWbindex(wbindexmaxkey,shownamemax);
            List<Wxindex> wxindexmax=allshowService.getWxindex(wxindexmaxkey,shownamemax);
            Showtgi showtgimax=allshowService.getShowtgi(tgimaxkey,shownamemax);


            // 最小值数据加载


            //设定键值
            String shownameavg=allshowavg.get(0).getShow();
            String showtypeavgkey="showtype_"+shownameavg;
            String showbrandavgkey="showbrand_"+shownameavg+Normmap.normmap(norm);
            String bdindexavgkey="bdindex_"+shownameavg;
            String wbindexavgkey="wbindex_"+shownameavg;
            String wxindexavgkey="wxindex_"+shownameavg;
            String tgiavgkey="showtgi_"+shownameavg;

            //从redis或者数据库中获取数据
            Showtype showtypeavg=allshowService.getShowtype(showtypeavgkey,shownameavg);
            List<Showbrand> showbrandavg=allshowService.getShowbrand(showbrandavgkey,shownameavg,norm);
            List<Bdindex> bdindexavg=allshowService.getBdindex(bdindexavgkey,shownameavg);
            List<Wbindex> wbindexavg=allshowService.getWbindex(wbindexavgkey,shownameavg);
            List<Wxindex> wxindexavg=allshowService.getWxindex(wxindexavgkey,shownameavg);
            Showtgi showtgiavg=allshowService.getShowtgi(tgiavgkey,shownameavg);



            String customshowkey="customshow";
            String custombrandkey="custombrand";
            List<Showname> customshow=indexService.getCustomshow(customshowkey);
            List<Brandname> custombrand=indexService.getCustombrand(custombrandkey);

            Allshow allshow=new Allshow();
            allshow.setAllshowmax(allshowmax);
            allshow.setShowtypemax(showtypemax);
            allshow.setShowbrandmax(showbrandmax);
            allshow.setBdindexmax(bdindexmax);
            allshow.setWbindexmax(wbindexmax);
            allshow.setWxindexmax(wxindexmax);
            allshow.setShowtgimax(showtgimax);

            allshow.setShowtypeavg(showtypeavg);
            allshow.setShowbrandavg(showbrandavg);
            allshow.setBdindexavg(bdindexavg);
            allshow.setWbindexavg(wbindexavg);
            allshow.setWxindexavg(wxindexavg);
            allshow.setShowtgiavg(showtgiavg);
            allshow.setCustombrand(custombrand);
            allshow.setCustomshow(customshow);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目排名与详情|打开页面"+"|@|";
            log.info(slog);

            return allshow;
        }

        @RequestMapping("getnorm")
        @SysServiceLog(project="svc",model = "节目排名详情")
        public Object getnorm(String playplat,String showtype,String showlevel,String spontype,String brandtype,
                              String sex,String agegroup,String citylevel,String bgdate,String enddaten,HttpServletRequest request){
            HttpSession session=request.getSession();
            log.info(playplat+showtype+showlevel+spontype+brandtype+sex+agegroup+citylevel+bgdate+enddaten);

            java.sql.Date begindate = java.sql.Date.valueOf(bgdate);
            java.sql.Date enddate = java.sql.Date.valueOf(enddaten);
            Norm norm=new Norm(sex,agegroup,citylevel,showtype,showlevel,playplat,brandtype,spontype,begindate,enddate);
            session.setAttribute("shownorm", norm);


            String allshowkey="allshow_"+Normmap.normmap(norm);
            List<Show> allshowmax=allshowService.getAllshow(allshowkey,norm);

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


            //设定键值
            String shownamemax=allshowmax.get(0).getShow();
            String showtypemaxkey="showtype_"+shownamemax;
            String showbrandmaxkey="showbrand_"+shownamemax+Normmap.normmap(norm);
            String bdindexmaxkey="bdindex_"+shownamemax;
            String wbindexmaxkey="wbindex_"+shownamemax;
            String wxindexmaxkey="wxindex_"+shownamemax;
            String tgimaxkey="showtgi_"+shownamemax;

            //从redis或者数据库中获取数据
            Showtype showtypemax=allshowService.getShowtype(showtypemaxkey,shownamemax);
            List<Showbrand> showbrandmax=allshowService.getShowbrand(showbrandmaxkey,shownamemax,norm);
            List<Bdindex> bdindexmax=allshowService.getBdindex(bdindexmaxkey,shownamemax);
            List<Wbindex> wbindexmax=allshowService.getWbindex(wbindexmaxkey,shownamemax);
            List<Wxindex> wxindexmax=allshowService.getWxindex(wxindexmaxkey,shownamemax);
            Showtgi showtgimax=allshowService.getShowtgi(tgimaxkey,shownamemax);


            // 最小值数据加载


            //设定键值
            String shownameavg=allshowavg.get(0).getShow();
            String showtypeavgkey="showtype_"+shownameavg;
            String showbrandavgkey="showbrand_"+shownameavg+Normmap.normmap(norm);
            String bdindexavgkey="bdindex_"+shownameavg;
            String wbindexavgkey="wbindex_"+shownameavg;
            String wxindexavgkey="wxindex_"+shownameavg;
            String tgiavgkey="showtgi_"+shownameavg;

            //从redis或者数据库中获取数据
            Showtype showtypeavg=allshowService.getShowtype(showtypeavgkey,shownameavg);
            List<Showbrand> showbrandavg=allshowService.getShowbrand(showbrandavgkey,shownameavg,norm);
            List<Bdindex> bdindexavg=allshowService.getBdindex(bdindexavgkey,shownameavg);
            List<Wbindex> wbindexavg=allshowService.getWbindex(wbindexavgkey,shownameavg);
            List<Wxindex> wxindexavg=allshowService.getWxindex(wxindexavgkey,shownameavg);
            Showtgi showtgiavg=allshowService.getShowtgi(tgiavgkey,shownameavg);



            String customshowkey="customshow";
            String custombrandkey="custombrand";
            List<Showname> customshow=indexService.getCustomshow(customshowkey);
            List<Brandname> custombrand=indexService.getCustombrand(custombrandkey);



            Allshow allshow=new Allshow();

            allshow.setAllshowmax(allshowmax);
            allshow.setShowtypemax(showtypemax);
            allshow.setShowbrandmax(showbrandmax);
            allshow.setBdindexmax(bdindexmax);
            allshow.setWbindexmax(wbindexmax);
            allshow.setWxindexmax(wxindexmax);
            allshow.setShowtgimax(showtgimax);

            allshow.setShowtypeavg(showtypeavg);
            allshow.setShowbrandavg(showbrandavg);
            allshow.setBdindexavg(bdindexavg);
            allshow.setWbindexavg(wbindexavg);
            allshow.setWxindexavg(wxindexavg);
            allshow.setShowtgiavg(showtgiavg);
            allshow.setCustomshow(customshow);
            allshow.setCustombrand(custombrand);
            allshow.setBgdate(bgdate);
            allshow.setEnddate(enddaten);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目排名与详情|Norm筛选"+"|@|";
            log.info(slog);

            return allshow;


        }
        
        
        @RequestMapping("getshow")
        @SysServiceLog(project="svc",model = "节目排名详情")
        public Object getshow(String showname,HttpServletRequest request){
            HttpSession session=request.getSession();
            log.info(showname);
            Norm norm=(Norm)session.getAttribute("shownorm");

            //设定键值
            String showbrandkey="showbrand_"+showname+Normmap.normmap(norm);
            String showtypekey="showtype_"+showname;
            String bdindexkey="bdindex_"+showname;
            String wbindexkey="wbindex_"+showname;
            String wxindexkey="wxindex_"+showname;
            String tgikey="showtgi_"+showname;

            //从redis或者数据库中获取数据

            List<Showbrand> showbrand=allshowService.getShowbrand(showbrandkey,showname,norm);
            Showtype showtype=allshowService.getShowtype(showtypekey,showname);
            List<Bdindex> bdindex=allshowService.getBdindex(bdindexkey,showname);
            List<Wbindex> wbindex=allshowService.getWbindex(wbindexkey,showname);
            List<Wxindex> wxindex=allshowService.getWxindex(wxindexkey,showname);
            Showtgi showtgi=allshowService.getShowtgi(tgikey,showname);
            


            Allshow allshow=new Allshow();
            allshow.setShowtype(showtype);
            allshow.setShowbrand(showbrand);
            allshow.setBdindex(bdindex);
            allshow.setWbindex(wbindex);
            allshow.setWxindex(wxindex);
            allshow.setShowtgi(showtgi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目排名与详情|节目搜索"+"|@|";
            log.info(slog);

            return allshow;

            
        }  





}

