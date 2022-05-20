package service_architecture.service;

import service_architecture.model.Venue;
import service_architecture.service.audit.AuditLog;

import java.util.Scanner;

public class EditVenue {
    private Venue venue;

    public EditVenue(Venue venue) {
        this.venue = venue;
    }

    public Venue Edit() {
        menu();
        return this.getVenue();
    }

    private void menu() {
        System.out.println("*");
        System.out.println("*");
        System.out.println("*");
        System.out.println("CHOOSE ATTRIBUTE TO MODIFY");
        System.out.println("1. Name: " + this.getVenue().getName());
        System.out.println("2. Address: " + this.getVenue().getAddress());
        System.out.println("3. Capacity: " + this.getVenue().getCapacity());
        System.out.println("4. Open: " + this.getVenue().getOpened());
        System.out.println("0. Save");
        System.out.println("Enter your option: ");
        Scanner scan = new Scanner(System.in);
        Integer option;
        String line;
        option = scan.nextInt();
        switch (option) {
            case 1: {
                // name
                System.out.println("*");
                System.out.println("Enter the new name of the venue: ");
                line = scan.nextLine();
                this.getVenue().setName(line.replace(",",""));
                AuditLog.getInstance().log("Edited name of venue " + this.getVenue().getName());
                this.menu();
                break;
            }
            case 2: {
                // address
                System.out.println("*");
                System.out.println("Enter the new address of the venue: ");
                line = scan.nextLine();
                this.getVenue().setAddress(line.replace(",",""));
                AuditLog.getInstance().log("Edited address of venue " + this.getVenue().getName());
                this.menu();
                break;
            }
            case 3: {
                // capacity
                System.out.println("*");
                System.out.println("Enter the new capacity of the venue: ");
                AuditLog.getInstance().log("Edited capacity of venue " + this.getVenue().getName());
                option = scan.nextInt();
                this.getVenue().setCapacity(option);
                this.menu();
                break;
            }
            case 4: {
                // open
                System.out.println("*");
                System.out.println("Enter the new status of the venue (true/false): ");
                Boolean opt = scan.nextBoolean();
                this.getVenue().setOpened(opt);
                AuditLog.getInstance().log("Edited opened of venue " + this.getVenue().getName());
                this.menu();
                break;
            }
            default: {
                // save
                break;
            }
        }
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
