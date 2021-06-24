
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Kitchen Product Supply</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 

		//addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		var tokenValue= '<%=request.getAttribute("TRACKERID") %>';

		//function hideURLbar(){ window.scrollTo(0,1); } 
					
		onload=function(){
			getKitchenProductSupplyInputList();
			addSupplyInputFieldsInRow();
		} 
		
		function trim(s){
		   return s.replace(/^\s+|\s+$/,''); 
		}
		
		function getKitchenProductSupplyInputList(){						
			document.getElementById("table").innerHTML = "<thead><tr><th>Product Id</th><th>Product Name</th><th>Requested Qty</th><th>Last Count</th><th>Supplied Qty</th><th>Cost Price (Rs.)</th><th>Credit Note Reminder</th></tr></thead><logic:iterate id='formVOO' name='productList'><bean:define id='tempFormVOO' name='formVOO' type='com.indianmesh.inventory.kitchen.stock.ProductFormVO'></bean:define><tbody><tr id='<bean:write name='tempFormVOO' property='productId'></bean:write>'><td data-th='Product ID'><span class='bt-content'><bean:write name='tempFormVOO' property='ID'></bean:write></span></td><td data-th='Product Name'><span class='bt-content'><bean:write name='tempFormVOO' property='productName'></bean:write></span></td><td data-th='Last Count'><span class='bt-content'><bean:write name='tempFormVOO' property='requirementId'></bean:write></span></td><td data-th='Last Count'><span class='bt-content'><bean:write name='tempFormVOO' property='lastCount'></bean:write></span></td></tr></tbody></logic:iterate>";
			for(var i=0;i<6;i++)j=i;
		}
			
		function addSupplyInputFieldsInRow(){
			var productText ='<bean:write name='tempFormVOO' property='productId'></bean:write>';
		
			if(productText!='----'){
			var table=document.getElementById('table');
			var numberOfRows = table.rows.length;
							
			//newCell0.innerHTML=numberOfRows-1;
			for(var i=1;i<numberOfRows;i++){
				var currentRow = document.getElementById("table").rows[i];
				
			    var newCell3=currentRow.insertCell(4);					
					newCell3.align="center";
					newCell3.className="formlabel";
					
				var newCell4=currentRow.insertCell(5);					
					newCell4.align="center";
					newCell4.className="formlabel";
					
				var newCell5=currentRow.insertCell(6);					
					newCell5.align="center";
					newCell5.className="formlabel";
					
				var newTextElement = document.createElement("INPUT");
					newTextElement = newCell3.appendChild(newTextElement);
					newTextElement.name = "quantity"+(i-1);
					newTextElement.id = "quantity"+(i-1);
					//newTextElement.value = 0;
					newTextElement.maxlength="10"
					newTextElement.size="10";
					
				var newTextElement = document.createElement("INPUT");
					newTextElement = newCell4.appendChild(newTextElement);
					newTextElement.name = "price"+(i-1);
					newTextElement.id = "price"+(i-1);
					//newTextElement.value = 0;
					newTextElement.maxlength="12"
					newTextElement.size="12";
					
				var newTextElement = document.createElement("INPUT");
					newTextElement = newCell5.appendChild(newTextElement);
					newTextElement.name = "reminder"+(i-1);
					newTextElement.id = "reminder"+(i-1);
					//newTextElement.value = 0;
					newTextElement.maxlength="56"
					newTextElement.size="32";							
				
					newTextElement.addEventListener('change', function(){
					var table=document.getElementById('table');
					var numberOfRows = table.rows.length;										
					if(numberOfRows==2){
						var product ='<bean:write name='tempFormVOO' property='productId'></bean:write>';
						var lastcount ='<bean:write name='tempFormVOO' property='lastCount'></bean:write>';	
						var adition ='<bean:write name='tempFormVOO' property='additionId'></bean:write>';
						var price ='<bean:write name='tempFormVOO' property='price'></bean:write>';
						var reminder ='<bean:write name='tempFormVOO' property='reminder'></bean:write>';

						var consumption = newTextElement.value;
						document.forms[0].action="kitchenVendor.do?methodName=updateIndexkitchenConsumption&product="+product+"&lastcount="+lastcount+"&adition="+adition+"&price="+price+"&reminder="+reminder;
						document.forms[0].submit();
					 }										
				 });	
			  }
		   }
		}
		
		function saveInvoiceForm(){
			if(document.getElementById("uploadInvoiceFile").value!=""){
				var photoUpLoad=document.getElementById("uploadInvoiceFile").value;
				var photo=photoUpLoad.substring(photoUpLoad.lastIndexOf(".")).toUpperCase();
				pattern=/(.JPG)|(.PNG)|(.GIF)|(.BMP)|(.JPEG)/
				if(photo.search(pattern)==-1){
					alert("Please upload correct invoice file Image, file with .JPG/.PNG/.GIF/.BMP/.JPEG image extension can only be uploaded.");
					document.getElementById("uploadInvoiceFile").value="";    
					document.getElementById("uploadInvoiceFile").focus();    
					return;
				}
			}
			
			var status = window.confirm("Do you want to update and save kitchen vendor stock supply details ?");
			if(status){						
				document.forms[0].action="kitchenVendor.do?methodName=updateKitchenVendorDetails";
				document.forms[0].submit();
			}
		}
		
		function popUp(url){
		    eval("page" + " = window.open(url, '"+"', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=240,height=280,left = 700,top = 220');");
		}
			
		function getPopUP(){
			document.getElementById('popUptable').className="";
		}
						     	
		function closePopUp(){
			document.getElementById('popUptable').className="hide";
		}			

		function closeForm(){
			var status = window.confirm("Do you wish to close the form ?");
			if(status){
				document.forms[0].action="kitchenVendor.do?methodName=getIndexkitchenVendor";
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
									
									<li>Kitchen Product Supply</li>
									<li class="pull-right section-head"><a href="inventoryKitchenManagement.do?methodName=getKitchenManagementStore">Kitchen Store</a></li>
									<li class="pull-right section-head"><a href="inventoryKitchenManagement.do?methodName=getkitchenManagement"> Back</a></li>
								</ul>
							</div>
						</div>	
                     <!-- /filter-->             		
					
                     <!-- /filter-->
                     
                <bean:define id="formVO" name="VendorFormVO" type="com.indianmesh.inventory.kitchen.vendor.VendorFormVO"></bean:define>               
                
                <html:hidden property="vendorId" name="formVO"/>
                     					
				<div class="col-md-12 listings">
					            <div class="w3l-table-info agile_info_shadow mrgn-tops">
										 <h3 class="w3_inner_tittle two pull-left">ADD Kitchen Products Stock</h3>
								                <div class="form-body">
													<div class="col-md-6">
														    <div class="form-group">
                                                                <label class="col-md-12 control-label">Vendor Id: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="ID"/>
																</div>
                                                            </div>                                                      
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Phone Number: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="phoneNo"/>
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Email Id: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="emailId"/>
																</div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-12 control-label">Contact Person: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="contactPerson"/>
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Contact Phone: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="contactPhone"/>
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Contact Email: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="contactEmail"/>
																</div>
                                                            </div>                                                                                                                                                                                    
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Tin: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="tin"/>
																</div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-12 control-label">Pan Card: </label>
                                                                <div class="col-md-12">
                                                                     <bean:write name="formVO" property="pan"/>
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Vat: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="vatNo"/>
																</div>
                                                            </div>																																																										                                                            
													</div>
													<div class="col-md-6">		
													        <div class="form-group">
                                                                <label class="col-md-12 control-label">Vendor Name: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="name"/>
																</div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-12 control-label">Type: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="type"/>
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Billing Address:</label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="billingAddress"/>
																</div>
                                                             </div>														    
															  <div class="form-group">
                                                                <label class="col-md-12 control-label">Pincode:</label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="billingPinCode"/>
																</div>
                                                             </div>
															
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Shipping Address:</label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="shippingAddress"/>
																</div>
                                                             </div>														    
															  <div class="form-group">
                                                                <label class="col-md-12 control-label">Pincode:</label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="shippingPinCode"/>
																</div>
                                                             </div>
                                                             <div class="form-group">
                                                                <label class="col-md-12 control-label">Gst In: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="gst"/>
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Service Tax No: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="serviceTaxNo"/>
																</div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-12 control-label">Date & Time: </label>
                                                                <div class="col-md-12">
                                                                    <bean:write name="formVO" property="lastModifiedDate"/>
																</div>
                                                            </div>
														</div>																														
													</div>																										
												</div>
											</div>
								
											<div class="col-md-12 listings">
								            	<div class="w3l-table-info agile_info_shadow">
														<div class="col-md-3">
															<div class="form-group">															                                                  
			                                                    <div class="col-md-12 input-group">
																	<input class="form-control" id="transactionNo" name="transactionNo" maxlength="23" readonly="" type="text" value='<bean:write name="formVO"  property="transactionNo" />' >															
											                	</div>
			                                                 </div> 
			                                           </div> 
			                                           <div class="col-md-3">
															<div class="form-group">																 
			                                                    <div class="col-md-12 input-group">
			                                                        <input class="form-control" id="invoiceNo" name="invoiceNo" maxlength="23" readonly="" type="text" value='<bean:write name="formVO"  property="invoiceNo" />' >
			                                                    </div> 
			                                                 </div> 
			                                           </div>  
			                                           <div class="col-md-4">
															<div class="form-group">																 
			                                                    <div class="col-md-12">
																	<input class="btn btn-primary" id="uploadInvoiceFile" property="uploadInvoiceFile" type="file" name="formVO"></input>			                                                    
																</div> 
			                                                 </div> 
			                                           </div>                                                                                     
			                                           <div class="col-md-2">
															<div class="form-group">
																<div class="col-md-12">
																	<button type="button" class="btn btn-primary" onclick="saveInvoiceForm();">Save</button>
																</div> 
			                                                </div> 
			                                           </div> 
												</div>
											</div>
																											
											<div class="col-md-12 listings">
								            	<div class="w3l-table-info agile_info_shadow">
								            		<div class="col-md-12">
														  <div class="col-md-12">
														  	<logic:present name="productList"><logic:notEmpty name="productList"><table id="table"></table></logic:notEmpty></logic:present>
			                                              </div>                                                                                                 
			                                        </div>                                       
												</div>
											</div>
									</div>
				
			</div><!-- /container-->
		</div><!-- //inner_content-->
		
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
        Check the hidden field for values!
    </div>
</div>
</form>
</body>
</html>