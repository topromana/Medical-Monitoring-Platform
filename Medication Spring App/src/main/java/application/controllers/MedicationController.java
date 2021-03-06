package application.controllers;

import application.entities.Medication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import application.services.MedicationService;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MedicationController {
    @Autowired
    MedicationService medicationService;

    @GetMapping("/medication")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Medication> listMedication(){
        return medicationService.findAllMedication();
    }
    @GetMapping("/medication/{id}")
    public ResponseEntity<Medication> findMedication(@PathVariable Long id){
        try {
                Medication medication = medicationService.getMedication(id);
            return new ResponseEntity<Medication>(medication, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<Medication>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/medication/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public void deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
    }

    @PostMapping("/medication")
    @PreAuthorize("hasRole('DOCTOR')")
    public void addMedication(@RequestBody Medication medication ) {
        medicationService.addMedication(medication);
    }
    @PutMapping("/medication/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> updateMedication(@RequestBody Medication medication, @PathVariable Long id) {
        try {
            Medication existingMedication = medicationService.getMedication(id);
            copyIgnoreNull(medication, existingMedication);
            medicationService.addMedication(existingMedication);
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
