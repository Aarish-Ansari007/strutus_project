package com.indianmesh.inventory.kitchen.report;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.indianmesh.inventory.kitchen.stock.ProductForm;
import com.indianmesh.inventory.kitchen.stock.ProductFormVO;
import com.indianmesh.inventory.kitchen.stock.ProductManager;

	public class ReportAction extends DispatchAction {
		
		public ActionForward getHomePage(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
				
			return null;
		}
		
		public ActionForward getKitchenReportIndex(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();
			
			try{					
				BeanUtils.copyProperties(formVO, formV);
				productList = productManager.getReportIndexMap(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenReport.do?methodName=getKitchenReportIndex").forward(request,response);					
			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenReportList");			
		}	
		
		public ActionForward getReportByProductId(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();
			String search = request.getParameter("search");
			
			try{					
				formV.setProductId(search);
				BeanUtils.copyProperties(formVO, formV);
				productList = productManager.getReportByProductId(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenReport.do?methodName=getKitchenReportIndex").forward(request,response);					
			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenReportList");			
		}
		
		public ActionForward getReportByProductName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();
			String search = request.getParameter("search");
			
			try{					
				formV.setProductName(search);
				BeanUtils.copyProperties(formVO, formV);
				productList = productManager.getReportByProductName(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenReport.do?methodName=getKitchenReportIndex").forward(request,response);					
			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenReportList");			
		}
		
		public ActionForward getReportByStatus(ActionMapping mapping,ActionForm form,HttpServletRequest request,
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
				productList = productManager.getReportByStatus(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenReport.do?methodName=getKitchenReportIndex").forward(request,response);					
			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenReportList");			
		}
		
		public ActionForward getReportByDate(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO>();
			
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			
			try{				
				formV.setFromDate(start);
				formV.setToDate(end);
				BeanUtils.copyProperties(formVO, formV);
				productList = productManager.getReportByCreatedDate(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);
				//request.getRequestDispatcher("kitchenReport.do?methodName=getKitchenReportIndex").forward(request,response);					
			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenReportList");			
		}
		
		public ActionForward getReportByPagination(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
			
			ProductForm formV = (ProductForm)form;
			ProductFormVO formVO = new ProductFormVO();
			
			ProductManager productManager = new ProductManager();
			List<ProductFormVO> productList = new ArrayList<ProductFormVO> ();
			
			String index = request.getParameter("index");

			try{
				formV.setPageFrom(index.split(",")[0]);
				formV.setPageTo(index.split(",")[1]);
				BeanUtils.copyProperties(formVO, formV);
				productList = productManager.getReportByPagination(formVO);
				request.setAttribute("length", productList.size());
				request.setAttribute("productList",productList);	
				//request.getRequestDispatcher("kitchenReport.do?methodName=getKitchenReportIndex").forward(request,response);					
			}catch(InvocationTargetException ex){			
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
		    }
			return mapping.findForward("kitchenReportList");			
		}
		
	}