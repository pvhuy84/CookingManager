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
<script src="libraries/RGraph.common.core.js"></script>
<script src="libraries/RGraph.common.dynamic.js"></script>
<script src="libraries/RGraph.common.annotate.js"></script>
<script src="libraries/RGraph.common.context.js"></script>
<script src="libraries/RGraph.bar.js"></script>
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
   <div class="col-sm-9">
    <!-- Thống kê -->
      <h3>Thống kê</h3>
     	<div class="col-sm-8 col-sm-push-2">
    	<h2> Thống kê công việc</h2><hr>
    	<canvas id="cvs1" width="600" height="250">[No canvas support]</canvas>
    	<hr>
    	<canvas id="cvs2" width="600" height="250">[No canvas support]</canvas>
   	<%
    	ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
   		ArrayList<UserTask> userTasks2 = (ArrayList<UserTask>) request.getAttribute("userTasks2");
   		
   		if(user != null && userTasks2 != null){
   		String amounts[] = new String[users.size()];
   		String usernames[] = new String[users.size()];
   		String amounts2[] = new String[userTasks2.size()];
   		String usernames2[] = new String[userTasks2.size()];
   		for(int i=0; i<users.size(); i++){
   			amounts[i] = String.valueOf(users.get(i).getAmount());
   			usernames[i] = "'" + users.get(i).getUsername() + "'";
   		}
   		
   		for(int i=0; i<userTasks2.size(); i++){
   			amounts2[i] = String.valueOf(userTasks2.get(i).getTaskAmount());
   			usernames2[i] = "'" + userTasks2.get(i).getUsername() + "'";
   		}
    %>
   <script>
        window.onload = function ()
        {
            var bar = new RGraph.Bar({
                id:'cvs1',
                data: [<%=String.join(",", amounts)%>],
                options: {
                    backgroundGridDashed: true,
                    labels: [<%=String.join(",", usernames)%>],
                    title: 'Khối lượng công việc đã làm',
                    strokestyle: 'rgba(0,0,0,0)',
                    textAccessible: true
                }
            }).draw();
            var bar2 = new RGraph.Bar({
                id:'cvs2',
                data: [<%=String.join(",", amounts2)%>],
                options: {
                    backgroundGridDashed: true,
                    labels: [<%=String.join(",", usernames2)%>],
                    title: 'Khối lượng công việc trong tuần',
                    strokestyle: 'rgba(0,0,0,0)',
                    textAccessible: true
                }
            }).draw();
        };
    </script>
	<%} %>
	</div>
   </div>
   <!-- /Thống kê -->
 </div>

<footer class="container-fluid">
  <p>Copyright@2016-5S team</p>
</footer>
</body>
</html>
