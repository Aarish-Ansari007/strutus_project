<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Login</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 

		//addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		//function hideURLbar(){ window.scrollTo(0,1); }
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
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- font -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font -->
</head>
<body onLoad = "disableKeys();">

<form id="inventoryManagementForm" method="post" name="inventoryManagementForm" action="">

<!-- banner -->
        <div class="main">
				<div class="login">
					<div class="banner">
					<img src="images/banner-login.jpg" alt="banner">
					<a href="#" class="admin-btn">Admin</a>
					</div>
					
					<p>Welcome to MOBE Inventory, Now we will have proper control over Store's, Kitchen's, Liquer, </p>
					<div class="col-md-12 gry">
					<h3 class="">Welcome to login</h3>
						<div class="col-md-6 col-sm-6 logo">
						<a href="#"><img src="images/logo-small.jpg" alt="logo"></a>
						</div>
						<div class="col-md-6 col-sm-6 login-box">
						<div class="col-md-6 col-sm-6">
								<a href="inventoryKitchenManagement.do?methodName=getkitchenManagement">
								<img src="images/kitchen.png" alt="bar">
								<span class="icon-text">Kitchen</span>
								</a>
							</div>
							
							<div class="col-md-6 col-sm-6">
								<a href="inventoryBarManagement.do?methodName=getBarManagement">
								<img src="images/bar.png" alt="bar">
								<span class="icon-text">Bar</span>
								</a>
							</div>
							<div class="col-md-6 col-sm-6 ">
								<a href="inventoryKitchenManagement.do?methodName=getKitchenManagementStore">
								<img src="images/store.png" alt="bar">
								<span class="icon-text">Store</span>
								</a>
							</div>
							
							<div class="col-md-6 col-sm-6">
								<a href="inventoryBarManagement.do?methodName=getBarManagementStore">
								<img src="images/bar-store.png" alt="bar">
								<span class="icon-text">Store</span>
								</a>
							</div>
							
						</div>
					</div>
				</div>
	    </div>
<!-- js -->

<!-- model 1 -->


		<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
				  <div class="modal-dialog">
				  
				<div class="loginmodal-container gray-dark">
				<button type="button" class="close yellow" data-dismiss="modal" aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<div class="login-logo text-center"><img src="images/store.png" alt="bar"></div>
					<br>
				  <form class="login-form">
				  
					  <div class="form-fields">
					   <div class="col-md-4">
					   <label>Login</label>
					   </div>
					   <div class="col-md-8">
					    <input type="text" name="user" placeholder="Enter Id">
					   </div>
					  </div>
					  
					   <div class="form-fields">
					   <div class="col-md-4">
					   <label>Password</label>
					   </div>
					   <div class="col-md-8">
					    <input type="text" name="user" placeholder="Enter Password">
					   </div>
					  </div>
					  
                       <div class="form-fields">
					   <div class="col-md-4">
					   <label></label>
					   </div>
					   <div class="col-md-8">					   
						<button class="red-btn">Login</button>
					   </div>
					  </div>				  
					
				  </form>				
				</div>
			</div>
		  </div>
		  
		  <!-- model -->
		  
		  <!-- model 2 -->


		<div class="modal fade" id="login-modal-1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
				  <div class="modal-dialog">
		  
				<div class="loginmodal-container yellow-back">
				<button type="button" class="close yellow" data-dismiss="modal" aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<div class="login-logo text-center">
					<img src="images/bar.png" alt="bar">
					</div>
					<br>
				  <form class="login-form">
				  
					  <div class="form-fields">
					   <div class="col-md-4">
					   <label>Login</label>
					   </div>
					   <div class="col-md-8">
					    <input type="text" name="user" placeholder="Enter Id">
					   </div>
					  </div>
					  
					   <div class="form-fields">
					   <div class="col-md-4">
					   <label>Password</label>
					   </div>
					   <div class="col-md-8">
					    <input type="text" name="user" placeholder="Enter Password">
					   </div>
					  </div>
					  
                       <div class="form-fields">
					   <div class="col-md-4">
					   <label></label>
					   </div>
					   <div class="col-md-8">					   
						<button class="red-btn">Login</button>
					   </div>
					  </div>				  
					
				  </form>				
				</div>
			</div>
		  </div>
		  
		  <!-- model -->
		  
		  <!-- model 3 -->


		<div class="modal fade" id="login-modal-2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
				  <div class="modal-dialog">
		  
				<div class="loginmodal-container brown-dark">
				<button type="button" class="close yellow" data-dismiss="modal" aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<div class="login-logo text-center"><img src="images/kitchen.png" alt="bar"></div>
					<br>
				  <form class="login-form">
				  
					  <div class="form-fields">
					   <div class="col-md-4">
					   <label>Login</label>
					   </div>
					   <div class="col-md-8">
					    <input type="text" name="user" placeholder="Enter Id">
					   </div>
					  </div>
					  
					   <div class="form-fields">
					   <div class="col-md-4">
					   <label>Password</label>
					   </div>
					   <div class="col-md-8">
					    <input type="text" name="user" placeholder="Enter Password">
					   </div>
					  </div>
					  
                       <div class="form-fields">
					   <div class="col-md-4">
					   <label></label>
					   </div>
					   <div class="col-md-8">					   
						<button class="red-btn">Login</button>
					   </div>
					  </div>				  
					
				  </form>				
				</div>
			</div>
		  </div>
		  
		  <!-- model 4 -->
		  
		  <!-- model -->


		<div class="modal fade" id="login-modal-3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
				  <div class="modal-dialog">
		  
				<div class="loginmodal-container green">
				<button type="button" class="close yellow" data-dismiss="modal" aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<div class="login-logo text-center"><img src="images/bar-store.png" alt="bar"></div>
					<br>
				  <form class="login-form">
				  
					  <div class="form-fields">
					   <div class="col-md-4">
					   <label>Login</label>
					   </div>
					   <div class="col-md-8">
					    <input type="text" name="user" placeholder="Enter Id">
					   </div>
					  </div>
					  
					   <div class="form-fields">
					   <div class="col-md-4">
					   <label>Password</label>
					   </div>
					   <div class="col-md-8">
					    <input type="text" name="user" placeholder="Enter Password">
					   </div>
					  </div>
					  
                       <div class="form-fields">
					   <div class="col-md-4">
					   <label></label>
					   </div>
					   <div class="col-md-8">					   
						<button class="red-btn">Login</button>
					   </div>
					  </div>				  
					
				  </form>				
				</div>
			</div>
		  </div>
		  
		  <!-- model --> 
		  
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script src="js/scripts.js"></script>
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
</form>
</body>
</html>