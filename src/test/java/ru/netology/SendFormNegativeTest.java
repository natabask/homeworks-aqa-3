package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendFormNegativeTest {

    @Test
    public void shouldFailWhenNameIsIncorrect() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Peter the Great");
        $("[data-test-id=phone] input").setValue("+79886752435");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click(); // send form
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldFailWhenPhoneIsIncorrect() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Попович");
        $("[data-test-id=phone] input").setValue("+79886752");
        $("[data-test-id=agreement]").click();
        $("button").click(); // send form
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldFailWhenAgreementIsNotAccepted() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Попович");
        $("[data-test-id=phone] input").setValue("+79886752876");
//      $("[data-test-id=agreement]").click(); не кликаем чекбокс с соглашением
        $("button").click(); // send form
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }

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

    @Test
    public void shouldFailWhenNameIsEmpty() {
        open("http://localhost:9999");
        // $("[data-test-id=name] input").setValue("Иван Васильевич"); забыли заполнить поле Имя
        $("[data-test-id=phone] input").setValue("+79886897777");
        $("[data-test-id=agreement]").click();
        $("button").click(); // send form
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldFailWhenPhoneIsEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Петя Пипеткин");
        // $("[data-test-id=phone] input").setValue("+79886897777"); забыли заполнить поле Телефон
        $("[data-test-id=agreement]").click();
        $("button").click(); // send form
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldFailWhenNameAndPhoneAreEmpty() {
        open("http://localhost:9999");
        // $("[data-test-id=name] input").setValue("Иван Васильевич"); забыли заполнить поле Имя
        // $("[data-test-id=phone] input").setValue("+79886897777"); забыли заполнить поле Телефон
        $("[data-test-id=agreement]").click();
        $("button").click(); // send form
        // должно подсвечиваться только первое неправильно заполненное поле
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
        $("[data-test-id=phone]").shouldNotHave(cssClass("input_invalid"));
    }

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