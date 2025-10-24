package com.dream.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test_dream_a")
public class TestADO {
    private Integer id;
    private String colA;
}
