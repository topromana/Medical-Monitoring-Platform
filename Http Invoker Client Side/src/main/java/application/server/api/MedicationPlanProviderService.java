package application.server.api;

import java.util.List;

public interface MedicationPlanProviderService {
    List<MedicationPlanDTO> retrieveMedicationPlan(String patientName) throws Exception;
}
