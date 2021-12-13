package com.smartstake.api.controllers;

import com.smartstake.api.dto.OrderDTO;
import com.smartstake.api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        boolean isValid = orderService.validateCreateOrder(orderDTO);
        if(!isValid) return new ResponseEntity<>("Invalid request. Your order was not be created.", HttpStatus.BAD_REQUEST);
        return orderService.createOrder(orderDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO orderDTO) {
        boolean isValid = orderService.validateUpdateOrder(orderDTO);
        if(!isValid) return new ResponseEntity<>("Invalid request. Your order could not be updated.", HttpStatus.BAD_REQUEST);
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id);
    }
}
