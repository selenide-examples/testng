package org.selenide.examples.testng;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.util.Collections.unmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.SoftAssertionError;
import com.codeborne.selenide.ex.UIAssertionError;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;

public class SoftAssertTestNGScreenshotsTest extends AbstractSoftAssertTestNGTest {

  private final TestLogListener testLogListener = new TestLogListener();

  @BeforeMethod
  public void addListener() {
    SelenideLogger.addListener("SoftAssertTestLogListener", testLogListener);
  }

  /**
   * Expected to throw: com.codeborne.selenide.ex.SoftAssertionError
   */
  @Test(enabled = false)
  public void shouldTakeScreenshotForSoftAsserts() {
    $("h666").shouldBe(visible);
    $$("#radioButtons input").shouldHave(size(888));
    assertThat(testLogListener.getEvents()).hasSize(2);
    testLogListener.getEvents().stream().map(LogEvent::getError).forEach(error -> {
      assertThat(error).isInstanceOf(UIAssertionError.class);
      assertScreenshot((UIAssertionError) error);
    });
  }

  @AfterMethod
  public void tearDown() {
    SelenideLogger.removeListener("SoftAssertTestLogListener");
  }

  @ParametersAreNonnullByDefault
  private static class TestLogListener implements LogEventListener {

    private final List<LogEvent> events = new ArrayList<>();

    Collection<LogEvent> getEvents() {
      return unmodifiableList(events);
    }

    @Override
    public void afterEvent(LogEvent currentLog) {
      events.add(currentLog);
    }

    @Override
    public void beforeEvent(LogEvent currentLog) {
      //ignore
    }
  }

  private void assertScreenshot(UIAssertionError expected) {
    assertThat(expected.getScreenshot().getImage())
      .contains(Configuration.reportsFolder);
  }
}
