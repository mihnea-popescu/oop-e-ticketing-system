package service_architecture.service.fileio;

import service_architecture.model.Venue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VenueReader {
    private static VenueReader instance;
    private VenueReader(){}

    public static VenueReader getInstance() {
        if(instance == null) {
            instance = new VenueReader();
        }
        return instance;
    }

    private static String line = "";

    public static ArrayList<Venue> readFile(String basePath) {
        ArrayList<Venue> venues = new ArrayList<Venue>();
        try(BufferedReader br = new BufferedReader(new FileReader(basePath+"venue_list.csv"))) {
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length > 0) {
                    Venue venue = new Venue();
                    venue.setName(values[0]);
                    venue.setAddress(values[1]);
                    venue.setCapacity(Integer.parseInt(values[2]));
                    venue.setOpened(Boolean.parseBoolean(values[3]));
                    venues.add(venue);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venues;
    }
}
