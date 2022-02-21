package selenidegithub;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void replaceRectanglesTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $(".column").shouldHave(Condition.exactText("A"));
        $("#column-a").dragAndDropTo($("#column-b"));
        $(".column").shouldHave(Condition.exactText("B"));
    }

}
