package com.dream.dept.DTO;

import com.dream.dept.enums.StatusEnum;
import lombok.Data;

@Data
public class DeptDTO {
    /**
     * 部门ID
     */
    private Long id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 父部门ID
     *
     * 关联 {@link #id}
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 负责人
     *
     * 关联 {@link AdminUserDO#getId()}
     */
    private Long leaderUserId;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 部门状态
     */
    private StatusEnum status;

}
