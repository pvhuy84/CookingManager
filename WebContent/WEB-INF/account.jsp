<%if(session.getAttribute("user")==null) response.sendRedirect("Login"); %>
<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	User user = (User) session.getAttribute("user");
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cooking Managerment</title>
<jsp:include page="_bootstrap.jsp" />
<style>
div{
padding-top: 1px;
}
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
	      <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
	    </ul>
	  </div>
	</nav>

<!------------------------------------------- Content --------------------------------> 
<div class="container-fluid" style="min-height: 430px">
  <div class="row content">
  <!------------------------------------------- Left-Menu --------------------------------> 
    <div class="col-sm-3 sidenav">
    <p>OPTION</p>
    <%	
    	String isActiveTabChangePassword="active";
    	String isActiveTabChangeName="";
   		if(request.getAttribute("reportChangeName")!=null) {
   			isActiveTabChangePassword="";
   			isActiveTabChangeName="active";
   		}
	  %>
    <ul class="nav nav-tabs nav-stacked">
	    <li class="<%=isActiveTabChangePassword%>"><a data-toggle="tab" href="#menu1">Đổi mật khẩu</a></li>
	    <li class="<%=isActiveTabChangeName%>"><a data-toggle="tab" href="#menu2">Thông tin Tài khoản</a></li>
  	</ul>
     <br>
    </div>
<!------------------------------------------- Main-content --------------------------------> 
    <div class="col-sm-9">
      <div class="tab-content">
      
	    <div id="menu1" class="tab-pane fade in <%=isActiveTabChangePassword%>">
	      <h3>Đổi mật khẩu</h3>
	      <form method="post" action="ChangePassword">
		     	<div class="row">
			     	<div class="col-md-2"></div>
			     	<div class="col-md-6">
			     	<center><img src="images/avatar.png" class="img-circle" alt="Avartar" width="200" height="150"></center>
			     	</div>
		     	</div>
		     	<br>
		     	<%	
		      		if(request.getAttribute("reportChangePassword")!=null){
		      			String reportChangePassword="";
		      			String state="red";
		      			reportChangePassword=(String) request.getAttribute("reportChangePassword");
		      			if("Đổi mật khẩu thành công".equals((reportChangePassword))) {
		      				state = "green";	
		      			}
		      			out.print("<p style='color: "+state+"'>"+reportChangePassword+"</p>");
		      		}
		      	%>
		     	<div class="row">
			     	<div class="col-md-2">
			     	Mật khẩu cũ 
			     	</div>
			     	<input class="col-md-6" type="password" value="" name="oldpassword"/>
		     	</div>
		     	<br>
		     	<div class="row">
			     	<div class="col-md-2">
			     	Mật khẩu mới
			     	</div>
			     	<input class="col-md-6" type="password" value="" name="newpassword"/>
		     	</div>
		     	<br>
		     	<div class="row">
			     	<div class="col-md-2">
			     	Xác nhận mật khẩu
			     	</div>
			     	<input class="col-md-6" type="password" value="" name="renewpassword"/>
		     	</div>
		     	<br>
		     	<div class="row">
			     	<div class="col-md-2"></div>
			     	<input class="col-md-2" type="submit" value="Submit"/>
			     	<div class="col-md-1"></div>
			     	<input class="col-md-2" type="reset" value="Reset"/>
		     	</div>
	     	</form>	
	    </div>
	    
	    <div id="menu2" class="tab-pane fade in <%=isActiveTabChangeName%>">
	      <h3>Thông tin tài khoản</h3>
	      <form method="post" action="ChangeName">
		     	<div class="row">
			     	<div class="col-md-2"></div>
			     	<div class="col-md-6">
			     	<center><img src="images/avatar.png" class="img-circle" alt="Avartar" width="200" height="150"></center>
			     	</div>
		     	</div>
		     	<br>
		     	<%	
		      		if(request.getAttribute("reportChangeName")!=null){
		      			String reportChangeName="";
		      			String state="red";
		      			reportChangeName=(String) request.getAttribute("reportChangeName");
		      			if("Đổi tên thành công".equals((reportChangeName))) {
		      				state = "green";	
		      			}
		      			out.print("<p style='color: "+state+"'>"+reportChangeName+"</p>");
		      		}
		      	%>
		     	<div class="row">
			     	<div class="col-md-2">
			     	Họ tên 
			     	</div>
			     	<input class="col-md-6" type="text" name="newname" value="<%=user.getFullname() %>"/>
		     	</div>
		     	<br>
		     	<div class="row">
			     	<div class="col-md-2"></div>
			     	<input class="col-md-2" type="submit" value="Submit"/>
			     	<div class="col-md-1"></div>
			     	<input class="col-md-2" type="reset" value="Reset"/>
		     	</div>
	     	</form>
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
