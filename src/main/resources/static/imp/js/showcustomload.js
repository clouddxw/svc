var filnormcon=divfilnorm();
$('#filter').html(filnormcon[0]);
$('#normcon').html(filnormcon[1]);
setDate();

var showname = getQueryString("showname");
showname1=encodeURI(showname);
urlori='http://svc.iresearch.cn/api/svc/vip/showcustom/get1st?showname='+showname1;

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
        url: "http://svc.iresearch.cn/api/svc/vip/showcustom/getnorm",
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

function divshowdt(show,showtype){
	try{
        var Con='<div class="box_l">'+
            '        <div class="box_l1">'+show.show+'</div>'+
            '        <div class="box_l2"><img src="'+show.photo+'" style="width:100%;height:100%"></div>'+
            '        <ul class="box_l3">'+
            '          <li><span>SVC综合指数排名</span>：'+show.svcrk+'</li>'+
            '          <li><span>节目流量等级</span>：'+showtype.showlevel+'</li>    '+
            '        <li><span>终端</span>：'+showtype.type+'</li>'+
            '          <li><span>平台</span>：'+showtype.platform+'</li>'+
            '          <li><span>类型</span>：'+showtype.showtype+'</li>'+
            '          <li><span>首播</span>：'+showtype.begindate+'</li>'+
            '          <li><span>期数</span>：'+showtype.episode+'</li>'+
            '          <li><span>监测植入品牌数</span>：'+show.brandnum+'</li>  '+
            '        </ul>'+
            '</div>'+
            '<div class="box_r">'+
            '    <div class="box_r1">'+
            '        <div class="box_r1l"><img src="http://svc.iresearch.cn/img/star1.png" style="height:40px;width:40px;float:left;margin:13px 20px;margin-left:-10px;"><span style="float:left;">'+show.svc+'</span>'+
            '          (SVC综合指数)<div id="dtsvcchart" style="float:left;width:70px;height:50px;margin-left:20px;margin-top:20px;"></div>'+
            '        </div>'+
            '        <div style="height:20px;font-size:14px;line-height:12px;color:#7e7e7e;">VS之后对比的原始值为NORM</div>'+
            '    </div>'+
            '    <ul class="box_r2"  >'+
            '        <li style="width:30px;line-height:20px;color:#7e7e7e;margin:0px;margin-left:20px;padding:20px 0px;">二<br/>级<br/>指<br/>数</li>'+
            '        <li style="color:#555;font-size:14px;"><span>'+show.showval+'</span><br/>节目价值<div id="dtvalchart" style="width:120px;height:60px;"></div></li>'+
            '        <li style="color:#555;font-size:14px;"><span>'+show.showbdrel+'</span><br/>节目-品牌关联<div id="dtrelchart" style="width:120px;height:60px;"></div></li>'+
            '        <li style="color:#555;font-size:14px;"><span>'+show.brandpro+'</span><br/>品牌资产提升<div id="dtprochart" style="width:120px;height:60px;"></div></li>'+
            '    </ul>'+
            '    <ul class="box_r3 origindata">'+
            '        <li style="width:30px;line-height:20px;color:#7e7e7e;margin-left:20px;margin-right:0px;padding:5px 0px;">三<br />级<br />指<br />数</li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+show.wtindex+'</span>收视热度<br/>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.rw+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+show.rwnorm+'%</span>重复收视<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.kbindex+'</span>口碑传播</li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+show.rm+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+show.rmnorm+'%</span>回忆度<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.rel+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+show.relnorm+'%</span>相关度<br>'+
            '        </li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+show.rzup+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+show.rznorm+'%</span>认知提升<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.xaup+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+show.xanorm+'%</span>喜爱提升<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.tjup+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e">'+show.tjnorm+'%</span>推荐提升</li>'+
            '    </ul>'+
            '    <ul class="box_r3 indexdata">'+
            '        <li style="width:30px;line-height:20px;color:#7e7e7e;margin-left:20px;margin-right:0px;padding:5px 0px;">三<br />级<br />指<br />数</li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+show.wtindex+'</span>收视热度<br/>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.rwindex+'</span>重复收视<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.kbindex+'</span>口碑传播</li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+show.rmindex+'</span>回忆度<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.relindex+'</span>相关度<br>'+
            '        </li>'+
            '        <li><span class="v1" style="margin-right:5px;color:#1d914d">'+show.rzupindex+'</span>认知提升<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.xaupindex+'</span>喜爱提升<br>'+
            '            <span class="v1" style="margin-right:5px;color:#1d914d">'+show.tjupindex+'</span>推荐提升</li>'+
            '    </ul>'+
            '</div>';
        return Con;

	}catch(TypeError){
		console.log("节目详情数据类型错误！")
	}


}


