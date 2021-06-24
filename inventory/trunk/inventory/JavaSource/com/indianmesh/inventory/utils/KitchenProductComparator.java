package com.indianmesh.inventory.utils;

import java.util.Comparator;

import com.indianmesh.inventory.kitchen.product.ProductFormVO;

public class KitchenProductComparator {

	public static Comparator<ProductFormVO> productComparator = new Comparator<ProductFormVO>() {

		public int compare(ProductFormVO po1, ProductFormVO po2) {
			 int vi1 = Integer.parseInt(po1.getProductId());
			 int vi2 = Integer.parseInt(po2.getProductId());

			 //ascending order
			 return vi1 - vi2;
			   
			 /*For descending order*/
			 //return vi2 - vi1;
	}};
}
