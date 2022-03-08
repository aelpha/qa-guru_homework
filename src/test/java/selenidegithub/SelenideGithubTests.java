package selenidegithub;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideGithubTests extends TestBase {

    @Test
    public void wikiHaveSoftAssertionsPage() {
        open("/selenide/selenide");
        $("h1").shouldHave(text("""
                selenide
                /
                selenide"""));
        $("#wiki-tab").click();
        $("#wiki-pages-box button").click();
        $("#wiki-pages-box").$$("li").findBy(text("SoftAssertions")).shouldBe(visible).click();
        $$(".markdown-body ol").findBy(text("JUnit5")).shouldBe(visible);
    }
}
