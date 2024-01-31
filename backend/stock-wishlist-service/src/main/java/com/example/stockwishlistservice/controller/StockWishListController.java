package com.example.stockwishlistservice.controller;

import com.example.stockwishlistservice.entity.StockWishList;
import com.example.stockwishlistservice.model.WishList;
import com.example.stockwishlistservice.service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/wishlist")
public class StockWishListController {
    private static final String USERNAME_HEADER = "X-USERNAME";
    @Autowired
    WishListService wishListService;

    @GetMapping("/stocks")
    public Optional<StockWishList> hello(HttpServletRequest request) {
        String username = request.getHeader(USERNAME_HEADER);
        return wishListService.getWishListStocks(username);
    }

    @PostMapping("/stocks")
    public String addStockToWishList(HttpServletRequest request, @RequestBody WishList wishList) {
        String username = request.getHeader(USERNAME_HEADER);
        wishListService.addToWishlist(username, wishList);
        return "Stocks Successfully added to wishlist!";
    }

    @DeleteMapping("/stocks")
    public StockWishList removeStockFromWishList(HttpServletRequest request, @RequestBody WishList wishList) {
        String username = request.getHeader(USERNAME_HEADER);
        return wishListService.removeFromWishlist(username, wishList);
    }
}
