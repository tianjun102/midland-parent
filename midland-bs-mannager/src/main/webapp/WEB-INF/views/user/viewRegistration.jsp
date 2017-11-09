<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<style type="text/css">
	.content ul.adminfo li>span {
		float: left;
		display: inline-block;
		width: 100px;
		height: 28px;
		line-height: 28px;
		text-align: right;
		font-size: 14px;
		color: rgb( 102, 102, 102 );
	}
	.content ul.adminfo li input[type=text] {
		float: left;
		width: 400px;
		height: 28px;
		line-height: 28px;
		border: 1px solid #dbe2e6;
		border-radius: 4px;
		text-indent: 10px;
		outline-color: #0099e0;
		/* margin-left: 4px; */
	}
</style>
</head>
<body>
<section class = "content" style = "border:none;">
	<p class = "detail-title">
		<span>实名信息</span>
		<%--<a class = "setup"  target="contentF" onclick="addEntrust()">新增</a>--%>
	</p>
<form action="${ctx}/rest/user/edit" method="post" id="userInfoForm">
	<ul class = "adminfo row">
		<input type="hidden" name="id" id="id" value="${user.id}">
		<input type="hidden" name="ph" id="ph" value="${user.phone}">
		<li><span>用户名：</span><input type="text" name="userName" id="userName" value="${user.username}" disabled="true"/></li>
		<li><span>身份证号：</span><input type="text" name="identification" id="identification" value="${user.identification}" disabled="true" maxlength="50"/></li>
		<li><span>邮箱：</span><input type="text" name="email" id="email" value="${user.email}" disabled="true"/></li>
		<li><span>真实姓名：</span><input type="text" name="actualName" id="actualName" value="${user.actualName}" disabled="true"/></li>
		<li><span>身份证图片：</span><img style="width: 145px;height: 145px;" src="${user.idcartImg}"  maxlength="50"/><img style="width: 145px;height: 145px;" src="${user.idcartImg1}"  maxlength="50"/></li>
		<c:if test="${user.auditStatus == 0}" >
		<li>
			<span></span>
			<a target="contentF" class = "public_btn bg2" id="save" onclick="auditsuccess(${user.id })">审核通过</a>
			<a style="margin-left: 20px" class = "public_btn bg3" id="cancel" onclick="auditRejectView(${user.id });">审核拒绝</a>
			<a target="contentF" class = "public_btn bg3" href="${ctx}/rest/user/userIndex">返回</a>
		</li>
		</c:if>
		<c:if test="${user.auditStatus != 0}">
			<li>
				<span></span>
				<a target="contentF" class = "public_btn bg3" href="${ctx}/rest/user/userIndex">返回</a>
			</li>
		</c:if>
	</ul>
			
	</form>	
</section>

<script type="text/javascript">


    function auditsuccess(userId){
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px', '200px'],
            shadeClose: false, //点击遮罩关闭
            title: ['编辑'],
            resize: false,
            content:
            '<section  style = "border:none; height:auto;">' +
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">实名认证通过?</p>' +
            '</section>',
            btn: ['确定', '取消'],
            yes: function (index) {
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/user/audit?id="+userId+'&auditStatus=2',
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(obj){
                        if(obj.state==0){
                            layer.msg("审核成功！",{icon:1});
                            window.open("${ctx}/rest/user/userIndex","contentF");
                        }
                        if(obj.state==-1){
                            layer.msg("审核失败！！",{icon:7});
                        }

                    }
                });


            }
            , success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
            }
        });


    }

    function auditRejectView(userId){
        layer.open({
            type: 1,
            title: [''],
            shade: 0.3,
            area: ['450px', '400px'],
            content:'<section  style="border:none; padding-left:20px;">' +
			'<form action="${ctx}/rest/user/audit" method="post" id="adduserInfoForm">' +
			'<ul class = "userinfo row"><li><span>拒绝原因：</span>' +
			'<textarea name="auditRemark" id="auditRemark"  style="width:260px;height:' +
			' 150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" >' +
			'</textarea></li><li style = "padding-top:30px;"><a target="contentF" class = "public_btn bg2" ' +
			'style="margin-left: 70px;" id="save" onclick="rejust(${user.id })">拒绝</a>' +
			'<a style="margin-left: 20px" class = "public_btn bg3" id="cancel" ' +
			'onclick="closeWin(${user.id });">取消</a></li></ul></form>'
        });
    }


    //取消
    function closeWin(id){
        window.open("${ctx}/rest/user/findUser?userId="+id+"&flag=1","contentF");
    }



    function rejust(userId){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/user/audit?id="+userId+'&auditStatus=3'+'&auditRemark='+$('#auditRemark').val(),
            cache:false,
            async:false, // 此处必须同步
            dataType: "json",
            success: function(obj){
                if(obj.state==0){
                    layer.msg("成功！",{icon:5});
                    parent.window.location.reload();
                    parent.layer.closeAll();
                }
                if(obj.state==-1){
                    layer.msg("失败！！",{icon:7});
                }

            }
        });
    }

</script>	
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>