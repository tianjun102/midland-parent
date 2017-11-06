<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>


<div class="table-responsive m40">
    <table class="table table-bordered table-add">
        <thead>
            <tr>
                <th style="width: 8%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >取消</a></th>
                <th style="width: 8%">编号</th>
				<th style="width: 8%">招聘类别</th>
				<th style="width: 8%">城市</th>
				<th style="width: 8%">招聘岗位</th>
				<th style="width: 8%">上线状态</th>
				<th style="width: 10%">上线时间</th>
                <th style="width: 15%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
                        <td><input type="checkbox" name="pid" value="${item.id}"></td>
                        <td>${xh.count}</td>
						<td><c:if test="${item.type==1}">校招</c:if><c:if test="${item.type==2}">社招</c:if></td>
                        <td>${item.cityName}</td>
						<td>${item.post}</td>
                        <td><c:if test="${item.releaseStatus==0}">已上线</c:if><c:if test="${item.releaseStatus==1}">已下线</c:if></td>
                        <td>${item.releaseTime}</td>
						<td>
                            <a target="contentF" class="edit_img" href="${ctx}/rest/recruitManager/to_update?id=${item.id}"></a>
                            <c:if test="${item.isDelete==0}">
                                <a target="contentF" onclick="delete1(${item.id },1)" class="delete_img"></a>
                            </c:if>
                            <c:if test="${item.isDelete==1}">
                                <a target="contentF" class="recove_img" title="恢复" onclick="delete1(${item.id },0)"></a>
                            </c:if>
                            <c:if test="${empty item.releaseStatus or item.releaseStatus==1}"><a target="contentF" title="上线" class="lineup_img" onclick="updateStatus(${item.id},${item.releaseStatus});"></a></c:if>
                            <c:if test="${item.releaseStatus==0}"><a target="contentF" title="下线" class="linedown_img" onclick="updateStatus(${item.id},${item.releaseStatus});"></a></c:if>
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
    <%@include file="../layout/pagination.jsp" %>
</c:if>

<script type="text/javascript">

   function delete1(id,isDelete){
       var msg = "您确定要删除当前数据吗？";
       if(isDelete==0){
           msg = "您确定要恢复当前数据吗？"
       }
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px','200px'],
            shadeClose: false, //点击遮罩关闭
            title:['删除招聘信息'],
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
                    url: "${ctx}/rest/recruitManager/update?id="+id+"&isDelete="+isDelete,
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(data){
                        if(data.state==0){
                            layer.msg("删除成功！",{icon:1});
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }else{
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

    function to_edit(id){
        layer.open({
            type: 2,
            title: ['修改'],
            shade: 0.3,
            area: ['500px', '700px'],
            content: ['${ctx}/rest/recruitManager/to_update?id='+id,'no']
        });
    }

   //修改发布状态
   function updateStatus(id,status){
       if(status==1){
           status=0;
       }else if(status == 0){
           status = 1;
       }else if(status ==null){
           status=0;
       }
       $.ajax({
           type: "post",
           url: "${ctx}/rest/recruitManager/update?releaseStatus="+status+"&id="+id,
           async: false, // 此处必须同步
           dataType: "json",

           success: function (data) {
               if (data.state==0){
                   if(status==null||status==0){
                       layer.msg("上线成功！", {icon: 1});
                   }else if(status==1){
                       layer.msg("下线成功！", {icon: 1});
                   }
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

       $.ajax({
           type: "post",
           url: "${ctx}/rest/recruitManager/batchUpdate?ids="+ids+"&isDelete="+status,
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