package com.indianmesh.inventory.utils.filter;

/*
 * Copyright (c) 2017, Indian Mesh Pvt. Ltd.
 * 
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met,
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the inventory project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts.action.ActionMapping;

public class RequestProcessor extends org.apache.struts.tiles.TilesRequestProcessor{

	private boolean isTokenValid(HttpServletRequest request,String aevFlag){
		
		if(request.getAttribute("VALIDATED")!=null){
			return true;
		}	
		
		HttpSession session=null;
		request.getSession(true);
		session=request.getSession();
		String reqToken=request.getParameter("jsessionid");	
		
		if(reqToken!=null && (reqToken.trim()).equals(session.getAttribute("TRACKERID"))){
			setToken(session);
			request.setAttribute("VALIDATED","true");
			return true;					
		}	
		
        return false;
	}

	private void setToken(HttpSession session){
		
		String token=RandomStringUtils.randomAlphanumeric(25);
		session.setAttribute("TRACKERID",token);
	}

	protected boolean processRoles ( HttpServletRequest request, HttpServletResponse response, ActionMapping mapping ) throws IOException, ServletException{

		HttpSession ses=request.getSession();		 				
		String trackId = request.getParameter("jsessionid");

		trackId = request.getParameter("jsessionid");
			if(trackId !=null  && trackId.trim().length() != 0  )   {
				if(!isTokenValid(request, "")){
				return redirect ( request, response, "" );
				}
			}
		return true;	
	}
	
	private boolean redirect ( HttpServletRequest request, HttpServletResponse response, String page ) throws IOException{
		
		response.sendRedirect ( request.getContextPath() + page );		
		return false;
	}
}
