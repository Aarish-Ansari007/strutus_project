<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Requirements</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 

		addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		
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
<link href="css/component.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="css/table-style.css" />
<link rel="stylesheet" type="text/css" href="css/dropdown.css" />
<link rel="stylesheet" type="text/css" href="css/basictable.css" />
<link href="css/export.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/flipclock.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/circles.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style_grid.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/datepicker3.css" rel="stylesheet" type="text/css" media="all" />

<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome-icons -->
<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800" rel="stylesheet">
</head>
<body onLoad = "disableKeys();">
<form id="inventoryBarRequirementForm" method="post" name="inventoryBarRequirementForm" action="">
<!-- banner -->
<div class="wthree_agile_admin_info">
		  <!-- /w3_agileits_top_nav-->
		  <!-- /nav-->
		  <div class="w3_agileits_top_nav bar-store">
			<ul id="gn-menu" class="gn-menu-main">			  	
				<li class="second logo"><h1><a href="index.html"><i class="fa fa-graduation-cap" aria-hidden="true"></i>Ministry Of Bar Exchange</a></h1></li>
					<li class="second admin-pic">
				       <ul class="top_dp_agile">
									<li class="dropdown profile_details_drop">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
											<div class="profile_img">	
												<span class="prfil-img"><img src="images/admin.jpg" alt=""> </span> 
											</div>	
										</a>
										<ul class="dropdown-menu drp-mnu">
											<li> <a href="#"><i class="fa fa-cog"></i> Settings</a> </li> 
											<li> <a href="#"><i class="fa fa-user"></i> Profile</a> </li> 
											<li> <a href="#"><i class="fa fa-sign-out"></i> Logout</a> </li>
										</ul>
									</li>
									
						</ul>
				</li>
			</ul>
			<!-- //nav -->
			
		</div>
		<div class="clearfix"></div>
		<!-- //w3_agileits_top_nav-->
		
		<!-- /content-->
		
		<div class="inner_content">		
			<div class="container custm">
			<div class="w3l_agileits_breadcrumbs">
							<div class="w3l_agileits_breadcrumbs_inner">
								<ul>
									<li><a href="../inventory">Home</a><span></span></li>
									
									<li>Requirement</li>
									<li class="pull-right section-head"><a href="inventoryBarManagement.do?methodName=getBarManagementStore"> Bar Store</a></li>
									<li class="pull-right section-head"><a href="inventoryBarManagement.do?methodName=getBarManagementStore"> Back</a></li>
								</ul>
							</div>
						</div>	
                     <!-- /filter-->
					 
             		<div class="col-md-12 listings filter">
					<div class="w3l-table-info agile_info_shadow mrgn-0">
					<form class="filter-drop-down">
								<div class="col-md-3 col-sm-12">
									 <div class="input-search">
									<input type="text" class="search-query" placeholder="Search by Product name and Id">						
									</div>
								</div>	
                                         <div class="col-md-2 col-sm-12">            
											<a class="btn btn-default btn-select">
												<input type="hidden" class="btn-select-input" id="" name="" value="" />
												<span class="btn-select-value">Select an Item</span>
												<span class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
												<ul>
													<li class="selected">Select Date Type</li>
													<li>Date Of Addition</li>								
													<li>Date of Expiry</li>								
												</ul>
											</a>
                                         </div>				    
						 
									<div class="col-md-3 col-sm-12">
										<div class="input-daterange input-group">
											<input type="text" id="start" name="start" class="input-sm form-control" placeholder="Date From">
														<span class="input-group-addon">
															<i class="fa fa-exchange"></i>
														</span>
											<input type="text" id="end" name="end" class="input-sm form-control" placeholder="Date to">
										</div>
									</div>	
                                          <div class="col-md-2 col-sm-12">            
											<a class="btn btn-default btn-select">
												<input type="hidden" class="btn-select-input" id="" name="" value="" />
												<span class="btn-select-value">Select an Item</span>
												<span class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
												<ul>
													<li class="selected">Select Supplier</li>
													<li>wasi</li>								
													<li>John Doe</li>
                                                    <li>wasi</li>								
													<li>John Doe</li>
													<li>wasi</li>								
													<li>John Doe</li>
												</ul>
											</a>
                                         </div>										
										<div class="col-md-2 col-sm-12">
											 <button type="button" class="btn btn-primary">Apply</button>						 
											 <button type="button" class="btn btn-danger">Clear</button>						 
										</div>
					</form>
					</div>
                    </div>
					
                     <!-- /filter-->
					 
					 <div class="col-md-12 listings">
									    <div class="w3l-table-info agile_info_shadow">
										<h3 class="pull-left">Requirement</h3>
										<div class="col-md-2 col-sm-12 pull-right">            
											<a class="btn btn-default btn-select mrgn-botm">
												<input type="hidden" class="btn-select-input" id="" name="" value="" />
												<span class="btn-select-value">Select an Item</span>
												<span class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
												<ul>
													<li class="selected">Show List</li>
													<li>10</li>									
													<li>50</li>									
													<li>100</li>
												</ul>
											</a>
                                         </div> 
                                           <div class="col-md-6 col-sm-12 pull-right text-right">
											 <button type="button" class="btn btn-primary">Check Updated Stock</button>
											 <button type="button" class="btn btn-danger">Required</button>
											 <button type="button" class="btn btn-primary">Save & publish</button>
											 <button type="button" class="btn btn-primary">Save</button>
											 
											 </div>										 
											
												<table id="table">
												<thead>
												  <tr>
													<th>
													<div class="checkbox">
														<input id="checkbox1" type="checkbox">
														<label for="checkbox1">
															Select All
														</label>
													</div>
											        </th>
													<th>Product Id</th>
													<th>Product Name</th>
													<th>Date</th>
													<th>Qty in Stock</th>
													<th>Required Qty</th>
													<th>Status</th>
													<th>Addition Date</th>
													<th>Qty Left</th>													
												 </tr>
												</thead>
												<tbody>
												  <tr>
													<td>
													<div class="checkbox">
														<input id="checkbox1" type="checkbox">
														<label for="checkbox1">
															
														</label>
													</div>
													</td>
													<td><span class="bt-content">1</span></td>
													<td><span class="bt-content">Red bulls</span></td>
													<td><span class="bt-content">17th Aug, 2017</span></td>
													<td><span class="bt-content">14 Bottle</span></td>
													<td>
													<span class="bt-content">10 Bottle</span>
													<input class="hide-input" type="text" value=""/>
													</td>
													<td><span class="bt-content">Requested</span></td>
													<td><span class="bt-content">--</span></td>
													<td><span class="bt-content">14 Bottle</span></td>
												</tr>
												
												 <tr>
													<td>
													<div class="checkbox">
														<input id="checkbox1" type="checkbox">
														<label for="checkbox1">
															
														</label>
													</div>
													</td>
													<td><span class="bt-content">1</span></td>
													<td><span class="bt-content">Red bulls</span></td>
													<td><span class="bt-content">17th Aug, 2017</span></td>
													<td><span class="bt-content">14 Bottle</span></td>
													<td>
													<span class="bt-content">10 Bottle</span>
													<input class="hide-input" type="text" value=""/>
													</td>
													<td><span class="bt-content">Done</span></td>
													<td><span class="bt-content">15th Aug, 2017</span></td>
													<td><span class="bt-content">24 Bottle</span></td>
												</tr>
													
												</tbody>
											  </table>
											<div class="col-md-12 text-center">
												<nav>
													<ul class="pagination">
														<li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
														<li><a href="#">1</a></li>
														<li><a href="#">2</a></li>
														<li><a href="#">3</a></li>
														<li><a href="#">4</a></li>
														<li><a href="#">5</a></li>
														<li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
													</ul>
												</nav>
											</div>										  
                                     </div>
				                    </div> 
					  
					
								
				                       
			</div><!-- /container-->
		</div><!-- //inner_content-->
		
	</div>
