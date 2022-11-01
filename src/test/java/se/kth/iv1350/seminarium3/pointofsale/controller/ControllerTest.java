package se.kth.iv1350.seminarium3.pointofsale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.seminarium3.pointofsale.exceptions.ItemNotFoundException;
import se.kth.iv1350.seminarium3.pointofsale.exceptions.ServerConnectionFailedException;
import se.kth.iv1350.seminarium3.pointofsale.integration.AccountingDatabase;
import se.kth.iv1350.seminarium3.pointofsale.model.Sale;
import se.kth.iv1350.seminarium3.pointofsale.integration.Printer;
import se.kth.iv1350.seminarium3.pointofsale.controller.Controller;
import org.junit.jupiter.api.Assertions;
import se.kth.iv1350.seminarium3.pointofsale.DTO.ItemDTO;
import se.kth.iv1350.seminarium3.pointofsale.integration.InventoryDatabase;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Sale sale;
    private Printer printer;
    private Controller controller;


    @BeforeEach
    void setUp() {
        this.sale = new Sale();
        this.printer = new Printer();
        this.controller = new Controller(this.printer);
    }

    @AfterEach
    void tearDown() {
        this.sale = null;
        this.printer = null;
        this.controller = null;
    }

    @Test
    void startSaleTest() {
        int expectedNumberOfGoodsAtStartOfSale = 0;
        Assertions.assertEquals(expectedNumberOfGoodsAtStartOfSale, this.sale.getTotalNumberOfGoods());
    }

    @Test
    void addItemToSaleTest() {
        InventoryDatabase inventoryDatabase = InventoryDatabase.getInstance();
        ItemDTO itemDTO = null;

        try {
            itemDTO = inventoryDatabase.fetchItemInformation(1);
        } catch (ServerConnectionFailedException | ItemNotFoundException exception_1) {
            Assertions.fail(exception_1.getMessage());
        }
        this.sale.addItemToSale(itemDTO);
        int expectedQuantityAfterScanningOneItem = 1;
        Assertions.assertEquals(expectedQuantityAfterScanningOneItem, this.sale.getTotalNumberOfGoods());
    }

   @Test
   void addItemToSaleThrowServerConnectionFailedExceptionTest() {
        InventoryDatabase inventoryDatabase = InventoryDatabase.getInstance();
        ItemDTO itemDTO = null;

        try {
            itemDTO = inventoryDatabase.fetchItemInformation(3);
            Assertions.fail("ServerConnectionFailedException should have been thrown, item was found.");
        } catch (ItemNotFoundException exception_1) {
            Assertions.fail("ItemNotFoundException should have been thrown, item was not found");
        } catch (ServerConnectionFailedException exception_2) {

        }
   }

   @Test
   void addItemToSaleThrowItemNotFoundExceptionTest() {
        InventoryDatabase inventoryDatabase = InventoryDatabase.getInstance();
        ItemDTO itemDTO = null;

        try {
            itemDTO = inventoryDatabase.fetchItemInformation(0);
            Assertions.fail("ServerConnectionFailedException should've been thrown, item was found");
        } catch (ItemNotFoundException exception_1) {
        } catch (ServerConnectionFailedException exception_2) {
          Assertions.fail("ItemNotFoundException should have been thrown, item was not found");
        }

   }



    @Test
    void endSaleTest() {
        int expectedTotalCostForPurchase = 12;
        this.addCucumberToSale();
        Assertions.assertEquals(expectedTotalCostForPurchase, this.sale.endSale(), "Wrong total");

    }



    @Test
    void receivesPaymentUpdateExtAccountingDatabaseTest() {
        AccountingDatabase accountingDatabase = new AccountingDatabase();
        this.addCucumberToSale();
        int amountPaidByCustomer = 70;
        accountingDatabase.updateAccountingDatabase(this.sale, amountPaidByCustomer);
        int expectedAmountInSystemAfterSalePurchase = 20;
        Assertions.assertEquals(expectedAmountInSystemAfterSalePurchase, accountingDatabase.getBalanceInCashRegister());
    }


    void addCucumberToSale() {
        int itemID = 1;
        int itemPrice = 10;
        int itemTax = 2;
        String itemName = "Cucumber";
        String itemDescription = "Food";
        ItemDTO itemDTO = new ItemDTO (itemID, itemPrice, itemTax, itemName, itemDescription);
        this.sale.addItemToSale(itemDTO);

    }

}