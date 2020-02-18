
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTransferTest {
    @Test
     void ShouldTransferMoneyBetweenOwnCardsFromSecondToFirst() {
        TestHelper.openPage();
        TestHelper.sendLogin();
        val personalAccount = new PersonalAccountPage();
        val cardInfo = DataHelper.getCardInfo2();
        int firstBalance = DataHelper.getBalance();
        personalAccount.validDeposit1();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoney(cardInfo);
        int newBalance = personalAccount.returnBalance();
        int expected = DataHelper.expectAmount(firstBalance, Integer.parseInt(CardBalanceAddPage.amountTransferred));
        assertEquals(expected, newBalance);
    }
    @Test
    void ShouldTransferMoneyBetweenOwnCardsFromFirstToSecond() {
        TestHelper.openPage();
        TestHelper.sendLogin();
        val personalAccount = new PersonalAccountPage();
        val cardInfo = DataHelper.getCardInfo1();
        int firstBalance = DataHelper.getOtherBalance();
        personalAccount.validDeposit2();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoney(cardInfo);
        int newBalance = personalAccount.returnOtherBalance();
        int expected = DataHelper.expectAmount(firstBalance, Integer.parseInt(CardBalanceAddPage.amountTransferred));
        assertEquals(expected, newBalance);
    }
    @Test
    void ShouldShowErrorWhenFieldIsEmpty() {
        TestHelper.openPage();
        TestHelper.sendLogin();
        val personalAccount = new PersonalAccountPage();
        val cardInfo = DataHelper.getCardInfo1();
        personalAccount.validDeposit2();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoneyError(cardInfo);

    }
    @Test
    void ShouldTransferMoneyNotIntegerBetweenOwnCardsFromFirstToSecond() {
        TestHelper.openPage();
        TestHelper.sendLogin();
        val personalAccount = new PersonalAccountPage();
        val cardInfo = DataHelper.getCardInfo1();
        int firstBalance = DataHelper.getOtherBalance();
        personalAccount.validDeposit2();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoneyDoubleAmount(cardInfo);
        int newBalance = personalAccount.returnOtherBalance();
        int expected = (int) (firstBalance - Double.parseDouble(DataHelper.shouldReturnDoubleAmount()));
        assertEquals(expected, newBalance);
    }







}
