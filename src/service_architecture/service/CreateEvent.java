package service_architecture.service;

import service_architecture.model.Event;
import service_architecture.model.Organiser;
import service_architecture.model.Venue;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateEvent {
    private ArrayList<Venue> venues;
    private Organiser organiser;

    private Event event;

    public CreateEvent(ArrayList<Venue> venues, Organiser organiser) {
        this.venues = venues;
        this.organiser = organiser;
    }

    public Event create() {
        this.startMenu();

        return this.getEvent();
    }

    private void startMenu() {
        System.out.println("*");
        System.out.println("*");
        System.out.println("CREATE EVENT");
        System.out.println("Choose a venue for your event:");
        Integer venueid;
        for(Venue v : this.getVenues()) {
            if(v.getOpened()) {
                System.out.println((this.getVenues().indexOf(v) + 1) + ". " + v.getName() + " Address: " + v.getAddress() + " Capacity: " + v.getCapacity());
            }
        }
        System.out.println("Enter your input...");
        Scanner scan = new Scanner(System.in);
        int option;
        option = scan.nextInt();
        try {
            venueid = this.getVenues().get(option - 1).hashCode();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e);
            this.startMenu();
            return;
        }

        String line;
        this.setEvent(new Event());
        this.getEvent().setVenue(venueid);
        scan.nextLine();

        System.out.println("Enter the name of the event: ");
        line = scan.nextLine().replace(",","");
        this.getEvent().setName(line);

        System.out.println("Enter the date of the event: ");
        line = scan.nextLine().replace(",", "");
        this.getEvent().setDate(line);

        System.out.println("Enter the description of the event: ");
        line = scan.nextLine().replace(",", "");
        this.getEvent().setDescription(line);

        System.out.println("Enter the ticket price of the event (min. 1, integer values only): ");
        option = scan.nextInt();
        while(option < 1) {
            System.out.println("INVALID INPUT! Ticket price must be an integer with a value bigger than 0");
            System.out.println("Enter the ticket price of the event (min. 1, integer values only): ");
            option = scan.nextInt();
        }
        this.getEvent().setTicket_price(option);

        // set organiser
        this.getEvent().setOrganiser(this.getOrganiser().hashCode());
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public Organiser getOrganiser() {
        return organiser;
    }
}
