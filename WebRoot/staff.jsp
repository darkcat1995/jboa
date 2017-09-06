<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="s"
	uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>北大青鸟办公自动化管理系统</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		//保存报销单
		function save(){
			var myForm = document.getElementById("myForm");
			myForm.action = "save.action";
			if(checkFields("save")){
				myForm.submit();
			}
		}	
		//提交报销单
		function submitAll(){
			var myForm = document.getElementById("myForm");
			myForm.action = "submit.action";
			if(checkFields("submitAll")){
				myForm.submit();
			}

		}
		//添加报销单详细信息
		function addDetail(){
			var detailId=document.getElementById("claimVoucherDetailId").value
			var myForm = document.getElementById("myForm");
			myForm.action = "addDetail.action?claimVoucherDetail.id="+detailId;
			if(checkFields("addDetail")){
				myForm.submit();
			}
	    }	
	    //删除报销单详细信息
	    function delDetail(id){
	    	if(confirm("要删除该条详细信息？")){
				var myForm = document.getElementById("myForm");
				myForm.action = "deleteClaimVoucherDetial.action?claimVoucherDetail.id="+id;
				myForm.submit();
			}else{
				return ;
			}
   		}
		//将指定报销单详细信息添加到编辑文本框中
	    function edit(id){
	    	var itemName="item"+id;
	    	var accountName="account"+id;
	    	var descName="desc"+id;
	    	if(document.all){
		    	var item=document.getElementById(itemName).innerText;
				var	account=document.getElementById(accountName).innerText;
				var	desc=document.getElementById(descName).innerText;
			}else{
				var item=document.getElementById(itemName).textContent;
				var	account=document.getElementById(accountName).textContent;
				var	desc=document.getElementById(descName).textContent;
			}
	    	document.getElementById("claimVoucherDetailItem").value=item;
		    document.getElementById("claimVoucherDetailAccount").value=account.substr(1,account.length);
		    document.getElementById("claimVoucherDetailDesc").value=desc;
		    document.getElementById("claimVoucherDetailId").value=id;
   		}
   		//判空
	    function checkFields(opt){
	    	var claimVoucherEvent = document.getElementById("event").value;
			
			var claimVoucherDetailAccount = document.getElementById("claimVoucherDetailAccount").value;
			
			var claimVoucherDetailAesc = document.getElementById("claimVoucherDetailDesc").value;
			
			if(opt=="addDetail"){
				if(claimVoucherEvent==null||claimVoucherEvent==""){
					alert("事由不能为空");
					return false;
				}else if(claimVoucherDetailAccount==null||claimVoucherDetailAccount==""){
					alert("金额不能为空");
					return false;
				}else if(claimVoucherDetailAesc==null||claimVoucherDetailAesc==""){
					alert("费用说明不能为空");
					return false;
				}else{
					return true;	
				}
			}else if(opt=="save"){
				if(claimVoucherEvent==null||claimVoucherEvent==""){
					alert("事由不能为空");
					return false;
				}else{
					return true;
				}
			}else if(opt=="submitAll"){
				if(document.getElementById("claimVoucherId").value>0){
					return true;
				}else{
					if(claimVoucherEvent==null||claimVoucherEvent==""){
						alert("事由不能为空");
						return false;
					}else if(claimVoucherDetailAccount==null||claimVoucherDetailAccount==""){
						alert("金额不能为空");
						return false;
					}else if(claimVoucherDetailAesc==null||claimVoucherDetailAesc==""){
						alert("费用说明不能为空");
						return false;
					}else{
						return true;	
					}
				}
			}
       }
       //获得当前系统时间
	    function setCurTime(){
			var now=new Date();
			var year=now.getFullYear();
			var month=now.getMonth()+1;
			var day=now.getDate();
			var hours=now.getHours();
			var minutes=now.getMinutes();
			var seconds=now.getSeconds();
			//var timeString = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		    var timeString = year+"-"+month+"-"+day;
			var oCtl = document.getElementById("createTime");
			var view=document.getElementById("time");
			oCtl.value = timeString;
			if(document.all){
				view.innerText= timeString;
			}else{
				view.textContent= timeString;
			}
		}
	</script>	
