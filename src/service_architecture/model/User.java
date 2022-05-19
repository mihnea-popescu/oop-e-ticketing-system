package service_architecture.model;

import java.util.ArrayList;

abstract class User {
    protected String name;
    protected String email;

    protected ArrayList<Ticket> tickets;

    public User(String name, String email, ArrayList<Ticket> tickets) {
        this.name = name;
        this.email = email;
        this.tickets = tickets;
    }

    public User() {
        this.name = "Utilizator";
        this.email = "user@exemplu.ro";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public abstract String getPermissions();
}
