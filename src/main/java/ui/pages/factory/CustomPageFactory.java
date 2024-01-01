package ui.pages.factory;

import com.codeborne.selenide.Driver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.ElementFinder;
import com.codeborne.selenide.impl.LazyWebElementSnapshot;
import com.codeborne.selenide.impl.SelenidePageFactory;
import com.codeborne.selenide.impl.WebElementSource;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ui.selectors.CustomSelenideElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import static ui.pages.factory.CustomLocatorBuilder.getCustomLocator;

/**
 * Стандартная селенидовская PageFactory с двумя переопределенными методами для работы с кастомными локаторами
 */
public class CustomPageFactory extends SelenidePageFactory {

    /**
     * Возвращает By локатор для поля PageObject/ElementsContainer на основе аннотаций
     * Используем стандартный метод, если есть одна из селениумовских аннотаций,
     * ставим болванку, если попадаемся на поле self(это поле абстрактного ElementsContainer),
     * во всех остальных случаях полагаемся на кастомный локатор
     */
    @NotNull
    @Override
    protected By findSelector(@NotNull Driver driver, @NotNull Field field) {
        if (defaultLocatorDefined(field)) {
            return super.findSelector(driver, field);
        }
        if (field.getName().equals("self")) {
            return new ByIdOrName("self");
        }
        return getCustomLocator(field.getDeclaredAnnotation(CustomLocator.class));
    }

    /**
     * Практически полностью скопирован из стандартной реализации за исключением последнего условия
     * Метод проверяет, является ли поле листом ElementsContainer-ов
     */
    @Override
    protected boolean isDecoratableList(Field field, Type[] genericTypes, Class<?> type) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        }

        Class<?> listType = getListGenericType(field, genericTypes);

        return listType != null && type.isAssignableFrom(listType)
                && (field.getAnnotation(FindBy.class) != null
                || field.getAnnotation(FindBys.class) != null
                || field.getAnnotation(CustomLocator.class) != null);
    }

    @Nonnull
    @Override
    protected SelenideElement decorateWebElement(Driver driver, @Nullable WebElementSource searchContext, By selector,
                                                 Field field, @Nullable String alias) {
        return shouldCache(field) ?
                LazyWebElementSnapshot.wrap(new ElementFinder(driver, searchContext, selector, 0, alias)) :
                ElementFinder.wrap(driver, CustomSelenideElement.class, searchContext, selector, 0, alias);
    }

    private boolean defaultLocatorDefined(Field field) {
        return field.isAnnotationPresent(FindBy.class)
                || field.isAnnotationPresent(FindAll.class)
                || field.isAnnotationPresent(FindBys.class);
    }
}
