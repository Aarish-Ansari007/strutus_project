package com.indianmesh.inventory.kitchen.stock;

import java.sql.SQLException;
import java.util.List;

import com.indianmesh.inventory.kitchen.consumption.KitchenConsumptionFormVO;

public interface ProductBaseDAO {
		
	public void updateKitchenProductConsumptionAfterSingleEntry(ProductFormVO formVO) throws SQLException, Exception;
	public void updateKitchenProductConsumptionAfterMultipleEntry(ProductFormVO formVO) throws SQLException, Exception;
	public void updateKitchenProductRequirementAfterSingleEntry(ProductFormVO formVO) throws SQLException, Exception;
	public void updateKitchenProductRequirementAfterMultipleEntry(ProductFormVO formVO) throws SQLException, Exception;

	//-----------------------------Product Requirements---------------------------------------------------------------------
	public List<ProductFormVO> getKitchenProductRequirementIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByProductName(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByProductId(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByCreatedDate(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByPagination(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByRecords(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductRequirementIndexMapByStatus(ProductFormVO formVO) throws SQLException, Exception;
	
	//-----------------------------Product Consumptions---------------------------------------------------------------------
	public List<ProductFormVO> getKitchenProductConsumptionIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByProductName(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByProductId(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByCreatedDate(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByPagination(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductConsumptionIndexMapByRecords(ProductFormVO formVO) throws SQLException, Exception;
	
	//----------------------------Product Reports---------------------------------------------------------------------------------
	public List<ProductFormVO> getKitchenProductReportIndexMap(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductReportByProductId(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductReportByProductName(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductReportByStatus(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductReportByCreatedDate(ProductFormVO formVO) throws SQLException, Exception;
	public List<ProductFormVO> getKitchenProductReportByPagination(ProductFormVO formVO) throws SQLException, Exception;
	public int getPaginationCount()throws SQLException,Exception;
	
}
