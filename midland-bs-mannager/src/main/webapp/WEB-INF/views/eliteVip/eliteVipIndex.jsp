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
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
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
            $("#showDiv").hide();
        }

        function showTree(event){
            $("#showDiv").show();
        }

        function hideTree(event){
            $("#showDiv").hide();
        }

	</script>
</head>
<body>
	
	
	<!--列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>精英会>>精英会会员管理</span>
				<a class = "setup"  target="contentF" href="${ctx}/rest/eliteVip/to_add">新增</a>
			</p>
		<form action="${ctx }/rest/eliteVip/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<%--<li><span>select：</span>
					<select name="cityId" id="cityId" class="dropdown">
						<option value="" >全部</option>
						<c:forEach items="${citys}" var="item">
						<option value="${item.id}" >${item.name}</option>
						</c:forEach>
					</select>
				</li>--%>
				<li><span>会员分类：</span><input class="vipcate" name="cateName" onclick="showTree()" readonly="readonly"/>
				</li>
				<li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
					<div class="zTreeDemoBackground left" style  = "position:absolute;top: -10px;"   onblur="test(event)">
						<ul id="categoryTree" class="ztree" style  = "width:235px; height: 140px!important;margin-left: -350px!important;margin-top: 70px!important;"></ul>
					</div>
					<img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: 290px;left:-63px;top: 60px;" onclick="hideTree()">
				</li>
				<li><span>中文名：</span>
					<input type="text" name="cname" id="cname" placeholder="请输入会员中文名称"/></li>
				</li>
				<li><span>英文名：</span>
					<input type="text" name="ename" id="ename" placeholder="请输入会员英文名称" /></li>
				</li>
				<li><span>会员级别：</span>
					<input type="text" name="level" id="level" placeholder="请输入会员级别" /></li>
				</li>
				<li><span>所属地区：</span>
					<input type="text" name="address" id="address" placeholder="请输入会员所属地区" /></li>
				</li>
				<c:if test="${not empty isSuper}">
					<li>
						<span style = "float:left;">是否删除：</span>
						<select name="isDelete" id="isDelete" style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
							<option value="0">未删除</option>
							<option value="1">已删除</option>
						</select>
					</li>
				</c:if>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
				</li>
			</ul>
			</form>
			<input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;" onclick="batchDelete(1)" class = "public_btn bg1" type="submit"  value = "批量删除"/>
			<c:if test="${not empty isSuper}"><input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;" onclick="batchDelete(0)" class = "public_btn bg1" type="submit"  value = "批量恢复"/></c:if>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
        function toAddPage(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['400px;','500px;'],
                shadeClose: false, //点击遮罩关闭
                title:['新增'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/eliteVip/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>