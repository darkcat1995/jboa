<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加报销单页</title>
		<script type="text/javascript">
		function save(){
			var myForm = document.getElementById("myForm");
			myForm.action = "save.action";
			if(checkFields("save")){
				myForm.submit();
			}
		}	
		
		function submitAll(){
			var myForm = document.getElementById("myForm");
			myForm.action = "submit.action";
			if(checkFields("submitAll")){
				myForm.submit();
			}
		}

		function addDetail(){
			var myForm = document.getElementById("myForm");
			myForm.action = "addDetail.action";
			
			if(checkFields("addDetail")){
				myForm.submit();
			}
	    }	
	    function delDetail(id){
			var myForm = document.getElementById("myForm");
			myForm.action = "delDetail.action?id="+id;
			myForm.submit();
   		}

	    function search(){
			var myForm = document.getElementById("myForm");
			myForm.action = "searchClaimVoucher.action";
			myForm.submit();
   		}
   		
	    function checkFields(opt){
	    	var claimVoucherEvent = document.getElementById("event").value;			
			var claimVoucherDetailAccount = document.getElementById("account").value;
			var claimVoucherDetailAesc = document.getElementById("desc").value;
			
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
       </script>
	</head>
	<jsp:include page="top.jsp" />
	<hr />
	<body>
		<s:if test="%{#session.employee!=null}">
			<h1>报销单添加</h1>
			<s:actionmessage />
			<s:form id="myForm" name="myForm">
				<s:fielderror />
				<table>
					<tr>
						<td>
							*填报人：
						</td>
						<td>
							<input type="text" name="claimVoucher.id"
								value="${claimVoucher.id}" id="claimVoucherId">
							<input type="hidden" name="employee.sn"
								value="${sessionScope.employee.sn}">
							${sessionScope.employee.name}
						</td>
						<td>
							*填报时间：
						</td>
						<td>

							${sessionScope.claimVoucher.createTime}

						</td>
					</tr>
					<tr>
						<td>
							*总金额：￥
						</td>
						<td>
							<input type="text" name="claimVoucher.totalAccount"
								value="${sessionScope.claimVoucher.totalAccount}" readonly="readonly" />
						</td>
						<td>
							*状态：
						</td>
						<td>
							<input type="text" id="claimVoucher.status"
								name="claimVoucher.status" value="${claimVoucher.status}"
								readonly="readonly" />
						</td>
					</tr>
				</table>
				<table>
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
					<c:if
						test="${sessionScope.claimVoucher.bizClaimVoucherDetails!=null}">
						<c:forEach
							items="${sessionScope.claimVoucher.bizClaimVoucherDetails}"
							var="detail">
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

								<c:choose>
									<c:when test="${claimVoucher.status!='新创建'}">
										<td>
											[X]
										</td>
									</c:when>

									<c:otherwise>
										<td>
											<a href="#" name="${detail.id}"
												onclick="delDetail(this.name)">[X]</a>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
						<td>
							<select name="claimVoucherDetail.item">
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
							<input type="text" name="claimVoucherDetail.account" id="account" />
						</td>
						<td>
							<input type="text" name="claimVoucherDetail.desc" id="desc" />
						</td>
						<c:choose>
							<c:when test="${claimVoucher.status!='新创建'}">
								<td>
									[S]
								</td>
							</c:when>

							<c:otherwise>
								<td>
									<a href="#" onclick="addDetail()">[S]</a>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							*事由：
						</td>
						<td colspan="3">
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
					<tr align="center">
						<td colspan="2">
							<input type="button" id="1" name="1" value="保存" onclick="save()" />
						</td>
						<td colspan="2">
							<input type="button" id="2" name="2" value="提交"
								onclick="submitAll()" />
						</td>
					</tr>
				</table>
			</s:form>
		</s:if>
		<s:else>
			<h1>
				你还没登录，请您先登录。
				<a href="login.jsp">返回</a>
			</h1>
		</s:else>
		<br>
	</body>
</html>
