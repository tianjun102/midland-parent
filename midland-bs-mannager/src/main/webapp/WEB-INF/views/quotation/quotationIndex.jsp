<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/excel_read',
				'method':'get',
                'multi': false,// 是否支持多个文件上传
                'onUploadStart' : function(file) {
                    $("#file_upload").uploadify("settings", "formData", {
                        'cityId':$('#excelCityId').val(),
						'cityName':$('#excelCityName').val()

                    });

                },
                'onUploadSuccess': function (file, data, response) {

                },
                'onQueueComplete': function (queueData) {
                    if (queueData.uploadsSuccessful < 1) {
                        alert('文件上传失败');
                    }
                }

                // Your options here
            });
        })
	</script>
</head>
<body>


	<!--列表界面-->
	<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>
			</p>


		<form action="${ctx }/rest/quotation/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<input type="hidden" name="isNew" value="${isNew}"/>
			<ul class = "userinfo row">
				<li><span>城市：</span>
					<select  name="cityId" id="cityId" class="dropdown">
						<option value="" >全部</option>
						<c:forEach items="${citys}" var="item">
						<option value="${item.id}" >${item.name}</option>
						</c:forEach>
					</select>
				</li>
				<li><span>区域：</span>
					<input type="text" name="phone" id="phone" placeholder="请输入手机号码" /></li>
				</li>
				<li><span>类型：</span>
					<select name="type" id="type" class="dropdown">
						<option value="" >全部</option>
						<c:forEach items="${types}" var="type">
							<option value="${type.id}" >${type.name}</option>
						</c:forEach>
					</select>

				</li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
				</li>
			</ul>
			</form>
			<div id="listDiv"></div>
			<a class="edit_img" target="contentF" href="${ctx}/rest/quotation/showTooltip">生成预览</a>
		</section>
	</div>
	<li><span>城市：</span>
		<input type="text" id="excelCityName" value="">
		<select onchange="setCityName()" id="excelCityId" class="dropdown">
			<option value="" >请选择</option>
			<c:forEach items="${citys}" var="item">
				<option value="${item.id}" >${item.name}</option>
			</c:forEach>
		</select>
	</li>
	<li><span>图片上传：</span>
		<div style="width: 250px;float: left;">
			<img style="margin-bottom: 10px;max-width:80px;max-height:80px" id="iconImg1" >
			<input type="file" name="file_upload" id="file_upload"/>
		</div>
	</li>

	<script type="text/javascript">

		function setCityName() {
            if($("#excelCityId").find("option:selected").text()=="请选择"){
                $("#excelCityName").attr("value","");

            }else{
                var str = $("#excelCityId").find("option:selected").text();
                var sta =unicode(str);
                $("#excelCityName").attr("value",sta);
            }
        }

            function toAddPage(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['500px','700px'],
                shadeClose: false, //点击遮罩关闭
                title:['新增'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/quotation/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}

        function unicode(str){
            var value='';
            for (var i = 0; i < str.length; i++) {
                value += '\\u' + left_zero_4(parseInt(str.charCodeAt(i)).toString(16));
            }
            return value;
        }
        function left_zero_4(str) {
            if (str != null && str != '' && str != 'undefined') {
                if (str.length == 2) {
                    return '00' + str;
                }
            }
            return str;
        }

	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>