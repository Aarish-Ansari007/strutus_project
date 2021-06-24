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
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;


public class AuthFilter implements Filter {

	String host;
	String loginPattern;
	String loginMapping;
	List commonURLList = null;

	public void init(FilterConfig config) throws ServletException {
		this.host = config.getInitParameter("host");
		this.loginPattern = config.getInitParameter("login");
		this.loginMapping = config.getInitParameter("loginMapping");
		this.commonURLList = (List) config.getServletContext().getAttribute("commonURL");
	}
	
	private void setToken(HttpSession session){
		
		String token=RandomStringUtils.randomAlphanumeric(25);
		session.setAttribute("jsessionid",token);
		session.setAttribute("TRACKERID",token);
	}

	public void doFilter(ServletRequest request, ServletResponse res,
			FilterChain next) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) res;
		
		req.setAttribute("VALIDATED","true");

		HttpSession session=null;
		req.getSession(true);
		session=req.getSession();
		setToken(session);
		
		//this.setNoCache(req, resp);
		next.doFilter(req, resp);
		return;
				
		
	}
		
	private void setNoCache (HttpServletRequest request,
            HttpServletResponse response) {
		if(request.getProtocol().compareTo ("HTTP/1.0") == 0) {
			response.setHeader ("Pragma", "no-cache");
		} else if (request.getProtocol().compareTo ("HTTP/1.1") == 0) {
			response.setHeader ("Cache-Control", "no-cache");
		}
		response.setDateHeader ("Expires", 0);
	}

	private void setErrorPage (HttpServletRequest request, ServletResponse res) throws IOException {
		res.setContentType("text/html");
	    PrintWriter out = res.getWriter();

	    out.println("<HTML>");
	    out.println("<HEAD><TITLE>Error</TITLE></HEAD>");
	    out.println("<BODY>");
	    out.println("<BIG>An Error has Occured.Click <a href='"+request.getContextPath()+"'>here</a> to Login Again!</BIG>");
	    out.println("</BODY></HTML>");
	}

	public void destroy() {
	}
}
