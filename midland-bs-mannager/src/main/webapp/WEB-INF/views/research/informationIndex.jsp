<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<%@include file="../layout/zTree.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">

</head>
<body>
	
	
	<!--委托列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>市场调究管理>>市场调究列表</span>
				<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>
				<a style="margin-right: 10px;" class = "setup"  target="contentF" href="${ctx}/rest/category/index?type=0">分类管理</a>
			</p>
		<form action="${ctx }/rest/research/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<input type="hidden" name="articeType" id="articeType" value="0"/>
				<%@include file="../layout/sherchArea.jsp" %>
				<li><span>平台：</span>
					<select name="source" id="source" class="dropdown">
						<option value="0">网站</option>
						<option value="1">微站</option>
					</select>
				</li>


				<li>
					<span style = "float:left;">状态：</span>
					<select name="status" id="status"  class="dropdown">
						<option value="">全部</option>
						<option value="0">上架</option>
						<option value="1">下架</option>
					</select>
				</li>
				<li><span>分类：</span>
					<select name="cateId" id="id"
							style="height: 28px; width:120px;display: inline-block;border-radius: 4px;border: 1px solid #dbe2e6;">
						<option value="">请选择</option>
						<c:forEach items="${cateList}" var="s">
							<option value="${s.cateId}">
									${s.cateName}
							</option>
						</c:forEach>
					</select>
				</li>

				<li><span>标题：</span><input type="text" name="title" id="title" placeholder="请输入标题" /></li>
				<li>
					<span>发布时间：</span><input class="Wdate half" id="time1"
											 onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
											 name="startTime"/> <em class="gang">-</em><input
						class="Wdate half"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
						id="time2" name="endTime"/>
				</li>
				<c:if test="${not empty isSuper}">
					<li>
						<span style = "float:left;">是否删除：</span>
						<select name="isDelete" id="isDelete" style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
							<option value="0">未删除</option>
							<option value="1">已删除</option>
						</select>
					</li>
				</c:if>

				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			</form>
			<input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;" onclick="batchDelete(1)" class = "public_btn bg1" type="submit"  value = "批量删除"/>
			<c:if test="${not empty isSuper}"><input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;" onclick="batchDelete(0)" class = "public_btn bg1" type="submit"  value = "批量恢复"/></c:if>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">

		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<!-- 本页私有js -->
	
	<script type="text/javascript">


        $("#source").change(function () {
            getCate();
        })
        $("#status").change(function () {
            getCate();
        })
        $("#citys").change(function () {
            getCate();
        })
        $("#isDelete").change(function () {
            getCate();
        })

        function getCate() {
            var data = "articeType=" + $("#articeType").val() + "&cityId=" + $("#cityId").val() +
                "&isDelete=" + $("#isDelete").val() + "&source=" + $("#source").val() + "&status=" + $("#status").val();
			debugger;
            $.ajax({
                type: "post",
                url: "${ctx}/rest/information/getCate",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.state == 0) {
                        debugger;
                        var obj = data.data;
                        var html = "<option value=>请选择</option>";
                        for (var i = 0; i < obj.length; i++) {
                            html += "<option value=\"" + obj[i].cateId + "\">" + obj[i].cateName + "</option>";
                        }
                        $("#id").html(html);

                    } else {
                        layer.msg("获取资讯分类失败！", {icon: 2});
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
        }





        function toAddPage() {
            layer.open({
                type: 2,
                title: ['新增市场调究'],
                shade: 0.3,
                area: ['100%', '100%'],
                content: ['${ctx}/rest/research/to_add' , 'yes']
            });
        }


	</script>
</body>
</html>