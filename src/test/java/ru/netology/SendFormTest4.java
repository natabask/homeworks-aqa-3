package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendFormTest4 {

    @Test
    public void shouldFailWhenAgreementIsNotAccepted() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Попович");
        $("[data-test-id=phone] input").setValue("+79886752876");
//      $("[data-test-id=agreement]").click(); не кликаем чекбокс с соглашением
        $("button").click(); // send form
        // тест на валидность поля
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
}