function getTopWinow(){
    var p = window;
    while(p != p.parent){
        p = p.parent;
    }
    return p;
}


$(function () {
    $(document).ajaxStart(function () {
        var lockwin = $("#loading");
        lockwin.css("width", "100%");
        lockwin.css("display", "block");
        lockwin.css("height", $(window).height() + $(window).scrollTop());
        $("#loading img").css("display", "block");
        $("#loading img").css("left", ($(window).width() - 150) / 2);
        $("#loading img").css("top", ($(window).height() + $(window).scrollTop()) / 2);
    });

    $.ajaxSetup({
        type: 'get',
        error: function(xhr,status) {
            var sessionStatus = xhr.getResponseHeader('sessionstatus');
            if(sessionStatus == 'timeout') {
                var top = getTopWinow();
                var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
                if (yes) {
                    top.location.href = 'http://svc.iresearch.cn/login.html';
                }
            }else{
                var errorE = '抱歉，没有找到符合您要求的数据!';
                alert(errorE);
            }

        }
    });

    $(document).ajaxStop(function () {
        var lockwin = $("#loading");
        lockwin.css("width", "0");
        lockwin.css("display", "none");
        lockwin.css("height", "0");
        $("#loading img").css("display", "none");
    });
});