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

<div class = "table-responsive m40">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
							<th style="width: 25%; font-weight:bold;">产品名称</th>
							<th style="width: 15%; font-weight:bold;">产品编码</th>
							<th style="width: 20%; font-weight:bold;">分类</th>
							<th style="width: 10%; font-weight:bold;">在库库存</th>
							<th style="width: 10%; font-weight:bold;">占用库存</th>
							<th style="width: 10%; font-weight:bold;">可用库存</th>
							<th style="width: 10%; font-weight:bold;">预警库存</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty stockList}">
						  <c:forEach var="stock" items="${stockList}">
							<tr>
							    <td>${stock.productName}</td>
							    <td>${stock.productCode}</td>
							    <td>${stock.catName}</td>
						     	<td>${stock.sl}</td>
							    <td>${stock.sl1}</td>
						     	<td>${stock.sl2}</td>
						     	<td>${stock.safeSl}</td>
							</tr>
						 </c:forEach>	
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="7">没有找到数据!</td>
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


</body>
</html>