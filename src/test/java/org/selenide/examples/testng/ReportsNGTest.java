package org.selenide.examples.testng;

import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Listeners(TextReport.class)
public class ReportsNGTest extends BaseTestNGTest {

  @Test(expectedExceptions = ElementNotFound.class)
  public void failingMethod() {
    $("h22").shouldBe(visible).shouldHave(text("Selenide"));
  }

  @Test
  public void successfulMethod() {
    $("#user-tags .reset-filter").shouldBe(visible);
  }

  @Test
  public void reportingCollections() {
    $$("#user-tags .tag").shouldHave(sizeGreaterThan(5));
  }
}
