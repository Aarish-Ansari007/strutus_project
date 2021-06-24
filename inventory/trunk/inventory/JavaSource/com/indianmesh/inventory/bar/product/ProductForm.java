package com.indianmesh.inventory.bar.product;

import org.apache.struts.action.ActionForm;

public class ProductForm extends ActionForm {

			//---------------------------------------------------------------------------------------------------------------------
			//Properties
			//---------------------------------------------------------------------------------------------------------------------
			
			private static final long serialVersionUID = 1L;
			
			private String productId = null;
			private String sellerId = null;
			private String subSellerId = null;
			private String name = null;
			private String productType = null;
			private String description = null;
			private String quantityId = null;
			private String quantityPerItem = null;
			private String quantityCount = null;
			private String consumed = null;
			private String required = null;
			private String status = null;
			private String total = null;
			private String vendorId = null;
			private String costPrice = null;
			private String amountPaid = null;
			private String amountPaidTypeId = null;
			private String pendingAmount = null;
			private String creditNote = null;
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

			public ProductForm(String productId, String sellerId, String subSellerId, String name, String productType,
					String description, String quantityId, String quantityPerItem, String quantityCount,
					String consumed, String required, String status, String total, String vendorId, String costPrice,
					String amountPaid, String amountPaidTypeId, String pendingAmount, String creditNote,
					String createdDate, String lastModifiedDate, String pageFrom, String pageTo, String limit, String length, String fromDate, String toDate) {
				super();
				this.productId = productId;
				this.sellerId = sellerId;
				this.subSellerId = subSellerId;
				this.name = name;
				this.productType = productType;
				this.description = description;
				this.quantityId = quantityId;
				this.quantityPerItem = quantityPerItem;
				this.quantityCount = quantityCount;
				this.consumed = consumed;
				this.required = required;
				this.status = status;
				this.total = total;
				this.vendorId = vendorId;
				this.costPrice = costPrice;
				this.amountPaid = amountPaid;
				this.amountPaidTypeId = amountPaidTypeId;
				this.pendingAmount = pendingAmount;
				this.creditNote = creditNote;
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
				result = prime * result + ((amountPaid == null) ? 0 : amountPaid.hashCode());
				result = prime * result + ((amountPaidTypeId == null) ? 0 : amountPaidTypeId.hashCode());
				result = prime * result + ((consumed == null) ? 0 : consumed.hashCode());
				result = prime * result + ((costPrice == null) ? 0 : costPrice.hashCode());
				result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
				result = prime * result + ((creditNote == null) ? 0 : creditNote.hashCode());
				result = prime * result + ((description == null) ? 0 : description.hashCode());
				result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
				result = prime * result + ((name == null) ? 0 : name.hashCode());
				result = prime * result + ((pageFrom == null) ? 0 : pageFrom.hashCode());
				result = prime * result + ((pageTo == null) ? 0 : pageTo.hashCode());
				result = prime * result + ((pendingAmount == null) ? 0 : pendingAmount.hashCode());
				result = prime * result + ((productId == null) ? 0 : productId.hashCode());
				result = prime * result + ((productType == null) ? 0 : productType.hashCode());
				result = prime * result + ((quantityCount == null) ? 0 : quantityCount.hashCode());
				result = prime * result + ((quantityId == null) ? 0 : quantityId.hashCode());
				result = prime * result + ((quantityPerItem == null) ? 0 : quantityPerItem.hashCode());
				result = prime * result + ((required == null) ? 0 : required.hashCode());
				result = prime * result + ((sellerId == null) ? 0 : sellerId.hashCode());
				result = prime * result + ((limit == null) ? 0 : limit.hashCode());
				result = prime * result + ((status == null) ? 0 : status.hashCode());
				result = prime * result + ((subSellerId == null) ? 0 : subSellerId.hashCode());
				result = prime * result + ((total == null) ? 0 : total.hashCode());
				result = prime * result + ((vendorId == null) ? 0 : vendorId.hashCode());
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
				ProductForm other = (ProductForm) obj;
				if (amountPaid == null) {
					if (other.amountPaid != null)
						return false;
				} else if (!amountPaid.equals(other.amountPaid))
					return false;
				if (amountPaidTypeId == null) {
					if (other.amountPaidTypeId != null)
						return false;
				} else if (!amountPaidTypeId.equals(other.amountPaidTypeId))
					return false;
				if (consumed == null) {
					if (other.consumed != null)
						return false;
				} else if (!consumed.equals(other.consumed))
					return false;
				if (costPrice == null) {
					if (other.costPrice != null)
						return false;
				} else if (!costPrice.equals(other.costPrice))
					return false;
				if (createdDate == null) {
					if (other.createdDate != null)
						return false;
				} else if (!createdDate.equals(other.createdDate))
					return false;
				if (creditNote == null) {
					if (other.creditNote != null)
						return false;
				} else if (!creditNote.equals(other.creditNote))
					return false;
				if (description == null) {
					if (other.description != null)
						return false;
				} else if (!description.equals(other.description))
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
				if (pendingAmount == null) {
					if (other.pendingAmount != null)
						return false;
				} else if (!pendingAmount.equals(other.pendingAmount))
					return false;
				if (productId == null) {
					if (other.productId != null)
						return false;
				} else if (!productId.equals(other.productId))
					return false;
				if (productType == null) {
					if (other.productType != null)
						return false;
				} else if (!productType.equals(other.productType))
					return false;
				if (quantityCount == null) {
					if (other.quantityCount != null)
						return false;
				} else if (!quantityCount.equals(other.quantityCount))
					return false;
				if (quantityId == null) {
					if (other.quantityId != null)
						return false;
				} else if (!quantityId.equals(other.quantityId))
					return false;
				if (quantityPerItem == null) {
					if (other.quantityPerItem != null)
						return false;
				} else if (!quantityPerItem.equals(other.quantityPerItem))
					return false;
				if (required == null) {
					if (other.required != null)
						return false;
				} else if (!required.equals(other.required))
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
				if (subSellerId == null) {
					if (other.subSellerId != null)
						return false;
				} else if (!subSellerId.equals(other.subSellerId))
					return false;
				if (total == null) {
					if (other.total != null)
						return false;
				} else if (!total.equals(other.total))
					return false;
				if (vendorId == null) {
					if (other.vendorId != null)
						return false;
				} else if (!vendorId.equals(other.vendorId))
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
				return "ProductForm [productId=" + productId + ", sellerId=" + sellerId + ", subSellerId=" + subSellerId
						+ ", name=" + name + ", productType=" + productType + ", description=" + description
						+ ", quantityId=" + quantityId + ", quantityPerItem=" + quantityPerItem + ", quantityCount="
						+ quantityCount + ", consumed=" + consumed + ", required=" + required + ", status=" + status
						+ ", total=" + total + ", vendorId=" + vendorId + ", costPrice=" + costPrice + ", amountPaid="
						+ amountPaid + ", amountPaidTypeId=" + amountPaidTypeId + ", pendingAmount=" + pendingAmount
						+ ", creditNote=" + creditNote + ", createdDate=" + createdDate + ", lastModifiedDate="
						+ lastModifiedDate + ", pageFrom=" + pageFrom + ", pageTo=" + pageTo + ", limit=" + limit + ", length=" + length + ", fromDate=" + fromDate + ", toDate=" + toDate +"]";
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

			public String getSubSellerId() {
				return subSellerId;
			}

			public void setSubSellerId(String subSellerId) {
				this.subSellerId = subSellerId;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getProductType() {
				return productType;
			}

			public void setProductType(String productType) {
				this.productType = productType;
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

			public String getQuantityPerItem() {
				return quantityPerItem;
			}

			public void setQuantityPerItem(String quantityPerItem) {
				this.quantityPerItem = quantityPerItem;
			}

			public String getQuantityCount() {
				return quantityCount;
			}

			public void setQuantityCount(String quantityCount) {
				this.quantityCount = quantityCount;
			}

			public String getTotal() {
				return total;
			}

			public void setTotal(String total) {
				this.total = total;
			}

			public String getVendorId() {
				return vendorId;
			}

			public void setVendorId(String vendorId) {
				this.vendorId = vendorId;
			}

			public String getCostPrice() {
				return costPrice;
			}

			public void setCostPrice(String costPrice) {
				this.costPrice = costPrice;
			}

			public String getAmountPaid() {
				return amountPaid;
			}

			public void setAmountPaid(String amountPaid) {
				this.amountPaid = amountPaid;
			}

			public String getAmountPaidTypeId() {
				return amountPaidTypeId;
			}

			public void setAmountPaidTypeId(String amountPaidTypeId) {
				this.amountPaidTypeId = amountPaidTypeId;
			}

			public String getPendingAmount() {
				return pendingAmount;
			}

			public void setPendingAmount(String pendingAmount) {
				this.pendingAmount = pendingAmount;
			}

			public String getCreditNote() {
				return creditNote;
			}

			public void setCreditNote(String creditNote) {
				this.creditNote = creditNote;
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

			public String getConsumed() {
				return consumed;
			}

			public void setConsumed(String consumed) {
				this.consumed = consumed;
			}

			public String getRequired() {
				return required;
			}

			public void setRequired(String required) {
				this.required = required;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
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
