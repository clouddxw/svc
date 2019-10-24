package com.iresearch.svc.service.svc;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.svc.AllshowMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllshowService {
    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private AllshowMapper allshowMapper;
    private long expire_time=RedisContrasts.expire_time;


    public List<Show> getAllshow(String key,Norm norm){
        List<Show> out=new ArrayList<>();
        
        if (redisUtils.hasKey(key)) {
            out =(List<Show>)redisUtils.get(key);
        } else {
            out = allshowMapper.getAllshow(norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Show> getAllshow_Basic(String key,Norm norm){
        List<Show> out=new ArrayList<>();
        
        if (redisUtils.hasKey(key)) {
            out =(List<Show>)redisUtils.get(key);
        } else {
            out = allshowMapper.getAllshow_Basic(norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }


    public Showtype getShowtype(String key,String show){
        Showtype out=new Showtype();
        
        if (redisUtils.hasKey(key)) {
            out =(Showtype) redisUtils.get(key);
        } else {
            out = allshowMapper.getShowtype(show);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Showbrand> getShowbrand(String key,String show,Norm norm){
        List<Showbrand> out=new ArrayList<>();
        
        if (redisUtils.hasKey(key)) {
            out =(List<Showbrand>) redisUtils.get(key);
        } else {
            out = allshowMapper.getShowbrand(show,norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Showbrand> getShowbrand_Basic(String key,String show,Norm norm){
        List<Showbrand> out=new ArrayList<>();
        
        if (redisUtils.hasKey(key)) {
            out =(List<Showbrand>) redisUtils.get(key);
        } else {
            out = allshowMapper.getShowbrand_Basic(show,norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }


    public List<Bdindex> getBdindex(String key,String show){
        List<Bdindex> out=new ArrayList<>();
        
        if (redisUtils.hasKey(key)) {
            out =(List<Bdindex>) redisUtils.get(key);
        } else {
            out = allshowMapper.getBdindex(show);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Wbindex> getWbindex(String key,String show){
        List<Wbindex> out=new ArrayList<>();
        
        if (redisUtils.hasKey(key)) {
            out =(List<Wbindex>) redisUtils.get(key);
        } else {
            out = allshowMapper.getWbindex(show);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Wxindex> getWxindex(String key,String show){
        List<Wxindex> out=new ArrayList<>();
        
        if (redisUtils.hasKey(key)) {
            out =(List<Wxindex>) redisUtils.get(key);
        } else {
            out = allshowMapper.getWxindex(show);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }


    public Showtgi getShowtgi(String key,String show){
        Showtgi out=new Showtgi();
        
        if (redisUtils.hasKey(key)) {
            out =(Showtgi) redisUtils.get(key);
        } else {
            out = allshowMapper.getShowtgi(show);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }
    

}
