package org.example.slot.ui;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.slot.beans.BeansConfig;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/resources/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example.slot.ui")
@CucumberContextConfiguration
@SpringBootTest(classes = BeansConfig.class)
public class CucumberRunner implements TestWatcher {

    /*@Autowired
    private WebDriver driver;

    public void testFailed(ExtensionContext context, Throwable cause) {
        takeScreenshot();
    }

    public void testAborted(ExtensionContext context, Throwable cause) {
        takeScreenshot();
    }

    private void takeScreenshot() {
        var screenshot = (TakesScreenshot) driver;
        var file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileCopyUtils.copy(file, new File("./fail_screenshots/" + LocalDateTime.now() + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
