package com.example.stockorderservice.controller;


import com.example.stockorderservice.dto.OrderRequestDto;
import com.example.stockorderservice.entity.PurchaseOrder;
import com.example.stockorderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final String USERNAME_HEADER = "X-USERNAME";

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(HttpServletRequest request, @Valid @RequestBody OrderRequestDto orderRequestDto) {
        orderRequestDto.setUserId(request.getHeader(USERNAME_HEADER));
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping
    public List<PurchaseOrder> getOrders(HttpServletRequest request) {
        return orderService.getAllOrders(request.getHeader(USERNAME_HEADER));
    }
}
