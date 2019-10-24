var filnormcon=divfilnorm();
$('#filter').html(filnormcon[0]);
$('#normcon').html(filnormcon[1]);
setDate();


$.ajax({
    url: 'http://svc.iresearch.cn/api/svc/basic/allbrand/get1st',
    type: 'get',
    dataType: 'json',
    //data:data,
    jsonp: 'callback',
    async: false,
    success: function (data) {
        //callback(r);
        dataList(data)
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
        url: "http://svc.iresearch.cn/api/svc/basic/allbrand/getnorm",
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


function divbrandrk(brand,brandtype,shownum,spontype,disp){
	try{
        var svc=0;
        var showval=0;
        var showbdrel=0;
        var brandpro=0;
        var svcrk;

        if (disp=="max"){
            svc=brand.svcmax;
            svcrk=brand.svcmaxrk;
            showval=brand.showvalmax;
            showbdrel=brand.showbdrelmax;
            brandpro=brand.brandpromax;
        }else if(disp="avg"){
            svc=brand.svcavg;
            svcrk=brand.svcavgrk;
            showval=brand.showvalavg;
            showbdrel=brand.showbdrelavg;
            brandpro=brand.brandproavg;

        }


        var Con='    <div class="brandrank_l">'+
            '    	<div class="brandrank_l1">'+brand.brand+'</div>'+
            '        <div class="brandrank_l2"><img src="'+brand.logo+'" style="width:100%;height:100%"></div>'+
            '        <ul  class="brandrank_l3">'+
            '        	 <li><span>SVC指数排名：</span>'+svcrk+'</li>'+
            '        	 <li><span>品类：</span>'+brandtype.brandtype+'</li>'+
            '            <li><span>监测植入节目数量：</span> '+shownum+'</li>'+
            '            <li><span>赞助等级：</span>'+spontype+'</li>'+
            '        </ul>'+
            '    </div>'+
            '    <div class="brandrank_r">'+
            '    	<div class="brandrank_r1"><span>'+svc+'</span>(SVC综合指数)</div>'+
            '        <ul class="brandrank_r2">'+
            '            <li><span>'+showval+'</span><br/>节目价值指数</li>'+
            '            <li><span>'+showbdrel+'</span><br/>节目-品牌关联指数</li>'+
            '            <li><span>'+brandpro+'</span><br/>品牌资产提升指数</li>'+
            '        </ul>'+
            '        '+
            '       <div class="brandrank_r4">赞助节目SVC综合指数趋势：</div>'+
            '		<div class="brandrank_r3" id="bdswchart'+disp+'">'+
            '		</div>'+
            '    </div>';
        return Con;

	}catch(TypeError) {
		console.log("品牌排行数据类型错误！")
    }

}



function divbranddtl(brand,brandtype,spontype,showlv,disp){
    try{
        var Con='<div class="branddeatil_l1">'+brand.brand+'</div>'+
            '            <div class="branddeatil_l2"><img src="'+brand.logo+'" style="width:100%;height:100%"></div>'+
            '            <ul class="branddeatil_l3">'+
            '                <li><span>品类：</span>'+brandtype.brandtype+'</li>'+
            '                <li id="bdswspontype'+disp+'"><span>赞助等级：</span>'+spontype+'</li>'+
            '                <li id="bdswshowlv'+disp+'"><span>节目流量等级：</span>'+showlv+'</li>'+
            '            </ul>';
        return Con;
    }catch(TypeError) {
        console.log("品牌详情数据类型错误！")
    }

}



function divshowdt(show,showtype,linkcon,showrk){
    try{
        var Con='    <div class="box_l">'+
            '            <div class="box_l1">'+show.show+'</div>'+
            '            <div class="box_l2"><img src="'+show.photo+'" style="width:100%;height:100%"></div>'+
            '            <ul class="box_l3">'+
            '                <li><span>SVC综合指数排名：</span>'+showrk.svcavgrk+'</li>'+
            '                <li><span>节目流量等级：</span>'+showtype.showlevel+'</li>'+
            '                <li><span>首播：</span>'+showtype.begindate+'</li>'+
            '                <li><span>期数：</span>'+showtype.episode+'</li>'+
            '                <li><span>终端：</span>'+showtype.type+'</li>'+
            '                <li><span>平台：</span>'+showtype.platform+'</li>'+
            '                <li><span>类型：</span>'+showtype.showtype+'</li>'+
            '            </ul>'+
            '    </div>'+
            '    <div class="box_r">'+
            '        <div class="box_r1">'+
            '            <div class="box_r1l"><span>'+show.svc+'</span>&nbsp&nbsp&nbsp&nbsp(SVC综合指数)</div>'+
            '            <div class="box_r1r">'+linkcon+'</div>'+
            '        </div>'+
            '        <ul class="box_r2">'+
            '            <li style="width:80px;line-height:22.5px;color:#7e7e7e;">二<br/>级<br/>指<br/>数</li>'+
            '            <li><span>'+show.showval+'</span><br/>节目价值</li>'+
            '            <li><span>'+show.showbdrel+'</span><br/>节目-品牌关联</li>'+
            '            <li><span>'+show.brandpro+'</span><br/>品牌资产提升</li>'+
            '        </ul>'+
            '        <ul class="box_r3">'+
            '            <li style="width:80px;line-height:22.5px;color:#7e7e7e;">三<br />级<br />指<br />数</li>'+
            '            <li><span>'+show.wtindex+'</span>&nbsp&nbsp收视热度<br /><span>'+show.rwindex+'</span>&nbsp&nbsp重复收视<br><span>'+show.kbindex+'</span>&nbsp&nbsp口碑传播</li>'+
            '            <li><span>'+show.rmindex+'</span>&nbsp&nbsp回忆度<br/><span>'+show.relindex+'</span>&nbsp&nbsp相关度<br></li>'+
            '            <li><span>'+show.rzupindex+'</span>&nbsp&nbsp认知提升<br/><span>'+show.xaupindex+'</span>&nbsp&nbsp喜爱提升<br><span>'+show.tjupindex+'</span>&nbsp&nbsp推荐提升</li>'+
            '        </ul>'+
            '    </div>';
        return Con;

    }catch(TypeError) {
        console.log("节目详情数据类型错误！")
    }


}


function divtop50(allbrand,disp){
    try{
        var con1='';
        var con2='';
        var length;
        if(allbrand.length>50){
            length=50;
        }else{
            length=allbrand.length;
        }
        for (var i = 0; i < allbrand.length; i++){
            con1+='<option value="'+allbrand[i].brand+'">'+allbrand[i].brand+'</option>';
        }
        for (var i = 0; i < length ; i++){
            var svcrk;
            var svc;
            if(disp=="max"){
                svcrk=allbrand[i].svcmaxrk;
                svc=allbrand[i].svcmax;
            }else{
                svcrk=allbrand[i].svcavgrk;
                svc=allbrand[i].svcavg;
            }
            //con1+='<option value="'+allbrand[i].brand+'">'+allbrand[i].brand+'</option>';
            con2+='     <li>'+
                '      <div class="imag">'+
                '          <div class="imag_tag">'+svcrk+'</div>'+
                '        <a href="javascript:getbrand(\''+allbrand[i].brand+'\');"><img referrer="no-referrer" src="'+allbrand[i].logo+'" style="width:100%;height:100%;"></a>'+
                '      </div>'+
                '      <div class="conte">'+
                '        <span>'+allbrand[i].brand+'</span><br>'+svc+'</div>'+
                '    </li>';
        }

        return [con1,con2];

    }catch(TypeError) {
        console.log("TOP50数据类型错误！")
    }


}


function divshowlist(brandshow,disp){
    try{
        var Con='';
        for (var i = 0; i < brandshow.length; i++){
            Con+='     <li>'+
                '      <div class="imag">'+
                '          <div class="imag_tag">'+brandshow[i].svcrk+'</div>'+
                '        <a href="javascript:getoneshow(\''+brandshow[i].show+'\',\''+disp+'\');"><img src="'+brandshow[i].photo+'" style="width:100%;height:100%;"></a>'+
                '      </div>'+
                '      <div class="conte">'+
                '        <span>'+brandshow[i].show+'</span><br>'+
                '        '+brandshow[i].svc+''+
                '      </div>'+
                '    </li>';
        }

        return Con;

    }catch(TypeError) {
        console.log("品牌节目列表数据类型错误！")
    }

}


function divshowcustom(show,customshows){
    try{
        var con;
        if(customshows.indexOf(show.show)!=-1){
            con='<a href="" class="btn btn-danger disabled" role="button">节目定制监测</a>'
            // con='<a href="http://svc.iresearch.cn/svc/vip/showcustom.html?showname='+show.show+'" target="_blank" class="btn btn-danger" role="button">节目定制监测</a>'
        }else{
            con='<a href="" class="btn btn-danger disabled" role="button">节目定制监测</a>'
        }
        return con;

    }catch(TypeError) {
        console.log("定制节目数据类型错误！")
    }

}

function divbrandcustom(brand,custombrands){
    try{
        var con;
        if(custombrands.indexOf(brand.brand)!=-1){
            con='<a href="" class="btn btn-danger disabled" role="button">品牌定制监测</a>'
            // con='<a href="http://svc.iresearch.cn/svc/vip/brandcustom.html?brandname='+brand.brand+'" target="_blank" class="btn btn-danger" role="button">品牌定制监测</a>'
        }else{
            con='<a href="" class="btn btn-danger disabled" role="button">品牌定制监测</a>'
        }
        return con;

    }catch(TypeError) {
        console.log("定制品牌数据类型错误！")
    }

}



function dataList(data) {
	var  allbrandmax=data.allbrandmax;
	var  brandmax=allbrandmax[0];
	var  brandtypemax=data.brandtypemax;
	var  brandshowmax=data.brandshowmax;
	var  brandtrendmax=data.brandtrendmax;
	var  t1showmax=brandshowmax[0];
	var  t1showtypemax=data.t1showtypemax;
	var  bdmax=data.bdindexmax;
	var  wbmax=data.wbindexmax;
	var  wxmax=data.wxindexmax;
	var  tgimax=data.showtgimax;
	var  allshowrk=data.allshowrk;
	var  t1showmaxrk;
    for(var i=0;i<allshowrk.length;i++){
    	if(allshowrk[i].show==t1showmax.show){
    		t1showmaxrk=allshowrk[i];
    	}
    }

    var  allbrandavg=deepClone(data.allbrandmax);
    allbrandavg.sort(function (a,b) {
        return a.svcavgrk - b.svcavgrk;
    })
	//var  allbrandavg=data.allbrandavg;
	var  brandavg=allbrandavg[0];
	var  brandtypeavg=data.brandtypeavg;
	var  brandshowavg=data.brandshowavg;
	var  brandtrendavg=data.brandtrendavg;
	var  t1showtypeavg=data.t1showtypeavg;
	var  t1showavg=brandshowavg[0];
	var  bdavg=data.bdindexavg;
	var  wbavg=data.wbindexavg;
	var  wxavg=data.wxindexavg;
	var  tgiavg=data.showtgiavg;
	var  t1showavgrk;
    for(var i=0;i<allshowrk.length;i++){
    	if(allshowrk[i].show==t1showavg.show){
    		t1showavgrk=allshowrk[i];
    	}
    }

	var bgdate=data.bgdate;
	var enddate=data.enddate;
	if(bgdate!=null && enddate!=null){
		  $('#statdatepickern').val(bgdate);
		  $('#enddatepickern').val(enddate);
	}

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



	var  spontypelvmax='其他';
	var  spontypelvavg='其他';
	var  sponbrandshowavg=brandshowavg;
    for(var i=0;i<brandshowmax.length;i++){
    	if(brandshowmax[i].sponsortype=='总冠名'){
    		spontypelvmax='总冠名';
    		break;
    	}
    }
    for(var i=0;i<brandshowavg.length;i++){
    	if(brandshowavg[i].sponsortype=='总冠名'){
    		spontypelvavg='总冠名';
    		break;
    	}
    }

	var brandcustomlinkmax=divbrandcustom(brandmax,custombrands);
	var brandcustomlinkavg=divbrandcustom(brandavg,custombrands);
	var showcustomlinkmax=divshowcustom(t1showmax,customshows);
	var showcustomlinkavg=divshowcustom(t1showavg,customshows);

    top50max=divtop50(allbrandmax,'max');
    top50avg=divtop50(allbrandavg,'avg');
    serbrandopt=top50max[0];
    top50maxcon=top50max[1];
    top50avgcon=top50avg[1];
    brandrkmax=divbrandrk(brandmax,brandtypemax,brandshowmax.length,t1showmax.sponsortype,'max');
    brandrkavg=divbrandrk(brandavg,brandtypeavg,brandshowavg.length,t1showavg.sponsortype,'avg');

    top50max='<ul style="list-style:none">'+top50maxcon+'</ul>';
    top50avg='<ul style="list-style:none">'+top50avgcon+'</ul>';
    branddtlmax=divbranddtl(brandmax,brandtypemax,spontypelvmax,t1showtypemax.showlevel,'max');
    branddtlavg=divbranddtl(brandavg,brandtypeavg,spontypelvavg,t1showtypeavg.showlevel,'avg');
    showlistmax=divshowlist(brandshowmax,'max');
    showlistavg=divshowlist(brandshowavg,'avg');
    oneshowdtmax=divshowdt(t1showmax,t1showtypemax,showcustomlinkmax,t1showmaxrk);
    oneshowdtavg=divshowdt(t1showavg,t1showtypeavg,showcustomlinkavg,t1showavgrk);





    $('#brandrankmax').html(brandrkmax);
    $('#brandrankavg').html(brandrkavg);
    $('#top50brandmax').html(top50max);
    $('#top50brandavg').html(top50avg);
    $('#serbrand').html(serbrandopt);
    $('#branddtlmax').html(branddtlmax);
    $('#branddtlavg').html(branddtlavg);
    $('#showlistmax').html(showlistmax);
    $('#showlistavg').html(showlistavg);
    $('#showdeatilmax').html(oneshowdtmax);
    $('#showdeatilavg').html(oneshowdtavg);
    $('#bdcustommax').html(brandcustomlinkmax);
    $('#bdcustomavg').html(brandcustomlinkavg);

//
    brandshowchart(brandtrendmax,'max');
    brandshowchart(brandtrendavg,'avg');


    document.getElementById('storeallbrandmax').value = JSON.stringify(data.allbrandmax);
    document.getElementById('storeallbrandavg').value = JSON.stringify(allbrandavg);
    document.getElementById('storeallshowrk').value = JSON.stringify(data.allshowrk);
    document.getElementById('storebdshowmax').value = JSON.stringify(data.brandshowmax);
    document.getElementById('storebdshowavg').value = JSON.stringify(data.brandshowavg);
    document.getElementById('storecustombrands').value = custombrands;
    document.getElementById('storecustomshows').value = customshows;


    loadporpertycharts(bdmax,wbmax,wxmax,tgimax,'max');
    loadporpertycharts(bdavg,wbavg,wxavg,tgiavg,'avg');


};



function dataList1(brandname,data) {



	var  allbrandmax=JSON.parse(document.getElementById('storeallbrandmax').value);
	var  allshowrk=JSON.parse(document.getElementById('storeallshowrk').value);
	var  custombrands=document.getElementById('storecustombrands').value;
    var  customshows=document.getElementById('storecustomshows').value;
	var  brandmax=null;
	for (var i=0;i<allbrandmax.length;i++){
		if(allbrandmax[i].brand==brandname){
			//alert(showname);
			brandmax=allbrandmax[i];
		}
	}
	var  brandtypemax=data.brandtype;
	var  brandshowmax=data.brandshow;
	var  brandtrend=data.brandtrend;
	var  t1showmax=brandshowmax[0];
	var  t1showtypemax=data.t1showtype;
	var  bdmax=data.bdindex;
	var  wbmax=data.wbindex;
	var  wxmax=data.wxindex;
	var  tgimax=data.showtgi;


	var  allbrandavg=JSON.parse(document.getElementById('storeallbrandavg').value);
	var	brandavg = brandmax;
	var	brandtypeavg = brandtypemax;
	var	brandshowavg = brandshowmax;
	var	t1showtypeavg = t1showtypemax;
	var	t1showavg = t1showmax;
	var	bdavg = bdmax;
	var	wbavg = wbmax;
	var	wxavg = wxmax;
	var	tgiavg = tgimax;

	var  spontypelvmax='其他';
	var  spontypelvavg='其他';
	var  sponbrandshowavg=brandshowavg;
    for(var i=0;i<brandshowmax.length;i++){
    	if(brandshowmax[i].sponsortype=='总冠名'){
    		spontypelvmax='总冠名';
    		break;
    	}
    }
    for(var i=0;i<brandshowavg.length;i++){
    	if(brandshowavg[i].sponsortype=='总冠名'){
    		spontypelvavg='总冠名';
    		break;
    	}
    }

	var  t1showavgrk;
    for(var i=0;i<allshowrk.length;i++){
    	if(allshowrk[i].show==t1showavg.show){
    		t1showavgrk=allshowrk[i];
    	}
    }

	var brandcustomlinkmax=divbrandcustom(brandmax,custombrands);
	var brandcustomlinkavg=divbrandcustom(brandavg,custombrands);
	var showcustomlinkmax=divshowcustom(t1showmax,customshows);
	var showcustomlinkavg=divshowcustom(t1showavg,customshows);

    top50max=divtop50(allbrandmax,'max');
    top50avg=divtop50(allbrandavg,'avg');
    serbrandopt=top50max[0];
    top50maxcon=top50max[1];
    top50avgcon=top50avg[1];
    brandrkmax=divbrandrk(brandmax,brandtypemax,brandshowmax.length,t1showmax.sponsortype,'max');
    brandrkavg=divbrandrk(brandavg,brandtypeavg,brandshowavg.length,t1showavg.sponsortype,'avg');

    top50max='<ul style="list-style:none">'+top50maxcon+'</ul>';
    top50avg='<ul style="list-style:none">'+top50avgcon+'</ul>';
    branddtlmax=divbranddtl(brandmax,brandtypemax,spontypelvmax,t1showtypemax.showlevel,'max');
    branddtlavg=divbranddtl(brandavg,brandtypeavg,spontypelvavg,t1showtypeavg.showlevel,'avg');
    showlistmax=divshowlist(brandshowmax,'max');
    showlistavg=divshowlist(brandshowavg,'avg');
    oneshowdtmax=divshowdt(t1showmax,t1showtypemax,showcustomlinkmax,t1showavgrk);
    oneshowdtavg=divshowdt(t1showavg,t1showtypeavg,showcustomlinkavg,t1showavgrk);





    $('#brandrankmax').html(brandrkmax);
    $('#brandrankavg').html(brandrkavg);
    $('#top50brandmax').html(top50max);
    $('#top50brandavg').html(top50avg);
    $('#serbrand').html(serbrandopt);
    $('#branddtlmax').html(branddtlmax);
    $('#branddtlavg').html(branddtlavg);
    $('#showlistmax').html(showlistmax);
    $('#showlistavg').html(showlistavg);
    $('#showdeatilmax').html(oneshowdtmax);
    $('#showdeatilavg').html(oneshowdtavg);
    $('#bdcustommax').html(brandcustomlinkmax);
    $('#bdcustomavg').html(brandcustomlinkavg);


    brandshowchart(brandtrend,'max');
    brandshowchart(brandtrend,'avg');



    document.getElementById('storebdshowmax').value = JSON.stringify(brandshowmax);
    document.getElementById('storebdshowavg').value = JSON.stringify(brandshowavg);

    loadporpertycharts(bdmax,wbmax,wxmax,tgimax,'max');
    loadporpertycharts(bdavg,wbavg,wxavg,tgiavg,'avg');




};


function dataList2(showname,disp,data) {
	var  t1show=null;
	var  allshowrk=JSON.parse(document.getElementById('storeallshowrk').value);
    var  customshows=document.getElementById('storecustomshows').value;

	if(disp=="max"){
		var  brandshowmax=JSON.parse(document.getElementById('storebdshowmax').value);
		for (var i=0;i<brandshowmax.length;i++){
			if(brandshowmax[i].show==showname){
				//alert(showname);
				t1show=brandshowmax[i];
			}
		}

	}else if(disp=="avg"){
		var  brandshowavg=JSON.parse(document.getElementById('storebdshowavg').value);
		for (var i=0;i<brandshowavg.length;i++){
			if(brandshowavg[i].show==showname){
				//alert(showname);
				t1show=brandshowavg[i];
			}
		}
	}


	var  t1showtype=data.t1showtype;
	var  bdmax=data.bdindex;
	var  wbmax=data.wbindex;
	var  wxmax=data.wxindex;
	var  tgimax=data.showtgi;
	var  showcustomlink=divshowcustom(t1show,customshows);

	var  t1showrk;
    for(var i=0;i<allshowrk.length;i++){
    	if(allshowrk[i].show==showname){
    		t1showrk=allshowrk[i];
    	}
    }

	oneshowid='#showdeatil'+disp;
    oneshowdt=divshowdt(t1show,t1showtype,showcustomlink,t1showrk);

    $('#bdswspontype'+disp).html('<span>赞助等级：</span>'+t1show.sponsortype);
    $('#bdswshowlv'+disp).html('<span>节目流量等级：</span>'+t1showtype.showlevel);
    $(oneshowid).html(oneshowdt);


    loadporpertycharts(bdmax,wbmax,wxmax,tgimax,disp);

};



function  getbrand(brandname){
//	var normcon=document.getElementById('storenormcondi').value;
//	var normcondi=encodeURIComponent(normcon.replace(/"/g,""));
	$.ajax({
	    url: 'http://svc.iresearch.cn/api/svc/basic/allbrand/getbrand',
	    type: 'post',
	    dataType: 'json',
	    data:{
	        brandname:brandname
        },
	    jsonp: 'callback',
	    async: true,
	    success: function (data) {
	        //callback(r);
	        dataList1(brandname,data)
	    },
	    // error: function () {
	    //     var errorE = '抱歉，没有找到符合您要求的数据!';
	    //     alert(errorE);
	    // }
	});
}


 function  getoneshow(showname,disp){
	$.ajax({
	    url: 'http://svc.iresearch.cn/api/svc/basic/allbrand/getoneshow',
	    type: 'post',
	    dataType: 'json',
	    data:{
	        showname:showname
        },
	    jsonp: 'callback',
	    async: true,
	    success: function (data) {
	        //callback(r);
	        dataList2(showname,disp,data)
	    },
        // error: function () {
        //     var errorE = '抱歉，没有找到符合您要求的数据!';
        //     alert(errorE);
        // }
	});



}


function  searchbrand(){
	var brandname=$('#serbrand').val();
	//var normcon=document.getElementById('storenormcondi').value;
	//var normcondi=encodeURIComponent(normcon.replace(/"/g,""));
	$.ajax({
	    url: 'http://svc.iresearch.cn/api/svc/basic/allbrand/getbrand',
	    type: 'post',
	    dataType: 'json',
	    data:{
	        brandname:brandname
        },
	    jsonp: 'callback',
	    async: true,
	    success: function (data) {
	        //callback(r);
	        dataList1(brandname,data)
	    },
	    // error: function () {
	    //     var errorE = '抱歉，没有找到符合您要求的数据!';
	    //     alert(errorE);
	    // }
	});


}
