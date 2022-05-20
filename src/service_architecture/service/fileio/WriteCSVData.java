package service_architecture.service.fileio;

import service_architecture.model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteCSVData {
    private static WriteCSVData instance;
    private WriteCSVData(){}

    private static Integer ticketCount = 0;

    public static WriteCSVData getInstance() {
        if(instance == null) {
            instance = new WriteCSVData();
        }
        return instance;
    }

    public static void saveData(String basePath, ArrayList<Venue> venues, ArrayList<Organiser> organisers, ArrayList<Event> events, ArrayList<Client> clients) {
        System.out.println("SAVING DATA.");
        writeVenues(basePath, venues);

        // Reset the ticket_list.csv file
        File ticketList = new File(basePath, "ticket_list.csv");
        try {
            if(ticketList.createNewFile()) {
                System.out.println("ticket_list.csv file created");
            }
            else {
                // Delete all contents from the ticket_list.csv
                new FileWriter(basePath+"ticket_list.csv", false).close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        ticketCount = 0;

        writeOrganisers(basePath, organisers);
        writeEvents(basePath, events);
        writeClients(basePath, clients);

        System.out.println("Saved " + ticketCount + " tickets.");
        System.out.println("Saved all data successfully.");
    }

    public static void writeVenues(String basePath, ArrayList<Venue> venues) {
        File f = new File(basePath, "venue_list.csv");
        try{
            if(f.createNewFile()) {
                System.out.println("venue_list.csv file created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(basePath+"venue_list.csv"))) {
            String line;
            for(Venue venue : venues) {
                line = venue.getName() + "," + venue.getAddress() + "," + venue.getCapacity().toString() + "," + venue.getOpened().toString();
                bw.write(line);
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved " + venues.size() + " venues.");
    }

    public static void writeOrganisers(String basePath, ArrayList<Organiser> organisers) {
        File f = new File(basePath, "organiser_list.csv");
        try{
            if(f.createNewFile()) {
                System.out.println("organiser_list.csv file created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(basePath+"organiser_list.csv"))) {
            String line;
            for(Organiser organiser : organisers) {
                line = organiser.getName() + "," + organiser.getEmail() + "," + organiser.getCompany() + "," + organiser.getBalance();
                bw.write(line);
                bw.newLine();

                writeTicketsOrganiser(basePath, organiser);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved " + organisers.size() + " organisers.");
    }

    public static void writeEvents(String basePath, ArrayList<Event> events) {
        File f = new File(basePath, "event_list.csv");
        try{
            if(f.createNewFile()) {
                System.out.println("event_list.csv file created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(basePath+"event_list.csv"))) {
            String line;
            for(Event event : events) {
                line = event.getOrganiser().toString() + "," + event.getName() + "," + event.getDate() + "," + event.getDescription() + "," + event.getTicket_price().toString() + "," + event.getVenue();
                bw.write(line);
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved " + events.size() + " events.");
    }

    public static void writeClients(String basePath, ArrayList<Client> clients) {
        File f = new File(basePath, "client_list.csv");
        try{
            if(f.createNewFile()) {
                System.out.println("client_list.csv file created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(basePath+"client_list.csv"))) {
            String line;
            for(Client client : clients) {
                line = client.getName() + "," + client.getEmail() + "," + client.getAddress() + "," + client.getPhone() + "," + client.getBalance();
                bw.write(line);
                bw.newLine();

                writeTicketsClient(basePath, client);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved " + clients.size() + " clients.");
    }

    public static void writeTicketsOrganiser(String basepath, Organiser organiser) {
        ArrayList<Ticket> tickets = organiser.getTickets();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(basepath+"ticket_list.csv", true))) {
            String line;
            for(Ticket ticket : tickets) {
                line = organiser.hashCode() + "," + ticket.getEvent() + "," + ticket.getPrice().toString() + "," + ticket.getDate_bought() + "," + ticket.getTicket_type().toString() + "," + ticket.getUsed().toString() + "," + ticket.getId();
                bw.write(line);
                bw.newLine();
                ticketCount++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTicketsClient(String basepath, Client client) {
        ArrayList<Ticket> tickets = client.getTickets();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(basepath+"ticket_list.csv", true))) {
            String line;
            for(Ticket ticket : tickets) {
                line = client.hashCode() + "," + ticket.getEvent() + "," + ticket.getPrice().toString() + "," + ticket.getDate_bought() + "," + ticket.getTicket_type().toString() + "," + ticket.getUsed().toString() + "," + ticket.getId();
                bw.write(line);
                bw.newLine();
                ticketCount++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
