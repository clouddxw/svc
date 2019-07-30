var filnormcon=divfilnorm();
$('#filter').html(filnormcon[0]);
$('#normcon').html(filnormcon[1]);
setDate();



$.ajax({
    url: 'http://svc.iresearch.cn/allshow/get1st',
    type: 'get',
    dataType: 'json',
    //data:data,
    jsonp: 'callback',
    async: false,
    success: function (data) {
        //callback(r);
			dataList(data);
    },
    error: function(xhr,status) {
        var sessionStatus = xhr.getResponseHeader('sessionstatus');
        if(sessionStatus == 'timeout') {
            var top = getTopWinow();
            var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
            if (yes) {
                top.location.href = 'http://svc.iresearch.cn/login.html';
            }
        }else{
            var errorE = '抱歉，没有找到符合您要求的数据!';
            alert(errorE);
        }
    },
});
//}

function getnorm() {
    //alert($('#formn').serialize());
    $.ajax({
        url: "http://svc.iresearch.cn/allshow/getnorm",
        type: 'post',
        dataType: 'json',
        data: $('#formn').serialize(),
        jsonp: 'callback',
        async: true,
        success: function (data) {
            //callback(r);
            dataList(data);
        },
    });
}


function divshowsv(show,showtype,tbcon1,tbcon2,disp){
	try{
        var svcrk=0;
        var svc=0;
        var showval=0;
        var showbdrel=0;
        var brandpro=0;

        if (disp=="max"){
            svcrk=show.svcmaxrk;
            svc=show.svcmax;
            showval=show.showvalmax;
            showbdrel=show.showbdrelmax;
            brandpro=show.brandpromax;
        }else if(disp="avg"){
            svcrk=show.svcavgrk;
            svc=show.svcavg;
            showval=show.showvalavg;
            showbdrel=show.showbdrelavg;
            brandpro=show.brandproavg;

        }


        var Con='<div class="showsurvey_l">'+
            '<div class="showsurvey_l1">'+show.show+'</div>'+
            '<div class="showsurvey_l2"><img src="'+show.photo+'" style="width:100%;height:100%"></div>'+
            '<ul  class="showsurvey_l3">'+
            '<li><span>SVC综合指数排名</span>：'+svcrk+'</li>'+
            '<li><span>节目流量等级</span>：'+showtype.showlevel+'</li>    '+
            '<li><span>终端</span>：'+showtype.type+'</li>'+
            '<li><span>平台</span>：'+showtype.platform+'</li>'+
            '<li><span>类型</span>：'+showtype.showtype+'</li>'+
            '<li><span>首播</span>：'+showtype.begindate+'</li>'+
            '<li><span>期数</span>：'+showtype.episode+'</li>'+
            '<li><span>监测植入品牌数</span>：'+show.brandnum+'</li>  '+
            '</ul>'+
            '</div>'+
            '<div class="showsurvey_r">'+
            '<div class="showsurvey_r1"><span>'+svc+'</span>&nbsp(SVC综合指数)</div>'+
            '<ul class="showsurvey_r2">'+
            '<li><span>'+showval+'</span><br/>节目价值指数</li>'+
            '<li><span>'+showbdrel+'</span><br/>节目-品牌关联指数</li>'+
            '<li><span>'+brandpro+'</span><br/>品牌资产提升指数</li>'+
            '</ul>'+
            '<div class="showsurvey_r3">'+
            '<div class="showsurvey_r3l">植入品牌SVC综合指数:</div>'+
            '<div class="spontype"><div class="starimg" ><img src="http://svc.iresearch.cn/img/star2.png" ></div>其他</div>'+
            '<div class="spontype"><div class="starimg" ><img src="http://svc.iresearch.cn/img/star1.png" ></div>总冠名</div>'+
            '</div>'+
            '<div class="showsurvey_r4">'+
            '<div class="showsurvey_r4l">'+
            '<table class="table  table-striped" >'+tbcon1+
            '</tbody>'+
            '</table>'+
            '</div>'+
            '<div class="showsurvey_r4r">'+
            '<table class="table  table-striped" >'+tbcon2+
            '</tbody>'+
            '</table>'+
            '</div>'+
            '</div>'+
            '</div>';
        return Con;
	}catch(TypeError) {
		console.log("节目排行数据类型出错")
    }

}

