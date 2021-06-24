<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Inventory Product List</title>
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
<link href="css/fastselect.min.css" rel="stylesheet" type="text/css" media="all" />
<!-- <script src="https://rawgit.com/dbrekalo/attire/master/dist/js/build.min.js"></script> -->

<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome-icons -->
<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800" rel="stylesheet">
</head>
<body onLoad = "disableKeys();">

<form id="inventoryBarInventoryProductForm" method="post" name="inventoryBarInventoryProductForm" action="">

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
									
									<li>Inventory Product List</li>
									<li class="pull-right section-head"><a href="inventoryBarManagement.do?methodName=getBarManagementStore"> Bar Store</a></li>
									<li class="pull-right section-head"><a href="inventoryBarManagement.do?methodName=getBarManagement"> Back</a></li>
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
                         <button type="button" class="btn btn-primary">Apply</button>						 
                         <button type="button" class="btn btn-danger">Clear</button>						 
					</div>
					</form>
					</div>
                    </div>
					
                     <!-- /filter-->
					  <div class="col-md-12 listings">
									    <div class="w3l-table-info agile_info_shadow">
										<div class="col-md-2 col-sm-12">            
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
										 <a href="bar-inventory-add.html" class="btn btn-primary btn-flat btn-pri pull-right"><i class="fa fa-plus" aria-hidden="true"></i> Add New Product</a>

												<table id="table">
												<thead>
												  <tr>
													<th>Product Id</th>
													<th>Product Name</th>
													<th>Item Descriotion</th>
													<th>Type</th>
													<th>Qty in Store</th>
													<th>Serv Qty</th>													
													<th>Action</th>													
												 </tr>
												</thead>
												<tbody>
												  <tr>
													<td><span class="bt-content">1</span></td>
													<td><span class="bt-content">Chivas 12yr</span></td>
													<td><span class="bt-content">
													<a class="description" data-toggle="modal" data-target="#description" href="#">Lorem ipsum dummy text</a>
													</span></td>
													<td><span class="bt-content">whisky</span></td>
													<td data-th="Qty in Stock">
													<span class="bt-content">15 bottle / 3000 ml</span>
													
													</td>													
                                                     <td>
													<span class="bt-content">125 peg</span>
													</td>													
													<td>													
													<span class="bt-content">
                                                     <div class="btn-group" id="status" data-toggle="buttons">
													  <label class="btn btn-default btn-on btn-sm active">
													  <input type="radio" value="1" name="multifeatured_module[module_id][status]" checked="checked">ON</label>
													  <label class="btn btn-default btn-off btn-sm ">
													  <input type="radio" value="0" name="multifeatured_module[module_id][status]">OFF</label>
													</div>													
													<a class="delets btn-primary btn" href="inventory-edit-prod.html"><i class="fa fa-pencil" aria-hidden="true"></i>
													</a>
													<a class="delets btn-danger btn" data-toggle="modal" data-target="#delet" href="#"><i class="fa fa-trash-o" aria-hidden="true"></i>
													</a>
													<a class="delets btn-primary btn" href="inventory-view.html"><i class="fa fa-eye" aria-hidden="true"></i></i>
													</a>
													</span>
													</td>												
												</tr>
												
												<tr>
													<td><span class="bt-content">1</span></td>
													<td><span class="bt-content">Chivas 12yr</span></td>
													<td><span class="bt-content">
													<a class="description" data-toggle="modal" data-target="#description" href="#">Lorem ipsum dummy text</a>
													</span></td>
													<td><span class="bt-content">whisky</span></td>
													<td data-th="Qty in Stock">
													<span class="bt-content">15 bottle / 3000 ml</span>
													
													</td>													
                                                     <td>
													<span class="bt-content">125 peg</span>
													</td>													
													<td>													
													<span class="bt-content">
                                                     <div class="btn-group" id="status" data-toggle="buttons">
													  <label class="btn btn-default btn-on btn-sm active">
													  <input type="radio" value="1" name="multifeatured_module[module_id][status]" checked="checked">ON</label>
													  <label class="btn btn-default btn-off btn-sm ">
													  <input type="radio" value="0" name="multifeatured_module[module_id][status]">OFF</label>
													</div>													
													<a class="delets btn-primary btn" href="inventory-edit-prod.html"><i class="fa fa-pencil" aria-hidden="true"></i>
													</a>
													<a class="delets btn-danger btn" data-toggle="modal" data-target="#delet" href="#"><i class="fa fa-trash-o" aria-hidden="true"></i>
													</a>
													<a class="delets btn-primary btn" href="inventory-view.html"><i class="fa fa-eye" aria-hidden="true"></i></i>
													</a>
													</span>
													</td>												
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


		
		<div class="modal fade" id="delet" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
				  <div class="modal-dialog">
				  
				<div class="loginmodal-container">
				    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					
				  <form class="delete-form">
				  
					  <h3>Are you sure want to delet</h3>	  			  
					<div class="col-md-12 col-sm-12">
                         <button type="button" class="btn btn-danger">Yes</button>						 
                         <button type="button" class="btn btn-primary">No</button>						 
					</div>
				  </form>				
				</div>
			</div>
		  </div>
		  
		  <div class="modal fade" id="description" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
				  <div class="modal-dialog">
				  
				<div class="loginmodal-container">
				    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					
				  <form class="delete-form">
				  
					 <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,</p><br>
					 <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,</p>
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
      $('#table').basictable();

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
<script>
$(document).ready(function(e){
    $( document ).on( 'click', '.bs-dropdown-to-select-group .dropdown-menu li', function( event ) {
    	var $target = $( event.currentTarget );
		$target.closest('.bs-dropdown-to-select-group')
			.find('[data-bind="bs-drp-sel-value"]').val($target.attr('data-value'))
			.end()
			.children('.dropdown-toggle').dropdown('toggle');
		$target.closest('.bs-dropdown-to-select-group')
    		.find('[data-bind="bs-drp-sel-label"]').text($target.context.textContent);
		return false;
	});
});
</script>
<script type="text/javascript" src="js/fastselect.standalone.js"></script>
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
</form>

</body>
</html>