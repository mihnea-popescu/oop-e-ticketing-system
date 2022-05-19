package service_architecture.model;

import java.util.ArrayList;
import java.util.Objects;

public class Client extends User{
    private String address;
    private String phone;
    private String permissions;
    private int balance;

    public Client(String name, String email, ArrayList<Ticket> tickets, String address, String phone, String permissions, Integer balance) {
        super(name, email, tickets);
        this.address = address;
        this.phone = phone;
        this.permissions = permissions;
        this.balance = balance;
    }

    public Client() {
        super("Exemplu", "exemplu@ex.ro", new ArrayList<Ticket>());
        this.address = "Str. Exemplu";
        this.phone = "0712312312";
        this.permissions = "";
        this.balance = 0;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "Client{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", permissions='" + permissions + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(getName(), client.getName()) && Objects.equals(getAddress(), client.getAddress()) && Objects.equals(getPhone(), client.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getPhone(), getAddress());
    }
}
