package service_architecture.service;

import service_architecture.model.*;
import service_architecture.model.enums.ticketType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ManageEvent {
    private Event event;
    private ArrayList<Client> clients;
    private ArrayList<Organiser> organisers;
    private Organiser organiser;

    private ArrayList<Venue> venues;

    public void manage() {
        System.out.println("*");
        System.out.println("*");
        System.out.println("*");
        System.out.println(this.getEvent().getName() + " - " + this.getEvent().getDate());
        System.out.println("1. Process tickets");
        System.out.println("2. Giveaway tickets");
        System.out.println("0. PREVIOUS MENU");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        switch(option) {
            case 1: {
                this.processTickets();
                this.manage();
                break;
            }
            case 2: {
                this.giveawayTickets();
                this.manage();
                break;
            }
            case 0: {
                break;
            }
            default: {
                System.out.println("INVALID INPUT!)");
                this.manage();
            }
        }
    }

    private void giveawayTickets() {
        System.out.println("Enter the e-mail of the user you want to giveaway the tickets: ");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        Boolean found = false;

        for(Client c : this.getClients()) {
            if(c.getEmail().equals(line)) {
                found = true;
                Ticket ticket = new Ticket();
                ticket.setPrice(0);
                ticket.setEvent(this.getEvent().hashCode());
                ticket.setUser(c.hashCode());
                ticket.setTicket_type(ticketType.giveaway);
                ticket.setUsed(false);
                ticket.setDate_bought(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                c.getTickets().add(ticket);
            }
        }

        for(Organiser o : this.getOrganisers()) {
            if(o.getEmail().equals(line)) {
                found = true;
                Ticket ticket = new Ticket();
                ticket.setPrice(0);
                ticket.setEvent(this.getEvent().hashCode());
                ticket.setUser(o.hashCode());
                ticket.setTicket_type(ticketType.giveaway);
                ticket.setUsed(false);
                ticket.setDate_bought(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                o.getTickets().add(ticket);
            }
        }

        if(found == false) System.out.println("Error: User was not found!");
        else System.out.println("Ticket has been sent successfully!");
    }

    private void processTickets() {
//        System.out.println("*");
        System.out.println("Enter ticket ID to verify (0 to exit): ");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        if(id == 0) return;
        Boolean found = false;
        for(Client c : this.getClients()) {
            for(Ticket t : c.getTickets()) {
                if(t.getId() == id) {
                    if(t.getEvent() != this.getEvent().hashCode()) System.out.println("Ticket is not for this event!");
                    else if(t.getUsed()) System.out.println("Ticket has been used!");
                    else {
                        // process ticket
                        t.setUsed(true);
                        found = true;
                    }
                }
            }
        }
        for(Organiser o : this.getOrganisers()) {
            for(Ticket t : o.getTickets()) {
                if(t.getId() == id) {
                    if(t.getEvent() != this.getEvent().hashCode()) System.out.println("Ticket is not for this event!");
                    else if(t.getUsed()) System.out.println("Ticket has been used!");
                    else {
                        // process ticket
                        t.setUsed(true);
                        found = true;
                    }
                }
            }
        }
        if(found == true) System.out.println("VALID TICKET! ID - " + id);
        else System.out.println("INVALID TICKET!");
        processTickets();
    }

    public ManageEvent(Event event, ArrayList<Client> clients, ArrayList<Organiser> organisers, Organiser organiser, ArrayList<Venue> venues) {
        this.event = event;
        this.clients = clients;
        this.organisers = organisers;
        this.organiser = organiser;
        this.venues = venues;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public Event getEvent() {
        return event;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Organiser> getOrganisers() {
        return organisers;
    }

    public Organiser getOrganiser() {
        return organiser;
    }
}
