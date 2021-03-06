package application.repositories;

import application.entities.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationPlanRepository extends JpaRepository<MedicationPlan,Long> {
}
