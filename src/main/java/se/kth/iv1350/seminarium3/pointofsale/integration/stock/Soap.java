package se.kth.iv1350.seminarium3.pointofsale.integration.stock;

import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;
public class Soap implements Product {

    private ItemDTO soapDTO;

    /**
     * Constructor for the product class of Soap item
     * with parameters for item's ID, price, tax, name and description
     */
    public Soap (int itemID, int itemPrice, int itemTax, String itemName, String itemDescription) {
        soapDTO = new ItemDTO(itemID, itemPrice, itemTax, itemName, itemDescription);
    }

    /**
     * Fetches the DTO for the class that creates the soap product
     * @return DTO for soap product
     */
    @Override
    public ItemDTO getDTO() {
        return soapDTO;
    }

}
