package com.indianmesh.inventory.utils.filter;

import java.io.IOException;

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

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestFilter implements Filter{

  private FilterConfig filterConfig;

  static class FilteredRequest extends HttpServletRequestWrapper {
	
	public FilteredRequest(ServletRequest request) {
		super((HttpServletRequest)request);
	  }

	/**
	 Method used to remove the unsafe characters(<,>,(,),%) from parameter values
	*/
	public String getParameter(String paramName) {
			String value = removeUntrustChars(super.getParameter(paramName));
			return value;
		  }
	
	/**
	 Method used to remove the unsafe characters(<,>,(,),%) from parameter values
	*/
	public String[] getParameterValues(String paramName) {
		  
	  	String values[] = super.getParameterValues(paramName);
		if(values != null && values.length >0){
		
		   for (int index = 0; index < values.length; index++) {
				//System.out.println("===CHAR==="+values[index]);
  		      if(values[index] != null && values[index].length()>0){
		         values[index] = removeUntrustChars(values[index]);
		      }
	  	    }
	  	}
	  	return values;
	  }
	  
	/**
	 Method used to remove the unsafe characters(<,>,(,),%) from cookie values
	*/
	  public Cookie[] getCookies(){
	  
	  	Cookie[] cookies = super.getCookies();
	    	for (int i = 0; i < cookies.length; i++) {
	    	    cookies[i].setValue(removeUntrustChars(cookies[i].getValue()));
	    	}
	    	return cookies;
	  }
	  

	/**
	 Method used to remove the unsafe characters(<,>,(,),%) from given String. 
	*/
	  private String removeUntrustChars(String value){
	  
	  	if(value != null && value.length()>0){
	  		 value = value.replaceAll(">"," ");
			   value = value.replaceAll("<"," ");
		  	   value=EscapeChars.forScriptTagsOnly(value);
			   value = value.replaceAll("\\("," ");
			   value = value.replaceAll("\\)"," ");
			   value = value.replaceAll("%"," ");
			   value = value.replaceAll("&"," ");
			   value = value.replaceAll("\""," ");
			   value = value.replaceAll("\'"," ");
		}
		return value;
	  }
  }

  public void init(FilterConfig filterConfig){
	  this.filterConfig=filterConfig;
  }

  public void doFilter (ServletRequest request,ServletResponse response,FilterChain chain) {

    try{
       // Wrapping the request object using custom wrapper
        FilteredRequest filteredRequest= new FilteredRequest(request);
        chain.doFilter(filteredRequest, response);
    }
    catch (IOException io){      
    }
    catch (ServletException se)
    {
     
    }
  }

  public FilterConfig getFilterConfig()
  {
    return this.filterConfig;
  }

  public void setFilterConfig (FilterConfig filterConfig)
  {
    this.filterConfig = filterConfig;
  }
  
  public void destroy(){
  }
}