
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardBalanceAddPage;
import ru.netology.data.DataHelper;
import ru.netology.data.LoginPage;
import ru.netology.data.PersonalAccountPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTransferTest {
    @Test
     void ShouldTransferMoneyBetweenOwnCardsFromSecondToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
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
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
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
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        val personalAccount = new PersonalAccountPage();
        val cardInfo = DataHelper.getCardInfo1();
        personalAccount.validDeposit2();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoneyError(cardInfo);

    }
    @Test
    void ShouldTransferMoneyNotIntegerBetweenOwnCardsFromFirstToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
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
