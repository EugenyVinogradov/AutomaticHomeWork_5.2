package ru.netology.testmode.data;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public static void login(DataGenerator.RegistrationDto user) {
        $("[name=login]").setValue(user.getLogin());
        $("[name=password]").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();
    }
}
