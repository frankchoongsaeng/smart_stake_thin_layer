package com.smartstake.api.controllers;

import com.smartstake.api.dto.OrderDTO;
import com.smartstake.api.services.OrderService;
import com.smartstake.api.services.OrderValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderValidationService orderValidationService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        boolean isValid = orderValidationService.validateCreateOrder(orderDTO);
        if(!isValid) return new ResponseEntity<>("Invalid create order request. Your order was not be created.", HttpStatus.BAD_REQUEST);
        return orderService.createOrder(orderDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO orderDTO) {
        boolean isValid = orderValidationService.validateUpdateOrder(orderDTO);
        if(!isValid) return new ResponseEntity<>("Invalid update request. Your order could not be updated.", HttpStatus.BAD_REQUEST);
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id);
    }
}
