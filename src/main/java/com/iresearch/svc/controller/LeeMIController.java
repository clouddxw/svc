package com.iresearch.svc.controller;



import com.iresearch.svc.bean.ItemDemo;
import com.iresearch.svc.bean.LeeproductItem;
import com.iresearch.svc.mapper.vf.LeemiMapper;
import com.iresearch.svc.mapper.vf.VfmiMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Lee/MI/")
@Controller
public class LeeMIController {
    Logger logger = LoggerFactory.getLogger(AllbrandController.class);
    @Autowired(required = false)
    private LeemiMapper leemimapper;
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("getTopItem")
    @ResponseBody
    public Object getTopItem(HttpServletRequest request) throws UnsupportedEncodingException{
        String date=request.getParameter("date");
        String type=request.getParameter("type");
        String industry_en=new String(URLDecoder.decode(request.getParameter("industry_en")).getBytes("ISO-8859-1"),"UTF-8");
        String industry2=new String(URLDecoder.decode(request.getParameter("industry2")).getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(date+type+industry_en+industry2);
        String itemskey="leemi_proitems"+date+type+industry_en+industry2;
        logger.info(itemskey);
        List<LeeproductItem> items=new ArrayList<>();
        if(redisUtils.hasKey(itemskey)){
            items=(List<LeeproductItem>)redisUtils.get(itemskey);
        }else{
            items=leemimapper.getItems(date, type, industry_en,industry2);
            if(!items.isEmpty()){
                redisUtils.set(itemskey,items,3600);
            }
        }
        return  items;

    }





//    @GetMapping("getCbsTopItem")
//    @ResponseBody
//    public Object getCbsTopItem(HttpServletRequest request){
//        String type=request.getParameter("type");
//        String date=request.getParameter("date");
//        String itemskey="vfmi_cbsitems"+type+date;
//        logger.info(itemskey);
//        List<ItemDemo> items=new ArrayList<>();
//        if(redisUtils.hasKey(itemskey)){
//            items=(List<ItemDemo>)redisUtils.get(itemskey);
//        }else{
//            items=vfmimapper.getCbsItems(date, type);
//            if(!items.isEmpty()){
//                redisUtils.set(itemskey,items,3600);
//            }
//        }
//        return  items;
//    }

}
