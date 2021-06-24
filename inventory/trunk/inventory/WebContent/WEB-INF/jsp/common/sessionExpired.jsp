<!--
@ Author:           Brajesh Murari - Using good style improves the maintainability of software code. Master it.
@ Date:             30/1/2017
@ Email:            brajesh.murari@gmail.com
@ Copyright Notice: Copyright (c) 2017, Indian Mesh Pvt. Ltd.
@ Description:      This JSP involves the 'Change Password Password For First Times Login with new password. 
                    This Page Is Open when . 
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zxx">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK href="<%=request.getContextPath() %>/css/Styles.css" rel=stylesheet>
<link href="css/PRIMES.css" rel="stylesheet" type="text/css" />
<link href="css/calendar.css" rel="stylesheet" type="text/css" />
<title>Session Expired</title>
<script>
function login(){
document.sessionExpiry.action="sessionExpired";
document.sessionExpiry.submit();   
}

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
</head>
<body onLoad = "disableKeys();">

<form name="sessionExpiry" method="post" styleId="form1" enctype="multipart/form-data">

<table  align="center" width="50%">
 <tr><td align="center"><img src="images/Banner.JPG" alt="banner" width="100%" alt="PRIMES" width="404" height="104" /></td>
 </tr>
	<tr height="110"><td>&nbsp;</td></tr>


	<tr><td class="actionstxt" align="center" width="50%" >Your Session has Expired Please relogin.</td></tr>
    <tr><td>&nbsp;&nbsp;</td></tr>
    <tr><td>&nbsp;&nbsp;</td></tr>
    
	<tr class="tableborder">
		<td width="50%" class="oddrow" align="center">
			<input type="button" value="Login" onclick="login()" class="primesButton"/>
		</td>
	</tr>

	<tr height="120"><td>&nbsp;</td></tr>
</table>
<div class="footer">
    <table width="120%"  border="0" align="center" cellpadding="0" >
      <tr>
        <td align="center"><ul>
            <li><a href="#">Contact Us</a></li>
            <li><a href="#">User Role</a></li>
            <li><a href="#">Feed back</a></li>         
          </ul></td>
      </tr>
    </table>
  </div>
  <table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
     
    <td  class="footer-message" >
<!-- 		<img border="0" src="images/Nic-Logo.jpg" width="119" height="40" align="right"></td>
 -->    </tr>
  </table>
	<p>&nbsp;</div>
</form>
</body>
</html>