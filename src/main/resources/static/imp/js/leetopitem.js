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



function str_br(sStr, iPerLineLen ,lf){
    //var sStr=document.getElementById(id).innerHTML;
    if(sStr.replace(/[^\x00-\xff]/g,"xx").length <= iPerLineLen){
        return -1;
    }

    var str="";
    var l=0;
    var schar;
    for(var i=0;schar=sStr.charAt(i);i++){
        str+=schar;
        l+=(schar.match(/[^\x00-\xff]/)!=null?2:1);
        if(l>= iPerLineLen){
            //str+="\n";
            str+=lf;
            l=0;
        }
    }
    //document.getElementById(id).innerHTML=str;
    return str;
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


function divitem(link,units,rate,items,backcolor){


    Con='<li style="background:'+backcolor+'">'+
        '<div class="tooltip">'+
		   '<div class="imag"><img src="http://svc.iresearch.cn/lee_mi_proimg/'+link+'"></div>'+
		   '<div class="units">'+units+'/'+rate+'%'+'</div>'+
           '<span class="tooltiptext">'+str_br(items,24,"<br>")+'</span>'+
        '</div>'+
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

	bd1Con =divitem(items[0].t1link,	items[0].t1units,	items[0].t1rate,	items[0].t1items,	"#e5e6e5")+
            divitem(items[0].t2link,	items[0].t2units,	items[0].t2rate,	items[0].t2items,	"#e5e6e5")+
            divitem(items[0].t3link,	items[0].t3units,	items[0].t3rate,	items[0].t3items,	"#e5e6e5")+
            divitem(items[0].t4link,	items[0].t4units,	items[0].t4rate,	items[0].t4items,	"#e5e6e5")+
            divitem(items[0].t5link,	items[0].t5units,	items[0].t5rate,	items[0].t5items,	"#e5e6e5")+
            divitem(items[0].t6link,	items[0].t6units,	items[0].t6rate,	items[0].t6items,	"#e5e6e5")+
            divitem(items[0].t7link,	items[0].t7units,	items[0].t7rate,	items[0].t7items,	"#e5e6e5")+
            divitem(items[0].t8link,	items[0].t8units,	items[0].t8rate,	items[0].t8items,	"#e5e6e5")+
            divitem(items[0].t9link,	items[0].t9units,	items[0].t9rate,	items[0].t9items,	"#e5e6e5")+
            divitem(items[0].t10link,	items[0].t10units,	items[0].t10rate,	items[0].t10items,	"#e5e6e5");
    bd2Con =divitem(items[1].t1link,	items[1].t1units,	items[1].t1rate,	items[1].t1items,	"#fff")+
            divitem(items[1].t2link,	items[1].t2units,	items[1].t2rate,	items[1].t2items,	"#fff")+
            divitem(items[1].t3link,	items[1].t3units,	items[1].t3rate,	items[1].t3items,	"#fff")+
            divitem(items[1].t4link,	items[1].t4units,	items[1].t4rate,	items[1].t4items,	"#fff")+
            divitem(items[1].t5link,	items[1].t5units,	items[1].t5rate,	items[1].t5items,	"#fff")+
            divitem(items[1].t6link,	items[1].t6units,	items[1].t6rate,	items[1].t6items,	"#fff")+
            divitem(items[1].t7link,	items[1].t7units,	items[1].t7rate,	items[1].t7items,	"#fff")+
            divitem(items[1].t8link,	items[1].t8units,	items[1].t8rate,	items[1].t8items,	"#fff")+
            divitem(items[1].t9link,	items[1].t9units,	items[1].t9rate,	items[1].t9items,	"#fff")+
            divitem(items[1].t10link,	items[1].t10units,	items[1].t10rate,	items[1].t10items,	"#fff");
    bd3Con =divitem(items[2].t1link,	items[2].t1units,	items[2].t1rate,	items[2].t1items,	"#e5e6e5")+
            divitem(items[2].t2link,	items[2].t2units,	items[2].t2rate,	items[2].t2items,	"#e5e6e5")+
            divitem(items[2].t3link,	items[2].t3units,	items[2].t3rate,	items[2].t3items,	"#e5e6e5")+
            divitem(items[2].t4link,	items[2].t4units,	items[2].t4rate,	items[2].t4items,	"#e5e6e5")+
            divitem(items[2].t5link,	items[2].t5units,	items[2].t5rate,	items[2].t5items,	"#e5e6e5")+
            divitem(items[2].t6link,	items[2].t6units,	items[2].t6rate,	items[2].t6items,	"#e5e6e5")+
            divitem(items[2].t7link,	items[2].t7units,	items[2].t7rate,	items[2].t7items,	"#e5e6e5")+
            divitem(items[2].t8link,	items[2].t8units,	items[2].t8rate,	items[2].t8items,	"#e5e6e5")+
            divitem(items[2].t9link,	items[2].t9units,	items[2].t9rate,	items[2].t9items,	"#e5e6e5")+
            divitem(items[2].t10link,	items[2].t10units,	items[2].t10rate,	items[2].t10items,	"#e5e6e5");
    bd4Con =divitem(items[3].t1link,	items[3].t1units,	items[3].t1rate,	items[3].t1items,	"#fff")+
            divitem(items[3].t2link,	items[3].t2units,	items[3].t2rate,	items[3].t2items,	"#fff")+
            divitem(items[3].t3link,	items[3].t3units,	items[3].t3rate,	items[3].t3items,	"#fff")+
            divitem(items[3].t4link,	items[3].t4units,	items[3].t4rate,	items[3].t4items,	"#fff")+
            divitem(items[3].t5link,	items[3].t5units,	items[3].t5rate,	items[3].t5items,	"#fff")+
            divitem(items[3].t6link,	items[3].t6units,	items[3].t6rate,	items[3].t6items,	"#fff")+
            divitem(items[3].t7link,	items[3].t7units,	items[3].t7rate,	items[3].t7items,	"#fff")+
            divitem(items[3].t8link,	items[3].t8units,	items[3].t8rate,	items[3].t8items,	"#fff")+
            divitem(items[3].t9link,	items[3].t9units,	items[3].t9rate,	items[3].t9items,	"#fff")+
            divitem(items[3].t10link,	items[3].t10units,	items[3].t10rate,	items[3].t10items,	"#fff");
    bd5Con =divitem(items[4].t1link,	items[4].t1units,	items[4].t1rate,	items[4].t1items,	"#e5e6e5")+
            divitem(items[4].t2link,	items[4].t2units,	items[4].t2rate,	items[4].t2items,	"#e5e6e5")+
            divitem(items[4].t3link,	items[4].t3units,	items[4].t3rate,	items[4].t3items,	"#e5e6e5")+
            divitem(items[4].t4link,	items[4].t4units,	items[4].t4rate,	items[4].t4items,	"#e5e6e5")+
            divitem(items[4].t5link,	items[4].t5units,	items[4].t5rate,	items[4].t5items,	"#e5e6e5")+
            divitem(items[4].t6link,	items[4].t6units,	items[4].t6rate,	items[4].t6items,	"#e5e6e5")+
            divitem(items[4].t7link,	items[4].t7units,	items[4].t7rate,	items[4].t7items,	"#e5e6e5")+
            divitem(items[4].t8link,	items[4].t8units,	items[4].t8rate,	items[4].t8items,	"#e5e6e5")+
            divitem(items[4].t9link,	items[4].t9units,	items[4].t9rate,	items[4].t9items,	"#e5e6e5")+
            divitem(items[4].t10link,	items[4].t10units,	items[4].t10rate,	items[4].t10items,	"#e5e6e5");
    bd6Con =divitem(items[5].t1link,	items[5].t1units,	items[5].t1rate,	items[5].t1items,	"#fff")+
            divitem(items[5].t2link,	items[5].t2units,	items[5].t2rate,	items[5].t2items,	"#fff")+
            divitem(items[5].t3link,	items[5].t3units,	items[5].t3rate,	items[5].t3items,	"#fff")+
            divitem(items[5].t4link,	items[5].t4units,	items[5].t4rate,	items[5].t4items,	"#fff")+
            divitem(items[5].t5link,	items[5].t5units,	items[5].t5rate,	items[5].t5items,	"#fff")+
            divitem(items[5].t6link,	items[5].t6units,	items[5].t6rate,	items[5].t6items,	"#fff")+
            divitem(items[5].t7link,	items[5].t7units,	items[5].t7rate,	items[5].t7items,	"#fff")+
            divitem(items[5].t8link,	items[5].t8units,	items[5].t8rate,	items[5].t8items,	"#fff")+
            divitem(items[5].t9link,	items[5].t9units,	items[5].t9rate,	items[5].t9items,	"#fff")+
            divitem(items[5].t10link,	items[5].t10units,	items[5].t10rate,	items[5].t10items,	"#fff");    


	$("#t1brand").html(bd1Con);
	$("#t2brand").html(bd2Con);
	$("#t3brand").html(bd3Con);
	$("#t4brand").html(bd4Con);
	$("#t5brand").html(bd5Con);
	$("#t6brand").html(bd6Con);



    }
   
