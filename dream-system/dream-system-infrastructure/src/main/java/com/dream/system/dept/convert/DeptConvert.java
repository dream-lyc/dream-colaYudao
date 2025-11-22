package com.dream.system.dept.convert;


import com.dream.system.dept.DO.DeptDO;
import com.system.dept.model.Dept;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface DeptConvert {

    Dept convert2Entity(DeptDO DO);

    DeptDO convert2DO(Dept dept);

}
