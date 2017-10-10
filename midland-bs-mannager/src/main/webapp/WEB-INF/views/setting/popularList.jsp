<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<div class = "table-responsive m40">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
							<th style="width: 5%">序号</th>
							<th style="width: 5%">城市</th>
							<th style="width: 10%">平台</th>
							<th style="width: 5%">模块</th>
							<th style="width: 10%">分类</th>
							<th style="width: 4%">名称</th>
							<th style="width: 5%">操作</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.popularList }">
						<c:forEach items="${requestScope.popularList}" var="popular"
							varStatus="xh">
							<tr>
								<td>${xh.count }</td>
								<td>${popular.cityName}</td>
								<td>
									<c:if test="${popular.source==1}">网站</c:if>
									<c:if test="${popular.source==2}">微站</c:if>
								</td>
								<td>
									<c:if test="${popular.menuId==1}">首页</c:if>
									<c:if test="${popular.menuId==2}">新房</c:if>
									<c:if test="${popular.menuId==3}">二手房</c:if>
								</td>
								<td>${popular.cateName}</td>
								<td>${popular.name}</td>
								<td>
									<a onclick="preUpdate(${popular.id })" target="contentF" class = "edit_img" title = "编辑"></a>
									<a target="contentF" class = "delete_img" title = "删除" onclick="isDelete(${popular.id })"></a>
								  </td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="14">没有找到数据!</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
				</table>
</div>
	<!-- 分页 -->
	<c:if test="${!empty paginator}"> 
		  <c:set var="paginator" value="${paginator}"/>
		  <c:set var="target" value="listDiv"/>
		  <%@include file="../layout/pagination.jsp"%>
	</c:if>		  

<script type="text/javascript">
	//删除
    //修改
    function viewRealRegistration(userId){
        layer.open({
            type: 2,
            title: ['用户信息'],
            shade: 0.3,
            area: ['500px', '500px'],
            content: ['${ctx}/rest/user/findUser?userId='+userId+'&flag=1','no']
        });
    }

function takeblacklist(userId){
        layer.open({
            type: 1,
            title: ['加入黑名单'],
            shade: 0.3,
            area: ['500px', '500px'],
            content: '<form action="${ctx}/rest/user/update" method="post" id="blackForm">'
            +'<ul class = "userinfo row"><input type="hidden" name="id" id="id" value="'+userId+'">'
            +'<li><span>拉黑原因：</span><textarea style="width:80%;height:100px" name="blackRemark" id="blackRemark"></textarea></li>'
            +'<li><a target="contentF" class = "public_btn bg2" id="save" onclick="consume('+userId+')">加入黑名单</a>'
            +'</ul></form>'
        });
    }


    function consume(userId){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/user/update?id="+userId+'&isBlack=1'+'&blackRemark='+$('#blackRemark').val(),
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



	function isDelete(Id){
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除热门关注'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none; height:100%;">'+
							'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除吗?</p>'+
				'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/setting/deletePopular?id="+Id,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("删除成功！",{icon:1});
								setTimeout(function(){$("#searchForm").submit();},1000);
							}
							if(xmlobj.flag==0){
								layer.msg("删除失败！！",{icon:7});
							}
							layer.close(index);
						} 
					});
				 }
				,success: function (layero) {
				      var btn = layero.find('.layui-layer-btn');
				      btn.css('text-align', 'center');
				  }
			  });
		
	}

	//重置
	function isReset(userId){

		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['重置密码'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none; height:100%;">'+
							'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否确定重置该用户的密码?</p>'+
				'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/user/resetPwd?userId="+userId,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("已重置！",{icon:1});
							}
							if(xmlobj.flag==0){
								layer.msg("重置失败！！",{icon:7});
							}
							layer.close(index);
						} 
					});
				 }
				,success: function (layero) {
				      var btn = layero.find('.layui-layer-btn');
				      btn.css('text-align', 'center');
				  }
			  });
	}
	//修改
	function preUpdate(Id){
		layer.open({
			type: 2,
			title: ['修改热门关注'],
			shade: 0.3,
			area: ['500px', '600px'],
			content: ['${ctx}/rest/setting/toEditPage?id='+Id,'no']
			});
	}
	//角色列表
	function userRole(userId,userName){
		layer.open({
			type: 2,
			title: ['用户角色信息:'+userName],
			shade: 0.3,
			area: ['600px', '450px'],
			content: ['${ctx}/rest/user/userRole?userId='+userId,'yes']
			});
	}
	//查看
	function viewUser(userId){
		layer.open({
			type: 2,
			title: ['用户信息'],
			shade: 0.3,
			area: ['600px', '550px'],
			content: ['${ctx}/rest/user/findUser?userId='+userId,'no']
			});
	}
	//启用关闭
	function isOffOn(userId,state){
		if(state=="0"){
			
			layer.open({
				  type: 1,
				  skin: 'layer-style',
				  area: ['350px','200px'],
				  shadeClose: false, //点击遮罩关闭
				  title:['启用'],
				  resize: false,
				  scrollbar:false,
				  content:
				 	'<section class = "content" style = "border:none; height:100%;">'+
								'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否确定开启该用户?</p>'+
					'</section>',
				  btn:['确定','取消'],
				  yes: function(index){
					  $.ajax({ 
							type: "post", 
							url: "${ctx}/rest/user/offOn?id="+userId+"&state=1",
							cache:false, 
							async:false, // 此处必须同步
							dataType: "json",
							success: function(xmlobj){
								if(xmlobj.flag==1){
									layer.msg("已重新开启！",{icon:1});
								}
								if(xmlobj.flag==0){
									layer.msg("操作失败！！",{icon:7});
								}
								setTimeout(function(){$("#searchForm").submit();},1000);
								layer.close(index);
							}
						});
					 }
					,success: function (layero) {
					      var btn = layero.find('.layui-layer-btn');
					      btn.css('text-align', 'center');
					  }
				  });
		}else{
			layer.open({
				  type: 1,
				  skin: 'layer-style',
				  area: ['350px','200px'],
				  shadeClose: false, //点击遮罩关闭
				  title:['关闭'],
				  resize: false,
				  scrollbar:false,
				  content:
				 	'<section class = "content" style = "border:none; height:100%;">'+
								'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否确定关闭该用户?</p>'+
					'</section>',
				  btn:['确定','取消'],
				  yes: function(index){
					  $.ajax({ 
							type: "post", 
							url: "${ctx}/rest/user/offOn?id="+userId+"&state=0",
							cache:false, 
							async:false, // 此处必须同步
							dataType: "json",
							success: function(xmlobj){
								if(xmlobj.flag==1){
									layer.msg("已关闭！",{icon:1});
								}
								if(xmlobj.flag==0){
									layer.msg("操作失败！！",{icon:7});
								}
								setTimeout(function(){$("#searchForm").submit();},1000);
								layer.close(index);
							}
						});
					 }
					,success: function (layero) {
					      var btn = layero.find('.layui-layer-btn');
					      btn.css('text-align', 'center');
					  }
				  });
		}
	}
</script>
</body>
</html>