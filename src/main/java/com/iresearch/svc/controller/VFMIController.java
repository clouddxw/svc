package com.iresearch.svc.controller;



import com.iresearch.svc.bean.ItemDemo;
import com.iresearch.svc.bean.VfMiPriceRange;
import com.iresearch.svc.bean.VfMiTopitem;
import com.iresearch.svc.bean.VfMiTopstore;
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
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/VF/MI/")
@Controller
public class VFMIController {
    Logger logger = LoggerFactory.getLogger(AllbrandController.class);
    @Autowired(required = false)
    private VfmiMapper vfmimapper;
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("getTopItem")
    @ResponseBody
    public Object getTopItem(HttpServletRequest request){
        String brandtype=request.getParameter("brandtype");
        String type=request.getParameter("type");
        String date=request.getParameter("date");
        String itemskey="vfmi_proitems"+brandtype+type+date;
        //logger.info(itemskey);
        List<ItemDemo> items=new ArrayList<>();
        if(redisUtils.hasKey(itemskey)){
            items=(List<ItemDemo>)redisUtils.get(itemskey);
        }else{
            items=vfmimapper.getItems(brandtype, type, date);
            if(!items.isEmpty()){
                redisUtils.set(itemskey,items,3600);
            }
        }
        return  items;

    }

    @GetMapping("getCbsTopItem")
    @ResponseBody
    public Object getCbsTopItem(HttpServletRequest request){
        String type=request.getParameter("type");
        String date=request.getParameter("date");
        String itemskey="vfmi_cbsitems"+type+date;
        //logger.info(itemskey);
        List<ItemDemo> items=new ArrayList<>();
        if(redisUtils.hasKey(itemskey)){
            items=(List<ItemDemo>)redisUtils.get(itemskey);
        }else{
            items=vfmimapper.getCbsItems(date, type);
            if(!items.isEmpty()){
                redisUtils.set(itemskey,items,3600);
            }
        }
        return  items;
    }

    @GetMapping("getMiTopItems")
    @ResponseBody
    public Object getMiTopItems(HttpServletRequest request){
        String category=request.getParameter("category").replace("$","&");
        String date=request.getParameter("date");
        String itemskey="vfmi_topitems"+category+date;
        //logger.info(itemskey);
        List<VfMiTopitem> items=new ArrayList<>();
        if(redisUtils.hasKey(itemskey)){
            items=(List<VfMiTopitem>)redisUtils.get(itemskey);
        }else{
            items=vfmimapper.getMiTopItems(category, date);
            if(!items.isEmpty()){
                redisUtils.set(itemskey,items,3600);
            }
        }
        return  items;
    }

    @GetMapping("getMiTopStores")
    @ResponseBody
    public Object getMiTopStores(HttpServletRequest request){
        String category=request.getParameter("category").replace("$","&");
        String date=request.getParameter("date");
        String itemskey="vfmi_topstores"+category+date;
        //logger.info(itemskey);
        List<VfMiTopstore> items=new ArrayList<>();
        if(redisUtils.hasKey(itemskey)){
            items=(List<VfMiTopstore>)redisUtils.get(itemskey);
        }else{
            items=vfmimapper.getMiTopStores(category, date);
            if(!items.isEmpty()){
                redisUtils.set(itemskey,items,3600);
            }
        }
        return  items;
    }


    @GetMapping("getMiPirceRanges")
    @ResponseBody
    public Object getMiPirceRanges(HttpServletRequest request){
        String category=request.getParameter("category").replace("$","&");
        String date=request.getParameter("date");
        String itemskey="vfmi_priceranges"+category+date;
        //logger.info(itemskey);
        List<VfMiPriceRange> items=new ArrayList<>();
        if(redisUtils.hasKey(itemskey)){
            items=(List<VfMiPriceRange>)redisUtils.get(itemskey);
        }else{
            items=vfmimapper.getMiPirceRanges(category, date);
            if(!items.isEmpty()){
                redisUtils.set(itemskey,items,3600);
            }
        }
        return  items;
    }

}
