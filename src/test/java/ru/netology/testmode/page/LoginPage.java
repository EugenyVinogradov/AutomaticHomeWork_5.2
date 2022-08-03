package ru.netology.testmode.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Value;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Value
public class LoginPage {
    private SelenideElement loginField = $("[name=login]");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement wrongLoginOrPassword = $x("//*[text()='Неверно указан логин или пароль']");
    private SelenideElement blockedUser = $x("//*[text()='Пользователь заблокирован']");

    public DashBoardPage login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
        return new DashBoardPage();
    }

//    public void loginWrongLogin(DataGenerator.RegistrationDto user, String wrongLogin) {
//        $("[name=login]").setValue(wrongLogin);
//        $("[name=password]").setValue(user.getPassword());
//        $("[data-test-id=action-login]").click();
//    }
//
//    public void loginWrongPassword(DataGenerator.RegistrationDto user, String wrongPassword) {
//        $("[name=login]").setValue(user.getLogin());
//        $("[name=password]").setValue(wrongPassword);
//        $("[data-test-id=action-login]").click();
//    }
}