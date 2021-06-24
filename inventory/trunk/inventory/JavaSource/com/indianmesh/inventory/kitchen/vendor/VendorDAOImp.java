package com.indianmesh.inventory.kitchen.vendor;

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

import org.apache.commons.lang.RandomStringUtils;

import com.indianmesh.inventory.kitchen.stock.ProductFormVO;
import com.indianmesh.inventory.utils.ProductComparator;
import com.indianmesh.inventory.utils.VendorComparator;
import com.indianmesh.inventory.utils.dbConnection.DBConnection;

public class VendorDAOImp implements VendorBaseDAO {
	
	VendorComparator vendorComparator = new VendorComparator();
	ProductComparator productComparator = new ProductComparator();
	
	public VendorFormVO addKitchenVendor(VendorFormVO formVO) throws SQLException, Exception {
		
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs1 = null;
		String vendorId = null;
		int temp = 0;
		
		String countQuery="SELECT COUNT(*) FROM inventory_kitchen_vendor "; 
		String idQuery="SELECT vendorid FROM inventory_kitchen_vendor where vendorid=? ";
		String Query = "INSERT INTO inventory_kitchen_vendor (vendorid,sellorid,subsellorid,created,lastmodified,ID) VALUES (?,?,?,?,?,?) ";
			
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
						vendorId = String.valueOf(i);
				}
			}
			
			String ID="KV"+vendorId;
			pStmt2=con.prepareStatement(Query);
			pStmt2.setString(1,vendorId);
			pStmt2.setString(2,formVO.getSellerId());
			pStmt2.setString(3,formVO.getSubSellerId());
			pStmt2.setDate(4,new java.sql.Date(new Date().getTime()));
			pStmt2.setDate(5,new java.sql.Date(new Date().getTime()));
			pStmt2.setString(6,ID);
			pStmt2.executeUpdate();
			
			formVO.setVendorId(vendorId);
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
	
	public VendorFormVO editkitchenVendor(VendorFormVO formVO) throws SQLException, Exception {
			
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		String Query="SELECT vendorid,sellorid,subsellorid,name,phone,emailid,tin,vat,pan,servicetaxno,contactpersonname,contactphone,contactemail,billingaddress,billingcountryid,billingstateid,billingcity,billingpincode,shippingaddress,shippingcountryid,shippingstateid,shippingcity,shippingpincode,gst,ballance,created,lastmodified,typeid,ID FROM inventory_kitchen_vendor WHERE vendorid=? ";
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			pStmt.setInt(1, Integer.parseInt(formVO.getVendorId()));
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				formVO.setID(rs.getString("ID"));			
				formVO.setVendorId(rs.getString("vendorid"));
				formVO.setName(rs.getString("name"));
				formVO.setType(rs.getString("typeid"));
				formVO.setPhoneNo(rs.getString("phone"));
				formVO.setEmailId(rs.getString("emailid"));
				formVO.setTin(rs.getString("tin"));
				formVO.setVatNo(rs.getString("vat"));
				formVO.setPan(rs.getString("pan"));
				formVO.setGst(rs.getString("gst"));
				formVO.setServiceTaxNo(rs.getString("servicetaxno"));
				formVO.setContactPerson(rs.getString("contactpersonname"));
				formVO.setContactPhone(rs.getString("contactphone"));
				formVO.setContactEmail(rs.getString("contactemail"));
				formVO.setBillingAddress(rs.getString("billingaddress"));
				formVO.setBillingCountryId(rs.getString("billingcountryid"));
				formVO.setBillingStateId(rs.getString("billingstateid"));
				formVO.setBillingCityId(rs.getString("billingcity"));
				formVO.setBillingPinCode(rs.getString("billingpincode"));
				formVO.setShippingAddress(rs.getString("shippingaddress"));
				formVO.setShippingCountryId(rs.getString("shippingcountryid"));
				formVO.setShippingStateId(rs.getString("shippingstateid"));
				formVO.setShippingCityId(rs.getString("shippingcity"));
				formVO.setShippingPinCode(rs.getString("shippingpincode"));				
				formVO.setBallance(rs.getString("ballance"));								
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
	
	public String updateKitchenVendor(VendorFormVO formVO) throws SQLException, Exception {
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
					
		String Query="UPDATE inventory_kitchen_vendor SET sellorid=?, subsellorid=?, name=?, phone=?, emailid=?, tin=?, vat=?, pan=?, servicetaxno=?, contactpersonname=?, "
				+ "contactphone=?, contactemail=?, billingaddress=?, billingcountryid=?, billingstateid=?, billingcity=?, billingpincode=?, shippingaddress=?, "
				+ "shippingcountryid=?, shippingstateid=?, shippingcity=?, shippingpincode=?, gst=?, ballance=?, created=?, lastmodified=?, typeid=? "
				+ "WHERE vendorid=? ";

		try{
			con = dbConnection.getConnection();
			
			pStmt = con.prepareStatement(Query);
			
			pStmt.setString(1,formVO.getSellerId());
			pStmt.setString(2,formVO.getSubSellerId());
			pStmt.setString(3,formVO.getName());
			pStmt.setString(4,formVO.getPhoneNo());
			pStmt.setString(5,formVO.getEmailId());
			pStmt.setString(6,formVO.getTin());
			pStmt.setString(7,formVO.getVatNo());
			pStmt.setString(8,formVO.getPan());
			pStmt.setString(9,formVO.getServiceTaxNo());
			pStmt.setString(10,formVO.getContactPerson());
			pStmt.setString(11,formVO.getContactPhone());
			pStmt.setString(12,formVO.getContactEmail());
			pStmt.setString(13,formVO.getBillingAddress());
			pStmt.setString(14,formVO.getBillingCountryId());
			pStmt.setString(15,formVO.getBillingStateId());
			pStmt.setString(16,formVO.getBillingCityId());
			pStmt.setString(17,formVO.getBillingPinCode());
			pStmt.setString(18,formVO.getShippingAddress());
			pStmt.setString(19,formVO.getShippingCountryId());
			pStmt.setString(20,formVO.getShippingStateId());
			pStmt.setString(21,formVO.getShippingCityId());
			pStmt.setString(22,formVO.getShippingPinCode());
			pStmt.setString(23,formVO.getGst());
			pStmt.setString(24,formVO.getBallance());
			pStmt.setDate(25,new java.sql.Date(new Date().getTime()));	
			pStmt.setDate(26,new java.sql.Date(new Date().getTime()));
			pStmt.setString(27,formVO.getType());
			pStmt.setString(28,formVO.getVendorId());
			
			pStmt.executeUpdate();
				
		}
		
		catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		
		finally{
			dbConnection.close(pStmt, con);
		}
		return formVO.getVendorId();
	}
	
	public VendorFormVO viewKitchenVendor(VendorFormVO formVO) throws SQLException, Exception {
			
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
		
		String Query= "SELECT vendorid, ID, sellorid, subsellorid, name, phone, emailid, tin, vat, pan, servicetaxno, contactpersonname, contactphone, contactemail, billingaddress, billingcountryid, billingstateid, billingcity, billingpincode, shippingaddress, shippingcountryid, shippingstateid, shippingcity, shippingpincode, gst, ballance, created, lastmodified, typeid from inventory_kitchen_vendor WHERE vendorid=? ";

		String billingCountryNameQuery= "SELECT nationality from inventory_nationality where nationalityid = ? ";
		String billingStateNameQuery= "SELECT domissile from inventory_domissile where domissileid = ? ";
		
		String shippingCountryNameQuery= "SELECT nationality from inventory_nationality where nationalityid = ? ";
		String shippingStateNameQuery= "SELECT domissile from inventory_domissile where domissileid = ? ";
		
		String vendorTypeQuery= "SELECT type from inventory_vendor_type where typeid = ? ";
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			pStmt.setInt(1, Integer.parseInt(formVO.getVendorId()));
			rs = pStmt.executeQuery();
			
			while(rs.next()){
							
				formVO.setVendorId(rs.getString("ID"));
				formVO.setSellerId(rs.getString("sellorid"));
				formVO.setSubSellerId(rs.getString("subsellorid"));
				formVO.setName(rs.getString("name"));
				
				pStmt5 = con.prepareStatement(vendorTypeQuery);
				pStmt5.setString(1, rs.getString("typeid"));
				rs5 = pStmt5.executeQuery();
				while(rs5.next()){
					formVO.setType(rs5.getString("type"));
				}
				
				formVO.setPhoneNo(rs.getString("phone"));
				formVO.setEmailId(rs.getString("emailid"));
				formVO.setTin(rs.getString("tin"));
				formVO.setVatNo(rs.getString("vat"));
				formVO.setPan(rs.getString("pan"));
				formVO.setGst(rs.getString("gst"));
				formVO.setServiceTaxNo(rs.getString("servicetaxno"));
				formVO.setContactPerson(rs.getString("contactpersonname"));
				formVO.setContactPhone(rs.getString("contactphone"));
				formVO.setContactEmail(rs.getString("contactemail"));
				formVO.setBillingAddress(rs.getString("billingaddress"));
								
				pStmt1 = con.prepareStatement(billingCountryNameQuery);
				pStmt1.setString(1, rs.getString("billingcountryid"));
				rs1 = pStmt1.executeQuery();
				while(rs1.next()){
					formVO.setBillingCountryId(rs1.getString("nationality"));
				}
				
				pStmt2 = con.prepareStatement(billingStateNameQuery);
				pStmt2.setString(1, rs.getString("billingstateid"));
				rs2 = pStmt2.executeQuery();
				while(rs2.next()){
					formVO.setBillingStateId(rs2.getString("domissile"));
				}
								
				formVO.setBillingCityId(rs.getString("billingcity"));
				formVO.setBillingPinCode(rs.getString("billingpincode"));
				formVO.setShippingAddress(rs.getString("shippingaddress"));
								
				pStmt3 = con.prepareStatement(shippingCountryNameQuery);
				pStmt3.setString(1, rs.getString("shippingcountryid"));
				rs3 = pStmt3.executeQuery();
				while(rs3.next()){
					formVO.setShippingCountryId(rs3.getString("nationality"));
				}
				
				pStmt4 = con.prepareStatement(shippingStateNameQuery);
				pStmt4.setString(1, rs.getString("shippingstateid"));
				rs4 = pStmt4.executeQuery();
				while(rs4.next()){
					formVO.setShippingStateId(rs4.getString("domissile"));
				}
					
				formVO.setShippingCityId(rs.getString("shippingcity"));
				formVO.setShippingPinCode(rs.getString("shippingpincode"));			
				
				formVO.setBallance(rs.getString("ballance"));								
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
		}
		
		return formVO;			
	}
	
	public VendorFormVO getSupplyKitchenProductsFormByVendorId(VendorFormVO formVO) throws SQLException, Exception {
		
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
		boolean available=false;
		
		String Query= "SELECT vendorid, ID, sellorid, subsellorid, name, phone, emailid, tin, vat, pan, servicetaxno, contactpersonname, contactphone, contactemail, billingaddress, billingcountryid, billingstateid, billingcity, billingpincode, shippingaddress, shippingcountryid, shippingstateid, shippingcity, shippingpincode, gst, ballance, created, lastmodified, typeid from inventory_kitchen_vendor WHERE vendorid=? ";

		String billingCountryNameQuery= "SELECT nationality from inventory_nationality where nationalityid = ? ";
		String billingStateNameQuery= "SELECT domissile from inventory_domissile where domissileid = ? ";
		
		String shippingCountryNameQuery= "SELECT nationality from inventory_nationality where nationalityid = ? ";
		String shippingStateNameQuery= "SELECT domissile from inventory_domissile where domissileid = ? ";
		
		String vendorTypeQuery= "SELECT type from inventory_vendor_type where typeid = ? ";
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			pStmt.setInt(1, Integer.parseInt(formVO.getVendorId()));
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				available=true;			
				formVO.setVendorId(formVO.getVendorId());
				formVO.setID(rs.getString("ID"));
				formVO.setSellerId(rs.getString("sellorid"));
				formVO.setSubSellerId(rs.getString("subsellorid"));
				formVO.setName(rs.getString("name"));
				
				pStmt5 = con.prepareStatement(vendorTypeQuery);
				pStmt5.setString(1, rs.getString("typeid"));
				rs5 = pStmt5.executeQuery();
				while(rs5.next()){
					formVO.setType(rs5.getString("type"));
				}
				
				formVO.setPhoneNo(rs.getString("phone"));
				formVO.setEmailId(rs.getString("emailid"));
				formVO.setTin(rs.getString("tin"));
				formVO.setVatNo(rs.getString("vat"));
				formVO.setPan(rs.getString("pan"));
				formVO.setGst(rs.getString("gst"));
				formVO.setServiceTaxNo(rs.getString("servicetaxno"));
				formVO.setContactPerson(rs.getString("contactpersonname"));
				formVO.setContactPhone(rs.getString("contactphone"));
				formVO.setContactEmail(rs.getString("contactemail"));
				formVO.setBillingAddress(rs.getString("billingaddress"));
								
				pStmt1 = con.prepareStatement(billingCountryNameQuery);
				pStmt1.setString(1, rs.getString("billingcountryid"));
				rs1 = pStmt1.executeQuery();
				while(rs1.next()){
					formVO.setBillingCountryId(rs1.getString("nationality"));
				}
				
				pStmt2 = con.prepareStatement(billingStateNameQuery);
				pStmt2.setString(1, rs.getString("billingstateid"));
				rs2 = pStmt2.executeQuery();
				while(rs2.next()){
					formVO.setBillingStateId(rs2.getString("domissile"));
				}
								
				formVO.setBillingCityId(rs.getString("billingcity"));
				formVO.setBillingPinCode(rs.getString("billingpincode"));
				formVO.setShippingAddress(rs.getString("shippingaddress"));
								
				pStmt3 = con.prepareStatement(shippingCountryNameQuery);
				pStmt3.setString(1, rs.getString("shippingcountryid"));
				rs3 = pStmt3.executeQuery();
				while(rs3.next()){
					formVO.setShippingCountryId(rs3.getString("nationality"));
				}
				
				pStmt4 = con.prepareStatement(shippingStateNameQuery);
				pStmt4.setString(1, rs.getString("shippingstateid"));
				rs4 = pStmt4.executeQuery();
				while(rs4.next()){
					formVO.setShippingStateId(rs4.getString("domissile"));
				}
					
				formVO.setShippingCityId(rs.getString("shippingcity"));
				formVO.setShippingPinCode(rs.getString("shippingpincode"));			
				
				formVO.setBallance(rs.getString("ballance"));	
				formVO.setLastModifiedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				formVO.setInvoiceNo(RandomStringUtils.randomAlphanumeric(23).toUpperCase());
				formVO.setTransactionNo(RandomStringUtils.randomAlphanumeric(23).toUpperCase());
			}if(!available){
				formVO.setVendorId("Unavailable");
				formVO.setID("Unavailable");
				formVO.setSellerId("----");
				formVO.setSubSellerId("----");
				formVO.setName("----");							
				formVO.setType("----");								
				formVO.setPhoneNo("----");
				formVO.setEmailId("----");
				formVO.setTin("----");
				formVO.setVatNo("----");
				formVO.setPan("----");
				formVO.setGst("----");
				formVO.setServiceTaxNo("----");
				formVO.setContactPerson("----");
				formVO.setContactPhone("----");
				formVO.setContactEmail("----");
				formVO.setBillingAddress("----");											
				formVO.setBillingCountryId("----");											
				formVO.setBillingStateId("----");												
				formVO.setBillingCityId("----");
				formVO.setBillingPinCode("----");
				formVO.setShippingAddress("----");											
				formVO.setShippingCountryId("----");									
				formVO.setShippingStateId("----");								
				formVO.setShippingCityId("----");
				formVO.setShippingPinCode("----");						
				formVO.setBallance("----");	
				formVO.setLastModifiedDate("----");
				formVO.setInvoiceNo("----");
				formVO.setTransactionNo("----");
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
		}
		
		return formVO;			
	}
	
	public List<ProductFormVO> getKitchenProductSupplyIndexMapByVendorId(ProductFormVO formVO) throws SQLException, Exception {
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
		ResultSet rs14 = null;
		
		String tempStockId = null;
		boolean available=false;
		
		String Query0="SELECT productid from inventory_kitchen_product_stock where stockid = ( SELECT stockid from inventory_kitchen_store where storeid=? ) ";
		String Query1="SELECT name, ID from inventory_kitchen_product where productid = ? ";
		String Query2="SELECT available, description, lastcount from inventory_kitchen_stock_available where availableid = ? ";
		String Query3="SELECT consumption from inventory_kitchen_stock_consumtion where consumptionId = ? ";
		String Query4="SELECT requirement from inventory_kitchen_stock_requirement where requirementId = ? ";
		String Query5="SELECT addition, price, reminder from inventory_kitchen_stock_addition where additionId = ? ";
		String Query6="SELECT creditnote from inventory_kitchen_stock_creditnote where creditnoteId = ? ";
		String Query7="SELECT quantity from inventory_quantity_type where quantityid = ( SELECT quantity from inventory_kitchen_product WHERE productid = ( SELECT productid from inventory_kitchen_product_stock where stockid=? ))";		
		String Query8="SELECT productid FROM inventory_kitchen_product WHERE vendorid = ?";
		String Query9="SELECT stockid from inventory_kitchen_product_stock WHERE productid = ? ORDER BY lastmodified DESC LIMIT 1 ";
		
		try{			
			con = dbConnection.getConnection();
			
			pStmt12 = con.prepareStatement(Query8);
			pStmt12.setString(1, formVO.getVendorId());
			rs12 = pStmt12.executeQuery();
			int i = 1;
			
			while(rs12.next()){		
				pStmt13 = con.prepareStatement(Query9);
				pStmt13.setString(1, rs12.getString("productid"));
				rs13 = pStmt13.executeQuery();				
				while(rs13.next()){
					if(i==1){
						tempStockId=rs13.getString("stockid");
						i++;
					}else{
						tempStockId=tempStockId +","+ rs13.getString("stockid");
						i++;
					}
				}			
			}
			
			String Query="SELECT DISTINCT storeid, sellerid, subsellerid, stockid, availableid, consumptionid, requirementid, additionid, creditnoteid, status, statuson, statusoff, created, lastmodified from inventory_kitchen_store WHERE stockid IN ("+tempStockId+") ORDER BY stockid ASC, lastmodified DESC ";
			
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
					formVOO.setProductId(rs10.getString("productId"));
					pStmt14 = con.prepareStatement(Query1);
					pStmt14.setString(1, rs10.getString("productId"));
					rs14 = pStmt14.executeQuery();			
					while(rs14.next()){
						formVOO.setID(rs14.getString("ID"));
					}
				}
									
				pStmt9 = con.prepareStatement(Query1);
				pStmt9.setString(1, formVOO.getProductId());
				rs9 = pStmt9.executeQuery();			
				while(rs9.next()){
					formVOO.setProductName(rs9.getString("name"));
				}
				
				pStmt4 = con.prepareStatement(Query2);
				pStmt4.setString(1, rs2.getString("availableid"));
				rs4 = pStmt4.executeQuery();
				String requestedQuantityType = null;
				while(rs4.next()){
					formVOO.setAvailableId(rs4.getString("available") +" "+ rs4.getString("description"));
					
					pStmt11 = con.prepareStatement(Query7);
					pStmt11.setString(1, rs2.getString("stockid"));
					rs11 = pStmt11.executeQuery();	
					
					while(rs11.next()){
						formVOO.setLastCount(rs4.getString("lastcount") +" "+ rs11.getString("quantity") );
						requestedQuantityType = rs11.getString("quantity");
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
					formVOO.setRequirementId(rs6.getString("requirement") +" "+ requestedQuantityType ); 
				}
				
				pStmt7 = con.prepareStatement(Query5);
				pStmt7.setString(1, rs2.getString("additionid"));
				rs7 = pStmt7.executeQuery();			
				while(rs7.next()){
					formVOO.setAdditionId(rs7.getString("addition"));
					formVOO.setPrice(rs7.getString("price"));
					formVOO.setPrice(rs7.getString("reminder"));
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
			dbConnection.close(pStmt14, con);
		}
		
		return productList;			
	}

	public Map<String, String> getKitchenVendorList(Map<String, String> map) throws SQLException,Exception{
			
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
						
			String vendorListQuery="SELECT vendorid, name FROM inventory_kitchen_vendor ";	
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(vendorListQuery);
				rs = pStmt.executeQuery();
				
				while(rs.next())
				{
					map.put(rs.getString("vendorid"), rs.getString("name"));
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
	
		public Map<String, String> getSupplierList(Map<String, String> map) throws SQLException,Exception{
			
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
						
			String vendorListQuery="SELECT vendorid, name FROM inventory_kitchen_vendor WHERE typeid = 2 ";	
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(vendorListQuery);
				rs = pStmt.executeQuery();
				
				while(rs.next())
				{
					map.put(rs.getString("vendorid"), rs.getString("name"));
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
	
		public Map<String, String> getVendorTypeList(Map<String, String> map) throws SQLException,Exception{
			
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
						
			String Query="SELECT typeid, type FROM inventory_vendor_type ";	
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query);
				rs = pStmt.executeQuery();
				
				while(rs.next())
				{
					map.put(rs.getString("typeid"), rs.getString("type"));
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

		public List<VendorFormVO> getKitchenVendorMap(VendorFormVO formVO) throws SQLException, Exception {
		
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
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
			String vendorIds = null;
			int count = 0;
						
			String vendorTypeQuery= "SELECT type from inventory_vendor_type where typeid = ? ";
			String countQuery = "SELECT COUNT(*) FROM inventory_kitchen_vendor";
			String IdQuery = "SELECT vendorid FROM inventory_kitchen_vendor where vendorid = ?";
			
			try{
				con = dbConnection.getConnection();
				
				pStmt2 = con.prepareStatement(countQuery);
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					count = rs2.getInt(1);
				}
				
				int tempCount=count+1;
				List<String> countList = new ArrayList<String>();
				
				for(int i=1; i<tempCount; i++){
					pStmt3 = con.prepareStatement(IdQuery);
					pStmt3.setInt(1, i);
					rs3 = pStmt3.executeQuery();
					
					while(rs3.next()){
						int j=1;
						if(i<6){
							if(j==1){
								vendorIds=rs3.getString("vendorid");
								j++;
							}
							else{
								vendorIds=vendorIds +","+ rs3.getString("vendorid");
								j++;
							}
						}
					}
					
					String []str = null;
					
					if(vendorIds!=null){
						str = vendorIds.split(",");				
						for(int m=0;m<str.length;m++){
							if(str[m]!=null && str[m]!="")
								countList.add(str[m]);
						}
						if(countList.size()<count){
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
				for(int k=0;k<countList.size();k++){
					if(k==0)
						id=countList.get(k);
					else
						id=id+","+countList.get(k);
				}
				
				String Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN ("+id+") ";
				
				pStmt = con.prepareStatement(Query);
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					available = true;
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setID(rs.getString("ID"));
					formVOO.setName(rs.getString("name"));
					pStmt1 = con.prepareStatement(vendorTypeQuery);
					pStmt1.setString(1, rs.getString("typeid"));
					rs1 = pStmt1.executeQuery();
					while(rs1.next()){
						formVOO.setType(rs1.getString("type"));
					}					
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}if(!available){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setName("----");					
					formVOO.setType("----");
					formVOO.setEmailId("----");
					formVOO.setPhoneNo("----");
					formVOO.setBallance("----");
					vendorList.add(formVOO);
				}
				
				Collections.sort(vendorList, vendorComparator.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
				dbConnection.close(pStmt1, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getKitchenVendorIndexMap(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
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
			String vendorIds = null;
			int count = 0;
			
			String vendorTypeQuery= "SELECT type from inventory_vendor_type where typeid = ? ";
			String countQuery = "SELECT COUNT(*) FROM inventory_kitchen_vendor";
			String IdQuery = "SELECT vendorid FROM inventory_kitchen_vendor where vendorid = ?";
			
			try{
				con = dbConnection.getConnection();
				
				pStmt2 = con.prepareStatement(countQuery);
				rs2 = pStmt2.executeQuery();
				
				while(rs2.next()){
					count = rs2.getInt(1);
				}
				
				int tempCount=count+1;
				List<String> countList = new ArrayList<String>();
				
				for(int i=1; i<tempCount; i++){
					pStmt3 = con.prepareStatement(IdQuery);
					pStmt3.setInt(1, i);
					rs3 = pStmt3.executeQuery();
					
					while(rs3.next()){
						int j=1;
						if(i<6){
							if(j==1){
								vendorIds=rs3.getString("vendorid");
								j++;
							}
							else{
								vendorIds=vendorIds +","+ rs3.getString("vendorid");
								j++;
							}
						}
					}
					
					String []str = null;
					
					if(vendorIds!=null){
						str = vendorIds.split(",");				
						for(int m=0;m<str.length;m++){
							if(str[m]!=null && str[m]!="")
								countList.add(str[m]);
						}
						if(countList.size()<count){
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
				
				String Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN ("+id+") ";

				pStmt = con.prepareStatement(Query);
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					available=true;
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setID(rs.getString("ID"));
					formVOO.setName(rs.getString("name"));
					pStmt1 = con.prepareStatement(vendorTypeQuery);
					pStmt1.setString(1, rs.getString("typeid"));
					rs1 = pStmt1.executeQuery();
					while(rs1.next()){
						formVOO.setType(rs1.getString("type"));
					}	
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}if(!available){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setName("----");					
					formVOO.setType("----");
					formVOO.setEmailId("----");
					formVOO.setPhoneNo("----");
					formVOO.setBallance("----");
					vendorList.add(formVOO);
				}
				
				Collections.sort(vendorList, vendorComparator.vendorIdComparator);
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
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getKitchenVendorById(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			PreparedStatement pStmt1 = null;
			boolean available = false;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			
			String Query1="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE vendorid = "+formVO.getVendorId();
			String vendorTypeQuery="SELECT type from inventory_vendor_type where typeid = ? ";

			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query1);
				rs = pStmt.executeQuery();
							
					while(rs.next()){
						available=true;
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId(rs.getString("vendorid"));
						formVOO.setID(rs.getString("ID"));
						formVOO.setName(rs.getString("name"));
						pStmt2 = con.prepareStatement(vendorTypeQuery);
						pStmt2.setString(1, rs.getString("typeid"));
						rs2 = pStmt2.executeQuery();
						while(rs2.next()){
							formVOO.setType(rs2.getString("type"));
						}	
						formVOO.setEmailId(rs.getString("emailid"));
						formVOO.setPhoneNo(rs.getString("phone"));
						formVOO.setBallance(rs.getString("ballance"));
						vendorList.add(formVOO);
					}
					
					if(!available){
						
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId("Unavailable");
						formVOO.setID("Unavailable");
						formVOO.setName("----");						
						formVOO.setType("----");					
						formVOO.setEmailId("----");
						formVOO.setPhoneNo("----");
						formVOO.setBallance("----");
						vendorList.add(formVOO);
					}
				
					Collections.sort(vendorList, vendorComparator.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
				dbConnection.close(pStmt1, con);
				dbConnection.close(pStmt2, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getKitchenVendorsByType(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;

			boolean available = false;
			
			String Query1="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE type = "+formVO.getType();
			String vendorTypeQuery="SELECT type from inventory_vendor_type where typeid = ? ";
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query1);
				rs = pStmt.executeQuery();
				
					while(rs.next()){
						available=true;
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId(rs.getString("vendorid"));
						formVOO.setID(rs.getString("ID"));
						formVOO.setName(rs.getString("name"));
						pStmt2 = con.prepareStatement(vendorTypeQuery);
						pStmt2.setString(1, rs.getString("typeid"));
						rs2 = pStmt2.executeQuery();
						while(rs2.next()){
							formVOO.setType(rs2.getString("type"));
						}
						formVOO.setEmailId(rs.getString("emailid"));
						formVOO.setPhoneNo(rs.getString("phone"));
						formVOO.setBallance(rs.getString("ballance"));
						vendorList.add(formVOO);
					}
					if(!available){
						
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId("Unavailable");
						formVOO.setID("Unavailable");
						formVOO.setName("----");						
						formVOO.setType("----");					
						formVOO.setEmailId("----");
						formVOO.setPhoneNo("----");
						formVOO.setBallance("----");
						vendorList.add(formVOO);
					}
					Collections.sort(vendorList, vendorComparator.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
				dbConnection.close(pStmt1, con);
				dbConnection.close(pStmt2, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getKitchenVendorsByCreationDates(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			PreparedStatement pStmt1 = null;
			boolean available = false;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			
			String Query1="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE created >= '"+formVO.getFromDate()+"' AND created <='"+formVO.getToDate()+"' ";
			String vendorTypeQuery="SELECT type from inventory_vendor_type where typeid = ? ";

			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query1);
				rs = pStmt.executeQuery();
				
					while(rs.next()){
						available=true;
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId(rs.getString("vendorid"));
						formVOO.setID(rs.getString("ID"));
						formVOO.setName(rs.getString("name"));
						pStmt2 = con.prepareStatement(vendorTypeQuery);
						pStmt2.setString(1, rs.getString("typeid"));
						rs2 = pStmt2.executeQuery();
						while(rs2.next()){
							formVOO.setType(rs2.getString("type"));
						}
						formVOO.setEmailId(rs.getString("emailid"));
						formVOO.setPhoneNo(rs.getString("phone"));
						formVOO.setBallance(rs.getString("ballance"));
						vendorList.add(formVOO);
					}
					if(!available){
						
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId("Unavailable");
						formVOO.setID("Unavailable");
						formVOO.setName("----");						
						formVOO.setType("----");					
						formVOO.setEmailId("----");
						formVOO.setPhoneNo("----");
						formVOO.setBallance("----");
						vendorList.add(formVOO);
					}
				
				Collections.sort(vendorList, vendorComparator.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
				dbConnection.close(pStmt1, con);
				dbConnection.close(pStmt2, con);
			}
			
			return vendorList;				
		}

		public List<VendorFormVO> getKitchenVendorByName(VendorFormVO formVO) throws SQLException, Exception {
		
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			PreparedStatement pStmt1 = null;
			boolean available = false;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			
			String Query1="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name like '%"+formVO.getName()+"%' ";
			String vendorTypeQuery="SELECT type from inventory_vendor_type where typeid = ? ";

			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query1);
				rs = pStmt.executeQuery();
				
					while(rs.next()){
						available=true;
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId(rs.getString("vendorid"));
						formVOO.setID(rs.getString("ID"));
						formVOO.setName(rs.getString("name"));
						pStmt2 = con.prepareStatement(vendorTypeQuery);
						pStmt2.setString(1, rs.getString("typeid"));
						rs2 = pStmt2.executeQuery();
						while(rs2.next()){
							formVOO.setType(rs2.getString("type"));
						}
						formVOO.setEmailId(rs.getString("emailid"));
						formVOO.setPhoneNo(rs.getString("phone"));
						formVOO.setBallance(rs.getString("ballance"));
						vendorList.add(formVOO);
					}
					if(!available){
						
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId("Unavailable");
						formVOO.setID("Unavailable");
						formVOO.setName("----");						
						formVOO.setType("----");					
						formVOO.setEmailId("----");
						formVOO.setPhoneNo("----");
						formVOO.setBallance("----");
						vendorList.add(formVOO);
					}
					Collections.sort(vendorList, vendorComparator.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
				dbConnection.close(pStmt1, con);
				dbConnection.close(pStmt2, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getKitchenVendorByPhone(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			PreparedStatement pStmt1 = null;
			boolean available = false;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;
			
			String Query1="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE phone like '%"+formVO.getContactPhone()+"%' ";
			String vendorTypeQuery="SELECT type from inventory_vendor_type where typeid = ? ";

			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query1);
				rs = pStmt.executeQuery();
				
					while(rs.next()){
						available=true;
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId(rs.getString("vendorid"));
						formVOO.setID(rs.getString("ID"));
						formVOO.setName(rs.getString("name"));
						pStmt2 = con.prepareStatement(vendorTypeQuery);
						pStmt2.setString(1, rs.getString("typeid"));
						rs2 = pStmt2.executeQuery();
						while(rs2.next()){
							formVOO.setType(rs2.getString("type"));
						}
						formVOO.setEmailId(rs.getString("emailid"));
						formVOO.setPhoneNo(rs.getString("phone"));
						formVOO.setBallance(rs.getString("ballance"));
						vendorList.add(formVOO);
					}
					
					if(!available){						
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId("Unavailable");
						formVOO.setID("Unavailable");
						formVOO.setName("----");						
						formVOO.setType("----");					
						formVOO.setEmailId("----");
						formVOO.setPhoneNo("----");
						formVOO.setBallance("----");
						vendorList.add(formVOO);
					}
					Collections.sort(vendorList, vendorComparator.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
				dbConnection.close(pStmt1, con);
				dbConnection.close(pStmt2, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getkitchenVendorsByRecords(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
			
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
			String vendorIds1 = null;
			String vendorIds2 = null;
			String vendorIds3 = null;
			int count = 0;
			
			String Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN (?) ";
			String vendorTypeQuery= "SELECT type from inventory_vendor_type where typeid = ? ";
			String countQuery = "SELECT COUNT(*) FROM inventory_kitchen_vendor";
			String IdQuery = "SELECT vendorid FROM inventory_kitchen_vendor where vendorid = ?";
			
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
								vendorIds1=rs3.getString("vendorid");
							else
								vendorIds1=vendorIds1 +","+ rs3.getString("vendorid");
						}
						if(i>5 && i<11){
							if(i==6)
								vendorIds2=vendorIds1 +","+rs3.getString("vendorid");
							else
								vendorIds2=vendorIds2 +","+ rs3.getString("vendorid");
						}
						if(i>10 && i<16){
							if(i==11)
								vendorIds3=vendorIds2 +","+rs3.getString("vendorid");
							else
								vendorIds3=vendorIds3 +","+ rs3.getString("vendorid");
						}
					}			
				}
				
				if(formVO.getLimit().equalsIgnoreCase("1")){
					if(vendorIds1!=null)
						Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN ("+vendorIds1+") ";
					else
						Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid=1 ";
				}else if(formVO.getLimit().equalsIgnoreCase("2")){
					if(vendorIds2!=null){
						Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN ("+vendorIds2+") ";
					}else{
						if(vendorIds1!=null)
							Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN ("+vendorIds1+") ";
						else
							Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid=1 ";
					}					
				}else if(formVO.getLimit().equalsIgnoreCase("3")){
					if(vendorIds3!=null){
						Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN ("+vendorIds3+") ";
					}else{
						if(vendorIds2!=null){
							Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN ("+vendorIds2+") ";
						}else{
							if(vendorIds1!=null)
								Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN ("+vendorIds1+") ";
							else
								Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid=1 ";
						}
					}
				}else{
					Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid=1 ";
				}
					
				pStmt = con.prepareStatement(Query);	
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					available=true;
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setID(rs.getString("ID"));
					formVOO.setName(rs.getString("name"));
					pStmt1 = con.prepareStatement(vendorTypeQuery);
					pStmt1.setString(1, rs.getString("typeid"));
					rs1 = pStmt1.executeQuery();
					while(rs1.next()){
						formVOO.setType(rs1.getString("type"));
					}
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}if(!available){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setName("----");					
					formVOO.setType("----");
					formVOO.setEmailId("----");
					formVOO.setPhoneNo("----");
					formVOO.setBallance("----");
					vendorList.add(formVOO);
				}
				
				Collections.sort(vendorList, vendorComparator.vendorIdComparator);
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
			
			return vendorList;	
		}
		
		public int getPaginationCount()throws SQLException,Exception{
			
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			
			int count = 0;
			
			String countQuery = "SELECT COUNT(*) FROM inventory_kitchen_vendor";

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

		public List<VendorFormVO> getPagination(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
			
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
			String vendorIds = null;
			List <String> vendorIdsList = new ArrayList<String>();
			int count = 0;
			
			String Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN (?) ";
			String vendorTypeQuery="SELECT type from inventory_vendor_type where typeid = ? ";

			String countQuery = "SELECT COUNT(*) FROM inventory_kitchen_vendor";
			String IdQuery = "SELECT vendorid FROM inventory_kitchen_vendor where vendorid = ?";
			
			try{	
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
						if(i<6){
							if(j==1){
								vendorIds=rs3.getString("vendorid");
								j++;
							}
							else{
								vendorIds=vendorIds +","+ rs3.getString("vendorid");
								j++;
							}
						}
					}
					
					String []str = null;
					
					if(vendorIds!=null){
						str = vendorIds.split(",");				
						for(int m=0;m<str.length;m++){
							if(str[m]!=null && str[m]!="")
								vendorIdsList.add(str[m]);
						}
						if(vendorIdsList.size()<count){
							continue;
						}
					}else{
						tempCount++;
					}								
				}
				
				for (int i=1;i<vendorIdsList.size(); i++) {
		            String a1 = vendorIdsList.get(i);
		            String a2 = vendorIdsList.get(i-1);
		            if (a1.equals(a2)) {
		            	vendorIdsList.remove(a1);
		            }
		        }
									
				String id = null;
				int temp = Integer.parseInt(formVO.getPageFrom());
				
				if(temp>0){			
					if(vendorIdsList.size() >= (temp+5)){
						for(int k=temp;k<temp+5;k++){
							if(k==0)
								id=vendorIdsList.get(k);
							else
								id=id+","+vendorIdsList.get(k);
						}
					}else{
						for(int k=0; k<vendorIdsList.size();k++){
							if(k==0)
								id=vendorIdsList.get(k);
							else
								id=id+","+vendorIdsList.get(k);
						}
					}
				}else{
					for(int k=0; k<vendorIdsList.size();k++){
						if(k==0)
							id=vendorIdsList.get(k);
						else
							id=id+","+vendorIdsList.get(k);
					}
				}
																
				Query="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid IN ("+id+") ";
				
				pStmt = con.prepareStatement(Query);
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					available = true;
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setID(rs.getString("ID"));
					formVOO.setName(rs.getString("name"));
					pStmt1 = con.prepareStatement(vendorTypeQuery);
					pStmt1.setString(1, rs.getString("typeid"));
					rs1 = pStmt1.executeQuery();
					while(rs1.next()){
						formVOO.setType(rs1.getString("type"));
					}					
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}if(!available){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId("Unavailable");
					formVOO.setID("Unavailable");
					formVOO.setName("----");					
					formVOO.setType("----");
					formVOO.setEmailId("----");
					formVOO.setPhoneNo("----");
					formVOO.setBallance("----");
					vendorList.add(formVOO);
				}
				
				Collections.sort(vendorList, vendorComparator.vendorIdComparator);
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
			
			return vendorList;	
		}

		public int deleteKitchenVendor(VendorFormVO formVO) throws SQLException, Exception{
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			int ret = 0;
			
			String deleteKitchenVendorFromListQuery="DELETE FROM inventory_kitchen_vendor WHERE vendorid=? ";	
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(deleteKitchenVendorFromListQuery);
				pStmt.setInt(1, Integer.parseInt(formVO.getVendorId()));
				
				//if(Integer.parseInt(formVO.getVendorId())!=1)
					ret = pStmt.executeUpdate();			
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
			}
			
			return ret;	
			
		}
		
		public List<VendorFormVO> getkitchenVendorsByType(VendorFormVO formVO) throws SQLException, Exception {
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
			
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			PreparedStatement pStmt1 = null;
			PreparedStatement pStmt2 = null;
			ResultSet rs2 = null;

			boolean available = false;
			
			String Query1="SELECT vendorid, ID, name, typeid, emailid, phone, ballance from inventory_kitchen_vendor WHERE typeid = ? ";
			String vendorTypeQuery="SELECT type from inventory_vendor_type where typeid = ? ";

			try{
				con = dbConnection.getConnection();
				
				pStmt = con.prepareStatement(Query1);
				pStmt.setString(1, formVO.getType());
				
				rs = pStmt.executeQuery();
								
					while(rs.next()){
						available=true;
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId(rs.getString("vendorid"));
						formVOO.setID(rs.getString("ID"));
						formVOO.setName(rs.getString("name"));
						pStmt2 = con.prepareStatement(vendorTypeQuery);
						pStmt2.setString(1, rs.getString("typeid"));
						rs2 = pStmt2.executeQuery();
						while(rs2.next()){
							formVOO.setType(rs2.getString("type"));
						}						
						formVOO.setEmailId(rs.getString("emailid"));
						formVOO.setPhoneNo(rs.getString("phone"));
						formVOO.setBallance(rs.getString("ballance"));
						vendorList.add(formVOO);
					}
					if(!available){
						
						VendorFormVO formVOO = new VendorFormVO();
						formVOO.setVendorId("Unavailable");
						formVOO.setID("Unavailable");
						formVOO.setName("----");						
						formVOO.setType("----");					
						formVOO.setEmailId("----");
						formVOO.setPhoneNo("----");
						formVOO.setBallance("----");
						vendorList.add(formVOO);
					}
				
					Collections.sort(vendorList, vendorComparator.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
				dbConnection.close(pStmt1, con);
				dbConnection.close(pStmt2, con);
			}
			
			return vendorList;	
		}
		
		
		public VendorFormVO getStockKitchenVendor(VendorFormVO formVO)throws SQLException, Exception{
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
			
			String Query="SELECT vendorid, ID, sellorid, subsellorid, name, typeid, phone, emailid, tin, vat, pan, gst, service_tax_no, "
					+ "contact_person_name, contact_phone, contact_email, billing_address, billing_country_id, billing_state_id, billing_city_id, billing_pin_code, "
					+ "shipping_address, shipping_country_id, shipping_state_id, shipping_city_id, shipping_pin_code, created_date, last_modified_date, ballance from inventory_kitchen_vendor WHERE vendorid=? ";
			
			String billingCountryNameQuery ="SELECT nationality from inventory_nationality where nationalityid = ? ";
			String billingStateNameQuery = "SELECT domissile from inventory_domissile where domissileid = ? ";
			
			String shippingCountryNameQuery="SELECT nationality from inventory_nationality where nationalityid = ? ";
			String shippingStateNameQuery="SELECT domissile from inventory_domissile where domissileid = ? ";
			
			String vendorTypeQuery="SELECT type from inventory_vendor_type where typeid = ? ";
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query);
				pStmt.setInt(1, Integer.parseInt(formVO.getVendorId()));
				rs = pStmt.executeQuery();
				
				while(rs.next()){
								
					formVO.setVendorId(rs.getString("vendorid"));
					formVO.setID(rs.getString("ID"));
					formVO.setSellerId(rs.getString("sellorid"));
					formVO.setSubSellerId(rs.getString("subsellorid"));
					formVO.setName(rs.getString("name"));
					
					pStmt5 = con.prepareStatement(vendorTypeQuery);
					pStmt5.setString(1, rs.getString("typeid"));
					rs5 = pStmt5.executeQuery();
					while(rs5.next()){
						formVO.setType(rs5.getString("type"));
					}
					
					//formVO.setType(rs.getString("type"));
					formVO.setPhoneNo(rs.getString("phone"));
					formVO.setEmailId(rs.getString("emailid"));
					formVO.setTin(rs.getString("tin"));
					formVO.setVatNo(rs.getString("vat"));
					formVO.setPan(rs.getString("pan"));
					formVO.setGst(rs.getString("gst"));
					formVO.setServiceTaxNo(rs.getString("servicetaxno"));
					formVO.setContactPerson(rs.getString("contactpersonname"));
					formVO.setContactPhone(rs.getString("contactphone"));
					formVO.setContactEmail(rs.getString("contactemail"));
					formVO.setBillingAddress(rs.getString("billingaddress"));
									
					pStmt1 = con.prepareStatement(billingCountryNameQuery);
					pStmt1.setString(1, rs.getString("billingcountryid"));
					rs1 = pStmt1.executeQuery();
					while(rs1.next()){
						formVO.setBillingCountryId(rs1.getString("name"));
					}
					
					pStmt2 = con.prepareStatement(billingStateNameQuery);
					pStmt2.setString(1, rs.getString("billingstateid"));
					rs2 = pStmt2.executeQuery();
					while(rs2.next()){
						formVO.setBillingStateId(rs2.getString("name"));
					}
									
					formVO.setBillingCityId(rs.getString("billingcityid"));
					formVO.setBillingPinCode(rs.getString("billingpincode"));
					formVO.setShippingAddress(rs.getString("shippingaddress"));
									
					pStmt3 = con.prepareStatement(shippingCountryNameQuery);
					pStmt3.setString(1, rs.getString("shippingcountryid"));
					rs3 = pStmt3.executeQuery();
					while(rs3.next()){
						formVO.setShippingCountryId(rs3.getString("name"));
					}
					
					pStmt4 = con.prepareStatement(shippingStateNameQuery);
					pStmt4.setString(1, rs.getString("shippingstateid"));
					rs4 = pStmt4.executeQuery();
					while(rs4.next()){
						formVO.setShippingStateId(rs4.getString("name"));
					}
						
					formVO.setShippingCityId(rs.getString("shippingcityid"));
					formVO.setShippingPinCode(rs.getString("shippingpincode"));			
					
					formVO.setBallance(rs.getString("ballance"));								
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
			}
			
			return formVO;			
		}
		
}
