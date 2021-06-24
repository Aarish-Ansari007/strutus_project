package com.indianmesh.inventory.bar.inventory;

import java.io.Serializable;
import java.util.List;

public class ProductFormVO implements Serializable {

		//---------------------------------------------------------------------------------------------------------------------
		//Properties
		//---------------------------------------------------------------------------------------------------------------------
			
		private static final long serialVersionUID = 1L;
		
		public String productId = null;
		public String sellerId = null;
		public String subsellerId = null;
		public String name = null;
		public String productTypeId = null;
		public String description = null;
		public String quantityId = null;
		public String quantityTypeId = null;
		public String inventoryProductId = null;
		public String inventoryProductQuantityId = null;
		public String inventoryProductQuantityTypeId = null;
		public String createdDate = null;
		public String lastModifiedDate = null;
		public String status = null;
		public String statusOn = null;
		public String statusOff = null;
		
		private List<String> inventoryProductIngredientList = null;
		private List<String> inventoryProductQuantityList = null;
		private List<String> inventoryProductQuantityTypeList = null;
		
		private String pageFrom = null;
		private String pageTo = null;
		private String limit = null;
		private String length = null;
		
		private String fromDate = null;
		private String toDate = null;
		
		//---------------------------------------------------------------------------------------------------------------------
		//Constructors Override 
		//---------------------------------------------------------------------------------------------------------------------
			
		public ProductFormVO() {
			super();
		}

		public ProductFormVO(String productId, String sellerId, String subsellerId, String name, String productTypeId,
				String description, String quantityId, String quantityTypeId, String inventoryProductId,
				String inventoryProductQuantityId, String inventoryProductQuantityTypeId, String createdDate,
				String lastModifiedDate, String status, String statusOn, String statusOff,
				List<String> inventoryProductIngredientList, List<String> inventoryProductQuantityList,
				List<String> inventoryProductQuantityTypeList, String pageFrom, String pageTo, String limit, String length, String fromDate, String toDate) {
			super();
			this.productId = productId;
			this.sellerId = sellerId;
			this.subsellerId = subsellerId;
			this.name = name;
			this.productTypeId = productTypeId;
			this.description = description;
			this.quantityId = quantityId;
			this.quantityTypeId = quantityTypeId;
			this.inventoryProductId = inventoryProductId;
			this.inventoryProductQuantityId = inventoryProductQuantityId;
			this.inventoryProductQuantityTypeId = inventoryProductQuantityTypeId;
			this.createdDate = createdDate;
			this.lastModifiedDate = lastModifiedDate;
			this.status = status;
			this.statusOn = statusOn;
			this.statusOff = statusOff;
			this.inventoryProductIngredientList = inventoryProductIngredientList;
			this.inventoryProductQuantityList = inventoryProductQuantityList;
			this.inventoryProductQuantityTypeList = inventoryProductQuantityTypeList;
			this.pageFrom = pageFrom;
			this.pageTo = pageTo;
			this.limit = limit;
			this.length = length;
			this.fromDate = fromDate;
			this.toDate = toDate;
		}

