package com.indianmesh.inventory.bar.product;

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

public class ProductDAOImp implements ProductBaseDAO {

	public ProductFormVO addBarProduct(ProductFormVO formVO) throws SQLException, Exception {

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs1 = null;
		String productId = null;
		int temp = 0;
				
		String countQuery=" SELECT COUNT(*) FROM inventory_kitchen_product"; 
		String idQuery=" SELECT productid FROM inventory_kitchen_product where productid=?";
		String Query = "INSERT INTO inventory_kitchen_product (productid,sellerid,subsellerid,created_date,last_modified_date) VALUES (?,?,?,?,?)";
		
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
						productId = String.valueOf(i);
				}
			}
			
			pStmt2=con.prepareStatement(Query);
			pStmt2.setString(1,productId);
			pStmt2.setString(2,formVO.getSellerId());
			pStmt2.setString(3,formVO.getSubSellerId());
			pStmt2.setDate(4,new java.sql.Date(new Date().getTime()));
			pStmt2.setDate(5,new java.sql.Date(new Date().getTime()));
			pStmt2.executeUpdate();
			
			formVO.setProductId(productId);
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			dbConnection.close(pStmt, con);
		}
		
		return formVO;			
	}
	
	public ProductFormVO editBarProduct(ProductFormVO formVO) throws SQLException, Exception {

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		String Query="SELECT productid, name, product_type, description, quantityid, quantity_per_item, quantity_count, "
				+ "total, vendorid, cost_price, amount_paid, amount_paid_typeid, pending_amount, credit_note from inventory_kitchen_product WHERE productid=? ";
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
			rs = pStmt.executeQuery();
			
			while(rs.next()){							
				formVO.setProductId(rs.getString("productid"));
				formVO.setName(rs.getString("name"));
				formVO.setProductType(rs.getString("product_type"));
				formVO.setDescription(rs.getString("description"));
				formVO.setQuantityId(rs.getString("quantityid"));
				formVO.setQuantityPerItem(rs.getString("quantity_per_item"));
				formVO.setQuantityCount(rs.getString("quantity_count"));
				formVO.setTotal(rs.getString("total"));
				formVO.setVendorId(rs.getString("vendorid"));
				formVO.setCostPrice(rs.getString("cost_price"));
				formVO.setAmountPaid(rs.getString("amount_paid"));
				formVO.setAmountPaidTypeId(rs.getString("amount_paid_typeid"));
				formVO.setPendingAmount(rs.getString("pending_amount"));
				formVO.setCreditNote(rs.getString("credit_note"));				
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
		
	public int updateBarProduct(ProductFormVO formVO) throws SQLException, Exception {

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		int temp = 0;
		
		String Query="UPDATE inventory_kitchen_product SET name=?, product_type=?, description=?, quantityid=?, quantity_per_item=?, quantity_count=?, "
				+ "total=?, vendorid=?, cost_price=?, amount_paid=?, amount_paid_typeid=?, pending_amount=?, credit_note=? WHERE productid=? ";
		
		try{
			con = dbConnection.getConnection();
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
			pStmt.setString(14,formVO.getProductId());

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
	
	public Map<String, Object> getBarProductList(Map<String, Object> map) throws SQLException, Exception{
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
	
	public List<ProductFormVO> getBarProductMap(ProductFormVO formVO) throws SQLException, Exception {

		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		
		String Query="SELECT productid, name, created_date, quantity_per_item, quantity_count, quantityid, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL ";
		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created_date"));
				formVOO.setQuantityPerItem(rs.getString("quantity_per_item"));
				formVOO.setQuantityCount(rs.getString("quantity_count"));				
				
				pStmt1 = con.prepareStatement(vendorNameQuery);
				pStmt1.setString(1, rs.getString("vendorid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setVendorId(rs1.getString("name"));
				} 
				
				productList.add(formVOO);
			}
			
			Collections.sort(productList, ProductDAOImp.productComparator);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
		}
		
		return productList;		
	}
	
	public List<ProductFormVO> getBarProductIndexMap(ProductFormVO formVO) throws SQLException, Exception {

		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		
		String Query="SELECT productid, name, created_date, quantity_per_item, quantity_count, quantityid, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND productid BETWEEN 0 AND 5 ";
		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created_date"));
				formVOO.setQuantityPerItem(rs.getString("quantity_per_item"));
				formVOO.setQuantityCount(rs.getString("quantity_count"));
								
				pStmt1 = con.prepareStatement(vendorNameQuery);
				pStmt1.setString(1, rs.getString("vendorid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setVendorId(rs1.getString("name"));
				} 
				
				//productFormVO.setTotal(rs.getString("total"));
				productList.add(formVOO);
			}
			
			Collections.sort(productList, ProductDAOImp.productComparator);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
		}
		
		return productList;			
	}
	
	public List<ProductFormVO> getBarProductsByRecords(ProductFormVO formVO) throws SQLException, Exception {

		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
				
		String productListQuery="SELECT productid, name, created_date, quantity_per_item, quantity_count, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND productid BETWEEN ? AND ? ";
		String productVendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(productListQuery);	
			
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
				formVOO.setCreatedDate(rs.getString("created_date"));
				formVOO.setQuantityPerItem(rs.getString("quantity_per_item"));
				formVOO.setQuantityCount(rs.getString("quantity_count"));
				
				pStmt1 = con.prepareStatement(productVendorNameQuery);
				pStmt1.setString(1, rs.getString("vendorid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setVendorId(rs1.getString("name"));
				}			
				
				formVOO.setTotal(rs.getString("total"));
				productList.add(formVOO);
			}
			
			Collections.sort(productList, ProductDAOImp.productComparator);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
		}
		
		return productList;			
	}
	
	public List<ProductFormVO> getPagination(ProductFormVO formVO) throws SQLException, Exception {

		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		
		String Query="SELECT productid, name, created_date, quantity_per_item, quantity_count, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND productid BETWEEN ? AND ? ";		
		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";

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
				formVOO.setCreatedDate(rs.getString("created_date"));
				formVOO.setQuantityPerItem(rs.getString("quantity_per_item"));
				formVOO.setQuantityCount(rs.getString("quantity_count"));
								
				pStmt1 = con.prepareStatement(vendorNameQuery);
				pStmt1.setString(1, rs.getString("vendorid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setVendorId(rs1.getString("name"));
				} 
				
				formVOO.setTotal(rs.getString("total"));
				productList.add(formVOO);

			}
			
			Collections.sort(productList, ProductDAOImp.productComparator);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
		}
		
		return productList;			
	}
	
	public int deleteBarProduct(ProductFormVO formVO) throws SQLException, Exception{

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		int ret = 0;
		
		String deleteKitchenProductFromListQuery="DELETE FROM inventory_kitchen_product WHERE productid=?";	
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(deleteKitchenProductFromListQuery);
			pStmt.setInt(1, Integer.parseInt(formVO.getProductId()));
			
			if(Integer.parseInt(formVO.getProductId())!=1)
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
	
	public static Comparator<ProductFormVO> productComparator = new Comparator<ProductFormVO>() {

		public int compare(ProductFormVO po1, ProductFormVO po2) {
			 int vi1 = Integer.parseInt(po1.getProductId());
			 int vi2 = Integer.parseInt(po2.getProductId());

			 //ascending order
			 return vi1 - vi2;
			   
			 /*For descending order*/
			 //return vi2 - vi1;
	}};

	@Override
	public List<ProductFormVO> getBarProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		
		String Query="SELECT productid, name, created_date, quantity_per_item, quantity_count, quantityid, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND created_date BETWEEN '"+formVO.getFromDate()+"' AND '"+formVO.getToDate()+"' ";
		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created_date"));
				formVOO.setQuantityPerItem(rs.getString("quantity_per_item"));
				formVOO.setQuantityCount(rs.getString("quantity_count"));
								
				pStmt1 = con.prepareStatement(vendorNameQuery);
				pStmt1.setString(1, rs.getString("vendorid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setVendorId(rs1.getString("name"));
				} 
				
				//productFormVO.setTotal(rs.getString("total"));
				productList.add(formVOO);
			}
			
			Collections.sort(productList, ProductDAOImp.productComparator);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
		}
		
		return productList;			
	}

	@Override
	public List<ProductFormVO> getBarProductsByVendor(ProductFormVO formVO) throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		
		String Query="SELECT productid, name, created_date, quantity_per_item, quantity_count, quantityid, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND vendorid= "+formVO.getVendorId();
		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created_date"));
				formVOO.setQuantityPerItem(rs.getString("quantity_per_item"));
				formVOO.setQuantityCount(rs.getString("quantity_count"));
								
				pStmt1 = con.prepareStatement(vendorNameQuery);
				pStmt1.setString(1, rs.getString("vendorid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setVendorId(rs1.getString("name"));
				} 
				
				//productFormVO.setTotal(rs.getString("total"));
				productList.add(formVOO);
			}
			
			Collections.sort(productList, ProductDAOImp.productComparator);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			dbConnection.close(pStmt, con);
		}
		
		return productList;			
	}

	@Override
	public List<ProductFormVO> getBarProductsById(ProductFormVO formVO) throws SQLException, Exception {
		List<ProductFormVO> productList = new LinkedList<ProductFormVO>();

		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		PreparedStatement pStmt1 = null;
		ResultSet rs1 = null;
		
		String Query="SELECT productid, name, created_date, quantity_per_item, quantity_count, quantityid, vendorid, total from inventory_kitchen_product WHERE name IS NOT NULL AND productid= "+formVO.getProductId();
		String vendorNameQuery = "SELECT name from inventory_kitchen_vendor where vendorid = ? ";

		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				ProductFormVO formVOO = new ProductFormVO();
				formVOO.setProductId(rs.getString("productid"));
				formVOO.setName(rs.getString("name"));
				formVOO.setCreatedDate(rs.getString("created_date"));
				formVOO.setQuantityPerItem(rs.getString("quantity_per_item"));
				formVOO.setQuantityCount(rs.getString("quantity_count"));
								
				pStmt1 = con.prepareStatement(vendorNameQuery);
				pStmt1.setString(1, rs.getString("vendorid"));
				rs1 = pStmt1.executeQuery();
				
				while(rs1.next()){
					formVOO.setVendorId(rs1.getString("name"));
				} 
				
				//productFormVO.setTotal(rs.getString("total"));
				productList.add(formVOO);
			}
			
			Collections.sort(productList, ProductDAOImp.productComparator);
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
