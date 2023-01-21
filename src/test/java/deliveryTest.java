import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {
    public String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

    String planningDate = generateDate(4);

    //public static String dateGenerator() {
    //DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    //Calendar c = Calendar.getInstance();
    //c.add(Calendar.DATE, 4);
    //return dateFormat.format(c.getTime());
    //}


    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Санкт-Петербург");
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(planningDate);
        form.$("[data-test-id=name] input").setValue("Тестов Тест");
        form.$("[data-test-id=phone] input").setValue("+78536521458");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))

                .shouldBe(Condition.visible);


    }

    @Test
    void shouldSubmitRequestWithList() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Са");
        $x("//span[text()='Санкт-Петербург']").click();
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(planningDate);
        form.$("[data-test-id=name] input").setValue("Тестов Тест");
        form.$("[data-test-id=phone] input").setValue("+76528531459");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();


        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))

                .shouldBe(Condition.visible);

    }

}