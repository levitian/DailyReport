<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>日报列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css">
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
  </head>
  
  <body style="margin:10px">
	<input id="writeDailyReport" class="btn btn-primary" type="button" value="填写日报" />
	<a href="<%=request.getContextPath()%>/dr/exportTodayDailyReport" class="btn btn-primary" >导出日报</a>
  	<div id="dialog-confirm" title="填报工作日报" style="display:none">
 		<p>
 			<form id="add_form" class="form-horizontal" role="form">
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">工作日</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="work_date" name="work_date" type="text" placeholder="2015-12-02"/>
                   </div>
                </div>
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">姓名</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="name" name="name" type="text" placeholder="左锐锋"/>
                   </div>
                </div>
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">工作内容（任务）</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="work_content" name="work_content" type="text" placeholder="增加用户管理功能"/>
                   </div>
                </div>
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">牵头人</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="initiator" name="initiator" type="text" placeholder="田力"/>
                   </div>
                </div>                 				
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">工作进展(%)</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="job_progress" name="job_progress" type="text" placeholder="39"/>
                   </div>
                </div>
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">是否测试</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="is_test" name="is_test" type="text" placeholder="true"/>
                   </div>
                </div>
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">是否提交SVN</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="is_submit" name="is_submit" type="text" placeholder="false"/>
                   </div>
                </div>
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">遇到的困难/问题</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="doubt" name="doubt" type="text" placeholder="数据库无法连接"/>
                   </div>
                </div> 
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">备注</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="remark" name="remark" type="text" placeholder="接替田力工作"/>
                   </div>
                </div>                      				
 				<!-- 工作日:<input id="work_date" name="work_date" type="text" /><br /><br />
 				姓名:<input id="name" name="name" type="text" /><br /><br />
 				工作内容（任务）:<input id="work_content" name="work_content" type="text" /><br /><br />
 				牵头人:<input id="initiator" name="initiator" type="text" /><br /><br />
 				工作进展:<input id="job_progress" name="job_progress" type="text" /><br /><br />
 				是否测试:<input id="is_test" name="is_test" type="text" /><br /><br />
 				是否提交SVN:<input id="is_submit" name="is_submit" type="text" /><br /><br />
 				遇到的困难/问题:<input id="doubt" name="doubt" type="text" /><br /><br />
 				备注:<input id="remark" name="remark" type="text" /> -->
 			</form>
 		</p>
	</div>

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
	     <tbody></tbody>
	 </table>
	<script type="text/javascript">
		$(function() {
			$("#writeDailyReport").on("click",function(){
				$( "#dialog-confirm" ).dialog({
					resizable: false,
					width:750,
					height:600,
					modal: true,
					//按钮
					buttons: {
				       "cancle": function() {
				         $( this ).dialog( "close" );
				       },
				       "submit": function() {
				    		add_daily_report();
				       	 	$( this ).dialog( "close" );
				       }
				 	}
				 });				
			});
			

			function add_daily_report(){
				 $.ajax({
				        url: '<%=request.getContextPath()%>/dr/add',
				        data: {
				        	"work_date":$("#work_date").val(),
				        	"name":$("#name").val(),
				        	"work_content":$("#work_content").val(),
				        	"initiator":$("#initiator").val(),
				        	"job_progress":$("#job_progress").val(),
				        	"is_test":$("#is_test").val(),
				        	"is_submit":$("#is_submit").val(),
				        	"doubt":$("#doubt").val(),
				        	"remark":$("#remark").val()
				        },
				        cache: false,
				        async: true,
				        type: "POST",
				        dataType: 'json',
				        success: function(data) {
							if(data){
								alert("添加成功");
							}
				        }
				    });
			}
			
			
			 $.ajax({
			        url: '<%=request.getContextPath()%>/dr/list',
			        data: {},
			        cache: false,
			        async: true,
			        type: "GET",
			        dataType: 'json',
			        success: function(data) {
			        	var ctx = "";
						for(var i=0;i<data.length;i++){
							var da = data[i];
							ctx += "<tr>"
									+ "<td style='display:none'>" + da.id + "</td>"
									+ "<td>" + da.work_date + "</td>"
									+ "<td>" + da.name + "</td>"
									+ "<td>" + da.work_content + "</td>"
									+ "<td>" + da.initiator + "</td>"
									+ "<td>" + da.job_progress + "%</td>"
									+ "<td>" + da.is_test + "</td>"
									+ "<td>" + da.is_submit + "</td>"
									+ "<td>" + da.doubt + "</td>"
									+ "<td>" + da.remark + "</td>"
								+ "</tr>";
						}
						$("#example tbody").html(ctx)
			        }
			    });
		});
		 

	 </script>
  </body>
</html>
