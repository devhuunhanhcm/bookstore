package com.indentity_oauth2.security.config;

import com.indentity_oauth2.role.model.CRole;
import com.indentity_oauth2.role.repository.CRoleRepository;
import com.indentity_oauth2.user.model.CUser;
import com.indentity_oauth2.user.repository.CUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CRoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(CUserRepository cUserRepository) {
        return args -> {
            if(cUserRepository.findByUsername("admin1").isEmpty()){
                CRole adminRole = CRole.builder().code("ROLE_ADMIN").build();
                CRole userRole = CRole.builder().code("ROLE_USER").build();
                CUser adminUser = CUser.builder()
                                    .username("admin1")
                                    .displayName("admin1")
                                    .email("admin1@gmail.com")
                                    .password(passwordEncoder.encode("Admin123*"))
                                    .build();
                adminUser.getRoles().add(adminRole);
                cUserRepository.save(adminUser);
                roleRepository.save(userRole);
            }
        };
    }
}
