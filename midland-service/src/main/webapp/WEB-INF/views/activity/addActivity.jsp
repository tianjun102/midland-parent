<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <base href="<%=basePath%>">
        <meta charset="utf-8" />
        <title>栏目管理</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
        <link rel="stylesheet" href="${ctx}/assets/css/layer.css">
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/assets/css/common.css">
		<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
    </head>
    <body >
    	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
		<script src="${ctx}/assets/scripts/common.js"></script>
		<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
		<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
		<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/assets/UEditor/ueditor.config.js" type="text/javascript"></script>
		<script src="${ctx}/assets/UEditor/ueditor.all.js" type="text/javascript"></script>
		<script src="${ctx}/assets/UEditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>
		<script src="${ctx}/assets/scripts/inputControl.js" type="text/javascript"></script>
		
		
		
	<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<span>添加活动</span>
			</p>
		<form id="formId" action="${ctx}/rest/activity/addActivity" enctype="multipart/form-data" method="post">
			<ul class = "adminfo row">
				<li><span>活动标题：</span><input maxlength="50" type="text" name="actiTitle"><span class = "_star ">*</span></li>
				<li style = "overflow:hidden;">
				<span style = "float:left;">大图：</span>
				<div id="img" style= "display:inline-block; float:left;">
					<div class="imgbox">
					    <div class="imgnum">
					        <input id="file1" name="file1" type="file" class="filepath" />
					        <span class="close">X</span>
					        <img src="${ctx}/assets/img/btn.png" class="img1" />
					        <img src="" class="img2" />
					    </div>
					</div>
				</div>
				<div style = "font-size:12px; color:#666; height:202px; line-height:202px;">(请添加大小不超过500K、分辨率为1180 * 340 Pix、图片格式为JPG或PNG的图片)</div>
				</li>
				<!-- <li><span>大图：</span><img  src="" width="120px" height="100px" alt=""><input type="file" name="file1" id="file1" onchange="previewImg(this)"></li>
				<li><span>小图：</span><img  src="" width="120px" height="100px" alt=""><input type="file" name="file2" id="file2" onchange="previewImg(this)"></li> -->
				<li style = "overflow:hidden;">
					<span style = "float:left;">详情：</span>
					<textarea style="float:left; width: 92%;min-height: 350px; resize:none; outline-color: #0099e0;" name="actiDetails" id="myEditor" rows="" cols=""></textarea></li>
				<li><span>排序：</span><input maxlength="3" type="text" name="sortOrder" onfocus="InitInput.setNumber(this,3,0,0)"><span class = "_star ">*</span></li>
				<li style="display:flex;align-items:center">
				<span>活动状态：<input name = "isShow" id = "isShow" type="hidden" value=""></span>
					<input style="width: 15px;height: 15px;margin: 0px;" onchange="addIsShow();" type="radio" name="isshow" value="1">
					<em class = "gang" style = "margin-right:15px;">开放</em> 
					<input style="width: 15px;height: 15px;margin: 0px;" onchange="addIsShow();" type="radio" name="isshow" value="0">
					<em class = "gang">关闭</em>
					<span class = "_star ">*</span>
				</li>
				<li>
					<span>发布日期：</span><input onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" name="addTime">
					<span class = "_star ">*</span>
				</li>
			</ul>
	       	<ul class = "adminfo row">
	       		<li>
					<span></span>
					<a onclick="subumintActivity();" target="contentF" class = "public_btn bg2">保存</a>
					<a style="margin-left: 20px" href="${ctx}/rest/activity/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
				</li>
	       	</ul>
		</form>
		</section>
	</div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

