package com.indianmesh.inventory.bar.vendor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.utils.dbConnection.DBConnection;

public class VendorDAOImp implements VendorBaseDAO {
	
	public VendorFormVO addBarVendor(VendorFormVO formVO) throws SQLException, Exception {
		
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
		String Query = "INSERT INTO inventory_kitchen_vendor (vendorid,sellorid,subsellorid,created_date,last_modified_date) VALUES (?,?,?,?,?) ";
			
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
			
			pStmt2=con.prepareStatement(Query);
			pStmt2.setString(1,vendorId);
			pStmt2.setString(2,formVO.getSellerId());
			pStmt2.setString(3,formVO.getSubSellerId());
			pStmt2.setDate(4,new java.sql.Date(new Date().getTime()));
			pStmt2.setDate(5,new java.sql.Date(new Date().getTime()));
			pStmt2.executeUpdate();
			
			formVO.setVendorId(vendorId);
		}

		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
		}
		
		return formVO;	
	}
	
	public VendorFormVO editBarVendor(VendorFormVO formVO) throws SQLException, Exception {
			
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		String Query="SELECT vendorid, name, phone, emailid, tin, vat, pan, gst, service_tax_no, "
				+ "contact_person_name, contact_phone, contact_email, billing_address, billing_country_id, billing_state_id, billing_city_id, billing_pin_code, "
				+ "shipping_address, shipping_country_id, shipping_state_id, shipping_city_id, shipping_pin_code, vandor_product, ballance from inventory_kitchen_vendor WHERE vendorid=? ";
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			pStmt.setInt(1, Integer.parseInt(formVO.getVendorId()));
			rs = pStmt.executeQuery();
			
			while(rs.next()){
							
				formVO.setVendorId(rs.getString("vendorid"));
				formVO.setName(rs.getString("name"));
				formVO.setPhoneNo(rs.getString("phone"));
				formVO.setEmailId(rs.getString("emailid"));
				formVO.setTin(rs.getString("tin"));
				formVO.setVatNo(rs.getString("vat"));
				formVO.setPan(rs.getString("pan"));
				formVO.setGst(rs.getString("gst"));
				formVO.setServiceTaxNo(rs.getString("service_tax_no"));
				formVO.setContactPerson(rs.getString("contact_person_name"));
				formVO.setContactPhone(rs.getString("contact_phone"));
				formVO.setContactEmail(rs.getString("contact_email"));
				formVO.setBillingAddress(rs.getString("billing_address"));
				formVO.setBillingCountryId(rs.getString("billing_country_id"));
				formVO.setBillingStateId(rs.getString("billing_state_id"));
				formVO.setBillingCityId(rs.getString("billing_city_id"));
				formVO.setBillingPinCode(rs.getString("billing_pin_code"));
				formVO.setShippingAddress(rs.getString("shipping_address"));
				formVO.setShippingCountryId(rs.getString("shipping_country_id"));
				formVO.setShippingStateId(rs.getString("shipping_state_id"));
				formVO.setShippingCityId(rs.getString("shipping_city_id"));
				formVO.setShippingPinCode(rs.getString("shipping_pin_code"));				
				formVO.setVendorProduct(rs.getString("vandor_product"));			
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
	
	public String updateBarVendor(VendorFormVO formVO) throws SQLException, Exception {
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
				
		String Query = "UPDATE inventory_kitchen_vendor SET name=?, phone=?, emailid=?, tin=?, vat=?, pan=?, gst=?, service_tax_no=?, "
				+ "contact_person_name=?, contact_phone=?, contact_email=?, billing_address=?, billing_country_id=?, billing_state_id=?, billing_city_id=?, billing_pin_code=?, "
				+ "shipping_address=?, shipping_country_id=?, shipping_state_id=?, shipping_city_id=?, shipping_pin_code=?, last_modified_date=?, vandor_product=?, ballance=? WHERE vendorId=? ";
				
		try{
			con = dbConnection.getConnection();
			
			pStmt = con.prepareStatement(Query);
			
			pStmt.setString(1,formVO.getName());
			pStmt.setString(2,formVO.getPhoneNo());
			pStmt.setString(3,formVO.getEmailId());
			pStmt.setString(4,formVO.getTin());
			pStmt.setString(5,formVO.getVatNo());
			pStmt.setString(6,formVO.getPan());
			pStmt.setString(7,formVO.getGst());
			pStmt.setString(8,formVO.getServiceTaxNo());
			pStmt.setString(9,formVO.getContactPerson());
			pStmt.setString(10,formVO.getContactPhone());
			pStmt.setString(11,formVO.getContactEmail());
			pStmt.setString(12,formVO.getBillingAddress());
			pStmt.setString(13,formVO.getBillingCountryId());
			pStmt.setString(14,formVO.getBillingStateId());
			pStmt.setString(15,formVO.getBillingCityId());
			pStmt.setString(16,formVO.getBillingPinCode());
			pStmt.setString(17,formVO.getShippingAddress());
			pStmt.setString(18,formVO.getShippingCountryId());
			pStmt.setString(19,formVO.getShippingStateId());
			pStmt.setString(20,formVO.getShippingCityId());
			pStmt.setString(21,formVO.getShippingPinCode());			
			pStmt.setDate(22,new java.sql.Date(new Date().getTime()));			
			pStmt.setString(23,formVO.getVendorProduct());			
			pStmt.setString(24,formVO.getBallance());				
			pStmt.setString(25,formVO.getVendorId());
			
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
	
	public VendorFormVO viewBarVendor(VendorFormVO formVO) throws SQLException, Exception {
			
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
		
		String Query="SELECT vendorid, sellorid, subsellorid, name, phone, emailid, tin, vat, pan, gst, service_tax_no, "
				+ "contact_person_name, contact_phone, contact_email, billing_address, billing_country_id, billing_state_id, billing_city_id, billing_pin_code, "
				+ "shipping_address, shipping_country_id, shipping_state_id, shipping_city_id, shipping_pin_code, created_date, last_modified_date, vandor_product, ballance from inventory_kitchen_vendor WHERE vendorid=? ";
		
		String billingCountryNameQuery = "SELECT name from inventory_nationality where id = ? ";
		String billingStateNameQuery = "SELECT name from inventory_domissile where domissileid = ? ";
		
		String shippingCountryNameQuery= "SELECT name from inventory_nationality where id = ? ";
		String shippingStateNameQuery= "SELECT name from inventory_domissile where domissileid = ? ";
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			pStmt.setInt(1, Integer.parseInt(formVO.getVendorId()));
			rs = pStmt.executeQuery();
			
			while(rs.next()){
							
				formVO.setVendorId(rs.getString("vendorid"));
				formVO.setSellerId(rs.getString("sellorid"));
				formVO.setSubSellerId(rs.getString("subsellorid"));
				formVO.setName(rs.getString("name"));
				formVO.setPhoneNo(rs.getString("phone"));
				formVO.setEmailId(rs.getString("emailid"));
				formVO.setTin(rs.getString("tin"));
				formVO.setVatNo(rs.getString("vat"));
				formVO.setPan(rs.getString("pan"));
				formVO.setGst(rs.getString("gst"));
				formVO.setServiceTaxNo(rs.getString("service_tax_no"));
				formVO.setContactPerson(rs.getString("contact_person_name"));
				formVO.setContactPhone(rs.getString("contact_phone"));
				formVO.setContactEmail(rs.getString("contact_email"));
				formVO.setBillingAddress(rs.getString("billing_address"));
								
				pStmt1 = con.prepareStatement(billingCountryNameQuery);
				pStmt1.setString(1, rs.getString("billing_country_id"));
				rs1 = pStmt1.executeQuery();
				while(rs1.next()){
					formVO.setBillingCountryId(rs1.getString("name"));
				}
				
				pStmt2 = con.prepareStatement(billingStateNameQuery);
				pStmt2.setString(1, rs.getString("billing_state_id"));
				rs2 = pStmt2.executeQuery();
				while(rs2.next()){
					formVO.setBillingStateId(rs2.getString("name"));
				}
								
				formVO.setBillingCityId(rs.getString("billing_city_id"));
				formVO.setBillingPinCode(rs.getString("billing_pin_code"));
				formVO.setShippingAddress(rs.getString("shipping_address"));
								
				pStmt3 = con.prepareStatement(shippingCountryNameQuery);
				pStmt3.setString(1, rs.getString("shipping_country_id"));
				rs3 = pStmt3.executeQuery();
				while(rs3.next()){
					formVO.setShippingCountryId(rs3.getString("name"));
				}
				
				pStmt4 = con.prepareStatement(shippingStateNameQuery);
				pStmt4.setString(1, rs.getString("shipping_state_id"));
				rs4 = pStmt4.executeQuery();
				while(rs4.next()){
					formVO.setShippingStateId(rs4.getString("name"));
				}
					
				formVO.setShippingCityId(rs.getString("shipping_city_id"));
				formVO.setShippingPinCode(rs.getString("shipping_pin_code"));			
				
				formVO.setVendorProduct(rs.getString("vandor_product"));			
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
		}
		
		return formVO;			
	}

	public Map<String, Object> getVendorList(Map<String, Object> map) throws SQLException,Exception{
			
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			
			//Map<String, Object> map = new LinkedHashMap<String, Object>();  
			
			String vendorListQuery="SELECT vendorid, name FROM inventory_kitchen_vendor";	
			
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

		public List<VendorFormVO> getBarVendorMap(VendorFormVO formVO) throws SQLException, Exception {
		
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			
			String kitchenProductListQuery="SELECT vendorid, name, vandor_product, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL";
			
			try{
				con = dbConnection.getConnection();
							
				pStmt = con.prepareStatement(kitchenProductListQuery);
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setName(rs.getString("name"));
					formVOO.setVendorProduct(rs.getString("vandor_product"));
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}
				
				Collections.sort(vendorList, VendorDAOImp.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getBarVendorIndexMap(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			
			String Query="SELECT vendorid, name, vandor_product, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid BETWEEN 0 AND 5";
			
			try{
				con = dbConnection.getConnection();
							
				pStmt = con.prepareStatement(Query);
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setName(rs.getString("name"));
					formVOO.setVendorProduct(rs.getString("vandor_product"));
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}
				
				Collections.sort(vendorList, VendorDAOImp.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getBarVendorById(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			
			String Query="SELECT vendorid, name, vandor_product, emailid, phone, ballance from inventory_kitchen_vendor WHERE vendorid = "+formVO.getVendorId();
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query);
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setName(rs.getString("name"));
					formVOO.setVendorProduct(rs.getString("vandor_product"));
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}
				Collections.sort(vendorList, VendorDAOImp.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getBarVendorsByType(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			
			String Query="SELECT vendorid, name, vandor_product, emailid, phone, ballance from inventory_kitchen_vendor WHERE type = "+formVO.getType();
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query);
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setName(rs.getString("name"));
					formVOO.setVendorProduct(rs.getString("vandor_product"));
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}
				Collections.sort(vendorList, VendorDAOImp.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getBarVendorsByCreatedDates(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			
			String Query="SELECT vendorid, name, vandor_product, emailid, phone, ballance from inventory_kitchen_vendor WHERE created_date BETWEEN = '"+formVO.getFromDate()+"' AND '"+formVO.getToDate()+"'";
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(Query);
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setName(rs.getString("name"));
					formVOO.setVendorProduct(rs.getString("vandor_product"));
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}
				Collections.sort(vendorList, VendorDAOImp.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
			}
			
			return vendorList;				
		}

		public List<VendorFormVO> getBarVendorByName(VendorFormVO formVO) throws SQLException, Exception {
		
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			
			String kitchenProductListQuery="SELECT vendorid, name, vandor_product, emailid, phone, ballance from inventory_kitchen_vendor WHERE name like '%"+formVO.getName()+"%'";
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(kitchenProductListQuery);
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setName(rs.getString("name"));
					formVOO.setVendorProduct(rs.getString("vandor_product"));
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}
				Collections.sort(vendorList, VendorDAOImp.vendorIdComparator);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
			}
			
			return vendorList;				
		}
		
		public List<VendorFormVO> getBarVendorsByRecords(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
			
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;			
			
			String kitchenProductListQuery="SELECT vendorid, name, vandor_product, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid BETWEEN ? AND ?";
			
			try{	
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(kitchenProductListQuery);	
				
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
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setName(rs.getString("name"));
					formVOO.setVendorProduct(rs.getString("vandor_product"));
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}
				
				Collections.sort(vendorList, VendorDAOImp.vendorIdComparator);
			}		
			
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
			}
			
			return vendorList;	
		}

		public List<VendorFormVO> getPagination(VendorFormVO formVO) throws SQLException, Exception {
			
			List<VendorFormVO> vendorList = new LinkedList<VendorFormVO>();
			
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;			
			
			String kitchenProductListQuery="SELECT vendorid, name, vandor_product, emailid, phone, ballance from inventory_kitchen_vendor WHERE name IS NOT NULL AND vendorid BETWEEN ? AND ? ";
			
			try{	
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(kitchenProductListQuery);	
				
				if(Integer.parseInt(formVO.getPageFrom()) < 0) {
					pStmt.setInt(1, 1);
					pStmt.setInt(2, 5);
				}else{					
					pStmt.setInt(1, Integer.parseInt(formVO.getPageFrom()));
					pStmt.setInt(2, Integer.parseInt(formVO.getPageTo()));
				}
				
				rs = pStmt.executeQuery();
				
				while(rs.next()){
					VendorFormVO formVOO = new VendorFormVO();
					formVOO.setVendorId(rs.getString("vendorid"));
					formVOO.setName(rs.getString("name"));
					formVOO.setVendorProduct(rs.getString("vandor_product"));
					formVOO.setEmailId(rs.getString("emailid"));
					formVOO.setPhoneNo(rs.getString("phone"));
					formVOO.setBallance(rs.getString("ballance"));
					vendorList.add(formVOO);
				}
				
				Collections.sort(vendorList, VendorDAOImp.vendorIdComparator);
			}		
			
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				dbConnection.close(pStmt, con);
			}
			
			return vendorList;	
		}

		public int deleteBarVendor(VendorFormVO formVO) throws SQLException, Exception{
		
			DBConnection dbConnection = new DBConnection();
			Connection con = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			int ret = 0;
			
			String deleteKitchenVendorFromListQuery="DELETE FROM inventory_kitchen_vendor WHERE vendorid=? ";	
			
			try{
				con = dbConnection.getConnection();
				pStmt = con.prepareStatement(deleteKitchenVendorFromListQuery);
				pStmt.setInt(1, Integer.parseInt(formVO.getVendorId()));
				
				if(Integer.parseInt(formVO.getVendorId())!=1)
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
		
		public static Comparator<VendorFormVO> vendorIdComparator = new Comparator<VendorFormVO>() {

			public int compare(VendorFormVO vo1, VendorFormVO vo2) {
				 int vi1 = Integer.parseInt(vo1.getVendorId());
				 int vi2 = Integer.parseInt(vo2.getVendorId());

				 //ascending order
				 return vi1 - vi2;
				   
				 /*For descending order*/
				 //return vi2 - vi1;
		}};
}
