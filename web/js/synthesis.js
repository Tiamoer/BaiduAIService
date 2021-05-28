$("document").ready(function (){

    //1.剩余输入字符数量
    $("#text").bind("input propertychange", function () {
        //获取当前输入框中输入内容的字数
        let length = $("#text").val().length;

        //使用300减去上面得到的字符串的长度，就是剩余字符的数量
        let remeaningLength = 300 - length;

        //改变剩余字数的取值
        $("#length").html(remeaningLength);
    });

    //2.按钮点击事件
    $("#submit").click(function () {

        //2.1 获取输入
        let content = $("#text").val(); //文本框输入的内容
        let per = $("[name='per']:checked").val(); //选择所有name属性未per的元素中，被选中的那一个
        let spd = $("[name='spd']:checked").val(); //语速的选择
        let pit = $("[name='pit']:checked").val(); //语调的选择

        //2.2 Ajax发送请求的配置
        let config = {
            url: "AipSpeechServlet",
            data: {method:"synthesis", content:content, per:per, spd:spd, pit:pit},
            method: "GET",
            dataType: "text", //使用text的原因：Ajax接收响应的时候不能够直接接受字节数组的响应，所以我们在服务器后台将字节数粗转换为Base64字符串值进行传递
            success: function (text) {

                //1.找到进行音频播放的audio组件（以原生JS对象的方式进行查找）
                let player = document.getElementById("audio");

                //2.将接收到的Base64字符串拼接在audio组件的src属性中
                //$(原生JS对象) -> 转换为JQuery对象，使其可以使用JQuery的方法
                //audio组件会自动将Base64字符串转换为需要播放的字节信息，是自动的过程
                $(player).attr("src", "data:audio/wav;base64," + text);

                //3.加载src属性中，Base64字符串中携带的音频数据信息
                player.load();

                if (player.paused) {  //如果为真，说明当前播放器没有播放任何音频
                    player.play();  //play()方法是原生的JS对象方法
                } else { //当前播放器在播放其他音频
                    player.pause();
                }

            }
        };

        //2.3 发送Ajax请求
        $.ajax(config);

    });

});