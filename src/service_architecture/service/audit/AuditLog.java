package service_architecture.service.audit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuditLog {
    private static AuditLog instance;
    private AuditLog(){}

    public static AuditLog getInstance() {
        if(instance == null) {
            instance = new AuditLog();
        }
        return instance;
    }

    public void writeData(String basePath) {
        File f = new File(basePath, "audit_log.csv");
        try{
            if(f.createNewFile()) {
                System.out.println("audit_log.csv file created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(basePath+"audit_log.csv",true))) {
            String line;
            for(Map m : this.getLogs()) {
                if(m.containsKey("text") && m.containsKey("date")) {
                    line = m.get("text") + "," + m.get("date");
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved " + this.logs.size() + " audit logs.");
        this.setLogs(new ArrayList<Map>());
    }

    public void log(String text) {
        Map element = new HashMap<String, String>();
        element.put("text", text.replace(",",""));
        element.put("date", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        this.getLogs().add(element);
    }

    private ArrayList<Map> logs = new ArrayList<Map>();

    public ArrayList<Map> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<Map> logs) {
        this.logs = logs;
    }
}
