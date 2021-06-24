package com.indianmesh.inventory.utils;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class PrinterPropertyPrint {

	public static void main(String[] args) throws PrinterException {
		// TODO Auto-generated method stub
		
		PrinterJob pj = PrinterJob.getPrinterJob();
	    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
	    System.out.println("Number of printers configured: " + printServices.length);
	    for (PrintService printer : printServices) {
	        System.out.println("Printer: " + printer.getName());
	        if (printer.getName().equals("***MYPRINTER***")) {
	            try {
	                pj.setPrintService(printer);
	            } catch (PrinterException ex) {
	            }
	        }
	    }
	    
		//-----------------------------------------------------------
		/*PrinterJob pjob = PrinterJob.getPrinterJob();
	        PageFormat pf = pjob.defaultPage();
	        pjob.setPrintable(null, pf);

	        if (pjob.printDialog()) {
	          pjob.print();
	        }*/
	    
	        //----------------------------------------
		

	}

}
