package com.zzy.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * 工作经历
 */
@Data
public class EmpExpr {
    private Integer id; //ID
    private Integer empId; //员工ID
    private LocalDate begin; //开始时间
    private LocalDate end; //结束时间
    private String company; //公司名称
    private String job; //职位


    @Override
    public String toString() {
        return "EmpExpr{" +
                "id=" + id +
                ", empId=" + empId +
                ", begin=" + begin +
                ", end=" + end +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
