package com.smartstake.api.controllers;

import com.smartstake.api.model.entities.ClientEntity;
import com.smartstake.api.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/clients")
    public Set<ClientEntity> getAllClients() {
        return adminService.getAllClients();
    }
}
