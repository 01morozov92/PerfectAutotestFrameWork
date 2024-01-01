package ui.pages.factory;

import com.codeborne.selenide.Container;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public abstract class ElementsContainer implements Container {

    @Self
    private SelenideElement self;
}
