package technical_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import technical_model.Cart;
import technical_model.Product;

public class ProductDao {
       
	private Connection con;
	private ResultSet rs;
	private String query;
	private PreparedStatement pst;

	public ProductDao(Connection con) throws ClassNotFoundException, SQLException {
		this.con = con;

	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();

		try {
			query = "select * from product";
			pst = this.con.prepareStatement(query);
			rs = (ResultSet) pst.executeQuery();
			while (((ResultSet) rs).next()) {
				Product row = new Product();
				row.setId(((ResultSet) rs).getInt("id"));
				row.setName(((ResultSet) rs).getString("name"));
				row.setCategory(((ResultSet) rs).getString("category"));
				row.setPrice(((ResultSet) rs).getInt("price"));
				row.setImage(((ResultSet) rs).getString("image"));

				products.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;
	}

	
	  public Product getSingleProduct(int id) {
		  Product row = null;
	  
	  try {
	  query = "select * from products where id=?";
	  pst=this.con.prepareStatement(query);
	  pst.setInt(1, id);
	  rs = (ResultSet) pst.executeQuery();
	  
	  while(((ResultSet) rs).next()) {
	  row = new Product();
	  row.setId(((ResultSet) rs).getInt("id"));
	  row.setName(((ResultSet) rs).getString("name"));
	  row.setCategory(((ResultSet) rs).getString("category"));
	  row.setPrice(((ResultSet) rs).getDouble("price")); 
	  row.setImage(((ResultSet)  rs).getString("image"));
	  
	  }
	  }catch(Exception e) {
		  e.printStackTrace();
		  }
	  
	  return row;
	  }
	 
	  

	public List<Cart> getCartProducts(ArrayList<Cart> cartList){ 
		List<Cart> products = new ArrayList<Cart>();
  
  try {
	  if(cartList.size()>0) {
		  for(Cart item:cartList) { 
			  query = "select * from product where id=?";
			  pst=this.con.prepareStatement(query);
			  pst.setInt(1, item.getId()); 
			  rs =(ResultSet) pst.executeQuery();
			  while(((ResultSet) rs).next()) { 
			   Cart row =new Cart();
			   row.setId(((ResultSet) rs).getInt("id")); 
			   row.setName(((ResultSet) rs).getString("name"));
			   row.setCategory(((ResultSet) rs).getString("category"));
			   row.setPrice(((ResultSet)rs).getDouble("Price")*item.getQuantity());
			   row.setQuantity(item.getQuantity());
              products.add(row); 
              } 
			  }
		  }
  
  
  }catch(Exception e) {
	  System.out.println(e.getMessage());
	  }
  return products;
  
}


	public double getTotalCartPrice(ArrayList<Cart> cartList) { 
	double sum=0;
  
  try {
	  if(cartList.size()>0) { 
		  for(Cart item:cartList) {
		query ="select price from product where id=?";
		pst =this.con.prepareStatement(query);
		pst.setInt(1, item.getId());
		rs = (ResultSet) pst.executeQuery();
  
        while(((ResultSet) rs).next()) {
         sum+=((ResultSet)rs).getDouble("price")*item.getQuantity();
         }
        }
		  }
	  }catch(Exception e) {
  e.printStackTrace(); }
  return sum;
  }
}