package com.iresearch.svc.service.svc;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.svc.ShowcompMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowcompService {

    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private ShowcompMapper showcompMapper;
    private long expire_time=RedisContrasts.expire_time;

    public List<Showname> getLshowname(String key, Norm norm){
        List<Showname> out=new ArrayList<>();

        if (redisUtils.hasKey(key)) {
            out =(List<Showname>)redisUtils.get(key);
        } else {
            out = showcompMapper.getLshowname(norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Brandname> getLbrandname(String key, Norm norm){
        List<Brandname> out=new ArrayList<>();

        if (redisUtils.hasKey(key)) {
            out =(List<Brandname>)redisUtils.get(key);
        } else {
            out = showcompMapper.getLbrandname(norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }


    public Show getOneshow(String key, String showname,Norm norm){
        Show out;

        if (redisUtils.hasKey(key)) {
            out =(Show)redisUtils.get(key);
        } else {
            out = showcompMapper.getOneshow(showname,norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public Brandcomp getBrandcomp(String key, String brandname,Norm norm){
        Brandcomp out;

        if (redisUtils.hasKey(key)) {
            out =(Brandcomp) redisUtils.get(key);
        } else {
            out = showcompMapper.getBrandcomp(brandname,norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

}
