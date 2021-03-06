package ru.netology.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PersonalAccountPage {
    private SelenideElement depositButton1 = $("[data-test-id=action-deposit]");
    private SelenideElement balance = $(withText("**** **** **** 0002"));
    private SelenideElement otherBalance = $(withText("**** **** **** 0001"));

    public CardBalanceAddPage validDeposit1() {
        depositButton1.click();
        return new CardBalanceAddPage();
    }
    public DashboardPage validDeposit2 () {
        $$("[data-test-id=action-deposit]").last().click();
        return new DashboardPage();
    }
    public PersonalAccountPage() {
        depositButton1.shouldBe(Condition.visible);
    }

    public int returnBalance() {
        String text = balance.text();
        String[] textArray = text.split(" ");
        return Integer.parseInt(textArray[5]);
    }

    public int returnOtherBalance() {
        String text = otherBalance.text();
        String[] textArray = text.split(" ");
        return Integer.parseInt(textArray[5]);
    }

}
