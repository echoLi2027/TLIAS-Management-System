package com.zzy.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpJobDataMapper {

//mistake of mybatis plugin, without this still work@MapKey("pos")
    @MapKey("pos")
    List<Map<String, Object>> search();

    @MapKey("name")
    List<Map<String, Object>> genderData();
}
