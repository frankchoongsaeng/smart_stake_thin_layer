package com.smartstake.api.services;

import com.smartstake.api.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReportService {
    @Autowired
    RestTemplate restTemplate;

    private final String REPORT_ENDPOINT = "http://localhost:8080/stubs/report";

    public void getAllOrders() {
        // get all orders
    }

    public void getCompletedOrders() {
        // get completed orders
    }

    public void getCancelledOrders() {

    }

    public ResponseEntity<OrderDTO> getSingleOrder(String id) {
        return restTemplate.getForEntity(REPORT_ENDPOINT + "/orders", OrderDTO.class);
    }
}
