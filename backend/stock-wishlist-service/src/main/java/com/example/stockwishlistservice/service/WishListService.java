package com.example.stockwishlistservice.service;

import com.example.stockwishlistservice.entity.StockWishList;
import com.example.stockwishlistservice.model.WishList;
import com.example.stockwishlistservice.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishListService {
    @Autowired
    WishlistRepository wishlistRepository;

    public Optional<StockWishList> getWishListStocks(String userName) {
        return wishlistRepository.findById(userName);
    }

    public StockWishList addToWishlist(String userName, WishList wishList) {
        StockWishList wishlist = wishlistRepository.findById(userName).orElseGet(() -> {
            StockWishList newWishlist = new StockWishList();
            newWishlist.setEmail(userName);
            return newWishlist;
        });

        wishlist.getStocks().addAll(wishList.getStockSymbol());
        return wishlistRepository.save(wishlist);
    }

    public StockWishList removeFromWishlist(String userName, WishList wishList) {
        return wishlistRepository.findById(userName)
                .map(wishlistRepo -> {
                    wishlistRepo.getStocks().removeAll(wishList.getStockSymbol());
                    return wishlistRepository.save(wishlistRepo);
                })
                .orElse(null);
    }
}
