package application.repositories;


import application.entities.Role;
import application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String user_name);
    List<User>findByRoles(Role user_role);
    Boolean existsByName(String username);
    @Query(value = "SELECT * FROM medication_project.users u join medication_project.user_roles r on u.user_id = r.user_id join medication_project.role ro on r.role_id=ro.role_id where ro.rolename =\"ROLE_PATIENT\" ", nativeQuery = true)
    List<User> findAllPatients();
}
