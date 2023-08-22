package org.selenide.examples.testng;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.codeborne.selenide.testng.TextReport;

@Listeners(TextReport.class)
public class SelenideOrgTest extends BaseTestNGTest {
  @BeforeMethod
  public void setUp() {
    TextReport.onSucceededTest = false;
    TextReport.onFailedTest = true;
    open("https://selenide.org/users.html");
  }

  @Test(enabled = false)
  public void failingMethod() {
    $$("#user-tags .tag").shouldHave(sizeGreaterThan(Integer.MAX_VALUE));
    $("#missing-button").click();
  }

  @Test
  public void successfulMethod() {
    $("#user-tags .reset-filter").shouldBe(visible).shouldHave(text("all"));
    $$("#user-tags .tag").shouldHave(sizeGreaterThan(5));
  }
}
