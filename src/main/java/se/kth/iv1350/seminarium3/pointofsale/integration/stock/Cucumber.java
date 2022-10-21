package se.kth.iv1350.seminarium3.pointofsale.integration.stock;

import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;

public class Cucumber implements Product {

    private ItemDTO cucumberDTO;

    /**
     * Constructor for the product class of Cucumber item
     * with parameters for item's ID, price, tax, name and description
     */
    public Cucumber(int itemID, int itemPrice, int itemTax, String itemName, String itemDescription) {
        cucumberDTO = new ItemDTO(itemID, itemPrice, itemTax, itemName, itemDescription);

    }

    /**
     * Fetches the DTO for the class that creates the cucumber product
     * @return DTO for cucumber product
     */
    @Override
    public ItemDTO getDTO() {
        return cucumberDTO;
    }
}
