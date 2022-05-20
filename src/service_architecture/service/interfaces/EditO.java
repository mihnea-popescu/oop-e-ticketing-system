package service_architecture.service.interfaces;

import service_architecture.model.Client;
import service_architecture.model.Event;
import service_architecture.model.Organiser;
import service_architecture.model.Ticket;

import java.util.ArrayList;
import java.util.Scanner;

public interface EditO {
    public static Organiser edit(Organiser organiser, ArrayList<Event> events, ArrayList<Client> clients, ArrayList<Organiser> organisers) {
            Integer prevHash = organiser.hashCode();

            System.out.println("*");
            System.out.println("*");
            System.out.println("EDIT ORGANISER ACCOUNT");
            System.out.println("1. Name: " + organiser.getName());
            System.out.println("2. E-mail: " + organiser.getEmail());
            System.out.println("3. Company: " + organiser.getCompany());
            System.out.println("0. Save changes");
            System.out.println("Enter your input: ");
            Scanner scan = new Scanner(System.in);
            int option;
            String line;
            option = scan.nextInt();
            scan.nextLine();
            while(option != 0) {
             if(option == 1) {
                 // name
                 System.out.println("*");
                 System.out.println("Enter your new name: ");
                 line = scan.nextLine().replace(",","");
                 organiser.setName(line);
                 System.out.println("NAME UPDATED!");
             }
             else if(option == 2) {
                 // email
                 System.out.println("*");
                 System.out.println("Enter your new e-mail address: ");
                 line = scan.nextLine().replace(",","");
                 organiser.setEmail(line);
                 System.out.println("E-MAIL UPDATED!");
             }
             else if(option == 3) {
                 // company
                 System.out.println("*");
                 System.out.println("Enter your new company: ");
                 line = scan.nextLine().replace(",","");
                 organiser.setCompany(line);
                 System.out.println("COMPANY UPDATED!");
             }
             else {
                 // wrong answer
                 System.out.println("INVALID INPUT!");
             }
            System.out.println("*");
            System.out.println("EDIT ORGANISER ACCOUNT");
            System.out.println("1. Name: " + organiser.getName());
            System.out.println("2. E-mail: " + organiser.getEmail());
            System.out.println("3. Company: " + organiser.getCompany());
            System.out.println("0. Save changes");
            System.out.println("Enter your input: ");
            option = scan.nextInt();
            scan.nextLine();
        }

        // Update all events
        for(Event event : events) {
            if(event.getOrganiser() == prevHash) {
                Integer prevHashEvent = event.hashCode();
                event.setOrganiser(organiser.hashCode());

                // Update the clients' tickets
                for(Client c : clients) {
                    for(Ticket t : c.getTickets()) {
                        if(t.getEvent() == prevHashEvent) {
                            t.setEvent(event.hashCode());
                        }
                    }
                }

                // Update the organisers' tickets
                for(Organiser o : organisers) {
                    for(Ticket t : o.getTickets()) {
                        if(t.getEvent() == prevHashEvent) {
                            t.setEvent(event.hashCode());
                        }
                    }
                }
            }
        }

        return organiser;
    }
}
