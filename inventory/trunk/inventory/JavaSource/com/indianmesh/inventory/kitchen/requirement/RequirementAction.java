package com.indianmesh.inventory.kitchen.requirement;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.indianmesh.inventory.kitchen.stock.ProductForm;
import com.indianmesh.inventory.kitchen.stock.ProductFormVO;
import com.indianmesh.inventory.kitchen.stock.ProductManager;

	public class RequirementAction extends DispatchAction {
		
		public ActionForward getHomePage(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			return null;
		}
		
		public ActionForward updateIndexkitchenRequirement(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();

			String product = request.getParameter("product");
			String available = request.getParameter("available");
			String requirement = request.getParameter("requirement");
			String quantityType = request.getParameter("type");			
			String productIds = request.getParameter("productId");
			String requirements = request.getParameter("requirementId"); 
			String quantityTypes = request.getParameter("quantityTypeId");
						
			try{	
				if(product!="" && product!=null 
						&& requirement!="" && requirement!=null 
						&& quantityType!="" && quantityType!=null
						&& available!="" && available!=null){
					formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
					formV.setSubSellerId(RandomStringUtils.randomAlphanumeric(25));
					formV.setProductId(product);
					formV.setAvailableId(available);
					formV.setRequirementId(requirement);
					formV.setQuantityPerUnit(quantityType);
					formV.setStatus("requested");
					BeanUtils.copyProperties(formVO, formV);				
					productManager.updateKitchenProductRequirementAfterSingleEntry(formVO);					
					request.getRequestDispatcher("kitchenRequirement.do?methodName=getIndexkitchenRequirement").forward(request,response);					
				}else if(productIds!="" && productIds!=null
						&& requirements!="" && requirements!=null
						&& quantityTypes!="" && quantityTypes!=null){ 
					String [] products = productIds.split(",");
					String [] requirementss = requirements.split(",");
					String [] quantityTypess = quantityTypes.split(",");									
					for(int i=0;i<products.length;i++){
						formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
						formV.setSubSellerId(RandomStringUtils.randomAlphanumeric(25));
						formV.setProductId(products[i]);
						formV.setRequirementId(requirementss[i]);
						formV.setQuantityPerUnit(quantityTypess[i]);
						formV.setStatus("requested");
						BeanUtils.copyProperties(formVO, formV);				
						productManager.updateKitchenProductRequirementAfterMultipleEntry(formVO);						
					}
					request.getRequestDispatcher("kitchenRequirement.do?methodName=getIndexkitchenRequirement").forward(request,response);	
				}
			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenRequirementList");
		}	
		
		public ActionForward getIndexkitchenRequirement(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO>(); 
			
			try{					
				BeanUtils.copyProperties(formVO, formV);
				productList = productManager.getKitchenProductRequirementIndexMap(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenRequirement.do?methodName=getIndexkitchenRequirement").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenRequirementList");	
		}	
		
		public ActionForward getRequirementIndexMapByProductName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO>(); 
			
			try{		
				formV.setProductName(request.getParameter("name"));
				BeanUtils.copyProperties(formVO, formV);
				productList = productManager.getKitchenProductRequirementIndexMapByProductName(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);
				//request.getRequestDispatcher("kitchenRequirement.do?methodName=getIndexkitchenRequirement").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenRequirementList");	
		}
		
		public ActionForward getRequirementIndexMapByProductId(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO>(); 
			
			try{	
				formV.setProductId(request.getParameter("productId"));
				BeanUtils.copyProperties(formVO, formV);
				productList=productManager.getKitchenProductRequirementIndexMapByProductId(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenRequirement.do?methodName=getIndexkitchenRequirement").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenRequirementList");	
		}
		
		public ActionForward getRequirementIndexMapByDate(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO>(); 

			try{	
				formV.setFromDate(request.getParameter("start"));
				formV.setToDate(request.getParameter("end"));
				BeanUtils.copyProperties(formVO, formV);
				productList=productManager.getKitchenProductRequirementIndexMapByCreatedDate(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);
				//request.getRequestDispatcher("kitchenRequirement.do?methodName=getIndexkitchenRequirement").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenRequirementList");	
		}
		
		public ActionForward getRequirementIndexMapByPagination(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			String[] index = request.getParameter("index").split(",");
			List<ProductFormVO> productList = new ArrayList<ProductFormVO>();

			try{	
				formV.setPageFrom(index[0]);
				formV.setPageTo(index[1]);
				BeanUtils.copyProperties(formVO, formV);
				productList=productManager.getKitchenProductRequirementIndexMapByPagination(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenRequirement.do?methodName=getIndexkitchenRequirement").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenRequirementList");
		}
		
		public ActionForward getRequirementIndexMapByRecords(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO>();
			
			try{	
				formV.setLimit(request.getParameter("limit"));
				BeanUtils.copyProperties(formVO, formV);
				productList = productManager.getKitchenProductRequirementIndexMapByRecords(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenRequirement.do?methodName=getIndexkitchenRequirement").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenRequirementList");	
		}
		
		public ActionForward getRequirementsByStatus(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();
			String status = request.getParameter("status");
			
			try{												
					formV.setStatus(status);
					BeanUtils.copyProperties(formVO, formV);
					productList = productManager.getKitchenProductRequirementIndexMapByStatus(formVO);
					request.setAttribute("length", productList.size());
					request.setAttribute("productList",productList);
					//request.getRequestDispatcher("kitchenRequirement.do?methodName=getIndexkitchenRequirement").forward(request,response);					
				
			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenRequirementList");			
		}
		
	}