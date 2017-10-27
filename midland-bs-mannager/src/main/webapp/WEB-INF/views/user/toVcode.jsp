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
            <input type="hidden" name="id" id="id" value="">
            <li id="id1"><span>手机号码：</span>
                <input type="text" name="phone" id="phone" />
            </li>
            <li id="id2"><span>验证码：</span>
               <input type="text" name="vCode" id="vCode" />  <input style="width: 110px!important;" id="btnSendCode" type="button" value="发送验证码" onclick="sendMessage()" />
            </li>
            <li id="id3">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="checkVcode()">下一步</a>
                <%--<a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>--%>
            </li>

            <li id="id4" style="display: none"><span>新密码：</span><input style="width: 300px;" type="password" name="newPwd" id="newPwd" onblur="checkNewPwd();"/>
                <label class="_star" id="checkNewPwdMsg">*</label>
            </li>
            <li id="id5" style="display: none"><span>确认密码：</span><input style="width: 300px;" type="password" name="confirmPwd" id="confirmPwd" onblur="checkConfirmPwd();"/>
                <label class="_star" id="checkConfirmPwdMsg">*</label>
            </li>
            <li id="id6" style="display: none">
                <span></span>
                <a target="_top" class = "public_btn bg2" id="confirm" onclick="saveData();">确认</a>
            </li>

        </ul>

    </form>
</section>

<script type="text/javascript">

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
            dataType: "json", //数据格式:JSON
            url: '${ctx}/rest/user/vcode/sendSms?phone='+phone, //目标地址
            data: "",
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg("发送失败，请检查手机号码！",{icon:2});
            },
            success: function (msg){
                debugger;
                if(msg.flag==1){
                    layer.msg("发送成功！",{icon:1});
                    $("#id").val(msg.id);
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
            dataType: "json", //数据格式:JSON
            url: '${ctx}/rest/user/vcode/checkVcode?phone='+phone+"&vcode="+vCode, //目标地址
            data: "" ,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg("发送失败，请检查手机号码！",{icon:2});
            },
            success: function (msg){
                if(msg.flag==1){
                    $("#id1").show();
                    $("#id2").show();
                    $("#id3").show();
                    layer.msg("验证码正确！",{icon:1});
                }else {
                    $("#id1").hide();
                    $("#id2").hide();
                    $("#id3").hide();
                    layer.msg("验证码错误！",{icon:2});
                }
            }
        });
    }



    function saveData() {

        if (checkNewPwd() && checkConfirmPwd()) {
            var id = $("#id").val();
            var newPwd=$("#newPwd").val();
            $.ajax({
                type : "post",
                url : "${ctx}/rest/user/vcode/updatePwd?newPwd="+newPwd+"id="+id,
                async : false, // 此处必须同步
                dataType : "json",
                data : "",
                success : function(data) {
                    if (data.flag == 1) {
                        layer.msg("修改成功,请重新登陆！",{icon:1});
                        setTimeout(function(){closeWin();},2000);
                    } else {
                        layer.msg("修改失败！",{icon:7});
                    }
                }
            });

        }
    }


    function checkNewPwd() {
        var reg = /^[a-zA-Z0-9!@#$%^&*()_+|{}?><\.\-\]\\[\/]*$/;//密码格式校验

        var regex1 = /[0-9]+/;
        var regex2 = /[a-zA-Z]+/;
        var regex3 = /[!@#$%^&*()_+|{}?><\.\-\]\\[\/]+$/;

        var flag = 0;

        $("#checkNewPwdMsg").text("*");
        var oldPwd = $("#oldPwd").val();
        var newPwd = $("#newPwd").val();
        if (newPwd == "" || newPwd == null) {
            $("#checkNewPwdMsg").text("新密码不能为空!");
            return false;
        } else if (newPwd.length <8) {
            $("#checkNewPwdMsg").text("密码不能小于8位数！");
            return false;
        } else if (newPwd.length > 20) {
            $("#checkNewPwdMsg").text("密码不能大于20位数！");
            return false;
        } else if (!reg.test(newPwd)) {
            $("#checkNewPwdMsg").text("请输入正确格式的密码!");
            return false;
        }

        if (regex1.test(newPwd)) {
            flag++;
        }
        if (regex2.test(newPwd)) {
            flag++;
        }
        if (regex3.test(newPwd)) {
            flag++;
        }

        if (flag < 2) {
            $("#checkNewPwdMsg").text("请使用8-20位字母/数字/符号的至少两种组合的密码!");
            return false;
        }
        return true;
    }

    function checkConfirmPwd() {
        $("#checkConfirmPwdMsg").text("*");
        var newPwd1 = $("#newPwd").val();
        var confirmPwd = $("#confirmPwd").val();
        if (confirmPwd == "" || confirmPwd == null) {
            $("#checkConfirmPwdMsg").text("确认密码不能为空!");
            return false;
        }
        if (newPwd1 != confirmPwd) {
            $("#checkConfirmPwdMsg").text("两次输入密码不一致!");
            return false;
        }
        return true;
    }


</script>
</body>
</html>