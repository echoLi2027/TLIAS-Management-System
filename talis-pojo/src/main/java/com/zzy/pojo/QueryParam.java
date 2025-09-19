package com.zzy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryParam {

    private String name;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    @Override
    public String toString() {
        return "QueryParam{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
