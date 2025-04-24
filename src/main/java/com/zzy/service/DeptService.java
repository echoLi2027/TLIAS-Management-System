package com.zzy.service;

import com.zzy.pojo.Dept;

import java.util.List;


public interface DeptService {

    /**
     * find all dept info
     */
    public List<Dept> findAll();

    /**
     * delete dept by id
     * @param id
     */
    void delById(Integer id);

    /**
     * insert a dept
     * @param dept
     */
    void insert(Dept dept);

    /**
     *
     */
    Dept searchById(Integer id);

    /**
     * update dept info
     * @param dept
     */
    void update(Dept dept);
}
