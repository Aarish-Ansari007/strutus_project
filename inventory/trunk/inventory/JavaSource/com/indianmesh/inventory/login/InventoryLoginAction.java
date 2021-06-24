package com.indianmesh.inventory.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InventoryLoginAction extends DispatchAction {

	public ActionForward getHomePage(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		try{			
			String userid=(String) request.getParameter("userid"); 
			if(userid==null){
				return mapping.findForward("home");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("home");			
	}
	
	public ActionForward getLogin(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		try{			
			String userid=(String) request.getParameter("userid"); 
			if(userid==null){
				return mapping.findForward("login");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("login");			
	}
	
	public ActionForward getKitchenManagement(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		try{			
			String userid=(String) request.getParameter("userid"); 
			if(userid==null){
				return mapping.findForward("kitchenManagement");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("kitchenManagement");			
	}
	
	public ActionForward getKitchenStore(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		try{			
			String userid=(String) request.getParameter("userid"); 
			if(userid==null){
				return mapping.findForward("kitchenStore");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("kitchenStore");			
	}
	
	public ActionForward getBarManagement(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		try{			
			String userid=(String) request.getParameter("userid"); 
			if(userid==null){
				return mapping.findForward("barManagement");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("barManagement");			
	}
	
	public ActionForward getBarStore(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		try{			
			String userid=(String) request.getParameter("userid"); 
			if(userid==null){
				return mapping.findForward("barStore");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("barStore");			
	}
}
