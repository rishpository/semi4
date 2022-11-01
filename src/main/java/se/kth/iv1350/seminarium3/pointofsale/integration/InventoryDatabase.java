package se.kth.iv1350.seminarium3.pointofsale.integration;

import se.kth.iv1350.seminarium3.pointofsale.exceptions.ServerConnectionFailedException;
import se.kth.iv1350.seminarium3.pointofsale.exceptions.ItemNotFoundException;
import se.kth.iv1350.seminarium3.pointofsale.integration.stock.Stock;
import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;
import se.kth.iv1350.seminarium3.pointofsale.model.Sale;
import java.util.ArrayList;
import java.lang.String;

/**
 * Class that calls to external inventory system
 *
 */
public class InventoryDatabase {
    private int stockOfCucumbers = 100;
    private int stockOfSoap = 100;
    private Stock stock;


    private static final InventoryDatabase instanceOfInventoryDatabase = new InventoryDatabase();
    public InventoryDatabase() {
        stock = new Stock();
    }

    public static InventoryDatabase getInstance() {
        return instanceOfInventoryDatabase;
    }
    public int getStockOfCucumbers() {
        return this.stockOfCucumbers;
    }

    public int getStockOfSoap() {
        return this.stockOfSoap;
    }

    public ItemDTO fetchItemInformation(int scannedItemID) throws
            ItemNotFoundException, ServerConnectionFailedException {
        int itemID;
        int itemPrice;
        int itemTax;
        String itemName;
        String itemDescription;
        ItemDTO itemDTO;

        switch(scannedItemID) {
            case 1:
                itemName = "Cucumber";
                itemID = 1;
                itemDescription = "Food";
                itemPrice = 10;
                itemTax = 1;
                itemDTO = new ItemDTO(itemID, itemPrice, itemTax, itemName, itemDescription);
                break;
            case 2:
                itemName = "Soap";
                itemID = 2;
                itemDescription = "Utilities";
                itemPrice = 20;
                itemTax = 6;
                itemDTO = new ItemDTO(itemID, itemPrice, itemTax, itemName, itemDescription);
                break;
            case 3:
                throw new ServerConnectionFailedException("The server is currently not responding. " +
                        "Please try again later.");
            default:
                throw new ItemNotFoundException("Item with item ID: " + scannedItemID + " does not exist. " +
                        "Please type in another ID.");


        }

        return itemDTO;
    }

    /**
     * Updates the inventory database
     * @param sale is the current sale
     */
    public void updateInventoryDatabase(Sale sale, int amountPayment) {
        if (sale.getTotalPriceAndTax() <= amountPayment) {
            ArrayList<ItemDTO> NamesOfItemsInTheSale = sale.getCopyOfItemDTOList();

            for(int i = 0; i < NamesOfItemsInTheSale.size(); ++i) {
                if (((ItemDTO)NamesOfItemsInTheSale.get(i)).getItemName().equals("Cucumber")) {
                    --this.stockOfCucumbers;
                }
                if(((ItemDTO)NamesOfItemsInTheSale.get(i)).getItemName().equals("Soap")) {
                    --this.stockOfSoap;
                }

            }
        }
    }
}
