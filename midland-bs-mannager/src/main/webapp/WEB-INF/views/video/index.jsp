<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Video.js 6.2.8</title>
	<link rel="stylesheet" href="${ctx}/assets/video/css/video-js.css">
	<style>
		body{background-color: #ffffff}
		.m{ width: 740px; height: 400px; margin-left: auto; margin-right: auto; margin-top: 100px; }
	</style>

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

		.fileupload {
			float: left;
		}

		.fileupload .uploadify-button-text {
			position: absolute;
			left: 50%;
			transform: translateX(-50%);
			bottom: 0px;
		}
	</style>
	<script type="text/javascript">
        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/video',
                'multi': true,// 是否支持多个文件上传
                'buttonText': '上传文件',
                'onUploadSuccess': function (file, data, response) {
                    var myPlayer = videojs('my-video');
                    var videoUrl = data;
                    videojs("my-video", {}, function() {
                        window.myPlayer = this;
                        $("#mymoda .video-con #my-video source").attr("src", videoUrl);
                        myPlayer.src(videoUrl);
                        myPlayer.load(videoUrl);
                        myPlayer.play();
                    });
                    $("#videoUrl").val(data);
                },
                'onQueueComplete': function (queueData) {
                    if (queueData.uploadsSuccessful < 1) {
                        alert('文件上传失败');
                    }
                }

            });

        })
	</script>

</head>

<body>
	<div class="m" style="width:100%; height: 100%; margin-top:0;">
		<video id="my-video" class="video-js" controls preload="auto" style = "width:100%;height: 906px;"
		  poster="m.png" data-setup="{}">
			<source src="${videoUrl}" type="video/mp4">
			<p class="vjs-no-js">
			  To view this video please enable JavaScript, and consider upgrading to a web browser that
			  <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
			</p>
		  </video>
		<li>
			<div class="fileupload">
				<input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">
				<input type="file" name="file_upload" id="file_upload"/>
			</div>
		</li>
		<form id="videoForm" action="${ctx}/setting/videoSave">
			<input type="hidden" id="videoUrl" name="videoUrl" >
			<a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">确定</a>
		</form>

		  <script src="${ctx}/assets/video/js/video.min.js"></script>
		  <script type="text/javascript">
			var myPlayer = videojs('my-video');
			videojs("my-video").ready(function(){
				var myPlayer = this;
				myPlayer.play();
			});
		</script>
	</div>

</body>
<
<script type="text/javascript">
    function updateData() {
        if (notEmpty('videoUrl', 'videoUrl', '请上传视频')) {
            var data = $("#videoForm").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/setting/videoSave",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.state == 0) {
                        layer.msg("操作成功！！！", {icon: 1});

                    } else {
                        layer.msg("操作失败！", {icon: 2});
                    }
                },
                error: function (data) {
                    if (data.responseText != null) {
                        layer.msg(data.responseText, {icon: 2});
                    } else {
                        layer.msg("操作失败！", {icon: 2});
                    }
                }
            });
        }
    }
</script>
</html>
