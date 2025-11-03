package com.dream.login.convert;

import com.dream.login.DTO.LoginDTO;
import com.dream.oauth2.DO.OAuth2AccessTokenDO;
import com.dream.oauth2.DO.OAuth2RefreshTokenDO;
import com.dream.oauth2.model.OAuth2AccessToken;
import com.dream.oauth2.model.OAuth2RefreshToken;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface LoginConvert {

    LoginDTO convert2LoginDTO(OAuth2AccessToken accessToken);


}
