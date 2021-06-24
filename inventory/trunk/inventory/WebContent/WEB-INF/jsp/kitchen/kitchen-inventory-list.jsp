
<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Kitchen Inventory product</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>

<script type="application/x-javascript"> 

		//addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		var tokenValue= '<%=request.getAttribute("TRACKERID") %>';
		
		var i=0;
 		var j=0; 
 		var pageNo=0;
	    var min=0;	

	    var pattern1=/^([0-9]+)$/;
		var pattern2=/^([^A-Za-z_]+)$/;
		var pattern3=/^[A-Za-z]+$/; 
				
		onload=function(){
			getKitchenInvengoryProductList();
			//getPaginationList();
		} 
		
		function trim(s){
			return s.replace(/^\s+|\s+$/,''); 
		}
		
		function getKitchenInvengoryProductList(){						
			var threadStr='';
			threadStr = "<thead><tr><th>Product Id</th><th>Product Name</th><th>Item Descriotion</th><th>Type</th><th>Qty in Store</th><th>Action</th></tr></thead><logic:iterate id='formVOO' name='productList'><bean:define id='tempFormVOO' name='formVOO' type='com.indianmesh.inventory.kitchen.inventory.ProductFormVO'></bean:define><tbody><tr><td><span class='bt-content'><bean:write name='tempFormVOO' property='ID'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='name'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='description'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='productTypeId'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='quantityId'></bean:write></span></td><td><span class='bt-content'><div class='btn-group' id='statusMain' data-toggle='buttons'><label class='<bean:write name='tempFormVOO' property='statusOn'/>'><input type='radio' id='status' value='1' property='status' styleClass='radioCheckTag' onchange='check(<bean:write name='tempFormVOO' property='productId'/>,<bean:write name='tempFormVOO' property='status'/>);' >ON</label> <label class='<bean:write name='tempFormVOO' property='statusOff'/>'><input type='radio' id='status' value='0' property='status' styleClass='radioCheckTag' onchange='check(<bean:write name='tempFormVOO' property='productId'/>,<bean:write name='tempFormVOO' property='status'/>);' >OFF</label></div><a class='delets btn-primary btn' href='javascript:editInventoryProduct(<bean:write name='tempFormVOO' property='productId'/>);'><i class='fa fa-pencil' aria-hidden='true'></i></a><a class='delets btn-danger btn' data-toggle='modal' data-target='#delet' ><i class='fa fa-trash-o' aria-hidden='true'></i></a><a class='delets btn-primary btn' href='javascript:viewInventoryProduct(<bean:write name='tempFormVOO' property='productId'/>);'><i class='fa fa-eye' aria-hidden='true'></i></a></span></td></tr></logic:iterate>";						
			document.getElementById("table").innerHTML = threadStr;	
			
			for(var i=0;i<6;i++)j=i;
		}
		
		function getPaginationList(){
		    var length = '<%=request.getAttribute("length")%>';			    
			var innerStr = '';			
			
			var pageNo = i;
			for(var min=0;min<=length;pageNo++){				
					
				if(min==0 && i==0){
					innerStr=innerStr+"<div class='col-md-12 text-center'><nav><ul class='pagination'><li><a href='javascript:getPagination("+(min-j)+","+(min)+")' aria-label='Previous'><span aria-hidden='true'>«</span></a></li>";								
					i++;
				}
				
				if((pageNo!=0) && ((min+j)<length)){
					innerStr=innerStr+"<li><a href='javascript:getPagination("+min+","+(min+j)+")'>"+pageNo+"</a></li>";	 			
					min=(min+j);
					i++;
				}
				
				if((min+j)>length || (i>6)){
					innerStr=innerStr+"<li><a href='javascript:getPagination("+min+","+(min+j)+")' aria-label='Next'><span aria-hidden='true'>»</span></a></li>";
					break;
				}								
			}
					
			document.getElementById("pagination").innerHTML = innerStr;	    	    
		}
		
		//function hideURLbar(){ window.scrollTo(0,1); } 
		
		function editInventoryProduct(productId){			
			document.forms[0].action="kitchenInventoryProduct.do?methodName=editKitchenInventoryProduct&productId="+productId;
			document.forms[0].submit();			
		}
		
		function viewInventoryProduct(productId){
			document.forms[0].action="kitchenInventoryProduct.do?methodName=viewKitchenInventoryProduct&productId="+productId;
			document.forms[0].submit();				
		}
		
		function deleteKitchenInventoryProduct(productId){
			document.forms[0].action="kitchenInventoryProduct.do?methodName=deleteKitchenInventoryProduct&productId="+productId; 
			document.forms[0].submit();				
		}
		
		function showListCriteriaWise(){
			var e = document.getElementById("listOfItem");
			var limit = e.options[e.selectedIndex].value;															
			document.forms[0].action="kitchenInventoryProduct.do?methodName=getKitchenInventoryProductsByRecords&limit="+limit;
			document.forms[0].submit();
		}
		
		function getPagination(pageFrom,pageTo){																																
			var index=pageFrom+','+pageTo;																
			document.forms[0].action="kitchenInventoryProduct.do?methodName=getPagination&index="+index;
			document.forms[0].submit();
		}
		
		function check(productId,status){
	  		//debugger;
	  		if(status){																	  																				
	  			var statusOn="btn btn-default btn-on btn-sm";
	  			var statusOff="btn btn-default btn-off btn-sm active";
	  			var status="false";
	  			document.forms[0].action="kitchenInventoryProduct.do?methodName=updateKitchenInventoryProductStatus&productId="+productId+"&status="+status+"&statusOn="+statusOn+"&statusOff="+statusOff;
				document.forms[0].submit();		
	  		} else {
	  			var statusOn = "btn btn-default btn-on btn-sm active";
	  			var statusOff = "btn btn-default btn-off btn-sm";
	  			var status="true";
	  			document.forms[0].action="kitchenInventoryProduct.do?methodName=updateKitchenInventoryProductStatus&productId="+productId+"&status="+status+"&statusOn="+statusOn+"&statusOff="+statusOff;
				document.forms[0].submit();
			}												  				
		}
		
		function searchDetails(){
			
			var str = document.getElementById("search").value;
			var start = document.getElementById("start").value;
			var end = document.getElementById("end").value;
			
			//pattern1.test(search);
			//pattern3.test(search);
				
			if(str.includes('IKP')){
				var len = str.indexOf('P');
				var index = len + 1;
				var search = str.substring(index, str.length);
				document.forms[0].action="kitchenInventoryProduct.do?methodName=getKitchenInventoryProductsById&search="+search;
				document.forms[0].submit();
			}
			
			else if(pattern3.test(search)){
				document.forms[0].action="kitchenInventoryProduct.do?methodName=getKitchenInventoryProductsByName&search="+str;
				document.forms[0].submit(); 
			}
			
			else if((start!="") && ( end != "")){
				document.forms[0].action="kitchenInventoryProduct.do?methodName=getKitchenInventoryProductsByCreatedDates&start="+start+"&end="+end;
				document.forms[0].submit();
			}
			
			else{
				alert('Please enter correct inputs for the inventory product search.');
				 document.getElementById('search').value=''; 
			        document.getElementById("search").focus();
			}
		}
		
		function clearSearch(){
			document.getElementById('search').value=''; 
	        document.getElementById("search").focus();
		}
			
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
<body>

