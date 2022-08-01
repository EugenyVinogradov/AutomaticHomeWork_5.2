package ru.netology.testmode.test;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.netology.testmode.data.DataGenerator;
import ru.netology.testmode.data.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.testmode.data.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.testmode.data.DataGenerator.Registration.getUser;
import static ru.netology.testmode.data.DataGenerator.getRandomLogin;
import static ru.netology.testmode.data.DataGenerator.getRandomPassword;

class AuthTest {

    LoginPage loginPage = new LoginPage();

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = getRegisteredUser("active");
        DataGenerator.sendRequest(registeredUser);
        loginPage.login(registeredUser);
        $x("//*[contains(text(),'Личный кабинет')]").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var notRegisteredUser = getUser("active");
        loginPage.login(notRegisteredUser);
        $x("//*[text()='Неверно указан логин или пароль']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        var blockedUser = getRegisteredUser("blocked");
        DataGenerator.sendRequest(blockedUser);
        loginPage.login(blockedUser);
        $x("//*[text()='Пользователь заблокирован']").shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();
        DataGenerator.sendRequest(registeredUser);
        loginPage.loginWrongLogin(registeredUser, wrongLogin);
        $x("//*[text()='Неверно указан логин или пароль']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();
        DataGenerator.sendRequest(registeredUser);
        loginPage.loginWrongLogin(registeredUser, wrongPassword);
        $x("//*[text()='Неверно указан логин или пароль']").shouldBe(Condition.visible);
    }
}

// Время, затраченное на ручное тестирование (минут): 20
// Время, затраченное на автоматизацию (минут): 240 - большую часть разбирался с кодом, селекторами.
// Сами тесты писал минут 10 (с проверкой), благо, заготовки были))
