function getTopWinow(){
    var p = window;
    while(p != p.parent){
        p = p.parent;
    }
    return p;
};

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

var stat = getQueryString("stat");

$(function (){if(stat=='onlineover'){

    layer.alert("您的账号已在其他地区登录",function () {
        window.location.href = "http://svc.iresearch.cn/login.html"
    });

}})

function loginCheck(){
    var username = $("#loginUser").val();    //用户名
    var password = $("#loginPwd").val();      //密码
    // var datas="";                  //返回来的结果
    // var diff;
    // var enddate;
    // var role;
    $.ajax({
        url: "http://svc.iresearch.cn/logining",
        data:{
            "username": username,//字段和html页面的要对应  id和name一致
            "password": password,//字段和html页面的要对应
            // "remember-me":remember,
            // "imageCode": imageCode
        },
        dataType:"json",
        type:'post',
        async:false,
        success:function (data) {
            if (data.code == 402){
                layer.alert(data.msg,function () {
                    window.location.href = "http://svc.iresearch.cn/login.html"
                });
            }
            if (data.code == 403){
                layer.alert(data.msg,function () {
                    window.location.href = "http://svc.iresearch.cn/login.html"
                });
            }

            // if (data.code == 600){
            //     layer.alert(data.msg,function () {
            //         window.location.href = "http://svc.iresearch.cn/login.html"
            //     });
            // }

            if(data.code == 200){
                window.location.href = "http://svc.iresearch.cn/svc/index";
            }
        }});


}