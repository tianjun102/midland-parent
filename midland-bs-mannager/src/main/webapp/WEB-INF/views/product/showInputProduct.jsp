<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/zTree.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
<title>发布产品</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">
<link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css"
	type="text/css">
<link rel="shortcut icon" href="${ctx}/assets/app/img/favicon.ico" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<script type="text/javascript"src="${ctx}/assets/scripts/layer/layer.js"></script>
<script type="text/javascript"src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"src="${ctx}/assets/UEditor/ueditor.config.js"></script>
<script type="text/javascript"src="${ctx}/assets/UEditor/ueditor.all.js"></script>
<script type="text/javascript"src="${ctx}/assets/UEditor/lang/zh-cn/zh-cn.js"></script>
</head>
<!-- END HEAD -->
<style>
#redBtn {
	height: 28px;
	line-height: 28px;
	border: 1px solid #dbe2e6;
	border-radius: 4px;
	text-indent: 10px;
	outline-color: #0099e0;
}

.layui-layer{
	top:260px!important;
}
</style>
<SCRIPT type="text/javascript">
		
		
		var setting = {
				check: {
					enable: true,
					chkboxType: { "Y": "sp", "N": "sp" }
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					beforeClick: beforeClick
				}
		};

		var catProNodes =[${categoryData}];
		
		$(document).ready(function(){
			$.fn.zTree.init($("#categoryTree"), setting, catProNodes);
		});
		
		function beforeClick(treeId, treeNode, clickFlag) {
			var  chirdCount   = treeNode.chirdCount;
			if(chirdCount>0){
				layer.msg("存在子分类，不能被选择！",{icon:7});
			}else{
				$("input[name='catId']").val(treeNode.id);
				$("input[name='catName']").val(treeNode.name);
				hideTree();
				showProperty();
			}
		
		}
		
		function showTree(){
			$("#showCatDiv").show();
		}
		
		function hideTree(){
			$("#showCatDiv").hide();
		}
</SCRIPT>
<!-- BEGIN BODY -->


