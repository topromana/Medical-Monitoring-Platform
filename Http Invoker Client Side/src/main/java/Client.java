import application.server.api.MedicationPlanDTO;
import application.server.api.MedicationPlanProviderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.boot.SpringApplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class Client {

    @Bean
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8080/api/auth/medicationPlanProvider");
        invoker.setServiceInterface(MedicationPlanProviderService.class);
        return invoker;
    }

    public static void populateTable(List<MedicationPlanDTO> medicationPlanList, DefaultTableModel model){
        for(MedicationPlanDTO m:medicationPlanList){
            List<String> names = m.getMedication_names_list();
            List<String> dosage = m.getMedication_dosage_list();
            for(int i = 0, j =0; i<names.size(); i++,j++) {
                Object[] row = new Object[4];
                row[0] = m.getMedicationplan_id();
                row[1] = names.get(i);
                row[2] = dosage.get(j);
                row[3] = m.getIntake_intervals();

                model.addRow(row);
            }
        }
    }
    public static boolean containsMorning(String intakeInterval){
        if(intakeInterval.equals("1-0-0") || intakeInterval.equals("1-1-0")
                || intakeInterval.equals("1-1-1") || intakeInterval.equals("1-0-1")) return  true;
        return false;
    }
    public static boolean containsNoon(String intakeInterval){
        if(intakeInterval.equals("1-1-0") || intakeInterval.equals("0-1-0")
                || intakeInterval.equals("1-1-1") || intakeInterval.equals("0-1-1")) return  true;
        return false;
    }
    public static boolean containsEvening(String intakeInterval){
        if(intakeInterval.equals("1-1-1") || intakeInterval.equals("0-1-1")
                || intakeInterval.equals("0-0-1") || intakeInterval.equals("1-0-1")) return  true;
        return false;
    }
    public static void populateTableMorning(List<MedicationPlanDTO> medicationPlanList, DefaultTableModel model){
        model.setRowCount(0);
        for(MedicationPlanDTO m:medicationPlanList){
            List<String> names = m.getMedication_names_list();
            List<String> dosage = m.getMedication_dosage_list();
            for(int i = 0, j =0; i<names.size(); i++,j++) {
                if(containsMorning(m.getIntake_intervals())) {
                    Object[] row = new Object[4];
                    row[0] = m.getMedicationplan_id();
                    row[1] = names.get(i);
                    row[2] = dosage.get(j);
                    row[3] = m.getIntake_intervals();

                    model.addRow(row);
                }
            }
        }
    }
    public static void populateTableNoon(List<MedicationPlanDTO> medicationPlanList, DefaultTableModel model){
        model.setRowCount(0);
        for(MedicationPlanDTO m:medicationPlanList){
            List<String> names = m.getMedication_names_list();
            List<String> dosage = m.getMedication_dosage_list();
            if(containsNoon(m.getIntake_intervals())) {
            for(int i = 0, j =0; i<names.size(); i++,j++) {

                    Object[] row = new Object[4];
                    row[0] = m.getMedicationplan_id();
                    row[1] = names.get(i);
                    row[2] = dosage.get(j);
                    row[3] = m.getIntake_intervals();

                    model.addRow(row);
                }
            }
        }
    }
    public static void populateTableEvening(List<MedicationPlanDTO> medicationPlanList, DefaultTableModel model){
        model.setRowCount(0);
        for(MedicationPlanDTO m:medicationPlanList){
            List<String> names = m.getMedication_names_list();
            List<String> dosage = m.getMedication_dosage_list();
            if(containsEvening(m.getIntake_intervals())) {
                for(int i = 0, j =0; i<names.size(); i++,j++) {

                    Object[] row = new Object[4];
                    row[0] = m.getMedicationplan_id();
                    row[1] = names.get(i);
                    row[2] = dosage.get(j);
                    row[3] = m.getIntake_intervals();

                    model.addRow(row);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception, ParseException {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel currentTimeLabel = new JLabel();

       // currentTimeLabel.setLocation(500,400);
        JTable table = new JTable();

        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Id Plan","Medication","Dosage","Intake Intervals"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);


        // set the model to the table
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        currentTimeLabel.setBounds(0,400,200,200);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);


        frame.setLayout(null);
        frame.add(pane);
        frame.add(currentTimeLabel);
        frame.setSize(900,800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        MedicationPlanProviderService service = SpringApplication.run(Client.class, args).getBean(MedicationPlanProviderService.class);

        //List<MedicationPlanDTO> medicationPlanList = service.retrieveMedicationPlan("patient1");
        List<MedicationPlanDTO> medicationPlanList = new ArrayList<>();
        int count  = 0;
        int countAcquire = 0;
        while(true) {

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.S");
            String formatDateTime = localDateTime.format(format);
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(formatDateTime);

            String nowTime = new SimpleDateFormat("HH:mm:ss.S").format(date);
            currentTimeLabel.setText("Current time: " + nowTime);
            if (nowTime.equals("18:10:00.0") && countAcquire == 0){
                 medicationPlanList = service.retrieveMedicationPlan("patient1");
                countAcquire = 1;
            }
            if (nowTime.equals("18:10:10.0") && count ==0){
                populateTableMorning(medicationPlanList, model);
                count = 1;}
            if (nowTime.equals("18:10:30.0") && count ==1){
                populateTableNoon(medicationPlanList, model);
                count = 2;}
            if (nowTime.equals("18:11:00.0") && count ==2){
                populateTableEvening(medicationPlanList, model);
                count = 3;
                countAcquire = 0;}


        }

    }

}
