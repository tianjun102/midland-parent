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
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/assets/css/common.css">
		<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
		<style type="text/css">
	        .imgbox,.imgbox1
	        {
	            float: left;
	            margin-right: 20px;
	            margin-top: 20px;
	            position: relative;
	            width: 182px;
	            height: 142px;
	           /*  border: 1px solid; */
	            overflow: hidden;
	        }
	        .imgbox1{border: 1px solid blue;
	        }
	        .imgnum{
	            left: 0px;
	            top: 0px;
	            margin: 0px;
	            padding: 0px;
	        }
	        .imgnum input,.imgnum1 input {
	            position: absolute;
	            width: 182px;
	            height: 142px;
	            opacity: 0;
	        }
	        .imgnum img,.imgnum1 img {
	           /*  width: 100%; */
	            height: 100%;
	        }
	        .close,
	        .close1 {
	            color: red;
	            position: absolute;
	            left: 170px;
	            top: 0px;
	            display: none;
	        }
	    </style>
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
    </head>
    <body >
    
    
    
    
    
    	<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<span>修改栏目</span>
			</p>
		<form id="formId" action="${ctx}/rest/class/submiteditmenus" method="post" enctype="multipart/form-data">
			<ul class = "adminfo row">
				<li>
					<span>所属父级：</span><c:if test="${empty parentClass.className}"><input type="text" disabled="disabled" value="顶级菜单"></c:if> <c:if test="${!empty parentClass.className}"><input disabled="disabled" value="${parentClass.className}"></c:if><input name="parentId" type="hidden" value="${classed.parentId}"><input type="hidden" name="classId" value="${classed.classId}">
				</li>
				<li><span>栏目名称：</span><input type="text" maxlength="50" value="${classed.className}" name="className"><span class = "_star ">*</span></li>
				<li>
					<span>栏目属性：</span><input disabled="disabled" type="text" value="${classed.propertyName}" >
					<div id = "prop">
						<input name="propertyId" type="hidden" value="${classed.propertyId}" >
			       	</div>
				</li>
				<li id="category" >
					<span>产品分类：</span>
					<c:if test="${!empty categoryList}">
			       	<select class="dropdown" id="categoryId" name="catId">
			       		<option value="${category.catId}">${category.catName}</option>
			       		<c:forEach items="${categoryList}" var="categoryList">
			       		<option <c:if test="${classed.catId == categoryList.catId}">selected="selected"</c:if> value="${categoryList.catId}">${categoryList.catName}</option>
			       		</c:forEach>
			       	</select>
			       	<span class = "_star ">*</span>
			       	</c:if>
			       	<c:if test="${empty categoryList and !empty category }">
			       	<input type="text" disabled="disabled" value="${category.catName}" >
			       	</c:if>
				</li>
				<li style = "overflow:hidden;">
				<span style = "float:left;">栏目图片：</span>
				<div id="img" style= "display:inline-block; float:left;">
					<div class="imgbox">
					    <div class="imgnum">
					        <input id="file1" name="file1" type="file" class="filepath" />
					        <span class="close">X</span>
					        <img src="<c:if test='${!empty classed.classPic and classed.classPic!="" }'>${classed.classPic }</c:if>  <c:if test='${empty classed.classPic or classed.classPic=="" }'>${classed.classPic }${ctx}/assets/img/btn.png</c:if>" class="img1" />
					        <img src="" class="img2" />
					    </div>
					</div>
				</div>
				<div style = "font-size:12px; color:#666; height:202px; line-height:202px;">(请添加大小不超过300K、分辨率为320 * 170 Pix、图片格式为JPG或PNG的图片)</div>
				</li>
				<%-- <li><span>栏目图片：</span><img  src="${classed.classPic}" width="120px" height="100px" alt=""><input type="file" name="file1" id="file1" onchange="previewImg(this)"></li> --%>
				<%-- <li><span>栏目链接：</span><input type="text" value="${classed.linkUrl}" name="linkUrl"></li> --%>
				<%-- <li><span>页面内容：</span><textarea style="width: 100%;min-height: 400px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" name="classDescription" id="myEditor" rows="" cols="">${classed.classDescription}</textarea></li> --%>
				<li><span>排序：</span><input maxlength="3" type="text" name="sortOrder" value="${classed.sortOrder}" onfocus="InitInput.setNumber(this,3,0,0)"><span class = "_star ">*</span></li>
				<li style="display:flex;align-items:center">
				<span>栏目状态：<input name = "isClose" id = "iscolse" type="hidden" value="${classed.isClose}"></span>
					<input style="width: 15px;height: 15px;margin: 0px;" id="radio1" onchange="addIsCloseVal();" type="radio" value="0" name="isclose">
					<em class = "gang" style = "margin-right:15px;">开放</em>  
					<input style="width: 15px;height: 15px;margin: 0px;" id="radio2" onchange="addIsCloseVal();" value="1" type="radio" name="isclose">
					<em class = "gang">关闭</em>
					<span class = "_star ">*</span>
				</li>
			</ul>
	       	<ul class = "adminfo row">
	       		<li>
					<span></span>
					<a onclick="subumintEditClass();" class = "public_btn bg2">保存</a>
					<a style="margin-left: 20px" href="${ctx}/rest/class/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
				</li>
	       	</ul>
		</form>
		</section>
	</div>
    
 <%--        <div>
	       <form action="${ctx}/rest/class/submiteditmenus" method="post">
	       	<table width="400px;" border="1" cellspacing="0" cellpadding="0">
		       	<col width="200px;">
	       		<col width="200px;">
		       	<tr>
			       	<td>所属父级：</td>
			       	<td>
				       	<p><c:if test="${empty parentClass.className}">顶级菜单</c:if> ${parentClass.className}<input name="parentId" type="hidden" value="${classed.parentId}"><input type="hidden" name="classId" value="${classed.classId}"></p>
			       	</td>
		       	</tr>
		       	<tr>
			       	<td>栏目名称：</td>
			       	<td><input type="text" name="className" value="${classed.className}"></td>
		       	</tr>       	
		       	<tr>
			       	<td>栏目属性：</td>
			       	<td id = "prop">
			       	<p>${classed.propertyName}<input name="propertyId" type="hidden" value="${classed.propertyId}" ></p>
			       	</td>
		       	</tr>
		       	<c:if test="${!empty classed.catId}">
		       	<tr id="category">
			       	<td>产品分类：</td>
			       	<td>
			       	<select id="categoryId" name="catId">
			       		<c:forEach items="${categoryList}" var="categoryList">
			       		<option <c:if test="${classed.catId == categoryList.catId}">selected="selected"</c:if> value="${categoryList.catId}">${categoryList.catName}</option>
			       		</c:forEach>
			       	</select>
			       	
			       	</td>
		       	</tr>
		       	</c:if>      
		       	<tr>
			       	<td>栏目连接：</td>
			       	<td><input type="text" name="linkUrl" value="${classed.linkUrl}"></td>
		       	</tr>       	
		       	<tr>
			       	<td>页面内容：</td>
			       	<td><textarea id="myEditor"  name="classDescription" >${classed.classDescription}</textarea></td>
		       	</tr>       	
		       	<tr>
			       	<td>排序：</td>
			       	<td><input type="text" name="sortOrder" value="${classed.sortOrder}"></td>
		       	</tr>       	
		       	<tr>
			       	<td>是否关闭：<input name = "isClose" id = "iscolse" type="hidden" value="${classed.isClose}"></td>
			       	
			       	<td>开<input id="radio1" onchange="addIsCloseVal();" type="radio" value="0" name="isclose">  关<input id="radio2" onchange="addIsCloseVal();" value="1" type="radio" name="isclose"> </td>
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
    $(function(){
    	if("${classed.isClose}" == "1"){
    		$("#radio2").attr("checked","checked");
    	}else if("${classed.isClose}" == "0"){
    		$("#radio1").attr("checked","checked");
    	}
    	
    })
    
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
    
	function addIsCloseVal(){
		if($('input[name="isclose"]:checked ').val()=="0"){
			$("#iscolse").val("0");
		}
		if($('input[name="isclose"]:checked ').val()=="1"){
			$("#iscolse").val("1");
		}
	}
    
    $(function() {
        $(".filepath").on("change",function() {
//          alert($('.imgbox').length);
			FileExt=this.value.substr(this.value.lastIndexOf(".")).toLowerCase();
			
			if(AllowExt!=0&&AllowExt.indexOf(FileExt+"|")==-1) //判断文件类型是否允许上传
			  {
			    ErrMsg="\n该文件类型不允许上传。请上传 "+AllowExt+" 类型的文件，当前文件类型为"+FileExt;
			    $(this).val("");
			    layer.alert(ErrMsg);
			    return false;
			  }
			    ImgFileSize=document.getElementById(this.id).files.item(0).size;
			 	ImgFileSize=Math.round(ImgFileSize*1000/(1024*1024))/1000;//取得图片文件的大小
			 	if(ImgFileSize>1){
			 		layer.alert("图片大小为"+ImgFileSize+"M，请上传小于1M的图片！");
			 		return false;
			 	}
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

    function subumintEditClass() {
        var required = true;
        if ($("input[name='className']").val() == "") {
            layer.msg("请完成必填项");
            required = false;
        }
        if (required) {
            var form = document.getElementById('formId');
            var data = new FormData(form);
            $.ajax({
                url: "${ctx}/rest/class/submiteditmenus",
                type: "post",
                cache: false,
                contentType: false,
                processData: false,
                dataType: "json",
                data: data,
                async: false,
                success: function (result) {
                    if (result.result == "ok") {
                        layer.msg("保存成功！", {icon: 1});
                        setTimeout(function () {
                            window.open("${ctx}/rest/class/index", "contentF");
                        }, 2000);
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