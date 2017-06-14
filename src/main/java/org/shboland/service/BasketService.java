package org.shboland.service;

import org.shboland.model.basket.Basket;
import org.shboland.model.basket.BasketRepository;
import org.shboland.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    public Basket fetchBasket(String basketId) {
        return basketRepository.findOne(Long.valueOf(basketId));
    }

    public void addProduct(String basketId, Product product) {
        Basket basket = basketRepository.findOne(Long.valueOf(basketId));
        basket.getProductList().add(product);
        basketRepository.save(basket);
    }
}
