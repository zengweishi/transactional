package com.transactional.service.tran.impl;

import com.transactional.Entiry.User1;
import com.transactional.Entiry.User2;
import com.transactional.dao.User1Mapper;
import com.transactional.service.user.UserService1;
import com.transactional.service.user.UserService2;
import com.transactional.service.tran.RequiredTranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 13:35
 * @Description:
 * 在外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，
 * 所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
 */
@Service
public class RequiredTranServiceImpl implements RequiredTranService {
    @Autowired
    private UserService1 userService1;
    @Autowired
    private UserService2 userService2;
    @Autowired
    private User1Mapper user1Mapper;

    /**
     * 外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，
     * 外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     */
    @Override
    public void notransaction_exception_required_required(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequired(user2);

        throw new RuntimeException();
    }

    /**
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,
     * 所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
     */
    @Override
    public void notransaction_required_required_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequiredException(user2);
    }

    /**
     * 外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequired(user2);

        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务，内部方法没有事务,外围方法异常，内部方法回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_no_no(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addNoTran(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addNoTran(user2);

        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务，内部方法没有事务,内部方法异常，内部方法均回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_tran_no_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addNoTran(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addNoTranException(user2);

    }@Override
    public void transaction_execption() {

    }

    /**
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequiredException(user2);
    }

    /**
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，即使方法被catch不被外围方法感知，整个事务依然回滚。\
     * 这是因为内部方法的事务有异常，已经mark为rollbackOnly了，而外围方法将其try...catch了，无法感知还会去commit，
     * 而此时是无法提交事务的，所以会抛出UnexpectedRollbackException异常来告知事务回滚了。
     */
    @Transactional
    @Override
    public void transaction_required_required_exception_try(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        try {
            userService2.addRequiredException(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }

    public void test() {
        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequiredException(user2);
    }


    /**
     * 外围方法开启事务，内部方法没有开启事务，内部方法异常，外围catch不回滚
     */
    @Transactional
    @Override
    public void transaction_required_no_exception_try(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        try {
            userService2.addNoTranException(user2);
        } catch (Exception e) {
            System.out.println("异常捕获");
        }
    }




}
