package application.controllers;

import application.entities.Activity;
import application.entities.Medication;
import application.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ActivityController {

    @Autowired
    ActivityService activityService;
    @GetMapping("/activity")
    public List<Activity> listActivity(){
        return activityService.findAllActivity();
    }
    @DeleteMapping("/activity/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
    @PostMapping("/activity")
    public void addActivity(@RequestBody Activity activity ) {
        activityService.addActivity(activity);
    }

}
