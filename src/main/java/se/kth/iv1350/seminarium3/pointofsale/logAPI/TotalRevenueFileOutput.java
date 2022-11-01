package se.kth.iv1350.seminarium3.pointofsale.logAPI;

import se.kth.iv1350.seminarium3.pointofsale.integration.observer.Observer;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * Writes out total revenue to the text file of the package
 */
public class TotalRevenueFileOutput implements Observer {

    public TotalRevenueFileOutput() {

    }
    /**
     * Writes over the total revenue to the created text file
     * @param totalRevenue is the total revenue
     */
    @Override
    public void update(int totalRevenue) {
        try { BufferedWriter writer = new BufferedWriter (new
                FileWriter("totalRevenueNew.txt"));
            writer.write("Total Revenue:" + totalRevenue);
            writer.write("\n");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
