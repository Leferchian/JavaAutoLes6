package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferMoney;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMoneyTransfer {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
//    var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
        //Переводим с 0002 на 0001
    void transferMoneyToCard1FromCard2() {
        int amount = 8000;

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashBoardPage();
        // Запомнить текущий баланс
        int[] currentBalance = {
                dashboardPage.getCardBalance(0),
                dashboardPage.getCardBalance(1)
        };
        dashboardPage.card1Balance();

        var transferMoney = new TransferMoney();
        transferMoney.cardBalance(amount,1);

        int expected1 = currentBalance[0] + amount;
        int expected2 = currentBalance[1] - amount;

        int actual1 = dashboardPage.getCardBalance(0);
        int actual2 = dashboardPage.getCardBalance(1);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test

        //Переводим с 0001 на 0002
    void transferMoneyToCard2FromCard1() {
        int amount = 3000;

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashBoardPage();
        // Запомнить текущий баланс
        int[] currentBalance = {
                dashboardPage.getCardBalance(0),
                dashboardPage.getCardBalance(1)
        };

        dashboardPage.card2Balance();

        var transferMoney = new TransferMoney();
        transferMoney.cardBalance(amount,0);

        int expected1 = currentBalance[0] - amount;
        int expected2 = currentBalance[1] + amount;

        int actual1 = dashboardPage.getCardBalance(0);
        int actual2 = dashboardPage.getCardBalance(1);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

}
