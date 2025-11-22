package com.dream.system.oauth2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dream.system.oauth2.DO.OAuth2RefreshTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {

//    default int deleteByRefreshToken(String refreshToken) {
//        return delete(new LambdaQueryWrapperX<OAuth2RefreshTokenDO>()
//                .eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken));
//    }

//    default OAuth2RefreshTokenDO selectByRefreshToken(String refreshToken) {
//        return selectOne(OAuth2RefreshTokenDO::getRefreshToken, refreshToken);
//    }

}
