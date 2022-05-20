package service_architecture.service;

import service_architecture.model.Client;
import service_architecture.model.Event;
import service_architecture.model.Organiser;
import service_architecture.model.Venue;

import java.util.ArrayList;
import java.util.Scanner;

public class SelectOrganiser {
    private final Organiser organiser;
    private ArrayList<Organiser> organisers;
    private ArrayList<Client> clients;
    private ArrayList<Venue> venues;
    private ArrayList<Event> events;

    public void main() {
        System.out.println("*");
        System.out.println("*");
        System.out.println("*");
        System.out.println("Welcome, " + this.getOrganiser().getName() + " !");
        System.out.println("Balance: " + this.getOrganiser().getBalance() + "$");
        System.out.println("1. Manage Events");
        System.out.println("2. Create new event");
        System.out.println("3. Modify account details");
        System.out.println("0. Log out");
        System.out.println("Enter your input...");
        int option;
        Scanner scan = new Scanner(System.in);
        option = scan.nextInt();
        switch(option) {
            case 1: {
                // manage events
                this.ShowEvents();
                this.main();
                break;
            }
            case 2: {
                // create event
                CreateEvent ce = new CreateEvent(this.getVenues(), this.getOrganiser());
                ArrayList<Event> events = this.getEvents();
                events.add(ce.create());
                this.setEvents(events);
                this.main();
                break;
            }
            case 3: {
                // modify organiser details
                EditOrganiser.edit(this.getOrganiser(), this.getEvents(), this.getClients(), this.getOrganisers());
                this.main();
                break;
            }
            case 0: {
                // log out
                break;
            }
            default : {
                // incorrect
                System.out.println("IMPOSSIBLE OPERATION");
                this.main();
            }
        }
    }

    private void ShowEvents() {
        ArrayList<Event> organiserEvents = new ArrayList<Event>();
        for(Event e : this.getEvents()) {
            if(e.getOrganiser() == this.getOrganiser().hashCode()) {
                organiserEvents.add(e);
            }
        }

        System.out.println("*");
        System.out.println("*");
        System.out.println("MANAGE EVENTS");
        if(organiserEvents.size() == 0) System.out.println("You do not manage any events!");
        for(Event e : organiserEvents) {
            System.out.println((organiserEvents.indexOf(e) + 1) + ". " + e.getName() + " - " + e.getDate());
        }
        System.out.println("0. PREVIOUS MENU");
        System.out.println("Enter your input...");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if(option > 0) {
            try {
                Event event = organiserEvents.get(option - 1);
                ManageEvent me = new ManageEvent(event, this.getClients(), this.getOrganisers(), this.getOrganiser(), this.getVenues());
                me.manage();
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Error: " + e);
                return;
            }
        }
    }

    public SelectOrganiser(Organiser organiser, ArrayList<Organiser> organisers, ArrayList<Client> clients, ArrayList<Venue> venues, ArrayList<Event> events) {
        this.organiser = organiser;
        this.organisers = organisers;
        this.clients = clients;
        this.venues = venues;
        this.events = events;
    }

    public Organiser getOrganiser() {
        return organiser;
    }

    public ArrayList<Organiser> getOrganisers() {
        return organisers;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
