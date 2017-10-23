<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.layui-layer{
			top:260px!important;
		}
	</style>
</head>
<body>


<!--列表界面-->
<div class="box">
	<section class = "content">
		<p class = "detail-title">
				<span>系统设置>>404页面设置</span>
		</p>
		<form id="dataForm" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="id" value="${items.id}" >
			<ul class = "adminfo row">

				<li id="textArea" style="display: block;">
					<textarea style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;"  id="myEditor" rows="" cols="" name="page404">${items.page404}</textarea>
				</li>
			</ul>
			<ul class = "adminfo row">
				<li>
					<span></span>
					<a onclick="saveData();" target="contentF" class = "public_btn bg2">保存</a>
					<a style="margin-left: 20px" href="${ctx}/rest//errorPage/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
				</li>
			</ul>
		</form>
	</section>




</div>


<script type="text/javascript">
    //保存数据
    UE.getEditor('myEditor');
    function saveData() {
        var data = $("#dataForm").serialize();
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/errorPage/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");

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
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>