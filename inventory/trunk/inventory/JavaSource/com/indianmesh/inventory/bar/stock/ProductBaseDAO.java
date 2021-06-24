package com.indianmesh.inventory.bar.stock;

import java.sql.SQLException;
import java.util.List;

public interface ProductBaseDAO {
		
	public ProductFormVO getKitchenStockProductMap(ProductFormVO formVO) throws SQLException, Exception;
	public void updateKitchenStockProductMap(ProductFormVO formVO) throws SQLException, Exception;
	
	public ProductFormVO getKitchenAvailableStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public void updateKitchenAvailableStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	
	public ProductFormVO getKitchenConsumedStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public void updateKitchenConsumedStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	
	public ProductFormVO getKitchenRequiredStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public void updateKitchenRequiredStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;	
	
	public List<ProductFormVO> getKitchenStockProductsByRecords(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenStockProductsByPagination(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenStockProductsByCreatedDate(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenStockProductsByLastModifiedDate(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenStockProductsByVendorId(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenStockProductsByProductId(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenStockProductsByVendorName(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenStockProductsByProductName(ProductFormVO formVO) throws SQLException, Exception;
}
