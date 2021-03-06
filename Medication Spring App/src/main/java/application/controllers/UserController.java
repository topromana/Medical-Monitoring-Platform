package application.controllers;

import application.entities.ERoles;
import application.entities.Medication;
import application.entities.Role;
import application.entities.User;
import application.repositories.RoleRepository;
import application.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/users")
    public List<User> listUsers(){
        return userService.findAllUsers();
    }
    @GetMapping("/doctors")
    public List<User> listDoctors(){

        List<User> allUsers = userService.findAllUsers();
        List<User> doctorsList = new ArrayList<>();
        for(User user:allUsers){
            Set<Role> roles = user.getRoles();
            for(Role r:roles){
                if(r.getRole_name() == ERoles.ROLE_DOCTOR){
                    doctorsList.add(user);
                }
            }
        }
        return doctorsList;
    }

    @GetMapping("/patients")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<User> listPatients(){

       //return userService.findAllPatients();
        List<User> allUsers = userService.findAllUsers();
        List<User> patientsList = new ArrayList<>();
        for(User user:allUsers){
            Set<Role> roles = user.getRoles();
            for(Role r:roles){
                if(r.getRole_name() == ERoles.ROLE_PATIENT){
                    patientsList.add(user);
                }
            }
        }
        return patientsList;
    }

    @GetMapping("/patientsOfCaregiver/{id}")
    @PreAuthorize("hasRole('CAREGIVER')")
    public Set<User> listPatientsOfCaregiver(@PathVariable Long id){

       User caregiver = userService.getUser(id);
       return caregiver.getPatients_list();
    }
    @GetMapping("/caregivers")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<User> listCaregivers(){

        List<User> allUsers = userService.findAllUsers();
        List<User> caregiversList = new ArrayList<>();
        for(User user:allUsers){
            Set<Role> roles = user.getRoles();
            for(Role r:roles){
                if(r.getRole_name() == ERoles.ROLE_CAREGIVER){
                    caregiversList.add(user);
                }
            }
        }
        return caregiversList;
    }

    @PostMapping("/users")
    public void addUser (@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id){
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser (@RequestBody User user, @PathVariable Long id){
        try{
            User existUser = userService.getUser(id);
            Set<Role> existRoles = existUser.getRoles();
           User existCaregiver = existUser.getCaregiver();


            copyIgnoreNull(user, existUser);
            existUser.setRoles(existRoles);
            if(user.getCaregiver() !=null){
                User newCaregiver = userService.getUser( user.getCaregiver().getUser_id());
                System.out.println(newCaregiver.getUser_id()+" "+newCaregiver.getUser_address());
                existUser.setCaregiver(newCaregiver);}



            userService.addUser(existUser);
            //userService.addUser(newCaregiver);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

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


    public static void copyIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
