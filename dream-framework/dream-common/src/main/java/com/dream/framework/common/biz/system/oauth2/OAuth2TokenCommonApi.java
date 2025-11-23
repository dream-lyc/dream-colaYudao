package com.dream.framework.common.biz.system.oauth2;

import com.dream.framework.common.biz.system.oauth2.dto.OAuth2AccessTokenCheckRespDTO;

public interface OAuth2TokenCommonApi {
    OAuth2AccessTokenCheckRespDTO checkAccessToken(String accessToken);
}
