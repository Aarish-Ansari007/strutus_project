<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>


<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Add Kitchen Vendor</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="application/x-javascript"> 
	//addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
	//function hideURLbar(){ window.scrollTo(0,1); } 	
	
	
</script>

<script type="text/javascript">		

	var tokenValue= '<%=request.getAttribute("TRACKERID") %>';
	
	function stateListEnableForBilling(){
		
		if(document.getElementById('billingCountryId').options[document.getElementById('billingCountryId').selectedIndex].value!='82')
	    {				  
			  var dropDown = document.getElementById("billingStateId");
		      dropDown.selectedIndex = 0;
	
			  document.getElementById('billingStateId').disabled=true;
	    }else{
			  document.getElementById('billingStateId').disabled=false;
	    } 
	}
	
	function stateListEnableForShipping(){
		
		if(document.getElementById('shippingCountryId').options[document.getElementById('shippingCountryId').selectedIndex].value!='82')
	    {				  
			  var dropDown = document.getElementById("shippingStateId");
		      dropDown.selectedIndex = 0;
	
			  document.getElementById('shippingStateId').disabled=true;
	    }else{
			  document.getElementById('shippingStateId').disabled=false;
	    } 
	}
	
	//function hideURLbar(){ window.scrollTo(0,1); } 
	
	// disable the backward-forward button of browser          
	function disableKeys(){
			window.history.forward(); 		
	}
	
	function setShippingAddress(){
		document.getElementById('shippingAddress').value=document.getElementById('billingAddress').value;		
	}
	
	function setShippingAddressCity(){
		document.getElementById('shippingCityId').value=document.getElementById('billingCityId').value;
	}
	
	function setShippingAddressPIN(){
		document.getElementById('shippingPinCode').value=document.getElementById('billingPinCode').value;
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
									
									<li>Add Kitchen Vendor</li>
									<li class="pull-right section-head"><a href="inventoryKitchenManagement.do?methodName=getKitchenManagementStore">Kitchen Store</a></li>
									<li class="pull-right section-head"><a href="inventoryKitchenManagement.do?methodName=getkitchenManagement"> Back</a></li>
								</ul>
							</div>
						</div>	
                     <!-- /filter-->             		
					
                     <!-- /filter-->
					
				<div class="col-md-12 listings">
					            <div class="w3l-table-info agile_info_shadow mrgn-tops">
										 <h3 class="w3_inner_tittle two pull-left">Add Kitchen Vendor</h3>
								                <div class="form-body">
												<form>
												
													 <bean:define id="tempKitchenFormVO" name="kitchenFormVO" type="com.indianmesh.inventory.kitchen.vendor.VendorFormVO"></bean:define>               
               										 <html:hidden property="vendorId" name="tempKitchenFormVO"/>
               										 
													<div class="col-md-6">																										       													
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Name Of Company: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="name" maxlength="35" placeholder="" id="name" value="" type="text">
																</div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-12 control-label">Type:</label>
                                                                <div class="col-md-12">  	
																	<html:select property="type" styleId="type" styleClass="selectBoxTag" style="width:125px" name="tempKitchenFormVO">
																		<html:option value="0">----SELECT----</html:option>
																		<html:options collection="typeList" property="key" labelProperty="value" name="tempKitchenFormVO"></html:options>
																	</html:select>																
																</div>  
                                                             </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Phone Number: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="phoneNo" maxlength="10" placeholder="" id="phoneNo" value="" type="text">
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Email Id: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="emailId" maxlength="256" placeholder="" id="emailId" value="" type="text">
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Tin: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="tin" maxlength="11" placeholder=""  id="tin" value="" type="text">
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Vat: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="vatNo" maxlength="9" placeholder=""  id="vatNo" value="" type="text">
																</div>
                                                            </div>															
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Pan Card: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="pan" maxlength="10" placeholder=""  id="pan" value="" type="text">
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Gst In: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="gst" maxlength="15" placeholder=""  id="gst" value="" type="text">
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Service Tax No: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="serviceTaxNo" maxlength="15" placeholder=""  id="serviceTaxNo" value="" type="text">
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Contact Person: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="contactPerson" maxlength="50" placeholder=""  id="contactPerson" value="" type="text">
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Contact Phone: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="contactPhone" maxlength="10" placeholder="" id="contactPhone" value="" type="text">
																</div>
                                                            </div>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Contact Email: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="contactEmail" placeholder="" id="contactEmail" value="" type="text">
																</div>
                                                            </div>
                                                            
													</div>
													<div class="col-md-6">		
													        
															<div class="biling-address">
															<h3>Billing Address</h3>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Address:</label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="billingAddress" maxlength="150" placeholder="" id="billingAddress" value="" type="text" onblur="setShippingAddress();">
																</div>
                                                             </div>
														    <div class="form-group">
                                                                <label class="col-md-12 control-label">Country:</label>
                                                                <div class="col-md-12">   
                                                                <script type="application/x-javascript"> 
                                                                 function setShippingAddressCountry(){
																		var elementsBillingCountry = document.getElementById("billingCountryId");		
																		var elementsShippingCountry = document.getElementById("shippingCountryId");	
																		var elementsBillingState = document.getElementById("billingStateId"); 
																		var elementsShippingState = document.getElementById("shippingStateId");
																		
																		var length=elementsBillingCountry.length;																		
																
																		for(var i=0;i<length;i++){
																			
																			var oOption = document.createElement("OPTION");
																			oOption.text = document.getElementById('billingCountryId').options[i].text;
																			oOption.value = document.getElementById('billingCountryId').options[i].value;
																			
																			if(i!=0){
																				if(!elementsBillingCountry.options[i].selected){
																					if(elementsBillingCountry.options[i].text != 'India'){																																																														
																						//elementsBillingState.disabled="true";
																						elementsShippingCountry.disabled="true";						
																						elementsShippingState.disabled="true";
																						elementsShippingCountry.options.add(oOption);
																						continue;
																					}else{
																						//elementsBillingState.disabled="true";
																						elementsShippingCountry.disabled="true";						
																						elementsShippingState.disabled="true";																		
																						elementsShippingCountry.options.add(oOption);																						
																						break;
																					}						
																				}else{																					
																					if(elementsBillingCountry.options[i].text != 'India'){																																			
																						//elementsBillingState.disabled="true";
																						elementsShippingCountry.disabled="true";						
																						elementsShippingState.disabled="true";
																						oOption.selected='true';
																						oOption.selected='selected';
																						elementsShippingCountry.options.add(oOption);	
																						continue;
																					}else{
																						//elementsBillingState.disabled="false";
																						elementsShippingCountry.disabled="true";						
																						elementsShippingState.disabled="true";																		
																						oOption.selected='true';
																						oOption.selected='selected';
																						elementsShippingCountry.options.add(oOption);																						
																						break;
																					}																					
																				}
																			}else{
																				if(elementsBillingCountry.options[i].selected){
																					oOption.selected='true';
																					oOption.selected='selected';
																					elementsShippingCountry.options.add(oOption);
																					continue;
																				}else{
																					elementsShippingCountry.options.add(oOption);
																					continue;
																				}
																			}
																		}																													
                                                                 	}
                                                                </script>        																														
																<html:select property="billingCountryId" styleId="billingCountryId" styleClass="selectBoxTag" onchange="stateListEnableForBilling();" style="width:125px" name="tempKitchenFormVO" onchange="setShippingAddressCountry();">
																			<html:option value="0">----SELECT----</html:option>
																			<html:options collection="nationalityList1" property="key" labelProperty="value" name="tempKitchenFormVO"></html:options>																			                                                                                                                         
																</html:select>																														
																																																	
																</div>  
                                                             </div>
															 <div class="form-group">
                                                                <label class="col-md-12 control-label">State:</label>
                                                                <div class="col-md-12">
                                                                <script type="application/x-javascript">                                                            
																	function setShippingAddressState(){
																		var elementsBillingCountry=document.getElementById("billingCountryId");
																		var elementsBillingState=document.getElementById("billingStateId");		
																		var elementsShippingState=document.getElementById("shippingStateId");		
																		var length=elementsBillingState.length;
																																		
																		for(var i=0;i<length;i++){
																			
																			var oOption=document.createElement("OPTION");
																			oOption.text=document.getElementById('billingStateId').options[i].text;
																			oOption.value=document.getElementById('billingStateId').options[i].value;
																			
																			if(i!=0){
																				if(elementsBillingState.options[i].selected){
																					oOption.selected='true';
																					oOption.selected='selected';																							
																				}
																			}
																			elementsShippingState.options.add(oOption);
																		}
																		
																		/* var elements=document.getElementById("billingCountryId");
																		
																		for(var i=0;i<elementsBillingState.length;i++){																			
																			if(elementsBillingState[i].selected){
																				if(elements.options.text=='India'){
																					elementsBillingState.options[0].selected;
																					elementsShippingState.options[0].selected;
																					elementsBillingState.disabled="true";
																					elementsShippingState.disabled="true";
																				}
																			}																
																		} */	
																	}																																	
                                                                </script>             																
 																<html:select property="billingStateId" styleId="billingStateId" styleClass="selectBoxTag" style="width:125px" name="tempKitchenFormVO" onchange="setShippingAddressState();">
																			<html:option value="0">----SELECT----</html:option>
																			<html:options collection="stateList1" property="key" labelProperty="value" name="tempKitchenFormVO"></html:options>
																</html:select>
																</div>  
                                                             </div>
															 <div class="form-group">
                                                                <label class="col-md-12 control-label">City:</label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="billingCityId" maxlength="20" placeholder="" id="billingCityId" value="" type="text" onblur="setShippingAddressCity();">
																</div>
                                                             </div>
															  <div class="form-group">
                                                                <label class="col-md-12 control-label">Pincode:</label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="billingPinCode" maxlength="6" placeholder="" id="billingPinCode" value="" type="text" onblur="setShippingAddressPIN();">
																</div>
                                                             </div>
															</div>
															
															<div class="biling-address">
															<h3>Shipping Address</h3>
															<div class="form-group">
                                                                <label class="col-md-12 control-label">Address:</label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" readonly="" name="shippingAddress" maxlength="150" placeholder="" id="shippingAddress" value="" type="text">
																</div>
                                                             </div>
														  <div class="form-group">
                                                                <label class="col-md-12 control-label">Country:</label>
                                                                <div class="col-md-12">            
																		<html:select property="shippingCountryId" styleId="shippingCountryId" styleClass="selectBoxTag" disabled="true" onchange="stateListEnableForShipping();" style="width:125px" name="tempKitchenFormVO">
																			<html:option value="0">----SELECT----</html:option>
																			<%--<html:options collection="nationalityList2" property="key" labelProperty="value" name="tempKitchenFormVO"></html:options> --%>
																			
																		</html:select>
																  </div>
                                                             </div>
															 <div class="form-group">
                                                                <label class="col-md-12 control-label">State:</label>
                                                                <div class="col-md-12">            
 																	    <html:select property="shippingStateId" styleId="shippingStateId" styleClass="selectBoxTag" disabled="true" style="width:125px" name="tempKitchenFormVO">
																			<html:option value="0">----SELECT----</html:option>
																			<%--<html:options collection="stateList2" property="key" labelProperty="value" name="tempKitchenFormVO"></html:options> --%>
																		</html:select>
																 </div>
                                                             </div>
															 <div class="form-group">
                                                                <label class="col-md-12 control-label">City:</label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="shippingCityId" readonly="" maxlength="20" placeholder="" id="shippingCityId" value="" type="text">
																</div>
                                                             </div>
															  <div class="form-group">
                                                                <label class="col-md-12 control-label">Pincode:</label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" name="shippingPinCode" readonly="" maxlength="6" placeholder="" id="shippingPinCode" value="" type="text">
																</div>
                                                             </div>
															</div>														
															<div class="form-group">
															<div class="col-md-12">
															 <script type="text/javascript">		
															
															function trim(s){
															    return s.replace(/^\s+|\s+$/,''); 
															}
															
															function saveKitchenVandorDetails(){
																
																var pattern1=/^([0-9]+)$/;
																var pattern2=/^([^A-Za-z_]+)$/;
																var pattern3=/^[A-Za-z]+$/; 
																
																// Name of the company
																if(document.getElementById("name").value==""){
																   alert("Company name is Mandatory, Please enter the name of the company.");
																   document.getElementById('name').value='';
																   document.getElementById("name").focus();
																   return; 
																}
																
																var name=document.getElementById("name").value;
																if(name!=""){
																	if(document.getElementById('name').value.length > 35){  
																         alert("Vendor Name should not be greater than 35 characters long.");																      
																         document.getElementById('name').value='';
																         document.getElementById("name").focus();
																         return;
																    }
															 	}
																
																if(document.getElementById('name').value.length > 35)  
															    {  
															        alert('The length of name should be less 35 character long.');  
															        document.getElementById('name').value=''; 
															        document.getElementById("name").focus();
															        return;  
															    } 
																
																var elements = document.getElementById("type");
																for(var i=0; i<elements.length; i++){
																	if(i==0){
																		if(elements[i].selected){
																			alert("Please select type of vendor.");
																			document.getElementById("type").focus();
																			return; 
																		}else{
																			break;
																		}																		
																	}																    
																}			
																
																// phoneNo of the company
																if(document.getElementById("phoneNo").value==""){
																   alert("Phone No is Mandatory, Please enter the phoneNo of the company.");
																   document.getElementById('phoneNo').value='';
																   document.getElementById("phoneNo").focus();
																   return; 
																}															
															 	
															 	var phoneNo=document.getElementById("phoneNo").value;
																if(phoneNo!=""){
																   if(!pattern1.test(phoneNo)){
																         alert("Phone No should have numari digits only.");
																         document.getElementById('phoneNo').value='';
																         document.getElementById("phoneNo").focus();
																         return;
																    }
															 	}
																
																if(document.getElementById('phoneNo').value.length != 10)  
															    {  
															        alert('Please enter your correct phone no. Phone number length should be 10 numiric character long.');  
															        document.getElementById('phoneNo').value=''; 
															        document.getElementById("phoneNo").focus();
															        return;  
															    }  
																
																// emailId of the company
															    if(document.getElementById("emailId").value==""){
																   //alert("EmailId is Mandatory, Please enter the emailId of the company.");
																  // document.getElementById('emailId').value='';
																   //document.getElementById("emailId").focus();
																  // return; 
																}else{
																
																	var emailId = document.getElementById("emailId").value;
																	if (emailId.indexOf("@") > 0){																	
																	}else{
																		alert("Please enter a valid emailId of the company.");
																		document.getElementById('emailId').value='';
																		document.getElementById("emailId").focus();
																		return; 
																	}
																	
																	if(document.getElementById("emailId").value==""){
																		var emailRegEx = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
																		str = document.getElementById("emailId").value;
																		if(!str.match(emailRegEx)){
																			   alert("Please enter a valid Email Address.");
																			   document.getElementById('emailId').value='';
																			   document.getElementById("emailId").focus();
																			   return ;  
																		}
																	} 
																}
																
																// tin of the company
																if(document.getElementById("tin").value==""){
																   //alert("Please enter the TIN.");
																   //document.getElementById('tin').value='';
																   //document.getElementById("tin").focus();
																   //return; 
																} else {																
																	if(document.getElementById('tin').value.length != 11){
																		alert("Please enter the valid TIN. TIN should be 11 character long.");
																		document.getElementById('tin').value='';
																		document.getElementById("tin").focus();
																		return; 
																	}
																	
																	var tin = document.getElementById('tin').value;
																	if(pattern1.test(tin)){																	
																	}else{
																		alert("Please enter the valid TIN. TIN should be numaric character only.");
																		document.getElementById('tin').value='';
																		document.getElementById("tin").focus();
																		return; 
																	}																	
																}
																
																// vatNo of the company
																if(document.getElementById("vatNo").value==""){
																  // alert("Please enter the vatNo.");
																  // document.getElementById('vatNo').value='';
																  // document.getElementById("vatNo").focus();
																  // return; 
																}else{																
																	if(document.getElementById('vatNo').value.length != 9){
																		alert("Please enter the valid VAT. VAT should be 9 character long.");
																		document.getElementById('vatNo').value='';
																		document.getElementById("vatNo").focus();
																		return; 
																	}
																	
																	var vat = document.getElementById('vatNo').value;
																	if(!pattern1.test(vat)){																																	
																		alert("Please enter the valid VAT. VAT should be numaric character only.");
																		document.getElementById('vatNo').value='';
																		document.getElementById("vatNo").focus();
																		return; 
																	} 
																}
															
																// pan of the company
																if(document.getElementById("pan").value==""){
																   alert("PAN No is Mandatory, Please enter the PAN .");
																   document.getElementById('pan').value='';
																   document.getElementById("pan").focus();
																   return; 
																}
																																														
															    if(document.getElementById('pan').value.length != 10)  
															    {  
															        alert('Please enter your correct PAN Card no. It should be alpha numaric with 10 character long.');  
															        document.getElementById('pan').value=''; 
															        document.getElementById("pan").focus();
															        return;  
															    }  															    															   
															
															    var pan = document.getElementById('pan').value;
															    var temPan1 = pan.substring(0, 5); //alphabet
															    var temPan2 = pan.substring(5, 9); //numari
															    var temPan3 = pan.substr(9); // alphabet
															    
															    if(pattern1.test(temPan2)){
															    	if(pattern3.test(temPan1)){
															    		if(pattern3.test(temPan3)){															    			
															    		}else{
																	    	alert('Please enter your correct pan Card no. PAN should contain alphabet character at 10th place.');  
																	        document.getElementById('pan').value=''; 
																	        document.getElementById("pan").focus();
																	        return;
																	    } 
															    	}else{
																    	alert('Please enter your correct pan Card no. PAN should contain alphabet character from 1st to 5th place.');  
																        document.getElementById('pan').value=''; 
																        document.getElementById("pan").focus();
																        return;
																    } 															    	
															    }else{
															    	alert('Please enter your correct pan Card no. PAN should contain numaric character from 6th to 9th place.');  
															        document.getElementById('pan').value=''; 
															        document.getElementById("pan").focus();
															        return;
															    } 															    																
															
																// gst of the company
																if(document.getElementById("gst").value==""){
																   alert("GST No is Mandatory, Please enter the GST .");
																   document.getElementById('gst').value='';
																   document.getElementById("gst").focus();
																   return; 
																}
																
																if(document.getElementById('gst').value.length != 15)  
															    {  
															        alert('Please enter your correct GST no. GST length should be 15 character long only.');  
															        document.getElementById('gst').value=''; 
															        document.getElementById("gst").focus();
															        return;  
															    }  
																																															
																var pan = document.getElementById('pan').value;
																var gst = document.getElementById('gst').value;
																var temSubGst1 = gst.substr(0, 2);
																var temSubGst2 = gst.substr(2, 10);
																//var temSubGst3 = gst.substr(11,1);
																var temSubGst4 = gst.substr(12,1);
																var temSubGst5 = gst.substr(13,1);
																var temSubGst6 = gst.substr(14,1);
																
																if(pattern1.test(temSubGst1)){
																	if(temSubGst2.includes(pan)){
																		if(pattern1.test(temSubGst4)){
																			if(pattern1.test(temSubGst6)){
																				if(temSubGst5.includes('z') || temSubGst5.includes('Z')){
																					if(gst.length != 15){
																						alert('Please enter your correct GST no. The length of GST should be 15 character long.');  
																				        document.getElementById('gst').value=''; 
																				        document.getElementById("gst").focus();
																				        return; 
																					}																				
																				}else{
																					alert('Please enter your correct GST no. GST no should contain Z at 14th position in GST.');  
																			        document.getElementById('gst').value=''; 
																			        document.getElementById("gst").focus();
																			        return;  
																				}
																			}else{
																				alert('Please enter your correct GST no. The 15th place should be numeric character in GST.');  
																		        document.getElementById('gst').value=''; 
																		        document.getElementById("gst").focus();
																		        return;  
																			}
																		}else{
																			alert('Please enter your correct GST no. The 13th place should be numeric character in GST.'); 
																	        document.getElementById('gst').value=''; 
																	        document.getElementById("gst").focus();
																	        return;  
																		}
																	}else{
																		alert('Please enter your correct GST no. The 3rd to 12th place should be occupied with PAN.');  
																        document.getElementById('gst').value=''; 
																        document.getElementById("gst").focus();
																        return;  
																	}																	
																}else{
																	alert('Please enter your correct GST no. The first 2 digits should be numaric state code in GST.');  
															        document.getElementById('gst').value=''; 
															        document.getElementById("gst").focus();
															        return;  
																}																																													
																
																// serviceTaxNo of the company
																if(trim(document.getElementById("serviceTaxNo").value)==""){
																  // alert("Service Tax No is Mandatory, Please enter the Service Tax No .");
																  // document.getElementById('serviceTaxNo').value='';
																  // document.getElementById("serviceTaxNo").focus();
																  // return; 
																}else{
																
																	if(document.getElementById('serviceTaxNo').value.length != 15)  
																    {  
																        alert('Please enter your correct service tax no. service tax no length should be 15 only.');  
																        document.getElementById('serviceTaxNo').value=''; 
																        document.getElementById("serviceTaxNo").focus();
																        return;  
																    } 
																	
																	var pan = document.getElementById('pan').value;
																	var serviceTaxNo = document.getElementById('serviceTaxNo').value;
																	var tempStr1 = serviceTaxNo.substr(0, 10);
																	var tempStr2 = serviceTaxNo.substr(10, 5);
																	
																	if(tempStr1.includes(pan)){
																		if(pattern1.test(tempStr2)){
																			if(serviceTaxNo.length != 15){																		
																				alert('Please enter your correct service tax no. Service tax no should be 15 character long');  
																		        document.getElementById('serviceTaxNo').value=''; 
																		        document.getElementById("serviceTaxNo").focus();
																		        return;  
																			}
																		}else{
																			alert('Please enter your correct service tax no. From 11th to 15the characters should be numaric in Service Tax No.');  
																	        document.getElementById('serviceTaxNo').value=''; 
																	        document.getElementById("serviceTaxNo").focus();
																	        return;  
																		}																
																	}else{
																		alert('Please enter your correct service tax no. From 1st to 10the characters should be PAN in Service Tax No.');  
																        document.getElementById('serviceTaxNo').value=''; 
																        document.getElementById("serviceTaxNo").focus();
																        return;  
																	} 
																}
																																															
																// contactPerson of the company
																if(document.getElementById("contactPerson").value==""){
																  // alert("Contact Person is Mandatory, Please enter the Contact Person .");
																  // document.getElementById('contactPerson').value='';
																  // document.getElementById("contactPerson").focus();
																  // return; 
																}else{
																
																if(document.getElementById('contactPerson').value.length > 50)  
															    {  
															        alert('Contact Person name should not be more than 50 character length long.');  
															        document.getElementById('contactPerson').value=''; 
															        document.getElementById("contactPerson").focus();
															        return;  
															    } 
																
																// contactPhone of the company
																if(document.getElementById("contactPhone").value==""){
																   alert("Contact Phone No is Mandatory, Please enter the Contact Phone .");
																   document.getElementById('contactPhone').value=''; 
																   document.getElementById("contactPhone").focus();
																   return; 
																} 
																
																if(document.getElementById('contactPhone').value.length != 10)  
															    {  
															        alert('Please enter your correct contact phone no.');  
															        document.getElementById('contactPhone').value=''; 
															        document.getElementById("contactPhone").focus();
															        return;  
															    }  
																
																var contactPhone=document.getElementById("contactPhone").value;
																if(phoneNo!=""){
																   if(!pattern1.test(contactPhone)){
																         alert("Phone No should have numari digits only.");
																         document.getElementById('contactPhone').value='';
																         document.getElementById("contactPhone").focus();
																         return;
																    }
															 	}
															}
																
																// contactEmail of the company
																if(document.getElementById("contactEmail").value==""){
																   //alert("Contact Email is Mandatory, Please enter the Contact Email .");
																   //document.getElementById('contactEmail').value='';
																   //document.getElementById("contactEmail").focus();
																   //return; 
																}else{
																
																	var contactEmail = document.getElementById("contactEmail").value;
																	if (contactEmail.indexOf("@") > 0){																	
																	}else{
																		alert("Please enter a valid emailId of the contact persone.");
																		document.getElementById('contactEmail').value='';
																		document.getElementById("contactEmail").focus();
																		return; 
																	}
																	
																	var emailRegEx = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
																	var str = document.getElementById("contactEmail").value;
																	if(!str.match(emailRegEx)){
																		alert("Please enter a valid emailId of the contact persone.");
																		document.getElementById('contactEmail').value='';
																		document.getElementById("contactEmail").focus();
																		return;  
																	}
																
																}
																
																// billingAddress of the company
																if(document.getElementById("billingAddress").value==""){
																  alert("Billing Address is Mandatory, Please enter the Billing Address .");
																  document.getElementById('billingAddress').value='';
																  document.getElementById("billingAddress").focus();
																  return; 
															
																	if((document.getElementById('billingAddress').value.length < 20) 
																		|| (document.getElementById('billingAddress').value.length > 150))
																    {  
																        alert('The length of billingAddress should be greater than 50 charatet and less 150 character.');  
																        document.getElementById('billingAddress').value=''; 
																        document.getElementById("billingAddress").focus();
																        return;  
																    } 																
																}
																																																
																var elements = document.getElementById("billingCountryId");
																for(var i=0; i < elements.length ; i++){
																	if(i==0){
																		if(elements[i].selected){
																			alert("Please select the billing country from the drop down list.");
																			document.getElementById("billingCountryId").focus();
																			return; 
																		}else{
																			break;
																		}																		
																	}																    
																}																																
																																																
																var elements = document.getElementById("billingStateId");
																for(var i=0; i < elements.length ; i++){
																	if(i==0){
																		if(elements[i].selected){
																			alert("Please select the billing state from the drop down list.");
																			document.getElementById("billingStateId").focus();
																			return; 
																		}else{
																			break;
																		}																		
																	}																    
																}		
																
																// shippingCityId of the company
																if(document.getElementById("billingCityId").value==""){
																   //alert("Billing City is Mandatory, Please enter the Billing City.");
																   //document.getElementById('billingCityId').value='';
																   //document.getElementById("billingCityId").focus();
																   //return; 
																} else{
																
																if(document.getElementById('billingCityId').value.length > 21)  
															    {  
															        alert('Please enter your correct billing city. Billing city name should not be greater than 20 character long.');  
															        document.getElementById('billingCityId').value=''; 
															        document.getElementById("billingCityId").focus();
															        return;  
															    } 
																
																
																
																// billingPinCode of the company
																if(document.getElementById("billingPinCode").value==""){
																   alert("Billing Pin Code No is Mandatory, Please enter the Billing Pin Code .");
																   document.getElementById('billingPinCode').value='';
																   document.getElementById("billingPinCode").focus();
																   return; 
																}																
																	if(document.getElementById('billingPinCode').value.length != 6)  
																    {  
																        alert('Please enter your correct billing pin code no.');  
																        document.getElementById('billingPinCode').value=''; 
																        document.getElementById("billingPinCode").focus();
																        return;  
																    }  																
																}
																	
																// shippingAddress of the company
																if(document.getElementById("shippingAddress").value==""){
																  alert("Shipping Address is Mandatory, Please enter the Shipping Address .");
																  document.getElementById('shippingAddress').value='';
																  document.getElementById("shippingAddress").focus();
																  return; 
																} 																															
																
																var len = document.getElementById('shippingAddress').value.length;
																if((len < 20) || (len > 150))
															    {  

															        alert('Please enter your correct shipping address. Shipping address length should not be greater than 50 character long.');  
															        document.getElementById('shippingAddress').value=''; 
															        document.getElementById("shippingAddress").focus();
															        return;  
															    } 																															
																																																
																var elements = document.getElementById("shippingCountryId");
																for(var i=0; i < elements.length ; i++){
																	if(i==0){
																		if(elements[i].selected){
																			alert("Please select the shipping country from the drop down list.");
																			document.getElementById("shippingCountryId").focus();
																			return; 
																		}else{
																			break;
																		}																		
																	}																    
																}		
																
																var elements = document.getElementById("shippingStateId");
																for(var i=0; i < elements.length ; i++){
																	if(i==0){
																		if(elements[i].selected){
																			alert("Please select the shipping state from the drop down list.");
																			document.getElementById("shippingStateId").focus();
																			return; 
																		}else{
																			break;
																		}																		
																	}																    
																}		
																
																// shippingCityId of the company
																if(document.getElementById("shippingCityId").value==""){
																   alert("Shipping City is Mandatory, Please enter the Shipping City.");
																   document.getElementById('shippingCityId').value=''; 
																   document.getElementById("shippingCityId").focus();
																   return; 
																}
																
																if(document.getElementById('shippingCityId').value.length > 21)  
															    {  
															        alert('Please enter your correct shipping city. Shipping city length should not be greater than 20 character long.');  
															        document.getElementById('shippingCityId').value=''; 
															        document.getElementById("shippingCityId").focus();
															        return;  
															    } 
																
																// shippingPinCode of the company
																if(document.getElementById("shippingPinCode").value==""){
																   alert("Shipping Pin Code is Mandatory, Please enter the Shipping Pin Code .");
																   document.getElementById('shippingPinCode').value='';
																   document.getElementById("shippingPinCode").focus();
																   return; 
																}
																
																if(document.getElementById('shippingPinCode').value.length != 6)  
															    {  
															        alert('Please enter your correct shipping pin code no.');  
															        document.getElementById('shippingPinCode').value=''; 
															        document.getElementById("shippingPinCode").focus();
															        return;  
															    } 
																
																
																var status = window.confirm("Do you want to save kitchen vendor registration details");
																if(status){						
																	document.forms[0].action="kitchenVendor.do?methodName=updateKitchenVendor";
																	document.forms[0].submit();
																}
															}	
															
															// close Form
															function closeForm(){
																var status = window.confirm("All the entered details in form will be lost. Do you wish to close the form");
																var vendorId = document.getElementById('vendorId');
																if(status){
																	
																	document.forms[0].action="kitchenVendor.do?methodName=deleteKitchenVendor&vendorId="+vendorId;
																	document.forms[0].submit();
																}
															}
															
															</script>			
															 <button type="button" class="btn btn-primary" onclick="saveKitchenVandorDetails();">Save</button>															 															 												 
															 <button type="button" class="btn btn-primary" onclick="closeForm();">Close</button>

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
        Check the hidden field for values!
    </div>
</div>
			</form>
</body>
</html>