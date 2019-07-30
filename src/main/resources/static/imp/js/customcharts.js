function sshowcharts(sshow){
	var svcindex=[];
    var serialno=[];
    var relindex=[];
    var valindex=[];
    var proindex=[];
    var watch=[];
    var rewatch=[];
    var remember=[];
    var relate=[];
    var rzup=[];
    var xaup=[];
    var tjup=[];
    var watchindex=[];
    var rwindex=[];
    var rmindex=[];
    var relindex=[];
    var rzindex=[];
    var xaindex=[];
    var tjindex=[];
    var kbindex=[];
    var lgr1=['重复收视度'];
    var lgr2=['回忆度','相关度'];
    var lgr3=['认知提升','喜爱提升','推荐提升'];
    var lgr4=['收视热度','重复收视度','口碑传播'];

    try{
        for(var i=0;i<sshow.length;i++){
            svcindex.push(sshow[i].svc);
            serialno.push(sshow[i].serialno);
            relindex.push(sshow[i].showbdrel);
            valindex.push(sshow[i].showval);
            proindex.push(sshow[i].brandpro);
            watch.push(sshow[i].wt);
            rewatch.push(sshow[i].rw);
            remember.push(sshow[i].rm);
            relate.push(sshow[i].rel);
            rzup.push(sshow[i].rzup);
            xaup.push(sshow[i].xaup);
            tjup.push(sshow[i].tjup);

            watchindex.push(sshow[i].wtindex);
            rwindex.push(sshow[i].rwindex);
            kbindex.push(sshow[i].kbindex);
            rmindex.push(sshow[i].rmindex);
            relindex.push(sshow[i].relindex);
            rzindex.push(sshow[i].rzupindex);
            xaindex.push(sshow[i].xaupindex);
            tjindex.push(sshow[i].tjupindex);
        }

    }catch(TypeError) {
        console.log("定制化图表数据类型错误");
    }



    qsx_chart('dtsvcchart',serialno,svcindex,'SVC指数');
    qsm_chart('dtrelchart',serialno,relindex,'节目品牌关联指数');
    qsm_chart('dtvalchart',serialno,valindex,'节目价值指数');
    qsm_chart('dtprochart',serialno,proindex,'品牌资产提升指数');
    zxt1_chart('sivalchartori',lgr1,'重复收视度',serialno,rewatch,'ori1',true,true);
    zxt2_chart('sirelchartori',lgr2,'回忆度','相关度',serialno,remember,relate,'ori2',true,true);
    zxt3_chart('siprochartori',lgr3,'认知提升','喜爱提升','推荐提升',serialno,rzup,xaup,tjup,'ori3',true,true);
    zxt3v_chart('sivalchartindex',lgr4,'收视热度','重复收视度','口碑传播',serialno,watchindex,rwindex,kbindex,true,true);
    zxt2v_chart('sirelchartindex',lgr2,'回忆度','相关度',serialno,rmindex,relindex,true,true);
    zxt3v_chart('siprochartindex',lgr3,'认知提升','喜爱提升','推荐提升',serialno,rzindex,xaindex,tjindex,true,true);
};

function sshowbdcharts(showbranddt){
	var svc=[];
    var serialno=[];
    var relindex=[];
    var valindex=[];
    var proindex=[];
    var watch=[];
    var rewatch=[];
    var remember=[];
    var relate=[];
    var rzup=[];
    var xaup=[];
    var tjup=[];
    var watchindex=[];
    var rwindex=[];
    var rmindex=[];
    var relindex=[];
    var rzindex=[];
    var xaindex=[];
    var tjindex=[];
    var kbindex=[];
    var lgr1=['重复收视度'];
    var lgr2=['回忆度','相关度'];
    var lgr3=['认知提升','喜爱提升','推荐提升'];
    var lgr4=['收视热度','重复收视度','口碑传播'];

    try{
        for(var i=0;i<showbranddt.length;i++){
            svc.push(showbranddt[i].svc);
            serialno.push(showbranddt[i].serialno);
            relindex.push(showbranddt[i].showbdrel);
            valindex.push(showbranddt[i].showval);
            proindex.push(showbranddt[i].brandpro);
            watch.push(showbranddt[i].wt);
            rewatch.push(showbranddt[i].rw);
            remember.push(showbranddt[i].rm);
            relate.push(showbranddt[i].rel);
            rzup.push(showbranddt[i].rzup);
            xaup.push(showbranddt[i].xaup);
            tjup.push(showbranddt[i].tjup);

            watchindex.push(showbranddt[i].wtindex);
            rwindex.push(showbranddt[i].rwindex);
            kbindex.push(showbranddt[i].kbindex);
            rmindex.push(showbranddt[i].rmindex);
            relindex.push(showbranddt[i].relindex);
            rzindex.push(showbranddt[i].rzupindex);
            xaindex.push(showbranddt[i].xaupindex);
            tjindex.push(showbranddt[i].tjupindex);
        }

    }catch(TypeError){
        console.log("定制化图表数据类型错误");
    }



    qsx_chart('bddtsvchartori',serialno,svc,'SVC指数');
    qsx_chart('bddtsvchartindex',serialno,svc,'SVC指数');
    qsx_chart('bddtvalchartori',serialno,valindex,'节目价值指数');
    qsx_chart('bddtrelchartori',serialno,relindex,'节目品牌关联指数');
    qsx_chart('bddtprochartori',serialno,proindex,'品牌资产提升指数');
    qsx_chart('bddtvalchartindex',serialno,valindex,'节目价值指数');
    qsx_chart('bddtrelchartindex',serialno,relindex,'节目品牌关联指数');
    qsx_chart('bddtprochartindex',serialno,proindex,'品牌资产提升指数');
    zxt1_chart('bddtvalsubchartori',lgr1,'重复收视度',serialno,rewatch,'ori4',true,false);
    zxt2_chart('bddtrelsubchartori',lgr2,'回忆度','相关度',serialno,remember,relate,'ori5',true,false);
    zxt3_chart('bddtprosubchartori',lgr3,'认知提升','喜爱提升','推荐提升',serialno,rzup,xaup,tjup,'ori6',true,false);
    zxt3v_chart('bddtvalsubchartindex',lgr4,'收视热度','重复收视度','口碑传播',serialno,watchindex,rwindex,kbindex,true,false);
    zxt2v_chart('bddtrelsubchartindex',lgr2,'回忆度','相关度',serialno,rmindex,relindex,true,false);
    zxt3v_chart('bddtprosubchartindex',lgr3,'认知提升','喜爱提升','推荐提升',serialno,rzindex,xaindex,tjindex,true,false);
}


