package resource;

import model.Basket;
import model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BasketController implements IBasketController {

    private List<Product> productList = new ArrayList<>();

    public BasketController() {
        Product product1 = new Product("1", "Wooden chair", "This is a oak hand made chair.");
        productList.add(product1);
        Product product2 = new Product("2", "Suede poof", "Original maroccan poof.");
        productList.add(product2);
    }

    @Override
    public Basket getBasket(@PathVariable String basketId) {
        return new Basket(basketId, this.productList);
    }

    @Override
    public ResponseEntity addProduct(@PathVariable String basketId, @RequestBody Product product) {

        this.productList.add(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + basketId)
                .buildAndExpand().toUri();

        return ResponseEntity.created(location).build();
    }
}
