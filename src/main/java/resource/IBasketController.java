package resource;

import model.Basket;
import model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/basket")
public interface IBasketController {

    @RequestMapping(method = RequestMethod.GET)
    Basket getBasket(@RequestParam(value="basketId", defaultValue = "1") String basketId);

    @RequestMapping(path = "/product", method = RequestMethod.POST)
    ResponseEntity addProduct(@RequestParam(value="basketId", defaultValue = "1") String basketId, @RequestBody Product product);
}
