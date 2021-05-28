$("document").ready(function () {

    //1.上传文件后显示文件名称
    $("#file").change(function () {

        let audio = this.files[0];
        let audioURL = URL.createObjectURL(audio);
        // console.log(audioURL);
        $("#show_audio").attr("src", audioURL);

    });

    //2.点击按钮开始识别的按钮点击事件
    $("#submit").click(function () {

        //1.取得表单中的音频数据
        let audio = document.getElementById("uploadForm");
        let formData = new FormData(audio);

        let config = {
            url: "AipSpeechServlet?method=asr",
            data: formData,
            method: "POST",
            dataType: "json",
            processData: false,
            contentType: false,
            success: function (json) {

                console.log(json);
                $("#results").append("<div><span>识别结果<p>" + json["result"] +"</p></span></div>"); //追加<p>标签
                
            }
        }

        $.ajax(config);

    });

})