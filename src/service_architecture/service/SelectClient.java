package service_architecture.service;

import service_architecture.model.*;
import service_architecture.service.exceptions.NotMoneyNumber;

import java.util.ArrayList;
import java.util.Scanner;

public class SelectClient {
    Client client;

    ArrayList<Event> events;

    ArrayList<Venue> venues;

    ArrayList<Organiser> organisers;

    public SelectClient(Client client, ArrayList<Event> events, ArrayList<Venue> venues, ArrayList<Organiser> organisers) {
        this.client = client;
        this.events = events;
        this.venues = venues;
        this.organisers = organisers;
    }

    public Client ManageClient() {
        this.menu();
        return this.getClient();
    }

    private void menu() {
        System.out.println("*");
        System.out.println("Welcome, " + this.getClient().getName() + "!");
        System.out.println("1. Buy a ticket");
        System.out.println("2. See existing tickets");
        System.out.println("3. Modify details");
        System.out.println("4. Deposit money");
        System.out.println("0. Log out");
        System.out.println("Enter your input: ");
        int option;
        Scanner scan = new Scanner(System.in);
        option = scan.nextInt();
        switch(option) {
            case 1: {
                // buy ticket
                this.BuyTicketMenu();
                break;
            }
            case 2: {
                // existing tickets
                this.ExistingTicketsMenu();
                break;
            }
            case 3: {
                // modify client details
                this.ModifyClientMenu();
                break;
            }
            case 4: {
                // deposit money
                this.DepositMoneyMenu();
                this.menu();
                break;
            }
            case 0: {break;}
            default: {
                System.out.println("INVALID OPTION!");
                this.menu();
                break;
            }
        }
    }

    private void DepositMoneyMenu() {
        DepositMoney.processTransaction(this.getClient());
    }

    private void ModifyClientMenu() {
        System.out.println("*");
        System.out.println("*");
        System.out.println("*");
        System.out.println("MODIFY ACCOUNT DETAILS");
        System.out.println("1. Name: " + this.getClient().getName());
        System.out.println("2. Email: " + this.getClient().getEmail());
        System.out.println("3. Address: " + this.getClient().getAddress());
        System.out.println("4. Phone number: " + this.getClient().getPhone());
        System.out.println("0. PREVIOUS MENU");
        System.out.println("Enter your option: ");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        String line;
        switch(option) {
            case 1: {
                // name
                System.out.println("*");
                System.out.println("Enter your new name: ");
                line = scan.nextLine().replace(",","");
                this.getClient().setName(line);
                this.ModifyClientMenu();
                break;
            }
            case 2: {
                // email
                System.out.println("*");
                System.out.println("Enter your new e-mail address: ");
                line = scan.nextLine().replace(",","");
                this.getClient().setEmail(line);
                this.ModifyClientMenu();
                break;
            }
            case 3: {
                // address
                System.out.println("*");
                System.out.println("Enter your new address: ");
                line = scan.nextLine().replace(",","");
                this.getClient().setAddress(line);
                this.ModifyClientMenu();
                break;
            }
            case 4: {
                // phone no
                System.out.println("*");
                System.out.println("Enter your new phone number: ");
                line = scan.nextLine().replace(",","");
                this.getClient().setPhone(line);
                this.ModifyClientMenu();
                break;
            }
            case 0: {this.ModifyClientMenu(); break;}
            default: {
                System.out.println("INVALID OPTION!");
                this.ModifyClientMenu();
            }
        }
    }

    private void BuyTicketMenu() {
        System.out.println("*");
        System.out.println("CURRENT EVENTS LIST");
        ArrayList<Event> currentEvents = this.getEvents();
        for(Event event : currentEvents) {
            // Get event's venue name
            String venueName = "";
            for(Venue v : this.getVenues()) {
                if(v.hashCode() == event.getVenue()) venueName = v.getName();
            }


            System.out.println((currentEvents.indexOf(event) + 1) + ". " + event.getName() + " - " + venueName + " - " + event.getTicket_price() + "$");
        }
        System.out.println("0. PREVIOUS MENU");
        System.out.println("Enter your input: ");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if(option == 0) {
            menu();
        }
        else {
            option = option - 1;
            try {
                Event event = currentEvents.get(option);
                Organiser organiser = null;
                for(Organiser o : this.getOrganisers()) {
                    if(event.getOrganiser() == o.hashCode()) organiser = o;
                }
                BuyTicket bt = new BuyTicket(this.getClient(), event, organiser);
                bt.ProcessTransaction();
                this.menu();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("INVALID OPTION!");
                BuyTicketMenu();
            }
        }
    }

    private void ExistingTicketsMenu() {
        ArrayList<Ticket> clientTickets = this.getClient().getTickets();
        System.out.println("*");
        System.out.println("YOUR TICKETS");
        if(clientTickets.size() == 0) System.out.println("- You do not have any tickets! -");
        String msg;
        String eventName = ""; String eventDate = "";
        String venueName = ""; Integer venueid = 0;
        for(Ticket t : clientTickets) {
            if(t.getUsed()) {
                msg = "used";
            }
            else {
                msg = "unused";
            }
            for(Event e : this.getEvents()) {
                if(t.getEvent() == e.hashCode()) {
                    eventName = e.getName();
                    eventDate = e.getDate();
                    venueid = e.getVenue();
                }
            }
            for(Venue v : this.getVenues()) {
                if(v.hashCode() == venueid) venueName = v.getName();
            }

            System.out.println((clientTickets.indexOf(t) + 1) + ". " + eventName + " - " + venueName + " - " + eventDate + " " + msg);
        }
        System.out.println("0. PREVIOUS MENU");
        System.out.println("Enter your input: ");
        int option;
        Scanner scan = new Scanner(System.in);
        option = scan.nextInt();
        if(option == 0) {
            this.menu();
        }
        else {
            this.showTicket(clientTickets.get(option - 1));
        }
    }

    private void showTicket(Ticket t) {
        System.out.println("*");
        System.out.println("Ticket ID: " + t.getId());
        System.out.println("Type 0 to return to the previous menu: ");
        Scanner scan = new Scanner(System.in);
        if(scan.nextInt() == 0) {
            this.ExistingTicketsMenu();
        }
        else {
            System.out.println("INVALID OPTION!");
            this.showTicket(t);
        }
    }

    public static void validateMoney(int no) throws NotMoneyNumber {
        if(no < 0) {
            throw new NotMoneyNumber("Invalid amount of money! (only positive values)");
        }
    }

    public ArrayList<Organiser> getOrganisers() {
        return organisers;
    }

    public void setOrganisers(ArrayList<Organiser> organisers) {
        this.organisers = organisers;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
