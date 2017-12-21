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

        .dropdown {
            position: relative;
            width: 200px;
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
            width: 200px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #dbe2e6;
            border-radius: 4px;
            text-indent: 10px;
            outline-color: #0099e0;
        }


        .fileupload .fileupload-item {
            display: inline-block;
            position: relative;
            width: 110px;
            height: 64px;
            margin: 10px 10px 0 0;
            overflow: hidden;
            border: 1px solid #ccc;
        }

        .fileupload-item img {
            max-width: 100%;
            max-height: 100%;
        }

        .fileupload-item .xclose {
            display: block;
            position: absolute;
            right: 0;
            top: 0;
            width: 16px;
            height: 16px;
            line-height: 16px;
            text-align: center;
            background: rgba(0, 0, 0, .7);
            font-size: 14px;
            color: #ddd;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">


        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': true,// 是否支持多个文件上传
                'buttonText': '上传文件',
                'onUploadSuccess': function (file, data, response) {

                    $(".fileupload").append("<span class='fileupload-item'><img src='" + data + "'><i class='xclose'>×</i></span>")
                    $("#imgUrl").attr("value", data + "||" + $("#imgUrl").val());

                    $(".xclose").on("click", function () {
                        var temp = "";
                        var $this = $(this);
                        var $parent = $this.parent("span");
                        var imgsrcs = $("#imgUrl").val();
                        var imgsrc = $parent.find("img").attr("src");
                        var imgArray = imgsrcs.split("||");
                        for (var i = 0; i < imgArray.length; i++) {
                            if (imgArray[i].match(imgsrc)) {
                                continue;
                            }
                            if (imgArray[i] != "" && imgArray != null) {
                                temp += imgArray[i] + "||";
                            }
                        }
                        $("#imgUrl").val(temp);
                        $parent.remove();
                    });


                },
                'onQueueComplete': function (queueData) {
                    if (queueData.uploadsSuccessful < 1) {
                        alert('文件上传失败');
                    }
                }

                // Your options here
            });

            function uploadClose() {

            }
        })
    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/hotHand/add" method="post" id="dataForm">
        <ul class="userinfo  row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <%@include file="../menu/area_up_required.jsp" %>
            <li class="col-md-5"><span>均价：</span>
                <input type="text" name="price" id="price" value="${item.price} "
                       onblur="InitInput.setNumber(this,9,2,2)"/>
            </li>
            <li class="col-md-5"><span>入伙日期：</span>
                <input type="text" name="intoTime" id="intoTime" onblur="notEmpty('intoTime','intoTime','')"
                       value="${item.intoTime}"
                       onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                       maxlength="50"/>

            </li>
            <li class="col-md-5"><span>管理费用：</span>
                <input type="text" name="managerCosts" id="managerCosts" value="${item.managerCosts}"
                       onblur="InitInput.setNumber(this,9,2,2)"/>
            </li>
            <li class="col-md-5"><span>单位总数：</span>
                <input type="text" name="unitTotal" id="unitTotal"  value="${item.unitTotal}"/>
            </li>
            <li class="col-md-5"><span>物业座数：</span>
                <input type="text" name="propertyNum" id="propertyNum"  value="${item.propertyNum}"/>
            </li>
            <li class="col-md-5"><span>裝修标准：</span>
                <select name="decoration" id="decoration" class="dropdown">
                    <c:forEach items="${decorations}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==item.decoration}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>

            <li class="col-md-5"><span>主推户型：</span>
                <input type="text" name="recommend" id="recommend" value="${item.recommend}"
                />
            </li>

            <li class="col-md-5"><span>占地面积：</span>
                <input type="text" name="landArea" id="landArea" value="${item.landArea}"
                       onblur="InitInput.setNumber(this,9,2,2)"/>
            </li>
            <li class="col-md-5"><span>建筑面积：</span>
                <input type="text" name="buildingArea" id="buildingArea"  value="${item.buildingArea}"/>
            </li>
            <li class="col-md-5"><span>车位总数：</span>
                <input type="text" name="parkingNum" id="parkingNum" value="${item.parkingNum}"
                       onblur="InitInput.setNumber(this,9,0,0)"/>
            </li>
            <li class="col-md-5"><span>建筑类型：</span>
                <input type="text" name="buildingType" id="buildingType" value="${item.buildingType}"
                />
            </li>
            <li class="col-md-5"><span>物业地址：</span>
                <input type="text" name="propertyAddress" id="propertyAddress" value="${item.propertyAddress}"
                />
            </li>
            <li class="col-md-5"><span>发展商：</span>
                <input type="text" name="developer" id="developer" value="${item.developer}"
                       onblur="notEmpty('developer','developer','')"/>
            </li>
            <li class="col-md-5"><span>房源名：</span>
                <input type="text" name="houseName" id="houseName" value="${item.houseName}"
                />
            </li>
            <li class="col-md-8"><span>物业管理：</span>
                <textarea name="propertyManagement" id="propertyManagement" style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">${item.propertyManagement}</textarea>
            </li>
            <li class="col-md-8"><span>地理位置：</span>
                <textarea name="position" id="position" style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">${item.position}</textarea>
            </li>
            <li class="col-md-8"><span>配套信息：</span>
                <textarea name="supporting" id="supporting" style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">${item.supporting}</textarea>
            </li>
            <li class="col-md-8"><span>特色：</span>
                <textarea name="feature" id="feature" style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">${item.feature}</textarea>
            </li>


            <li class="col-md-5" style="width: 100%;">
                <span>图片url：</span>
                <%--<span style="vertical-align: top;">图片上传：</span>--%>
                <div class="fileupload" style="margin-left: 70px;font-size: 0;width: 360px;">
                    <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">
                    <input type="file" name="file_upload" id="file_upload"/>
                    <c:forEach items="${item.imgUrlList }" var="s">
                        <span class='fileupload-item'><img src='${s}'><i class='xclose'>×</i></span>
                    </c:forEach>
                </div>
            </li>

            <li id="textArea1" style="width: 100%;">
                <span style="display: block;float: none;">房源描述：</span>
                <textarea style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;"
                          name="propertyDesc"
                          id="myEditor1" rows="" cols="">${item.propertyDesc}</textarea>
            </li>
            <li><span>物业优点：</span>
            <li id="textArea2" style="display: block;">
                    <textarea style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;"
                              name="propertyAdvantages"
                              id="myEditor2" rows="" cols="">${item.propertyAdvantages}</textarea>
            </li>
            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">更新</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">

    $(".fileupload").delegate(".xclose", "click", function () {
        var temp = "";
        var $this = $(this);
        var $parent = $this.parent("span");
        var imgsrcs = $("#imgUrl").val();
        var imgsrc = $parent.find("img").attr("src");
        var imgArray = imgsrcs.split("||");
        for (var i = 0; i < imgArray.length; i++) {
            if (imgArray[i].match(imgsrc)) {
                continue;
            }
            if (imgArray[i] != "" && imgArray != null) {
                temp += imgArray[i] + "||";
            }
        }
        $("#imgUrl").val(temp);
        $parent.remove();
    });

    //保存数据
    function updateData() {
        if (!checkSelect('citys', '请选择市级')) {
            return;
        }

        var data = $("#dataForm").serialize();
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/hotHand/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        window.open("${ctx}/rest/hotHand/index", "contentF")
                    }, 1000);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            }
        });
    }

    //取消
    function closeWin() {
        window.open("${ctx}/rest/hotHand/index", "contentF")
    }
</script>
<script type="text/javascript">

    HasCheked = true;
    UE.getEditor('myEditor1');
    UE.getEditor('myEditor2');


    function subumintBanner() {
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px', '200px'],
            shadeClose: false, //点击遮罩关闭
            title: ['编辑'],
            resize: false,
            scrollbar: false,
            content:
            '<section class = "content" style = "border:none; height:100%;">' +
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">确定要保存么?</p>' +
            '</section>',
            btn: ['确定', '取消'],
            yes: function (index) {


                var data = $("#formId").serialize();
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/footer/update",
                    async: false, // 此处必须同步
                    dataType: "json",
                    data: data,
                    success: function (data) {
                        if (data.state == 0) {
                            layer.msg("保存成功！", {icon: 1});
                            window.location.reload();
                        } else {
                            layer.msg("保存失败！", {icon: 2});
                        }
                    },
                    error: function (data) {
                        if (data.responseText != null) {
                            layer.msg(data.responseText, {icon: 2});
                        } else {
                            layer.msg("保存失败！", {icon: 2});
                        }
                    }

                });


            }
            , success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
            }
        });

    }


</script>
</body>
</html>