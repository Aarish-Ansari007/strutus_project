package com.indianmesh.inventory.utils;

import java.util.Comparator;

import com.indianmesh.inventory.kitchen.stock.ProductFormVO;

public class StockComparator {

	public Comparator<ProductFormVO> stockComparator = new Comparator<ProductFormVO>() {

		public int compare(ProductFormVO po1, ProductFormVO po2) {
			 int vi1 = Integer.parseInt(po1.getStockId());
			 int vi2 = Integer.parseInt(po2.getStockId());

			 //ascending order
			 return vi1 - vi2;
			   
			 /*For descending order*/
			 //return vi2 - vi1;
		}};
}
