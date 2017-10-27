<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>省市区</title>
</head>
<body>

            <li class=""><span style="width: 30px;">省：</span> <!-- 省 -->
                <p id="province" style="display: inline-block;height: 38px;">
                    <input type="hidden"  name="provinceId">
                    <input type="hidden" name="regionSn"> <input type="hidden"
                                                                 name="provinceName">
                    <!-- 第一次进页面加载省 -->
                    <select id="provinces" name="provinces" onchange="initProvince()"
                            style="height: 100%;width: 120px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" onblur="checkSelect('provinces','请选择省级')">
                        <option value="">请选择</option>
                        <c:forEach items="${provinceList}" var="province">
                            <option
                                    value="${province.parentId}">${province.parentName}
                            </option>
                        </c:forEach>
                    </select>
                </p>
                <!-- 市 -->
                &nbsp;&nbsp;
                <p id="city" style="display: inline-block;height: 38px;">
                    <span>市：</span>
                    <input type="hidden" name="cityId">
                    <input type="hidden" name="cityName">
                    <select id="citys" name="citys" onchange="initCity()" style="height: 100%;width: 120px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" onblur="checkSelect('citys','请选择市级')">
                        <c:choose>
                            <c:when test="${not empty item.cityId}">
                                <option value="${item.cityId}">${item.cityName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="">请选择</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </p>

            </li>

            <li class=""> <span>地区：</span>
                <p id="district" style="display: inline-block;height: 38px;">
                    <input type="hidden" value="" name="areaId"> <input type="hidden" value="" name="areaName">
                    <select id="districts" name="districts"  onchange="initDistrict()" style="height: 100%;width: 120px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" onblur="checkSelect('districts','请选择区级')">
                        <c:choose>
                            <c:when test="${not empty item.areaId}">
                                <option value="${item.areaId}">${item.areaName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="">请选择</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </p>
                <!-- 片区 -->

                <p id="sheet" style="display: inline-block;height: 38px;">
                    <span>片区：</span>
                    <input type="hidden" value="" name="sliceId">
                    <input type="hidden" value="" name="sliceName">
                    <select id="sheets" name="sheets" onchange="initSheet()" style="height: 100%;width: 120px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <c:choose>
                            <c:when test="${not empty item.sliceId}">
                                <option value="${item.sliceId}">${item.sliceName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="">请选择</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </p>
                <span  class="_star">*</span>
            </li>

<script type="text/javascript">

    //省级赋值
    function initProvince() {
        var addrId = $("#provinces option:selected").val();
        var addName = $("#provinces option:selected").text();
        $("#citys").html("<option value='' >请选择</option>");
        $("#districts").html("<option  value=''>请选择</option>");
        $("#sheets").html("<option  value=''>请选择</option>");

        $("input[name=provinceId]").val(addrId);
        $("input[name=provinceName]").val(addName);


        $.ajax({
            type : "post",
            url : "${ctx}/rest/setting/seleAddress?flag=city&id=" + addrId,
            async : false, // 此处必须同步
            dataType : "json",
            data : "",
            success : function(data) {
                $("#citys").html("<option  >请选择</option>");

                data.result.forEach(function(list) {
                    $("#citys").append(
                        "<option value="+list.id+" >" + list.name + "</option>");
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
            $("#districts").html("<option  value=''>请选择</option>");
            //其值及其下级值变成空
            $("input[name='cityId']").val("");
            $("input[name='cityName']").val("");
            $("input[name='areaId']").val("");
            $("input[name='areaName']").val("");
            return;
        }
        $("input[name='cityId']").val(addrId);
        $("input[name='cityName']").val(addName);
        $.ajax({
            type : "post",
            url : "${ctx}/rest/setting/seleAddress?flag=area&id=" + addrId,
            async : false, // 此处必须同步
            dataType : "json",
            data : "",
            success : function(data) {
                $("#districts").html("<option value='' >请选择</option>");
                data.result.forEach(function(list) {
                    $("#districts").append(
                        "<option value="+list.id+" >"
                        + list.name + "</option>");
                })
            }
        });
    }


    //区级赋值
    function initDistrict() {
        var cityId =$("input[name='cityId']").val();
        var addrId = $("#districts option:selected").val();
        var addName = $("#districts option:selected").text();
        if ("请选择" == addName) {
            //下级改变成请选择
            $("#districts option:selected").text(addName);
            //其值及其下级值变成空
            $("input[name='areaId']").val("");
            $("input[name='areaName']").val("");
            return;
        }
        $("input[name='areaId']").val(addrId);
        $("input[name='areaName']").val(addName);

        $.ajax({
            type : "post",
            url : "${ctx}/rest/setting/seleAddress?flag=sheet&id=" + cityId+"&parentId=" + addrId,
            async : false, // 此处必须同步
            dataType : "json",
            data : "",
            success : function(data) {
                $("#sheets").html("<option value='' >请选择</option>");
                data.result.forEach(function(list) {
                    $("#sheets").append(
                        "<option value="+list.id+" >"
                        + list.name + "</option>");
                })
            }
        });

    }


    //区级赋值
    function initSheet() {
        var cityId =$("input[name='cityId']").val();
        var addrId = $("#sheets option:selected").val();
        var addName = $("#sheets option:selected").text();
        if ("请选择" == addName) {
            //下级改变成请选择
            $("#sheets option:selected").text(addName);
            //其值及其下级值变成空
            $("input[name='sliceId']").val("");
            $("input[name='sliceName']").val("");
            return;
        }
        $("input[name='sliceId']").val(addrId);
        $("input[name='sliceName']").val(addName);

    }



</script>

</body>
</html>