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
            url: "AipOcrServlet?method=textdetect",
            data: formData,
            method: "POST",
            dataType: "json",
            processData: false,
            contentType: false,
            success: function (json) {

                let $results = $("#results");

                //1.在id=results的div标签中保留<p>标签，删除<div>占位符
                $results.empty(); //清空当前标签下的所有元素
                $results.append("<p>识别结果</p>"); //追加<p>标签

                //2.将分析结果json串中的分析结果传输到前端
                let wordsArray = json["words_result"];

                for (let i = 0; i < wordsArray.length; i++) {

                    $results.append("<div><span>文字内容<p>" + wordsArray[i]["words"] +"</p></span></div>"); //追加<p>标签
                    $results.append("<div><span>置信程度<p></p>" + wordsArray[i]["probability"]["average"] +"</span></div>"); //追加<p>标签

                }

            }
        };

        //3.发送Ajax
        $.ajax(config);
    });
});