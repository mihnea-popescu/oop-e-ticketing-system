package service_architecture.service.interfaces;

import service_architecture.model.Organiser;

import java.util.Scanner;

public interface CreateO {
    public static Organiser create() {
        String name;
        String email;
        String company;
        System.out.println("*");
        System.out.println("NEW ORGANISER");
        System.out.println("What's the name of the organiser?");
        System.out.println("Enter your input: ");
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine().replace(",","");

        System.out.println("What's the email of the organiser?");
        System.out.println("Enter your input: ");
        email = scan.nextLine().replace(",","");

        System.out.println("What's the company of the organiser?");
        System.out.println("Enter your input: ");
        company = scan.nextLine().replace(",","");

        Organiser o = new Organiser();
        o.setName(name);
        o.setEmail(email);
        o.setCompany(company);
        o.setBalance(0);
        return o;
    };
}
