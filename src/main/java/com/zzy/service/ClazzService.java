package com.zzy.service;

import com.zzy.pojo.Clazz;
import com.zzy.pojo.ClazzParam;
import com.zzy.pojo.EmpLog;
import com.zzy.pojo.PageResult;

import java.util.List;

public interface ClazzService {


    /**
     * select clazz by page
     * @param clazzParam
     * @return
     */
    PageResult queryClassPage(ClazzParam clazzParam);

    /**
     * add new clazz into clazz table
     * @param clazz
     */
    void add(Clazz clazz);

    /**
     * search clazz by id
     * @param id
     * @return
     */
    Clazz searchById(Integer id);

    /**
     * update clazz
     * @param clazz
     */
    void update(Clazz clazz);

    /**
     * delete clazz by id
     * @param id
     */
    void deleteById(Integer id);

    List<Clazz> findAll();
}
