<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Kitchen</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 
	var tokenValue= '<%=request.getAttribute("TRACKERID") %>';			
</script>
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- font -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i|Playball" rel="stylesheet"> 
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font -->
</head>
<body>
<form id="inventoryKitchenManagementForm" method="post" name="inventoryKitchenManagementForm" action="">
<!-- banner -->
        <div class="main">
				<div class="login">
					<div class="banner-kitchen">
					<img src="images/kitchen-store-baner.jpg" alt="banner">					
					</div>				
					
					<div class="col-md-12 gry">
					<h3>Kitchen Store</h3>
						<div class="col-md-6 col-sm-6 login-box white-back">					
							<div class="col-md-12 col-sm-6 space-top">
								<a href="kitchenReport.do?methodName=getKitchenReportIndex">
								<img src="images/bar-report.png" alt="bar">
								<span class="icon-text">Reports</span>
								</a>
							</div>							
							<div class="col-md-12 col-sm-6 space-top">
								<a href="kitchenRequirement.do?methodName=getIndexkitchenRequirement">
								<img src="images/Bar-Requirements.png" alt="bar">
								<span class="icon-text">Kitchen Requirements</span>
								</a>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 logo">
                                 
						<a href="#"><img src="images/logo-small.jpg" alt="logo"></a>
						</div>
					</div>
				</div>
	    </div>
<!-- js -->
		  
          <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
		  
<!-- //js -->

<script src="js/scripts.js"></script>

<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>

</form>
</body>
</html>