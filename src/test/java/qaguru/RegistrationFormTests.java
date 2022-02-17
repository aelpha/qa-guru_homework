package qaguru;

import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegistrationFormTests {

    @BeforeAll
    public static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    public void fillFormTest() {
        String firstName = RandomStringUtils.randomAlphabetic(5);
        String lastName = RandomStringUtils.randomAlphabetic(5);
        String email = RandomStringUtils.randomAlphabetic(5) + "@rrr.com";
        String userNumber = RandomStringUtils.randomNumeric(10);
        String address = RandomStringUtils.randomAlphabetic(20);
        String dayIndex = "17";
        int monthIndex = 3;
        int yearIndex = 25;

        RegistrationPage.openPage();
        RegistrationPage.fillForm(firstName, lastName, email, userNumber, address, dayIndex, monthIndex, yearIndex);
        RegistrationPage.checkModal(firstName, lastName, email, userNumber, address);
        RegistrationPage.closeModal();
    }

}
