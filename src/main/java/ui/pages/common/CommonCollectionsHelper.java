package ui.pages.common;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.impl.CollectionSource;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonCollectionsHelper {

    @Step("Проверить количество элементов в коллекции {collectionName}")
    public static ElementsCollection checkCollectionSize(ElementsCollection collection, String collectionName, int size) {
        return collection.as(collectionName).shouldHave(CollectionCondition.size(size)
                .because("Количество элементов не равно %d".formatted(size)));
    }

    public static ElementsCollection checkCollectionSize(ElementsCollection collection, int size) {
        return checkCollectionSize(collection, getCollectionAliasName(collection), size);
    }

    @Step("Проверить присутствие элементов коллекции {collectionName}")
    public static ElementsCollection checkCollectionIsNotEmpty(ElementsCollection collection, String collectionName) {
        return collection.as(collectionName).shouldHave(CollectionCondition.sizeNotEqual(0)
                .because("%s должны отображаться".formatted(collectionName)));
    }

    public static ElementsCollection checkCollectionIsNotEmpty(ElementsCollection collection) {
        return checkCollectionIsNotEmpty(collection, getCollectionAliasName(collection));
    }

    @Step("Проверить отсутствие элементов коллекции {collectionName}")
    public static ElementsCollection checkCollectionIsEmpty(ElementsCollection collection, String collectionName) {
        return collection.as(collectionName).shouldBe(CollectionCondition.empty
                .because("%s должны отсутствовать".formatted(collectionName)));
    }

    public static ElementsCollection checkCollectionIsEmpty(ElementsCollection collection) {
        return checkCollectionIsEmpty(collection, getCollectionAliasName(collection));
    }

    @SneakyThrows
    private static String getCollectionAliasName(ElementsCollection elementsCollection) {
        var field = ElementsCollection.class.getDeclaredField("collection");
        field.setAccessible(true);
        var alias = ((CollectionSource) field.get(elementsCollection)).getAlias();

        return Optional.ofNullable(alias.getText())
                .orElseThrow(() -> new RuntimeException("""
                        Нельзя использовать метод без указания имени коллекции элементов
                        Варианты решения:
                        a) Проставить аннотацию @As("Имя коллекции") у ElementsCollection
                        б) Добавить alias для коллекции -> elementsCollection.as("Имя элемента")
                        в) Использовать метод в который передаётся elementName для ElementsCollection
                        """));
    }
}
