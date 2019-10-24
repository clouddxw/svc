package com.iresearch.svc.controller.vf;



import com.iresearch.svc.aspect.SysServiceLog;
import com.iresearch.svc.bean.ItemDemo;
import com.iresearch.svc.bean.VfMiPriceRange;
import com.iresearch.svc.bean.VfMiTopitem;
import com.iresearch.svc.bean.VfMiTopstore;
import com.iresearch.svc.service.vf.VfMiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping("/api/vf/mi/")
@RestController
public class VFMIApi {
    @Resource
    private VfMiService vfMiService;


    @RequestMapping("getTopItem")
    @SysServiceLog(project="vf",model = "MI Product")
    public Object getTopItem(String brandtype,String type,String date,HttpServletRequest request){
        String itemskey="vfmi_proitems"+brandtype+type+date;
        List<ItemDemo> items=vfMiService.getItems(itemskey,brandtype,type,date);
        return  items;

    }

    @RequestMapping("getCbsTopItem")
    @SysServiceLog(project="vf",model = "MI CBS Product")
    public Object getCbsTopItem(String type,String date,HttpServletRequest request){
        String itemskey="vfmi_cbsitems"+type+date;
        List<ItemDemo> items=vfMiService.getCbsItems(itemskey,date,type);
        return  items;
    }

    @RequestMapping("getMiTopItems")
    @SysServiceLog(project="vf",model = "MI Top Items")
    public Object getMiTopItems(String category,String date,HttpServletRequest request){
        String categoryf=category.replace("$","&");
        String itemskey="vfmi_topitems"+categoryf+date;
        List<VfMiTopitem> items=vfMiService.getMiTopItems(itemskey,categoryf,date);
        return  items;
    }

    @RequestMapping("getMiTopStores")
    @SysServiceLog(project="vf",model = "MI Top Stores")
    public Object getMiTopStores(String category,String date,HttpServletRequest request){
        String categoryf=category.replace("$","&");
        String itemskey="vfmi_topstores"+categoryf+date;
        List<VfMiTopstore> items=vfMiService.getMiTopStores(itemskey,categoryf,date);
        return  items;
    }


    @RequestMapping("getMiPirceRanges")
    @SysServiceLog(project="vf",model = "MI PirceRanges")
    public Object getMiPirceRanges(String category,String date,HttpServletRequest request){
        String categoryf=category.replace("$","&");
        String itemskey="vfmi_priceranges"+categoryf+date;
        List<VfMiPriceRange> items=vfMiService.getMiPirceRanges(itemskey,categoryf,date);
        return  items;
    }

}
