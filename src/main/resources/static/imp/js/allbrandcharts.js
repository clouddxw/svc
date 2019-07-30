function brandshowchart(brandshow,disp){
	//var show=[];
	var xser=[];
	var yser=[];
	for(var i=0;i<brandshow.length;i++){
		//show.push(brandshow[i].show);
		xser.push(brandshow[i].show+"|"+brandshow[i].begindate);
		yser.push(brandshow[i].svc);
	}

	var bdswchart = echarts.init(document.getElementById('bdswchart'+disp),'macarons');
	option = {
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
	        },
            formatter:function(params){  //数据单位格式化
            	var axisv= params[0].name.split("|")
                var relVal =axisv[0]+'<br>开播时间：'+axisv[1];  //x轴名称  
                for (var i = 0, l = params.length; i < l; i++) {    
                 if(params[i].value){
                    relVal += '<br/> ' + params[i].seriesName + ' : ' + params[i].value;    
                 }
                }  
                    
                return relVal;    
             },
  	        position: function(point, params, dom, rect, size){
  	            //其中point为当前鼠标的位置，size中有两个属性：viewSize和contentSize，分别为外层div和tooltip提示框的大小
  	            var x = point[0];//
  	            var y = point[1];
  	            var viewWidth = size.viewSize[0];
  	            var viewHeight = size.viewSize[1];
  	            var boxWidth = size.contentSize[0];
  	            var boxHeight = size.contentSize[1];
  	            var posX = 0;//x坐标位置
  	            var posY = 0;//y坐标位置

  	            if(x<boxWidth){//左边放不开
  	                posX = 5;
  	            }else{//左边放的下
  	                posX = x-boxWidth;
  	            }

  	            if(y<boxHeight){//上边放不开
  	                posY = 5;
  	            }else{//上边放得下
  	                posY = y-boxHeight;
  	            }

  	            return [posX,posY];

  	        },
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : xser,
	            axisTick: {
	                alignWithLabel: true
	            },
	            axisLabel : {
	            	formatter: function(value){
	            	return value.split("|")[1];}
	            	}

	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            show:false,
	        }
	    ],
	    series : [
	        {
	            name:'SVC指数',
	            type:'scatter',
	            //barWidth: '5',
	            data:yser
	        }
	    ]
	};
	
	bdswchart.setOption(option);
}
