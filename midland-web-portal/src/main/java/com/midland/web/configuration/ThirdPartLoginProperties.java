package com.midland.web.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartLoginProperties {
    /**
     *
     */
    @Value("${token_url}")
    private String tokenUrl;
    /**
     *
     */
    @Value("${grant_type}")
    private String grantType;
    /**
     *
     */
    @Value("${client_id}")
    private String clientId;
    /**
     *
     */
    @Value("${client_secret}")
    private String clientSecret;
    /**
     *
     */
    @Value("${redirect_uri}")
    private String redirectUri;
    /**
     *
     */
    @Value("${open_id_url}")
    private String openIdUrl;

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getOpenIdUrl() {
        return openIdUrl;
    }

    public void setOpenIdUrl(String openIdUrl) {
        this.openIdUrl = openIdUrl;
    }
}
