package com.indianmesh.inventory.bar.requirement;

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

	public class RequirementAction extends DispatchAction {
		
		public ActionForward getHomePage(ActionMapping mapping,ActionForm form,HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			
			return null;

		}
		
	}