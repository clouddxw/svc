function getTopWinow(){
    var p = window;
    while(p != p.parent){
        p = p.parent;
    }
    return p;
};
function loginCheck(){
    var name = $("#loginUser").val();    //用户名
    var pwd = $("#loginPwd").val();      //密码
    var datas="";                  //返回来的结果
    var diff;
    var enddate;
    var role;
    $.ajax({
        url: "/loginuser",
        type: 'post',
        dataType: 'json',
        data:{
            username:name,
            password:pwd
        },
        jsonp: 'callback',
        async: false,
        success: function (data) {
            datas = data.res;
            diff = data.diff;
            enddate = data.enddate;
            role=data.role;
        }
    });
    // var endTime=new Date('06/30/2019');
    // var now =new Date();
    // var daydiff =Math.floor((endTime.getTime()-now.getTime())/(1000*60*60*24))+1;
    var top = getTopWinow();
    //alert(datas);
    //console.log(datas);
    if(datas == "nameFalse"){                 //用户名不正确
        layer.tips('用户名不存在！', '#loginUser', {
            tips: [2, '#FF3030'],
            time: 2000
        });
    }else if(datas == "pwdFalse"){            //密码不正确

        layer.tips('密码不正确	！', '#loginPwd', {
            tips: [2, '#FF3030'],
            time: 2000
        });
    }else if(datas == "Success"){
        if(role == "normal" ){
            top.location.href = 'http://svc.iresearch.cn/svc/basic/index.html';
        }else{
            top.location.href = 'http://svc.iresearch.cn/svc/vip/index.html';
        }
    }
    else if(datas == "SuccessTw"){
        alert('您的正式账号将在'+diff+'天后到期，届时只能访问部分数据。如果需要申请正式账号续期请联系IRS.SVC@iresearch.com.cn或咨询相关商务销售同事。');
        if(role == "normal" ){
            top.location.href = 'http://svc.iresearch.cn/svc/basic/index.html';
        }else{
            top.location.href = 'http://svc.iresearch.cn/svc/vip/index.html';
        }

    }else if(datas == "Te"){
        alert('您的正式账号已于'+enddate+'到期，只能访问部分数据。如果需要申请正式账号续期请联系IRS.SVC@iresearch.com.cn或咨询相关商务销售同事。');
        if(role == "normal" ){
            top.location.href = 'http://svc.iresearch.cn/svc/basic/index.html';
        }else{
            top.location.href = 'http://svc.iresearch.cn/svc/vip/index.html';
        }
    }

}