package com.indianmesh.inventory.bar.product;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.utils.DAOFactory;
import com.indianmesh.inventory.utils.dbConnection.Constant;

public class ProductManager {

	private static int DATA_SOURCE = Constant.DBNAME;	
	ProductBaseDAO productBaseDAOObject = null;
	
	public ProductManager(){
		productBaseDAOObject = (ProductBaseDAO) DAOFactory.getBarProductDAO(DATA_SOURCE);
	}
	
	public List<ProductFormVO> getBarProductMap(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getBarProductMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getBarProductIndexMap(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getBarProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public Map<String, Object> getBarProductList(Map<String, Object> map)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getBarProductList(map);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public ProductFormVO addBarProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.addBarProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public ProductFormVO editBarProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.editBarProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public int updateBarProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.updateBarProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<ProductFormVO> getBarProductsByRecords(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getBarProductsByRecords(formVO);	
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getPagination(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getPagination(formVO);	
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public int deleteBarProduct(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.deleteBarProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}

	public List<ProductFormVO> getBarProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getBarProductsByCreatedDates(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
		
	public List<ProductFormVO> getBarProductsByVendor(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getBarProductsByVendor(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}

	public List<ProductFormVO> getBarProductsById(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getBarProductsById(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
}
