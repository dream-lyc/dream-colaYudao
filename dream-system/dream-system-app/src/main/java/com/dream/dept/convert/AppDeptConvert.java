package com.dream.dept.convert;

import com.dream.dept.DTO.DeptDTO;
import com.dream.dept.model.Dept;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AppDeptConvert {

    DeptDTO convert2DTO(Dept dept);

    Dept convert2Entity(DeptDTO deptDTO);

}
