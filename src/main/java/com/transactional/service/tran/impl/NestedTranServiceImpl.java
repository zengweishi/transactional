package com.transactional.service.tran.impl;

import com.transactional.Entiry.User1;
import com.transactional.Entiry.User2;
import com.transactional.service.user.UserService1;
import com.transactional.service.user.UserService2;
import com.transactional.service.tran.NestedTranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 15:42
 * @Description:
 * 在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，
 * 外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
 */
@Service
public class NestedTranServiceImpl implements NestedTranService {
    @Autowired
    private UserService1 userService1;
    @Autowired
    private UserService2 userService2;

    /**
     * 外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     */
    @Override
    public void notransaction_exception_nested_nested(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addNested(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addNested(user2);
        throw new RuntimeException();
    }

    /**
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
     */
    @Override
    public void notransaction_nested_nested_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addNested(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addNestedException(user2);
    }

    /**
     * 外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
     */
    @Transactional
    @Override
    public void transaction_exception_nested_nested(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addNested(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addNested(user2);
        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
     */
    @Transactional
    @Override
    public void transaction_nested_nested_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addNested(user1);

        User2 user2=new User2();
        user2.setName("李四");
        userService2.addNestedException(user2);
    }

    /**
     * 外围方法开启事务，内部事务为外围事务的子事务，插入“李四”内部方法抛出异常，可以单独对子事务回滚。
     */
    @Transactional
    @Override
    public void transaction_nested_nested_exception_try(){
        User1 user1=new User1();
        user1.setName("张三");
        userService1.addNested(user1);

        User2 user2=new User2();
        user2.setName("李四");
        try {
            userService2.addNestedException(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }
}
