<%@page import="model.bean.UserTask"%>
<%if(session.getAttribute("user")==null) response.sendRedirect("Login"); %>
<%@page import="model.bean.User"%>
<%
	User user = (User) session.getAttribute("user");
%>

<%@page import="model.bean.WorkTime"%>
<%@page import="org.w3c.dom.ls.LSInput"%>
<%@page import="model.bean.Task"%>
<%@page import="model.bean.Works"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cooking Managerment</title>
<jsp:include page="_bootstrap.jsp" />
<style>
 /* Set black background color, white text and some padding */
    footer {
      background-color: #2020df;
      color: white;
      padding: 15px;
      text-align: center;
    }
    /*                   */
.nav.navbar-nav li a {
    color: #ffbf00;
}
.nav.navbar-nav li:active,
.nav.navbar-nav li:focus ,
.nav.navbar-nav li:hover {
    background-color: #bf00ff;
}
.table-bordered.table-striped tr {
	height: 25px;
}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
<!------------------------------------------- Menu --------------------------------> 
	<nav class="navbar navbar-inverse" style="background-color: #2020df">
	  <div class="container-fluid" > 
	    <ul class="nav navbar-nav" >
	      <li><a href="#"><span class="glyphicon glyphicon-home"></span>Home</a></li>
	      <li><a href="Task">Công việc</a></li> 
	      <li><a href="Account">Tài khoản</a></li>
	      <li><a href="Statistic">Thống kê</a></li>
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	      <li><a href="#"><span class="glyphicon glyphicon-user"></span><%=user.getFullname() %></a></li>
	      <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Log out</a></li>
	    </ul>
	  </div>
	</nav>

<!------------------------------------------- Content --------------------------------> 
<div class="container-fluid" style="min-height: 420px">
  <div class="row content">
  <!------------------------------------------- Left-Menu --------------------------------> 
    <div class="col-sm-3 sidenav">
    <p>OPTION</p>
    <ul class="nav nav-tabs nav-stacked">
	    <li class="active"><a data-toggle="tab" href="#menu1">Công việc trong tuần</a></li>
	    <li><a data-toggle="tab" href="#menu2">Đăng kí công việc</a></li>
	    <li><a data-toggle="tab" href="#menu3">Đăng kí thời gian rảnh</a></li>
  	</ul>
     <br>
      
    </div>
