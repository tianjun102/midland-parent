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
    <form action="${ctx}/rest/layoutMap/update" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <input type="hidden" name="hotHandId" id="hotHandId" value="${item.hotHandId}">
            <li><span>图片上传：</span>
               <input type="text" name="imgUrl" id="imgUrl" value="${item.imgUrl}"/>
            </li>
            <li><span>类型：</span>
                <select name="type" id="type" class="dropdown">
                    <option value="0" <c:if test="${item.type == 0}">selected</c:if> >一室</option>
                    <option value="1" <c:if test="${item.type == 1}">selected</c:if> >二室</option>
                    <option value="2" <c:if test="${item.type == 2}">selected</c:if> >三室</option>
                </select>
            </li>
            <li><span>标题：</span>
               <input type="text" name="title" id="title" value="${item.title}"/>
            </li>
            <li><span>朝向：</span>
                <select name="turned" id="turned" class="dropdown">
                    <c:forEach items="${turneds}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==entrust.turned}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>
            <li><span>面积：</span>
               <input type="text" name="acreage" id="acreage" value="${item.acreage}"/>
            </li>
            <li><span>均价：</span>
               <input type="text" name="avgPrice" id="avgPrice" value="${item.avgPrice}"/>
            </li>
            <li><span>在售套数：</span>
               <input type="text" name="saleingNum" id="saleingNum" value="${item.saleingNum}"/>
            </li>
            <li><span>价格：</span>
               <input type="text" name="price" id="price" value="${item.price}"/>
            </li>
            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">更新</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    //保存数据
    function updateData() {
        var data = $("#dataForm").serialize();
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/layoutMap/update",
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
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>