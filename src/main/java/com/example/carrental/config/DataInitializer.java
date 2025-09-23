package com.example.carrental.config;

import com.example.carrental.model.Role;
import com.example.carrental.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        if (!roleRepository.findByName("ROLE_CUSTOMER").isPresent()) {
            roleRepository.save(new Role(null, "ROLE_CUSTOMER"));
        }
        if (!roleRepository.findByName("ROLE_ADMIN").isPresent()) {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
        }
    }
}