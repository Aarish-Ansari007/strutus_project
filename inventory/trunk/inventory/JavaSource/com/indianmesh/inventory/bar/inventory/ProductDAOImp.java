package com.indianmesh.inventory.bar.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.bar.inventory.ProductBaseDAO;
import com.indianmesh.inventory.bar.inventory.ProductFormVO;
import com.indianmesh.inventory.utils.dbConnection.DBConnection;

public class ProductDAOImp implements ProductBaseDAO {
			
			public int deleteBarInventoryProduct(ProductFormVO formVO) throws SQLException, Exception{

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				PreparedStatement pStmt1 = null;
				
				int ret = 0;
					
				String Query1="DELETE FROM inventory_kitchen_inventory_product WHERE ingredientid=? ";
				String Query2="DELETE FROM inventory_kitchen_product WHERE productid=? ";
				
				try{
					con=dbConnection.getConnection();
					pStmt = con.prepareStatement(Query1);
					pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
					
					if(Integer.parseInt(formVO.getProductId())!=1)
						ret = pStmt.executeUpdate();
					
					pStmt1 = con.prepareStatement(Query2);
					pStmt1.setInt(1, Integer.parseInt(formVO.getProductId()));
					
					if(Integer.parseInt(formVO.getProductId())!=1)
						ret = pStmt1.executeUpdate();
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return ret;			
			}
			
			public ProductFormVO addBarInventoryProduct(ProductFormVO formVO) throws SQLException,Exception{

				DBConnection dbConnection = new DBConnection();
				
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs  = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1  = null;
				PreparedStatement pStmt2 = null;
				
				String inventoryId = null;
				
				int temp = 0;

				String countQuery=" SELECT COUNT(*) FROM inventory_kitchen_inventory"; 
				String idQuery=" SELECT productid FROM inventory_kitchen_inventory where productid=?"; 
						
				String Query="INSERT INTO inventory_kitchen_inventory ( productid, sellerid, subsellerid, createddate, lastmodifieddate, statuson, statusoff, status ) VALUES ( ?,?,?,?,?,?,?,? ) ";		
						
				try{
					con = dbConnection.getConnection();
							
					pStmt = con.prepareStatement(countQuery);
					rs = pStmt.executeQuery();
					
					while(rs.next()){
						temp = rs.getInt(1);
						
						for(int i=1; i<=temp+1; i++){
							pStmt1 = con.prepareStatement(idQuery);
							pStmt1.setInt(1,i);
							rs1 = pStmt1.executeQuery();					
							
							if(!rs1.next())
								inventoryId = String.valueOf(i);
						}
					}
					
					pStmt2=con.prepareStatement(Query);
					pStmt2.setString(1,inventoryId);
					pStmt2.setString(2,formVO.getSellerId());
					pStmt2.setString(3,formVO.getSubsellerId());
					pStmt2.setDate(4,new java.sql.Date(new Date().getTime()));
					pStmt2.setDate(5,new java.sql.Date(new Date().getTime()));
					pStmt2.setString(6,"btn btn-default btn-on btn-sm active");
					pStmt2.setString(7,"btn btn-default btn-off btn-sm");
					pStmt2.setString(8,"true");
					pStmt2.executeUpdate();
					
					formVO.setProductId(inventoryId);
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}	
				
				return formVO;	
			}

			
			public ProductFormVO editBarInventoryProduct(ProductFormVO formVO) throws SQLException,Exception{

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				
				String Query="SELECT productid, name, producttypeid, description, quantityid, quantitytypeid from inventory_kitchen_inventory WHERE productid=? ";

				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);
					pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
					rs = pStmt.executeQuery();
					
					while(rs.next()){							
						formVO.setProductId(rs.getString("productid"));
						formVO.setName(rs.getString("name"));
						formVO.setProductTypeId(rs.getString("producttypeid"));
						formVO.setDescription(rs.getString("description"));
						formVO.setQuantityId(rs.getString("quantityid"));
						formVO.setQuantityTypeId(rs.getString("quantitytypeid"));
					}
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}	
				
