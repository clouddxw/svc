package com.iresearch.svc.mapper.vf;

import com.iresearch.svc.bean.ItemDemo;
import com.iresearch.svc.bean.VfMiPriceRange;
import com.iresearch.svc.bean.VfMiTopitem;
import com.iresearch.svc.bean.VfMiTopstore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VfmiMapper {

    List<ItemDemo> getItems(@Param("brandtype") String brandtype,@Param("type") String type,@Param("date") String date);
    List<ItemDemo> getCbsItems(@Param("date") String date,@Param("type") String type);
    List<VfMiTopitem> getMiTopItems(@Param("category") String category, @Param("date") String date);
    List<VfMiTopstore> getMiTopStores(@Param("category") String category, @Param("date") String date);
    List<VfMiPriceRange> getMiPirceRanges(@Param("category") String category, @Param("date") String date);

    
}

