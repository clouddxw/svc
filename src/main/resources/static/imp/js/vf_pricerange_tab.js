
function boottab(id,category,date) {
    var oTableInit= new Object();
    //初始化Table
    oTableInit.Init = function () {
        $(id).bootstrapTable({
            url: 'http://svc.iresearch.cn/VF/MI/getPirceRanges?category='+category+'&date='+date,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sidePagination: "client",
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            contentType: "application/x-www-form-urlencoded",
            height: 590,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            columns: [
                {
                    field: 'link',
                    title: '图片',
                    align: 'center',
                    valign: 'center',
                    width: '100',
                    formatter: operateFormatter,
                    events: 'operateEvents'
                }, {
                    field: 'brand',
                    align: 'center',
                    valign: 'center',
                    width: '100',
                    title: 'Brand'
                }, {
                    field: 'sales',
                    width: '50',
                    align: 'center',
                    title: 'Sales<br>(M)'
                },
                {
                    field: 'price',
                    width: '50',
                    align: 'center',
                    valign: 'center',
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




function operateFormatter(value, row, index) {//赋予的参数
    return '<a class = "view"  href="javascript:void(0)"><div style="height: 90px;width: 90px;"><img style="height: 90px;" src="http://svc.iresearch.cn/vf_mi_img/'+value+'"></div> </a>'
}


window.operateEvents = {
    'click .view': function (e, value, row, index) {
        layer.open({
            type: 1,
            title: '<span style="font-size:8px;word-break: break-all;' +
            '    white-space: normal !important;">'+row.items+'</span>',
            closeBtn: 0,
            area: 'auto',
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            scrollbar: false,
            content: '<img style="width:100%;height: 100%" src="http://svc.iresearch.cn/vf_mi_img/'+row.link+'"/>'
        });
    },
};

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}