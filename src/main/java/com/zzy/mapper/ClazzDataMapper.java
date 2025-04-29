package com.zzy.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzDataMapper {


    List<Map<String, Object>> clazzCount();

    List<Map<String, Object>> degreeCount();
}
