package com.iresearch.svc.service.vf;

import com.iresearch.svc.bean.LeeproductItem;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.vf.LeemiMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LeemiService {
    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private LeemiMapper leemiMapper;
    private long expire_time=RedisContrasts.expire_time;

    public List<LeeproductItem> getItems(String key, String date,String type,String industry_en,String industry2){
        List<LeeproductItem> out;
        if(redisUtils.hasKey(key)){
            out=(List<LeeproductItem>)redisUtils.get(key);
        }else{
            out=leemiMapper.getItems(date, type, industry_en, industry2);
            redisUtils.set(key,out,expire_time);
        }
        return  out;

    }


}
