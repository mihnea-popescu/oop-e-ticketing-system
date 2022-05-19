package service_architecture.model;

import java.util.Objects;

public class Event {
    private String name;
    private String date;
    private String description;
    private Integer ticket_price;
    private Integer venue;

    private Integer organiser;

    public Event(String name, String date, String description, Integer ticket_price, Integer venue, Integer organiser) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.ticket_price = ticket_price;
        this.venue = venue;
        this.organiser = organiser;
    }

    public Event() {
        this.name = "Eveniment exemplu";
        this.date = "18 ian 2023 19:00";
        this.description = "Descriere exemplu";
        this.ticket_price = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Integer ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Integer getVenue() {
        return venue;
    }

    public void setVenue(Integer venue) {
        this.venue = venue;
    }

    public Integer getOrganiser() {
        return organiser;
    }

    public void setOrganiser(Integer organiser) {
        this.organiser = organiser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(getName(), event.getName()) && Objects.equals(getDate(), event.getDate()) && Objects.equals(getDescription(), event.getDescription()) && Objects.equals(getTicket_price(), event.getTicket_price()) && Objects.equals(getVenue(), event.getVenue()) && Objects.equals(getOrganiser(), event.getOrganiser());
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", ticket_price=" + ticket_price +
                ", venue=" + venue +
                ", organiser=" + organiser +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDate(), getDescription(), getVenue(), getOrganiser());
    }
}
