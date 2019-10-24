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
public class Allbrand {
    private  Object allshowrk;
    private  Object allbrandmax;
    private  Object brandtypemax;
    private  Object	t1showtypemax;
    private  Object	brandshowmax;
    private  Object	brandtrendmax;
    private  Object	bdindexmax;
    private  Object	wbindexmax;
    private  Object	wxindexmax;
    private  Object	showtgimax;
    private  Object	allbrandavg;
    private  Object	brandtypeavg;
    private  Object	t1showtypeavg;
    private  Object	brandshowavg;
    private  Object	brandtrendavg;
    private  Object	bdindexavg;
    private  Object	wbindexavg;
    private  Object	wxindexavg;
    private  Object	showtgiavg;

    private  Object brandtype;
    private  Object brandshow;
    private  Object brandtrend;
    private  Object t1showtype;
    private  Object bdindex;
    private  Object wbindex;
    private  Object wxindex;
    private  Object showtgi;
    private  Object bgdate;
    private  Object endate;

    private  Object customshow;
    private  Object custombrand;


    public Allbrand(Object allshowrk, Object allbrandmax, Object brandtypemax, Object t1showtypemax, Object brandshowmax, Object brandtrendmax, Object bdindexmax, Object wbindexmax, Object wxindexmax, Object showtgimax, Object allbrandavg, Object brandtypeavg, Object t1showtypeavg, Object brandshowavg, Object brandtrendavg, Object bdindexavg, Object wbindexavg, Object wxindexavg, Object showtgiavg, Object customshow, Object custombrand) {
        this.allshowrk = allshowrk;
        this.allbrandmax = allbrandmax;
        this.brandtypemax = brandtypemax;
        this.t1showtypemax = t1showtypemax;
        this.brandshowmax = brandshowmax;
        this.brandtrendmax = brandtrendmax;
        this.bdindexmax = bdindexmax;
        this.wbindexmax = wbindexmax;
        this.wxindexmax = wxindexmax;
        this.showtgimax = showtgimax;
        this.allbrandavg = allbrandavg;
        this.brandtypeavg = brandtypeavg;
        this.t1showtypeavg = t1showtypeavg;
        this.brandshowavg = brandshowavg;
        this.brandtrendavg = brandtrendavg;
        this.bdindexavg = bdindexavg;
        this.wbindexavg = wbindexavg;
        this.wxindexavg = wxindexavg;
        this.showtgiavg = showtgiavg;
        this.customshow = customshow;
        this.custombrand = custombrand;
    }

    public Allbrand(Object allshowrk, Object allbrandmax, Object brandtypemax, Object t1showtypemax, Object brandshowmax, Object brandtrendmax, Object bdindexmax, Object wbindexmax, Object wxindexmax, Object showtgimax, Object allbrandavg, Object brandtypeavg, Object t1showtypeavg, Object brandshowavg, Object brandtrendavg, Object bdindexavg, Object wbindexavg, Object wxindexavg, Object showtgiavg, Object bgdate, Object endate, Object customshow, Object custombrand) {
        this.allshowrk = allshowrk;
        this.allbrandmax = allbrandmax;
        this.brandtypemax = brandtypemax;
        this.t1showtypemax = t1showtypemax;
        this.brandshowmax = brandshowmax;
        this.brandtrendmax = brandtrendmax;
        this.bdindexmax = bdindexmax;
        this.wbindexmax = wbindexmax;
        this.wxindexmax = wxindexmax;
        this.showtgimax = showtgimax;
        this.allbrandavg = allbrandavg;
        this.brandtypeavg = brandtypeavg;
        this.t1showtypeavg = t1showtypeavg;
        this.brandshowavg = brandshowavg;
        this.brandtrendavg = brandtrendavg;
        this.bdindexavg = bdindexavg;
        this.wbindexavg = wbindexavg;
        this.wxindexavg = wxindexavg;
        this.showtgiavg = showtgiavg;
        this.bgdate = bgdate;
        this.endate = endate;
        this.customshow = customshow;
        this.custombrand = custombrand;
    }


    public Allbrand(Object brandtype, Object brandshow, Object brandtrend, Object t1showtype, Object bdindex, Object wbindex, Object wxindex, Object showtgi) {
        this.brandtype = brandtype;
        this.brandshow = brandshow;
        this.brandtrend = brandtrend;
        this.t1showtype = t1showtype;
        this.bdindex = bdindex;
        this.wbindex = wbindex;
        this.wxindex = wxindex;
        this.showtgi = showtgi;
    }


    public Allbrand(Object t1showtype, Object bdindex, Object wbindex, Object wxindex, Object showtgi) {
        this.t1showtype = t1showtype;
        this.bdindex = bdindex;
        this.wbindex = wbindex;
        this.wxindex = wxindex;
        this.showtgi = showtgi;
    }
}
