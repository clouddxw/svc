function kbindex_chart(bdtime,bdval,wbtime,wbval,wxtime,wxval,opt){

    var bdchart = echarts.init(document.getElementById('bdindex'+opt),'macarons');
    var wbchart = echarts.init(document.getElementById('wbindex'+opt),'macarons');
    var wxchart = echarts.init(document.getElementById('wxindex'+opt),'macarons');
    bdopt = {
            title: {
                text: '百度指数',
            },
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },

                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: bdtime
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value}'
                    }
                }
            ],
            series: [
                {
                    
                    type: 'line',
                    data: bdval,
                    itemStyle : {  
                        normal : {  
                            lineStyle:{  
                                color:'#FA7F4f'  
                            }  
                        }  
                    }, 
                    markPoint: {
                        data: [
                            { type: 'max', name: '最大值' },
                            { type: 'min', name: '最小值' }
                        ]
                    },
                    markLine: {
                        data: [
                            { type: 'average', name: '平均值' }
                        ]
                    }
                },

            ]
        };
    
    wbopt = {
            title: {
                text: '微博指数',
            },
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },

                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: wbtime
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value}'
                    }
                }
            ],
            series: [
                {
                    
                    type: 'line',
                    data: wbval,
                    itemStyle : {  
                        normal : {  
                            lineStyle:{  
                                color:'#FA7F4f'  
                            }  
                        }  
                    }, 
                    markPoint: {
                        data: [
                            { type: 'max', name: '最大值' },
                            { type: 'min', name: '最小值' }
                        ]
                    },
                    markLine: {
                        data: [
                            { type: 'average', name: '平均值' }
                        ]
                    }
                },

            ]
        };
    
    wxopt = {
            title: {
                text: '微信指数',
            },
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },

                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: wxtime
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value}'
                    }
                }
            ],
            series: [
                {
                    
                    type: 'line',
                    data: wxval,
                    itemStyle : {  
                        normal : {  
                            lineStyle:{  
                                color:'#FA7F4f'  
                            }  
                        }  
                    }, 
                    markPoint: {
                        data: [
                            { type: 'max', name: '最大值' },
                            { type: 'min', name: '最小值' }
                        ]
                    },
                    markLine: {
                        data: [
                            { type: 'average', name: '平均值' }
                        ]
                    }
                },

            ]
        };
		
		
    bdchart.setOption(bdopt);
    wbchart.setOption(wbopt);
    wxchart.setOption(wxopt);
    
};

