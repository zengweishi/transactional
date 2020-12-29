package com.transactional.test;

import com.transactional.service.tran.NestedTranService;
import com.transactional.service.tran.RequiredTranService;
import com.transactional.service.tran.RequiresNewTranService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 11:17
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionalTest {
    @Autowired
    private RequiredTranService requiredTranService;
    @Autowired
    private RequiresNewTranService requiresNewTranService;
    @Autowired
    private NestedTranService nestedTranService;

    @Test
    public void test1() {
        requiredTranService.notransaction_exception_required_required();
    }

    @Test
    public void test2() {
        requiredTranService.notransaction_required_required_exception();
    }

    @Test
    public void test3() {
        requiredTranService.transaction_exception_required_required();
    }

    @Test
    public void test4() {
        requiredTranService.transaction_exception_no_no();
    }

    @Test
    public void test5() {
        requiredTranService.transaction_required_required_exception();
    }

    @Test
    public void test6() {
        requiredTranService.transaction_required_required_exception_try();
    }

    @Test
    public void test7() {
        requiredTranService.transaction_required_no_exception_try();
    }

    @Test
    public void test8() {
        requiredTranService.transaction_tran_no_exception();
    }

    @Test
    public void test81() {
        requiredTranService.transaction_execption();
    }

    @Test
    public void test9() {
        requiresNewTranService.notransaction_exception_requiresNew_requiresNew();
    }


    @Test
    public void test10() {
        requiresNewTranService.notransaction_requiresNew_requiresNew_exception();
    }

    @Test
    public void test11() {
        requiresNewTranService.transaction_exception_required_requiresNew_requiresNew();
    }

    @Test
    public void test12() {
        requiresNewTranService.transaction_required_requiresNew_requiresNew_exception();
    }

    @Test
    public void test13() {
        requiresNewTranService.transaction_required_requiresNew_requiresNew_exception_try();
    }

    @Test
    public void test14() {
        nestedTranService.notransaction_exception_nested_nested();
    }

    @Test
    public void test15() {
        nestedTranService.notransaction_nested_nested_exception();
    }

    @Test
    public void test16() {
        nestedTranService.transaction_exception_nested_nested();
    }

    @Test
    public void test17() {
        nestedTranService.transaction_nested_nested_exception();
    }

    @Test
    public void test18() {
        nestedTranService.transaction_nested_nested_exception_try();
    }
}
