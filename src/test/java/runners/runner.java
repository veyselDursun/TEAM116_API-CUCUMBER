package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

public class runner {

    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {
                    "pretty",
                    "html:target/default-cucumber-reports.html",
                    "json:target/json-reports/cucumber.json",
                    "junit:target/xml-report/cucumber.xml",
                    "rerun:target/failedRerun.txt"
            },
            features = "src/test/resources/features",
            glue = {"stepDefinitions", "hooks/api"},
            tags = "@US1001",
            dryRun = true
    )
    public class RunnerAPI {
    }
}
