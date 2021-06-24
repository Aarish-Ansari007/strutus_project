package com.indianmesh.inventory.kitchen.product;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

import com.indianmesh.inventory.kitchen.vendor.VendorManager;
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
	
	public ActionForward getIndexKitchenProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{	
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenProductIndexMap(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);	
		}catch(InvocationTargetException ex){			
			ex.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("indexKitchenProduct");			
	}
	
	public ActionForward getIndexKitchenProductsBySearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{	
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenProductIndexMap(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			//request.getRequestDispatcher("kitchenProduct.do?methodName=getIndexKitchenProduct").forward(request,response);					

		}catch(InvocationTargetException ex){			
			ex.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("indexKitchenProduct");			
	}

	public ActionForward addKitchenProduct(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		Map<String, String> map = new LinkedHashMap<String, String>(); 
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		ProductManager productManager = new ProductManager();
		VendorManager vendorManager = new VendorManager();

		try{			
			formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
			formV.setSubSellerId(RandomStringUtils.randomAlphanumeric(25));
			formV.setTransactionId(RandomStringUtils.randomAlphanumeric(25));
			formV.setInvoiceId(RandomStringUtils.randomAlphanumeric(25));
			
			BeanUtils.copyProperties(formVO, formV);
			request.setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.setAttribute("vendorList",vendorManager.getSupplierList(map).entrySet());
			request.setAttribute("amountPaidTypeList",utilDBMethodManager.getAmountPaidTypeList().entrySet());
			request.setAttribute("productFormVO",productManager.addKitchenProduct(formVO));				
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("addKitchenProduct");			
	}
	
	public ActionForward editKitchenProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		
		ProductFormVO formVO = new ProductFormVO();
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
				
		ProductManager registrationManager = new ProductManager();		
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();			
		VendorManager vendorManager = new VendorManager();

		try{			
			if(formV.getTransactionId() == null)
				formV.setTransactionId(RandomStringUtils.randomAlphanumeric(25));
			
			if(formV.getInvoiceId() == null)
				formV.setInvoiceId(RandomStringUtils.randomAlphanumeric(25));
			
			//formV.setProductId(request.getParameter("productId"));
			BeanUtils.copyProperties(formVO, formV);
			request.setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.setAttribute("vendorList",vendorManager.getSupplierList(map).entrySet());
			request.setAttribute("amountPaidTypeList",utilDBMethodManager.getAmountPaidTypeList().entrySet());
			request.setAttribute("ProductFormVO",registrationManager.editKitchenProduct(formVO));	

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("editKitchenProduct");			
	}
	
	public ActionForward updateKitchenProduct(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
				
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{	
			formV.setQuantityTypeOfTotal(request.getParameter("quantity"));
			BeanUtils.copyProperties(formVO, formV);
			productManager.updateKitchenProduct(formVO);
			productList = productManager.getKitchenProductMap(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);	
			request.getRequestDispatcher("kitchenProduct.do?methodName=getIndexKitchenProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenProduct");			
	}

	public ActionForward deleteKitchenProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			formV.setProductId(request.getParameter("productId"));
			BeanUtils.copyProperties(formVO, formV);
			int res = productManager.deleteKitchenProduct(formVO);
			productList = productManager.getKitchenProductMap(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			request.getRequestDispatcher("kitchenProduct.do?methodName=getIndexKitchenProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenProduct");			
	}
	
	public ActionForward getPagination(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		String index = request.getParameter("index");								
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{			
			formV.setPageFrom(index.split(",")[0]);
			formV.setPageTo(index.split(",")[1]);
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getPagination(formVO);
			request.setAttribute("length", productManager.getPaginationCount());
			request.setAttribute("productList",productList);	
			//request.getRequestDispatcher("kitchenProduct.do?methodName=getIndexKitchenProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenProduct");			
	}
	
	public ActionForward getKitchenProductsByRecords(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			formV.setLimit(request.getParameter("limit"));
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenProductsByRecords(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			//request.getRequestDispatcher("kitchenProduct.do?methodName=getIndexKitchenProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenProduct");			
	}
	
	public ActionForward getKitchenProductsByDates(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			formV.setFromDate(request.getParameter("start"));
			formV.setToDate(request.getParameter("end"));
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenProductsByCreatedDates(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);	
			//request.getRequestDispatcher("kitchenProduct.do?methodName=getIndexKitchenProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenProduct");	
	}
	
		
	public ActionForward getKitchenProductsByVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			formV.setVendorId(request.getParameter("vendor"));
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenProductsByVendor(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			//request.getRequestDispatcher("kitchenProduct.do?methodName=getIndexKitchenProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenProduct");	
	}

	public ActionForward getKitchenProductsById(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			formV.setProductId(request.getParameter("productId"));
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getKitchenProductsById(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);
			//request.getRequestDispatcher("kitchenProduct.do?methodName=getIndexKitchenProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenProduct");	
	}
	
	public ActionForward getkitchenProductsByName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{			
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();

		try{
			formV.setName(request.getParameter("name"));
			BeanUtils.copyProperties(formVO, formV);
			productList = productManager.getkitchenProductsByName(formVO);
			request.setAttribute("length", productList.size());
			request.setAttribute("productList",productList);	
			//request.getRequestDispatcher("kitchenProduct.do?methodName=getIndexKitchenProduct").forward(request,response);					

		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexKitchenProduct");	
		
	}
	
	public ActionForward getSupplierList(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		Map<String, String> productListMap = null;
		Map<String, String> map = new LinkedHashMap<String, String>(); 
		VendorManager vendorManager = new VendorManager();

		try {			
				productListMap = vendorManager.getSupplierList(map);
			} catch (Exception e1) {
				e1.printStackTrace();
		}
	
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		
		//HttpSession httpSession = request.getSession();
		//httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		StringBuffer sb = new StringBuffer();	
		if(productListMap!=null  && productListMap.size()!=0){
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<vendorsList>");
		sb.append("<valid>true</valid>");
		for (Iterator<String> it=productListMap.keySet().iterator(); it.hasNext(); ) 
		{  		
			String key = it.next();  
			String value = productListMap.get(key);  
			sb.append("<vendor>");
			sb.append("<vendorId>"+key+"</vendorId>");
			sb.append("<vendorName>"+value+"</vendorName>");
			sb.append("</vendor>");
		}
		sb.append("</vendorsList>");
		try {
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		else{
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<vendorsList>");
			sb.append("<valid>false</valid>");
			sb.append("</vendorsList>");
			try {
				response.getWriter().write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}

}
