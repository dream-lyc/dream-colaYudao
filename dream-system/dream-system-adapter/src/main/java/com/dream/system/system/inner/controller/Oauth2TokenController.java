package com.dream.system.system.inner.controller;


import com.dream.framework.common.biz.system.oauth2.OAuth2TokenCommonApi;
import com.dream.framework.common.biz.system.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.dream.system.oauth2.service.OAuth2TokenService;
import com.system.oauth2.model.OAuth2AccessToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * todo 改成 feign 调用
 */
@Component
public class Oauth2TokenController implements OAuth2TokenCommonApi {

    @Autowired
    private OAuth2TokenService OAuth2TokenService;


    @Override
    public OAuth2AccessTokenCheckRespDTO checkAccessToken(String accessToken){
        OAuth2AccessTokenCheckRespDTO  respDTO = new OAuth2AccessTokenCheckRespDTO();
        OAuth2AccessToken oAuth2AccessToken = OAuth2TokenService.checkAccessToken(accessToken);
        BeanUtils.copyProperties(oAuth2AccessToken,respDTO);
        return respDTO;
    };

}
