package com.zzy.mapper;

import com.zzy.pojo.Dept;
import com.zzy.pojo.EmpExpr;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void insertExpr(List<EmpExpr> exprList);

    void deleteEmpExpr(List<Integer> ids);
}
