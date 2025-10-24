package com.dream.dept.convert;


import com.dream.dept.DO.DeptDO;
import com.dream.dept.model.Dept;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface DeptConvert {

    Dept convert2Entity(DeptDO DO);

}
