package ru.netology.testmode.data;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public void login(DataGenerator.RegistrationDto user) {
        $("[name=login]").setValue(user.getLogin());
        $("[name=password]").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();
    }

    public void loginWrongLogin(DataGenerator.RegistrationDto user, String wrongLogin) {
        $("[name=login]").setValue(wrongLogin);
        $("[name=password]").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();
    }

    public void loginWrongPassword(DataGenerator.RegistrationDto user, String wrongPassword) {
        $("[name=login]").setValue(user.getLogin());
        $("[name=password]").setValue(wrongPassword);
        $("[data-test-id=action-login]").click();
    }
}