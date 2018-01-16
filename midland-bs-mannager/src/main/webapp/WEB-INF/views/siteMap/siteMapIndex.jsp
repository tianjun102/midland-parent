<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<%@include file="../layout/zTree.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
	<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
	<link rel="stylesheet" href="${ctx }/assets/css/common.css">
</head>
<body>
	
	
	<!--列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>系统管理>>网站地图管理列表</span>
				<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>
			</p>
		<form action="${ctx }/rest/siteMap/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<%--<li>
					<span style = "float:left;">城市：</span>
					<select name="cityId" id="cityId" style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
						<option value="">全部</option>
						<c:forEach items="${cityList}" var="city">
							<option value="${city.id}">${city.name}</option>
						</c:forEach>
					</select>
				</li>--%>
				<%@include file="../layout/sherchArea.jsp" %>
				<li>
					<span style = "float:left;">平台：</span>
					<select name="source" id="source" class="dropdown">
						<option value="">全部</option>
						<option value="0">网站</option>
						<option value="1">微站</option>
					</select>
				</li>
				<c:if test="${not empty isSuper}">
					<li>
						<span style = "float:left;">是否删除：</span>
						<select name="isDelete" id="isDelete" style="height: 28px;width: 100px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
							<option value="0">未删除</option>
							<option value="1">已删除</option>
						</select>
					</li>
				</c:if>
				<li><span>类别：</span><input style="width: 243px;" class="vipcate" id="vipcate"  name="vipcate" onclick="showTree()" readonly="readonly"/>
					<input name="cateId" type="hidden"/>
					<input name="noteType" type="hidden"/>

				</li>
				<li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
					<div class="zTreeDemoBackground left" style  = "position:absolute;left: -263px;top:29px;"   onblur="test(event)">
						<ul id="categoryTree" class="ztree" style  = "width:235px; height: 140px!important;"></ul>
					</div>
					<img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: -46px;margin-top: 40px;" onclick="hideTree()">
				</li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
				</li>
			</ul>
			</form>
			<input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;" onclick="batchDelete(1)" class = "public_btn bg1" type="submit"  value = "批量删除"/>
			<c:if test="${not empty isSuper}"><input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;" onclick="batchDelete(0)" class = "public_btn bg1" type="submit"  value = "批量恢复"/></c:if>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
        function toAddPage(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['450px','400px'],
                shadeClose: false, //点击遮罩关闭
                title:['新增'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/siteMap/to_add?type=4', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript">

        var setting = {
            check: {
                enable: true,
                chkboxType: { "Y": "sp", "N": "sp" }


            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick
            }
        };
        var catProNodes =[{id:0, pId:0,name:'分类',open:true,nocheck:true,iconSkin:"pIcon01"},${categoryData}];


        $(document).ready(function(){
            $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
        });

        function beforeClick(treeId, treeNode, clickFlag) {
            $("input[name='cateId']").val(treeNode.id);
            $("input[name='cateName']").val(treeNode.name);
            $("input[name='noteType']").val(treeNode.type);
            $("input[name='vipcate']").val(treeNode.name);
            $("#showDiv").hide();
        }

        function showTree(event){
            $("#showDiv").show();
        }

        function hideTree(event){
            $("#showDiv").hide();
        }

	</script>
</body>
</html>