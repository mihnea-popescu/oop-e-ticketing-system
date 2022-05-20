package service_architecture.service;

import service_architecture.model.Client;
import service_architecture.service.audit.AuditLog;

import java.util.Scanner;

public class CreateClient {
    public Client CreateClient() {
        String name;
        String email;
        String address;
        String phone;
        System.out.println("*");
        System.out.println("NEW CLIENT");
        System.out.println("What's the name of the client?");
        System.out.println("Enter your input: ");
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine().replace(",","");

        System.out.println("What's the email of the client?");
        System.out.println("Enter your input: ");
        email = scan.nextLine().replace(",","");

        System.out.println("What's the address of the client?");
        System.out.println("Enter your input: ");
        address = scan.nextLine().replace(",","");

        System.out.println("What's the phone number of the client?");
        System.out.println("Enter your input: ");
        phone = scan.nextLine().replace(",","");

        Client client = new Client();
        client.setName(name);
        client.setAddress(address);
        client.setEmail(email);
        client.setPhone(phone);
        client.setBalance(0);

        System.out.println("Client created!");
        AuditLog.getInstance().log("Created client " + client.getName());
        return client;
    }
}
