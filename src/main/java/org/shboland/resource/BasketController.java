package org.shboland.resource;

import org.shboland.model.basket.Basket;
import org.shboland.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.shboland.service.BasketService;

import java.net.URI;

@RestController
public class BasketController implements IBasketController {

    @Autowired
    private BasketService basketService;

    @Override
    public Basket getBasket(@PathVariable String basketId) {
        return basketService.fetchBasket(basketId);
    }

    @Override
    public ResponseEntity addProduct(@PathVariable String basketId, @RequestBody Product product) {

        basketService.addProduct(basketId, product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + basketId)
                .buildAndExpand().toUri();

        return ResponseEntity.created(location).build();
    }
}
