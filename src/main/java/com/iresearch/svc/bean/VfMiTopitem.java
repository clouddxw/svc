package com.iresearch.svc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VfMiTopitem {
    String brand;
    String category;
    Double sales;
    String items;
    String link;
    String tblink;
    Integer price;
    String discount;
}
