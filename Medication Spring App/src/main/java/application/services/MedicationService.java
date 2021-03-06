package application.services;

import application.entities.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.repositories.MedicationRepository;

import java.util.List;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepo;
    public List<Medication> findAllMedication(){ return medicationRepo.findAll();}
    public void addMedication(Medication medication){
        medicationRepo.save(medication);
    }
    public Medication getMedication(Long id){
        return medicationRepo.findById(id).get();
    }
    public void deleteMedication(Long id){
        medicationRepo.deleteById(id);
    }
}
