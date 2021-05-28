$("document").ready(function () {

    //1.给剩余字数的显示绑定事件监听
    $("#text").bind("input propertychange", function (){
        let length = $("#text").val().length;
        let remindingLength = 300 - length;
        $("#length").html(remindingLength);
    });

    //2.给按钮绑定点击事件监听，使用Ajax方式将输入的数据传输到服务器
    $("#submit").click(function (){

        //2.1 从输入框中拿到文本内容
        let content = $("#text").val();

        //2.2 配置Ajax请求参数
        let config = {
            url: "AipNlpServlet",
            data: {method: "address", content:content},
            method: "GET",
            dataType: "json",
            success: function (json) {

                //2.2.1 解析Json字符串并得到分析结果
                let province = json["province"];
                let city = json["city"];
                let county = json["county"];
                let town = json["town"];
                let person = json["person"];
                let detail = json["detail"];
                let phonenum = json["phonenum"];

                $("#province").html(province);
                $("#city").html(city);
                $("#county").html(county);
                $("#town").html(town);
                $("#person").html(person);
                $("#detail").html(detail);
                $("#phonenum").html(phonenum);
            }
        }

        //2.3 发送Ajax到服务器端后台
        $.ajax(config);
    });
})