		//---------------------------------------------------------------------------------------------------------------------
		//hashCode, equals and toString Method Override 
		//---------------------------------------------------------------------------------------------------------------------
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((inventoryProductId == null) ? 0 : inventoryProductId.hashCode());
			result = prime * result
					+ ((inventoryProductIngredientList == null) ? 0 : inventoryProductIngredientList.hashCode());
			result = prime * result
					+ ((inventoryProductQuantityId == null) ? 0 : inventoryProductQuantityId.hashCode());
			result = prime * result
					+ ((inventoryProductQuantityList == null) ? 0 : inventoryProductQuantityList.hashCode());
			result = prime * result
					+ ((inventoryProductQuantityTypeId == null) ? 0 : inventoryProductQuantityTypeId.hashCode());
			result = prime * result
					+ ((inventoryProductQuantityTypeList == null) ? 0 : inventoryProductQuantityTypeList.hashCode());
			result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((pageFrom == null) ? 0 : pageFrom.hashCode());
			result = prime * result + ((pageTo == null) ? 0 : pageTo.hashCode());
			result = prime * result + ((productId == null) ? 0 : productId.hashCode());
			result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
			result = prime * result + ((quantityId == null) ? 0 : quantityId.hashCode());
			result = prime * result + ((quantityTypeId == null) ? 0 : quantityTypeId.hashCode());
			result = prime * result + ((sellerId == null) ? 0 : sellerId.hashCode());
			result = prime * result + ((limit == null) ? 0 : limit.hashCode());
			result = prime * result + ((status == null) ? 0 : status.hashCode());
			result = prime * result + ((statusOff == null) ? 0 : statusOff.hashCode());
			result = prime * result + ((statusOn == null) ? 0 : statusOn.hashCode());
			result = prime * result + ((subsellerId == null) ? 0 : subsellerId.hashCode());
			result = prime * result + ((length == null) ? 0 : length.hashCode());
			result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
			result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
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
			ProductFormVO other = (ProductFormVO) obj;
			if (createdDate == null) {
				if (other.createdDate != null)
					return false;
			} else if (!createdDate.equals(other.createdDate))
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (inventoryProductId == null) {
				if (other.inventoryProductId != null)
					return false;
			} else if (!inventoryProductId.equals(other.inventoryProductId))
				return false;
			if (inventoryProductIngredientList == null) {
				if (other.inventoryProductIngredientList != null)
					return false;
			} else if (!inventoryProductIngredientList.equals(other.inventoryProductIngredientList))
				return false;
			if (inventoryProductQuantityId == null) {
				if (other.inventoryProductQuantityId != null)
					return false;
			} else if (!inventoryProductQuantityId.equals(other.inventoryProductQuantityId))
				return false;
			if (inventoryProductQuantityList == null) {
				if (other.inventoryProductQuantityList != null)
					return false;
			} else if (!inventoryProductQuantityList.equals(other.inventoryProductQuantityList))
				return false;
			if (inventoryProductQuantityTypeId == null) {
				if (other.inventoryProductQuantityTypeId != null)
					return false;
			} else if (!inventoryProductQuantityTypeId.equals(other.inventoryProductQuantityTypeId))
				return false;
			if (inventoryProductQuantityTypeList == null) {
				if (other.inventoryProductQuantityTypeList != null)
					return false;
			} else if (!inventoryProductQuantityTypeList.equals(other.inventoryProductQuantityTypeList))
				return false;
			if (lastModifiedDate == null) {
				if (other.lastModifiedDate != null)
					return false;
			} else if (!lastModifiedDate.equals(other.lastModifiedDate))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
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
			if (productTypeId == null) {
				if (other.productTypeId != null)
					return false;
			} else if (!productTypeId.equals(other.productTypeId))
				return false;
			if (quantityId == null) {
				if (other.quantityId != null)
					return false;
			} else if (!quantityId.equals(other.quantityId))
				return false;
			if (quantityTypeId == null) {
				if (other.quantityTypeId != null)
					return false;
			} else if (!quantityTypeId.equals(other.quantityTypeId))
				return false;
			if (sellerId == null) {
				if (other.sellerId != null)
					return false;
			} else if (!sellerId.equals(other.sellerId))
				return false;
			if (limit != other.limit)
				return false;
			if (status == null) {
				if (other.status != null)
					return false;
			} else if (!status.equals(other.status))
				return false;
			if (statusOff == null) {
				if (other.statusOff != null)
					return false;
			} else if (!statusOff.equals(other.statusOff))
				return false;
			if (statusOn == null) {
				if (other.statusOn != null)
					return false;
			} else if (!statusOn.equals(other.statusOn))
				return false;
			if (subsellerId == null) {
				if (other.subsellerId != null)
					return false;
			} else if (!subsellerId.equals(other.subsellerId))
				return false;
			if (length == null) {
				if (other.length != null)
					return false;
			} else if (!length.equals(other.length))
				return false;			
			if (fromDate == null) {
				if (other.fromDate != null)
					return false;
			} else if (!fromDate.equals(other.fromDate))
				return false;			
			if (toDate == null) {
				if (other.toDate != null)
					return false;
			} else if (!toDate.equals(other.toDate))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ProductFormVO [productId=" + productId + ", sellerId=" + sellerId + ", subsellerId=" + subsellerId
					+ ", name=" + name + ", productTypeId=" + productTypeId + ", description=" + description
					+ ", quantityId=" + quantityId + ", quantityTypeId=" + quantityTypeId + ", inventoryProductId="
					+ inventoryProductId + ", inventoryProductQuantityId=" + inventoryProductQuantityId
					+ ", inventoryProductQuantityTypeId=" + inventoryProductQuantityTypeId + ", createdDate="
					+ createdDate + ", lastModifiedDate=" + lastModifiedDate + ", status=" + status + ", statusOn="
					+ statusOn + ", statusOff=" + statusOff + ", inventoryProductIngredientList="
					+ inventoryProductIngredientList + ", inventoryProductQuantityList=" + inventoryProductQuantityList
					+ ", inventoryProductQuantityTypeList=" + inventoryProductQuantityTypeList + ", pageFrom="
					+ pageFrom + ", pageTo=" + pageTo + ", limit=" + limit + ", length=" + length + ", fromDate=" + fromDate + ", toDate=" + toDate +"]";
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

		public String getSellerId() {
			return sellerId;
		}

		public void setSellerId(String sellerId) {
			this.sellerId = sellerId;
		}

		public String getSubsellerId() {
			return subsellerId;
		}

		public void setSubsellerId(String subsellerId) {
			this.subsellerId = subsellerId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getProductTypeId() {
			return productTypeId;
		}

		public void setProductTypeId(String productTypeId) {
			this.productTypeId = productTypeId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getQuantityId() {
			return quantityId;
		}

		public void setQuantityId(String quantityId) {
			this.quantityId = quantityId;
		}

		public String getQuantityTypeId() {
			return quantityTypeId;
		}

		public void setQuantityTypeId(String quantityTypeId) {
			this.quantityTypeId = quantityTypeId;
		}

		public String getInventoryProductId() {
			return inventoryProductId;
		}

		public void setInventoryProductId(String inventoryProductId) {
			this.inventoryProductId = inventoryProductId;
		}

		public String getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}

		public String getLastModifiedDate() {
			return lastModifiedDate;
		}

		public void setLastModifiedDate(String lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
		}

		public String getInventoryProductQuantityId() {
			return inventoryProductQuantityId;
		}

		public void setInventoryProductQuantityId(String inventoryProductQuantityId) {
			this.inventoryProductQuantityId = inventoryProductQuantityId;
		}

		public String getInventoryProductQuantityTypeId() {
			return inventoryProductQuantityTypeId;
		}

		public void setInventoryProductQuantityTypeId(String inventoryProductQuantityTypeId) {
			this.inventoryProductQuantityTypeId = inventoryProductQuantityTypeId;
		}

		public List<String> getInventoryProductIngredientList() {
			return inventoryProductIngredientList;
		}

		public void setInventoryProductIngredientList(List<String> inventoryProductIngredientList) {
			this.inventoryProductIngredientList = inventoryProductIngredientList;
		}

		public List<String> getInventoryProductQuantityList() {
			return inventoryProductQuantityList;
		}

		public void setInventoryProductQuantityList(List<String> inventoryProductQuantityList) {
			this.inventoryProductQuantityList = inventoryProductQuantityList;
		}

		public List<String> getInventoryProductQuantityTypeList() {
			return inventoryProductQuantityTypeList;
		}

		public void setInventoryProductQuantityTypeList(List<String> inventoryProductQuantityTypeList) {
			this.inventoryProductQuantityTypeList = inventoryProductQuantityTypeList;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStatusOn() {
			return statusOn;
		}

		public void setStatusOn(String statusOn) {
			this.statusOn = statusOn;
		}

		public String getStatusOff() {
			return statusOff;
		}

		public void setStatusOff(String statusOff) {
			this.statusOff = statusOff;
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

		public String getFromDate() {
			return fromDate;
		}

		public void setFromDate(String fromDate) {
			this.fromDate = fromDate;
		}

		public String getToDate() {
			return toDate;
		}

		public void setToDate(String toDate) {
			this.toDate = toDate;
		}
	}