package se.kth.iv1350.seminarium3.pointofsale.view;

import se.kth.iv1350.seminarium3.pointofsale.integration.observer.Observer;

public class TotalRevenueView implements Observer {

    @Override
    public void update(int totalRevenue) {
        System.out.println(" ");
        System.out.println("The current total revenue has been updated.");
    }

}
