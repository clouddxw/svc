function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
	}


function formatDate(date,del) {
	var del1;
	if(del==null){
		del1="";
	}else{
		del1=del;
	}
    var myyear = date.getFullYear();
    var mymonth = date.getMonth() + 1;
    var myweekday = date.getDate();
 
    if (mymonth < 10) {
        mymonth = "0" + mymonth;
    }
    if (myweekday < 10) {
        myweekday = "0" + myweekday;
    }
    return (myyear + del1 + mymonth + del1 + myweekday);
}


var bgdate =getQueryString("bgdate");
var enddate =getQueryString("enddate");
//alert(enddate);
if(bgdate=='quarter'){
	var tmpdate = new Date(enddate.slice(4,6)+'/01/'+enddate.slice(0,4));
	tmpdate.setMonth(tmpdate.getMonth()+1);
	tmpdate.setDate(tmpdate.getDate()-1);
	enddate=formatDate(tmpdate);
}else{
	enddate=enddate.slice(0,6)+"01";
}

var industry_en =getQueryString("industry_en");
var industry2 =getQueryString("industry2");

$.ajax({
    url: 'http://svc.iresearch.cn/Lee/MI/getTopItem',
    type: 'get',
    dataType: 'json',
    data:{
		type:bgdate,
		date:enddate,
        industry_en:industry_en,
        industry2:industry2
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
		   '<div class="imag"><img src="http://svc.iresearch.cn/lee_mi_proimg/'+link+'"></div>'+
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

	bd1Con=divitem(items[0].t1link,"#e5e6e5")+divitem(items[0].t2link,"#e5e6e5")+divitem(items[0].t3link,"#e5e6e5")+divitem(items[0].t4link,"#e5e6e5")+divitem(items[0].t5link,"#e5e6e5")+
           divitem(items[0].t6link,"#e5e6e5")+divitem(items[0].t7link,"#e5e6e5")+divitem(items[0].t8link,"#e5e6e5")+divitem(items[0].t9link,"#e5e6e5")+divitem(items[0].t10link,"#e5e6e5");
	bd2Con=divitem(items[1].t1link,"#fff")+divitem(items[1].t2link,"#fff")+divitem(items[1].t3link,"#fff")+divitem(items[1].t4link,"#fff")+divitem(items[1].t5link,"#fff")+
           divitem(items[1].t6link,"#fff")+divitem(items[1].t7link,"#fff")+divitem(items[1].t8link,"#fff")+divitem(items[1].t9link,"#fff")+divitem(items[1].t10link,"#fff");
	bd3Con=divitem(items[2].t1link,"#e5e6e5")+divitem(items[2].t2link,"#e5e6e5")+divitem(items[2].t3link,"#e5e6e5")+divitem(items[2].t4link,"#e5e6e5")+divitem(items[2].t5link,"#e5e6e5")+
	       divitem(items[2].t6link,"#e5e6e5")+divitem(items[2].t7link,"#e5e6e5")+divitem(items[2].t8link,"#e5e6e5")+divitem(items[2].t9link,"#e5e6e5")+divitem(items[2].t10link,"#e5e6e5");
	bd4Con=divitem(items[3].t1link,"#fff")+divitem(items[3].t2link,"#fff")+divitem(items[3].t3link,"#fff")+divitem(items[3].t4link,"#fff")+divitem(items[3].t5link,"#fff")+
           divitem(items[3].t6link,"#fff")+divitem(items[3].t7link,"#fff")+divitem(items[3].t8link,"#fff")+divitem(items[3].t9link,"#fff")+divitem(items[3].t10link,"#fff");
	bd5Con=divitem(items[4].t1link,"#e5e6e5")+divitem(items[4].t2link,"#e5e6e5")+divitem(items[4].t3link,"#e5e6e5")+divitem(items[4].t4link,"#e5e6e5")+divitem(items[4].t5link,"#e5e6e5")+
           divitem(items[4].t6link,"#e5e6e5")+divitem(items[4].t7link,"#e5e6e5")+divitem(items[4].t8link,"#e5e6e5")+divitem(items[4].t9link,"#e5e6e5")+divitem(items[4].t10link,"#e5e6e5");
	bd6Con=divitem(items[5].t1link,"#fff")+divitem(items[5].t2link,"#fff")+divitem(items[5].t3link,"#fff")+divitem(items[5].t4link,"#fff")+divitem(items[5].t5link,"#fff")+
		   divitem(items[5].t6link,"#fff")+divitem(items[5].t7link,"#fff")+divitem(items[5].t8link,"#fff")+divitem(items[5].t9link,"#fff")+divitem(items[5].t10link,"#fff");

	$("#t1brand").html(bd1Con);
	$("#t2brand").html(bd2Con);
	$("#t3brand").html(bd3Con);
	$("#t4brand").html(bd4Con);
	$("#t5brand").html(bd5Con);
	$("#t6brand").html(bd6Con);



    }
   
