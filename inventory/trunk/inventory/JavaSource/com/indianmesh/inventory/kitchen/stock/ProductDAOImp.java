package com.indianmesh.inventory.kitchen.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.indianmesh.inventory.utils.ProductComparator;
import com.indianmesh.inventory.utils.StockComparator;
import com.indianmesh.inventory.utils.dbConnection.DBConnection;

public class ProductDAOImp implements ProductBaseDAO {
	
		ProductComparator productComparator = new ProductComparator();
		StockComparator stockComparator = new StockComparator();
		
		public void updateKitchenProductConsumptionAfterSingleEntry(ProductFormVO formVO) throws SQLException, Exception {

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
			PreparedStatement pStmt6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			
			int temp = 0;
			
			String available = null;
			String totalCount = null;
			String availableid = null;
			String consumptionid = null;
			String lastSupplyDate = null;
			String storeid = null;
			String stockid = null;
			String requirementid = null;
			String additionid = null;
			String creditnoteid = null;
			
			String Query="SELECT stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ) ORDER BY lastmodified DESC LIMIT 1  ";
			String Query1="INSERT INTO inventory_kitchen_stock_available ( availableid, available, description, created, lastmodified, sellerid, subsellerid, lastcount ) VALUES (?,?,?,?,?,?,?,?) ";
			String Query2="INSERT INTO inventory_kitchen_stock_consumtion ( consumptionid, consumption, description, created, lastmodified, sellerid, subsellerid ) VALUES (?,?,?,?,?,?,?) ";	 		
			String Query3="INSERT INTO inventory_kitchen_store ( storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			String Query4="SELECT addition, description, lastmodified from inventory_kitchen_stock_addition where additionid = ( SELECT additionid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";
 
			String countQuery1 = "SELECT COUNT(*) FROM inventory_kitchen_stock_available "; 
			String idQuery1 = "SELECT availableid FROM inventory_kitchen_stock_available where availableid=? ";
			
			String countQuery2 = "SELECT COUNT(*) FROM inventory_kitchen_stock_consumtion "; 
			String idQuery2 = "SELECT consumptionid FROM inventory_kitchen_stock_consumtion where consumptionid=? ";
			
			String countQuery3 = "SELECT COUNT(*) FROM inventory_kitchen_store "; 
			String idQuery3 = "SELECT storeid FROM inventory_kitchen_store where storeid=? ";
			
			try{
				con = dbConnection.getConnection();
				pStmt1 = con.prepareStatement(countQuery1);
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					temp = rs1.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt2 = con.prepareStatement(idQuery1);
						pStmt2.setInt(1,i);
						rs2 = pStmt2.executeQuery();					
						
						if(!rs2.next())
							availableid = String.valueOf(i);
					}
				}
				
				pStmt3 = con.prepareStatement(countQuery2);
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					temp = rs3.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt4 = con.prepareStatement(idQuery2);
						pStmt4.setInt(1,i);
						rs4 = pStmt4.executeQuery();					
						
						if(!rs4.next())
							consumptionid = String.valueOf(i);
					}
				}
				
				if(formVO.getAvailableId().contains(formVO.getQuantityPerUnit())){
					available = String.valueOf(Integer.parseInt(formVO.getAvailableId().split(" ")[0])-Integer.parseInt(formVO.getConsumptionId()));
					if(formVO.getLastCount().contains("KG")){
						totalCount = String.valueOf(Integer.parseInt(available) / (1000)) ;
					}else if(formVO.getLastCount().contains("JAR")){
						totalCount = String.valueOf(Integer.parseInt(available) / (1140)) ;
					}else if(formVO.getLastCount().contains("GALLON")){
						totalCount = String.valueOf(Integer.parseInt(available) / (3785.41)) ;
					}else if(formVO.getLastCount().contains("LTR")){
						totalCount = String.valueOf(Integer.parseInt(available) / (1000)) ;
					}else if(formVO.getLastCount().contains("BTL")){
						totalCount = String.valueOf(Integer.parseInt(available) / (1000)) ;
					}else if(formVO.getLastCount().contains("NOS")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}else if(formVO.getLastCount().contains("BOX")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}else if(formVO.getLastCount().contains("POUCH")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}else if(formVO.getLastCount().contains("CASE")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}else if(formVO.getLastCount().contains("BUNCH")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}else if(formVO.getLastCount().contains("GM")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}else if(formVO.getLastCount().contains("Ml")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}else if(formVO.getLastCount().contains("PKT")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}else if(formVO.getLastCount().contains("PCS")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}else if(formVO.getLastCount().contains("TIN")){
						totalCount = String.valueOf(Integer.parseInt(available)) ;
					}
				}else if(formVO.getLastCount().contains(formVO.getQuantityPerUnit())){
					totalCount = String.valueOf(Integer.parseInt(formVO.getLastCount().split(" ")[0])-Integer.parseInt(formVO.getConsumptionId()));	
					
					if(formVO.getLastCount().contains("KG")){
						available = String.valueOf(Integer.parseInt(formVO.getAvailableId().split(" ")[0]) - (Integer.parseInt(formVO.getConsumptionId()) * 1000));
					}else if(formVO.getLastCount().contains("JAR")){
						available = String.valueOf(Integer.parseInt(formVO.getAvailableId().split(" ")[0]) - (Integer.parseInt(formVO.getConsumptionId()) * 1140));
					}else if(formVO.getLastCount().contains("GALLON")){
						available = String.valueOf(Integer.parseInt(formVO.getAvailableId().split(" ")[0]) - (Integer.parseInt(formVO.getConsumptionId()) * 3785.41));
					}else if(formVO.getLastCount().contains("LTR")){
						available = String.valueOf(Integer.parseInt(formVO.getAvailableId().split(" ")[0]) - (Integer.parseInt(formVO.getConsumptionId()) * 1000));
					}else if(formVO.getLastCount().contains("BTL")){
						available = String.valueOf(Integer.parseInt(formVO.getAvailableId().split(" ")[0]) - (Integer.parseInt(formVO.getConsumptionId()) * 1000));
					}else if(formVO.getLastCount().contains("NOS")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}else if(formVO.getLastCount().contains("BOX")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}else if(formVO.getLastCount().contains("POUCH")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}else if(formVO.getLastCount().contains("CASE")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}else if(formVO.getLastCount().contains("BUNCH")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}else if(formVO.getLastCount().contains("GM")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}else if(formVO.getLastCount().contains("Ml")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}else if(formVO.getLastCount().contains("PKT")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}else if(formVO.getLastCount().contains("PCS")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}else if(formVO.getLastCount().contains("TIN")){
						available = String.valueOf(Integer.parseInt(totalCount)) ;
					}
				}
				
				pStmt5=con.prepareStatement(Query1);
				pStmt5.setString(1,availableid);			
				pStmt5.setString(2,available);
				pStmt5.setString(3,formVO.getAvailableId().split(" ")[1].toString());
				pStmt5.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt5.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt5.setString(6,formVO.getSellerId());
				pStmt5.setString(7,formVO.getSubSellerId());			
				pStmt5.setString(8,totalCount);
				pStmt5.executeUpdate();
				
				pStmt6=con.prepareStatement(Query2);
				pStmt6.setString(1,consumptionid);			
				pStmt6.setString(2,formVO.getConsumptionId());
				pStmt6.setString(3,formVO.getQuantityPerUnit());
				pStmt6.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt6.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt6.setString(6,formVO.getSellerId());
				pStmt6.setString(7,formVO.getSubSellerId());
				pStmt6.executeUpdate();
				
				pStmt7 = con.prepareStatement(countQuery3);
				rs7 = pStmt7.executeQuery();
				
				while(rs7.next()){
					temp = rs7.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt8 = con.prepareStatement(idQuery3);
						pStmt8.setInt(1,i);
						rs8 = pStmt8.executeQuery();					
						
						if(!rs8.next())
							storeid = String.valueOf(i);
					}
				}
				
				pStmt10 = con.prepareStatement(Query);
				pStmt10.setString(1,formVO.getProductId());
				rs10 = pStmt10.executeQuery();
				
				while(rs10.next()){
					stockid = rs10.getString("stockid");
					requirementid = rs10.getString("requirementid");
					additionid = rs10.getString("additionid");
					creditnoteid = rs10.getString("creditnoteid");
				}
								
				pStmt11 = con.prepareStatement(Query4);
				pStmt11.setString(1,formVO.getProductId());
				rs11 = pStmt11.executeQuery();
				
				while(rs11.next()){
					lastSupplyDate = rs11.getString("lastmodified");
				}
				
				pStmt9=con.prepareStatement(Query3);
				pStmt9.setString(1,storeid);			
				pStmt9.setString(2,formVO.getSellerId());
				pStmt9.setString(3,formVO.getSubSellerId());		
				pStmt9.setString(4,stockid);
				pStmt9.setString(5,availableid);
				pStmt9.setString(6,consumptionid);
				pStmt9.setString(7,requirementid);
				pStmt9.setString(8,additionid);
				pStmt9.setString(9,creditnoteid);
				pStmt9.setString(10,formVO.getStatus());
				pStmt9.setString(11,"btn btn-default btn-on btn-sm");
				pStmt9.setString(12,"btn btn-default btn-off btn-sm active");
				pStmt9.setString(13,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt9.setString(14,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt9.setString(15,lastSupplyDate);
				pStmt9.executeUpdate();
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
			}
		}
		
		public void updateKitchenProductConsumptionAfterMultipleEntry(ProductFormVO formVO) throws SQLException, Exception {

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
			PreparedStatement pStmt6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			PreparedStatement pStmt12 = null;
			ResultSet rs12 = null;
			
			int temp = 0;
			
			String available = null;
			int totalCount = 0;
			String availableid = null;
			String consumptionid = null;
			String lastSupplyDate = null;
			String storeid = null;
			String stockid = null;
			String requirementid = null;
			String additionid = null;
			String creditnoteid = null;
			
			String availableTemp = null;
			String descriptionTemp = null;
			String lastcountTemp = null;
			
			String Query="SELECT stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ) ORDER BY lastmodified DESC LIMIT 1  ";
			String Query1="INSERT INTO inventory_kitchen_stock_available ( availableid, available, description, created, lastmodified, sellerid, subsellerid, lastcount ) VALUES (?,?,?,?,?,?,?,?) ";
			String Query2="INSERT INTO inventory_kitchen_stock_consumtion ( consumptionid, consumption, description, created, lastmodified, sellerid, subsellerid ) VALUES (?,?,?,?,?,?,?) ";	 		
			String Query3="INSERT INTO inventory_kitchen_store ( storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			String Query4="SELECT addition, description, lastmodified from inventory_kitchen_stock_addition where additionid = ( SELECT additionid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";
			String Query5="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ( SELECT availableid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";

			String countQuery1 = "SELECT COUNT(*) FROM inventory_kitchen_stock_available "; 
			String idQuery1 = "SELECT availableid FROM inventory_kitchen_stock_available where availableid=? ";
			
			String countQuery2 = "SELECT COUNT(*) FROM inventory_kitchen_stock_consumtion "; 
			String idQuery2 = "SELECT consumptionid FROM inventory_kitchen_stock_consumtion where consumptionid=? ";
			
			String countQuery3 = "SELECT COUNT(*) FROM inventory_kitchen_store "; 
			String idQuery3 = "SELECT storeid FROM inventory_kitchen_store where storeid=? ";
			
			try{
				con = dbConnection.getConnection();
				pStmt1 = con.prepareStatement(countQuery1);
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					temp = rs1.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt2 = con.prepareStatement(idQuery1);
						pStmt2.setInt(1,i);
						rs2 = pStmt2.executeQuery();					
						
						if(!rs2.next())
							availableid = String.valueOf(i);
					}
				}
				
				pStmt3 = con.prepareStatement(countQuery2);
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					temp = rs3.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt4 = con.prepareStatement(idQuery2);
						pStmt4.setInt(1,i);
						rs4 = pStmt4.executeQuery();					
						
						if(!rs4.next())
							consumptionid = String.valueOf(i);
					}
				}
				
				pStmt12 = con.prepareStatement(Query5);
				pStmt12.setString(1, formVO.getProductId());
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					
					availableTemp=rs12.getString("available");
					descriptionTemp=rs12.getString("description");
					lastcountTemp=rs12.getString("lastcount");
					
					if(descriptionTemp.contains("KG")){
						if(formVO.getQuantityPerUnit().contains("KG")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId())));
							totalCount = (int) Integer.parseInt(available) / 1000 ;
						}else if(formVO.getQuantityPerUnit().contains("GM")){
							available = String.valueOf((Integer.parseInt(availableTemp) * 1000 ) - (Integer.parseInt(formVO.getConsumptionId())));
							available = String.valueOf(Integer.parseInt(available) / 1000 );
							totalCount = (int) Integer.parseInt(available) / 1000 ;
						}
					}else if(descriptionTemp.contains("JAR")){
						if(formVO.getQuantityPerUnit().contains("JAR")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId())));
							totalCount = (int) Integer.parseInt(available) / 1140 ;
						}else if(formVO.getQuantityPerUnit().contains("Ml")){
							available = String.valueOf((Integer.parseInt(availableTemp) * 1140 ) - (Integer.parseInt(formVO.getConsumptionId())));
							available = String.valueOf(Integer.parseInt(available) / 1140);
							totalCount = (int) Integer.parseInt(available) / 1140;
						}else if(formVO.getQuantityPerUnit().contains("LTR")){
							available = String.valueOf((Integer.parseInt(availableTemp) * 1140 ) - (Integer.parseInt(formVO.getConsumptionId()) * 1000 ));
							available = String.valueOf(Integer.parseInt(available) / 1140);
							totalCount = (int) Integer.parseInt(available) / 1140;
						}
					}else if(descriptionTemp.contains("GALLON")){
						if(formVO.getQuantityPerUnit().contains("GALLON")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId())));
							totalCount = (int) (Integer.parseInt(available) / 3785.41) ;
						}else if(formVO.getQuantityPerUnit().contains("Ml")){
							available = String.valueOf((Integer.parseInt(availableTemp) * 3785.41 ) - (Integer.parseInt(formVO.getConsumptionId())));
							available = String.valueOf(Integer.parseInt(available) / 3785.41);
							totalCount = (int) (Integer.parseInt(available) / 3785.41);
						}else if(formVO.getQuantityPerUnit().contains("LTR")){
							available = String.valueOf((Integer.parseInt(availableTemp) * 3785.41 ) - (Integer.parseInt(formVO.getConsumptionId()) * 1000 ));
							available = String.valueOf(Integer.parseInt(available) / 3785.41);
							totalCount = (int) (Integer.parseInt(available) / 3785.41);
						}
					}else if(descriptionTemp.contains("LTR")){
						if(formVO.getQuantityPerUnit().contains("LTR")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId())));
							totalCount = (int) (Integer.parseInt(available) / 1000 );
						}else if(formVO.getQuantityPerUnit().contains("Ml")){
							available = String.valueOf((Integer.parseInt(availableTemp) * 1000 ) - (Integer.parseInt(formVO.getConsumptionId())));
							totalCount =  (int) (Integer.parseInt(available) / 1000 );
						}
					}else if(descriptionTemp.contains("BTL")){
						if(formVO.getQuantityPerUnit().contains("Ml")){
							available = String.valueOf((Integer.parseInt(availableTemp) * 1000 ) - (Integer.parseInt(formVO.getConsumptionId())));
							totalCount = (int) (Integer.parseInt(available) / 1000 );
						}else if(formVO.getQuantityPerUnit().contains("BTL")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId())));
							totalCount =  (int) (Integer.parseInt(available) / 1000 );
						}
					}else if(descriptionTemp.contains("NOS")){
						available = String.valueOf(Integer.parseInt(availableTemp) - Integer.parseInt(formVO.getConsumptionId()));
						totalCount = (int) Integer.parseInt(available);
					}else if(descriptionTemp.contains("BOX")){
						available = String.valueOf(Integer.parseInt(availableTemp) - Integer.parseInt(formVO.getConsumptionId()));
						totalCount = (int) Integer.parseInt(available);
					}else if(descriptionTemp.contains("POUCH")){
						available = String.valueOf(Integer.parseInt(availableTemp) - Integer.parseInt(formVO.getConsumptionId()));
						totalCount = (int) Integer.parseInt(available);
					}else if(descriptionTemp.contains("CASE")){
						available = String.valueOf(Integer.parseInt(availableTemp) - Integer.parseInt(formVO.getConsumptionId()));
						totalCount = (int) Integer.parseInt(available);
					}else if(descriptionTemp.contains("BUNCH")){
						available = String.valueOf(Integer.parseInt(availableTemp) - Integer.parseInt(formVO.getConsumptionId()));
						totalCount = (int) Integer.parseInt(available);
					}else if(descriptionTemp.contains("GM")){
						if(formVO.getQuantityPerUnit().contains("GM")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId())));
							totalCount = (int) (Integer.parseInt(available));
						}else if(formVO.getQuantityPerUnit().contains("KG")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId()) * 1000 ));
							totalCount = (int) (Integer.parseInt(available) / 1000 );
						}
					}else if(descriptionTemp.contains("Ml")){
						if(formVO.getQuantityPerUnit().contains("Ml")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId())));
							totalCount = (int) (Integer.parseInt(available) / 1000 );
						}else if(formVO.getQuantityPerUnit().contains("LTR")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId()) * 1000 ));
							totalCount = (int) (Integer.parseInt(available) / 1000 );
						}else if(formVO.getQuantityPerUnit().contains("GALLON")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId()) * 3785.41 ));
							totalCount = (int) (Integer.parseInt(available) / 3.78541 );
						}else if(formVO.getQuantityPerUnit().contains("JAR")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId()) * 1140 ));
							totalCount = (int) (Integer.parseInt(available) / 1140 );
						}else if(formVO.getQuantityPerUnit().contains("BTL")){
							available = String.valueOf((Integer.parseInt(availableTemp)) - (Integer.parseInt(formVO.getConsumptionId()) * 1000 ));
							totalCount = (int) (Integer.parseInt(available) / 1000 );
						}						
					}else if(descriptionTemp.contains("PKT")){
						available = String.valueOf(Integer.parseInt(availableTemp) - Integer.parseInt(formVO.getConsumptionId()));
						totalCount = (int) Integer.parseInt(available);
					}else if(descriptionTemp.contains("PCS")){
						available = String.valueOf(Integer.parseInt(availableTemp) - Integer.parseInt(formVO.getConsumptionId()));
						totalCount = (int) Integer.parseInt(available);
					}else if(descriptionTemp.contains("TIN")){
						available = String.valueOf(Integer.parseInt(availableTemp) - Integer.parseInt(formVO.getConsumptionId()));
						totalCount = (int) Integer.parseInt(available);
				}
				
				pStmt5=con.prepareStatement(Query1);
				pStmt5.setString(1,availableid);			
				pStmt5.setString(2,available);
				pStmt5.setString(3,descriptionTemp);
				pStmt5.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt5.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt5.setString(6,formVO.getSellerId());
				pStmt5.setString(7,formVO.getSubSellerId());			
				pStmt5.setInt(8,totalCount);
				pStmt5.executeUpdate();
				
				pStmt6=con.prepareStatement(Query2);
				pStmt6.setString(1,consumptionid);			
				pStmt6.setString(2,formVO.getConsumptionId());
				pStmt6.setString(3,formVO.getQuantityPerUnit());
				pStmt6.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt6.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt6.setString(6,formVO.getSellerId());
				pStmt6.setString(7,formVO.getSubSellerId());
				pStmt6.executeUpdate();
				
				pStmt7 = con.prepareStatement(countQuery3);
				rs7 = pStmt7.executeQuery();
				
				while(rs7.next()){
					temp = rs7.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt8 = con.prepareStatement(idQuery3);
						pStmt8.setInt(1,i);
						rs8 = pStmt8.executeQuery();					
						
						if(!rs8.next())
							storeid = String.valueOf(i);
					}
				}
				
				pStmt10 = con.prepareStatement(Query);
				pStmt10.setString(1,formVO.getProductId());
				rs10 = pStmt10.executeQuery();
				
				while(rs10.next()){
					stockid = rs10.getString("stockid");
					requirementid = rs10.getString("requirementid");
					additionid = rs10.getString("additionid");
					creditnoteid = rs10.getString("creditnoteid");
				}
				
				
				pStmt11 = con.prepareStatement(Query4);
				pStmt11.setString(1,formVO.getProductId());
				rs11 = pStmt11.executeQuery();
				
				while(rs11.next()){
					lastSupplyDate = rs11.getString("lastmodified");
				}
				
				pStmt9=con.prepareStatement(Query3);
				pStmt9.setString(1,storeid);			
				pStmt9.setString(2,formVO.getSellerId());
				pStmt9.setString(3,formVO.getSubSellerId());		
				pStmt9.setString(4,stockid);
				pStmt9.setString(5,availableid);
				pStmt9.setString(6,consumptionid);
				pStmt9.setString(7,requirementid);
				pStmt9.setString(8,additionid);
				pStmt9.setString(9,creditnoteid);
				pStmt9.setString(10,formVO.getStatus());
				pStmt9.setString(11,"btn btn-default btn-on btn-sm");
				pStmt9.setString(12,"btn btn-default btn-off btn-sm active");
				pStmt9.setString(13,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt9.setString(14,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt9.setString(15,lastSupplyDate);
				pStmt9.executeUpdate();
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
				dbConnection.close(pStmt4, con);
				dbConnection.close(pStmt5, con);
				dbConnection.close(pStmt6, con);
				dbConnection.close(pStmt7, con);
				dbConnection.close(pStmt8, con);
				dbConnection.close(pStmt9, con);
				dbConnection.close(pStmt10, con);
				dbConnection.close(pStmt11, con);				
			}
		}
		
		public void updateKitchenProductRequirementAfterSingleEntry(ProductFormVO formVO) throws SQLException, Exception {

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			ResultSet rs1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			
			int temp = 0;
			
			String availableid = null;
			String consumptionid = null;
			String requirementid = null;
			String additionid = null;
			String lastSupplyDate = null;
			String creditnoteid = null;
			String stockid = null;
			String storeid = null;
			
			String Query="SELECT stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ) ORDER BY lastmodified DESC LIMIT 1  ";
			String Query1="INSERT INTO inventory_kitchen_stock_requirement ( requirementid, requirement, description, created, lastmodified, sellerid, subsellerid ) VALUES (?,?,?,?,?,?,?) ";	 		
			String Query2="INSERT INTO inventory_kitchen_store ( storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			String Query3="SELECT addition, description, lastmodified from inventory_kitchen_stock_addition where additionid = ( SELECT additionid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";
			
			String countQuery1 = "SELECT COUNT(*) FROM inventory_kitchen_stock_requirement "; 
			String idQuery1 = "SELECT requirementid FROM inventory_kitchen_stock_requirement where requirementid=? ";
			
			String countQuery2 = "SELECT COUNT(*) FROM inventory_kitchen_store "; 
			String idQuery2 = "SELECT storeid FROM inventory_kitchen_store where storeid=? ";
			
			try{
				con = dbConnection.getConnection();
				
				pStmt1 = con.prepareStatement(countQuery1);
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					temp = rs1.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt2 = con.prepareStatement(idQuery1);
						pStmt2.setInt(1,i);
						rs2 = pStmt2.executeQuery();					
						
						if(!rs2.next())
							requirementid = String.valueOf(i);
					}
				}
								
				pStmt3=con.prepareStatement(Query1);
				pStmt3.setString(1,requirementid);			
				pStmt3.setString(2,formVO.getRequirementId());
				pStmt3.setString(3,formVO.getQuantityPerUnit());
				pStmt3.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt3.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt3.setString(6,formVO.getSellerId());
				pStmt3.setString(7,formVO.getSubSellerId());
				pStmt3.executeUpdate();
				
				pStmt4 = con.prepareStatement(countQuery2);
				rs4 = pStmt4.executeQuery();
				
				while(rs4.next()){
					temp = rs4.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt5 = con.prepareStatement(idQuery2);
						pStmt5.setInt(1,i);
						rs5 = pStmt5.executeQuery();					
						
						if(!rs5.next())
							storeid = String.valueOf(i);
					}
				}
				
				pStmt6 = con.prepareStatement(Query);
				pStmt6.setString(1,formVO.getProductId());
				rs6 = pStmt6.executeQuery();
				
				while(rs6.next()){
					stockid = rs6.getString("stockid");
					availableid = rs6.getString("availableid");
					consumptionid = rs6.getString("consumptionid");
					additionid = rs6.getString("additionid");					
					creditnoteid = rs6.getString("creditnoteid");
				}
								
				pStmt7 = con.prepareStatement(Query3);
				pStmt7.setString(1,formVO.getProductId());
				rs7 = pStmt7.executeQuery();
				
				while(rs7.next()){
					lastSupplyDate = rs7.getString("lastmodified");
				}
				
				pStmt8=con.prepareStatement(Query2);
				pStmt8.setString(1,storeid);			
				pStmt8.setString(2,formVO.getSellerId());
				pStmt8.setString(3,formVO.getSubSellerId());		
				pStmt8.setString(4,stockid);
				pStmt8.setString(5,availableid);
				pStmt8.setString(6,consumptionid);
				pStmt8.setString(7,requirementid);
				pStmt8.setString(8,additionid);
				pStmt8.setString(9,creditnoteid);
				pStmt8.setString(10,formVO.getStatus());
				pStmt8.setString(11,"btn btn-default btn-on btn-sm");
				pStmt8.setString(12,"btn btn-default btn-off btn-sm active");
				pStmt8.setString(13,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt8.setString(14,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt8.setString(15,lastSupplyDate);
				pStmt8.executeUpdate();
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
			}
		}
		
		public void updateKitchenProductRequirementAfterMultipleEntry(ProductFormVO formVO) throws SQLException, Exception {

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			ResultSet rs1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			
			int temp = 0;
			
			String availableid = null;
			String consumptionid = null;
			String requirementid = null;
			String additionid = null;
			String lastSupplyDate = null;
			String creditnoteid = null;
			String stockid = null;
			String storeid = null;
			
			String Query="SELECT stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ) ORDER BY lastmodified DESC LIMIT 1  ";
			String Query1="INSERT INTO inventory_kitchen_stock_requirement ( requirementid, requirement, description, created, lastmodified, sellerid, subsellerid ) VALUES (?,?,?,?,?,?,?) ";	 		
			String Query2="INSERT INTO inventory_kitchen_store ( storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			String Query3="SELECT addition, description, lastmodified from inventory_kitchen_stock_addition where additionid = ( SELECT additionid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";

			String countQuery1 = "SELECT COUNT(*) FROM inventory_kitchen_stock_requirement "; 
			String idQuery1 = "SELECT requirementid FROM inventory_kitchen_stock_requirement where requirementid=? ";
			
			String countQuery2 = "SELECT COUNT(*) FROM inventory_kitchen_store "; 
			String idQuery2 = "SELECT storeid FROM inventory_kitchen_store where storeid=? ";
			
			try{
				con = dbConnection.getConnection();
				
				pStmt1 = con.prepareStatement(countQuery1);
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					temp = rs1.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt2 = con.prepareStatement(idQuery1);
						pStmt2.setInt(1,i);
						rs2 = pStmt2.executeQuery();					
						
						if(!rs2.next())
							requirementid = String.valueOf(i);
					}
				}
									
				pStmt3=con.prepareStatement(Query1);
				pStmt3.setString(1,requirementid);			
				pStmt3.setString(2,formVO.getRequirementId());
				pStmt3.setString(3,formVO.getQuantityPerUnit());
				pStmt3.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt3.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt3.setString(6,formVO.getSellerId());
				pStmt3.setString(7,formVO.getSubSellerId());
				pStmt3.executeUpdate();
				
				pStmt4 = con.prepareStatement(countQuery2);
				rs4 = pStmt4.executeQuery();
				
				while(rs4.next()){
					temp = rs4.getInt(1);
					
					for(int i=1; i<=temp+1; i++){
						pStmt5 = con.prepareStatement(idQuery2);
						pStmt5.setInt(1,i);
						rs5 = pStmt5.executeQuery();					
						
						if(!rs5.next())
							storeid = String.valueOf(i);
					}
				}
				
				pStmt6 = con.prepareStatement(Query);
				pStmt6.setString(1,formVO.getProductId());
				rs6 = pStmt6.executeQuery();
				
				while(rs6.next()){
					stockid = rs6.getString("stockid");
					availableid = rs6.getString("availableid");
					consumptionid = rs6.getString("consumptionid");
					additionid = rs6.getString("additionid");					
					creditnoteid = rs6.getString("creditnoteid");
				}
								
				pStmt7 = con.prepareStatement(Query3);
				pStmt7.setString(1,formVO.getProductId());
				rs7 = pStmt7.executeQuery();
				
				while(rs7.next()){
					lastSupplyDate = rs7.getString("lastmodified");
				}
				
				pStmt8=con.prepareStatement(Query2);
				pStmt8.setString(1,storeid);			
				pStmt8.setString(2,formVO.getSellerId());
				pStmt8.setString(3,formVO.getSubSellerId());		
				pStmt8.setString(4,stockid);
				pStmt8.setString(5,availableid);
				pStmt8.setString(6,consumptionid);
				pStmt8.setString(7,requirementid);
				pStmt8.setString(8,additionid);
				pStmt8.setString(9,creditnoteid);
				pStmt8.setString(10,formVO.getStatus());
				pStmt8.setString(11,"btn btn-default btn-on btn-sm");
				pStmt8.setString(12,"btn btn-default btn-off btn-sm active");
				pStmt8.setString(13,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt8.setString(14,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt8.setString(15,lastSupplyDate);
				pStmt8.executeUpdate();
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
			}
		}
	
		//--------------------------------Product Requirements----------------------------------------------------------

		@Override
		public List<ProductFormVO> getKitchenProductRequirementIndexMap(ProductFormVO formVO)
				throws SQLException, Exception {
			List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			ResultSet rs9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			PreparedStatement pStmt12 = null;
			ResultSet rs12 = null;
			PreparedStatement pStmt13 = null;
			ResultSet rs13 = null;
			
			int procuctCount = 0;
			String tempStockId = null;
			
			String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
			String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
			String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
			String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
			String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
			String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
			String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
			String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
			String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
			String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";

			try{			
				con = dbConnection.getConnection();
				
				pStmt12 = con.prepareStatement(Query8);
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					procuctCount = rs12.getInt(1);
				}
				
				int tempCount=procuctCount+1;
				List<String> countList = new ArrayList<String>();
				
				for(int i=1; i<tempCount; i++){
					pStmt13 = con.prepareStatement(Query9);
					pStmt13.setInt(1, i);
					rs13 = pStmt13.executeQuery();
					
					while(rs13.next()){
						int j=1;
						if(i<6){
							if(j==1){
								tempStockId=rs13.getString("stockid");
								j++;
							}
							else{
								tempStockId=tempStockId +","+ rs13.getString("stockid");
								j++;
							}
						}
					}
					
					String []str = null;
					
					if(tempStockId!=null){
						str = tempStockId.split(",");				
						for(int m=0;m<str.length;m++){
							if(str[m]!=null && str[m]!="")
								countList.add(str[m]);
						}
						if(countList.size()<procuctCount){
							tempCount++;
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
				
				String Query="SELECT DISTINCT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+id+") ORDER BY stockid ASC, lastmodified DESC ";

				String count = null;
				boolean available = false;
				pStmt2 = con.prepareStatement(Query);
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					available=true;
					if(rs2.getString("stockid").equalsIgnoreCase(count))
						continue;
					
					ProductFormVO formVOO = new ProductFormVO();
					
					pStmt10 = con.prepareStatement(Query0);
					pStmt10.setString(1, rs2.getString("storeid"));
					rs10 = pStmt10.executeQuery();			
					while(rs10.next()){					
						formVOO.setProductId(rs10.getString("productid"));
					}
										
					pStmt9 = con.prepareStatement(Query1);
					pStmt9.setString(1, formVOO.getProductId());
					rs9 = pStmt9.executeQuery();			
					while(rs9.next()){
						formVOO.setProductName(rs9.getString("name"));
						formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
						formVOO.setID(rs9.getString("ID"));
					}
					
					pStmt4 = con.prepareStatement(Query2);
					pStmt4.setString(1, rs2.getString("availableid"));
					rs4 = pStmt4.executeQuery();			
					while(rs4.next()){
						formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
						
						pStmt11 = con.prepareStatement(Query7);
						pStmt11.setString(1, rs2.getString("stockid"));
						rs11 = pStmt11.executeQuery();	
						
						while(rs11.next()){
							formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
							formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
						}								
					}
					
					pStmt5 = con.prepareStatement(Query3);
					pStmt5.setString(1, rs2.getString("consumptionid"));
					rs5 = pStmt5.executeQuery();			
					while(rs5.next()){
						formVOO.setConsumptionId(rs5.getString("consumption"));
					}
					
					pStmt6 = con.prepareStatement(Query4);
					pStmt6.setString(1, rs2.getString("requirementid"));
					rs6 = pStmt6.executeQuery();			
					while(rs6.next()){
						formVOO.setRequirementId(rs6.getString("requirement"));
					}
					
					pStmt7 = con.prepareStatement(Query5);
					pStmt7.setString(1, rs2.getString("additionid"));
					rs7 = pStmt7.executeQuery();			
					while(rs7.next()){
						formVOO.setAdditionId(rs7.getString("addition"));
					}
					
					pStmt8 = con.prepareStatement(Query6);
					pStmt8.setString(1, rs2.getString("creditnoteid"));
					rs8 = pStmt8.executeQuery();			
					while(rs8.next()){
						formVOO.setCreditnoteId(rs8.getString("creditnote"));
					}
					
					if(rs2.getString("status").contains("pending"))					
						formVOO.setStatus("Pending");	
					else if(rs2.getString("status").contains("requested"))					
						formVOO.setStatus("Requested");
					else if(rs2.getString("status").contains("supplied"))					
						formVOO.setStatus("Supplied");	
					
					formVOO.setStatusOn(rs2.getString("statuson"));
					formVOO.setStatusOff(rs2.getString("statusoff"));
					formVOO.setCreatedDate(rs2.getString("created"));
					formVOO.setLastModifiedDate(rs2.getString("lastModified"));
					formVOO.setSupplyDate(rs2.getString("supplydate"));
				
					productList.add(formVOO);
					count = rs2.getString("stockid");
				}if(!available){
										
						ProductFormVO formVOO = new ProductFormVO();
						
						formVOO.setProductId("Unavailable");
						formVOO.setID("----");
						formVOO.setProductName("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setAvailableId("----");
						formVOO.setLastCount("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setConsumptionId("----");					
						formVOO.setRequirementId("----");						
						formVOO.setAdditionId("----");											
						formVOO.setCreditnoteId("----");																		
						formVOO.setStatus("----");
						formVOO.setStatusOn("----");
						formVOO.setStatusOff("----");
						formVOO.setCreatedDate("----");
						formVOO.setLastModifiedDate("----");
						formVOO.setSupplyDate("----");

						productList.add(formVOO);					
				}
				
				Collections.sort(productList, productComparator.productComparator);
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
				dbConnection.close(pStmt13, con);
			}
			
			return productList;			
		}

		@Override
		public List<ProductFormVO> getKitchenProductRequirementIndexMapByProductName(ProductFormVO formVO)
				throws SQLException, Exception {
			List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			ResultSet rs9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			PreparedStatement pStmt12 = null;
			ResultSet rs12 = null;
			PreparedStatement pStmt13 = null;
			ResultSet rs13 = null;
			
			int procuctCount = 0;
			String tempStockId = null;
			
			String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
			String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
			String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
			String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
			String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
			String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
			String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
			String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
			String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
			String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ( SELECT productid from inventory_kitchen_product where name = ? ) ORDER BY lastmodified DESC LIMIT 1 ";

			try{			
				con = dbConnection.getConnection();
				
				pStmt12 = con.prepareStatement(Query8);
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					procuctCount = rs12.getInt(1);
				}
				
				for(int i=1; i<procuctCount+1; i++){
					pStmt13 = con.prepareStatement(Query9);
					pStmt13.setString(1, formVO.getProductName());
					rs13 = pStmt13.executeQuery();
					
					while(rs13.next()){
						if(i==1)
							tempStockId=rs13.getString("stockid");
						else
							tempStockId=tempStockId +","+ rs13.getString("stockid");
					}			
				}
				
				String Query="SELECT DISTINCT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") ORDER BY stockid ASC, lastmodified DESC ";

				String count = null;
				boolean available = false;
				pStmt2 = con.prepareStatement(Query);
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					available=true;
					if(rs2.getString("stockid").equalsIgnoreCase(count))
						continue;
					
					ProductFormVO formVOO = new ProductFormVO();
					
					pStmt10 = con.prepareStatement(Query0);
					pStmt10.setString(1, rs2.getString("storeid"));
					rs10 = pStmt10.executeQuery();			
					while(rs10.next()){					
						formVOO.setProductId(rs10.getString("productid"));
					}
										
					pStmt9 = con.prepareStatement(Query1);
					pStmt9.setString(1, formVOO.getProductId());
					rs9 = pStmt9.executeQuery();			
					while(rs9.next()){
						formVOO.setProductName(rs9.getString("name"));
						formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
						formVOO.setID(rs9.getString("ID"));
					}
					
					pStmt4 = con.prepareStatement(Query2);
					pStmt4.setString(1, rs2.getString("availableid"));
					rs4 = pStmt4.executeQuery();			
					while(rs4.next()){
						formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
						
						pStmt11 = con.prepareStatement(Query7);
						pStmt11.setString(1, rs2.getString("stockid"));
						rs11 = pStmt11.executeQuery();	
						
						while(rs11.next()){
							formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
							formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
						}								
					}
					
					pStmt5 = con.prepareStatement(Query3);
					pStmt5.setString(1, rs2.getString("consumptionid"));
					rs5 = pStmt5.executeQuery();			
					while(rs5.next()){
						formVOO.setConsumptionId(rs5.getString("consumption"));
					}
					
					pStmt6 = con.prepareStatement(Query4);
					pStmt6.setString(1, rs2.getString("requirementid"));
					rs6 = pStmt6.executeQuery();			
					while(rs6.next()){
						formVOO.setRequirementId(rs6.getString("requirement"));
					}
					
					pStmt7 = con.prepareStatement(Query5);
					pStmt7.setString(1, rs2.getString("additionid"));
					rs7 = pStmt7.executeQuery();			
					while(rs7.next()){
						formVOO.setAdditionId(rs7.getString("addition"));
					}
					
					pStmt8 = con.prepareStatement(Query6);
					pStmt8.setString(1, rs2.getString("creditnoteid"));
					rs8 = pStmt8.executeQuery();			
					while(rs8.next()){
						formVOO.setCreditnoteId(rs8.getString("creditnote"));
					}
					
					if(rs2.getString("status").contains("pending"))					
						formVOO.setStatus("Pending");	
					else if(rs2.getString("status").contains("requested"))					
						formVOO.setStatus("Requested");
					else if(rs2.getString("status").contains("supplied"))					
						formVOO.setStatus("Supplied");	
					
					formVOO.setStatusOn(rs2.getString("statuson"));
					formVOO.setStatusOff(rs2.getString("statusoff"));
					formVOO.setCreatedDate(rs2.getString("created"));
					formVOO.setLastModifiedDate(rs2.getString("lastModified"));
					formVOO.setSupplyDate(rs2.getString("supplydate"));
				
					productList.add(formVOO);
					count = rs2.getString("stockid");
				}if(!available){					
						ProductFormVO formVOO = new ProductFormVO();
						
						formVOO.setProductId("Unavailable");
						formVOO.setID("ID");
						formVOO.setProductName("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setAvailableId("----");
						formVOO.setLastCount("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setConsumptionId("----");					
						formVOO.setRequirementId("----");						
						formVOO.setAdditionId("----");											
						formVOO.setCreditnoteId("----");																		
						formVOO.setStatus("----");
						formVOO.setStatusOn("----");
						formVOO.setStatusOff("----");
						formVOO.setCreatedDate("----");
						formVOO.setLastModifiedDate("----");
						formVOO.setSupplyDate("----");

						productList.add(formVOO);					
				}
				
				Collections.sort(productList, productComparator.productComparator);
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
				dbConnection.close(pStmt13, con);
			}
			
			return productList;			
		}

		@Override
		public List<ProductFormVO> getKitchenProductRequirementIndexMapByProductId(ProductFormVO formVO)
				throws SQLException, Exception {
			List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			ResultSet rs9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			PreparedStatement pStmt12 = null;
			ResultSet rs12 = null;
			PreparedStatement pStmt13 = null;
			ResultSet rs13 = null;
			
			int procuctCount = 0;
			String tempStockId = null;
			
			String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
			String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
			String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
			String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
			String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
			String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
			String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
			String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
			String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
			String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ( SELECT productid from inventory_kitchen_product where productid = ? ) ORDER BY lastmodified DESC LIMIT 1 ";

			try{			
				con = dbConnection.getConnection();
				
				pStmt12 = con.prepareStatement(Query8);
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					procuctCount = rs12.getInt(1);
				}
				
				for(int i=1; i<procuctCount+1; i++){
					pStmt13 = con.prepareStatement(Query9);
					pStmt13.setString(1, formVO.getProductId());
					rs13 = pStmt13.executeQuery();
					
					while(rs13.next()){
						if(i==1)
							tempStockId=rs13.getString("stockid");
						else
							tempStockId=tempStockId +","+ rs13.getString("stockid");
					}			
				}
				
				String Query="SELECT DISTINCT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") ORDER BY stockid ASC, lastmodified DESC ";

				String count = null;
				boolean available = false;
				pStmt2 = con.prepareStatement(Query);
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					available=true;
					if(rs2.getString("stockid").equalsIgnoreCase(count))
						continue;
					
					ProductFormVO formVOO = new ProductFormVO();
					
					pStmt10 = con.prepareStatement(Query0);
					pStmt10.setString(1, rs2.getString("storeid"));
					rs10 = pStmt10.executeQuery();			
					while(rs10.next()){					
						formVOO.setProductId(rs10.getString("productid"));
					}
										
					pStmt9 = con.prepareStatement(Query1);
					pStmt9.setString(1, formVOO.getProductId());
					rs9 = pStmt9.executeQuery();			
					while(rs9.next()){
						formVOO.setProductName(rs9.getString("name"));
						formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
						formVOO.setID(rs9.getString("ID"));
					}
					
					pStmt4 = con.prepareStatement(Query2);
					pStmt4.setString(1, rs2.getString("availableid"));
					rs4 = pStmt4.executeQuery();			
					while(rs4.next()){
						formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
						
						pStmt11 = con.prepareStatement(Query7);
						pStmt11.setString(1, rs2.getString("stockid"));
						rs11 = pStmt11.executeQuery();	
						
						while(rs11.next()){
							formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
							formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
						}								
					}
					
					pStmt5 = con.prepareStatement(Query3);
					pStmt5.setString(1, rs2.getString("consumptionid"));
					rs5 = pStmt5.executeQuery();			
					while(rs5.next()){
						formVOO.setConsumptionId(rs5.getString("consumption"));
					}
					
					pStmt6 = con.prepareStatement(Query4);
					pStmt6.setString(1, rs2.getString("requirementid"));
					rs6 = pStmt6.executeQuery();			
					while(rs6.next()){
						formVOO.setRequirementId(rs6.getString("requirement"));
					}
					
					pStmt7 = con.prepareStatement(Query5);
					pStmt7.setString(1, rs2.getString("additionid"));
					rs7 = pStmt7.executeQuery();			
					while(rs7.next()){
						formVOO.setAdditionId(rs7.getString("addition"));
					}
					
					pStmt8 = con.prepareStatement(Query6);
					pStmt8.setString(1, rs2.getString("creditnoteid"));
					rs8 = pStmt8.executeQuery();			
					while(rs8.next()){
						formVOO.setCreditnoteId(rs8.getString("creditnote"));
					}
					
					if(rs2.getString("status").contains("pending"))					
						formVOO.setStatus("Pending");	
					else if(rs2.getString("status").contains("requested"))					
						formVOO.setStatus("Requested");
					else if(rs2.getString("status").contains("supplied"))					
						formVOO.setStatus("Supplied");	
					
					formVOO.setStatusOn(rs2.getString("statuson"));
					formVOO.setStatusOff(rs2.getString("statusoff"));
					formVOO.setCreatedDate(rs2.getString("created"));
					formVOO.setLastModifiedDate(rs2.getString("lastModified"));
					formVOO.setSupplyDate(rs2.getString("supplydate"));
				
					productList.add(formVOO);
					count = rs2.getString("stockid");
				}if(!available){					
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setID("Unavailable");
						formVOO.setProductId("Unavailable");						
						formVOO.setProductName("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setAvailableId("----");
						formVOO.setLastCount("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setConsumptionId("----");					
						formVOO.setRequirementId("----");						
						formVOO.setAdditionId("----");											
						formVOO.setCreditnoteId("----");																		
						formVOO.setStatus("----");
						formVOO.setStatusOn("----");
						formVOO.setStatusOff("----");
						formVOO.setCreatedDate("----");
						formVOO.setLastModifiedDate("----");
						formVOO.setSupplyDate("----");

						productList.add(formVOO);					
				}
				
				Collections.sort(productList, productComparator.productComparator);
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
				dbConnection.close(pStmt13, con);
			}
			
			return productList;			
		}
		
		@Override
		public List<ProductFormVO> getKitchenProductRequirementIndexMapByCreatedDate(ProductFormVO formVO)
				throws SQLException, Exception {
			List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			ResultSet rs9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			PreparedStatement pStmt12 = null;
			ResultSet rs12 = null;
			PreparedStatement pStmt13 = null;
			ResultSet rs13 = null;
			PreparedStatement pStmt14 = null;
			
			int procuctCount = 0;
			String tempStockId = null;
			boolean available=false;
			String count=null;
			
			String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
			String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
			String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
			String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
			String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
			String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
			String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
			String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
			String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
			String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";

			try{			
				con = dbConnection.getConnection();
				
				pStmt12 = con.prepareStatement(Query8);
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					procuctCount = rs12.getInt(1);
				}
				
				for(int i=1; i<procuctCount+1; i++){
					pStmt13 = con.prepareStatement(Query9);
					pStmt13.setInt(1, i);
					rs13 = pStmt13.executeQuery();
					
					while(rs13.next()){
						if(i==1)
							tempStockId=rs13.getString("stockid");
						else
							tempStockId=tempStockId +","+ rs13.getString("stockid");
					}			
				}
				
				String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") AND created >= '"+formVO.getFromDate()+"' AND created <='"+formVO.getToDate()+"' ORDER BY stockid ASC, lastmodified DESC ";
				
				pStmt2 = con.prepareStatement(Query);
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					available = true;
					if(rs2.getString("stockid").equalsIgnoreCase(count))
						continue;
					
					ProductFormVO formVOO = new ProductFormVO();
					
					pStmt10 = con.prepareStatement(Query0);
					pStmt10.setString(1, rs2.getString("storeid"));
					rs10 = pStmt10.executeQuery();			
					while(rs10.next()){					
						formVOO.setProductId(rs10.getString("productid"));
					}
										
					pStmt9 = con.prepareStatement(Query1);
					pStmt9.setString(1, formVOO.getProductId());
					rs9 = pStmt9.executeQuery();			
					while(rs9.next()){
						formVOO.setProductName(rs9.getString("name"));
						formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
						formVOO.setID(rs9.getString("ID"));
					}
					
					pStmt4 = con.prepareStatement(Query2);
					pStmt4.setString(1, rs2.getString("availableid"));
					rs4 = pStmt4.executeQuery();			
					while(rs4.next()){
						formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
						
						pStmt11 = con.prepareStatement(Query7);
						pStmt11.setString(1, rs2.getString("stockid"));
						rs11 = pStmt11.executeQuery();	
						
						while(rs11.next()){
							formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
							formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
						}								
					}
					
					pStmt5 = con.prepareStatement(Query3);
					pStmt5.setString(1, rs2.getString("consumptionid"));
					rs5 = pStmt5.executeQuery();			
					while(rs5.next()){
						formVOO.setConsumptionId(rs5.getString("consumption"));
					}
					
					pStmt6 = con.prepareStatement(Query4);
					pStmt6.setString(1, rs2.getString("requirementid"));
					rs6 = pStmt6.executeQuery();			
					while(rs6.next()){
						formVOO.setRequirementId(rs6.getString("requirement"));
					}
					
					pStmt7 = con.prepareStatement(Query5);
					pStmt7.setString(1, rs2.getString("additionid"));
					rs7 = pStmt7.executeQuery();			
					while(rs7.next()){
						formVOO.setAdditionId(rs7.getString("addition"));
					}
					
					pStmt8 = con.prepareStatement(Query6);
					pStmt8.setString(1, rs2.getString("creditnoteid"));
					rs8 = pStmt8.executeQuery();			
					while(rs8.next()){
						formVOO.setCreditnoteId(rs8.getString("creditnote"));
					}
					
					if(rs2.getString("status").contains("pending"))					
						formVOO.setStatus("Pending");	
					else if(rs2.getString("status").contains("requested"))					
						formVOO.setStatus("Requested");
					else if(rs2.getString("status").contains("supplied"))					
						formVOO.setStatus("Supplied");	
					
					formVOO.setStatusOn(rs2.getString("statuson"));
					formVOO.setStatusOff(rs2.getString("statusoff"));
					formVOO.setCreatedDate(rs2.getString("created"));
					formVOO.setLastModifiedDate(rs2.getString("lastModified"));
					formVOO.setSupplyDate(rs2.getString("supplydate"));

					productList.add(formVOO);
					count=rs2.getString("stockid");
				}
				
				if(!available){					
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId("Unavailable");	
						formVOO.setID("Unavailable");
						formVOO.setProductName("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setAvailableId("----");
						formVOO.setLastCount("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setConsumptionId("----");					
						formVOO.setRequirementId("----");						
						formVOO.setAdditionId("----");											
						formVOO.setCreditnoteId("----");																		
						formVOO.setStatus("----");
						formVOO.setStatusOn("----");
						formVOO.setStatusOff("----");
						formVOO.setCreatedDate("----");
						formVOO.setLastModifiedDate("----");
						formVOO.setSupplyDate("----");

						productList.add(formVOO);
				}
				
				Collections.sort(productList, productComparator.productComparator);
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
				dbConnection.close(pStmt13, con);
				dbConnection.close(pStmt14, con);
			}
			
			return productList;			
		}

		@Override
		public List<ProductFormVO> getKitchenProductRequirementIndexMapByPagination(ProductFormVO formVO)
				throws SQLException, Exception {
			List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			ResultSet rs9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			PreparedStatement pStmt12 = null;
			ResultSet rs12 = null;
			PreparedStatement pStmt13 = null;
			ResultSet rs13 = null;
			
			int procuctCount = 0;
			String tempStockId = null;
			String count=null;
			boolean available=false;
			
			String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
			String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
			String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
			String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
			String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
			String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
			String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
			String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
			String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
			String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";

			try{			
				con = dbConnection.getConnection();
				
				pStmt12 = con.prepareStatement(Query8);
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					procuctCount = rs12.getInt(1);
				}
				
				for(int i=1; i<procuctCount+1; i++){
					pStmt13 = con.prepareStatement(Query9);
					pStmt13.setInt(1, i);
					rs13 = pStmt13.executeQuery();
					
					while(rs13.next()){
						if(i==1)
							tempStockId=rs13.getString("stockid");
						else
							tempStockId=tempStockId +","+ rs13.getString("stockid");
					}			
				}
				
				String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid BETWEEN ? AND ? ORDER BY stockid ASC, lastmodified DESC ";
				String Query10="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") ORDER BY stockid ASC, lastmodified DESC ";	
				
				if((Integer.parseInt(formVO.getPageFrom()) < 0) || 
						(Integer.parseInt(formVO.getPageFrom()) >= 0) && (Integer.parseInt(formVO.getPageTo()) > tempStockId.split(",").length)){
					pStmt2 = con.prepareStatement(Query10);
				}else{	
					pStmt2 = con.prepareStatement(Query);
					pStmt2.setInt(1, Integer.parseInt(tempStockId.split(",")[Integer.parseInt(formVO.getPageFrom())-1]));
					pStmt2.setInt(2, Integer.parseInt(tempStockId.split(",")[Integer.parseInt(formVO.getPageTo())-1]));
				}
				
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					available=true;
					if(rs2.getString("stockid").equalsIgnoreCase(count))
						continue;
					
					ProductFormVO formVOO = new ProductFormVO();
					
					pStmt10 = con.prepareStatement(Query0);
					pStmt10.setString(1, rs2.getString("storeid"));
					rs10 = pStmt10.executeQuery();			
					while(rs10.next()){					
						formVOO.setProductId(rs10.getString("productid"));
					}
										
					pStmt9 = con.prepareStatement(Query1);
					pStmt9.setString(1, formVOO.getProductId());
					rs9 = pStmt9.executeQuery();			
					while(rs9.next()){
						formVOO.setProductName(rs9.getString("name"));
						formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
						formVOO.setID(rs9.getString("ID"));
					}
					
					pStmt4 = con.prepareStatement(Query2);
					pStmt4.setString(1, rs2.getString("availableid"));
					rs4 = pStmt4.executeQuery();			
					while(rs4.next()){
						formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
						
						pStmt11 = con.prepareStatement(Query7);
						pStmt11.setString(1, rs2.getString("stockid"));
						rs11 = pStmt11.executeQuery();	
						
						while(rs11.next()){
							formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
							formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
						}								
					}
					
					pStmt5 = con.prepareStatement(Query3);
					pStmt5.setString(1, rs2.getString("consumptionid"));
					rs5 = pStmt5.executeQuery();			
					while(rs5.next()){
						formVOO.setConsumptionId(rs5.getString("consumption"));
					}
					
					pStmt6 = con.prepareStatement(Query4);
					pStmt6.setString(1, rs2.getString("requirementid"));
					rs6 = pStmt6.executeQuery();			
					while(rs6.next()){
						formVOO.setRequirementId(rs6.getString("requirement"));
					}
					
					pStmt7 = con.prepareStatement(Query5);
					pStmt7.setString(1, rs2.getString("additionid"));
					rs7 = pStmt7.executeQuery();			
					while(rs7.next()){
						formVOO.setAdditionId(rs7.getString("addition"));
					}
					
					pStmt8 = con.prepareStatement(Query6);
					pStmt8.setString(1, rs2.getString("creditnoteid"));
					rs8 = pStmt8.executeQuery();			
					while(rs8.next()){
						formVOO.setCreditnoteId(rs8.getString("creditnote"));
					}
					
					if(rs2.getString("status").contains("pending"))					
						formVOO.setStatus("Pending");	
					else if(rs2.getString("status").contains("requested"))					
						formVOO.setStatus("Requested");
					else if(rs2.getString("status").contains("supplied"))					
						formVOO.setStatus("Supplied");	
					
					formVOO.setStatusOn(rs2.getString("statuson"));
					formVOO.setStatusOff(rs2.getString("statusoff"));
					formVOO.setCreatedDate(rs2.getString("created"));
					formVOO.setLastModifiedDate(rs2.getString("lastModified"));
					formVOO.setSupplyDate(rs2.getString("supplydate"));

					productList.add(formVOO);
					count=rs2.getString("stockid");
				}if(!available){										
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId("Unavailable");
						formVOO.setID("Unavailable");
						formVOO.setProductName("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setAvailableId("----");
						formVOO.setLastCount("----");
						formVOO.setQuantityPerUnit("----");						
						formVOO.setConsumptionId("----");					
						formVOO.setRequirementId("----");						
						formVOO.setAdditionId("----");											
						formVOO.setCreditnoteId("----");																		
						formVOO.setStatus("----");
						formVOO.setStatusOn("----");
						formVOO.setStatusOff("----");
						formVOO.setCreatedDate("----");
						formVOO.setLastModifiedDate("----");
						formVOO.setSupplyDate("----");

						productList.add(formVOO);
				}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
		}
		
		return productList;			
		}

		@Override
		public List<ProductFormVO> getKitchenProductRequirementIndexMapByRecords(ProductFormVO formVO)
				throws SQLException, Exception {
			List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			ResultSet rs9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			PreparedStatement pStmt12 = null;
			ResultSet rs12 = null;
			PreparedStatement pStmt13 = null;
			ResultSet rs13 = null;
			
			int procuctCount = 0;
			String tempStockId = null;
			int count = 0;
			boolean available=false;
			String stockCount = null;
								
			String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
			String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
			String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
			String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
			String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
			String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
			String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
			String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
			String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
			String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";

			try{			
				con = dbConnection.getConnection();
				
				pStmt12 = con.prepareStatement(Query8);
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					procuctCount = rs12.getInt(1);
				}
				
				if(procuctCount>4 && procuctCount<16){
					if(formVO.getLimit().equalsIgnoreCase("1")){
						count = 5;
					}else if(formVO.getLimit().equalsIgnoreCase("2")){
						count = 10;
					}else{
						count = 15;
					}
				}else{
					count=procuctCount;
				}
				
				for(int i=1; i<count+1; i++){
					pStmt13 = con.prepareStatement(Query9);
					pStmt13.setInt(1, i);
					rs13 = pStmt13.executeQuery();
					
					while(rs13.next()){
						if(i==1)
							tempStockId=rs13.getString("stockid");
						else
							tempStockId=tempStockId +","+ rs13.getString("stockid");
					}			
				}
				
				String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") ORDER BY stockid ASC, lastmodified DESC ";
				
				pStmt2 = con.prepareStatement(Query);
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					available=true;
					if(rs2.getString("stockid").equalsIgnoreCase(stockCount))
						continue;
					
					ProductFormVO formVOO = new ProductFormVO();
					pStmt10 = con.prepareStatement(Query0);
					pStmt10.setString(1, rs2.getString("storeid"));
					rs10 = pStmt10.executeQuery();			
					while(rs10.next()){					
						formVOO.setProductId(rs10.getString("productid"));
					}
										
					pStmt9 = con.prepareStatement(Query1);
					pStmt9.setString(1, formVOO.getProductId());
					rs9 = pStmt9.executeQuery();			
					while(rs9.next()){
						formVOO.setProductName(rs9.getString("name"));
						formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
						formVOO.setID(rs9.getString("ID"));
					}
					
					pStmt4 = con.prepareStatement(Query2);
					pStmt4.setString(1, rs2.getString("availableid"));
					rs4 = pStmt4.executeQuery();			
					while(rs4.next()){
						formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
						
						pStmt11 = con.prepareStatement(Query7);
						pStmt11.setString(1, rs2.getString("stockid"));
						rs11 = pStmt11.executeQuery();	
						
						while(rs11.next()){
							formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
							formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
						}								
					}
					
					pStmt5 = con.prepareStatement(Query3);
					pStmt5.setString(1, rs2.getString("consumptionid"));
					rs5 = pStmt5.executeQuery();			
					while(rs5.next()){
						formVOO.setConsumptionId(rs5.getString("consumption"));
					}
					
					pStmt6 = con.prepareStatement(Query4);
					pStmt6.setString(1, rs2.getString("requirementid"));
					rs6 = pStmt6.executeQuery();			
					while(rs6.next()){
						formVOO.setRequirementId(rs6.getString("requirement"));
					}
					
					pStmt7 = con.prepareStatement(Query5);
					pStmt7.setString(1, rs2.getString("additionid"));
					rs7 = pStmt7.executeQuery();			
					while(rs7.next()){
						formVOO.setAdditionId(rs7.getString("addition"));
					}
					
					pStmt8 = con.prepareStatement(Query6);
					pStmt8.setString(1, rs2.getString("creditnoteid"));
					rs8 = pStmt8.executeQuery();			
					while(rs8.next()){
						formVOO.setCreditnoteId(rs8.getString("creditnote"));
					}
					
					if(rs2.getString("status").contains("pending"))					
						formVOO.setStatus("Pending");	
					else if(rs2.getString("status").contains("requested"))					
						formVOO.setStatus("Requested");
					else if(rs2.getString("status").contains("supplied"))					
						formVOO.setStatus("Supplied");	
					
					formVOO.setStatusOn(rs2.getString("statuson"));
					formVOO.setStatusOff(rs2.getString("statusoff"));
					formVOO.setCreatedDate(rs2.getString("created"));
					formVOO.setLastModifiedDate(rs2.getString("lastModified"));
					formVOO.setSupplyDate(rs2.getString("supplydate"));

					productList.add(formVOO);
					stockCount = rs2.getString("stockid");
					
				}if(!available){										
					ProductFormVO formVOO = new ProductFormVO();
					formVOO.setProductId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setProductName("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setAvailableId("----");
					formVOO.setLastCount("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setConsumptionId("----");					
					formVOO.setRequirementId("----");						
					formVOO.setAdditionId("----");											
					formVOO.setCreditnoteId("----");																		
					formVOO.setStatus("----");
					formVOO.setStatusOn("----");
					formVOO.setStatusOff("----");
					formVOO.setCreatedDate("----");
					formVOO.setLastModifiedDate("----");
					formVOO.setSupplyDate("----");

					productList.add(formVOO);
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
		}
		
		return productList;			
		}
		
		public List<ProductFormVO> getKitchenProductRequirementIndexMapByStatus(ProductFormVO formVO) throws SQLException, Exception {
			List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			ResultSet rs9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			PreparedStatement pStmt12 = null;
			ResultSet rs12 = null;
			PreparedStatement pStmt13 = null;
			ResultSet rs13 = null;
			
			int procuctCount = 0;
			String tempStockId = null;
			String count=null;
			boolean available=false;
			
			String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
			String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
			String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
			String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
			String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
			String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
			String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
			String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
			String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
			String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";

			try{			
				con = dbConnection.getConnection();
				
				pStmt12 = con.prepareStatement(Query8);
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					procuctCount = rs12.getInt(1);
				}
				
				for(int i=1; i<procuctCount+1; i++){
					pStmt13 = con.prepareStatement(Query9);
					pStmt13.setInt(1, i);
					rs13 = pStmt13.executeQuery();
					
					while(rs13.next()){
						if(i==1)
							tempStockId=rs13.getString("stockid");
						else
							tempStockId=tempStockId +","+ rs13.getString("stockid");
					}			
				}
				
				String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") AND status=? ORDER BY stockid ASC, lastmodified DESC ";
				String Query10="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+")  ORDER BY stockid ASC, lastmodified DESC ";

				pStmt2 = con.prepareStatement(Query);
				
				if(formVO.getStatus().contains("Requested"))
					pStmt2.setString(1,"requested");	
				else if(formVO.getStatus().contains("Supplied"))
					pStmt2.setString(1,"supplied");
				else if(formVO.getStatus().contains("Pending"))
					pStmt2.setString(1,"pending");
				else
					pStmt2 = con.prepareStatement(Query10);
					
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					available=true;
					if(rs2.getString("stockid").equalsIgnoreCase(count))
						continue;
					
					ProductFormVO formVOO = new ProductFormVO();
					
					pStmt10 = con.prepareStatement(Query0);
					pStmt10.setString(1, rs2.getString("storeid"));
					rs10 = pStmt10.executeQuery();			
					while(rs10.next()){					
						formVOO.setProductId(rs10.getString("productid"));
					}
										
					pStmt9 = con.prepareStatement(Query1);
					pStmt9.setString(1, formVOO.getProductId());
					rs9 = pStmt9.executeQuery();			
					while(rs9.next()){
						formVOO.setProductName(rs9.getString("name"));
						formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
						formVOO.setID(rs9.getString("ID"));
					}
					
					pStmt4 = con.prepareStatement(Query2);
					pStmt4.setString(1, rs2.getString("availableid"));
					rs4 = pStmt4.executeQuery();			
					while(rs4.next()){
						formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
						
						pStmt11 = con.prepareStatement(Query7);
						pStmt11.setString(1, rs2.getString("stockid"));
						rs11 = pStmt11.executeQuery();	
						
						while(rs11.next()){
							formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
							formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
						}								
					}
					
					pStmt5 = con.prepareStatement(Query3);
					pStmt5.setString(1, rs2.getString("consumptionid"));
					rs5 = pStmt5.executeQuery();			
					while(rs5.next()){
						formVOO.setConsumptionId(rs5.getString("consumption"));
					}
					
					pStmt6 = con.prepareStatement(Query4);
					pStmt6.setString(1, rs2.getString("requirementid"));
					rs6 = pStmt6.executeQuery();			
					while(rs6.next()){
						formVOO.setRequirementId(rs6.getString("requirement"));
					}
					
					pStmt7 = con.prepareStatement(Query5);
					pStmt7.setString(1, rs2.getString("additionid"));
					rs7 = pStmt7.executeQuery();			
					while(rs7.next()){
						formVOO.setAdditionId(rs7.getString("addition"));
					}
					
					pStmt8 = con.prepareStatement(Query6);
					pStmt8.setString(1, rs2.getString("creditnoteid"));
					rs8 = pStmt8.executeQuery();			
					while(rs8.next()){
						formVOO.setCreditnoteId(rs8.getString("creditnote"));
					}
					
					if(rs2.getString("status").contains("pending"))					
						formVOO.setStatus("Pending");	
					else if(rs2.getString("status").contains("requested"))					
						formVOO.setStatus("Requested");
					else if(rs2.getString("status").contains("supplied"))					
						formVOO.setStatus("Supplied");	
					
					formVOO.setStatusOn(rs2.getString("statuson"));
					formVOO.setStatusOff(rs2.getString("statusoff"));
					formVOO.setCreatedDate(rs2.getString("created"));
					formVOO.setLastModifiedDate(rs2.getString("lastModified"));
					formVOO.setSupplyDate(rs2.getString("supplydate"));
				
					productList.add(formVOO);
					count=rs2.getString("stockid");
					
				}if(!available){										
					ProductFormVO formVOO = new ProductFormVO();
					formVOO.setProductId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setProductName("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setAvailableId("----");
					formVOO.setLastCount("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setConsumptionId("----");					
					formVOO.setRequirementId("----");						
					formVOO.setAdditionId("----");											
					formVOO.setCreditnoteId("----");																		
					formVOO.setStatus("----");
					formVOO.setStatusOn("----");
					formVOO.setStatusOff("----");
					formVOO.setCreatedDate("----");
					formVOO.setLastModifiedDate("----");
					formVOO.setSupplyDate("----");

					productList.add(formVOO);
			}
				
				Collections.sort(productList, productComparator.productComparator);
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
				dbConnection.close(pStmt13, con);
			}
			
			return productList;			
		}
	
	//--------------------------------Product Consumptions----------------------------------------------------------

	public List<ProductFormVO> getKitchenProductConsumptionIndexMap(ProductFormVO formVO) throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt12 = null;
		ResultSet rs12 = null;
		PreparedStatement pStmt13 = null;
		ResultSet rs13 = null;
		
		int procuctCount = 0;
		String tempStockId = null;
		boolean available=false;
		
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
		String Query1="SELECT name, ID, quantityunit from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
		String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
		String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";
		
		try{			
			con = dbConnection.getConnection();
			
			pStmt12 = con.prepareStatement(Query8);
			rs12 = pStmt12.executeQuery();
			
			while(rs12.next()){
				procuctCount = rs12.getInt(1);
			}
			
			int tempCount=procuctCount+1;
			List<String> countList = new ArrayList<String>();
			
			for(int i=1; i<tempCount; i++){
				pStmt13 = con.prepareStatement(Query9);
				pStmt13.setInt(1, i);
				rs13 = pStmt13.executeQuery();
				
				while(rs13.next()){
					int j=1;
					if(i<6){
						if(j==1){
							tempStockId=rs13.getString("stockid");
							j++;
						}
						else{
							tempStockId=tempStockId +","+ rs13.getString("stockid");
							j++;
						}
					}
				}
				
				String []str = null;
				
				if(tempStockId!=null){
					str = tempStockId.split(",");				
					for(int m=0;m<str.length;m++){
						if(str[m]!=null && str[m]!="")
							countList.add(str[m]);
					}
					if(countList.size()<procuctCount){
						tempCount++;
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
			
			String Query="SELECT DISTINCT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified from inventory_kitchen_store WHERE stockid IN ("+id+") ORDER BY stockid ASC, lastmodified DESC ";
			
			pStmt2 = con.prepareStatement(Query);
			rs2 = pStmt2.executeQuery();
			
			String count = null;
			
			while(rs2.next()){
				available=true;
				if(rs2.getString("stockid").equalsIgnoreCase(count))
					continue;
				
				ProductFormVO formVOO = new ProductFormVO();
				
				pStmt10 = con.prepareStatement(Query0);
				pStmt10.setString(1, rs2.getString("storeid"));
				rs10 = pStmt10.executeQuery();			
				while(rs10.next()){					
					formVOO.setProductId(rs10.getString("productid"));
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setID(rs9.getString("ID"));
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();			
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
					}								
				}
				
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();			
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement"));
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition"));
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
			
				productList.add(formVOO);
				
				count = rs2.getString("stockid");
			}if(!available){
				
				ProductFormVO formVOO = new ProductFormVO();
				
				formVOO.setProductId("Unavailable");	
				formVOO.setID("Unavailable");
				formVOO.setProductName("----");
				formVOO.setQuantityPerUnit("----");						
				formVOO.setAvailableId("----");
				formVOO.setLastCount("----");
				formVOO.setQuantityPerUnit("----");						
				formVOO.setConsumptionId("----");					
				formVOO.setRequirementId("----");						
				formVOO.setAdditionId("----");											
				formVOO.setCreditnoteId("----");																		
				formVOO.setStatus("----");
				formVOO.setStatusOn("----");
				formVOO.setStatusOff("----");
				formVOO.setCreatedDate("----");
				formVOO.setLastModifiedDate("----");
				formVOO.setSupplyDate("----");
			
				productList.add(formVOO);
		}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
		}
		
		return productList;			
	}

	@Override
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByProductName(ProductFormVO formVO)
			throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt12 = null;
		ResultSet rs12 = null;
		PreparedStatement pStmt13 = null;
		ResultSet rs13 = null;
		
		int procuctCount = 0;
		String tempStockId = null;
		
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
		String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
		String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ( SELECT productid from inventory_kitchen_product where name = ? ) ORDER BY lastmodified DESC LIMIT 1 ";

		try{			
			con = dbConnection.getConnection();
			
			pStmt12 = con.prepareStatement(Query8);
			rs12 = pStmt12.executeQuery();
			
			while(rs12.next()){
				procuctCount = rs12.getInt(1);
			}
			
			for(int i=1; i<procuctCount+1; i++){
				pStmt13 = con.prepareStatement(Query9);
				pStmt13.setString(1, formVO.getProductName());
				rs13 = pStmt13.executeQuery();
				
				while(rs13.next()){
					if(i==1)
						tempStockId=rs13.getString("stockid");
					else
						tempStockId=tempStockId +","+ rs13.getString("stockid");
				}			
			}
			
			String Query="SELECT DISTINCT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") ORDER BY stockid ASC, lastmodified DESC ";

			String count = null;
			boolean available = false;
			pStmt2 = con.prepareStatement(Query);
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				available=true;
				if(rs2.getString("stockid").equalsIgnoreCase(count))
					continue;
				
				ProductFormVO formVOO = new ProductFormVO();
				
				pStmt10 = con.prepareStatement(Query0);
				pStmt10.setString(1, rs2.getString("storeid"));
				rs10 = pStmt10.executeQuery();			
				while(rs10.next()){					
					formVOO.setProductId(rs10.getString("productid"));
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
					formVOO.setID(rs9.getString("ID"));
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();			
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
						formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
					}								
				}
				
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();			
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement"));
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition"));
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
				formVOO.setSupplyDate(rs2.getString("supplydate"));
			
				productList.add(formVOO);
				count = rs2.getString("stockid");
			}if(!available){				
					ProductFormVO formVOO = new ProductFormVO();
					
					formVOO.setProductId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setProductName("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setAvailableId("----");
					formVOO.setLastCount("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setConsumptionId("----");					
					formVOO.setRequirementId("----");						
					formVOO.setAdditionId("----");											
					formVOO.setCreditnoteId("----");																		
					formVOO.setStatus("----");
					formVOO.setStatusOn("----");
					formVOO.setStatusOff("----");
					formVOO.setCreatedDate("----");
					formVOO.setLastModifiedDate("----");
					formVOO.setSupplyDate("----");

					productList.add(formVOO);					
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
		}
		
		return productList;			
	}

	@Override
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByProductId(ProductFormVO formVO)
			throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		
		boolean available=false;
		String count=null;
				
		String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ) ORDER BY stockid ASC, lastmodified DESC LIMIT 1  ";
		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = (SELECT productid from inventory_kitchen_product_stock where stockid=?))";
		
		try{
			
			con = dbConnection.getConnection();
						
			pStmt2 = con.prepareStatement(Query);
			pStmt2.setString(1, formVO.getProductId());
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				available=true;
				if(rs2.getString("stockid").equalsIgnoreCase(count))
					continue;
				
				ProductFormVO formVOO = new ProductFormVO();				
				formVOO.setProductId(formVO.getProductId());
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setID(rs9.getString("ID"));
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();			
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
					}								
				}
				
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();			
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement"));
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition"));
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
			
				productList.add(formVOO);
				count=rs2.getString("stockid");
			}
			
			if(!available){					
					ProductFormVO formVOO = new ProductFormVO();
					
					formVOO.setProductId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setProductName("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setAvailableId("----");
					formVOO.setLastCount("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setConsumptionId("----");					
					formVOO.setRequirementId("----");						
					formVOO.setAdditionId("----");											
					formVOO.setCreditnoteId("----");																		
					formVOO.setStatus("----");
					formVOO.setStatusOn("----");
					formVOO.setStatusOff("----");
					formVOO.setCreatedDate("----");
					formVOO.setLastModifiedDate("----");
					formVOO.setSupplyDate("----");
				
					productList.add(formVOO);
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
		}
		
		return productList;			
	}

	@Override
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByCreatedDate(ProductFormVO formVO)
			throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt12 = null;
		ResultSet rs12 = null;
		PreparedStatement pStmt13 = null;
		ResultSet rs13 = null;
		PreparedStatement pStmt14 = null;
		
		int procuctCount = 0;
		String tempStockId = null;
		boolean available=false;
		
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
		String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
		String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";

		try{			
			con = dbConnection.getConnection();
			
			pStmt12 = con.prepareStatement(Query8);
			rs12 = pStmt12.executeQuery();
			
			while(rs12.next()){
				procuctCount = rs12.getInt(1);
			}
			
			for(int i=1; i<procuctCount+1; i++){
				pStmt13 = con.prepareStatement(Query9);
				pStmt13.setInt(1, i);
				rs13 = pStmt13.executeQuery();
				
				while(rs13.next()){
					if(i==1)
						tempStockId=rs13.getString("stockid");
					else
						tempStockId=tempStockId +","+ rs13.getString("stockid");
				}			
			}
			
			String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") AND created >= '"+formVO.getFromDate()+"' AND created <='"+formVO.getToDate()+"' ORDER BY stockid ASC, lastmodified DESC ";
			
			pStmt2 = con.prepareStatement(Query);
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				available = true;
				ProductFormVO formVOO = new ProductFormVO();
				
				pStmt10 = con.prepareStatement(Query0);
				pStmt10.setString(1, rs2.getString("storeid"));
				rs10 = pStmt10.executeQuery();			
				while(rs10.next()){					
					formVOO.setProductId(rs10.getString("productid"));
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setID(rs9.getString("ID"));
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();			
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
					}								
				}
				
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();			
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement"));
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition"));
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
			
				productList.add(formVOO);
			}
			
			if(!available){				
					ProductFormVO formVOO = new ProductFormVO();
					
					formVOO.setProductId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setProductName("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setAvailableId("----");
					formVOO.setLastCount("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setConsumptionId("----");					
					formVOO.setRequirementId("----");						
					formVOO.setAdditionId("----");											
					formVOO.setCreditnoteId("----");																		
					formVOO.setStatus("----");
					formVOO.setStatusOn("----");
					formVOO.setStatusOff("----");
					formVOO.setCreatedDate("----");
					formVOO.setLastModifiedDate("----");
					formVOO.setSupplyDate("----");
				
					productList.add(formVOO);			
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
			dbConnection.close(pStmt14, con);
		}
		
		return productList;			
	}

	@Override
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByPagination(ProductFormVO formVO)
			throws SQLException, Exception {
		
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt12 = null;
		ResultSet rs12 = null;
		PreparedStatement pStmt13 = null;
		ResultSet rs13 = null;
		
		int procuctCount = 0;
		String tempStockId = null;
		boolean available=false;
		String count=null;
		
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
		String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
		String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";

		try{			
				con = dbConnection.getConnection();
				
				pStmt12 = con.prepareStatement(Query8);
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					procuctCount = rs12.getInt(1);
				}
				
				for(int i=1; i<procuctCount+1; i++){
					pStmt13 = con.prepareStatement(Query9);
					pStmt13.setInt(1, i);
					rs13 = pStmt13.executeQuery();
					
					while(rs13.next()){
						if(i==1)
							tempStockId=rs13.getString("stockid");
						else
							tempStockId=tempStockId +","+ rs13.getString("stockid");
					}			
				}
				
				String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid BETWEEN ? AND ?  ORDER BY stockid ASC, lastmodified DESC ";
				String Query10="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+")  ORDER BY stockid ASC, lastmodified DESC ";
				
				if((Integer.parseInt(formVO.getPageFrom()) < 0) || 
						(Integer.parseInt(formVO.getPageFrom()) >= 0) && (Integer.parseInt(formVO.getPageTo()) > tempStockId.split(",").length)){
					pStmt2 = con.prepareStatement(Query10);
				}else{	
					pStmt2 = con.prepareStatement(Query);
					pStmt2.setInt(1, Integer.parseInt(tempStockId.split(",")[Integer.parseInt(formVO.getPageFrom())-1]));
					pStmt2.setInt(2, Integer.parseInt(tempStockId.split(",")[Integer.parseInt(formVO.getPageTo())-1]));
				}
			
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					available=true;
					if(rs2.getString("stockid").equalsIgnoreCase(count))
						continue;
					
					ProductFormVO formVOO = new ProductFormVO();
					
					pStmt10 = con.prepareStatement(Query0);
					pStmt10.setString(1, rs2.getString("storeid"));
					rs10 = pStmt10.executeQuery();			
					while(rs10.next()){					
						formVOO.setProductId(rs10.getString("productid"));
					}
										
					pStmt9 = con.prepareStatement(Query1);
					pStmt9.setString(1, formVOO.getProductId());
					rs9 = pStmt9.executeQuery();			
					while(rs9.next()){
						formVOO.setProductName(rs9.getString("name"));
						formVOO.setID(rs9.getString("ID"));
					}
					
					pStmt4 = con.prepareStatement(Query2);
					pStmt4.setString(1, rs2.getString("availableid"));
					rs4 = pStmt4.executeQuery();			
					while(rs4.next()){
						formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
						
						pStmt11 = con.prepareStatement(Query7);
						pStmt11.setString(1, rs2.getString("stockid"));
						rs11 = pStmt11.executeQuery();	
						
						while(rs11.next()){
							formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
						}								
					}
					
					pStmt5 = con.prepareStatement(Query3);
					pStmt5.setString(1, rs2.getString("consumptionid"));
					rs5 = pStmt5.executeQuery();			
					while(rs5.next()){
						formVOO.setConsumptionId(rs5.getString("consumption"));
					}
					
					pStmt6 = con.prepareStatement(Query4);
					pStmt6.setString(1, rs2.getString("requirementid"));
					rs6 = pStmt6.executeQuery();			
					while(rs6.next()){
						formVOO.setRequirementId(rs6.getString("requirement"));
					}
					
					pStmt7 = con.prepareStatement(Query5);
					pStmt7.setString(1, rs2.getString("additionid"));
					rs7 = pStmt7.executeQuery();			
					while(rs7.next()){
						formVOO.setAdditionId(rs7.getString("addition"));
					}
					
					pStmt8 = con.prepareStatement(Query6);
					pStmt8.setString(1, rs2.getString("creditnoteid"));
					rs8 = pStmt8.executeQuery();			
					while(rs8.next()){
						formVOO.setCreditnoteId(rs8.getString("creditnote"));
					}
					
					if(rs2.getString("status").contains("pending"))					
						formVOO.setStatus("Pending");	
					else if(rs2.getString("status").contains("requested"))					
						formVOO.setStatus("Requested");
					else if(rs2.getString("status").contains("supplied"))					
						formVOO.setStatus("Supplied");	
					
					formVOO.setStatusOn(rs2.getString("statuson"));
					formVOO.setStatusOff(rs2.getString("statusoff"));
					formVOO.setCreatedDate(rs2.getString("created"));
					formVOO.setLastModifiedDate(rs2.getString("lastModified"));
				
					productList.add(formVOO);
					count=rs2.getString("stockid");
				}if(!available){					
					ProductFormVO formVOO = new ProductFormVO();
					
					formVOO.setProductId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setProductName("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setAvailableId("----");
					formVOO.setLastCount("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setConsumptionId("----");					
					formVOO.setRequirementId("----");						
					formVOO.setAdditionId("----");											
					formVOO.setCreditnoteId("----");																		
					formVOO.setStatus("----");
					formVOO.setStatusOn("----");
					formVOO.setStatusOff("----");
					formVOO.setCreatedDate("----");
					formVOO.setLastModifiedDate("----");
					formVOO.setSupplyDate("----");
				
					productList.add(formVOO);
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
		}
		
		return productList;			
	}

	@Override
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByRecords(ProductFormVO formVO)
			throws SQLException, Exception {
			
			List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			PreparedStatement pStmt3 = null;
			PreparedStatement pStmt4 = null;
			ResultSet rs4 = null;
			PreparedStatement pStmt5 = null;
			ResultSet rs5 = null;
			PreparedStatement pStmt6 = null;
			ResultSet rs6 = null;
			PreparedStatement pStmt7 = null;
			ResultSet rs7 = null;
			PreparedStatement pStmt8 = null;
			ResultSet rs8 = null;
			PreparedStatement pStmt9 = null;
			ResultSet rs9 = null;
			PreparedStatement pStmt10 = null;
			ResultSet rs10 = null;
			PreparedStatement pStmt11 = null;
			ResultSet rs11 = null;
			PreparedStatement pStmt12 = null;
			ResultSet rs12 = null;
			PreparedStatement pStmt13 = null;
			ResultSet rs13 = null;
			
			int procuctCount = 0;
			String tempStockId = null;
			int count = 0;
			boolean available=false;
								
			String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
			String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
			String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
			String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
			String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
			String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
			String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
			String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
			String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
			String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";

			try{			
				con = dbConnection.getConnection();
				
				pStmt12 = con.prepareStatement(Query8);
				rs12 = pStmt12.executeQuery();
				
				while(rs12.next()){
					procuctCount = rs12.getInt(1);
				}
				
				if(procuctCount>4 && procuctCount<16){
					if(formVO.getLimit().equalsIgnoreCase("1")){
						count = 5;
					}else if(formVO.getLimit().equalsIgnoreCase("2")){
						count = 10;
					}else{
						count = 15;
					}
				}else{
					count=procuctCount;
				}
				
				for(int i=1; i<count+1; i++){
					pStmt13 = con.prepareStatement(Query9);
					pStmt13.setInt(1, i);
					rs13 = pStmt13.executeQuery();
					
					while(rs13.next()){
						if(i==1)
							tempStockId=rs13.getString("stockid");
						else
							tempStockId=tempStockId +","+ rs13.getString("stockid");
					}			
				}
				
				String Query="SELECT DISTINCT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") ORDER BY stockid ASC, lastmodified DESC ";
				
				String tempcount = null;
				
				pStmt2 = con.prepareStatement(Query);
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					available=true;
					if(rs2.getString("stockid").equalsIgnoreCase(tempcount))
						continue;
					
					ProductFormVO formVOO = new ProductFormVO();
					pStmt10 = con.prepareStatement(Query0);
					pStmt10.setString(1, rs2.getString("storeid"));
					rs10 = pStmt10.executeQuery();			
					while(rs10.next()){					
						formVOO.setProductId(rs10.getString("productid"));
					}
										
					pStmt9 = con.prepareStatement(Query1);
					pStmt9.setString(1, formVOO.getProductId());
					rs9 = pStmt9.executeQuery();			
					while(rs9.next()){
						formVOO.setProductName(rs9.getString("name"));
						formVOO.setID(rs9.getString("ID"));
					}
					
					pStmt4 = con.prepareStatement(Query2);
					pStmt4.setString(1, rs2.getString("availableid"));
					rs4 = pStmt4.executeQuery();			
					while(rs4.next()){
						formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
						
						pStmt11 = con.prepareStatement(Query7);
						pStmt11.setString(1, rs2.getString("stockid"));
						rs11 = pStmt11.executeQuery();	
						
						while(rs11.next()){
							formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
						}								
					}
					
					pStmt5 = con.prepareStatement(Query3);
					pStmt5.setString(1, rs2.getString("consumptionid"));
					rs5 = pStmt5.executeQuery();			
					while(rs5.next()){
						formVOO.setConsumptionId(rs5.getString("consumption"));
					}
					
					pStmt6 = con.prepareStatement(Query4);
					pStmt6.setString(1, rs2.getString("requirementid"));
					rs6 = pStmt6.executeQuery();			
					while(rs6.next()){
						formVOO.setRequirementId(rs6.getString("requirement"));
					}
					
					pStmt7 = con.prepareStatement(Query5);
					pStmt7.setString(1, rs2.getString("additionid"));
					rs7 = pStmt7.executeQuery();			
					while(rs7.next()){
						formVOO.setAdditionId(rs7.getString("addition"));
					}
					
					pStmt8 = con.prepareStatement(Query6);
					pStmt8.setString(1, rs2.getString("creditnoteid"));
					rs8 = pStmt8.executeQuery();			
					while(rs8.next()){
						formVOO.setCreditnoteId(rs8.getString("creditnote"));
					}
					
					if(rs2.getString("status").contains("pending"))					
						formVOO.setStatus("Pending");	
					else if(rs2.getString("status").contains("requested"))					
						formVOO.setStatus("Requested");
					else if(rs2.getString("status").contains("supplied"))					
						formVOO.setStatus("Supplied");	
					
					formVOO.setStatusOn(rs2.getString("statuson"));
					formVOO.setStatusOff(rs2.getString("statusoff"));
					formVOO.setCreatedDate(rs2.getString("created"));
					formVOO.setLastModifiedDate(rs2.getString("lastModified"));
				
					productList.add(formVOO);
					tempcount = rs2.getString("stockid");
				}if(!available){					
					ProductFormVO formVOO = new ProductFormVO();
					
					formVOO.setProductId("Unavailable");	
					formVOO.setID("Unavailable");
					formVOO.setProductName("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setAvailableId("----");
					formVOO.setLastCount("----");
					formVOO.setQuantityPerUnit("----");						
					formVOO.setConsumptionId("----");					
					formVOO.setRequirementId("----");						
					formVOO.setAdditionId("----");											
					formVOO.setCreditnoteId("----");																		
					formVOO.setStatus("----");
					formVOO.setStatusOn("----");
					formVOO.setStatusOff("----");
					formVOO.setCreatedDate("----");
					formVOO.setLastModifiedDate("----");
					formVOO.setSupplyDate("----");
				
					productList.add(formVOO);
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
		}
		
		return productList;			
	}
	
	
	//-------------------------------------------Product Reports----------------------------------------------------------------------------
	
	public List<ProductFormVO> getKitchenProductReportIndexMap(ProductFormVO formVO)
			throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt12 = null;
		ResultSet rs12 = null;
		PreparedStatement pStmt13 = null;
		PreparedStatement pStmt14 = null;
		ResultSet rs99 = null;
		PreparedStatement pStmt15 = null;
		ResultSet rs999 = null;
		PreparedStatement pStmt16 = null;
		ResultSet rs16 = null;
		PreparedStatement pStmt17 = null;
		ResultSet rs17 = null;
		PreparedStatement pStmt18 = null;
		ResultSet rs18 = null;
		PreparedStatement pStmt19 = null;
		ResultSet rs19 = null;
		PreparedStatement pStmt20 = null;
		ResultSet rs20 = null;
		
		String count = null;
		int procuctCount = 0;
		String tempStockId = null;
		boolean available = false;				
		boolean available1=false;
		boolean available2=false;
		boolean available3=false;
		
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement, description, lastmodified from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
		String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
		String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";
		String Query11="SELECT productid, name, quantitytypeid, ID from inventory_kitchen_inventory where productid = ? ";
		String Query12="SELECT COUNT(*) FROM inventory_kitchen_inventory ";
		String Query13="SELECT productid FROM inventory_kitchen_product ";
		String Query14="SELECT productid FROM inventory_kitchen_inventory ";
		
		try{			
			con = dbConnection.getConnection();
			
			pStmt12 = con.prepareStatement(Query8);
			rs12 = pStmt12.executeQuery();
			
			while(rs12.next()){
				procuctCount = rs12.getInt(1);
			}
			
			List<String> productstr1 = new ArrayList<String>();
			List<String> productstr2 = new ArrayList<String>();
			
			pStmt18 = con.prepareStatement(Query13);
			rs18 = pStmt18.executeQuery();
			
			while(rs18.next()){
				productstr1.add(rs18.getString("productid"));
			}
						
			int tempCount=procuctCount;
			List<String> countList = new ArrayList<String>();
			
			for(int i=0; i<tempCount; i++){
				pStmt17 = con.prepareStatement(Query9);
				pStmt17.setInt(1, Integer.parseInt(productstr1.get(i)));
				rs17 = pStmt17.executeQuery();
				
				while(rs17.next()){
					tempStockId=rs17.getString("stockid");
				}						
								
				if(tempStockId!=null && tempStockId!=""){
					for(int m=0;m<1;m++){
						countList.add(tempStockId);
					}
					if(countList.size()<procuctCount){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			//--------------
			
			pStmt16 = con.prepareStatement(Query12);
			rs16 = pStmt16.executeQuery();
			
			while(rs16.next()){
				procuctCount = rs16.getInt(1);
			}
			
			pStmt19 = con.prepareStatement(Query14);
			rs19 = pStmt19.executeQuery();
			
			while(rs19.next()){
				productstr2.add(rs19.getString("productid"));
			}
			
			int tempCountProcuctCount = tempCount + procuctCount;
			tempCount=procuctCount;
			
			for(int i=0; i<tempCount; i++){
				pStmt20 = con.prepareStatement(Query9);
				pStmt20.setInt(1, Integer.parseInt(productstr2.get(i)));
				rs20 = pStmt20.executeQuery();
				
				while(rs20.next()){
					tempStockId=rs20.getString("stockid");
				}						
								
				if(tempStockId!=null && tempStockId!=""){
					for(int m=0;m<1;m++){
						countList.add(tempStockId);
					}
					if(countList.size()<tempCountProcuctCount){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			//--------
			
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
			
			//----------
			
			String Query="SELECT DISTINCT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+id+") ORDER BY stockid ASC, lastmodified DESC ";
			
			pStmt2 = con.prepareStatement(Query);			
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				available=true;
				if(rs2.getString("stockid").equalsIgnoreCase(count))
					continue;
				
				ProductFormVO formVOO = new ProductFormVO();
				pStmt10 = con.prepareStatement(Query0);
				pStmt10.setString(1, rs2.getString("storeid"));
				rs10 = pStmt10.executeQuery();			
				while(rs10.next()){	
					available2=true;
					formVOO.setProductId(rs10.getString("productid"));					
				}if(!available2){
					pStmt15 = con.prepareStatement(Query11);
					pStmt15.setString(1, formVOO.getProductId());
					rs999 = pStmt15.executeQuery();			
					while(rs999.next()){
						formVOO.setProductId(rs999.getString("productid"));
					}
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					available1=true;
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
					formVOO.setID(rs9.getString("ID"));
				}if(!available1){
					pStmt14 = con.prepareStatement(Query11);
					pStmt14.setString(1, formVOO.getProductId());
					rs99 = pStmt14.executeQuery();			
					while(rs99.next()){
						formVOO.setProductName(rs99.getString("name"));
						formVOO.setQuantityPerUnit(rs99.getString("quantitytypeid"));
						formVOO.setID(rs99.getString("ID"));
					}
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();	
				String availableQuantityType=null;
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					availableQuantityType=rs4.getString("description");
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						available3=true;
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
						formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
					}if(!available3){
						formVOO.setLastCount("1");
					}								
				}
				
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();			
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement") +" "+ rs6.getString("description") );
					formVOO.setRequestDate(rs6.getString("lastmodified"));
					if(formVOO.getRequirementId().equalsIgnoreCase("0 0"))
						formVOO.setRequirementId("-------");
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition") +" "+ availableQuantityType);
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
				formVOO.setSupplyDate(rs2.getString("supplydate"));
			
				available1=false;
				available2=false;
				available3=false;
				productList.add(formVOO);
				
				count = rs2.getString("stockid");
			}if(!available){				
				ProductFormVO formVOO = new ProductFormVO();												
				formVOO.setProductId("Unavailable");	
				formVOO.setID("Unavailable");
				formVOO.setProductName("----");
				formVOO.setQuantityPerUnit("----");				
				formVOO.setAvailableId("----");										
				formVOO.setLastCount("----"); 					
				formVOO.setConsumptionId("----");		
				formVOO.setRequirementId("----");			
				formVOO.setAdditionId("----");		
				formVOO.setCreditnoteId("----");						
				formVOO.setStatus("----");							
				formVOO.setStatusOn("----");
				formVOO.setStatusOff("----");
				formVOO.setCreatedDate("----");
				formVOO.setLastModifiedDate("----");
				formVOO.setSupplyDate("----");
			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
			dbConnection.close(pStmt14, con);
			dbConnection.close(pStmt15, con);
			dbConnection.close(pStmt16, con);
			dbConnection.close(pStmt17, con);
			dbConnection.close(pStmt18, con);
			dbConnection.close(pStmt19, con);
			dbConnection.close(pStmt20, con);

		}
		
		return productList;			
	}
	
	public List<ProductFormVO> getKitchenProductReportByProductId(ProductFormVO formVO) throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt99 = null;
		ResultSet rs99 = null;
		PreparedStatement pStmt999 = null;
		ResultSet rs999 = null;
		
		boolean available = false;
		String count=null;
				
		boolean available1=false;
		boolean available2=false;
		boolean available3=false;
						
		String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate FROM inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ) ORDER BY stockid ASC, lastmodified DESC LIMIT 1  ";
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid=(SELECT stockid from inventory_kitchen_store where storeid=?) ";
		String Query11="SELECT productid, name, quantitytypeid, ID from inventory_kitchen_inventory where productid = ? ";

		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement, description, lastmodified from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = (SELECT productid from inventory_kitchen_product_stock where stockid=?))";

		
		try{
			
			con = dbConnection.getConnection();
			
			pStmt2 = con.prepareStatement(Query);
			pStmt2.setString(1, formVO.getProductId());
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				available=true;
				if(rs2.getString("stockid").equalsIgnoreCase(count))
					continue;
								
				ProductFormVO formVOO = new ProductFormVO();
				pStmt10 = con.prepareStatement(Query0);
				pStmt10.setString(1, rs2.getString("storeid"));
				rs10 = pStmt10.executeQuery();			
				while(rs10.next()){	
					available2=true;
					formVOO.setProductId(rs10.getString("productid"));					
				}if(!available2){
					pStmt999 = con.prepareStatement(Query11);
					pStmt999.setString(1, formVOO.getProductId());
					rs999 = pStmt999.executeQuery();			
					while(rs999.next()){
						formVOO.setProductId(rs999.getString("productid"));
					}
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					available1=true;
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
					formVOO.setID(rs9.getString("ID"));
				}if(!available1){
					pStmt99 = con.prepareStatement(Query11);
					pStmt99.setString(1, formVOO.getProductId());
					rs99 = pStmt99.executeQuery();			
					while(rs99.next()){
						formVOO.setProductName(rs99.getString("name"));
						formVOO.setQuantityPerUnit(rs99.getString("quantitytypeid"));
						formVOO.setID(rs99.getString("ID"));
					}
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();	
				String availableQuantityType=null;
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					availableQuantityType=rs4.getString("description");
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						available3=true;
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
						formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
					}if(!available3){
						formVOO.setLastCount("1");
					}								
				}
				
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();			
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement") +" "+ rs6.getString("description") );
					formVOO.setRequestDate(rs6.getString("lastmodified"));
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition") +" "+ availableQuantityType);
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
				formVOO.setSupplyDate(rs2.getString("supplydate"));
			
				available1=false;
				available2=false;
				available3=false;
				count = rs2.getString("stockid");

				productList.add(formVOO);
				
			}if(!available){				
				ProductFormVO formVOO = new ProductFormVO();												
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setProductName("----");
				formVOO.setQuantityPerUnit("----");				
				formVOO.setAvailableId("----");										
				formVOO.setLastCount("----"); 					
				formVOO.setConsumptionId("----");		
				formVOO.setRequirementId("----");			
				formVOO.setAdditionId("----");		
				formVOO.setCreditnoteId("----");						
				formVOO.setStatus("----");							
				formVOO.setStatusOn("----");
				formVOO.setStatusOff("----");
				formVOO.setCreatedDate("----");
				formVOO.setLastModifiedDate("----");
				formVOO.setSupplyDate("----");
			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, stockComparator.stockComparator);
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
			dbConnection.close(pStmt99, con);
			dbConnection.close(pStmt999, con);
		}
		
		return productList;			
	}
	
	
	public List<ProductFormVO> getKitchenProductReportByProductName(ProductFormVO formVO)
			throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt99 = null;
		ResultSet rs99 = null;
		PreparedStatement pStmt999 = null;
		ResultSet rs999 = null;
		
		String count=null;
		boolean available = false;				
		boolean available1=false;
		boolean available2=false;
		boolean available3=false;
				
		String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate FROM inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid IN ( SELECT productid from inventory_kitchen_product WHERE name = ? )) ORDER BY stockid ASC, lastmodified DESC LIMIT 1";
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid=(SELECT stockid from inventory_kitchen_store where storeid=?) ";
		String Query11="SELECT productid, name, quantitytypeid, ID from inventory_kitchen_inventory where productid = ? ";

		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement, description, lastmodified from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = (SELECT productid from inventory_kitchen_product_stock where stockid=?))";
		
		try{
			
			con = dbConnection.getConnection();
						
			pStmt2 = con.prepareStatement(Query);			
			pStmt2.setString(1, formVO.getProductName());			
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				available=true;
				if(rs2.getString("stockid").equalsIgnoreCase(count))
					continue;
												
				ProductFormVO formVOO = new ProductFormVO();
				pStmt10 = con.prepareStatement(Query0);
				pStmt10.setString(1, rs2.getString("storeid"));
				rs10 = pStmt10.executeQuery();			
				while(rs10.next()){	
					available2=true;
					formVOO.setProductId(rs10.getString("productid"));					
				}if(!available2){
					pStmt999 = con.prepareStatement(Query11);
					pStmt999.setString(1, formVOO.getProductId());
					rs999 = pStmt999.executeQuery();			
					while(rs999.next()){
						formVOO.setProductId(rs999.getString("productid"));
					}
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					available1=true;
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
					formVOO.setID(rs9.getString("ID"));
				}if(!available1){
					pStmt99 = con.prepareStatement(Query11);
					pStmt99.setString(1, formVOO.getProductId());
					rs99 = pStmt99.executeQuery();			
					while(rs99.next()){
						formVOO.setProductName(rs99.getString("name"));
						formVOO.setQuantityPerUnit(rs99.getString("quantitytypeid"));
						formVOO.setID(rs99.getString("ID"));
					}
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();
				String availableQuantityType=null;
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					availableQuantityType=rs4.getString("description");
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						available3=true;
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
						formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
					}if(!available3){
						formVOO.setLastCount("1");
					}								
				}
				
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();			
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement") +" "+ rs6.getString("description") );
					formVOO.setRequestDate(rs6.getString("lastmodified"));
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition") +" "+ availableQuantityType);
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
				formVOO.setSupplyDate(rs2.getString("supplydate"));
			
				available1=false;
				available2=false;
				available3=false;
				
				count = rs2.getString("stockid");
				productList.add(formVOO);
				
			}if(!available){				
				ProductFormVO formVOO = new ProductFormVO();												
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setProductName("----");
				formVOO.setQuantityPerUnit("----");				
				formVOO.setAvailableId("----");										
				formVOO.setLastCount("----"); 					
				formVOO.setConsumptionId("----");		
				formVOO.setRequirementId("----");			
				formVOO.setAdditionId("----");		
				formVOO.setCreditnoteId("----");						
				formVOO.setStatus("----");							
				formVOO.setStatusOn("----");
				formVOO.setStatusOff("----");
				formVOO.setCreatedDate("----");
				formVOO.setLastModifiedDate("----");
				formVOO.setSupplyDate("----");
			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, stockComparator.stockComparator);
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
			dbConnection.close(pStmt99, con);
			dbConnection.close(pStmt999, con);
		}
		
		return productList;			
	}
	
	public List<ProductFormVO> getKitchenProductReportByStatus(ProductFormVO formVO) throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt12 = null;
		ResultSet rs12 = null;
		PreparedStatement pStmt13 = null;
		PreparedStatement pStmt14 = null;
		ResultSet rs99 = null;
		PreparedStatement pStmt15 = null;
		ResultSet rs999 = null;
		PreparedStatement pStmt16 = null;
		ResultSet rs16 = null;
		PreparedStatement pStmt17 = null;
		ResultSet rs17 = null;
		PreparedStatement pStmt18 = null;
		ResultSet rs18 = null;
		PreparedStatement pStmt19 = null;
		ResultSet rs19 = null;
		PreparedStatement pStmt20 = null;
		ResultSet rs20 = null;
		
		String count = null;
		int procuctCount = 0;
		String tempStockId = null;
		boolean available = false;				
		boolean available1=false;
		boolean available2=false;
		boolean available3=false;
		
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement, description, lastmodified from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
		String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
		String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";
		String Query11="SELECT productid, name, quantitytypeid, ID from inventory_kitchen_inventory where productid = ? ";
		String Query12="SELECT COUNT(*) FROM inventory_kitchen_inventory ";
		String Query13="SELECT productid FROM inventory_kitchen_product ";
		String Query14="SELECT productid FROM inventory_kitchen_inventory ";
		
		try{			
			con = dbConnection.getConnection();
			
			pStmt12 = con.prepareStatement(Query8);
			rs12 = pStmt12.executeQuery();
			
			while(rs12.next()){
				procuctCount = rs12.getInt(1);
			}
			
			List<String> productstr1 = new ArrayList<String>();
			List<String> productstr2 = new ArrayList<String>();
			
			pStmt18 = con.prepareStatement(Query13);
			rs18 = pStmt18.executeQuery();
			
			while(rs18.next()){
				productstr1.add(rs18.getString("productid"));
			}
						
			int tempCount=procuctCount;
			List<String> countList = new ArrayList<String>();
			
			for(int i=0; i<tempCount; i++){
				pStmt17 = con.prepareStatement(Query9);
				pStmt17.setInt(1, Integer.parseInt(productstr1.get(i)));
				rs17 = pStmt17.executeQuery();
				
				while(rs17.next()){
					tempStockId=rs17.getString("stockid");
				}						
								
				if(tempStockId!=null && tempStockId!=""){
					for(int m=0;m<1;m++){
						countList.add(tempStockId);
					}
					if(countList.size()<procuctCount){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			//--------------
			
			pStmt16 = con.prepareStatement(Query12);
			rs16 = pStmt16.executeQuery();
			
			while(rs16.next()){
				procuctCount = rs16.getInt(1);
			}
			
			pStmt19 = con.prepareStatement(Query14);
			rs19 = pStmt19.executeQuery();
			
			while(rs19.next()){
				productstr2.add(rs19.getString("productid"));
			}
			
			int tempCountProcuctCount = tempCount + procuctCount;
			tempCount=procuctCount;
			
			for(int i=0; i<tempCount; i++){
				pStmt20 = con.prepareStatement(Query9);
				pStmt20.setInt(1, Integer.parseInt(productstr2.get(i)));
				rs20 = pStmt20.executeQuery();
				
				while(rs20.next()){
					tempStockId=rs20.getString("stockid");
				}						
								
				if(tempStockId!=null && tempStockId!=""){
					for(int m=0;m<1;m++){
						countList.add(tempStockId);
					}
					if(countList.size()<tempCountProcuctCount){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			//--------
			
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
			
			//----------
			
			String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+id+") AND status=? ORDER BY stockid ASC, lastmodified DESC ";
			String Query10="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+id+")  ORDER BY stockid ASC, lastmodified DESC ";

			pStmt2 = con.prepareStatement(Query);
			
			if(formVO.getStatus().contains("Requested"))
				pStmt2.setString(1,"requested");	
			else if(formVO.getStatus().contains("Supplied"))
				pStmt2.setString(1,"supplied");
			else if(formVO.getStatus().contains("Pending"))
				pStmt2.setString(1,"pending");
			else
				pStmt2 = con.prepareStatement(Query10);
				
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				available=true;
				if(rs2.getString("stockid").equalsIgnoreCase(count))
					continue;
								
				ProductFormVO formVOO = new ProductFormVO();
				pStmt10 = con.prepareStatement(Query0);
				pStmt10.setString(1, rs2.getString("storeid"));
				rs10 = pStmt10.executeQuery();			
				while(rs10.next()){	
					available2=true;
					formVOO.setProductId(rs10.getString("productid"));					
				}if(!available2){
					pStmt15 = con.prepareStatement(Query11);
					pStmt15.setString(1, formVOO.getProductId());
					rs999 = pStmt15.executeQuery();			
					while(rs999.next()){
						formVOO.setProductId(rs999.getString("productid"));
					}
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					available1=true;
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
					formVOO.setID(rs9.getString("ID"));
				}if(!available1){
					pStmt14 = con.prepareStatement(Query11);
					pStmt14.setString(1, formVOO.getProductId());
					rs99 = pStmt14.executeQuery();			
					while(rs99.next()){
						formVOO.setProductName(rs99.getString("name"));
						formVOO.setQuantityPerUnit(rs99.getString("quantitytypeid"));
						formVOO.setID(rs99.getString("ID"));
					}
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();
				String availableQuantityType=null;
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					availableQuantityType=rs4.getString("description");
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						available3=true;
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
						formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
					}if(!available3){
						formVOO.setLastCount("1");
					}
				}
							
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();			
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement") +" "+ rs6.getString("description") );
					formVOO.setRequestDate(rs6.getString("lastmodified"));
					if(rs6.getString("requirement").equalsIgnoreCase("0"))
						formVOO.setRequirementId("-------");
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition") +" "+ availableQuantityType);
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
				formVOO.setSupplyDate(rs2.getString("supplydate"));			
				available1=false;
				available2=false;	
				available3=false;
				count = rs2.getString("stockid");
				productList.add(formVOO);								
			}if(!available){				
				ProductFormVO formVOO = new ProductFormVO();												
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setProductName("----");
				formVOO.setQuantityPerUnit("----");				
				formVOO.setAvailableId("----");										
				formVOO.setLastCount("----"); 					
				formVOO.setConsumptionId("----");		
				formVOO.setRequirementId("----");			
				formVOO.setAdditionId("----");		
				formVOO.setCreditnoteId("----");						
				formVOO.setStatus("----");							
				formVOO.setStatusOn("----");
				formVOO.setStatusOff("----");
				formVOO.setCreatedDate("----");
				formVOO.setLastModifiedDate("----");
				formVOO.setSupplyDate("----");
			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
			dbConnection.close(pStmt14, con);
			dbConnection.close(pStmt15, con);
			dbConnection.close(pStmt16, con);
			dbConnection.close(pStmt17, con);
			dbConnection.close(pStmt18, con);
			dbConnection.close(pStmt19, con);
			dbConnection.close(pStmt20, con);
		}
		
		return productList;			
	}

	public List<ProductFormVO> getKitchenProductReportByCreatedDate(ProductFormVO formVO)
			throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt12 = null;
		ResultSet rs12 = null;
		PreparedStatement pStmt13 = null;
		PreparedStatement pStmt14 = null;
		ResultSet rs99 = null;
		PreparedStatement pStmt15 = null;
		ResultSet rs999 = null;
		PreparedStatement pStmt16 = null;
		ResultSet rs16 = null;
		PreparedStatement pStmt17 = null;
		ResultSet rs17 = null;
		PreparedStatement pStmt18 = null;
		ResultSet rs18 = null;
		PreparedStatement pStmt19 = null;
		ResultSet rs19 = null;
		PreparedStatement pStmt20 = null;
		ResultSet rs20 = null;
		
		String count = null;
		int procuctCount = 0;
		String tempStockId = null;
		boolean available = false;				
		boolean available1=false;
		boolean available2=false;
		boolean available3=false;
		
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement, description, lastmodified from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
		String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
		String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";
		String Query11="SELECT productid, name, quantitytypeid, ID from inventory_kitchen_inventory where productid = ? ";
		String Query12="SELECT COUNT(*) FROM inventory_kitchen_inventory ";
		String Query13="SELECT productid FROM inventory_kitchen_product ";
		String Query14="SELECT productid FROM inventory_kitchen_inventory ";
		
		try{			
			con = dbConnection.getConnection();
			
			pStmt12 = con.prepareStatement(Query8);
			rs12 = pStmt12.executeQuery();
			
			while(rs12.next()){
				procuctCount = rs12.getInt(1);
			}
			
			List<String> productstr1 = new ArrayList<String>();
			List<String> productstr2 = new ArrayList<String>();
			
			pStmt18 = con.prepareStatement(Query13);
			rs18 = pStmt18.executeQuery();
			
			while(rs18.next()){
				productstr1.add(rs18.getString("productid"));
			}
						
			int tempCount=procuctCount;
			List<String> countList = new ArrayList<String>();
			
			for(int i=0; i<tempCount; i++){
				pStmt17 = con.prepareStatement(Query9);
				pStmt17.setInt(1, Integer.parseInt(productstr1.get(i)));
				rs17 = pStmt17.executeQuery();
				
				while(rs17.next()){
					tempStockId=rs17.getString("stockid");
				}						
								
				if(tempStockId!=null && tempStockId!=""){
					for(int m=0;m<1;m++){
						countList.add(tempStockId);
					}
					if(countList.size()<procuctCount){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			//--------------
			
			pStmt16 = con.prepareStatement(Query12);
			rs16 = pStmt16.executeQuery();
			
			while(rs16.next()){
				procuctCount = rs16.getInt(1);
			}
			
			pStmt19 = con.prepareStatement(Query14);
			rs19 = pStmt19.executeQuery();
			
			while(rs19.next()){
				productstr2.add(rs19.getString("productid"));
			}
			
			int tempCountProcuctCount = tempCount + procuctCount;
			tempCount=procuctCount;
			
			for(int i=0; i<tempCount; i++){
				pStmt20 = con.prepareStatement(Query9);
				pStmt20.setInt(1, Integer.parseInt(productstr2.get(i)));
				rs20 = pStmt20.executeQuery();
				
				while(rs20.next()){
					tempStockId=rs20.getString("stockid");
				}						
								
				if(tempStockId!=null && tempStockId!=""){
					for(int m=0;m<1;m++){
						countList.add(tempStockId);
					}
					if(countList.size()<tempCountProcuctCount){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			//--------
			
			for (int i=1;i<countList.size(); i++) {
	            String a1 = countList.get(i);
	            String a2 = countList.get(i-1);
	            if (a1.equals(a2)) {
	            	countList.remove(a1);
	            }
	        }
			
			String id = null;
			for(int k=0; k<countList.size();k++){
				if(k==0 && k<countList.size())
					id=countList.get(k);
				else
					id=id+","+countList.get(k);
			}
			
			//----------
			
			String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+id+") AND created >= '"+formVO.getFromDate()+"' AND created <='"+formVO.getToDate()+"' ORDER BY stockid ASC, lastmodified DESC ";

			pStmt2 = con.prepareStatement(Query);				
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				available=true;
				if(rs2.getString("stockid").equalsIgnoreCase(count))
					continue;
								
				ProductFormVO formVOO = new ProductFormVO();
				pStmt10 = con.prepareStatement(Query0);
				pStmt10.setString(1, rs2.getString("storeid"));
				rs10 = pStmt10.executeQuery();			
				while(rs10.next()){	
					available2=true;
					formVOO.setProductId(rs10.getString("productid"));					
				}if(!available2){
					pStmt15 = con.prepareStatement(Query11);
					pStmt15.setString(1, formVOO.getProductId());
					rs999 = pStmt15.executeQuery();			
					while(rs999.next()){
						formVOO.setProductId(rs999.getString("productid"));
					}
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					available1=true;
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
					formVOO.setID(rs9.getString("ID"));
				}if(!available1){
					pStmt14 = con.prepareStatement(Query11);
					pStmt14.setString(1, formVOO.getProductId());
					rs99 = pStmt14.executeQuery();			
					while(rs99.next()){
						formVOO.setProductName(rs99.getString("name"));
						formVOO.setQuantityPerUnit(rs99.getString("quantitytypeid"));
						formVOO.setID(rs99.getString("ID"));
					}
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();
				String availableQuantityType=null;
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					availableQuantityType=rs4.getString("description");
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						available3=true;
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
						formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
					}if(!available3){
						formVOO.setLastCount("1");
					}
				}
							
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();			
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement") +" "+ rs6.getString("description") );
					formVOO.setRequestDate(rs6.getString("lastmodified"));
					if(rs6.getString("requirement").equalsIgnoreCase("0"))
						formVOO.setRequirementId("-------");
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition") +" "+ availableQuantityType);
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
				formVOO.setSupplyDate(rs2.getString("supplydate"));			
				available1=false;
				available2=false;	
				available3=false;
				count = rs2.getString("stockid");
				productList.add(formVOO);								
			}if(!available){				
				ProductFormVO formVOO = new ProductFormVO();												
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setProductName("----");
				formVOO.setQuantityPerUnit("----");				
				formVOO.setAvailableId("----");										
				formVOO.setLastCount("----"); 					
				formVOO.setConsumptionId("----");		
				formVOO.setRequirementId("----");			
				formVOO.setAdditionId("----");		
				formVOO.setCreditnoteId("----");						
				formVOO.setStatus("----");							
				formVOO.setStatusOn("----");
				formVOO.setStatusOff("----");
				formVOO.setCreatedDate("----");
				formVOO.setLastModifiedDate("----");
				formVOO.setSupplyDate("----");
			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
			dbConnection.close(pStmt14, con);
			dbConnection.close(pStmt15, con);
			dbConnection.close(pStmt16, con);
			dbConnection.close(pStmt17, con);
			dbConnection.close(pStmt18, con);
			dbConnection.close(pStmt19, con);
			dbConnection.close(pStmt20, con);
		}
		
		return productList;			
	}
	
	public int getPaginationCount()throws SQLException,Exception{
		
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String countQuery = "SELECT COUNT(*) FROM inventory_kitchen_product";

		try{
			con = dbConnection.getConnection();
			
			pStmt = con.prepareStatement(countQuery);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
		}
		
		return count;	
	}
	
	public List<ProductFormVO> getKitchenProductReportByPagination(ProductFormVO formVO)
			throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		ResultSet rs6 = null;
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		ResultSet rs11 = null;
		PreparedStatement pStmt12 = null;
		ResultSet rs12 = null;
		PreparedStatement pStmt13 = null;
		ResultSet rs13 = null;
		PreparedStatement pStmt99 = null;
		ResultSet rs99 = null;
		PreparedStatement pStmt999 = null;
		ResultSet rs999 = null;
		
		int procuctCount = 0;
		String tempStockId = null;
		boolean available = false;
		String count=null;
		
		boolean available1=false;
		boolean available2=false;
		boolean available3=false;
		
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
		String Query11="SELECT productid, name, quantitytypeid, ID from inventory_kitchen_inventory where productid = ? ";

		String Query1="SELECT name, quantityunit, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement, description, lastmodified from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";
		String Query8="SELECT COUNT(*) FROM inventory_kitchen_product ";
		String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";

		try{			
			con = dbConnection.getConnection();
			
			pStmt12 = con.prepareStatement(Query8);
			rs12 = pStmt12.executeQuery();
			
			while(rs12.next()){
				procuctCount = rs12.getInt(1);
			}
			
			for(int i=1; i<procuctCount+1; i++){
				pStmt13 = con.prepareStatement(Query9);
				pStmt13.setInt(1, i);
				rs13 = pStmt13.executeQuery();
				
				while(rs13.next()){
					if(i==1)
						tempStockId=rs13.getString("stockid");
					else
						tempStockId=tempStockId +","+ rs13.getString("stockid");
				}			
			}
			
			String Query="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid BETWEEN ? AND ? ORDER BY stockid ASC, lastmodified DESC ";
			String Query10="SELECT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified, supplydate from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") ORDER BY stockid ASC, lastmodified DESC ";	
			
			if((Integer.parseInt(formVO.getPageFrom()) < 0) || 
					(Integer.parseInt(formVO.getPageFrom()) >= 0) && (Integer.parseInt(formVO.getPageTo()) > tempStockId.split(",").length)){
				pStmt2 = con.prepareStatement(Query10);
			}else{	
				pStmt2 = con.prepareStatement(Query);
				pStmt2.setInt(1, Integer.parseInt(tempStockId.split(",")[Integer.parseInt(formVO.getPageFrom())-1]));
				pStmt2.setInt(2, Integer.parseInt(tempStockId.split(",")[Integer.parseInt(formVO.getPageTo())-1]));
			}
			
			rs2 = pStmt2.executeQuery();
			
			while(rs2.next()){
				available=true;
				if(rs2.getString("stockid").equalsIgnoreCase(count))
					continue;
				
				ProductFormVO formVOO = new ProductFormVO();
				pStmt10 = con.prepareStatement(Query0);
				pStmt10.setString(1, rs2.getString("storeid"));
				rs10 = pStmt10.executeQuery();			
				while(rs10.next()){	
					available2=true;
					formVOO.setProductId(rs10.getString("productid"));					
				}if(!available2){
					pStmt999 = con.prepareStatement(Query11);
					pStmt999.setString(1, formVOO.getProductId());
					rs999 = pStmt999.executeQuery();			
					while(rs999.next()){
						formVOO.setProductId(rs999.getString("productid"));
					}
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					available1=true;
					formVOO.setProductName(rs9.getString("name"));
					formVOO.setQuantityPerUnit(rs9.getString("quantityunit"));
					formVOO.setID(rs9.getString("ID"));
				}if(!available1){
					pStmt99 = con.prepareStatement(Query11);
					pStmt99.setString(1, formVOO.getProductId());
					rs99 = pStmt99.executeQuery();			
					while(rs99.next()){
						formVOO.setProductName(rs99.getString("name"));
						formVOO.setQuantityPerUnit(rs99.getString("quantitytypeid"));
						formVOO.setID(rs99.getString("ID"));
					}
				}
												
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();	
				String availableQuantityType=null;
				
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					availableQuantityType=rs4.getString("description");
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						available3=true;
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") ); 
						formVOO.setQuantityPerUnit(formVOO.getQuantityPerUnit() +" "+ rs11.getString("quantity"));
					}if(!available3){
						formVOO.setLastCount("1");
					}									
				}
				
				pStmt5 = con.prepareStatement(Query3);
				pStmt5.setString(1, rs2.getString("consumptionid"));
				rs5 = pStmt5.executeQuery();	
				
				while(rs5.next()){
					formVOO.setConsumptionId(rs5.getString("consumption"));
				}
				
				pStmt6 = con.prepareStatement(Query4);
				pStmt6.setString(1, rs2.getString("requirementid"));
				rs6 = pStmt6.executeQuery();			
				while(rs6.next()){
					formVOO.setRequirementId(rs6.getString("requirement") +" "+ rs6.getString("description") );
					formVOO.setRequestDate(rs6.getString("lastmodified"));
					
					if(rs6.getString("requirement").equalsIgnoreCase("0"))
						formVOO.setRequirementId("-------");
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition") +" "+ availableQuantityType);
				}
				
				pStmt8 = con.prepareStatement(Query6);
				pStmt8.setString(1, rs2.getString("creditnoteid"));
				rs8 = pStmt8.executeQuery();			
				while(rs8.next()){
					formVOO.setCreditnoteId(rs8.getString("creditnote"));
				}
				
				if(rs2.getString("status").contains("pending"))					
					formVOO.setStatus("Pending");	
				else if(rs2.getString("status").contains("requested"))					
					formVOO.setStatus("Requested");
				else if(rs2.getString("status").contains("supplied"))					
					formVOO.setStatus("Supplied");	
				
				formVOO.setStatusOn(rs2.getString("statuson"));
				formVOO.setStatusOff(rs2.getString("statusoff"));
				formVOO.setCreatedDate(rs2.getString("created"));
				formVOO.setLastModifiedDate(rs2.getString("lastModified"));
				formVOO.setSupplyDate(rs2.getString("supplydate"));
			
				available1=false;
				available2=false;
				available3=false;
				count = rs2.getString("stockid");				
				productList.add(formVOO);								
			}if(!available){				
				ProductFormVO formVOO = new ProductFormVO();												
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setProductName("----");
				formVOO.setQuantityPerUnit("----");				
				formVOO.setAvailableId("----");										
				formVOO.setLastCount("----"); 					
				formVOO.setConsumptionId("----");		
				formVOO.setRequirementId("----");			
				formVOO.setAdditionId("----");		
				formVOO.setCreditnoteId("----");						
				formVOO.setStatus("----");							
				formVOO.setStatusOn("----");
				formVOO.setStatusOff("----");
				formVOO.setCreatedDate("----");
				formVOO.setLastModifiedDate("----");
				formVOO.setSupplyDate("----");
			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, productComparator.productComparator);
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
			dbConnection.close(pStmt13, con);
			dbConnection.close(pStmt99, con);
			dbConnection.close(pStmt999, con);
		}
		
		return productList;			
	}
}
