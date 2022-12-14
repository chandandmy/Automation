package com.sedintechnologies.SauceDemoUIAutomation;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
                                             glue="stepDefinations",
                                             tags="@sauceDemo",plugin="json:target/jsonReports/cucumber-report.json",dryRun = false
                                             )

public class TestRunner {

}