<form id="inventoryKitchenInventoryProductForm" method="post" name="inventoryKitchenInventoryProductForm" action="">

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
									<li>Inventory</li>
									<li class="pull-right section-head"><a href="inventoryKitchenManagement.do?methodName=getKitchenManagementStore">Kitchen Store</a></li>
									<li class="pull-right section-head"><a href="inventoryKitchenManagement.do?methodName=getkitchenManagement"> Back</a></li>
								</ul>
							</div>
						</div>	
                     <!-- /filter-->
             		<div class="col-md-12 listings filter">
					<div class="w3l-table-info agile_info_shadow mrgn-0">
					<form class="filter-drop-down">
					<div class="col-md-3 col-sm-12">
						 <div class="input-search">
						<input type="text" class="search-query" id="search" placeholder="Search by Product name and Id">						
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
                         <button type="button" class="btn btn-primary" onclick="searchDetails();">Apply</button>						 
                         <button type="button" class="btn btn-danger" onclick="clearSearch();">Clear</button>						 
					</div>
					</form>
					</div>
                    </div>
					
                     			<!-- /filter-->
					  				<div class="col-md-12 listings">
									    <div class="w3l-table-info agile_info_shadow">
										<div class="col-md-2 col-sm-12">            
											<a class="btn btn-default btn-select mrgn-botm">
												<select id="listOfItem" onChange="showListCriteriaWise();">
												  <option value="">Select an Item</option>
												  <option value="1">05</option>
												  <option value="2">10</option>
												  <option value="3">25</option>
												</select>		
											</a> 
                                         </div> 
                                         	
										 <a href="kitchenInventoryProduct.do?methodName=addKitchenInventoryProduct" class="btn btn-primary btn-flat btn-pri pull-right"><i class="fa fa-plus" aria-hidden="true"></i> Add New Product</a>
																																	 
										 <logic:present name="productList"><logic:notEmpty name="productList">
    									 <table id="table"></table>																																			
											  											  
										 </logic:notEmpty></logic:present>		
											<div class="col-md-12 text-center">
												<nav>
													<ul id="pagination" class="pagination"></ul>
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
	 <p>Â© 2017 Indian Mesh Pvt. Ltd.</a> </p>
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
                         <button type="button" class="btn btn-danger" onclick="deleteKitchenInventoryProduct('<bean:write name="tempFormVOO" property="productId"/>');">Yes</button>						 
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