package com.iresearch.svc.service.svc;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.svc.AllbrandMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllbrandService {
    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private AllbrandMapper allbrandMapper;
    private long expire_time=RedisContrasts.expire_time;


    public List<Brand> getAllbrand(String key,Norm norm){
        List<Brand> out=new ArrayList<>();

        if (redisUtils.hasKey(key)) {
            out =(List<Brand>)redisUtils.get(key);
        } else {
            out = allbrandMapper.getAllbrand(norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Brand> getAllbrand_Basic(String key,Norm norm){
        List<Brand> out=new ArrayList<>();

        if (redisUtils.hasKey(key)) {
            out =(List<Brand>)redisUtils.get(key);
        } else {
            out = allbrandMapper.getAllbrand_Basic(norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }


    public List<Brandtrend> getBrandtrend(String key,String brand,Norm norm){
        List<Brandtrend> out=new ArrayList<>();

        if (redisUtils.hasKey(key)) {
            out =(List<Brandtrend>)redisUtils.get(key);
        } else {
            out = allbrandMapper.getBrandtrend(brand,norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Brandtrend> getBrandtrend_Basic(String key,String brand,Norm norm){
        List<Brandtrend> out=new ArrayList<>();

        if (redisUtils.hasKey(key)) {
            out =(List<Brandtrend>)redisUtils.get(key);
        } else {
            out = allbrandMapper.getBrandtrend_Basic(brand,norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }


    public Brandtype getBrandtype(String key,String brand){
        Brandtype out;

        if (redisUtils.hasKey(key)) {
            out =(Brandtype) redisUtils.get(key);
        } else {
            out = allbrandMapper.getBrandtype(brand);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }


    public List<Brandshow> getBrandshow(String key,String brand,Norm norm){
        List<Brandshow> out=new ArrayList<>();

        if (redisUtils.hasKey(key)) {
            out =(List<Brandshow>)redisUtils.get(key);
        } else {
            out = allbrandMapper.getBrandshow(brand,norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Brandshow> getBrandshow_Basic(String key,String brand,Norm norm){
        List<Brandshow> out=new ArrayList<>();

        if (redisUtils.hasKey(key)) {
            out =(List<Brandshow>)redisUtils.get(key);
        } else {
            out = allbrandMapper.getBrandshow_Basic(brand,norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

}
