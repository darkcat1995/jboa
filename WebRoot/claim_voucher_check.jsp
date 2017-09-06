<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>北大青鸟办公自动化管理系统</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function pass(){
				var myForm = document.getElementById("myForm");
				myForm.action = "pass.action";
				myForm.submit();
			}	
			function regist(){
				var myForm = document.getElementById("myForm");
				myForm.action = "regist.action";
				myForm.submit();
			}
		
			function back(){
				var myForm = document.getElementById("myForm");
				myForm.action = "back.action";
				myForm.submit();
		    }
		</script>
	</head>

	<body>
		<s:if test="%{#session.employee!=null}">
			<div class="top">
				<div class="global-width">
					<img src="Images/logo.gif" class="logo" />
				</div>
			</div>
			<div class="status">
				<div class="global-width">
					<span class="usertype">【登录角色：${sessionScope.employee.sysPosition.nameCn}】</span>${sessionScope.employee.name}你好！欢迎访问青鸟办公管理系统！
					<a href="loginOut.action">注销</a>
				</div>
			</div>
			<div class="main">
				<div class="global-width">
					<div class="nav" id="nav">
						<div class="t"></div>
						<dl class="open">
							<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
								报销单管理
							</dt>
							<dd>
								<a href="index.html" target="_self">添加报销单</a>
							</dd>
							<dd>
								<a href="list.html" target="_self">审核报销单</a>
							</dd>
						</dl>
						<dl>
							<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
								我要采购
							</dt>
							<dd>
								信心收件箱
							</dd>
							<dd>
								信心发件箱
							</dd>
						</dl>
						<dl>
							<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
								我要销售
							</dt>
							<dd>
								信心收件箱
							</dd>
							<dd>
								信心发件箱
							</dd>
						</dl>
						<dl>
							<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
								信息中心
							</dt>
							<dd>
								信心收件箱
							</dd>
							<dd>
								信心发件箱
							</dd>
						</dl>
					</div>
					<form id="myForm" name="myForm" method="post">
						<div class="action">
							<div class="t">
								审核报销单
							</div>
							<div class="pages">
								<!--增加报销单 区域 开始-->
								<table width="90%" border="0" cellspacing="0" cellpadding="0"
									class="addform-base">
									<caption>
										基本信息
									</caption>
									<tr>
										<td width="36%">
											填写人：
											<input type="hidden" name="claimVoucher.id"
												value="${sessionScope.claimVoucher.id}" />
											<input type="hidden" name="claimVoucher.creator.sn"
												value="${sessionScope.claimVoucher.creator.sn}" />
											<input type="hidden" name="employee.sn"
												value="${sessionScope.employee.sn}" />
											${sessionScope.claimVoucher.creator.name}
										</td>
										<td width="64%">
											填报时间：
											<s:date name="#session.claimVoucher.createTime"
												format="yyyy-MM-dd" />
										</td>
									</tr>
									<tr>
										<td>
											总金额：${sessionScope.claimVoucher.totalAccount==0.0?"":sessionScope.claimVoucher.totalAccount}
										</td>
										<td>
											状态：${sessionScope.claimVoucher.status}
										</td>
									</tr>
								</table>
								<p>&nbsp;</p>
								<table width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
									<tr>
										<td>项目</td>
										<td>金额</td>
										<td>费用</td>
									</tr>
									<c:if test="${sessionScope.claimVoucher.bizClaimVoucherDetails!=null}">
										<c:forEach items="${sessionScope.claimVoucher.bizClaimVoucherDetails}" var="detail">
									<tr>
										<td>
											${detail.item}
										</td>
										<td>
											￥${detail.account}
										</td>
										<td>
											${detail.desc}
										</td>
									</tr>
									</c:forEach>
									</c:if>
								</table>
								<p>
									&nbsp;
								</p>
								<p>
									-------------------------------------------------------------------------------
								</p>
								<p>
									&nbsp;
								</p>
								<table width="90%" border="0" cellspacing="0" cellpadding="0"
									class="addform-base">
									<c:forEach items="${sessionScope.checkResultList}"
										var="checkResult">
										<c:choose>
											<c:when
												test="${sessionScope.claimVoucher.status=='已审批'&&checkResult.type=='部门经理审核'}">
												<tr>
													<td width="27%">
														${manager.name }(部门经理)
													</td>
													<td width="20%">
														${checkResult.checkTime}
													</td>
													<td width="38%">
														审核：
														<span class="red"><strong>${checkResult.comment}</strong>
														</span>
													</td>
												</tr>
												<tr>
													<td>
														审核意见：${checkResult.comment}
													</td>
													<td>
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
											</c:when>
											<c:when
												test="${sessionScope.claimVoucher.status=='已提交'&&checkResult.type=='部门经理审核'&&claimVoucher.totalAccount>5000}">
												<tr>
													<td width="27%">
														${manager.name }(部门经理)
													</td>
													<td width="20%">
														${checkResult.checkTime}
													</td>
													<td width="38%">
														审核：
														<span class="red"><strong>${checkResult.comment}</strong>
														</span>
													</td>
												</tr>
												<tr>
													<td>
														审核意见：${checkResult.comment}
													</td>
													<td>
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
											</c:when>
											<c:when
												test="${sessionScope.claimVoucher.status=='已拒绝'&&checkResult.type=='部门经理审核'}">
												<tr>
													<td width="27%">
														${manager.name }(部门经理)
													</td>
													<td width="20%">
														${checkResult.checkTime}
													</td>
													<td width="38%">
														审核：
														<span class="red"><strong>${checkResult.comment}</strong>
														</span>
													</td>
												</tr>
												<tr>
													<td>
														审核意见：${checkResult.comment}
													</td>
													<td>
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
											</c:when>
											<c:when
												test="${sessionScope.claimVoucher.status=='已打回'&&checkResult.type=='部门经理审核'}">
												<tr>
													<td width="27%">
														${manager.name }(部门经理)
													</td>
													<td width="20%">
														${checkResult.checkTime}
													</td>
													<td width="38%">
														审核：
														<span class="red"><strong>${checkResult.comment}</strong>
														</span>
													</td>
												</tr>
												<tr>
													<td>
														审核意见：${checkResult.comment}
													</td>
													<td>
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
											</c:when>
											<c:when
												test="${sessionScope.claimVoucher.status=='已审批'&&checkResult.type=='总经理审核'}">
												<tr>
													<td width="27%">
														${boss.name }(总经理)
													</td>
													<td width="20%">
														${checkResult.checkTime}
													</td>
													<td width="38%">
														审核：
														<span class="red"><strong>${checkResult.comment}</strong>
														</span>
													</td>
												</tr>
												<tr>
													<td>
														审核意见：${checkResult.comment}
													</td>
													<td>
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
											</c:when>
											<c:when
												test="${sessionScope.claimVoucher.status=='已打回'&&checkResult.type=='总经理审核'}">
												<tr>
													<td width="27%">
														${boss.name }(总经理)
													</td>
													<td width="20%">
														${checkResult.checkTime}
													</td>
													<td width="38%">
														审核：
														<span class="red"><strong>${checkResult.comment}</strong>
														</span>
													</td>
												</tr>
												<tr>
													<td>
														审核意见：${checkResult.comment}
													</td>
													<td>
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
											</c:when>
											<c:when
												test="${sessionScope.claimVoucher.status=='已拒绝'&&checkResult.type=='总经理审核'}">
												<tr>
													<td width="27%">
														${boss.name }(总经理)
													</td>
													<td width="20%">
														${checkResult.checkTime}
													</td>
													<td width="38%">
														审核：
														<span class="red"><strong>${checkResult.comment}</strong>
														</span>
													</td>
												</tr>
												<tr>
													<td>
														审核意见：${checkResult.comment}
													</td>
													<td>
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
											</c:when>
										</c:choose>

									</c:forEach>

								</table>
								<p>
									&nbsp;
								</p>
								<table width="90%" border="0" cellspacing="0" cellpadding="0"
									class="addform-base">
									<tr>
										<td>
											审核意见：
										</td>
									</tr>
									<tr>
										<td>
											<textarea name="checkResult.comment" id="textarea" cols="45"
												rows="5"></textarea>
										</td>
									</tr>
									<!--表单提交行-->
									<tr>
										<td colspan="4" class="submit">
											<input type="button" name="button" id="button" value="审核通过"
												class="submit_01" onclick="pass()" />
											<c:choose>
												<c:when test="${sessionScope.employee.sysPosition.id!=4}">
													<input type="button" name="button" id="button" value="审核拒绝"
														class="submit_01" onclick="regist()" />
													<input type="button" name="button" id="button" value="打回"
														class="submit_01" onclick="back()" />
												</c:when>
											</c:choose>
										</td>
									</tr>

								</table>
								<p>
									&nbsp;
								</p>
								<p>
									&nbsp;
								</p>
								<p>
									&nbsp;
								</p>
								<!--增加报销单 区域 结束-->
							</div>
						</div>
					</form>
				</div>
			</div>

		</s:if>
		<s:else>
			<h1>
				你还没登录，请您先登录。
				<a href="login.jsp">返回</a>
			</h1>
		</s:else>
		<div class="copyright">
			Copyright &nbsp; &copy; &nbsp; 北大青鸟
		</div>

	</body>
</html>