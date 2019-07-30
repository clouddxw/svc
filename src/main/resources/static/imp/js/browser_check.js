var userAgent = navigator.userAgent.toLowerCase(); //取得浏览器的userAgent字符串
var isIE = userAgent.indexOf("msie")>-1;
var safariVersion;
if(isIE){
    safariVersion =  userAgent.match(/msie ([\d.]+)/)[1];
};

if(safariVersion <= 9.0 ){
    alert('系统检测到您正在使用ie9以下内核的浏览器，不能实现完美体验，请更换或升级浏览器访问！')
};
