package com.indianmesh.inventory.bar.inventory;

import java.io.IOException;
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

import com.indianmesh.inventory.kitchen.vendor.VendorManager;
import com.indianmesh.inventory.utils.utilDBMethod.UtilDBMethodManager;

public class ProductAction extends DispatchAction {
	
	public ActionForward getHomePage(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
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
	
	public ActionForward getIndexBarInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		/*HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
					
		ProductManager productManager = new ProductManager();
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		
		try{						
			BeanUtils.copyProperties(formVO, formV);
			request.getSession().setAttribute("length", productManager.getBarInventoryProductMap(formVO).size());
			request.getSession().setAttribute("size", productManager.getBarInventoryProductIndexMap(formVO).size());
			request.getSession().setAttribute("criteriaList", utilDBMethodManager.getCriteriaList());
			request.setAttribute("compositProductList",productManager.getBarInventoryProductIndexMap(formVO));	
		}catch(Exception ex){
			ex.printStackTrace();
	    }*/
		
		return mapping.findForward("indexBarInventory");			
	}
	
	public ActionForward getIndexBarInventoryProductsBySearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
					
		ProductManager productManager = new ProductManager();
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		
		try{						
			BeanUtils.copyProperties(formVO, formV);
			request.getSession().setAttribute("length", productManager.getBarInventoryProductMap(formVO).size());
			request.getSession().setAttribute("size", productManager.getBarInventoryProductIndexMap(formVO).size());
			request.getSession().setAttribute("criteriaList", utilDBMethodManager.getVendorRecord());
			request.setAttribute("compositProductList",productManager.getBarInventoryProductIndexMap(formVO));	
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarInventoryProduct");			
	}
	
	public ActionForward addBarInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		
		try{			
			String token1=RandomStringUtils.randomAlphanumeric(20);	
			String token2=RandomStringUtils.randomAlphanumeric(5);
			
			formV.setSellerId(token1);
			formV.setSubsellerId(token1+token2);
			
			if(request.getParameter("inventoryProductId")!=null)		
				formV.setInventoryProductIngredientList(Arrays.asList(request.getParameter("inventoryProductId").split(",")));
						
			if(request.getParameter("inventoryProductQuantityId")!=null)			
				formV.setInventoryProductQuantityList(Arrays.asList(request.getParameter("inventoryProductQuantityId").split(",")));			
			
			if(request.getParameter("inventoryProductQuantityTypeId")!=null)	
				formV.setInventoryProductQuantityTypeList(Arrays.asList(request.getParameter("inventoryProductQuantityTypeId").split(",")));
									
			BeanUtils.copyProperties(formVO, formV);
			
			request.getSession().setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.getSession().setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.getSession().setAttribute("ingredientQuantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			//request.getSession().setAttribute("inventoryProductList",productManager.getBarInventoryProductList(map).entrySet());		
			request.setAttribute("compositProductVO",productManager.addBarInventoryProduct(formVO));	
			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("addBarInventoryProduct");			
	}
	
	public ActionForward editBarInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();	
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
				
		try{			
			formV.setProductId(request.getParameter("productId"));			
			BeanUtils.copyProperties(formVO, formV);
			
			List<String> tmp = null;

			//Sports Facility
			tmp = productManager.viewBarIngredientProductList(formVO);
			if(tmp!=null){
			    String str11="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str11=str11+s+",";
				}
				request.getSession().setAttribute("inventoryProductIds",str11);
			}

			//camp observer
			tmp = productManager.viewBarIngredientProductQuantityList(formVO);
			if(tmp!=null){
			    String str9="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str9=str9+s+",";
				}
				request.getSession().setAttribute("inventoryProductQuantites",str9);	
			}
			
