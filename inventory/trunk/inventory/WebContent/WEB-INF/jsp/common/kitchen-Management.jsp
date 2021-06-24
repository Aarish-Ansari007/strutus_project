<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Kitchen Store</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="application/x-javascript"> 
		
		var tokenValue= '<%=request.getAttribute("TRACKERID") %>' ;
		
		function getkitchenProductList(){
			document.forms[0].action="kitchenProduct.do?methodName=getIndexkitchenProduct";
			document.forms[0].submit();
		}
		
	function report()
	{
		alert('under construction');
	}
		</script>
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- font -->
<!-- <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet"> -->
<!-- <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i|Playball" rel="stylesheet">  -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font -->
</head>
<body >
<form id="inventoryManagementForm" method="post" name="inventoryManagementForm" action="">
<!-- banner -->
        <div class="main">
				<div class="login">
					<div class="banner-kitchen">
					<img src="images/kitchen-banner.jpg" alt="banner">					
					</div>				
					
					<div class="col-md-12 gry">
					        <h3>Kitchen Management</h3>
						<div class="col-md-6 col-sm-6 login-box white-back">
							<div class="col-md-6 col-sm-6 ">
								<a href="kitchenVendor.do?methodName=getIndexkitchenVendor" >
								<img src="images/vendor.png" alt="bar">
								<span class="icon-text">Vendor</span>
								</a>
							</div>
							<div class="col-md-6 col-sm-6" id="report">
								<!-- <a href="kitchenReport.do?methodName=getKitchenReportIndex"> -->
								<a onclick="report()">
								<img src="images/bar-report.png" alt="bar">
								<span class="icon-text">Reports</span>
								</a>
							</div>
							<div class="col-md-6 col-sm-6">
								<a href="kitchenProduct.do?methodName=getIndexKitchenProduct">
								<img src="images/product-list.png" alt="bar">
								<span class="icon-text">Product List</span>
								</a>
							</div>
							<div class="col-md-6 col-sm-6">
								<!-- <a href="kitchenRequirement.do?methodName=getIndexkitchenRequirement"> -->
								<a onclick="report()">
								<img src="images/Bar-Requirements.png" alt="bar">
								<span class="icon-text">Kitchen Requirements</span>
								</a>
							</div>
							<div class="col-md-6 col-sm-6">
								<a href="kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct">
								<img src="images/bar-add-vndr.png" alt="bar">
								<span class="icon-text">inventory</span>
								</a>
							</div>
							<div class="col-md-6 col-sm-6">
								<!-- <a href="kitchenConsumption.do?methodName=getIndexkitchenConsumption"> -->
								<a onclick="report()">
								<img src="images/kitchen-store-6.png" alt="bar">
								<span class="icon-text">Consumption</span>
								</a>
							</div>
							
						</div>
						<div class="col-md-6 col-sm-6 logo">
                         
						<a href="#"><img src="images/logo.jpg" alt="logo"></a>
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