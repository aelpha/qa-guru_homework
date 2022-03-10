package selenidegithub;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    //*** Добавлен второй комментарий, чтоб сделать коммит***
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x1080";
    }

    @AfterAll
    //***Добавлен третий комментарий***
    static void afterAll(){
        closeWebDriver();
    }
}
