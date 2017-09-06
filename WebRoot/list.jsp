<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="java.text.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>北大青鸟办公自动化管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="top"><div class="global-width"><img src="Images/logo.gif" class="logo" /></div></div>

<div class="status"><div class="global-width"><span class="usertype">【登录角色：部门经理】</span>董平你好！欢迎访问青鸟办公管理系统！</div></div>

<div class="main"><div class="global-width">
	<div class="search">
        <form name="listSearch" method="post" action="">
            选择查询的日期：<input type="text" class="keywords" />
            <input type="submit" value="提交查询" class="sub-search" />
        </form>
    </div>


	<div class="nav" id="nav">
    	<div class="t"></div>
    	<dl class="open">
        	<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">报销单管理</dt>
            <dd><a href="index.html" target="_self">添加报销单</a></dd>
            <dd><a href="list.html" target="_self">审核报销单</a></dd>
        </dl>
        <dl>
        	<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">我要采购</dt>
            <dd>信心收件箱</dd>
            <dd>信心发件箱</dd>
        </dl>
        <dl>
        	<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">我要销售</dt>
            <dd>信心收件箱</dd>
            <dd>信心发件箱</dd>
        </dl>
        <dl>
        	<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">信息中心</dt>
            <dd>信心收件箱</dd>
            <dd>信心发件箱</dd>
        </dl>
    </div>
    <div class="action">
    	<div class="t">增加报销单</div>
   		<div class="pages">
        	<!--增加报销单 区域 开始-->
                <table width="90%" border="0" cellspacing="0" cellpadding="0" class="list">
                  <tr>
                    <td>编号</td>
                    <td>填报日期</td>
                    <td>填报人</td>
                    <td>总金额</td>
                    <td>状态</td>
                    <td>操作</td>
                  </tr>
                  <tr>
                    <td>3265</td>
                    <td>2010-6-6 15：30</td>
                    <td>董平</td>
                    <td>￥125.0.</td>
                    <td>待审核</td>
                    <td><img src="Images/edit.gif" width="16" height="16" /> <img src="Images/delete.gif" width="16" height="16" /> <img src="Images/save.gif" width="16" height="16" /> <img src="Images/search.gif" width="16" height="15" /> <img src="Images/sub.gif" width="16" height="16" /></td>
                  </tr>
                  <tr>
                    <td>3265</td>
                    <td>2010-6-6 15：30</td>
                    <td>董平</td>
                    <td>￥125.0.</td>
                    <td>待审核</td>
                    <td><img src="Images/edit.gif" width="16" height="16" /> <img src="Images/delete.gif" width="16" height="16" /> <img src="Images/save.gif" width="16" height="16" /> <img src="Images/search.gif" width="16" height="15" /> <img src="Images/sub.gif" width="16" height="16" /></td>
                  </tr>
                  <tr>
                    <td>3265</td>
                    <td>2010-6-6 15：30</td>
                    <td>董平</td>
                    <td>￥125.0.</td>
                    <td>待审核</td>
                    <td><img src="Images/edit.gif" width="16" height="16" /> <img src="Images/delete.gif" width="16" height="16" /> <img src="Images/save.gif" width="16" height="16" /> <img src="Images/search.gif" width="16" height="15" /> <img src="Images/sub.gif" width="16" height="16" /></td>
                  </tr>
                  <tr>
                    <td>3265</td>
                    <td>2010-6-6 15：30</td>
                    <td>董平</td>
                    <td>￥125.0.</td>
                    <td>待审核</td>
                    <td><img src="Images/edit.gif" width="16" height="16" /> <img src="Images/delete.gif" width="16" height="16" /> <img src="Images/save.gif" width="16" height="16" /> <img src="Images/search.gif" width="16" height="15" /> <img src="Images/sub.gif" width="16" height="16" /></td>
                  </tr>
                  <tr>
                    <td colspan="6" align="center">首页 前5页 1 2 3 4 5 后5页 尾页</td>
                  </tr>
                </table>        
            <!--增加报销单 区域 结束-->
        </div>
    </div>
</div></div>

<div class="copyright">Copyright  &nbsp;   &copy;  &nbsp; 北大青鸟</div>

</body>
</html>