// JavaScript Document
var selebl = {
	loadselect:function(id,objJson,pars,resultMethod){
		var text="";
		var div="";
		var lable="";
		if(pars){
			if(!pars.txtWidth)pars.txtWidth=200;
			if(!pars.txtHeight)pars.txtHeight=20;
			if(!pars.selectWidth)pars.selectWidth=200;
			if(!pars.selectHeight)pars.selectHeight=150;
			if(!pars.bordercolor)pars.bordercolor="#C00";
			if(!pars.textsize)pars.textsize="12px";
			if(!pars.bordersize)pars.bordersize="1px";
			if(!pars.scros)pars.scros="scroll";
		}
		var sele = new loadpars(objJson,id,"","",-1,pars);
		text="<input type='text' id='"+sele.textid+"' style='border:"+sele.parameter.bordersize+"px solid;font-size:"+sele.parameter.textsize+"px;border-color:"+sele.parameter.bordercolor+";width:"+sele.parameter.txtWidth+"px;height:"+sele.parameter.txtHeight+"px;' value='请选择' class='textft'>"
		
		lable = "<lable id='"+sele.lableid+"' for='"+sele.textid+"'></lable>";
		
		div="<div id='"+sele.optionsid+"' scros='"+sele.parameter.scros+"' style='display:none;overflow-y:scroll;width:"
		+sele.parameter.selectWidth+"px;height:"+sele.parameter.selectHeight+"px;position:absolute;background-color:#CCC;'>"+lable+"</div>";
		
		document.getElementById(sele.thisid).innerHTML=text+div;
		
		q("#"+sele.textid).change(function(){
			if(q("#"+sele.textid).val().trim()=="" || q("#"+sele.textid).val().trim()=="请选择" ){
				q("#"+sele.textid).val("请选择");
				if(q("#"+sele.textid).attr("class")!="textft")
					q("#"+sele.textid).addClass("textft");
			}else{
				if(q("#"+sele.textid).attr("class")=="textft")
					q("#"+sele.textid).removeClass("textft");
			}
		});
		
		q("#"+sele.textid).click(function(){
			if(q("#"+sele.textid).val().trim()=="请选择" ){
				q("#"+sele.textid).val("");
			}
			q("#"+sele.lableid).empty();
			for(var js in sele.Jsons){
				var newdiv = document.createElement('div');
				newdiv.innerHTML=sele.Jsons[js].name;
				newdiv.className="divmoveroutcla";
				newdiv.setAttribute("objid",sele.Jsons[js].id);
				document.getElementById(sele.lableid).appendChild(newdiv);
			}
			q("#"+sele.optionsid).slideDown("show",function(){
				q("#"+sele.optionsid+" div").click(function(){
					if(q("#"+sele.textid).attr("class")=="textft"){
					q("#"+sele.textid).removeClass("textft");}
					q("#"+sele.textid).val(q(this).text());
					sele.selectedValue = q(this).attr("objid");
					q("#"+sele.optionsid).hide(500,function(){
						if(q("#"+sele.textid).val().trim()=="" || q("#"+sele.textid).val().trim()=="请选择" ){
							q("#"+sele.textid).val("请选择");
							if(q("#"+sele.textid).attr("class")!="textft")
								q("#"+sele.textid).addClass("textft");
						}
					});
					resultMethod(q(this).attr("objid"),q(this).text());
				});
			});
		});
		/*q("#"+sele.textid).focus(function(){
			if(q("#"+sele.textid).val().trim()=="请选择" ){
				q("#"+sele.textid).val("");
			}
			q("#"+sele.lableid).empty();
			for(var js in sele.Jsons){
				var newdiv = document.createElement('div');
				newdiv.innerHTML=sele.Jsons[js].name;
				newdiv.className="divmoveroutcla";
				newdiv.setAttribute("objid",sele.Jsons[js].id);
				document.getElementById(sele.lableid).appendChild(newdiv);
			}
			q("#"+sele.optionsid).slideDown("show",function(){
				q("#"+sele.optionsid+" div").click(function(){
					if(q("#"+sele.textid).attr("class")=="textft"){
					q("#"+sele.textid).removeClass("textft");}
					q("#"+sele.textid).val(q(this).text());
					sele.selectedValue = q(this).attr("objid");
					q("#"+sele.optionsid).hide(500,function(){
						if(q("#"+sele.textid).val().trim()=="" || q("#"+sele.textid).val().trim()=="请选择" ){
							q("#"+sele.textid).val("请选择");
							if(q("#"+sele.textid).attr("class")!="textft")
								q("#"+sele.textid).addClass("textft");
						}
					});
					resultMethod(q(this).attr("objid"),q(this).text());
				});
			});
		});*/
		q("#"+sele.textid).focusout(function(){
			if(document.activeElement.id==sele.optionsid){
				q("#"+sele.textid).focus();
			}else{
				q("#"+sele.optionsid).hide(500,function(){
						if(q("#"+sele.textid).val().trim()=="" || q("#"+sele.textid).val().trim()=="请选择" ){
							q("#"+sele.textid).val("请选择");
							if(q("#"+sele.textid).attr("class")!="textft")
								q("#"+sele.textid).addClass("textft");
						}
				});
			}
		});
		
		
		var findoption = function(){
			var rr=q("#"+sele.textid).val().trim();
			if(rr!=""){
				q("#"+sele.lableid).empty();
				var reg = new RegExp("^"+rr);
				for(var js in sele.Jsons){
					if(sele.Jsons[js].name.indexOf(rr)>0 || reg.test(sele.Jsons[js].name))
					{
						var newdiv = document.createElement('div');
						newdiv.innerHTML=sele.Jsons[js].name;
						newdiv.className="divmoveroutcla";
						newdiv.setAttribute("objid",sele.Jsons[js].id);
						document.getElementById(sele.lableid).appendChild(newdiv);
					}
				}
				q("#"+sele.optionsid+" div").eq(0).attr("class","divcla");
			}else{
				q("#"+sele.lableid).empty();
				for(var js in sele.Jsons){
					var newdiv = document.createElement('div');
					newdiv.innerHTML=sele.Jsons[js].name;
					newdiv.className="divmoveroutcla";
					newdiv.setAttribute("objid",sele.Jsons[js].id);
					document.getElementById(sele.lableid).appendChild(newdiv);
				}
			}
			q("#"+sele.optionsid).slideDown("show",function(){
				q("#"+sele.optionsid+" div").click(function(){
					if(q("#"+sele.textid).attr("class")=="textft"){
					q("#"+sele.textid).removeClass("textft");}
					q("#"+sele.textid).val(q(this).text());
					sele.selectedValue = q(this).attr("objid");
					q("#"+sele.optionsid).hide(500,function(){
						if(q("#"+sele.textid).val().trim()=="" || q("#"+sele.textid).val().trim()=="请选择" ){
							q("#"+sele.textid).val("请选择");
							if(q("#"+sele.textid).attr("class")!="textft")
								q("#"+sele.textid).addClass("textft");
						}
					});
					resultMethod(q(this).attr("objid"),q(this).text());
				});
			});
		}
		
		
		
		q('#'+sele.textid).keyup(function(e){
			var ctrlbol = window.event ? e.ctrlKey : e.which;
			var keynum = window.event ? e.keyCode : e.which;
			if(keynum==40){
				if(q("#"+sele.optionsid+" div[class=divcla] + div").length>0){
					q("#"+sele.optionsid+" div[class=divcla] + div").attr("class","divcla");
					q("#"+sele.optionsid+" div[class=divcla]").eq(0).attr("class","divmoveroutcla");
					
					sele.optioncolor=scrolltop;
					if(q("#"+sele.optionsid+" div[class=divcla]").position().top > q("#"+sele.optionsid).height()-q("#"+sele.optionsid+" div[class=divcla]").height()){
						var scrolltop = q("#"+sele.optionsid).scrollTop()+q("#"+sele.optionsid+" div").height()+30;
						q("#"+sele.optionsid).scrollTop(scrolltop);
					};
				}
				else{
					q("#"+sele.optionsid+" div").eq(0).attr("class","divcla");
					q("#"+sele.optionsid+" div:last-child").attr("class","divmoveroutcla");
					q("#"+sele.optionsid).scrollTop(0);
					sele.optioncolor=0;
				}
			}
			else if(keynum==38){
				if(q("#"+sele.optionsid+" div[class=divcla]").prev().length>0){
					q("#"+sele.optionsid+" div[class=divcla]").prev().attr("class","divcla");
					q("#"+sele.optionsid+" div[class=divcla]").eq(1).attr("class","divmoveroutcla");
					var scrolltop = q("#"+sele.optionsid).scrollTop()-q("#"+sele.optionsid+" div").height();
					q("#"+sele.optionsid).scrollTop(scrolltop);
					sele.optioncolor=scrolltop;
				}
			}
			else if(keynum==13){
				if(q("#"+sele.textid).attr("class")=="textft"){
					q("#"+sele.textid).removeClass("textft");}
				q("#"+sele.textid).val(q("#"+sele.optionsid+" div[class=divcla]").text());
				sele.selectedValue = q("#"+sele.optionsid+" div[class=divcla]").attr("objid");
				q("#"+sele.optionsid).hide(500,function(){
						if(q("#"+sele.textid).val().trim()=="" || q("#"+sele.textid).val().trim()=="请选择" ){
							q("#"+sele.textid).val("请选择");
							if(q("#"+sele.textid).attr("class")!="textft")
								q("#"+sele.textid).addClass("textft");
						}
				});
				resultMethod(q("#"+sele.optionsid+" div[class=divcla]").attr("objid"),q("#"+sele.optionsid+" div[class=divcla]").text());
			}
			else if(ctrlbol==true && keynum==86){
				findoption();
			}
			else{
				findoption();
			}
		});
	},
	
}

String.prototype.trim=function(str) {
	return   this.replace(/(^\s*)|(\s*$)/g,""); 
}

function loadpars(jsons,this_id,option_color,er_ro,selected_Value,para){
	this.Jsons=jsons;
	this.thisid=this_id;
	this.textid=this_id+"_text";
	this.lableid=this_id+"_lable";
	this.optionsid=this_id+"_option";
	this.optioncolor=option_color;
	this.erro=er_ro;
	this.selectedValue=selected_Value;
	this.parameter=para;
}