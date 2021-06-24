package com.indianmesh.inventory.utils.utilDBMethod;

import java.sql.SQLException;
import java.util.Map;

public interface UtilDBMethodBaseDAO {
	
	public Map<String, Object> getStateList() throws SQLException,Exception;	
	public Map<String, Object> getNationalityList() throws SQLException,Exception;	
	public Map<String, Object> getQuantityTypeList() throws SQLException,Exception;	
	public Map<String, Object> getAmountPaidTypeList() throws SQLException,Exception;
	public Map<String, Object> getKitchenProductTypeList() throws SQLException,Exception;
	public Map<String, String> getVendorRecord() throws SQLException,Exception;

}
