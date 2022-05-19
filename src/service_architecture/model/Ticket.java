package service_architecture.model;
import service_architecture.model.enums.ticketType;

import java.util.Objects;

public class Ticket {
    private Integer price;
    private Integer event;
    private Integer user;
    private String date_bought;
    private ticketType ticket_type;
    private Boolean used;

    private int id = 0;

    public Ticket(Integer price, Integer event, Integer user, String date_bought, ticketType ticket_type, Boolean used, int id) {
        this.price = price;
        this.event = event;
        this.user = user;
        this.date_bought = date_bought;
        this.ticket_type = ticket_type;
        this.used = used;
        this.id = id;
    }

    public Ticket() {
        this.price = 0;
        this.user = 0;
        this.event = 0;
        this.date_bought = "";
        this.ticket_type = ticketType.giveaway;
        this.used = false;
        this.id = (int) (Math.random()*(100000-1000+1) + 1000);
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getEvent() {
        return event;
    }

    public void setEvent(Integer event) {
        this.event = event;
    }

    public String getDate_bought() {
        return date_bought;
    }

    public void setDate_bought(String date_bought) {
        this.date_bought = date_bought;
    }

    public ticketType getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(ticketType ticket_type) {
        this.ticket_type = ticket_type;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public int getId() {
        if(id == 0) {
            setId((int) (Math.random()*(100000-1000+1) + 1000));
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getPrice(), ticket.getPrice()) && Objects.equals(getEvent(), ticket.getEvent()) && Objects.equals(getDate_bought(), ticket.getDate_bought()) && getTicket_type() == ticket.getTicket_type() && Objects.equals(getUsed(), ticket.getUsed());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", event=" + event +
                ", date_bought='" + date_bought + '\'' +
                ", ticket_type=" + ticket_type +
                ", used=" + used +
                ", id=" + id +
                '}';
    }
}
