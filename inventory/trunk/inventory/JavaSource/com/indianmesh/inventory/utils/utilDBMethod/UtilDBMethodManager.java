package com.indianmesh.inventory.utils.utilDBMethod;

import java.sql.SQLException;
import java.util.Map;

import com.indianmesh.inventory.utils.DAOFactory;
import com.indianmesh.inventory.utils.dbConnection.Constant;

public class UtilDBMethodManager {
	
	private static int DATA_SOURCE = Constant.DBNAME;	
	UtilDBMethodBaseDAO  utilBaseDAOObject = null;
	
	public UtilDBMethodManager(){
		utilBaseDAOObject = (UtilDBMethodBaseDAO) DAOFactory.getUtilMethodDAO(DATA_SOURCE);
	}
	
	public  Map<String, Object> getStateList()throws SQLException,Exception{
		return utilBaseDAOObject.getStateList();		
	}

	public  Map<String, Object> getNationalityList()throws SQLException,Exception{
		return utilBaseDAOObject.getNationalityList();		
	}
	
	public  Map<String, Object> getQuantityTypeList()throws SQLException,Exception{
		return utilBaseDAOObject.getQuantityTypeList();		
	}
	
	public  Map<String, Object> getAmountPaidTypeList()throws SQLException,Exception{
		return utilBaseDAOObject.getAmountPaidTypeList();		
	}
	
	public Map<String, Object> getKitchenProductTypeList() throws SQLException,Exception{
		return utilBaseDAOObject.getKitchenProductTypeList();		
	}
	
	public Map<String, String> getVendorRecord() throws SQLException,Exception{
		return utilBaseDAOObject.getVendorRecord();		
	}
		
}

