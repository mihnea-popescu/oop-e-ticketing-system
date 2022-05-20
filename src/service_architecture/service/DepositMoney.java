package service_architecture.service;

import service_architecture.model.Client;
import service_architecture.service.audit.AuditLog;
import service_architecture.service.exceptions.NotMoneyNumber;

import java.util.Scanner;

import static service_architecture.service.SelectClient.validateMoney;

public class DepositMoney {

    public static void processTransaction(Client client) {
        int amount = 0;
        System.out.println("*");
        System.out.println("*");
        System.out.println("*");
        System.out.println("Type the amount of money you want to add (positive integer values only, type 0 to cancel): ");
        Scanner scan = new Scanner(System.in);
        amount = scan.nextInt();
        if(amount == 0) {
            return;
        }
        else {
            try {
                validateMoney(amount);
            } catch (NotMoneyNumber e) {
                System.out.println("Error: " + e);
                return;
            }

            String cardNumber = "", expirationDate = "", cvv = "";
            System.out.println("Type your debit/credit card number (e.g. 1234-5678-9012-3456): ");
            scan.nextLine();
            cardNumber = scan.nextLine();
            while(cardNumber.length() != 19) {
                System.out.println("*");
                System.out.println("INVALID CREDIT CARD NUMBER!");
                System.out.println("Type your debit/credit card number (Ex: 1234-5678-9012-3456): ");
                cardNumber = scan.nextLine();
            }

            System.out.println("Type your debit/credit card expiraton date (e.g. 07/23): ");
            expirationDate = scan.nextLine();
            while(expirationDate.length() != 5) {
                System.out.println("*");
                System.out.println("INVALID EXPIRATION DATE!");
                System.out.println("Type your debit/credit card expiraton date (e.g. 07/23): ");
                expirationDate = scan.nextLine();
            }

            System.out.println("Type the last 3 digits from the back of your Credit Card (CVV, e.g. 012): ");
            cvv = scan.nextLine();
            while(cvv.length() != 3) {
                System.out.println("*");
                System.out.println("INVALID CVV!");
                System.out.println("Type the last 3 digits from the back of your Credit Card (CVV, e.g. 012): ");
                cvv = scan.nextLine();
            }

            client.setBalance(client.getBalance() + amount);
            System.out.println("TRANSACTION SUCCESSFUL!");
            System.out.println(amount + "$ have been added to your account.");
            AuditLog.getInstance().log("User " + client.getName() + " added " + amount + "$ in his balance.");
        }
    }
}
