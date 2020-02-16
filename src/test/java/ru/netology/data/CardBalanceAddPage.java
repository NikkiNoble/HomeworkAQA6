package ru.netology.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import ru.netology.data.PersonalAccountPage;

import static com.codeborne.selenide.Selenide.$;

public class CardBalanceAddPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromCardField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private SelenideElement errorNotofication = $("[data-test-id=error-notification]");
    public static String amountTransferred = DataHelper.shouldReturnARandomAmount();


    public PersonalAccountPage transferMoney(DataHelper.CardInfo info) {
        amountField.setValue(amountTransferred);
        fromCardField.setValue(info.getNumber());
        transferButton.click();
        return new PersonalAccountPage();
    }
    public CardBalanceAddPage() {
        amountField.shouldBe(Condition.visible);
    }
    public void transferMoneyError(DataHelper.CardInfo info) {
        amountField.setValue(amountTransferred);
        transferButton.click();
        errorNotofication.shouldBe(Condition.visible);
    }
    public PersonalAccountPage transferMoneyDoubleAmount(DataHelper.CardInfo info) {
        amountField.setValue(DataHelper.shouldReturnDoubleAmount());
        fromCardField.setValue(info.getNumber());
        transferButton.click();
        return new PersonalAccountPage();
    }
}