function divshowdt(show,showtype,linkcon,disp){
	try{
        var svcrk=0;
        var svc=0;
        var showval=0;
        var showbdrel=0;
        var brandpro=0;
        var wtindex=0;
        var rwindex=0;
        var kbindex=0;
        var rzupindex=0;
        var xaupindex=0;
        var tjupindex=0;
        var rmindex=0;
        var relindex=0;

        if (disp=="max"){
            svcrk=show.svcmaxrk;
            svc=show.svcmax;
            showval=show.showvalmax;
            showbdrel=show.showbdrelmax;
            brandpro=show.brandpromax;
            wtindex=show.wtindexmax;
            rwindex=show.rwindexmax;
            kbindex=show.kbindexmax;
            rzupindex=show.rzupindexmax;
            xaupindex=show.xaupindexmax;
            tjupindex=show.tjupindexmax;
            rmindex=show.rmindexmax;
            relindex=show.relindexmax;
        }else if(disp="avg"){
            svcrk=show.svcavgrk;
            svc=show.svcavg;
            showval=show.showvalavg;
            showbdrel=show.showbdrelavg;
            brandpro=show.brandproavg;
            wtindex=show.wtindexavg;
            rwindex=show.rwindexavg;
            kbindex=show.kbindexavg;
            rzupindex=show.rzupindexavg;
            xaupindex=show.xaupindexavg;
            tjupindex=show.tjupindexavg;
            rmindex=show.rmindexavg;
            relindex=show.relindexavg;

        }

        var Con='<div class="box_l">'+
            '		<div class="box_l1">'+show.show+'</div>'+
            '		<div class="box_l2"><img src="'+show.photo+'" style="width:100%;height:100%"></div>'+
            '		<ul class="box_l3">'+
            '			<li><span>SVC综合指数排名：</span>'+svcrk+'</li>'+
            '			<li><span>节目流量等级：</span>'+showtype.showlevel+'</li>'+
            '			<li><span>首播：</span>'+showtype.begindate+'</li>'+
            '			<li><span>期数：</span>'+showtype.episode+'</li>'+
            '			<li><span>终端：</span>'+showtype.type+'</li>'+
            '			<li><span>平台：</span>'+showtype.platform+'</li>'+
            '			<li><span>类型：</span>'+showtype.showtype+'</li>'+
            '		</ul>'+
            '</div>'+
            '<div class="box_r">'+
            '	<div class="box_r1">'+
            '		<div class="box_r1l"><span>'+svc+'</span>&nbsp&nbsp&nbsp&nbsp(SVC综合指数)</div>'+
            '		<div class="box_r1r">'+linkcon+'</div>'+
            '	</div>'+
            '	<ul class="box_r2">'+
            '		<li style="width:80px;line-height:22.5px;color:#7e7e7e;">二<br/>级<br/>指<br/>数</li>'+
            '		<li><span>'+showval+'</span><br/>节目价值</li>'+
            '		<li><span>'+showbdrel+'</span><br/>节目-品牌关联</li>'+
            '		<li><span>'+brandpro+'</span><br/>品牌资产提升</li>'+
            '	</ul>'+
            '	<ul class="box_r3">'+
            '		<li style="width:80px;line-height:22.5px;color:#7e7e7e;">三<br />级<br />指<br />数</li>'+
            '		<li><span>'+wtindex+'</span>&nbsp&nbsp收视热度<br /><span>'+rwindex+'</span>&nbsp&nbsp重复收视<br><span>'+kbindex+'</span>&nbsp&nbsp口碑传播</li>'+
            '		<li><span>'+rmindex+'</span>&nbsp&nbsp回忆度<br/><span>'+relindex+'</span>&nbsp&nbsp相关度<br></li>'+
            '		<li><span>'+rzupindex+'</span>&nbsp&nbsp认知提升<br/><span>'+xaupindex+'</span>&nbsp&nbsp喜爱提升<br><span>'+tjupindex+'</span>&nbsp&nbsp推荐提升</li>'+
            '	</ul>'+
            '</div>'
        return Con;

	}catch(TypeError) {
        console.log("节目详情数据类型出错")
    }

}



