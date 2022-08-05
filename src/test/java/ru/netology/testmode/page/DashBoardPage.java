package ru.netology.testmode.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class DashBoardPage {
    private SelenideElement heading = $x("//*[contains(text(),'Личный кабинет')]");

    public boolean isPageExist() {
        boolean exist = heading.isDisplayed();
        return exist;
    }
}
