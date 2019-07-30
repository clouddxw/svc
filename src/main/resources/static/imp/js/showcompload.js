var filnormcon=divfilnorm();
$('#filter').html(filnormcon[0]);
$('#normcon').html(filnormcon[1]);
setDate();



$.ajax({
    url: 'http://svc.iresearch.cn/showcomp/get1st',
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

function getnorm() {
    //alert($('#formn').serialize());
    $.ajax({
        url: "http://svc.iresearch.cn/showcomp/getnorm",
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

function dataList(data) {
    try{
        var  shownlist=data.shownlist;
        var  brandnlist=data.brandnlist;
        var  bgdate=data.bgdate;
        var  enddate=data.enddate;
        if(bgdate!=null && enddate!=null){
            $('#statdatepickern').val(bgdate);
            $('#enddatepickern').val(enddate);
        }

        var showopt="";
        var brandopt="";

        for (var i = 0; i < shownlist.length; i++) {
            showopt+='<option value="'+shownlist[i].show+'">'+shownlist[i].show+'</option>';
        }



        for (var i = 0; i < brandnlist.length; i++) {
            brandopt+='<option value="'+brandnlist[i].brand+'">'+brandnlist[i].brand+'</option>';
        }

        $('#showname_l').html(showopt);
        $('#showname_m').html(showopt);
        $('#showname_r').html(showopt);
        $('#brandname_l').html(brandopt);
        $('#brandname_m').html(brandopt);
        $('#brandname_r').html(brandopt);
        document.getElementById('storeallshowrk').value=JSON.stringify(data.shownlist);

    }catch(TypeError){
        console.log("节目品牌清单数据类型错误！");
    }


};

function divshowdt(oneshow,showtype,disp,oneshowrk){
    try{
        var svcrk;
        if(disp=="avg"){
            svcrk=oneshow.svcavgrk;
        }else if(disp==""){
            svcrk=oneshowrk.svcavgrk;
        }

        var Con='     <div class="imgx">'+
            '                 <img src="'+oneshow.photo+'" style="width:100%;height:100%;">'+
            '             </div>'+
            '             <ul>'+
            '                 <li>SVC综合指数排名：'+svcrk+'</li>'+
            '                 <li>节目流量等级：'+showtype.showlevel+'</li>'+
            '                 <li>首播：'+showtype.begindate+'</li>'+
            '                 <li>期数：'+showtype.episode+'</li>'+
            '                 <li>终端：'+showtype.type+'</li>'+
            '                 <li>平台：'+showtype.platform+'</li>'+
            '                 <li>类型：'+showtype.showtype+'</li>'+
            '             </ul>';
        return Con;

    }catch(TypeError){
        console.log("节目详情数据类型错误！");
    }

}

function divshowindex(oneshow,disp){
    try{
        var svc;
        var showval;
        var wtindex;
        var rwindex;
        var kbindex;
        var showbdrel;
        var rmindex;
        var relindex;
        var brandpro;
        var rzupindex;
        var xaupindex;
        var tjupindex;
        if(disp=="max"){
            svc=oneshow.svcmax;
            showval=oneshow.showvalmax;
            wtindex=oneshow.wtindexmax;
            rwindex=oneshow.rwindexmax;
            kbindex=oneshow.kbindexmax;
            showbdrel=oneshow.showbdrelmax;
            rmindex=oneshow.rmindexmax;
            relindex=oneshow.relindexmax;
            brandpro=oneshow.brandpromax;
            rzupindex=oneshow.rzupindexmax;
            xaupindex=oneshow.xaupindexmax;
            tjupindex=oneshow.tjupindexmax;

        }else if(disp=="avg"){
            svc=oneshow.svcavg;
            showval=oneshow.showvalavg;
            wtindex=oneshow.wtindexavg;
            rwindex=oneshow.rwindexavg;
            kbindex=oneshow.kbindexavg;
            showbdrel=oneshow.showbdrelavg;
            rmindex=oneshow.rmindexavg;
            relindex=oneshow.relindexavg;
            brandpro=oneshow.brandproavg;
            rzupindex=oneshow.rzupindexavg;
            xaupindex=oneshow.xaupindexavg;
            tjupindex=oneshow.tjupindexavg;
        }else if(disp=="sum"){
            svc=oneshow.svcsum;
            showval=oneshow.showvalsum;
            wtindex=oneshow.wtindexsum;
            rwindex=oneshow.rwindexsum;
            kbindex=oneshow.kbindexsum;
            showbdrel=oneshow.showbdrelsum;
            rmindex=oneshow.rmindexsum;
            relindex=oneshow.relindexsum;
            brandpro=oneshow.brandprosum;
            rzupindex=oneshow.rzupindexsum;
            xaupindex=oneshow.xaupindexsum;
            tjupindex=oneshow.tjupindexsum;
        }else if(disp==""){
            svc=oneshow.svc;
            showval=oneshow.showval;
            wtindex=oneshow.wtindex;
            rwindex=oneshow.rwindex;
            kbindex=oneshow.kbindex;
            showbdrel=oneshow.showbdrel;
            rmindex=oneshow.rmindex;
            relindex=oneshow.relindex;
            brandpro=oneshow.brandpro;
            rzupindex=oneshow.rzupindex;
            xaupindex=oneshow.xaupindex;
            tjupindex=oneshow.tjupindex;
        }
        var Con='<ul class="contentx">'+
            '         <li class="s1title">'+svc+'</li>'+
            '         <li class="s2title">'+showval+'</li>'+
            '         <li>'+wtindex+'</li>'+
            '         <li>'+rwindex+'</li>'+
            '         <li>'+kbindex+'</li>'+
            '         <li class="s2title">'+showbdrel+'</li>'+
            '         <li>'+rmindex+'</li>'+
            '         <li>'+relindex+'</li>'+
            '         <li class="s2title">'+brandpro+'</li>'+
            '         <li>'+rzupindex+'</li>'+
            '         <li>'+xaupindex+'</li>'+
            '         <li>'+tjupindex+'</li>'+
            '         </ul>';

        return Con;

    }catch(TypeError){
        console.log("节目指数数据类型错误！")
    }


}

function divswapcon(brandshow,fid,disp){
    try{
        var Con='';
        for (var i=0;i<brandshow.length;i++){
            Con+='<div class="swiper-slide">'+
                '   <div><a onclick="getoneshow(\''+brandshow[i].show+'\',\''+fid+'\',\''+disp+'\');">'+brandshow[i].show+'</a><br>'+brandshow[i].svc+'</div>'+
                '</div>';
        }
        return Con;


    }catch(TypeError){
        console.log("翻页数据类型错误！")

    }


}

function dataList1(id1,id2,disp,data){

    var oneshow=data.oneshow;
    var showtype=data.showtype;

    var showdtCon=divshowdt(oneshow,showtype,disp);
    var showindexCon=divshowindex(oneshow,disp);

	$(id1).html(showdtCon);
    $(id2).html(showindexCon);
}




function dataList2(disp,data){

    var  brandshow=data.brandshow;
    var  t1show=brandshow[0];
    var  showtype=data.showtype;
    var  brandcomp=data.brandcomp;
    var  storeeleid='storebrand'+disp;
    var  ttid='brandswdt'+disp;
    var  allshowrk=JSON.parse(document.getElementById("storeallshowrk").value);
    var  oneshowrk;
    for(var i=0;i<allshowrk.length;i++){
    	if(allshowrk[i].show==t1show.show){
    		oneshowrk=allshowrk[i];
    	}
    }



    var swapCon=divswapcon(brandshow,storeeleid,disp);
    var showdtCon=divshowdt(t1show,showtype,'',oneshowrk);
    var maxCon=divshowindex(brandcomp,'max');
    var avgCon=divshowindex(brandcomp,'avg');
    var sumCon=divshowindex(brandcomp,'sum');
    $('#swapcon'+disp).html(swapCon);
    $('#brandswdt'+disp).html(showdtCon);
    $('#maxindex'+disp).html(maxCon);
    $('#avgindex'+disp).html(avgCon);
    $('#sumindex'+disp).html(sumCon);

    document.getElementById(storeeleid).value=JSON.stringify(data);

    var swiper = new Swiper('.swiper-container.swap'+disp, {
        pagination: {
            el: '.swiper-pagination',
            type: 'progressbar',
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });

	}


function dataList3(showname,fid,disp,data){
    var  bddata=JSON.parse(document.getElementById(fid).value);
    var  allshowrk=JSON.parse(document.getElementById("storeallshowrk").value);
    var  showtype=data.showtype;
    var  brandshow=bddata.brandshow
    var  oneshow;
    var  oneshowrk;
    for(var i=0;i<brandshow.length;i++){
    	if(brandshow[i].show==showname){
    		oneshow=brandshow[i];
    	}
    }
    for(var i=0;i<allshowrk.length;i++){
    	if(allshowrk[i].show==showname){
    		oneshowrk=allshowrk[i];
    	}
    }
    
    
    var showdtCon=divshowdt(oneshow,showtype,'',oneshowrk);
    $('#brandswdt'+disp).html(showdtCon);
    var oshowCon=divshowindex(oneshow,'');
    $('#oshowindex'+disp).html(oshowCon);
    chgdisplay('maxindex'+disp,'none');
    chgdisplay('avgindex'+disp,'none');
    chgdisplay('sumindex'+disp,'none');
    chgdisplay('oshowindex'+disp,'block');

	}


function getshow(fid,id1,id2,disp){

	var showname=$("#"+fid).val();
//	var normcon=document.getElementById('storenormcondi').value;
//	var normcondi=encodeURIComponent(normcon.replace(/"/g,""));
    urlori='http://svc.iresearch.cn/showcomp/getshow';
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
	        dataList1(id1,id2,disp,data)
	    },
	    // error: function () {
	    //     var errorE = '抱歉，没有找到符合您要求的数据!';
	    //     alert(errorE);
        //
	    // }
	});


}


function getbrand(fid,disp){
	//var disp=document.getElementById('dispara').value;
	var brandname=$("#"+fid).val();
	//var normcon=document.getElementById('storenormcondi').value;
	//var normcondi=encodeURIComponent(normcon.replace(/"/g,""));
    urlori='http://svc.iresearch.cn/showcomp/getbrand';
	$.ajax({
	    url: urlori,
	    type: 'post',
	    dataType: 'json',
	    data:{
	        brandname:brandname
        },
	    jsonp: 'callback',
	    async: true,
	    success: function (data) {
	        //callback(r);
	        dataList2(disp,data);
	    },

	});


}

function getoneshow(showname,fid,disp){
    urlori='http://svc.iresearch.cn/showcomp/getshowtype';
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
	        dataList3(showname,fid,disp,data);
	    },
	    // error: function () {
	    //     var errorE = '抱歉，没有找到符合您要求的数据!';
	    //     alert(errorE);
        //
	    // }
	});
}
	
