$("document").ready(function () {

    //1.将用户上传的图片显示在页面中
    $("#file").change(function () {

        //1.1 取得文件上传组件中上传的文件信息
        /*
        事件源：被事件动作所指向的HTML页面中的元素
        事件动作：
        this关键字的解释：当前对象 -> 谁在调用这个方法，谁就是我的当前对象
         */
        let img = this.files[0];

        //2.2 将上传的图片文件转换为一个URL，并使用这个转换得到的URL替换img组件的src属性
        let imgURL = URL.createObjectURL(img);  //jpg -> URL
        $("#show_img").attr("src", imgURL);

    });

    //2.给开始分析按钮绑定点击事件
   $("#submit").click(function () {

       //1.获取加载在表单当中的文件数据
       //1.1 找到表单在HTML页面中的原生JS对象
       let form = document.getElementById("uploadForm");
       //1.2 创建一个FormData对象用来承载表单中的所有数据信息（包括上传的文件信息）
       let formData = new FormData(form);

       //2.创建Ajax请求的配置对象
       let config = {
           url: "AipFaceServlet?method=faceDetection", //在发送POST请求的时候携带自定义的数据
           data: formData,
           method: "POST",
           dataType: "json",
           //如果实现文件上传的Ajax的配置，那么必须添加如下两个配置
           processData: false, //关闭数据校验功能
           contentType: false, //忽略请求头信息
           success: function (json) {

               // if (json["result"]["face_list"][0]["beauty"] > 60)
               //     $("#beauty").html("好看的一塌糊涂！");

               $("#num").html(json["result"]["face_num"]);
               $("#age").html(json["result"]["face_list"][0]["age"]);
               $("#beauty").html(json["result"]["face_list"][0]["beauty"]);
               $("#mood").html(json["result"]["face_list"][0]["emotion"]["type"]);
               $("#sex").html(json["result"]["face_list"][0]["gender"]["type"]);
               $("#token").html(json["log_id"]);

           }
       }

       //3.发送Ajax到服务器端后台
       $.ajax(config);
   })

});