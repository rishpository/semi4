package se.kth.iv1350.seminarium3.pointofsale.integration.stock;

import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;

/**
 * Product Interface as per the Factory Design pattern
 */
public interface Product {

    public ItemDTO getDTO();
}
