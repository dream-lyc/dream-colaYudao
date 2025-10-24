package com.dream.framework.core.util;

import com.alibaba.cola.dto.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;

public class MyBatisUtils {
    public static <T> PageResponse<T> convertPage(IPage<T> page) {
        return PageResponse.of(page.getRecords(), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
    }
}
