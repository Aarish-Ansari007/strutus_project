package com.indianmesh.inventory.kitchen.consumption;

import java.io.Serializable;

public class KitchenConsumptionFormVO implements Serializable {

	//---------------------------------------------------------------------------------------------------------------------
	//Properties
	//---------------------------------------------------------------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	private String storeId = null;
	private String stockId = null;
	private String sellerId = null;
	private String subSellerId = null;
	private String productId = null;
	private String productName = null;
	private String quantityTypeId = null;
	private String vendorId = null;
	private String vendorName = null;
	private String quantityPerUnit = null;
	private String lastCount = null;
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
	private String supplyDate = null;
	private String requestDate = null;
	private String price = null;
	private String reminder = null;
	
	private String pageFrom = null;
	private String pageTo = null;
	private String limit = null;
	private String length = null;
	
	private String fromDate = null;
	private String toDate = null;
	
	//---------------------------------------------------------------------------------------------------------------------
	//Constructors Override 
	//---------------------------------------------------------------------------------------------------------------------
		
	public KitchenConsumptionFormVO() {
		super();
	}

	public KitchenConsumptionFormVO(String storeId, String stockId, String sellerId, String subSellerId, String productId,
			String productName, String vendorId, String vendorName, String availableId, String consumptionId,
			String requirementId, String additionId, String creditnoteId, String status, String statusOn,
			String statusOff, String createdDate, String lastModifiedDate, String pageFrom, String pageTo,
			String limit, String length, String fromDate, String toDate, String quantityTypeId, String lastCount, 
			String quantityPerUnit, String supplyDate, String requestDate, String price, String reminder) {
		super();
		this.stockId = stockId;
		this.sellerId = sellerId;
		this.subSellerId = subSellerId;
		this.productId = productId;
		this.productName = productName;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
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
		this.storeId=storeId;
		this.quantityTypeId=quantityTypeId;
		this.lastCount=lastCount;
		this.quantityPerUnit=quantityPerUnit;
		this.supplyDate=supplyDate;
		this.requestDate=requestDate;
		this.price=price;
		this.reminder=reminder;
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
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((requirementId == null) ? 0 : requirementId.hashCode());
		result = prime * result + ((sellerId == null) ? 0 : sellerId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((statusOff == null) ? 0 : statusOff.hashCode());
		result = prime * result + ((statusOn == null) ? 0 : statusOn.hashCode());
		result = prime * result + ((stockId == null) ? 0 : stockId.hashCode());
		result = prime * result + ((subSellerId == null) ? 0 : subSellerId.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		result = prime * result + ((vendorId == null) ? 0 : vendorId.hashCode());
		result = prime * result + ((vendorName == null) ? 0 : vendorName.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		result = prime * result + ((quantityTypeId == null) ? 0 : quantityTypeId.hashCode());
		result = prime * result + ((lastCount == null) ? 0 : lastCount.hashCode());
		result = prime * result + ((quantityPerUnit == null) ? 0 : quantityPerUnit.hashCode());
		result = prime * result + ((supplyDate == null) ? 0 : supplyDate.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((reminder == null) ? 0 : reminder.hashCode());
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
		KitchenConsumptionFormVO other = (KitchenConsumptionFormVO) obj;
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
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantityTypeId == null) {
			if (other.quantityTypeId != null)
				return false;
		} else if (!quantityTypeId.equals(other.quantityTypeId))
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
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		if (vendorName == null) {
			if (other.vendorName != null)
				return false;
		} else if (!vendorName.equals(other.vendorName))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		if (lastCount == null) {
			if (other.lastCount != null)
				return false;
		} else if (!lastCount.equals(other.lastCount))
			return false;
		if (quantityPerUnit == null) {
			if (other.quantityPerUnit != null)
				return false;
		} else if (!quantityPerUnit.equals(other.quantityPerUnit))
			return false;
		if (supplyDate == null) {
			if (other.supplyDate != null)
				return false;
		} else if (!supplyDate.equals(other.supplyDate))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;				
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (reminder == null) {
			if (other.reminder != null)
				return false;
		} else if (!reminder.equals(other.reminder))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductForm [stockId=" + stockId + ", sellerId=" + sellerId + ", subSellerId=" + subSellerId
				+ ", productId=" + productId + ", productName=" + productName + ", vendorId=" + vendorId
				+ ", vendorName=" + vendorName + ", availableId=" + availableId + ", consumptionId="
				+ consumptionId + ", requirementId=" + requirementId + ", additionId=" + additionId
				+ ", creditnoteId=" + creditnoteId + ", status=" + status + ", statusOn=" + statusOn
				+ ", statusOff=" + statusOff + ", createdDate=" + createdDate + ", lastModifiedDate="
				+ lastModifiedDate + ", pageFrom=" + pageFrom + ", pageTo=" + pageTo + ", limit=" + limit
				+ ", length=" + length + ", fromDate=" + fromDate + ", toDate=" + toDate + ", storeId=" + storeId 
				+ ", quantityTypeId=" + quantityTypeId + ", lastCount=" + lastCount + ", quantityPerUnit=" + quantityPerUnit 
				+ ", supplyDate=" + supplyDate + ", requestDate=" + requestDate + ", price=" + price
				+ ", reminder=" + reminder + "]";
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	//Setter and Getter Method 
	//---------------------------------------------------------------------------------------------------------------------

	public String getQuantityTypeId() {
		return quantityTypeId;
	}

	public void setQuantityTypeId(String quantityTypeId) {
		this.quantityTypeId = quantityTypeId;
	}

	public String getStockId() {
		return stockId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getLastCount() {
		return lastCount;
	}

	public void setLastCount(String lastCount) {
		this.lastCount = lastCount;
	}
	
	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public String getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(String supplyDate) {
		this.supplyDate = supplyDate;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
}
