<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<title>View Inventory product</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> 

		/* addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } */ 
		
		   var tokenValue= '<%=request.getAttribute("TRACKERID") %>';	
		   
		
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
										 <h3 class="w3_inner_tittle two pull-left">View Inventory Product</h3>
								                <div class="form-body">
												<form>
												
													<bean:define id="inventoryProductFormVO" name="compositProductVO" type="com.indianmesh.inventory.kitchen.inventory.ProductFormVO"></bean:define>               
               										<html:hidden property="productId" name="inventoryProductFormVO"/> 
               										
													<div class="col-md-6">												
													       <div class="form-group">
                                                                <label class="col-md-12 control-label">Product Name: </label>
                                                                <div class="col-md-12">
																	<bean:write name="inventoryProductFormVO" property="name" />
																</div>
                                                            </div>															
                                                            <div class="form-group">
                                                                <label class="col-md-12 control-label">Product Type: </label>
	                                                                <div class="col-md-12">            																																	
																		<bean:write name="inventoryProductFormVO" property="productTypeId" />
																	</div>																
                                                            </div>  
                                                            <div class="form-group">
                                                                <label class="col-md-12 control-label">Description:
                                                                </label>
                                                                <div class="col-md-12">
                                                                	<bean:write name="inventoryProductFormVO" property="description" />
                                                                </div>
                                                            </div>																																															                                                           
												    </div>
												    
													<div class="col-md-6">		
																																					
													<div class="form-group">
													      <div class="col-md-12">
                                                               <label class="col-md-6 control-label">Serving Qty:</label>
                                                               <label class="col-md-6 control-label">Qty Type:</label>
                                                          </div>
                                                          <div class="col-md-12">            
	                                                            <div class="col-md-6">                                                            
																	<bean:write name="inventoryProductFormVO" property="quantityId" />
																</div>																	  
																<div class="col-md-6">            																	
																	<bean:write name="inventoryProductFormVO" property="quantityTypeId" />
																</div>															 
															</div>
													 </div>
													
											  		<table id="inventoryProductTab">											 												 
														<div class="form-group">													
																<script type="text/javascript">																	
																		onload=function(){
																			getInventoryProductRows();
																    	} 
																																												
																		function getInventoryProductRows(){
																			var strHtml1 = "<tr><td width='10%' align='center'>Sl.No.</td><td width='20%' align='center'>Name</td><td width='35%' align='center'>Quantity</td><td width='35%' align='center'>QuantityType</td></tr>";		
																					
																			var strInventoryProductId = '<%=request.getSession().getAttribute("inventoryProductIds")%>';
																			var strInventoryProductQuantityId = '<%=request.getSession().getAttribute("inventoryProductQuantites")%>';
																			var strInventoryProductQuantityTypeId = '<%=request.getSession().getAttribute("inventoryProductQuantityTypeIds")%>';
																				    
																			var tempStrInventoryProductId = strInventoryProductId.split(',');					    
																			var tempStrInventoryProductQuantityId = strInventoryProductQuantityId.split(',');
																			var tempStrInventoryProductQuantityTypeId = strInventoryProductQuantityTypeId.split(',');
																			    					    
																			for( var i=0; i<tempStrInventoryProductId.length-1; i++){				   	   
																				strHtml1+="<tr><td width='20%' align='center' class='formlabel'>" + (i+1) + "</td><td width='35%' align='center' class='formlabel'>" + tempStrInventoryProductId[i] + "</td><td width='35%' align='center' class='formlabel'>" + tempStrInventoryProductQuantityId[i] + "</td><td width='10%' align='center' class='formlabel'>" + tempStrInventoryProductQuantityTypeId[i] + "</td></tr>";
																			}
																			
																			document.getElementById("inventoryProductTab").innerHTML = strHtml1;
																		}	
																		
																		//close Form
																		function clearInventoryProduct(){
																			document.forms[0].action="kitchenInventoryProduct.do?methodName=getIndexkitchenInventoryProduct";
																			document.forms[0].submit();
																		}	
																  	</script> 
																  		
	                                                                <div class="col-md-12">
	                                                                   <label class="col-md-4 control-label">Ingredients:</label>
	                                                                   <label class="col-md-2 control-label apend-div"><!-- <i class="fa fa-plus-circle" aria-hidden="true" onclick="addInventoryProductIngredients(null,null,null)"></i> --></label>
	                                                                   <label class="col-md-3 control-label apend-div"><!-- <i class="fa fa-minus-circle" aria-hidden="true" onclick="removeInventoryProductRow('inventoryProductTab',1)"></i> --></label>
	                                                                   <label class="col-md-1 control-label"></label>
	                                                                 </div>
	                                                                 
	                                                                  <div class="col-md-12" id="inventoryProductDiv" style="display:none;"> 
																		 <div class="col-md-4">															 																
																			 <html:select property="inventoryProductId" styleId="inventoryProductId" styleClass="selectBoxTag" style="width:138px" name="inventoryProductFormVO">
																					<html:option value="">----SELECT----</html:option>
																					<html:options collection="inventoryProductList" property="key" labelProperty="value" name="inventoryProductFormVO"></html:options>
																			 </html:select>																 
																		 </div>
																		 <div class="col-md-2">
	                                                                    	<html:text property="inventoryProductQuantityId" styleId="inventoryProductQuantityId" maxlength="5" size="6" styleClass="textBox" name="inventoryProductFormVO"/> 
																	     </div>
																		 <div class="col-md-3">	
																		 	 <html:select property="inventoryProductQuantityTypeId" styleId="inventoryProductQuantityTypeId" styleClass="selectBoxTag" style="width:110px" name="inventoryProductFormVO">
																				<html:option value="">----SELECT----</html:option>
																				<html:options collection="ingredientQuantityTypeList" property="key" labelProperty="value" name="inventoryProductFormVO"></html:options>
																		     </html:select>	
																		 </div>														 																	 																	 																	 
																	 </div>															 
															</div>																
														</table>																		
														<div class="form-group">
															<div class="col-md-12">															
															 
															 <button type="button" class="btn btn-danger" onclick="clearInventoryProduct();">Clear</button> 
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
	 <p>?? 2017 Indian Mesh Pvt. Ltd.</a> </p>
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