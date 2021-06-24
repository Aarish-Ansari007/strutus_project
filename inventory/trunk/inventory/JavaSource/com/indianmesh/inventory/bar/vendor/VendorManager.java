package com.indianmesh.inventory.bar.vendor;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.indianmesh.inventory.utils.DAOFactory;
import com.indianmesh.inventory.utils.dbConnection.Constant;

public class VendorManager {

	private static int DATA_SOURCE = Constant.DBNAME;	
	VendorBaseDAO vendorBaseDAOObject = null;
	
	public VendorManager(){
		vendorBaseDAOObject = (VendorBaseDAO) DAOFactory.getBarVendorDAO(DATA_SOURCE);
	}
	
	public  Map<String, Object> getBarVendorList(Map<String, Object> map)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getVendorList(map);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getBarVendorIndexMap(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getBarVendorIndexMap(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getBarVendorMap(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getBarVendorMap(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public VendorFormVO addBarVendor(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.addBarVendor(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public String updateBarVendor(VendorFormVO formVO)throws SQLException,Exception {
		try{
			return vendorBaseDAOObject.updateBarVendor(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
		
	public List<VendorFormVO> getBarVendorByName(VendorFormVO formVO)throws SQLException,Exception{
			
		try{
			return vendorBaseDAOObject.getBarVendorByName(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getBarVendorsByRecords(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.getBarVendorsByRecords(formVO);	
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
	
	public VendorFormVO editBarVendor(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.editBarVendor(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public VendorFormVO viewBarVendor(VendorFormVO formVO)throws SQLException,Exception{
		
		try{
			return vendorBaseDAOObject.viewBarVendor(formVO);
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public int deleteBarVendor(VendorFormVO formVO) throws SQLException,Exception{
			
		try{
			return vendorBaseDAOObject.deleteBarVendor(formVO);		
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getBarVendorsByCreatedDates(VendorFormVO formVO) throws SQLException, Exception{
			
		try{
			return vendorBaseDAOObject.getBarVendorsByCreatedDates(formVO);		
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
	public List<VendorFormVO> getBarVendorsByType(VendorFormVO formVO) throws SQLException, Exception{
				
			try{
				return vendorBaseDAOObject.getBarVendorsByType(formVO);		
				}
			catch (Exception e) {
				// TODO: handle exception
				throw e;
			}	
		}
			
	public List<VendorFormVO> getBarVendorById(VendorFormVO formVO) throws SQLException, Exception{
			
		try{
			return vendorBaseDAOObject.getBarVendorById(formVO);		
			}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}	
	}
	
}
