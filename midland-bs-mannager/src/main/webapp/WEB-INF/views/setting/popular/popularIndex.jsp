<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../layout/tablib.jsp"%>
<%@include file="../../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">

<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">

</head>
<body>
	
	
	<!--用户列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>站点管理>>热门关注链接管理</span>
				<a class = "setup"  target="contentF" onclick="toAddPage()">添加</a>
			</p>
		<form action="${ctx}/rest/setting/showPopularList" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">

				<input type="hidden" name="type" value="${type}">
				<%@include file="../../layout/sherchArea.jsp" %>
				<li>
					<span style = "float:left;">平台：</span>
					<select name="source" id="source" class="dropdown">
						<option value="">全部</option>
						<option value="0">网站</option>
						<option value="1">微站</option>
					</select>
				</li>
				<c:if test="${not empty isSuper}">
					<li>
						<span style = "float:left;">是否删除：</span>
						<select name="isDelete" id="isDelete" class="dropdown">
							<option value>全部</option>
							<option value="0">未删除</option>
							<option value="1">已删除</option>
						</select>
					</li>
				</c:if>

				<li>
					<span style="float:left;">模块：</span>
					<select name="menuId" id="menuId" class="dropdown">
						<option value>全部</option>
						<option value="0">首页</option>
						<option value="1">新房</option>
						<option value="2">二手房</option>
						<option value="3">租房</option>
						<option value="4">写字楼</option>
						<option value="5">商铺</option>
						<option value="6">小区</option>
						<option value="7">经纪人</option>
						<option value="8">外销网</option>
						<option value="9">市场调究</option>
						<option value="10">资讯</option>
						<option value="11">问答</option>
					</select>
				</li>
				<li><span>分类：</span>
					<select name="cateName" id="cateName"
							style="height: 28px; width:120px;display: inline-block;border-radius: 4px;border: 1px solid #dbe2e6;">
						<option value="">请选择</option>
						<c:forEach items="${cateList}" var="s">
							<option value="${s.cateName}">
									${s.cateName}
							</option>
						</c:forEach>
					</select>
				</li>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			</form>
			<input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;" onclick="batchDelete(1)" class = "public_btn bg1" type="submit"  value = "批量删除"/>
			<c:if test="${not empty isSuper}"><input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;" onclick="batchDelete(0)" class = "public_btn bg1" type="submit"  value = "批量恢复"/></c:if>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
		$("#menuId").change(function () {
		    var data = "menuId="+$("#menuId").val();
		    debugger;
            $.ajax({
                type: "post",
                url: "${ctx}/rest/setting/getCate",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.state == 0) {
                        debugger;
                        var obj = data.data;
                        var html = "<option value=>请选择</option>";
                        for (var i = 0; i < obj.length; i++) {
                            html += "<option value=\"" + obj[i].cateName + "\">" + obj[i].cateName + "</option>";
                        }
                        $("#cateName").html(html);

                    } else {
                        layer.msg("新增失败！", {icon: 2});
                    }
                },
                error: function (data) {
                    if (data.responseText != null) {
                        layer.msg(data.responseText, {icon: 2});
                    } else {
                        layer.msg("操作失败！", {icon: 2});
                    }
                }

            });
        })




		 window.onload = function(){
             $('#searchForm').submit();
		}

		function toAddPage(){
			layer.open({
				type: 2,
				skin: 'layer-style',
				area: ['500px','500px'],
				shadeClose: false, //点击遮罩关闭
				title:['添加热门关注'],
				resize: false,
				scrollbar:false,
				content:['${ctx}/rest/setting/toAddPage', 'no']
			});
		}
	</script>
	<!-- 本页私有js -->

</body>
</html>