function divfilnorm(){
	
var filtercon='<li><div class="norm_tag" >时间范围</div>'+
'	             <div class="input-group date" data-provide="statdatepickerf" style="float:left;max-width:220px;">'+	
'                   <input type="text" name="statdate" id="statdatepickerf" class="form-control" style="width:120px;height:30px;">'+
'                    <div class="input-group-addon" style="width:30px;height:30px;">'+
'                    <span class="glyphicon glyphicon-th"></span>'+
'                   </div>'+
'                </div>'+
'                <div style="float:left;line-height:30px;margin:0px 5px;">-</div>'+
'	             <div class="input-group date" data-provide="enddatepickerf" style="float:left;max-width:220px;">'+
'                   <input type="text" name="enddate" id="enddatepickerf" class="form-control" style="width:120px;height:30px;">'+
'                    <div class="input-group-addon" style="width:30px;height:30px;">'+
'                  <span class="glyphicon glyphicon-th"></span>'+
'                   </div>'+
'                </div>'+
'               </li>'+
'       <li><div class="norm_tag">播放平台</div>'+
'                    <label><input name="playplatf" type="checkbox" value="网络平台" id="playplatif1">网络平台</label>'+
'                    <label><input name="playplatf" type="checkbox" value="电视平台" id="playplatif2">电视平台</label>'+
'               </li>'+
'               <li><div class="norm_tag">节目类型</div>'+
'                    <label><input name="showtypef" type="checkbox" value="谈话/脱口秀类" id="showtypeif1">谈话/脱口秀类</label>'+
'                    <label><input name="showtypef" type="checkbox" value="生活观察类" id="showtypeif2">生活观察类</label>'+
'                    <label><input name="showtypef" type="checkbox" value="综合游戏类" id="showtypeif3">综合游戏类</label>'+
'                    <label><input name="showtypef" type="checkbox" value="才艺竞演类" id="showtypeif4">才艺竞演类</label>'+
'                    <label><input name="showtypef" type="checkbox" value="文化类" id="showtypeif5">文化类</label>'+
'                    <label><input name="showtypef" type="checkbox" value="美食类" id="showtypeif6">美食类</label>'+
'                    <label><input name="showtypef" type="checkbox" value="亲子/儿童互动" id="showtypeif7">亲子/儿童互动</label>'+
'                    <label><input name="showtypef" type="checkbox" value="明星竞演类" id="showtypeif8">明星竞演类</label>'+
'                    <label><input name="showtypef" type="checkbox" value="喜剧类" id="showtypeif9">喜剧类</label>'+
    '                    <label><input name="showtypef" type="checkbox" value="创意类" id="showtypeif10">创意类</label>'+
    '                    <label><input name="showtypef" type="checkbox" value="其他" id="showtypeif11">其他</label>'+
'               </li>'+
'               <li><div class="norm_tag">节目级别</div>'+
'                    <label><input name="showlevelf" type="checkbox" value="S+级" id="showlevelif1">S+级</label>'+
'                    <label><input name="showlevelf" type="checkbox" value="S级" id="showlevelif2">S级</label>'+
'                    <label><input name="showlevelf" type="checkbox" value="A级" id="showlevelif3">A级</label>'+
'                    <label><input name="showlevelf" type="checkbox" value="B级" id="showlevelif4">B级</label>'+
'                    <label><input name="showlevelf" type="checkbox" value="C级" id="showlevelif5">C级</label>'+
'                    '+
'               </li>'+
'               <li><div class="norm_tag">赞助权益</div>'+
'                    <label><input name="spontypef" type="checkbox" value="总冠名" id="spontypeif1">总冠名</label>'+
'                    <label><input name="spontypef" type="checkbox" value="其他" id="spontypeif2">其他</label>    '+
'               </li>'+
'               <li><div class="norm_tag">品牌类型</div>'+
'                    <label><input name="brandtypef" type="checkbox" value="快速消费品" id="brandtypeif1">快速消费品</label>'+
'                    <label><input name="brandtypef" type="checkbox" value="互联网应用" id="brandtypeif2">互联网应用</label>'+
'                    <label><input name="brandtypef" type="checkbox" value="耐用消费品" id="brandtypeif3">耐用消费品</label>'+
'                    <label><input name="brandtypef" type="checkbox" value="汽车" id="brandtypeif4">汽车</label>'+
'                    <label><input name="brandtypef" type="checkbox" value="手机" id="brandtypeif5">手机</label>'+
'                    <label><input name="brandtypef" type="checkbox" value="其他" id="brandtypeif6">其他</label>'+
'               </li>'+
'               <li class="divider" style="height:2px;margin:0px;"></li>'+
'               <li   style="margin:10px 0px;">'+
'                   <div style="height:30px;width:60px;margin:0 auto;">'+
'		               <input type="text" name="playplat" id="playplatvf" value=""   class="undisp"  >'+
'		               <input type="text" name="showtype" id="showtypevf" value=""   class="undisp" >'+
'		               <input type="text" name="showlevel" id="showlevelvf" value="" class="undisp" >'+
'		               <input type="text" name="spontype"  id="spontypevf" value=""  class="undisp" >'+
'		               <input type="text" name="brandtype" id="brandtypevf" value="" class="undisp" >'+
'		               <input type="text" name="bgdate" id="bgdatevf" value="1990-01-01" class="undisp" >'+
'		               <input type="text" name="enddatef" id="enddatevf" value="2050-01-01" class="undisp" >'+
'		               <input type="submit" name="Submit" value="提交" id="filtersubmit"'+
'		               onclick="remecheck();getval(\'playplatf\',\'playplatvf\');getval(\'showtypef\',\'showtypevf\');getval(\'showlevelf\',\'showlevelvf\');getval(\'spontypef\',\'spontypevf\');getval(\'brandtypef\',\'brandtypevf\');setftval(\'statdatepickern\',\'bgdatevn\');'+
'		               setftval(\'enddatepickern\',\'enddatevn\');"'+
'		               style="height:30px;width:60px;line-height:30px;text-align:center">'+
'                   </div>'
'               </li>';

var normcon='<li><div class="norm_tag" >时间范围</div>'+
'	             <div class="input-group date" data-provide="statdatepickern" style="float:left;max-width:220px;">'+	
'                   <input type="text" name="statdate" id="statdatepickern" class="form-control" style="width:120px;height:30px;">'+
'                    <div class="input-group-addon" style="width:30px;height:30px;">'+
'                    <span class="glyphicon glyphicon-th"></span>'+
'                   </div>'+
'                </div>'+
'                <div style="float:left;line-height:30px;margin:0px 5px;">-</div>'+
'	             <div class="input-group date" data-provide="enddatepickern" style="float:left;max-width:220px;">'+
'                   <input type="text" name="enddate" id="enddatepickern" class="form-control" style="width:120px;height:30px;">'+
'                    <div class="input-group-addon" style="width:30px;height:30px;">'+
'                  <span class="glyphicon glyphicon-th"></span>'+
'                   </div>'+
'                </div>'+
'               </li>'+
'               <li><div class="norm_tag">播放平台</div>'+
'                    <label><input name="playplatn" type="checkbox" value="网络平台" id="playplatin1">网络平台</label>'+
'                    <label><input name="playplatn" type="checkbox" value="电视平台" id="playplatin2">电视平台</label>'+
'               </li>'+
'               <li><div class="norm_tag">节目类型</div>'+
'                    <label><input name="showtypen" type="checkbox" value="谈话/脱口秀类" id="showtypein1">谈话/脱口秀类</label>'+
'                    <label><input name="showtypen" type="checkbox" value="生活观察类" id="showtypein2">生活观察类</label>'+
'                    <label><input name="showtypen" type="checkbox" value="综合游戏类" id="showtypein3">综合游戏类</label>'+
'                    <label><input name="showtypen" type="checkbox" value="才艺竞演类" id="showtypein4">才艺竞演类</label>'+
'                    <label><input name="showtypen" type="checkbox" value="文化类" id="showtypein5">文化类</label>'+
'                    <label><input name="showtypen" type="checkbox" value="美食类" id="showtypein6">美食类</label>'+
'                    <label><input name="showtypen" type="checkbox" value="亲子/儿童互动" id="showtypein7">亲子/儿童互动</label>'+
'                    <label><input name="showtypen" type="checkbox" value="明星竞演类" id="showtypein8">明星竞演类</label>'+
'                    <label><input name="showtypen" type="checkbox" value="喜剧类" id="showtypein9">喜剧类</label>'+
    '                    <label><input name="showtypen" type="checkbox" value="创意类" id="showtypein10">创意类</label>'+
    '                    <label><input name="showtypen" type="checkbox" value="其他" id="showtypein11">其他</label>'+
'               </li>'+
'               <li><div class="norm_tag">节目级别</div>'+
'                    <label><input name="showleveln" type="checkbox" value="S+级" id="showlevelin1">S+级</label>'+
'                    <label><input name="showleveln" type="checkbox" value="S级" id="showlevelin2">S级</label>'+
'                    <label><input name="showleveln" type="checkbox" value="A级" id="showlevelin3">A级</label>'+
'                    <label><input name="showleveln" type="checkbox" value="B级" id="showlevelin4">B级</label>'+
'                    <label><input name="showleveln" type="checkbox" value="C级" id="showlevelin5">C级</label>'+
'                    '+
'               </li>'+
'               <li><div class="norm_tag">赞助权益</div>'+
'                    <label><input name="spontypen" type="checkbox" value="总冠名" id="spontypein1">总冠名</label>'+
'                    <label><input name="spontypen" type="checkbox" value="其他" id="spontypein2">其他</label>    '+
'               </li>'+
'               <li><div class="norm_tag">品牌类型</div>'+
'                    <label><input name="brandtypen" type="checkbox" value="快速消费品" id="brandtypein1">快速消费品</label>'+
'                    <label><input name="brandtypen" type="checkbox" value="互联网应用" id="brandtypein2">互联网应用</label>'+
'                    <label><input name="brandtypen" type="checkbox" value="耐用消费品" id="brandtypein3">耐用消费品</label>'+
'                    <label><input name="brandtypen" type="checkbox" value="汽车" id="brandtypein4">汽车</label>'+
'                    <label><input name="brandtypen" type="checkbox" value="手机" id="brandtypein5">手机</label>'+
'                    <label><input name="brandtypen" type="checkbox" value="其他" id="brandtypein6">其他</label>'+
'               </li>'+
'               <li><div class="norm_tag">用户性别</div>'+
'                    <label><input name="sexn" type="checkbox" value="男" id="sexin1">男</label>'+
'                    <label><input name="sexn" type="checkbox" value="女" id="sexin2">女</label>                  '+
'               </li>'+
'               <li><div class="norm_tag">用户年龄</div>'+
'                    <label><input name="agegroupn" type="checkbox" value="18岁以下" id="agegroupin1">18岁以下</label>'+
'                    <label><input name="agegroupn" type="checkbox" value="18-24岁" id="agegroupin2">18-24岁</label>'+
'                    <label><input name="agegroupn" type="checkbox" value="25-34岁" id="agegroupin3">25-34岁</label>'+
'                    <label><input name="agegroupn" type="checkbox" value="35-50岁" id="agegroupin4">35-50岁</label>'+
'                    <label><input name="agegroupn" type="checkbox" value="50岁以上" id="agegroupin5">50岁以上</label>'+
'               </li>'+
'               <li><div class="norm_tag">城市等级</div>'+
'                    <label><input name="cityleveln" type="checkbox" value="一线城市" id="citylevelin1">一线城市</label>'+
'                    <label><input name="cityleveln" type="checkbox" value="二线城市" id="citylevelin2">二线城市</label>'+
'                    <label><input name="cityleveln" type="checkbox" value="三线城市及以下" id="citylevelin3">三线城市及以下</label>'+
'               </li>'+
'               <li class="divider" style="height:2px;margin:0px;"></li>'+
'               <li   style="margin:10px 0px;">'+
'               	<form id="formn"  action="##" method="post" onsubmit="return false" style="height:30px;width:60px;margin:0px auto;">'+
'		               <input type="text" name="playplat" id="playplatvn" value="" class="undisp" >'+
'		               <input type="text" name="showtype" id="showtypevn" value=""  class="undisp" >'+
'		               <input type="text" name="showlevel" id="showlevelvn" value="" class="undisp" >'+
'		               <input type="text" name="spontype"  id="spontypevn" value=""  class="undisp" >'+
'		               <input type="text" name="brandtype" id="brandtypevn" value="" class="undisp" >'+
'		               <input type="text" name="sex" id="sexvn" value="" class="undisp" >'+
'		               <input type="text" name="agegroup"  id="agegroupvn" value="" class="undisp" >'+
'		               <input type="text" name="citylevel" id="citylevelvn" value="" class="undisp" >'+
'		               <input type="text" name="bgdate" id="bgdatevn" value="1990-01-01" class="undisp" >'+
'		               <input type="text" name="enddaten" id="enddatevn" value="2050-01-01" class="undisp" >'+
'		               <input type="text" name="showname" id="shownamevn" value="" class="undisp"  >'+
'		               <input type="text" name="brandname" id="brandnamevn" value="" class="undisp"  >'+
'		               <input type="submit" name="Submit" id="normsubmit" value="提交"'+
'		               onclick="remecheck();getval(\'playplatn\',\'playplatvn\');getval(\'showtypen\',\'showtypevn\');getval(\'showleveln\',\'showlevelvn\');getval(\'spontypen\',\'spontypevn\');'+
'		               getval(\'brandtypen\',\'brandtypevn\');getval(\'sexn\',\'sexvn\');getval(\'agegroupn\',\'agegroupvn\');getval(\'cityleveln\',\'citylevelvn\');setftval(\'statdatepickern\',\'bgdatevn\');'+
'		               setftval(\'enddatepickern\',\'enddatevn\');getnorm();"'+
'		               style="height:30px;width:60px;line-height:30px;text-align:center;">'+
'	               </form>'+
'               </li>';

return [filtercon,normcon];
}