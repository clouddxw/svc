package com.iresearch.svc.service.vf;

import com.alibaba.druid.sql.ast.expr.SQLCaseStatement;
import com.iresearch.svc.bean.ItemDemo;
import com.iresearch.svc.bean.VfMiPriceRange;
import com.iresearch.svc.bean.VfMiTopitem;
import com.iresearch.svc.bean.VfMiTopstore;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.vf.VfmiMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VfMiService {
    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private VfmiMapper vfmiMapper;
    private long expire_time=RedisContrasts.expire_time;

    public List<ItemDemo> getItems(String key,String brandtype,String type,String date){
        List<ItemDemo> out;
        if(redisUtils.hasKey(key)){
            out=(List<ItemDemo>)redisUtils.get(key);
        }else{
            out=vfmiMapper.getItems(brandtype,type,date);
            redisUtils.set(key,out,expire_time);
        }
        return  out;

    }

    public List<ItemDemo> getCbsItems(String key,String date,String type){
        List<ItemDemo> out;
        if(redisUtils.hasKey(key)){
            out=(List<ItemDemo>)redisUtils.get(key);
        }else{
            out=vfmiMapper.getCbsItems(date,type);
            redisUtils.set(key,out,expire_time);
        }
        return  out;
    }

    public List<VfMiTopitem> getMiTopItems(String key,String category,String date){
        List<VfMiTopitem> out;
        if(redisUtils.hasKey(key)){
            out=(List<VfMiTopitem>)redisUtils.get(key);
        }else{
            out=vfmiMapper.getMiTopItems(category,date);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<VfMiTopstore> getMiTopStores(String key,String category,String date){
        List<VfMiTopstore> out;
        if(redisUtils.hasKey(key)){
            out=(List<VfMiTopstore>)redisUtils.get(key);
        }else{
            out=vfmiMapper.getMiTopStores(category,date);
            redisUtils.set(key,out,expire_time);
        }
        return  out;
    }


    public List<VfMiPriceRange> getMiPirceRanges(String key,String category,String date){
        List<VfMiPriceRange> out;
        if(redisUtils.hasKey(key)){
            out=(List<VfMiPriceRange>)redisUtils.get(key);
        }else{
            out=vfmiMapper.getMiPirceRanges(category,date);
            redisUtils.set(key,out,expire_time);
        }
        return  out;
    }



}
