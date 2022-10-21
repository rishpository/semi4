package se.kth.iv1350.seminarium3.pointofsale.integration.stock;

import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;
import java.util.List;
import java.util.ArrayList;

/**
 *
 */

public class Stock {
    private Cucumber cucumber;
    private Soap soap;
    private List <Product> products = new ArrayList<Product>();

    /**
     * Class constructor
     */
    public Stock() {
        cucumber = new Cucumber(1, 10, 2, "Cucumber", "Food");
        soap = new Soap(2, 20, 6, "Soap", "Utilities");
        products.add(cucumber);
        products.add(soap);
    }

    private int getitemIDOfCurrentCheckedItem(int i) {
        return products.get(i).getDTO().getItemID();
    }

    /**
     * Looks for product DTO with a matching item ID
     * @param itemID is the searched product's ID
     * @return the product's DTO with matching ID as the ID parameter
     */
    public ItemDTO getProduct(int itemID) {
        for (int i = 0; i < products.size(); i++) {
            if (getitemIDOfCurrentCheckedItem(i) == itemID)
                return products.get(i).getDTO();
        }

        return null;

    }

}
