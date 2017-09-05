<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<p class = "detail-titleBtn" style="text-align:left;margin-top:5px">
	<!-- <a class = "setup" style = "float:left;" href="#" onclick="isChange('99')">全部</a>
	<a class = "setup" style = "float:left;" href="#" onclick="isChange('-1')">待付款</a>
	<a class = "setup" style = "float:left;" href="#" onclick="isChange('0')">待确认</a>
	<a class = "setup" style = "float:left;" href="#" onclick="isChange('1')">待发货</a> -->
	<input onclick="isChange('99')" class = "public_btn bg1" style = "float:left; margin-left:6px;" type="submit" name="inquery" value = "全部"/>
	<input onclick="isChange('-1')" class = "public_btn bg1" style = "float:left; margin-left:23px;" type="submit" name="inquery" value = "待付款"/>
	<input onclick="isChange('0')" class = "public_btn bg1" style = "float:left; margin-left:23px;" type="submit" name="inquery" value = "待确认"/>
	<input onclick="isChange('1')" class = "public_btn bg1" style = "float:left; margin-left:23px;" type="submit" name="inquery" value = "待发货"/>
</p>
<div class = "table-responsive" style = "margin-top:15px;">
				<table class="table table-bordered table-add" >
	 				<thead>
						<tr>
							<th style="width:14%">订单单号</th>
							<th style="width:14%">下单时间</th>
							<th style="width:14%">支付时间</th>
							<th style="width:12%">客户名称</th>
							<th style="width:8%">订单金额</th>
							<th style="width:7%">数量</th>
							<th style="width:7%">订单状态</th>
							<th style="width:8%">发货仓库</th>
							<th style="width:11%">支付方式</th>
							<th style="width:5%">操作</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty orderInfoList}">
						<c:forEach items="${orderInfoList}" var="orderInfo" >
							<tr>
								<td><a href="${ctx}/rest/order/showOrderInfoDetail?id=${orderInfo.id}" target="contentF" style="width:auto; height:auto; margin:auto;" class="showA" >${orderInfo.orderSn}</a></td>
							    <td>${orderInfo.createTime}</td>
							    <td>${orderInfo.payTime}</td>
							    <td>${orderInfo.custName}</td>
							    <td>${orderInfo.orderAmount}</td>
							    <td>${orderInfo.num}</td>
							    <td>${orderInfo.orderStatusName}</td>
								<td>${orderInfo.shipperCode}</td>
							    <td>${orderInfo.payCodeName}</td>
							    <td>
							    	<c:if test="${orderInfo.orderStatus>=0}">
										<a  onclick="toAddPage(${orderInfo.id},'${orderInfo.orderSn}')" target="contentF" class = "remark_img" title="备注"></a>
							    	</c:if>
							    </td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="10">没有找到数据!</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
				</table>
</div>
	<!-- 分页 -->
	<c:if test="${!empty paginator}"> 
		  <c:set var="paginator" value="${paginator}"/>
		  <c:set var="target" value="listDiv"/>
		  <%@include file="../layout/pagination.jsp"%>
	</c:if>		  

<script type="text/javascript">
		function checkall(){
			$("input[name='pid']").each(function(){
				this.checked=true;
			}); 
		}
		
		function delcheckall(){
			$("input[name='pid']").each(function(){
				this.checked=false;
			}); 
		}
		
		
		function toAddPage(id,orderSn){
			layer.open({
				type: 2,
				skin: 'layer-style',
				area: ['470px','400px'],
				shadeClose: false, //点击遮罩关闭
				title:['新增备注'],
				resize: false,
				scrollbar:false,
				content:['${ctx}/rest/order/showInputRemark?id='+id+'&orderSn='+orderSn, 'no']
			});
		}
	
</script>
</body>
</html>