package com.dream.system.oauth2.service;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.dream.system.oauth2.DO.OAuth2AccessTokenDO;
import com.system.oauth2.gateway.Oauth2Gateway;
import com.system.oauth2.model.OAuth2AccessToken;
import com.system.oauth2.model.OAuth2Client;
import com.system.oauth2.model.OAuth2RefreshToken;
import com.system.user.gateway.UserGateWay;
import com.system.user.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * OAuth2.0 Token Service 实现类
 *
 * @author 芋道源码
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {

    @Autowired
    private Oauth2Gateway oauth2Gateway;
    @Autowired
    private UserGateWay userGateWay;

//    @Resource
//    private OAuth2AccessTokenRedisDAO oauth2AccessTokenRedisDAO;

//    @Resource
//    private OAuth2ClientService oauth2ClientService;
//    @Resource
//    @Lazy // 懒加载，避免循环依赖
//    private AdminUserService adminUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OAuth2AccessToken createAccessToken(Long userId, Integer userType, String clientId, List<String> scopes) {
//        OAuth2Client clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        OAuth2Client client = OAuth2Client.getDefaultClient();
        // 创建刷新令牌
        OAuth2RefreshToken refreshTokenDO = createOAuth2RefreshToken(userId, userType, client, scopes);
        // 创建访问令牌
        return createOAuth2AccessToken(refreshTokenDO, client);
    }

    //
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public OAuth2AccessTokenDO refreshAccessToken(String refreshToken, String clientId) {
//        // 查询访问令牌
//        OAuth2RefreshTokenDO refreshTokenDO = oauth2RefreshTokenMapper.selectByRefreshToken(refreshToken);
//        if (refreshTokenDO == null) {
//            throw exception0(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "无效的刷新令牌");
//        }
//
//        // 校验 Client 匹配
//        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
//        if (ObjectUtil.notEqual(clientId, refreshTokenDO.getClientId())) {
//            throw exception0(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "刷新令牌的客户端编号不正确");
//        }
//
//        // 移除相关的访问令牌
//        List<OAuth2AccessTokenDO> accessTokenDOs = oauth2AccessTokenMapper.selectListByRefreshToken(refreshToken);
//        if (CollUtil.isNotEmpty(accessTokenDOs)) {
//            oauth2AccessTokenMapper.deleteByIds(convertSet(accessTokenDOs, OAuth2AccessTokenDO::getId));
//            oauth2AccessTokenRedisDAO.deleteList(convertSet(accessTokenDOs, OAuth2AccessTokenDO::getAccessToken));
//        }
//
//        // 已过期的情况下，删除刷新令牌
//        if (DateUtils.isExpired(refreshTokenDO.getExpiresTime())) {
//            oauth2RefreshTokenMapper.deleteById(refreshTokenDO.getId());
//            throw exception0(GlobalErrorCodeConstants.UNAUTHORIZED.getCode(), "刷新令牌已过期");
//        }
//
//        // 创建访问令牌
//        return createOAuth2AccessToken(refreshTokenDO, clientDO);
//    }
//
//    @Override
//    public OAuth2AccessTokenDO getAccessToken(String accessToken) {
//        // 优先从 Redis 中获取
//        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenRedisDAO.get(accessToken);
//        if (accessTokenDO != null) {
//            return accessTokenDO;
//        }
//
//        // 获取不到，从 MySQL 中获取访问令牌
//        accessTokenDO = oauth2AccessTokenMapper.selectByAccessToken(accessToken);
//        if (accessTokenDO == null) {
//            // 特殊：从 MySQL 中获取刷新令牌。原因：解决部分场景不方便刷新访问令牌场景
//            // 例如说，积木报表只允许传递 token，不允许传递 refresh_token，导致无法刷新访问令牌
//            // 再例如说，前端 WebSocket 的 token 直接跟在 url 上，无法传递 refresh_token
//            OAuth2RefreshTokenDO refreshTokenDO = oauth2RefreshTokenMapper.selectByRefreshToken(accessToken);
//            if (refreshTokenDO != null && !DateUtils.isExpired(refreshTokenDO.getExpiresTime())) {
//                accessTokenDO = convertToAccessToken(refreshTokenDO);
//            }
//        }
//
//        // 如果在 MySQL 存在，则往 Redis 中写入
//        if (accessTokenDO != null && !DateUtils.isExpired(accessTokenDO.getExpiresTime())) {
//            oauth2AccessTokenRedisDAO.set(accessTokenDO);
//        }
//        return accessTokenDO;
//    }
//
    @Override
    public OAuth2AccessToken checkAccessToken(String accessToken) {

        OAuth2AccessToken token = oauth2Gateway.getAccessToken(accessToken);
        if (token == null) {
            throw new RuntimeException("访问令牌不存在");
        }
//        if (DateUtils.isExpired(accessTokenDO.getExpiresTime())) {
//            throw new RuntimeException("访问令牌已过期");
//        }
        return token;
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public OAuth2AccessTokenDO removeAccessToken(String accessToken) {
//        // 删除访问令牌
//        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectByAccessToken(accessToken);
//        if (accessTokenDO == null) {
//            return null;
//        }
//        oauth2AccessTokenMapper.deleteById(accessTokenDO.getId());
//        oauth2AccessTokenRedisDAO.delete(accessToken);
//        // 删除刷新令牌
//        oauth2RefreshTokenMapper.deleteByRefreshToken(accessTokenDO.getRefreshToken());
//        return accessTokenDO;
//    }
//
//    @Override
//    public PageResult<OAuth2AccessTokenDO> getAccessTokenPage(OAuth2AccessTokenPageReqVO reqVO) {
//        return oauth2AccessTokenMapper.selectPage(reqVO);
//    }
//
    private OAuth2AccessToken createOAuth2AccessToken(OAuth2RefreshToken refreshToken, OAuth2Client client) {
        OAuth2AccessToken accessToken = new OAuth2AccessToken().setAccessToken(generateAccessToken())
                .setUserId(refreshToken.getUserId()).setUserType(refreshToken.getUserType())
                .setUserInfo(buildUserInfo(refreshToken.getUserId(), refreshToken.getUserType()))
                .setClientId(client.getClientId()).setScopes(refreshToken.getScopes())
                .setRefreshToken(refreshToken.getRefreshToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(client.getAccessTokenValiditySeconds()));
        oauth2Gateway.insert(accessToken);
        // todo 记录到 Redis 中
//        oauth2AccessTokenRedisDAO.set(accessTokenDO);
        return accessToken;
    }

    private OAuth2RefreshToken createOAuth2RefreshToken(Long userId, Integer userType, OAuth2Client clientDO, List<String> scopes) {
        OAuth2RefreshToken refreshToken = new OAuth2RefreshToken().setRefreshToken(generateRefreshToken())
                .setUserId(userId).setUserType(userType)
                .setClientId(clientDO.getClientId()).setScopes(scopes)
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getRefreshTokenValiditySeconds()));
        oauth2Gateway.insert(refreshToken);

        return refreshToken;
    }

//    private OAuth2AccessToken convertToAccessToken(OAuth2RefreshToken refreshTokenDO) {
//        OAuth2AccessTokenDO accessTokenDO = BeanUtils.toBean(refreshTokenDO, OAuth2AccessTokenDO.class)
//                .setAccessToken(refreshTokenDO.getRefreshToken());
//        TenantUtils.execute(refreshTokenDO.getTenantId(),
//                        () -> accessTokenDO.setUserInfo(buildUserInfo(refreshTokenDO.getUserId(), refreshTokenDO.getUserType())));
//        return accessTokenDO;
//    }
//

    /**
     * 加载用户信息
     */
    private Map<String, String> buildUserInfo(Long userId, Integer userType) {
        if (userId == null || userId <= 0) {
            return Collections.emptyMap();
        }

        AdminUser user = userGateWay.getUser(userId);
        return MapUtil.builder("nickName", user.getNickname())
                .put("dept", StrUtil.toStringOrNull(user.getDeptId())).build();

    }

    private static String generateAccessToken() {
        return IdUtil.fastSimpleUUID();
    }

    private static String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }

}
