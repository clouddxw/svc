function chgbg(name,backcol,fontcol){
	   document.getElementById(name).style.background=backcol;
	   document.getElementById(name).style.color=fontcol;
	};
	
//多个checkbox选取值聚合

function getval(name,tov) {

  //获取input所在div的对象 

  obj = document.getElementsByName(name);
  check_val = [];
  var str="";
  cnt=0;
  cnt1=0;
  cnt2=0;
  //定义一个变量并初始化为空

  //循环遍历，判断INPUT是否选中
  for (var i=0;i<obj.length;i++) {
      if(obj[i].type=="checkbox" && obj[i].checked) {
      	cnt++;
      	if(cnt==1){
      		str+=obj[i].value;
      	}else{
      		str+=","+obj[i].value;
          	}
          	
          }          	  
} ;
      
if ( str=="" ){
 for (var k=0;k<obj.length;k++) {
	 if(obj[k].type=="checkbox"){
	 cnt1++;
	 str+=","+obj[k].value;
		 }
	 }
	 str=str.slice(1);
};

document.getElementById(tov).value = str;//把选择完后的字符串给一个隐藏空间以便向后台传送 
localStorage.setItem(name, str);

} ;
//画面切换
function chgdisplay(name,stats){
   document.getElementById(name).style.display=stats;
};
//阻止点击事件传播；
$(function(){
	$("body").on('click','[data-stopPropagation]',function (e) {
	        e.stopPropagation();
	    });
	    });
//页面嵌套;

function chgclassdisp(name,stats){
	   var x = document.getElementsByClassName(name);
	   var i;
	   for (i = 0; i < x.length; i++) {
	       x[i].style.display = stats;
	   }
	};
	
function settval(name,val){

	document.getElementById(name).value=val;

		};

function chgval(element,val){
	document.getElementById(element).value = val;	
	};
		
function setftval(fid,tid){
	var val=document.getElementById(fid).value;
	if(val!=null && val.length>0){
	document.getElementById(tid).value=val; 
	}
};

$(function () {
    var datepickerconf = {
        todayHighlight: true,
        language: 'zh-CN',
        autoclose: 1,
        todayBtn: 1,
        pickerPosition: "bottom-left",
        minuteStep: 5,
        format: 'yyyy-mm-dd',
    };
    $('#statdatepickerf').parent().datepicker(datepickerconf);
    $('#enddatepickerf').parent().datepicker(datepickerconf);
    $('#statdatepickern').parent().datepicker(datepickerconf);
    $('#enddatepickern').parent().datepicker(datepickerconf);
    


    
});

$(function() {
    $("#filtersubmit").click(function() {
        $("#filtermenu").dropdown('toggle');
    })
})

$(function() {
    $("#normsubmit").click(function() {
        $("#normmenu").dropdown('toggle');
    })
})

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
	}


function loadporpertycharts(bd,wb,wx,tgi,disp){
	
    var bdtime=[];
    var bdval=[];
    for(var i=0;i<bd.length;i++){
    	bdtime.push(bd[i].time);
    	bdval.push(bd[i].value);
    }

    var wbtime=[];
    var wbval=[];
    for(var i=0;i<wb.length;i++){
    	wbtime.push(wb[i].time);
    	wbval.push(wb[i].value);
    }

    var wxtime=[];
    var wxval=[];
    for(var i=0;i<wx.length;i++){
    	wxtime.push(wx[i].time);
    	wxval.push(wx[i].value);
    }


    try{
        kbindex_chart(bdtime,bdval,wbtime,wbval,wxtime,wxval,disp);
	}catch(TypeError) {
		console.log("口碑指数数据类型错误");
    };

    try{
        userproty_chart(tgi.sexm,tgi.sexw,tgi.citylv1,tgi.citylv2,tgi.citylv3,tgi.agegp1,tgi.agegp2,tgi.agegp3,tgi.agegp4,tgi.agegp5,disp);
	}catch(TypeError) {
    	console.log("观众属性数据类型错误");
	};




	//kbindex_chart(convary(bd,'time'),convary(bd,'value'),convary(wb,'time'),convary(wb,'value'),convary(wx,'time'),convary(wx,'value'),'');
	//kbindex_chart(bdavg.time,bdavg.value,wbavg.time,wbavg.value,wxavg.time,wxavg.value,'avg');


}
function setDate(){
	$('#statdatepickerf').val('2016-01-01');
	$('#statdatepickern').val('2016-01-01');
	var now = new Date();
	//格式化日，如果小于9，前面补0
	var day = ("0" + now.getDate()).slice(-2);
	//格式化月，如果小于9，前面补0
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	//拼装完整日期格式
	var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
	//完成赋值
	$('#enddatepickerf').val(today);
	$('#enddatepickern').val(today);
}

function getoneboxval(name,tov,dispele) {

	  //获取input所在div的对象 

	  obj = document.getElementsByName(name);
	  check_val = [];
	  var str="";
	  cnt=0;
	  cnt1=0;
	  cnt2=0;
	  //定义一个变量并初始化为空

	  //循环遍历，判断INPUT是否选中
	  for (var i=0;i<obj.length;i++) {
	      if(obj[i].type=="checkbox" && obj[i].checked) {               	
	      	cnt++;
	      	if(cnt==1){
	      		str+=obj[i].value;
	      	}else{
	      		str+=","+obj[i].value;
	          	}
	          	
	          }          	  
	} ;
	      
	if ( str=="" ){
		document.getElementById(dispele).style.display="inline-block";
		
	}else{
		document.getElementById(dispele).style.display="none";
	};
	document.getElementById(tov).value = str;//把选择完后的字符串给一个隐藏空间以便向后台传送 

	} ;
	
function clicktrans(tid)
{
$('#'+tid).click();//调用按钮2
}

function deepClone(obj){
    var _obj = JSON.stringify(obj);
    objClone = JSON.parse(_obj);
    return objClone
}

