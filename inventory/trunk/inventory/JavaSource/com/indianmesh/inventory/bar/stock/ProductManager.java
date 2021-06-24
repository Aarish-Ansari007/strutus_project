package com.indianmesh.inventory.bar.stock;

import java.sql.SQLException;
import java.util.List;

import com.indianmesh.inventory.utils.DAOFactory;
import com.indianmesh.inventory.utils.dbConnection.Constant;

public class ProductManager {

	private static int DATA_SOURCE = Constant.DBNAME;
	
	ProductBaseDAO productBaseDAOObject = null;
	
	public ProductManager(){
		productBaseDAOObject = (ProductBaseDAO) DAOFactory.getKitchenProductStockDAO(DATA_SOURCE);
	}
		
	public ProductFormVO getKitchenStockProductMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenStockProductMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void updateKitchenStockProductMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
				productBaseDAOObject.updateKitchenStockProductMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public ProductFormVO getKitchenAvailableStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenAvailableStockProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void updateKitchenAvailableStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
				productBaseDAOObject.updateKitchenAvailableStockProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public ProductFormVO getKitchenConsumedStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenConsumedStockProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void updateKitchenConsumedStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
				productBaseDAOObject.updateKitchenConsumedStockProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public ProductFormVO getKitchenRequiredStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenRequiredStockProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void updateKitchenRequiredStockProductIndexMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
				productBaseDAOObject.updateKitchenRequiredStockProductIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenStockProductsByRecords(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenStockProductsByRecords(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenStockProductsByPagination(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenStockProductsByPagination(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenStockProductsByCreatedDate(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenStockProductsByCreatedDate(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenStockProductsByLastModifiedDate(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenStockProductsByLastModifiedDate(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenStockProductsByVendorId(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenStockProductsByVendorId(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenStockProductsByProductId(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenStockProductsByProductId(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenStockProductsByVendorName(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenStockProductsByVendorName(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenStockProductsByProductName(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenStockProductsByProductName(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}

}