				return formVO;	
			}
			
			public int updateBarInventoryProductStatus(ProductFormVO formVO) throws SQLException, Exception {

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				int temp = 0;
				
				String productUpdateQuery="UPDATE inventory_kitchen_inventory SET status=?, statuson=?, statusoff=? WHERE productid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(productUpdateQuery);
									
					pStmt.setString(1,formVO.getStatus());
					pStmt.setString(2,formVO.getStatusOn());
					pStmt.setString(3,formVO.getStatusOff());
					pStmt.setInt(4,Integer.parseInt(formVO.getProductId()));
					
					temp = pStmt.executeUpdate();
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return temp;	
			}
			
			public int updateBarInventoryProduct(ProductFormVO formVO) throws SQLException,Exception{
				
				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs  = null;
				PreparedStatement pStmt1 = null;
				PreparedStatement pStmt4 = null;
				ResultSet rs4  = null;
				PreparedStatement pStmt5 = null;
				ResultSet rs5  = null;
				PreparedStatement pStmt6 = null;
				PreparedStatement pStmt7 = null;
				PreparedStatement pStmt8 = null;
				ResultSet rs8  = null;
				PreparedStatement pStmt9 = null;
				
				List <String> ingredientIds = new ArrayList<String>();
				
				int temp = 0;
				int tempCompositProductIngredientId = 0;
				
				String compositProductIngredientCountQuery=" SELECT COUNT(*) FROM inventory_kitchen_inventory_product "; 
				String compositProductIngredientCountQuery1=" SELECT COUNT(*) FROM inventory_kitchen_inventory_product where inventoryid=? "; 
				
				String compositProductIngredientIdQuery=" SELECT ingredientid FROM inventory_kitchen_inventory_product where ingredientid=? ";
				String inventoryIngredientProductInsQuery="INSERT INTO inventory_kitchen_inventory_product ( ingredientid, productid, productquantity, quantitytypeid, inventoryid ) VALUES ( ?,?,?,?,? ) ";		
						
				String inventoryIngredientProductSelectQuery = "SELECT * FROM inventory_kitchen_inventory_product where inventoryid=? ";
				String inventoryIngredientProductDeleteQuery = "DELETE FROM inventory_kitchen_inventory_product where inventoryid=? ";
				
				String compositProductDetailsInsQuery = "UPDATE inventory_kitchen_inventory SET name=?, producttypeid=?, description=?, quantityid=?, quantitytypeid=?, lastmodifieddate=? WHERE productid=? ";
								
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(compositProductIngredientCountQuery1);
				pStmt.setInt(1,Integer.parseInt(formVO.getProductId()));
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					temp = rs.getInt(1);			
					for(int i=1; i<=temp; i++){
						pStmt1 = con.prepareStatement(inventoryIngredientProductDeleteQuery);
						pStmt1.setInt(1,Integer.parseInt(formVO.getProductId()));
						pStmt1.executeUpdate();	
					}
				}
				
				try{
								
					if(formVO.getInventoryProductIngredientList() != null){
						for(int j=1;j<=formVO.getInventoryProductIngredientList().size();j++){					
							pStmt4 = con.prepareStatement(compositProductIngredientCountQuery);
							rs4 = pStmt4.executeQuery();
							
							while(rs4.next()){
								temp = rs4.getInt(1);				
									for(int i=1; i<=temp+1; i++){
										pStmt5 = con.prepareStatement(compositProductIngredientIdQuery);
										pStmt5.setInt(1,i);
										rs5 = pStmt5.executeQuery();		
										
										if(!rs5.next()){
											if(tempCompositProductIngredientId != 0){
												tempCompositProductIngredientId++;
											}else{
												tempCompositProductIngredientId=i;
											}
											ingredientIds.add(String.valueOf(tempCompositProductIngredientId)); 				
										}
									}
								}						
							}
						}

					if(formVO.getInventoryProductIngredientList() != null){
						for(int i=0;i<formVO.getInventoryProductIngredientList().size();i++){
							if(formVO.getInventoryProductIngredientList().get(i) != null){
								pStmt6 = con.prepareStatement(inventoryIngredientProductInsQuery);
								pStmt6.setInt(1, Integer.parseInt(ingredientIds.get(i)));
								pStmt6.setInt(2, Integer.parseInt(formVO.getInventoryProductIngredientList().get(i).toString()));
								pStmt6.setInt(3, Integer.parseInt(formVO.getInventoryProductQuantityList().get(i).toString()));
								pStmt6.setInt(4, Integer.parseInt(formVO.getInventoryProductQuantityTypeList().get(i).toString()));
								pStmt6.setInt(5, Integer.parseInt(formVO.getProductId()));							
								pStmt6.executeUpdate();
							}
						}
					}
					
					pStmt7 = con.prepareStatement(compositProductDetailsInsQuery);
					
					pStmt7.setString(1,formVO.getName());
					pStmt7.setString(2,formVO.getProductTypeId());
					pStmt7.setString(3,formVO.getDescription());
					pStmt7.setString(4,formVO.getQuantityId());
					pStmt7.setString(5,formVO.getQuantityTypeId());	
					pStmt7.setDate(6,new java.sql.Date(new Date().getTime()));
					pStmt7.setInt(7,Integer.parseInt(formVO.getProductId()));
					temp = pStmt7.executeUpdate();			
				}
				
				catch(Exception ex){
					
					pStmt8 = con.prepareStatement(inventoryIngredientProductSelectQuery);
					pStmt8.setInt(1,Integer.parseInt(formVO.getProductId()));
					rs8 = pStmt8.executeQuery();
					
					while(rs8.next()){
						temp = rs8.getInt(1);			
						for(int i=1; i<=temp; i++){
							pStmt9 = con.prepareStatement(inventoryIngredientProductDeleteQuery);
							pStmt9.setInt(1,Integer.parseInt(formVO.getProductId()));
							pStmt9.executeUpdate();	
						}
					}
					
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return temp;	
			}
			
			public ProductFormVO viewBarInventoryProduct(ProductFormVO formVO) throws SQLException,Exception{

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
				PreparedStatement pStmt2 = null;
				ResultSet rs2 = null;
				
				String Query="SELECT productid, name, producttypeid, description, quantityid, quantitytypeid from inventory_kitchen_inventory WHERE productid=? ";
				String QuantityTypeQuery = "SELECT product_type_description FROM inventory_kitchen_product_type WHERE producttypeid=? ";
				String ServingQuantityTypeQuery = "SELECT description FROM inventory_quantity_type WHERE quantityid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);
					pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
					rs = pStmt.executeQuery();
					
					while(rs.next()){							
						formVO.setProductId(rs.getString("productid"));
						formVO.setName(rs.getString("name"));
						
						pStmt1 = con.prepareStatement(QuantityTypeQuery);
						pStmt1.setInt(1,Integer.parseInt(rs.getString("producttypeid")));
						rs1 = pStmt1.executeQuery();	
						
						while(rs1.next()){
							formVO.setProductTypeId(rs1.getString("product_type_description"));
						}
						
						formVO.setDescription(rs.getString("description"));
						formVO.setQuantityId(rs.getString("quantityid"));
						pStmt2 = con.prepareStatement(ServingQuantityTypeQuery);
						pStmt2.setInt(1,Integer.parseInt(rs.getString("quantitytypeid")));
						rs2 = pStmt2.executeQuery();				
						while(rs2.next()){
							formVO.setQuantityTypeId(rs2.getString("description"));
						}								
					}
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}	
				
				return formVO;	
			}

			public List<ProductFormVO> getBarInventoryProductMap(ProductFormVO formVO) throws SQLException, Exception {

				List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
				
				String compositProductListQuery="SELECT productid, name, producttypeid, description, quantityid, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL";
				String compositProductQuantityTypeQuery = "SELECT product_type_description FROM inventory_kitchen_product_type WHERE producttypeid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(compositProductListQuery);
					rs = pStmt.executeQuery();
					
					while(rs.next()){
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId(rs.getString("productid"));
						formVOO.setName(rs.getString("name"));
						
						pStmt1 = con.prepareStatement(compositProductQuantityTypeQuery);
						pStmt1.setString(1,rs.getString("producttypeid"));
						rs1 = pStmt1.executeQuery();
						
						while(rs1.next()){
							formVOO.setProductTypeId(rs1.getString("product_type_description"));
						}		
						
						formVOO.setDescription(rs.getString("description"));
						formVOO.setQuantityId(rs.getString("quantityid"));
						formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
						formVOO.setStatusOn(rs.getString("statuson"));
						formVOO.setStatusOff(rs.getString("statusoff"));
						formVOO.setStatus(rs.getString("status"));
						productList.add(formVOO);
					}
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return productList;			
			 }
			
			public List<ProductFormVO> getBarInventoryProductIndexMap(ProductFormVO formVO) throws SQLException, Exception {

				List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
				
				String Query="SELECT productid, name, producttypeid, description, quantityid, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid BETWEEN 0 AND 5";
				String QuantityTypeQuery = "SELECT product_type_description FROM inventory_kitchen_product_type WHERE producttypeid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);
					rs = pStmt.executeQuery();
					
					while(rs.next()){
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId(rs.getString("productid"));
						formVOO.setName(rs.getString("name"));
						
						pStmt1 = con.prepareStatement(QuantityTypeQuery);
						pStmt1.setString(1,rs.getString("producttypeid"));
						rs1 = pStmt1.executeQuery();
						
						while(rs1.next()){
							formVOO.setProductTypeId(rs1.getString("product_type_description"));
						}
						
						formVOO.setDescription(rs.getString("description"));
						formVOO.setQuantityId(rs.getString("quantityid"));
						formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
						formVOO.setStatusOn(rs.getString("statuson"));
						formVOO.setStatusOff(rs.getString("statusoff"));
						formVOO.setStatus(rs.getString("status"));
						productList.add(formVOO);
					}
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return productList;			
			 }
			
			public List<ProductFormVO> getBarInventoryProductsByRecords(ProductFormVO formVO)throws SQLException,Exception{
				List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
						
				String Query="SELECT productid, name, producttypeid, description, quantityid, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid BETWEEN ? AND ?";
				String QuantityTypeQuery = "SELECT product_type_description FROM inventory_kitchen_product_type WHERE producttypeid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);	
					
					if(formVO.getLimit().equalsIgnoreCase("1")){
						pStmt.setInt(1, 1);
						pStmt.setInt(2, 5);
					}else if(formVO.getLimit().equalsIgnoreCase("2")){
						pStmt.setInt(1, 1);
						pStmt.setInt(2, 10);
					}else{
						pStmt.setInt(1, 1);
						pStmt.setInt(2, 15);
					}
									
					rs = pStmt.executeQuery();
					
					while(rs.next()){
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId(rs.getString("productid"));
						formVOO.setName(rs.getString("name"));
						
						pStmt1 = con.prepareStatement(QuantityTypeQuery);
						pStmt1.setString(1,rs.getString("producttypeid"));
						rs1 = pStmt1.executeQuery();	
						
						while(rs1.next()){
							formVOO.setProductTypeId(rs1.getString("product_type_description"));
						}
						
						formVOO.setDescription(rs.getString("description"));
						formVOO.setQuantityId(rs.getString("quantityid"));
						formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
						formVOO.setStatusOn(rs.getString("statuson"));
						formVOO.setStatusOff(rs.getString("statusoff"));
						formVOO.setStatus(rs.getString("status"));
						productList.add(formVOO);
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return productList;	
			}
			
			public List<ProductFormVO> getPagination(ProductFormVO formVO)throws SQLException,Exception{
				List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
				
				String Query="SELECT productid, name, producttypeid, description, quantityid, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid BETWEEN ? AND ?";		  
				String QuantityTypeQuery = "SELECT product_type_description FROM inventory_kitchen_product_type WHERE producttypeid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);	
					
					if(Integer.parseInt(formVO.getPageFrom()) < 0) {
						pStmt.setInt(1, 1);
						pStmt.setInt(2, 5);
					}else{					
						pStmt.setInt(1, Integer.parseInt(formVO.getPageFrom()));
						pStmt.setInt(2, Integer.parseInt(formVO.getPageTo()));
					}
					
					rs = pStmt.executeQuery();
					
					while(rs.next()){
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId(rs.getString("productid"));
						formVOO.setName(rs.getString("name"));
						
						pStmt1 = con.prepareStatement(QuantityTypeQuery);
						pStmt1.setInt(1,Integer.parseInt(rs.getString("producttypeid")));
						rs1 = pStmt1.executeQuery();
						
						while(rs1.next()){
							formVOO.setProductTypeId(rs1.getString("product_type_description"));
						}
						
						formVOO.setDescription(rs.getString("description"));
						formVOO.setQuantityId(rs.getString("quantityid"));
						formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
						formVOO.setStatusOn(rs.getString("statuson"));
						formVOO.setStatusOff(rs.getString("statusoff"));
						formVOO.setStatus(rs.getString("status"));
						
						productList.add(formVOO);
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return productList;	
			}
			
			public Map<String, String> getBarInventoryProductList(Map<String, String> map) throws SQLException, Exception{
				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				
				//Map<String, Object> map = new LinkedHashMap<String, Object>();  
				
				String productNameQuery="SELECT productid, name FROM inventory_kitchen_product";	
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(productNameQuery);
					rs = pStmt.executeQuery();
					
					while(rs.next())
					{
						map.put(rs.getString("productid"), rs.getString("name"));
					}			
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return map;	
			}
			
			public List<String> viewBarIngredientProductList(ProductFormVO formVO)throws SQLException,Exception{
				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
				
				List<String> BarIngredientProductList = new ArrayList<String>();  
				
				String Query1="SELECT ingredientid FROM inventory_Bar_inventory_product where inventoryid=? ";
				String Query2="SELECT name FROM inventory_kitchen_product where productid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query1);
					pStmt.setInt(1,Integer.parseInt(formVO.getProductId()));
					rs = pStmt.executeQuery();
					
					while(rs.next())
					{				
						pStmt1 = con.prepareStatement(Query2);
						pStmt1.setString(1,rs.getString("ingredientid"));
						rs1 = pStmt1.executeQuery();
						
						while(rs1.next())
						{
							BarIngredientProductList.add(rs1.getString("name"));
						}
					}			
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return BarIngredientProductList;	
			}
			
			public List<String> viewBarIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception{
				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				
				List<String> kitchenIngredientProductQuantityList = new ArrayList<String>();  
				
				String Query="SELECT productquantity FROM inventory_kitchen_inventory_product where inventoryid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);
					pStmt.setInt(1,Integer.parseInt(formVO.getProductId()));
					rs = pStmt.executeQuery();
					
					while(rs.next())
					{
						kitchenIngredientProductQuantityList.add(rs.getString("productquantity"));
					}			
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return kitchenIngredientProductQuantityList;

			}
			
			public List<String> viewBarIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception{
				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
				
				List<String> kitchenIngredientProductQuantityTypeList = new ArrayList<String>();  
				
				String Query1="SELECT quantitytypeid FROM inventory_kitchen_inventory_product where inventoryid=? ";
				String Query2="SELECT quantity FROM inventory_quantity_type where quantityid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query1);
					pStmt.setInt(1,Integer.parseInt(formVO.getProductId()));
					rs = pStmt.executeQuery();
					
					while(rs.next())
					{				
						pStmt1 = con.prepareStatement(Query2);
						pStmt1.setInt(1,Integer.parseInt(rs.getString("quantitytypeid")));
						rs1 = pStmt1.executeQuery();
						
						while(rs1.next())
						{
							kitchenIngredientProductQuantityTypeList.add(rs1.getString("description"));
						}
					}			
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return kitchenIngredientProductQuantityTypeList;
			}
			
			public List<String> getBarIngredientProductList(ProductFormVO formVO)throws SQLException,Exception{
				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				
				List<String> kitchenIngredientProductList = new ArrayList<String>();  
				
				String Query1="SELECT ingredientid FROM inventory_kitchen_inventory_product where inventoryid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query1);
					pStmt.setInt(1,Integer.parseInt(formVO.getProductId()));
					rs = pStmt.executeQuery();
					
					while(rs.next())
					{		
						kitchenIngredientProductList.add(rs.getString("ingredientid"));				
					}			
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return kitchenIngredientProductList;	
			}
			
			public List<String> getBarIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception{
				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				
				List<String> kitchenIngredientProductQuantityList = new ArrayList<String>();  
				
				String Query="SELECT productquantity FROM inventory_kitchen_inventory_product where inventoryid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);
					pStmt.setInt(1,Integer.parseInt(formVO.getProductId()));
					rs = pStmt.executeQuery();
					
					while(rs.next())
					{
						kitchenIngredientProductQuantityList.add(rs.getString("productquantity"));
					}			
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return kitchenIngredientProductQuantityList;

			}
			
			public List<String> getBarIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception{
				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				
				List<String> kitchenIngredientProductQuantityTypeList = new ArrayList<String>();  
				
				String Query1="SELECT quantitytypeid FROM inventory_kitchen_inventory_product where inventoryid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query1);
					pStmt.setInt(1,Integer.parseInt(formVO.getProductId()));
					rs = pStmt.executeQuery();
					
					while(rs.next())
					{		
						kitchenIngredientProductQuantityTypeList.add(rs.getString("quantitytypeid"));				
					}			
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return kitchenIngredientProductQuantityTypeList;
			}

			public List<ProductFormVO> getBarInventoryProductsByCreatedDates(ProductFormVO formVO)
					throws SQLException, Exception {
				List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
				
				String Query="SELECT productid, name, producttypeid, description, quantityid, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND createddate BETWEEN '"+formVO.getFromDate()+"' AND '"+formVO.getToDate()+"'";
				String QuantityTypeQuery = "SELECT product_type_description FROM inventory_kitchen_product_type WHERE producttypeid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);
					rs = pStmt.executeQuery();
					
					while(rs.next()){
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId(rs.getString("productid"));
						formVOO.setName(rs.getString("name"));
						
						pStmt1 = con.prepareStatement(QuantityTypeQuery);
						pStmt1.setString(1,rs.getString("producttypeid"));
						rs1 = pStmt1.executeQuery();
						
						while(rs1.next()){
							formVOO.setProductTypeId(rs1.getString("product_type_description"));
						}
						
						formVOO.setDescription(rs.getString("description"));
						formVOO.setQuantityId(rs.getString("quantityid"));
						formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
						formVOO.setStatusOn(rs.getString("statuson"));
						formVOO.setStatusOff(rs.getString("statusoff"));
						formVOO.setStatus(rs.getString("status"));
						productList.add(formVOO);
					}
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return productList;			
			}

			public List<ProductFormVO> getBarInventoryProductsByName(ProductFormVO formVO) throws SQLException, Exception {
				List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
				
				String Query="SELECT productid, name, producttypeid, description, quantityid, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name like '%"+formVO.getName()+"%'";
				String QuantityTypeQuery = "SELECT product_type_description FROM inventory_kitchen_product_type WHERE producttypeid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);
					rs = pStmt.executeQuery();
					
					while(rs.next()){
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId(rs.getString("productid"));
						formVOO.setName(rs.getString("name"));
						
						pStmt1 = con.prepareStatement(QuantityTypeQuery);
						pStmt1.setString(1,rs.getString("producttypeid"));
						rs1 = pStmt1.executeQuery();
						
						while(rs1.next()){
							formVOO.setProductTypeId(rs1.getString("product_type_description"));
						}
						
						formVOO.setDescription(rs.getString("description"));
						formVOO.setQuantityId(rs.getString("quantityid"));
						formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
						formVOO.setStatusOn(rs.getString("statuson"));
						formVOO.setStatusOff(rs.getString("statusoff"));
						formVOO.setStatus(rs.getString("status"));
						productList.add(formVOO);
					}
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return productList;			
			}

			public List<ProductFormVO> getBarInventoryProductsById(ProductFormVO formVO) throws SQLException, Exception {
				List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

				DBConnection dbConnection = new DBConnection();
				Connection con = null;
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				PreparedStatement pStmt1 = null;
				ResultSet rs1 = null;
				
				String Query="SELECT productid, name, producttypeid, description, quantityid, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid ="+formVO.getProductId();
				String QuantityTypeQuery = "SELECT product_type_description FROM inventory_kitchen_product_type WHERE producttypeid=? ";
				
				try{
					con = dbConnection.getConnection();
					pStmt = con.prepareStatement(Query);
					rs = pStmt.executeQuery();
					
					while(rs.next()){
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId(rs.getString("productid"));
						formVOO.setName(rs.getString("name"));
						
						pStmt1 = con.prepareStatement(QuantityTypeQuery);
						pStmt1.setString(1,rs.getString("producttypeid"));
						rs1 = pStmt1.executeQuery();
						
						while(rs1.next()){
							formVOO.setProductTypeId(rs1.getString("product_type_description"));
						}
						
						formVOO.setDescription(rs.getString("description"));
						formVOO.setQuantityId(rs.getString("quantityid"));
						formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
						formVOO.setStatusOn(rs.getString("statuson"));
						formVOO.setStatusOff(rs.getString("statusoff"));
						formVOO.setStatus(rs.getString("status"));
						productList.add(formVOO);
					}
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				finally{
					dbConnection.close(pStmt, con);
				}
				
				return productList;			
			}

		}


