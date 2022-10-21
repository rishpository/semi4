package se.kth.iv1350.seminarium3.pointofsale.startup;

import se.kth.iv1350.seminarium3.pointofsale.exceptions.ItemNotFoundException;
import se.kth.iv1350.seminarium3.pointofsale.exceptions.ServerConnectionFailedException;
import se.kth.iv1350.seminarium3.pointofsale.controller.Controller;
import se.kth.iv1350.seminarium3.pointofsale.integration.Printer;
import se.kth.iv1350.seminarium3.pointofsale.view.View;
import java.util.Scanner;


/**
 * This class contains the main method that needs to be run
 * in order to start the whole application
 */
public class Main {


    public static void main(String[] args) {
        Printer printer = new Printer();
        Controller contr = new Controller(printer);
        View view = new View(contr);
        String answerForSale = " ";
        String shutDownSystem = "No";

        while (!shutDownSystem.equals("Yes")) {
            contr.startSale();
            answerForSale = " ";
            while (!answerForSale.equals("Terminate")) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Press 1 for cucumber");
                System.out.println("Press 2 for soap");
                System.out.println("Type Terminate to end the sale");
                answerForSale = sc.nextLine();

                switch(answerForSale) {
                    case "1":
                        try {
                            contr.addItemToSale(1);
                        } catch (ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Log: ItemNotFoundException was thrown due to " + e.getMessage());
                        } catch (ServerConnectionFailedException p){
                            System.out.println(p.getMessage());
                            System.out.println("Log: Server connection issue." +
                                    " Item was not added to sale.");
                        }
                          break;

                    case "2":
                        try {
                            contr.addItemToSale(2);
                        } catch (ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Log: ItemNotFoundException was thrown due to " + e.getMessage());
                        } catch (ServerConnectionFailedException p) {
                            System.out.println(p.getMessage());
                            System.out.println("Log: Server connection issue." +
                                    " Item was not added to sale.");
                    }
                          break;

                    case "3":
                        try {
                            contr.addItemToSale(3);
                        } catch (ItemNotFoundException e){
                            System.out.println(e.getMessage());
                            System.out.println("Log: ItemNotFoundException was thrown due to " + e.getMessage());
                        } catch (ServerConnectionFailedException p) {
                            System.out.println(p.getMessage());
                            System.out.println("Log: Server connection issue." +
                                    " Item was not added to sale.");
                    }
                          break;

                    case "Terminate":
                        int totalPriceWithTax = contr.endSale();
                        int totalPayment = 0;

                        System.out.println("The total cost is: " + totalPriceWithTax + "SEK.");
                        System.out.println("Enter payment amount:");
                        int payment = sc.nextInt();
                        totalPayment += payment;

                        if (payment < totalPriceWithTax)
                        {
                            totalPriceWithTax -= payment;
                            while(totalPriceWithTax > 0) {
                                System.out.println("Payment is not enough. Please enter more money.");
                                payment = sc.nextInt();
                                totalPayment += payment;
                                totalPriceWithTax -= payment;
                            }
                        }
                        contr.receivePayment(totalPayment);
                        break;

                    default:
                        try {
                            contr.addItemToSale(0);
                        } catch (ItemNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Log: ItemNotFoundException was thrown due to " + e.getMessage());
                        } catch (ServerConnectionFailedException p) {
                            System.out.println(p.getMessage());
                            System.out.println("Log: Server connection issue." +
                                    " Item was not added to sale.");
                        }
                        break;
                }
            }

            System.out.println("Shut off system? Type Yes or No");
            Scanner stringScanner = new Scanner(System.in);
            shutDownSystem = stringScanner.nextLine();
        }
    }
}

