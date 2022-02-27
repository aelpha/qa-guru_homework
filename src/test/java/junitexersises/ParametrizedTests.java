package junitexersises;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTests {

    @BeforeEach
    public void beforeEach(){
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    public void afterEach(){
        closeWebDriver();
    }

    @CsvSource(value = {
            "zero| ноль",
            "Change the encoding used to view a file| Измените кодировку, используемую для просмотра файла"

    },
            delimiter = '|')
    @ParameterizedTest (name = "Проверка яндекс-переводчика")
    public void yandexTranslatorTest(String testData, String testResult ){
        open("https://translate.yandex.by/");
        $("#fakeArea").setValue(testData);
        $$("#translation").last().shouldHave(text(testResult));

    }

    @ValueSource(strings = {"sample@sample.com", "nettakogonika", "+375293450000"})
    @ParameterizedTest (name = "Восстановление пароля. Невалидные данные: {0}")
    public void passwordRestoreTest(String testData){
        open("https://profile.onliner.by/recover-password");
        $("[type=text]").setValue(testData);
        $("[type=submit]").click();
        $(".auth-form__description_error").shouldHave(text("Такой пользователь не зарегистрирован"));
    }
}
