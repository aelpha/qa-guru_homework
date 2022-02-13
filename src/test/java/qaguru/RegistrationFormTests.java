package qaguru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import net.jodah.failsafe.internal.util.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegistrationFormTests {

    @Test
    public void fillFormTest(){
        openPage();
        fillForm();
        checkModal();
    }

    private void checkModal() {
        $(".modal-header").shouldHave(Condition.text("Thanks for submitting the form"));
    }

    private void openPage() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
        $("h5").shouldHave(Condition.text("Student Registration Form"));
    }

    private void fillForm() {
        $("#firstName").setValue(RandomStringUtils.randomAlphabetic(5));
        $("#lastName").setValue(RandomStringUtils.randomAlphabetic(5));
        $("#userEmail").setValue(RandomStringUtils.randomAlphabetic(5) + "@rrr.com");
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue(RandomStringUtils.randomNumeric(10));
        $("#dateOfBirthInput").click();
        $("#dateOfBirthInput").sendKeys(Keys.BACK_SPACE);
        $("#dateOfBirthInput").sendKeys(Keys.BACK_SPACE);
        $("#dateOfBirthInput").setValue("10");
        $("#subjects-label").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/picture.jpg"));
        $("#currentAddress").setValue(RandomStringUtils.randomAlphabetic(20));
        $("#state").click();
        $("#state div:nth-child(3) div div").click();
        $("#city").click();
        $("#city div:nth-child(3) div div").click();
        $("#submit").click();
    }
}
