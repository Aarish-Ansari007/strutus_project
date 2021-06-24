<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>


<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Kitchen Product Consumption</title>
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
		
			onload=function(){
				getKitchenProductConsumptionList();
				addConsumptionInputFieldsInRow();
				getVendorRecordList();
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
			
			function getKitchenProductConsumptionList(){						
				document.getElementById("table").innerHTML = "<thead><tr><th>Product Id</th><th>Product Name</th><th>Date & Time</th><th>Last Count</th><th>Quantity Consumed</th><th>Quantity Type</th><th>Quantity Left</th></tr></thead><logic:iterate id='formVOO' name='productList'><bean:define id='tempFormVOO' name='formVOO' type='com.indianmesh.inventory.kitchen.stock.ProductFormVO'></bean:define><tbody><tr id='<bean:write name='tempFormVOO' property='productId'></bean:write>'><td><span class='bt-content'><bean:write name='tempFormVOO' property='ID'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='productName'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='lastModifiedDate'></bean:write></span></td><td><span class='bt-content'><bean:write name='tempFormVOO' property='lastCount'></bean:write></span></td><td data-th='Qty Left in Stock'><span class='bt-content'><bean:write name='tempFormVOO' property='availableId'></bean:write></span></td></tr></tbody></logic:iterate>";
				for(var i=0;i<6;i++)j=i;
			}
			
			function addConsumptionInputFieldsInRow(){
				var status ='<bean:write name='tempFormVOO' property='ID'></bean:write>';
			
				if(status!='Unavailable'){
				var table=document.getElementById('table');
				var numberOfRows = table.rows.length;
								
				//newCell0.innerHTML=numberOfRows-1;
				for(var i=1;i<numberOfRows;i++){
					var currentRow = document.getElementById("table").rows[i];
					
				    var newCell0 = currentRow.insertCell(4);					
						newCell0.align="center";
						newCell0.className="formlabel";
						
					var newCell1 = currentRow.insertCell(5);					
						newCell1.align="center";
						newCell1.className="formlabel";
						
					var newTextElement = document.createElement("INPUT");
						newTextElement = newCell0.appendChild(newTextElement);
						newTextElement.name = "consumption"+(i-1);
						newTextElement.id = "consumption"+(i-1);
						//newTextElement.value = 0;
						newTextElement.maxlength="50"
						newTextElement.size="16";
						
					var newSelectElement = document.createElement("select");
						newSelectElement = newCell1.appendChild(newSelectElement);// CHANGEED newCell0 inplace of newCell1
						newSelectElement.id = "quantityType"+(i-1);
						newSelectElement.style.width="112px";
						newSelectElement.name="quantityType"+(i-1);
						
					var text = currentRow.cells[3].innerHTML;			
					
					if(text.includes("KG")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="GM";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="2";
						oOption.text ="KG";
						newSelectElement.options.add(oOption); 
						
					}else if(text.includes("JAR")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="LTR";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="2";
						oOption.text ="Ml";
						newSelectElement.options.add(oOption); 
					}else if(text.includes("GALLON")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="Ml";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="2";
						oOption.text ="LTR";
						newSelectElement.options.add(oOption); 
					}else if(text.includes("LTR")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="Ml";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="2";
						oOption.text ="LTR";
						newSelectElement.options.add(oOption); 
					}else if(text.includes("BTL")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="BTL";
						newSelectElement.options.add(oOption); 	
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="2";
						oOption.text ="Ml";
						newSelectElement.options.add(oOption); 
					}else if(text.includes("NOS")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="NOS";
						newSelectElement.options.add(oOption); 	
						
					}else if(text.includes("BOX")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="BOX";
						newSelectElement.options.add(oOption); 	
						
					}else if(text.includes("POUCH")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="POUCH";
						newSelectElement.options.add(oOption); 	
						
					}else if(text.includes("CASE")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="CASE";
						newSelectElement.options.add(oOption); 	
						
					}else if(text.includes("BUNCH")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="BUNCH";
						newSelectElement.options.add(oOption); 	
						
					}else if(text.includes("GM")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="GM";
						newSelectElement.options.add(oOption); 	
						
					}else if(text.includes("Ml")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="Ml";
						newSelectElement.options.add(oOption); 	
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="2";
						oOption.text ="LTR";
						newSelectElement.options.add(oOption); 	
					}else if(text.includes("PKT")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="PKT";
						newSelectElement.options.add(oOption); 	
					}else if(text.includes("PCS")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="PCS";
						newSelectElement.options.add(oOption); 	
					}else if(text.includes("TIN")){
						var oOption = document.createElement("OPTION");					
						oOption.value ="0";
						oOption.text ="Select Quantity";
						newSelectElement.options.add(oOption); 
						
						var oOption = document.createElement("OPTION");					
						oOption.value ="1";
						oOption.text ="TIN";
						newSelectElement.options.add(oOption); 	
					}	
					
					newSelectElement.addEventListener('change', function(){
						var table=document.getElementById('table');
						var numberOfRows = table.rows.length;										
						if(numberOfRows==2){
							var type = newSelectElement.options[newSelectElement.selectedIndex].text;
							var product ='<bean:write name='tempFormVOO' property='productId'></bean:write>';
							var available ='<bean:write name='tempFormVOO' property='availableId'></bean:write>';
							var lastCount ='<bean:write name='tempFormVOO' property='lastCount'></bean:write>';							
							var consumption = newTextElement.value;
							document.forms[0].action="kitchenConsumption.do?methodName=updateIndexkitchenConsumption&product="+product+"&lastCount="+lastCount+"&available="+available+"&consumption="+consumption+"&type="+type;
							document.forms[0].submit();
						}										
					});	
				  }
			   }
			}
			
			function updateMultipleEntryConsumption(){

				var len=document.getElementById('table').rows.length;				
				var tempURI = "";
		
			    if(len>2){								
					for(var i=0;i<len-1;i++){	
						var element = document.getElementById('consumption'+i).value;
						
						if(element == ''){
							alert("Please enter consumption quantity in all input fields displayed in list.");
							document.getElementById('consumption'+i).focus();
							return;
						}																																																    
					}						    	
											
					for(var i=0;i<len-1;i++){	
						var elements = document.getElementById('quantityType'+i);
						for(var j=0; j<elements.length; j++){
							if(elements[j].selected && j==0){
						    	alert("Please select quantity type of all the consumption input fields.");
						    	document.getElementById('quantityType'+i).focus();
								return;
							}
						}																																											    
					}						    	
					
					var productIdURI = "";																			
					for(var i=0;i<len-1;i++){
						var element = document.getElementById("table").rows[i+1];
						productIdURI = productIdURI + element.id + ",";																					
					}																												
					tempURI = tempURI + "&productId=" + productIdURI;
													   					
				 	var urival = "";
					for(var i=0;i<len-1;i++){
						var val = document.getElementById('consumption'+i).value;
						if(val!=""){
					    	urival = urival + val + ",";
					 	}
					}
					
					tempURI = tempURI + "&consumptionId=" + urival;
					
					var quantityTypeIdURI = "";																			
					for(var i=0;i<len-1;i++){	
						var element = document.getElementById('quantityType'+i);
						for(var j=0; j<element.length; j++){
							if (element[j].selected && j!=0){
								quantityTypeIdURI = quantityTypeIdURI + element[j].text + ",";
							}
						}
					}
					tempURI = tempURI + "&quantityTypeId=" + quantityTypeIdURI;
			    }
																									
				var status = window.confirm("Do you want to update the entered consumption details ?");
				if(status){
					document.forms[0].action="kitchenConsumption.do?methodName=updateIndexkitchenConsumption" + tempURI;
					document.forms[0].submit();																		
				}						
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
	
			function showListRecordsWise(){
				var e = document.getElementById("listOfItem");
				var limit = e.options[e.selectedIndex].value;															
				document.forms[0].action="kitchenConsumption.do?methodName=getConsumptionIndexMapByRecords&limit="+limit;
				document.forms[0].submit();
			}
			
			function getPagination(pageFrom,pageTo){																																
				var index=pageFrom+','+pageTo;																
				document.forms[0].action="kitchenConsumption.do?methodName=getConsumptionIndexMapByPagination&index="+index;
				document.forms[0].submit();				
			}
			
			function searchDetails(){				
				var str = document.getElementById("search").value;
				var start = document.getElementById("start").value;
				var end = document.getElementById("end").value;
								
				if((start!="") && ( end !="")){
					document.forms[0].action="kitchenConsumption.do?methodName=getConsumptionIndexMapByDate&start="+start+"&end="+end;
					document.forms[0].submit();
				}
				
				else{					
					if(search!=''){
						if(str.includes('KP')){
							var len = str.indexOf('P');
							var index = len + 1;
							var search = str.substring(index, str.length);
							document.forms[0].action="kitchenConsumption.do?methodName=getConsumptionIndexMapByProductId&productId="+search;
							document.forms[0].submit();
						}else{
							document.forms[0].action="kitchenConsumption.do?methodName=getConsumptionIndexMapByProductName&name="+str; 
							document.forms[0].submit(); 
						}
					}else{					
						alert('Please enter correct inputs for the product search.');
						  document.getElementById('search').value=''; 
						  document.getElementById('start').value='';
						  document.getElementById('end').value='';
					      document.getElementById("search").focus();
					}
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
<form id="inventoryKitchenConsumptionForm" method="post" name="inventoryKitchenConsumptionForm" action="">

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
									
									<li>Kitchen Consumptions</li>
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
												<select id="listOfItem" onChange="showListRecordsWise();">
												  <option value="">Select an Item</option>
												</select></a> 
											</a>
                                         </div> 
										 <div class="col-md-6 col-sm-12 pull-right text-right">
												 <button type="button" class="btn btn-primary" onclick="updateMultipleEntryConsumption();">Save & Update later</button>
												 <button type="button" class="btn btn-primary" onclick="updateMultipleEntryConsumption();">Update</button>						 
											</div>
                                         	
																			 
										 <logic:present name="productList"><logic:notEmpty name="productList"><table id="table"></table></logic:notEmpty></logic:present>   													
  											
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