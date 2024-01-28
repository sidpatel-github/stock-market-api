package com.example.stockwishlistservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/stock-wishlist")
public class StockWishListController {


    @GetMapping("")
    public String hello() {
        return "Hello from Wishlist service";
    }

}