function divshowbdl(show,tbcon){
	try{
        var Con='<div class="showbrand_l">'+
            '	<div class="showbrand_l1">'+
            '		<div class="showbrand_l1l">节目赞助品牌表现</div>'+
            '		<div class="showbrand_l1r">其他<img src="http://svc.iresearch.cn/img/star2.png" style="width:20px;height:20px;float:right;margin-right:25px;margin-top:15px;"></div>'+
            '		<div class="showbrand_l1r">总冠名<img src="http://svc.iresearch.cn/img/star1.png" style="width:20px;height:20px;float:right;margin-right:25px;margin-top:15px;"></div>'+
            '	</div>'+
            '<div class="brandtable" >'+
            '<table class="table table-striped" >'+
            '		<thead>'+
            '		<tr>'+
            '				<th>品牌数量:'+show.brandnum+'</th>'+
            '				<th></th>'+
            '				<th>期数</th>'+
            '				<th>SVC综合指数</th>'+
            '				<th>节目价值</th>'+
            '				<th>节目品牌关联</th>'+
            '				<th>品牌资产提升</th>'+
            '		</tr>'+
            '		</thead>'+
            '		<tbody class="scalbar_sty2">'+tbcon+'</tbody>'+
            '</table>'+
            '</div>'+
            '</div>';

        return Con;

	}catch(TypeError){
		console.log("节目品牌数据左类型错误！");

	}


}



