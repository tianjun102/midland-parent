<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>省市区</title>
    <style type="text/css">

    </style>
</head>
<body>

<li class="col-md-6" style="display:flex;align-items:center">
    <span>省：</span> <!-- 省 -->
    <label></label>
    <input type="hidden" name="provinceId">
    <input type="hidden" name="regionSn">
    <input type="hidden"  name="provinceName">
    <!-- 第一次进页面加载省 -->
    <select id="provinces" onchange="initProvince()" style="height: 28px;width: 105px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;"  >
        <option>请选择</option>
        <c:forEach items="${provinceList}" var="province">
            <option
                    value="${province.parentId}">${province.parentName}
            </option>
        </c:forEach>
    </select>
    <!-- 市 -->
    &nbsp;&nbsp;
    <p id="city" style="display: inline-block;height: 28px;">
        <span>市：</span>
        <label></label> <input type="hidden" name="cityId" id="cityId" value="${cityId}" >
        <input type="hidden" name="cityName" id="cityName">
        <select id="citys" name="citys" onchange="initCity()" style="height: 28px;width: 105px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;"  onblur="checkSelect('citys','请选择市级')">
            <c:choose>
                <c:when test="${not empty item.cityId}">
                    <option value="${item.cityId}">${item.cityName}</option>
                </c:when>
                <c:otherwise>
                    <option value="">请选择</option>
                </c:otherwise>
            </c:choose>

        </select>
        <label style="color: red;padding-top: 0!important;" class = "_star " >*</label>
    </p>

    <script type="text/javascript">

        //省级赋值
        function initProvince() {
            var addrId = $("#provinces option:selected").val();
            var addName = $("#provinces option:selected").text();
            /*$("#districts").html("<option  >请选择</option>");*/

            /*$("input[name=provinceId]").val(addrId);
            $("input[name=provinceName]").val(addName);*/
            if(addrId==null||addrId==""){
                $("#cityId").val("");
                $("#cityName").val("");
                $("#citys").html("<option value='' >请选择</option>");
                return;
            }
            $.ajax({
                type: "post",
                url: "${ctx}/rest/setting/seleAddress?flag=city&id=" + addrId,
                async: false, // 此处必须同步
                dataType: "json",
                data: "",
                success: function (data) {
                    $("#citys").html("<option  value=''>请选择</option>");

                    data.result.forEach(function (list) {
                        $("#citys").append(
                            "<option value=" + list.id + " >" + list.name + "</option>");
                    })
                }
            });
        }


        //市级赋值
        function initCity() {
            var addrId = $("#citys option:selected").val();
            var addName = $("#citys option:selected").text();
            if ("请选择" == addName) {
                //下级改变成请选择
                $("#districts option:selected").text(addName);
                $("#districts").html("<option  >请选择</option>");
                //其值及其下级值变成空
                $("input[name='cityId']").val("");
                $("input[name='cityName']").val("");
                $("input[name='distId']").val("");
                $("input[name='distName']").val("");
                return;
            }
            $("input[name='cityId']").val(addrId);
            $("input[name='cityName']").val(addName);
        }

    </script>

</body>
</html>