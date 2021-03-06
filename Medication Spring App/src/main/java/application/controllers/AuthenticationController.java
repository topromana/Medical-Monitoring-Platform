package application.controllers;

import application.entities.ERoles;
import application.entities.Role;
import application.entities.User;
import application.payloads.requestObj.LoginRequest;
import application.payloads.requestObj.SignupRequest;
import application.payloads.responseObj.JwtResponse;
import application.payloads.responseObj.MessageResponse;
import application.repositories.RoleRepository;
import application.repositories.UserRepository;
import application.security.jwt.JwtUtils;
import application.security.servicesSecurity.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("lalalaaaaaaaaaaaaaa"+loginRequest.getUsername()+" "+ loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUser_id(), userDetails.getUsername(), userDetails.getUser_birthdate(), userDetails.getUser_gender(),
                userDetails.getUser_address(), userDetails.getMedical_record(), roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByName(signUpRequest.getUser_name())) {
            return ResponseEntity.badRequest() .body(new MessageResponse("Error: Username is already taken!"));
        }


        // Create new account
        User user = new User(signUpRequest.getUser_name(),signUpRequest.getUser_birthdate(), signUpRequest.getUser_gender(),
                             signUpRequest.getUser_address(), signUpRequest.getMedical_record(),
                            encoder.encode(signUpRequest.getUser_password()));

        Set<String> signupRoles = signUpRequest.getUser_role();
        Set<Role> roles = new HashSet<>();

        if (signupRoles == null) {
            Role userRole = roleRepository.findByRolename(ERoles.ROLE_PATIENT).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            System.out.println("ROL NULLLLLLL");
        }
        else {
            signupRoles.forEach(role -> {
                switch (role) {
                    case "doctor":
                        Role doctorRole = roleRepository.findByRolename(ERoles.ROLE_DOCTOR).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(doctorRole);
                        System.out.println("ROL doctorrrr");
                        break;
                    case "caregiver":
                        Role caregiverRole = roleRepository.findByRolename(ERoles.ROLE_CAREGIVER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(caregiverRole);
                        break;

                    default:
                        Role patientRole = roleRepository.findByRolename(ERoles.ROLE_PATIENT).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(patientRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
