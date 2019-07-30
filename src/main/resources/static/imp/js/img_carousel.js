var timer = null,
    index = 0,
    pics = document.getElementsByClassName("banner-slide"),
    lis = document.getElementsByTagName("li");
 
 
//封装一个代替getElementById()的方法
function byId(id){
    return typeof(id) === "string"?document.getElementById(id):id;
}
 
function slideImg() {
    var newslist = byId("newslist");
    var banner = byId("newsrelease");
    newslist.onmouseover = function(){
        stopAutoPlay();
    }
    newslist.onmouseout = function(){
        startAutoPlay();
    }
    newslist.onmouseout();
 
    //鼠标悬浮切换图片
    for(var i=0;i<pics.length;i++){
        lis[i].id = i;
          //给每个li项绑定鼠标悬浮事件
        lis[i].onmouseover = function(){
          //获取当前li项的index值
            index = this.id;
            changeImg();
        }
    }
}
//开始播放轮播图
function startAutoPlay(){
    timer = setInterval(function(){
        index++;
        if(index>9){
            index = 0;
        }
        changeImg();
    },5000);
}
//暂停播放
function stopAutoPlay(){
    if (timer) {
        clearInterval(timer);
    }
}
//改变轮播图
function changeImg(){
    for(var i=0;i<pics.length;i++){
        pics[i].style.display = "none";
        lis[i].className = "";
    }
    pics[index].style.display = "block";
    lis[index].className = "changeColor";
}
slideImg();
