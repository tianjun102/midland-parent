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


<div class="table-responsive m40"  id='table-cont'>
    <%--<a target="contentF" onclick="deletelist()">删除</a>--%>

    <table class="table table-bordered table-add">
        <thead>
        <tr>
            <th style="width: auto"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >取消</a></th>
            <th style="width: 5%">序号</th>
            <th style="width: 10%">问题主题</th>
            <th style="width: 5%">平台</th>
            <th style="width: 15%">提问时间</th>
            <th style="width: 10%">提问人昵称</th>
            <th style="width: 10%">提问人手机号</th>
            <th style="width: 8%">审核人</th>
            <th style="width: 5%">状态</th>
            <th style="width: 20%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.questions }">
                <c:forEach items="${requestScope.questions }" var="item"
                           varStatus="xh">
                    <tr>
                        <td><input type="checkbox" name="pid" value="${item.id}"></td>
                        <td>${xh.count}</td>
                        <td>${item.questionsTitle }</td>
                        <td> <c:if test="${item.source ==0 }">网站</c:if>
                            <c:if test="${item.source ==1 }">微站</c:if></td>
                        <td>${item.questionTime }</td>
                        <td>${item.questionName }</td>
                        <td>${item.questionPhone }</td>
                        <td>${item.auditor }</td>

                        <td>
                            <c:if test="${item.status ==0}">未审核</c:if>
                            <c:if test="${item.status ==1}">审核通过</c:if>
                            <c:if test="${item.status ==2}">审核失败</c:if>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${item.status==0}">
                                    <a  class="check_img" title="审核" target="contentF" onclick="toAudit(${item.id })"></a>
                                </c:when>
                                <c:otherwise>
                                    <a  class="see_img" title="查看问题" target="contentF" href="${ctx}/rest/questions/to_view?id=${item.id}"></a>
                                </c:otherwise>
                            </c:choose>
                            <a  target="contentF" class="reply_img" title="回答" href="${ctx}/rest/questions/to_repeat?id=${item.id}"></a>
                            <c:if test="${item.isDelete==0}">
                                <a target="contentF" onclick="deleteQuestions(${item.id },1)" class="delete_img"></a>
                            </c:if>
                            <c:if test="${item.isDelete==1}">
                                <a target="contentF" class="recove_img" title="恢复" onclick="recover(${item.id },0)"></a>
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


    function deletelist(){
        var ids="";
        $("input[name='checkbox']:checkbox:checked").each(function(){
            ids+=","+$(this).val()
        })
        $.ajax({
            type: "post",
            url: "${ctx}/rest/questions/delete",
            async: false, // 此处必须同步
            dataType: "json",
            data: {"ids": ids},
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("删除成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("删除失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("删除失败！", {icon: 2});
            }

        });
    }


    function viewQuestion(id) {
        layer.open({
            type: 2,
            title: ['查看回答'],
            shade: 0.3,
            area: ['1000px', '700px'],
            content: ['${ctx}/rest/questions/to_view?id=' + id , 'no']
        });
    }
function toAudit(id) {
        layer.open({
            type: 2,
            title: ['审核'],
            shade: 0.3,
            area: ['1000px', '700px'],
            content: ['${ctx}/rest/questions/toAudit?id=' + id , 'no']
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
            url: "${ctx}/rest/questions/batchUpdate?ids="+ids+"&isDelete="+status,
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

    function deleteQuestions(id,flag){
        var msg = "您确定要删除当前数据吗？";
        if(flag==0){
            msg = "您确定要恢复当前数据吗？"
        }
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px','200px'],
            shadeClose: false, //点击遮罩关闭
            title:['删除片库'],
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
                    url: "${ctx}/rest/questions/update?id="+id+"&isDelete="+flag,
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


    function recover(id,flag){
        var msg = "您确定要删除当前数据吗？";
        if(flag==0){
            msg = "您确定要恢复当前数据吗？"
        }

                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/questions/update?id="+id+"&isDelete="+flag,
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(data){
                        if(data.state==0){
                            layer.msg("操作成功！",{icon:1});
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }else{
                            layer.msg("操作失败！！",{icon:7});
                        }
                        layer.close(index);
                    }
                });
    }
</script>
</body>
</html>