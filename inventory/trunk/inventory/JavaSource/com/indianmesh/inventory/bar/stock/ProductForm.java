package com.indianmesh.inventory.bar.stock;

import org.apache.struts.action.ActionForm;

public class ProductForm extends ActionForm {

			//---------------------------------------------------------------------------------------------------------------------
			//Properties
			//---------------------------------------------------------------------------------------------------------------------
			
			private static final long serialVersionUID = 1L;
			
			private String stockId = null;
			private String sellerId = null;
			private String subSellerId = null;
			private String productId = null;
			private String availableId = null;
			private String consumptionId = null;
			private String requirementId = null;
			private String additionId = null;
			private String creditnoteId = null;
			private String status = null;
			private String statusOn = null;
			private String statusOff = null;
			private String createdDate = null;
			private String lastModifiedDate = null;
			
			private String pageFrom = null;
			private String pageTo = null;
			private String limit = null;
			private String length = null;
			
			private String fromDate = null;
			private String toDate = null;
			
			//---------------------------------------------------------------------------------------------------------------------
			//Constructors Override 
			//---------------------------------------------------------------------------------------------------------------------
				
			public ProductForm() {
				super();
			}

			public ProductForm(String stockId, String sellerId, String subSellerId, String productId,
					String availableId, String consumptionId, String requirementId, String additionId,
					String creditnoteId, String status, String statusOn, String statusOff, String createdDate,
					String lastModifiedDate, String pageFrom, String pageTo, String limit, String length,
					String fromDate, String toDate) {
				super();
				this.stockId = stockId;
				this.sellerId = sellerId;
				this.subSellerId = subSellerId;
				this.productId = productId;
				this.availableId = availableId;
				this.consumptionId = consumptionId;
				this.requirementId = requirementId;
				this.additionId = additionId;
				this.creditnoteId = creditnoteId;
				this.status = status;
				this.statusOn = statusOn;
				this.statusOff = statusOff;
				this.createdDate = createdDate;
				this.lastModifiedDate = lastModifiedDate;
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
				result = prime * result + ((additionId == null) ? 0 : additionId.hashCode());
				result = prime * result + ((availableId == null) ? 0 : availableId.hashCode());
				result = prime * result + ((consumptionId == null) ? 0 : consumptionId.hashCode());
				result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
				result = prime * result + ((creditnoteId == null) ? 0 : creditnoteId.hashCode());
				result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
				result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
				result = prime * result + ((length == null) ? 0 : length.hashCode());
				result = prime * result + ((limit == null) ? 0 : limit.hashCode());
				result = prime * result + ((pageFrom == null) ? 0 : pageFrom.hashCode());
				result = prime * result + ((pageTo == null) ? 0 : pageTo.hashCode());
				result = prime * result + ((productId == null) ? 0 : productId.hashCode());
				result = prime * result + ((requirementId == null) ? 0 : requirementId.hashCode());
				result = prime * result + ((sellerId == null) ? 0 : sellerId.hashCode());
				result = prime * result + ((status == null) ? 0 : status.hashCode());
				result = prime * result + ((statusOff == null) ? 0 : statusOff.hashCode());
				result = prime * result + ((statusOn == null) ? 0 : statusOn.hashCode());
				result = prime * result + ((stockId == null) ? 0 : stockId.hashCode());
				result = prime * result + ((subSellerId == null) ? 0 : subSellerId.hashCode());
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
				ProductForm other = (ProductForm) obj;
				if (additionId == null) {
					if (other.additionId != null)
						return false;
				} else if (!additionId.equals(other.additionId))
					return false;
				if (availableId == null) {
					if (other.availableId != null)
						return false;
				} else if (!availableId.equals(other.availableId))
					return false;
				if (consumptionId == null) {
					if (other.consumptionId != null)
						return false;
				} else if (!consumptionId.equals(other.consumptionId))
					return false;
				if (createdDate == null) {
					if (other.createdDate != null)
						return false;
				} else if (!createdDate.equals(other.createdDate))
					return false;
				if (creditnoteId == null) {
					if (other.creditnoteId != null)
						return false;
				} else if (!creditnoteId.equals(other.creditnoteId))
					return false;
				if (fromDate == null) {
					if (other.fromDate != null)
						return false;
				} else if (!fromDate.equals(other.fromDate))
					return false;
				if (lastModifiedDate == null) {
					if (other.lastModifiedDate != null)
						return false;
				} else if (!lastModifiedDate.equals(other.lastModifiedDate))
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
				if (requirementId == null) {
					if (other.requirementId != null)
						return false;
				} else if (!requirementId.equals(other.requirementId))
					return false;
				if (sellerId == null) {
					if (other.sellerId != null)
						return false;
				} else if (!sellerId.equals(other.sellerId))
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
				if (stockId == null) {
					if (other.stockId != null)
						return false;
				} else if (!stockId.equals(other.stockId))
					return false;
				if (subSellerId == null) {
					if (other.subSellerId != null)
						return false;
				} else if (!subSellerId.equals(other.subSellerId))
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
				return "ProductForm [stockId=" + stockId + ", sellerId=" + sellerId + ", subSellerId=" + subSellerId
						+ ", productId=" + productId + ", availableId=" + availableId + ", consumptionId="
						+ consumptionId + ", requirementId=" + requirementId + ", additionId=" + additionId
						+ ", creditnoteId=" + creditnoteId + ", status=" + status + ", statusOn=" + statusOn
						+ ", statusOff=" + statusOff + ", createdDate=" + createdDate + ", lastModifiedDate="
						+ lastModifiedDate + ", pageFrom=" + pageFrom + ", pageTo=" + pageTo + ", limit=" + limit
						+ ", length=" + length + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
			}
			
			//---------------------------------------------------------------------------------------------------------------------
			//Setter and Getter Method 
			//---------------------------------------------------------------------------------------------------------------------

			public String getStockId() {
				return stockId;
			}

			public void setStockId(String stockId) {
				this.stockId = stockId;
			}

			public String getSellerId() {
				return sellerId;
			}

			public void setSellerId(String sellerId) {
				this.sellerId = sellerId;
			}

			public String getSubSellerId() {
				return subSellerId;
			}

			public void setSubSellerId(String subSellerId) {
				this.subSellerId = subSellerId;
			}

			public String getProductId() {
				return productId;
			}

			public void setProductId(String productId) {
				this.productId = productId;
			}

			public String getAvailableId() {
				return availableId;
			}

			public void setAvailableId(String availableId) {
				this.availableId = availableId;
			}

			public String getConsumptionId() {
				return consumptionId;
			}

			public void setConsumptionId(String consumptionId) {
				this.consumptionId = consumptionId;
			}

			public String getRequirementId() {
				return requirementId;
			}

			public void setRequirementId(String requirementId) {
				this.requirementId = requirementId;
			}

			public String getAdditionId() {
				return additionId;
			}

			public void setAdditionId(String additionId) {
				this.additionId = additionId;
			}

			public String getCreditnoteId() {
				return creditnoteId;
			}

			public void setCreditnoteId(String creditnoteId) {
				this.creditnoteId = creditnoteId;
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
