package com.iresearch.svc.mapper.svc;

import com.iresearch.svc.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AllshowMapper {

    List<Show> getAllshow(Norm norm);
    List<Show> getAllshow_Basic(Norm norm);
    Showtype getShowtype(String show);
    /**
     *
     * @param show
     * @param norm
     * @return
     */
    public List<Showbrand> getShowbrand(@Param("show") String show, @Param("norm")Norm norm);
    public List<Showbrand> getShowbrand_Basic(@Param("show") String show, @Param("norm")Norm norm);
    //List<Showbrand> getShowbrand(String show,Norm norm);
    List<Bdindex> getBdindex(String showname);
    List<Wbindex> getWbindex(String showname);
    List<Wxindex> getWxindex(String showname);
    Showtgi getShowtgi(String show);



}