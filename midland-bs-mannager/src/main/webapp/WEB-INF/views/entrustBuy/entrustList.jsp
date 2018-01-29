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


<div class="table-responsive m40" id='table-cont'>
    <table class="table table-bordered table-add">
        <thead>
        <tr>
            <th style="width: auto"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >取消</a></th>
            <th style="width: auto">委托编号</th>
            <th style="width: auto">平台</th>
            <th style="width: auto">意向区域</th>
            <th style="width: auto">装修情况</th>
            <th style="width: auto">意向小区</th>
            <th style="width: auto">意向面积</th>
            <th style="width: auto">意向户型</th>
            <th style="width: auto">意向价位</th>
            <th style="width: auto">房屋类型</th>
            <th style="width: auto">联系人</th>
            <th style="width: auto">手机号码</th>
            <th style="width: auto">经纪人</th>
            <th style="width: auto">委托时间</th>
            <th style="width: auto">处理时间</th>
            <th style="width: auto">状态</th>
            <th style="width: auto">是否删除</th>

            <th style="width: auto">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.entrusts }">
                <c:forEach items="${requestScope.entrusts }" var="item"
                           varStatus="xh">
                    <tr>
                        <td><input type="checkbox" name="pid" value="${item.id}"></td>
                        <td>${item.entrustSn }</td>
                        <td><c:forEach items="${sources}" var="s">
                            <c:if test="${item.source == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>${item.areaName }</td>
                        <td><c:forEach items="${decorations}" var="s">
                            <c:if test="${item.renovation == s.id}">${s.name}</c:if>
                        </c:forEach></td>

                        <td>${item.communityName }</td>
                        <td>${item.measure }</td>
                        <td>${item.layout }</td>
                        <td>${item.price }</td>
                        <td><c:forEach items="${houses}" var="s">
                            <c:if test="${item.houseType == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>${item.nickName }</td>
                        <td>${item.phone }</td>
                        <td>${item.agentName }</td>
                        <td>${item.entrustTime }</td>
                        <td>${item.handleTime }</td>
                        <td><c:forEach items="${statusList}" var="s">
                            <c:if test="${item.status == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>
                            <c:forEach items="${isDeletes}" var="s1" >
                                <c:if test="${s1.id==item.isDelete}">${s1.name}</c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:if test="${item.resetFlag==1 and item.isDelete ==0}">
                                <a target="contentF" class="arrange_img" title="分配" onclick="toRedistribute(${item.id })"></a>
                            </c:if>
                            <c:choose>
                                <c:when test="${item.status==3 or item.status==5}">
                                    <a target="contentF" class="see_img" title="查看" onclick="toUpdateEntrust(${item.id })"></a>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${item.isDelete ==0}">
                                        <a target="contentF" class="edit_img" title="编辑" onclick="toUpdateEntrust(${item.id })"></a>
                                    </c:if>

                                </c:otherwise>
                            </c:choose>

                            <c:if test="${item.isDelete ==0}">
                            <a target="contentF"  class="delete_img" title="删除" onclick="deleteEntrust(${item.id })"></a>
                            </c:if>
                            <c:if test="${item.isDelete ==1}">
                                <a target="contentF"  class="recove_img" title="恢复" onclick="deleteOrRecover(${item.id },0)"></a>
                            </c:if>
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


    function deleteOrRecover(id, flag) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/entrust/buy/update?id=" + id + "&isDelete=" + flag,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state == 0) {
                    $('#searchForm').submit();
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }
        })
    }


    $(function () {
        var headIndex = $("#headIndex").height();
        $("#table-cont").css({maxHeight:allHeight-headIndex-100-17});
        var tableCont = document.querySelector('#table-cont');
        /**
         * scroll handle
         * @param {event} e -- scroll event
         */
        function scrollHandle (e){
            var scrollTop = this.scrollTop;
            this.querySelector('thead').style.transform = 'translateY(' + scrollTop + 'px)';
        }

        tableCont.addEventListener('scroll',scrollHandle);
    })

    function toRedistribute(id) {
        var url = "${ctx}/rest/entrust/reset_agent";
       layer.open({
            type: 2,
            title: ['重新分配经纪人'],
            shade: 0.3,
            area: ['100%', '100%'],
            content: ['${ctx}/rest/public/toRedistribute?id=' + id+"&url="+url , 'no']
        });
    }
    function deleteEntrust(id) {
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px','200px'],
            shadeClose: false, //点击遮罩关闭
            title:['删除'],
            resize: false,
            scrollbar:false,
            content:
            '<section class = "content" style = "border:none; height:100%;">'+
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前信息?</p>'+
            '</section>',
            btn:['确定','取消'],
            yes: function(index){
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/entrust/buy/update?id="+id+"&isDelete=1",
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(xmlobj){
                        if(xmlobj.state==0){
                            layer.msg("操作成功！",{icon:1});
                            $("#searchForm").submit();
                        }
                        if(xmlobj.state==1){
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


    function toUpdateEntrust(appointId) {
        layer.open({
            type: 2,
            title: ['委托详情'],
            shade: 0.3,
            area: ['100%', '100%'],
            content: ['${ctx}/rest/entrust/buy/to_update?entrustId=' + appointId , 'yes']
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
            url: "${ctx}/rest/entrust/batchUpdate?ids="+ids+"&isDelete="+status,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    layer.msg("操作成功！", {icon: 1});
                    $('#searchForm').submit();
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }
        })
    }
</script>
</body>
</html>