function divshowbdr(t1brand,linkcon){
    try{
        var Con='<div style="overflow:hidden;">'+
            '<div class="showbrand_r1">'+linkcon+'</div>'+
            '<div class="t1brandl" >'+
            '<ul class="top1brandl" style="color:#7e7f7e;padding:0px 20px;margin-top:5px;">'+
            '	<li class="uli1" >'+
            '		<div class="imag" ><img src=\''+t1brand.logo+'\' style="width:100%;height:100%;"></div>'+
            '		<div style="display: float;"><div style="height:40px;line-height:55px;font-size:22px;">'+t1brand.brand+'</div><div style="height:36px;line-height:20px;font-size:15px;">'+t1brand.sponsortype+'</div></div>'+
            '	</li >'+
            '	<li class="uli2" >'+
            '		<div >'+t1brand.svc+'</div>&nbsp&nbspSVC综合指数 '+
            '	</li>'+
            '	<li class="uli3" >'+
            '		<span class="l">SVC二级指数</span>'+
            '		<span class="r">SVC三级指数</span>'+
            '	</li>'+
            '	<li class="uli4" >'+
            '		<div class="firtag" >'+
            '			<div class="score" >'+t1brand.showval+'</div>'+
            '			<div class="tag" >节目价值</div>'+
            '		</div>'+
            '		<ul style="">'+
            '			<li ><span>'+t1brand.wtindex+'</span>&nbsp&nbsp收视热度</li>'+
            '			<li ><span>'+t1brand.rwindex+'</span>&nbsp&nbsp重复收视</li>'+
            '			<li ><span>'+t1brand.kbindex+'</span>&nbsp&nbsp口碑传播</li>'+
            '		</ul>'+
            '	</li>'+
            '	<li class="uli5">'+
            '		<div class="firtag" >'+
            '			<div class="score" >'+t1brand.showbdrel+'</div>'+
            '			<div class="tag" >节目-品牌关联</div>'+
            '		</div>'+
            '		<ul >'+
            '			<li ><span>'+t1brand.rmindex+'</span>&nbsp&nbsp回忆度</li>'+
            '			<li ><span>'+t1brand.relindex+'</span>&nbsp&nbsp相关度</li>'+
            '		</ul>'+
            '	</li>'+
            '	<li class="uli6" >'+
            '		<div class="firtag" style="width:50%;">'+
            '			<div class="score" >'+t1brand.brandpro+'</div>'+
            '			<div class="tag" >品牌资产提升</div>'+
            '		</div>'+
            '		<ul style="width:50%;">'+
            '			<li style=""><span>'+t1brand.rzupindex+'</span>&nbsp&nbsp认知提升</li>'+
            '			<li ><span>'+t1brand.xaupindex+'</span>&nbsp&nbsp喜爱提升</li>'+
            '			<li ><span>'+t1brand.tjupindex+'</span>&nbsp&nbsp推荐提升</li>'+
            '		</ul>'+
            '	</li>'+
            '</ul>'+
            '</div>'+
            '</div>';

        return Con;

    }catch(TypeError) {
        console.log("节目品牌数据类型出错")
    }


}

