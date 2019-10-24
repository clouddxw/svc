

function sboottab(id,category,date) {
    var oTableInit= new Object();
    //初始化Table
    oTableInit.Init = function () {
        $(id).bootstrapTable({
            url: 'http://svc.iresearch.cn/api/vf/mi/getMiPirceRanges?category='+category+'&date='+date,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sidePagination: "client",
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            contentType: "application/x-www-form-urlencoded",
            columns: [
                {
                    field: 'price_range',
                    title: 'Price<br>Range',
                    align: 'center',
                    width: '120'
                }, {
                    field: 'sales_rate',
                    align: 'center',
                    valign: 'middle',
                    title: '% of<br>Sales',
                    formatter:rateFormatter
                },
                {
                    field: 'sales_yoy',
                    width: '60',
                    align: 'center',
                    valign: 'middle',
                    title: 'YoY',
                    formatter: yoyFormatter
                }
            ],
            rowStyle:  function rowStyle(row, index) {
                var style = {};
                // style={css:{'color':'#ed5565'}};
                if (index % 2 === 0) {//偶数行
                    style={css:{'background':'#CCCCCC'}};
                } else {//奇数行
                    style={css:{'background':'#ffffff'}};
                }
                return style;
            }


        });

    };


    return oTableInit;
};


function yoyFormatter(value, row, index) {//赋予的参数
    var out;
    yoy=Math.round(value*100);
    if(value<0){
        out='<div style="color:red;font-weight:900;">'+yoy+'%</div>'
    }else{
        out='<div style="color:green;font-weight:900">'+yoy+'%</div>'
    }
    return out;
}

function rateFormatter(value, row, index) {//赋予的参数
    var out;
    rate=(value*100).toFixed(1);
    if (rate.toString().indexOf(".")<0){
        ratef=rate.toString()+".0";
        out="<span>"+ratef+"%</span>"
    }else{
        out="<span>"+rate+"%</span>";
    }
    return out;
}



function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}