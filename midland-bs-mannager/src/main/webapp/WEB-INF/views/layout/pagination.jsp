<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="row">
    <div class="col-sm-6">
    	<div style = "overflow:hidden; line-height:30px;">
       <%--  显示[${paginator.startRow}]到[${paginator.endRow}]条数据，总共[${paginator.totalCount}]条数据,当前第[${paginator.page}/${paginator.totalPages}]页 --%>
      <!-- 每页显示多少记录 --> 
     &nbsp;每页显示： <select style="width: 90px;height: 30px; display:inline-block!important;border-radius: 4px;" id="pageSize" onchange="setPageSize(${paginator.page},'${target}');">
        <option <c:if test="${paginator.limit==5}">selected="selected"</c:if> value="5" >5条</option>
        <option <c:if test="${paginator.limit==10}">selected="selected"</c:if> value="10" >10条</option>
        <option <c:if test="${paginator.limit==20}">selected="selected"</c:if> value="20" >20条</option>
      </select>
               总共&nbsp;&nbsp;[${paginator.totalCount}]&nbsp;&nbsp;条数据,当前第&nbsp;&nbsp;[${paginator.page}/${paginator.totalPages}]&nbsp;&nbsp;页  
        </div>
    </div>
    <div class="col-sm-6" style="text-align: right;">
        <ul class="pagination" style="margin: 0px;">
            <c:if test="${paginator.firstPage}">
                <li class="paginate_button first disabled" tabindex="0"><a href="javascript:void(0);">首页</a></li>
             </c:if>
            <c:if test="${!paginator.firstPage}">
                    <li class="paginate_button first" tabindex="0"><a href="javascript:void(0);" onclick="javascript:setPage(1,'${target}');">首页</a></li>
                </c:if>

            <c:if test="${!paginator.hasPrePage}">
                <li class="paginate_button previous disabled" tabindex="0"><a href="javascript:void(0);">上一页</a></li>
            </c:if>
            <c:if test="${paginator.hasPrePage}">
                <li class="paginate_button previous" tabindex="0"><a href="javascript:void(0);" onclick="javascript:setPage(${paginator.page-1},'${target}');">上一页</a></li>
            </c:if>
            <c:forEach var="i" items="${paginator.slider}">
                <c:choose>
                    <c:when test="${i == paginator.page}">
                        <li class="paginate_button active disabled" tabindex="0"><a href="javascript:void(0);">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="paginate_button " tabindex="0"><a href="javascript:setPage('${i}','${target}');">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${paginator.hasNextPage}">
            <li class="paginate_button next" tabindex="0"><a href="javascript:void(0);" onclick="javascript:setPage(${paginator.page+1},'${target}');">下一页</a></li>
            </c:if>
            <c:if test="${!paginator.hasNextPage}">
            <li class="paginate_button next disabled" tabindex="0"><a href="javascript:void(0);">下一页</a></li>
            </c:if>
            <c:if test="${paginator.lastPage}">
            <li class="paginate_button last disabled" tabindex="0"><a href="javascript:void(0);">尾页</a></li>
            </c:if>
            <c:if test="${!paginator.lastPage}">
            <li class="paginate_button last" tabindex="0"><a href="javascript:void(0);" onclick="javascript:setPage(${paginator.totalPages},'${target}');">尾页</a></li>
           </c:if>
        </ul>
    </div>
</div>