function divshowtb(showbrand,disp){
    try{
        var con1='';
        var con2='';
        var con3='';
        for (var i = 0; i < showbrand.length; i++) {

            if (showbrand[i].sponsortype=="总冠名"){
                con1+='<tr>'+
                    '<td style="width:160px"><a  onclick="getbrand(\''+showbrand[i].brand+'\',\''+disp+'\')">'+showbrand[i].brand+'</a></td>'+
                    '<td style="width:50px"><img src="http://svc.iresearch.cn/img/star1.png" style="width:20px;height:20px"></td>'+
                    '<td style="width:90px">'+showbrand[i].episodenum+'</td>'+
                    '<td style="width:110px">'+showbrand[i].svc+'</td>'+
                    '<td style="width:90px">'+showbrand[i].showval+'</td>'+
                    '<td style="width:120px">'+showbrand[i].showbdrel+'</td>'+
                    '<td style="width:120px">'+showbrand[i].brandpro+'</td>'+
                    '</tr>';
                if(i<=4){
                    con2+='<tr><td style="height:44px;padding-left:5px;">' + showbrand[i].brand + '</td><td> <img src="http://svc.iresearch.cn/img/star1.png" style="width:20px;height:20px"></td><td align="right" style="height:44px;padding-left:5px;color:#1d914d;">'+showbrand[i].svc+'</td></tr>';
                }else{
                    con3+='<tr><td style="height:44px;padding-left:5px;">' + showbrand[i].brand + '</td><td> <img src="http://svc.iresearch.cn/img/star1.png" style="width:20px;height:20px"></td><td align="right" style="height:44px;padding-left:5px;color:#1d914d;">'+showbrand[i].svc+'</td></tr>';
                }
            }else{
                con1+='<tr>'+
                    '<td style="width:160px"><a  onclick="getbrand(\''+showbrand[i].brand+'\',\''+disp+'\')">'+showbrand[i].brand+'</a></td>'+
                    '<td style="width:50px"><img src="http://svc.iresearch.cn/img/star2.png" style="width:20px;height:20px"></td>'+
                    '<td style="width:90px">'+showbrand[i].episodenum+'</td>'+
                    '<td style="width:110px">'+showbrand[i].svc+'</td>'+
                    '<td style="width:90px">'+showbrand[i].showval+'</td>'+
                    '<td style="width:120px">'+showbrand[i].showbdrel+'</td>'+
                    '<td style="width:120px">'+showbrand[i].brandpro+'</td>'+
                    '</tr>';
                if(i<=4){
                    con2+='<tr><td style="height:44px;padding-left:5px;">' + showbrand[i].brand + '</td><td> <img src="http://svc.iresearch.cn/img/star2.png" style="width:20px;height:20px"></td><td align="right" style="height:44px;padding-left:5px;color:#1d914d;">'+showbrand[i].svc+'</td></tr>';
                }else{
                    con3+='<tr><td style="height:44px;padding-left:5px;">' + showbrand[i].brand + '</td><td> <img src="http://svc.iresearch.cn/img/star2.png" style="width:20px;height:20px"></td><td align="right" style="height:44px;padding-left:5px;color:#1d914d;">'+showbrand[i].svc+'</td></tr>';
                }
            }

        }

        return [con1,con2,con3];

    }catch(TypeError) {
        console.log("节目表格数据类型出错")
    }

	
}


function divtop50(allshow,disp){
    try{
        var con1='';
        var con2='';
        var length;
        if(allshow.length>50){
            length=50;
        }else{
            length=allshow.length;
        }
        for (var i = 0; i < allshow.length; i++) {
            con1+='<option value="'+allshow[i].show+'">'+allshow[i].show+'</option>';
        }
        for (var i = 0; i < length; i++) {
            var svc,svcrk;
            if(disp=="max"){
                svcrk=allshow[i].svcmaxrk;
                svc=allshow[i].svcmax;
            }else{
                svcrk=allshow[i].svcavgrk;
                svc=allshow[i].svcavg;
            }
            //con1+='<option value="'+allshow[i].show+'">'+allshow[i].show+'</option>';
            con2+='<li>'+
                '<div class="imag">'+
                '<div class="imag_tag">'+svcrk+'</div>'+
                '<a href="javascript:getshow(\''+allshow[i].show+'\');"><img  referrer="no-referrer" src="'+allshow[i].photo+'"  style="width:100%;height:100%;"></a>'+
                '</div>'+
                '<div class="conte">'+
                '<span>'+allshow[i].show+'</span><br>'+svc+'</div>'+
                '</li>'
        }

        return [con1,con2];

    }catch(TypeError) {
        console.log("Top50数据类型出错");
    }


}


