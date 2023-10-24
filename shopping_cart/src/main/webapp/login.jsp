<%@page import="java.util.ArrayList" %>
<%@page import="technical_model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
    user auth = (user) request.getSession().getAttribute("auth");
    if(auth!=null){
    request.getAttribute("index.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list"); 
    if(cart_list != null){
   	 request.setAttribute("cart_list", cart_list);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Shopping Cart Login</title>
<%@include file="include/head.jsp"%>
</head>
<body>
	<%@include file="include/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<form action="user-login" method="post">

				<div class="form-group">
					<label>Email Address</label> <input type="email" class="form-control" name="login-email" placeholder="Enter your Email" required>
				</div>

				<div class="form-group">
					<label>Password</label> <input type="password" class="form-control"	name="login-password" placeholder="***********" required>
				</div>

				<div class="text-center">
					<button type="submit" class="btn btn-primary">Login</button>
				</div>
			</form>
		</div>
	</div>

		<%@include file="include/footer.jsp"%>
</body>
</html>