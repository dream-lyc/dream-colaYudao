package com.dream.system.oauth2.convert;

import com.dream.system.oauth2.DO.OAuth2AccessTokenDO;
import com.dream.system.oauth2.DO.OAuth2RefreshTokenDO;
import com.system.oauth2.model.OAuth2AccessToken;
import com.system.oauth2.model.OAuth2RefreshToken;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface Oauth2Convert {

    OAuth2RefreshTokenDO convert2DO(OAuth2RefreshToken refreshToken);

    OAuth2RefreshToken convert2Entity(OAuth2RefreshTokenDO refreshTokenDO);

    OAuth2AccessTokenDO convert2DO(OAuth2AccessToken accessToken);

    OAuth2AccessToken convert2Entity(OAuth2AccessTokenDO accessTokenDO);


}
