<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
<script type="text/javascript">  
	function check() {  
	    var excel_file = $("#excel_file").val();  
	    if (excel_file == "" || excel_file.length == 0) {  
	    	layer.msg("请选择文件导入！");  
	        return false;  
	    } else {  
	       return true;  
	    }  
	} 
	
	$(document).ready(function () {  
	     var msg="";  
	     if($("#importMsg").text()!=null){  
	         msg=$("#importMsg").text();  
	     }  
	     if(msg!=""){  
	    	 layer.alert(msg);  
	     }  
	});  
</script> 
</head>
<body>
	<div style = "text-align:center; height:80px; line-height:80px; font-size:18px;">批量导入安全库存</div>
     <form action="${ctx }/rest/stock/importStock" method="post" enctype="multipart/form-data" onsubmit="return check();" id="uploadForm">
         <div style = "overflow:hidden;">
         <input style = "width:70px; float:left; margin-left:50px;" id="excel_file" type="file" name="filename" accept="xlsx" size="80"/>
         <input style = "float:right; margin-right:50px;" id="excel_button" type="button" value="导入Excel" onclick="doUpload();"/>
         <div style  = "margin-left:130px; color:#999; font-size:12px; height:23px; line-height:23px;">未选择任何文件</div>
         </div>
         <font id="importMsg" color="red"></font><input type="hidden"/>
     </form>
   <script type="text/javascript">
	   function doUpload() {
		  	 if(!check()) return;
		     var formData = new FormData($( "#uploadForm" )[0]);
		     $.ajax({  
		          url: '${ctx }/rest/stock/importStock' ,  
		          type: 'POST',  
		          data: formData,  
		          dataType: "json",  
		          async: false,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (returndata) {
		        	  if(returndata.flag == 1 ){
		        		  layer.msg("导入成功!",{icon:1});
		        		  $('#excel_button').removeAttr("onclick");
		        		  $('#excel_file').removeAttr("onclick");
			              setTimeout(function(){parent.location.reload();},2000);  
		        	  }else{
		        		  layer.msg("导入出错!!",{icon:2});
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
   </script>
</body>
</html>