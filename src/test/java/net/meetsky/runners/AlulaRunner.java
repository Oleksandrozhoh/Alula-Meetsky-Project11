package net.meetsky.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(

        features = "src/test/resources/features",
        glue = "net/meetsky/step_definitions",
        plugin = {
                "html:target/alula-report.html",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber.json"
        },
        publish = true,
        tags = "@smoke",
        dryRun = false

)

public class AlulaRunner {
}
