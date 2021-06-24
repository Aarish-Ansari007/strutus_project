package com.indianmesh.inventory.kitchen.inventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.utils.DAOFactory;
import com.indianmesh.inventory.utils.dbConnection.Constant;

public class ProductManager {

	private static int DATA_SOURCE = Constant.DBNAME;	
	ProductBaseDAO productBaseDAOObject = null;
	
	public ProductManager(){
		productBaseDAOObject = (ProductBaseDAO) DAOFactory.getKitchenInventoryProductDAO(DATA_SOURCE);
	}
	
	public List<ProductFormVO> getKitchenInventoryProductMap(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getKitchenInventoryProductMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenInventoryProductIndexMap(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getKitchenInventoryProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public Map<String, Object> getKitchenInventoryProductList(Map<String, Object> map)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getKitchenInventoryProductList(map);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public ProductFormVO addKitchenInventoryProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.addKitchenInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public ProductFormVO editKitchenInventoryProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.editKitchenInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
		
	public int updateKitchenInventoryProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.updateKitchenInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public int updateKitchenInventoryProductStatus(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.updateKitchenInventoryProductStatus(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public ProductFormVO viewKitchenInventoryProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.viewKitchenInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}

	public int deleteKitchenInventoryProduct(ProductFormVO formVO) throws SQLException,Exception {
		try{
			return productBaseDAOObject.deleteKitchenInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<String> viewKitchenIngredientProductList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.viewKitchenIngredientProductList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
    public List<String> viewKitchenIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.viewKitchenIngredientProductQuantityList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}

	public List<String> viewKitchenIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.viewKitchenIngredientProductQuantityTypeList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<String> getKitchenIngredientProductList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getKitchenIngredientProductList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
    public List<String> getKitchenIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getKitchenIngredientProductQuantityList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}

	public List<String> getKitchenIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getKitchenIngredientProductQuantityTypeList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
		
	public List<ProductFormVO> getKitchenInventoryProductsByRecords(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getKitchenInventoryProductsByRecords(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<ProductFormVO> getPagination(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getPagination(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<ProductFormVO> getKitchenInventoryProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception {
		try{
			return productBaseDAOObject.getKitchenInventoryProductsByCreatedDates(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<ProductFormVO> getKitchenInventoryProductsByName(ProductFormVO formVO) throws SQLException, Exception {
		try{
			return productBaseDAOObject.getKitchenInventoryProductsByName(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<ProductFormVO> getKitchenInventoryProductsById(ProductFormVO formVO) throws SQLException, Exception {
		try{
			return productBaseDAOObject.getKitchenInventoryProductsById(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
}
