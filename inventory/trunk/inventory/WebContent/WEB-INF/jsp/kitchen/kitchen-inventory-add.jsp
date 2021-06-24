<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<title>inventory product</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 
	var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>';	
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
										 <h3 class="w3_inner_tittle two pull-left">Add inventory Product</h3>
								                <div class="form-body">
												
												<bean:define id="formVOO" name="compositProductVO" type="com.indianmesh.inventory.kitchen.inventory.ProductFormVO"></bean:define>               
               										<html:hidden property="productId" name="formVOO"/> 
               										
													<div class="col-md-6">												
													       <div class="form-group">
                                                                <label class="col-md-12 control-label">Product Name: </label>
                                                                <div class="col-md-12">
                                                                    <input class="form-control" id="name" name="name" value="" placeholder="" styleClass="textBox" maxlength="20" size="63" >
																</div>
                                                            </div>															
                                                            <div class="form-group">
                                                                <label class="col-md-4 control-label">Product Type: </label>
                                                                <label class="col-md-4 control-label">Serving Qty: </label>
                                                                <label class="col-md-4 control-label">Qty Type: </label>
                                                                <div class="col-md-4">            																	
																	<html:select property="productTypeId" styleId="productTypeId" styleClass="selectBoxTag" style="width:125px" name="formVOO">
																		<html:option value="0">----SELECT----</html:option>
																		<html:options collection="productTypeList" property="key" labelProperty="value" name="formVOO"></html:options>
																	</html:select>
																</div>
																<div class="col-md-4">            																	
																	<input id="quantityId" name="quantityId" value="" placeholder="" styleClass="textBox" maxlength="10" size="12" onblur="distributeServingQuantity();" />
																</div>
																<div class="col-md-4">            																	
																	<html:select property="quantityTypeId" styleId="quantityTypeId" styleClass="selectBoxTag" style="width:95px" name="formVOO">
																		<html:option value="">----SELECT----</html:option>
																		<html:options collection="quantityTypeList" property="key" labelProperty="value" name="formVOO"></html:options>
																	</html:select>
																</div>
                                                            </div>  
                                                            <div class="form-group">
                                                                <label class="col-md-12 control-label">Description:</label>                                                              
                                                                <div class="col-md-12">
                                                                    <textarea class="form-control" id="description" name="description" value="" placeholder="" styleClass="textBox" maxlength="256" size="63"></textarea>
                                                                </div>
                                                            </div>																																															                                                           
												    </div>
																																					
													<div class="col-md-6">													      
                                                          <div class="col-md-12">                                                                    
	                                                            <div class="col-md-12">
	                                                            <script type="text/javascript">
	                                                            	function distributeServingQuantity(){
	                                                        			//alert("distributeServingQuantity2");

	                                                            		var pattern1=/^([0-9.]+)/;
																		var pattern2=/^([^A-Za-z_]+)$/;
																		var len = document.getElementById('inventoryProductTab').rows.length;	
																		var tempVal = 0;
																		var servingQuantityVal = document.getElementById("quantityId").value;
																		
																		if(len>=1){
																			for(var i=0;i<len;i++){																				
																				if(servingQuantityVal!=""){
																				   if(!pattern1.test(servingQuantityVal)){
																				       alert("Product quantity should be only Digits.");
																				       document.getElementById('inventoryProductQuantityId'+i).select();
																				       return;
																				    }																				   
																				   else {
																					  // document.getElementById('inventoryProductQuantityId'+i).value = ((servingQuantityVal)/(len));
																				   }
																			 	} else {
																			 		//document.getElementById('inventoryProductQuantityId'+i).value = ((servingQuantityVal)/(len));
																			 	}																			
																			}
																		}
																	}
																	
																</script> 																																		  																	
																<div class="col-md-12">
																	<div class="form-group">
																		 <label class="col-md-6 control-label">Product Ingredients:</label>            																	
		                                                                 <label class="col-md-1 control-label apend-div"><i class="fa fa-plus-circle" aria-hidden="true" onclick="addInventoryProductIngredients()"></i></label>
		                                                                 <label class="col-md-1 control-label apend-div"><i class="fa fa-minus-circle" aria-hidden="true" onclick="removeInventoryProductRow('inventoryProductTab',2)"></i></label>
	                                                                 </div>
																</div>														 
															</div>
													 </div>
													<div id="ingred" class="col-md-12 listings">
					            								<div class="w3l-table-info agile_info_shadow"> 
											  		<table id="inventoryProductTab">											 												 																																	  			                                                                	
	                                                                   <script type="text/javascript">																
																		function addInventoryProductIngredients(){
																			//document.getElementById('ingred').disabled=false;
																			var table = document.getElementById('inventoryProductTab');
																			var numberOfRows = table.rows.length;
																			var newRow = table.insertRow(numberOfRows);
																		    var newCell0 = newRow.insertCell(0);
																			var newCell1 = newRow.insertCell(1);
																			var newCell2 = newRow.insertCell(2);
																				
																			newCell0.align="center";
																			newCell1.align="center";
																			newCell2.align="center";
																																	
																			newCell0.className="formlabel";
																			newCell1.className="formlabel";
																			newCell2.className="formlabel";
																																														
																			var newSelectElement1 = document.createElement("select");
																				newSelectElement1 = newCell0.appendChild(newSelectElement1);
																				newSelectElement1.id = "inventoryProductId"+(numberOfRows);
																				newSelectElement1.style.width="127px";
																				newSelectElement1.name="inventoryProductId"+(numberOfRows);
																			var inventoryItemSelectBox=document.getElementById('inventoryProductId');
																		 	var inventoryItemSelectBoxLength=inventoryItemSelectBox.length;
																		 		for(var i=0;i<inventoryItemSelectBoxLength;i++){ 
																		 	        var oOption = document.createElement("OPTION");
																					oOption.text =document.getElementById('inventoryProductId').options[i].text;										
																					oOption.value =document.getElementById('inventoryProductId').options[i].value;
																					
																					newSelectElement1.options.add(oOption);
																				}
					
																		 		newSelectElement1.addEventListener('change', function(){
																					newSelectElement1.disabled="true";
																			});
												
																			//cell2
																			var newTextElement2 = document.createElement("INPUT");
																				newTextElement2 = newCell1.appendChild(newTextElement2);//changed
																				newTextElement2.id = "inventoryProductQuantityId"+(numberOfRows);
																				newTextElement2.name = "inventoryProductQuantityId"+(numberOfRows);
																				/* if(strTraineesName!=null){
																					newTextElement2.value = strTraineesName;
																				} */
																				newTextElement2.type = "text";
																				newTextElement2.align = "center";
																				newTextElement2.size= "12";
																				//newTextElement2.disabled = "true";
																				newTextElement2.addEventListener('click', function(){
																						var pattern1=/^([0-9.]+)$/;
																						var pattern2=/^([^A-Za-z_]+)$/;
																						var len = document.getElementById('inventoryProductTab').rows.length;	
																						var tempVal = 0;
																						var servingQuantityVal = document.getElementById("quantityId").value;
																						
																						if(len>=1){
																							for(var i=0;i<len;i++){																								
																								if(servingQuantityVal!=""){
																								   if(!pattern1.test(servingQuantityVal)){
																								       alert("Product quantity should be only Digits.");
																								       document.getElementById('inventoryProductQuantityId'+i).select();
																								       return;																							   
																								    } else {
																								    	//document.getElementById('inventoryProductQuantityId'+i).value = ((servingQuantityVal)/(len));
																								    }
																							 	} else {
																							 		//document.getElementById('inventoryProductQuantityId'+i).value = ((servingQuantityVal)/(len));
																							 	}																								
																							}
																						}
																				});	
																				
																				var pattern1=/^([0-9.]+)$/;
																				var pattern2=/^([^A-Za-z_]+)$/;
																				var len = document.getElementById('inventoryProductTab').rows.length;	
																				var tempVal = 0;
																				var servingQuantityVal = document.getElementById("quantityId").value;
																				
																				if(len>=1){
																					for(var i=0;i<len;i++){																								
																						if(servingQuantityVal!=""){
																						   if(!pattern1.test(servingQuantityVal)){
																						       alert("Product quantity should be only Digits.");
																						       document.getElementById('inventoryProductQuantityId'+i).select();
																						       return;																							   
																						    } else {
																						    	//document.getElementById('inventoryProductQuantityId'+i).value = ((servingQuantityVal)/(len));
																						    }
																					 	} else {
																					 		//document.getElementById('inventoryProductQuantityId'+i).value = ((servingQuantityVal)/(len));
																					 	}																								
																					}
																				}
																			
																				//cell 3
																				var newSelectElement3 = document.createElement("select");
																					newSelectElement3 = newCell2.appendChild(newSelectElement3);
																					newSelectElement3.id = "inventoryProductQuantityTypeId"+(numberOfRows);
																					newSelectElement3.style.width="127px";
																					newSelectElement3.name="inventoryProductQuantityTypeId"+(numberOfRows);
																				var inventoryItemTypeSelectBox=document.getElementById('inventoryProductQuantityTypeId');
																			 	var inventoryItemTypeSelectBoxLength=inventoryItemTypeSelectBox.length;
																			 		for(var i=0;i<inventoryItemTypeSelectBoxLength;i++){ 
																			 	        var oOption = document.createElement("OPTION");
																						oOption.text = document.getElementById('inventoryProductQuantityTypeId').options[i].text;										
																						oOption.value = document.getElementById('inventoryProductQuantityTypeId').options[i].value;																				
																						newSelectElement3.options.add(oOption);
																					}
						
																			 		newSelectElement3.addEventListener('change', function(){
																						newSelectElement3.disabled="true";
																				});
						
																			 }
																		 
																			function removeInventoryProductRow(tableid,length){
																				var table = document.getElementById(tableid);
																				if(table.rows.length>length){
																					table.deleteRow(table.rows.length - 2);
																				}
																				
																				var pattern1=/^([0-9.]+)$/;
																				var pattern2=/^([^A-Za-z_]+)$/;
																				var len = document.getElementById('inventoryProductTab').rows.length;	
																				var tempVal = 0;
																				var servingQuantityVal = document.getElementById("quantityId").value;
																				
																				if(len>=1){
																					for(var i=0;i<len;i++){																								
																						if(servingQuantityVal!=""){
																						   if(!pattern1.test(servingQuantityVal)){
																						       alert("Product quantity should be only Digits.");
																						       document.getElementById('inventoryProductQuantityId'+i).select();
																						       return;																							   
																						    } else {
																						    	//document.getElementById('inventoryProductQuantityId'+i).value = ((servingQuantityVal)/(len));
																						    }
																					 	} else {
																					 		//document.getElementById('inventoryProductQuantityId'+i).value = ((servingQuantityVal)/(len));
																					 	}																								
																					}
																				}
																			}
																			
																			
																			function getProductTypeList(){	
																				
																				var url = "kitchenInventoryProduct.do?methodName=getProductTypeList"; 
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
																  	   </script>															  	  	                                                                
	                                                                 <tr style="display:none"><td>
	                                                                  <div class="col-md-12" id="inventoryProductDiv" > 
																		 <div class="col-md-4">															 																
																			 <html:select property="inventoryProductId" styleId="inventoryProductId" styleClass="selectBoxTag" style="width:138px" name="formVOO">
																					<html:option value="">----SELECT----</html:option>
																					<html:options collection="inventoryProductList" property="key" labelProperty="value" name="formVOO"></html:options>
																			 </html:select>																 
																		 </div>
																		 <div class="col-md-4">
	                                                                    	<html:text property="inventoryProductQuantityId" styleId="inventoryProductQuantityId" maxlength="5" size="6" styleClass="textBox" name="formVOO"/> 
																	     </div>
																		 <div class="col-md-4">	
																		 	 <html:select property="inventoryProductQuantityTypeId" styleId="inventoryProductQuantityTypeId" styleClass="selectBoxTag" style="width:110px" name="formVOO">
																				<html:option value="">----SELECT----</html:option>
																				<html:options collection="ingredientQuantityTypeList" property="key" labelProperty="value" name="formVOO"></html:options>
																		     </html:select>	
																		 </div>														 																	 																	 																	 
																	   </div>	
																	 </td></tr>														 
														</table>
														</div></div>														 																	 																	 																	 
																	  																		
														<div class="form-group">
															<div class="col-md-12">
																<script type="text/javascript">																														 
																				    var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;															
																					
																					// trime
																					function trim(s){
																					    return s.replace(/^\s+|\s+$/,''); 
																					}
																
																					function addInventoryProduct(){
																				
																					var pattern1=/^([0-9]+)$/;
																					var pattern2=/^([^A-Za-z_]+)$/;
																					var pattern3=/^[0-9.,]+$/;
																					
																					// Name of the product
																					if(trim(document.getElementById("name").value)==""){
																					   alert("Product name is Mandatory, Please enter the name of the product.");
																					   document.getElementById("name").focus();
																					   return; 
																					}
																					
																					var name=document.getElementById("name").value;
																					if(name!=""){
																						if(document.getElementById('name').value.length > 20){  
																					        alert("Product Name should not be greater than 20 characters long.");	
																					        document.getElementById("name").select();
																					        return;
																					    }
																				 	}
																					
																					if(document.getElementById('name').value.length > 20)  
																				    {  
																				        alert('The length of name should not be greater than 21 character long.');  
																				        document.getElementById('name').value=''; 
																				        document.getElementById("name").select();
																				        return;  
																				    } 
																																																															
																					// productType of the company																																					
																					var elements = document.getElementById("productTypeId");
																					for(var i=0; i < elements.length ; i++){
																						if(i==0){
																							if(elements[i].selected){
																								alert("Product Type is Mandatory, Please select the Product Type.");
																								document.getElementById("productTypeId").focus();
																								return; 
																							}else{
																								break;
																							}																		
																						}																    
																					}		
						
																					// Description of the product
																					if(trim(document.getElementById("description").value)==""){
																					   alert("Product description is Mandatory, Please enter the description of the product.");
																					   document.getElementById("description").focus();
																					   return; 
																					}
																																																														
																					var quantityId=document.getElementById("quantityId").value;
																					if(quantityId!=""){
																					   if(!pattern1.test(quantityId)){
																					         alert("Product quantity should be only Digits.");
																					         document.getElementById("quantityId").select();
																					         return;
																					    }
																				 	}
																					
																					// Serving quantity type of the product
																					if(trim(document.getElementById("quantityTypeId").value)==""){
																					   alert("Product serving quantity type is Mandatory, Please enter the quantity type of the product.");
																					   document.getElementById("quantityTypeId").focus();
																					   return; 
																					}
																					
																					// collecting all ingredients in an inventory product
																					var len=document.getElementById('inventoryProductTab').rows.length;				
																					var tempURI = "";
																			
																				    if(len>=1){								
																						for(var i=1;i<len;i++){	
																							var elements = document.getElementById('inventoryProductId'+i);
																							for(var j=0; j<elements.length; j++){
																								if(elements[j].selected && j==0){
																									alert("Please select ingradient for the selected inventory product.");
																									document.getElementById('inventoryProductId'+i).focus();
																									return;
																								}
																							}																																											    
																						}						    	
																					} 																				    																				  																																										
	
																				    if(len>=1){								
																						for(var i=1;i<len;i++){	
																							var elements = document.getElementById('inventoryProductQuantityTypeId'+i);
																							for(var j=0; j<elements.length; j++){
																								if(elements[j].selected && j==0){
																							    	alert("Please select ingradient for the selected inventory product quantity type.");
																							    	document.getElementById('inventoryProductQuantityTypeId'+i).focus();
																									return;
																								}
																							}																																											    
																						}						    	
																					} 
																																																																											   
																					if(len>=1){								
																						for(var i=1;i<len;i++){	
																							for(var j=i+1;j<len;j++){																							
																									var elementi = document.getElementById('inventoryProductId'+i);
																									var elementj = document.getElementById('inventoryProductId'+j);
																									if(elementi[i].value == elementj[j].value){
																									    alert("Please select distinguished ingradients for the selected inventory product.");
																									    document.getElementById('inventoryProductId'+i).focus();
																										return;
																									}
																								}																																											    
																							}						    	
																						} 
																																							
																					
																					if(len>=1){		
																						var inventoryProductIdURI = "";																			
																						for(var i=1;i<len;i++){	
																							var elements = document.getElementById('inventoryProductId'+i);
																							for(var j=0; j<elements.length; j++){
																								if(elements[j].selected && j==0){
																									alert("Please select ingradient for the selected inventory product.");
																									document.getElementById('inventoryProductId'+i).focus();
																									return;
																								}else if (elements[j].selected && j!=0){
																									inventoryProductIdURI = inventoryProductIdURI + elements[j].value + ",";
																								}																							
																							}																						
																						}	
																						tempURI = tempURI + "&inventoryProductId=" + inventoryProductIdURI;
																					} 																																																																																																	
																			
																					// collecting all ingredients with quantity in an inventory product																		 			 	
																					if(len>=1){					
																					    for(var i=1;i<len;i++){
																							if(document.getElementById('inventoryProductQuantityId'+i).value == ""){ 											    	 	
																							    alert("Please enter inventory product ingradients quantity.");
																							    document.getElementById('inventoryProductQuantityId'+i).select();
																							    return;
																							}
																						}
																					    																						
																						for(var i=1;i<len;i++){
																							var val = document.getElementById('inventoryProductQuantityId'+i).value;
																							if(val!=""){
																							   if(!pattern3.test(val)){
																							         alert("Product quantity should be only Digits.");
																							         document.getElementById('inventoryProductQuantityId'+i).select();
																							         return;
																							    }
																						 	}
																						}
																						
																					 	var urival = "";
																						for(var i=1;i<len;i++){
																							var val = document.getElementById('inventoryProductQuantityId'+i).value;
																							if(val!=""){
																						    	urival = urival + val + ",";
																						 	}
																						}
																						
																						tempURI = tempURI + "&inventoryProductQuantity=" + urival;
																					}						
																																																																																				
																					if(len>=1){		
																						var inventoryProductQuantityTypeIdURI = "";																			
																						for(var i=1;i<len;i++){	
																							var elements = document.getElementById('inventoryProductQuantityTypeId'+i);
																							for(var j=0;j<elements.length;j++){
																								if(elements[j].selected && j==0){
																									alert("Please select quantity type for ingradients for the selected inventory product.");
																									document.getElementById('inventoryProductQuantityTypeId'+i).focus();
																									return;
																								}else if (elements[j].selected && j!=0){
																									inventoryProductQuantityTypeIdURI = inventoryProductQuantityTypeIdURI + elements[j].value + ",";
																								}																							
																							}																						
																						}
																						tempURI = tempURI + "&inventoryProductQuantityTypeId=" + inventoryProductQuantityTypeIdURI;
																					} 	
																																										
																					var status = window.confirm("Do you want to save inventory product with all ingredients Detail ?");
																					if(status){
																						var productId='<bean:write name="formVOO" property="productId" />'
																						document.forms[0].action="kitchenInventoryProduct.do?methodName=updateKitchenInventoryProduct&productId" + productId + tempURI;
																						document.forms[0].submit();																		
																					}						
																				  }	
																																									
																				  // close Form
																				  function clearInventoryProduct(){
																					document.forms[0].action="kitchenInventoryProduct.do?methodName=clearKitchenInventoryProduct";
																					document.forms[0].submit();
																				  }																		
															 </script>
															 <button type="button" class="btn btn-primary" onclick="addInventoryProduct()">Apply</button>
															 <button type="button" class="btn btn-danger" onclick="clearInventoryProduct()">Clear</button>
														</div>
													</div>
															
                                                 </div>
												</div>
												</div>												
								</div>
				</div>
				
			</div><!-- /container-->
		</div><!-- //inner_content-->
		</form>
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
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     