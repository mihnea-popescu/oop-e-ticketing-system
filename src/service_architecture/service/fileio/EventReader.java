package service_architecture.service.fileio;

import service_architecture.model.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EventReader {
    private static EventReader instance;
    private EventReader(){}

    public static EventReader getInstance() {
        if(instance == null) {
            instance = new EventReader();
        }
        return instance;
    }

    private static String line = "";

    public static ArrayList<Event> readFile(String basePath) {
        ArrayList<Event> events = new ArrayList<Event>();
        try(BufferedReader br = new BufferedReader(new FileReader(basePath+"event_list.csv"))) {
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length > 0 && values[0] != null && values[0] != "") {
                    Event event = new Event();
                    event.setOrganiser(Integer.parseInt(values[0]));
                    event.setName(values[1]);
                    event.setDate(values[2]);
                    event.setDescription(values[3]);
                    event.setTicket_price(Integer.parseInt(values[4]));
                    event.setVenue(Integer.parseInt(values[5]));
                    events.add(event);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
}
