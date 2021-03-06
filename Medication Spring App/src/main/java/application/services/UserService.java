package application.services;

import application.entities.Role;
import application.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }
    public List<User> findAllPatients(){
        return userRepo.findAllPatients();
    }
    public List<User> findAllByRole(Role role){
        return userRepo.findByRoles(role);
    }
    public void addUser(User user){
        userRepo.save(user);
    }
    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }
    public User getUser(Long id){
        return userRepo.findById(id).get();
    }
}