</head>
<body onload="setCurTime()">
	 <s:if test="%{#session.employee!=null}">
		<div class="top">
			<div class="global-width">
				<br /><img src="Images/logo.gif" class="logo" />
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
							增加报销单
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
										填写人：${sessionScope.employee.name}
										<input type="text" name="claimVoucher.id"
											value="${sessionScope.claimVoucher.id}" id="claimVoucherId" />
										<input type="text" name="employee.sn"
											value="${sessionScope.employee.sn}" />
										<input type="hidden" name="employee.name"
											value="${sessionScope.employee.name}" />
									</td>
									<td width="64%">
										填报时间：
										<span id="time"></span>
										<input type="hidden" name="claimVoucher.createTime" id="createTime"
											 readonly="readonly" value="${claimVoucher.createTime}" />
									</td>
								</tr>
								<tr>
									<td>
										总金额：${sessionScope.claimVoucher.totalAccount}
										<input type="hidden" name="claimVoucher.totalAccount" 
											value="${sessionScope.claimVoucher.totalAccount}" readonly="readonly" />
									</td>
									<td>
										状态：${claimVoucher.status}
										<input type="hidden" id="claimVoucher.status" name="claimVoucher.status" value="${claimVoucher.status}" readonly="readonly" />
									</td>
								</tr>
							</table>
							<p>
								&nbsp;
							</p>
							<table width="90%" border="0" cellspacing="0" cellpadding="0"
								class="addform-item">
								<thead>
									<tr>
										<td>
											项目
										</td>
										<td>
											金额
										</td>
										<td>
											费用说明
										</td>
										<td>
											操作
										</td>
									</tr>
								</thead>
								<c:if test="${sessionScope.details!=null}">
									<c:forEach items="${sessionScope.details}" var="detail">
										<tr>
											<td>
												<span id="item${detail.id}" >
													${detail.item}
												</span>
											</td>
											<td>
												<span id="account${detail.id}" >￥${detail.account}</span>
											</td>
											<td>
												<span id="desc${detail.id}" >${detail.desc}</span>
											</td>
											<c:choose>
												<c:when test="${claimVoucher.status!='新创建'}">
													<td>
														<img src="Images/edit.gif" width="16" height="16" />
													</td>
												</c:when>
												<c:otherwise>
													<td>
														<a href="#" name="${detail.id}"
															onclick="edit(this.name)"><img src="Images/edit.gif" width="16" height="16" /></a>
														<a href="#" name="${detail.id}"
															onclick="delDetail(this.name)"><img
																src="Images/delete.gif" width="16" height="16" />
														</a>
													</td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</c:if>
								<tr>
									<td>
										<select name="claimVoucherDetail.item" id="claimVoucherDetailItem">
											<option value="城际交通费">
												城际交通费
											</option>
											<option value="市内交通费">
												市内交通费
											</option>
											<option value="通讯费">
												通讯费
											</option>
											<option value="礼品费">
												礼品费
											</option>
											<option value="办公费">
												办公费
											</option>
											<option value="交际餐费">
												交际餐费
											</option>
											<option value="餐补">
												餐补
											</option>
											<option value="住宿费">
												住宿费
											</option>
										</select>
									</td>
									<td>
										<input type="text" name="claimVoucherDetail.account"
											id="claimVoucherDetailAccount" />
											
										
										<input type="hidden" id="claimVoucherDetailId" />	
										
									</td>
									<td>
										<input type="text" name="claimVoucherDetail.desc" id="claimVoucherDetailDesc"  />
									</td>
									<c:choose>
										<c:when test="${claimVoucher.status!='新创建'}">
											<td>
												<img src="Images/save.gif" width="16" height="16" />
											</td>
										</c:when>

										<c:otherwise>
											<td>
												<a href="#" onclick="addDetail()"><img
														src="Images/save.gif" width="16" height="16" />
												</a>
											</td>
										</c:otherwise>
									</c:choose>
								</tr>

								<!--报销单事由-->
								<tr>
									<td colspan="4" class="event">
										<label>
											事 由：
										</label>
										<c:choose>
											<c:when test="${claimVoucher.status!='新创建'}">
												<textarea rows="5" cols="66" name="claimVoucher.event"
													readonly="readonly">${claimVoucher.event}</textarea>
											</c:when>

											<c:otherwise>
												<textarea rows="5" cols="66" name="claimVoucher.event"
													id="event">${claimVoucher.event}</textarea>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>

								<!--表单提交行-->
								<tr>
									<td colspan="4" class="submit">
										<input type="button" id="1" name="1" value="保存"
											onclick="save()" class="submit_01" />
										<input type="button" id="2" name="2" value="提交"
											onclick="submitAll()" class="submit_01" />
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
