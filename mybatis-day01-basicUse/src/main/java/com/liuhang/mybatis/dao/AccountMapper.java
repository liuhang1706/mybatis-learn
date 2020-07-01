package com.liuhang.mybatis.dao;

import com.liuhang.mybatis.entity.Account;

import java.util.List;

public interface AccountMapper {

    /**
     * 查询所有账户，带有用户信息
     * 一对一
     * @return
     */
    List<Account> listAllAccounts();

    /**
     * 查询所有账户，带有用户信息
     * 一对一 ，延时加载用户信息
     * @return
     */
    List<Account> listAllAccountsWithLazyUser();


    /**
     * 根据用户id查询账户
     *
     * @param uid 用户id
     * @return
     */
    List<Account> getAccountByUid(Integer uid);
}
