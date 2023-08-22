package org.selenide.examples.testng;

import com.codeborne.selenide.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.AssertionMode.STRICT;
import static com.codeborne.selenide.Selenide.open;

abstract class BaseTestNGTest {
  private static final Logger log = LoggerFactory.getLogger(BaseTestNGTest.class);

  @BeforeClass
  final void startTestClass() {
    log.info("Start {} TestNG tests in {}", getClass().getName(), Configuration.browser);
  }

  @BeforeMethod
  public void setUp() {
    Configuration.timeout = 1;
    Configuration.assertionMode = STRICT;
    open("https://selenide.org/users.html");
  }

  @AfterClass
  final void finishTestClass() {
    log.info("Finished {} TestNG tests in {}", getClass().getName(), Configuration.browser);
  }
}
