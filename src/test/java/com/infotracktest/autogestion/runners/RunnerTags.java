package com.infotracktest.autogestion.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features"
        , snippets = SnippetType.CAMELCASE
        , tags = "@CP001_Creacion_OrdenServicio"
        , glue = "com.infotracktest.autogestion.definition")

public class RunnerTags {
}
