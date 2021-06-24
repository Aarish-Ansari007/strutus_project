package com.indianmesh.inventory.bar.vendor;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface VendorBaseDAO {
	
	public VendorFormVO addBarVendor(VendorFormVO formVO) throws SQLException, Exception;
	public VendorFormVO editBarVendor(VendorFormVO formVO) throws SQLException, Exception;
	public String updateBarVendor(VendorFormVO formVO) throws SQLException, Exception;
	public VendorFormVO viewBarVendor(VendorFormVO formVO) throws SQLException, Exception;
	public Map<String, Object> getVendorList(Map<String, Object> map) throws SQLException,Exception;
	public List<VendorFormVO> getBarVendorMap(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getBarVendorIndexMap(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getBarVendorByName(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getBarVendorsByRecords(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getPagination(VendorFormVO formVO) throws SQLException, Exception;
	public int deleteBarVendor(VendorFormVO formVO) throws SQLException, Exception;
	
	public List<VendorFormVO> getBarVendorsByCreatedDates(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getBarVendorsByType(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getBarVendorById(VendorFormVO formVO) throws SQLException, Exception;
}
