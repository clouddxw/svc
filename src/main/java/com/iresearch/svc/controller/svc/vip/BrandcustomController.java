package com.iresearch.svc.controller.svc.vip;

import com.iresearch.svc.aspect.SysServiceLog;
import com.iresearch.svc.bean.*;
import com.iresearch.svc.outbean.Brandcustom;
import com.iresearch.svc.service.svc.AllbrandService;
import com.iresearch.svc.service.svc.AllshowService;
import com.iresearch.svc.service.svc.BrandcustomService;
import com.iresearch.svc.service.svc.ShowcompService;
import com.iresearch.svc.constant.Normdefv;
import com.iresearch.svc.utils.Normmap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RequestMapping("/api/svc/vip/brandcustom")
@RestController
public class BrandcustomController {
        @Resource
        private AllshowService allshowService;
        @Resource
        private BrandcustomService brandcustomService;
        @Resource
        private AllbrandService allbrandService;
        @Resource
        private ShowcompService showcompService;




        @RequestMapping("get1st")
        @SysServiceLog(project="svc",model = "定制节目详情")
        public Object get1st(HttpServletRequest request) throws UnsupportedEncodingException{
            HttpSession session=request.getSession();
            String brandname=new String(request.getParameter("brandname").getBytes("ISO-8859-1"),"UTF-8");
            Norm norm=new Norm(Normdefv.sex,Normdefv.agegroup,Normdefv.citylevel,Normdefv.showtype,Normdefv.showlevel,
                    Normdefv.playplat, Normdefv.brandtype,Normdefv.sponsortype,Normdefv.begindate,Normdefv.enddate);
            session.setAttribute("brandcustomnorm", norm);

            
            String brandshowkey="brandshow_"+brandname+Normmap.normmap(norm);
            String brandshowdtkey="brandshowdt_"+brandname+Normmap.normmap(norm);
            List<Brandshow> brandshow=allbrandService.getBrandshow(brandshowkey,brandname,norm);
            List<Brandshowdt> brandshowdts=brandcustomService.getBrandshowdt(brandshowdtkey,brandname,norm);

            String t1showname=brandshow.get(0).getShow();
            String allshowrkkey="allshowrk_"+Normmap.normmap(norm);
            String brandtypekey="brandtype_"+brandname;
            String t1showtypekey="showtype_"+t1showname;
            String bdindexkey="bdindex_"+t1showname;
            String wbindexkey="wbindex_"+t1showname;
            String wxindexkey="wxindex_"+t1showname;
            String tgikey="showtgi_"+t1showname;

            List<Showname> allshowrk=showcompService.getLshowname(allshowrkkey,norm);
            Brandtype brandtype=allbrandService.getBrandtype(brandtypekey,brandname);
            Showtype t1showtype=allshowService.getShowtype(t1showtypekey,t1showname);
            List<Bdindex> bdindex=allshowService.getBdindex(bdindexkey,t1showname);
            List<Wbindex> wbindex=allshowService.getWbindex(wbindexkey,t1showname);
            List<Wxindex> wxindex=allshowService.getWxindex(wxindexkey,t1showname);
            Showtgi showtgi=allshowService.getShowtgi(tgikey,t1showname);


            //log.info(allshowrk.get(0).getSvcavgrk());
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
            log.info(slog);

            return brandcustom;


            
        }

        @RequestMapping("getnorm")
        @SysServiceLog(project="svc",model = "定制节目详情")
        public Object getnorm(String playplat,String showtype,String showlevel,String spontype,
                              String sex,String agegroup,String citylevel,String bgdate,String enddaten,
                              String brandname,HttpServletRequest request) throws UnsupportedEncodingException{
            String brandtypei=request.getParameter("brandtype");
            HttpSession session=request.getSession();
            log.info(playplat+showtype+showlevel+spontype+brandtypei+sex+agegroup+citylevel+bgdate+enddaten);
            java.sql.Date begindate = java.sql.Date.valueOf(bgdate);
            java.sql.Date enddate = java.sql.Date.valueOf(enddaten);
            Norm norm=new Norm(sex,agegroup,citylevel,showtype,showlevel,playplat,brandtypei,spontype,begindate,enddate);
            session.setAttribute("brandcustomnorm", norm);

            String brandshowkey="brandshow_"+brandname+Normmap.normmap(norm);
            String brandshowdtkey="brandshowdt_"+brandname+Normmap.normmap(norm);
            List<Brandshow> brandshow=allbrandService.getBrandshow(brandshowkey,brandname,norm);
            List<Brandshowdt> brandshowdts=brandcustomService.getBrandshowdt(brandshowdtkey,brandname,norm);

            String t1showname=brandshow.get(0).getShow();
            String allshowrkkey="allshowrk_"+Normmap.normmap(norm);
            String brandtypekey="brandtype_"+brandname;
            String t1showtypekey="showtype_"+t1showname;
            String bdindexkey="bdindex_"+t1showname;
            String wbindexkey="wbindex_"+t1showname;
            String wxindexkey="wxindex_"+t1showname;
            String tgikey="showtgi_"+t1showname;

            List<Showname> allshowrk=showcompService.getLshowname(allshowrkkey,norm);
            Brandtype brandtype=allbrandService.getBrandtype(brandtypekey,brandname);
            Showtype t1showtype=allshowService.getShowtype(t1showtypekey,t1showname);
            List<Bdindex> bdindex=allshowService.getBdindex(bdindexkey,t1showname);
            List<Wbindex> wbindex=allshowService.getWbindex(wbindexkey,t1showname);
            List<Wxindex> wxindex=allshowService.getWxindex(wxindexkey,t1showname);
            Showtgi showtgi=allshowService.getShowtgi(tgikey,t1showname);


            Brandcustom brandcustom=new Brandcustom();
            brandcustom.setBgdate(bgdate);
            brandcustom.setEnddate(enddaten);
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
            log.info(slog);

            return brandcustom;

        }

    @RequestMapping("getshowdt")
    @SysServiceLog(project="svc",model = "定制节目详情")
    public Object getshowdt(String showname,HttpServletRequest request){
        HttpSession session=request.getSession();
        String showtypekey="showtype_"+showname;
        String bdindexkey="bdindex_"+showname;
        String wbindexkey="wbindex_"+showname;
        String wxindexkey="wxindex_"+showname;
        String tgikey="showtgi_"+showname;


        Showtype showtype=allshowService.getShowtype(showtypekey,showname);
        List<Bdindex> bdindex=allshowService.getBdindex(bdindexkey,showname);
        List<Wbindex> wbindex=allshowService.getWbindex(wbindexkey,showname);
        List<Wxindex> wxindex=allshowService.getWxindex(wxindexkey,showname);
        Showtgi showtgi=allshowService.getShowtgi(tgikey,showname);

        Brandcustom brandcustom=new Brandcustom();
        brandcustom.setShowtype(showtype);
        brandcustom.setBdindex(bdindex);
        brandcustom.setWbindex(wbindex);
        brandcustom.setWxindex(wxindex);
        brandcustom.setShowtgi(showtgi);

        String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|定制品牌详情|查看节目详情"+"|@|";
        log.info(slog);

        return brandcustom;
            
    }



}

