<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>

	<style type="text/css">
		.content ul.userinfo > li {
			margin-left: 0;
			padding-top: 8px;
		}

		.content ul.userinfo li > span,
		.content ul.userinfo li:not(:last-child) input,
		.content ul.userinfo ._star,
		.content ul.userinfo .dropdown {
			height: 30px !important;
			line-height: 30px !important;
			vertical-align: middle;
		}

		.content ul.userinfo li input[type=checkbox] {
			float: none;
			width: auto !important;
			height: auto !important;
			margin-right: 3px;
		}

		.peitao {
			height: 30px;
			line-height: 30px;
		}

		.peitao > span {
			padding-right: 15px;
		}

		.peitao > span em {
			vertical-align: middle;
		}
		.content ul.userinfo li>span{
			float: left;
			display: inline-block;
			width: 90px;
			height: 38px;
			line-height: 38px;
			text-align: right;
			font-size: 14px;
			color: rgb( 102, 102, 102 );
		}
	</style>
</head>
<body>


<section class="content" style="border:none;">
	<form action="${ctx}/rest/setting/setTime" method="post" id="timeSett">
		<ul class="userinfo row">
			<li class="col-md-6"><span>预约告警时间：</span>
			<input type="text" name="appointmentWarn" id="appointmentWarn" onblur="notEmpty('appointmentWarn','appointmentWarn','');InitInput.setNumber(this,9,2,2)"><span>小时</span>
			</li>
			<li class="col-md-6"><span>预约关闭时间：</span>
			<input type="text" name="appointClose" id="appointClose" onblur="notEmpty('appointClose','appointClose','');InitInput.setNumber(this,9,2,2)"><span>小时</span>
			</li>
			<li class="col-md-6">
				<span></span>
				<a target="contentF" class="public_btn bg2" id="save" onclick="submitTime()">保存</a>
				<a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
			</li>
		</ul>

	</form>
</section>


<script type="text/javascript">
function submitTime() {
    var data = $("#timeSett").serialize();
    $.ajax({
        type: "post",
        url: "${ctx}/rest/setting/setTime",
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
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>