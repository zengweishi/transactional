package com.transactional.service.tran;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 13:35
 * @Description:
 */
public interface RequiredTranService {
    public void notransaction_exception_required_required();
    public void notransaction_required_required_exception();
    public void transaction_exception_required_required();
    public void transaction_exception_no_no();
    public void transaction_required_required_exception();
    public void transaction_required_required_exception_try();
    public void transaction_required_no_exception_try();
    public void transaction_tran_no_exception();
    public void transaction_execption();
}
