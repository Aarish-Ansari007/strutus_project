package com.indianmesh.inventory.bar.vendor;

import org.apache.struts.action.ActionForm;

public class VendorForm extends ActionForm{
	
	//---------------------------------------------------------------------------------------------------------------------
	//Properties
	//---------------------------------------------------------------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	private String vendorId = null;
	private String sellerId = null;
	private String subSellerId = null;
	private String uuid = null;
	private String name = null;
	private String phoneNo = null;
	private String emailId = null;
	private String tin = null;
	private String vatNo = null;
	private String pan = null;
	private String gst = null;
	private String vendorProduct = null;
	private String ballance = null;
	
	private String serviceTaxNo = null;
	private String contactPerson = null;
	private String contactPhone = null;
	private String contactEmail = null;
	
	//billing address
	private String billingAddress=null;
	private String billingCountryId=null;
	private String billingStateId=null;
	private String billingCityId=null;
	private String billingPinCode=null;
	
	//shipping address 
	private String shippingAddress=null;
	private String shippingCountryId=null;
	private String shippingStateId=null;
	private String shippingCityId=null;
	private String shippingPinCode=null;
	
	private String createdDate = null;
	private String lastModifiedDate = null;
	private String pageFrom = null;
	private String pageTo = null;
	private String limit = null;
	private String length = null;
	private String type = null;
	private String fromDate = null;
	private String toDate = null;
	
	//---------------------------------------------------------------------------------------------------------------------
	//Constructors Override 
	//---------------------------------------------------------------------------------------------------------------------
		
	public VendorForm() {
		super();
	}

