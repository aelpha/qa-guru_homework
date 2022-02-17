package qaguru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage extends TestBase {

    //locators *все не заменяла, т.к. они по одному разу используются, особого смысла нет
    private static final SelenideElement firstNameInput = $("#firstName");
    private static final SelenideElement lastNameInput = $("#lastName");
    private static final SelenideElement emailInput = $("#userEmail");
    private static final SelenideElement genderMale = $("[for='gender-radio-1']");
    private static final SelenideElement mobileNumberInput = $("#userNumber");
    private static final SelenideElement subjectInput = $("#subjectsInput");
    private static final SelenideElement currentAddress = $("#currentAddress");
    private static final SelenideElement datepickerInput = $("#dateOfBirthInput");

    //functions
    public static void openPage() {
        open("/automation-practice-form");
        $("h5").shouldHave(Condition.text("Student Registration Form"));
    }

    public static void closeModal() {
        $("#closeLargeModal").click();
        $(".modal-header").shouldNotBe(Condition.visible);
    }

    public static void checkModal(String firstname, String lastName, String email, String userNumber, String address) {
        $(".modal-header").shouldHave(Condition.text("Thanks for submitting the form"));
        $("tbody").shouldHave(Condition.text(firstname), Condition.text(lastName), Condition.text(email),
                Condition.text("Male"), Condition.text(userNumber), Condition.text("17 April,1925"),
                Condition.text("Computer Science"), Condition.text("Sports"), Condition.text("picture"),
                Condition.text(address), Condition.text("NCR Delhi"));
    }

    public static void fillForm(String firstname, String lastName, String email, String userNumber, String address,
                                String dayIndex, int monthIndex, int yearIndex) {
        firstNameInput.setValue(firstname);
        lastNameInput.setValue(lastName);
        emailInput.setValue(email);
        genderMale.click();
        mobileNumberInput.setValue(userNumber);
        datepickerInput.click();
        selectData(monthIndex, yearIndex, dayIndex);
        $("#subjects-label").click();
        subjectInput.setValue("Computer Science").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/picture.jpg"));
        currentAddress.setValue(address);
        $("#state").click();
        $("#state div:nth-child(3) div div").click();
        $("#city").click();
        $("#city div:nth-child(3) div div").click();
        clickSubmit();
    }

}
