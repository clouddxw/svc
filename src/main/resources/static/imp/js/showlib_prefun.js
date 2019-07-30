
function getycpara(){
	var jzparaval=$("#jzpara").val();
	var starparaval=$('#starpara').val();
	if(jzparaval==""){
		document.getElementById("jzpararm").style.display="inline-block";	
	}else{
		document.getElementById("jzpararm").style.display="none";
	}
	
	if(starparaval==""){
		document.getElementById("starpararm").style.display="inline-block";	
	}else{
		document.getElementById("starpararm").style.display="none";
	}
	
	getoneboxval('procosty','procostvy','procostrm');
	getoneboxval('playplaty','playplatvy','playplatrm');
	getoneboxval('propcosty','propcostvy','propcostrm');
	getoneboxval('showtypey','showtypevy','showtyperm');
	getoneboxval('playtimey','playtimevy','playtimerm');
	getoneboxval('showisny','showisnvy','showisnrm');
	getoneboxval('showisdby','showisdbvy','showisdbrm');
	getoneboxval('playtypey','playtypevy','playtyperm');
};
function  datacheck(tid)
{
  var jzparaval=$("#jzpara").val();
  var procostval=$("#procostvy").val();
  var playplatval=$('#playplatvy').val();
  var propcostval=$('#propcostvy').val();
  var showtypeval=$('#showtypevy').val();
  var playtimeval=$('#playtimevy').val();
  var showisnval=$('#showisnvy').val();
  var showisdbval=$('#showisdbvy').val();
  var playtypeval=$('#playtypevy').val();
  var starparaval=$('#starpara').val();
  
 if(jzparaval!="" && procostval!="" && playplatval!="" && propcostval!="" 
		 && showtypeval!="" && playtimeval!="" && showisnval!="" && showisdbval!="" && playtypeval!="" && starparaval!="" ){
	 clicktrans("test");
	 yccon=playtypeval+"-"+showtypeval+"-"+playplatval+"-"+procostval+"-"+propcostval+"-"+starparaval+"-"+playtimeval+"-"+showisnval+"-"+showisdbval+"-"+jzparaval;
     $.ajax({
		    url: 'http://svc.iresearch.cn/showlib/getRF',
		    type: 'get',
		    dataType: 'json',
		    data:{
		    	yccon:yccon
			},
		    jsonp: 'callback',
		    async: true,
		    success: function (data) {
		        //callback(r);
		        dataList1(data)
		    },
		    // error: function () {
		    //     var errorE = '抱歉，没有找到符合您要求的数据!';
		    //     alert(errorE);
		    // }
		});
	 
 }else{
	 alert("请填写必填选项！");
 }

  
  
  
  
}