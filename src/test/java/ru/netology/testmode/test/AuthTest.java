package ru.netology.testmode.test;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.testmode.data.DataGenerator;
import ru.netology.testmode.page.LoginPage;


import static com.codeborne.selenide.Selenide.*;
import static ru.netology.testmode.data.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.testmode.data.DataGenerator.Registration.getUser;
import static ru.netology.testmode.data.DataGenerator.getRandomLogin;
import static ru.netology.testmode.data.DataGenerator.getRandomPassword;

class AuthTest {

    LoginPage loginPage;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        loginPage = new LoginPage();
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = getRegisteredUser("active");
        DataGenerator.sendRequest(registeredUser);
        loginPage.login(registeredUser.getLogin(), registeredUser.getPassword()).isPageExist();
    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var notRegisteredUser = getUser("active");
        loginPage.login(notRegisteredUser.getLogin(), notRegisteredUser.getPassword());
        loginPage.wrongLoginOrPassword().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        var blockedUser = getRegisteredUser("blocked");
        DataGenerator.sendRequest(blockedUser);
        loginPage.login(blockedUser.getLogin(), blockedUser.getPassword());
        loginPage.blockedUser().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();
        DataGenerator.sendRequest(registeredUser);
        loginPage.login(wrongLogin, registeredUser.getPassword());
        loginPage.wrongLoginOrPassword().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();
        DataGenerator.sendRequest(registeredUser);
        loginPage.login(registeredUser.getLogin(), wrongPassword);
        loginPage.wrongLoginOrPassword().shouldBe(Condition.visible);
    }
}

// Время, затраченное на ручное тестирование (минут): 20
// Время, затраченное на автоматизацию (минут): 240 - большую часть разбирался с кодом, селекторами.
// Сами тесты писал минут 10 (с проверкой), благо, заготовки были))
