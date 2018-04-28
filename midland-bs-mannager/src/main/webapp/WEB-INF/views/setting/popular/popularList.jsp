<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		input.orderByF:hover {
			color: #7ee043;
			/* font-weight: bold; */

		}
	</style>
</head>
<body>


<div class = "table-responsive m40">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
							<th style="width: 8%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >取消</a></th>
							<th style="width: 5%">编号</th>
							<th style="width: 8%">城市</th>
							<th style="width: 5%">平台</th>
							<th style="width: 5%">模块</th>
							<th style="width: 10%">分类</th>
							<th style="width: 10%">名称</th>
							<th style="width: 10%">nofollow</th>
							<th style="width: 20%">操作</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.popularList }">
						<c:forEach items="${requestScope.popularList}" var="popular"
							varStatus="xh">
							<tr>
								<td><input type="checkbox" name="pid" value="${popular.id}"></td>
								<td>${xh.count }</td>
								<td>${popular.cityName}</td>
								<td>
									<c:if test="${popular.source==0}">网站</c:if>
									<c:if test="${popular.source==1}">微站</c:if>
								</td>
								<td>
									<c:if test="${popular.menuId==0}">首页</c:if>
									<c:if test="${popular.menuId==1}">新房</c:if>
									<c:if test="${popular.menuId==2}">二手房</c:if>
									<c:if test="${popular.menuId==3}">租房</c:if>
									<c:if test="${popular.menuId==4}">写字楼</c:if>
									<c:if test="${popular.menuId==5}">商铺</c:if>
									<c:if test="${popular.menuId==6}">小区</c:if>
									<c:if test="${popular.menuId==7}">经纪人</c:if>
									<c:if test="${popular.menuId==8}">外销网</c:if>
									<c:if test="${popular.menuId==9}">市场调究</c:if>
									<c:if test="${popular.menuId==10}">资讯</c:if>
									<c:if test="${popular.menuId==11}">问答</c:if>
								</td>
								<td>${popular.cateName}</td>
								<td>${popular.name}</td>
								<td><c:if test="${popular.nofollow==0}">否</c:if>
									<c:if test="${popular.nofollow==1}">是</c:if></td>
								<td>
									<input type="text" name="orderByF" class="orderByF"
										   style="width: 25px;border:none;text-align: center;background: transparent;"
										   onblur="sortForm(${popular.id},this.value)" value="${popular.orderByF}"
											onfocus="sortFocus(this)">
									<c:if test="${popular.isDelete==0}">
									<a onclick="preUpdate(${popular.id })" target="contentF" class = "edit_img" title = "编辑"></a>
									</c:if>
									<c:if test="${popular.isDelete==0}">
										<a target="contentF" title="删除" onclick="isDelete(${popular.id },1)" class="delete_img"></a>
									</c:if>
									<c:if test="${popular.isDelete==1}">
										<a target="contentF" class="recove_img" title="恢复" onclick="isDelete(${popular.id },0)"></a>
									</c:if>
									<a target="contentF" class="up_img" title="上移" onclick="sort(${popular.id },${popular.orderByF},${popular.orderBy},1)"></a>
									<a target="contentF" class="down_img" title="下移" onclick="sort(${popular.id },${popular.orderByF},${popular.orderBy},2)"></a>

									<a <c:if test="${popular.isShow==0}">class="onoff_img"</c:if> <c:if test="${popular.isShow==1}">class="offon_img"</c:if> target="contentF" onclick="updatePopular(${popular.isShow},${popular.id })"></a>
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
		  <%@include file="../../layout/pagination.jsp"%>
	</c:if>		  

<script type="text/javascript">

	function sortFocus(obj) {
        $(obj).css("border-style","solid 1px");
        $(obj).css("border-color","blue");
    }
	
	function sortForm(id,orderByF) {

        var data = "id="+id+"&orderByF="+orderByF;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/saveEditPopular",
            async: false, // 此处必须同步
            dataType: "json",
            data:data,
            success: function (data) {
                if (data.flag==1){
                    $('#searchForm').submit();

                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
	}
	
    //排序
    function sort(id,orderByF,orderById,sort) {
        var data = $("#searchForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/popular/sort?sort="+sort+"&orderByF="+orderByF+"&orderBy="+orderById+"&id="+id,
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
			  title:['删除热门关注'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none; height:100%;">'+
							'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">'+msg+'</p>'+
				'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/setting/deletePopular?id="+Id+"&isDelete="+isDelete,
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
			title: ['修改热门关注'],
			shade: 0.3,
			area: ['500px', '500px'],
			content: ['${ctx}/rest/setting/toEditPage?id='+Id,'no']
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
            url: "${ctx}/rest/setting/batchUpdatePopular?ids="+ids+"&isDelete="+status,
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
    function updatePopular(isShow,id){
        if(isShow==1){
            isShow=0;
        }else if(isShow == 0){
            isShow = 1;
        }

        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/saveEditPopular?isShow="+isShow+"&id="+id,
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