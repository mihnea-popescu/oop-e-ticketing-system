package service_architecture.service.fileio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetCSVData {

    private static GetCSVData instance;
    private GetCSVData(){}

    public static GetCSVData getInstance() {
        if(instance == null) {
            instance = new GetCSVData();
        }
        return instance;
    }

    public static Map<String, ArrayList> getCSVInputData(String basePath) {
        Map map = new HashMap<String, ArrayList>();

        VenueReader vr = VenueReader.getInstance();
        map.put("venues",vr.readFile(basePath));

        OrganiserReader or = OrganiserReader.getInstance();
        map.put("organisers", or.readFile(basePath));

        EventReader er = EventReader.getInstance();
        map.put("events", er.readFile(basePath));

        ClientReader cr = ClientReader.getInstance();
        map.put("clients", cr.readFile(basePath));

        TicketReader tr = TicketReader.getInstance();
        map.put("tickets", tr.readFile(basePath));

        return map;
    }
}
