package se.kth.iv1350.seminarium3.pointofsale.exceptions;

/**
 *  This exception is thrown when the scanned item does not have a specific itemID
 *  in the point of sale database
 */

public class ItemNotFoundException extends Exception {

    public ItemNotFoundException(String message) {
        super(message);

    }


}
