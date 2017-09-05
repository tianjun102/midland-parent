<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class = "table-responsive m40">
		<table class="table table-bordered table-add">
				<thead>
				<tr>
					<th style="width: 5%">序号</th>
					<th style="width: 40%">公告标题</th>
					<th style="width: 15%">公告类型</th>
					<th style="width: 20%">发布时间</th>
					<th style="width: 30%">操作</th>
				</tr>
			</thead>
			<tbody>
		<c:choose>
			<c:when test="${!empty requestScope.notices }">
				<c:forEach items="${requestScope.notices }" var="notice" varStatus="xh">
					<tr>
						<td>${xh.count }</td>
						<td><a style = "width:auto;height:auto;margin:auto;" href="${ctx}/rest/notice/findNotice?id=${notice.id}" target="contentF">${notice.title}</a></td>
						<td>
							<c:if test="${notice.msgType==1}">系统消息 </c:if> 
							<c:if test="${notice.msgType==2}">应用通知 </c:if>
						</td>
						
						
						<td>${notice.sendTime }</td>
						<td>
						<a href="${ctx}/rest/notice/findNotice?id=${notice.id}" target="contentF" class = "edit_img" title = "编辑"></a>
						
						<a target="contentF" class = "delete_img" title = "删除" onclick="isDelete(${notice.id});"></a>
						<c:if test="${notice.isTop!='1'}">
							<a target="contentF" class = "top_img" title = "置顶" onclick="topa(${notice.id });"></a>
						</c:if>
						<c:if test="${notice.isTop=='1'}">
							<a href="javascript:;" target="contentF" class = "decline_img" title = "降级" onclick="decline(${notice.id});"></a>
						</c:if>
						<a href="javascript:;" target="contentF" class = "release_img" title = "发布" onclick="send(${notice.id });"
						<c:if test="${notice.isSend=='1'}">style="visibility: hidden;"</c:if>></a>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="14">没有找到数据!</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
		</table>
		</div>
	
	<!-- 分页 -->
	<c:if test="${!empty paginator}"> 
		  <c:set var="paginator" value="${paginator}"/>
		  <c:set var="target" value="listDiv"/>
		  <%@include file="../layout/pagination.jsp"%>
	</c:if>
	
<script type="text/javascript">
	//删除
	function isDelete(noticeId){
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除公告'],
			  resize: false,
			  scrollbar:false,
			  content:
				  '<section class = "content" style = "border:none; height:100%;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除吗?</p>'+
				  '</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/notice/deleteNotice?noticeId="+noticeId,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("删除成功！",{icon:1});
								setTimeout(function(){$("#searchForm").submit();},1000);
							}
							if(xmlobj.flag==0){
								layer.msg("删除失败！！",{icon:7});
							}
							layer.close(index);
						} 
					});
				 }
				,success: function (layero) {
				      var btn = layero.find('.layui-layer-btn');
				      btn.css('text-align', 'center');
				  }
			  });
		
	}

	//发布
	function send(noticeId){
		
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['发布公告'],
			  resize: false,
			  scrollbar:false,
			  content:
				  '<section class = "content" style = "border:none; height:100%;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否确定发布公告?</p>'+
				  '</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/notice/sendNotice?noticeId="+noticeId,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("发布成功！",{icon:1});
							}
							if(xmlobj.flag==0){
								layer.msg("发布失败！！",{icon:7});
							}
							setTimeout(function(){$("#searchForm").submit();},1000);
							layer.close(index);
						} 
					});
				 }
				,success: function (layero) {
				      var btn = layero.find('.layui-layer-btn');
				      btn.css('text-align', 'center');
				  }
			  });
	}
	//置顶
	function topa(noticeId){
		$.ajax({ 
			type: "post", 
			url: "${ctx}/rest/notice/top?noticeId="+noticeId,
			cache:false, 
			async:false, // 此处必须同步
			dataType: "json",
			success: function(xmlobj){
				if(xmlobj.flag==0){
					layer.msg("操作失败！！",{icon:7});
				}else{
					layer.msg("置顶成功",{icon:1});
				}
			} 
		});
		
		setTimeout(function(){$("#searchForm").submit();},1000);
	}
	//取消置顶
	function decline(noticeId){
		$.ajax({ 
			type: "post", 
			url: "${ctx}/rest/notice/decline?noticeId="+noticeId,
			cache:false, 
			async:false, // 此处必须同步
			dataType: "json",
			success: function(xmlobj){
				if(xmlobj.flag==0){
					layer.msg("操作失败！！",{icon:7});
				}else{
					layer.msg("取消置顶成功！",{icon:1});
				}
			} 
		});
		
		setTimeout(function(){$("#searchForm").submit();},1000);
	}
</script>
</body>
</html>