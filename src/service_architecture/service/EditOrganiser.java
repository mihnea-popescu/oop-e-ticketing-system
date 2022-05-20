package service_architecture.service;

import service_architecture.model.Client;
import service_architecture.model.Event;
import service_architecture.model.Organiser;
import service_architecture.service.interfaces.EditO;

import java.util.ArrayList;

public class EditOrganiser implements EditO {
    public static Organiser edit(Organiser organiser, ArrayList<Event> events, ArrayList<Client> clients, ArrayList<Organiser> organisers) {
        return EditO.edit(organiser, events, clients, organisers);
    }
}
