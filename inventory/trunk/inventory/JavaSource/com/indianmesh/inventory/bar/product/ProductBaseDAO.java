package com.indianmesh.inventory.bar.product;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.kitchen.vendor.VendorFormVO;

public interface ProductBaseDAO {
		
	public ProductFormVO addBarProduct(ProductFormVO formVO) throws SQLException, Exception;
	public ProductFormVO editBarProduct(ProductFormVO formVO) throws SQLException, Exception;
	public int updateBarProduct(ProductFormVO formVO) throws SQLException, Exception;
	public Map<String, Object> getBarProductList(Map<String, Object> map) throws SQLException, Exception;
	public List<ProductFormVO> getBarProductMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getBarProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getBarProductsByRecords(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getPagination(ProductFormVO formVO) throws SQLException, Exception;
	public int deleteBarProduct(ProductFormVO formVO) throws SQLException, Exception;
	
	public List<ProductFormVO> getBarProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getBarProductsByVendor(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getBarProductsById(ProductFormVO formVO) throws SQLException, Exception;
}
