package ru.netology.testmode.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Value;

import static com.codeborne.selenide.Selenide.$x;

@Value
public class DashBoardPage {
    private SelenideElement heading = $x("//*[contains(text(),'Личный кабинет')]");
}
