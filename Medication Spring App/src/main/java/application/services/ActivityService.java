package application.services;


import application.MessageDispatcher;
import application.entities.Activity;

import application.entities.MedicationPlan;
import application.entities.User;
import application.repositories.ActivityRepository;
import application.repositories.MedicationPlanRepository;
import application.repositories.UserRepository;


import com.rabbitmq.client.DeliverCallback;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ActivityService {

    @Autowired
   ActivityRepository activityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageDispatcher messageDispatcher;
    public List<Activity> findAllActivity(){ return activityRepository.findAll();}
    public void addActivity(Activity activity){
        activityRepository.save(activity);
    }
    public Activity getActivity (Long id){
        return activityRepository.findById(id).get();
    }
    public void deleteActivity(Long id){
        activityRepository.deleteById(id);
    }
    @Autowired
    private MedicationPlanRepository medicationPlanRepo;



    private final static String QUEUE_NAME = "hello";
    @RabbitListener(queues = QUEUE_NAME)
    void handleMessages(String message) throws Exception {

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
       // DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //String message = new String(delivery.getBody(), "UTF-8");
            JSONArray array = new JSONArray(message);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int ok = 0;
                //System.out.println(object.getString("patient_id"));
                //System.out.println(object.getString("activity"));
               // System.out.println(object.getString("start_time"));

                String activityName = object.getString("activity");
                Long start_time = Long.parseLong(object.getString("start_time"));
                Long end_time = Long.parseLong(object.getString("end_time"));
                if(activityName.equals("Sleeping") && end_time - start_time >= 25200000){
                    ok = 1;
                }
                else if(activityName.equals("Leaving") && end_time - start_time >= 18000000){
                    ok = 1;
                }
                else if(activityName.equals("Toileting") && end_time - start_time >= 1800000 ){
                    ok = 1;
                }
                else if(activityName.equals("Showering") && end_time - start_time >= 1800000 ){
                    ok = 1;
                }

                if(ok == 1) {
                    User patient = userRepository.findById(Long.parseLong(object.getString("patient_id"))).get();
                    Activity newActivity = new Activity(patient, object.getString("activity"), Long.parseLong(object.getString("start_time")), Long.parseLong(object.getString("end_time")));
                   // activityRepository.save(newActivity);
                    messageDispatcher.sendToClient("Patient " +patient.getUser_id() + " did activity " +activityName+" for longer than allowed");
                }
            }
            System.out.println(" [x] Received '" + message + "'");

    }
}
