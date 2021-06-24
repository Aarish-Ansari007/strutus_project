package com.indianmesh.inventory.bar.product;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
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

import com.indianmesh.inventory.bar.product.ProductManager;
import com.indianmesh.inventory.bar.vendor.VendorManager;
import com.indianmesh.inventory.utils.utilDBMethod.UtilDBMethodManager;

public class ProductAction extends DispatchAction {
	
	public ActionForward getHomePage(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
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
	
	public ActionForward getIndexBarProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		/*HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		ProductManager productManager = new ProductManager();
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();

		try{	
			BeanUtils.copyProperties(formVO, formV);
			request.getSession().setAttribute("length", productManager.getBarProductMap(formVO).size());
			request.getSession().setAttribute("size", productManager.getBarProductIndexMap(formVO).size());
			request.getSession().setAttribute("criteriaList", utilDBMethodManager.getCriteriaList());
			request.setAttribute("productList",productManager.getBarProductIndexMap(formVO));	
		}catch(InvocationTargetException ex){			
			ex.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
	    }*/
		
		return mapping.findForward("indexBarProduct");			
	}
	
	public ActionForward getIndexBarProductsBySearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		
		ProductManager productManager = new ProductManager();
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();

		try{	
			BeanUtils.copyProperties(formVO, formV);
			request.getSession().setAttribute("length", productManager.getBarProductMap(formVO).size());
			request.getSession().setAttribute("size", productManager.getBarProductIndexMap(formVO).size());
			request.getSession().setAttribute("criteriaList", utilDBMethodManager.getVendorRecord());
			request.setAttribute("productList",productManager.getBarProductIndexMap(formVO));	
		}catch(InvocationTargetException ex){			
			ex.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("indexBarProduct");			
	}

	public ActionForward addBarProduct(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		Map<String, Object> map = new LinkedHashMap<String, Object>(); 
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		ProductManager productManager = new ProductManager();
		VendorManager vendorManager = new VendorManager();
		
		try{
			String token1=RandomStringUtils.randomAlphanumeric(20);	
			String token2=RandomStringUtils.randomAlphanumeric(5);
			
			formV.setSellerId(token1);
			formV.setSubSellerId(token1+token2);
			
			BeanUtils.copyProperties(formVO, formV);
			request.getSession().setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.getSession().setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.getSession().setAttribute("vendorList",vendorManager.getBarVendorList(map).entrySet());
			request.getSession().setAttribute("amountPaidTypeList",utilDBMethodManager.getAmountPaidTypeList().entrySet());
			request.getSession().setAttribute("productFormVO",productManager.addBarProduct(formVO));
				
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("addBarProduct");			
	}
	
	public ActionForward editBarProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
				
		ProductManager registrationManager = new ProductManager();		
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();			
		VendorManager vendorManager = new VendorManager();
		
		try{
			formV.setProductId(request.getParameter("productId"));
			BeanUtils.copyProperties(formVO, formV);
			request.getSession().setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.getSession().setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.getSession().setAttribute("vendorList",vendorManager.getBarVendorList(map).entrySet());
			request.getSession().setAttribute("amountPaidTypeList",utilDBMethodManager.getAmountPaidTypeList().entrySet());
			request.getSession().setAttribute("ProductFormVO",registrationManager.editBarProduct(formVO));	
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("editBarProduct");			
	}
	
	public ActionForward updateBarProduct(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
				
		ProductManager productManager = new ProductManager();

		try{	
			formV.setProductId(request.getParameter("productId"));
			BeanUtils.copyProperties(formVO, formV);
			productManager.updateBarProduct(formVO);
			request.setAttribute("productList",productManager.getBarProductMap(formVO));	
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarProduct");			
	}

	public ActionForward deleteBarProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();

		try{
			formV.setProductId(request.getParameter("productId"));
			BeanUtils.copyProperties(formVO, formV);
			int res = productManager.deleteBarProduct(formVO);
			request.setAttribute("productList",productManager.getBarProductMap(formVO));				
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarProduct");			
	}
	
	public ActionForward getPagination(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		String[] index = request.getParameter("index").split(",");								
		ProductManager productManager = new ProductManager();
		
		try{			
			formV.setPageFrom(index[0]);
			formV.setPageTo(index[1]);
			BeanUtils.copyProperties(formVO, formV);
			request.setAttribute("productList",productManager.getPagination(formVO));			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarProduct");			
	}
	
	public ActionForward getBarProductsByRecords(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm)form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		
		try{
			formV.setLimit(request.getParameter("limit"));
			BeanUtils.copyProperties(formVO, formV);
			request.setAttribute("productList",productManager.getBarProductsByRecords(formVO));			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarProduct");			
	}
	
}
