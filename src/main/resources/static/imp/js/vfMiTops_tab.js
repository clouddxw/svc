
function boottab(id,category,date) {
    var oTableInit= new Object();
    //初始化Table
    oTableInit.Init = function () {
        $(id).bootstrapTable({
            url: 'http://svc.iresearch.cn/VF/MI/getMiTopItems?category='+category+'&date='+date,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sidePagination: "client",
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            contentType: "application/x-www-form-urlencoded",
            columns: [
                {
                    field: 'link',
                    title: 'Item',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                    formatter: operateFormatter,
                    events: 'operateEvents'
                }, {
                    field: 'brand',
                    align: 'center',
                    valign: 'middle',
                    width: '85',
                    title: 'Brand'
                }, {
                    field: 'sales',
                    width: '50',
                    align: 'center',
                    title: 'Sales<br>(M)',
                    formatter: salesFormatter,
                },
                {
                    field: 'price',
                    width: '45',
                    align: 'center',
                    valign: 'middle',
                    title: 'Price'
                },
                {
                    field: 'discount',
                    width: '60',
                    align: 'center',
                    title: 'Discount<br>(off)'
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

function sboottab(id,category,date) {
    var oTableInit= new Object();
    //初始化Table
    oTableInit.Init = function () {
        $(id).bootstrapTable({
            url: 'http://svc.iresearch.cn/VF/MI/getMiTopStores?category='+category+'&date='+date,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sidePagination: "client",
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            contentType: "application/x-www-form-urlencoded",
            columns: [
                {
                    field: 'rk',
                    title: 'RK',
                    align: 'center',
                    valign: 'middle',
                    width: '40',
                    height:'60'
                }, {
                    field: 'store',
                    align: 'center',
                    valign: 'middle',
                    width: '230',
                    title: 'Store'
                }, {
                    field: 'sales',
                    width: '50',
                    align: 'center',
                    title: 'Sales<br>(M)',
                    formatter: salesFormatter,
                },
                {
                    field: 'yoy',
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


function operateFormatter(value, row, index) {//赋予的参数
    return '<a class = "view"  href="javascript:void(0)"><div style="height: 90px;width: 90px;"><img style="height: 90px;" src="http://svc.iresearch.cn/vf_mi_img/'+value+'"></div> </a>'
}

function salesFormatter(value, row, index) {//赋予的参数
    var out;
    if (value==null){
        out="<span>-</span>"
    }else{
        sales=value.toFixed(1);
        if (sales.toString().indexOf(".")<0){
            salesf=sales.toString()+".0";
            out="<span>"+salesf+"</span>"
        }else{
            out="<span>"+sales+"</span>";
        }

    }

    return out;
}


function yoyFormatter(value, row, index) {//赋予的参数
    var out;

    if (value==null){
        out='<div style="height:36px;line-height:36px;font-weight:900">-</div>';
    }else if(value<0){
        yoy=Math.round(value*100);
        out='<div style="height:36px;line-height:36px;color:red;font-weight:900;">'+yoy+'%</div>'
    }else{
        yoy=Math.round(value*100);
        out='<div style="height:36px;line-height:36px;font-weight:900">'+yoy+'%</div>'
    }
    return out;
}


window.operateEvents = {
    'click .view': function (e, value, row, index) {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            area: 'auto',
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            scrollbar: false,
            // offset:['200px','300px'],
            content: '<img style="width:100%;height: 100%" src="http://svc.iresearch.cn/vf_mi_img/'+row.link+'"/>'
        });
    },
};

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}