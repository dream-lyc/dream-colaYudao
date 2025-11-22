package com.dream.system.dept.convert;

import com.dream.system.dept.DTO.DeptDTO;
import com.system.dept.model.Dept;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AppDeptConvert {

    DeptDTO convert2DTO(Dept dept);

    Dept convert2Entity(DeptDTO deptDTO);

}
