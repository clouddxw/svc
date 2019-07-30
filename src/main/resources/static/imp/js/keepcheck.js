/* 将选中的数据ID保存*/
function remecheck(){
	var checkedIds= [];//保存选中的id，亦适用于分页
	var inputc=$("input[type=checkbox]");
	inputc.each(function(){
	    if($(this).is(":checked")){
	         checkedIds.push($(this).attr("id"));
	     }else{
	         for(var i=0; i<checkedIds.length; i++){
	             if($(this).attr("id") == checkedIds[i]){
	             checkedIds.splice(i, 1);
	             break;
	             }
	         }
	     }
	});

    localStorage.setItem('checkedIds',checkedIds);
 };
$(document).ready(function (){
	var checkedIds=localStorage.getItem('checkedIds')
    var $boxes = $("input[type=checkbox]");
    for(var i=0;i<$boxes.length;i++){
        var id = $boxes[i].id;
        if(checkedIds.indexOf(id,0)!=-1){
            $boxes[i].checked = true;
        }else{
            $boxes[i].checked = false;
        }
    }
    
    localStorage.clear(); 
    
 })