<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dli">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
<style type="text/css">
.content ul.adminfo li>span {
	width: 90px;
}

.cbox input {
	
}
</style>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/scripts/layer/layer.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/scripts/jquery.easydropdown.js"></script>
</head>
<body>
<div class = "box" style="min-width:auto;">
	<section class="content" style="width:auto;">
	<p class="detail-title">
		<span>新增客户</span>
	</p>
	<form action="${ctx}/rest/cust/addCust" method="post"
		enctype="multipart/form-data" id="addForm">
		<ul class="adminfo row _align">
			<li><span>客户类别：</span><select name="custType" id="custType"
				class="dropdown" onchange="showHide(this.value);">
					<option value="1">渠道服务商</option>
					<option value="2">终端服务商</option>
			</select><span
				class="_star">*</span></li>
			<li id="d1"><span>渠道商分类：</span> <select name="distType"
				id="distType" class="dropdown">
					<option value="VOXA">直属销售服务分公司</option>
					<option value="VOXB">股东省区渠道服务商</option>
					<option value="VOXC">普通省区渠道服务商</option>
					<option value="VOXD">4S店渠道服务商</option>
			</select><span
				class="_star">*</span></li>
			<li id="d2"><span>区域：</span> <select class="dropdown"
				id="areaId" name="areaId">
					<option value="" class="">请选择</option>
					<c:if test="${!empty areas }">
						<c:forEach items="${areas }" var="rost">
							<option value="${rost.areaId }">${rost.areaName }</option>
						</c:forEach>
					</c:if>
			</select><span
				class="_star">*</span>
			</li>
			<li id="d3" style="display: none"><span>上级客户：</span> <select
				class="dropdown" id="custParentId" name="custParentId">
					<option value="" class="">请选择</option>
					<c:if test="${!empty custParentList }">
						<c:forEach items="${custParentList }" var="custParent">
							<option value="${custParent.custId }">${custParent.custName }</option>
						</c:forEach>
					</c:if>
			</select><span
				class="_star">*</span></li>
			<!-- <li><span>客户代码：</span><input type="text" name="custCode" id="custCode" onblur="checkCustCode();"/></li> -->
			<li><span>客户简称：</span><input type="text" name="custName"
				id="custName" onblur="checkCustName();" maxlength="20" 
					onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" 
					onpaste="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" 
					oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')"/><span
				class="_star">*</span></li>
			<li><span>公司全称：</span><input type="text" name="custFullName"
				id="custFullName" maxlength="50"/><span
				class="_star">*</span></li>
			<li><span>state：</span><select name="status" id="status"
				class="dropdown">
					<option value="1"
						<c:if test="${status==1}">selected="selected"</c:if>>合作中</option>
					<option value="0"
						<c:if test="${status==0}">selected="selected"</c:if>>停用</option>
			</select><span
				class="_star">*</span></li>
			<li><span style = "float:left;">备注：</span> <!-- <textarea name="custNote" id="custNote" rows="" cols="" placeholder="备注说明"></textarea> -->
				<textarea id="custNote" name="custNote"
					style="width: 250px; height: 110px; resize: none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"
					rows="" cols=""></textarea></li>
			<li><p>
					<b>联系方式</b>
				</p></li>
			<li><span>联系人：</span><input type="text" name="contactName"
				id="contactName" maxlength="50"/><span
				class="_star">*</span></li>
			<li><span>联系电话：</span><input type="text" name="contactPhone"
				id="contactPhone" onblur="checkPhone();" /><span
				class="_star">*</span></li>
			<li><span>收货地址：</span> <!-- 省 -->
				<p id="province" style="display: inline-block;height: 38px;">
					<label for="pop-14"></label> <input type="hidden" name="provinceId">
					<input type="hidden" name="regionSn"> <input type="hidden"
						name="provinceName">
					<!-- 第一次进页面加载省 -->
					<select id="provinces" onchange="initProvince()"
						style="height: 100%; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
						<option>请选择</option>
						<c:forEach items="${provinces}" var="province">
							<option
								value="${province.regionId},${province.parentId},${province.regionSn}">${province.regionName}</option>
						</c:forEach>
					</select>
				</p> <!-- 市 -->
				<p id="city" style="margin-left: 20px; display: inline-block; height: 38px;">
					<label for="pop-15"></label> <input type="hidden" name="cityId">
					<input type="hidden" name="cityName"> <select id="citys"
						onchange="initCity()" style="height: 100%; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
						<option value="">请选择</option>
					</select>
				</p>
				<p id="district"
					style="margin-left: 20px; display: inline-block; height: 38px;">
					<label for="pop-16"></label> <input type="hidden" value=""
						name="distId"> <input type="hidden" value=""
						name="distName"> <select id="districts"
						onchange="initDistrict()"
						style="height: 100%; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
						<option value="">请选择</option>
					</select>
				</p></li>
			<li>
				<span>详细地址：</span>
				<input type="text" name="addressDetail" id="addressDetail" maxlength="100"/> 
				<span class="_star">*</span>
				<label style="line-height: 42px; margin-left: 10px;">
				<!-- <input style="" id="isDefault" name="isDefault" type="checkbox" checked = "true" value="1" />默认 -->
				</label>
			</li>
			<li><p>
					<b>企业信息</b>
				</p></li>
			<li><span>企业法人：</span><input type="text" name="realName"
				id="realName" maxlength="20"/><span
				class="_star">*</span></li>
			<li><span>法人电话：</span><input type="text" name="custTel"
				id="custTel" onblur="checkTel();" /><span
				class="_star">*</span></li>
			<li><span>营业执照号： </span><input type="text"
				name="businessLicense" id="businessLicense" maxlength="20"/><span
				class="_star">*</span></li>
			<li><span>营业执照：</span>
				<div id="img" style="display: inline-block;">
					<div class="imgbox">
						<div class="imgnum">
							<input id="buPic" name="buPic" type="file" class="filepath" /> <span
								class="close">X</span> <img src="${ctx}/assets/img/btn.png"
								class="img1" alt="营业执照" /> <img src="" class="img2" />
						</div>
					</div>
				</div><span class="_star">*</span></li>
			<li><span>注册地址：</span><input type="text" name="registAddress"
				id="registAddress" maxlength="256"/><span
				class="_star">*</span></li>
			<li><span>经营性质：</span><select name="enterAttr" id="enterAttr"
				class="dropdown">
					<option value="" class="">请选择</option>
					<c:if test="${!empty enterAttrs }">
						<c:forEach items="${enterAttrs }" var="rost">
							<option value="${rost.propId }">${rost.propValues }</option>
						</c:forEach>
					</c:if>
			</select> <span
				class="_star">*</span>
			</li>
			<li><span>经营产品：</span>
				<div id="cbox" class="cbox"
					style="display: inline-block; line-height: 38px;">
					<c:if test="${!empty enterProds }">
						<c:forEach items="${enterProds }" var="rost">
							<label> <input style="" type="checkbox" name="enterProd"
								value="${rost.propValues }" /> ${rost.propValues }
							</label>
						</c:forEach>
					</c:if>
				</div> 
			</li>
			<li><span></span> <a target="contentF" class="public_btn bg2"
				id="save" onclick="saveData()">保存</a> <a
				href="${ctx}/rest/cust/custIndex" style="margin-left: 20px"
				class="public_btn bg3" id="cancel">取消</a></li>
		</ul>
	</form>
	</section>
