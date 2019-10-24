package com.iresearch.svc.controller.vf;



import com.iresearch.svc.aspect.SysServiceLog;
import com.iresearch.svc.bean.LeeproductItem;
import com.iresearch.svc.mapper.vf.LeemiMapper;
import com.iresearch.svc.redis.RedisUtils;
import com.iresearch.svc.service.vf.LeemiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/api/lee/mi/")
@RestController
public class LeeMIController {
    @Resource
    private LeemiService leemiService;


    @RequestMapping("getTopItem")
    @SysServiceLog(project="lee",model = "MI Product")
    public Object getTopItem(String date,String type,HttpServletRequest request) throws UnsupportedEncodingException {
        String industry_en=new String(request.getParameter("industry_en").getBytes("ISO-8859-1"),"UTF-8");
        String industry2=new String(request.getParameter("industry2").getBytes("ISO-8859-1"),"UTF-8");
        log.info(date+type+industry_en+industry2);

        String itemskey="leemi_proitems"+date+type+industry_en+industry2;
        List<LeeproductItem> items=leemiService.getItems(itemskey,date,type,industry_en,industry2);
        return  items;

    }


}
