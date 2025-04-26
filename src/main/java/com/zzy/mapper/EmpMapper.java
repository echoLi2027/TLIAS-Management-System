package com.zzy.mapper;

import com.zzy.pojo.Dept;
import com.zzy.pojo.Emp;
import com.zzy.pojo.QueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Select("select count(*) from emp")
    Long total();

    @Select("select e.*, dept.name deptName  from emp e left join dept on e.dept_id=dept.id limit #{start}, #{pageSize}")
    List<Emp> queryPage(int start, Integer pageSize);

    @Select("select e.*, dept.name deptName  from emp e left join dept on e.dept_id=dept.id")
    List<Emp> list();

    List<Emp> search(@Param("param") QueryParam param);
}
