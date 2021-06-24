package com.indianmesh.inventory.utils;

//import com.indianmesh.inventory.kitchen.consumption.ConsumptionDAOImp;
import com.indianmesh.inventory.kitchen.product.ProductBaseDAO;
import com.indianmesh.inventory.kitchen.product.ProductDAOImp;
//import com.indianmesh.inventory.kitchen.report.ReportDAOImp;
//import com.indianmesh.inventory.kitchen.requirement.RequirementDAOImp;
import com.indianmesh.inventory.kitchen.vendor.VendorBaseDAO;
import com.indianmesh.inventory.kitchen.vendor.VendorDAOImp;
import com.indianmesh.inventory.utils.dbConnection.Constant;
import com.indianmesh.inventory.utils.utilDBMethod.UtilDBMethodBaseDAO;
import com.indianmesh.inventory.utils.utilDBMethod.UtilDBMethodDAOImp;

public class DAOFactory {
	
	public static VendorBaseDAO getKitchenVendorDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new VendorDAOImp();
			}
		}
		return null;
	}
	
	public static ProductBaseDAO getKitchenProductDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new ProductDAOImp();
			}
		}
		return null;
	}
	
	public static com.indianmesh.inventory.kitchen.inventory.ProductBaseDAO getKitchenInventoryProductDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.kitchen.inventory.ProductDAOImp();
			}
		}
		return null;
	}
	
	public static com.indianmesh.inventory.kitchen.stock.ProductDAOImp getKitchenConsumptionDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.kitchen.stock.ProductDAOImp();
			}
		}
		return null;
	}
	
	public static com.indianmesh.inventory.kitchen.stock.ProductDAOImp getKitchenRequirementDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.kitchen.stock.ProductDAOImp();
			}
		}
		return null;
	}
	
	public static com.indianmesh.inventory.kitchen.stock.ProductDAOImp getKitchenReportDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.kitchen.stock.ProductDAOImp();
			}
		}
		return null;
	}
	
	public static UtilDBMethodBaseDAO getUtilMethodDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new UtilDBMethodDAOImp();
			}
		}
		return null;
	}
	
	public static ProductBaseDAO getBarProductDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new ProductDAOImp();
			}
		}
		return null;
	}
	
	public static com.indianmesh.inventory.bar.vendor.VendorBaseDAO getBarVendorDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.bar.vendor.VendorDAOImp();
			}
		}
		return null;
	}
	
	public static com.indianmesh.inventory.kitchen.inventory.ProductDAOImp getBarInventoryProductDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.kitchen.inventory.ProductDAOImp();
			}
		}
		return null;
	}
	
	/*public static com.indianmesh.inventory.bar.consumption.ConsumptionDAOImp getBarConsumptionDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.bar.consumption.ConsumptionDAOImp();
			}
		}
		return null;
	}
	
	public static com.indianmesh.inventory.bar.requirement.RequirementDAOImp getBarRequirementDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.bar.requirement.RequirementDAOImp();
			}
		}
		return null;
	}
	
	public static com.indianmesh.inventory.bar.report.ReportDAOImp getBarReportDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.bar.report.ReportDAOImp();
			}
		}
		return null;
	}*/
	
	public static com.indianmesh.inventory.kitchen.stock.ProductDAOImp getKitchenProductStockDAO(int iDAO) {

		switch (iDAO) {
			case Constant.DBNAME_POSTGRE: {
				return new com.indianmesh.inventory.kitchen.stock.ProductDAOImp();
			}
		}
		return null;
	}

}

