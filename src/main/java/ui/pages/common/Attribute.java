package ui.pages.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Attribute {
    ID("id"),
    SRC("src"),
    HREF("href"),
    NAME("name"),
    STYLE("style"),
    VALUE("value"),
    TYPE("type"),
    CLASS("class"),
    SRCSET("srcset"),
    TARGET("target"),
    CONTENT("content"),
    ARIA_LABEL("aria-label"),
    ARIA_SELECTED("aria-selected"),
    ;

    private final String value;
}
