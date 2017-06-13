package resource;

import model.Basket;
import model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/baskets")
public interface IBasketController {

    @RequestMapping(path = "/{basketId}", method = RequestMethod.GET)
    Basket getBasket(@PathVariable String basketId);

    @RequestMapping(path = "/{basketId}/product", method = RequestMethod.POST)
    ResponseEntity addProduct(@PathVariable String basketId, @RequestBody Product product);
}