function divshowbdr(brand){
	try{
        var Con='<div class ="branddetail origindata" id="branddtori">'+
            '<ul class="top1brandl" style="color:#7e7f7e;padding:0px 20px;margin-top:5px;">'+
            '     <li class="uli1" style="height:90px;">'+
            '         <div class="imag" style="witdh:70px;height:70px;margin:10px" ><img src="'+brand.logo+'" style="width:100%;height:100%;"></div>'+
            '         <div style="float:left;line-height:35px;margin:10px;font-size:25px;color:#222;">'+brand.brand+''+
            '           <div style="height:35px;font-size:15px;font-weight:normal;">'+brand.sponsortype+'</div>'+
            '         </div>'+
            '         <div style="float:right;margin-right:20px;line-height:30px;">'+
            '           <div style="height:30px;">SVC综合指数</div>'+
            '           <span style="font-size:35px;color:#da4353;line-height:60px;">'+brand.svc+'</span>'+
            '           <div id="bddtsvchartori" style="height:60px;width:80px;margin-left:10px;float:right;"></div>'+
            '         </div>'+
            '     </li >'+
            '     <li   style="height:200px;">'+
            '       <ul style="height:100%;width:50%;list-style:none;display:block;float:left;">'+
            '           <li style="height:80px;">'+
            '             <div style="width:120px;line-height:40px;font-size:15px;float:left;color:#555;">'+
            '                节目价值<br>'+
            '                <span style="height:40px;line-height:10px;font-size:30px;color:#1d914d">'+brand.showval+'</span></div>'+
            '                <div id="bddtvalchartori" style="height:70px;width:70px;margin:5px;float:left;"></div>'+
            '           </li>'+
            '             <li style="height:38px;line-height:38px;">'+
            '                 收视热度<span class="v1" style="margin-left:30px;color:#1d914d;font-size:20px;">'+brand.wtindex+'</span>'+
            '             </li>'+
            '             <li style="height:38px;line-height:38px;">重复收视<span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.rw+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e;font-size:20px;">'+brand.rwnorm+'%</span></li>'+
            '             <li style="height:38px;line-height:38px;">口碑传播<span class="v1" style="margin-left:30px;color:#1d914d;font-size:20px;">'+brand.kbindex+'</span></li>'+
            '       </ul>'+
            '       <div id="bddtvalsubchartori" style="height:90%;width:50%;float:left;"></div>'+
            '     </li>'+
            '    '+
            '     <li  style="height:200px;">'+
            '       <ul style="height:100%;width:50%;list-style:none;display:block;float:left;">'+
            '           <li style="height:80px;">'+
            '             <div style="width:120px;line-height:40px;font-size:15px;float:left;color:#555;">节目-品牌关联<br><span style="height:40px;line-height:10px;font-size:30px;color:#1d914d">'+brand.showbdrel+'</span></div>'+
            '             <div id="bddtrelchartori" style="height:70px;width:70px;margin:5px;float:left;"></div>'+
            '           </li>'+
            '             <li style="height:38px;line-height:38px;">回忆度<span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.rm+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e;font-size:20px;">'+brand.rmnorm+'%</span></li>'+
            '             <li style="height:38px;line-height:38px;">相关度<span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.rel+'%</span>VS<span class="v2" style="margin:0px 5px;color:#7e7e7e;font-size:20px;">'+brand.relnorm+'%</span></li>'+
            ' '+
            '       </ul>'+
            '       <div id="bddtrelsubchartori" style="height:90%;width:50%;float:left;"></div>'+
            '     </li>'+
            '    '+
            '     <li   style="height:200px;">'+
            '       <ul style="height:100%;width:50%;list-style:none;display:block;float:left;">'+
            '           <li style="height:80px;">'+
            '             <div style="width:120px;line-height:40px;font-size:15px;float:left;color:#555;">品牌资产提升<br><span style="height:40px;line-height:10px;font-size:30px;color:#1d914d">'+brand.brandpro+'</span></div>'+
            '             <div id="bddtprochartori" style="height:70px;width:70px;margin:5px;float:left;"></div>'+
            '           </li>'+
            '             <li style="height:38px;line-height:38px;">认知提升'+
            '                 <span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.rzup+'%</span>'+
            '                 VS<span class="v2" style="margin:0px 5px;color:#7e7e7e;font-size:20px;">'+brand.rznorm+'%</span></li>'+
            '             <li style="height:38px;line-height:38px;">喜爱提升'+
            '                <span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.xaup+'%</span>'+
            '                VS<span class="v2" style="margin:0px 5px;color:#7e7e7e;font-size:20px;">'+brand.xanorm+'%</span></li>'+
            '             <li style="height:38px;line-height:38px;">推荐提升'+
            '               <span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.tjup+'%</span>'+
            '               VS<span class="v2" style="margin:0px 5px;color:#7e7e7e;font-size:20px;">'+brand.tjnorm+'%</span></li>'+
            '       </ul>'+
            '       <div id="bddtprosubchartori" style="height:90%;width:50%;float:left;"></div>'+
            '     </li>'+
            '   '+
            ' </ul>'+
            ' </div>'+
            ' '+
            '<div class="branddetail indexdata" id="branddtindex" >'+
            '<ul class="top1brandl" style="color:#7e7f7e;padding:0px 20px;margin-top:5px;">'+
            '     <li class="uli1" style="height:90px;">'+
            '         <div class="imag" style="witdh:70px;height:70px;margin:10px" ><img src="'+brand.logo+'" style="width:100%;height:100%;"></div>'+
            '         <div style="float:left;line-height:35px;margin:10px;font-size:25px;color:#222;">'+brand.brand+''+
            '           <div style="height:35px;font-size:15px;font-weight:normal;">'+brand.sponsortype+'</div>'+
            '         </div>'+
            '         <div style="float:right;margin-right:20px;line-height:30px;">'+
            '           <div style="height:30px;">SVC综合指数</div>'+
            '           <span style="font-size:35px;color:#da4353;line-height:60px;">'+brand.svc+'</span>'+
            '           <div id="bddtsvchartindex" style="height:60px;width:80px;margin-left:10px;float:right;"></div>'+
            '         </div>'+
            '     </li >'+
            '     <li   style="height:200px;">'+
            '       <ul style="height:100%;width:50%;list-style:none;display:block;float:left;">'+
            '           <li style="height:80px;">'+
            '             <div style="width:120px;line-height:40px;font-size:15px;float:left;color:#555;">'+
            '                节目价值<br>'+
            '                <span style="height:40px;line-height:10px;font-size:30px;color:#1d914d">'+brand.showval+'</span></div>'+
            '                <div id="bddtvalchartindex" style="height:70px;width:70px;margin:5px;float:left;"></div>'+
            '           </li>'+
            '             <li style="height:38px;line-height:38px;">'+
            '                 收视热度<span class="v1" style="margin-left:30px;color:#1d914d;font-size:20px;">'+brand.wtindex+'</span>'+
            '             </li>'+
            '             <li style="height:38px;line-height:38px;">重复收视<span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.rwindex+'</span></li>'+
            '             <li style="height:38px;line-height:38px;">口碑传播<span class="v1" style="margin-left:30px;color:#1d914d;font-size:20px;">'+brand.kbindex+'</span></li>'+
            '       </ul>'+
            '       <div id="bddtvalsubchartindex" style="height:180px;width:230px;float:left;"></div>'+
            '     </li>'+
            '     <li  style="height:200px;">'+
            '       <ul style="height:100%;width:50%;list-style:none;display:block;float:left;">'+
            '           <li style="height:80px;">'+
            '             <div style="width:120px;line-height:40px;font-size:15px;float:left;color:#555;">节目-品牌关联<br><span style="height:40px;line-height:10px;font-size:30px;color:#1d914d">'+brand.showbdrel+'</span></div>'+
            '             <div id="bddtrelchartindex" style="height:70px;width:70px;margin:5px;float:left;"></div>'+
            '           </li>'+
            '             <li style="height:38px;line-height:38px;">回忆度<span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.rmindex+'</span></li>'+
            '             <li style="height:38px;line-height:38px;">相关度<span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.relindex+'</span></li>'+
            ' '+
            '       </ul>'+
            '       <div id="bddtrelsubchartindex" style="height:180px;width:230px;float:left;"></div>'+
            '     </li>'+
            '    <li   style="height:200px;">'+
            '       <ul style="height:100%;width:50%;list-style:none;display:block;float:left;">'+
            '           <li style="height:80px;">'+
            '             <div style="width:120px;line-height:40px;font-size:15px;float:left;color:#555;">品牌资产提升<br><span style="height:40px;line-height:10px;font-size:30px;color:#1d914d">'+brand.brandpro+'</span></div>'+
            '             <div id="bddtprochartindex" style="height:70px;width:70px;margin:5px;float:left;"></div>'+
            '           </li>'+
            '             <li style="height:38px;line-height:38px;">认知提升'+
            '                 <span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.rzupindex+'</span></li>'+
            '             <li style="height:38px;line-height:38px;">喜爱提升'+
            '                <span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.xaupindex+'</span></li>'+
            '             <li style="height:38px;line-height:38px;">推荐提升'+
            '               <span class="v1" style="margin:0px 8px;color:#1d914d;font-size:20px;">'+brand.tjupindex+'</span></li>'+
            '       </ul>'+
            '       <div id="bddtprosubchartindex" style="height:180px;width:230px;float:left;"></div>'+
            '     </li>'+
            ' </ul>'+
            ' </div>';
        return  Con;

	}catch(TypeError){
		console.log("节目品牌数据右类型错误！");
	}

}

