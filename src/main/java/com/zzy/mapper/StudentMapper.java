package com.zzy.mapper;

import com.zzy.pojo.Student;
import com.zzy.pojo.StudentParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from student where clazz_id = #{classId}")
    List<Student> findByClazzId(Integer classId);

    List<Student> pageQuery(StudentParam param);

    @Insert("INSERT INTO student (name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) " +
            "VALUES (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{createTime}, #{updateTime})")
    void addStu(Student student);

    @Select("SELECT id, name, no, gender, phone, id_card AS idCard, is_college AS isCollege, address, degree, graduation_date AS graduationDate, clazz_id AS clazzId, violation_count AS violationCount, violation_score AS violationScore, create_time AS createTime, update_time AS updateTime FROM student where id = #{id}")
    Student findById(Integer id);

    void updateStu(Student student);

    void deleteByIds(List<Integer> ids);

    void violationProcess(Integer id, Short score, LocalDateTime updateTime);
}
