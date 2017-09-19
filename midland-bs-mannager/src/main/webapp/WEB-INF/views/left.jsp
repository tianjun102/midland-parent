<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />
<!DOCTYPE html>
<html lang="en" style  = "height:100%;">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>美联物业管理平台</title>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/assets/css/common.css">
</head>
<body
	style="background-color: #eff2f6; border-right: 1px solid #d8dde0; overflow-x:hidden; height:100%;">
	<!--左侧导航-->
	<nav class="nav">

		<%--<c:forEach items="${items}" var="item">--%>
			<%--<shiro:hasPermission name="custManage">--%>
				<%--<dl class="list-left1">--%>
					<%--<dt>--%>
						<%--<a href="javascript:;"--%>
						   <%--dota="url(${ctx}/assets/img/01.png) no-repeat 20px center"--%>
						   <%--data-img="url(${ctx}/assets/img/01_w.png) no-repeat 20px center">${item.menuName}<span--%>
								<%--class="glyphicon glyphicon-menu-right"></span></a>--%>
					<%--</dt>--%>
					<%--<dd>--%>
						<%--<c:forEach items="${item.menuChilds}" var="s" >--%>
							<%--<shiro:hasPermission name="arealist">--%>
								<%--<a id = "areaIndex" href="${ctx}/rest/${s.url}" target="contentF"><span>${s.menuName}</span></a>--%>
							<%--</shiro:hasPermission>--%>
						<%--</c:forEach>--%>

					<%--</dd>--%>
				<%--</dl>--%>
			<%--</shiro:hasPermission>--%>
		<%--</c:forEach>--%>



		<shiro:hasPermission name="appointManange">
			<dl class="list-left1">
				<dt>
					<a href="javascript:;"
						dota="url(${ctx}/assets/img/01.png) no-repeat 20px center"
						data-img="url(${ctx}/assets/img/01_w.png) no-repeat 20px center">预约管理<span
						class="glyphicon glyphicon-menu-right"></span></a>
				</dt>
				<dd>
					<shiro:hasPermission name="appointList">
						<a id = "areaIndex" href="${ctx}/rest/appoint/index" target="contentF"><span>预约记录</span></a>
					</shiro:hasPermission>
				</dd>
			</dl>
		</shiro:hasPermission>
		<shiro:hasPermission name="informationManage">
			<dl class="list-left2">
				<dt>
					<a href="javascript:;"
						dota="url(${ctx}/assets/img/02.png) no-repeat 20px center"
						data-img="url(${ctx}/assets/img/02_w.png) no-repeat 20px center">资讯管理<span
						class="glyphicon glyphicon-menu-right"></span></a>
				</dt>
				<dd>
					<shiro:hasPermission name="categoryList">
						<a id = "showCategoryIndex" href="${ctx}/rest/category/index" target="contentF"><span>分类</span></a>
					</shiro:hasPermission>
					<shiro:hasPermission name="informationList">
						<a id = "showProductIndex" href="${ctx}/rest/information/index" target="contentF"><span>资讯列表</span></a>
					</shiro:hasPermission>
				</dd>
			</dl>
		</shiro:hasPermission>
		<shiro:hasPermission name="entrustManage">
			<dl class="list-left3">
				<dt>
					<a href="javascript:;"
						dota="url(${ctx}/assets/img/03.png) no-repeat 20px center"
						data-img="url(${ctx}/assets/img/03_w.png) no-repeat 20px center">委托管理<span
						class="glyphicon glyphicon-menu-right"></span></a>
				</dt>
				<dd>
					<shiro:hasPermission name="entrustList">
						<a id = "enterPromotionIndex" href="${ctx}/rest/entrust/index" target="contentF"><span>委托记录</span></a>
					</shiro:hasPermission>
				</dd>
			</dl>
		</shiro:hasPermission>
			<shiro:hasPermission name="exportManage">
				<dl class="list-left3">
					<dt>
						<a href="javascript:;"
						   dota="url(${ctx}/assets/img/03.png) no-repeat 20px center"
						   data-img="url(${ctx}/assets/img/03_w.png) no-repeat 20px center">外销网管理<span
								class="glyphicon glyphicon-menu-right"></span></a>
					</dt>
					<dd>
						<shiro:hasPermission name="serviceAreaList">
							<a id = "enterPromotionIndex" href="${ctx}/rest/privacy/index" target="contentF"><span>隐私政策管理</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="serviceAreaList">
							<a id = "enterPromotionIndex" href="${ctx}/rest/disclaimer/index" target="contentF"><span>免责声明</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="serviceAreaList">
							<a id = "enterPromotionIndex" href="${ctx}/rest/registrationProtocol/index" target="contentF"><span>注册协议</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="serviceAreaList">
							<a id = "enterPromotionIndex" href="${ctx}/rest/serviceArea/index" target="contentF"><span>服务范围</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="aboutUsList">
							<a id = "enterPromotionIndex" href="${ctx}/rest/aboutUs/index" target="contentF"><span>关于我们</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="tradingProcessList">
							<a id = "enterPromotionIndex" href="${ctx}/rest/tradingProcess/index" target="contentF"><span>买卖流程</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="tradeFairList">
							<a id = "enterPromotionIndex" href="${ctx}/rest/tradeFair/index" target="contentF"><span>楼盘展销会</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="filmLibraryList">
							<a id = "enterPromotionIndex" href="${ctx}/rest/filmLibrary/index" target="contentF"><span>片库管理</span></a>
						</shiro:hasPermission>
					</dd>
				</dl>
			</shiro:hasPermission>
		<shiro:hasPermission name="assistantManage">
			<dl class="list-left4">
				<dt>
					<a href="javascript:;"
						dota="url(${ctx}/assets/img/04.png) no-repeat 20px center"
						data-img="url(${ctx}/assets/img/04_w.png) no-repeat 20px center">买房助手<span
						class="glyphicon glyphicon-menu-right"></span></a>
				</dt>
				<dd>
					<shiro:hasPermission name="questionsList">
						<a id = "showOrderInfoIndex" href="${ctx}/rest/questions/index" target="contentF"><span>问答管理</span></a>
					</shiro:hasPermission>
				</dd>
			</dl>
		</shiro:hasPermission>
		<shiro:hasPermission name="quotationManage">
			<dl class="list-left5">
				<dt>
					<a href="javascript:;"
						dota="url(${ctx}/assets/img/05.png) no-repeat 20px center"
						data-img="url(${ctx}/assets/img/05_w.png) no-repeat 20px center">行情信息管理<span
						class="glyphicon glyphicon-menu-right"></span></a>
				</dt>
				<dd>
					<shiro:hasPermission name="quotationNew">
						<a id = "settlementIndex" target="contentF" href="${ctx}/rest/quotation/index"><span>新房</span></a>
					</shiro:hasPermission>
					<shiro:hasPermission name="quotationOld">
						<a id = "settlementIndex" target="contentF" href="${ctx}/rest/quotationSecondHand/index"><span>二手房</span></a>
					</shiro:hasPermission>
				</dd>
			</dl>
		</shiro:hasPermission>
		<shiro:hasPermission name="hotSearchManage">
				<dl class="list-left6">
					<dt>
						<a href="javascript:;"
						   dota="url(${ctx}/assets/img/06.png) no-repeat 20px center"
						   data-img="url(${ctx}/assets/img/06_w.png) no-repeat 20px center">搜索管理<span
								class="glyphicon glyphicon-menu-right"></span></a>
					</dt>
					<dd>
						<shiro:hasPermission name="hotSearchList">
							<a id = "report1" href="${ctx}/rest/hotSearch/index" target="contentF"><span>搜索列表</span></a>
						</shiro:hasPermission>
					</dd>
				</dl>
		</shiro:hasPermission>
		<shiro:hasPermission name="researchManage">
			<dl class="list-left6">
				<dt>
					<a href="javascript:;"
						dota="url(${ctx}/assets/img/06.png) no-repeat 20px center"
						data-img="url(${ctx}/assets/img/06_w.png) no-repeat 20px center">市场调究管理<span
						class="glyphicon glyphicon-menu-right"></span></a>
				</dt>
				<dd>
					<shiro:hasPermission name="researchList">
						<a id = "report1" href="${ctx}/rest/research/index" target="contentF"><span>市场调究列表</span></a>
					</shiro:hasPermission>
				</dd>
			</dl>
		</shiro:hasPermission>

		<shiro:hasPermission name="talentRecruitment">
				<dl class="list-left6">
					<dt>
						<a href="javascript:;"
						   dota="url(${ctx}/assets/img/06.png) no-repeat 20px center"
						   data-img="url(${ctx}/assets/img/06_w.png) no-repeat 20px center">招聘管理<span
								class="glyphicon glyphicon-menu-right"></span></a>
					</dt>
					<dd>
						<shiro:hasPermission name="resumeManagerList">
							<a id = "report1" href="${ctx}/rest/resumeManager/index" target="contentF"><span>简历信息</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="recruitList">
							<a id = "report1" href="${ctx}/rest/recruitManager/index" target="contentF"><span>招聘管理</span></a>
						</shiro:hasPermission>
					</dd>
				</dl>
		</shiro:hasPermission>

		<shiro:hasPermission name="intoMidland">
				<dl class="list-left6">
					<dt>
						<a href="javascript:;"
						   dota="url(${ctx}/assets/img/06.png) no-repeat 20px center"
						   data-img="url(${ctx}/assets/img/06_w.png) no-repeat 20px center">走进美联<span
								class="glyphicon glyphicon-menu-right"></span></a>
					</dt>
					<dd>
						<shiro:hasPermission name="intoMidlandList">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=1" target="contentF"><span>公司介绍</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="researchList">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=2" target="contentF"><span>公司历程</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="researchList">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=3" target="contentF"><span>公司荣誉</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="researchList">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=4" target="contentF"><span>公司培训</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="researchList">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=5" target="contentF"><span>公司文化</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="researchList">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=6" target="contentF"><span>晋升福利</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="researchList">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=7" target="contentF"><span>联系我们</span></a>
						</shiro:hasPermission>
					</dd>
				</dl>
		</shiro:hasPermission>

        <%--精英会--%>
		<shiro:hasPermission name="elite">
				<dl class="list-left6">
					<dt>
						<a href="javascript:;"
						   dota="url(${ctx}/assets/img/06.png) no-repeat 20px center"
						   data-img="url(${ctx}/assets/img/06_w.png) no-repeat 20px center">精英会<span
								class="glyphicon glyphicon-menu-right"></span></a>
					</dt>
					<dd>
						<shiro:hasPermission name="purpose">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=1" target="contentF"><span>精英会宗旨</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="eliteDesc">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=2" target="contentF"><span>精英会简介</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="memberShip">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=3" target="contentF"><span>入会资格</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="development">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=4" target="contentF"><span>发展前瞻</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="eliteAd">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=5" target="contentF"><span>精英会活动</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="eliteVip">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=6" target="contentF"><span>精英会员管理</span></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="eliteCate">
							<a id = "report1" href="${ctx}/rest/intoMidland/index?flag=7" target="contentF"><span>会员分类管理</span></a>
						</shiro:hasPermission>
					</dd>
				</dl>
			</shiro:hasPermission>

		<shiro:hasPermission name="systemManage">
			<dl class="list-left8">
				<dt>
					<a href="javascript:;"
						dota="url(${ctx}/assets/img/08.png) no-repeat 20px center"
						data-img="url(${ctx}/assets/img/08_w.png) no-repeat 20px center">系统管理<span
						class="glyphicon glyphicon-menu-right"></span></a>
				</dt>
				<dd>

					<shiro:hasPermission name="popularList">
						<a id = "system2" href="${ctx}/rest/setting/showPopularIndex" target="contentF"><span>热门关注管理</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="linkUrlList">
						<a id = "system2" href="${ctx}/rest/setting/showlinkUrlIndex" target="contentF"><span>友情链接管理</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="bannerList">
						<a id = "system2" href="${ctx}/rest/setting/bannerIndex" target="contentF"><span>Banner管理</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="menuList">
						<a id = "system1" href="${ctx}/rest/menu/index" target="contentF"><span>菜单管理</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="userList">
						<a id = "system4" href="${ctx}/rest/user/userIndex" target="contentF"><span>用户列表</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="rolelist">
						<a id = "system5" href="${ctx}/rest/role/roleIndex" target="contentF"><span>角色列表</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="specialPageList">
						<a id = "system6" href="${ctx}/rest/specialPage/index" target="contentF"><span>首页特殊模块配置</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="qrCodeList">
						<a id = "system6" href="${ctx}/rest/qrCode/index" target="contentF"><span>二维码管理</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="pageConfList">
						<a id = "system6" href="${ctx}/rest/pageConf/index" target="contentF"><span>页面配置</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="feedbackList">
						<a id = "feedbackIndex" href="${ctx}/rest/feedback/index" target="contentF"><span>反馈管理</span></a>
					</shiro:hasPermission>

					<shiro:hasPermission name="siteMapList">
						<a id = "feedbackIndex" href="${ctx}/rest/siteMap/index" target="contentF"><span>网站地图管理</span></a>
					</shiro:hasPermission>

						<a id = "system7" href="${ctx}/rest/user/toModifyPwdPage" target="contentF"><span>修改密码</span></a>
						<a id = "system8" href="${ctx}/rest/user/about" target="contentF"><span>关于平台</span></a>
				</dd>
			</dl>
		</shiro:hasPermission>
	</nav>
	<script type="text/javascript"
		src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<!-- 本页私有js -->
	<script type="text/javascript">
		$(document).ready(
				function() {

					intImg();

					var my_body = $("body").height();
				

					$(".nav dt a").click(
							function() {

								var $dt = $(".nav dt a").parent();

								var $dd = $(this).parent().next();

								var $a = $(".nav dt a");

								if ($dd.is(":hidden")) {

									$a.children().removeClass(
											'glyphicon glyphicon-menu-down');
									$a.children().addClass(
											'glyphicon glyphicon-menu-right');
									$(this).find('span').addClass(
											'glyphicon glyphicon-menu-down');

								}
								if ($dd.is(":visible")) {

									$(this).find('span').removeClass(
											'glyphicon glyphicon-menu-down');
									$(this).find('span').addClass(
											'glyphicon glyphicon-menu-right');
								}

								$dt.next().slideUp(400);

								if ($dd.is(":hidden")) {

									$dd.slideDown(400);

								}

								$dt.removeClass("turnBK");
								$(this).parent().addClass("turnBK");

								$a.removeClass("turnWH");
								$(this).addClass("turnWH");

								intImg();

								if ($dt.hasClass("turnBK")) {
									var dataImg = $(this).attr("data-img");
									$(this).css("background", dataImg);
								}

							});

					$(".nav dd a").addClass("other-list");
					$(".nav dd a").click(function() {
						$(".nav dd a").removeClass("current-list");
						$(this).addClass("current-list");
					})
				});
		function intImg() {
			var $a = $(".nav dt a");
			var arrlist = [];
			var len = $a.length;
			for (var i = 0; i < len; i++) {
				arrlist.push($a.eq(i).attr("dota"));
				$a.eq(i).css("background", arrlist[i]);
			}
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("a").click(function(event) {
				var e = event || window.event;
				if ($(this)[0].href) {
					var path = $(this)[0].getAttribute("href");
					sessionStorage.setItem("href", path);
				} 
				if ($(this)[0].id) {
					var id = $(this)[0].getAttribute("id");
					sessionStorage.setItem("ID", id);
				}

			})
		})

		window.onload = function() {
			var id_a = sessionStorage.getItem("ID");
			if (id_a) {
				$("#" + id_a).addClass("current-list");
				$("#" + id_a).parent().siblings().addClass("turnBK");
				$("#" + id_a).parent().siblings().children().addClass("turnWH");

				if ($("#" + id_a).parent().siblings().hasClass("turnBK")) {
					var dataImg = $("#" + id_a).parent().siblings().children()
							.attr("data-img");
					$("#" + id_a).parent().siblings().children().css(
							"background", dataImg);
				}
				if ($("#" + id_a).parent().is(":hidden")) {
					$("#" + id_a).parent().siblings().children().find('span')
							.removeClass('glyphicon glyphicon-menu-down');
					$("#" + id_a).parent().siblings().children().find('span')
							.addClass('glyphicon glyphicon-menu-down');
					$("#" + id_a).parent().slideDown(400);
				}
			}
		}
	</script>
</body>
</html>
