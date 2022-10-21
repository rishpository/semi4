package se.kth.iv1350.seminarium3.pointofsale.DTO;

/**
 * An Item DTO is created to make the lists
 * of parameters shorter
 */
public class ItemDTO {
    private int itemID;
    private int itemPrice;
    private int itemTax;
    private String itemName;
    private String itemDescription;

    public int getItemID() {
        return itemID;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getItemTax() {
        return itemTax;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Method instantiates values to the object.
     * @param itemID identification number of item
     * @param itemPrice Price excluding tax of item
     * @param itemTax The tax of item
     * @param itemName the Name of the item
     * @param itemDescription the description of the item.
     */
    public ItemDTO(int itemID, int itemPrice, int itemTax, String itemName, String itemDescription) {
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemTax = itemTax;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }


}
