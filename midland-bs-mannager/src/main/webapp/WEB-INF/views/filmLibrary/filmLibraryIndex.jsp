<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .content ul.userinfo li>span {
            float: left;
            display: inline-block;
            width: 85px;
            height: 38px;
            line-height: 38px;
            text-align: right;
            font-size: 14px;
            color: rgb( 102, 102, 102 );
        }
        .dropdown {
            position: relative;
            width: 100px;
            border: 1px solid #ccc;
            cursor: pointer;
            background: #fff;
            border-radius: 3px;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }
        .content ul.userinfo li:not(:last-child) input {
            float: left;
            width: 140px;
            height: 38px;
            line-height: 38px;
            border: 1px solid #dbe2e6;
            border-radius: 4px;
            text-indent: 10px;
            outline-color: #0099e0;
        }

    </style>
</head>
<body>


<!--委托列表界面-->
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>外销网管理>>片库管理</span>
            <a class="setup" target="contentF" href="${ctx}/rest/filmLibrary/to_add" >新增</a>
        </p>
        <form action="${ctx }/rest/filmLibrary/list" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <ul class="userinfo row">
                <%@include file="../menu/area.jsp" %>
                <li><span>楼盘id：</span>
                    <input type="text" name="housesId" id="housesId" placeholder="请输入楼盘id" /></li>
                </li>
                <li><span>楼盘名称：</span>
                    <input type="text" name="title" id="title" placeholder="请输入楼盘名称" /></li>
                </li>
                <li>
                    <span>分类：</span>
                    <select name="filmType" id="filmType" class="dropdown">
                        <option value="">全部</option>
                        <c:forEach items="${filmTypes}" var="s" >
                            <option value="${s.id}">
                                    ${s.name}
                            </option>
                        </c:forEach>
                    </select>
                </li>
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
        <input style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;margin-top: 10px;" onclick="batchDelete(1)" class = "public_btn bg1" type="submit"  value = "批量删除"/>
        <input style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;" onclick="batchDelete(0)" class = "public_btn bg1" type="submit"  value = "批量恢复"/>
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