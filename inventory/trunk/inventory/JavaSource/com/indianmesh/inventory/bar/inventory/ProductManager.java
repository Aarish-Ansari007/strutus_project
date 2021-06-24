package com.indianmesh.inventory.bar.inventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.bar.inventory.ProductBaseDAO;
import com.indianmesh.inventory.bar.inventory.ProductFormVO;
import com.indianmesh.inventory.utils.DAOFactory;
import com.indianmesh.inventory.utils.dbConnection.Constant;

public class ProductManager {

	private static int DATA_SOURCE = Constant.DBNAME;	
	ProductBaseDAO productBaseDAOObject = null;
	
	public ProductManager(){
		productBaseDAOObject = (ProductBaseDAO) DAOFactory.getBarInventoryProductDAO(DATA_SOURCE);
	}
	
	public List<ProductFormVO> getBarInventoryProductMap(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getBarInventoryProductMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getBarInventoryProductIndexMap(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getBarInventoryProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public Map<String, String> getBarInventoryProductList(Map<String, String> map)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getBarInventoryProductList(map);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public ProductFormVO addBarInventoryProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.addBarInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public ProductFormVO editBarInventoryProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.editBarInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
		
	public int updateBarInventoryProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.updateBarInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public int updateBarInventoryProductStatus(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.updateBarInventoryProductStatus(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public ProductFormVO viewBarInventoryProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.viewBarInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}

	public int deleteBarInventoryProduct(ProductFormVO formVO) throws SQLException,Exception {
		try{
			return productBaseDAOObject.deleteBarInventoryProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<String> viewBarIngredientProductList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.viewBarIngredientProductList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
    public List<String> viewBarIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.viewBarIngredientProductQuantityList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}

	public List<String> viewBarIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.viewBarIngredientProductQuantityTypeList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<String> getBarIngredientProductList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getBarIngredientProductList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
    public List<String> getBarIngredientProductQuantityList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getBarIngredientProductQuantityList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}

	public List<String> getBarIngredientProductQuantityTypeList(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getBarIngredientProductQuantityTypeList(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
		
	public List<ProductFormVO> getBarInventoryProductsByRecords(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.getBarInventoryProductsByRecords(formVO);
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
	
	public List<ProductFormVO> getBarInventoryProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception {
		try{
			return productBaseDAOObject.getBarInventoryProductsByCreatedDates(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<ProductFormVO> getBarInventoryProductsByName(ProductFormVO formVO) throws SQLException, Exception {
		try{
			return productBaseDAOObject.getBarInventoryProductsByName(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<ProductFormVO> getBarInventoryProductsById(ProductFormVO formVO) throws SQLException, Exception {
		try{
			return productBaseDAOObject.getBarInventoryProductsById(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
}
