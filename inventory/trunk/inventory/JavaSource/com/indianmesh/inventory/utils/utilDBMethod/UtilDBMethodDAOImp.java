package com.indianmesh.inventory.utils.utilDBMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.indianmesh.inventory.utils.dbConnection.DBConnection;

public class UtilDBMethodDAOImp implements UtilDBMethodBaseDAO {
	
	public  Map<String, Object> getStateList() throws SQLException,Exception{
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();  
		
		String stateListQuery="SELECT domissileid, domissile FROM inventory_domissile";	
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(stateListQuery);
			rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				map.put(rs.getString("domissileid"), rs.getString("domissile"));
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
	
	public  Map<String, Object> getNationalityList() throws SQLException,Exception{
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();  
		
		String nationalityListQuery="SELECT nationalityid, nationality FROM inventory_nationality";	
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(nationalityListQuery);
			rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				map.put(rs.getString("nationalityid"), rs.getString("nationality"));
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
	
	public Map<String, Object> getQuantityTypeList() throws SQLException,Exception{
		
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();  
		
		String quantityTypeQuery="SELECT quantityid, quantity FROM inventory_quantity_type";	
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(quantityTypeQuery);
			rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				map.put(rs.getString("quantityid"), rs.getString("quantity"));
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
	
	public  Map<String, Object> getAmountPaidTypeList() throws SQLException,Exception{
		
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();  
		
		String amountPaidTypeQuery="SELECT typeid, type FROM inventory_amount_type";	
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(amountPaidTypeQuery);
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

	public Map<String, Object> getKitchenProductTypeList() throws SQLException,Exception{
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();  
		
		String kitchenProductTypeListQuery="SELECT producttypeid, producttype FROM inventory_kitchen_product_type";	
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(kitchenProductTypeListQuery);
			rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				map.put(rs.getString("producttypeid"), rs.getString("producttype"));
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

	public Map<String, String> getVendorRecord() throws SQLException, Exception {
		DBConnection dbConnection = new DBConnection();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		Map<String, String> map = new LinkedHashMap<String, String>();  
		
		String Query="SELECT criteriaid, criteria FROM inventory_record_criteria";	
		
		try{
			con = dbConnection.getConnection();
			pStmt = con.prepareStatement(Query);
			rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				map.put(rs.getString("criteriaid"), rs.getString("criteria"));
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
	
}











