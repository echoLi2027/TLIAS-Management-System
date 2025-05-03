package com.zzy.service;

import com.zzy.pojo.*;

import java.util.List;


public interface EmpService {

    /**
     * find emp by current page and page size
     * @param page
     * @param pageSize
     * @return
     */
//    PageResult queryPage(Integer page, Integer pageSize);

    /**
     * find emp by search condition and page and page size
     * @param param
     * @return
     */
    PageResult queryPage(QueryParam param, Integer page, Integer pageSize);

    /**
     * insert emp and emp_expr
     * @param emp
     * @throws Exception
     */
    void insert(Emp emp) throws Exception;

    /**
     * delete emp which id in ids
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * search emp and emp_expr by id
     * @param id
     * @return
     */
    Emp searchById(Integer id);

    /**
     * update emp
     * @param emp
     */
    void update(Emp emp);

    /**
     * get all employees in the company
     * @return
     */
    List<Emp> getAll();

    /**
     * find emp by username and password
     * @param emp
     * @return
     */
    LoginInfo findByUsernameAndPassword(Emp emp);
}
