import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringProfileCucumber.class)
@CucumberOptions(format = "pretty", features = "src/test/cucumber/feature")
public class IntegrationTests {
}
