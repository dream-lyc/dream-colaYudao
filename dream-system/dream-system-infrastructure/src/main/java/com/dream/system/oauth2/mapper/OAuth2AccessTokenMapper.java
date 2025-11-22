package com.dream.system.oauth2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dream.system.oauth2.DO.OAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapper<OAuth2AccessTokenDO> {

//    default OAuth2AccessTokenDO selectByAccessToken(String accessToken) {
//        return selectOne(OAuth2AccessTokenDO::getAccessToken, accessToken);
//    }
//
//    default List<OAuth2AccessTokenDO> selectListByRefreshToken(String refreshToken) {
//        return selectList(OAuth2AccessTokenDO::getRefreshToken, refreshToken);
//    }
//
//    default PageResult<OAuth2AccessTokenDO> selectPage(OAuth2AccessTokenPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<OAuth2AccessTokenDO>()
//                .eqIfPresent(OAuth2AccessTokenDO::getUserId, reqVO.getUserId())
//                .eqIfPresent(OAuth2AccessTokenDO::getUserType, reqVO.getUserType())
//                .likeIfPresent(OAuth2AccessTokenDO::getClientId, reqVO.getClientId())
//                .gt(OAuth2AccessTokenDO::getExpiresTime, LocalDateTime.now())
//                .orderByDesc(OAuth2AccessTokenDO::getId));
//    }

}
