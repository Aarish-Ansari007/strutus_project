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

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DisableUrlSessionFilter implements Filter

{
	/** * Filters requests to disable URL-based session identifiers. */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException { 
			
		if (!(request instanceof HttpServletRequest)) {			
			System.out.print("| request instanceof HttpServletRequest | Session | ");
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if (httpRequest.isRequestedSessionIdFromURL()) {
			HttpSession session = httpRequest.getSession();
			
			if (session != null){				
				System.out.print("| DisableUrlSessionFilter | session != null | "+session.toString());
			}else{
				session.invalidate();
				System.out.print("| DisableUrlSessionFilter | session == null | "+session.toString());
			}
		}
		
		// wrap response to remove URL encoding
		HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(
				httpResponse) {
			@Override
			public String encodeRedirectUrl(String url) {
				return url;
			}

			@Override
			public String encodeRedirectURL(String url) {
				return url;
			}

			@Override
			public String encodeUrl(String url) {
				return url;
			}

			@Override
			public String encodeURL(String url) {
				return url;
			}
		};
		
		// process next request in chain
		chain.doFilter(request, wrappedResponse);
	}

	/** * Unused. */
	public void init(FilterConfig config) throws ServletException {
	}

	/** * Unused. */
	public void destroy() {
	}
}