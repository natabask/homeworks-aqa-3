package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendFormTest {
    WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp2() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void close() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldPassWhenAllDataCorrect() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=\"name\"] input")).sendKeys("Василий Попович");
        driver.findElement(By.cssSelector("[data-test-id=\"phone\"] input")).sendKeys("+79886752435");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button")).click(); // send form
        String text = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}