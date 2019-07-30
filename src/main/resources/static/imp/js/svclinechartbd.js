
function qsx_chart(id,xser,yser){
	  var qschart = echarts.init(document.getElementById(id),'macarons');
	  option = {
	  		tooltip: {
	  	        trigger: 'axis',
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
	  	    calculable : true,
	  	    grid:{
	  	    	top:'8%',
	  	    	left:'8%',
	  	        bottom:'8%',
	  	        right:'8%'
	  	    },
	  	    xAxis : [
	  	        {
	  	            type : 'category',
	  	            boundaryGap : false,
	  	            show:false,
	  	            data : xser
	  	        }
	  	    ],
	  	    yAxis : [
	  	        {
	  	            show:false,
	  	            type : 'value'
	  	        }
	  	    ],
	  	    series : [
	  	        {
	  	            name:'SVC指数',
	  	            type:'line',
	  	            stack: '总量',
	  	            data:yser
	  	        }
	  	    ]
	  	};

	  qschart.setOption(option);
	  };

	  function qsm_chart(id,xser,yser){
		  var qschart = echarts.init(document.getElementById(id),'macarons');
		  option = {
		  		tooltip: {
		  	        trigger: 'axis',
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
		  	    calculable : true,
		  	    grid:{
		  	    	top:'7%',
		  	    	left:'7%',
		  	        bottom:'7%',
		  	        right:'7%'
		  	    },
		  	    xAxis : [
		  	        {
		  	            type : 'category',
		  	            boundaryGap : false,
		  	            show:false,
		  	            data : xser
		  	        }
		  	    ],
		  	    yAxis : [
		  	        {
		  	            show:false,
		  	            type : 'value'
		  	        }
		  	    ],
		  	    series : [
		  	        {
		  	            name:'SVC指数',
		  	            type:'line',
		  	            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		  	            stack: '总量',
		  	            data:yser
		  	        }
		  	    ]
		  	};

		  qschart.setOption(option);
		  };


function zxt3_chart(id,lgser,tag1,tag2,tag3,xser,ser1,ser2,ser3,opt){
	  var chart = echarts.init(document.getElementById(id),'macarons');
	  var optionp=opt+option;
	     optionp = {
				    tooltip : {
				    	show: true,
				    	formatter: function(params) {
			                var res = '';
			                var myseries = optionp.series;
			                for (var i = 0; i < myseries.length; i++) {
			                    for(var j=0;j<myseries[i].data.length;j++){
			                        if(xser[j]==params.name){
			                            res+=myseries[i].name +' : '+myseries[i].data[j]+'%</br>';
			                        }
			                    }
			                }
			                return res;
			            }
		            },
				    legend: {
				        data:lgser,
				        y:'bottom',
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            dataView : {show: true, readOnly: false},
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : xser
				        }
				    ],
				    yAxis : [
				        {
		                    type: 'value',
		                    axisLabel: {
		                          show: true,
		                          interval: 'auto',
		                          formatter: '{value} %'
		                        },
		                    show: true,
				        },
				    ],
			        grid: {
			            left: 50
			        },
				    series : [
				        {
				            name:tag1,
				            type:'line',
				            stack: tag1,
				            data:ser1,

				        },
				        {
				            name:tag2,
				            type:'line',
				            stack: tag2,
				            data:ser2,

				        },
				        {
				            name:tag3,
				            type:'line',
				            stack: tag3,
				            data:ser3,

				        },

				    ]
				};
		  chart.setOption(optionp);
		  };

		  function zxt2_chart(id,lgser,tag1,tag2,xser,ser1,ser2,opt){
			  var chart = echarts.init(document.getElementById(id),'macarons');
			  var optionp=opt+option;
			  optionp = {
					    tooltip : {
					    	trigger: 'item',
					    	formatter: function(params) {
				                var res = '';
				                var myseries = optionp.series;
				                for (var i = 0; i < myseries.length; i++) {
				                    for(var j=0;j<myseries[i].data.length;j++){
				                        if(xser[j]==params.name){
				                            res+=myseries[i].name +' : '+myseries[i].data[j]+'%</br>';
				                        }
				                    }
				                }
				                return res;
				            }

			            },
					    legend: {
					        data:lgser,
					        y:'bottom',
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            dataView : {show: true, readOnly: false},
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false,
					            data : xser
					        }
					    ],
					    yAxis : [
					        {
			                    type: 'value',
			                    axisLabel: {
			                          show: true,
			                          interval: 'auto',
			                          formatter: '{value} %'
			                        },
			                    show: true,
					        }
					    ],
			            grid: {
			                left: 50
			            },
					    series : [

					        {
					            name:tag1,
					            type:'line',
					            stack: tag1,
					            data:ser1,
					        },
					        {
					            name:tag2,
					            type:'line',
					            stack: tag2,
					            data:ser2,

					        }

					    ]
					};
			  chart.setOption(optionp);

			  };

function zxt3v_chart(id,lgser,tag1,tag2,tag3,xser,ser1,ser2,ser3){
	  var chart = echarts.init(document.getElementById(id),'macarons');
		  option = {
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:lgser,
				        y:'bottom',
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            dataView : {show: true, readOnly: false},
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : xser
				        }
				    ],
				    yAxis : [
				        {
		                    type: 'value',
				        },
				    ],
			        grid: {
			            left: 50
			        },
				    series : [
				        {
				            name:tag1,
				            type:'line',
				            stack: tag1,
				            data:ser1
				        },
				        {
				            name:tag2,
				            type:'line',
				            stack: tag2,
				            data:ser2
				        },
				        {
				            name:tag3,
				            type:'line',
				            stack: tag3,
				            data:ser3
				        },

				    ]
				};
		  chart.setOption(option);
		  };

		  function zxt2v_chart(id,lgser,tag1,tag2,xser,ser1,ser2){
			  var chart = echarts.init(document.getElementById(id),'macarons');
			  option = {
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					        data:lgser,
					        y:'bottom',
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            dataView : {show: true, readOnly: false},
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false,
					            data : xser
					        }
					    ],
					    yAxis : [
					        {
			                    type: 'value',
					        }
					    ],
			            grid: {
			                left: 50
			            },
					    series : [
					        {
					            name:tag1,
					            type:'line',
					            stack: tag1,
					            data:ser1
					        },
					        {
					            name:tag2,
					            type:'line',
					            stack: tag2,
					            data:ser2
					        }

					    ]
					};
			  chart.setOption(option);

			  };
