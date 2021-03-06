package application.server.api;

import application.entities.Medication;
import application.entities.MedicationPlan;
import application.repositories.MedicationPlanRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;


public class MedicationPlanProviderServiceImpl implements MedicationPLanProviderService {


    MedicationPlanRepository medicationPlanRepo;

    public MedicationPlanProviderServiceImpl(MedicationPlanRepository medicationPlanRepo) {
        this.medicationPlanRepo = medicationPlanRepo;
    }

    @Override
    public List<MedicationPlanDTO> retrieveMedicationPlan(String patientName) throws Exception {

        List<MedicationPlanDTO> dtolist = new ArrayList<>();
        List<MedicationPlan> medPlanList = medicationPlanRepo.findAll();
        for(MedicationPlan medPlan:medPlanList) {
            if (medPlan.getUser().getUser_name().equals(patientName)) {
                MedicationPlanDTO dto = new MedicationPlanDTO();
                List<String> medication_name_list = new ArrayList<>();
                List<String> medication_dosage_list = new ArrayList<>();

                dto.setMedicationplan_id(medPlan.getMedicationplan_id());
                dto.setIntake_intervals(medPlan.getIntake_intervals());
                dto.setStart_date(medPlan.getStart_date());
                dto.setEnd_date(medPlan.getEnd_date());

                List<Medication> medList = medPlan.getMedication_list();
                for (Medication med : medList) {
                    medication_name_list.add(med.getMedication_name());
                    medication_dosage_list.add(med.getMedication_dosage());
                }
                dto.setMedication_names_list(medication_name_list);
                dto.setMedication_dosage_list(medication_dosage_list);
                dtolist.add(dto);
            }
        }

        return dtolist;
    }





}