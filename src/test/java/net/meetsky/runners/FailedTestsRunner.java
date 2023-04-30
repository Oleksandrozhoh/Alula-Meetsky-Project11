package net.meetsky.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(

        features = "@target/rerun.txt",

        glue = "net/meetsky/step_definitions"

)

public class FailedTestsRunner {
}
