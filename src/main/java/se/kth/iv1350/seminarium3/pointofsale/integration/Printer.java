package se.kth.iv1350.seminarium3.pointofsale.integration;

import se.kth.iv1350.seminarium3.pointofsale.model.Receipt;

/**
 * The printer that prints out the sales information
 * from the current sale into a receipt.
 */
public class Printer {

    public Printer() {
    }

    /**
     * This method tells the printer to print a copy of
     * the current sales information onto a receipt
     * @param receipt current receipt of a current sale
     */
    public void printReceipt(Receipt receipt) {
        System.out.println(" ");
        System.out.println("*********************************");
        System.out.println("          Cash Receipt");
        System.out.println("*********************************");
        System.out.println("Cucumbers: " + receipt.getNumberOfCucumbers());
        System.out.println("Soap: " + receipt.getNumberOfSoap());
        System.out.println("Total price: " + receipt.getTotalPrice());
        System.out.println("Price incl. tax: " + receipt.getTotalPriceAndTax());
        System.out.println(" ");
        System.out.println("Change: " + receipt.getAmountChange());
        System.out.println("*********************************");
        System.out.println("Bank card     ---- ---- ---- 1234");
        System.out.println("*********************************");
        System.out.println("            Thank you!");
    }
}
