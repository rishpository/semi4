package se.kth.iv1350.seminarium3.pointofsale.view;

import se.kth.iv1350.seminarium3.pointofsale.controller.Controller;
import se.kth.iv1350.seminarium3.pointofsale.logAPI.TotalRevenueFileOutput;

/**
 * A placeholder class for the view.
 */
public class View {
    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;

        contr.attach(new TotalRevenueView(), new TotalRevenueFileOutput());
    }
}