<!-- banner -->
<!--copy rights start here-->
<div class="copyrights">
	 <p>© 2017 Indian Mesh Pvt. Ltd.</a> </p>
</div>	
<!--copy rights end here-->
<!-- js -->


<!-- model Edit product -->


		<div class="modal fade" id="edit-product" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
				  <div class="modal-dialog">
				  
				<div class="loginmodal-container">
				    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					
				  <form class="edit-form">
				  
					  <div class="form-group">
					  <input type="text" value="" placeholder="Product Name">
					  </div>
						<div class="form-group">
					  <input type="text" value="" placeholder="Brand">
					  </div>
						<div class="form-group">
					  <input type="text" value="" placeholder="Qty in Stock">
					  </div>
					   <div class="form-group">
					  <input type="text" value="" placeholder="Expiry Date">
					  </div>	  			  
					
				  </form>				
				</div>
			</div>
		  </div>
		  
		<!-- model Edit product -->		

<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script src="js/classie.js"></script>
			<!-- script-for-menu -->
<!-- //js -->
	<script type="text/javascript" src="js/jquery.basictable.min.js"></script>
	<script type="text/javascript">
    $(document).ready(function() {
      $('#table #table').basictable();

      $('#table-breakpoint').basictable({
        breakpoint: 768
      });

      $('#table-swap-axis').basictable({
        swapAxis: true
      });

      $('#table-force-off').basictable({
        forceResponsive: false
      });

      $('#table-no-resize').basictable({
        noResize: true
      });

      $('#table-two-axis').basictable();

      $('#table-max-height').basictable({
        tableWrapper: true
      });
    });
</script>
<script>
$(document).ready(function () {
    $(".btn-select").each(function (e) {
        var value = $(this).find("ul li.selected").html();
        if (value != undefined) {
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
    });
});

$(document).on('click', '.btn-select', function (e) {
    e.preventDefault();
    var ul = $(this).find("ul");
    if ($(this).hasClass("active")) {
        if (ul.find("li").is(e.target)) {
            var target = $(e.target);
            target.addClass("selected").siblings().removeClass("selected");
            var value = target.html();
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
        ul.hide();
        $(this).removeClass("active");
    }
    else {
        $('.btn-select').not(this).each(function () {
            $(this).removeClass("active").find("ul").hide();
        });
        ul.slideDown(300);
        $(this).addClass("active");
    }
});

$(document).on('click', function (e) {
    var target = $(e.target).closest(".btn-select");
    if (!target.length) {
        $(".btn-select").removeClass("active").find("ul").hide();
    }
});

</script>
<script>
$(document).ready(function() {
    $('.input-daterange').datepicker({autoclose:true, startView:2, format:"yyyy-mm-dd"}).on('changeDate', function(e){
        if (e.viewMode === 'days') {
            $(this).datepicker('hide');
        }
    });
});
</script>
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>

</form>
</body>
</html>