package com.indianmesh.inventory.kitchen.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;

import com.indianmesh.inventory.kitchen.stock.ProductManager;
import com.indianmesh.inventory.utils.dbConnection.DBConnection;

public class ProductDAOImp implements ProductBaseDAO {
	
	ProductManager productManagerStock = new ProductManager();
	
	public int deleteKitchenInventoryProduct(ProductFormVO formVO) throws SQLException, Exception{

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		PreparedStatement pStmt5 = null;
		PreparedStatement pStmt6 = null;
		PreparedStatement pStmt7 = null;
		PreparedStatement pStmt8 = null;
		PreparedStatement pStmt9 = null;
		PreparedStatement pStmt10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt12 = null;
		
		int ret = 0;
		int temp = 0;
		
		String ingredientProductSelectQuery = "SELECT * FROM inventory_kitchen_inventory_product where inventoryid=? ";
		String deleteQueryProduct="DELETE FROM inventory_kitchen_inventory WHERE productid=? ";
		String deleteQueryInventoryProduct="DELETE FROM inventory_kitchen_inventory_product WHERE inventoryid=? ";
		String deleteQueryStore="DELETE FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ";
		String deleteQueryStock="DELETE FROM inventory_kitchen_product_stock WHERE productid=? ";	
		String deleteQuerySupply="DELETE FROM inventory_kitchen_product_supply WHERE supplyid IN ( SELECT supplyid FROM inventory_kitchen_product_stock WHERE supplyid =? ) ";
		String deleteQueryTransaction="DELETE FROM inventory_kitchen_product_transaction WHERE transactionid IN ( SELECT transactionid FROM inventory_kitchen_product_supply WHERE supplyid IN ( SELECT supplyid from inventory_kitchen_product_stock where productid=? ) ) ";
		String deleteQueryInvoice="DELETE FROM inventory_kitchen_product_invoice WHERE invoiceid IN ( SELECT invoiceid FROM inventory_kitchen_product_supply WHERE supplyid IN ( SELECT supplyid from inventory_kitchen_product_stock where productid=? ) ) ";			
		
		String deleteQueryCreditNote="DELETE FROM inventory_kitchen_stock_creditnote WHERE creditnoteid IN ( SELECT creditnoteid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";
		String deleteQueryRequirement="DELETE FROM inventory_kitchen_stock_requirement WHERE requirementid IN ( SELECT requirementid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";
		String deleteQueryConsumption="DELETE FROM inventory_kitchen_stock_consumtion WHERE consumptionid IN ( SELECT consumptionid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";
		String deleteQueryAddition="DELETE FROM inventory_kitchen_stock_addition WHERE additionid IN ( SELECT additionid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";
		String deleteQueryAvailable="DELETE FROM inventory_kitchen_stock_available WHERE availableid IN ( SELECT availableid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";

		try{
			con = dbConnection.getConnection();
			
			//delete creditnotes
			pStmt1 = con.prepareStatement(deleteQueryCreditNote);
			pStmt1.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt1.executeUpdate();
			
			//delete requirement
			pStmt2 = con.prepareStatement(deleteQueryRequirement);
			pStmt2.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt2.executeUpdate();
			
			//delete consumption
			pStmt3 = con.prepareStatement(deleteQueryConsumption);
			pStmt3.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt3.executeUpdate();
			
			//delete addition
			pStmt4 = con.prepareStatement(deleteQueryAddition);
			pStmt4.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt4.executeUpdate();
			
			//de;ete available
			pStmt5 = con.prepareStatement(deleteQueryAvailable);
			pStmt5.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt5.executeUpdate();
			
			//delete invoice
			pStmt6 = con.prepareStatement(deleteQueryInvoice);
			pStmt6.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt6.executeUpdate();
			
			//delete transaction
			pStmt7 = con.prepareStatement(deleteQueryTransaction);
			pStmt7.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt7.executeUpdate();
			
			//delete store
			pStmt8 = con.prepareStatement(deleteQueryStore);
			pStmt8.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt8.executeUpdate();
			
			//delete supply
			pStmt9 = con.prepareStatement(deleteQuerySupply);
			pStmt9.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt9.executeUpdate();
			
			//delete stock
			pStmt10 = con.prepareStatement(deleteQueryStock);
			pStmt10.setInt(1, Integer.parseInt(formVO.getProductId()));			
			ret = pStmt10.executeUpdate();
			
			//delete ingredient inventory procuct
			pStmt11 = con.prepareStatement(ingredientProductSelectQuery);
			pStmt11.setInt(1,Integer.parseInt(formVO.getProductId()));
			rs11 = pStmt11.executeQuery();
				
			while(rs11.next()){
				temp = rs11.getInt(1);			
				for(int i=1; i<=temp; i++){
					pStmt12 = con.prepareStatement(deleteQueryInventoryProduct);
					pStmt12.setInt(1,Integer.parseInt(formVO.getProductId()));
					pStmt12.executeUpdate();	
				}
			}
			
			//delete inventory procuct
			pStmt = con.prepareStatement(deleteQueryProduct);
			pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			ret = pStmt.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
			dbConnection.close(pStmt4, con);
			dbConnection.close(pStmt5, con);
			dbConnection.close(pStmt6, con);
			dbConnection.close(pStmt7, con);
			dbConnection.close(pStmt8, con);
			dbConnection.close(pStmt9, con);
			dbConnection.close(pStmt10, con);
			dbConnection.close(pStmt11, con);
			dbConnection.close(pStmt12, con);
		}
		
		return ret;			
	}
	
	public ProductFormVO addKitchenInventoryProduct(ProductFormVO formVO) throws SQLException,Exception{

		DBConnection dbConnection = new DBConnection();
		
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		ResultSet rs3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		
		int temp1 = 0;
		int temp2 = 0;
		
		String id = null;
		String proId = null;
		String invProId = null;
		
		List<String> proIds = new ArrayList<String>();
		List<String> invProIds = new ArrayList<String>();

		String Query1 = "SELECT COUNT(*) FROM inventory_kitchen_inventory "; 
		String Query2 = "SELECT productid FROM inventory_kitchen_inventory ";
		String Query3 = "SELECT productid FROM inventory_kitchen_inventory where productid=? ";		
		
		String Query11 = "SELECT COUNT(*) FROM inventory_kitchen_product "; 
		String Query22 = "SELECT productid FROM inventory_kitchen_product";
		String Query33 = "SELECT productid FROM inventory_kitchen_product where productid=? ";
		
		String Query="INSERT INTO inventory_kitchen_inventory ( productid, sellerid, subsellerid, created, lastmodified, statuson, statusoff, status, ID ) VALUES ( ?,?,?,?,?,?,?,?,? ) ";		
				
		try{
			con = dbConnection.getConnection();
			pStmt1 = con.prepareStatement(Query2);
			rs1 = pStmt1.executeQuery();
			
			while(rs1.next()){
				proIds.add(rs1.getString("productid")) ;
			}
			
			pStmt2 = con.prepareStatement(Query1);
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				temp1 = rs2.getInt(1);
			}
								
			for(int i=0;i<temp1;i++){
				pStmt3 = con.prepareStatement(Query3);
				pStmt3.setInt(1,Integer.parseInt(proIds.get(i)));
				rs3 = pStmt3.executeQuery();										
				while(rs3.next()){						
					proId = rs3.getString("productid");	
				}
			}
					
			pStmt4 = con.prepareStatement(Query22);
			rs4 = pStmt4.executeQuery();
			
			while(rs4.next()){
				invProIds.add(rs4.getString("productid")) ;
			}
			
			pStmt5 = con.prepareStatement(Query11);
			rs5 = pStmt5.executeQuery();
			
			while(rs5.next()){
				temp2 = rs5.getInt(1);
			}
								
			for(int i=0;i<temp2;i++){
				pStmt6 = con.prepareStatement(Query33);
				pStmt6.setInt(1,Integer.parseInt(invProIds.get(i)));
				rs6 = pStmt6.executeQuery();										
				while(rs6.next()){						
					invProId = rs6.getString("productid");	
				}
			}
			
			if(proId!=null){
				if(Integer.parseInt(proId)>Integer.parseInt(invProId)){
					id=String.valueOf(Integer.parseInt(proId)+1);	
				}else{
					id=String.valueOf(Integer.parseInt(invProId)+1);
				}
			}else{
				if(invProId!=null){
					id=String.valueOf(Integer.parseInt(invProId)+1);
				}else{
					id=String.valueOf("1");
				}
			}
			
			String ID="IKP"+id;
			
			formVO.setProductId(id);
			pStmt=con.prepareStatement(Query);
			pStmt.setString(1,id);
			pStmt.setString(2,formVO.getSellerId());
			pStmt.setString(3,formVO.getSubsellerId());
			pStmt.setDate(4,new java.sql.Date(new Date().getTime()));
			pStmt.setDate(5,new java.sql.Date(new Date().getTime()));
			pStmt.setString(6,"btn btn-default btn-on btn-sm active");
			pStmt.setString(7,"btn btn-default btn-off btn-sm");
			pStmt.setString(8,"true");
			pStmt.setString(9,ID);
			pStmt.executeUpdate();			
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
			dbConnection.close(pStmt4, con);
			dbConnection.close(pStmt5, con);
			dbConnection.close(pStmt6, con);
		}	
		
