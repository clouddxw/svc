$(document).ready(function () {
    $('#vip_scselect').select2();
    $('#vip_bcselect').select2();
    $('#vip_lkselect').select2();
    $('#basic_scselect').select2();
    $('#basic_bcselect').select2();
    $('#basic_lkselect').select2();

});


function getTopWinow(){
    var p = window;
    while(p != p.parent){
        p = p.parent;
    }
    return p;
}

$.ajax({
    url: 'http://svc.iresearch.cn/api/svc/index/getData',
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



function dataList(data){
	var newslist=data.newslist;
	var customshow=data.customshow;
	var custombrand=data.custombrand;
	var allshow=data.allshow;
	var newsCon='';
	var newsImg='';
	var showcCon='';
	var brandcCon='';
	var showlibCon='';
	for(var i=0;i<newslist.length;i++){
		imglink="http://svc.iresearch.cn/"+newslist[i].imglink;
		newsImg+='<a href="'+newslist[i].titlelink+'" target="_blank" style="text-decoration:none"> <div class="banner-slide slide'+i+'" ><img src="'+imglink+'" alt="'+i+'" /></div></a>';
		if(i==0){
			newsCon+='<li class="changeColor"><a href="'+newslist[i].titlelink+'" target="_blank"><span class="v1">'+newslist[i].title1+'</span><span class="v2">：</span>'+newslist[i].title2+'</a></li>';
		}else{
			newsCon+='<li><a href="'+newslist[i].titlelink+'" target="_blank" style="text-decoration:none"><span class="v1">'+newslist[i].title1+'</span><span class="v2">：</span>'+newslist[i].title2+'</a></li>';
		}
	}
	for(var i=0;i<customshow.length;i++){
		showcCon+='<option value="'+customshow[i].show+'">'+customshow[i].show+'</option>';
	}
	for(var i=0;i<custombrand.length;i++){
		brandcCon+='<option value="'+custombrand[i].brand+'">'+custombrand[i].brand+'</option>';
	}
	
	for(var i=0;i<allshow.length;i++){
		showlibCon+='<option value="'+allshow[i].show+'">'+allshow[i].show+'</option>';
	}
	
	$("#newsimg").html(newsImg);
	$("#newslist").html(newsCon);
	$("#vip_scselect").html(showcCon);
	$("#vip_bcselect").html(brandcCon);
	$("#vip_lkselect").html(showlibCon);
    $("#basic_scselect").html(showcCon);
    $("#basic_bcselect").html(brandcCon);
    $("#basic_lkselect").html(showlibCon);
	
	
	
}
