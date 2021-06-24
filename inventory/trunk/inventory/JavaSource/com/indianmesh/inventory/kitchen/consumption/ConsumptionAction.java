package com.indianmesh.inventory.kitchen.consumption;

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

	public class ConsumptionAction extends DispatchAction {
		
		public ActionForward getHomePage(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			return null;
		}
		
		public ActionForward getIndexkitchenConsumption(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO>();

			try{					
				BeanUtils.copyProperties(formVO, formV);
				productList = productManager.getKitchenProductConsumptionIndexMap(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);
				//request.getRequestDispatcher("kitchenConsumption.do?methodName=getIndexkitchenConsumption").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenConsumptionList");			
		}	
		
		public ActionForward updateIndexkitchenConsumption(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();

			String product = request.getParameter("product");
			String lastCount = request.getParameter("lastCount");
			String available = request.getParameter("available");
			String consumption = request.getParameter("consumption");
			String quantityType = request.getParameter("type");
			
			String productIds = request.getParameter("productId");
			String consumptions = request.getParameter("consumptionId"); 
			String quantityTypes = request.getParameter("quantityTypeId");
						
			try{	
				if(product!="" && product!=null
						&& consumption!="" && consumption!=null
						&& quantityType!="" && quantityType!=null
						&& lastCount!="" && lastCount!=null
						&& available!="" && available!=null ){
					formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
					formV.setSubSellerId(RandomStringUtils.randomAlphanumeric(25));
					formV.setProductId(product);
					formV.setLastCount(lastCount);
					formV.setAvailableId(available);
					formV.setConsumptionId(consumption);
					formV.setQuantityPerUnit(quantityType);
					formV.setStatus("pending");
					BeanUtils.copyProperties(formVO, formV);				
					productManager.updateKitchenProductConsumptionAfterSingleEntry(formVO);					
					request.getRequestDispatcher("kitchenConsumption.do?methodName=getIndexkitchenConsumption").forward(request,response);					
				}else if(productIds!="" && productIds!=null
						&& consumptions!="" && consumptions!=null
						&& quantityTypes!="" && quantityTypes!=null){ 
					String [] products = productIds.split(",");
					String [] consumptionss = consumptions.split(",");
					String [] quantityTypess = quantityTypes.split(",");
									
					for(int i=0;i<products.length;i++){
						formV.setSellerId(RandomStringUtils.randomAlphanumeric(25));
						formV.setSubSellerId(RandomStringUtils.randomAlphanumeric(25));
						formV.setProductId(products[i]);
						formV.setConsumptionId(consumptionss[i]);
						formV.setQuantityPerUnit(quantityTypess[i]);
						formV.setStatus("pending");
						BeanUtils.copyProperties(formVO, formV);				
						productManager.updateKitchenProductConsumptionAfterMultipleEntry(formVO);					
					}
					request.getRequestDispatcher("kitchenConsumption.do?methodName=getIndexkitchenConsumption").forward(request,response);	
				}
				
			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenConsumptionList");
		}	
		
		public ActionForward getConsumptionIndexMapByProductName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
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
				productList = productManager.getKitchenProductConsumptionIndexMapByProductName(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);
				//request.getRequestDispatcher("kitchenConsumption.do?methodName=getIndexkitchenConsumption").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenConsumptionList");	
		}
		
		public ActionForward getConsumptionIndexMapByProductId(ActionMapping mapping,ActionForm form,HttpServletRequest request,
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
				productList = productManager.getKitchenProductConsumptionIndexMapByProductId(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);
				//request.getRequestDispatcher("kitchenConsumption.do?methodName=getIndexkitchenConsumption").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenConsumptionList");	
		}
		
		public ActionForward getConsumptionIndexMapByDate(ActionMapping mapping,ActionForm form,HttpServletRequest request,
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
				productList = productManager.getKitchenProductConsumptionIndexMapByCreatedDate(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);
				//request.getRequestDispatcher("kitchenConsumption.do?methodName=getIndexkitchenConsumption").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenConsumptionList");	
		}
		
		public ActionForward getConsumptionIndexMapByPagination(ActionMapping mapping,ActionForm form,HttpServletRequest request,
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
				productList=productManager.getKitchenProductConsumptionIndexMapByPagination(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenConsumption.do?methodName=getIndexkitchenConsumption").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenConsumptionList");	
		}
		
		public ActionForward getConsumptionIndexMapByRecords(ActionMapping mapping,ActionForm form,HttpServletRequest request,
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
				productList = productManager.getKitchenProductConsumptionIndexMapByRecords(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenConsumption.do?methodName=getIndexkitchenConsumption").forward(request,response);					

			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenConsumptionList");	
		}
		
	}