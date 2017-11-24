<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    </script>
    <style type="text/css">
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
                    /*$("#imgUrl").attr("value", data);
                    $("#iconImg1").attr("src", data);*/

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


        $(function () {
            $('#file_upload1').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': true,// 是否支持多个文件上传
                'buttonText': '上传文件',
                'onUploadSuccess': function (file, data, response) {
                    /*$("#imgUrl").attr("value", data);
                    $("#iconImg1").attr("src", data);*/

                    $(".fileupload1").append("<span class='fileupload-item'><img src='" + data + "'><i class='xclose'>×</i></span>")
                    $("#imgUrl1").attr("value", data + "||" + $("#imgUrl").val());

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


        $(function () {
            $('#file_upload2').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': true,// 是否支持多个文件上传
                'buttonText': '上传文件',
                'onUploadSuccess': function (file, data, response) {
                    /*$("#imgUrl").attr("value", data);
                    $("#iconImg1").attr("src", data);*/

                    $(".fileupload2").append("<span class='fileupload-item'><img src='" + data + "'><i class='xclose'>×</i></span>")
                    $("#imgUrl2").attr("value", data + "||" + $("#imgUrl").val());

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
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>均价：</span>
                <input type="text" name="price" id="price" ß/>
            </li>
            <li><span>入伙日期：</span>
                <input type="text" name="intoTime" id="intoTime" ß/>
            </li>
            <li><span>管理费用：</span>
                <input type="text" name="managerCosts" id="managerCosts" ß/>
            </li>
            <li><span>物业座数：</span>
                <input type="text" name="unitTotal" id="unitTotal" ß/>
            </li>
            <li><span>占地面积：</span>
                <input type="text" name="landArea" id="landArea" ß/>
            </li>
            <li><span>车位总数：</span>
                <input type="text" name="parkingNum" id="parkingNum" ß/>
            </li>
            <li><span>建筑类型：</span>
                <input type="text" name="buildingType" id="buildingType" ß/>
            </li>
            <li><span>建筑地址：</span>
                <input type="text" name="propertyAddress" id="propertyAddress" ß/>
            </li>
            <li><span>发展商：</span>
                <input type="text" name="developer" id="developer" ß/>
            </li>
            <li><span>物业管理：</span>
                <input type="text" name="propertyManagement" id="propertyManagement" ß/>
            </li>

            <li><span>物业优点：</span>
                <input type="text" name="propertyAdvantages" id="propertyAdvantages" ß/>
            </li>
            <li><span>地理位置：</span>
                <input type="text" name="position" id="position" ß/>
            </li>
            <li><span>配套信息：</span>
                <input type="text" name="supporting" id="supporting" ß/>
            </li>
            <li><span>房源名：</span>
                <input type="text" name="houseName" id="houseName" ß/>
            </li>
            <li><span>图片url：</span></li>
            <li><span style="vertical-align: top;">图片上传：</span>
                <div class="fileupload">
                    <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">
                    <input type="file" name="file_upload" id="file_upload"/>
                </div>
            </li>

            <li><span>房源描述：</span>
            <li id="textArea1" style="display: block;">
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


            <div>

                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                              data-toggle="tab">实景图</a></li>
                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab"
                                               data-toggle="tab">户型图</a></li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                        <li><span style="vertical-align: top;">图片上传：</span>
                            <div class="fileupload">
                                <input type="hidden" name="imgUrl" id="imgUrl1" value="${item.imgUrl}">
                                <input type="file" name="file_upload" id="file_upload1"/>
                            </div>
                        </li>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="profile">
                        <li><span style="vertical-align: top;">图片上传：</span>
                            <div class="fileupload">
                                <input type="hidden" name="imgUrl" id="imgUrl2" value="${item.imgUrl}">
                                <input type="file" name="file_upload" id="file_upload2"/>
                            </div>
                        </li>
                    </div>
                </div>

            </div>


            <div>

                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#tab1" aria-controls="home" role="tab"
                                                              data-toggle="tab">一室</a></li>
                    <li role="presentation"><a href="#tab2" aria-controls="profile" role="tab"
                                               data-toggle="tab">二室</a></li>
                    <li role="presentation"><a href="#tab3" aria-controls="profile" role="tab"
                                               data-toggle="tab">三室</a></li>
                    <li role="presentation"><a href="#tab4" aria-controls="profile" role="tab"
                                               data-toggle="tab">四室</a></li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tab1">

                    </div>
                    <div role="tabpanel" class="tab-pane" id="tab2">

                    </div>
                    <div role="tabpanel" class="tab-pane" id="tab3">

                    </div>
                    <div role="tabpanel" class="tab-pane" id="tab4">

                    </div>
                </div>

            </div>



            <ul class="adminfo row">
                <li>
                    <span></span>
                    <a onclick="subumintBanner();" target="contentF" class="public_btn bg2">保存</a>
                    <a style="margin-left: 20px" href="${ctx}/rest/banner/bannerindex" target="contentF"
                       class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>

        </ul>

    </form>
</section>

<script type="text/javascript">
    //保存数据
    function updateData() {
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
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("保存失败！", {icon: 2});
            }
        });
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
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
                    error: function () {
                        layer.msg("保存失败！", {icon: 2});
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