package service_architecture.main;
import service_architecture.model.*;
import service_architecture.service.fileio.GetCSVData;
import service_architecture.service.fileio.WriteCSVData;

import java.util.ArrayList;
import java.util.Map;

public class Main {

    private String basePath = "D:\\facultate\\an2\\EAP\\Proiect_EAP\\src\\storage\\";

    private ArrayList<Client> clients;
    private ArrayList<Organiser> organisers;
    private ArrayList<Venue> venues;

    private ArrayList<Event> events;

    private void getData() {
        System.out.println("Loading the data...");
        String basePath = this.basePath;

        //inputs
        GetCSVData singleton = GetCSVData.getInstance();

        Map<String, ArrayList> map = singleton.getCSVInputData(basePath);

        if(map.containsKey("venues")) {
            this.setVenues(map.get("venues"));
            for (Venue venue:
                 this.getVenues()) {
                System.out.println(venue.hashCode());
            }
        }
        System.out.println("Found " + this.getVenues().size() +" venues.");

        if(map.containsKey("organisers")) {
            this.setOrganisers(map.get("organisers"));
            for (Organiser organiser:
                 this.getOrganisers()) {
                System.out.println("Organiser id " + organiser.hashCode());
            }
        }
        System.out.println("Found " + this.getOrganisers().size() + " organisers.");

        if(map.containsKey("events")) {
            this.setEvents(map.get("events"));
            for (Event event:
                 this.getEvents()) {
                System.out.println(event);
                System.out.println(event.hashCode());
            }
        }
        System.out.println("Found "+ this.getEvents().size() + " events.");

        if(map.containsKey("clients")) {
            this.setClients(map.get("clients"));
            for(Client client: this.getClients()) {
                System.out.println(client);
                System.out.println(client.hashCode());
            }
        }
        System.out.println("Found " + this.getClients().size() + " clients.");

        if(map.containsKey("tickets")) {
            ArrayList<Ticket> tickets = map.get("tickets");
            for(Ticket ticket: tickets) {
                System.out.println(ticket);
                System.out.println("Ticket id: " + ticket.getId());
            }

            for(Client client : this.getClients()) {
                ArrayList<Ticket> clientTickets = new ArrayList<Ticket>();
                for(Ticket ticket : tickets) {
                    if(ticket.getUser() == client.hashCode()) {
                        clientTickets.add(ticket);
                    }
                }
                client.setTickets(clientTickets);
            }

            for(Organiser organiser : this.getOrganisers()) {
                ArrayList<Ticket> organiserTickets = new ArrayList<Ticket>();
                for(Ticket ticket : tickets) {
                    if(ticket.getUser() == organiser.hashCode()) {
                        organiserTickets.add(ticket);
                    }
                }
                organiser.setTickets(organiserTickets);
            }

            System.out.println("Found " + tickets.size() + " tickets.");
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.getData();
        main.saveData();
    }

    public void saveData() {
        WriteCSVData singleton = WriteCSVData.getInstance();
        singleton.saveData(basePath, this.getVenues(), this.getOrganisers(), this.getEvents(), this.getClients());
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
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
}
