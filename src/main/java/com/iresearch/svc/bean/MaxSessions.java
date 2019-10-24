//package com.iresearch.svc.bean;
//
//import com.iresearch.svc.bean.SysUser;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
//
//public class MaxSessions {
//
//
//    public int getMaxSessions(){
//        try{
//            SysUser user=(SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//
//
//            if(user.getAuthorities().toString().indexOf("ROLE_USER")>0){
//                return 1;
//            }else{
//                return 20;
//            }
//
//        }catch(Exception  e){
//            return 20;
//
//        }
//
//    }
//}
