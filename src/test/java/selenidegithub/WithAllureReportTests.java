package selenidegithub;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class WithAllureReportTests {
    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static String TEXT = "С Новым Годом";

    @Test
    public void issueNameCheck(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com/" + REPOSITORY);
        $("[data-content='Issues']").click();
        $("#issue_76_link").shouldHave(text(TEXT));
    }

    @Test
    public void issueNameCheckWithLambdaSteps(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("открываем репозиторий " + REPOSITORY, () -> {
            open("https://github.com/" + REPOSITORY);
        });

        step("переходим в раздел Issues", () -> {
                    $("[data-content='Issues']").click();
                });

        step("проверяем, что ишью 76 имеет в названии " + TEXT, () -> {
            $("#issue_76_link").shouldHave(text(TEXT));
        });
    }

    @Test
    public void issueNameCheckWithAnnotationSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();
        steps.openRepository(REPOSITORY);
        steps.openIssuePage();
        steps.shouldIssueHaveName(TEXT);
    }
}
