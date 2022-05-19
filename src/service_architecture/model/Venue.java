package service_architecture.model;

import java.util.Objects;

public class Venue {
    private String name;
    private String address;
    private Integer capacity;
    private Boolean opened;

    public Venue(String name, String address, Integer capacity, Boolean opened) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.opened = opened;
    }

    public Venue() {
        this.name = "Exemplu";
        this.address = "Str. Exemplu, Nr.2, Mun. Bucuresti, Sec.1";
        this.capacity = 0;
        this.opened = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", opened=" + opened +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return Objects.equals(getName(), venue.getName()) && Objects.equals(getAddress(), venue.getAddress()) && Objects.equals(getCapacity(), venue.getCapacity()) && Objects.equals(getOpened(), venue.getOpened());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getCapacity());
    }
}
