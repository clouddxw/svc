package com.iresearch.svc.service.svc;

import com.iresearch.svc.bean.Brandname;
import com.iresearch.svc.bean.News;
import com.iresearch.svc.bean.Show;
import com.iresearch.svc.bean.Showname;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.svc.IndexMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {
    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private IndexMapper indexMapper;
    private long expire_time=RedisContrasts.expire_time;


    public List<News> getNews(String key){
        List<News> out=new ArrayList<>();
        if (redisUtils.hasKey(key)) {
            out =(List<News>)redisUtils.get(key);
        } else {
            out = indexMapper.getNews();
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Showname> getCustomshow(String key){
        List<Showname> out=new ArrayList<>();
        if (redisUtils.hasKey(key)) {
            out =(List<Showname>)redisUtils.get(key);
        } else {
            out = indexMapper.getCustomshow();
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Brandname> getCustombrand(String key){
        List<Brandname> out=new ArrayList<>();
        if (redisUtils.hasKey(key)) {
            out =(List<Brandname>)redisUtils.get(key);
        } else {
            out = indexMapper.getCustombrand();
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }

    public List<Showname> getLibshowname(String key){
        List<Showname> out=new ArrayList<>();
        if (redisUtils.hasKey(key)) {
            out =(List<Showname>)redisUtils.get(key);
        } else {
            out = indexMapper.getLibshowname();
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }


}