function divshowbdtb(showbrand){
	try{
        var Con='';
        for (var i = 0; i < showbrand.length; i++) {

            if (showbrand[i].sponsortype=="总冠名"){
                Con+='<tr>'+
                    '<td style="width:160px;"><a  onclick="getbrand(\''+showbrand[i].brand+'\')">'+showbrand[i].brand+'</a></td>'+
                    '<td style+"width:40px;"><img src="http://svc.iresearch.cn/img/star1.png" style="width:20px;height:20px"></td>'+
                    '<td style="width:50px;">'+showbrand[i].episodenum+'</td>'+
                    '<td style="width:110px;">'+showbrand[i].svc+'</td>'+
                    '<td style="width:80px;">'+showbrand[i].showval+'</td>'+
                    '<td style="width:110px;">'+showbrand[i].showbdrel+'</td>'+
                    '<td style="width:110px;">'+showbrand[i].brandpro+'</td>'+
                    '</tr>';
            }else{
                Con+='<tr>'+
                    '<td style="width:160px"><a  onclick="getbrand(\''+showbrand[i].brand+'\')">'+showbrand[i].brand+'</a></td>'+
                    '<td style="width:40px;"><img src="http://svc.iresearch.cn/img/star2.png" style="width:20px;height:20px"></td>'+
                    '<td style="width:50px;">'+showbrand[i].episodenum+'</td>'+
                    '<td style="width:110px;">'+showbrand[i].svc+'</td>'+
                    '<td style="width:80px">'+showbrand[i].showval+'</td>'+
                    '<td style="width:110px;">'+showbrand[i].showbdrel+'</td>'+
                    '<td style="width:110px;">'+showbrand[i].brandpro+'</td>'+
                    '</tr>';
            }

        }
        return Con;
	}catch(TypeError){
		console.log("节目品牌详情数据类型错误！");
	}

}




