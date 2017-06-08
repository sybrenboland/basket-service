package integration;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(SpringProfileCucumber.class)
@CucumberOptions(format = "pretty", features = "src/test/java/integration/feature")
public class IntegrationTests {
}
