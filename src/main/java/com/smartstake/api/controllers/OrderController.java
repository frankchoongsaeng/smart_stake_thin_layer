package com.smartstake.api.controllers;

import com.smartstake.api.dto.OrderDTO;
import com.smartstake.api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
    }

    @PutMapping("/update")
    public void updateOrder(@RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id);
    }
}