function dataList(data) {


	var  show=data.show;
	var  sshow=data.sshow;
	var  showtype=data.showtype;
	var  showbrand=data.showbrand;
	var  t1brand=showbrand[0];
	var  lshowbranddt=data.lshowbranddt;
	var  bdmax=data.bdindex;
	var  wbmax=data.wbindex;
	var  wxmax=data.wxindex;
	var  tgimax=data.showtgi;
	
	var bgdate=data.bgdate;
	var enddate=data.enddate;
	if(bgdate!=null && enddate!=null){
		  $('#statdatepickern').val(bgdate);
		  $('#enddatepickern').val(enddate);
	}

    
	var  showbdtbCon=divshowbdtb(showbrand);
    var  showdtCon=divshowdt(show,showtype);
    //var  showbdlCon=divshowbdl(show,showbdtbCon);
    var  showbdrCon=divshowbdr(t1brand);
    $('#shownamevn').val(show.show);
    $('#showdt').html(showdtCon);
    $('#bdtbthnum').html("品牌数量："+show.brandnum);
    $('#brandtbcon').html(showbdtbCon);
    //$('#showbdl').html(showbdlCon);
    $('#showbdr').html(showbdrCon);



    //document.getElementById('storenormcondi').value = JSON.stringify(data.normcondi);
    document.getElementById('storedata').value = JSON.stringify(data);

    sshowcharts(sshow);
    
    var t1branddtobj=new Array();
    for (var i=0;i<lshowbranddt.length;i++){
    	if(t1brand.brand==lshowbranddt[i].brand){
    		t1branddtobj.push(lshowbranddt[i]);
    	}
    }
    sshowbdcharts(t1branddtobj);
    loadporpertycharts(bdmax,wbmax,wxmax,tgimax,'max');


    //});
};






function  getbrand(brandname){

	var data=JSON.parse(document.getElementById('storedata').value);
    var showbrand=data.showbrand;
    var lshowbranddt=data.lshowbranddt;
	var onebrand;
	for(var i=0;i<showbrand.length;i++){
		if(showbrand[i].brand==brandname){
			onebrand=showbrand[i];
		}
	}

    var  showbdrCon=divshowbdr(onebrand);
    $('#showbdr').html(showbdrCon);


    var t1branddtobj=new Array();
    for (var i=0;i<lshowbranddt.length;i++){
    	if(onebrand.brand==lshowbranddt[i].brand){
    		t1branddtobj.push(lshowbranddt[i]);
    	}
    }
    sshowbdcharts(t1branddtobj);



}
