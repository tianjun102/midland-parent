<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

</head>
<body>


<!--委托列表界面-->
<div class="box">
    <section class="content">
        <p class="detail-title">

            <a class="setup" target="contentF" href="${ctx}/rest/filmLibrary/to_add" >新增</a>
        </p>
        <form action="${ctx }/rest/filmLibrary/list" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <ul class="userinfo row">
                <%@include file="../menu/area.jsp" %>
                <li><span>状态：</span>
                    <select name="isShow" id="isShow" class="dropdown">
                        <option value="">全部</option>
                        <option value="0">显示</option>
                        <option value="1">隐藏</option>

                    </select>
                </li>
                <li><input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/></li>
            </ul>
        </form>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">


    function toAddPage1() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['500px', '700px'],
            shadeClose: false, //点击遮罩关闭
            title: ['片库信息'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/filmLibrary/to_add', 'no']
        });
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
<!-- 本页私有js -->


<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>

<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>