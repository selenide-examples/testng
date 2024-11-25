package org.selenide.examples.testng;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SimpleTest extends BaseTestNGTest {
  @Test
  public void successfulMethod() {
    $("#user-tags .reset-filter").shouldBe(visible).shouldHave(text("all"));
    $$("#user-tags .tag").shouldHave(sizeGreaterThan(5));
  }
}
