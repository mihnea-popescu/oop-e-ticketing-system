package service_architecture.service;

import service_architecture.model.Client;
import service_architecture.model.Event;
import service_architecture.model.Organiser;
import service_architecture.model.Ticket;
import service_architecture.model.enums.ticketType;
import service_architecture.service.exceptions.ClientNotEnoughBalanceException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BuyTicket {
    private Client client;
    private Event event;

    private Organiser organiser;

    public BuyTicket(Client client, Event event, Organiser organiser) {
        this.client = client;
        this.event = event;
        this.organiser = organiser;
    }

    public boolean ProcessTransaction() {

        try {
            this.validateTransaction();
        }
        catch (ClientNotEnoughBalanceException e) {
            System.out.println("Error: " + e);
            return false;
        }

        this.getClient().setBalance(this.getClient().getBalance() - this.getEvent().getTicket_price());
        this.getOrganiser().setBalance(this.getOrganiser().getBalance() + this.getEvent().getTicket_price());

        Ticket ticket = new Ticket();
        ticket.setPrice(this.getEvent().getTicket_price());
        ticket.setEvent(this.getEvent().hashCode());
        ticket.setUser(this.getClient().hashCode());
        ticket.setDate_bought(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        ticket.setTicket_type(ticketType.bought);
        ticket.setUsed(false);

        ArrayList<Ticket> userTickets = this.getClient().getTickets();
        userTickets.add(ticket);
        this.getClient().setTickets(userTickets);

        System.out.println("Ticket bought!");
        return true;
    }

    void validateTransaction() throws ClientNotEnoughBalanceException{
        if(this.getClient().getBalance() < this.getEvent().getTicket_price()) {
            throw new ClientNotEnoughBalanceException("You do not have enough money in your balance!");
        }
    }

    public Organiser getOrganiser() {
        return organiser;
    }

    public Client getClient() {
        return client;
    }


    public Event getEvent() {
        return event;
    }

}
