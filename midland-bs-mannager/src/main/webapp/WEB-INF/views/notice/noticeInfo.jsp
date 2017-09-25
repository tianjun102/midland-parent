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
				<span>编辑公告</span>
			</p>
		<form action="${ctx}/rest/notice/editNotice" method="post" >
			<input type="hidden" name="id" id="id" value="${notice.id}">
			<ul class = "adminfo row">
				<li><span>公告标题：</span><input style = "width: 500px;" type="text" name="title" id="title" value="${notice.title }" maxlength="255"/><span class="_star">*</span></li>
				<c:if test="${notice.isSend == '1'}">
				<li><span>发布时间：</span><input type="text" name="sendTime" value="${notice.sendTime }" disabled="disabled"/></li>
				</c:if>
				<li>
					<span>公告类型：</span>
					<select name="msgType" id="msgType" class="dropdown">
						<option value=""
						<c:if test="${notice.msgType==-1}">selected="selected"</c:if>>请选择</option>
					<option value="1"
						<c:if test="${notice.msgType==1}">selected="selected"</c:if>>系统消息</option>
					<option value="2"
						<c:if test="${notice.msgType==2}">selected="selected"</c:if>>应用通知</option>
					</select>
				</li>
				<li style = "overflow:hidden;">
					<span style = "float:left;">公告内容：</span>
					<textarea id = "notice_content" name="content" style="float:left; width:500px;height:230px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" rows="" cols="">${notice.content }</textarea><span class="_star">*</span>
				</li>
				<li>
					<span></span>
					<a href="#" target="contentF" class = "public_btn bg2" id="save" onclick="submint(0)">保存</a>
					<c:if test="${notice.isSend=='0'}">
					<a href="#" target="contentF" class = "public_btn bg2" id="release" onclick="submint(1)">发布</a>
					</c:if>
					<a href="${ctx}/rest/notice/noticeIndex" target="contentF" class = "public_btn bg3" id="cancel">取消</a>
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
		if(title.trim()==""||sendTime==""||msgType==""||content.trim()==""){
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