<!------------------------------------------- Main-content --------------------------------> 
    <div class="col-sm-9">
      <div class="tab-content">
      
      	<!-- tab công việc trong tuần -->
	    <div id="menu1" class="tab-pane fade in active">
	      <h3>Công việc trong tuần</h3>
	      <div class="container">
	      <%
	      		ArrayList<Works> listWorks = new ArrayList<>();
	      		listWorks = (ArrayList<Works>) request.getAttribute("listWorks");
	      %>
	      <table class="table-bordered table-striped" style="width:70%">
	      	<tr>
		      	<td>Thứ 2</td>
		      	<td>Thứ 3</td>
		      	<td>Thứ 4</td>
		      	<td>Thứ 5</td>
		      	<td>Thứ 6</td>
		      	<td>Thứ 7</td>
		      	<td>Chủ nhật</td>
	      	</tr>
	      	<tr>
	      	<%
	      		for(int i = 0; i < 7; i++) {
	      			String works = "";
	      			if(listWorks!=null) {
	      				for(int j = 0; j < listWorks.size(); j++) {
	      					if (listWorks.get(j).getDay()==i && listWorks.get(j).getTime()==0) {
	      						works=listWorks.get(i).getTaskName();
	      						break;
	      					}
	      				}
	      			}
	      			out.print("<td>"+works+"</td>");
	      		}
	      	%>
	      	</tr>
	      	<tr>
	      	<%
	      		for(int i = 0; i < 7; i++) {
	      			String works = "";
	      			if(listWorks!=null) {
	      				for(int j = 0; j < listWorks.size(); j++) {
	      					if (listWorks.get(j).getDay()==i && listWorks.get(j).getTime()==1) {
	      						works=listWorks.get(i).getTaskName();
	      						break;
	      					}
	      				}
	      			}
	      			out.print("<td>"+works+"</td>");
	      		}
	      	%>
	      	</tr>
	      </table>
	      </div>
	    </div>
	    
	    <!-- tab đăng ký công việc -->
	    <div id="menu2" class="tab-pane fade">
	      <h3>Đăng kí công việc</h3>
	      <div class="container">
	      <%
	      	ArrayList<Task> listTask = new ArrayList<>();
	      	listTask = (ArrayList<Task>) request.getAttribute("listTask");
	      	
	      	ArrayList<Task> listTaskRegistered = new ArrayList<>();
	      	listTaskRegistered = (ArrayList<Task>) request.getAttribute("listTaskRegistered");
	      %>
	      <form action = "UpdateTaskRegisterd" method="Post">
		      <table class="table-bordered table-striped" style="width:50%">
		      	<tr>
		      	<td>Công việc</td>
		      	<td>Lựa chọn</td>
		      	</tr>
		      	<%
		      		if(listTask!=null) {
		      			for(int i = 0; i < listTask.size(); i++) {
	      					String state = "";
		      				if(listTaskRegistered!=null) {
			      				for(int j = 0; j < listTaskRegistered.size(); j++) {
			      					if(listTaskRegistered.get(j).getTaskName().equals(listTask.get(i).getTaskName())) {
			      						state="checked";
			      						break;
			      					}
			      					
			      				}
		      				}
		      				out.print("<tr>"
		      						+ "<td>" + listTask.get(i).getTaskName() + "</td>"
		      						+ "<td>" + "<center><input type='checkbox' name='task' value='" + listTask.get(i).getTaskId() +"' " + state +"></center>" +"</td>"
		      					+"</tr>");
		      			}
		      			
		      		}
		      	%>
		      </table>
		      <br>
		      <input type="submit" value="submit">
	      </form>
	      </div>
	    </div>
	    
	    <!-- tab đăng ký thời gian rảnh -->
	    <div id="menu3" class="tab-pane fade">
	      <h3>Đăng kí thời gian rảnh</h3>
	      <div class="row">
	      <div class="col-md-1">
	      	<br>
	      	<p>Sáng </p>
	 		<p>Chiều </p>
	      </div>
	      <div class="col-md-11">
	      <%
		      	ArrayList<WorkTime> freeTime = new ArrayList<>();
	      		freeTime = (ArrayList<WorkTime>) request.getAttribute("freeTime");
	      %>
	      <form action="UpdateFreeTime" method="post">
		      <table class="table-bordered table-striped" style="width:80%">
		      	<tr>
			      	<td>Thứ 2</td>
			      	<td>Thứ 3</td>
			      	<td>Thứ 4</td>
			      	<td>Thứ 5</td>
			      	<td>Thứ 6</td>
			      	<td>Thứ 7</td>
			      	<td>Chủ nhật</td>
		      	</tr>
		      	<tr>
		      	<%
		      		for(int i = 0; i < 7; i++) {
		      			String state="";
		      			if(freeTime!=null) {
		      				for(int j = 0; j < freeTime.size(); j++) {
		      					if(freeTime.get(j).getDay()==i && freeTime.get(j).getTime()==0) {
		      						state="checked";
		      						break;
		      					}
		      				}
		      			}
		      			out.print("<td><center><input type='checkbox' name='time0' value='" + i + "' " + state + "></center></td>");
		      		}
		      	%>
		      	</tr>
		      	<tr>
		      	<%
		      		for(int i = 0; i < 7; i++) {
		      			String state="";
		      			if(freeTime!=null) {
		      				for(int j = 0; j < freeTime.size(); j++) {
		      					if(freeTime.get(j).getDay()==i && freeTime.get(j).getTime()==1) {
		      						state="checked";
		      						break;
		      					}
		      				}
		      			}
		      			out.print("<td><center><input type='checkbox' name='time1' value='" + i + "' " + state + "></center></td>");
		      		}
		      	%>
		      	</tr>
		      </table>
		      <br>
		      <input type="submit" value="submit">
	      </form>
	      </div>
	     </div>
	    </div>
	  </div>
    </div>
  </div>
</div>

<footer class="container-fluid">
  <p>Copyright@2016-5S team</p>
</footer>
</body>
</html>
