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
			<p class="detail-title">
				<span>系统管理>>评论列表</span>
			</p>
		<form action="${ctx }/rest/comment/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<input type="hidden" name="informationId" value="${comment.informationId}">
			<ul class = "userinfo row">
				<li><span>评论类容：</span>
					<input type="text" name="comment" id="comment" placeholder="请输入评论类容" /></li>
				</li>
				<li>
					<span>评论时间：</span><input class="Wdate half" id="time1"
											 onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
											 name="startTime"/> <em class="gang">-</em><input
						class="Wdate half"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
						id="time2" name="endTime"/>
				</li>
				<c:if test="${not empty isSuper}">
					<li>
						<span style = "float:left;">是否删除：</span>
						<select name="isDelete" id="isDelete" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
							<option value="0">未删除</option>
							<option value="1">已删除</option>
						</select>
					</li>
				</c:if>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			</form>
			<input style="width: 70px;height: 30px;line-height: 30px!important;" onclick="batchUpdate(0)" class = "public_btn bg1" type="submit"  value = "批量通过"/>
			<input style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;" onclick="batchUpdate(1)" class = "public_btn bg1" type="submit"  value = "批量拒绝"/>
			<input style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;" onclick="batchDelete(1)" class = "public_btn bg1" type="submit"  value = "批量删除"/>
			<c:if test="${not empty isSuper}"><input style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;" onclick="batchDelete(0)" class = "public_btn bg1" type="submit"  value = "批量恢复"/></c:if>
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
                content:['${ctx}/rest/comment/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>