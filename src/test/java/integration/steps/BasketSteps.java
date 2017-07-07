package integration.steps;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.shboland.BasketApplication;
import org.shboland.model.basket.Basket;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = BasketApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class BasketSteps {

    private static final String SERVICE_URL = "http://localhost:8881/baskets";
    private static final String APPLICATION_JSON = "application/json";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private HttpResponse response;
    private String basketId;

    private String jsonBasket = "{\"productList\":[]}";
    private String jsonProduct = "{\"name\":\"Rockingchair\",\"description\":\"Like the one grandma has.\"}";

    @Before
    public void clean() {

    }

    @Given("^The basket contains '(.*)' products")
    public void theBasketContainsXProducts(final String numberOfProducts) throws Throwable {

        HttpPost request = new HttpPost(SERVICE_URL);
        request.addHeader("content-type", "application/json");
        HttpEntity bodyEntity = new StringEntity(jsonBasket);
        request.setEntity(bodyEntity);
        HttpResponse response = httpClient.execute(request);
        assertEquals("Basket was not created correctly.",
                HttpStatus.CREATED, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
        Header[] headers = response.getAllHeaders();
        Header locationHeader = Arrays.stream(headers).filter(h -> h.getName().equals("Location")).findFirst().get();
        basketId = last(locationHeader.getValue().split("/"));

        IntStream.range(0, Integer.valueOf(numberOfProducts)).forEach(i -> {
            try {
                addProduct();
            } catch(Exception e) {}
        });
    }

    @When("^I ask for the basket")
    public void iAskForTheBasket() throws Throwable {

        HttpGet request = new HttpGet(SERVICE_URL + "/" + basketId);
        request.addHeader("accept", APPLICATION_JSON);
        response = httpClient.execute(request);
    }

    @When("^I add a product to the basket")
    public void iAddAProductToTheBasket() throws Throwable {
        addProduct();
    }

    @Then("^(?:I get|the user gets) a list with '(.*)' products$")
    public void iGetAListWithXProducts(final String numberOfProducts) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = response.getEntity().getContent();
        String inputString = IOUtils.toString(inputStream);

        Basket basket = mapper.readValue(inputString, Basket.class);
        assertEquals("Not right number of products returned.",
                Integer.valueOf(numberOfProducts), basket.getProductList().size());
    }

    private void addProduct() throws Exception {
        HttpPost request = new HttpPost(SERVICE_URL + "/" + basketId + "/products");
        request.addHeader("content-type", "application/json");
        HttpEntity bodyEntity = new StringEntity(jsonProduct);
        request.setEntity(bodyEntity);
        response = httpClient.execute(request);
    }

    public static <T> T last(T[] array) {
        return array[array.length - 1];
    }
}
