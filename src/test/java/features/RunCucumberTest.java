package features;

import io.cucumber.java.DataTableType;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;

import java.util.Map;

@Cucumber
@CucumberOptions(plugin = {"pretty"})
public class RunCucumberTest {
    @DataTableType
    public AdTestItem adEntry(Map<String, String> entry){
        return AdTestItem.FromMap(entry);
    }
}
