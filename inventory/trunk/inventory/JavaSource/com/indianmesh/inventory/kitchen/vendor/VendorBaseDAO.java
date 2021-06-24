package com.indianmesh.inventory.kitchen.vendor;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indianmesh.inventory.kitchen.stock.ProductFormVO;

public interface VendorBaseDAO {
	
	public VendorFormVO addKitchenVendor(VendorFormVO formVO) throws SQLException, Exception;
	public VendorFormVO editkitchenVendor(VendorFormVO formVO) throws SQLException, Exception;
	public String updateKitchenVendor(VendorFormVO formVO) throws SQLException, Exception;
	public VendorFormVO viewKitchenVendor(VendorFormVO formVO) throws SQLException, Exception;
	public VendorFormVO getSupplyKitchenProductsFormByVendorId(VendorFormVO formVO)throws SQLException,Exception;
	public List<ProductFormVO> getKitchenProductSupplyIndexMapByVendorId(ProductFormVO formVO) throws SQLException, Exception;
	public Map<String, String> getKitchenVendorList(Map<String, String> map) throws SQLException,Exception;
	public Map<String, String> getSupplierList(Map<String, String> map) throws SQLException,Exception;
	public List<VendorFormVO> getKitchenVendorMap(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getKitchenVendorIndexMap(VendorFormVO formVO) throws SQLException, Exception;
	
	public List<VendorFormVO> getkitchenVendorsByRecords(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getPagination(VendorFormVO formVO) throws SQLException, Exception;
	public int getPaginationCount()throws SQLException,Exception;
	public int deleteKitchenVendor(VendorFormVO formVO) throws SQLException, Exception;
	
	public List<VendorFormVO> getKitchenVendorById(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getKitchenVendorByName(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getKitchenVendorByPhone(VendorFormVO formVO) throws SQLException, Exception;
	public List<VendorFormVO> getkitchenVendorsByType(VendorFormVO formVO)throws SQLException, Exception;
	public List<VendorFormVO> getKitchenVendorsByCreationDates(VendorFormVO formVO)throws SQLException, Exception;
	public Map<String, String> getVendorTypeList(Map<String, String> map) throws SQLException,Exception;
	
	public VendorFormVO getStockKitchenVendor(VendorFormVO formVO)throws SQLException, Exception;

}
