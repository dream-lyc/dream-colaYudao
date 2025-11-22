package com.system.oauth2.model;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OAuth2 授权码 DO
 *
 * @author 芋道源码
 */
@Data
public class OAuth2Code {

    /**
     * 编号，数据库递增
     */
    private Long id;
    /**
     * 授权码
     */
    private String code;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     *
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;
    /**
     * 客户端编号
     *
     * 关联 {@link OAuth2Client#getClientId()}
     */
    private String clientId;
    /**
     * 授权范围
     */
    private List<String> scopes;
    /**
     * 重定向地址
     */
    private String redirectUri;
    /**
     * 状态
     */
    private String state;
    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

}
