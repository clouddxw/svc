package com.iresearch.svc.controller.svc;

import com.iresearch.svc.aspect.SysServiceLog;
import com.iresearch.svc.bean.*;
import com.iresearch.svc.mapper.svc.IndexMapper;
import com.iresearch.svc.redis.RedisUtils;
import com.iresearch.svc.service.svc.IndexService;
import com.iresearch.svc.constant.Normdefv;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;



@Slf4j
@Controller
public class IndexController {
        @Resource
        private IndexService indexService;



        @RequestMapping("svc/index")
        @SysServiceLog(project="svc",model = "首页")
        public String Index(Model model,HttpServletRequest request){
            HttpSession session=request.getSession();
            Norm norm=new Norm(Normdefv.sex,Normdefv.agegroup,Normdefv.citylevel,Normdefv.showtype,Normdefv.showlevel,
                    Normdefv.playplat, Normdefv.brandtype,Normdefv.sponsortype,Normdefv.begindate,Normdefv.enddate);
            session.setAttribute("indexnorm", norm);

            String newskey="index_news";
            String customshowkey="customshow";
            String custombrandkey="custombrand";
            String showlibkey="showlib";

            List<News> newslist=indexService.getNews(newskey);
            List<Showname> customshow=indexService.getCustomshow(customshowkey);
            List<Brandname> custombrand=indexService.getCustombrand(custombrandkey);
            List<Showname> allshow=indexService.getLibshowname(showlibkey);

            model.addAttribute("allshow",allshow);
            model.addAttribute("custombrand",custombrand);
            model.addAttribute("customshow",customshow);
            model.addAttribute("newslist",newslist);

            String slog="|@|"+request.getRemoteAddr()+'|'+session.getAttribute("svcuser")+"|首页|打开页面"+"|@|";
            log.info(slog);

            return "svc/index";
        }








}

