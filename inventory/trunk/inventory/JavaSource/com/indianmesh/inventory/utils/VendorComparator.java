package com.indianmesh.inventory.utils;

import java.util.Comparator;

import com.indianmesh.inventory.kitchen.vendor.VendorFormVO;

public class VendorComparator {

	public Comparator<VendorFormVO> vendorIdComparator = new Comparator<VendorFormVO>() {

		public int compare(VendorFormVO vo1, VendorFormVO vo2) {
			 int vi1 = Integer.parseInt(vo1.getVendorId());
			 int vi2 = Integer.parseInt(vo2.getVendorId());

			 //ascending order
			 return vi1 - vi2;
			   
			 /*For descending order*/
			 //return vi2 - vi1;
	}};

}
