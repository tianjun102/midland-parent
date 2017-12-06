<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<style type="text/css">
			.pagination>li>a:focus, .pagination>li>a:hover, .pagination>li>span:focus, .pagination>li>span:hover {
				background-color:#a9b3c0!important;
				color:#ffffff!important;
			}
			.pagination>li>a, .pagination>li>span {
				color:#666666!important;
			}
			.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover{
				color:#ffffff!important;
				background-color:#18aec9!important;
			}
     </style>
	<title>智者汇 - 活动管理</title>
</head>
	<body>
	
	<!--活动管理界面-->

			<div class = "table-responsive m40">
				<table class="table table-bordered table-add">
				<col width="7%">
				<col width="4%" >
				<col width="7%" >
				<col width="6%" >
				<col width="6%" >
				<col width="6%" >
				<col width="6%" >
				<col width="6%" >
				<col width="12%" >
				<col width="12%" >
				<col width="5%" >
				<col width="33%" >
	 				<thead>
						<tr>
							<th><a target="contentF" href="javascript:void (0)" onclick="checkall()" >全选</a> / <a target="contentF" href="javascript:void (0)" onclick="delcheckall()" >取消</a></th>
							<th>编号</th>
							<th>轮播图</th>
							<th>状态</th>
							<th>城市</th>
							<th>平台</th>
							<th>模块</th>
							<th>位置</th>
							<th>上线时间</th>
							<th>下线时间</th>
							<th>点击量</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${bannerList}" var="banner" varStatus="xh">
						<tr>
							<td><input type="checkbox" name="pid" value="${banner.id}"></td>
							<td>${xh.count}</td>
							<td><img style="height: 36px;" src="${fileUrl}${banner.bannerImg}" class="suo"/></td>
							<td><c:if test="${banner.enabled =='0'}">开放</c:if><c:if test="${banner.enabled =='1'}">关闭</c:if></td>
							<td>${banner.cityName}</td>
							<td><c:if test="${banner.source =='0'}">网站</c:if><c:if test="${banner.source =='1'}">微站</c:if></td>
							<td>
                                <c:if test="${banner.model=='0'}">首页</c:if>
                                <c:if test="${banner.model=='1'}">新房</c:if>
                                <c:if test="${banner.model=='2'}">市场研究</c:if>
                                <c:if test="${banner.model=='3'}">资讯</c:if>
                                <c:if test="${banner.model=='4'}">地产新闻</c:if>
                                <c:if test="${banner.model=='5'}">美联资讯</c:if>
                                <c:if test="${banner.model=='6'}">购房指南</c:if>
                                <c:if test="${banner.model=='7'}">新政解读</c:if>
                                <c:if test="${banner.model=='8'}">人物专访</c:if>
                                <c:if test="${banner.model=='9'}">购房资格</c:if>
                                <c:if test="${banner.model=='10'}">关于我们</c:if>
							</td>
							<td>
								<c:if test="${banner.position==0}">位置一</c:if>
								<c:if test="${banner.position==1}">位置二</c:if>
								<c:if test="${banner.position==2}">位置三</c:if>
								<c:if test="${banner.position==3}">位置四</c:if>
							</td>
							<td>${banner.startTime}</td>
							<td>${banner.endTime}</td>
							<td>${banner.clikNum}</td>
							<td>
								<c:if test="${banner.isDelete==0}">
								<a target="contentF" class = "edit_img" title = "编辑" href="${ctx}/rest/setting/enterEditBanner?id=${banner.id}"></a>
								</c:if>
								<c:if test="${banner.isDelete==0}">
									<a target="contentF" title="删除" onclick="deleteBanner(${banner.id },1)" class="delete_img"></a>
								</c:if>
								<c:if test="${banner.isDelete==1}">
									<a target="contentF" class="recove_img" title="恢复" onclick="deleteBanner(${banner.id },0)"></a>
								</c:if>
								<a class="up_img" title="上移" target="contentF" onclick="sort(${banner.id },${banner.orderBy},1)"></a>
								<a class="down_img" title="下移" target="contentF" onclick="sort(${banner.id },${banner.orderBy},2)"></a>
								<a <c:if test="${banner.enabled==0}">class="onoff_img"</c:if> <c:if test="${banner.enabled==1}">class="offon_img"</c:if> target="contentF" onclick="updateEnabled(${banner.enabled},${banner.id })"></a>
								<!-- <a href="javascript:;" target="contentFrame" class = "admin_img" title = "管理图片"></a> -->
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 分页 -->
			 <c:if test="${!empty paginator}"> 
			    <c:set var="paginator" value="${paginator}"/>
			    <c:set var="target" value="listDiv"/>
			    <%@include file="../layout/pagination.jsp"%>
			 </c:if> 
	<!-- 本页私有js -->
	<script type="text/javascript">
	$(document).ready(function(){
		//checkbox的整体选中
	
		$("#selectAll").on("click",function() {
			if($("input[name = 'selectAll']").is(":checked")){
				$("table td input").each(function(){
					$(this)[0].checked = true;
				})
			}
			if(!($("input[name = 'selectAll']").is(":checked"))){
				$("table td input").each(function(){
					$(this)[0].checked = false;
				})
			}
		})
	})

    //排序
    function sort(id,orderById,sort) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/bannerSort?sort="+sort+"&orderBy="+orderById+"&id="+id,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

    function checkall(){
        $("input[name='pid']").each(function(){
            this.checked=true;
        });
    }



    function delcheckall(){
        $("input[name='pid']").each(function(){
            this.checked=false;
        });
    }

    function batchDelete(status) {
        var ids = [];
        $("input[name='pid']").each(function(){
            if(this.checked){
                ids.push($(this).val());
            }
        });
        if(ids.length==0){
            layer.msg("请选择所操作的数据！", {icon: 2})
            return;
        }
        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/batchUpdateBanner?ids="+ids+"&isDelete="+status,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    layer.msg("操作成功！", {icon: 1});
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

    //启用，禁用
    function updateEnabled(isShow,id){
        if(isShow==1){
            isShow=0;
        }else if(isShow == 0){
            isShow = 1;
        }

        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/editBanner?enabled="+isShow+"&id="+id,
            async:false, // 此处必须同步
            dataType: "json",
            data:"",
            success: function(data){

                $('#searchForm').submit();
            }
        });
    }

	</script>
	</body>
</html>
