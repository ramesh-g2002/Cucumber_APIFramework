package CucumberOption;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".//Features/Loginpage.feature",
glue = "StepDefinations",plugin = "json:target/jsonreports/cucumber-report.json")

public class TestRunner {

}
