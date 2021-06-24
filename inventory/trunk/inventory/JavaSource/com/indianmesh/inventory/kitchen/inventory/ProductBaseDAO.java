package com.indianmesh.inventory.kitchen.inventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.kitchen.inventory.ProductFormVO;

public interface ProductBaseDAO {
	
	public int deleteKitchenInventoryProduct(ProductFormVO formVO) throws SQLException, Exception;
	public ProductFormVO addKitchenInventoryProduct(ProductFormVO formVO) throws SQLException,Exception;
	public ProductFormVO editKitchenInventoryProduct(ProductFormVO formVO) throws SQLException,Exception;
	public int updateKitchenInventoryProductStatus(ProductFormVO formVO) throws SQLException, Exception;
	public int updateKitchenInventoryProduct(ProductFormVO formVO) throws SQLException,Exception;
	public ProductFormVO viewKitchenInventoryProduct(ProductFormVO formVO) throws SQLException,Exception;
	public List<ProductFormVO> getKitchenInventoryProductMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenInventoryProductIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenInventoryProductsByRecords(ProductFormVO formVO)throws SQLException,Exception;
	public List<ProductFormVO> getPagination(ProductFormVO formVO)throws SQLException,Exception;
	public Map<String, Object> getKitchenInventoryProductList(Map<String, Object> map) throws SQLException, Exception;
	public List<String> viewKitchenIngredientProductList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> viewKitchenIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> viewKitchenIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> getKitchenIngredientProductList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> getKitchenIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception;
	public List<String> getKitchenIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception;
	
	public List<ProductFormVO> getKitchenInventoryProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenInventoryProductsByName(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenInventoryProductsById(ProductFormVO formVO) throws SQLException, Exception;
	 
}