			//Sports Facility
			tmp = productManager.viewBarIngredientProductQuantityTypeList(formVO);
			if(tmp!=null){
			    String str11="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str11=str11+s+",";
				}
				request.getSession().setAttribute("inventoryProductQuantityTypeIds",str11);
			}
			
			request.getSession().setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.getSession().setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.getSession().setAttribute("ingredientQuantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			//request.getSession().setAttribute("inventoryProductList",productManager.getBarInventoryProductList(map).entrySet());				
			request.setAttribute("compositProductVO",productManager.editBarInventoryProduct(formVO));
			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("editBarInventoryProduct");			
	}
	
	/*public ActionForward saveBarInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		
		try{	
			formV.setProductId(request.getParameter("productId"));
			
			if(request.getParameter("inventoryProductId")!=null)			
				formV.setInventoryProductIngredientList(Arrays.asList(request.getParameter("inventoryProductId").split(",")));
			
			
			if(request.getParameter("inventoryProductQuantityId")!=null)		
				formV.setInventoryProductQuantityList(Arrays.asList(request.getParameter("inventoryProductQuantityId").split(",")));
			
			
			if(request.getParameter("inventoryProductQuantityTypeId")!=null)		
				formV.setInventoryProductQuantityTypeList(Arrays.asList(request.getParameter("inventoryProductQuantityTypeId").split(",")));
			

			BeanUtils.copyProperties(formVO, formV);
			
			productManager.saveBarInventoryProduct(formVO);
			//request.getSession().setAttribute("compositProductList",productManager.getCompositProductMap());

			request.setAttribute("compositProductList",productManager.getBarInventoryProductMap());	
			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarInventoryProduct");			
	}*/
	
	public ActionForward updateBarInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		
		try{	
			formV.setProductId(request.getParameter("productId"));
			
			if(request.getParameter("inventoryProductId")!=null)			
				formV.setInventoryProductIngredientList(Arrays.asList(request.getParameter("inventoryProductId").split(",")));
						
			if(request.getParameter("inventoryProductQuantityId")!=null)			
				formV.setInventoryProductQuantityList(Arrays.asList(request.getParameter("inventoryProductQuantityId").split(",")));
					
			if(request.getParameter("inventoryProductQuantityTypeId")!=null)		
				formV.setInventoryProductQuantityTypeList(Arrays.asList(request.getParameter("inventoryProductQuantityTypeId").split(",")));
			
			BeanUtils.copyProperties(formVO, formV);
			
			request.getSession().setAttribute("compositProductVO",productManager.updateBarInventoryProduct(formVO));
			request.setAttribute("compositProductList",productManager.getBarInventoryProductMap(formVO));	
			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarInventoryProduct");			
	}
	
	public ActionForward updateBarInventoryProductStatus(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
					
		String productId = request.getParameter("productId");
		String status = request.getParameter("status");
		String statusOn = request.getParameter("statusOn");
		String statusOff = request.getParameter("statusOff");
		
		ProductManager productManager = new ProductManager();
		
		try{	
			formV.setProductId(productId);
			formV.setStatus(status);
			formV.setStatusOn(statusOn);
			formV.setStatusOff(statusOff);
			
			BeanUtils.copyProperties(formVO, formV);
			
			productManager.updateBarInventoryProductStatus(formVO);
			request.getSession().setAttribute("compositProductList",productManager.getBarInventoryProductMap(formVO));	
								  
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarInventoryProduct");			
	}

	
	/*public ActionForward editBarInventoryProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm compositProductForm = (ProductForm) form;
		ProductFormVO compositProductVO = new ProductFormVO();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
		ProductManager productManager = new ProductManager();
		
		try{			
			
			BeanUtils.copyProperties(compositProductVO, compositProductForm);	
			request.getSession().setAttribute("productTypeList",utilDBMethodManager.getBarProductTypeList().entrySet());
			request.getSession().setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.getSession().setAttribute("ingredientQuantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.getSession().setAttribute("inventoryProductList",productManager.getProductNameMap().entrySet());
			request.getSession().setAttribute("compositProductVO",productManager.beforeAddNewCompositProduct(compositProductVO));
			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("addCompositProductPage");			
	}
*/	
		
	public ActionForward viewBarInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();
		UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
				
		try{				
			formV.setProductId(request.getParameter("productId"));			
			BeanUtils.copyProperties(formVO, formV);
			
			request.getSession().setAttribute("productTypeList",utilDBMethodManager.getKitchenProductTypeList().entrySet());
			request.getSession().setAttribute("quantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			request.getSession().setAttribute("ingredientQuantityTypeList",utilDBMethodManager.getQuantityTypeList().entrySet());
			//request.getSession().setAttribute("inventoryProductList",productManager.getBarInventoryProductList(map).entrySet());
			
			List<String> tmp = null;

			//Sports Facility
			tmp = productManager.viewBarIngredientProductList(formVO);
			if(tmp!=null){
			    String str11="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str11=str11+s+",";
				}
				request.getSession().setAttribute("inventoryProductIds",str11);
			}

			//camp observer
			tmp = productManager.viewBarIngredientProductQuantityList(formVO);
			if(tmp!=null){
			    String str9="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str9=str9+s+",";
				}
				request.getSession().setAttribute("inventoryProductQuantites",str9);	
			}
			
			//Sports Facility
			tmp = productManager.viewBarIngredientProductQuantityTypeList(formVO);
			if(tmp!=null){
			    String str11="";
				for(String s:tmp){	
					if(s!=null && s!="")
					str11=str11+s+",";
				}
				request.getSession().setAttribute("inventoryProductQuantityTypeIds",str11);
			}
			
			request.setAttribute("compositProductVO",productManager.viewBarInventoryProduct(formVO));			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("viewBarInventoryProduct");			
	}
		
	public ActionForward getBarInventoryProductsByRecords(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();

		try{	
			formV.setLimit(request.getParameter("limit"));
			BeanUtils.copyProperties(formVO, formV);
			request.getSession().setAttribute("length", productManager.getBarInventoryProductMap(formVO).size());
			request.getSession().setAttribute("size", productManager.getBarInventoryProductsByRecords(formVO).size());
			request.setAttribute("compositProductList",productManager.getBarInventoryProductsByRecords(formVO));
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarInventoryProduct");		
	}
	
	public ActionForward getPagination(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		ProductManager productManager = new ProductManager();

		try{
			String[] index = request.getParameter("index").split(",");	
			formV.setPageFrom(index[0]);
			formV.setPageTo(index[1]);
			
			BeanUtils.copyProperties(formVO, formV);
			request.setAttribute("compositProductList",productManager.getPagination(formVO));
			
			request.getSession().setAttribute("length", productManager.getBarInventoryProductMap(formVO).size());
			request.getSession().setAttribute("size", productManager.getPagination(formVO).size());
			request.setAttribute("compositProductList",productManager.getPagination(formVO));
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarInventoryProduct");		
	}
	
	public ActionForward deleteBarInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
		ProductManager productManager = new ProductManager();

		try{	
			BeanUtils.copyProperties(formVO, formV);
			int res = productManager.deleteBarInventoryProduct(formVO);
			request.setAttribute("compositProductList",productManager.getBarInventoryProductMap(formVO));						  
		
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarInventoryProduct");				
	}
	
	public ActionForward clearBarInventoryProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ProductForm formV = (ProductForm) form;
		ProductFormVO formVO = new ProductFormVO();
		ActionForward forward = null;
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
					
		ProductManager productManager = new ProductManager();
		
		try{	
			//formV.setProductId(request.getParameter("productId")); 
			BeanUtils.copyProperties(formVO, formV);
			int res = productManager.deleteBarInventoryProduct(formVO);
			
			request.setAttribute("compositProductList",productManager.getBarInventoryProductMap(formVO));									  
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		
		return mapping.findForward("indexBarInventoryProduct");	
	}
	
	public ActionForward getProductList(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		Map<String,String> productListMap = null;
		Map<String, String> map = new LinkedHashMap<String, String>(); 
		ProductManager productManager = new ProductManager();

		try {			
			productListMap = productManager.getBarInventoryProductList(map);
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
		sb.append("<productsList>");
		sb.append("<valid>true</valid>");
		for (Iterator<String> it=productListMap.keySet().iterator(); it.hasNext(); ) 
		{  		
			String key = it.next();  
			String value = productListMap.get(key);  
			sb.append("<product>");
			sb.append("<productId>"+key+"</productId>");
			sb.append("<productName>"+value+"</productName>");
			sb.append("</product>");
			}
		sb.append("</productsList>");
		try {
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		else{
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<productsList>");
			sb.append("<valid>false</valid>");
			sb.append("</productsList>");
			try {
				response.getWriter().write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}
	

}
