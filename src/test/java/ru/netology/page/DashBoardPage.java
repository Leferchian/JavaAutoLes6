package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection depositCard1 = $$("[data-test-id=action-deposit]").first(1);
    private final ElementsCollection depositCard2 = $$("[data-test-id=action-deposit]").last(1);
    private final ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashBoardPage() {
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance(int cardIndex) {
        val text = cards.get(cardIndex).text();
        return extractBalance(text);
    }
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void card1Balance() {
        depositCard1.get(0).click();
    }

    public void card2Balance() {
        depositCard2.get(0).click();
    }
}