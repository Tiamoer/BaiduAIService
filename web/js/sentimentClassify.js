$("document").ready(function () {   //页面内容加载完成后再执行其中的内容

    //第一个动作，给submit按钮添加事件监听
    $("#submit").click(function () {
        let content = $("#text").val(); //获取文本框输入内容

        //将上面获取到的输入框中的文字发送到服务器端后台，进一步交给百度AI进行分析并得到结果
        //使用Ajax,对Ajax请求进行配置
        let config = {
            url: "AipNlpServlet", //这个属性决定当前请求的发送路径
            data: {method:"sentimentClassify",content:content}, //通过data属性确定向服务器发送的数据
            method: "GET",  //确定发送请求使用的方式
            dataType: "json",  //通过这属性决定后台响应回来数据的格式：text，html，xml，json
            success: function (json){   //用来确定向Ajax发送成功并且接收到结果后，要做的事情

                //解析JSON和在JS中访问数组和值
                let items = json["items"];
                let result = items[0];
                let positive_prob = result["positive_prob"];
                let negative_prob = result["negative_prob"];
                let sentiment = result["sentiment"];
                let confidence = result["confidence"];

                switch (sentiment) {
                    case 0:
                        sentiment = "消极的";
                        break;
                    case 1:
                        sentiment = "中性的";
                        break;
                    case 2:
                        sentiment = "积极的";
                        break;
                }

                positive_prob *= 100;
                positive_prob += "%";
                negative_prob *= 100;
                negative_prob += "%";
                confidence *= 100;
                confidence += "%";

                //向前端页面中指定的输入框回显数据
                $("#sentiment").html(sentiment);
                $("#confidence").html(confidence);
                $("#positive_prob").html(positive_prob);
                $("#negative_prob").html(negative_prob);
            }
        };

        //向服务器按照上述配置发送Ajax请求
        $.ajax(config);
    });

    //第二个动作：为输入框加上一个监听，在输入文字的时候，改变下面剩余字数的值
    $("#text").bind("input propertychange", function () {
        //获取当前输入框中输入内容的字数
        let length = $("#text").val().length;

        //使用300减去上面得到的字符串的长度，就是剩余字符的数量
        let remeaningLength = 300 - length;

        //改变剩余字数的取值
        $("#length").html(remeaningLength);
    });
})