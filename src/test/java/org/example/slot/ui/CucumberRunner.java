package org.example.slot.ui;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.slot.beans.UiBeansConfig;
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
@SpringBootTest(classes = UiBeansConfig.class)
public class CucumberRunner{
}
