package com.iresearch.svc.mapper.svc;

import com.iresearch.svc.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShowlibMapper {
    List<Libshowbd> getShowbd(String  showname);
    /**
     * @param  showname
     * @norm   norm
     * @return
     */
    public List<Showsimilar> getShowsimilar(@Param("showname") String showname, @Param("norm") Norm norm);
}
