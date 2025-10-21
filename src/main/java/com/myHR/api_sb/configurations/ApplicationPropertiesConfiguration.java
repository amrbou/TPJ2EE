package com.myHR.api_sb.configurations;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {
    private int limitDeProduits;
    public int getLimitDEmploye() {
        return limitDeProduits;
    }
    public void setLimitDEmploye(int limitDeProduits) {
        this.limitDeProduits = limitDeProduits;
    }
}
