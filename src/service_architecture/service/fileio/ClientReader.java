package service_architecture.service.fileio;

import service_architecture.model.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClientReader {
    private static ClientReader instance;
    private ClientReader(){}

    public static ClientReader getInstance() {
        if(instance == null) {
            instance = new ClientReader();
        }
        return instance;
    }

    private static String line = "";

    public static ArrayList<Client> readFile(String basePath) {
        ArrayList<Client> clients = new ArrayList<Client>();
        try(BufferedReader br = new BufferedReader(new FileReader(basePath+"client_list.csv"))) {
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length > 0) {
                    Client client = new Client();
                    client.setName(values[0]);
                    client.setEmail(values[1]);
                    client.setAddress(values[2]);
                    client.setPhone(values[3]);
                    client.setBalance(Integer.parseInt(values[4]));
                    clients.add(client);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.print("Accessed index out of array bounds!");
          e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }
}

