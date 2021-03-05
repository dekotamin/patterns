package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {
    String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Test
    void shouldRegisterByDelivery() {
        open("http://localhost:9999");
        //SelenideElement form = $("form");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(inputDate = "17.03.2021");
        $("[data-test-id=name] input").setValue("Василий Дегин");
        $("[data-test-id=phone] input").setValue("+79110000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно")).waitUntil(Condition.visible, 15000);
        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + inputDate));
    }
}