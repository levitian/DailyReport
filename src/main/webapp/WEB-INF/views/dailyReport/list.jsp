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
	<input id="writeDailyReport" class="btn btn-primary" type="button" value="填写日报" />
	<a href="<%=request.getContextPath()%>/dr/exportTodayDailyReport" class="btn btn-primary" >导出今天日报</a>
  	<div id="dialog-confirm" title="填报工作日报" style="display:none">
 		<p>
 			<form id="add_form" class="form-horizontal" role="form">
<!--        			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">工作日</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="work_date" name="work_date" type="text" placeholder="2015-12-02"/>
                   </div>
                </div> -->
        		<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">姓名</label>
                   <div class="col-sm-6">
                      <input class="form-control" id="name" name="name" type="text" placeholder="左锐锋"/>
                   </div>
                </div>               
				<div class="form-group">
	                <label for="dtp_input2" class="col-md-3 control-label">工作日</label>
	                <div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
	                    <input class="form-control" size="16" id="work_date" name="work_date" type="text" placeholder="2001-01-02" value="" readonly>
	                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
	                </div>
					<input type="hidden" id="dtp_input2" value="" /><br/>
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
                      <!-- <input id="ex1" data-slider-id='ex1Slider' type="text" data-slider-min="0" data-slider-max="20" data-slider-step="1" data-slider-value="14"/> -->
                   </div>
				</div>
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">是否测试</label>
                   <div class="col-sm-6">
						<select class="form-control" id="is_test">
						  	<option value="true">是</option>
						  	<option value="false">否</option>
						</select>                      
                   </div>
                </div>                
       			<div class="form-group">
                   <label class="col-sm-3 control-label" for="ds_host">是否提交SVN</label>
                   <div class="col-sm-6">
                     <!--  <input class="form-control" id="is_submit" name="is_submit" type="text" placeholder="false"/> -->
					  <select class="form-control" id="is_submit">
					  	<option value="true">是</option>
					  	<option value="false">否</option>
					  </select>                         
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
	 
	 
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/bootstrap-slider.min.js"></script>
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/ehualu.date.format.js"></script>
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
								location.reload();
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
									+ "<td>" + getSmpFormatDateByLong(da.work_date,false) + "</td>"
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
				
		    	$('#ex1').slider({
		          	formatter: function(value) {
		            	return 'Current value: ' + value;
		          	}
		        });	

		});
	 </script>
  </body>
</html>