	public VendorForm(String vendorId, String sellerId, String subSellerId, String uuid, String name, String phoneNo,
			String emailId, String tin, String vatNo, String pan, String gst, String vendorProduct, String ballance,
			String serviceTaxNo, String contactPerson, String contactPhone, String contactEmail, String billingAddress,
			String billingCountryId, String billingStateId, String billingCityId, String billingPinCode,
			String shippingAddress, String shippingCountryId, String shippingStateId, String shippingCityId,
			String shippingPinCode, String createdDate, String lastModifiedDate, String pageFrom, String pageTo,
			String limit, String length, String type, String fromDate, String toDate) {
		super();
		this.vendorId = vendorId;
		this.sellerId = sellerId;
		this.subSellerId = subSellerId;
		this.uuid = uuid;
		this.name = name;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
		this.tin = tin;
		this.vatNo = vatNo;
		this.pan = pan;
		this.gst = gst;
		this.vendorProduct = vendorProduct;
		this.ballance = ballance;
		this.serviceTaxNo = serviceTaxNo;
		this.contactPerson = contactPerson;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.billingAddress = billingAddress;
		this.billingCountryId = billingCountryId;
		this.billingStateId = billingStateId;
		this.billingCityId = billingCityId;
		this.billingPinCode = billingPinCode;
		this.shippingAddress = shippingAddress;
		this.shippingCountryId = shippingCountryId;
		this.shippingStateId = shippingStateId;
		this.shippingCityId = shippingCityId;
		this.shippingPinCode = shippingPinCode;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.pageFrom = pageFrom;
		this.pageTo = pageTo;
		this.limit = limit;
		this.length = length;
		this.type = type;
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
		result = prime * result + ((ballance == null) ? 0 : ballance.hashCode());
		result = prime * result + ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result + ((billingCityId == null) ? 0 : billingCityId.hashCode());
		result = prime * result + ((billingCountryId == null) ? 0 : billingCountryId.hashCode());
		result = prime * result + ((billingPinCode == null) ? 0 : billingPinCode.hashCode());
		result = prime * result + ((billingStateId == null) ? 0 : billingStateId.hashCode());
		result = prime * result + ((contactEmail == null) ? 0 : contactEmail.hashCode());
		result = prime * result + ((contactPerson == null) ? 0 : contactPerson.hashCode());
		result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((gst == null) ? 0 : gst.hashCode());
		result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pageFrom == null) ? 0 : pageFrom.hashCode());
		result = prime * result + ((pageTo == null) ? 0 : pageTo.hashCode());
		result = prime * result + ((pan == null) ? 0 : pan.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((sellerId == null) ? 0 : sellerId.hashCode());
		result = prime * result + ((serviceTaxNo == null) ? 0 : serviceTaxNo.hashCode());
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((shippingCityId == null) ? 0 : shippingCityId.hashCode());
		result = prime * result + ((shippingCountryId == null) ? 0 : shippingCountryId.hashCode());
		result = prime * result + ((shippingPinCode == null) ? 0 : shippingPinCode.hashCode());
		result = prime * result + ((shippingStateId == null) ? 0 : shippingStateId.hashCode());
		result = prime * result + ((subSellerId == null) ? 0 : subSellerId.hashCode());
		result = prime * result + ((tin == null) ? 0 : tin.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + ((vatNo == null) ? 0 : vatNo.hashCode());
		result = prime * result + ((vendorId == null) ? 0 : vendorId.hashCode());
		result = prime * result + ((vendorProduct == null) ? 0 : vendorProduct.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		VendorForm other = (VendorForm) obj;
		if (ballance == null) {
			if (other.ballance != null)
				return false;
		} else if (!ballance.equals(other.ballance))
			return false;
		if (billingAddress == null) {
			if (other.billingAddress != null)
				return false;
		} else if (!billingAddress.equals(other.billingAddress))
			return false;
		if (billingCityId == null) {
			if (other.billingCityId != null)
				return false;
		} else if (!billingCityId.equals(other.billingCityId))
			return false;
		if (billingCountryId == null) {
			if (other.billingCountryId != null)
				return false;
		} else if (!billingCountryId.equals(other.billingCountryId))
			return false;
		if (billingPinCode == null) {
			if (other.billingPinCode != null)
				return false;
		} else if (!billingPinCode.equals(other.billingPinCode))
			return false;
		if (billingStateId == null) {
			if (other.billingStateId != null)
				return false;
		} else if (!billingStateId.equals(other.billingStateId))
			return false;
		if (contactEmail == null) {
			if (other.contactEmail != null)
				return false;
		} else if (!contactEmail.equals(other.contactEmail))
			return false;
		if (contactPerson == null) {
			if (other.contactPerson != null)
				return false;
		} else if (!contactPerson.equals(other.contactPerson))
			return false;
		if (contactPhone == null) {
			if (other.contactPhone != null)
				return false;
		} else if (!contactPhone.equals(other.contactPhone))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (gst == null) {
			if (other.gst != null)
				return false;
		} else if (!gst.equals(other.gst))
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
		if (pan == null) {
			if (other.pan != null)
				return false;
		} else if (!pan.equals(other.pan))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (sellerId == null) {
			if (other.sellerId != null)
				return false;
		} else if (!sellerId.equals(other.sellerId))
			return false;
		if (serviceTaxNo == null) {
			if (other.serviceTaxNo != null)
				return false;
		} else if (!serviceTaxNo.equals(other.serviceTaxNo))
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (shippingCityId == null) {
			if (other.shippingCityId != null)
				return false;
		} else if (!shippingCityId.equals(other.shippingCityId))
			return false;
		if (shippingCountryId == null) {
			if (other.shippingCountryId != null)
				return false;
		} else if (!shippingCountryId.equals(other.shippingCountryId))
			return false;
		if (shippingPinCode == null) {
			if (other.shippingPinCode != null)
				return false;
		} else if (!shippingPinCode.equals(other.shippingPinCode))
			return false;
		if (shippingStateId == null) {
			if (other.shippingStateId != null)
				return false;
		} else if (!shippingStateId.equals(other.shippingStateId))
			return false;
		if (limit != other.limit)
			return false;
		if (subSellerId == null) {
			if (other.subSellerId != null)
				return false;
		} else if (!subSellerId.equals(other.subSellerId))
			return false;
		if (tin == null) {
			if (other.tin != null)
				return false;
		} else if (!tin.equals(other.tin))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (vatNo == null) {
			if (other.vatNo != null)
				return false;
		} else if (!vatNo.equals(other.vatNo))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		if (vendorProduct == null) {
			if (other.vendorProduct != null)
				return false;
		} else if (!vendorProduct.equals(other.vendorProduct))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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
		return "ConsumptionForm [vendorId=" + vendorId + ", sellerId=" + sellerId + ", subSellerId=" + subSellerId
				+ ", uuid=" + uuid + ", name=" + name + ", phoneNo=" + phoneNo + ", emailId=" + emailId + ", tin=" + tin
				+ ", vatNo=" + vatNo + ", pan=" + pan + ", gst=" + gst + ", vendorProduct=" + vendorProduct
				+ ", ballance=" + ballance + ", serviceTaxNo=" + serviceTaxNo + ", contactPerson=" + contactPerson
				+ ", contactPhone=" + contactPhone + ", contactEmail=" + contactEmail + ", billingAddress="
				+ billingAddress + ", billingCountryId=" + billingCountryId + ", billingStateId=" + billingStateId
				+ ", billingCityId=" + billingCityId + ", billingPinCode=" + billingPinCode + ", shippingAddress="
				+ shippingAddress + ", shippingCountryId=" + shippingCountryId + ", shippingStateId=" + shippingStateId
				+ ", shippingCityId=" + shippingCityId + ", shippingPinCode=" + shippingPinCode + ", createdDate="
				+ createdDate + ", lastModifiedDate=" + lastModifiedDate + ", pageFrom=" + pageFrom + ", pageTo="
				+ pageTo + ", limit=" + limit + ", length=" + length + ", fromDate=" + fromDate + ", toDate=" + toDate + ", type=" + type +"]";
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	//Setter and Getter Method 
	//---------------------------------------------------------------------------------------------------------------------

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getServiceTaxNo() {
		return serviceTaxNo;
	}

	public void setServiceTaxNo(String serviceTaxNo) {
		this.serviceTaxNo = serviceTaxNo;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingCountryId() {
		return billingCountryId;
	}

	public void setBillingCountryId(String billingCountryId) {
		this.billingCountryId = billingCountryId;
	}

	public String getBillingStateId() {
		return billingStateId;
	}

	public void setBillingStateId(String billingStateId) {
		this.billingStateId = billingStateId;
	}

	public String getBillingCityId() {
		return billingCityId;
	}

	public void setBillingCityId(String billingCityId) {
		this.billingCityId = billingCityId;
	}

	public String getBillingPinCode() {
		return billingPinCode;
	}

	public void setBillingPinCode(String billingPinCode) {
		this.billingPinCode = billingPinCode;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCountryId() {
		return shippingCountryId;
	}

	public void setShippingCountryId(String shippingCountryId) {
		this.shippingCountryId = shippingCountryId;
	}

	public String getShippingStateId() {
		return shippingStateId;
	}

	public void setShippingStateId(String shippingStateId) {
		this.shippingStateId = shippingStateId;
	}

	public String getShippingCityId() {
		return shippingCityId;
	}

	public void setShippingCityId(String shippingCityId) {
		this.shippingCityId = shippingCityId;
	}

	public String getShippingPinCode() {
		return shippingPinCode;
	}

	public void setShippingPinCode(String shippingPinCode) {
		this.shippingPinCode = shippingPinCode;
	}

	public String getVendorProduct() {
		return vendorProduct;
	}

	public void setVendorProduct(String vendorProduct) {
		this.vendorProduct = vendorProduct;
	}

	public String getBallance() {
		return ballance;
	}

	public void setBallance(String ballance) {
		this.ballance = ballance;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
