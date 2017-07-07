package org.shboland.resource;


import org.shboland.model.basket.Basket;
import org.shboland.model.product.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/baskets")
public interface IBasketController {

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity createBasket(@RequestBody Basket basket);

    @RequestMapping(path = "/{basketId}", method = RequestMethod.GET)
    Basket getBasket(@PathVariable String basketId);

    @RequestMapping(path = "/{basketId}/products", method = RequestMethod.POST)
    ResponseEntity addProduct(@PathVariable String basketId, @RequestBody Product product);
}
