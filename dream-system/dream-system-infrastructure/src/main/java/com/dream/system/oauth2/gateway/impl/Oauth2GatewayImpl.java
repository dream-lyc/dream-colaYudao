package com.dream.system.oauth2.gateway.impl;

import com.dream.system.oauth2.DO.OAuth2AccessTokenDO;
import com.dream.system.oauth2.DO.OAuth2RefreshTokenDO;
import com.dream.system.oauth2.convert.Oauth2Convert;
import com.system.oauth2.gateway.Oauth2Gateway;
import com.dream.system.oauth2.mapper.OAuth2AccessTokenMapper;
import com.dream.system.oauth2.mapper.OAuth2RefreshTokenMapper;
import com.system.oauth2.model.OAuth2AccessToken;
import com.system.oauth2.model.OAuth2RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Oauth2GatewayImpl implements Oauth2Gateway {

    @Autowired
    private OAuth2RefreshTokenMapper oAuth2RefreshTokenMapper;
    @Autowired
    private OAuth2AccessTokenMapper oAuth2AccessTokenMapper;

    @Autowired
    private Oauth2Convert oauth2Convert;

    @Override
    public void insert(OAuth2RefreshToken oAuth2RefreshToken) {
        OAuth2RefreshTokenDO oAuth2RefreshTokenDO = oauth2Convert.convert2DO(oAuth2RefreshToken);
        oAuth2RefreshTokenMapper.insert(oAuth2RefreshTokenDO);
    }

    @Override
    public void insert(OAuth2AccessToken oAuth2AccessToken) {
        OAuth2AccessTokenDO oAuth2AccessTokenDO = oauth2Convert.convert2DO(oAuth2AccessToken);
        oAuth2AccessTokenMapper.insert(oAuth2AccessTokenDO);
    }
}
