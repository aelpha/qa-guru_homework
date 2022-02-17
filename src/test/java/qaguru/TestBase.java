package qaguru;

import static com.codeborne.selenide.Selenide.$;

public class TestBase {
    static void clickSubmit() {
        $("#submit").click();
    }

    static void selectData(int monthIndex, int yearIndex, String dayIndex) {
        $(".react-datepicker__month-select").selectOption(monthIndex);
        $(".react-datepicker__year-select").selectOption(yearIndex);
        $("[class*='react-datepicker__day--0"+dayIndex+"']").click();
    }
}
