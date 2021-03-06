package application.controllers;

import application.entities.MedicationPlan;
import application.services.MedicationPlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MedicationPlanController {
    @Autowired
    MedicationPlanService medicationPlanService;

    @GetMapping("/medicationPlan")
    public List<MedicationPlan> listMedicationPlans(){
        return medicationPlanService.findAllMedicationPlan();
    }

    @DeleteMapping("/medicationPlan/{id}")
    public void deleteMedicationPlan(@PathVariable Long id) {
        medicationPlanService.deleteMedicationPlan(id);
    }

    @PostMapping("/medicationPlan")
    public void addMedicationPlan(@RequestBody MedicationPlan medicationPlan ) {
        medicationPlanService.addMedicationPlan(medicationPlan);
    }

    @PutMapping("/medicationPlan/{id}")
    public ResponseEntity<?> updateMedicationPlan(@RequestBody MedicationPlan medicationPlan, @PathVariable Long id) {
        try {
            MedicationPlan existingMedicationPlan = medicationPlanService.getMedicationPlan(id);
            copyIgnoreNull(medicationPlan, existingMedicationPlan);
            medicationPlanService.addMedicationPlan(existingMedicationPlan);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    public static void copyIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
