package com.indianmesh.inventory.kitchen.product;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.utils.DAOFactory;
import com.indianmesh.inventory.utils.dbConnection.Constant;

public class ProductManager {

	private static int DATA_SOURCE = Constant.DBNAME;	
	ProductBaseDAO productBaseDAOObject = null;
	
	public ProductManager(){
		productBaseDAOObject = (ProductBaseDAO) DAOFactory.getKitchenProductDAO(DATA_SOURCE);
	}
	
	public List<ProductFormVO> getKitchenProductMap(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getKitchenProductMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductIndexMap(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getKitchenProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public Map<String, String> getKitchenProductList(Map<String, String> map)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getKitchenProductList(map);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public ProductFormVO addKitchenProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.addKitchenProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public ProductFormVO editKitchenProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.editKitchenProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public int updateKitchenProduct(ProductFormVO formVO)throws SQLException,Exception {
		try{
			return productBaseDAOObject.updateKitchenProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}	
	}
	
	public List<ProductFormVO> getKitchenProductsByRecords(ProductFormVO formVO)throws SQLException,Exception{
		try{
			return productBaseDAOObject.getKitchenProductsByRecords(formVO);	
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
	
	public int getPaginationCount()throws SQLException,Exception{
		try{
			return productBaseDAOObject.getPaginationCount();	
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public int deleteKitchenProduct(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.deleteKitchenProduct(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}

	public List<ProductFormVO> getKitchenProductsByCreatedDates(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductsByCreatedDates(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
		
	public List<ProductFormVO> getKitchenProductsByVendor(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductsByVendor(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}

	public List<ProductFormVO> getKitchenProductsById(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductsById(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getkitchenProductsByName(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getkitchenProductsByName(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}

}
