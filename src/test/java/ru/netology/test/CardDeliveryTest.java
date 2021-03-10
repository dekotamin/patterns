package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationByDate;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldCityAndNameAreValid () {
        RegistrationByDate newUser = DataGenerator.Registration.generateByDate("ru");
        String validCity = DataGenerator.Registration.generateValidCity();
        String validName = DataGenerator.Registration.generateValidName();
        open("http://localhost:9999");
        $("[data-test-id=city] [placeholder=Город]").setValue(validCity);
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(newUser.getDataOfMeeting());
        $("[data-test-id='name'] .input__control").setValue(validName);
        $("[data-test-id='phone'] .input__control").setValue(newUser.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").
                shouldBe(Condition.visible, Duration.ofMillis(15000)).
                shouldHave(text("Встреча успешно запланирована на " + newUser.getDataOfMeeting()));

        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(newUser.getAnotherDataOfMeeting());
        $$("[type='button']").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification] .notification__content").
                shouldBe(Condition.visible, Duration.ofMillis(15000)).
                shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$(".button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").
                shouldBe(Condition.visible, Duration.ofMillis(15000)).
                shouldHave(text("Встреча успешно запланирована на " + newUser.getAnotherDataOfMeeting()));
    }

    @Test
    void shouldCityIsInvalid () {
        RegistrationByDate newUser = DataGenerator.Registration.generateByDate("ru");
        String invalidCity = DataGenerator.Registration.generateInvalidCity();
        String validName = DataGenerator.Registration.generateValidName();
        open("http://localhost:9999");
        $("[data-test-id=city] [placeholder=Город]").setValue(invalidCity);
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(newUser.getDataOfMeeting());
        $("[data-test-id='name'] .input__control").setValue(validName);
        $("[data-test-id='phone'] .input__control").setValue(newUser.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(".input_invalid[data-test-id=city] .input__sub").
                shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNameIsInvalid () {
        RegistrationByDate newUser = DataGenerator.Registration.generateByDate("ru");
        String validCity = DataGenerator.Registration.generateValidCity();
        String invalidName = DataGenerator.Registration.generateInvalidName();
        open("http://localhost:9999");
        $("[data-test-id=city] [placeholder=Город]").setValue(validCity);
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(newUser.getDataOfMeeting());
        $("[data-test-id='name'] .input__control").setValue(invalidName);
        $("[data-test-id='phone'] .input__control").setValue(newUser.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(".input_invalid[data-test-id=name] .input__sub").
                shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }
}