package ru.netology.testmode.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class DashBoardPage {
    private SelenideElement heading = $x("//*[contains(text(),'Личный кабинет')]");

    public void isPageExist() {
        heading.shouldBe(Condition.visible);
    }
}