function divshowcustom(show,customshows){
    try{
        var con;
        if(customshows.indexOf(show.show)!=-1){
            con='<a href="http://svc.iresearch.cn/vip/showcustom.html?showname='+show.show+'" target="_blank" class="btn btn-danger" role="button">节目定制监测</a>'
        }else{
            con='<a href="" class="btn btn-danger disabled" role="button">节目定制监测</a>'
        }
        return con;
    }catch(TypeError) {
        console.log("节目定制监测数据类型出错")
    }

}

function divbrandcustom(brand,custombrands){
    try{
        var con;
        if(custombrands.indexOf(brand.brand)!=-1){
            con='<a href="http://svc.iresearch.cn/vip/brandcustom.html?brandname='+brand.brand+'" target="_blank" class="btn btn-danger" role="button">品牌定制监测</a>'
        }else{
            con='<a href="" class="btn btn-danger disabled" role="button">品牌定制监测</a>'
        }
        return con;
    }catch(TypeError) {
        console.log("品牌定制监测数据类型出错")
    }

}

function dataList(data) {

	var  allshowmax=data.allshowmax;
	var  showmax=allshowmax[0];
	var  showtypemax=data.showtypemax;
	var  showbrandmax=data.showbrandmax;
	var  t1brandmax=showbrandmax[0];
	var  bdmax=data.bdindexmax;
	var  wbmax=data.wbindexmax;
	var  wxmax=data.wxindexmax;
	var  tgimax=data.showtgimax;

    var  allshowavg=deepClone(data.allshowmax);
    allshowavg.sort(function (a,b) {
        return a.svcavgrk - b.svcavgrk;
    })
	//var  allshowavg=data.allshowavg;
	var  showavg=allshowavg[0];
	var  showtypeavg=data.showtypeavg;
	var  showbrandavg=data.showbrandavg;
	var  t1brandavg=showbrandavg[0];
	var  bdavg=data.bdindexavg;
	var  wbavg=data.wbindexavg;
	var  wxavg=data.wxindexavg;
	var  tgiavg=data.showtgiavg;
    var customshow=data.customshow;
    var custombrand=data.custombrand;
    var customshows='';
    var custombrands='';
    for(var i=0;i<custombrand.length;i++){
        custombrands+=custombrand[i].brand;
    }
    for(var i=0;i<customshow.length;i++){
        customshows+=customshow[i].show;
    }

	var showcustomlinkmax=divshowcustom(showmax,customshows);
	var showcustomlinkavg=divshowcustom(showavg,customshows);
	var brandcustomlinkmax=divbrandcustom(t1brandmax,custombrands);
	var brandcustomlinkavg=divbrandcustom(t1brandavg,custombrands);
	var bgdate=data.bgdate;
	var enddate=data.enddate;
	if(bgdate!=null && enddate!=null){
		  $('#statdatepickern').val(bgdate);
		  $('#enddatepickern').val(enddate);
	}




	
    showtbmax=divshowtb(showbrandmax,'max');
    showtbavg=divshowtb(showbrandavg,'avg');
    showbdtbmax=showtbmax[0];
    showsvtbmax1=showtbmax[1];
    showsvtbmax2=showtbmax[2];
    showbdtbavg=showtbavg[0];
    showsvtbavg1=showtbavg[1];
    showsvtbavg2=showtbavg[2];    
    showsvmax=divshowsv(showmax,showtypemax,showsvtbmax1,showsvtbmax2,'max');
    showsvavg=divshowsv(showavg,showtypeavg,showsvtbavg1,showsvtbavg2,'avg');
    top50max=divtop50(allshowmax,'max');
    top50avg=divtop50(allshowavg,'avg');
    sershowopt=top50max[0];
    top50maxcon=top50max[1];
    top50avgcon=top50avg[1];
    top50max='<ul style="list-style:none">'+top50maxcon+'</ul>';
    top50avg='<ul style="list-style:none">'+top50avgcon+'</ul>';

    showdtmax=divshowdt(showmax,showtypemax,showcustomlinkmax,'max');
    showdtavg=divshowdt(showavg,showtypeavg,showcustomlinkavg,'avg');
    showbdrmax=divshowbdr(t1brandmax,brandcustomlinkmax);
    showbdravg=divshowbdr(t1brandavg,brandcustomlinkavg);





    $('#showsvmax').html(showsvmax);
    $('#showsvavg').html(showsvavg);
    $('#top50max').html(top50max);
    $('#top50avg').html(top50avg);
    $('#sershow').html(sershowopt);
    $('#showdtmax').html(showdtmax);
    $('#showdtavg').html(showdtavg);
    $('#bdtbthnummax').html("品牌数量："+showmax.brandnum);
    $('#brandtbconmax').html(showbdtbmax);
    $('#showbdrmax').html(showbdrmax);
    $('#bdtbthnumavg').html("品牌数量："+showavg.brandnum)
    $('#brandtbconavg').html(showbdtbavg);
    $('#showbdravg').html(showbdravg);
    
    document.getElementById('storeallshowmax').value = JSON.stringify(data.allshowmax);
    document.getElementById('storeallshowavg').value = JSON.stringify(allshowavg);
//  document.getElementById('storenormcondi').value = JSON.stringify(data.normcondi);
    document.getElementById('storeshowbdmax').value = JSON.stringify(data.showbrandmax);
    document.getElementById('storeshowbdavg').value = JSON.stringify(data.showbrandavg);
    document.getElementById('storecustombrands').value = custombrands;
    document.getElementById('storecustomshows').value = customshows;


    loadporpertycharts(bdmax,wbmax,wxmax,tgimax,'max');
    loadporpertycharts(bdavg,wbavg,wxavg,tgiavg,'avg');


    //});
};



