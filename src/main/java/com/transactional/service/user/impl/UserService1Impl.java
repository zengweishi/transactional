package com.transactional.service.user.impl;

import com.transactional.Entiry.User1;
import com.transactional.dao.User1Mapper;
import com.transactional.service.user.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 11:13
 * @Description:
 */
@Service
public class UserService1Impl implements UserService1 {
    @Autowired
    private User1Mapper user1Mapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User1 user){
        user1Mapper.insert(user);
    }

    @Override
    public void addNoTran(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    public void test() {
        System.out.println(123);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User1 user){
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNested(User1 user){
        user1Mapper.insert(user);
    }
}
