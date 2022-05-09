package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendFormTest5 {

    @Test
    public void shouldFailWhenNameAndPhoneIncorrect() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Yuriy Gagarin");
        $("[data-test-id=phone] input").setValue("+79886");
        $("[data-test-id=agreement]").click();
        $("button").click(); // send form
        // должно подсвечиваться только первое неправильно заполненное поле
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=phone]").shouldNotHave(cssClass("input_invalid"));
    }
}