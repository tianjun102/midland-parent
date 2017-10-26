<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/smsConfig/add" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>手机号码：</span>
                <input type="text" name="phone" id="phone" />
            </li>
            <li><span>验证码：</span>
               <input type="text" name="vCode" id="vCode" />  <input style="width: 110px!important;" id="btnSendCode" type="button" value="发送验证码" onclick="sendMessage()" />
            </li>
            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="checkVcode()">下一步</a>
                <%--<a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>--%>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    //保存数据
    /*function updateData() {
        var data = $("#dataForm").serialize();
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/smsConfig/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("保存失败！", {icon: 2});
            }
        });
    }*/

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }


    var InterValObj; //timer变量，控制时间
    var count = 20; //间隔函数，1秒执行
    var curCount;//当前剩余秒数

    function sendMessage() {
        var phone = $("#phone").val();
        curCount = count;
        //设置button效果，开始计时
        $("#btnSendCode").attr("disabled", "true");
        $("#btnSendCode").val("还剩" + curCount + "秒");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        //向后台发送处理数据
        $.ajax({
            type: "POST", //用POST方式传输
            dataType: "JSON", //数据格式:JSON
            url: '${ctx}/rest/user/vcode/sendSms?phone='+phone, //目标地址
            data: "",
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg("发送失败，请检查手机号码！",{icon:2});
            },
            success: function (msg){
                debugger;
                if(msg.flag==1){
                    layer.msg("发送成功！",{icon:1});
                }else {
                    layer.msg("发送失败，请检查手机号码！",{icon:2});
                }
            }
        });
    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#btnSendCode").removeAttr("disabled");//启用按钮
            $("#btnSendCode").val("重新发送验证码");
        }
        else {
            curCount--;
            $("#btnSendCode").val("还剩" + curCount + "秒");
        }
    }

    function checkVcode(){
        var phone = $("#phone").val();
        var vCode = $("#vCode").val();
        $.ajax({
            type: "POST", //用POST方式传输
            dataType: "text", //数据格式:JSON
            url: '${ctx}/rest/user/vcode/checkVcode?phone='+phone+"&vcode="+vCode, //目标地址
            data: "" ,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg("发送失败，请检查手机号码！",{icon:2});
            },
            success: function (msg){
                if(msg.flag==1){
                    layer.msg("发送成功！",{icon:1});
                }else {
                    layer.msg("发送失败，请检查手机号码！",{icon:2});
                }
            }
        });
    }


</script>
</body>
</html>