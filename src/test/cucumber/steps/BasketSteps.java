package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Basket;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import resource.BasketApplication;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = BasketApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class BasketSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private Basket basket;

    private ResponseEntity<Basket> responseEntity;

    @Before
    public void setUp() {
        List<Product> productList = new ArrayList();

        Product product1 = new Product(1, "Wooden chair", "This is a oak hand made chair.");
        productList.add(product1);
        Product product2 = new Product(2, "Suede poof", "Original maroccan poof.");
        productList.add(product2);

        basket = new Basket(7L, productList);
    }

    @Given("^I have a basket")
    public void i_have_a_basket() throws Throwable {
        assertNotNull(basket);
    }

    @When("^I ask for the basket")
    public void i_ask_for_the_basket() throws Throwable {

        responseEntity = restTemplate
                .getForEntity("http://localhost:8881".concat("/").concat("basket"), Basket.class);
    }

    @Then("^(?:I get|the user gets) a '(.*)' response$")
    public void I_get_a__response(final String statusCode) throws Throwable {
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.valueOf(Integer.valueOf(statusCode))));
    }
}
