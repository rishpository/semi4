package se.kth.iv1350.seminarium3.pointofsale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.seminarium3.pointofsale.exceptions.ServerConnectionFailedException;
import se.kth.iv1350.seminarium3.pointofsale.exceptions.ItemNotFoundException;
import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;
import se.kth.iv1350.seminarium3.pointofsale.model.Sale;


class InventoryDatabaseTest {
    private InventoryDatabase inventoryDatabase;
    private Sale sale;

    @BeforeEach
    void setUp() {
        this.inventoryDatabase = new InventoryDatabase();
        this.sale = new Sale();
    }

    @AfterEach
    void tearDown() {
        this.inventoryDatabase = null;
        this.sale = null;
    }

    @Test
    void fetchCucumberDTOInformationTest() {
        ItemDTO itemDTO = null;

        try {
            itemDTO = this.inventoryDatabase.fetchItemInformation(1);
        } catch (ItemNotFoundException | ServerConnectionFailedException e) {
            Assertions.fail(e.getMessage());
        }
        String expectedName = "Cucumber";
        Assertions.assertEquals(expectedName, itemDTO.getItemName(), "Wrong Item DTO name");
    }

    @Test
    void fetchSoapDTOInformationTest() {
        ItemDTO itemDTO = null;

        try {
            itemDTO = this.inventoryDatabase.fetchItemInformation(2);
        } catch (ItemNotFoundException | ServerConnectionFailedException e) {
            Assertions.fail(e.getMessage());
        }

        String expectedName = "Soap";
        Assertions.assertEquals(expectedName, itemDTO.getItemName());

    }
}