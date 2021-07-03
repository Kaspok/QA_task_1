package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
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
        $(byText("Female")).click();
        $("#userNumber").setValue("5674387181");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("1");
        $(".react-datepicker__year-select").selectOptionByValue("1998");
        $(".react-datepicker__day--020").click();

        SelenideElement Subject = $("#subjectsInput");
        Subject.setValue("Math").pressEnter();
        Subject.setValue("Histor").pressEnter();

        $(byText("Reading")).click();
        $(byText("Music")).click();

        $("#uploadPicture").sendKeys("C:\\Users\\79051\\Desktop\\ava.jpg");
        $("#currentAddress").setValue("This is Current Address");

        $("#react-select-4-input").shouldBe(Condition.disabled); // Проверям, что поле City не доступно для редактирования (так как не заполнено поле State).
        $("#react-select-3-input").setValue("Uttar").pressEnter(); // Заполняем поле State.
        $("#react-select-4-input").shouldBe(Condition.enabled).setValue("Me").pressEnter(); // Проверяем что поле City стала доступна для редактирования, вводим данные.

        executeJavaScript("window.scrollTo(0, 200)"); // Прокручиваем страницу вниз
        $("#submit").click();

        // Проверка таблицы и веденных данных
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//thead/tr/th[1]").shouldHave(text("Label"));
        $x("//thead/tr/th[2]").shouldHave(text("Values"));

        $x("//td[text()='Student Name']/following::td").shouldHave(text("Lera Ivanova"));
        $x("//td[text()='Student Email']/following::td").shouldHave(text("Ivanova@example.com"));
        $x("//td[text()='Gender']/following::td").shouldHave(text("Female"));
        $x("//td[text()='Mobile']/following::td").shouldHave(text("5674387181"));
        $x("//td[text()='Date of Birth']/following::td").shouldHave(text("20 February,1998"));
        $x("//td[text()='Subjects']/following::td").shouldHave(text("Maths, History"));
        $x("//td[text()='Hobbies']/following::td").shouldHave(text("Reading, Music"));
        $x("//td[text()='Picture']/following::td").shouldHave(text("ava.jpg"));
        $x("//td[text()='Address']/following::td").shouldHave(text("This is Current Address"));
        $x("//td[text()='State and City']/following::td").shouldHave(text("Uttar Pradesh Merrut"));

        sleep(1000);

    }
}
