package service_architecture.service;

import service_architecture.model.Venue;
import service_architecture.service.audit.AuditLog;

import java.util.Scanner;

public class CreateVenue {
    public Venue CreateVenue() {
        String name;
        String address;
        Integer capacity;
        Boolean opened;

        System.out.println("*");
        System.out.println("NEW VENUE");
        System.out.println("What's the name of the venue?");
        System.out.println("Enter your input: ");
        String line;
        Integer option;
        Scanner scan = new Scanner(System.in);
        line = scan.nextLine();
        name = line;

        System.out.println("What's the address of the venue?");
        System.out.println("Enter your input: ");
        line = scan.nextLine();
        address = line;
        address = address.replace(",",""); // delete all the commas if there are any

        System.out.println("What's the capacity of the venue?");
        System.out.println("Enter your input: ");
        option = scan.nextInt();
        capacity = option;

        System.out.println("Is the venue open? (true/false)");
        System.out.println("Enter your input: ");
        opened = scan.nextBoolean();
        Venue venue = new Venue(name, address, capacity, opened);
        AuditLog.getInstance().log("Created venue " + venue.getName());
        return venue;
    }
}
