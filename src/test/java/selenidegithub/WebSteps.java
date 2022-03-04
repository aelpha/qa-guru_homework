package selenidegithub;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("открываем репозиторий {repository}")
    public void openRepository (String repository) {
        open("https://github.com/" + repository);
    }

    @Step("переходим в раздел Issues")
    public void openIssuePage () {
        $("[data-content='Issues']").click();
    }

    @Step("проверяем, что ишью 76 имеет в названии {text}")
    public void shouldIssueHaveName (String text) {
        $("#issue_76_link").shouldHave(text(text));
    }
}
