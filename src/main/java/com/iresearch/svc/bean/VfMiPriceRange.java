package com.iresearch.svc.bean;

import lombok.Data;

@Data
public class VfMiPriceRange {
    String industry;
    String category_en;
    String price_range;
    Double sales_rate;
    Double sales_yoy;
}
