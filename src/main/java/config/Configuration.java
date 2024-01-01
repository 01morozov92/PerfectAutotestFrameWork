package config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Configuration {

    private static final MainConfig MAIN_CONFIG = ConfigFactory.create(MainConfig.class);

    public static MainConfig getMainConfig() {
        return MAIN_CONFIG;
    }
}
