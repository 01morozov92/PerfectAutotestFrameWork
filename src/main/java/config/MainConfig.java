package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties", "classpath:environments/${env}/config.properties"})
public interface MainConfig extends Config {

    @Config.Key("base.api.uri")
    String getBaseApiUri();

    @Config.Key("base.ui.url")
    String getBaseUiUrl();

    @Config.Key("user.login")
    String getUserLogin();

    @Config.Key("user.password")
    String getUserPassword();

    @Config.Key("user.token")
    String getUserToken();
}