function dataList1(showname,data) {
	var  allshowmax=JSON.parse(document.getElementById('storeallshowmax').value);
    var  custombrands=document.getElementById('storecustombrands').value;
    var  customshows=document.getElementById('storecustomshows').value;
	var  showmax=null;
	for (var i=0;i<allshowmax.length;i++){
		if(allshowmax[i].show==showname){
			//alert(showname);
			showmax=allshowmax[i];
		}
	}
	var  showtypemax=data.showtype;
	var  showbrandmax=data.showbrand;
	var  t1brandmax=showbrandmax[0];
	var  bdmax=data.bdindex;
	var  wbmax=data.wbindex;
	var  wxmax=data.wxindex;
	var  tgimax=data.showtgi;



	var  allshowavg=JSON.parse(document.getElementById('storeallshowavg').value);
	var  showavg=showmax;
	var  showtypeavg=showtypemax;
	var  showbrandavg=showbrandmax;
	var  t1brandavg=t1brandmax;
	var  bdavg=bdmax;
	var  wbavg=wbmax;
	var  wxavg=wxmax;
	var  tgiavg=tgimax;

	var showcustomlinkmax=divshowcustom(showmax,customshows);
	var showcustomlinkavg=divshowcustom(showavg,customshows);
	var brandcustomlinkmax=divbrandcustom(t1brandmax,custombrands);
	var brandcustomlinkavg=divbrandcustom(t1brandavg,custombrands);



    showtbmax=divshowtb(showbrandmax,'max');
    showtbavg=divshowtb(showbrandavg,'avg');
    showbdtbmax=showtbmax[0];
    showsvtbmax1=showtbmax[1];
    showsvtbmax2=showtbmax[2];
    showbdtbavg=showtbavg[0];
    showsvtbavg1=showtbavg[1];
    showsvtbavg2=showtbavg[2];    
    showsvmax=divshowsv(showmax,showtypemax,showsvtbmax1,showsvtbmax2,'max');
    showsvavg=divshowsv(showavg,showtypeavg,showsvtbavg1,showsvtbavg2,'avg');
    top50max=divtop50(allshowmax,'max');
    top50avg=divtop50(allshowavg,'avg');
    sershowopt=top50max[0];
    top50maxcon=top50max[1];
    top50avgcon=top50avg[1];
    
    top50max='<ul style="list-style:none">'+top50maxcon+'</ul>';
    top50avg='<ul style="list-style:none">'+top50avgcon+'</ul>';

    showdtmax=divshowdt(showmax,showtypemax,showcustomlinkmax,'max');
    showdtavg=divshowdt(showavg,showtypeavg,showcustomlinkavg,'avg');
    showbdrmax=divshowbdr(t1brandmax,brandcustomlinkmax);
    showbdravg=divshowbdr(t1brandavg,brandcustomlinkavg);




    $('#showsvmax').html(showsvmax);
    $('#showsvavg').html(showsvavg);
    $('#top50max').html(top50max);
    $('#top50avg').html(top50avg);
    $('#sershow').html(sershowopt);
    $('#showdtmax').html(showdtmax);
    $('#showdtavg').html(showdtavg);
    $('#bdtbthnummax').html("品牌数量："+showmax.brandnum);
    $('#brandtbconmax').html(showbdtbmax);
    $('#showbdrmax').html(showbdrmax);
    $('#bdtbthnumavg').html("品牌数量："+showavg.brandnum)
    $('#brandtbconavg').html(showbdtbavg);
    $('#showbdravg').html(showbdravg);


//    document.getElementById('storenormcondi').value = JSON.stringify(data.normcondi);
    document.getElementById('storeshowbdmax').value = JSON.stringify(showbrandmax);
    document.getElementById('storeshowbdavg').value = JSON.stringify(showbrandavg);

    loadporpertycharts(bdmax,wbmax,wxmax,tgimax,'max');
    loadporpertycharts(bdavg,wbavg,wxavg,tgiavg,'avg');


    //});
};




