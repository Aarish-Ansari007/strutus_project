<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>


<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Kitchen Reports</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 		
		//addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);		
		//function hideURLbar(){ window.scrollTo(0,1); } 
		var tokenValue= '<%=request.getAttribute("TRACKERID") %>';

			var i=0;
	 		var j=0; 
	 		var pageNo=0;
		    var min=0;	
		    
		    var pattern1=/^([0-9]+)$/;
			var pattern2=/^([^A-Za-z_]+)$/;
			var pattern3=/^[A-Za-z]+$/; 
			var pattern4=/^[a-z0-9]+$/i;
					
			onload=function(){
				getKitchenProductReportList();
				//getPaginationList();
			} 
			
			function getVendorRecordList(){	
				var url="kitchenVendor.do?methodName=getVendorRecordList";
				if (window.XMLHttpRequest) {
					req=new XMLHttpRequest();
					req.open("GET", url, true);
					req.onreadystatechange = handleHttpResponseRecord;
					req.send(null);
				} else if (window.ActiveXObject) {
					//req = new ActiveXObject("Msxml2.XMLHTTP");
					req=new ActiveXObject("Microsoft.XMLHTTP");
					req.open("GET", url, true);
					req.onreadystatechange = handleHttpResponseRecord;
					req.send(null);
				}
			}
						
			function handleHttpResponseRecord(){
				  if (req.readyState == 4){	
				       var xmlDoc = req.responseXML; 
				       var validate = xmlDoc.getElementsByTagName("recordList");
				   
				  try{ 														
					  //document.getElementById("typeId").options.length = 0;
						  									  
					  if(validate[0].childNodes[0].childNodes[0].nodeValue == "true"){
					     var selectBox = document.getElementById("listOfItem"); 
					     for(var i=1;i<validate[0].childNodes.length;i++){
					            var oOption = document.createElement("OPTION");
								oOption.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
								oOption.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
								selectBox.options.add(oOption);
					     	}
					  }else{
					    alert("No type available for this selected Category");
					  }
				   }catch(err){
				  		alert("Error While fetching the data for type category");
				    }
				   }
				 }	
			
			function getProductSupplierList(){	
				
				var url = "kitchenProduct.do?methodName=getSupplierList"; 
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				} else if (window.ActiveXObject) {
					//req = new ActiveXObject("Msxml2.XMLHTTP");
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}
				req.open("GET", url, true);
				req.onreadystatechange = handleHttpResponse;
				req.send(null);
			}
						
			function handleHttpResponse(){
				  if (req.readyState == 4){	
				       var xmlDoc = req.responseXML; 
				       var validate = xmlDoc.getElementsByTagName("vendorsList");
				   
				  try{ 														
					  //document.getElementById("vendorId").options.length = 0;
						  									  
					  if(validate[0].childNodes[0].childNodes[0].nodeValue == "true"){
					     var selectBox = document.getElementById("vendorId");
					     for(var i=1;i<validate[0].childNodes.length;i++){
					            var oOption = document.createElement("OPTION");
								oOption.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
								oOption.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
								selectBox.options.add(oOption);
					     	}
					  }else{
					    alert("No type available for this selected Category");
					  }
				   }catch(err){
				  		alert("Error While fetching the data for type category");
				    }
				   }
				 }	
			
			function getKitchenProductReportList(){						
				document.getElementById("table").innerHTML ="<thead><tr><th>Product Id</th><th>Date & Time</th><th>Product Name</th><th>Last Count</th><th>Requested Qty</th><th>Issued Qty</th><th>Qty In Store</th><th>Action</th></tr></thead><logic:iterate id='formVOO' name='productList'><bean:define id='tempFormVOO' name='formVOO' type='com.indianmesh.inventory.kitchen.stock.ProductFormVO'></bean:define><tbody><tr><td><span class='bt-content'><bean:write name='tempFormVOO' property='ID'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='requestDate'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='productName'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='lastCount'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='requirementId'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='additionId'></bean:write></span></td><td data-th='Qty in Stock'><span class='bt-content'><bean:write name='tempFormVOO' property='availableId'></bean:write></span></td><td data-th='Qty in Stock'><span class='bt-content'><bean:write name='tempFormVOO' property='status'></bean:write></span></td></tr></tbody></logic:iterate>"; 
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
			
			function getPagination(pageFrom,pageTo){																																
				var index=pageFrom+','+pageTo;																
				document.forms[0].action="kitchenReport.do?methodName=getReportByPagination&index="+index;
				document.forms[0].submit();
			}
		
			function searchDetails(){
				
				var search = document.getElementById("search").value;
				var start = document.getElementById("start").value;
				var end = document.getElementById("end").value;
				
				var vendor = null;
				var elements = document.getElementById("vendorId");
				
				if(elements!=null){
					for(var i=0;i<elements.length;i++){
						if(i==0){
							if(elements[i].selected){						
								break;
							}				
						} else if (elements[i].selected){ 
							vendor = elements[i].value;
						}																    
					}	
				}
				
				var search = document.getElementById("search").value;
				
				if((start!="") && ( end !="")){
					document.forms[0].action="kitchenReport.do?methodName=getReportByDate&start="+start+"&end="+end;
					document.forms[0].submit();
				}else{					
					var str = document.getElementById("search").value;	
					if(str.includes('KP')){
						var len = str.indexOf('P');
						var index = len + 1;
						var search = str.substring(index, str.length);
						document.forms[0].action="kitchenReport.do?methodName=getReportByProductId&search="+search;
						document.forms[0].submit();
					}else if(!str.includes('KP')){						
						document.forms[0].action="kitchenReport.do?methodName=getReportByProductName&search="+str;
						document.forms[0].submit();
					}else{					
						alert('Please enter correct inputs for the product search.');
						  document.getElementById('search').value=''; 
					        document.getElementById("search").focus();
					}
				}
			}
			
			function showListStatusWise(){
				var e = document.getElementById("status");			
				var status = e.options[e.selectedIndex].value;
				
				if (status == 1)
					status = 'All';
					else if (status == 2)
						status = 'Pending';
						else if(status == 3)
							status = 'Requested';
							else if (status == 4)
								status = 'Supplied';												
							
				/* if( status=='All'){
					document.forms[0].action="kitchenReport.do?methodName=getKitchenReportIndex";  
					document.forms[0].submit();
				}else{	 */				
					document.forms[0].action="kitchenReport.do?methodName=getReportByStatus&status="+status;
					document.forms[0].submit();
				//}
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
<form id="inventoryKitchenReportForm" method="post" name="inventoryKitchenReportForm" action="">

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
									
									<li>Kitchen Reports</li>
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
						<input type="text" id="search" class="search-query" placeholder="Search by Product name and Id">						
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
												<select id="status" onChange="showListStatusWise();">
												<option value="0">Select an Item</option>
												  <option value="1">All</option>
												  <option value="3">Requested</option>
												  <option value="4">Supplied</option>
												</select></a> 
											</a>
                                         </div>	
                                        						 
										 <logic:present name="productList">
											 <logic:notEmpty name="productList">
											 	<table id="table"></table>
											 </logic:notEmpty>
										 </logic:present>   													
  											
											<div class="col-md-12 text-center">
												<nav>
													<ul id="pagination" class="pagination">																									
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
                         <button type="button" class="btn btn-danger" onclick="deleteKitchenProduct('<bean:write name="tempFormVOO" property="productId"/>');">Yes</button>						 
                         <button type="button" class="btn btn-primary">No</button>						 
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