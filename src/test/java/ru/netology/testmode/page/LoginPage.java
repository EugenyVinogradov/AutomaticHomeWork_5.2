package ru.netology.testmode.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
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

    public SelenideElement wrongLoginOrPassword() {
        SelenideElement wrongLogOrPass = wrongLoginOrPassword;
        return wrongLogOrPass;
    }

    public SelenideElement blockedUser() {
        SelenideElement blockUser = blockedUser;
        return blockUser;
    }
}