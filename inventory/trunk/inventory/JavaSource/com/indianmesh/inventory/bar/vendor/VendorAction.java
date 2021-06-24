package com.indianmesh.inventory.bar.vendor;

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

	public class VendorAction extends DispatchAction {
		
		public ActionForward getHomePage(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
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
		 	
		public ActionForward getIndexBarVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
					
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			/*HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();

			try{	
				BeanUtils.copyProperties(formVO, formV);
				request.getSession().setAttribute("length", vendorManager.getBarVendorMap(formVO).size());
				request.getSession().setAttribute("size", vendorManager.getBarVendorIndexMap(formVO).size());
				request.getSession().setAttribute("criteriaList", utilDBMethodManager.getCriteriaList());
				request.getSession().setAttribute("BarVendortList", vendorManager.getBarVendorIndexMap(formVO));	
			}catch(Exception ex){
				ex.printStackTrace();
		    }*/
			
			return mapping.findForward("indexBarVendor");			
		}
		
		public ActionForward getIndexBarVendorsBySearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
					
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();

			try{	
				BeanUtils.copyProperties(formVO, formV);
				request.getSession().setAttribute("length", vendorManager.getBarVendorMap(formVO).size());
				request.getSession().setAttribute("size", vendorManager.getBarVendorIndexMap(formVO).size());
				request.getSession().setAttribute("criteriaList", utilDBMethodManager.getVendorRecord());
				request.getSession().setAttribute("BarVendortList", vendorManager.getBarVendorIndexMap(formVO));	
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexBarVendor");			
		}
				
		public ActionForward addBarVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			String token1=RandomStringUtils.randomAlphanumeric(20);	
			String token2=RandomStringUtils.randomAlphanumeric(5);
			
			VendorManager vendorManager = new VendorManager();
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();

			try{	
				formV.setSellerId(token1);
				formV.setSubSellerId(token1+token2);
				formV.setVendorProduct("Bar");
				BeanUtils.copyProperties(formVO, formV);
				request.getSession().setAttribute("nationalityList1",utilDBMethodManager.getNationalityList().entrySet());
				request.getSession().setAttribute("stateList1",utilDBMethodManager.getStateList().entrySet());
				request.getSession().setAttribute("nationalityList2",utilDBMethodManager.getNationalityList().entrySet());
				request.getSession().setAttribute("stateList2",utilDBMethodManager.getStateList().entrySet());
				request.getSession().setAttribute("BarFormVO",vendorManager.addBarVendor(formVO));
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("addBarVendor");			
		}
		
		public ActionForward editBarVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
							 
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			//String vendorId = request.getParameter("vendorId");
			
			UtilDBMethodManager utilDBMethodManager = new UtilDBMethodManager();
			VendorManager vendorManager = new VendorManager();
			
			try{	
				//formV.setVendorId(vendorId);
				BeanUtils.copyProperties(formVO, formV);
				request.getSession().setAttribute("nationalityList1",utilDBMethodManager.getNationalityList().entrySet());
				request.getSession().setAttribute("stateList1",utilDBMethodManager.getStateList().entrySet());
				request.getSession().setAttribute("nationalityList2",utilDBMethodManager.getNationalityList().entrySet());
				request.getSession().setAttribute("stateList2",utilDBMethodManager.getStateList().entrySet());				
				request.setAttribute("BarFormVO",vendorManager.editBarVendor(formVO));
				
				String userid=(String) request.getParameter("userid"); 
				if(userid==null){
					return mapping.findForward("editBarVendor");
				}			
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("editBarVendor");			
		}
		
		public ActionForward updateBarVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
						
			HttpSession httpSession = request.getSession();			
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			String str = request.getParameter("jsessionid");
			
			VendorManager vendorManager = new VendorManager();
			
			try{					
				BeanUtils.copyProperties(formVO, formV);
				String vendorId = vendorManager.updateBarVendor(formVO);			
				request.setAttribute("BarVendortList",vendorManager.getBarVendorIndexMap(formVO));
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexBarVendor");			
		}
		
		public ActionForward viewBarVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			//String vendorId = request.getParameter("vendorId");
			VendorManager vendorManager = new VendorManager();

			try{	
				BeanUtils.copyProperties(formVO, formV);
				request.getSession().setAttribute("BarFormVO",vendorManager.viewBarVendor(formVO));				
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("viewBarVendor");	
		}
		
		public ActionForward getBarVendorByName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			VendorManager vendorManager = new VendorManager();

			try{	
				formV.setName(request.getParameter("name"));
				BeanUtils.copyProperties(formVO, formV);
				request.setAttribute("BarVendortList",vendorManager.getBarVendorByName(formVO));				
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexBarVendor");			
		}
		
		public ActionForward getBarVendorsByRecords(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
						
			VendorManager vendorManager = new VendorManager();

			try{	
				formV.setLimit(request.getParameter("limit"));
				BeanUtils.copyProperties(formVO, formV);
				request.setAttribute("BarVendortList",vendorManager.getBarVendorsByRecords(formVO));				
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexBarVendor");			
		}
		
		public ActionForward getPagination(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			String[] index = request.getParameter("index").split(",");						
			VendorManager vendorManager = new VendorManager();

			try{		
				formV.setPageFrom(index[0]);
				formV.setPageTo(index[1]);
				BeanUtils.copyProperties(formVO, formV);
				request.setAttribute("BarVendortList",vendorManager.getPagination(formVO));				
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexBarVendor");			
		}

		public ActionForward deleteBarVendor(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			VendorForm formV = (VendorForm) form;
			VendorFormVO formVO = new VendorFormVO();
			ActionForward forward = null;
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
						
			VendorManager vendorManager = new VendorManager();

			try{
				BeanUtils.copyProperties(formVO, formV);
				int res = vendorManager.deleteBarVendor(formVO);
				request.setAttribute("BarVendortList",vendorManager.getBarVendorIndexMap(formVO));				
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			
			return mapping.findForward("indexBarVendor");			
		}
		
	}