		return formVO;	
	}
	
	public ProductFormVO editKitchenInventoryProduct(ProductFormVO formVO) throws SQLException,Exception{

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		String Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid from inventory_kitchen_inventory WHERE productid=? ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
			rs = pStmt.executeQuery();
			
			while(rs.next()){							
				formVO.setProductId(rs.getString("productid"));
				formVO.setID(rs.getString("ID"));
				formVO.setName(rs.getString("name"));
				formVO.setProductTypeId(rs.getString("producttypeid"));
				formVO.setDescription(rs.getString("description"));
				formVO.setQuantityId(rs.getString("quantity"));
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
	
	public int updateKitchenInventoryProductStatus(ProductFormVO formVO) throws SQLException, Exception {

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
	
	public int updateKitchenInventoryProduct(ProductFormVO formVO) throws SQLException,Exception{
		
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
		
		PreparedStatement pStmta = null;
		PreparedStatement pStmt1a = null;
		ResultSet rs1a = null;
		PreparedStatement pStmt2a = null;
		ResultSet rs2a = null;
		PreparedStatement pStmt3a = null;
		ResultSet rs3a = null;
		PreparedStatement pStmt4a = null;
		ResultSet rs4a = null;
		PreparedStatement pStmt5a = null;
		ResultSet rs5a = null;
		PreparedStatement pStmt6a = null;
		ResultSet rs6a = null;
		PreparedStatement pStmt7a = null;
		ResultSet rs7a = null;
		PreparedStatement pStmt8a = null;
		ResultSet rs8a = null;
		PreparedStatement pStmt9a = null;
		ResultSet rs9a = null;
		PreparedStatement pStmt10a = null;
		ResultSet rs10a = null;
		PreparedStatement pStmt11a = null;
		PreparedStatement pStmt12a = null;
		PreparedStatement pStmt13a = null;
		PreparedStatement pStmt14a = null;
		PreparedStatement pStmt15a = null;
		PreparedStatement pStmt16a = null;
		ResultSet rs16a = null;
		PreparedStatement pStmt17a = null;
		ResultSet rs17a = null;
		PreparedStatement pStmt18a = null;
		ResultSet rs18a = null;
		PreparedStatement pStmt19a = null;
		ResultSet rs19a = null;
		PreparedStatement pStmt20a = null;
		PreparedStatement pStmt21a = null;
		PreparedStatement pStmt22a = null;
		ResultSet rs22a = null;
		PreparedStatement pStmt23a = null;
		ResultSet rs23a = null;
		PreparedStatement pStmt24a = null;
		ResultSet rs24a = null;
		PreparedStatement pStmt25a = null;
		ResultSet rs25a = null;
		PreparedStatement pStmt26a = null;
		PreparedStatement pStmt27a = null;
		PreparedStatement pStmt28a = null;
		ResultSet rs28a = null;
		PreparedStatement pStmt29a = null;
		ResultSet rs29a = null;
		PreparedStatement pStmt30a = null;
		
		PreparedStatement pStmt2aa = null;
		ResultSet rs2aa = null;
		PreparedStatement pStmt4aa = null;
		ResultSet rs4aa = null;
		PreparedStatement pStmt11aa = null;
		ResultSet rs11aa = null;
		
		PreparedStatement pStmt123 = null;
		ResultSet rs123 = null;
		
		String available = null;
    	String totalCount = null;
		
		String availableid = null;
		String consumptionid = null;
		String requirementid = null;
		String additionid = null;
		String creditnoteid = null;
		String transactionid = null;
		String invoiceid = null;
		String supplyid = null;
		String stockid = null;
		String storeid = null;
				
		List <String> ingredientIds = new ArrayList<String>();
		
		int temp = 0;
		int tempCompositProductIngredientId = 0;
				
		String Query = "UPDATE inventory_kitchen_inventory SET name=?, producttypeid=?, description=?, quantity=?, quantitytypeid=?, lastmodified=?, ID=? WHERE productid=? ";											
		
		//store components
		String Query1="INSERT INTO inventory_kitchen_stock_available ( availableid, available, description, created, lastmodified, sellerid, subsellerid, lastcount, lastcounttype ) VALUES (?,?,?,?,?,?,?,?,?) ";
		String Query2="INSERT INTO inventory_kitchen_stock_consumtion ( consumptionid, consumption, description, created, lastmodified, sellerid, subsellerid ) VALUES (?,?,?,?,?,?,?) ";
		String Query3="INSERT INTO inventory_kitchen_stock_requirement ( requirementid, requirement, description, created, lastmodified, sellerid, subsellerid ) VALUES (?,?,?,?,?,?,?) ";
		String Query4="INSERT INTO inventory_kitchen_stock_addition ( additionid, addition, description, created, lastmodified, sellerid, subsellerid ) VALUES (?,?,?,?,?,?,?) ";
		String Query5="INSERT INTO inventory_kitchen_stock_creditnote ( creditnoteid, creditnote, created, lastmodified, sellerid, subsellerid ) VALUES (?,?,?,?,?,?) ";
		 		
		//invoice and transaction components
		String Query6="INSERT INTO inventory_kitchen_product_transaction ( transactionid, sellerid, subsellerid, transation, transationamount, created, lastmodified ) VALUES (?,?,?,?,?,?,?) ";
		String Query7="INSERT INTO inventory_kitchen_product_invoice ( invoiceid, sellerid, subsellerid, invoiceno, invoicefilepath, created, lastmodified ) VALUES (?,?,?,?,?,?,?) ";
		 
		//invoice and transaction components
		String Query8="INSERT INTO inventory_kitchen_product_supply ( supplyid, sellerid, subsellerid, invoiceid, transactionid, created, lastmodified ) VALUES (?,?,?,?,?,?,?) ";
		String Query9="INSERT INTO inventory_kitchen_product_stock ( stockid, sellerid, subsellerid, supplyid, productid, created, lastmodified, ID ) VALUES (?,?,?,?,?,?,?,?) ";
		String Query10="INSERT INTO inventory_kitchen_store ( storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		 
		String countQuery1 = "SELECT COUNT(*) FROM inventory_kitchen_stock_available "; 
		String idQuery1 = "SELECT availableid FROM inventory_kitchen_stock_available where availableid=? ";
		
		String countQuery2 = "SELECT COUNT(*) FROM inventory_kitchen_stock_consumtion "; 
		String idQuery2 = "SELECT consumptionid FROM inventory_kitchen_stock_consumtion where consumptionid=? ";
		
		String countQuery3 = "SELECT COUNT(*) FROM inventory_kitchen_stock_requirement "; 
		String idQuery3 = "SELECT requirementid FROM inventory_kitchen_stock_requirement where requirementid=? ";
		
		String countQuery4 = "SELECT COUNT(*) FROM inventory_kitchen_stock_addition "; 
		String idQuery4 = "SELECT additionid FROM inventory_kitchen_stock_addition where additionid=? ";
		
		String countQuery5 = "SELECT COUNT(*) FROM inventory_kitchen_stock_creditnote "; 
		String idQuery5 = "SELECT creditnoteid FROM inventory_kitchen_stock_creditnote where creditnoteid=? ";
		
		String countQuery6 = "SELECT COUNT(*) FROM inventory_kitchen_product_transaction "; 
		String idQuery6 = "SELECT transactionid FROM inventory_kitchen_product_transaction where transactionid=? ";
		
		String countQuery7 = "SELECT COUNT(*) FROM inventory_kitchen_product_invoice "; 
		String idQuery7 = "SELECT invoiceid FROM inventory_kitchen_product_invoice where invoiceid=? ";
		
		String countQuery8 = "SELECT COUNT(*) FROM inventory_kitchen_product_supply "; 
		String idQuery8 = "SELECT supplyid FROM inventory_kitchen_product_supply where supplyid=? ";
		
		String countQuery9 = "SELECT COUNT(*) FROM inventory_kitchen_product_stock "; 
		String idQuery9 = "SELECT stockid FROM inventory_kitchen_product_stock where stockid=? ";
		
		String countQuery10 = "SELECT COUNT(*) FROM inventory_kitchen_store "; 
		String idQuery10 = "SELECT storeid FROM inventory_kitchen_store where storeid=? ";
		
		String ingredientCountQuery0=" SELECT COUNT(*) FROM inventory_kitchen_inventory_product "; 
		String ingredientCountQuery1=" SELECT COUNT(*) FROM inventory_kitchen_inventory_product where inventoryid=? "; 
		
		String compositProductIngredientIdQuery=" SELECT ingredientid FROM inventory_kitchen_inventory_product where ingredientid=? ";
		String inventoryIngredientProductInsQuery="INSERT INTO inventory_kitchen_inventory_product ( ingredientid, productid, productquantity, quantitytypeid, inventoryid, created, lastmodified, sellerid, subsellerid ) VALUES ( ?,?,?,?,?,?,?,?,? ) ";		
				
		String ingredientProductSelectQuery = "SELECT * FROM inventory_kitchen_inventory_product where inventoryid=? ";
		String ingredientProductDeleteQuery = "DELETE FROM inventory_kitchen_inventory_product where inventoryid=? ";
		
		try{			
						con = dbConnection.getConnection();
								
						pStmt1a = con.prepareStatement(countQuery1);
						rs1a = pStmt1a.executeQuery();
						
						while(rs1a.next()){
							temp = rs1a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt2a = con.prepareStatement(idQuery1);
								pStmt2a.setInt(1,i);
								rs2a = pStmt2a.executeQuery();					
								
								if(!rs2a.next())
									availableid = String.valueOf(i);
							}
						}
						
						pStmt3a = con.prepareStatement(countQuery2);
						rs3a = pStmt3a.executeQuery();
						
						while(rs3a.next()){
							temp = rs3a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt4a = con.prepareStatement(idQuery2);
								pStmt4a.setInt(1,i);
								rs4a = pStmt4a.executeQuery();					
								
								if(!rs4a.next())
									consumptionid = String.valueOf(i);
							}
						}
						
						pStmt5a = con.prepareStatement(countQuery3);
						rs5a = pStmt5a.executeQuery();
						
						while(rs5a.next()){
							temp = rs5a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt6a = con.prepareStatement(idQuery3);
								pStmt6a.setInt(1,i);
								rs6a = pStmt6a.executeQuery();					
								
								if(!rs6a.next())
									requirementid = String.valueOf(i);
							}
						}
						
						pStmt7a = con.prepareStatement(countQuery4);
						rs7a = pStmt7a.executeQuery();
						
						while(rs7a.next()){
							temp = rs7a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt8a = con.prepareStatement(idQuery4);
								pStmt8a.setInt(1,i);
								rs8a = pStmt8a.executeQuery();					
								
								if(!rs8a.next())
									additionid = String.valueOf(i);
							}
						}
					
						pStmt9a = con.prepareStatement(countQuery5);
						rs9a = pStmt9a.executeQuery();
						
						while(rs9a.next()){
							temp = rs9a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt10a = con.prepareStatement(idQuery5);
								pStmt10a.setInt(1,i);
								rs10a = pStmt10a.executeQuery();					
								
								if(!rs10a.next())
									creditnoteid = String.valueOf(i);
							}
						}
						
						String qunType = "SELECT quantity from inventory_quantity_type where quantityid = ?";
						
						pStmt123 = con.prepareStatement(qunType);
						pStmt123.setString(1,formVO.getQuantityTypeId());
						rs123 = pStmt123.executeQuery();
						
						String strType = null;
						String avlType = null;
						String lastCountType = null;
						
						while(rs123.next()){
							strType = rs123.getString("quantity");
						}
												    	
						if(strType.contains("KG")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()) * 1000 );
							avlType = "GM";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("GM")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "GM";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("JAR")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()) * 1140 );
							avlType = "Ml";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("Ml")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "Ml";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("LTR")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()) * 1000 );
							avlType = "Ml";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("GALLON")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()) * 3785.41 );
							avlType = "Ml";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("BTL")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()) * 1000 );
							avlType = "Ml";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("NOS")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "NOS";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("BOX")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "BOX";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("POUCH")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "POUCH";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("CASE")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "CASE";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("BUNCH")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "BUNCH";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("PKT")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "PKT";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("PCS")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "PCS";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}else if(strType.contains("TIN")){
							available = String.valueOf(Integer.parseInt(formVO.getQuantityId()));
							avlType = "TIN";
							totalCount = formVO.getQuantityId() ;
							lastCountType = strType;
						}			
					
						pStmt11a=con.prepareStatement(Query1);
						pStmt11a.setString(1,availableid);			
						pStmt11a.setString(2,available);
						pStmt11a.setString(3,avlType);
						pStmt11a.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt11a.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt11a.setString(6,formVO.getSellerId());
						pStmt11a.setString(7,formVO.getSubsellerId());				
						pStmt11a.setString(8,String.valueOf(totalCount));
						pStmt11a.setString(9,String.valueOf(lastCountType));
						pStmt11a.executeUpdate();
						
						pStmt12a=con.prepareStatement(Query2);
						pStmt12a.setString(1,consumptionid);			
						pStmt12a.setInt(2,0);
						pStmt12a.setInt(3,0);
						pStmt12a.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt12a.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt12a.setString(6,formVO.getSellerId());
						pStmt12a.setString(7,formVO.getSubsellerId());
						pStmt12a.executeUpdate();
						
						pStmt13a=con.prepareStatement(Query3);
						pStmt13a.setString(1,requirementid);			
						pStmt13a.setInt(2,0);
						pStmt13a.setInt(3,0);
						pStmt13a.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt13a.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt13a.setString(6,formVO.getSellerId());
						pStmt13a.setString(7,formVO.getSubsellerId());
						pStmt13a.executeUpdate();
						
						pStmt14a=con.prepareStatement(Query4);
						pStmt14a.setString(1,additionid);			
						pStmt14a.setString(2,available);
						pStmt14a.setString(3,avlType);
						pStmt14a.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt14a.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt14a.setString(6,formVO.getSellerId());
						pStmt14a.setString(7,formVO.getSubsellerId());
						pStmt14a.executeUpdate();
						
						pStmt15a=con.prepareStatement(Query5);
						pStmt15a.setString(1,creditnoteid);			
						pStmt15a.setString(2,formVO.getDescription());
						pStmt15a.setString(3,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt15a.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt15a.setString(5,formVO.getSellerId());
						pStmt15a.setString(6,formVO.getSubsellerId());
						pStmt15a.executeUpdate();
						
						//inserting data for invoice and transactions tables
						pStmt16a = con.prepareStatement(countQuery6);
						rs16a = pStmt16a.executeQuery();
						
						while(rs16a.next()){
							temp = rs16a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt17a = con.prepareStatement(idQuery6);
								pStmt17a.setInt(1,i);
								rs17a = pStmt17a.executeQuery();					
								
								if(!rs17a.next())
									transactionid = String.valueOf(i);
							}
						}
						
						pStmt18a = con.prepareStatement(countQuery7);
						rs18a = pStmt18a.executeQuery();
						
						while(rs18a.next()){
							temp = rs18a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt19a = con.prepareStatement(idQuery7);
								pStmt19a.setInt(1,i);
								rs19a = pStmt19a.executeQuery();					
								
								if(!rs19a.next())
									invoiceid = String.valueOf(i);
							}
						}
						
						pStmt20a=con.prepareStatement(Query6);
						pStmt20a.setString(1,transactionid);			
						pStmt20a.setString(2,formVO.getSellerId());
						pStmt20a.setString(3,formVO.getSubsellerId());
						pStmt20a.setString(4,"0");
						pStmt20a.setString(5,"0");
						pStmt20a.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt20a.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt20a.executeUpdate();
						
						pStmt21a=con.prepareStatement(Query7);
						pStmt21a.setString(1,invoiceid);			
						pStmt21a.setString(2,formVO.getSellerId());
						pStmt21a.setString(3,formVO.getSubsellerId());
						pStmt21a.setString(4,formVO.getInvoiceId());
						pStmt21a.setString(5,formVO.getFilePathOfInvoice());
						pStmt21a.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt21a.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt21a.executeUpdate();
						
						//inserting data into supply and than stock table
						pStmt22a = con.prepareStatement(countQuery8);
						rs22a = pStmt22a.executeQuery();
						
						while(rs22a.next()){
							temp = rs22a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt23a = con.prepareStatement(idQuery8);
								pStmt23a.setInt(1,i);
								rs23a = pStmt23a.executeQuery();					
								
								if(!rs23a.next())
									supplyid = String.valueOf(i);
							}
						}
						
						pStmt24a = con.prepareStatement(countQuery9);
						rs24a = pStmt24a.executeQuery();
						
						while(rs24a.next()){
							temp = rs24a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt25a = con.prepareStatement(idQuery9);
								pStmt25a.setInt(1,i);
								rs25a = pStmt25a.executeQuery();					
								
								if(!rs25a.next())
									stockid = String.valueOf(i);
							}
						}
						
						pStmt26a=con.prepareStatement(Query8);
						pStmt26a.setString(1,supplyid);			
						pStmt26a.setString(2,formVO.getSellerId());
						pStmt26a.setString(3,formVO.getSubsellerId());
						pStmt26a.setString(4,invoiceid);
						pStmt26a.setString(5,transactionid);
						pStmt26a.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt26a.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt26a.executeUpdate();
						
						String ID="IKP"+formVO.getProductId();
						
						pStmt27a=con.prepareStatement(Query9);
						pStmt27a.setString(1,stockid);			
						pStmt27a.setString(2,formVO.getSellerId());
						pStmt27a.setString(3,formVO.getSubsellerId());
						pStmt27a.setString(4,supplyid);
						pStmt27a.setString(5,formVO.getProductId());
						pStmt27a.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt27a.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt27a.setString(8,ID);
						pStmt27a.executeUpdate();
						
						// inserting data into store data value table
						pStmt28a = con.prepareStatement(countQuery10);
						rs28a = pStmt28a.executeQuery();
						
						while(rs28a.next()){
							temp = rs28a.getInt(1);
							
							for(int i=1; i<=temp+1; i++){
								pStmt29a = con.prepareStatement(idQuery10);
								pStmt29a.setInt(1,i);
								rs29a = pStmt29a.executeQuery();					
								
								if(!rs29a.next())
									storeid = String.valueOf(i);
							}
						}
						
						pStmt30a=con.prepareStatement(Query10);
						pStmt30a.setString(1,storeid);			
						pStmt30a.setString(2,formVO.getSellerId());
						pStmt30a.setString(3,formVO.getSubsellerId());
						pStmt30a.setString(4,stockid);
						pStmt30a.setString(5,availableid);
						pStmt30a.setString(6,consumptionid);
						pStmt30a.setString(7,requirementid);
						pStmt30a.setString(8,additionid);
						pStmt30a.setString(9,creditnoteid);
						pStmt30a.setString(10,"pending");
						pStmt30a.setString(11,"btn btn-default btn-on btn-sm");
						pStmt30a.setString(12,"btn btn-default btn-off btn-sm active");
						pStmt30a.setString(13,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt30a.setString(14,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt30a.setString(15,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						pStmt30a.executeUpdate();
						
						pStmt = con.prepareStatement(ingredientCountQuery1);
						pStmt.setInt(1,Integer.parseInt(formVO.getProductId()));
						rs = pStmt.executeQuery();
						
						while(rs.next()){
							temp = rs.getInt(1);			
							for(int i=1; i<=temp; i++){
								pStmt1 = con.prepareStatement(ingredientProductDeleteQuery);
								pStmt1.setInt(1,Integer.parseInt(formVO.getProductId()));
								pStmt1.executeUpdate();	
							}
						}
													
						if(formVO.getInventoryProductIngredientList() != null){
							for(int j=1;j<=formVO.getInventoryProductIngredientList().size();j++){					
								pStmt4 = con.prepareStatement(ingredientCountQuery0);
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
										pStmt6.setDate(6,new java.sql.Date(new Date().getTime()));
										pStmt6.setDate(7,new java.sql.Date(new Date().getTime()));
										pStmt6.setString(8,formVO.getSellerId());
										pStmt6.setString(9,formVO.getSubsellerId());
										pStmt6.executeUpdate();
																														
										com.indianmesh.inventory.kitchen.stock.ProductFormVO formVC = new com.indianmesh.inventory.kitchen.stock.ProductFormVO();
										formVC.setSellerId(RandomStringUtils.randomAlphanumeric(25));
										formVC.setSubSellerId(RandomStringUtils.randomAlphanumeric(25));
										formVC.setProductId(formVO.getInventoryProductIngredientList().get(i).toString());
										
										String QueryStoreSel="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ) ORDER BY stockid ASC, lastmodified DESC LIMIT 1  ";
										String QueryStokAvail="SELECT available, description, lastcount, lastcounttype from inventory_kitchen_stock_available where availableid = ? ORDER BY availableid ASC, lastmodified DESC LIMIT 1  ";
																				
										pStmt2aa = con.prepareStatement(QueryStoreSel);
										pStmt2aa.setString(1, formVO.getInventoryProductIngredientList().get(i).toString());
										rs2aa = pStmt2aa.executeQuery();
										
										while(rs2aa.next()){
											pStmt4aa = con.prepareStatement(QueryStokAvail);
											pStmt4aa.setString(1, rs2aa.getString("availableid"));
											rs4aa = pStmt4aa.executeQuery();			
											while(rs4aa.next()){	
												formVC.setAvailableId(rs4aa.getString("available") +" "+ rs4aa.getString("description"));
												formVC.setLastCount(rs4aa.getString("lastcount") +" "+ rs4aa.getString("lastcounttype")); 																			
											}
										}
										
										formVC.setConsumptionId(formVO.getInventoryProductQuantityList().get(i).toString());
										formVC.setQuantityPerUnit(formVO.getInventoryProductQuantityTypeList().get(i).toString());
										formVC.setStatus("pending");
										productManagerStock.updateKitchenProductConsumptionAfterSingleEntry(formVC);
									}
								}
							}
						
							//String ID="IKP"+formVO.getProductId();
							
							pStmt7 = con.prepareStatement(Query);							
							pStmt7.setString(1,formVO.getName());
							pStmt7.setString(2,formVO.getProductTypeId());
							pStmt7.setString(3,formVO.getDescription());
							pStmt7.setString(4,formVO.getQuantityId());
							pStmt7.setString(5,formVO.getQuantityTypeId());	
							pStmt7.setDate(6,new java.sql.Date(new Date().getTime()));
							pStmt7.setString(7,ID);
							pStmt7.setInt(8,Integer.parseInt(formVO.getProductId()));
							temp = pStmt7.executeUpdate();				
						}
						
						catch(Exception ex){
							
							PreparedStatement pStmtaa = null;
							PreparedStatement pStmt1aa = null;
							PreparedStatement pStmt2aaa = null;
							PreparedStatement pStmt3aa = null;
							PreparedStatement pStmt4aaa = null;
							PreparedStatement pStmt5aa = null;
							PreparedStatement pStmt6aa = null;
							PreparedStatement pStmt7aa = null;
							PreparedStatement pStmt8aa = null;
							PreparedStatement pStmt9aa = null;
							PreparedStatement pStmt10aa = null;
							PreparedStatement pStmt11aaa = null;
							ResultSet rs11aaa = null;
							PreparedStatement pStmt12aa = null;
							
							int ret = 0;
							
							String ingredientProductSelectQueryaa = "SELECT * FROM inventory_kitchen_inventory_product where inventoryid=? ";
							String deleteQueryProduct="DELETE FROM inventory_kitchen_inventory WHERE productid=? ";
							String deleteQueryInventoryProduct="DELETE FROM inventory_kitchen_inventory_product WHERE inventoryid=? ";
							String deleteQueryStore="DELETE FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ";
							String deleteQueryStock="DELETE FROM inventory_kitchen_product_stock WHERE productid=? ";	
							String deleteQuerySupply="DELETE FROM inventory_kitchen_product_supply WHERE supplyid IN ( SELECT supplyid FROM inventory_kitchen_product_stock WHERE supplyid =? ) ";
							String deleteQueryTransaction="DELETE FROM inventory_kitchen_product_transaction WHERE transactionid IN ( SELECT transactionid FROM inventory_kitchen_product_supply WHERE supplyid IN ( SELECT supplyid from inventory_kitchen_product_stock where productid=? ) ) ";
							String deleteQueryInvoice="DELETE FROM inventory_kitchen_product_invoice WHERE invoiceid IN ( SELECT invoiceid FROM inventory_kitchen_product_supply WHERE supplyid IN ( SELECT supplyid from inventory_kitchen_product_stock where productid=? ) ) ";			
							
							String deleteQueryCreditNote="DELETE FROM inventory_kitchen_stock_creditnote WHERE creditnoteid IN ( SELECT creditnoteid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";
							String deleteQueryRequirement="DELETE FROM inventory_kitchen_stock_requirement WHERE requirementid IN ( SELECT requirementid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";
							String deleteQueryConsumption="DELETE FROM inventory_kitchen_stock_consumtion WHERE consumptionid IN ( SELECT consumptionid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";
							String deleteQueryAddition="DELETE FROM inventory_kitchen_stock_addition WHERE additionid IN ( SELECT additionid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";
							String deleteQueryAvailable="DELETE FROM inventory_kitchen_stock_available WHERE availableid IN ( SELECT availableid FROM inventory_kitchen_store WHERE stockid IN ( SELECT stockid FROM inventory_kitchen_product_stock WHERE productid=? ) ) ";

							try{								
								//delete creditnotes
								pStmt1aa = con.prepareStatement(deleteQueryCreditNote);
								pStmt1aa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt1aa.executeUpdate();
								
								//delete requirement
								pStmt2aaa = con.prepareStatement(deleteQueryRequirement);
								pStmt2aaa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt2aaa.executeUpdate();
								
								//delete consumption
								pStmt3aa = con.prepareStatement(deleteQueryConsumption);
								pStmt3aa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt3aa.executeUpdate();
								
								//delete addition
								pStmt4aaa = con.prepareStatement(deleteQueryAddition);
								pStmt4aa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt4aa.executeUpdate();
								
								//de;ete available
								pStmt5aa = con.prepareStatement(deleteQueryAvailable);
								pStmt5aa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt5aa.executeUpdate();
								
								//delete invoice
								pStmt6aa = con.prepareStatement(deleteQueryInvoice);
								pStmt6aa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt6aa.executeUpdate();
								
								//delete transaction
								pStmt7aa = con.prepareStatement(deleteQueryTransaction);
								pStmt7aa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt7aa.executeUpdate();
								
								//delete store
								pStmt8aa = con.prepareStatement(deleteQueryStore);
								pStmt8aa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt8aa.executeUpdate();
								
								//delete supply
								pStmt9aa = con.prepareStatement(deleteQuerySupply);
								pStmt9aa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt9aa.executeUpdate();
								
								//delete stock
								pStmt10aa = con.prepareStatement(deleteQueryStock);
								pStmt10aa.setInt(1, Integer.parseInt(formVO.getProductId()));			
								ret = pStmt10aa.executeUpdate();
								
								//delete ingredient inventory procuct
								pStmt11aaa = con.prepareStatement(ingredientProductSelectQueryaa);
								pStmt11aaa.setInt(1,Integer.parseInt(formVO.getProductId()));
								rs11aaa = pStmt11aaa.executeQuery();
									
								while(rs11aaa.next()){
									temp = rs11aaa.getInt(1);			
									for(int i=1; i<=temp; i++){
										pStmt12aa = con.prepareStatement(deleteQueryInventoryProduct);
										pStmt12aa.setInt(1,Integer.parseInt(formVO.getProductId()));
										pStmt12aa.executeUpdate();	
									}
								}
								
								//delete inventory procuct
								pStmtaa = con.prepareStatement(deleteQueryProduct);
								pStmtaa.setInt(1, Integer.parseInt(formVO.getProductId()));
								
								ret = pStmtaa.executeUpdate();
							}
							catch(Exception ex1){
								ex1.printStackTrace();
							}
							finally{
								dbConnection.close(pStmtaa, con);
								dbConnection.close(pStmt1aa, con);
								dbConnection.close(pStmt2aaa, con);
								dbConnection.close(pStmt3aa, con);
								dbConnection.close(pStmt4aaa, con);
								dbConnection.close(pStmt5aa, con);
								dbConnection.close(pStmt6aa, con);
								dbConnection.close(pStmt7aa, con);
								dbConnection.close(pStmt8aa, con);
								dbConnection.close(pStmt9aa, con);
								dbConnection.close(pStmt10aa, con);
								dbConnection.close(pStmt11aaa, con);
								dbConnection.close(pStmt12aa, con);
							}
							
							ex.printStackTrace();
						}
						
						finally{
							dbConnection.close(pStmt, con);
							dbConnection.close(pStmt1, con);
							dbConnection.close(pStmt4, con);
							dbConnection.close(pStmt5, con);
							dbConnection.close(pStmt6, con);
							dbConnection.close(pStmt7, con);
							dbConnection.close(pStmt8, con);
							dbConnection.close(pStmt9, con);							
							dbConnection.close(pStmta, con);
							dbConnection.close(pStmt1a, con);
							dbConnection.close(pStmt2a, con);
							dbConnection.close(pStmt3a, con);
							dbConnection.close(pStmt4a, con);
							dbConnection.close(pStmt5a, con);
							dbConnection.close(pStmt6a, con);
							dbConnection.close(pStmt7a, con);
							dbConnection.close(pStmt8a, con);
							dbConnection.close(pStmt9a, con);
							dbConnection.close(pStmt10a, con);
							dbConnection.close(pStmt11a, con);
							dbConnection.close(pStmt12a, con);
							dbConnection.close(pStmt13a, con);
							dbConnection.close(pStmt14a, con);
							dbConnection.close(pStmt15a, con);
							dbConnection.close(pStmt16a, con);
							dbConnection.close(pStmt17a, con);
							dbConnection.close(pStmt18a, con);
							dbConnection.close(pStmt19a, con);
							dbConnection.close(pStmt20a, con);
							dbConnection.close(pStmt21a, con);
							dbConnection.close(pStmt22a, con);
							dbConnection.close(pStmt23a, con);
							dbConnection.close(pStmt24a, con);
							dbConnection.close(pStmt25a, con);
							dbConnection.close(pStmt26a, con);
							dbConnection.close(pStmt27a, con);
							dbConnection.close(pStmt28a, con);
							dbConnection.close(pStmt29a, con);
							dbConnection.close(pStmt30a, con);
							
							dbConnection.close(pStmt2aa, con);
							dbConnection.close(pStmt4aa, con);
							dbConnection.close(pStmt11aa, con);
						}
		
		return temp;	
	}
	
	public ProductFormVO viewKitchenInventoryProduct(ProductFormVO formVO) throws SQLException,Exception{

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		
		String Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid from inventory_kitchen_inventory WHERE productid=? ";
		String QuantityTypeQuery = "SELECT producttype FROM inventory_kitchen_product_type WHERE producttypeid=? ";
		String ServingQuantityTypeQuery = "SELECT quantity FROM inventory_quantity_type WHERE quantityid=? ";
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
			rs = pStmt.executeQuery();
			
			while(rs.next()){							
				formVO.setProductId(rs.getString("productid"));
				formVO.setID(rs.getString("ID"));
				formVO.setName(rs.getString("name"));
				
				pStmt1 = con.prepareStatement(QuantityTypeQuery);
				pStmt1.setInt(1,Integer.parseInt(rs.getString("producttypeid")));
				rs1 = pStmt1.executeQuery();	
				
				while(rs1.next()){
					formVO.setProductTypeId(rs1.getString("producttype"));
				}
				
				formVO.setDescription(rs.getString("description"));
				formVO.setQuantityId(rs.getString("quantity"));
				pStmt2 = con.prepareStatement(ServingQuantityTypeQuery);
				pStmt2.setInt(1,Integer.parseInt(rs.getString("quantitytypeid")));
				rs2 = pStmt2.executeQuery();				
				while(rs2.next()){
					formVO.setQuantityTypeId(rs2.getString("quantity"));
				}								
			}
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
		}	
		
		return formVO;	
	}

	public List<ProductFormVO> getKitchenInventoryProductMap(ProductFormVO formVO) throws SQLException, Exception {

		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		ResultSet rs3 = null;
		
		boolean available=false;
		String productIds = null;
		int count = 0;
		
		String compositProductQuantityTypeQuery = "SELECT producttype FROM inventory_kitchen_product_type WHERE producttypeid=? ";
		
		String countQuery="SELECT COUNT(*) FROM inventory_kitchen_inventory";
		String IdQuery="SELECT productid FROM inventory_kitchen_inventory where productid = ?";
		
		try{
			con = dbConnection.getConnection();
			
			pStmt2 = con.prepareStatement(countQuery);
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				count = rs2.getInt(1);
			}
			
			int tempCount=count;
			List<String> countList = new ArrayList<String>();
			
			for(int i=1; i<=tempCount; i++){
				pStmt3 = con.prepareStatement(IdQuery);
				pStmt3.setInt(1, i);
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					int j=1;
					//if(i<6){
						if(j==1){
							productIds=rs3.getString("productid");
							j++;
						}
						else{
							productIds=productIds +","+ rs3.getString("productid");
							j++;
						}
					//}
				}
				
				String []str = null;
				
				if(productIds!=null){
					str = productIds.split(",");				
					for(int m=0;m<str.length;m++){
						if(str[m]!=null && str[m]!="")
							countList.add(str[m]);
					}
					if(countList.size()<count){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			for (int i=1;i<countList.size(); i++) {
	            String a1 = countList.get(i);
	            String a2 = countList.get(i-1);
	            if (a1.equals(a2)) {
	            	countList.remove(a1);
	            }
	        }
			
			String id = null;
			for(int k=0; k<countList.size();k++){
				if(k==0)
					id=countList.get(k);
				else
					id=id+","+countList.get(k);
			}
					
			String Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN ("+id+") ";

			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				
				pStmt1 = con.prepareStatement(compositProductQuantityTypeQuery);
				pStmt1.setString(1,rs.getString("producttypeid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setProductTypeId(rs1.getString("producttype"));
				}		
				
				formVOO.setDescription(rs.getString("description"));
				formVOO.setQuantityId(rs.getString("quantity"));
				formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
				formVOO.setStatusOn(rs.getString("statuson"));
				formVOO.setStatusOff(rs.getString("statusoff"));
				formVOO.setStatus(rs.getString("status"));
				productList.add(formVOO);
			}if(!available){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");				
				formVOO.setProductTypeId("----");			
				formVOO.setDescription("----");
				formVOO.setQuantityId("----");
				formVOO.setQuantityTypeId("----");
				formVOO.setStatusOn("btn btn-default btn-on btn-sm active");
				formVOO.setStatusOff("btn btn-default btn-off btn-sm");
				formVOO.setStatus("true");
				productList.add(formVOO);
			}
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
		}
		
		return productList;			
	 }
	
	public List<ProductFormVO> getKitchenInventoryProductIndexMap(ProductFormVO formVO) throws SQLException, Exception {

		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		ResultSet rs3 = null;
		
		boolean available=false;
		String productIds = null;
		int count = 0;
		
		String QuantityTypeQuery = "SELECT producttype FROM inventory_kitchen_product_type WHERE producttypeid=? ";
		
		String countQuery="SELECT COUNT(*) FROM inventory_kitchen_inventory";
		String IdQuery="SELECT productid FROM inventory_kitchen_inventory where productid = ?";
		
		try{
			List<String> countList = new ArrayList<String>();
			
			con = dbConnection.getConnection();
			
			pStmt2 = con.prepareStatement(countQuery);
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				count = rs2.getInt(1);
			}
			
			int tempCount=count;
			
			for(int i=1; i<=tempCount; i++){
				pStmt3 = con.prepareStatement(IdQuery);
				pStmt3.setInt(1, i);
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					int j=1;
						if(j==1){
							productIds=rs3.getString("productid");
							j++;
						}
						else{
							productIds=productIds +","+ rs3.getString("productid");
							j++;
						}					
				}
				
				String []str = null;
				
				if(productIds!=null){
					str = productIds.split(",");				
					for(int m=0;m<str.length;m++){
						if(str[m]!=null && str[m]!="")
							countList.add(str[m]);
					}
					if(countList.size()<count){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			for (int i=1;i<countList.size(); i++) {
	            String a1 = countList.get(i);
	            String a2 = countList.get(i-1);
	            if (a1.equals(a2)) {
	            	countList.remove(a1);
	            }
	        }
			
			String id = null;
			for(int k=0; k<countList.size();k++){
				if(k==0)
					id=countList.get(k);
				else
					id=id+","+countList.get(k);
			}
			
			String Query="SELECT productid, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN ("+id+")";

			pStmt = con.prepareStatement(Query);			
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				available = true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				
				pStmt1 = con.prepareStatement(QuantityTypeQuery);
				pStmt1.setString(1,rs.getString("producttypeid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setProductTypeId(rs1.getString("producttype"));
				}
				
				formVOO.setDescription(rs.getString("description"));
				formVOO.setQuantityId(rs.getString("quantity"));
				formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
				formVOO.setStatusOn(rs.getString("statuson"));
				formVOO.setStatusOff(rs.getString("statusoff"));
				formVOO.setStatus(rs.getString("status"));
				productList.add(formVOO);
			}
			
			if(!available){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("----");
				formVOO.setName("----");				
				formVOO.setProductTypeId("----");			
				formVOO.setDescription("----");
				formVOO.setQuantityId("----");
				formVOO.setQuantityTypeId("----");
				formVOO.setStatusOn("btn btn-default btn-on btn-sm active");
				formVOO.setStatusOff("btn btn-default btn-off btn-sm");
				formVOO.setStatus("true");
				productList.add(formVOO);
			}
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
		}
		
		return productList;			
	 }
	
	public List<ProductFormVO> getKitchenInventoryProductsByRecords(ProductFormVO formVO)throws SQLException,Exception{
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;	
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		ResultSet rs3 = null;
		
		boolean available = false;
		String productIds1 = null;
		String productIds2 = null;
		String productIds3 = null;
		int count = 0;
		
		String Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN (?)";
		String QuantityTypeQuery = "SELECT producttype FROM inventory_kitchen_product_type WHERE producttypeid=? ";
		
		String countQuery = "SELECT COUNT(*) FROM inventory_kitchen_product";
		String IdQuery = "SELECT productid FROM inventory_kitchen_product where productid = ?";
		
		try{
			con = dbConnection.getConnection();
			
			pStmt2 = con.prepareStatement(countQuery);
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				count = rs2.getInt(1);
			}
			
			for(int i=1; i<=count; i++){
				pStmt3 = con.prepareStatement(IdQuery);
				pStmt3.setInt(1, i);
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					if(i<6){
						if(i==1)
							productIds1=rs3.getString("productid");
						else
							productIds1=productIds1 +","+ rs3.getString("productid");
					}
					if(i>5 && i<11){
						if(i==6)
							productIds2=productIds1+","+rs3.getString("productid");
						else
							productIds2=productIds2 +","+ rs3.getString("productid");
					}
					if(i>10 && i<16){
						if(i==11)
							productIds3=productIds2+","+rs3.getString("productid");
						else
							productIds3=productIds3 +","+ rs3.getString("productid");
					}
				}			
			}					
			
			if(formVO.getLimit().equalsIgnoreCase("1")){
				if(productIds1!=null)
					Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN ("+productIds1+")";
				else
					Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid=1";
			}else if(formVO.getLimit().equalsIgnoreCase("2")){
				if(productIds2!=null){
					Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN ("+productIds2+")";
				}else{
					if(productIds1!=null)
						Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN ("+productIds1+")";
					else
						Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid=1";
				}					
			}else if(formVO.getLimit().equalsIgnoreCase("3")){
				if(productIds3!=null){
					Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN ("+productIds3+")";
				}else{
					if(productIds2!=null){
						Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN ("+productIds2+")";
					}else{
						if(productIds1!=null)
							Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN ("+productIds1+")";
						else
							Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid=1";
					}
				}
			}else{
				Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid=1";
			}
				
			pStmt = con.prepareStatement(Query);	
			rs = pStmt.executeQuery();
			
			while(rs.next()){				
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				
				pStmt1 = con.prepareStatement(QuantityTypeQuery);
				pStmt1.setString(1,rs.getString("producttypeid"));
				rs1 = pStmt1.executeQuery();	
				
				while(rs1.next()){
					formVOO.setProductTypeId(rs1.getString("producttype"));
				}
				
				formVOO.setDescription(rs.getString("description"));
				formVOO.setQuantityId(rs.getString("quantity"));
				formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
				formVOO.setStatusOn(rs.getString("statuson"));
				formVOO.setStatusOff(rs.getString("statusoff"));
				formVOO.setStatus(rs.getString("status"));
				productList.add(formVOO);
			}if(!available){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");				
				formVOO.setProductTypeId("----");			
				formVOO.setDescription("----");
				formVOO.setQuantityId("----");
				formVOO.setQuantityTypeId("----");
				formVOO.setStatusOn("btn btn-default btn-on btn-sm active");
				formVOO.setStatusOff("btn btn-default btn-off btn-sm");
				formVOO.setStatus("true");
				productList.add(formVOO);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
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
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		ResultSet rs3 = null;
		
		boolean available = false;
		String productIds = null;
		List <String> productIdsList = new ArrayList<String>();
		int count = 0;
		
		String Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN (?)";		  
		String QuantityTypeQuery = "SELECT producttype FROM inventory_kitchen_product_type WHERE producttypeid=? ";
		
		String countQuery = "SELECT COUNT(*) FROM inventory_kitchen_inventory";
		String IdQuery = "SELECT productid FROM inventory_kitchen_inventory where productid = ?";
		
		try{
			con = dbConnection.getConnection();
			
			pStmt2 = con.prepareStatement(countQuery);
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				count = rs2.getInt(1);
			}
			
			int tempCount = count;
						
			for(int i=1; i<=tempCount; i++){
				pStmt3 = con.prepareStatement(IdQuery);
				pStmt3.setInt(1, i);
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					int j=1;
					//if(i<6){
						if(j==1){
							productIds=rs3.getString("productid");
							j++;
						}
						else{
							productIds=productIds +","+ rs3.getString("productid");
							j++;
						}
					//}
				}
				
				String []str = null;
				
				if(productIds!=null){
					str = productIds.split(",");				
					for(int m=0;m<str.length;m++){
						if(str[m]!=null && str[m]!="")
							productIdsList.add(str[m]);
					}
					if(productIdsList.size()<count){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			for (int i=1;i<productIdsList.size(); i++) {
	            String a1 = productIdsList.get(i);
	            String a2 = productIdsList.get(i-1);
	            if (a1.equals(a2)) {
	            	productIdsList.remove(a1);
	            }
	        }
											
			String id = null;
			int temp = Integer.parseInt(formVO.getPageFrom());
			
			if(temp>0){			
				if(productIdsList.size() >= (temp+5)){
					for(int k=temp;k<temp+5;k++){
						if(k==0)
							id=productIdsList.get(k);
						else
							id=id+","+productIdsList.get(k);
					}
				}else{
					for(int k=0; k<productIdsList.size();k++){
						if(k==0)
							id=productIdsList.get(k);
						else
							id=id+","+productIdsList.get(k);
					}
				}
			}else{
				for(int k=0; k<productIdsList.size();k++){
					if(k==0)
						id=productIdsList.get(k);
					else
						id=id+","+productIdsList.get(k);
				}
			}
									
			Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND productid IN ("+id+")";
			
			pStmt = con.prepareStatement(Query);	
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				
				pStmt1 = con.prepareStatement(QuantityTypeQuery);
				pStmt1.setInt(1,Integer.parseInt(rs.getString("producttypeid")));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setProductTypeId(rs1.getString("producttype"));
				}
				
				formVOO.setDescription(rs.getString("description"));
				formVOO.setQuantityId(rs.getString("quantity"));
				formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
				formVOO.setStatusOn(rs.getString("statuson"));
				formVOO.setStatusOff(rs.getString("statusoff"));
				formVOO.setStatus(rs.getString("status"));
				
				productList.add(formVOO);
			}
			if(!available){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");				
				formVOO.setProductTypeId("----");			
				formVOO.setDescription("----");
				formVOO.setQuantityId("----");
				formVOO.setQuantityTypeId("----");
				formVOO.setStatusOn("btn btn-default btn-on btn-sm active");
				formVOO.setStatusOff("btn btn-default btn-off btn-sm");
				formVOO.setStatus("true");
				productList.add(formVOO);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
		}
		
		return productList;	
	}
	
	public Map<String, Object> getKitchenInventoryProductList(Map<String, Object> map) throws SQLException, Exception{
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
				
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
	
	public List<String> viewKitchenIngredientProductList(ProductFormVO formVO)throws SQLException,Exception{
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		
		List<String> kitchenIngredientProductList = new ArrayList<String>();  
		
		String Query1="SELECT ingredientid FROM inventory_kitchen_inventory_product where inventoryid=? ";
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
					kitchenIngredientProductList.add(rs1.getString("name"));
				}
			}			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
		}
		
		return kitchenIngredientProductList;	
	}
	
	public List<String> viewKitchenIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception{
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
	
	public List<String> viewKitchenIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception{
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
					kitchenIngredientProductQuantityTypeList.add(rs1.getString("quantity"));
				}
			}			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
		}
		
		return kitchenIngredientProductQuantityTypeList;
	}
	
	public List<String> getKitchenIngredientProductList(ProductFormVO formVO)throws SQLException,Exception{
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
	
	public List<String> getKitchenIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception{
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
	
	public List<String> getKitchenIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception{
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

	public List<ProductFormVO> getKitchenInventoryProductsByCreatedDates(ProductFormVO formVO)
			throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pStmt2 = null;
		PreparedStatement pStmt3 = null;
		boolean available = false;
		
		String Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name IS NOT NULL AND created >= '"+formVO.getFromDate()+"' AND created <='"+formVO.getToDate()+"'";
		String QuantityTypeQuery = "SELECT producttype FROM inventory_kitchen_product_type WHERE producttypeid=? ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
						
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
					
				pStmt1 = con.prepareStatement(QuantityTypeQuery);
				pStmt1.setString(1,rs.getString("producttypeid"));
				rs1 = pStmt1.executeQuery();
					
				while(rs1.next()){
					formVOO.setProductTypeId(rs1.getString("producttype"));
				}
					
				formVOO.setDescription(rs.getString("description"));
				formVOO.setQuantityId(rs.getString("quantity"));
				formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
				formVOO.setStatusOn(rs.getString("statuson"));
				formVOO.setStatusOff(rs.getString("statusoff"));
				formVOO.setStatus(rs.getString("status"));
				productList.add(formVOO);
			}
			
			if(!available){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");								
				formVOO.setProductTypeId("----");									
				formVOO.setDescription("----");
				formVOO.setQuantityId("----");
				formVOO.setQuantityTypeId("----");
				formVOO.setStatusOn("btn btn-default btn-on btn-sm active");
				formVOO.setStatusOff("btn btn-default btn-off btn-sm");
				formVOO.setStatus("true");
				productList.add(formVOO);				
			}
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
		}
		
		return productList;			
	}

	public List<ProductFormVO> getKitchenInventoryProductsByName(ProductFormVO formVO) throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pStmt2 = null;
		PreparedStatement pStmt3 = null;
		boolean available = false;
		
		String Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE name like '%"+formVO.getName()+"%'";
		String QuantityTypeQuery = "SELECT producttype FROM inventory_kitchen_product_type WHERE producttypeid=? ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
						
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
					
				pStmt1 = con.prepareStatement(QuantityTypeQuery);
				pStmt1.setString(1,rs.getString("producttypeid"));
				rs1 = pStmt1.executeQuery();
					
				while(rs1.next()){
					formVOO.setProductTypeId(rs1.getString("producttype"));
				}
					
				formVOO.setDescription(rs.getString("description"));
				formVOO.setQuantityId(rs.getString("quantity"));
				formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
				formVOO.setStatusOn(rs.getString("statuson"));
				formVOO.setStatusOff(rs.getString("statusoff"));
				formVOO.setStatus(rs.getString("status"));
				productList.add(formVOO);
			}
			
			if(!available){				
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");								
				formVOO.setProductTypeId("----");									
				formVOO.setDescription("----");
				formVOO.setQuantityId("----");
				formVOO.setQuantityTypeId("----");
				formVOO.setStatusOn("btn btn-default btn-on btn-sm active");
				formVOO.setStatusOff("btn btn-default btn-off btn-sm");
				formVOO.setStatus("true");
				productList.add(formVOO);				
			}
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
		}
		
		return productList;			
	}

	public List<ProductFormVO> getKitchenInventoryProductsById(ProductFormVO formVO) throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pStmt2 = null;
		PreparedStatement pStmt3 = null;
		boolean available = false;
		
		String Query="SELECT productid, ID, name, producttypeid, description, quantity, quantitytypeid, statuson, statusoff, status from inventory_kitchen_inventory WHERE productid ="+formVO.getProductId();
		String QuantityTypeQuery = "SELECT producttype FROM inventory_kitchen_product_type WHERE producttypeid=? ";
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
					
				pStmt1 = con.prepareStatement(QuantityTypeQuery);
				pStmt1.setString(1,rs.getString("producttypeid"));
				rs1 = pStmt1.executeQuery();
					
				while(rs1.next()){
					formVOO.setProductTypeId(rs1.getString("producttype"));
				}
					
				formVOO.setDescription(rs.getString("description"));
				formVOO.setQuantityId(rs.getString("quantity"));
				formVOO.setQuantityTypeId(rs.getString("quantitytypeid"));
				formVOO.setStatusOn(rs.getString("statuson"));
				formVOO.setStatusOff(rs.getString("statusoff"));
				formVOO.setStatus(rs.getString("status"));
				productList.add(formVOO);
			}
			
			if(!available){			
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");								
				formVOO.setProductTypeId("----");									
				formVOO.setDescription("----");
				formVOO.setQuantityId("----");
				formVOO.setQuantityTypeId("----");
				formVOO.setStatusOn("btn btn-default btn-on btn-sm active");
				formVOO.setStatusOff("btn btn-default btn-off btn-sm");
				formVOO.setStatus("true");
				productList.add(formVOO);				
			}
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
		}
		
		return productList;			
	}

}
