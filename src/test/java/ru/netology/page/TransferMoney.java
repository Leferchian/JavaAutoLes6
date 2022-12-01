package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferMoney {
    private final SelenideElement amount = $("[data-test-id=amount]");
    private final SelenideElement amountTransfer = $$(".input__control").get(0);
    private final SelenideElement from = $$(".input__control").get(1);
    private final SelenideElement actionTransfer = $("[data-test-id=action-transfer]");


    public TransferMoney() {
        amount.shouldBe(Condition.visible);
    }

    public void cardBalance(int amount, int cadr) {
        amountTransfer.sendKeys(String.valueOf(amount)); //Ввод суммы
        from.sendKeys(String.valueOf(DataHelper.getCardNumber(cadr))); //Ввод номера карты
        actionTransfer.click();
    }
}
