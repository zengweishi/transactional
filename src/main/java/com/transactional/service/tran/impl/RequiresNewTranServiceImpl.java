package com.transactional.service.tran.impl;

import com.transactional.Entiry.User1;
import com.transactional.Entiry.User2;
import com.transactional.service.user.UserService1;
import com.transactional.service.user.UserService2;
import com.transactional.service.tran.RequiresNewTranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 14:56
 * @Description:
 * 在外围方法开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法依然会单独开启独立事务，
 * 且与外部方法事务也独立，内部方法之间、内部方法和外部方法事务均相互独立，互不干扰。
 */
@Service
public class RequiresNewTranServiceImpl implements RequiresNewTranService {
    @Autowired
    private UserService1 userService1;
    @Autowired
    private UserService2 userService2;

    /**
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,外围方法抛出异常回滚不会影响内部方法。
     */
    @Override
    public void notransaction_exception_requiresNew_requiresNew(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequiresNew(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequiresNew(user2);
        throw new RuntimeException();

    }

    /**
     * 外围方法没有开启事务，插入“张三”方法和插入“李四”方法分别开启自己的事务，插入“李四”方法抛出异常回滚，其他事务不受影响。
     */
    @Override
    public void notransaction_requiresNew_requiresNew_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequiresNew(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequiresNewException(user2);
    }

    /**
     * 外围方法开启事务，插入“张三”方法和外围方法一个事务，
     * 插入“李四”方法、插入“王五”方法分别在独立的新建事务中，
     * 外围方法抛出异常只回滚和外围方法同一事务的方法，故插入“张三”的方法回滚。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_requiresNew_requiresNew(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequiresNew(user2);

        User2 user3=new User2();
        user3.setName("王五");
        userService2.addRequiresNew(user3);
        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务，插入“张三”方法和外围方法一个事务，
     * 插入“李四”方法、插入“王五”方法分别在独立的新建事务中。
     * 插入“王五”方法抛出异常，首先插入 “王五”方法的事务被回滚，异常继续抛出被外围方法感知，
     * 外围方法事务亦被回滚，故插入“张三”方法也被回滚。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequiresNew(user2);

        User2 user3=new User2();
        user3.setName("王五");
        userService2.addRequiresNewException(user3);
    }

    /**
     * 外围方法开启事务，插入“张三”方法和外围方法一个事务，
     * 插入“李四”方法、插入“王五”方法分别在独立的新建事务中。
     * 插入“王五”方法抛出异常，首先插入“王五”方法的事务被回滚，异常被catch不会被外围方法感知，
     * 外围方法事务不回滚，故插入“张三”方法插入成功。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception_try(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addRequiresNew(user2);
        User2 user3=new User2();
        user3.setName("王五");
        try {
            userService2.addRequiresNewException(user3);
        } catch (Exception e) {
            System.out.println("回滚");
        }
    }
}
