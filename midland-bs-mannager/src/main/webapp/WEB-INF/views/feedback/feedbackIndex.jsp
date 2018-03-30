<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	
	
	<!--列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>系统设置>>反馈管理列表</span>
				<%--<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>--%>
			</p>
		<form action="${ctx }/rest/feedback/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li>
					<span>反馈时间：</span><input class="Wdate half" id="time1"
											onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
											name="startTime"/> <em class="gang">-</em><input
						class="Wdate half"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
						id="time2" name="endTime"/>
				</li>
				<li><span>平台：</span>
					<select name="source" id="source" class="dropdown">
						<option value="">全部</option>
						<c:forEach items="${sources}" var="s1">
							<option value="${s1.id}">
									${s1.name}
							</option>
						</c:forEach>
					</select>
				</li>
				<li><span>反馈类型：</span>
					<select name="type" id="type" class="dropdown">
						<option value="">全部</option>
						<option value="0">咨询</option>
						<option value="1">建议</option>
						<option value="2">投诉</option>
						<option value="3">其它</option>
					</select>
				</li>
				<li><span>联系方式：</span>
					<input type="text" style="width: 180px!important;" name="phone" id="phone" placeholder="请输入手机号码或邮箱" /></li>
				</li>
				<li><span>用户名：</span>
					<input type="text" style="width: 150px!important;" name="nickName" id="nickName" placeholder="请输入用户名" /></li>
				</li>
				<%--<li><span>反馈类容：</span>--%>
					<%--<input type="text" style="width: 150px!important;" name="feedbackContent" id="feedbackContent" placeholder="请输入反馈类容" /></li>--%>
				<%--</li>--%>
				<c:if test="${not empty isSuper}">
					<li>
						<span style = "float:left;">是否删除：</span>
						<select name="isDelete" id="isDelete" style="height: 28px;width: 100px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
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
			<input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;" onclick="window.open('${ctx}/rest/feedback/export','contentF')" class = "public_btn bg1" type="submit"  value = "导出"/>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
        function toAddPage(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['500px','700px'],
                shadeClose: false, //点击遮罩关闭
                title:['新增'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/feedback/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

</body>
</html>