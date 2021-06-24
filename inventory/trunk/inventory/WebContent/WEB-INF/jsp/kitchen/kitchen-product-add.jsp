<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Add Product</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 
		var tokenValue= '<%=request.getAttribute("TRACKERID") %>';
		
		var quantity = null;

		addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } 		

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
<body >
<form id="inventoryKitchenProductForm" method="post" name="inventoryKitchenProductForm" action="">

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
									<li>Add Product</li>
									<li class="pull-right section-head"><a href="inventoryKitchenManagement.do?methodName=getKitchenManagementStore">Kitchen Store</a></li>
									<li class="pull-right section-head"><a href="inventoryKitchenManagement.do?methodName=getkitchenManagement"> Back</a></li>
								</ul>
							</div>
						</div>	
                     <!-- /filter-->             		
					
                     <!-- /filter-->
					
				<div class="col-md-12 listings">
					            <div class="w3l-table-info agile_info_shadow mrgn-tops">
										 <h3 class="w3_inner_tittle two pull-left">Add Product</h3>
								                <div class="form-body">
												<form>																								
													<bean:define id="tempProductFormVO" name="productFormVO" type="com.indianmesh.inventory.kitchen.product.ProductFormVO"></bean:define>               
               										<html:hidden property="productId" name="tempProductFormVO"/> 
               										<html:hidden property="sellerId" name="tempProductFormVO"/> 
               										<html:hidden property="subSellerId" name="tempProductFormVO"/> 
               										 
													<div class="col-md-6">													
													       <div class="form-group">
                                                                <label class="col-md-12 control-label">Product Name: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="name" id="name" maxlength="20" placeholder="" type="text">
																</div>
                                                            </div>															
                                                            <div class="form-group">
                                                                <label class="col-md-6 control-label">Product Type: </label>
                                                                 <label class="col-md-6 control-label">Supplier Vendor: </label>                                                                                                                         
                                                                <div class="col-md-6">            																	
																	<html:select property="productType" styleId="productType" styleClass="selectBoxTag" style="width:125px" name="tempProductFormVO">
																		<html:option value="0">----SELECT----</html:option>
																		<html:options collection="productTypeList" property="key" labelProperty="value" name="tempProductFormVO"></html:options>
																	</html:select>
																  </div>															 
                                                                 <div class="col-md-6">  
                                                                 <script type="application/x-javascript">
		                                                                function getDescription(){
		                                                                	document.getElementById("description").value="";

																			var temp=(document.getElementById("costPrice").value) - (document.getElementById("amountPaid").value);
																			var amountPaidTypeText = document.getElementById("amountPaidTypeId").options[document.getElementById("amountPaidTypeId").selectedIndex].text;
																			var amountPaidTypeId = document.getElementById("amountPaidTypeId").options[document.getElementById("amountPaidTypeId").selectedIndex].value;
																			var description = "Product Name "+document.getElementById("name").value+" is "+document.getElementById("productType").options[document.getElementById("productType").selectedIndex].text+" supplied by "+document.getElementById("vendorId").options[document.getElementById("vendorId").selectedIndex].text+" .";
																			
																			document.getElementById("description").value=description;
																		}
		                                                                </script>          																	
																	<html:select property="vendorId" styleId="vendorId" styleClass="selectBoxTag" style="width:125px" onchange="getDescription();" name="tempProductFormVO">
																		<html:option value="0">----SELECT----</html:option>
																		<html:options collection="vendorList" property="key" labelProperty="value" name="tempProductFormVO"></html:options>
																	</html:select>
																  </div>
                                                            </div>                                                          
                                                            <div class="form-group">
                                                                <label class="col-md-12 control-label">Description: </label>
                                                                <div class="col-md-12">                                                               		
                                                                    <textarea class="form-control" name="description" id="description" maxlength="150" placeholder="" type="text"></textarea>
                                                                </div>
                                                            </div>															 
															<div class="form-group">
                                                                <label class="col-md-6 control-label">Qty Per Item:</label>
                                                                <label class="col-md-6 control-label">Qty Type: </label> 
                                                                <div class="col-md-6">
																  <input class="form-control" name="quantityPerItem" placeholder="" id="quantityPerItem" type="text" maxlength="6" onblur="totalCount();">
																</div>
																
																<div class="col-md-6">            
																	<html:select property="quantityId" styleId="quantityId" styleClass="selectBoxTag" style="width:125px" name="tempProductFormVO">
																			<html:option value="0">----SELECT----</html:option>
																			<html:options collection="quantityTypeList" property="key" labelProperty="value" name="tempProductFormVO"></html:options>
																	</html:select>																		
																 </div>
																 																 													 
																</div>
																<div class="form-group">
                                                                <label class="col-md-12 control-label">
																Qty Count:
                                                                </label>                                                                
																  <div class="col-md-8">
																  <script type="application/x-javascript">
																    function totalCount(){	
	                                                                	document.getElementById("total").value="";
																		var temp=(document.getElementById("quantityPerItem").value) * (document.getElementById("quantityCount").value);
																		quantity = document.getElementById("quantityId").options[document.getElementById("quantityId").selectedIndex].text;
																		
																		if(quantity=='KG'){
																			temp=(temp)*(1000);
																			quantity='GM';
																		}else if(quantity=='JAR'){
																			temp=(temp)*(1000);
																			quantity='Ml';
																		}else if(quantity=='GALLON'){
																			temp=(temp)*(3785.41);
																			quantity='Ml';
																		}else if(quantity=='LTR'){
																			temp=(temp)*(1000);
																			quantity='Ml';
																		}else if(quantity=='BTL'){
																			temp=(temp)*(1000);
																			quantity='Ml';
																		}
																		
																		//var total = temp +" "+quantity;
																		document.getElementById("total").value=temp;
																	}
																	</script>   
																  <input class="form-control" name="quantityCount" id="quantityCount" placeholder="" type="text" maxlength="3" onblur="totalCount();">
																  </div>
																</div>
																
																<div class="form-group">
                                                                	<label class="col-md-12 control-label">Total
                                                                </label>
                                                                <div class="col-md-8">
                                                                    <input readonly="" class="form-control" name="total" id="total" maxlength="26" placeholder="" type="text">
                                                                </div>
                                                            </div>																                                                           
												    </div>
													<div class="col-md-6">                                                         
															 <div class="form-group">
                                                                <label class="col-md-12 control-label">Cost Price:</label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="costPrice" id="costPrice" maxlength="6" placeholder="" type="text">
																</div>
                                                            </div>
															<div class="form-group"><!-- onblur="totalAmoundPending();" -->
                                                                <label class="col-md-6 control-label">Amount Paid:</label>
                                                                <label class="col-md-6 control-label">Amount Paid Type:</label>
                                                                <div class="col-md-6">                                                              
                                                                    <input class="form-control" name="amountPaid" id="amountPaid" placeholder="" maxlength="6" type="text" >
																</div>
																<div class="col-md-6">  
																<script type="application/x-javascript">
			                                                                function totalAmoundPending(){	
			                                                                	document.getElementById("creditNote").value="";
			                                                        			var amountPending=(document.getElementById("costPrice").value) - (document.getElementById("amountPaid").value);
			                                                        			document.getElementById("pendingAmount").value=amountPending;
			                                                        			
			                                                        			var amountPaidTypeId = document.getElementById("amountPaidTypeId").options[document.getElementById("amountPaidTypeId").selectedIndex].value;
			                                                        			
			                                                        			if(amountPaidTypeId == 1)
			                                                        				var creditNote = "Rs "+(document.getElementById("amountPaid").value)+" is Fully Paid.";
			                                                        			else if(amountPaidTypeId == 2)
			                                                        				var creditNote = "Rs "+(document.getElementById("amountPaid").value)+" is Partial Paid. And Rs "+amountPending+" is Pending.";
			                                                        			else if(amountPaidTypeId == 3)
			                                                        				var creditNote = "Rs "+amountPending+" is Pending.";
			                                                        			
			                                                        			document.getElementById("creditNote").value=creditNote;
			                                                        		}
		                                                                </script>          															
																	<html:select property="amountPaidTypeId" styleId="amountPaidTypeId" styleClass="selectBoxTag" style="width:125px" onchange="totalAmoundPending();" name="tempProductFormVO">
																		<html:option value="0">----SELECT----</html:option>
																		<html:options collection="amountPaidTypeList" property="key" labelProperty="value" name="tempProductFormVO"></html:options>
																	</html:select>
																  </div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Pending Amount:</label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="pendingAmount" id="pendingAmount" maxlength="26" readonly="" type="text">
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Credit Notes:</label>
                                                                <div class="col-md-12">
                                                                    <textarea class="form-control" id="creditNote" maxlength="256" name="creditNote"></textarea>
																</div>
                                                            </div>
                                                            <div class="form-group">
                                                               <label class="col-md-6 control-label">Transaction ID:</label>
                                                                <label class="col-md-6 control-label">Invoice ID:</label>
                                                                <div class="col-md-6">
                                                                	<input class="form-control" name="transactionId" readonly="" id="transactionId" type="text" maxlength="6" value='<bean:write name="tempProductFormVO"  property="transactionId" />' > 
																</div>
																<div class="col-md-6">
																	<input class="form-control" name="invoiceId" readonly="" id="invoiceId" type="text" maxlength="6" value='<bean:write name="tempProductFormVO"  property="invoiceId" />' > 
																</div>
                                                            </div>                                                                                                                                                                                																																
															<div class="form-group">
															<div class="col-md-12">
															 	<script type="text/javascript">																	
																function trim(s){
																    return s.replace(/^\s+|\s+$/,''); 
																}
																
																function saveProductDetails(){																																																	
																	var pattern1=/^([0-9]+)$/;
																	var pattern2=/^([^A-Za-z_]+)$/;
																	var pattern3=/^[A-Za-z]+$/; 
																	
																	// Name of the company
																	if(document.getElementById("name").value==""){
																	   alert("Product name is Mandatory, Please enter the name of the product.");
																	   document.getElementById('name').value=''; 
																	   document.getElementById("name").focus();
																	   return; 
																	}
																	
																	var name=document.getElementById("name").value;
																	if(name!=""){
																		if(document.getElementById('name').value.length > 20){  
																	         alert("Product Name should not greater than 20 characters long.");
																	         document.getElementById('name').value=''; 
																	         document.getElementById("name").focus();
																	         return;
																	    }
																 	}
																	
																	if(document.getElementById('name').value.length > 20)  
																    {  
																        alert('The length of name should be less 21 character long.');  
																        document.getElementById('name').value=''; 
																        document.getElementById("name").focus();
																        return;  
																    }  
																		
																	// productType of the company																																					
																	var elements = document.getElementById("productType");
																	for(var i=0; i < elements.length ; i++){
																		if(i==0){
																			if(elements[i].selected){
																				alert("Product Type is Mandatory, Please select the Product Type.");
																				document.getElementById("productType").focus();
																				return; 
																			}else{
																				break;
																			}																		
																		}																    
																	}		
																	
																	if(document.getElementById("description").value=="")  
																    {  
																        alert("Please enter your correct description.");  
																        document.getElementById("description").value=''; 
																        document.getElementById("description").focus();
																        return;  
																    }  
																																																	
																	if(document.getElementById("quantityPerItem").value==""){
																	   alert("Quantity Per Item is Mandatory, Please enter the quantity Per Item.");
																	   document.getElementById('quantityPerItem').value=''; 
																	   document.getElementById("quantityPerItem").focus();
																	   return; 
																	}																																		
																	
																	var quantityPerItem=document.getElementById("quantityPerItem").value;
																	if(quantityPerItem!=""){
																	   if(!pattern1.test(quantityPerItem)){
																	         alert("Product quantity should be only Digits.");
																	         document.getElementById("quantityPerItem").value='';
																	         document.getElementById("quantityPerItem").focus();
																	         return;
																	    }
																 	}
																	
																	if(trim(document.getElementById("quantityCount").value)==""){
																	   alert("Quantity Count No is Mandatory, Please enter the Quantity Count.");
																	   document.getElementById("quantityCount").value='';
																	   document.getElementById("quantityCount").focus();
																	   return; 
																	}
																	
																	var quantityCount=document.getElementById("quantityCount").value;
																	if(quantityCount!=""){
																	   if(!pattern1.test(quantityCount)){
																	         alert("Product quantity count should be only Digits.");
																	         document.getElementById("quantityCount").value='';
																			 document.getElementById("quantityCount").focus();
																	         return;
																	    }
																 	}
																		
																	var elements = document.getElementById("quantityId");
																	for(var i=0; i < elements.length ; i++){
																		if(i==0){
																			if(elements[i].selected){
																				alert("Quantity Type is Mandatory, Please select the quantity Type.");
																				document.getElementById("quantityId").focus();
																				return; 
																			}else{
																				break;
																			}																		
																		}																    
																	}	
																	
																	// vendorId of the company																	
																	 if(document.getElementById('vendorId').options[document.getElementById('vendorId').selectedIndex].defaultSelected==true){
																		 alert("Vendor is Mandatory, Please enter the Vendor details.");
																		 document.getElementById('vendorId').value=''; 
																		 document.getElementById("vendorId").focus();
																		 return; 
																	 }
																	
																	 var elements = document.getElementById("vendorId");
																		for(var i=0; i < elements.length ; i++){
																			if(i==0){
																				if(elements[i].selected){
																					alert("Vender is Mandatory, Please select the Vender.");
																					document.getElementById("vendorId").focus();
																					return; 
																				}else{
																					break;
																				}																		
																			}																    
																		}
																	
																	if(document.getElementById("costPrice").value=="")  
																    {  
																        alert("Please enter your correct costPrice" );  
																        document.getElementById("costPrice").value=""; 
																        document.getElementById("costPrice").focus();
																        return;  
																    }  
																																														
																	// serviceTaxNo of the company
																	if(trim(document.getElementById("amountPaid").value)==""){
																	   alert("Amount Paid is Mandatory, Please enter the correct amount paid.");
																	   document.getElementById('amountPaid').value=''; 
																	   document.getElementById("amountPaid").focus();
																	   return; 
																	}
																	
																	// contactPerson of the company
																	if(trim(document.getElementById("pendingAmount").value)==""){
																	   alert("Pending Amount is Mandatory, Please enter the pending amount.");
																	   document.getElementById('pendingAmount').value=''; 
																	   document.getElementById("pendingAmount").focus();
																	   return; 
																	}
																	
																	var costPrice=document.getElementById("costPrice").value;
																	if(costPrice!=""){
																	   if(!pattern2.test(costPrice)){
																	         alert("Cost Price should be Digits only.");
																	         document.getElementById('costPrice').value=''; 
																	         document.getElementById("costPrice").focus();
																	         return;
																	    }
																 	}
																																																														
																	var amountPaid=document.getElementById("amountPaid").value;
																	if(amountPaid!=""){
																	   if(!pattern2.test(amountPaid)){
																		   	 alert("Amount Paid should be Digits only.");
																		   	document.getElementById('amountPaid').value=''; 
																	         document.getElementById("amountPaid").focus();
																	         return;
																	    }
																 	}
																																																	
																	var pendingAmount=document.getElementById("pendingAmount").value;
																	if(pendingAmount==""){
																	   //if(pattern2.test(pendingAmount)){
																	         alert("Please enter Pending amount.");
																	         document.getElementById('pendingAmount').value=''; 
																	         document.getElementById("pendingAmount").focus();
																	         return;
																	   // }
																 	}
																	
																	var elements = document.getElementById("amountPaidTypeId");
																	for(var i=0; i < elements.length ; i++){
																		if(i==0){
																			if(elements[i].selected){
																				alert("Amount paid type is Mandatory, Please select the amount paid type.");
																				document.getElementById("amountPaidTypeId").focus();
																				return; 
																			}else{
																				break;
																			}																		
																		}																    
																	}
																	
																	// contactPhone of the company
																	if(trim(document.getElementById("creditNote").value)==""){
																	   alert("Credit note is mandatory, Please enter the credit note.");
																	   document.getElementById('creditNote').value=''; 
																	   document.getElementById("creditNote").focus();
																	   return; 
																	}
																																																																																			
																	var status = window.confirm("Do you want to save product registration details");
																	if(status){						
																		document.forms[0].action="kitchenProduct.do?methodName=updateKitchenProduct&quantity="+quantity;
																		document.forms[0].submit();
																	}
																}
																
																// close Form														
																function closeForm(){
																	var status = window.confirm("All the entered details in form will be lost. Do you wish to close the form");
																	if(status){
																		document.forms[0].action="kitchenProduct.do?methodName=deleteKitchenProduct";
																		document.forms[0].submit();
																	}
																}
															
															 </script>
															 <button type="button" class="btn btn-primary" onclick="saveProductDetails();">Save</button>
															 <button type="button" class="btn btn-danger" onclick="closeForm();">Close</button>
															 </div>
															 </div>
															
                                                     </div>

												</form>
												</div>
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
$(document).ready(function() {

    $('.button-add').click(function(){
        //we select the box clone it and insert it after the box
        $('.box.template').clone()
                          .show()
                          .removeClass("template")
                          .insertAfter(".box:last");
    }).trigger("click");
    
    $(document).on("click", ".button-remove", function() {
        $(this).closest(".box").remove();
    });
});
</script>
<script type="text/javascript" src="js/fastselect.standalone.js"></script>
<script>
$('.multipleSelect').fastselect();
</script>

<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<div class="container">
    <div class="row">
        <div class="form-group">
            
        </div>
    </div>
</div>
</form>
</body>
</html>