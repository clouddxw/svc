

function setBarWidth(dataElement, barElement, cssProperty, barPercent) {
  var listData = []
  $(dataElement).each(function () {
    listData.push($(this).html());
  });
  var listMax = Math.max.apply(Math, listData);
  $(barElement).each(function (index) {
    var width = (listData[index] / listMax) * barPercent + '%';
    $(this).css(cssProperty, width);
  });
};
function getzone(id,name,namev) {

    //获取input所在div的对象  
    var ob = document.getElementById(id);

    //获取div中所用的IUPUT控件集合
    var col = ob.getElementsByTagName(name);
    //定义一个变量并初始化为空
    var str = ",,";
    var count = 0;

    //循环遍历，判断INPUT是否选中
    for (var i = 0 ; i < col.length; i++) {  
        if (col[i].checked == true) {
        count++;
    if (count == 1) {//当是一个值得时候，直接把选中的值赋给字符串  
        str += col[i].value;
    }
            else {
        str += "," + col[i].value;//多个被选中的时候，需要把选定的值不断的拼接  
            }  
    //str+=col[i].value+"/";  
}


}  ;
   if (str.equals("")){
	   for (var i=0 ;i<col.length;i++){
		   if(i=0){
			   str=col[i].value;
		   }else{
			   str += "," + col[i].value;   
		   }
	   }

   }
document.getElementById(namev).value = str;//把选择完后的字符串给一个隐藏空间以便向后台传送  

}  ;

