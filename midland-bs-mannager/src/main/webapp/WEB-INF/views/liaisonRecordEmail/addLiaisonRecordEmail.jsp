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
    <form action="${ctx}/rest/liaisonRecordEmail/add" method="post" id="dataForm">
        <ul class="userinfo updInfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <%@include file="../menu/area.jsp" %>
            <li><span>主指：</span>
                <select name="category" id="category" class="dropdown">
                    <c:forEach items="${categorys}" var="s">
                        <option value="${s.id}" <c:if test="${s.id == item.category}">selected="selected"</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>
            <li><span>联络人：</span>
                <input type="text" name="contactName" id="contactName" />
            </li>
            <li><span>邮箱：</span>
                <input type="text" name="email" id="email" onblur="checkEmail('email','email','');"/>
            </li>
            <li><span>手机：</span>
                <input type="text" name="phone" id="phone" onblur="checkPhone('phone','phone','');"/>
            </li>

            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    //保存数据
    function updateData() {
        if (checkEmail('email', 'email', '')&& checkPhone('phone', 'phone', '')) {


            var data = $("#dataForm").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/liaisonRecordEmail/add",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.state == 0) {
                        layer.msg("保存成功！！！", {icon: 1});
                        $('#save').removeAttr("onclick");
                        setTimeout(function () {
                            parent.layer.closeAll();
                            parent.$("#inquery").click();
                        }, 1000);

                    } else {
                        layer.msg("保存失败！", {icon: 2});
                    }
                },
                error: function (data) {
                    if (data.responseText != null) {
                        layer.msg(data.responseText, {icon: 2});
                    } else {
                        layer.msg("保存失败！", {icon: 2});
                    }
                }
            });
        }
    }

    //取消
    function closeWin() {
        parent.layer.closeAll();
    }
</script>
</body>
</html>