package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendFormTest {

    @Test
    public void shouldPassWhenAllDataCorrect() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Попович");
        $("[data-test-id=phone] input").setValue("+79886752435");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click(); // send form
        $("[data-test-id=\"order-success\"]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}