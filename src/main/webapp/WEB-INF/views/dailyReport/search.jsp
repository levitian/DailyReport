<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>日报查询列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-slider.css">
	<style type="text/css">
		.form-horizontal .form-group {
		    margin-right: -14px;
		    margin-left: -15px;
		}
	</style>
	
  </head>
  
  <body style="margin:10px">


	<input id="queryDailyReport" class="btn btn-primary" type="button" value="查询日报" />
	
	<a href="<%=request.getContextPath()%>/dr" id="nowDate" class="btn btn-primary">今日日报</a>
 

	<table id="example" class="table" cellspacing="0" width="100%">
	     <thead>
	         <tr>
	             <th style="display: none">ID</th>
	             <th>工作日</th>
	             <th>姓名</th>
	             <th>工作内容（任务）</th>
	             <th>牵头人</th>
	             <th>工作进展</th>
	             <th>是否测试</th>
	             <th>是否提交SVN</th>
	             <th>遇到的困难/问题</th>
	             <th>备注</th>
	         </tr>
	     </thead>
	     <tbody>
	     	<c:forEach items="${drs }" var="dr">
	     		<tr>
					<td style="display: none">${dr.id }</td>	     		
					<td><fmt:formatDate value="${dr.work_date }" pattern="yyyy/MM/dd" /></td>	     		
					<td>${dr.name }</td>	     		
					<td>${dr.work_content }</td>	     		
					<td>${dr.initiator }</td>	     		
					<td>${dr.job_progress }</td>	     		
					<td>${dr.is_test }</td>	     		
					<td>${dr.is_submit }</td>	     		
					<td>${dr.doubt }</td>	     		
					<td>${dr.remark }</td>	     		
	     		</tr>
	     	</c:forEach>
	     </tbody>
	 </table>
	 
	 <div id="dialog-qeury-dr" title="工作日报查询" style="display:none">
	 	<form id="query-dr-form" class="form-horizontal" role="form" action="<%=request.getContextPath()%>/dr/search" method="POST">
			<div class="form-group">
                  <label class="col-sm-3 control-label" for="ds_host">姓名</label>
                  <div class="col-sm-6">
                     <input class="form-control" id="query-dr-name" name="query_name" type="text" placeholder="eg:Jim"/>
                  </div>
               </div>               
			<div class="form-group">
                <label for="dtp_input2" class="col-md-3 control-label">开始日期</label>
                <div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" id="start_work_date" name="start_work_date" type="text" placeholder="2001-01-02" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
				<input type="hidden" id="dtp_input2" value="" /><br/>
            </div>
			<div class="form-group">
                <label for="dtp_input2" class="col-md-3 control-label">结束日期</label>
                <div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" id="end_work_date" name="end_work_date" type="text" placeholder="2001-01-02" value="<%=today %>" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
				<input type="hidden" id="dtp_input2" value="" /><br/>
            </div>   
			<div class="form-group" style="float: right; margin: auto;">
				<input class="btn btn-primary" type="submit" value="查询">
            </div>                        
         </form>  
	 </div>
	 
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/bootstrap-slider.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/ehualu.date.format.js"></script>
	<script type="text/javascript">
		$(function() {
			var myDate = new Date();
			//alert(getSmpFormatDateByLong(myDate,false));

			function GetdateTime(AddDayCount) { 
				var dd = new Date(); 
				dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
				var y = dd.getFullYear(); 
				var m = dd.getMonth()+1;//获取当前月份的日期 
				var d = dd.getDate(); 
				return y+"-"+m+"-"+d; 
			} 
			
			
			var attrbutieDate = '${dateTime }';
			if(attrbutieDate == GetdateTime(0)){
				$("#nextDate").hide();
			}

				$('.form_date').datetimepicker({
			        language:  'zh-CN',
			        weekStart: 1,
			        todayBtn:  1,
					autoclose: 1,
					todayHighlight: 1,
					startView: 2,
					minView: 2,
					forceParse: 0
			    });
				$('.form_date').datepicker('option', 'currentText', 'Now'); 
				
				$('#ex1').slider({
		          	formatter: function(value) {
		            	return 'Current value: ' + value;
		          	}
		        });	
				
				$("#queryDailyReport").on("click",function(){
					$( "#dialog-qeury-dr" ).dialog({
						resizable: false,
						width:450,
						height:300,
						modal: true
					 });
				});	
				
		});
	 </script>
  </body>
</html>