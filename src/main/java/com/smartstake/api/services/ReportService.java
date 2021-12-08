package com.smartstake.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class ReportService {
    @Autowired
    RestTemplate restTemplate;

    public void getAllOrders() {
        // get all orders
    }

    public void getCompletedOrders() {
        // get completed orders
    }

    public void getCancelledOrders() {

    }
}