function userproty_chart(sexm,sexw,citylv1,citylv2,citylv3,agegp1,agegp2,agegp3,agegp4,agegp5,opt){
    var sexchart = echarts.init(document.getElementById('sex_chart'+opt),'macarons');
    var agechart = echarts.init(document.getElementById('age_chart'+opt),'macarons');
    var citychart = echarts.init(document.getElementById('city_chart'+opt),'macarons');	 
//	agegpoption = {  
//			   title : {  
//				   text: '年龄',  
//				   x:'center',
//				   y:15
//			   },  
//			   tooltip : {  
//				   trigger: 'item',  
//				   //formatter: "{b} : {c} ({d}%)"
//				   //formatter: "{b} : {c}"
//				   formatter: "人群标签 : {b}<br>TGI : {c} "  
//			   },  
//			   legend: {  
//				  orient : 'horizontal',  //horizontal
//				   x: 'center',  
//				   y: 'bottom',
//				   data: ['18岁以下','18-24岁','25-34岁','35-50岁','50岁以上'],
//				   formatter:function(val){    return val.substring(0,3)+("\n")+val.substring(3);},
//			   },  
//			   //formatter:function(val){    return val.split(",").join("\n");},//此语句是让legend 中的文字进行换行</span>			  
//			   series : [  
//				   {  
//					   name: '数据来源',  
//					   type: 'pie', 
//					   radius : '40%',  
//					   center: ['50%', '45%'],  
//					   data:[  
//						   {value:agegp1, name:'18岁以下'},
//						   {value:agegp2, name:'18-24岁'},
//						   {value:agegp3, name:'25-34岁'},
//						   {value:agegp4, name:'35-50岁'},
//						   {value:agegp5, name:'50岁以上'}
//					   ],  
//					   itemStyle: {  
//					   normal:{  
//						 label:{  
//						 show:true,  
//						 formatter:function(val){   //让series 中的文字进行换行  
//							 return val.name.split(",").join("\n");}  
//						 },  
//						 labelLine:{  
//						 show:true  
//						 }  
//						 },  
//						   emphasis: {  
//							   shadowBlur: 10,  
//							   shadowOffsetX: 0,  
//							   shadowColor: 'rgba(0, 0, 0, 0.5)'  
//						   }  
//					   }  
//				   }  
//			   ],  
//				
//			};
    
    agegpoption = {
    	    title: {
    	        text: '年龄',
    	        x: 'center',
    	        //y: 15
    	    },
    	    tooltip: {
    	        trigger: 'item',
    	        //formatter: "{b} : {c} ({d}%)"
    	        //formatter: "{b} : {c}"
    	        formatter: "人群标签 : {b}<br>TGI : {c} "
    	    },
    	    legend: {
    	        orient: 'horizontal',  //horizontal
    	        x: 'center',
    	        y: 220,
    	        data: ['18岁以下', '18-24岁', '25-34岁', '35-50岁', '50岁以上'],
    	        formatter: function (val) { return val.substring(0, 3) + ("\n") + val.substring(3); },
    	    },
    	    //formatter:function(val){    return val.split(",").join("\n");},//此语句是让legend 中的文字进行换行</span>			  
    	    series: [
    	        {
    	            name: '半径模式',
    	            type: 'pie',
    	            radius: [20, 80],
     	            center: ['50%', '45%'],
     	            roseType: 'radius',
     	            width: '35%',       // for funnel
    	            max: 40,            // for funnel
    	            itemStyle: {
    	                normal: {
    	                    label: {
    	                        show: false
    	                    },
    	                    labelLine: {
    	                        show: false
    	                    }
    	                },
    	                emphasis: {
    	                    label: {
    	                        show: true
    	                    },
    	                    labelLine: {
    	                        show: true
    	                    }
    	                }
    	            },
    	            data: [
    	                { value: agegp1, name: '18岁以下' },
    	                { value: agegp2, name: '18-24岁' },
    	                { value: agegp3, name: '25-34岁' },
    	                { value: agegp4, name: '35-50岁' },
    	                { value: agegp5, name: '50岁以上' }
    	            ]
    	        }
    	    ],

    	};

//	sexoption =  {  
//			   title : {  
//				   text: '性别',  
//				   x:'center'  
//			   },  
//			   tooltip : {  
//				   trigger: 'item',  
//				   //formatter: "{b} : {c} ({d}%)" 
//				   formatter: "人群标签 : {b}<br>TGI : {c} "  
//			   },  
//			   legend: {  
//					  orient : 'horizontal',  //horizontal
//					   x: 'center',  
//					   y: 'bottom',
//					  data: ['男','女'],
//					  
//			   },  
//			   //formatter:function(val){    return val.split(",").join("\n");},//此语句是让legend 中的文字进行换行</span>
//			   series : [  
//				   {  
//					   name: '数据来源',  
//					   type: 'pie', 
//					   radius : '55%',  
//					   center: ['50%', '50%'],  
//					   data:[
//							{value:sexm, name:'男'},
//							{value:sexw, name:'女'},
//						],  
//					   itemStyle: {  
//					   normal:{  
//						 label:{  
//						 show:true,  
//						 formatter:function(val){   //让series 中的文字进行换行  
//							 return val.name.split(",").join("\n");}  
//						 },  
//						 labelLine:{  
//						 show:true  
//						 }  
//						 },  
//						   emphasis: {  
//							   shadowBlur: 10,  
//							   shadowOffsetX: 0,  
//							   shadowColor: 'rgba(0, 0, 0, 0.5)'  
//						   }  
//					   }  
//				   }  
//			   ],  
//				
//			};
	
	
	sexoption = {
			   title : {  
				   text: '性别',  
				   x:'center'  
			   },  
			   tooltip : {  
				   trigger: 'item',  
				   //formatter: "{b} : {c} ({d}%)" 
				   formatter: "人群标签 : {b}<br>TGI : {c} "  
			   },  
			   legend: {  
					  orient : 'horizontal',  //horizontal
					   x: 'center',  
					   y: 220,
					  data: ['男','女'],
					  
			   },  
    	    //formatter:function(val){    return val.split(",").join("\n");},//此语句是让legend 中的文字进行换行</span>			  
    	    series: [
    	        {
    	            name: '半径模式',
    	            type: 'pie',
    	            radius: [20, 80],
     	            center: ['50%', '45%'],
     	            roseType: 'radius',
     	            width: '35%',       // for funnel
    	            max: 40,            // for funnel
    	            itemStyle: {
    	                normal: {
    	                    label: {
    	                        show: false
    	                    },
    	                    labelLine: {
    	                        show: false
    	                    }
    	                },
    	                emphasis: {
    	                    label: {
    	                        show: true
    	                    },
    	                    labelLine: {
    	                        show: true
    	                    }
    	                }
    	            },
				   data:[
						{value:sexm, name:'男'},
						{value:sexw, name:'女'},
					],  
    	        }
    	    ],

    	};
	
//	cityoption = {  
//			   title : {  
//				   text: '城市等级',  
//				   x:'center'  
//			   },  
//			   tooltip : {  
//				   trigger: 'item',  
//				   //formatter: "{b} : {c} ({d}%)"  
//				   formatter: "人群标签 : {b}<br>TGI : {c} " 
//			   },  
//			   legend: {  
//					  orient : 'horizontal',  //horizontal
//					   x: 'center',  
//					   y: 200,
//					   data: ['一线城市','二线城市','三线城市及以下'],
//					   formatter:function(val){    return val.substring(0,4)+("\n")+val.substring(4);},
//			   },  
//			   //formatter:function(val){    return val.split(",").join("\n");},//此语句是让legend 中的文字进行换行</span>
//			   series : [  
//				   {  
//					   name: '数据来源',  
//					   type: 'pie', 
//					   radius : '45%',  
//					   center: ['50%', '45%'],  
//					   data:[
//							{value:citylv1, name:'一线城市'},
//							{value:citylv2, name:'二线城市'},
//							{value:citylv3, name:'三线城市及以下'}
//						],  
//					   itemStyle: {  
//					   normal:{  
//						 label:{  
//						 show:true,  
//						 formatter:function(val){   //让series 中的文字进行换行  
//							 return val.name.split(",").join("\n");}  
//						 },  
//						 labelLine:{  
//						 show:true  
//						 }  
//						 },  
//						   emphasis: {  
//							   shadowBlur: 10,  
//							   shadowOffsetX: 0,  
//							   shadowColor: 'rgba(0, 0, 0, 0.5)'  
//						   }  
//					   }  
//				   }  
//			   ],  
//				
//			};

	cityoption = {
			   title : {  
				   text: '城市等级',  
				   x:'center'  
			   },  
			   tooltip : {  
				   trigger: 'item',  
				   //formatter: "{b} : {c} ({d}%)"  
				   formatter: "人群标签 : {b}<br>TGI : {c} " 
			   },  
			   legend: {  
					  orient : 'horizontal',  //horizontal
					   x: 'center',  
					   y: 220,
					   //y: 'bottom',
					   data: ['一线城市','二线城市','三线城市及以下'],
					   formatter:function(val){    return val.substring(0,4)+("\n")+val.substring(4);},
			   },  
 	    //formatter:function(val){    return val.split(",").join("\n");},//此语句是让legend 中的文字进行换行</span>			  
 	    series: [
 	        {
 	            name: '半径模式',
 	            type: 'pie',
 	            radius: [20, 80],
 	            center: ['50%', '45%'],
 	            roseType: 'radius',
 	            width: '35%',       // for funnel
 	            max: 40,            // for funnel
 	            itemStyle: {
 	                normal: {
 	                    label: {
 	                        show: false
 	                    },
 	                    labelLine: {
 	                        show: false
 	                    }
 	                },
 	                emphasis: {
 	                    label: {
 	                        show: true
 	                    },
 	                    labelLine: {
 	                        show: true
 	                    }
 	                }
 	            },
			   data:[
					{value:citylv1, name:'一线城市'},
					{value:citylv2, name:'二线城市'},
					{value:citylv3, name:'三线城市及以下'}
				],   
 	        }
 	    ],

 	};
	
	sexchart.setOption(sexoption);
	agechart.setOption(agegpoption);
	citychart.setOption(cityoption);

  }	;
  
 