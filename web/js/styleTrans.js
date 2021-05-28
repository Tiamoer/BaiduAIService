$("document").ready(function () {

    //1.获取上传的图片并回显
    $("#file").change(function () {

        let img = this.files[0];
        let imgURL = URL.createObjectURL(img);
        $("#show_img").attr("src", imgURL);

    });

    //2.按钮点击事件监听发送Ajax
    $("#submit").click(function () {

        //1.取得表单中上传文件图像的数据
        let form = document.getElementById("uploadForm");
        let formData = new FormData(form);

        //2.配置Ajax发送请求参数
        let config = {
            url: "AipImageProcessServlet?method=styleTrans",
            data: formData,
            method: "POST",
            dataType: "json",
            processData: false,
            contentType: false,
            success: function (json) {

                let imageBase64Str = json["image"];
                $("#result_img").attr("src", "data:image/jpg;base64," + imageBase64Str);
                console.log(json);

            }
        };

        //3.发送Ajax
        $.ajax(config);
    });
});