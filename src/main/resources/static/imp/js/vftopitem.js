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

var brandtype =getQueryString("brandtype");

$.ajax({
    url: 'http://svc.iresearch.cn/api/vf/mi/getTopItem',
    type: 'get',
    dataType: 'json',
    data:{
    	brandtype:brandtype,
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
		   '<div class="imag"><img src="http://svc.iresearch.cn/vf_mi_img/'+link+'"></div>'+
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
    if(items.length==2){
    	bd1Con=divitem(items[0].t1link,"#e5e6e5")+divitem(items[0].t2link,"#e5e6e5")+divitem(items[0].t3link,"#e5e6e5")+divitem(items[0].t4link,"#e5e6e5")+divitem(items[0].t5link,"#e5e6e5");
    	bd2Con=divitem(items[1].t1link,"#fff")+divitem(items[1].t2link,"#fff")+divitem(items[1].t3link,"#fff")+divitem(items[1].t4link,"#fff")+divitem(items[1].t5link,"#fff");
    	$("#t1brand").html(bd1Con);
    	$("#t2brand").html(bd2Con);

    	}else if(items.length==3){
    		bd1Con=divitem(items[0].t1link,"#e5e6e5")+divitem(items[0].t2link,"#e5e6e5")+divitem(items[0].t3link,"#e5e6e5")+divitem(items[0].t4link,"#e5e6e5")+divitem(items[0].t5link,"#e5e6e5");
    		bd2Con=divitem(items[1].t1link,"#fff")+divitem(items[1].t2link,"#fff")+divitem(items[1].t3link,"#fff")+divitem(items[1].t4link,"#fff")+divitem(items[1].t5link,"#fff");
    		bd3Con=divitem(items[2].t1link,"#e5e6e5")+divitem(items[2].t2link,"#e5e6e5")+divitem(items[2].t3link,"#e5e6e5")+divitem(items[2].t4link,"#e5e6e5")+divitem(items[2].t5link,"#e5e6e5");
    		//bd4Con=divitem(items[3].t1link,"#fff")+divitem(items[3].t2link,"#fff")+divitem(items[3].t3link,"#fff")+divitem(items[3].t4link,"#fff")+divitem(items[3].t5link,"#fff");
    		
    		$("#t1brand").html(bd1Con);
    		$("#t2brand").html(bd2Con);
    		$("#t3brand").html(bd3Con);
    		//$("#t4brand").html(bd4Con);
        }else if(items.length==4){
        	bd1Con=divitem(items[0].t1link,"#e5e6e5")+divitem(items[0].t2link,"#e5e6e5")+divitem(items[0].t3link,"#e5e6e5")+divitem(items[0].t4link,"#e5e6e5")+divitem(items[0].t5link,"#e5e6e5");
        	bd2Con=divitem(items[1].t1link,"#fff")+divitem(items[1].t2link,"#fff")+divitem(items[1].t3link,"#fff")+divitem(items[1].t4link,"#fff")+divitem(items[1].t5link,"#fff");
        	bd3Con=divitem(items[2].t1link,"#e5e6e5")+divitem(items[2].t2link,"#e5e6e5")+divitem(items[2].t3link,"#e5e6e5")+divitem(items[2].t4link,"#e5e6e5")+divitem(items[2].t5link,"#e5e6e5");
        	bd4Con=divitem(items[3].t1link,"#fff")+divitem(items[3].t2link,"#fff")+divitem(items[3].t3link,"#fff")+divitem(items[3].t4link,"#fff")+divitem(items[3].t5link,"#fff");
        	$("#t1brand").html(bd1Con);
        	$("#t2brand").html(bd2Con);
        	$("#t3brand").html(bd3Con);
        	$("#t4brand").html(bd4Con);

        }else if(items.length==5){
        	bd1Con=divitem(items[0].t1link,"#e5e6e5")+divitem(items[0].t2link,"#e5e6e5")+divitem(items[0].t3link,"#e5e6e5")+divitem(items[0].t4link,"#e5e6e5")+divitem(items[0].t5link,"#e5e6e5");
        	bd2Con=divitem(items[1].t1link,"#fff")+divitem(items[1].t2link,"#fff")+divitem(items[1].t3link,"#fff")+divitem(items[1].t4link,"#fff")+divitem(items[1].t5link,"#fff");
        	bd3Con=divitem(items[2].t1link,"#e5e6e5")+divitem(items[2].t2link,"#e5e6e5")+divitem(items[2].t3link,"#e5e6e5")+divitem(items[2].t4link,"#e5e6e5")+divitem(items[2].t5link,"#e5e6e5");
        	bd4Con=divitem(items[3].t1link,"#fff")+divitem(items[3].t2link,"#fff")+divitem(items[3].t3link,"#fff")+divitem(items[3].t4link,"#fff")+divitem(items[3].t5link,"#fff");
        	bd5Con=divitem(items[4].t1link,"#e5e6e5")+divitem(items[4].t2link,"#e5e6e5")+divitem(items[4].t3link,"#e5e6e5")+divitem(items[4].t4link,"#e5e6e5")+divitem(items[4].t5link,"#e5e6e5");
        	
        	$("#t1brand").html(bd1Con);
        	$("#t2brand").html(bd2Con);
        	$("#t3brand").html(bd3Con);
        	$("#t4brand").html(bd4Con);
        	$("#t5brand").html(bd5Con);

    	}else if(items.length==6){
        	bd1Con=divitem(items[0].t1link,"#e5e6e5")+divitem(items[0].t2link,"#e5e6e5")+divitem(items[0].t3link,"#e5e6e5")+divitem(items[0].t4link,"#e5e6e5")+divitem(items[0].t5link,"#e5e6e5");
        	bd2Con=divitem(items[1].t1link,"#fff")+divitem(items[1].t2link,"#fff")+divitem(items[1].t3link,"#fff")+divitem(items[1].t4link,"#fff")+divitem(items[1].t5link,"#fff");
        	bd3Con=divitem(items[2].t1link,"#e5e6e5")+divitem(items[2].t2link,"#e5e6e5")+divitem(items[2].t3link,"#e5e6e5")+divitem(items[2].t4link,"#e5e6e5")+divitem(items[2].t5link,"#e5e6e5");
        	bd4Con=divitem(items[3].t1link,"#fff")+divitem(items[3].t2link,"#fff")+divitem(items[3].t3link,"#fff")+divitem(items[3].t4link,"#fff")+divitem(items[3].t5link,"#fff");
        	bd5Con=divitem(items[4].t1link,"#e5e6e5")+divitem(items[4].t2link,"#e5e6e5")+divitem(items[4].t3link,"#e5e6e5")+divitem(items[4].t4link,"#e5e6e5")+divitem(items[4].t5link,"#e5e6e5");
        	bd6Con=divitem(items[5].t1link,"#fff")+divitem(items[5].t2link,"#fff")+divitem(items[5].t3link,"#fff")+divitem(items[5].t4link,"#fff")+divitem(items[5].t5link,"#fff");
        	
        	$("#t1brand").html(bd1Con);
        	$("#t2brand").html(bd2Con);
        	$("#t3brand").html(bd3Con);
        	$("#t4brand").html(bd4Con);
        	$("#t5brand").html(bd5Con);
        	$("#t6brand").html(bd6Con);

    	}




    }
   
