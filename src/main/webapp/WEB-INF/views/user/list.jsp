<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.dataTables.css">
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
  </head>
  
  <body>
	<table id="example" class="display" cellspacing="0" width="100%">
	     <thead>
	         <tr>
	             <th>userId</th>
	             <th>username</th>
	             <th>email</th>
	             <th>password</th>
	             <th>phone</th>
	         </tr>
	     </thead>
	     <tfoot>
	         <tr>
	             <th>userId</th>
	             <th>username</th>
	             <th>email</th>
	             <th>password</th>
	             <th>phone</th>
	         </tr>
	     </tfoot>
	 </table>
	 <script type="text/javascript">
		$(document).ready(function() {
		    $('#example').dataTable( {
		        "processing": true,
		        "serverSide": true,
		        "ajax": {
		            "url": '${pageContext.request.contextPath}' + "/user/list",
		            "type": "GET"
		        },
		        "columns": [
		            { "data": "userId" },
		            { "data": "username" },
		            { "data": "email" },
		            { "data": "password" },
		            { "data": "phone" }
		        ]
		    } );
		} );
	 </script>
  </body>
</html>
