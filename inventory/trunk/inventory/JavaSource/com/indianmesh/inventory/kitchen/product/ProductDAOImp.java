package com.indianmesh.inventory.kitchen.product;

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
import java.util.Map;

import com.indianmesh.inventory.utils.KitchenProductComparator;
import com.indianmesh.inventory.utils.dbConnection.DBConnection;

public class ProductDAOImp implements ProductBaseDAO {
	
	KitchenProductComparator kitchenProductComparator = new KitchenProductComparator();

	public ProductFormVO addKitchenProduct(ProductFormVO formVO) throws SQLException, Exception {

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
		
		String Query = "INSERT INTO inventory_kitchen_product (productid,sellerid,subsellerid,created,lastmodified) VALUES (?,?,?,?,?) ";
		
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
			
			if(proId!=null && invProId!=null){
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
									
			formVO.setProductId(id);
			pStmt=con.prepareStatement(Query);
			pStmt.setString(1,id);
			pStmt.setString(2,formVO.getSellerId());
			pStmt.setString(3,formVO.getSubSellerId());
			pStmt.setDate(4,new java.sql.Date(new Date().getTime()));
			pStmt.setDate(5,new java.sql.Date(new Date().getTime()));
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
	
	public ProductFormVO editKitchenProduct(ProductFormVO formVO) throws SQLException, Exception {

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		
		PreparedStatement pStmt2 = null;
		ResultSet rs2 = null;
		
		String Query="SELECT productid,ID,sellerid,subsellerid,name,type,description,quantity,quantityunit,quantitycount,total,vendorid,transaction,amountpaid,amountpaidtypeid,pendingamount,creditnote,created,lastmodified,consumed,required,status from inventory_kitchen_product WHERE productid=? ";
		String QueryTransaction="SELECT transation FROM inventory_kitchen_product_transaction WHERE transactionid IN ( SELECT transactionid FROM inventory_kitchen_product_supply WHERE supplyid IN ( SELECT supplyid from inventory_kitchen_product_stock where productid=? ) ) ORDER BY transactionid ASC LIMIT 1";
		String QueryInvoice="SELECT invoiceno FROM inventory_kitchen_product_invoice WHERE invoiceid IN ( SELECT invoiceid FROM inventory_kitchen_product_supply WHERE supplyid IN ( SELECT supplyid from inventory_kitchen_product_stock where productid=? ) ) ORDER BY invoiceid ASC LIMIT 1";			
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
			rs = pStmt.executeQuery();
			
			while(rs.next()){							
							
				formVO.setProductId(rs.getString("productid"));
				formVO.setID(rs.getString("ID"));
				formVO.setSellerId(rs.getString("sellerid"));
				formVO.setSubSellerId(rs.getString("subsellerid"));
				formVO.setName(rs.getString("name"));
				formVO.setProductType(rs.getString("type"));
				formVO.setDescription(rs.getString("description"));
				formVO.setQuantityId(rs.getString("quantity"));
				formVO.setQuantityPerItem(rs.getString("quantityunit"));
				formVO.setQuantityCount(rs.getString("quantitycount"));
				formVO.setTotal(rs.getString("total"));
				formVO.setVendorId(rs.getString("vendorid"));
				formVO.setCostPrice(rs.getString("transaction"));
				formVO.setAmountPaid(rs.getString("amountpaid"));
				formVO.setAmountPaidTypeId(rs.getString("amountpaidtypeid"));
				formVO.setPendingAmount(rs.getString("pendingamount"));
				formVO.setCreditNote(rs.getString("creditnote"));
				formVO.setCreatedDate(rs.getString("created"));
				formVO.setLastModifiedDate(rs.getString("lastmodified"));
				formVO.setConsumed(rs.getString("consumed"));
				formVO.setRequired(rs.getString("required"));
				formVO.setStatus(rs.getString("status"));
				
				pStmt1 = con.prepareStatement(QueryInvoice);
				pStmt1.setInt(1, Integer.parseInt(formVO.getProductId()));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){	
					formVO.setInvoiceId(rs1.getString("invoiceno"));
				}
				
				pStmt2 = con.prepareStatement(QueryTransaction);
				pStmt2.setInt(1, Integer.parseInt(formVO.getProductId()));
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){	
					formVO.setTransactionId(rs2.getString("transation"));
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
		
	public int updateKitchenProduct(ProductFormVO formVO) throws SQLException, Exception {

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
		PreparedStatement pStmt7 = null;
		ResultSet rs7 = null;
		PreparedStatement pStmt8 = null;
		ResultSet rs8 = null;
		PreparedStatement pStmt9 = null;
		ResultSet rs9 = null;
		PreparedStatement pStmt10 = null;
		ResultSet rs10 = null;
		PreparedStatement pStmt11 = null;
		PreparedStatement pStmt12 = null;
		PreparedStatement pStmt13 = null;
		PreparedStatement pStmt14 = null;
		PreparedStatement pStmt15 = null;
		PreparedStatement pStmt16 = null;
		ResultSet rs16 = null;
		PreparedStatement pStmt17 = null;
		ResultSet rs17 = null;
		PreparedStatement pStmt18 = null;
		ResultSet rs18 = null;
		PreparedStatement pStmt19 = null;
		ResultSet rs19 = null;
		PreparedStatement pStmt20 = null;
		PreparedStatement pStmt21 = null;
		PreparedStatement pStmt22 = null;
		ResultSet rs22 = null;
		PreparedStatement pStmt23 = null;
		ResultSet rs23 = null;
		PreparedStatement pStmt24 = null;
		ResultSet rs24 = null;
		PreparedStatement pStmt25 = null;
		ResultSet rs25 = null;
		PreparedStatement pStmt26 = null;
		PreparedStatement pStmt27 = null;
		PreparedStatement pStmt28 = null;
		ResultSet rs28 = null;
		PreparedStatement pStmt29 = null;
		ResultSet rs29 = null;
		PreparedStatement pStmt30 = null;
		PreparedStatement pStmt31 = null;
		PreparedStatement pStmt32 = null;
		ResultSet rs32 = null;
		PreparedStatement pStmt33 = null;
		ResultSet rs33 = null;
		
		int temp = 0;
		int count = 0;
		
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
		
		
		String Query="UPDATE inventory_kitchen_product SET name=?, type=?, description=?, quantity=?, quantityunit=?, quantitycount=?, total=?, vendorid=?, transaction=?, amountpaid=?, amountpaidtypeid=?, pendingamount=?, creditnote=?, created=?, lastmodified=?, consumed=?, required=?, status=?, ID=? WHERE productid=? ";
		
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
		
		String countQuery11 = "SELECT quantityid, quantity FROM inventory_quantity_type ";
		String idQuery11 = "INSERT INTO inventory_product_type_inversion ( procuctid, quantityid, quantity, sellerid, subsellerid, created, lastmodified, inversionid ) VALUES (?,?,?,?,?,?,?,?) ";
		
		String countQuery12 = "SELECT COUNT(*) FROM inventory_product_type_inversion ";
		
		try{
			con = dbConnection.getConnection();
			pStmt1 = con.prepareStatement(countQuery1);
			rs1 = pStmt1.executeQuery();
			
			while(rs1.next()){
				temp = rs1.getInt(1);
				
				for(int i=1;i<=temp+1;i++){
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
			
			pStmt5 = con.prepareStatement(countQuery3);
			rs5 = pStmt5.executeQuery();
			
			while(rs5.next()){
				temp = rs5.getInt(1);
				
				for(int i=1; i<=temp+1; i++){
					pStmt6 = con.prepareStatement(idQuery3);
					pStmt6.setInt(1,i);
					rs6 = pStmt6.executeQuery();					
					
					if(!rs6.next())
						requirementid = String.valueOf(i);
				}
			}
			
			pStmt7 = con.prepareStatement(countQuery4);
			rs7 = pStmt7.executeQuery();
			
			while(rs7.next()){
				temp = rs7.getInt(1);
				
				for(int i=1; i<=temp+1; i++){
					pStmt8 = con.prepareStatement(idQuery4);
					pStmt8.setInt(1,i);
					rs8 = pStmt8.executeQuery();					
					
					if(!rs8.next())
						additionid = String.valueOf(i);
				}
			}
			
			pStmt9 = con.prepareStatement(countQuery5);
			rs9 = pStmt9.executeQuery();
			
			while(rs9.next()){
				temp = rs9.getInt(1);
				
				for(int i=1; i<=temp+1; i++){
					pStmt10 = con.prepareStatement(idQuery5);
					pStmt10.setInt(1,i);
					rs10 = pStmt10.executeQuery();					
					
					if(!rs10.next())
						creditnoteid = String.valueOf(i);
				}
			}
				
			pStmt11=con.prepareStatement(Query1);
			pStmt11.setString(1,availableid);			
			pStmt11.setString(2,formVO.getTotal());
			pStmt11.setString(3,formVO.getQuantityTypeOfTotal());
			pStmt11.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt11.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt11.setString(6,formVO.getSellerId());
			pStmt11.setString(7,formVO.getSubSellerId());			
			count = (Integer.parseInt(formVO.getQuantityPerItem())) * (Integer.parseInt(formVO.getQuantityCount()));			
			pStmt11.setString(8,String.valueOf(count));
			pStmt11.setString(9,formVO.getQuantityTypeOfTotal());
			//pStmt11.executeQuery();
			pStmt11.executeUpdate();
			
			pStmt12=con.prepareStatement(Query2);
			pStmt12.setString(1,consumptionid);			
			pStmt12.setInt(2,0);
			pStmt12.setInt(3,0);
			pStmt12.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt12.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt12.setString(6,formVO.getSellerId());
			pStmt12.setString(7,formVO.getSubSellerId());
			//pStmt12.executeQuery();
			pStmt12.executeUpdate();
			
			pStmt13=con.prepareStatement(Query3);
			pStmt13.setString(1,requirementid);			
			pStmt13.setInt(2,0);
			pStmt13.setInt(3,0);
			pStmt13.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt13.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt13.setString(6,formVO.getSellerId());
			pStmt13.setString(7,formVO.getSubSellerId());
			//pStmt13.executeQuery();
			pStmt13.executeUpdate();
			
			pStmt14=con.prepareStatement(Query4);
			pStmt14.setString(1,additionid);			
			pStmt14.setString(2,formVO.getTotal());
			pStmt14.setString(3,formVO.getQuantityTypeOfTotal());
			pStmt14.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt14.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt14.setString(6,formVO.getSellerId());
			pStmt14.setString(7,formVO.getSubSellerId());
			//pStmt14.executeQuery();
			pStmt14.executeUpdate();
			
			pStmt15=con.prepareStatement(Query5);
			pStmt15.setString(1,creditnoteid);			
			pStmt15.setString(2,formVO.getCreditNote());
			pStmt15.setString(3,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt15.setString(4,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt15.setString(5,formVO.getSellerId());
			pStmt15.setString(6,formVO.getSubSellerId());
			//pStmt15.executeQuery();
			pStmt15.executeUpdate();
			
			//inserting data for invoice and transactions tables
			pStmt16 = con.prepareStatement(countQuery6);
			rs16 = pStmt16.executeQuery();
			
			while(rs16.next()){
				temp = rs16.getInt(1);
				
				for(int i=1; i<=temp+1; i++){
					pStmt17 = con.prepareStatement(idQuery6);
					pStmt17.setInt(1,i);
					rs17 = pStmt17.executeQuery();					
					
					if(!rs17.next())
						transactionid = String.valueOf(i);
				}
			}
			
			pStmt18 = con.prepareStatement(countQuery7);
			rs18 = pStmt18.executeQuery();
			
			while(rs18.next()){
				temp = rs18.getInt(1);
				
				for(int i=1; i<=temp+1; i++){
					pStmt19 = con.prepareStatement(idQuery7);
					pStmt19.setInt(1,i);
					rs19 = pStmt19.executeQuery();					
					
					if(!rs19.next())
						invoiceid = String.valueOf(i);
				}
			}
			
			pStmt20=con.prepareStatement(Query6);
			pStmt20.setString(1,transactionid);			
			pStmt20.setString(2,formVO.getSellerId());
			pStmt20.setString(3,formVO.getSubSellerId());
			pStmt20.setString(4,formVO.getTransactionId());
			pStmt20.setString(5,formVO.getAmountPaid());
			pStmt20.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt20.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//pStmt20.executeQuery();
			pStmt20.executeUpdate();
			
			pStmt21=con.prepareStatement(Query7);
			pStmt21.setString(1,invoiceid);			
			pStmt21.setString(2,formVO.getSellerId());
			pStmt21.setString(3,formVO.getSubSellerId());
			pStmt21.setString(4,formVO.getInvoiceId());
			pStmt21.setString(5,formVO.getFilePathOfInvoice());
			pStmt21.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt21.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//pStmt21.executeQuery();
			pStmt21.executeUpdate();
			
			//inserting data into supply and than stock table
			pStmt22 = con.prepareStatement(countQuery8);
			rs22 = pStmt22.executeQuery();
			
			while(rs22.next()){
				temp = rs22.getInt(1);
				
				for(int i=1; i<=temp+1; i++){
					pStmt23 = con.prepareStatement(idQuery8);
					pStmt23.setInt(1,i);
					rs23 = pStmt23.executeQuery();					
					
					if(!rs23.next())
						supplyid = String.valueOf(i);
				}
			}
			
			pStmt24 = con.prepareStatement(countQuery9);
			rs24 = pStmt24.executeQuery();
			
			while(rs24.next()){
				temp = rs24.getInt(1);
				
				for(int i=1; i<=temp+1; i++){
					pStmt25 = con.prepareStatement(idQuery9);
					pStmt25.setInt(1,i);
					rs25 = pStmt25.executeQuery();					
					
					if(!rs25.next())
						stockid = String.valueOf(i);
				}
			}
			
			pStmt26=con.prepareStatement(Query8);
			pStmt26.setString(1,supplyid);			
			pStmt26.setString(2,formVO.getSellerId());
			pStmt26.setString(3,formVO.getSubSellerId());
			pStmt26.setString(4,invoiceid);
			pStmt26.setString(5,transactionid);
			pStmt26.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt26.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//pStmt26.executeQuery();
			pStmt26.executeUpdate();
			
			String ID="KP"+formVO.getProductId();
			
			pStmt27=con.prepareStatement(Query9);
			pStmt27.setString(1,stockid);			
			pStmt27.setString(2,formVO.getSellerId());
			pStmt27.setString(3,formVO.getSubSellerId());
			pStmt27.setString(4,supplyid);
			pStmt27.setString(5,formVO.getProductId());
			pStmt27.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt27.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt27.setString(8,ID);
			//pStmt27.executeQuery();
			pStmt27.executeUpdate();
			
			// inserting data into store data value table
			pStmt28 = con.prepareStatement(countQuery10);
			rs28 = pStmt28.executeQuery();
			
			while(rs28.next()){
				temp = rs28.getInt(1);
				
				for(int i=1; i<=temp+1; i++){
					pStmt29 = con.prepareStatement(idQuery10);
					pStmt29.setInt(1,i);
					rs29 = pStmt29.executeQuery();					
					
					if(!rs29.next())
						storeid = String.valueOf(i);
				}
			}
			
			pStmt30=con.prepareStatement(Query10);
			pStmt30.setString(1,storeid);			
			pStmt30.setString(2,formVO.getSellerId());
			pStmt30.setString(3,formVO.getSubSellerId());
			pStmt30.setString(4,stockid);
			pStmt30.setString(5,availableid);
			pStmt30.setString(6,consumptionid);
			pStmt30.setString(7,requirementid);
			pStmt30.setString(8,additionid);
			pStmt30.setString(9,creditnoteid);
			pStmt30.setString(10,"false");
			pStmt30.setString(11,"btn btn-default btn-on btn-sm");
			pStmt30.setString(12,"btn btn-default btn-off btn-sm active");
			pStmt30.setString(13,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt30.setString(14,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt30.setString(15,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//pStmt30.executeQuery();
			pStmt30.executeUpdate();
			
			//String countQuery11 = "SELECT quantityid, quantity FROM inventory_quantity_type ";
			//String idQuery11 = "INSERT INTO inventory_product_type_conversion ( procuctid, quantityid, quantity, sellerid, subsellerid, created, lastmodified ) VALUES (?,?,?,?,?,?,?) ";
			
			pStmt31 = con.prepareStatement(idQuery11);
			pStmt32 = con.prepareStatement(countQuery11);						
			rs32 = pStmt32.executeQuery();
			
			pStmt33 = con.prepareStatement(countQuery12);
			rs33 = pStmt33.executeQuery();
			while(rs33.next()){
				temp = rs33.getInt(1);
			}
			
			while(rs32.next()){				
				formVO.setQuantityId(rs32.getString("quantityid"));
				formVO.setQuantityPerItem(rs32.getString("quantity"));
												
			if(Integer.parseInt(formVO.getQuantityId())==1){				
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(8,formVO.getSubSellerId());
				pStmt31.executeUpdate();	
				
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,"3");
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();	
			}else if(Integer.parseInt(formVO.getQuantityId())==2){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();	
				
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,"4");
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();	
			}else if(Integer.parseInt(formVO.getQuantityId())==3){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();									
			}else if(Integer.parseInt(formVO.getQuantityId())==4){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();								
			}else if(Integer.parseInt(formVO.getQuantityId())==5){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();					
			}else if(Integer.parseInt(formVO.getQuantityId())==6){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();									
			}else if(Integer.parseInt(formVO.getQuantityId())==7){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();
			}else if(Integer.parseInt(formVO.getQuantityId())==8){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();
			}else if(Integer.parseInt(formVO.getQuantityId())==9){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();									
			}else if(Integer.parseInt(formVO.getQuantityId())==10){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();									
			}else if(Integer.parseInt(formVO.getQuantityId())==11){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();									
			}else if(Integer.parseInt(formVO.getQuantityId())==12){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();								
			}else if(Integer.parseInt(formVO.getQuantityId())==13){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();									
			}else if(Integer.parseInt(formVO.getQuantityId())==14){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();									
			}else if(Integer.parseInt(formVO.getQuantityId())==15){
				pStmt31.setString(1,formVO.getProductId());
				pStmt31.setString(2,formVO.getQuantityPerItem());
				pStmt31.setString(3,formVO.getQuantityPerItem());
				pStmt31.setString(4,formVO.getSellerId());
				pStmt31.setString(5,formVO.getSubSellerId());
				pStmt31.setString(6,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pStmt31.executeUpdate();									
				}
			}
			
			//pStmt31.executeQuery();
			pStmt31.executeUpdate();
												
			//con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
							
			pStmt.setString(1,formVO.getName());
			pStmt.setString(2,formVO.getProductType());
			pStmt.setString(3,formVO.getDescription());
			pStmt.setString(4,formVO.getQuantityId());
			pStmt.setString(5,formVO.getQuantityPerItem());
			pStmt.setString(6,formVO.getQuantityCount());
			pStmt.setString(7,formVO.getTotal());
			pStmt.setString(8,formVO.getVendorId());
			pStmt.setString(9,formVO.getCostPrice());
			pStmt.setString(10,formVO.getAmountPaid());
			pStmt.setString(11,formVO.getAmountPaidTypeId());
			pStmt.setString(12,formVO.getPendingAmount());
			pStmt.setString(13,formVO.getCreditNote());
			pStmt.setString(14,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt.setString(15,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pStmt.setString(16,formVO.getConsumed());
			pStmt.setString(17,formVO.getRequired());
			pStmt.setString(18,"supplied");	
			pStmt.setString(19,ID);	
			pStmt.setString(20,formVO.getProductId());

			temp = pStmt.executeUpdate();
		}
		
		catch(Exception ex){
			
			PreparedStatement pStmta = null;
			PreparedStatement pStmt1a = null;
			PreparedStatement pStmt2a = null;
			PreparedStatement pStmt3a = null;
			PreparedStatement pStmt4a = null;
			PreparedStatement pStmt5a = null;
			PreparedStatement pStmt6a = null;
			PreparedStatement pStmt7a = null;
			PreparedStatement pStmt8a = null;
			PreparedStatement pStmt9a = null;
			PreparedStatement pStmt10a = null;
			
			String deleteQueryProduct="DELETE FROM inventory_kitchen_product WHERE productid=? ";
			//String Query1="DELETE FROM inventory_kitchen_inventory_product WHERE inventoryid=? ";
			//String Query2="DELETE FROM inventory_kitchen_inventory WHERE productid=? ";
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
				int tempDel = 0;
				
				//delete creditnotes
				pStmt1a = con.prepareStatement(deleteQueryCreditNote);
				pStmt1a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt1a.executeUpdate();
				
				//delete requirement
				pStmt2a = con.prepareStatement(deleteQueryRequirement);
				pStmt2a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt2a.executeUpdate();
				
				//delete consumption
				pStmt3a = con.prepareStatement(deleteQueryConsumption);
				pStmt3a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt3a.executeUpdate();
				
				//delete addition
				pStmt4a = con.prepareStatement(deleteQueryAddition);
				pStmt4a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt4a.executeUpdate();
				
				//de;ete available
				pStmt5a = con.prepareStatement(deleteQueryAvailable);
				pStmt5a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt5a.executeUpdate();
				
				//delete invoice
				pStmt6a = con.prepareStatement(deleteQueryInvoice);
				pStmt6a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt6a.executeUpdate();
				
				//delete transaction
				pStmt7a = con.prepareStatement(deleteQueryTransaction);
				pStmt7a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt7a.executeUpdate();
				
				//delete store
				pStmt8a = con.prepareStatement(deleteQueryStore);
				pStmt8a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt8a.executeUpdate();
				
				//delete supply
				pStmt9a = con.prepareStatement(deleteQuerySupply);
				pStmt9a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt9a.executeUpdate();
				
				//delete stock
				pStmt10a = con.prepareStatement(deleteQueryStock);
				pStmt10a.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmt10a.executeUpdate();
				
				//delete procuct
				pStmta = con.prepareStatement(deleteQueryProduct);
				pStmta.setInt(1, Integer.parseInt(formVO.getProductId()));
				
				//if(Integer.parseInt(formVO.getProductId())!=1)
				tempDel = pStmta.executeUpdate();
			}
			catch(Exception ex1){
				ex1.printStackTrace();
			}
			finally{
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
			}
			
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
			dbConnection.close(pStmt21, con);
			dbConnection.close(pStmt22, con);
			dbConnection.close(pStmt23, con);
			dbConnection.close(pStmt24, con);
			dbConnection.close(pStmt25, con);
			dbConnection.close(pStmt26, con);
			dbConnection.close(pStmt27, con);
			dbConnection.close(pStmt28, con);
			dbConnection.close(pStmt29, con);
			dbConnection.close(pStmt30, con);
		}
		
		return temp;	
	}
	
	public Map<String, String> getKitchenProductList(Map<String, String> map) throws SQLException, Exception{
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
	
	public List<ProductFormVO> getKitchenProductMap(ProductFormVO formVO) throws SQLException, Exception {

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
		PreparedStatement pStmt4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;

		boolean available=false;
		String productIds = null;
		int count = 0;

		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";
		String quantityTypeQuery = "SELECT quantity from inventory_quantity_type where quantityid = ? ";
		String countQuery = "SELECT COUNT(*) FROM inventory_kitchen_product";
		String IdQuery = "SELECT productid FROM inventory_kitchen_product where productid = ?";
		String IdQuery1="SELECT productid FROM inventory_kitchen_product";
		
		try{
			con = dbConnection.getConnection();
			
			pStmt3 = con.prepareStatement(countQuery);
			rs3 = pStmt3.executeQuery();
			
			while(rs3.next()){
				count = rs3.getInt(1);
			}
			
			pStmt5 = con.prepareStatement(IdQuery1);
			rs5 = pStmt5.executeQuery();
			List<String> productstr1 = new ArrayList<String>();

			while(rs5.next()){
				productstr1.add(rs5.getString("productid"));
			}
			
			int tempCount=count;
			List<String> countList = new ArrayList<String>();
			
			for(int i=0;i<tempCount;i++){
				pStmt3 = con.prepareStatement(IdQuery);
				pStmt3.setInt(1, Integer.parseInt(productstr1.get(i)));
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					productIds=rs3.getString("productid");							
				}
								
				if(productIds!=null && productIds!=null){
					for(int m=0;m<1;m++){
						countList.add(productIds);
					}
					if(countList.size()<=count){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			for (int i=1;i<countList.size();i++) {
	            String a1 = countList.get(i);
	            String a2 = countList.get(i-1);
	            if (a1.equals(a2)) {
	            	countList.remove(a1);
	            }
	        }
			
			String id = null;
			for(int k=0;k<countList.size();k++){
				if(k<countList.size()){
					if(k==0 && countList.get(k)!=null)
						id=countList.get(k);
					else{
						if(countList.get(k)!=null)
							id=id+","+countList.get(k);
					}
				}
			}
			
			String Query=null;
			
			if(id!=null)
				Query="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND productid IN ("+id+") ";
			else
				Query="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL LIMIT 5 ";

			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created"));
				pStmt2 = con.prepareStatement(quantityTypeQuery);
				pStmt2.setString(1, rs.getString("quantity"));
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs2.getString("quantity"));
				} 

				formVOO.setQuantityCount(rs.getString("quantitycount"));				
				
				pStmt1 = con.prepareStatement(vendorNameQuery);
				pStmt1.setString(1, rs.getString("vendorid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setVendorId(rs1.getString("name"));
				} 
				
				productList.add(formVOO);
			}if(!available){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");
				formVOO.setCreatedDate("----");		
				formVOO.setQuantityPerItem("----");
				formVOO.setQuantityCount("----");							
				formVOO.setVendorId("----");			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, kitchenProductComparator.productComparator);
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
		}
		
		return productList;		
	}
	
	public List<ProductFormVO> getKitchenProductIndexMap(ProductFormVO formVO) throws SQLException, Exception {

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
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		PreparedStatement pStmt6 = null;
		
		boolean available = false;
		String productIds = null;
		int count = 0;
		
		String vendorNameQuery="SELECT name from inventory_kitchen_vendor where vendorid = ? ";

		String quantityTypeQuery="SELECT quantity from inventory_quantity_type where quantityid = ? ";
		String QueryTotalAvailableStock="SELECT available, description from inventory_kitchen_stock_available where availableid = ( SELECT availableid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";
		String QuerySuppliedDate="SELECT addition, description, lastmodified from inventory_kitchen_stock_addition where additionid = ( SELECT additionid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";

		String countQuery="SELECT COUNT(*) FROM inventory_kitchen_product";
		String IdQuery="SELECT productid FROM inventory_kitchen_product where productid = ?";
		
		String IdQuery1="SELECT productid FROM inventory_kitchen_product";
		
		List<String> productstr1 = new ArrayList<String>();

		try{
			con = dbConnection.getConnection();
			
			pStmt3 = con.prepareStatement(countQuery);
			rs3 = pStmt3.executeQuery();
			
			while(rs3.next()){
				count = rs3.getInt(1);
			}
			
			pStmt5 = con.prepareStatement(IdQuery1);
			rs5 = pStmt5.executeQuery();
			
			while(rs5.next()){
				productstr1.add(rs5.getString("productid"));
			}
			
			int tempCount=count;
			List<String> countList = new ArrayList<String>();
			
			for(int i=0;i<tempCount;i++){
				pStmt3 = con.prepareStatement(IdQuery);
				pStmt3.setInt(1, Integer.parseInt(productstr1.get(i)));
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					productIds=rs3.getString("productid");							
				}
								
				if(productIds!=null && productIds!=null){
					for(int m=0;m<1;m++){
						countList.add(productIds);
					}
					if(countList.size()<=count){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			for (int i=1;i<countList.size();i++) {
	            String a1 = countList.get(i);
	            String a2 = countList.get(i-1);
	            if (a1.equals(a2)) {
	            	countList.remove(a1);
	            }
	        }
			
			String id = null;
			for(int k=0;k<countList.size();k++){
				if(k<countList.size()){
					if(k==0 && countList.get(k)!=null)
						id=countList.get(k);
					else{
						if(countList.get(k)!=null)
							id=id+","+countList.get(k);
					}
				}
			}
			
			String Query=null;
			
			if(id!=null)
				Query="SELECT productid, ID, name, created, quantity, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND productid IN ("+id+")";
			else
				Query="SELECT productid, ID, name, created, quantity, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL LIMIT 5";

			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				
				pStmt4 = con.prepareStatement(QuerySuppliedDate);
				pStmt4.setString(1, rs.getString("productid"));
				rs4 = pStmt4.executeQuery();
				
				while(rs4.next()){
					formVOO.setCreatedDate(rs4.getString("lastmodified"));
					formVOO.setQuantityCount(rs4.getString("addition") +" "+ rs4.getString("description"));
				}
								
				pStmt1 = con.prepareStatement(quantityTypeQuery);
				pStmt1.setString(1, rs.getString("quantity"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs1.getString("quantity"));
					
					pStmt2 = con.prepareStatement(QueryTotalAvailableStock);
					pStmt2.setString(1, rs.getString("productid"));
					rs2 = pStmt2.executeQuery();
					
					while(rs2.next()){
						formVOO.setTotal(rs2.getString("available")+" "+rs2.getString("description"));
					} 
				} 				
												
				pStmt3 = con.prepareStatement(vendorNameQuery);
				pStmt3.setString(1, rs.getString("vendorid"));
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					formVOO.setVendorId(rs3.getString("name"));
				} 
				
				productList.add(formVOO);
			}if(!available){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");
				formVOO.setCreatedDate("----");		
				formVOO.setQuantityPerItem("----");
				formVOO.setQuantityCount("----");							
				formVOO.setVendorId("----");			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, kitchenProductComparator.productComparator);
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
		
		return productList;			
	}
	
	public List<ProductFormVO> getKitchenProductsByRecords(ProductFormVO formVO) throws SQLException, Exception {

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
		PreparedStatement pStmt4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		
		boolean available = false;
		String productIds = null;
		int count = 0;
		
		String vendorNameQuery="SELECT name from inventory_kitchen_vendor where vendorid = ? ";

		String quantityTypeQuery="SELECT quantity from inventory_quantity_type where quantityid = ? ";
		String QueryTotalAvailableStock="SELECT available, description from inventory_kitchen_stock_available where availableid = ( SELECT availableid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";
		String QuerySuppliedDate="SELECT addition, description, lastmodified from inventory_kitchen_stock_addition where additionid = ( SELECT additionid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";

		String countQuery="SELECT COUNT(*) FROM inventory_kitchen_product";
		String IdQuery="SELECT productid FROM inventory_kitchen_product where productid = ?";
		String IdQuery1="SELECT productid FROM inventory_kitchen_product";
		
		try{
			con = dbConnection.getConnection();
			
			pStmt3 = con.prepareStatement(countQuery);
			rs3 = pStmt3.executeQuery();
			
			while(rs3.next()){
				count = rs3.getInt(1);
			}
			
			pStmt5 = con.prepareStatement(IdQuery1);
			rs5 = pStmt5.executeQuery();
			List<String> productstr1 = new ArrayList<String>();

			while(rs5.next()){
				productstr1.add(rs5.getString("productid"));
			}
			
			int tempCount=count;
			List<String> countList = new ArrayList<String>();
			
			for(int i=0;i<tempCount;i++){
				pStmt3 = con.prepareStatement(IdQuery);
				pStmt3.setInt(1, Integer.parseInt(productstr1.get(i)));
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					productIds=rs3.getString("productid");							
				}
								
				if(productIds!=null && productIds!=null){
					for(int m=0;m<1;m++){
						countList.add(productIds);
					}
					if(countList.size()<=count){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			for (int i=1;i<countList.size();i++) {
	            String a1 = countList.get(i);
	            String a2 = countList.get(i-1);
	            if (a1.equals(a2)) {
	            	countList.remove(a1);
	            }
	        }		

			String Query=null;
			String id = null;

			if(formVO.getLimit().equalsIgnoreCase("1")){
				for(int k=0;k<5;k++){
					if(k<countList.size()){
						if(k==0 && countList.get(k)!=null)
							id=countList.get(k);
						else{
							if(countList.get(k)!=null)
								id=id+","+countList.get(k);
						}
					}
				}
			}else if(formVO.getLimit().equalsIgnoreCase("2")){
				for(int k=0;k<10;k++){
					if(k<countList.size()){
						if(k==0 && countList.get(k)!=null)
							id=countList.get(k);
						else{
							if(countList.get(k)!=null)
								id=id+","+countList.get(k);
						}
					}
				}
			}else if(formVO.getLimit().equalsIgnoreCase("3")){
				for(int k=0;k<15;k++){
					if(k<countList.size()){
						if(k==0 && countList.get(k)!=null)
							id=countList.get(k);
						else{
							if(countList.get(k)!=null)
								id=id+","+countList.get(k);
						}
					}
				}	
			}else{
				Query="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL LIMIT 5 ";
			}
				
			if(id!=null)
				Query="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND productid IN ("+id+") ";
			else
				Query="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL LIMIT 5 ";

			pStmt = con.prepareStatement(Query);			
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created"));
				
				pStmt1 = con.prepareStatement(quantityTypeQuery);
				pStmt1.setString(1, rs.getString("quantity"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs1.getString("quantity"));
					
					pStmt2 = con.prepareStatement(QueryTotalAvailableStock);
					pStmt2.setString(1, rs.getString("productid"));
					rs2 = pStmt2.executeQuery();
					
					while(rs2.next()){
						formVOO.setTotal(rs2.getString("available")+" "+rs2.getString("description"));
					} 
				} 
				
				formVOO.setQuantityCount(rs.getString("quantitycount"));				
												
				pStmt3 = con.prepareStatement(vendorNameQuery);
				pStmt3.setString(1, rs.getString("vendorid"));
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					formVOO.setVendorId(rs3.getString("name"));
				} 
				
				productList.add(formVOO);
			}if(!available){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");
				formVOO.setCreatedDate("----");		
				formVOO.setQuantityPerItem("----");
				formVOO.setQuantityCount("----");							
				formVOO.setVendorId("----");			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, kitchenProductComparator.productComparator);
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
	
	public List<ProductFormVO> getPagination(ProductFormVO formVO) throws SQLException, Exception {

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
		PreparedStatement pStmt4 = null;
		PreparedStatement pStmt5 = null;
		ResultSet rs5 = null;
		
		boolean available = false;
		String productIds = null;
		int count = 0;
		
		String vendorNameQuery="SELECT name from inventory_kitchen_vendor where vendorid = ? ";

		String quantityTypeQuery="SELECT quantity from inventory_quantity_type where quantityid = ? ";
		String QueryTotalAvailableStock="SELECT available, description from inventory_kitchen_stock_available where availableid = ( SELECT availableid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";
		String QuerySuppliedDate="SELECT addition, description, lastmodified from inventory_kitchen_stock_addition where additionid = ( SELECT additionid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";

		String countQuery="SELECT COUNT(*) FROM inventory_kitchen_product";
		String IdQuery="SELECT productid FROM inventory_kitchen_product where productid = ?";
		String IdQuery1="SELECT productid FROM inventory_kitchen_product";
		
		try{
			con = dbConnection.getConnection();
			
			pStmt3 = con.prepareStatement(countQuery);
			rs3 = pStmt3.executeQuery();
			
			while(rs3.next()){
				count = rs3.getInt(1);
			}
			
			pStmt5 = con.prepareStatement(IdQuery1);
			rs5 = pStmt5.executeQuery();
			List<String> productstr1 = new ArrayList<String>();

			while(rs5.next()){
				productstr1.add(rs5.getString("productid"));
			}
			
			int tempCount=count;
			List<String> countList = new ArrayList<String>();
			
			for(int i=0;i<tempCount;i++){
				pStmt3 = con.prepareStatement(IdQuery);
				pStmt3.setInt(1, Integer.parseInt(productstr1.get(i)));
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					productIds=rs3.getString("productid");							
				}
								
				if(productIds!=null && productIds!=null){
					for(int m=0;m<1;m++){
						countList.add(productIds);
					}
					if(countList.size()<=count){
						continue;
					}
				}else{
					tempCount++;
				}								
			}
			
			for (int i=1;i<countList.size();i++) {
	            String a1 = countList.get(i);
	            String a2 = countList.get(i-1);
	            if (a1.equals(a2)) {
	            	countList.remove(a1);
	            }
	        }		

			String Query=null;
			String id = null;

			if(Integer.parseInt(formVO.getPageFrom())<0){
				for(int k=0;k<5;k++){
					if(k<countList.size()){
						if(k==0 && countList.get(k)!=null)
							id=countList.get(k);
						else{
							if(countList.get(k)!=null)
								id=id+","+countList.get(k);
						}
					}
				}	
			}else if(Integer.parseInt(formVO.getPageFrom())==0){
				for(int k=0;k<5;k++){
					if(k<countList.size()){
						if(k==0 && countList.get(k)!=null)
							id=countList.get(k);
						else{
							if(countList.get(k)!=null)
								id=id+","+countList.get(k);
						}
					}
				}	
			}else if(Integer.parseInt(formVO.getPageFrom())>0){
				int cnt = Integer.parseInt(formVO.getPageFrom());
				for(int k=cnt;k<cnt+5;k++){
					if(k<countList.size()){
						if(k==0 && countList.get(k)!=null)
							id=countList.get(k);
						else{
							if(countList.get(k)!=null)
								id=id+","+countList.get(k);
						}
					}
				}	
			}else{
				Query="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL LIMIT 5 ";
			}				
			
			if(id!=null)
				Query="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND productid IN ("+id+") ";
			else
				Query="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL LIMIT 5 ";

			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created"));
				
				pStmt1 = con.prepareStatement(quantityTypeQuery);
				pStmt1.setString(1, rs.getString("quantity"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs1.getString("quantity"));
					
					pStmt2 = con.prepareStatement(QueryTotalAvailableStock);
					pStmt2.setString(1, rs.getString("productid"));
					rs2 = pStmt2.executeQuery();
					
					while(rs2.next()){
						formVOO.setTotal(rs2.getString("available")+" "+rs2.getString("description"));
					} 
				} 
				
				formVOO.setQuantityCount(rs.getString("quantitycount"));				
												
				pStmt3 = con.prepareStatement(vendorNameQuery);
				pStmt3.setString(1, rs.getString("vendorid"));
				rs3 = pStmt3.executeQuery();
				
				while(rs3.next()){
					formVOO.setVendorId(rs3.getString("name"));
				} 
				
				productList.add(formVOO);
			}if(!available){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId("Unavailable");
				formVOO.setID("Unavailable");
				formVOO.setName("----");
				formVOO.setCreatedDate("----");		
				formVOO.setQuantityPerItem("----");
				formVOO.setQuantityCount("----");							
				formVOO.setVendorId("----");			
				productList.add(formVOO);
			}
			
			Collections.sort(productList, kitchenProductComparator.productComparator);
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
		}
		
		return productList;			
	}
	
	public List<ProductFormVO> getKitchenProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception {
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
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pStmt5 = null;
		
		boolean available=false;
		
		String Query1="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND created >= '"+formVO.getFromDate()+"' AND created <='"+formVO.getToDate()+"' ";
		String vendorNameQuery ="SELECT name from inventory_kitchen_vendor where vendorid = ? ";
		String quantityTypeQuery = "SELECT quantity from inventory_quantity_type where quantityid = ? ";
		String QueryTotalAvailableStock="SELECT available, description from inventory_kitchen_stock_available where availableid = ( SELECT availableid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query1);
			rs = pStmt.executeQuery();
			
				while(rs.next()){
					available=true;
					ProductFormVO formVOO = new ProductFormVO();
					formVOO.setProductId(rs.getString("productid"));
					formVOO.setID(rs.getString("ID"));
					formVOO.setName(rs.getString("name"));
					formVOO.setCreatedDate(rs.getString("created"));
					
					pStmt1 = con.prepareStatement(quantityTypeQuery);
					pStmt1.setString(1, rs.getString("quantity"));
					rs1 = pStmt1.executeQuery();
					
					while(rs1.next()){
						formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs1.getString("quantity"));
					} 
					
					pStmt2 = con.prepareStatement(quantityTypeQuery);
					pStmt2.setString(1, rs.getString("quantity"));
					rs2 = pStmt2.executeQuery();
					
					while(rs2.next()){
						formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs2.getString("quantity"));
						
						pStmt3 = con.prepareStatement(QueryTotalAvailableStock);
						pStmt3.setString(1, rs.getString("productid"));
						rs3 = pStmt3.executeQuery();
						
						while(rs3.next()){
							formVOO.setTotal(rs3.getString("available")+" "+rs3.getString("description"));
						} 
					} 
					
					formVOO.setQuantityCount(rs.getString("quantitycount"));				
					
					pStmt4 = con.prepareStatement(vendorNameQuery);
					pStmt4.setString(1, rs.getString("vendorid"));
					rs4 = pStmt4.executeQuery();
					
					while(rs4.next()){
						formVOO.setVendorId(rs4.getString("name"));
					} 			
					productList.add(formVOO);
				}
				
				if(!available){								
						ProductFormVO formVOO = new ProductFormVO();						
						formVOO.setProductId("Unavailable");
						formVOO.setID("Unavailable");
						formVOO.setName("----");
						formVOO.setCreatedDate("----");
						formVOO.setQuantityPerItem("----");						
						formVOO.setQuantityPerItem("----");													
						formVOO.setTotal("----");															
						formVOO.setQuantityCount("----");																
						formVOO.setVendorId("----");
						 			
						productList.add(formVOO);
					}
			Collections.sort(productList, kitchenProductComparator.productComparator);
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
		}
		
		return productList;			
	}

	@Override
	public List<ProductFormVO> getKitchenProductsByVendor(ProductFormVO formVO) throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pStmt2= null;
		ResultSet rs2 = null;
		PreparedStatement pStmt3 = null;
		ResultSet rs3 = null;
		boolean available = false;
		
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		
		PreparedStatement pStmt5 = null;
		
		String Query1="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND vendorid= "+formVO.getVendorId();
		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";
		String quantityTypeQuery = "SELECT quantity from inventory_quantity_type where quantityid = ? ";
		String QueryTotalAvailableStock="SELECT available, description from inventory_kitchen_stock_available where availableid = ( SELECT availableid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query1);
			rs = pStmt.executeQuery();
			
				while(rs.next()){
					available=true;
					ProductFormVO formVOO = new ProductFormVO();
					formVOO.setProductId(rs.getString("productid"));
					formVOO.setID(rs.getString("ID"));
					formVOO.setName(rs.getString("name"));
					formVOO.setCreatedDate(rs.getString("created"));
					
					pStmt1 = con.prepareStatement(quantityTypeQuery);
					pStmt1.setString(1, rs.getString("quantity"));
					rs1 = pStmt1.executeQuery();
					
					while(rs1.next()){
						formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs1.getString("quantity"));
					} 
					
					pStmt2 = con.prepareStatement(quantityTypeQuery);
					pStmt2.setString(1, rs.getString("quantity"));
					rs2 = pStmt2.executeQuery();
					
					while(rs2.next()){
						formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs2.getString("quantity"));
						
						pStmt3 = con.prepareStatement(QueryTotalAvailableStock);
						pStmt3.setString(1, rs.getString("productid"));
						rs3 = pStmt3.executeQuery();
						
						while(rs3.next()){
							formVOO.setTotal(rs3.getString("available")+" "+rs3.getString("description"));
						} 
					} 
					
					formVOO.setQuantityCount(rs.getString("quantitycount"));				
					
					pStmt4 = con.prepareStatement(vendorNameQuery);
					pStmt4.setString(1, rs.getString("vendorid"));
					rs4 = pStmt4.executeQuery();
					
					while(rs4.next()){
						formVOO.setVendorId(rs4.getString("name"));
					} 			
					productList.add(formVOO);
				}	
				
				if(!available){									
						ProductFormVO formVOO = new ProductFormVO();
						formVOO.setProductId("Unavailable");
						formVOO.setID("Unavailable");
						formVOO.setName("----");
						formVOO.setCreatedDate("----");
						formVOO.setQuantityPerItem("----");						
						formVOO.setQuantityPerItem("----");													
						formVOO.setTotal("----");															
						formVOO.setQuantityCount("----");																
						formVOO.setVendorId("----");
						productList.add(formVOO);
					}
			
			Collections.sort(productList, kitchenProductComparator.productComparator);
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
		}	
		return productList;			
	}

	@Override
	public List<ProductFormVO> getKitchenProductsById(ProductFormVO formVO) throws SQLException, Exception {
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
		
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		
		PreparedStatement pStmt5 = null;

		String Query1="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND productId= "+formVO.getProductId();
		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";
		String quantityTypeQuery = "SELECT quantity from inventory_quantity_type where quantityid = ? ";
		String QueryTotalAvailableStock="SELECT available, description from inventory_kitchen_stock_available where availableid = ( SELECT availableid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query1);
			rs = pStmt.executeQuery();
						
			while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created"));
				
				pStmt1 = con.prepareStatement(quantityTypeQuery);
				pStmt1.setString(1, rs.getString("quantity"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs1.getString("quantity"));
				} 
				
				pStmt2 = con.prepareStatement(quantityTypeQuery);
				pStmt2.setString(1, rs.getString("quantity"));
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs2.getString("quantity"));
					
					pStmt3 = con.prepareStatement(QueryTotalAvailableStock);
					pStmt3.setString(1, rs.getString("productid"));
					rs3 = pStmt3.executeQuery();
					
					while(rs3.next()){
						formVOO.setTotal(rs3.getString("available")+" "+rs3.getString("description"));
					} 
				} 
								
				formVOO.setQuantityCount(rs.getString("quantitycount"));				
				
				pStmt4 = con.prepareStatement(vendorNameQuery);
				pStmt4.setString(1, rs.getString("vendorid"));
				rs4 = pStmt4.executeQuery();
				
				while(rs4.next()){
					formVOO.setVendorId(rs4.getString("name"));
				} 			
				productList.add(formVOO);
			}
				
			if(!available){
				
					ProductFormVO formVOO = new ProductFormVO();
					formVOO.setProductId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setName("----");
					formVOO.setCreatedDate("----");
					formVOO.setQuantityPerItem("----");						
					formVOO.setQuantityPerItem("----");													
					formVOO.setTotal("----");															
					formVOO.setQuantityCount("----");																
					formVOO.setVendorId("----");
					productList.add(formVOO);
				}			
			
			Collections.sort(productList, kitchenProductComparator.productComparator);

		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
			dbConnection.close(pStmt4, con);
			dbConnection.close(pStmt5, con);
		}
		
		return productList;			
	}

	public List<ProductFormVO> getkitchenProductsByName(ProductFormVO formVO) throws SQLException, Exception {
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
		
		PreparedStatement pStmt4 = null;
		ResultSet rs4 = null;
		
		PreparedStatement pStmt5 = null;

		boolean available = false;
		
		String Query1="SELECT productid, ID, name, created, quantityunit, quantitycount, quantity, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND name= '"+formVO.getName()+"'";

		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";
		String quantityTypeQuery = "SELECT quantity from inventory_quantity_type where quantityid = ? ";
		String QueryTotalAvailableStock="SELECT available, description from inventory_kitchen_stock_available where availableid = ( SELECT availableid from inventory_kitchen_store WHERE stockid IN (SELECT stockid from inventory_kitchen_product_stock WHERE productid =? ) ORDER BY lastmodified DESC LIMIT 1 ) ORDER BY lastmodified DESC LIMIT 1  ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query1);
			rs = pStmt.executeQuery();

		while(rs.next()){
				available=true;
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setID(rs.getString("ID"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created"));
				
				pStmt1 = con.prepareStatement(quantityTypeQuery);
				pStmt1.setString(1, rs.getString("quantity"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs1.getString("quantity"));
				} 
				
				pStmt2 = con.prepareStatement(quantityTypeQuery);
				pStmt2.setString(1, rs.getString("quantity"));
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					formVOO.setQuantityPerItem(rs.getString("quantityunit")+" "+rs2.getString("quantity"));
					
					pStmt3 = con.prepareStatement(QueryTotalAvailableStock);
					pStmt3.setString(1, rs.getString("productid"));
					rs3 = pStmt3.executeQuery();
					
					while(rs3.next()){
						formVOO.setTotal(rs3.getString("available")+" "+rs3.getString("description"));
					} 
				} 
								
				formVOO.setQuantityCount(rs.getString("quantitycount"));				
				
				pStmt4 = con.prepareStatement(vendorNameQuery);
				pStmt4.setString(1, rs.getString("vendorid"));
				rs4 = pStmt4.executeQuery();
				
				while(rs4.next()){
					formVOO.setVendorId(rs4.getString("name"));
				} 			
				productList.add(formVOO);
			}
			
			if(!available){							
					ProductFormVO formVOO = new ProductFormVO();
					formVOO.setProductId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setName("----");
					formVOO.setCreatedDate("----");
					formVOO.setQuantityPerItem("----");						
					formVOO.setQuantityPerItem("----");													
					formVOO.setTotal("----");															
					formVOO.setQuantityCount("----");																
					formVOO.setVendorId("----");
					productList.add(formVOO);
				}
			Collections.sort(productList, kitchenProductComparator.productComparator);	
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
			dbConnection.close(pStmt1, con);
			dbConnection.close(pStmt2, con);
			dbConnection.close(pStmt3, con);
			dbConnection.close(pStmt4, con);
			dbConnection.close(pStmt5, con);
		}		
		return productList;			
	}
	
	public int deleteKitchenProduct(ProductFormVO formVO) throws SQLException, Exception{

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

		int ret = 0;
		
		String deleteQueryProduct="DELETE FROM inventory_kitchen_product WHERE productid=? ";
		//String Query1="DELETE FROM inventory_kitchen_inventory_product WHERE inventoryid=? ";
		//String Query2="DELETE FROM inventory_kitchen_inventory WHERE productid=? ";
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
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt1.executeUpdate();
			
			//delete requirement
			pStmt2 = con.prepareStatement(deleteQueryRequirement);
			pStmt2.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt2.executeUpdate();
			
			//delete consumption
			pStmt3 = con.prepareStatement(deleteQueryConsumption);
			pStmt3.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt3.executeUpdate();
			
			//delete addition
			pStmt4 = con.prepareStatement(deleteQueryAddition);
			pStmt4.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt4.executeUpdate();
			
			//de;ete available
			pStmt5 = con.prepareStatement(deleteQueryAvailable);
			pStmt5.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt5.executeUpdate();
			
			//delete invoice
			pStmt6 = con.prepareStatement(deleteQueryInvoice);
			pStmt6.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt6.executeUpdate();
			
			//delete transaction
			pStmt7 = con.prepareStatement(deleteQueryTransaction);
			pStmt7.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt7.executeUpdate();
			
			//delete store
			pStmt8 = con.prepareStatement(deleteQueryStore);
			pStmt8.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt8.executeUpdate();
			
			//delete supply
			pStmt9 = con.prepareStatement(deleteQuerySupply);
			pStmt9.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt9.executeUpdate();
			
			//delete stock
			pStmt10 = con.prepareStatement(deleteQueryStock);
			pStmt10.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
				ret = pStmt10.executeUpdate();
			
			//delete procuct
			pStmt = con.prepareStatement(deleteQueryProduct);
			pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			//if(Integer.parseInt(formVO.getProductId())!=1)
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
		}
		
		return ret;			
	}
	
}
