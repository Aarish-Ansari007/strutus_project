package com.indianmesh.inventory.bar.inventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.bar.inventory.ProductFormVO;

public interface ProductBaseDAO {
	
	public int deleteBarInventoryProduct(ProductFormVO formVO) throws SQLException, Exception;
	public ProductFormVO addBarInventoryProduct(ProductFormVO formVO) throws SQLException,Exception;
	public ProductFormVO editBarInventoryProduct(ProductFormVO formVO) throws SQLException,Exception;
	public int updateBarInventoryProductStatus(ProductFormVO formVO) throws SQLException, Exception;
	public int updateBarInventoryProduct(ProductFormVO formVO) throws SQLException,Exception;
	public ProductFormVO viewBarInventoryProduct(ProductFormVO formVO) throws SQLException,Exception;
	public List<ProductFormVO> getBarInventoryProductMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getBarInventoryProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getBarInventoryProductsByRecords(ProductFormVO formVO)throws SQLException,Exception;
	public List<ProductFormVO> getPagination(ProductFormVO formVO)throws SQLException,Exception;
	public Map<String, String> getBarInventoryProductList(Map<String, String> map) throws SQLException, Exception;
	public List<String> viewBarIngredientProductList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> viewBarIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> viewBarIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> getBarIngredientProductList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> getBarIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> getBarIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception;
	
	public List<ProductFormVO> getBarInventoryProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getBarInventoryProductsByName(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getBarInventoryProductsById(ProductFormVO formVO) throws SQLException, Exception;
	 
	
		
}
