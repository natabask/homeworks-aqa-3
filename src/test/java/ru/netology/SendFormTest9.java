package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendFormTest9 {

    @Test
    public void shouldFailWhenNameAndPhoneAreEmptyAndAgreementIsNotAccepted() {
        open("http://localhost:9999");
        // $("[data-test-id=name] input").setValue("Иван Васильевич"); забыли заполнить поле Имя
        // $("[data-test-id=phone] input").setValue("+79886897777"); забыли заполнить поле Телефон
        // $("[data-test-id=agreement]").click();
        $("button").click(); // send form
        // должно подсвечиваться только первое неправильно заполненное поле
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=phone]").shouldNotHave(cssClass("input_invalid"));
    }
}