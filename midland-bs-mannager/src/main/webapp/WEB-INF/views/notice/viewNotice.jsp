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

<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<span>查看公告</span>
				<a class = "setup" href="${ctx}/rest/notice/noticeShowIndex" target="contentF">返回</a>
			</p>
		<form action="" method="post" >
			<input type="hidden" name="id" id="id" value="${notice.id}">
			<ul class = "adminfo row">
				<li><span>公告标题：</span><label style = "width: 500px;" name="title" id="title" >${notice.title }</label></li>
				<li><span>发布时间：</span><label name="sendTime" >${notice.sendTime }</label></li>
				
				<li><span>公告类型：</span><label name="title" id="title" >
				<c:if test="${notice.msgType==1}">系统消息</c:if>
				<c:if test="${notice.msgType==2}">应用通知</c:if>
				</label></li>
				
				<%-- <li>
					<span>公告类型：</span>
					<select name="msgType" id="msgType" class="dropdown" disabled="disabled">
						<option value=""
						<c:if test="${notice.msgType==-1}">selected="selected"</c:if>>请选择</option>
					<option value="1"
						<c:if test="${notice.msgType==1}">selected="selected"</c:if>>系统消息</option>
					<option value="2"
						<c:if test="${notice.msgType==2}">selected="selected"</c:if>>应用通知</option>
					</select>
				</li> --%>
				<li style = "overflow:hidden;">
					<span style = "float:left;">公告内容：</span>
					<textarea id = "notice_content" name="content" style=" float:left; width:500px; height:230px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" rows="" cols=""  readonly="readonly">${notice.content }</textarea>
				</li>
			</ul>
		</form>
		</section>
</div>
	<script type="text/javascript">
	function submint(isSend){
		
		var id = $("input[name='id']").val();
		var title = $("input[name='title']").val();
		var sendTime = $("input[name='sendTime']").val();
		var msgType = $("#msgType option:selected").val();
		var content =$("#notice_content").val();

		if(title==""||sendTime==""||msgType==""||content==""){
			layer.msg("还有未输入的选项，请完成后再提交！",{icon:7});
			return;
		}
		
		$.ajax({ 
				type: "post", 
				url: "${ctx}/rest/notice/editNotice", 
				async:false, // 此处必须同步
				dataType: "json",
				data:{"id":id,"title":title,"sendTime":sendTime,"msgType":msgType,"content":content,"isSend":isSend},
				success: function(data){ 
					if(data.flag==1){
						layer.msg("保存成功！",{icon:1});
						$("#save").removeAttr("onclick");
						$("#release").removeAttr("onclick");
					setTimeout(function(){window.open("${ctx}/rest/notice/noticeIndex","contentF")},1000);
					}else{
						layer.msg("保存失败！",{icon:2});
					}
				},
				error: function(){
					layer.msg("保存失败！",{icon:2});
				}
			});
	}
	
	
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery-ui.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>
</body>
</html>