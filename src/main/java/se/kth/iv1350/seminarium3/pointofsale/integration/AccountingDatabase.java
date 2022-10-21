package se.kth.iv1350.seminarium3.pointofsale.integration;

import se.kth.iv1350.seminarium3.pointofsale.integration.observer.Observer;
import se.kth.iv1350.seminarium3.pointofsale.model.Sale;
import java.util.ArrayList;
import java.util.List;

/**
 * Calls to external accounting system.
 * The ensuing information is hardcoded for this application
 */

public class AccountingDatabase {
    private List<Observer> observers = new ArrayList<Observer>();
    private int balanceInCashRegister;

    public AccountingDatabase() {
    }

    public void attach (Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(balanceInCashRegister);
        }
    }

    /**
     * This method shows you the balance in the cash register
     * @return
     */
    public int getBalanceInCashRegister() {
        return this.balanceInCashRegister;
    }

    /**
     * Updates information in the system for the sale
     * @param sale
     * @param amountPayment is the amount that is paid by the customer
     */
    public void updateAccountingDatabase(Sale sale, int amountPayment) {
        if (amountPayment >= sale.getTotalPriceAndTax()) {
            this.balanceInCashRegister += sale.getTotalPrice() + sale.getTaxForEntirePurchase();
        }
    }

}
