import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Selenide.*;

class deliveryTest {

    public static String dateGenerator() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 4);

        return dateFormat.format(c.getTime());
    }


    @Test
    void test1() {


        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Санкт-Петербург");
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(dateGenerator());
        form.$("[data-test-id=name] input").setValue("Тестов Тест");
        form.$("[data-test-id=phone] input").setValue("+78536521458");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + dateGenerator()), Duration.ofSeconds(15));

    }

    @Test
    void test2() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Санкт-Петербург");
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(dateGenerator());
        form.$("[data-test-id=name] input").setValue("Тестов Тест");
        form.$("[data-test-id=phone] input").setValue("+78536521458");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + dateGenerator()), Duration.ofSeconds(15))

                .shouldBe(Condition.visible);

    }

}