package com.zzy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentParam {

    private String name;
    private Integer degree;
    private Integer clazzId;

    private Integer page = 1;
    private Integer pageSize = 10;

    @Override
    public String toString() {
        return "StudentParam{" +
                "name='" + name + '\'' +
                ", degree=" + degree +
                ", classId=" + clazzId +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
