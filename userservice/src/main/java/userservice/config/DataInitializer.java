package userservice.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import userservice.entity.Role;
import userservice.entity.User;
import userservice.repository.RoleRepository;
import userservice.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles
        initRoles();
        
        // Create users with roles
        createUser("admin", "Admin", "admin@example.com", passwordEncoder.encode("password"), "ADMIN");
        createUser("hasan", "User", "user@example.com", passwordEncoder.encode("1234"), "USER");
    }

    private void createUser(String username, String name, String email, String password, String role) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setFirstName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setRoles(new ArrayList<Role>()); // Set the role for the user

        userRepository.save(user);
    }
    
    private void initRoles() {
        // Check if roles already exist
        if (roleRepository.count() == 0) {
            // Create and save roles
            Role adminRole = new Role();
            adminRole.setRoleId("ADMIN");
            adminRole.setName("Admin");
            adminRole.setDescription(" Administrator role");

            Role userRole = new Role();
            userRole.setRoleId("USER");
            userRole.setName("User");
            userRole.setDescription("Normal user role");

            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        }
    }
}