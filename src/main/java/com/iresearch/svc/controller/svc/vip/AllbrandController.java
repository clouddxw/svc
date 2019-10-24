package com.iresearch.svc.controller.svc.vip;

import com.iresearch.svc.aspect.SysServiceLog;
import com.iresearch.svc.bean.*;
import com.iresearch.svc.outbean.Allbrand;
import com.iresearch.svc.redis.RedisUtils;
import com.iresearch.svc.service.svc.AllbrandService;
import com.iresearch.svc.service.svc.AllshowService;
import com.iresearch.svc.service.svc.IndexService;
import com.iresearch.svc.service.svc.ShowcompService;
import com.iresearch.svc.constant.Normdefv;
import com.iresearch.svc.utils.Normmap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@RequestMapping("/api/svc/vip/allbrand")
@RestController
public class AllbrandController {
        @Resource
        private AllbrandService allbrandService;
        @Resource
        private AllshowService allshowService;
        @Resource
        private ShowcompService showcompService;
        @Resource
        private IndexService indexService;



        @GetMapping("get1st")
        @SysServiceLog(project="svc",model = "品牌排名详情")
        public Object get1st(HttpServletRequest request) {

            HttpSession session=request.getSession();
            Norm norm=new Norm(Normdefv.sex,Normdefv.agegroup,Normdefv.citylevel,Normdefv.showtype,Normdefv.showlevel,
                    Normdefv.playplat, Normdefv.brandtype,Normdefv.sponsortype,Normdefv.begindate,Normdefv.enddate);
            session.setAttribute("brandnorm", norm);

            String allbrandkey="allbrand_"+Normmap.normmap(norm);
            List<Brand> allbrandmax=allbrandService.getAllbrand(allbrandkey,norm);

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
            List<Showname> allshowrk=showcompService.getLshowname(allshowrkkey,norm);


            // 最大值数据加载

            String brandnamemax=allbrandmax.get(0).getBrand();
            String brandtypemaxkey="brandtype_"+brandnamemax;
            String brandshowmaxkey="brandshow_"+brandnamemax+Normmap.normmap(norm);
            String brandtrendmaxkey="brandtrend_"+brandnamemax+Normmap.normmap(norm);
            
            
            Brandtype brandtypemax=allbrandService.getBrandtype(brandtypemaxkey,brandnamemax);
            List<Brandshow> brandshowmax=allbrandService.getBrandshow(brandshowmaxkey,brandnamemax,norm);
            List<Brandtrend> brandtrendmax=allbrandService.getBrandtrend(brandtrendmaxkey,brandnamemax,norm);
            
            String t1shownamemax=brandshowmax.get(0).getShow();
            String showtypemaxkey="showtype_"+t1shownamemax;
            String bdindexmaxkey="bdindex_"+t1shownamemax;
            String wbindexmaxkey="wbindex_"+t1shownamemax;
            String wxindexmaxkey="wxindex_"+t1shownamemax;
            String tgimaxkey="showtgi_"+t1shownamemax;

            Showtype t1showtypemax=allshowService.getShowtype(showtypemaxkey,t1shownamemax);
            List<Bdindex> bdindexmax=allshowService.getBdindex(bdindexmaxkey,t1shownamemax);
            List<Wbindex> wbindexmax=allshowService.getWbindex(wbindexmaxkey,t1shownamemax);
            List<Wxindex> wxindexmax=allshowService.getWxindex(wxindexmaxkey,t1shownamemax);
            Showtgi showtgimax=allshowService.getShowtgi(tgimaxkey,t1shownamemax);

            // 平均值数据加载

            String brandnameavg=allbrandavg.get(0).getBrand();
            String brandtypeavgkey="brandtype_"+brandnameavg;
            String brandshowavgkey="brandshow_"+brandnameavg+Normmap.normmap(norm);
            String brandtrendavgkey="brandtrend_"+brandnameavg+Normmap.normmap(norm);


            Brandtype brandtypeavg=allbrandService.getBrandtype(brandtypeavgkey,brandnameavg);
            List<Brandshow> brandshowavg=allbrandService.getBrandshow(brandshowavgkey,brandnameavg,norm);
            List<Brandtrend> brandtrendavg=allbrandService.getBrandtrend(brandtrendavgkey,brandnameavg,norm);

            String t1shownameavg=brandshowavg.get(0).getShow();
            String showtypeavgkey="showtype_"+t1shownameavg;
            String bdindexavgkey="bdindex_"+t1shownameavg;
            String wbindexavgkey="wbindex_"+t1shownameavg;
            String wxindexavgkey="wxindex_"+t1shownameavg;
            String tgiavgkey="showtgi_"+t1shownameavg;

            Showtype t1showtypeavg=allshowService.getShowtype(showtypeavgkey,t1shownameavg);
            List<Bdindex> bdindexavg=allshowService.getBdindex(bdindexavgkey,t1shownameavg);
            List<Wbindex> wbindexavg=allshowService.getWbindex(wbindexavgkey,t1shownameavg);
            List<Wxindex> wxindexavg=allshowService.getWxindex(wxindexavgkey,t1shownameavg);
            Showtgi showtgiavg=allshowService.getShowtgi(tgiavgkey,t1shownameavg);

            String customshowkey="customshow";
            String custombrandkey="custombrand";
            List<Showname> customshow=indexService.getCustomshow(customshowkey);
            List<Brandname> custombrand=indexService.getCustombrand(custombrandkey);

            
            Allbrand allbrand=new Allbrand(allshowrk,allbrandmax,brandtypemax,t1showtypemax,brandshowmax,brandtrendmax,bdindexmax,
                    wbindexmax,wxindexmax,showtgimax,allbrandavg,brandtypeavg,t1showtypeavg,brandshowavg,
                    brandtrendavg,bdindexavg,wbindexavg,wxindexavg,showtgiavg,customshow,custombrand);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|品牌排名与详情|打开页面"+"|@|";
            log.info(slog);
            return allbrand;


        }

