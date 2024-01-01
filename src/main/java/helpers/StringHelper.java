package helpers;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class StringHelper {

    public static String normalizeText(String text) {
        return Optional.ofNullable(text)
                .map(s -> StringUtils.normalizeSpace(s.replaceAll("\u00a0", " ").replaceAll("&nbsp;", " ")))
                .orElse(text);
    }
}
