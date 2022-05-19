package service_architecture.service.fileio;

import service_architecture.model.Event;
import service_architecture.model.Organiser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OrganiserReader {
    private static OrganiserReader instance;
    private OrganiserReader(){}

    public static OrganiserReader getInstance() {
        if(instance == null) {
            instance = new OrganiserReader();
        }
        return instance;
    }

    private static String line = "";

    public static ArrayList<Organiser> readFile(String basePath) {
        ArrayList<Organiser> organisers = new ArrayList<Organiser>();
        try(BufferedReader br = new BufferedReader(new FileReader(basePath+"organiser_list.csv"))) {
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length > 0) {
                    Organiser organiser = new Organiser();
                    // String name, String email, ArrayList<Ticket> tickets, String company, String permissions, int balance
                    organiser.setName(values[0]);
                    organiser.setEmail(values[1]);
                    organiser.setCompany(values[2]);
                    organiser.setBalance(Integer.parseInt(values[3]));
                    organisers.add(organiser);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return organisers;
    }
}
