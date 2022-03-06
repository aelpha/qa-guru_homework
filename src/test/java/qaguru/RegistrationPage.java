package qaguru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    //locators *все не заменяла, т.к. они по одному разу используются, особого смысла нет
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement genderMale = $("[for='gender-radio-1']");
    private final SelenideElement mobileNumberInput = $("#userNumber");
    private final SelenideElement subjectInput = $("#subjectsInput");
    private final SelenideElement currentAddress = $("#currentAddress");
    private final SelenideElement datepickerInput = $("#dateOfBirthInput");

    //functions
    public void openPage() {
        open("/automation-practice-form");
        $("h5").shouldHave(Condition.text("Student Registration Form"));
    }

    public void closeModal() {
        $("#closeLargeModal").click();
        $(".modal-header").shouldNotBe(Condition.visible);
    }

    public void checkModal(UserData user) {
        $(".modal-header").shouldHave(Condition.text("Thanks for submitting the form"));
        $("tbody").shouldHave(Condition.text(user.firstName), Condition.text(user.lastName), Condition.text(user.email),
                Condition.text("Male"), Condition.text(user.number), Condition.text("17 April,1925"),
                Condition.text("Computer Science"), Condition.text("Sports"), Condition.text("picture"),
                Condition.text(user.address), Condition.text("NCR Delhi"));
    }

    public void fillForm(UserData user,
                                String dayIndex, int monthIndex, int yearIndex) {
        firstNameInput.setValue(user.firstName);
        lastNameInput.setValue(user.lastName);
        emailInput.setValue(user.email);
        genderMale.click();
        mobileNumberInput.setValue(user.number);
        datepickerInput.click();
        selectData(monthIndex, yearIndex, dayIndex);
        $("#subjects-label").click();
        subjectInput.setValue("Computer Science").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/picture.jpg"));
        currentAddress.setValue(user.address);
        $("#state").click();
        $("#state div:nth-child(3) div div").click();
        $("#city").click();
        $("#city div:nth-child(3) div div").click();
    }

    public void clickSubmit() {
        $("#submit").click();
    }

    public void selectData(int monthIndex, int yearIndex, String dayIndex) {
        $(".react-datepicker__month-select").selectOption(monthIndex);
        $(".react-datepicker__year-select").selectOption(yearIndex);
        $("[class*='react-datepicker__day--0"+dayIndex+"']").click();
    }
}
