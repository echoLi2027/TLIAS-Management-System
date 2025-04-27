package com.zzy.service.impl;

import com.zzy.mapper.EmpLogMapper;
import com.zzy.pojo.EmpLog;
import com.zzy.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

//    no matter whether there is already have transaction,
//    it will create a new transaction for this method
//    which means if another transaction which has entangled with this transaction
//    that one is rollback,this one won't.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
