package com.system.oauth2.gateway;

import com.system.oauth2.model.OAuth2AccessToken;
import com.system.oauth2.model.OAuth2RefreshToken;

public interface Oauth2Gateway {

    void insert(OAuth2RefreshToken oAuth2RefreshToken);

    void insert(OAuth2AccessToken oAuth2AccessToken);
}
