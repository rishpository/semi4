package se.kth.iv1350.seminarium3.pointofsale.controller;

import se.kth.iv1350.seminarium3.pointofsale.exceptions.ItemNotFoundException;
import se.kth.iv1350.seminarium3.pointofsale.exceptions.ServerConnectionFailedException;
import se.kth.iv1350.seminarium3.pointofsale.integration.observer.Observer;
import se.kth.iv1350.seminarium3.pointofsale.logAPI.TotalRevenueFileOutput;
import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;
import se.kth.iv1350.seminarium3.pointofsale.integration.AccountingDatabase;
import se.kth.iv1350.seminarium3.pointofsale.integration.InventoryDatabase;
import se.kth.iv1350.seminarium3.pointofsale.integration.Printer;
import se.kth.iv1350.seminarium3.pointofsale.model.Sale;


/**
 * Controller class calls to the model to pass through.
 */
public class Controller {
    private Printer printer;
    private AccountingDatabase  accountingDatabase;
    private InventoryDatabase inventoryDatabase;
    private Sale sale;


    /**
     * Creates objects of classes to use in a specific instance
     * (for current sale)
     * @param printer prints receipt
     */
    public Controller(Printer printer) {
        this.printer = printer;
        accountingDatabase = new AccountingDatabase();
        inventoryDatabase = InventoryDatabase.getInstance();
    }

    /**
     * Method creates a new sale
     */
    public void startSale() {
        sale = new Sale();
    }

    /**
     * Gets item information from Inventory Database and adds the item to sale
     * @param scannedItemID
     */
    public void addItemToSale(int scannedItemID)
        throws ItemNotFoundException, ServerConnectionFailedException {
            ItemDTO itemDTO =
                    inventoryDatabase.fetchItemInformation(scannedItemID);
            sale.addItemToSale(itemDTO);
        }


    /**
     * This method ends the sale
     * @return
     */
    public int endSale() {
        return sale.endSale();
    }

    /**
     * Receives payment for sale, calculating change.
     * If payment > sale cost, external system is updated
     * and receipt is printed.
     * @param amountPayment
     * @return the amount of change the customer gets back
     */
    public int receivePayment(int amountPayment) {
        int amountChange = sale.calculateChange(amountPayment);
        updateExternalSystems(amountPayment);
        printReceipt(amountPayment);

        return amountChange;
    }

    /**
     * The external system is updated with the changes made
     * with the current sale
     */
    public void updateExternalSystems(int amountPayment) {
        this.inventoryDatabase.updateInventoryDatabase(sale, amountPayment);
        this.accountingDatabase.updateAccountingDatabase(sale, amountPayment);
    }

    /**
     * Sends the information of current sale
     * to be printed on the receipt
     */
    public void printReceipt(int amountPayment) {
        this.sale.printReceipt(printer, amountPayment);
    }

    public void attach (Observer obsView, Observer obsFile)
    {
        accountingDatabase.attach(obsView);
        accountingDatabase.attach(obsFile);
    }
}
