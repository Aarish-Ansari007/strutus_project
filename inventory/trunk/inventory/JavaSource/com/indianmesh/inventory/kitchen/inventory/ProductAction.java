package com.indianmesh.inventory.kitchen.inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.indianmesh.inventory.utils.utilDBMethod.UtilDBMethodManager;

public class ProductAction extends DispatchAction {
	
	public ActionForward getHomePage(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
				
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		try{			
			String userid=(String) request.getParameter("userid"); 
			if(userid==null){
				return mapping.findForward("loginPage");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("loginPage");			
	}
	
	public ActionForward getIndexkitchenInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
					
		ProductManager productManager = new ProductManager();
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();
		
		try{						
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenInventoryProductIndexMap(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("criteriaList", utilDBMethodManager.getVendorRecord());
			request.setAttribute("productList",productList);	
			//request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");			
	}
	
	public ActionForward getIndexkitchenInventoryProductsBySearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
					
		ProductManager productManager = new ProductManager();
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();
		
		try{						
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenInventoryProductIndexMap(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("criteriaList", utilDBMethodManager.getVendorRecord());
			request.setAttribute("productList", productList);
			//request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");			
	}
	
	public ActionForward addKitchenInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();		
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		
		try{						
			formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
			formV.setSubsellerId(RandomStringUtils.randomAlphanumeric(25));
			
			if(request.getParameter("inventoryProductId")!=null)		
				formV.setInventoryProductIngredientList(Arrays.asList(request.getParameter("inventoryProductId").split(",")));
						
			if(request.getParameter("inventoryProductQuantity")!=null)			
				formV.setInventoryProductQuantityList(Arrays.asList(request.getParameter("inventoryProductQuantity").split(",")));			
			
			if(request.getParameter("inventoryProductQuantityTypeId")!=null)	
				formV.setInventoryProductQuantityTypeList(Arrays.asList(request.getParameter("inventoryProductQuantityTypeId").split(",")));
									
			BeanUtils.copyProperties(formVO, formV);
			
			request.setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.setAttribute("ingredientQuantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.setAttribute("inventoryProductList",productManager.getKitchenInventoryProductList(map).entrySet());		
			request.setAttribute("compositProductVO",productManager.addKitchenInventoryProduct(formVO));	
			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("addKitchenInventoryProduct");			
	}
	
	public ActionForward editKitchenInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();	
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
				
		try{			
			formV.setProductId(request.getParameter("productId"));	
			formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
			formV.setSubsellerId(RandomStringUtils.randomAlphanumeric(25));
			
			BeanUtils.copyProperties(formVO, formV);
			
			List<String> tmp = null;

			//Sports Facility
			tmp = productManager.viewKitchenIngredientProductList(formVO);
			if(tmp!=null){
			    String str11="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str11=str11+s+",";
				}
				request.getSession().setAttribute("inventoryProductIds",str11);
			}

			//camp observer
			tmp = productManager.viewKitchenIngredientProductQuantityList(formVO);
			if(tmp!=null){
			    String str9="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str9=str9+s+",";
				}
				request.getSession().setAttribute("inventoryProductQuantites",str9);	
			}
			
			//Sports Facility
			tmp = productManager.viewKitchenIngredientProductQuantityTypeList(formVO);
			if(tmp!=null){
			    String str11="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str11=str11+s+",";
				}
				request.getSession().setAttribute("inventoryProductQuantityTypeIds",str11);
			}
			
			request.setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.setAttribute("ingredientQuantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.setAttribute("inventoryProductList",productManager.getKitchenInventoryProductList(map).entrySet());	
			
			request.setAttribute("length", productManager.getKitchenInventoryProductMap(formVO).size());
			request.setAttribute("compositProductVO",productManager.editKitchenInventoryProduct(formVO));
			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("editKitchenInventoryProduct");			
	}
	
	public ActionForward updateKitchenInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		
		try{	
			formV.setProductId(request.getParameter("productId"));
			formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
			formV.setSubsellerId(RandomStringUtils.randomAlphanumeric(25));
			
			if(request.getParameter("inventoryProductId")!=null)			
				formV.setInventoryProductIngredientList(Arrays.asList(request.getParameter("inventoryProductId").split(",")));
						
			if(request.getParameter("inventoryProductQuantity")!=null)			
				formV.setInventoryProductQuantityList(Arrays.asList(request.getParameter("inventoryProductQuantity").split(",")));
					
			if(request.getParameter("inventoryProductQuantityTypeId")!=null)		
				formV.setInventoryProductQuantityTypeList(Arrays.asList(request.getParameter("inventoryProductQuantityTypeId").split(",")));
			
			BeanUtils.copyProperties(formVO, formV);
			request.setAttribute("compositProductVO",productManager.updateKitchenInventoryProduct(formVO));
			
			request.setAttribute("length", productManager.getKitchenInventoryProductMap(formVO).size());
			request.setAttribute("productList",productManager.getKitchenInventoryProductMap(formVO));	
			
			request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");			
	}
	
	public ActionForward updateKitchenInventoryProductStatus(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
					
		String productId = request.getParameter("productId");
		String status = request.getParameter("status");
		String statusOn = request.getParameter("statusOn");
		String statusOff = request.getParameter("statusOff");
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{	
			formV.setProductId(productId);
			formV.setStatus(status);
			formV.setStatusOn(statusOn);
			formV.setStatusOff(statusOff);
			
			BeanUtils.copyProperties(formVO, formV);			
			productManager.updateKitchenInventoryProductStatus(formVO);
			
			productList = productManager.getKitchenInventoryProductMap(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);	
			
			//request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					
					  
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");			
	}
		
	public ActionForward viewKitchenInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
				
		try{				
			formV.setProductId(request.getParameter("productId"));			
			BeanUtils.copyProperties(formVO, formV);
			
			request.setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.setAttribute("ingredientQuantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.setAttribute("inventoryProductList",productManager.getKitchenInventoryProductList(map).entrySet());
			
			List<String> tmp = null;

			//Sports Facility
			tmp = productManager.viewKitchenIngredientProductList(formVO);
			if(tmp!=null){
			    String str11="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str11=str11+s+",";
				}
				request.setAttribute("inventoryProductIds",str11);
			}

			//camp observer
			tmp = productManager.viewKitchenIngredientProductQuantityList(formVO);
			if(tmp!=null){
			    String str9="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str9=str9+s+",";
				}
				request.setAttribute("inventoryProductQuantites",str9);	
			}
			
			//Sports Facility
			tmp = productManager.viewKitchenIngredientProductQuantityTypeList(formVO);
			if(tmp!=null){
			    String str11="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str11=str11+s+",";
				}
				request.setAttribute("inventoryProductQuantityTypeIds",str11);
			}
			
			request.setAttribute("compositProductVO",productManager.viewKitchenInventoryProduct(formVO));			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("viewKitchenInventoryProduct");			
	}
		
	public ActionForward getKitchenInventoryProductsByRecords(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();
		
		try{	
			formV.setLimit(request.getParameter("limit"));
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenInventoryProductsByRecords(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			//request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");		
	}
	
	public ActionForward getPagination(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			String[] index = request.getParameter("index").split(",");	
			formV.setPageFrom(index[0]);
			formV.setPageTo(index[1]);
			
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getPagination(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			//request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");		
	}
	
	public ActionForward deleteKitchenInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();
		
		try{	
			BeanUtils.copyProperties(formVO, formV);
			int res = productManager.deleteKitchenInventoryProduct(formVO);
			productList = productManager.getKitchenInventoryProductMap(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);						  
			request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");				
	}
	
	public ActionForward clearKitchenInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
					
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{	
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenInventoryProductMap(formVO);
			int res = productManager.deleteKitchenInventoryProduct(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");	
	}
	
	public ActionForward getKitchenInventoryProductsByCreatedDates(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			formV.setFromDate(request.getParameter("start"));
			formV.setToDate(request.getParameter("end"));			
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenInventoryProductsByCreatedDates(formVO);
			request.setAttribute("productList",productManager.getPagination(formVO));			
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			//request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");	
		
	}
	public ActionForward getKitchenInventoryProductsByName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			formV.setName( request.getParameter("search"));			
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenInventoryProductsByName(formVO);
			request.setAttribute("productList",productManager.getPagination(formVO));			
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			//request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");			
	}
	
	public ActionForward getKitchenInventoryProductsById(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			formV.setProductId(request.getParameter("search"));			
			BeanUtils.copyProperties(formVO, formV);		
			productList = productManager.getKitchenInventoryProductsById(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			//request.getRequestDispatcher("kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenInventoryProduct");	
		
	}
	
	public ActionForward getProductTypeList(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		Map<String,String> recordMap = null;
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		
		try {				
				recordMap = utilDBMethodManager.getVendorRecord();
			} catch (Exception e1) {
				e1.printStackTrace();
		}
	
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
					
		StringBuffer sb = new StringBuffer();	
		if(recordMap!=null  && recordMap.size()!=0){
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<recordList>");
		sb.append("<valid>true</valid>");
		for (Iterator<String> it=recordMap.keySet().iterator(); it.hasNext(); ) 
		{  		
			String key = it.next();  
			String value = recordMap.get(key);  
			sb.append("<record>");
			sb.append("<recordId>"+key+"</recordId>");
			sb.append("<recordName>"+value+"</recordName>");
			sb.append("</record>");
			}
		sb.append("</recordList>");
		try {
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		else{
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<recordList>");
			sb.append("<valid>false</valid>");
			sb.append("</recordList>");
			try {
				response.getWriter().write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}
	
}
