package com.dream.system.login.convert;

import com.dream.system.login.DTO.LoginDTO;
import com.system.oauth2.model.OAuth2AccessToken;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface LoginConvert {

    LoginDTO convert2LoginDTO(OAuth2AccessToken accessToken);


}
