<%@page import="model.bean.Worker"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.Task"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="libraries/RGraph.common.core.js"></script>
<script src="libraries/RGraph.common.dynamic.js"></script>
<script src="libraries/RGraph.common.annotate.js"></script>
<script src="libraries/RGraph.common.context.js"></script>
<script src="libraries/RGraph.bar.js"></script>
<jsp:include page="../_bootstrap.jsp" />

<title>Cooking Managerment</title>
<script type="text/javascript">
	$(document).ready(function(){
		$(".navbar").css("background-color","#2020df");
		$(".navbar").css('border-radius', '0px');
		$(".navbar a").css("color","#ff9900");
		$(".navbar li").hover(function(){
	    $(this).css("background-color", "#6666cc");
	    }, function(){
	    $(this).css("background-color", "#2020df");
		});
	});
	function setVal(taskId, taskName, taskAmount) {
		document.getElementById("taskId").value = taskId;
		document.getElementById("taskName").value = taskName;
		document.getElementById("taskAmount").value = taskAmount;
	}
</script>
</head>
<body>
<!------------------------------------------- Menu -------------------------------->
	<%
		User user = (User) session.getAttribute("user");
		if (user != null) {
	%>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span>Home</a></li>
			<li><a href="ManageAccount">Quản lí Tài khoản</a></li>
			<li><a href="ManageTask">Quản lí Công việc</a></li>
			<li><a href="Scheduler">Xếp lịch</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span><%=user.getFullname() %></a></li>
			<li><a href="Logout"><span
					class="glyphicon glyphicon-log-in"></span> Logout</a></li>
		</ul>
	</div>
	</nav>


	<!------------------------------------------- Content --------------------------------> 
<div class="container-fluid" style="min-height: 430px">
  <div class="row content">
  <!------------------------------------------- Left-Menu --------------------------------> 
    <div class="col-sm-3 sidenav">
    <p>OPTION</p>
    <ul class="nav nav-tabs nav-stacked nav-pills">
	    <li class="active"><a data-toggle="tab" href="#menu1">Danh sách công việc</a></li>
	    <li><a data-toggle="tab" href="#menu2">Thông tin Đăng kí</a></li>
  	</ul>
    </div>
<!------------------------------------------- Main-content --------------------------------> 
    <div class="col-sm-9">
      <div class="tab-content">
	    <div id="menu1" class="tab-pane fade in active">
	      <h3>Danh sách công việc</h3>
	      <hr>
	      <%
	      ArrayList<Task> tasks = (ArrayList<Task>)request.getAttribute("tasks");
	      if(tasks != null){
	      %>
	      <table class="table table-striped table-bordered table-hover">
	      	<tr class="btn-lg">
	      		<td>ID</td>
	      		<td>Công việc</td>
	      		<td>Trọng số</td>
	      		<td>Actions</td>
	      	</tr>
	      	<% 
	      	for(int i=0; i<tasks.size(); i++){
	      	%>
	      	<tr class="btn-lg">
	      		<td><%= i+1 %></td>
	      		<td><%= tasks.get(i).getTaskName() %></td>
	      		<td><%= tasks.get(i).getTaskAmount() %> </td>
	      		<td>
	      			<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal" onclick = "setVal('<%=tasks.get(i).getTaskId()%>', '<%=tasks.get(i).getTaskName()%>','<%=tasks.get(i).getTaskAmount()%>')">Edit</button>
	      			<button type="button" class="btn btn-primary btn-sm" >Delete</button>
	      		</td>
	      	</tr>
	      	<%}} %>
	      </table>
	      
	      <div id="myModal" class="modal fade" role="dialog">
			  <div class="modal-dialog">
			
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Edit Task</h4>
			      </div>
			      <div class="modal-body">
			      	<form class="form-horizontal" action="UpdateTask" method="post">
			        <div class="form-group">
			        	<label class="control-label col-md-3" for="taskId">Task Id:</label>
			        	<div class="col-md-9">
				     		<input class="form-control" type="text" name="taskId" id="taskId" value="" disable readonly/>
				     	</div>
			        </div>
			        
			         <div class="form-group">
			        	<label class="control-label col-md-3" for="taskName">Task Name:</label>
			        	<div class="col-md-9">
				     		<input class="form-control" type="text" name="taskName" id="taskName" value="" required/>
				     	</div>
			         </div>
			        
			        <div class="form-group">
			        	<label class="control-label col-md-3" for="username">Task Amount:</label>
			        	<div class="col-md-9">
				     		<input class="form-control" type="text" name="taskAmount" id="taskAmount" value="" required/>
				     	</div>
			         </div>
			      
			        <div class="form-group">
			        	<div class="col-md-offset-3 col-md-3">
			        		<button class="btn btn-primary btn-block" type="submit">Submit</button>
			        	</div>
			        	<div class="col-md-3">
			        		<button class="btn btn-primary btn-block" type="reset">Reset</button>
			        	</div>
			        </div>
			        </form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			
			  </div>
			</div>
	    </div>
	    <!--                                     Menu 2: thông tin đăng kí                              -->
	    <div id="menu2" class="tab-pane fade">
	      <h3>Thông tin đăng kí</h3>
	      <hr>
	      <%
	      ArrayList<Worker> workers = (ArrayList<Worker>)request.getAttribute("workers");
	      if(workers != null){
	      %>
	      <table class="table table-striped  table-bordered">
	      	<tr class="btn-lg">
	      		<td>ID</td>
	      		<td>Username</td>
	      		<td>Số buổi làm việc</td>
	      	</tr>
	      	<% for(int i=0; i< workers.size(); i++){ %>
	      	<tr class="btn-lg">
	      		<td><%= i+1 %></td>
	      		<td><%= workers.get(i).getUsername() %></td>
	      		<td><%= workers.get(i).getNumOfFreeTime() %></td>
	      	</tr>
	      	<%} %>
	      </table>
	      
	      <br><br>
	      <%} %>
	    </div>
	  </div>
     
    </div>  
    </div>
</div>
			
<%} %>
<footer class="alert alert-info" >
  <center>Copyright@2016-5S team</center>
</footer>
</body>
</html>
