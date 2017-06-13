package resource;

import model.Basket;
import model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final AtomicLong counter = new AtomicLong();

    private List<Product> productList = new ArrayList<>();

    public BasketController() {
        Product product1 = new Product(1, "Wooden chair", "This is a oak hand made chair.");
        productList.add(product1);
        Product product2 = new Product(2, "Suede poof", "Original maroccan poof.");
        productList.add(product2);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Basket getBasket(@RequestParam(value="basketId", defaultValue = "1") String basketId) {
        return new Basket(counter.incrementAndGet(), this.productList);
    }

    @RequestMapping(path = "/product", method = RequestMethod.POST)
    ResponseEntity addProduct(@RequestParam(value="basketId", defaultValue = "1") String basketId, @RequestBody Product product) {

        this.productList.add(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand().toUri();

        return ResponseEntity.created(location).build();
    }
}