</div>
	<script type="text/javascript">
		function addMore() {

			$("#addCbox").show();
			$("#prodName").show();
			$("#add").hide();

		}
		function addCheckbox() {
			var prodName = $("#prodName").val();
			var prodNames = $("#prodNames").val();

			if (prodName.trim() == "") {
				layer.tips("请输入经营产品名！", "input[name='prodName']", {
					tips : 1
				});
				return false;
			}
			if (prodNames == "") {
				prodNames = prodName;
			} else {
				prodNames = prodNames + "," + prodName;
			}

			$("#prodNames").val(prodNames);

			var oCheckbox = document.createElement("input");
			var myText = document.createTextNode(prodName);
			oCheckbox.setAttribute("type", "checkbox");
			oCheckbox.setAttribute("name", "enterProd");
			oCheckbox.setAttribute("value", prodName);

			var mydiv = document.getElementById("cbox");
			mydiv.appendChild(oCheckbox);
			mydiv.appendChild(myText);

			$("#addCbox").hide();
			$("#prodName").hide();
			$("#add").show();
		}

		function delCheckbox(t) {

			t.parentNode.parentNode.removeChild(t.parentNode);
		}

		//图片上传
		$(function() {
			$(".filepath").on("change", function() {
				var srcs = getObjectURL(this.files[0]); //获取路径
				$(this).nextAll(".img1").hide(); //this指的是input
				$(this).nextAll(".img2").show(); //fireBUg查看第二次换图片不起做用
				$(this).nextAll('.close').show(); //this指的是input
				$(this).nextAll(".img2").attr("src", srcs); //this指的是input
				$(".close").on("click", function() {
					$(this).hide(); //this指的是span
					$(this).nextAll(".img2").hide();
					$(this).nextAll(".img1").show();
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

		function checkData() {
			var custType = $("#custType option:selected").val();
			var distType = $("#distType option:selected").val();
			var areaId = $("#areaId option:selected").val();
			var custParentId = $("#custParentId option:selected").val();
			var custName = $("input[name='custName']").val();
			var custFullName = $("input[name='custFullName']").val();
			var contactName = $("input[name='contactName']").val();
			var provinceId = $("input[name='provinceId']").val();
			var cityId = $("input[name='cityId']").val();
			var distId = $("input[name='distId']").val();
			var addressDetail = $("input[name='addressDetail']").val();
			var realName = $("input[name='realName']").val();
			var businessLicense = $("input[name='businessLicense']").val();
			var buPic = $("#buPic").val();
			var registAddress = $("input[name='registAddress']").val();
			var enterAttr = $("#enterAttr option:selected").val();
			var enterProd = $("input[name='enterProd']:checked").size();

			var flag = 0;
			if (!checkIsEmpty(custType, "custType", "客户类型")) {
				flag = flag + 1;
			}
			;
			if (custType == 1 && !checkIsEmpty(distType, "distType", "渠道商类型")) {
				flag = flag + 1;
			}
			;
			if (custType == 1 && !checkIsEmpty(areaId, "areaId", "区域")) {
				flag = flag + 1;
			}
			;
			if (custType == 2
					&& !checkIsEmpty(custParentId, "custParentId", "上级客户")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(custName, "custName", "客户名称")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(custFullName, "custFullName", "公司全称")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(contactName, "contactName", "联系人")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(provinceId, "addressDetail", "地址")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(cityId, "addressDetail", "地址")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(distId, "addressDetail", "地址")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(addressDetail, "addressDetail", "地址")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(realName, "realName", "法人姓名")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(businessLicense, "businessLicense", "营业执照号")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(buPic, "buPic", "营业执照")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(registAddress, "registAddress", "注册地址")) {
				flag = flag + 1;
			}
			;
			if (!checkIsEmpty(enterAttr, "enterAttr", "经营性质")) {
				flag = flag + 1;
			}
			;
			/* if (enterProd == 0) {
				flag = flag + 1;
			}
			;
 */
			if (flag > 0) {
				return false;
			}
			return true;

		}

		function checkIsEmpty(a, b, c) {
			if (a == null || a.trim() == "") {
				layer.tips(c + "不能为空！", "input[name='" + b + "']", {
					tips : 2
				});
				return false;
			}
			return true;
		}

		//保存数据
		function saveData() {
			if (checkData() && checkCustName() && checkPhone() && checkTel()) {
				$("#addForm").submit();
				return true;
			}
			layer.msg("请输入带*号的必填项！");
			return false;
		}

		//检查客户编号是否已使用
		function checkCustCode() {
			var custCode = $("#custCode").val();
			if (custCode.trim() == "" || custCode == null) {
				layer.tips("客户编号不能为空！", "input[name='custCode']", {
					tips : 2
				});
				return false;
			}
			var bool = true;
			$.ajax({
				type : "post",
				url : "${ctx }/rest/cust/checkUnique",
				async : false, // 此处必须同步
				dataType : "json",
				data : {
					"custCode" : custCode
				},
				success : function(xmlobj) {
					if (xmlobj.flag == 1) {
						layer.tips("客户编号已存在！", "input[name='custCode']", {
							tips : 2
						});
						bool = false;
					} else {
						bool = true;
					}
				}
			});
			return bool;
		}

		//检查客户名称是否已使用
		function checkCustName() {
			var custName = $("#custName").val();
			if (custName == null || custName.trim() == "") {
				layer.tips("客户名称不能为空！", "input[name='custName']", {
					tips : 2
				});
				return false;
			}
			var bool = true;
			$.ajax({
				type : "post",
				url : "${ctx }/rest/cust/checkUnique",
				async : false, // 此处必须同步
				dataType : "json",
				data : {
					"custName" : custName
				},
				success : function(xmlobj) {
					if (xmlobj.flag == 1) {
						layer.tips("客户名称已存在！", "input[name='custName']", {
							tips : 2
						});
						bool = false;
					} else {
						bool = true;
					}
				}
			});
			return bool;
		}

		//检查手机号格式
		function checkPhone() {
			var reg = /^1[3,4,5,7,8]\d{9}$/;
			var contactPhone = $("input[name='contactPhone']").val();
			if (contactPhone.trim() == '') {
				layer.tips("手机号不能为空！", "input[name='contactPhone']", {
					tips : 2
				});
				return false;
			}
			if (!reg.test(contactPhone)) {
				layer.tips("手机号格式有误,请核对!", "input[name='contactPhone']", {
					tips : 2
				});
				$("input[name='contactPhone']").focus();
				return false;
			}
			var bool = true;
			$.ajax({
				type : "post",
				url : "${ctx }/rest/cust/checkPhoneUnique",
				async : false, // 此处必须同步
				dataType : "json",
				data : {
					"contactPhone" : contactPhone
				},
				success : function(xmlobj) {
					if (xmlobj.flag == 1) {
						layer.tips("该手机号已存在，请重新输入！",
								"input[name='contactPhone']", {
									tips : 2
								});
						bool = false;
					} else {
						bool = true;
					}
				}
			});
			return bool;
		}

		//检查电话号码格式
		function checkTel() {
			var regPhone = /^1[3,4,5,7,8]\d{9}$/;
			var regTel = /^0\d{2,3}-?\d{7,8}$/;
			var custTel = $("input[name='custTel']").val();
			if (custTel.trim() == '') {
				layer.tips("法人电话号码不能为空！", "input[name='custTel']", {
					tips : 2
				});
				return false;
			}
			if (!(regPhone.test(custTel) || regTel.test(custTel))) {
				layer.tips("法人电话号码格式有误,请核对!", "input[name='custTel']", {
					tips : 2
				});
				$("input[name='custTel']").focus();
				return false;
			}

			var bool = true;
			$.ajax({
				type : "post",
				url : "${ctx }/rest/cust/checkUnique",
				async : false, // 此处必须同步
				dataType : "json",
				data : {
					"custTel" : custTel
				},
				success : function(xmlobj) {
					if (xmlobj.flag == 1) {
						layer.tips("该电话号码已存在，请重新输入！", "input[name='custTel']",
								{
									tips : 2
								});
						bool = false;
					} else {
						bool = true;
					}
				}
			});
			return bool;
		}

		//控制显示区域和上级客户
		function showHide(obj) {
			if (obj == 1) {
				$("#d1").show();
				$("#d2").show();
				$("#d3").hide();
			} else {
				$("#d1").hide();
				$("#d2").hide();
				$("#d3").show();
			}
		}

		//省级赋值
		function initProvince() {
			var addrId = $("#provinces option:selected").val();
			var addName = $("#provinces option:selected").text();
			var arr = addrId.split(",");
			var id = arr[0];
			var regionSn = arr[2];
			$("#districts").html("<option  >请选择</option>");
			if ("请选择" == addName) {
				//下级改变成请选择
				$("#citys option:selected").text(addName);
				$("#districts option:selected").text(addName);
				$("#citys").html("<option  >请选择</option>");

				$("input[name=provinceId]").val("");
				$("input[name=provinceName]").val("");
				$("input[name='cityId']").val("");
				$("input[name='cityName']").val("");
				$("input[name='distId']").val("");
				$("input[name='distName']").val("");
				return;
			}
			$("input[name=provinceId]").val(id);
			$("input[name=provinceName]").val(addName);
			$("input[name=regionSn]").val(regionSn);
			$.ajax({
				type : "post",
				url : "${ctx}/rest/cust/seleAddress?pid=" + id,
				async : false, // 此处必须同步
				dataType : "json",
				data : "",
				success : function(data) {
					$("#citys").html("<option  >请选择</option>");
					data.result.forEach(function(list) {
						$("#citys").append(
								"<option value="+list.regionId+","+list.parentId+","+list.regionSn+" >"
										+ list.regionName + "</option>");
					})
				}
			});
		}

		//市级赋值
		function initCity() {
			var addrId = $("#citys option:selected").val();
			var addName = $("#citys option:selected").text();
			var arr = addrId.split(",");
			var id = arr[0];
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
			$("input[name='cityId']").val(id);
			$("input[name='cityName']").val(addName);
			$.ajax({
				type : "post",
				url : "${ctx}/rest/cust/seleAddress?pid=" + id,
				async : false, // 此处必须同步
				dataType : "json",
				data : "",
				success : function(data) {
					$("#districts").html("<option value='' >请选择</option>");
					data.result.forEach(function(list) {
						$("#districts").append(
								"<option value="+list.regionId+","+list.parentId+" >"
										+ list.regionName + "</option>");
					})
				}
			});
		}

		//区级赋值
		function initDistrict() {
			var addrId = $("#districts option:selected").val();
			var addName = $("#districts option:selected").text();
			var arr = addrId.split(",");
			var id = arr[0];
			if ("请选择" == addName) {
				//下级改变成请选择
				$("#districts option:selected").text(addName);
				//其值及其下级值变成空
				$("input[name='distId']").val("");
				$("input[name='distName']").val("");
				return;
			}
			$("input[name='distId']").val(id);
			$("input[name='distName']").val(addName);

		}
	</script>



</body>
</html>