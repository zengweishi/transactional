package com.transactional.service.user;

import com.transactional.Entiry.User1;
import org.springframework.stereotype.Service;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 10:39
 * @Description:
 */

public interface UserService1 {
    public void addRequired(User1 user);

    public void addNoTran(User1 user);

    public void test();

    public void addRequiresNew(User1 user);

    public void addNested(User1 user);
}
