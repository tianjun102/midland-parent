<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>美联物业 - 关于平台</title>
	<link rel="stylesheet" href="${ctx }/assets/css/common.css">
	<style type="text/css">
		.dropdown {
			height: 38px;
			line-height: 38px;
			width: 200px !important;
			display: inline-table;
			border-width: 1px !important;
			border-style: solid !important;
			border-top-style: solid;
			border-right-style: solid;
			border-bottom-style: solid;
			border-left-style: solid;
			border-color: rgb(219, 226, 230) !important;
			border-image: initial !important;
		}

	</style>

</head>
<body>
<!--关于平台界面-->
<section class="content" style="width:auto;">
	<form action="${ctx }/rest/quotation/toolsTip" method="POST" id="searchForm"
		  onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
		<input type="hidden" name="url" id="url" value="${url}"/>
		<input type="hidden" name="showType" id="showType" value="${showType}"/>
		<ul class="userinfo row">
			<li>
				<%@include file="../quotation/dist.jsp" %>
			</li>
			<li>
				<span>类型：</span>
				<select name="type" onchange="typeChange()" id="type" class="dropdown">
					<c:forEach items="${types}" var="type">
						<option value="${type.id}">${type.name}</option>
					</c:forEach>
				</select>
			</li>
			<li style="display: block" id="acreageShow">
				<span style="width:90px">面积范围：</span>
				<select name="houseAcreage" id="houseAcreage"  class="dropdown">
					<c:forEach items="${acreageRange}" var="s">
						<option value="${s.id}">${s.name}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<span style="width:90px">环比类型：</span>
				<select name="field" onchange="fieldChange()" id="field" style="height: 36px;">
					<option value="0">成交套数</option>
					<option value="1">成交面积</option>
					<option value="2">成交均价</option>
					<option value="3">成交金额</option>
				</select>
			</li>
			<li><input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/></li>
		</ul>


	</form>
	<div id="listDiv"></div>

</section>
</body>
<script type="text/javascript">
    window.onload = function () {
        $('#searchForm').submit();
    }
    function typeChange() {
		var j = $("#type").val();
        if (j==0){
            $("#field").html("<option value='0' selected='selected' >成交套数</option>");
            $("#field").append("<option value='1'>成交面积</option>");
            $("#field").append("<option value='2'>成交均价</option>");
            $("#field").append("<option value='4'>可售套数</option>");
            $("#field").append("<option value='5'>可售面积</option>");
			$("#acreageShow").css('display','none');
			$("#houseAcreage").val("");

		}else if (j==1){
            $("#field").html("<option value='0' selected='selected'>成交套数</option>");
            $("#field").append("<option value='1'>成交面积</option>");
            $("#field").append("<option value='2'>成交均价</option>");
            $("#field").append("<option value='3'>成交金额</option>");
            $("#acreageShow").css('display','block');
		}else if (j==2){
            $("#field").html("<option value='0' selected='selected'>成交套数</option>");
            $("#field").append("<option value='1'>成交面积</option>");
            $("#field").append("<option value='2'>成交均价</option>");
            $("#field").append("<option value='4'>可售套数</option>");
            $("#field").append("<option value='5'>可售面积</option>");
            $("#houseAcreage").val("");
            $("#acreageShow").css('display','none');
        }else if (j==3){
            $("#field").html("<option value='0' selected='selected'>成交套数</option>");
            $("#field").append("<option value='1'>成交面积</option>");
            $("#field").append("<option value='2'>成交均价</option>");
            $("#field").append("<option value='4'>可售套数</option>");
            $("#field").append("<option value='5'>可售面积</option>");
            $("#houseAcreage").val("");
            $("#acreageShow").css('display','none');
        }
    }

    function fieldChange() {
        var i= $("#field").val();
        if(i==0){//成交套数
            $("#url").val("dealNumContent");
            $("#showType").val("0");
        }else if(i==1){//成交面积
            $("#url").val("dealAcreageContent");
            $("#showType").val("1");

        }else if(i==2){//成交均价
            $("#url").val("dealAvgPriceContent");
            $("#showType").val("2");

        }else if (i==3){//成交金额
            $("#url").val("turnVolumeContent");
            $("#showType").val("3");

        }else if (i==4){//可售套数
            $("#url").val("soldNumContent");
            $("#showType").val("4");

        }
        else{//可售面积
            $("#url").val("soldAcreageContent");
            $("#showType").val("5");
        }
    }
</script>
</html>

