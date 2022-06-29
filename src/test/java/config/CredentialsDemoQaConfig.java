package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentialsDemoQa.properties")
public interface CredentialsDemoQaConfig extends Config {
    String login();
    String password();
}
