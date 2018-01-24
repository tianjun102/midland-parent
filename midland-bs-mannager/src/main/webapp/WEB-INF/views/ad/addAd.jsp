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
				<span>广告发布</span>
			</p>
		<form id="formId" action="${ctx}/rest/ad/addAd" method="post" enctype="multipart/form-data" method="post">
		<input type="hidden" name="catId" value="" id="cat_Id" >
		<input type="hidden" name="isAll" value="">
			<ul class = "adminfo row">
				<li><span>广告标题：</span><input maxlength="50" type="text" name="adName"><span class = "_star ">*</span></li>
				<li><span>展示方式：</span>
					<select name="type" id="selectType" class="dropdown" onchange="selectTypes();">
						<option value="" class="label">请选择</option>
					    <option value="0">单页面</option>
					    <option value="1">产品/产品类型</option>
					    <option value="2">外网链接</option>
					</select><span class = "_star ">*</span>
				</li>
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
				<div style = "font-size:12px; color:#666; height:202px; line-height:202px;">(请添加大小不超过500K、分辨率为1200 * 240 Pix、图片格式为JPG或PNG的图片)</div>
				</li>
            <!--<li><span>大图：</span><img  src="" width="120px" height="100px" alt=""><input type="file" name="file1" id="file1" onchange="previewImg(this)"></li>
				<li><span>小图：</span><img  src="" width="120px" height="100px" alt=""><input type="file" name="file2" id="file2" onchange="previewImg(this)"></li> -->
				<!-- <li><span>详情：</span><textarea style="width:590px;height:100px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" name="adInfo" id="myEditor" rows="" cols=""></textarea></li> -->
				<li id="picLike"><span>图片链接：</span>
				<input id="adId" name="adLinkurl" type="text" value="" > 
				</li>
				<li id="textArea" style="display: none;"><textarea style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;" name="adInfo" id="myEditor" rows="" cols=""></textarea></li>
				<li id="searchbatton" style="display: none;"><span>产品信息：</span>&nbsp;&nbsp;<input onclick="searchProduct(1)" style="width: 100px;text-indent: 0px; float:left;" class = "public_btn bg1" type="button" name="inquery" id="inquery" value = "查询商品"/></li>
				<li id="catInfo" style="display: none;"><span>分类：</span>
				<div style="display:inline-block" id="catName">
				</div>
				</li>
				<div id="prodInfo" style="display: none;" class = "table-responsive m40">
					<table class="table table-bordered table-add">
					<thead>
				       	<tr>
					       	<th>商品编号</th>
					       	<th>商品名称</th>
					       	<th>商品分类</th>
					       	<th>商品价格</th>
					       	<th>操作</th>
				       	</tr>
			       	</thead>
			       	<tbody id="productList">
			       	</tbody>
					</table>
				</div>
				
				<li><span>排序：</span><input maxlength="3" type="text" name="sortOrder" onfocus="InitInput.setNumber(this,3,0,0)"><span class = "_star ">*</span></li>
				<li style="display:flex;align-items:center">
				<span>广告状态：<input name = "enabled" id = "enabled" type="hidden" value=""></span>
					<input style="width: 15px;height: 15px;margin: 0px;" onchange="addIsShow();" type="radio" name="isshow" value="1">
					<em class = "gang" style = "margin-right:15px;">开放</em>  
					<input style="width: 15px;height: 15px;margin: 0px;" onchange="addIsShow();" type="radio" name="isshow" value="0">
					<em class = "gang">关闭</em> 
					<span class = "_star ">*</span>
				</li>
				<li><span>发布日期：</span><input onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" name="addTime"><span class = "_star ">*</span></li>
			</ul>
	       	<ul class = "adminfo row">
	       		<li>
					<span></span>
					<a onclick="subumintAd();" target="contentF" class = "public_btn bg2">保存</a>
					<a style="margin-left: 20px" href="${ctx}/rest/ad/listindex" target="contentF" class="public_btn bg3" id="cancel">取消</a>
				</li>
	       	</ul>
		</form>
		</section>
	</div>
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
	function addIsShow(){
		if($('input[name="isshow"]:checked ').val()=="0"){
			$("#enabled").val("0");
		}
		if($('input[name="isshow"]:checked ').val()=="1"){
			$("#enabled").val("1");
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
	
	
	//搜索商品
    function searchProduct(flag){
    	var isAll = $("input:hidden[name='isAll']").val();
    	var catId = $("#cat_Id").val();
    	layer.open({
    		type: 2,
    		title: ['产品搜索'],
    		shadeClose: false,
    		shadeClose: false,
    		shade: 0.5,
    		area: ['1050px', '500px'],
    		content: "${ctx}/rest/ad/enterSearchProduct?flag="+flag+"&catId="+catId+"&isAll="+isAll
    	
    		});
        }
	
    function delecte(ths){
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['440px','250px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['是否删除'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none;">'+
					'<dl>'+
						'<dt style = "text-align: center; margin-top: 20px;"><img src="${ctx}/assets/img/!.png"/></dt>'+
						'<dd>'+
							'<p style = "text-align: center; margin-top: 20px;">您确定要删除当前用户列表吗</p>'+
						'</dd>'+
					'</dl>'+
				'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				    var catId =  $("#cat_Id").val();
				    $(ths).parent().parent().remove();
		        	var array = new Array();
		        	$("input:hidden[name$='].prodId']").each(function(){
		        		array.push($(this).val());
		        	});
		        	if($("input:hidden[name$='].prodId']").size()>0){
		        	$("#adId").val("/ad/adinfo?productId="+array+"&catId="+catId);
		        	}else{
		        		$("#adId").val("");
		        	}
				    layer.close(index);
					layer.msg("删除成功！",{icon:1});
				 }
				,success: function (layero) {
				      var btn = layero.find('.layui-layer-btn');
				      btn.css('text-align', 'center');
				  }
			  });
	   
   }
    
    function deleteText(){
    	
    	
    	
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['440px','250px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['是否删除'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none;">'+
					'<dl>'+
						'<dt style = "text-align: center; margin-top: 20px;"><img src="${ctx}/assets/img/!.png"/></dt>'+
						'<dd>'+
							'<p style = "text-align: center; margin-top: 20px;">将删除添加的商品</p>'+
						'</dd>'+
					'</dl>'+
				'</section>',
			  btn:['确定','取消'],
			  btnAlign: 'c',
			  yes: function(index){
			    	$("#cat_Id").val("");
			    	$("input[name='isAll']").val("");
			    	$("#catName").html("");
			    	$("#productList").find("tr").remove();
			    	$("#adId").val("");
			    	layer.close(index);
			    	layer.msg("删除成功！",{icon:1});
				 }
				,success: function (layero) {
				      var btn = layero.find('.layui-layer-btn');
				      btn.css('text-align', 'center');
				  }
			  });
    			
    }
    
    function selectTypes(){
    	 if($("#selectType option:selected").val()==1){
    		$("#searchbatton").show();
    		$("#catInfo").show();
    		$("#prodInfo").show();
    		$("#textArea").hide();
    	}else if($("#selectType option:selected").val()==0){
    		$("#searchbatton").hide();
    		$("#catInfo").hide();
    		$("#prodInfo").hide();
    		$("#picLike").hide();
    		$("#textArea").show();
    	}else{
    		$("#searchbatton").hide();
    		$("#catInfo").hide();
    		$("#prodInfo").hide();
    		$("#textArea").hide();
    		$("#picLike").show();
    	} 
    	
    }

    function subumintAd() {
        var form = document.getElementById('formId');
        var data = new FormData(form);
        //var files=$("input[type='file']");
        /* $.each(files, function(i, file) {

            data.append(file.name, file.files[0]);
        }) */
        var required = true;
        if ($("input[name='adName']").val() == "" || $("select[name='type']").val() == "" || $("input[name='sortOrder']").val() == "" || $("#enabled").val() == "" || $("input[name='addTime']").val() == "") {
            layer.msg("请完成必填项");
            required = false;
        }
        if (required) {
            $.ajax({
                url: "${ctx}/rest/ad/addAd",
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
                            window.open("${ctx}/rest/ad/listindex", "contentF");
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
    
    
    
    
    </script>
</html>