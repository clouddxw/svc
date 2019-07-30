function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
	}

var bgdate =getQueryString("bgdate");
var enddate =getQueryString("enddate")+'01';
var brandtype =getQueryString("brandtype");
//urlori="";

urlori='vf?type='+bgdate+'&date='+enddate+'&brandtype='+brandtype+'&method=getTopItemDemo';


//if (bgdate=='current'){
//	urlori='vf?bgdate='+enddate+'&enddate='+enddate+'&brandtype='+brandtype+'&method=getTopItem';
//}else if(bgdate=='ytd'){
//	bgdate1=enddate.substring(0,4)+'0101';
//	urlori='vf?bgdate='+bgdate1+'&enddate='+enddate+'&brandtype='+brandtype+'&method=getTopItem';	
//}else if(bgdate==null || bgdate.length==0){
//    alert("输入参数有误！")
//}else{
//	bgdate1=bgdate+'01';
//	urlori='vf?bgdate='+bgdate1+'&enddate='+enddate+'&brandtype='+brandtype+'&method=getTopItem';	
//}



//function ajax(url,data,callback){//如果有不同的url 可以通过注释的部分传不同的url
$.ajax({
    url: urlori,
    type: 'get',
    dataType: 'json',
    //data:data,
    jsonp: 'callback',
    async: false,
    success: function (data) {
        //callback(r);
    	if(data.items.length>0){
            dataList(data);
    	}

    },
    error: function () {
        var errorE = '抱歉，没有找到符合您要求的数据!';
        alert(errorE);

    }
});

//function divitem(items,backcolor){
//	Con='';
//	var itema=new Array();
//	itema
//	for(var i=0;i<items.length;i++){
//		if(items[i].imgurl.length==0){
//			Con+='<li style="background:'+backcolor+'">'+
//				    '<div class="imag" style="background:#fff;"></div>'+
//			     '</li>';	
//		}else{
//			Con+='<li style="background:'+backcolor+'">'+
//					'<div class="imag"><img src="img/'+items[i].imgurl+'"></div>'+
//				  '</li>';
//		}
//
//	}
//
//	return Con;
//}

function divitem(link,backcolor){


    Con='<li style="background:'+backcolor+'">'+
		   '<div class="imag"><img src="img/'+link+'"></div>'+
		'</li>';
	return Con;
}

function dataList(data){
	var items=data.items;
//    var bd1=new Array();
//    var bd2=new Array();
//    var bd3=new Array();
//    var bd4=new Array();
//    var bd5=new Array();
	bd1Con=divitem(items[0].t1link,"#e5e6e5")+divitem(items[0].t2link,"#e5e6e5")+divitem(items[0].t3link,"#e5e6e5")+divitem(items[0].t4link,"#e5e6e5")+divitem(items[0].t5link,"#e5e6e5");
	bd2Con=divitem(items[1].t1link,"#fff")+divitem(items[1].t2link,"#fff")+divitem(items[1].t3link,"#fff")+divitem(items[1].t4link,"#fff")+divitem(items[1].t5link,"#fff");
	bd3Con=divitem(items[2].t1link,"#e5e6e5")+divitem(items[2].t2link,"#e5e6e5")+divitem(items[2].t3link,"#e5e6e5")+divitem(items[2].t4link,"#e5e6e5")+divitem(items[2].t5link,"#e5e6e5");
	bd4Con=divitem(items[3].t1link,"#fff")+divitem(items[3].t2link,"#fff")+divitem(items[3].t3link,"#fff")+divitem(items[3].t4link,"#fff")+divitem(items[3].t5link,"#fff");
	
	$("#t1brand").html(bd1Con);
	$("#t2brand").html(bd2Con);
	$("#t3brand").html(bd3Con);
	$("#t4brand").html(bd4Con);

    

//    if(items[0].brandnum==2){
//    	for(var i=0;i<items.length;i++){
//    		if(items[i].brandrn==1){
//    			 bd1.push(items[i]);
//    			}else if(items[i].brandrn==2){
//    			 bd2.push(items[i]);
//    			}
//
//    		}
//
//    	var bd1Con="";
//    	var bd2Con="";
//    	bd1Con+=divitem(bd1,"#e5e6e5");
//    	bd2Con+=divitem(bd2,"#fff");
//    	$("#t1brand").html(bd1Con);
//    	$("#t2brand").html(bd2Con);
//
//    	}else if(items[0].brandnum==3){
//        	for(var i=0;i<items.length;i++){
//        		if(items[i].brandrn==1){
//        			bd1.push(items[i]);
//        			}else if(items[i].brandrn==2){
//        			 bd2.push(items[i]);
//        			}else if(items[i].brandrn==3){
//        			 bd3.push(items[i]);
//        			}
//        		}
//
//        	var bd1Con="";
//        	var bd2Con="";
//        	var bd3Con="";
//        	bd1Con+=divitem(bd1,"#e5e6e5");
//        	bd2Con+=divitem(bd2,"#fff");
//        	bd3Con+=divitem(bd3,"#e5e6e5");
//        	$("#t1brand").html(bd1Con);
//        	$("#t2brand").html(bd2Con);
//        	$("#t3brand").html(bd3Con);
//        }else if(items[0].brandnum==4){
//        	for(var i=0;i<items.length;i++){
//        		if(items[i].brandrn==1){
//        			bd1.push(items[i]);
//        			}else if(items[i].brandrn==2){
//        			 bd2.push(items[i]);
//        			}else if(items[i].brandrn==3){
//        			 bd3.push(items[i]);
//        			}else if(items[i].brandrn==4){
//        			 bd4.push(items[i]);
//        			}
//        		}
//        	var bd1Con="";
//        	var bd2Con="";
//        	var bd3Con="";
//        	var bd4Con="";
//        	bd1Con+=divitem(bd1,"#e5e6e5");
//        	bd2Con+=divitem(bd2,"#fff");
//        	bd3Con+=divitem(bd3,"#e5e6e5");
//        	bd4Con+=divitem(bd4,"#fff");
//        	$("#t1brand").html(bd1Con);
//        	$("#t2brand").html(bd2Con);
//        	$("#t3brand").html(bd3Con);
//        	$("#t4brand").html(bd4Con);
//
//        }else if(items[0].brandnum==5){
//    	for(var i=0;i<items.length;i++){
//    		if(items[i].brandrn==1){
//    			bd1.push(items[i]);
//    			}else if(items[i].brandrn==2){
//    			 bd2.push(items[i]);
//    			}else if(items[i].brandrn==3){
//    			 bd3.push(items[i]);
//    			}else if(items[i].brandrn==4){
//    			 bd4.push(items[i]);
//    			}else if(items[i].brandrn==5){
//    			 bd5.push(items[i]);
//    			}
//    		}
//    	var bd1Con="";
//    	var bd2Con="";
//    	var bd3Con="";
//    	var bd4Con="";
//    	var bd5Con="";
//    	bd1Con+=divitem(bd1,"#e5e6e5");
//    	bd2Con+=divitem(bd2,"#fff");
//    	bd3Con+=divitem(bd3,"#e5e6e5");
//    	bd4Con+=divitem(bd4,"#fff");
//    	bd5Con+=divitem(bd5,"#e5e6e5");
//       	$("#t1brand").html(bd1Con);
//    	$("#t2brand").html(bd2Con);
//    	$("#t3brand").html(bd3Con);
//    	$("#t4brand").html(bd4Con);
//    	$("#t5brand").html(bd5Con);
//
//    	}




    }
   
