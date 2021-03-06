package application.services;

import application.entities.MedicationPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.repositories.MedicationPlanRepository;

import java.util.List;

@Service
public class MedicationPlanService{
    @Autowired
    private MedicationPlanRepository medicationPlanRepo;



    public List<MedicationPlan> findAllMedicationPlan(){
        return medicationPlanRepo.findAll();
    }
    public void addMedicationPlan(MedicationPlan medicationPlan){ medicationPlanRepo.save(medicationPlan); }
    public void deleteMedicationPlan(Long id){ medicationPlanRepo.deleteById(id);}
    public MedicationPlan getMedicationPlan(Long id){return medicationPlanRepo.findById(id).get();}

}
