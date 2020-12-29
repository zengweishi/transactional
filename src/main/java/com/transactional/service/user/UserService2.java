package com.transactional.service.user;

import com.transactional.Entiry.User2;
import org.springframework.stereotype.Service;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 10:39
 * @Description:
 */
public interface UserService2 {
    public void addRequired(User2 user);

    public void addRequiredException(User2 user);

    void addNoTran(User2 user2);

    void addNoTranException(User2 user2);

    public void addRequiresNew(User2 user);

    public void addRequiresNewException(User2 user);

    public void addNested(User2 user);

    public void addNestedException(User2 user);
}