function  getshow(showname){
	$.ajax({
	    url: 'http://svc.iresearch.cn/allshow/getshow',
	    type: 'post',
	    dataType: 'json',
        data:{
            showname:showname
        },
	    jsonp: 'callback',
	    async: true,          
	    success: function(data) {
	        //callback(r);
	        dataList1(showname,data)
	    },
	    // error: function() {
	    //     var errorE = '抱歉，没有找到符合您要求的数据!';
	    //     alert(errorE);
	    // }
	});
}


function  getbrand(brandname,disp){
	var eleid='storeshowbd'+disp;
	var showbdr='#showbdr'+disp;
	var showbd=document.getElementById(eleid).value;
    var  custombrands=document.getElementById('storecustombrands').value;
	var showbrand=JSON.parse(showbd);
	var onebrand=null;
	for(var i=0;i<showbrand.length;i++){
		if(showbrand[i].brand==brandname){
			onebrand=showbrand[i];
		}
	}
	
	var brandcustomlink=divbrandcustom(onebrand,custombrands);

	var Con=divshowbdr(onebrand,brandcustomlink);

	$(showbdr).html(Con);



}


function  searchshow(){
	var showname=$('#sershow').val();
//	var normcon=document.getElementById('storenormcondi').value;
//	var normcondi=encodeURIComponent(normcon.replace(/"/g,""));
	$.ajax({
	    url: 'http://svc.iresearch.cn/allshow/getshow',
	    type: 'post',
	    dataType: 'json',
	    data:{
	        showname:showname
        },
	    jsonp: 'callback',
	    async: true,
	    success: function (data) {
	        //callback(r);
	        dataList1(showname,data)
	    },
	    // error: function () {
	    //     var errorE = '抱歉，没有找到符合您要求的数据!';
	    //     alert(errorE);
	    // }
	});


}