<body>
<div class = "box">
	<section class="content" style = "width:auto;">
		<p class="detail-title">
			<span>产品发布</span>
		</p>
		<form action="${ctx}/rest/product/forInputProduct"
			id="InputProductForm" enctype="multipart/form-data" method="post">
			<ul class="adminfo row">
				<li><span>分类：</span> <input name="catName" type="text"
					onclick="showTree()" readonly="readonly" /> <input name="catId"
					type="hidden" id="catId" /> <label style="color: red"  class = "_star ">*</label></li>
				<li id="showCatDiv"
					style="display: none; padding-top: 0px; padding-left: 70px; position: relative;">
					<style>
 		  				.content ul.adminfo li:last-child>a:not(:nth-child(2)){
 		  					margin-left: 0px;
 		  				}
 		  			</style>
					<div class="zTreeDemoBackground left"
						style="position: absolute; top: -5px;">
						<ul id="categoryTree" class="ztree" style="width: 235px;"></ul>
					</div> <img src="${ctx}/assets/img/Closed_16px.png" alt="关闭"
					style="vertical-align: top; position: absolute; left: 318px; top: 5px;"
					onclick="hideTree()">
				</li>
				<li><span>产品名称：</span><input type="hidden" value="1"
					name="prodStatus" /> <input type="text" name="productName" /> <label
					style="color: red"  class = "_star ">*</label></li>
				<li><span>产品编码：</span><input type="text" name="productCode" /><label
					style="color: red"  class = "_star ">*</label></li>
				<li><span>市场价：</span><input type="text" name="marketPrice"><label
					style="color: red"  class = "_star ">*</label></li>
				<li><span>进店价：</span><input type="text" name="salePrice"><label
					style="color: red"  class = "_star ">*</label></li>
				<li><span>起订数：</span><input type="text" name="miniNum"><label
					style="color: red"  class = "_star ">*</label></li>
				<li><span>单位：</span><input type="text" name="unit"><label
					style="color: red"  class = "_star ">*</label></li>
				<li><span>质保维护：</span> <textarea name="warrantyNotes"
						id="warrantyNotes"
						style="width: 45%; height: 150px; resize: none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
					<label style="color: red"  class = "_star ">*</label></li>
				<li><span>图片：</span>
					<div id="img" style="overflow:hidden;">
						<div class="imgbox1">
							<div class="imgnum" id="showImgDiv">
								<input name="file1" type="file" class="filepath1"
									onchange="imgChange(this)" /> <span class="close1"
									onclick="imgDelete(this)">X</span> 
									<img src="${ctx}/assets/img/btn.png" class="img1" style="width: 100%;"> 
									<img src="" class="img2" />
							</div>
						</div>
					</div>
					<div style = "font-size:12px; color:#666; margin-top:10px; text-indent: 70px;">(请添加大小不超过500K、分辨率为416 * 416 Pix、图片格式为JPG或PNG的图片)</div>
				</li>
				<li><span>产品属性：</span>
				<div id="showDiv" style='width: 35%; overflow:hidden;'>
						<span style = "height:28px; line-height:28px;">请选择分类</span>
					</div></li>
				<li><span>产品描述：</span>
				<textarea name="prodDetails" id="myEditor"
						style="width: 92%; min-height: 350px; float:left;"></textarea></li>
				<li><span>上架状态：</span> <input type="radio" class="redBtn"
					name="isNowTime" value="1" checked="checked"
					onclick="upProduct(this.value)" /><span style = "text-align:left;">立即上架</span>&nbsp;&nbsp; <input
					type="radio" name="isNowTime" value="2"
					onclick="upProduct(this.value)" style = "margin-left:30px;"/><span style = "text-align:left;">定时上架</span>&nbsp;&nbsp;
					<div
						style="vertical-align: middle; display: none;"
						id="planSaleTimeDiv">
						<input class="Wdate date"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d %H:%m'})"
							name="planSaleTime" id="planSaleTime" /> <label
							style="color: red"  class = "_star ">*</label>
					</div></li>
				<li><span>橱窗推荐：</span> <input type="radio" class="redBtn"
					name="isRecommend" value="1" checked="checked" /><span style = "text-align:left;">推荐</span>&nbsp;&nbsp;
					<input style = "margin-left:30px;" type="radio" name="isRecommend" value="0" /><span style = "text-align:left;">不推荐</span>
				</li>
				<li><span>浏览授权：</span> <input type="radio" class="redBtn"
					name="isShowAll" value="1" checked="checked" /><span style = "text-align:left;">全部授权</span>&nbsp;&nbsp;
					<input style = "margin-left:30px;" type="radio" name="isShowAll" value="0" /><span style = "text-align:left;">部分授权</span>
				</li>
				<li><span></span> <a target="contentF" class="public_btn bg2"
					id="save" onclick="formSubmit()">发布</a> <a
					style="margin-left: 20px"
					href="${ctx}/rest/product/showProductIndex" target="contentF"
					class="public_btn bg3" id="cancel">取消</a></li>
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
	 }
		
	 function imgChange(img){
		 
		FileExt=img.value.substr(img.value.lastIndexOf(".")).toLowerCase();
		console.log(FileExt);
		if(AllowExt!=0&&AllowExt.indexOf(FileExt+"|")==-1){ //判断文件类型是否允许上传
		    ErrMsg="\n该文件类型不允许上传。请上传 "+AllowExt+" 类型的文件，当前文件类型为"+FileExt;
		    $(img).val("");
		    layer.alert(ErrMsg);
		    return false;
		}
	    ImgFileSize=img.files.item(0).size;
	 	ImgFileSize=Math.round(ImgFileSize*1000/(1024*1024))/1000;//取得图片文件的大小
	 	if(ImgFileSize>1){
		 		layer.alert("图片大小为"+ImgFileSize+"M，请上传小于1M的图片！");
		 		return false;
	 	}
		var srcs = getObjectURL(img.files[0]);   //获取路径
	
	  	var index = $("#img input").length+1;
	
	    var htmlImg='<div class="imgbox1">'+
	        '<div class="imgnum1">'+
	            '<input type="file" name="file'+index+'" class="filepath1"  onchange="imgChange(this)"/>'+
	            '<span class="close1" onclick="imgDelete(this)">X</span>'+
	            '<img src="${ctx}/assets/img/btn.png" class="img1" />'+
	            '<img src="" class="img2" />'+
	        '</div>'+
	    '</div>';
	    
	    /**
	     * 判断是新增操作，还是编辑操作
	     */
	    var _name = $(img).parent()[0].children[0].name;
	    var _index = _name.substr(4,1);

	    if(_index >= $("#img input").length){
	    	
	    	
	    	//新增操作添加图片，显示
	        $(img).parent().find(".img2").attr('src',srcs);
	        $(img).parent().parent().after(htmlImg);
	        $(img).parent().find(".img1").hide();   //this指的是input
	        $(img).parent().find('.close1').show();
	        	            
	        if($('.imgbox1').length==6){
	        	$("#img div:last").hide();
	        }            	
	    }else{
	    	//编辑操作，只更新src
	    	$(img).parent().find(".img2").attr('src',srcs);
	    }        		    	
	 }
		    
	function imgDelete(span){
	         $(span).hide();     //this指的是span
	         $(span).nextAll(".img2").hide();
	         $(span).nextAll(".img1").show();
	         $(span).parent().parent().remove();
	         $("#img div:last").show();
	         $("#img input:file").each(function(j) {
		    		$(this).attr("name","file"+(j+1));
	    	})
    }
	    
	    
	   
	
		UE.getEditor('myEditor');    
	
	function retrunOptionStr(name,value,arr,propId,i){
		var text = "<tr><td style='width:35%;text-align:left;'>"+name+"</td><td><input type='hidden' name='attrList["+i+"].propId' value='"+propId+"'/><select  class='dropdown' name='"+value+"' >";
		for(var i=0;i<arr.length;i++){
			text = text + "<option value='"+arr[i]+"'>"+arr[i]+"</option>";
		}
			text = text + "</td></tr>"
		return text;
	}
	
	function retrunCheckBoxStr(name,value,arr,propId,i){
		var text = "<tr><td style='width:35%;text-align:left;'>"+name+"</td><td><input type='hidden' name='attrList["+i+"].propId' value='"+propId+"'/><input type='hidden' name='"+value+"' />";
		for(var i=0;i<arr.length;i++){
			text = text + "<input type='checkbox' name='show"+value+"' value='"+arr[i]+"'>"+arr[i];
		}
			text = text + "</td></tr>"
		return text;
	}
	
	
	function getPropertyTab(result,i){
		var propId =result.propId;			
		var type = result.propType;
		var name = result.propName;
		var value = result.propValue;
		var enabled= result.enabled;
		var strs= new Array(); //定义一数组
		var context = ""; //返回内容字符串
		if("text"==type){//类型为text时
			context = context +  "<tr><td style='width:35%;text-align:left;'>"+name+"</td><td><input type='text' name='attrList["+i+"].propValue'/><input type='hidden' name='attrList["+i+"].propId' value='"+propId+"'/></td></tr>";
		}else{
			if(type=="option"){ //类型为option时
				 strs=value.split("||"); //字符分割 
				 context = context +  retrunOptionStr(name,"attrList["+i+"].propValue",strs,propId,i);
			}else{//类型为checkbox时
				 strs=value.split("||"); //字符分割 
				 context = context +  retrunCheckBoxStr(name,"attrList["+i+"].propValue",strs,propId,i);
			}
		}
		return context;
	}
	
	function showProperty(){
		$("#showDiv").empty();
		var catId =  $("input[name='catId']").val();
		var context= "";
		 if(catId!=""){
			$.ajax({ 
				url:"${ctx}/rest/product/searchPropertyListAll",
				type : 'post', 		//数据发送方式 
				dataType : 'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json) 
				data :{"catId":catId},
				async:false,
				success : function(result) {
					if(result.result.length>0){
	    				for(var i=0;i<result.result.length;i++){
	    					var enabled= result.result[i].enabled;  //是否为必须属性
	    					var propId = result.result[i].propId;			
	    					var name =  result.result[i].propName;
	    					context = context + getPropertyTab(result.result[i],i);
	    				}
	    				$("#showDiv").append("<table id='showTable' class='table table-bordered table-add' style='float:left;'>"+context+"</table>");
					}else{
						$("#showDiv").append("<span>请选择有效分类</span>");
					}
					
				}
			});
		} 
	}
	
	function formChange(){
		var arr = $("input[type='checkbox']");
		var content = "";
		for(var i = 0; i<arr.length;i++){
			 var str= "";
			 var checkbox = arr[i];
			 var boxId = arr.eq(i).attr("name");
			 var valueId= boxId.substr(4);
			 if(content.indexOf(boxId)!=-1){
				 continue;
			 }
			 content =  content + boxId ;
			 var valueStr = "";
			 $("input[name='"+boxId+"']:checked").each(function () {
				 valueStr = valueStr +  " "+this.value 
	         });
			 $("input[name='"+valueId+"']").val(valueStr.trim());
		}
	}
	
	
	function  changeFile(){
		$("input[type='file']").each(function (){
			if($(this).val==""){
				$(this).remove();
			}
		})
		$("#img div:last").remove();
	}
	
	
	function formSubmit(){
		var b = checkForm();
		if(!b){
			return false;
		}
		formChange();
		changeFile();
		var isNowTime = $("input:radio:checked").val();//上架状态
		var msg = "";
		if(isNowTime=="2"){
			msg="定时上架";
		}else{
			msg="立即上架";
		}
		var form = document.getElementById("InputProductForm");
		var data = new FormData(form);
		$.ajax({ 
			url:"${ctx}/rest/product/forInputProduct",
			type : "post",
			cache : false,
			contentType : false,
			processData : false,
			dataType: "json",
			data : data,
			async : false,
			success : function(result) {
				if(result.flag==1){
					layer.msg(msg+"成功！",{icon:1});
					$('#save').removeAttr("onclick");
					setTimeout(function(){window.open("${ctx}/rest/product/showProductIndex","contentF");},2000);
				}else{
					layer.msg(msg+"失败！",{icon:2});
				}
			},
			error: function(){
				layer.msg(msg+"失败！",{icon:2});
			}
		});
	}
	
	function upProduct(obj){
		if(obj=="2"){
			$("input[name='prodStatus']").val("3");
			$("#planSaleTimeDiv").show();
			$("#planSaleTimeDiv").css("display","inline-block")
		}else{
			$("input[name='prodStatus']").val("1");
			$("#planSaleTimeDiv").hide();
		}
	}
	
	//数据校验
	function checkForm(){
		var catId = $("input[name='catId']").val();  // 分类
		var productName = $("input[name='productName']").val();  // 产品名称
		var productCode = $("input[name='productCode']").val(); //产品编码
		var marketPrice = $("input[name='marketPrice']").val();//市场价
		var salePrice = $("input[name='salePrice']").val();//进店价
		var miniNum = $("input[name='miniNum']").val();//起订数
		var unit = $("input[name='unit']").val();//单位
		var warrantyNotes = $("#warrantyNotes").val();//质保维护
		var myEditor = $("#myEditor").val();//产品描述
		var isNowTime = $("input:radio:checked").val();//上架状态
		var planSaleTime = $("input[name='planSaleTime']").val();//上架状态
		//分类
		if(catId.trim() =="" || catId==null){
			layer.msg("分类不能为空！");
			return false;
		}
		//产品名称
		if(productName.trim() =="" || productName==null){
			layer.msg("产品名称不能为空！");
			return false;
		}
		if(!getStrLenght(productName,50)){
			layer.msg("产品名称长度不能超过50个字符！");
			return false;
		}
		//产品编码
		if(productCode.trim() =="" || productCode==null){
			layer.msg("产品编码不能为空！");
			return false;
		}
		if(!getStrLenght(productCode,20)){
			layer.msg("产品编码长度不能超过20个字符！");
			return false;
		}
		//市场价
		if(marketPrice.trim() =="" || marketPrice==null){
			layer.msg("市场价不能为空！");
			return false;
		}
		if(!getStrLenght(marketPrice,10)){
			layer.msg("市场价长度不能超过10个字符！");
			return false;
		}
		if(!moneyValidate(marketPrice) || marketPrice<=0){
			layer.msg("请输出正确的市场价！");
			return false;
		}
		//进店价
		if(salePrice.trim() =="" || salePrice==null){
			layer.msg("进店价不能为空！");
			return false;
		}
		if(!getStrLenght(salePrice,10)){
			layer.msg("进店价长度不能超过10个字符！");
			return false;
		}
		if(!moneyValidate(salePrice) || salePrice<=0){
			layer.msg("请输出正确的进店价！");
			return false;
		}
		//起订数
		if(miniNum.trim() =="" || miniNum==null){
			layer.msg("起订数不能为空！");
			return false;
		}
		if(isNaN(miniNum) || miniNum<=0 ){
			layer.msg("请输入正确的起订数！");
			return false;
		}
		//单位
		if(unit.trim() =="" || unit==null){
			layer.msg("单位不能为空！");
			return false;
		}
		if(!getStrLenght(unit,30)){
			layer.msg("单位长度不能超过30个字符！");
			return false;
		}
		//质保维护
		if(warrantyNotes.trim() =="" || warrantyNotes==null){
			layer.msg("质保维护不能为空！");
			return false;
		}
		if(!getStrLenght(warrantyNotes,500)){
			layer.msg("质保维护不能超过500个字符！");
			return false;
		}
		/* //产品描述
		if(myEditor.trim() =="" || myEditor==null){
			layer.tips("产品描述不能为空！", "#myEditor",{tips:1});
			return false;
		} */
		var imgLength = $("input[type='file']").length;
		if(imgLength<=1){
			layer.msg("产品图片不能为空！");
			return false;
		}
		//上架状态
		if(isNowTime == 2){
			if(planSaleTime.trim() =="" || planSaleTime==null){
				layer.tips("上架状态不能为空！");
				return false;
			}
		}
		
		return true;
	}
	
	
	 //长度校验 
	function getStrLenght(message,MaxLenght) {
        var strlenght = 0; //初始定义长度为0
        var txtval = message.trim();
        for (var i = 0; i < txtval.length; i++) {
               strlenght = strlenght + 1; //一个字符
        }
        return strlenght > MaxLenght ? false : true;
    }
	 
	//金额校验  
	function moneyValidate(money){
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		return reg.test(money);
	} 
    </script>
</html>