function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
	}

var bgdate =getQueryString("bgdate");
//alert(getQueryString("enddate"));
var enddate =getQueryString("enddate").slice(0,6)+'01';

$.ajax({
    url: 'http://svc.iresearch.cn/VF/MI/getCbsTopItem',
    type: 'get',
    dataType: 'json',
    data:{
        type:bgdate,
        date:enddate
    },
    jsonp: 'callback',
    async: false,
    success: function (data) {
        //callback(r);
        dataList(data)
    },
    error: function() {
        var errorE = '抱歉，没有找到符合您要求的数据!';
        alert(errorE);
    }
});




function divitem(link,backcolor){


    Con='<li style="background:'+backcolor+'">'+
		   '<div class="imag"><img src="http://svc.iresearch.cn/file-img/'+link+'"></div>'+
		'</li>';
	return Con;
}

function dataList(data){
	var items=data;
//    var bd1=new Array();
//    var bd2=new Array();
//    var bd3=new Array();
//    var bd4=new Array();
//    var bd5=new Array();
	bd1Con=divitem(items[0].t1link,"#e8e8e8")+divitem(items[0].t2link,"#e8e8e8")+divitem(items[0].t3link,"#e8e8e8")+divitem(items[0].t4link,"#e8e8e8")+divitem(items[0].t5link,"#e8e8e8");
	bd2Con=divitem(items[1].t1link,"#f5f5f5")+divitem(items[1].t2link,"#f5f5f5")+divitem(items[1].t3link,"#f5f5f5")+divitem(items[1].t4link,"#f5f5f5")+divitem(items[1].t5link,"#f5f5f5");
	bd3Con=divitem(items[2].t1link,"#e8e8e8")+divitem(items[2].t2link,"#e8e8e8")+divitem(items[2].t3link,"#e8e8e8")+divitem(items[2].t4link,"#e8e8e8")+divitem(items[2].t5link,"#e8e8e8");
	bd4Con=divitem(items[3].t1link,"#f5f5f5")+divitem(items[3].t2link,"#f5f5f5")+divitem(items[3].t3link,"#f5f5f5")+divitem(items[3].t4link,"#f5f5f5")+divitem(items[3].t5link,"#f5f5f5");
	
	$("#t1brand").html(bd1Con);
	$("#t2brand").html(bd2Con);
	$("#t3brand").html(bd3Con);
	$("#t4brand").html(bd4Con);

    




    }
   
