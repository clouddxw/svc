package com.iresearch.svc.outbean;

import com.sun.corba.se.spi.ior.ObjectKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Showcomp {
    private Object shownlist;
    private Object brandnlist;
    private Object bgdate;
    private Object enddate;
    private Object oneshow;
    private Object showtype;
    private Object brandshow;
    private Object brandcomp;

}
