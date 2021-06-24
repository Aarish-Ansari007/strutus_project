package com.indianmesh.inventory.kitchen.vendor;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indianmesh.inventory.kitchen.stock.ProductFormVO;
import com.indianmesh.inventory.utils.DAOFactory;
import com.indianmesh.inventory.utils.dbConnection.Constant;

public class VendorManager {

	private static int DATA_SOURCE = Constant.DBNAME;	
	VendorBaseDAO vendorBaseDAOObject = null;
	
	public VendorManager(){
		vendorBaseDAOObject = (VendorBaseDAO) DAOFactory.getKitchenVendorDAO(DATA_SOURCE);
	}
	
	public  Map<String, String> getKitchenVendorList(Map<String, String> map)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getKitchenVendorList(map);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public  Map<String, String> getSupplierList(Map<String, String> map)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getSupplierList(map);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getKitchenVendorIndexMap(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getKitchenVendorIndexMap(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getKitchenVendorMap(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getKitchenVendorMap(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public VendorFormVO addKitchenVendor(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.addKitchenVendor(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public String updateKitchenVendor(VendorFormVO formVO)throws SQLException,Exception {
		try{
			return vendorBaseDAOObject.updateKitchenVendor(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
			
	public List<VendorFormVO> getkitchenVendorsByRecords(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getkitchenVendorsByRecords(formVO);	
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getPagination(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getPagination(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public int getPaginationCount()throws SQLException,Exception{
		try{
			return vendorBaseDAOObject.getPaginationCount();	
			}
		catch (Exception e) {
			throw e;
		}
	}
	
	public VendorFormVO editkitchenVendor(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.editkitchenVendor(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public VendorFormVO viewKitchenVendor(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.viewKitchenVendor(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public VendorFormVO getSupplyKitchenProductsFormByVendorId(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getSupplyKitchenProductsFormByVendorId(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<ProductFormVO> getKitchenProductSupplyIndexMapByVendorId(ProductFormVO formVO) throws SQLException, Exception{
		
		try{
			return vendorBaseDAOObject.getKitchenProductSupplyIndexMapByVendorId(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public int deleteKitchenVendor(VendorFormVO formVO) throws SQLException,Exception{
			
		try{
			return vendorBaseDAOObject.deleteKitchenVendor(formVO);		
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
		
	public List<VendorFormVO> getkitchenVendorsByType(VendorFormVO formVO) throws SQLException, Exception{
				
			try{
				return vendorBaseDAOObject.getkitchenVendorsByType(formVO);		
				}
			catch (Exception e) {
				// TODO: handle exception
				throw e;
			}	
		}
			
	public List<VendorFormVO> getKitchenVendorById(VendorFormVO formVO) throws SQLException, Exception{
			
		try{
			return vendorBaseDAOObject.getKitchenVendorById(formVO);		
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getKitchenVendorByName(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getKitchenVendorByName(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getKitchenVendorByPhone(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getKitchenVendorByPhone(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getkitchenVendorsByCategory(VendorFormVO formVO)throws SQLException, Exception{
		try{
				return vendorBaseDAOObject.getkitchenVendorsByType(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getkitchenVendorsByCreationDate(VendorFormVO formVO)throws SQLException, Exception{
		try{
				return vendorBaseDAOObject.getKitchenVendorsByCreationDates(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}	
	
	public Map<String, String> getVendorTypeList(Map<String, String> map)throws SQLException, Exception{
		try{
				return vendorBaseDAOObject.getVendorTypeList(map);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}	
	
	public VendorFormVO getStockKitchenVendor(VendorFormVO formVO)throws SQLException, Exception{
		try{
				return vendorBaseDAOObject.getStockKitchenVendor(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
}