<%--         <div>
	       <form action="${ctx}/rest/activity/addActivity" enctype="multipart/form-data" method="post">
	       	<table width="1400px;" border="1" cellspacing="0" cellpadding="0">
		       	<col width="500px;">
	       		<col width="900px;">
		       	<tr>
			       	<td>活动标题：</td>
			       	<td><input type="text" name="actiTitle"></td>
		       	</tr>       	
		       	<tr>
			       	<td>大图：</td>
			       	<td><img  src="" width="120px" height="100px" alt=""><input type="file" name="file1" id="file1" onchange="previewImg(this)"></td>
		       	</tr>       	
		       	<tr>
			       	<td>小图：</td>
			       	<td><img  src="" width="120px" height="100px" alt=""><input type="file" name="file2" id="file2" onchange="previewImg(this)"></td>
		       	</tr>       	
		       	<tr>
		       		<td>详情： </td>
		       		<td><textarea name="actiDetails" id="myEditor">这里写你的初始化内容</textarea></td>
		       	</tr>
		       	<tr>
		       		<td>排序号： </td>
		       		<td><input type="text" name="sortOrder"></td>
		       	</tr>
		       	<tr>
		       		<td>是否开放：<input name = "isShow" id = "isShow" type="hidden" value=""> </td>
		       		<td>是：<input onchange="addIsShow();" type="radio" name="isshow" value="0">否：<input onchange="addIsShow();" type="radio" name="isshow" value="1"> </td>
		       	</tr>
		       	<tr>
		       		<td>发布日期：</td>
		       		<td><input onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" name="addTime"> </td>
		       	</tr>
	       	</table>
	       	<input type="submit" value="提交" >
	       </form>
        </div> --%>

    </body>
    <script type="text/javascript">
	var ImgObj=new Image();   //建立一个图像对象	
	var AllImgExt=".jpg|.jpeg|.gif|.bmp|.png|"//全部图片格式类型
	var FileObj,ImgFileSize,ImgWidth,ImgHeight,FileExt,ErrMsg,FileMsg,HasCheked,IsImg//全局变量 图片相关属性

	//以下为限制变量
	var AllowExt=".jpg|.jpeg|.png|" //允许上传的文件类型 ŀ为无限制 每个扩展名后边要加一个"|" 小写字母表示
	var AllowImgFileSize=1;  //允许上传图片文件的大小 0为无限制  单位：M
	var AllowImgWidth=225;   //允许上传的图片的宽度 ŀ为无限制　单位：px(像素)
	var AllowImgHeight=140;   //允许上传的图片的高度 ŀ为无限制　单位：px(像素)
	HasCheked=true;
    UE.getEditor('myEditor');
    function initProp(){
    	var classId = $("#level option:selected").val();
    	var className = $("#level option:selected").text();
    		
    		$.ajax({ 
    			type: "post", 
    			url: "${ctx}/rest/class/linkclassandprop?classId="+classId, 
    			async:false, // 此处必须同步
    			dataType: "json",
    			data:"",
    			success: function(data){ 
    				$("#prop").html("<input disabled='disabled' type='text' name='' value='"+data.classed.propertyName+"'> <input type='hidden' name='propertyId' value='"+data.classed.propertyId+"'>");
    			} 
    		});
    		
    }
    
	function addIsShow(){
		if($('input[name="isshow"]:checked ').val()=="0"){
			$("#isShow").val("0");
		}
		if($('input[name="isshow"]:checked ').val()=="1"){
			$("#isShow").val("1");
		}
	}
	
	
	
	
	
	//图片预览
	function previewImg(t){
		
		if(t.value=="") return false;
		
		FileExt=t.value.substr(t.value.lastIndexOf(".")).toLowerCase();
		
		if(AllowExt!=0&&AllowExt.indexOf(FileExt+"|")==-1) //判断文件类型是否允许上传
		  {
		    ErrMsg="\n该文件类型不允许上传。请上传 "+AllowExt+" 类型的文件，当前文件类型为"+FileExt;
		    $(t).val("");
		    layer.alert(ErrMsg);
		    return false;
		  }
		
		var src = window.URL.createObjectURL(document.getElementById(t.id).files.item(0));
	    ImgObj.src=src;
	    
	    ImgFileSize=document.getElementById(t.id).files.item(0).size;
	 	ImgFileSize=Math.round(ImgFileSize*1000/(1024*1024))/1000;//取得图片文件的大小
	 	console.log("图片大小为"+ImgFileSize)
	 	if(ImgFileSize==0){
	 		var url = window.URL.createObjectURL(document.getElementById(t.id).files.item(0)); 
	 		$(t).prev().attr("src","");
			 HasCheked=false;
			 return false;
		 }else{
			 var url = window.URL.createObjectURL(document.getElementById(t.id).files.item(0)); 
			 $(t).prev().attr("src",url);
			 HasCheked=true;
			 return true;
		 }
	 	
	}
    
    $(function() {
        $(".filepath").on("change",function() {
//          alert($('.imgbox').length);
            var srcs = getObjectURL(this.files[0]);   //获取路径
            $(this).nextAll(".img1").hide();   //this指的是input
            $(this).nextAll(".img2").show();  //fireBUg查看第二次换图片不起做用
            $(this).nextAll('.close').show();   //this指的是input
            $(this).nextAll(".img2").attr("src",srcs);    //this指的是input
			//$(this).val('');    //必须制空
            $(".close").on("click",function() {
                $(this).hide();     //this指的是span
                $(this).nextAll(".img2").hide();
                $(this).nextAll(".img1").show();
                $(this).prev().val('');
            })
        })
    })


    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file)
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file)
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file)
        }
        return url
    };
    
    function subumintActivity(){
    	var form = document.getElementById('formId');
		var data = new FormData(form);
		var required = true;
		if($("#isShow").val()==""||$("input[name='addTime']").val()==""){
			layer.msg("请完成必填项");
			required = false;
		}
		if(required){
		$.ajax({
			url : "${ctx}/rest/activity/addActivity",
			type : "post",
			cache : false,
			contentType : false,
			processData : false,
			dataType: "json",
			data : data,
			async : false,
			success : function(result) {
			if(result.result=="ok"){
				layer.msg("保存成功！",{icon:1});
				setTimeout(function(){window.open("${ctx}/rest/activity/index","contentF");},2000);  
			}
			},
			error:function(){
				layer.msg("保存失败！",{icon:2});
			}
			});
		}
    	
    }
    
    
    </script>
</html>