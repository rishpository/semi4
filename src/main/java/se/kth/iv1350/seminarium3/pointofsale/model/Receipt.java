package se.kth.iv1350.seminarium3.pointofsale.model;

import java.time.LocalTime;
import java.util.ArrayList;
import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;

/**
 * Documented proof of transaction
 * which also has information about the sale on it.
 */
public class Receipt {
    private int totalPrice;
    private int totalNumberOfGoods;
    private int amountChange;
    private int totalPriceAndTax;
    private int stockOfCucumbers;
    private int stockOfSoap;
    private int totalTax;
    private LocalTime timeOfSale;
    private ArrayList<ItemDTO> itemDTOArrayList = new ArrayList();

    public Receipt(LocalTime timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    public int getAmountChange() {
        return this.amountChange;
    }

    public int getNumberOfCucumbers() {
        return this.stockOfCucumbers;
    }

    public int getNumberOfSoap() {
        return this.stockOfSoap;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }

    public int getTotalTax() {
        return this.totalTax;
    }


    public int getTotalNumberOfGoods() {
        return this.totalNumberOfGoods;
    }

    public int getLastItemPriceInList() {
        return ((ItemDTO) this.itemDTOArrayList.get(this.itemDTOArrayList.size() - 1)).getItemPrice();
    }

    public String getLastItemNameInList() {
        return ((ItemDTO) this.itemDTOArrayList.get(this.itemDTOArrayList.size() - 1)).getItemName();
    }

    /**
     * Total price and tax for entire purchase
     * @return total price + vat for purchase
     */
    public int getTotalPriceAndTax() {
        return this.totalPriceAndTax;
    }

    /**
     * Updating receipt with new item that has not been scanned
     * in the ongoing sale, as well as the information on said item
     * @param itemDTO is the item that is added to the receipt.
     */
    public void addNewItemToReceipt (ItemDTO itemDTO) {
        this.totalPrice += itemDTO.getItemPrice();
        ++this.totalNumberOfGoods;
        this.itemDTOArrayList.add(itemDTO);
        this.totalTax += itemDTO.getItemTax();
        this.totalPriceAndTax += itemDTO.getItemPrice() + itemDTO.getItemTax();
    }

    public void setAmountChange(int amountChange) {
        this.amountChange = amountChange;
    }

    public void addStockOfCucumbers() {
        this.stockOfCucumbers++;
    }

    public void addStockOfSoap() {
        this.stockOfSoap++;
    }

    /**
     * Adds item that has already been scanned to the receipt
     * @param itemDTO item that is currently being scanned that
     *                is being added to the receipt
     */

    public void addSameItemToReceipt(ItemDTO itemDTO) {
        this.totalNumberOfGoods += 1;
        this.totalTax += itemDTO.getItemTax();
        this.totalPriceAndTax += itemDTO.getItemPrice() + itemDTO.getItemTax();
        this.totalPrice += itemDTO.getItemPrice();
    }


}
