package service_architecture.service;

import service_architecture.model.Organiser;
import service_architecture.service.interfaces.CreateO;

import java.util.Scanner;

public class CreateOrganiser implements CreateO {
    public static Organiser Create() {
        return CreateO.create();
    }
}
