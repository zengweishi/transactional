package com.transactional.service.tran;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 15:42
 * @Description:
 */
public interface NestedTranService {
    public void notransaction_exception_nested_nested();

    public void notransaction_nested_nested_exception();

    public void transaction_exception_nested_nested();

    public void transaction_nested_nested_exception();

    public void transaction_nested_nested_exception_try();
}
