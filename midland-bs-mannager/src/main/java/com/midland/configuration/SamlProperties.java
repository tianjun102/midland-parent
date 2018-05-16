package com.midland.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SamlProperties {
    @Value("${siam_login_url}")
    private String samlLoginUrl;
    @Value("${x509_location}")
    private String x509Location;
    @Value("${client_response_url}")
    private String clientResponseUrl;
    @Value("${client_redirect_url}")
    private String clientRedirectUrl;

    public String getSamlLoginUrl() {
        return samlLoginUrl;
    }

    public void setSamlLoginUrl(String samlLoginUrl) {
        this.samlLoginUrl = samlLoginUrl;
    }

    public String getX509Location() {
        return x509Location;
    }

    public void setX509Location(String x509Location) {
        this.x509Location = x509Location;
    }

    public String getClientResponseUrl() {
        return clientResponseUrl;
    }

    public void setClientResponseUrl(String clientResponseUrl) {
        this.clientResponseUrl = clientResponseUrl;
    }

    public String getClientRedirectUrl() {
        return clientRedirectUrl;
    }

    public void setClientRedirectUrl(String clientRedirectUrl) {
        this.clientRedirectUrl = clientRedirectUrl;
    }
}
