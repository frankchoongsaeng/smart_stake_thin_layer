package com.smartstake.api.controllers;

import com.smartstake.api.dto.OrderDTO;
import com.smartstake.api.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    RestTemplate restTemplate;



    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getAllClientOrders(@NonNull @PathVariable String id) {
        return reportService.getSingleOrder(id);
    }
}
