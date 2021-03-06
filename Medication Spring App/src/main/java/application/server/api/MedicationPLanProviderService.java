package application.server.api;

import java.util.List;

public interface MedicationPLanProviderService {
    List<MedicationPlanDTO> retrieveMedicationPlan(String patientName) throws Exception;
}
