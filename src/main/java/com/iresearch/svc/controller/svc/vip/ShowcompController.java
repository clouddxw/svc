package com.iresearch.svc.controller.svc.vip;

import com.iresearch.svc.aspect.SysServiceLog;
import com.iresearch.svc.bean.*;
import com.iresearch.svc.outbean.Showcomp;
import com.iresearch.svc.service.svc.AllbrandService;
import com.iresearch.svc.service.svc.AllshowService;
import com.iresearch.svc.service.svc.ShowcompService;
import com.iresearch.svc.constant.Normdefv;
import com.iresearch.svc.utils.Normmap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequestMapping("/api/svc/vip/showcomp")
@RestController
public class ShowcompController {
        @Resource
        private AllshowService allshowService;
        @Resource
        private AllbrandService allbrandService;
        @Resource
        private ShowcompService showcompService;


        @RequestMapping("get1st")
        @SysServiceLog(project="svc",model = "节目/品牌对比")
        public Object get1st(HttpServletRequest request) {
            HttpSession session=request.getSession();
            Norm norm=new Norm(Normdefv.sex,Normdefv.agegroup,Normdefv.citylevel,Normdefv.showtype,Normdefv.showlevel,
                    Normdefv.playplat, Normdefv.brandtype,Normdefv.sponsortype,Normdefv.begindate,Normdefv.enddate);
            session.setAttribute("showcompnorm", norm);


            String shownlistkey="allshowname_"+Normmap.normmap(norm);
            String brandnlistkey="allbrandname_"+Normmap.normmap(norm);
            List<Showname> shownlist=showcompService.getLshowname(shownlistkey,norm);
            List<Brandname> brandnlist=showcompService.getLbrandname(brandnlistkey,norm);

            Showcomp showcomp=new Showcomp();
            showcomp.setBrandnlist(brandnlist);
            showcomp.setShownlist(shownlist);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|打开页面"+"|@|";
            log.info(slog);

            return showcomp;
        }

        @RequestMapping("getnorm")
        @SysServiceLog(project="svc",model = "节目/品牌对比")
        public Object getnorm(String playplat,String showtype,String showlevel,String spontype,String brandtype,
                              String sex,String agegroup,String citylevel,String bgdate,String enddaten,HttpServletRequest request){
            HttpSession session=request.getSession();
            log.info(playplat+showtype+showlevel+spontype+brandtype+sex+agegroup+citylevel+bgdate+enddaten);
            java.sql.Date begindate = java.sql.Date.valueOf(bgdate);
            java.sql.Date enddate = java.sql.Date.valueOf(enddaten);
            Norm norm=new Norm(sex,agegroup,citylevel,showtype,showlevel,playplat,brandtype,spontype,begindate,enddate);
            session.setAttribute("showcompnorm", norm);

            String shownlistkey="allshowname_"+Normmap.normmap(norm);
            String brandnlistkey="allbrandname_"+Normmap.normmap(norm);
            List<Showname> shownlist=showcompService.getLshowname(shownlistkey,norm);
            List<Brandname> brandnlist=showcompService.getLbrandname(brandnlistkey,norm);

            Showcomp showcomp=new Showcomp();
            showcomp.setBgdate(bgdate);
            showcomp.setEnddate(enddaten);
            showcomp.setBrandnlist(brandnlist);
            showcomp.setShownlist(shownlist);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|Norm筛选"+"|@|";
            log.info(slog);

            return showcomp;


        }
        
        
        @RequestMapping("getshow")
        @SysServiceLog(project="svc",model = "节目/品牌对比")
        public Object getshow(String showname,HttpServletRequest request){
            HttpSession session=request.getSession();
            log.info(showname);
            Norm norm=(Norm)session.getAttribute("showcompnorm");
            String oneshowkey="oneshow_"+showname+Normmap.normmap(norm);
            String showtypekey="showtype_"+showname;
            Show oneshow=showcompService.getOneshow(oneshowkey,showname,norm);
            Showtype showtype=allshowService.getShowtype(showtypekey,showname);
            
            Showcomp showcomp=new Showcomp();
            showcomp.setShowtype(showtype);
            showcomp.setOneshow(oneshow);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|节目搜索"+"|@|";
            log.info(slog);

            return showcomp;

            
        }

        @RequestMapping("getbrand")
        @SysServiceLog(project="svc",model = "节目/品牌对比")
        public Object getbrand(String brandname,HttpServletRequest request){
            HttpSession session=request.getSession();
            Norm norm=(Norm)session.getAttribute("showcompnorm");

            String brandcompkey="brandcomp_"+brandname+Normmap.normmap(norm);
            String brandshowkey="brandshow_"+brandname+Normmap.normmap(norm);

            Brandcomp brandcomp=showcompService.getBrandcomp(brandcompkey,brandname,norm);
            List<Brandshow> brandshow=allbrandService.getBrandshow(brandshowkey,brandname,norm);

            String t1showname=brandshow.get(0).getShow();
            String showtypekey="showtype_"+t1showname;
            Showtype showtype=allshowService.getShowtype(showtypekey,t1showname);

            Showcomp showcomp=new Showcomp();
            showcomp.setBrandcomp(brandcomp);
            showcomp.setBrandshow(brandshow);
            showcomp.setShowtype(showtype);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|品牌搜索"+"|@|";
            log.info(slog);

            return showcomp;

        }

    @RequestMapping("getshowtype")
    @SysServiceLog(project="svc",model = "节目/品牌对比")
    public Object getshowtype(String showname,HttpServletRequest request){
        HttpSession session=request.getSession();
        String showtypekey="showtype_"+showname;
        Showtype showtype=allshowService.getShowtype(showtypekey,showname);
        Showcomp showcomp=new Showcomp();
        showcomp.setShowtype(showtype);
        String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|节目/品牌对比|查看节目详情"+"|@|";
        log.info(slog);
        return showcomp;
    }


}

