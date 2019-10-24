var filnormcon=divfilnorm();
$('#norm').html(filnormcon[1]);
setDate();



var showname=getQueryString("showname");
showname1=encodeURI(showname);
urlori="http://svc.iresearch.cn/api/svc/vip/showlib/get1st?showname="+showname1;

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
        url: "http://svc.iresearch.cn/api/svc/vip/showlib/getnorm",
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

function divshowdtl(showsi){
	try{
        Con='<div class="imag" ><img src="'+showsi.photo+'" style="width:100%;height:100%;"></div>'+
            '<ul class="showtype" >'+
            '  <li><span>终端：</span>'+showsi.type+'</li>'+
            '  <li><span>平台：</span>'+showsi.platform+'</li>'+
            '  <li><span>类型：</span>'+showsi.showtype+'</li>'+
            '  <li><span>首播：</span>'+showsi.showstart+'</li>'+
            '  <li><span>导演：</span>'+showsi.directorname+'</li>'+
            '  <li><span>明星：</span></li>'+
            '  <li></li>'+
            '  <li><span>匹配度：</span>'+showsi.show_similar+'</li>'+
            '          <li><span>SVC指数：</span>'+showsi.svc_index+'</li>'+
            '</ul>';
        return Con;

	}catch(TypeError){
		console.log("节目详情数据左类型错误！")
	}

}

function divshowbd(showbd){
	try{
        var Con='';
        for(var i=0;i<showbd.length;i++){
            Con+='<tr>'+
                '<td><div style="width:30px;height:30px;"><img src="'+showbd[i].logo+'" style="width:100%;height:100%"></div></td>'+
                '<td>'+showbd[i].brandname+'</td>'+
                '<td>'+showbd[i].sponsortype+'</td>'+
                '</tr>'
        }
        return Con;
	}catch(TypeError){
		console.log("节目品牌数据类型错误！")
	}

}


function divlshow(lshowsi){
	try{
        var Con='';
        for(var i=0;i<lshowsi.length;i++){
            Con+='<li>'+
                '<div class="imag">'+
                '<div class="imag_tag">'+lshowsi[i].rank_num+'</div>'+
                '<a onclick="getshow(\''+lshowsi[i].showname+'\');"><img src="'+lshowsi[i].photo+'" style="width:100%;height:100%;"></a>'+
                '</div>'+
                '<div class="conte">'+
                '<span>'+lshowsi[i].showname+'</span><br>'+lshowsi[i].show_similar+'<br><br><a href="javascript:getshowsim(\''+lshowsi[i].showname+'\')">找相似</a></div>'+
                '</li>'
        }
        return Con;

	}catch(TypeError){
		console.log("相似度节目列表数据类型错误！");
	}

}

function dataList(data) {
	var lshowsi=data.lshowsi;
	var lshowbd=data.lshowbd;
	var t1showsi=lshowsi[0];
	var t1showname=t1showsi.showname;
	var t1showbd=new Array();
	for(var i=0;i<lshowbd.length;i++){
		if(lshowbd[i].showname==t1showname){
			t1showbd.push(lshowbd[i]);
		}
	}
	var showdtlCon=divshowdtl(t1showsi);
	var showdtrtbCon=divshowbd(t1showbd);
	var lshowCon=divlshow(lshowsi);
	$(".showdtl").html(showdtlCon);
	$("#showbdtb").html(showdtrtbCon);
	$('#tjshowlist').html(lshowCon);
	
}

function dataList1(data){
	try{
        pyout=data.pyout;
        $("#pyout").html(pyout);
	}catch(TypeError){
		console.log("节目预测数据类型错误！");
	}

}


function getshowsim(showname){
	urlori='http://svc.iresearch.cn/api/svc/vip/showlib/getsim';
	$.ajax({
	    url: urlori,
	    type: 'get',
	    dataType: 'json',
	    data:{
	    	showname:showname
		},
	    jsonp: 'callback',
	    async: true,
	    success: function (data) {
	        //callback(r);
	        dataList(data)
	    },
	    // error: function () {
	    //     var errorE = '抱歉，没有找到符合您要求的数据!';
	    //     alert(errorE);
        //
	    // }
	});
}

