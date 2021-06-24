
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%> 
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Kitchen Vendor</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 		
		var pattern1=/^([0-9]+)$/;
		var pattern2=/^([^A-Za-z_]+)$/;
		var pattern3=/^[a-zA-Z ]*$/;
				
		var i=0;
 		var j=0; 
 		var pageNo=0;
	    var min=0;	
	    var req=null;
	    				
		onload=function(){
			getKitchenVendorList();
			//getPaginationList();
			getVendorRecordList();
			
		} 
		
		<%-- window.onload = function() {							
		    changePage(<%=request.getAttribute("page")%>);
		}; --%>
		
		function trim(s){
			return s.replace(/^\s+|\s+$/,''); 
		}
		
		function getKitchenVendorList(){						
			document.getElementById("table").innerHTML = "<thead><tr><th>Id</th><th>Name</th><th>Vendor Type</th><th>Email Id</th><th>Phone No.</th><th>Balance</th><th>Action</th></tr></thead><logic:iterate id='VendorFormVO' name='vendortList'><bean:define id='tmpVendorFormVO' name='VendorFormVO' type='com.indianmesh.inventory.kitchen.vendor.VendorFormVO'></bean:define><tbody><tr><td><span class='bt-content'><bean:write name='tmpVendorFormVO' property='ID'></bean:write></span></td><td><span class='bt-content'><bean:write name='tmpVendorFormVO' property='name'></bean:write></span></td><td><span class='bt-content'><bean:write name='tmpVendorFormVO' property='type'></bean:write></span></td><td data-th='Qty in Stock'><span class='bt-content'><bean:write name='tmpVendorFormVO' property='emailId'></bean:write></span></td><td><span class='bt-content'><bean:write name='tmpVendorFormVO' property='phoneNo'></bean:write></span></td><td><span class='bt-content'><bean:write name='tmpVendorFormVO' property='ballance'></bean:write></span></td><td class='font-st'><a class='delets btn-danger btn' data-toggle='modal' data-target='#delet' ><i class='fa fa-trash-o' aria-hidden='true'></i></a><a class='delets' href='javascript:viewKitchenProduct(<bean:write name='tmpVendorFormVO' property='vendorId'/>);'><i class='fa fa-eye' aria-hidden='true'></i></a><a class='delets' href='javascript:editKitchenProduct(<bean:write name='tmpVendorFormVO' property='vendorId'/>);'><i class='fa fa-pencil' aria-hidden='true'></i></a><a class='delets btn-primary btn' href='javascript:supplyKitchenVendor(<bean:write name='tmpVendorFormVO' property='vendorId'/>);'><i class='fa fa-pencil' aria-hidden='true'></i></a></td></tr></tbody></logic:iterate>";												
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
			document.forms[0].action="kitchenVendor.do?methodName=getPagination&index="+index;
			document.forms[0].submit();
		}
		
		function deleteKitchenVendor(vendorId){	 
			document.forms[0].action="kitchenVendor.do?methodName=deleteKitchenVendor&vendorId="+vendorId;
			document.forms[0].submit();
		} 
		
		function editKitchenProduct(vendorId){	
			document.forms[0].action="kitchenVendor.do?methodName=editKitchenVendor&vendorId="+vendorId;
			document.forms[0].submit();			
		}
		
		function viewKitchenProduct(vendorId){	
			document.forms[0].action="kitchenVendor.do?methodName=viewKitchenVendor&vendorId="+vendorId;
			document.forms[0].submit();			
		}
		
		function supplyKitchenVendor(vendorId){	
			document.forms[0].action="kitchenVendor.do?methodName=getSupplyKitchenProductsFormByVendorId&vendorId="+vendorId;
			document.forms[0].submit();			
		}
		
		function searchDetails(){
			
			var search = document.getElementById("search").value;
			var start = document.getElementById("start").value;
			var end = document.getElementById("end").value;
			
			//pattern1.test(search);
			//pattern3.test(search);
						
			if(pattern1.test(search.trim())){
				if(search.length!=10){
					alert('Please enter correct phone number for the the vendor search.');
					 document.getElementById('search').value=''; 
					 document.getElementById("start").value=''; 
					 document.getElementById("end").value=''; 
				     document.getElementById("search").focus();
				}else{
					document.forms[0].action="kitchenVendor.do?methodName=getkitchenVendorByPhone&phone="+search;
					document.forms[0].submit();
				}
			}else {
				if((start!="") && ( end != "")){
					document.forms[0].action="kitchenVendor.do?methodName=getkitchenVendorsByDates&start="+start+"&end="+end;
					document.forms[0].submit();
				}else{
					document.forms[0].action="kitchenVendor.do?methodName=getkitchenVendorByName&name="+search;
					document.forms[0].submit(); 
				}				
			}				
		}
		
		function getVendorTypeList(){	
			var url = "kitchenVendor.do?methodName=getVendorType";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
				req.open("GET", url, true);
				req.onreadystatechange = handleVendorTypeHttpResponse;
				req.send(null);
			} else if (window.ActiveXObject) {
				//req = new ActiveXObject("Msxml2.XMLHTTP");
				req = new ActiveXObject("Microsoft.XMLHTTP");
				req.open("GET", url, true);
				req.onreadystatechange = handleVendorTypeHttpResponse;
				req.send(null);
			}
		}
					
		function handleVendorTypeHttpResponse(){
			  if (req.readyState==4){	
			       var xmlDoc=req.responseXML; 
			       var validate=xmlDoc.getElementsByTagName("vendorTypeList");
			   
			  try{ 																		  					  									  
				  if(validate[0].childNodes[0].childNodes[0].nodeValue == "true"){
				     var selectBox = document.getElementById("typeId");  
				     
				     if(selectBox.options.length!=3){
				     for(var i=1;i<validate[0].childNodes.length;i++){
				        var oOption = document.createElement("OPTION");
							oOption.text =validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
							oOption.value =  validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
							selectBox.options.add(oOption);				            
				     	}
				     }
				  }else{
				    alert("No type available for this selected Category");
				  }
			   }catch(err){
			  		alert("Error While fetching the data for type category");
			    	}
			    }
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
		
		function clearSearch(){
			document.getElementById('search').value=''; 
	        document.getElementById("search").focus();
		}
		
		function showListCriteriaWise(){
			var e = document.getElementById("listOfItem");
			var limit = e.options[e.selectedIndex].value;
			document.forms[0].action="kitchenVendor.do?methodName=getkitchenVendorsByRecords&limit="+limit;
			document.forms[0].submit();
		}	
		
		function showListTypeWise(){
			getVendorTypeList();
			
			var type = null;
			var elements = document.getElementById("typeId");
			for(var i=0; i < elements.length ; i++){
				if(i==0){
					if(elements[i].selected){						
						break;
					}				
				} else if (elements[i].selected){
					type = elements[i].value;
				}																    
			}	
			
			if(type!=null){
				document.forms[0].action="kitchenVendor.do?methodName=getkitchenVendorsByType&type="+type;
				document.forms[0].submit();
			}
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
<form id="inventoryKitchenVendorForm" method="post" name="inventoryKitchenVendorForm" action="">
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
									
									<li>Kitchen Vendor</li>
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
							<input id="search" type="text" class="search-query" onblur="getVendorTypeList();" placeholder="Search by name and Phone No"> 						
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
												<select id="listOfItem" onchange="showListCriteriaWise();">
													<option value="">Select an Item</option>												  
												</select></a> 																																												 
                                         </div> 	
										 <div class="col-md-2 col-sm-12">            
											<a class="btn btn-default btn-select mrgn-botm">							
												<select id="typeId" onchange="showListTypeWise();">
													<option value="0">Select an Item</option>
													<option value="1">Buyer</option>
													<option value="2">Supplier</option>												  
												</select></a> 							
					                    </div>  					                                                 											 					 											 
										 <a href="kitchenVendor.do?methodName=addKitchenVendor" class="btn btn-primary btn-flat btn-pri pull-right"><i class="fa fa-plus" aria-hidden="true"></i> Add Vendor</a>									 										 	
										 <logic:present name="vendortList"><logic:notEmpty name="vendortList"><table id="table"></table></logic:notEmpty></logic:present>
											  
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
                         <button type="button" class="btn btn-danger" onclick="deleteKitchenVendor('<bean:write name="tmpVendorFormVO" property="vendorId"/>');">Yes</button>						 
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