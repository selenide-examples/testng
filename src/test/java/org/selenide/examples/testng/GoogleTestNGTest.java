package org.selenide.examples.testng;

import com.codeborne.selenide.testng.TextReport;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@Listeners(TextReport.class)
public class GoogleTestNGTest extends BaseTestNGTest {
  @BeforeMethod
  public void setUp() {
    TextReport.onSucceededTest = false;
    TextReport.onFailedTest = true;
    open("https://google.com/ncr");
  }

  @Test(enabled = false)
  public void failingMethod() {
    $(By.name("q")).shouldBe(visible, enabled);
    $("#missing-button").click();
  }

  @Test
  public void successfulMethod() {
    $(By.name("q")).setValue("selenide").pressEnter();
    $$("#res .g").shouldHave(sizeGreaterThan(5));
  }
}
