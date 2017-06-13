package integration.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Basket;
import model.Product;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import resource.BasketApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = BasketApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class BasketSteps {

    private static final String SERVICE_URL = "http://localhost:8881/baskets";
    private static final String APPLICATION_JSON = "application/json";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private HttpResponse response;

    private Basket basket;
    private String basketId;

    private String jsonProduct;

    @Before
    public void setUp() {
        List<Product> productList = new ArrayList();

        Product product1 = new Product("1", "Wooden chair", "This is a oak hand made chair.");
        productList.add(product1);
        Product product2 = new Product("2", "Suede poof", "Original maroccan poof.");
        productList.add(product2);

        basketId = "7";
        basket = new Basket(basketId, productList);

        jsonProduct = "{\"id\":\"3\",\"name\":\"Rockingchair\",\"description\":\"Like the one grandma has.\"}";
    }

    @Given("^I have a basket")
    public void i_have_a_basket() throws Throwable {
        assertNotNull(basket);
    }

    @Given("^I have a correct basketId")
    public void i_have_a_correct_basketId() throws Throwable {
        assertNotNull(basketId);
    }

    @When("^I ask for the basket")
    public void i_ask_for_the_basket() throws Throwable {

        HttpGet request = new HttpGet(SERVICE_URL + "/2");
        request.addHeader("accept", APPLICATION_JSON);
        response = httpClient.execute(request);
    }

    @When("^I add a product to the basket")
    public void i_add_a_product_to_the_basket() throws Throwable {

        HttpPost request = new HttpPost(SERVICE_URL + "/2/product");
        request.addHeader("content-type", "application/json");
        HttpEntity bodyEntity = new StringEntity(jsonProduct);
        request.setEntity(bodyEntity);
        response = httpClient.execute(request);
    }

    @Then("^(?:I get|the user gets) a '(.*)' response$")
    public void I_get_a__response(final String statusCode) throws Throwable {
        assertThat(HttpStatus.valueOf(response.getStatusLine().getStatusCode()),
                is(HttpStatus.valueOf(Integer.valueOf(statusCode))));
    }
}
