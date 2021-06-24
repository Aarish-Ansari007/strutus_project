package com.indianmesh.inventory.kitchen.vendor;

import java.io.IOException;
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

import com.indianmesh.inventory.kitchen.stock.ProductFormVO;
import com.indianmesh.inventory.utils.utilDBMethod.UtilDBMethodManager;

	public class VendorAction extends DispatchAction {
		
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
		 	
		public ActionForward getIndexkitchenVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
					
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();
			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();
			
			try{	
				BeanUtils.copyProperties(formVO, formV);
				vendortList = vendorManager.getKitchenVendorIndexMap(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("vendortList", vendortList);	
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}
		
		public ActionForward getIndexkitchenVendorsBySearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
					
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			Map<String, String> map = new LinkedHashMap<String, String>();
			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();

			try{	
				BeanUtils.copyProperties(formVO, formV);
				vendortList = vendorManager.getKitchenVendorIndexMap(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("typeList", vendorManager.getVendorTypeList(map));
				request.setAttribute("recordList", utilDBMethodManager.getVendorRecord());
				request.setAttribute("vendortList", vendortList);	
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}
				
		public ActionForward addKitchenVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
						
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			
			Map<String, String> map = new LinkedHashMap<String, String>();  

			try{	
				formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
				formV.setSubSellerId(RandomStringUtils.randomAlphanumeric(25));
				BeanUtils.copyProperties(formVO, formV);
				request.setAttribute("nationalityList1",utilDBMethodManager.getNationalityList().entrySet());
				request.setAttribute("stateList1",utilDBMethodManager.getStateList().entrySet());
				request.setAttribute("nationalityList2",utilDBMethodManager.getNationalityList().entrySet());
				request.setAttribute("stateList2",utilDBMethodManager.getStateList().entrySet());
				request.setAttribute("typeList",vendorManager.getVendorTypeList(map));
				request.setAttribute("kitchenFormVO",vendorManager.addKitchenVendor(formVO));
				
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("addKitchenVendor");			
		}
		
		public ActionForward editKitchenVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
							 
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			String vendorId = request.getParameter("vendorId");
			
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			VendorManager vendorManager = new VendorManager();
			
			Map<String, String> map = new LinkedHashMap<String, String>();  
			
			try{	
				formV.setVendorId(vendorId);
				BeanUtils.copyProperties(formVO, formV);
				request.setAttribute("nationalityList1",utilDBMethodManager.getNationalityList().entrySet());
				request.setAttribute("stateList1",utilDBMethodManager.getStateList().entrySet());
				request.setAttribute("nationalityList2",utilDBMethodManager.getNationalityList().entrySet());
				request.setAttribute("stateList2",utilDBMethodManager.getStateList().entrySet());	
				request.setAttribute("typeList",vendorManager.getVendorTypeList(map));
				request.setAttribute("kitchenFormVO",vendorManager.editkitchenVendor(formVO));
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					

				String userid=(String) request.getParameter("userid"); 
				if(userid==null){
					return mapping.findForward("editKitchenVendor");
				}			
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("editKitchenVendor");			
		}
		
		public ActionForward updateKitchenVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
						
			HttpSession httpSession = request.getSession();			
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			String str = request.getParameter("jsessionid");
						
			formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
			formV.setSubSellerId(RandomStringUtils.randomAlphanumeric(25));
			BeanUtils.copyProperties(formVO, formV);		
			
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			Map<String, String> map = new LinkedHashMap<String, String>();  

			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();

			try{					
				BeanUtils.copyProperties(formVO, formV);
				String vendorId = vendorManager.updateKitchenVendor(formVO);
				vendortList = vendorManager.getKitchenVendorIndexMap(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("typeList", vendorManager.getVendorTypeList(map));
				request.setAttribute("recordList", utilDBMethodManager.getVendorRecord());
				request.setAttribute("vendortList",vendortList);
				request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					

			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}
		
		public ActionForward viewKitchenVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();

			try{	
				BeanUtils.copyProperties(formVO, formV);
				request.setAttribute("kitchenFormVO",vendorManager.viewKitchenVendor(formVO));				
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("viewKitchenVendor");	
		}
		
		public ActionForward getSupplyKitchenProductsFormByVendorId(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();			
			ProductFormVO formVSO = new ProductFormVO();			
			
			VendorManager vendorManager = new VendorManager();			
			List<ProductFormVO> productList = new ArrayList<ProductFormVO>();

			try{	
				formV.setVendorId(request.getParameter("vendorId")); 
				BeanUtils.copyProperties(formVO, formV);
				request.setAttribute("VendorFormVO",vendorManager.getSupplyKitchenProductsFormByVendorId(formVO));	
				
				formVSO.setVendorId(request.getParameter("vendorId"));
				productList = vendorManager.getKitchenProductSupplyIndexMapByVendorId(formVSO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("supplyKitchenVendor");	
		}
		
		public ActionForward getkitchenVendorByName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			Map<String, String> map = new LinkedHashMap<String, String>();  
			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();

			try{	
				formV.setName(request.getParameter("name"));
				BeanUtils.copyProperties(formVO, formV);
				vendortList = vendorManager.getKitchenVendorByName(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("typeList", vendorManager.getVendorTypeList(map));
				request.setAttribute("recordList", utilDBMethodManager.getVendorRecord());
				request.setAttribute("vendortList",vendortList);	
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					

			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}
		
		public ActionForward getkitchenVendorByPhone(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			Map<String, String> map = new LinkedHashMap<String, String>();  
			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();

			try{	
				formV.setPhoneNo(request.getParameter("phone"));
				BeanUtils.copyProperties(formVO, formV);
				vendortList = vendorManager.getKitchenVendorByPhone(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("typeList", vendorManager.getVendorTypeList(map));
				request.setAttribute("recordList", utilDBMethodManager.getVendorRecord());
				request.setAttribute("vendortList",vendortList);
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					

			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}
		
		public ActionForward getkitchenVendorsByType(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			Map<String, String> map = new LinkedHashMap<String, String>();  

			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();

			try{	
				formV.setType(request.getParameter("type"));
				BeanUtils.copyProperties(formVO, formV);
				vendortList = vendorManager.getkitchenVendorsByType(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("typeList", vendorManager.getVendorTypeList(map));
				request.setAttribute("recordList", utilDBMethodManager.getVendorRecord());
				request.setAttribute("vendortList",vendortList);	
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					

			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}
		
		public ActionForward getkitchenVendorsByDates(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			Map<String, String> map = new LinkedHashMap<String, String>();  
			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();

			try{	
				formV.setFromDate(request.getParameter("start"));
				formV.setToDate(request.getParameter("end"));

				BeanUtils.copyProperties(formVO, formV);
				vendortList = vendorManager.getkitchenVendorsByCreationDate(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("typeList", vendorManager.getVendorTypeList(map));
				request.setAttribute("recordList", utilDBMethodManager.getVendorRecord());
				request.setAttribute("vendortList",vendortList);	
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					

			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}
		
		public ActionForward getkitchenVendorsByRecords(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
						
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			Map<String, String> map = new LinkedHashMap<String, String>();  
			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();

			try{	
				formV.setLimit(request.getParameter("limit"));
				BeanUtils.copyProperties(formVO, formV);
				vendortList = vendorManager.getkitchenVendorsByRecords(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("typeList", vendorManager.getVendorTypeList(map));
				request.setAttribute("recordList", utilDBMethodManager.getVendorRecord());
				request.setAttribute("vendortList",vendortList);	
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					

			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}
		
		public ActionForward getPagination(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			String index = request.getParameter("index");						
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			Map<String, String> map = new LinkedHashMap<String, String>();  
			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();

			try{		
				formV.setPageFrom(index.split(",")[0]);
				formV.setPageTo(index.split(",")[1]);
				BeanUtils.copyProperties(formVO, formV);
				vendortList = vendorManager.getPagination(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("typeList", vendorManager.getVendorTypeList(map));
				request.setAttribute("recordList", utilDBMethodManager.getVendorRecord());
				request.setAttribute("vendortList",vendorManager.getPagination(formVO));
				//request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					

			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}

		public ActionForward deleteKitchenVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
						
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			Map<String, String> map = new LinkedHashMap<String, String>();  

			List<VendorFormVO> vendortList = new ArrayList<VendorFormVO> ();

			try{
				BeanUtils.copyProperties(formVO, formV);
				int res = vendorManager.deleteKitchenVendor(formVO);
				vendortList = vendorManager.getKitchenVendorIndexMap(formVO);
				request.setAttribute("length", vendortList.size());
				request.setAttribute("typeList", vendorManager.getVendorTypeList(map));
				request.setAttribute("recordList", utilDBMethodManager.getVendorRecord());
				request.setAttribute("vendortList",vendortList);
				request.getRequestDispatcher("kitchenVendor.do?methodName=getIndexkitchenVendor").forward(request,response);					

			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexKitchenVendor");			
		}
		
		public ActionForward getVendorTypeList(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
						
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			Map<String, String> map = new LinkedHashMap<String, String>();  
			VendorManager vendorManager = new VendorManager();

			try{			
				String userid=(String) request.getParameter("userid"); 
				if(userid==null){
				}	
				request.setAttribute("typeList",vendorManager.getVendorTypeList(map));				
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("loginPage");			
		}
		
		public ActionForward getVendorType(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			Map<String,String> typeListMap = null;
			VendorManager vendorManager = new VendorManager();
			Map<String,String> map = new LinkedHashMap<String,String>();  
			
			try {				
					typeListMap = vendorManager.getVendorTypeList(map);
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
			if(typeListMap!=null  && typeListMap.size()!=0){
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<vendorTypeList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it=typeListMap.keySet().iterator(); it.hasNext(); ) 
			{  		
				String key = it.next();  
				String value = typeListMap.get(key);  
				sb.append("<type>");
				sb.append("<typeId>"+key+"</typeId>");
				sb.append("<typeName>"+value+"</typeName>");
				sb.append("</type>");
				}
			sb.append("</vendorTypeList>");
			try {
				response.getWriter().write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			else{
				sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
				sb.append("<vendorTypeList>");
				sb.append("<valid>false</valid>");
				sb.append("</vendorTypeList>");
				try {
					response.getWriter().write(sb.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
			return null;
		}
		
		public ActionForward getVendorRecordList(ActionMapping mapping,ActionForm form,HttpServletRequest request,
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