
// 标签导航和内容的同步显示

// 为了避免JS加载在body之前，延迟加载
// window.onload()
// $(document).read()
// document 文档建树对象：可以操作当前文档所有的标签
$(document).ready(function (ev) {

    // 1.默认选择标签导航及内容显示
    var key = 0; // key==选择的标签导航的index

    // querySelectorAll(): 查询所有的标签
    // 返回: 标签数组array[tag,tag,tag...]
    var navs = document.querySelectorAll('nav p');
    // 判断当前选择是哪个激活标签
    for (var i = 0; i < navs.length ; i++) {
        // 判断i==key，激活标签导航，增加样式和显示内容
        if(i == key){
            // classList 标签类名的列表属性
            // add()增加类名：active，使用对应样式
            navs[i].classList.add("active");
            // dataset 自定义data属性的集合[name:value]
            var id = navs[i].dataset["cont"];
            console.log(id)
            // 选择标签导航对应的内容：修改显示状态
            document.querySelector("#"+id).style.display = "block";
        }

        // 2.通过点击切换标签导航并同步内容
        navs[i].onclick = function () {
            // 先查询上一个激活的标签导航
            var currentNav = document.querySelector(".active");
            // 移除类名
            currentNav.classList.remove("active");

            // 获取标签导航的ID
            var currentID = currentNav.dataset["cont"];
            // 隐藏内容
            document.querySelector("#"+currentID).style.display = "none";

            // this = 点击标签
            // 设置当前点击标签导航的样式和内容显示
            this.classList.add("active");
            var myID = this.dataset["cont"];
            document.querySelector("#"+myID).style.display = "block";

        }
    }

});
