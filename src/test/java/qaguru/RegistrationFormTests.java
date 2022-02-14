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
    public void fillFormTest() {
        String firstName = RandomStringUtils.randomAlphabetic(5);
        String lastName = RandomStringUtils.randomAlphabetic(5);
        String email = RandomStringUtils.randomAlphabetic(5) + "@rrr.com";
        String userNumber = RandomStringUtils.randomNumeric(10);
        String address = RandomStringUtils.randomAlphabetic(20);

        openPage();
        fillForm(firstName, lastName, email, userNumber, address);
        checkModal(firstName, lastName, email, userNumber, address);
        closeModal();
    }

    private void closeModal() {
        $("#closeLargeModal").click();
        $(".modal-header").shouldNotBe(Condition.visible);
    }

    private void checkModal(String firstname, String lastName, String email, String userNumber, String address) {
        $(".modal-header").shouldHave(Condition.text("Thanks for submitting the form"));
        $("tbody").shouldHave(Condition.text(firstname), Condition.text(lastName), Condition.text(email),
                Condition.text("Male"), Condition.text(userNumber), Condition.text("2010"),
                Condition.text("Computer Science"), Condition.text("Sports"), Condition.text("picture"),
                Condition.text(address), Condition.text("NCR Delhi"));
    }

    private void openPage() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
        $("h5").shouldHave(Condition.text("Student Registration Form"));
    }

    private void fillForm(String firstname, String lastName, String email, String userNumber, String address) {
        $("#firstName").setValue(firstname);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $("#dateOfBirthInput").sendKeys(Keys.BACK_SPACE);
        $("#dateOfBirthInput").sendKeys(Keys.BACK_SPACE);
        $("#dateOfBirthInput").setValue("10");
        $("#subjects-label").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/picture.jpg"));
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#state div:nth-child(3) div div").click();
        $("#city").click();
        $("#city div:nth-child(3) div div").click();
        $("#submit").click();
    }
}
