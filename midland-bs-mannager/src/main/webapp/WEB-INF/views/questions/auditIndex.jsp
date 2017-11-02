<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
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
	
	
	<!--预约看房重新分配经纪人-->
	<div class="box"> 
		<section class = "content">
			<ul class = "userinfo row">

				<li>
					<span>提问人：</span><span >${questions.questionName}</span>
					<span>手机号码：</span><span >${questions.questionPhone}</span>
				</li>


			</ul>
			<ul>
				<li>
					<span>提问时间：</span><span >${questions.questionTime}</span>
				</li>
				<li>
					<span>提问主题：</span><span >${questions.questionsTitle}</span>
				</li>
				<li>
					<span>提问内容：</span><span >${questions.questionsArea}</span>
				</li>

			</ul>
			<form action="${ctx}/rest/questions/update" method="post" id="auditInfoForm">
				<ul class="userinfo row">
					<input type="hidden" id="id" name="id" value="${questions.id}"/>
					<li><span>备注：</span>
						<textarea name="auditRemark" id="auditRemark"
								  style="width:260px;height:150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
					</li>

				</ul>
				<ul>

					<li style="padding-top:30px;">
						<a target="contentF" class="public_btn bg2" style="margin-left: 70px;"
						   onclick="rejust()">拒绝</a>
						<a target="contentF" class="public_btn bg2" style="margin-left: 70px;"
						   onclick="pass()">通过</a>
						<a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
					</li>

				</ul>
			</form>

		</section>
	</div>
	
	
	<script type="text/javascript">
		 window.onload = function(){
             $('#searchForm').submit();
		}


		function rejust(){
            var data = $("#auditInfoForm").serialize()+"&status="+2;
            update(data);

        }
         function pass(){
             var data = $("#auditInfoForm").serialize()+"&status="+1;
             update(data);
         }
         function update(data){
             $.ajax({
                 type: "post",
                 url: "${ctx}/rest/questions/update",
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


	</script>
	<!-- 本页私有js -->

</body>
</html>