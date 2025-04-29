package com.zzy.mapper;

import com.zzy.pojo.Dept;
import com.zzy.pojo.Emp;
import com.zzy.pojo.EmpExpr;
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


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, dept_id, create_time, update_time, entry_date) " +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{deptId}, #{createTime}, #{updateTime}, #{entryDate})")
    void insert(Emp emp);


    void deleteEmp(List<Integer> ids);

    Emp searchById(Integer id);

    void update(Emp emp);

    @Select("SELECT id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time FROM emp")
    List<Emp> getAll();

    List<Emp> findByDeptId(Integer id);
}
