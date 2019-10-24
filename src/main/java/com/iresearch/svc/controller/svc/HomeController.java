package com.iresearch.svc.controller.svc;

import com.iresearch.svc.aspect.SysServiceLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
//    @RequestMapping("svc/index")
//    public String Index(Model model){
//
//        return "svc/index";
//    }

    @RequestMapping("svc/vip/allshow")
    public String VipAllShow(){
        return "svc/vip/allshow";
    }
    @RequestMapping("svc/vip/allbrand")
    public String VipAllBrand(){
        return "svc/vip/allbrand";
    }
    @RequestMapping("svc/vip/showcomp")
    public String VipShowComp(){
        return "svc/vip/showcomp";
    }
    @RequestMapping("svc/vip/showcustom")
    public String VipShowCustom(){
        return "svc/vip/showcustom";
    }
    @RequestMapping("svc/vip/brandcustom")
    public String VipBrandCustom(){
        return "svc/vip/brandcustom";
    }
    @RequestMapping("svc/vip/showlib")
    public String VipShowlib(){
        return "svc/vip/showlib";
    }

    @RequestMapping("svc/basic/allshow")
    public String BasicAllShow(){
        return "svc/basic/allshow";
    }
    @RequestMapping("svc/basic/allbrand")
    public String BasicAllBrand(){
        return "svc/basic/allbrand";
    }

    @RequestMapping("svc/versioncontrast")
    @SysServiceLog(project="svc",model = "版本对比")
    public String VersionContrast(){
        return "svc/versioncontrast";
    }

    @RequestMapping("logout")
    public String Logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
        return "login.html";
    }


    @RequestMapping("login.html")
    public String Login(){
        return "login";
    }




}
