package service_architecture.model;

import java.util.ArrayList;
import java.util.Objects;

public class Organiser extends User{
    private String company;
    private String permissions;
    private int balance;


    public Organiser(String name, String email, ArrayList<Ticket> tickets, String company, String permissions, int balance) {
        super(name, email, tickets);
        this.company = company;
        this.permissions = permissions;
        this.balance = balance;
    }

    public Organiser() {
        super("Exemplu", "exemplu@exemplu.ro", new ArrayList<Ticket>());
        this.company = "SC Exemplu SRL";
        this.permissions = "";
        this.balance = 0;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Organiser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", permissions='" + permissions + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organiser organiser = (Organiser) o;
        return getBalance() == organiser.getBalance() && Objects.equals(getCompany(), organiser.getCompany()) && Objects.equals(getPermissions(), organiser.getPermissions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getCompany());
    }
}
