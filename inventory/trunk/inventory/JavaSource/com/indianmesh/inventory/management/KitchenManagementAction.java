package com.indianmesh.inventory.management;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class KitchenManagementAction extends DispatchAction {

	public ActionForward getkitchenManagement(ActionMapping mapping,ActionForm form,HttpServletRequest request,
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
	
	public ActionForward getKitchenManagementStore(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("jsessionid"));
		
		try{			
			String userid=(String) request.getParameter("userid"); 
			if(userid==null){
				return mapping.findForward("kitchenManagementStore");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
	    }
		return mapping.findForward("kitchenManagementStore");			
	}
}