        @RequestMapping("getnorm")
        @SysServiceLog(project="svc",model = "品牌排名详情")
        public Object getnorm(String playplat,String showtype,String showlevel,String spontype,String brandtype,
                              String sex,String agegroup,String citylevel,String bgdate,String enddaten,HttpServletRequest request){
            HttpSession session=request.getSession();
            log.info(playplat+showtype+showlevel+spontype+brandtype+sex+agegroup+citylevel+bgdate+enddaten);
            java.sql.Date begindate = java.sql.Date.valueOf(bgdate);
            java.sql.Date enddate = java.sql.Date.valueOf(enddaten);
            Norm norm=new Norm(sex,agegroup,citylevel,showtype,showlevel,playplat,brandtype,spontype,begindate,enddate);
            session.setAttribute("brandnorm", norm);

            String allbrandkey="allbrand_"+Normmap.normmap(norm);
            List<Brand> allbrandmax=allbrandService.getAllbrand(allbrandkey,norm);

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
            List<Showname> allshowrk=showcompService.getLshowname(allshowrkkey,norm);


            // 最大值数据加载

            String brandnamemax=allbrandmax.get(0).getBrand();
            String brandtypemaxkey="brandtype_"+brandnamemax;
            String brandshowmaxkey="brandshow_"+brandnamemax+Normmap.normmap(norm);
            String brandtrendmaxkey="brandtrend_"+brandnamemax+Normmap.normmap(norm);


            Brandtype brandtypemax=allbrandService.getBrandtype(brandtypemaxkey,brandnamemax);
            List<Brandshow> brandshowmax=allbrandService.getBrandshow(brandshowmaxkey,brandnamemax,norm);
            List<Brandtrend> brandtrendmax=allbrandService.getBrandtrend(brandtrendmaxkey,brandnamemax,norm);

            String t1shownamemax=brandshowmax.get(0).getShow();
            String showtypemaxkey="showtype_"+t1shownamemax;
            String bdindexmaxkey="bdindex_"+t1shownamemax;
            String wbindexmaxkey="wbindex_"+t1shownamemax;
            String wxindexmaxkey="wxindex_"+t1shownamemax;
            String tgimaxkey="showtgi_"+t1shownamemax;

            Showtype t1showtypemax=allshowService.getShowtype(showtypemaxkey,t1shownamemax);
            List<Bdindex> bdindexmax=allshowService.getBdindex(bdindexmaxkey,t1shownamemax);
            List<Wbindex> wbindexmax=allshowService.getWbindex(wbindexmaxkey,t1shownamemax);
            List<Wxindex> wxindexmax=allshowService.getWxindex(wxindexmaxkey,t1shownamemax);
            Showtgi showtgimax=allshowService.getShowtgi(tgimaxkey,t1shownamemax);

            // 平均值数据加载

            String brandnameavg=allbrandavg.get(0).getBrand();
            String brandtypeavgkey="brandtype_"+brandnameavg;
            String brandshowavgkey="brandshow_"+brandnameavg+Normmap.normmap(norm);
            String brandtrendavgkey="brandtrend_"+brandnameavg+Normmap.normmap(norm);


            Brandtype brandtypeavg=allbrandService.getBrandtype(brandtypeavgkey,brandnameavg);
            List<Brandshow> brandshowavg=allbrandService.getBrandshow(brandshowavgkey,brandnameavg,norm);
            List<Brandtrend> brandtrendavg=allbrandService.getBrandtrend(brandtrendavgkey,brandnameavg,norm);

            String t1shownameavg=brandshowavg.get(0).getShow();
            String showtypeavgkey="showtype_"+t1shownameavg;
            String bdindexavgkey="bdindex_"+t1shownameavg;
            String wbindexavgkey="wbindex_"+t1shownameavg;
            String wxindexavgkey="wxindex_"+t1shownameavg;
            String tgiavgkey="showtgi_"+t1shownameavg;

            Showtype t1showtypeavg=allshowService.getShowtype(showtypeavgkey,t1shownameavg);
            List<Bdindex> bdindexavg=allshowService.getBdindex(bdindexavgkey,t1shownameavg);
            List<Wbindex> wbindexavg=allshowService.getWbindex(wbindexavgkey,t1shownameavg);
            List<Wxindex> wxindexavg=allshowService.getWxindex(wxindexavgkey,t1shownameavg);
            Showtgi showtgiavg=allshowService.getShowtgi(tgiavgkey,t1shownameavg);

            String customshowkey="customshow";
            String custombrandkey="custombrand";
            List<Showname> customshow=indexService.getCustomshow(customshowkey);
            List<Brandname> custombrand=indexService.getCustombrand(custombrandkey);


            Allbrand allbrand=new Allbrand(allshowrk,allbrandmax,brandtypemax,t1showtypemax,brandshowmax,brandtrendmax,bdindexmax,
                    wbindexmax,wxindexmax,showtgimax,allbrandavg,brandtypeavg,t1showtypeavg,brandshowavg,
                    brandtrendavg,bdindexavg,wbindexavg,wxindexavg,showtgiavg,bgdate,enddaten,customshow,custombrand);


            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|品牌排名与详情|Norm筛选"+"|@|";
            log.info(slog);
            return allbrand;


        }
        
        
        @RequestMapping("getbrand")
        @SysServiceLog(project="svc",model = "品牌排名详情")
        public Object getbrand(HttpServletRequest request){
            HttpSession session=request.getSession(); 
            String brandname=request.getParameter("brandname");
            log.info(brandname);
            Norm norm=(Norm)session.getAttribute("brandnorm");
            
            String brandtypekey="brandtype_"+brandname;
            String brandshowkey="brandshow_"+brandname+Normmap.normmap(norm);
            String brandtrendkey="brandtrend_"+brandname+Normmap.normmap(norm);

            Brandtype brandtype=allbrandService.getBrandtype(brandtypekey,brandname);
            List<Brandshow> brandshow=allbrandService.getBrandshow(brandshowkey,brandname,norm);
            List<Brandtrend> brandtrend=allbrandService.getBrandtrend(brandtrendkey,brandname,norm);

            
            String t1showname=brandshow.get(0).getShow();
            String t1showtypekey="showtype_"+t1showname;
            String bdindexkey="bdindex_"+t1showname;
            String wbindexkey="wbindex_"+t1showname;
            String wxindexkey="wxindex_"+t1showname;
            String tgikey="showtgi_"+t1showname;

            Showtype t1showtype=allshowService.getShowtype(t1showtypekey,t1showname);
            List<Bdindex> bdindex=allshowService.getBdindex(bdindexkey,t1showname);
            List<Wbindex> wbindex=allshowService.getWbindex(wbindexkey,t1showname);
            List<Wxindex> wxindex=allshowService.getWxindex(wxindexkey,t1showname);
            Showtgi showtgi=allshowService.getShowtgi(tgikey,t1showname);


            Allbrand allbrand=new Allbrand(brandtype,brandshow,brandtrend,t1showtype,bdindex,wbindex,wxindex,showtgi);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|品牌排名与详情|品牌搜索"+"|@|";
            log.info(slog);

            return allbrand;


        }

    @RequestMapping("getoneshow")
    @SysServiceLog(project="svc",model = "品牌排名详情")
    public Object getoneshow(HttpServletRequest request){
        HttpSession session=request.getSession();
        String showname=request.getParameter("showname");
        log.info(showname);

        String t1showtypekey="showtype_"+showname;
        String bdindexkey="bdindex_"+showname;
        String wbindexkey="wbindex_"+showname;
        String wxindexkey="wxindex_"+showname;
        String tgikey="showtgi_"+showname;


        Showtype t1showtype=allshowService.getShowtype(t1showtypekey,showname);
        List<Bdindex> bdindex=allshowService.getBdindex(bdindexkey,showname);
        List<Wbindex> wbindex=allshowService.getWbindex(wbindexkey,showname);
        List<Wxindex> wxindex=allshowService.getWxindex(wxindexkey,showname);
        Showtgi showtgi=allshowService.getShowtgi(tgikey,showname);


        Allbrand allbrand=new Allbrand(t1showtype,bdindex,wbindex,wxindex,showtgi);

        String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|品牌排名与详情|查看节目信息"+"|@|";
        log.info(slog);

        return allbrand;


    }



}

