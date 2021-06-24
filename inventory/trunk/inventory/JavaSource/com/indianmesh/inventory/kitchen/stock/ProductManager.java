package com.indianmesh.inventory.kitchen.stock;

import java.sql.SQLException;
import java.util.List;

import com.indianmesh.inventory.kitchen.consumption.KitchenConsumptionFormVO;
import com.indianmesh.inventory.utils.DAOFactory;
import com.indianmesh.inventory.utils.dbConnection.Constant;

public class ProductManager {

	private static int DATA_SOURCE = Constant.DBNAME;
	
	ProductBaseDAO productBaseDAOObject = null;
	
	public ProductManager(){
		productBaseDAOObject = (ProductBaseDAO) DAOFactory.getKitchenProductStockDAO(DATA_SOURCE);
	}
	
	public void updateKitchenProductConsumptionAfterSingleEntry(ProductFormVO formVO) throws SQLException, Exception{
		try{
			 productBaseDAOObject.updateKitchenProductConsumptionAfterSingleEntry(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}	
	
	public void updateKitchenProductConsumptionAfterMultipleEntry(ProductFormVO formVO) throws SQLException, Exception{
		try{
			 productBaseDAOObject.updateKitchenProductConsumptionAfterMultipleEntry(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}	
	
	public void updateKitchenProductRequirementAfterSingleEntry(ProductFormVO formVO) throws SQLException, Exception{
		try{
			 productBaseDAOObject.updateKitchenProductRequirementAfterSingleEntry(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void updateKitchenProductRequirementAfterMultipleEntry(ProductFormVO formVO) throws SQLException, Exception{
		try{
			 productBaseDAOObject.updateKitchenProductRequirementAfterMultipleEntry(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}	
	
	//----------------------------------Product Reports------------------------------------------------------------------
	
	public List<ProductFormVO> getReportIndexMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductReportIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}	
	
	public List<ProductFormVO> getReportByProductId(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductReportByProductId(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getReportByProductName(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductReportByProductName(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getReportByStatus(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductReportByStatus(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getReportByCreatedDate(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductReportByCreatedDate(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getReportByPagination(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductReportByPagination(formVO);
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
	
	//----------------------------------Product Requirements--------------------------------------------------------------
	
	public List<ProductFormVO> getKitchenProductRequirementIndexMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductRequirementIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}	
	
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByProductName(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductRequirementIndexMapByProductName(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByProductId(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductRequirementIndexMapByProductId(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByCreatedDate(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductRequirementIndexMapByCreatedDate(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByPagination(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductRequirementIndexMapByPagination(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByRecords(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductRequirementIndexMapByRecords(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByStatus(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductRequirementIndexMapByStatus(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	//----------------------------------Product Consumption--------------------------------------------------------
	
	public List<ProductFormVO> getKitchenProductConsumptionIndexMap(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductConsumptionIndexMap(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}	
	
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByProductName(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductConsumptionIndexMapByProductName(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByProductId(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductConsumptionIndexMapByProductId(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByCreatedDate(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductConsumptionIndexMapByCreatedDate(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByPagination(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductConsumptionIndexMapByPagination(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByRecords(ProductFormVO formVO) throws SQLException, Exception{
		try{
			return productBaseDAOObject.getKitchenProductConsumptionIndexMapByRecords(formVO);
			}
		catch (Exception e) {
			throw e;
		}
	}
}
