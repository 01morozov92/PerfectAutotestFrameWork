package ui.pages.factory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Кастомные локаторы на замену FindBy
 * Новый локатор добавляется сюда, ему необходимо присвоить пустую строку как стандартное значение
 * и поддержать его в CustomLocatorBuilder
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomLocator {

    String classStartsWith() default "";

    String text() default "";

    String withText() default "";

    String attributeName() default "";

    String attributeValue() default "";

    String notContainClass() default "";

    String tag() default "";

    String dataTarget() default "";
}
