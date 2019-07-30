package com.iresearch.svc.mapper.svc;

import com.iresearch.svc.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexMapper {

    List<News> getNews();
    List<Showname> getCustomshow();
    List<Brandname> getCustombrand();
    List<Showname> getLibshowname();

}