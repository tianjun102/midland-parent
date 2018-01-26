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
							<th style="width: 5%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >取消</a></th>
							<th style="width: 5%">编号</th>
							<th style="width: 5%">城市</th>
							<th style="width: 5%">平台</th>
							<th style="width: 5%">模块</th>
							<th style="width: 5%">名称</th>
							<th style="width: 10%">操作</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.linkUrlList}">
						<c:forEach items="${requestScope.linkUrlList}" var="linkUrl"
							varStatus="xh">
							<tr>
								<td><input type="checkbox" name="pid" value="${linkUrl.id}"></td>
								<td>${xh.count }</td>
								<td>${linkUrl.cityName}</td>
								<td>
									<c:if test="${linkUrl.source==0}">网站</c:if>
									<c:if test="${linkUrl.source==1}">微站</c:if>
								</td>
								<td>${linkUrl.modeName}</td>
								<td>${linkUrl.linkName}</td>

								<td>
									<a target="contentF" title="上移" class="up_img" onclick="sort(${linkUrl.id },${linkUrl.orderBy},1)"></a>
									<a target="contentF" title="下移" class="down_img" onclick="sort(${linkUrl.id },${linkUrl.orderBy},2)"></a>
									<c:if test="${linkUrl.isDelete==0}">
									<a onclick="preUpdate(${linkUrl.id })" target="contentF" class = "edit_img" title = "编辑"></a>
									</c:if>
									<c:if test="${linkUrl.isDelete==0}">
										<a target="contentF" title="删除" onclick="isDelete(${linkUrl.id },1)" class="delete_img"></a>
									</c:if>
									<c:if test="${linkUrl.isDelete==1}">
										<a target="contentF" class="recove_img" title="恢复" onclick="isDelete(${linkUrl.id },0)"></a>
									</c:if>
									<a <c:if test="${linkUrl.isShow==0}">class="onoff_img"</c:if> <c:if test="${linkUrl.isShow==1}">class="offon_img"</c:if> target="contentF" onclick="updateLinkUrl(${linkUrl.isShow},${linkUrl.id })"></a>
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



	function isDelete(Id,isDelete){
        var msg = "您确定要删除当前数据吗？";
        if(isDelete==0){
            msg = "您确定要恢复当前数据吗？"
        }
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除友情链接'],
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
						url: "${ctx}/rest/setting/deleteLinkUrl?id="+Id+"&isDelete="+isDelete,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("操作成功！",{icon:1});
								setTimeout(function(){$("#searchForm").submit();},1000);
							}
							if(xmlobj.flag==0){
								layer.msg("操作失败！！",{icon:7});
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
			title: ['修改友情链接'],
			shade: 0.3,
			area: ['500px', '500px'],
			content: ['${ctx}/rest/setting/toEditLinkUrlPage?id='+Id,'no']
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

    //排序
    function sort(id,orderById,sort) {
	    var data = $("#searchForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/linkUrlSort?sort="+sort+"&orderBy="+orderById+"&id="+id,
            async: false, // 此处必须同步
            dataType: "json",
			data:data,
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

    //启用，禁用
    function updateLinkUrl(isShow,id){
        if(isShow==1){
            isShow=0;
        }else if(isShow == 0){
            isShow = 1;
        }

        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/saveEditLinkUrl?isShow="+isShow+"&id="+id,
            async:false, // 此处必须同步
            dataType: "json",
            data:"",
            success: function(data){

                $('#searchForm').submit();
            }
        });
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
            url: "${ctx}/rest/setting/batchUpdatelinkUrl?ids="+ids+"&isDelete="+status,
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

</script>
</body>
</html>