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
            url: "AipOcrServlet?method=idcard",
            data: formData,
            method: "POST",
            dataType: "json",
            processData: false,
            contentType: false,
            success: function (json) {

                $("#home").html(json["words_result"]["住址"]["words"]);
                $("#id").html(json["words_result"]["公民身份号码"]["words"]);
                $("#birth").html(json["words_result"]["出生"]["words"]);
                $("#name").html(json["words_result"]["姓名"]["words"]);
                $("#sex").html(json["words_result"]["性别"]["words"]);
                $("#nation").html(json["words_result"]["民族"]["words"]);

            }
        };

        //3.发送Ajax
        $.ajax(config);
    });
});