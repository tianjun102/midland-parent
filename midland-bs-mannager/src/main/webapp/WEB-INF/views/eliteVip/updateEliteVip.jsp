<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
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
    <form action="${ctx}/rest/eliteVip/update" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>type：</span>
               <input type="text" name="type" id="type" value="${item.type}"/>
            </li>
            <li><span>level：</span>
               <input type="text" name="level" id="level" value="${item.level}"/>
            </li>
            <li><span>cname：</span>
               <input type="text" name="cname" id="cname" value="${item.cname}"/>
            </li>
            <li><span>ename：</span>
               <input type="text" name="ename" id="ename" value="${item.ename}"/>
            </li>
            <li><span>address：</span>
               <input type="text" name="address" id="address" value="${item.address}"/>
            </li>
            <li><span>post：</span>
               <input type="text" name="post" id="post" value="${item.post}"/>
            </li>
            <li><span>imgUrl：</span>
               <input type="text" name="imgUrl" id="imgUrl" value="${item.imgUrl}"/>
            </li>
            <li><span>imgDesc：</span>
               <input type="text" name="imgDesc" id="imgDesc" value="${item.imgDesc}"/>
            </li>
            <li><span>isDelete：</span>
               <input type="text" name="isDelete" id="isDelete" value="${item.isDelete}"/>
            </li>
            <li><span>cateId：</span>
               <input type="text" name="cateId" id="cateId" value="${item.cateId}"/>
            </li>
            <li><span>cateName：</span>
               <input type="text" name="cateName" id="cateName" value="${item.cateName}"/>
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
            url: "${ctx}/rest/eliteVip/update",
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