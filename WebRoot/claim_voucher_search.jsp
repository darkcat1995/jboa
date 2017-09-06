<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>北大青鸟办公自动化管理系统</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		
			function searchByConditon1(num1,num2,type){
				var myForm = document.getElementById("myForm");
				if(type=='page'){
					myForm.action = "searchClaimVoucher.action?page="+num1+"&currentGroup="+num2+"&type=page";
				}else if(type=='group'){
					myForm.action = "searchClaimVoucher.action?page="+num1+"&currentGroup="+num2+"&type=group";
				}
				myForm.submit();
				
			}
			function deleteClaimVoucher(id){
				if(confirm("要删除该条信息？")){
					var myForm = document.getElementById("myForm");
					myForm.action ="delete.action?claimVoucher.id="+id;
					myForm.submit();
				}else{
					return ;
				}
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
				<span class="usertype">【登录角色：${sessionScope.employee.sysPosition.nameCn}】</span>${sessionScope.employee.name}你好！欢迎访问青鸟办公管理系统！<a href="loginOut.action">注销</a>
			</div>
		</div>
		<div class="main">
			<div class="global-width">

				<div class="nav" id="nav">
					<div class="t"></div>
					<dl class="open">
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
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
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
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
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
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
						<dt
							onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">
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
							报销单列表
						</div>
						<div class="pages">
							<!--增加报销单 区域 开始-->
							<table width="90%" border="0" cellspacing="0" cellpadding="0"
								class="list">
								<tr>
									<td>
										编号
									</td>
									<td>
										填报日期
									</td>
									<td>
										填报人
									</td>
									<td>
										总金额
									</td>
									<td>
										状态
									</td>
									<td>
										操作
									</td>
								</tr>
								<s:iterator value="#session.pageBean.list" id="pl">
									<tr>
										<td>
											<s:property value="id" />
										</td>
										<td>
											<s:date name="createTime" format="yyyy-MM-dd" />
										</td>
										<td>
											<s:property value="creator.name" />
										</td>
										<td>
											￥<s:property value="totalAccount" />
										</td>
										<td>
											<s:property value="status" />
										</td>
										<td>
											<s:if test="%{#session.employee.sysPosition.nameCn=='员工'}">

												<s:if test="%{status == '已打回'||status == '新创建'}">

													<a
														href="edit.action?claimVoucher.id=<s:property value="id"/>"><img
															src="Images/edit.gif" width="16" height="16" />
													</a>
												</s:if>
												<s:else>
													<img src="Images/edit.gif" width="16" height="16" />
												</s:else>

												<s:if
													test="%{status != '已提交'&&status != '已审批'&&status != '已付款'&&status != '已终止'}">
													<a
														href="#" onclick="deleteClaimVoucher('<s:property value="id"/>')"><img
															src="Images/delete.gif" width="16" height="16"  /></a>
												</s:if>
												<s:else>
													<img src="Images/delete.gif" width="16" height="16" />
												</s:else>
												<s:if test="%{status == '已打回'||status == '新创建'}">

													<a href="submit.action?claimVoucher.id=<s:property value="id"/>"><img
															src="Images/save.gif" width="16" height="16" />
													</a>
												</s:if>
												<s:else>
													<img src="Images/save.gif" width="16" height="16" />
												</s:else>
												<s:if
													test="%{status != '新创建'&& status != '已打回'}">

													<a
														href="view.action?claimVoucher.id=<s:property value="id"/>"><img
															src="Images/search.gif" width="16" height="15" />
													</a>
												</s:if>
												<s:else>
													<img src="Images/search.gif" width="16" height="15" />
												</s:else>

												<s:else>
													<img src="Images/sub.gif" width="16" height="16" />
												</s:else>

											</s:if>

											<s:elseif
												test="%{#session.employee.sysPosition.nameCn == '部门经理'}">
												<s:if test="%{status == '已提交'  }">
													<s:if test="%{nextDealBy.sn == #session.employee.sn}">
														<a
															href="toCheck.action?claimVoucher.id=<s:property value="id"/>"><img
																src="Images/sub.gif" width="16" height="16" />
														</a>
													</s:if>
												</s:if>
												<s:else>
													<a
														href="view.action?claimVoucher.id=<s:property value="id"/>"><img
															src="Images/search.gif" width="16" height="15" />
													</a>
												</s:else>
											</s:elseif>
											<s:elseif
												test="%{#session.employee.sysPosition.nameCn == '总经理'}">
												<s:if test="%{status == '已提交'  }">
													<s:if test="%{nextDealBy.sn == #session.employee.sn}">
														<a
															href="toCheck.action?claimVoucher.id=<s:property value="id"/>"><img
																src="Images/sub.gif" width="16" height="16" />
														</a>
													</s:if>
												</s:if>
												<s:else>
													<a
														href="view.action?claimVoucher.id=<s:property value="id"/>"><img
															src="Images/search.gif" width="16" height="15" />
													</a>
												</s:else>
											</s:elseif>
											<s:elseif
												test="%{#session.employee.sysPosition.nameCn == '财务'}">

												<s:if test="%{status == '已审批'  }">
													<s:if test="%{nextDealBy.sn == #session.employee.sn}">
														<a
															href="toCheck.action?claimVoucher.id=<s:property value="id"/>"><img
																src="Images/sub.gif" width="16" height="16" />
														</a>
													</s:if>
												</s:if>
												<s:else>
													<a
														href="view.action?claimVoucher.id=<s:property value="id"/>"><img
															src="Images/search.gif" width="16" height="15" />
													</a>
												</s:else>
											</s:elseif>

										</td>

									</tr>
								</s:iterator>
								</table>
								<table>
								<tr>
									<td>
										<s:if test="%{#session.pageBean.currentPage > 1}">
											<a href="#" onclick="searchByConditon1(1,1,'page')">&nbsp;&nbsp;【首页】&nbsp;&nbsp;</a>
										</s:if>
									</td>
									<td>
										<s:if test="%{#session.pageBean.currentGroup > 1}">
											<a href="#"
												onclick="searchByConditon1(0,<s:property value="%{#session.pageBean.currentGroup-1}"/>,'group')">&nbsp;&nbsp;【前5页】&nbsp;&nbsp;</a>
										</s:if>
									</td>
									<td>
										<s:iterator id="p" value="#session.pageBean.pageList">
											<a href="#"
												onclick="searchByConditon1(<s:property value="p"/>,<s:property value="%{#session.pageBean.currentGroup}"/>,'page')"><s:property
													value="p" />
											</a>
										</s:iterator>
									</td>
									<td>
										<s:if
											test="%{#session.pageBean.currentGroup <#session.pageBean.totalGroup}">
											<a href="#"
												onclick="searchByConditon1(0,<s:property value="%{#session.pageBean.currentGroup+1}"/>,'group')">&nbsp;&nbsp;【后5页】&nbsp;&nbsp;</a>
										</s:if>
									</td>
									<td>
										<s:if
											test="%{#session.pageBean.currentPage != #session.pageBean.totalPage&&#session.pageBean.totalPage!=0}">
											<a href="#"
												onclick="searchByConditon1(<s:property value="%{#session.pageBean.totalPage}"/>,<s:property value="%{#session.pageBean.totalGroup}"/>,'page')">&nbsp;&nbsp;【末页】&nbsp;&nbsp;</a>
										</s:if>
									</td>
									<td>
										【第
										<s:property value="#session.pageBean.currentPage" />
										页 】/【共
										<s:property value="#session.pageBean.totalPage" />
										页】【共
										<s:property value="#session.pageBean.allRow" />
										条】
									</td>
								</tr>
							</table>
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
