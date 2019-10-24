var filnormcon=divfilnorm();
$('#filter').html(filnormcon[0]);
$('#normcon').html(filnormcon[1]);
setDate();

var brandname = getQueryString("brandname");


brandname1=encodeURI(brandname);
urlori='http://svc.iresearch.cn/api/svc/vip/brandcustom/get1st?brandname='+brandname1;

$.ajax({
    url: urlori,
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
        url: "http://svc.iresearch.cn/api/svc/vip/brandcustom/getnorm",
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

function divbranddt(brandname,brandtype,spontype,showlevel){
	try{
        var Con='<div class="branddeatil_l1">'+brandname+'</div>'+
            '        <div style="width:255px;height:278px;border-right:1px #888 solid">'+
            '          <div class="branddeatil_l2"><img src="'+brandtype.logo+'" style="width:100%;height:100%"></div>'+
            '          <ul class="branddeatil_l3">'+
            '              <li><span>品类：</span>'+brandtype.brandtype+'</li>'+
            '              <li><span>赞助等级：</span>'+spontype+'</li>'+
            '              <li><span>节目流量等级：</span>'+showlevel+'</li>'+
            '          </ul>'+
            '        </div>';
        return Con;

	}catch(TypeError){
		console.log("品牌详情数据类型错误！");
	}

}




function divshowdt(oneshow,showtype,svcrk){
	try{
        var Con='<div class="box_l">'+
            '        <div class="box_l1">'+oneshow.show+'</div>'+
            '        <div class="box_l2"><img src="'+oneshow.photo+'" style="width:100%;height:100%"></div>'+
            '        <ul class="box_l3">'+
            '          <li><span>SVC综合指数排名</span>：'+svcrk+'</li>'+
            '          <li><span>节目流量等级</span>：'+showtype.showlevel+'</li>    '+
            '        <li><span>终端</span>：'+showtype.type+'</li>'+
            '          <li><span>平台</span>：'+showtype.platform+'</li>'+
            '          <li><span>类型</span>：'+showtype.showtype+'</li>'+
            '          <li><span>首播</span>：'+showtype.begindate+'</li>'+
            '          <li><span>期数</span>：'+showtype.episode+'</li>'+
            '          <li><span>监测植入品牌数</span>：</li>  '+
            '        </ul>'+
            '</div>'+
            '<div class="box_r">'+
            '    <div class="box_r1">'+
            '        <div class="box_r1l"><img src="http://svc.iresearch.cn/img/star1.png" style="height:40px;width:40px;float:left;margin:13px 20px;margin-left:-10px;"><span style="float:left;">'+oneshow.svc+'</span>'+
            '          (SVC综合指数)<div id="dtsvcchart" style="float:left;width:70px;height:50px;margin-left:20px;margin-top:20px;"></div>'+
            '        </div>'+
            '        <div style="height:20px;font-size:14px;line-height:12px;color:#7e7e7e;">VS之后对比的原始值为NORM</div>'+
            '    </div>'+
            '    <ul class="box_r2"  >'+
            '        <li style="width:30px;line-height:20px;color:#7e7e7e;margin:0px;margin-left:20px;padding:20px 0px;">二<br/>级<br/>指<br/>数</li>'+
            '        <li style="color:#555;font-size:14px;"><span>'+oneshow.showval+'</span><br/>节目价值<div id="dtvalchart" style="width:120px;height:60px;"></div></li>'+
            '        <li style="color:#555;font-size:14px;"><span>'+oneshow.showbdrel+'</span><br/>节目-品牌关联<div id="dtrelchart" style="width:120px;height:60px;"></div></li>'+
            '        <li style="color:#555;font-size:14px;"><span>'+oneshow.brandpro+'</span><br/>品牌资产提升<div id="dtprochart" style="width:120px;height:60px;"></div></li>'+
            '    </ul>'+
            '    <ul class="box_r3 origindata">'+
            '        <li style="width:30px;line-height:20px;color:#7e7e7e;margin-left:20px;margin-right:0px;padding:5px 0px;">三<br />级<br />指<br />数</li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.wtindex+'</span>收视热度<br/>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.rw+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+oneshow.rwnorm+'%</span>重复收视<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.kbindex+'</span>口碑传播</li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.rm+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+oneshow.rmnorm+'%</span>回忆度<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.rel+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+oneshow.relnorm+'%</span>相关度<br>'+
            '        </li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.rzup+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+oneshow.rznorm+'%</span>认知提升<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.xaup+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+oneshow.xanorm+'%</span>喜爱提升<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.tjup+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+oneshow.tjnorm+'%</span>推荐提升</li>'+
            '    </ul>'+
            '    <ul class="box_r3 indexdata">'+
            '        <li style="width:30px;line-height:20px;color:#7e7e7e;margin-left:20px;margin-right:0px;padding:5px 0px;">三<br />级<br />指<br />数</li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.wtindex+'</span>收视热度<br/>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.rwindex+'</span>重复收视<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.kbindex+'</span>口碑传播</li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.rmindex+'</span>回忆度<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.relindex+'</span>相关度<br>'+
            '        </li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.rzupindex+'</span>认知提升<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.xaupindex+'</span>喜爱提升<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+oneshow.tjupindex+'</span>推荐提升</li>'+
            '    </ul>'+
            '</div>';

        return Con;

    }catch(TypeError){
		console.log("节目详情数据类型错误！");
	}

}



function divbrandswtb(brandshow){
    try{
        var Con='';
        for (var i = 0; i < brandshow.length; i++) {

            if (brandshow[i].sponsortype=="总冠名"){
                Con+='<tr>'+
                    '<td style="width:160px;"><a  onclick="getshow(\''+brandshow[i].show+'\')">'+brandshow[i].show+'</a></td>'+
                    '<td style="width:90px;"><img src="http://svc.iresearch.cn/img/star1.png" style="width:20px;height:20px"></td>'+
                    '<td style="width:160px;">'+brandshow[i].svc+'</td>'+
                    '<td style="width:160px;">'+brandshow[i].showval+'</td>'+
                    '<td style="width:160px;">'+brandshow[i].showbdrel+'</td>'+
                    '<td style="width:160px;">'+brandshow[i].brandpro+'</td>'+
                    '</tr>';
            }else{
                Con+='<tr>'+
                    '<td style="width:160px;"><a  onclick="getshow(\''+brandshow[i].show+'\')">'+brandshow[i].show+'</a></td>'+
                    '<td style="width:90px;"><img src="http://svc.iresearch.cn/img/star2.png" style="width:20px;height:20px"></td>'+
                    '<td style="width:160px;">'+brandshow[i].svc+'</td>'+
                    '<td style="width:160px;">'+brandshow[i].showval+'</td>'+
                    '<td style="width:160px;">'+brandshow[i].showbdrel+'</td>'+
                    '<td style="width:160px;">'+brandshow[i].brandpro+'</td>'+
                    '</tr>';
            }

        }
        return Con;

    }catch(TypeError){
        console.log("品牌节目详情数据类型错误！");
    }


}




function dataList(data) {



	var  brandtype=data.brandtype;
	var  brandshow=data.brandshow;
	var  t1showtype=data.t1showtype;
    var  t1show=brandshow[0];
    var  brandname=t1show.brand;
    var  spontype=t1show.sponsortype;
    var  showlevel=t1show.showlevel;
	var  lbrandshowdt=data.lbrandshowdt;
	var  bdmax=data.bdindex;
	var  wbmax=data.wbindex;
	var  wxmax=data.wxindex;
	var  tgimax=data.showtgi;
	var  allshowrk=data.allshowrk;
	
	var bgdate=data.bgdate;
	var enddate=data.enddate;
	if(bgdate!=null && enddate!=null){
		  $('#statdatepickern').val(bgdate);
		  $('#enddatepickern').val(enddate);
	}
	var t1svcrk;
	for(var i=0;i<allshowrk.length;i++){
		if(allshowrk[i].show==t1show.show){
			t1svcrk=allshowrk[i].svcavgrk;
		}
	}
	var  brandswtbCon=divbrandswtb(brandshow);
    //var  branddtCon=divbranddt(brandname,brandtype,brandswtbCon);
    var  branddtCon=divbranddt(brandname,brandtype,t1show.sponsortype,t1showtype.showlevel);
    var  showdtCon=divshowdt(t1show,t1showtype,t1svcrk);
    $('#brandnamevn').val(brandname);
    $('#branddt').html(branddtCon);
    $('#showtbcon').html(brandswtbCon);
    $('#showdt1').html(showdtCon);



    //document.getElementById('storenormcondi').value = JSON.stringify(data.normcondi);
    document.getElementById('storedata').value = JSON.stringify(data);
    
    var t1showdtobj=new Array();

    for (var i=0;i<lbrandshowdt.length;i++){
    	if(t1show.show==lbrandshowdt[i].show){
    		t1showdtobj.push(lbrandshowdt[i]);
    	}
    }

    sshowcharts(t1showdtobj);
    loadporpertycharts(bdmax,wbmax,wxmax,tgimax,'max');


    //});
};

function dataList1(showname,data) {



	var  showtype=data.showtype;
	var  data1=JSON.parse(document.getElementById('storedata').value);
	var  brandshow=data1.brandshow;
    var  oneshow;
	var  lbrandshowdt=data1.lbrandshowdt;
	var  bdmax=data.bdindex;
	var  wbmax=data.wbindex;
	var  wxmax=data.wxindex;
	var  tgimax=data.showtgi;
	var  allshowrk=data1.allshowrk;

	for(var i=0;i<brandshow.length;i++){
		if(showname==brandshow[i].show){
			oneshow=brandshow[i];
		}
	}
	var svcrk;
	for(var i=0;i<allshowrk.length;i++){
		if(allshowrk[i].show==oneshow.show){
			svcrk=allshowrk[i].svcavgrk;
		}
	}


    var showdtCon=divshowdt(oneshow,showtype,svcrk);
    $('#showdt1').html(showdtCon);
    var t1showdtobj=new Array();

    for (var i=0;i<lbrandshowdt.length;i++){
    	if(showname==lbrandshowdt[i].show){
    		t1showdtobj.push(lbrandshowdt[i]);
    	}
    }
    
    sshowcharts(t1showdtobj);
    loadporpertycharts(bdmax,wbmax,wxmax,tgimax,'max');


    //});
};




function  getshow(showname){
	urlori='http://svc.iresearch.cn/api/svc/vip/brandcustom/getshowdt';
	$.ajax({
	    url: urlori,
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
        //
	    // }
	});





}
