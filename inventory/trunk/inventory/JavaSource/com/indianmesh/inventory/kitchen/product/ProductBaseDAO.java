package com.indianmesh.inventory.kitchen.product;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.kitchen.vendor.VendorFormVO;

public interface ProductBaseDAO {
		
	public ProductFormVO addKitchenProduct(ProductFormVO formVO) throws SQLException, Exception;
	public ProductFormVO editKitchenProduct(ProductFormVO formVO) throws SQLException, Exception;
	public int updateKitchenProduct(ProductFormVO formVO) throws SQLException, Exception;
	public Map<String, String> getKitchenProductList(Map<String, String> map) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductsByRecords(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getPagination(ProductFormVO formVO) throws SQLException, Exception;
	public int getPaginationCount()throws SQLException,Exception;
	public int deleteKitchenProduct(ProductFormVO formVO) throws SQLException, Exception;
	
	public List<ProductFormVO> getKitchenProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductsByVendor(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductsById(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getkitchenProductsByName(ProductFormVO formVO) throws SQLException, Exception;
}
