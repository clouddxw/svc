package com.iresearch.svc.service.svc;

import com.iresearch.svc.bean.Norm;
import com.iresearch.svc.bean.Scshow;
import com.iresearch.svc.bean.Showbranddt;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.svc.ShowcustomMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowcustomService {
    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private ShowcustomMapper showcustomMapper;
    private long expire_time=RedisContrasts.expire_time;


    public Scshow getShow(String key,String showname,Norm norm){
        Scshow out;
        if (redisUtils.hasKey(key)){
            out=(Scshow)redisUtils.get(key);
        }else{
            out=showcustomMapper.getShow(showname,norm);
            redisUtils.set(key,out,expire_time);
        }

        return out;
    }

    public List<Scshow> getSshow(String key,String showname,Norm norm){
        List<Scshow> out=new ArrayList<>();
        if (redisUtils.hasKey(key)){
            out=(List<Scshow>)redisUtils.get(key);
        }else{
            out=showcustomMapper.getSshow(showname,norm);
            redisUtils.set(key,out,expire_time);
        }

        return out;
    }


    public List<Showbranddt> getShowbranddt(String key,String showname,Norm norm){
        List<Showbranddt> out=new ArrayList<>();
        if (redisUtils.hasKey(key)){
            out=(List<Showbranddt>)redisUtils.get(key);
        }else{
            out=showcustomMapper.getShowbranddt(showname,norm);
            redisUtils.set(key,out,expire_time);
        }

        return out;
    }

}
