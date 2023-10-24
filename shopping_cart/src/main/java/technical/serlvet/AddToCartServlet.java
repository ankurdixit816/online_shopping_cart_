package technical.serlvet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import technical_model.Cart;

@WebServlet("/Add-To-Cart-Servlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      
      try(PrintWriter out = response.getWriter()){
    	  ArrayList<Cart> cartList = new  ArrayList<Cart>();
    	  
    	  int id = Integer.parseInt(request.getParameter("id"));
    	  Cart cm = new Cart();
    	  cm.setId(id);
    	  cm.setQuantity(1);
    	  
    	  HttpSession session = request.getSession();
    	  ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    	  
    	  if(cart_list == null) {
    		  cartList.add(cm);
    		  session.setAttribute("cart-list", cartList);
    		  out.println("session created and added the list");
    	  }
    	  else {  
    		  cartList = cart_list;
    		  boolean exist = false;
    		  
    		  
    		  for(Cart c:cart_list) {
    			  if(c.getId()== id) {
    				  exist= true;
    				  out.println("<h3 style='color: crimson; text-align:center'> Item already exist in cart. <a href='cart.jsp'> Go TO Cart Page</a></h3> ");
    			  }
    		  }
    		  if(!exist) {
    			  cartList.add(cm);
    			  response.sendRedirect("index.jsp");
    		  }
    	  }
    }
   }
	}