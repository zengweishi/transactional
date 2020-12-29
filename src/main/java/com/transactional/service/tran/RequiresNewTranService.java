package com.transactional.service.tran;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 14:56
 * @Description:
 */
public interface RequiresNewTranService {
    public void notransaction_exception_requiresNew_requiresNew();
    public void notransaction_requiresNew_requiresNew_exception();
    public void transaction_exception_required_requiresNew_requiresNew();
    public void transaction_required_requiresNew_requiresNew_exception();
    public void transaction_required_requiresNew_requiresNew_exception_try();
}
