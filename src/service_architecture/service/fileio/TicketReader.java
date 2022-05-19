package service_architecture.service.fileio;

import service_architecture.model.Ticket;
import service_architecture.model.enums.ticketType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TicketReader {
    private static TicketReader instance;
    private TicketReader(){}

    public static TicketReader getInstance() {
        if(instance == null) {
            instance = new TicketReader();
        }
        return instance;
    }

    private static String line = "";

    public static ArrayList<Ticket> readFile(String basePath) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try (BufferedReader br = new BufferedReader(new FileReader(basePath + "ticket_list.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    Ticket ticket = new Ticket();
                    ticket.setUser(Integer.parseInt(values[0]));
                    ticket.setEvent(Integer.parseInt(values[1]));
                    ticket.setPrice(Integer.parseInt(values[2]));
                    ticket.setDate_bought(values[3]);
                    ticket.setTicket_type(ticketType.valueOf(values[4]));
                    ticket.setUsed(Boolean.valueOf(values[5]));
                    ticket.setId(Integer.parseInt(values[6]));
                    tickets.add(ticket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Accessed index out of array bounds!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
