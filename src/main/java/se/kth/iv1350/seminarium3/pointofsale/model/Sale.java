package se.kth.iv1350.seminarium3.pointofsale.model;

import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;
import se.kth.iv1350.seminarium3.pointofsale.integration.Printer;

/**
 * The sale and the different parameters that make up the sale.
 */
public class Sale {
    private LocalTime timeOfSale = LocalTime.now();
    private Receipt receipt;
    private int totalPrice;
    private int totalNumberOfGoods;
    private ArrayList<ItemDTO> itemDTOArrayList = new ArrayList();
    private int amountChange;
    private int taxForEntirePurchase;
    private int totalPriceAndTax;
    private int stockOfCucumbers;
    private int stockOfSoap;

    /**
     * Object Sales constructor (instantiation of the object Sale)
     */
    public Sale() {
        this.receipt = new Receipt(this.timeOfSale);
    }

    /**
     * Getting the amount of soap units in inventory
     */
    public int getStockOfSoap() {
        return this.stockOfSoap;
    }

    /**
     * Getting the amount of cucumbers in inventory
     */
    public int getStockOfCucumbers() {
        return this.stockOfCucumbers;
    }

    /**
     * Getting the total sale price excluding taxes.
     */
    public int getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Getting the total number of goods in the sale
     */
    public int getTotalNumberOfGoods() {
        return this.totalNumberOfGoods;
    }

    /**
     * Getting the total tax for the current sale
     */
    public int getTaxForEntirePurchase() {
        return this.taxForEntirePurchase;
    }

    /**
     * Getting price of the last scanned item in list
     */
    public int getLastItemPriceInList() {
            return ((ItemDTO)this.itemDTOArrayList.get(this.itemDTOArrayList.size() - 1)).getItemPrice();
    }

    /**
     * Getting name of the last scanned item in list
     */
    public String getLastItemNameInList() {
        return ((ItemDTO)this.itemDTOArrayList.get(this.itemDTOArrayList.size() - 1)).getItemName();
    }

    /**
     * Getting total price including tax of current purchase
     */
    public int getTotalPriceAndTax() {
        return this.totalPriceAndTax;
    }

    /**
     * Making a copy of the sales name list
     * @return copy of the name list
     */
    public ArrayList<ItemDTO> getCopyOfItemDTOList() {
        ArrayList<ItemDTO> copyOfItemDTOList = new ArrayList(this.itemDTOArrayList);
        return copyOfItemDTOList;
    }

    /**
     * This method adds the currently scanned item to the sale.
     * @param itemDTO
     */
    public void addItemToSale(ItemDTO itemDTO) {
        boolean addNewItem = true;

        for (int i = 0; i < this.itemDTOArrayList.size(); ++i) {
            if (itemDTO.getItemName().equals(((ItemDTO)this.itemDTOArrayList.get(i)).getItemName())) {
                addNewItem = false;
            }
        }

        if (itemDTO.getItemName() != null && addNewItem) {
            this.addNewItemToSale(itemDTO);
        } else if (itemDTO.getItemName() != null) {
            this.addAnotherScannedItem(itemDTO);

        }

    }

    /**
     * This method ends the sale
     * @return total amount of price including tax for sale
     */
    public int endSale() {
        return this.totalPriceAndTax;
    }


    /**
     * This method calculates the change difference that should
     * be handed to the customer after the sale has been concluded
     * @param amountPayment The money paid by customer for sale
     * @return amount of change
     */
    public int calculateChange(int amountPayment) {
        int amountChange;

        if (this.isPaymentNotEnoughForPurchase(amountPayment)) {
            this.totalPriceAndTax -= amountPayment;
            amountChange = 0;
            return amountChange;
            }
         {
            amountChange = amountPayment - this.totalPriceAndTax;
            receipt.setAmountChange(amountChange);

            return amountChange;
            }

        }

    /**
     * Prints receipt of current sale
     * @param printer The object instance
     */
    public void printReceipt(Printer printer, int amountPayment) {
            if (this.isPaymentEnoughForPurchase(amountPayment)) {
                printer.printReceipt(this.receipt);
            }
        }

    /**
     * Checks if payment by customer is enough
     * to cover the sales cost
     * @param amountPayment given by customer
     * @return boolean value
     */
    private boolean isPaymentEnoughForPurchase(int amountPayment) {
            return amountPayment >= this.totalPriceAndTax;
        }


        private boolean isPaymentNotEnoughForPurchase(int amountPayment) {
            return this.totalPriceAndTax > amountPayment;
        }

    private void addAnotherScannedItem(ItemDTO itemDTO) {
            if (itemDTO.getItemName() == "Cucumber") {
                ++this.stockOfCucumbers;
            }
            else if (itemDTO.getItemName() == "Soap") {
                ++this.stockOfSoap;
            }
        }


        private void addNewItemToSale(ItemDTO itemDTO) {
            this.addAnotherScannedItem(itemDTO);
            this.itemDTOArrayList.add(itemDTO);
            this.totalPrice+= itemDTO.getItemPrice();
            ++this.totalNumberOfGoods;
            this.taxForEntirePurchase += itemDTO.getItemPrice();
            this.totalPriceAndTax += itemDTO.getItemPrice() + itemDTO.getItemTax();
            this.receipt.addNewItemToReceipt (itemDTO);
            this.addScannedItemToReceipt(itemDTO);
        }

        private void addExistingItemToSale(ItemDTO itemDTO) {
            this.addAnotherScannedItem(itemDTO);
            ++this.totalNumberOfGoods;
            this.taxForEntirePurchase += itemDTO.getItemTax();
            this.totalPriceAndTax += itemDTO.getItemPrice() + itemDTO.getItemTax();
            this.totalPrice += itemDTO.getItemPrice();
            this.addScannedItemToReceipt(itemDTO);
        }

        private void addScannedItemToReceipt(ItemDTO itemDTO) {
            if (itemDTO.getItemName() == "Cucumber") {
                this.receipt.addStockOfCucumbers();
            }
            if (itemDTO.getItemName() == "Soap") {
                this.receipt.addStockOfSoap();
            }
        }

}
