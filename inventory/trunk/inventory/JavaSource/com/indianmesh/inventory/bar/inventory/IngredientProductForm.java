package com.indianmesh.inventory.bar.inventory;

import org.apache.struts.action.ActionForm;

public class IngredientProductForm extends ActionForm{

			//---------------------------------------------------------------------------------------------------------------------
			//Properties
			//---------------------------------------------------------------------------------------------------------------------
			
			private static final long serialVersionUID = 1L;
			
			public String productId = null;
			public String ingredientProductId = null;
			public String ingredientProductQuantity = null;
			public String ingredientProductQuantityTypeId = null;
			public String inventoryProductId = null;
			private String pageFrom = null;
			private String pageTo = null;
			private String limit = null;
			private String length = null;
			
			//---------------------------------------------------------------------------------------------------------------------
			//Constructors Override 
			//---------------------------------------------------------------------------------------------------------------------
				
			public IngredientProductForm() {
				super();
			}		

			public IngredientProductForm(String productId, String ingredientProductId, String ingredientProductQuantity,
					String ingredientProductQuantityTypeId, String inventoryProductId, String pageFrom, String pageTo,
					String limit, String length) {
				super();
				this.productId = productId;
				this.ingredientProductId = ingredientProductId;
				this.ingredientProductQuantity = ingredientProductQuantity;
				this.ingredientProductQuantityTypeId = ingredientProductQuantityTypeId;
				this.inventoryProductId = inventoryProductId;
				this.pageFrom = pageFrom;
				this.pageTo = pageTo;
				this.limit = limit;
				this.length = length;
			}

			//---------------------------------------------------------------------------------------------------------------------
			//hashCode, equals and toString Method Override 
			//---------------------------------------------------------------------------------------------------------------------
			
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((ingredientProductId == null) ? 0 : ingredientProductId.hashCode());
				result = prime * result
						+ ((ingredientProductQuantity == null) ? 0 : ingredientProductQuantity.hashCode());
				result = prime * result
						+ ((ingredientProductQuantityTypeId == null) ? 0 : ingredientProductQuantityTypeId.hashCode());
				result = prime * result + ((inventoryProductId == null) ? 0 : inventoryProductId.hashCode());
				result = prime * result + ((length == null) ? 0 : length.hashCode());
				result = prime * result + ((limit == null) ? 0 : limit.hashCode());
				result = prime * result + ((pageFrom == null) ? 0 : pageFrom.hashCode());
				result = prime * result + ((pageTo == null) ? 0 : pageTo.hashCode());
				result = prime * result + ((productId == null) ? 0 : productId.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				IngredientProductForm other = (IngredientProductForm) obj;
				if (ingredientProductId == null) {
					if (other.ingredientProductId != null)
						return false;
				} else if (!ingredientProductId.equals(other.ingredientProductId))
					return false;
				if (ingredientProductQuantity == null) {
					if (other.ingredientProductQuantity != null)
						return false;
				} else if (!ingredientProductQuantity.equals(other.ingredientProductQuantity))
					return false;
				if (ingredientProductQuantityTypeId == null) {
					if (other.ingredientProductQuantityTypeId != null)
						return false;
				} else if (!ingredientProductQuantityTypeId.equals(other.ingredientProductQuantityTypeId))
					return false;
				if (inventoryProductId == null) {
					if (other.inventoryProductId != null)
						return false;
				} else if (!inventoryProductId.equals(other.inventoryProductId))
					return false;
				if (length == null) {
					if (other.length != null)
						return false;
				} else if (!length.equals(other.length))
					return false;
				if (limit == null) {
					if (other.limit != null)
						return false;
				} else if (!limit.equals(other.limit))
					return false;
				if (pageFrom == null) {
					if (other.pageFrom != null)
						return false;
				} else if (!pageFrom.equals(other.pageFrom))
					return false;
				if (pageTo == null) {
					if (other.pageTo != null)
						return false;
				} else if (!pageTo.equals(other.pageTo))
					return false;
				if (productId == null) {
					if (other.productId != null)
						return false;
				} else if (!productId.equals(other.productId))
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "IngredientProductForm [productId=" + productId + ", ingredientProductId=" + ingredientProductId
						+ ", ingredientProductQuantity=" + ingredientProductQuantity
						+ ", ingredientProductQuantityTypeId=" + ingredientProductQuantityTypeId
						+ ", inventoryProductId=" + inventoryProductId + ", pageFrom=" + pageFrom + ", pageTo=" + pageTo
						+ ", limit=" + limit + ", length=" + length +"]";
			}

			//---------------------------------------------------------------------------------------------------------------------
			//Setter and Getter Method 
			//---------------------------------------------------------------------------------------------------------------------

			public String getProductId() {
				return productId;
			}

			public void setProductId(String productId) {
				this.productId = productId;
			}

			public String getIngredientProductId() {
				return ingredientProductId;
			}

			public void setIngredientProductId(String ingredientProductId) {
				this.ingredientProductId = ingredientProductId;
			}

			public String getIngredientProductQuantity() {
				return ingredientProductQuantity;
			}

			public void setIngredientProductQuantity(String ingredientProductQuantity) {
				this.ingredientProductQuantity = ingredientProductQuantity;
			}

			public String getIngredientProductQuantityTypeId() {
				return ingredientProductQuantityTypeId;
			}

			public void setIngredientProductQuantityTypeId(String ingredientProductQuantityTypeId) {
				this.ingredientProductQuantityTypeId = ingredientProductQuantityTypeId;
			}

			public String getInventoryProductId() {
				return inventoryProductId;
			}

			public void setInventoryProductId(String inventoryProductId) {
				this.inventoryProductId = inventoryProductId;
			}

			public String getPageFrom() {
				return pageFrom;
			}

			public void setPageFrom(String pageFrom) {
				this.pageFrom = pageFrom;
			}

			public String getPageTo() {
				return pageTo;
			}

			public void setPageTo(String pageTo) {
				this.pageTo = pageTo;
			}

			public String getLimit() {
				return limit;
			}

			public void setLimit(String limit) {
				this.limit = limit;
			}

			public String getLength() {
				return length;
			}

			public void setLength(String length) {
				this.length = length;
			}

}
