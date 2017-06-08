package integration;

import cucumber.api.junit.Cucumber;
import org.junit.runners.model.InitializationError;

import java.io.IOException;

public class SpringProfileCucumber extends Cucumber {
    public SpringProfileCucumber(Class clazz) throws InitializationError, IOException {
        super(clazz);
        System.setProperty("spring.profiles.active", "development");
    }
}
