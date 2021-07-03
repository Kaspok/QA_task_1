package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static  com.codeborne.selenide.Selenide. * ;


public class StudentRegistrationForm {

    @BeforeAll
    static void setup(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveTest() {
        open("/automation-practice-form");

        // Ввод данных в форму
        $("#firstName").setValue("Lera");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("Ivanova@example.com");
        $(new Selectors.ByText("Female")).click();
        $("#userNumber").setValue("5674387181");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("1");
        $(".react-datepicker__year-select").selectOptionByValue("1998");
        $(".react-datepicker__day--020").click();
        SelenideElement Subject = $("#subjectsInput");
        Subject.setValue("Math").pressEnter();
        Subject.setValue("Histor").pressEnter();
        $(new Selectors.ByText("Reading")).click();
        $(new Selectors.ByText("Music")).click();
        $("#uploadPicture").sendKeys("C:\\Users\\79051\\Desktop\\ava.jpg");
        $("#currentAddress").setValue("This is Current Address");
        $("#react-select-4-input").shouldBe(Condition.disabled); // Проверям, что поле City не доступно для редактирования (так как не заполнено поле State).
        $("#react-select-3-input").setValue("Uttar").pressEnter(); // Заполняем поле State.
        $("#react-select-4-input").shouldBe(Condition.enabled).setValue("Me").pressEnter(); // Проверяем что поле City стала доступна для редактирования, вводим данные.
        executeJavaScript("window.scrollTo(0, 200)");
        $("#submit").click();

        // Проверка таблицы и веденных данных
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//thead/tr/th[1]").shouldHave(text("Label"));
        $x("//thead/tr/th[2]").shouldHave(text("Values"));

        $x("//tbody/tr[1]/td[1]").shouldHave(text("Student Name"));
        $x("//tbody/tr[1]/td[2]").shouldHave(text("Lera Ivanova"));

        $x("//tbody/tr[2]/td[1]").shouldHave(text("Student Email"));
        $x("//tbody/tr[2]/td[2]").shouldHave(text("Ivanova@example.com"));

        $x("//tbody/tr[3]/td[1]").shouldHave(text("Gender"));
        $x("//tbody/tr[3]/td[2]").shouldHave(text("Female"));

        $x("//tbody/tr[4]/td[1]").shouldHave(text("Mobile"));
        $x("//tbody/tr[4]/td[2]").shouldHave(text("5674387181"));

        $x("//tbody/tr[5]/td[1]").shouldHave(text("Date of Birth"));
        $x("//tbody/tr[5]/td[2]").shouldHave(text("20 February,1998"));

        $x("//tbody/tr[6]/td[1]").shouldHave(text("Subjects"));
        $x("//tbody/tr[6]/td[2]").shouldHave(text("Maths, History"));

        $x("//tbody/tr[7]/td[1]").shouldHave(text("Hobbies"));
        $x("//tbody/tr[7]/td[2]").shouldHave(text("Reading, Music"));

        $x("//tbody/tr[8]/td[1]").shouldHave(text("Picture"));
        $x("//tbody/tr[8]/td[2]").shouldHave(text("ava.jpg"));

        $x("//tbody/tr[9]/td[1]").shouldHave(text("Address"));
        $x("//tbody/tr[9]/td[2]").shouldHave(text("This is Current Address"));

        $x("//tbody/tr[10]/td[1]").shouldHave(text("State and City"));
        $x("//tbody/tr[10]/td[2]").shouldHave(text("Uttar Pradesh Merrut"));

        sleep(1000);

    }
}
