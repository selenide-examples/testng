package org.selenide.examples.testng;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Listeners(SoftAsserts.class)
abstract class AbstractSoftAssertTestNGTest {
  @BeforeMethod
  final void openBrowser() {
    closeWebDriver();
    Configuration.assertionMode = SOFT;
    Configuration.timeout = 1;
    open("https://selenide.org/users.html");
  }
}
