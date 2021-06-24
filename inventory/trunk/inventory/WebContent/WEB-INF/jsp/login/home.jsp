<!--
@ Author:           Brajesh Murari - Using good style improves the maintainability of software code. Master it.
@ Date:             30/1/2017
@ Email:            brajesh.murari@gmail.com
@ Copyright Notice: Copyright (c) 2017, Indian Mesh Pvt. Ltd.
@ Description:      This JSP involves the 'Home  Role Discipline details 
                    module containing the ROLE DETAILS TO BE ADD. 
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<link href="css/calendar.css" rel="stylesheet" type="text/css" />
<link href="css/PRIMES.css" rel="stylesheet" type="text/css" />
<link href="css/calendar.css" rel="stylesheet" type="text/css" />	
<script language="javascript" type="text/javascript" src="javaScript/dhtmlgoodies_calendar.js">
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>';
//disable the backward-forward button of browser          
function disableKeys()
{
	window.history.forward(); 		
}

//disable the mouse right button  
	var message="Function Disabled!";

function clickIE4()
{
	if (event.button==2)
	{
		alert(message);
		return false;
	}
}

function clickNS4(e)
{
	if (document.layers||document.getElementById&&!document.all)
	{
		if (e.which==2||e.which==3)
		{
			alert(message);
			return false;
		}
	}
}

if (document.layers)
{
	document.captureEvents(Event.MOUSEDOWN);
	document.onmousedown=clickNS4;
}
else if (document.all&&!document.getElementById)
{
	document.onmousedown=clickIE4;
}

document.oncontextmenu=new Function("return false")	
</script>
  		
<html:html>
	<body class="rtl" onLoad = "disableKeys();">
		<table width="100%" height="100%" align="center" border="0" cellspacing="1" cellpadding="0" >
			 	<tr height="100%">
			 		<td class="userdetails" align="center" valign="middle">
				 		<logic:present name="message" scope="request">
				 			<bean:write name="message" scope="request"/>
						</logic:present>
					</td>
				</tr>
		</table>
	</body>
</html:html>
