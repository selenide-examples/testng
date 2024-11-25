package org.selenide.examples.testng;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SoftAssertTestNGTest1 extends AbstractSoftAssertTestNGTest {

  /**
   * Expected to throw: com.codeborne.selenide.ex.SoftAssertionError
   */
  @Test(enabled = false)
  public void softAsserts_wraps_multipleFailuresToSingleError() {
    $("#radioButtons input").shouldHave(value("777"));
    $("#xxx").shouldBe(visible);
    $$("#radioButtons input").shouldHave(size(888));
    $("#radioButtons").$$("input").shouldHave(size(999));
  }

  @Test
  public void successfulTest() {
  }

  /**
   * Expected to throw: com.codeborne.selenide.ex.ElementNotFound
   */
  @Test(enabled = false)
  public void softAsserts_but_onlyOneFailure() {
    $("h666").shouldBe(visible);
  }
}
