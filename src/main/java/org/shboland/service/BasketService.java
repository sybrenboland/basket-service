package org.shboland.service;

import org.shboland.model.Basket;
import org.shboland.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {

    private List<Product> productList = new ArrayList<>();

    public BasketService() {
        Product product1 = new Product("1", "Wooden chair", "This is a oak hand made chair.");
        productList.add(product1);
        Product product2 = new Product("2", "Suede poof", "Original maroccan poof.");
        productList.add(product2);
    }

    public Basket fetchBasket(String basketId) {
        return new Basket(basketId, this.productList);
    }

    public void addProduct(String basketId, Product product) {
        this.productList.add(product);
    }
}
