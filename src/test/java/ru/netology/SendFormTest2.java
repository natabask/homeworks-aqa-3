package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendFormTest2 {

    @Test
    public void shouldFailWhenNameIsIncorrect() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Peter the Great");
        $("[data-test-id=phone] input").setValue("+79886752435");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click(); // send form
        // тест на валидность поля
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
        // тест на проверку сообщения об ошибке (если нужно проверить текст сообщения)
        $("[data-test-id=name]").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}